package com.bill.service;

import com.bill.model.PageBean;
import com.bill.model.Result;
import com.bill.vo.Article;

public interface ArticleService {

    Result<String> add(Article article);

    Result<PageBean<Article>> list(Integer pageNumber, Integer pageSize, Integer categoryId, String state);

	Result<String> update(Article article);

	Result<String> delete(String articleId);
}
