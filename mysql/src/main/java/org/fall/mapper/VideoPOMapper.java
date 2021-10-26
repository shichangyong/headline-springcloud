package org.fall.mapper;

import org.apache.ibatis.annotations.Param;
import org.fall.entity.dto.ArticlePreviewDTO;
import org.fall.entity.dto.VideoDTO;
import org.fall.entity.po.ArticlePO;
import org.fall.entity.po.VideoPO;
import org.fall.entity.po.VideoPOExample;
import org.fall.entity.vo.PreviewByTimeVO;

import java.util.List;

public interface VideoPOMapper {
    int countByExample(VideoPOExample example);

    int deleteByExample(VideoPOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(VideoPO record);

    int insertSelective(VideoPO record);

    List<VideoPO> selectByExample(VideoPOExample example);

    VideoPO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") VideoPO record, @Param("example") VideoPOExample example);

    int updateByExample(@Param("record") VideoPO record, @Param("example") VideoPOExample example);

    int updateByPrimaryKeySelective(VideoPO record);

    int updateByPrimaryKey(VideoPO record);

    List<VideoPO> getListByTime(PreviewByTimeVO previewByTimeVO);

    VideoDTO getVideoPreview(Integer id);

    int addCommentNumber(int id);

    //根据关键字查找用户的方法
    List<VideoDTO> selectVideoByKeyword(String keyword);
}