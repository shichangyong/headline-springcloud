package org.fall.service.api;

import org.fall.entity.po.ArticlePO;
import org.fall.entity.po.UserPO;
import org.fall.entity.po.UserPOExample;

public interface UserService {
    UserPO selectByPhone(String phoneNumber);
    int updateUser(UserPO userPO, UserPOExample userPOExample);
}
