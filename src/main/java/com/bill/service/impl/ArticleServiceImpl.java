package com.bill.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bill.mapper.ArticleMapper;
import com.bill.model.PageBean;
import com.bill.model.Result;
import com.bill.service.ArticleService;
import com.bill.util.ThreadLocalUtil;
import com.bill.vo.Article;
import com.github.pagehelper.Page;
import com.github.pagehelper.page.PageMethod;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    /**
     * 查詢文章(分頁)
     */
    @Override
    public Result<PageBean<Article>> list(Integer pageNumber, Integer pageSize, Integer categoryId, String state) {
    	// PageHelper分頁查詢
    	PageMethod.startPage(pageNumber, pageSize);
    	
    	Integer userId = ThreadLocalUtil.getUserId();
    	Page<Article> list = articleMapper.list(categoryId, state, userId);
    	
    	PageBean<Article> pageBean = new PageBean<>();
    	pageBean.setTotal(list.getTotal());
    	pageBean.setItems(list.getResult());
    	
    	return Result.success(pageBean);
    }
    
    /**
     * 新增文章
     */
    @Override
    public Result<String> add(Article article) {
        Integer userId = ThreadLocalUtil.getUserId();
        article.setCreateUserId(userId);
        articleMapper.add(article);
        return Result.success();
    }

    /**
     * 修改文章
     */
	@Override
	public Result<String> update(Article article) {
		Integer userId = ThreadLocalUtil.getUserId();
        article.setCreateUserId(userId);
		articleMapper.update(article);
		return Result.success();
	}

	@Override
	public Result<String> delete(String articleId) {
		Integer createUserId = ThreadLocalUtil.getUserId();
		articleMapper.delete(articleId, createUserId);
		return Result.success();
	}
}
