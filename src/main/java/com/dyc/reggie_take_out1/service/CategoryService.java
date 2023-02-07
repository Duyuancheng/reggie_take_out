package com.dyc.reggie_take_out1.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dyc.reggie_take_out1.entity.Category;

public interface CategoryService extends IService<Category> {
    public void remove(Long id);
}
