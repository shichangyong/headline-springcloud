package org.fall.entity.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommentPO {
    private Integer id;

    private Integer sourceId;

    private String targetType;

    private Integer targetId;

    private String content;

    private Date time;

    private Integer repliesNumber;

    private Integer likesNumber;

}