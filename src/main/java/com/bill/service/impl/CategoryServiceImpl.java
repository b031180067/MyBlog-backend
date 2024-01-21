package com.bill.service.impl;

import com.bill.mapper.CategoryMapper;
import com.bill.model.Result;
import com.bill.service.CategoryService;
import com.bill.util.ThreadLocalUtil;
import com.bill.vo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> list() {
        Integer userId = ThreadLocalUtil.getUserId();
        return categoryMapper.list(userId);
    }

    @Override
    public Category findById(Integer categoryId) {
        Integer createUserId = ThreadLocalUtil.getUserId();
        return categoryMapper.findById(categoryId, createUserId);
    }

    @Override
    public Result<String> add(Category category) {
        Integer userId = ThreadLocalUtil.getUserId();
        category.setCreateUserId(userId);
        categoryMapper.add(category);
        return Result.success();
    }

    @Override
    public Result<String> update(Category category) {
        Integer userId = ThreadLocalUtil.getUserId();
        category.setCreateUserId(userId);
        categoryMapper.update(category);
        return Result.success();
    }

    @Override
    public Result<String> delete(Integer categoryId) {
        Integer createUserId = ThreadLocalUtil.getUserId();
        categoryMapper.delete(categoryId, createUserId);
        return Result.success();
    }

}
