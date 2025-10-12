package com.byc.mpcodegenerator.system.service.impl;

import com.byc.mpcodegenerator.system.entity.User;
import com.byc.mpcodegenerator.system.mapper.UserMapper;
import com.byc.mpcodegenerator.system.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息 服务实现类
 * </p>
 *
 * @author byc
 * @since 2025-10-12
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
