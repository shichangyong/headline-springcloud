package org.fall.config;

import org.fall.api.MySQLRemoteService;
import org.fall.entity.po.UserPO;
import org.fall.util.ResultEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    MySQLRemoteService mySQLRemoteService;

    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        ResultEntity<UserPO> userByPhone = mySQLRemoteService.getUserByPhone(phoneNumber);
        UserPO userPO = userByPhone.getData();
        // 创建List用来存放GrantedAuthority（权限信息）
        List<GrantedAuthority> authorities = new ArrayList<>();

        // 向List存放角色信息，注意角色必须要手动加上 “ROLE_” 前缀
        String roleName = "ROLE_"+ userPO.getAuthentication() ;
        SimpleGrantedAuthority simpleGrantedAuthority1 = new SimpleGrantedAuthority(roleName);
        authorities.add(simpleGrantedAuthority1);

        // 向List存放权限信息
        SimpleGrantedAuthority simpleGrantedAuthority2 = new SimpleGrantedAuthority("ALL");
        authorities.add(simpleGrantedAuthority2);

        SecurityUser securityUser = new SecurityUser(userPO, authorities);
        return securityUser;
    }

}
