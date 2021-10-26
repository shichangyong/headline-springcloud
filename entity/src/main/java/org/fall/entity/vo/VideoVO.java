package org.fall.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class VideoVO {
    private Integer authorId;

    private String title;

    private String introduce;

    private String coverUrl;

    private String videoUrl;

}
