package com.gcu.public_examination_planet.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gcu.public_examination_planet.domain.WeiboImg;
import com.gcu.public_examination_planet.service.WeiboImgService;
import com.gcu.public_examination_planet.mapper.WeiboImgMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
* @author HealMe
* @description 针对表【weibo_img(微博图片)】的数据库操作Service实现
* @createDate 2024-01-28 20:50:34
*/
@Service
public class WeiboImgServiceImpl extends ServiceImpl<WeiboImgMapper, WeiboImg>
    implements WeiboImgService{

    public void save(List<String> imageUploadList,Integer weiboId){
        for (String s : imageUploadList) {
            WeiboImg weiboImg = new WeiboImg();
            weiboImg.setWeiboImgUrl(s);
            weiboImg.setWeiboId(weiboId);
            save(weiboImg);
        }
    }

    public List<String> selectImageListByWeiboId(Integer weiboId){
        QueryWrapper<WeiboImg> wrapper = new QueryWrapper<>();
        wrapper.eq("weibo_id",weiboId);
        List<WeiboImg> weiboImgs = list(wrapper);
        ArrayList<String> weiboImgList = new ArrayList<>();
        for (WeiboImg weiboImg : weiboImgs) {
            weiboImgList.add(weiboImg.getWeiboImgUrl());
        }
        return weiboImgList;
    }
}





