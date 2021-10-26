package org.fall.mapper;

import org.apache.ibatis.annotations.Param;
import org.fall.entity.po.UserPO;
import org.fall.entity.po.UserPOExample;

import java.util.List;

public interface UserPOMapper {
    int countByExample(UserPOExample example);

    int deleteByExample(UserPOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserPO record);

    int insertSelective(UserPO record);

    List<UserPO> selectByExampleWithBLOBs(UserPOExample example);

    List<UserPO> selectByExample(UserPOExample example);

    UserPO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserPO record, @Param("example") UserPOExample example);

    int updateByExampleWithBLOBs(@Param("record") UserPO record, @Param("example") UserPOExample example);

    int updateByExample(@Param("record") UserPO record, @Param("example") UserPOExample example);

    int updateByPrimaryKeySelective(UserPO record);

    int updateByPrimaryKeyWithBLOBs(UserPO record);

    int updateByPrimaryKey(UserPO record);

    UserPO selectByPhone(String phoneNumber);
}