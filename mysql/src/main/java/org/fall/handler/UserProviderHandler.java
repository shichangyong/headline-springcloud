package org.fall.handler;

import org.fall.entity.po.UserPO;
import org.fall.entity.po.UserPOExample;
import org.fall.service.api.UserService;
import org.fall.util.ResultEntity;
import org.fall.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController
public class UserProviderHandler {
    @Autowired
    UserService userService;
    private static final Logger log = LoggerFactory.getLogger(UserProviderHandler.class);
    @RequestMapping("/get/user/by/phone")
    ResultEntity<UserPO> getUserByPhone(@RequestParam("phoneNumber") String phoneNumber){
        try{
            UserPO userPO = userService.selectByPhone(phoneNumber);
            return ResultEntity.successWithData(userPO);
        }catch (Exception e){
            return ResultEntity.failed(e.getMessage());
        }
    }

    @RequestMapping("/update/user/remoter")
    ResultEntity<String> updateUserRemoter(@RequestParam("id") int id,
                                           @RequestParam(value = "phoneNumber", required=false) String  phoneNumber,
                                           @RequestParam(value = "nickname", required=false) String  nickname,
                                           @RequestParam(value = "fileName", required = false) String fileName,
                                           @RequestParam(value = "password", required=false) String  password,
                                           @RequestParam(value = "authentication", required=false) String  authentication){
        UserPO userPO = new UserPO();
        if(phoneNumber != null){
            userPO.setPhoneNumber(phoneNumber);
        }
        if(nickname != null){
            userPO.setNickname(nickname);
        }
        if(fileName != null){
            String uri = "E:\\HeadlinesFiles\\"+fileName;
            byte[] bytesByFile = FileUtil.getBytesByFile(uri);
            userPO.setHeadPortrait(bytesByFile);
            File file = new File(uri);
            file.delete();
        }
        if(password != null){
            // 加密
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = passwordEncoder.encode(password.trim());
            userPO.setPassword(encodedPassword);
        }
        if(authentication != null){
            userPO.setAuthentication(authentication);
        }
        UserPOExample userPOExample = new UserPOExample();
        userPOExample.createCriteria().andIdEqualTo(id);
        try {
            int i = userService.updateUser(userPO, userPOExample);
            if( i > 0){
                return ResultEntity.successWithData("SUCCESS");
            }else{
                return ResultEntity.successWithData("FAILED");
            }
        }catch(Exception e){
            return ResultEntity.failed(e.getMessage());
        }

    }
}
