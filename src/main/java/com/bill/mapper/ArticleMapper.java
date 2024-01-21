package com.bill.mapper;

import com.bill.vo.Article;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArticleMapper {

	Page<Article> list(Integer categoryId, String state, Integer userId);
	
    void add(Article article);
    
    void update(Article article);

	void delete(String articleId, Integer createUserId);

}
