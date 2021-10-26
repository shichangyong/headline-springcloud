package org.fall.entity.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ArticlePO {
    private Integer id;

    private Integer authorId;

    private String htmlContent;

    private String coverUrl;

    private String pictureUrl;

    private String videoUrl;

    private Date time;

    private Integer commentNumber;

    private Integer fabulousNumber;

    private String title;

}