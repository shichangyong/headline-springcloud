package org.fall.config;

import org.fall.entity.po.UserPO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

/**
 * 为了能方便地获取到原始地Admin对象，因此创建一个SecurityAdmin类，继承User。
 */
public class SecurityUser extends User {

    private UserPO userPO;

    public SecurityUser(UserPO userPO, List<GrantedAuthority> authorities){
        super(userPO.getPhoneNumber(),userPO.getPassword(),authorities);

        this.userPO = userPO;
        // 为了保证安全性，擦除放入originalAdmin的对象的密码
        //this.userPO.setPassword(null);
    }

    public UserPO getOriginalAdmin(){
        return this.userPO;
    }
}
