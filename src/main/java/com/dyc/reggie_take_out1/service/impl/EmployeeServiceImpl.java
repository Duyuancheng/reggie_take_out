package com.dyc.reggie_take_out1.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dyc.reggie_take_out1.entity.Employee;
import com.dyc.reggie_take_out1.mapper.EmployeeMapper;
import com.dyc.reggie_take_out1.service.EmployeeService;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
}
