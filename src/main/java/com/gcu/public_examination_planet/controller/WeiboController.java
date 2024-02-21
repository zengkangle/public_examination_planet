package com.gcu.public_examination_planet.controller;

import com.gcu.public_examination_planet.common.Result;
import com.gcu.public_examination_planet.domain.WeiboComment;
import com.gcu.public_examination_planet.domain.WeiboCommentReply;
import com.gcu.public_examination_planet.dto.PostMsg;
import com.gcu.public_examination_planet.dto.SimpleUser;
import com.gcu.public_examination_planet.service.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author HealMe
 * @Description
 * @date 2024/1/28 下午 7:20
 **/
@RestController
@RequestMapping("/weibo")
public class WeiboController {

    @Resource
    private WeiboService weiboService;

    @Resource
    private WeiboImgService weiboImgService;

    @Resource
    private WeiboCommentService weiboCommentService;

    @Resource
    private WeiboCommentReplyService weiboCommentReplyService;

    @Resource
    private UserService userService;

    /**
     * 发布微博
     * @param postMsg
     * @return
     */
    @PostMapping("/postWeibo")
    public Result postWeibo(@RequestBody PostMsg postMsg){

        if (postMsg.getImageUploadList().size()!=0){
            Integer weiboId = weiboService.save(postMsg.getText(), postMsg.getUserId(),1);
            weiboImgService.save(postMsg.getImageUploadList(),weiboId);
        }else {
            Integer weiboId = weiboService.save(postMsg.getText(), postMsg.getUserId(),0);
        }
        return Result.success("成功");
    }

    /**
     * 分页展示微博列表
     * @param currentPage
     * @param pageSize
     * @return
     */
    @GetMapping("/showWeiboList")
    public Result showWeiboList(@RequestParam("currentPage") Integer currentPage, @RequestParam("pageSize") Integer pageSize) {
        return Result.success(weiboService.selectByPage(currentPage, pageSize));
    }

    /**
     * 发布微博评论
     * @param weiboComment
     * @return
     */
    @PostMapping("/postComment")
    public Result postComment(@RequestBody WeiboComment weiboComment){
        weiboCommentService.saveComment(weiboComment);
        weiboService.increasetById(weiboComment.getWeiboId());
        return Result.success("成功");
    }

    /**
     * 获取微博评论列表
     * @param weiboId
     * @return
     */
    @GetMapping("/showWeiboCommentList")
    public Result showWeiboCommentList(@RequestParam("weiboId") Integer weiboId) {
        return Result.success(weiboCommentService.getCommentListByWeiboId(weiboId));
    }

    /**
     * 发布微博回复
     * @param weiboCommentReply
     * @return
     */
    @PostMapping("/postReply")
    public Result postReply(@RequestBody WeiboCommentReply weiboCommentReply){
        weiboCommentReplyService.saveReply(weiboCommentReply);
        weiboCommentService.increaseById(weiboCommentReply.getWeiboCommentId());
        Integer weiboIdByCommentId = weiboCommentService.getWeiboIdByCommentId(weiboCommentReply.getWeiboCommentId());
        weiboService.increasetById(weiboIdByCommentId);
        return Result.success("成功");
    }

    /**
     * 获取该评论的最新的回复的用户昵称
     * @param weiboCommentId
     * @return
     */
    @GetMapping("/getRecentReplyUserName")
    public Result getRecentReplyUserName(@RequestParam("weiboCommentId") Integer weiboCommentId) {
        Integer userId = weiboCommentReplyService.getRecentReplyUserNameByCommentId(weiboCommentId);
        SimpleUser simpleUser = userService.selectUserById(userId);
        String userName = simpleUser.getUserName();
        return Result.success(userName);
    }

    /**
     * 获取该评论的回复列表
     * @param weiboCommentId
     * @return
     */
    @GetMapping("/getReplyList")
    public Result getReplyList(@RequestParam("weiboCommentId") Integer weiboCommentId) {
        return Result.success(weiboCommentReplyService.getReplyList(weiboCommentId));
    }
}
