package org.fall.mapper;

import org.apache.ibatis.annotations.Param;
import org.fall.entity.dto.ArticlePreviewDTO;
import org.fall.entity.dto.MicroHeadlinesDTO;
import org.fall.entity.dto.VideoDTO;
import org.fall.entity.po.ArticlePO;
import org.fall.entity.po.ArticlePOExample;
import org.fall.entity.po.MicroHeadlinesPO;
import org.fall.entity.vo.PreviewByTimeVO;

import java.util.List;

public interface ArticlePOMapper {
    int countByExample(ArticlePOExample example);

    int deleteByExample(ArticlePOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ArticlePO record);

    int insertSelective(ArticlePO record);

    List<ArticlePO> selectByExample(ArticlePOExample example);

    ArticlePO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ArticlePO record, @Param("example") ArticlePOExample example);

    int updateByExample(@Param("record") ArticlePO record, @Param("example") ArticlePOExample example);

    int updateByPrimaryKeySelective(ArticlePO record);

    int updateByPrimaryKey(ArticlePO record);

    ArticlePreviewDTO getArticlePreview(Integer id);

    int addCommentNumber(int id);

    List<ArticlePO> getListByTime(PreviewByTimeVO previewByTimeVO);

    //根据关键字查找用户的方法
    List<ArticlePreviewDTO> selectArticleByKeyword(String keyword);
}