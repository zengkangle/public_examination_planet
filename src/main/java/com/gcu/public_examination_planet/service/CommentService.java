package com.gcu.public_examination_planet.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gcu.public_examination_planet.domain.Comment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gcu.public_examination_planet.dto.CommentForShow;

/**
* @author HealMe
* @description 针对表【comment(评价)】的数据库操作Service
* @createDate 2024-02-12 18:54:00
*/
public interface CommentService extends IService<Comment> {
    IPage<CommentForShow> getRateByPage(Integer currentPage, Integer pageSize, String commentType, Integer courseId, Integer teacherId);
}
