package org.fall.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ArticleVO {

    private Integer authorId;

    private String htmlContent;

    private String coverUrl;

    private String pictureUrl;

    private String videoUrl;

    private String title;
}
