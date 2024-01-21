package com.bill.controller;

import com.bill.model.Result;
import com.bill.service.CategoryService;
import com.bill.vo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public Result<List<Category>> list() {
        return Result.success(categoryService.list());
    }

    @GetMapping("detail")
    public Result<Category> detail(Integer categoryId) {
        return Result.success(categoryService.findById(categoryId));
    }

    @PostMapping
    public Result<String> add(@RequestBody @Validated(Category.Add.class) Category category) {
        return categoryService.add(category);
    }

    @PutMapping
    public Result<String> update(@RequestBody @Validated(Category.Update.class) Category category) {
        return categoryService.update(category);
    }

    @DeleteMapping
    public Result<String> delete(Integer categoryId) {
        return categoryService.delete(categoryId);
    }

}
