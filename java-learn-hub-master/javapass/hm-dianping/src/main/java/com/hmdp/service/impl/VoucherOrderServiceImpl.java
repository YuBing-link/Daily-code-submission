package com.hmdp.service.impl;

import com.hmdp.dto.Result;
import com.hmdp.entity.VoucherOrder;
import com.hmdp.mapper.VoucherOrderMapper;
import com.hmdp.service.ISeckillVoucherService;
import com.hmdp.service.IVoucherOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hmdp.utils.RedisIdWork;
import com.hmdp.utils.ThreadPool;
import com.hmdp.utils.UserHolder;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.connection.stream.Consumer;
import org.springframework.data.redis.connection.stream.StreamOffset;
import org.springframework.data.redis.connection.stream.StreamReadOptions;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.util.Collections;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 虎哥
 * @since 2021-12-22
 */
@Service
public class VoucherOrderServiceImpl extends ServiceImpl<VoucherOrderMapper, VoucherOrder> implements IVoucherOrderService {
   @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private ISeckillVoucherService seckillVoucherService;
    @Autowired
    private RedisIdWork redisIdWork;
    @Autowired
    private ThreadPool threadPool;
    @Autowired
    private RedissonClient redissonClient;

    private static final DefaultRedisScript<Long> UNLOCK_SCRIPT;
    private static final String queueStream = "stream.ordes";
    static {
        UNLOCK_SCRIPT = new DefaultRedisScript<>();
        UNLOCK_SCRIPT.setLocation( new ClassPathResource("seckill.lua"));
        UNLOCK_SCRIPT.setResultType(Long.class);
    }


    @PostConstruct
    private void init(){
        threadPool.threadPoolExecutor.submit(new Thread(() -> {
            while (true) {
                try {
                   redisTemplate.opsForStream().read(
                           Consumer.from('g1','c1'),
                           StreamReadOptions.empty().count(1).block(Duration.ofSeconds(2)),
                           StreamOffset.create()
                   )


                } catch (Exception e) {
                    log.error("处理订单异常", e);
                }
            }
        }));
    }
    private IVoucherOrderService proxy;
    private void handleVoucherOrder(VoucherOrder order) {
        Long userId = order.getUserId();
        RLock rLock = redissonClient.getLock("voucher:order:" + userId);

        try {
            if (!rLock.tryLock(3,10, TimeUnit.SECONDS)) {
                log.error("获取锁失败");
                return;
            }
            proxy.queryVoucherUser(order.getVoucherId(), order.getUserId());

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            rLock.unlock();
        }
    }

    @Override
    public Result seckillVoucher(Long voucherId) {
        Long usrId = UserHolder.getUser().getId();
        long voucherOrderId = redisIdWork.nextId("VoucherOrder");
        Long executeResult =  redisTemplate.execute(UNLOCK_SCRIPT,
                Collections.emptyList(),
                voucherId.toString(),
                usrId.toString(),
                String.valueOf(voucherOrderId)
        );
        int r = executeResult.intValue();
        if (r != 0){
            return Result.fail(r== 1?"库存不足":"请勿重复下单");
        }

        // 保存订单
        proxy = (IVoucherOrderService) AopContext.currentProxy();

        return Result.ok(voucherOrderId);
    }
    /*
            SeckillVoucher SVer = seckillVoucherService.getById(voucherId);
        if (SVer.getStock() < 1) {
            return Result.fail("库存不足");
        }
        if (LocalDateTime.now().isBefore(SVer.getBeginTime())){
            return Result.fail("秒杀尚未开始");
        }
        if (LocalDateTime.now().isAfter(SVer.getEndTime())){
            return Result.fail("秒杀已经结束");
        }
        Long userId = UserHolder.getUser().getId();
        RLock rLock = redissonClient.getLock("voucher:order:" + userId);

        try {
            if (!rLock.tryLock(3,10, TimeUnit.SECONDS)) {
                return Result.fail("请勿重复下单");
            }
            IVoucherOrderService proxy = (IVoucherOrderService) AopContext.currentProxy();
            return proxy.queryVoucherUser(voucherId, userId);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            rLock.unlock();
        }
     */
    @Transactional
    public void queryVoucherUser(Long voucherId, Long userId) {


        Integer count = query().eq("voucher_id", voucherId)
                .eq("user_id", userId)
                .count();
        if (count > 0) {
            log.error("用户已经购买过");
            return ;
        }
        boolean date = seckillVoucherService.update().setSql("stock=stock-1")
                .eq("voucher_id", voucherId)
                .gt("stock",0)
                .update();
        if (!date) {
            log.error("库存不足");
            return ;
        }
        long orderId = redisIdWork.nextId("VoucherOrder");
        VoucherOrder voucherOrder = new VoucherOrder();
        voucherOrder.setUserId(userId);
        voucherOrder.setId(orderId);
        voucherOrder.setVoucherId(voucherId);
        save(voucherOrder);
    }
}
