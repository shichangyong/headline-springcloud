package org.fall.service.impl;

import org.fall.entity.po.UserPO;
import org.fall.entity.po.UserPOExample;
import org.fall.mapper.UserPOMapper;
import org.fall.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserPOMapper userPOMapper;

    @Override
    public UserPO selectByPhone(String phoneNumber) {
        UserPO userPO = userPOMapper.selectByPhone(phoneNumber);
        return userPO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    @Override
    public int updateUser(UserPO userPO, UserPOExample userPOExample) {
        int i = userPOMapper.updateByExampleSelective(userPO, userPOExample);
        return i;
    }
}
