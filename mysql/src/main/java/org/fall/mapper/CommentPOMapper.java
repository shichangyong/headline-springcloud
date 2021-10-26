package org.fall.mapper;

import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.fall.entity.dto.CommentDTO;
import org.fall.entity.po.CommentPO;
import org.fall.entity.po.CommentPOExample;

import java.util.List;

public interface CommentPOMapper {
    int countByExample(CommentPOExample example);

    int deleteByExample(CommentPOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CommentPO record);

    int insertSelective(CommentPO record);

    List<CommentPO> selectByExample(CommentPOExample example);

    CommentPO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CommentPO record, @Param("example") CommentPOExample example);

    int updateByExample(@Param("record") CommentPO record, @Param("example") CommentPOExample example);

    int updateByPrimaryKeySelective(CommentPO record);

    int updateByPrimaryKey(CommentPO record);

    List<CommentDTO> getByTarget(@Param("targetType")String targetType,@Param("targetId")Integer targetId);

    int addRepliesNumber(@Param("id")Integer id);
}