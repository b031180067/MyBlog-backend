package com.bill.service;

import com.bill.model.Result;
import com.bill.vo.Category;

import java.util.List;

public interface CategoryService {

    Result<String> add(Category category);

    List<Category> list();

    Category findById(Integer categoryId);

    Result<String> update(Category category);

    Result<String> delete(Integer categoryId);
}
