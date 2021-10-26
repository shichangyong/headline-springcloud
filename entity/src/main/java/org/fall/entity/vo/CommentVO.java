package org.fall.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommentVO {
    private Integer sourceId;

    private String targetType;

    private Integer targetId;

    private String content;
}
