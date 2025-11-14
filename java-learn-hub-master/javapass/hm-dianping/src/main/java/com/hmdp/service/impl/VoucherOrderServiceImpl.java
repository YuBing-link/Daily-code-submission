package com.hmdp.service.impl;

import com.hmdp.config.RedissonConfig;
import com.hmdp.dto.Result;
import com.hmdp.entity.SeckillVoucher;
import com.hmdp.entity.VoucherOrder;
import com.hmdp.mapper.VoucherOrderMapper;
import com.hmdp.service.ISeckillVoucherService;
import com.hmdp.service.IVoucherOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hmdp.utils.RedisIdWork;
import com.hmdp.utils.SimpleRedisLock;
import com.hmdp.utils.UserHolder;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Locale;
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
    private RedisIdWork RedisIdWork;

    private static final DefaultRedisScript<Long> UNLOCK_SCRIPT;
    static {
        UNLOCK_SCRIPT = new DefaultRedisScript<>();
        UNLOCK_SCRIPT.setLocation( new ClassPathResource("seckill.lua"));
        UNLOCK_SCRIPT.setResultType(Long.class);
    }

    @Autowired
    private RedisIdWork redisIdWork;

    @Override
    public Result seckillVoucher(Long voucherId) {
        Long usrId = UserHolder.getUser().getId();
        System.out.println(redisTemplate.opsForValue().get("seckill:stock:" + voucherId));
        Long executeResult =  redisTemplate.execute(UNLOCK_SCRIPT,
                Collections.emptyList(),
                voucherId.toString(),
                usrId.toString()
        );
        int r = executeResult.intValue();
        if (r != 0){
            return Result.fail(r== 1?"库存不足":"请勿重复下单");
        }
        long voucherOrder = redisIdWork.nextId("VoucherOrder");

        return Result.ok(voucherOrder);
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
    public  Result queryVoucherUser(Long voucherId,Long userId) {


        Integer count = query().eq("voucher_id", voucherId)
                .eq("user_id", userId)
                .count();
        if (count > 0) {
            return Result.fail("该产品仅限购买一次");
        }
        boolean date = seckillVoucherService.update().setSql("stock=stock-1")
                .eq("voucher_id", voucherId)
                .gt("stock",0)
                .update();
        if (!date) {
            return Result.fail("库存不足");
        }
        long orderId = RedisIdWork.nextId("VoucherOrder");
        VoucherOrder voucherOrder = new VoucherOrder();
        voucherOrder.setUserId(userId);
        voucherOrder.setId(orderId);
        voucherOrder.setVoucherId(voucherId);
        save(voucherOrder);
        return Result.ok(orderId);

    }
}
