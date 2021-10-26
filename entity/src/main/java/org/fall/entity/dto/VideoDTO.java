package org.fall.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.fall.entity.po.UserPO;

import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class VideoDTO {
    private Integer id;

    private Integer authorId;

    private String title;

    private String introduce;

    private String coverUrl;

    private String videoUrl;

    private Date time;

    private Integer commentnumber;

    private Integer fabulousnumber;

    private UserPO userPO;
}
