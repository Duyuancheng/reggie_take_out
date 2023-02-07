package com.dyc.reggie_take_out1.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dyc.reggie_take_out1.entity.AddressBook;
import com.dyc.reggie_take_out1.mapper.AddressBookMapper;
import com.dyc.reggie_take_out1.service.AddressBookService;
import org.springframework.stereotype.Service;

@Service
public class AddressBookServiceImpl extends ServiceImpl<AddressBookMapper, AddressBook> implements AddressBookService {
}
