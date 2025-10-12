package com.byc.mpcodegenerator.system.service.impl;

import com.byc.mpcodegenerator.system.entity.Employee;
import com.byc.mpcodegenerator.system.mapper.EmployeeMapper;
import com.byc.mpcodegenerator.system.service.EmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 员工信息 服务实现类
 * </p>
 *
 * @author byc
 * @since 2025-10-12
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

}
