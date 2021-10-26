package org.fall.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MicroHeadlinesVO {

    private Integer authorId;

    private String textContent;

    private String pictureUrl;

}
