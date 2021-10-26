package org.fall.entity.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MicroHeadlinesPO {
    private Integer id;

    private Integer authorId;

    private String textContent;

    private String pictureUrl;

    private Date time;

    private Integer commentNumber;

    private Integer fabulousNumber;

}