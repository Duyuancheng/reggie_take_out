package com.dyc.reggie_take_out1.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dyc.reggie_take_out1.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
}
