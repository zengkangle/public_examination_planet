package com.gcu.public_examination_planet.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gcu.public_examination_planet.common.Result;
import com.gcu.public_examination_planet.domain.Article;
import com.gcu.public_examination_planet.service.ArticleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author HealMe
 * @Description
 * @date 2024/2/17 15:47
 **/
@RestController
@RequestMapping("/article")
public class ArticleController {
    @Resource
    ArticleService articleService;

    /**
     * 添加文章
     * @param article
     * @return
     */
    @PostMapping("/saveArticle")
    public Result saveArticle(@RequestBody Article article) {
        return Result.success(articleService.save(article));
    }

    /**
     * 根据类型获取文章列表
     * @param articleType
     * @return
     */
    @GetMapping("/getArticleListOfType")
    public Result getArticleListOfType(@RequestParam("articleType") String articleType) {
        return Result.success(articleService.list(new QueryWrapper<Article>().select(Article.class, info ->!info.getColumn().equals("article_content")).eq("article_type",articleType).orderByDesc("create_time").last("limit 6")));
    }

    /**
     * 根据文章id获取文章内容
     * @param articleId
     * @return
     */
    @GetMapping("/getArticleInfo")
    public Result getArticleInfo(@RequestParam("articleId") Integer articleId) {
        return Result.success(articleService.getById(articleId));
    }
}
