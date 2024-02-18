package com.gcu.public_examination_planet.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gcu.public_examination_planet.domain.Weibo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gcu.public_examination_planet.dto.WeiboForShow;

/**
 * @author HealMe
 * @description 针对表【weibo(微博)】的数据库操作Service
 * @createDate 2024-01-28 17:01:11
 */
public interface WeiboService extends IService<Weibo> {
    Integer save(String text, Integer userId, Integer weiboHaveImg);
    IPage<WeiboForShow> selectByPage(Integer currentPage, Integer pageSize);

    void increasetById(Integer weiboId);
}
