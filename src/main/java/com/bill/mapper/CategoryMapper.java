package com.bill.mapper;

import com.bill.vo.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {

    void add(Category category);

    List<Category> list(Integer userId);

    Category findById(Integer categoryId, Integer createUserId);

    void update(Category category);

    void delete(Integer categoryId, Integer createUserId);
}
