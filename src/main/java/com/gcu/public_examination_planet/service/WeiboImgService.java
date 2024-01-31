package com.gcu.public_examination_planet.service;

import com.gcu.public_examination_planet.domain.WeiboImg;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author HealMe
* @description 针对表【weibo_img(微博图片)】的数据库操作Service
* @createDate 2024-01-28 20:50:34
*/
public interface WeiboImgService extends IService<WeiboImg> {
    void save(List<String> imageUploadList,Integer weiboId);

    List<String> selectImageListByWeiboId(Integer weiboId);
}
