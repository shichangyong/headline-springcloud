package org.fall.entity.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class VideoPO {
    private Integer id;

    private Integer authorId;

    private String title;

    private String introduce;

    private String coverUrl;

    private String videoUrl;

    private Date time;

    private Integer commentnumber;

    private Integer fabulousnumber;

}