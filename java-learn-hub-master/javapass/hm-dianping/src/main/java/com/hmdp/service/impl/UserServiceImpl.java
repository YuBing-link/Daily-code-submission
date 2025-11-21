package com.hmdp.service.impl;

import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hmdp.annotation.RedisAutoConvert;
import com.hmdp.annotation.TimeLog;
import com.hmdp.dto.LoginFormDTO;
import com.hmdp.dto.Result;
import com.hmdp.dto.UserDTO;
import com.hmdp.entity.Mail;
import com.hmdp.entity.User;
import com.hmdp.mapper.UserMapper;
import com.hmdp.service.IUserService;
import com.hmdp.utils.MailUtils;
import com.hmdp.utils.RegexUtils;
import com.hmdp.utils.SystemConstants;
import cn.hutool.core.bean.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import org.springframework.stereotype.Service;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.hmdp.utils.RedisConstants.LOGIN_CODE_KEY;
import static com.hmdp.utils.RedisConstants.LOGIN_USER_KEY;


/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 虎哥
 * @since 2021-12-22
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private Mail mail;
    @Autowired
    private MailUtils mailUtils;
    @Override
    public Result sendCode(String Mail, HttpSession session) {
        //1. 校验手机号
//        if (RegexUtils.isEmailInvalid(Mail)&&RegexUtils.isPhoneInvalid(Mail)) {
//            //手机号不符合要求
//            return Result.fail("邮箱格式错误");
//        }
        //2. 生成验证码
        String code = RandomUtil.randomNumbers(6);
        //3. 保存验证码到redis
        redisTemplate.opsForValue().set(LOGIN_CODE_KEY + Mail, code, 5, TimeUnit.MINUTES);
//        mail.setRecipient(Mail);
//        mail.setSubject("黑马点评验证码");
//        mail.setContent(mail.getRecipient()+"您的验证码为："+code);
//        mailUtils.sendSimpleMail(mail);
        System.out.println("----------------验证码----------------");
        System.out.println(code);
        return Result.ok("发送成功");
    }

    @Override
    @RedisAutoConvert
    public Result login(LoginFormDTO loginForm, HttpSession session) {
        String phone = loginForm.getPhone();
        String code = loginForm.getCode();
        User user = query().eq("phone", phone).one();
        String redisCode = redisTemplate.opsForValue().get(LOGIN_CODE_KEY + phone).toString();
        if (!code.equals(redisCode)) {
            return Result.fail("验证码错误或验证码过期");
        }
        if (user == null) {
            //用户不存在
            user = CreateUser(phone);
            save(user);
        }
        UserDTO userDTO = new UserDTO();
        BeanUtil.copyProperties(user, userDTO);
        Map<String, Object> userDTOMap = BeanUtil.beanToMap(userDTO);
        String token = UUID.randomUUID().toString(true);
        redisTemplate.opsForHash().putAll( LOGIN_USER_KEY+token, userDTOMap);
        redisTemplate.expire(token, 30, TimeUnit.MINUTES);
        System.out.println(token);
        return Result.ok(token);
    }

    @TimeLog
    private User CreateUser(String phone) {
        User user = new User();
        user.setPhone(phone);
        user.setNickName(SystemConstants.USER_NICK_NAME_PREFIX + RandomUtil.randomString(10));
        return user;
    }

}
