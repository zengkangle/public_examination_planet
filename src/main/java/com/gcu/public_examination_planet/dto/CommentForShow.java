package com.gcu.public_examination_planet.dto;

import com.gcu.public_examination_planet.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author HealMe
 * @Description
 * @date 2024/2/14 14:50
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentForShow extends Comment {

    /**
     * 昵称
     */
    private String userName;

    /**
     * 用户头像
     */
    private String userAvatarUrl;

    public CommentForShow(Comment comment) {
        super(comment);
    }
}
