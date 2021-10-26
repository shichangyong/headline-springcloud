package org.fall.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.fall.entity.po.UserPO;

import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommentDTO {
    private Integer id;

    private Integer sourceId;

    private String targetType;

    private Integer targetId;

    private String content;

    private Date time;

    private Integer repliesNumber;

    private Integer likesNumber;

    private UserPO userPO;
}
