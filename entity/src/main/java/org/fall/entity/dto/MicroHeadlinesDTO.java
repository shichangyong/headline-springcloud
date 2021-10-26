package org.fall.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.fall.entity.po.UserPO;

import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MicroHeadlinesDTO {
    private Integer id;

    private Integer authorId;

    private String textContent;

    private String pictureUrl;

    private Date time;

    private Integer commentNumber;

    private Integer fabulousNumber;

    private UserPO userPO;
}
