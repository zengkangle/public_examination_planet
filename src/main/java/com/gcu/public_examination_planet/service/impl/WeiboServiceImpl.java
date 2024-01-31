package com.gcu.public_examination_planet.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gcu.public_examination_planet.domain.Weibo;
import com.gcu.public_examination_planet.mapper.WeiboMapper;
import com.gcu.public_examination_planet.service.WeiboService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author HealMe
 * @description 针对表【weibo(微博)】的数据库操作Service实现
 * @createDate 2024-01-28 17:01:11
 */
@Service
public class WeiboServiceImpl extends ServiceImpl<WeiboMapper, Weibo>
        implements WeiboService{
    @Resource
    private WeiboMapper weiboMapper;
    public Integer save(String text,Integer userId, Integer weiboHaveImg){
        Weibo weibo = new Weibo();
        weibo.setUserId(userId);
        weibo.setWeiboContent(text);
        weibo.setWeiboHaveImg(weiboHaveImg);
        save(weibo);
        return weibo.getWeiboId();
    };

    //分页展示用户列表
    public IPage<Weibo> selectByPage(Integer currentPage, Integer pageSize) {
        QueryWrapper<Weibo> pageWrapper = new QueryWrapper<>();
        pageWrapper.orderByDesc("weibo_post_time");
        //第一个参数为查询第几页,第二个参数为每页多少条记录
        Page<Weibo> page = new Page<>(currentPage, pageSize);
        IPage<Weibo> weiboIPage = weiboMapper.selectPage(page, pageWrapper);
        //sk
//        weiboIPage.setTotal(weiboIPage.getTotal());
        return weiboIPage;
    }

    public void increasetById(Integer weiboId){
        Weibo weibo = getById(weiboId);
        weibo.setWeiboCommentAmount(weibo.getWeiboCommentAmount()+1);
        updateById(weibo);
    }
}