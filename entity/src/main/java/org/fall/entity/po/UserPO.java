package org.fall.entity.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserPO {
    private Integer id;

    private String phoneNumber;

    private String nickname;

    private String password;

    private String authentication;

    private byte[] headPortrait;


}