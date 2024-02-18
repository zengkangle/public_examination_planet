package com.gcu.public_examination_planet.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gcu.public_examination_planet.domain.User;
import com.gcu.public_examination_planet.domain.Weibo;
import com.gcu.public_examination_planet.domain.WeiboImg;
import com.gcu.public_examination_planet.dto.WeiboForShow;
import com.gcu.public_examination_planet.mapper.WeiboMapper;
import com.gcu.public_examination_planet.service.UserService;
import com.gcu.public_examination_planet.service.WeiboImgService;
import com.gcu.public_examination_planet.service.WeiboService;
import org.springframework.stereotype.Service;
import xin.altitude.cms.common.util.EntityUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

    @Resource
    UserService userService;

    @Resource
    WeiboImgService weiboImgService;

    public Integer save(String text,Integer userId, Integer weiboHaveImg){
        Weibo weibo = new Weibo();
        weibo.setUserId(userId);
        weibo.setWeiboContent(text);
        weibo.setWeiboHaveImg(weiboHaveImg);
        save(weibo);
        return weibo.getWeiboId();
    };

    //分页展示用户列表
    public IPage<WeiboForShow> selectByPage(Integer currentPage, Integer pageSize) {
        QueryWrapper<Weibo> weiboWrapper = new QueryWrapper<>();
        weiboWrapper.orderByDesc("weibo_post_time");
        Page<Weibo> weiboPage = page(new Page<>(currentPage, pageSize), weiboWrapper);
        IPage<WeiboForShow> weiboForShowIPage = EntityUtils.toPage(weiboPage, WeiboForShow::new);
        Set<Integer> userIds = EntityUtils.toSet(weiboForShowIPage.getRecords(), WeiboForShow::getUserId);
        if (userIds != null && userIds.size() > 0){
            List<User> userList = userService.list(new QueryWrapper<User>().select("user_id","user_name", "user_avatar_url","user_level").in("user_id", userIds));
            Map<Integer, User> userMap = EntityUtils.toMap(userList, User::getUserId, e -> e);
            Set<Integer> weiboIds = EntityUtils.toSet(weiboForShowIPage.getRecords(), WeiboForShow::getWeiboId);
            List<WeiboImg> weiboImgList = weiboImgService.list(new QueryWrapper<WeiboImg>().in("weibo_id", weiboIds));
            Map<Integer, List<WeiboImg>> weiboImgMap = EntityUtils.groupBy(weiboImgList, WeiboImg::getWeiboId);
            for (WeiboForShow weiboForShow : weiboForShowIPage.getRecords()) {
                weiboForShow.setUserName(userMap.get(weiboForShow.getUserId()).getUserName());
                weiboForShow.setUserAvatarUrl(userMap.get(weiboForShow.getUserId()).getUserAvatarUrl());
                weiboForShow.setUserLevel(userMap.get(weiboForShow.getUserId()).getUserLevel());
                if (weiboImgMap != null && weiboImgMap.size() > 0){
                    List<WeiboImg> weiboImgListOfOne = weiboImgMap.get(weiboForShow.getWeiboId());
                    List<String> weiboImgUrlList = new ArrayList<>();
                    if (weiboImgListOfOne != null && weiboImgListOfOne.size() > 0){
                        for (WeiboImg weiboImg : weiboImgListOfOne) {
                            weiboImgUrlList.add(weiboImg.getWeiboImgUrl());
                        }
                    }
                    weiboForShow.setWeiboImgList(weiboImgUrlList);
                }
            }
        }
        return weiboForShowIPage;
    }

    public void increasetById(Integer weiboId){
        Weibo weibo = getById(weiboId);
        weibo.setWeiboCommentAmount(weibo.getWeiboCommentAmount()+1);
        updateById(weibo);
    }
}