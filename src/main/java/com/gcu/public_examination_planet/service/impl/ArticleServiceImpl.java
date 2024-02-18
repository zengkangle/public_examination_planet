package com.gcu.public_examination_planet.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gcu.public_examination_planet.domain.Article;
import com.gcu.public_examination_planet.service.ArticleService;
import com.gcu.public_examination_planet.mapper.ArticleMapper;
import org.springframework.stereotype.Service;

/**
* @author HealMe
* @description 针对表【article(文章)】的数据库操作Service实现
* @createDate 2024-02-17 16:01:53
*/
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article>
    implements ArticleService{

}




