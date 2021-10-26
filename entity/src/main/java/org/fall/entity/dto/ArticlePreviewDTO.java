package org.fall.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.fall.entity.po.UserPO;

import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ArticlePreviewDTO {
    private Integer id;

    private Integer authorId;

    private String coverUrl;

    private Date time;

    private Integer commentNumber;

    private Integer fabulousNumber;

    private String title;

    private UserPO userPO;
}
