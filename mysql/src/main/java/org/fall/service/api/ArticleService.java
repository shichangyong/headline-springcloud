package org.fall.service.api;

import com.github.pagehelper.PageInfo;
import org.fall.entity.dto.ArticlePreviewDTO;
import org.fall.entity.dto.VideoDTO;
import org.fall.entity.po.ArticlePO;
import org.fall.entity.po.MicroHeadlinesPO;
import org.fall.entity.vo.PreviewByTimeVO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ArticleService {
    void saveArticle(ArticlePO articlePO);
    ArticlePreviewDTO getArticlePreview(Integer id);
    ArticlePO selectByPrimaryKey(Integer id);
    int addCommentNumber(Integer id);
    List<ArticlePO> getListByTimeRemote(PreviewByTimeVO previewByTimeVO);
    PageInfo<ArticlePreviewDTO> getPageInfo(String keyword, Integer pageNum, Integer pageSize);
}
