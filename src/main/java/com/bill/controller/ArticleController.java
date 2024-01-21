package com.bill.controller;

import com.bill.model.PageBean;
import com.bill.model.Result;
import com.bill.service.ArticleService;
import com.bill.vo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    /**
     * 查詢文章(分頁)
     */
    @GetMapping
    public Result<PageBean<Article>> list(
    		Integer pageNumber,
    		Integer pageSize,
    		Integer categoryId,
    		String state) {
    	return articleService.list(pageNumber, pageSize, categoryId, state);
    }
    
    /**
     * 新增文章
     */
    @PostMapping
    public Result<String> add(@RequestBody @Validated Article article) {
        return articleService.add(article);
    }
    
    /**
     * 修改文章
     */
    @PutMapping
    public Result<String> update(@RequestBody @Validated Article article) {
        return articleService.update(article);
    }
    
    /**
     * 刪除文章
     */
    @DeleteMapping
    public Result<String> delete(String articleId) {
		return articleService.delete(articleId);
	}

}
