package org.fall.handler;

import org.fall.api.MySQLRemoteService;
import org.fall.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserHandler {
    @Autowired
    MySQLRemoteService mySQLRemoteService;

    @RequestMapping("/update/user")
    ResultEntity<String> updateUser(@RequestParam("id") String id,
                                           @RequestParam(value = "phoneNumber", required=false) String  phoneNumber,
                                           @RequestParam(value = "nickname", required=false) String  nickname,
                                           @RequestParam(value = "fileName", required = false) String fileName,
                                           @RequestParam(value = "password", required=false) String  password,
                                           @RequestParam(value = "authentication", required=false) String  authentication){
        ResultEntity<String> stringResultEntity = mySQLRemoteService.updateUserRemoter(id, phoneNumber, nickname, fileName, password, authentication);
        return stringResultEntity;
    }
}
