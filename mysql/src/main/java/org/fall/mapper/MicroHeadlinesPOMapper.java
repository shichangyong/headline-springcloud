package org.fall.mapper;

import org.apache.ibatis.annotations.Param;
import org.fall.entity.dto.MicroHeadlinesDTO;
import org.fall.entity.dto.VideoDTO;
import org.fall.entity.po.MicroHeadlinesPO;
import org.fall.entity.po.MicroHeadlinesPOExample;
import org.fall.entity.vo.PreviewByTimeVO;

import java.util.List;

public interface MicroHeadlinesPOMapper {
    int countByExample(MicroHeadlinesPOExample example);

    int deleteByExample(MicroHeadlinesPOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MicroHeadlinesPO record);

    int insertSelective(MicroHeadlinesPO record);

    List<MicroHeadlinesPO> selectByExample(MicroHeadlinesPOExample example);

    MicroHeadlinesPO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MicroHeadlinesPO record, @Param("example") MicroHeadlinesPOExample example);

    int updateByExample(@Param("record") MicroHeadlinesPO record, @Param("example") MicroHeadlinesPOExample example);

    int updateByPrimaryKeySelective(MicroHeadlinesPO record);

    int updateByPrimaryKey(MicroHeadlinesPO record);

    MicroHeadlinesDTO getMicroHeadlines(Integer id);

    List<MicroHeadlinesPO> getListByTime(PreviewByTimeVO previewByTimeVO);

    int addCommentNumber(int id);

    //根据关键字查找用户的方法
    List<MicroHeadlinesDTO> selectHeadlinesByKeyword(String keyword);
}