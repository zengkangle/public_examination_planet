package com.gcu.public_examination_planet.dto;

import com.gcu.public_examination_planet.domain.Video;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author HealMe
 * @Description
 * @date 2024/2/7 12:28
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoForShow extends Video {
    /**
     * 课程标题
     */
    private String courseTitle;

    public VideoForShow(Video video) {
        super(video);
    }
}
