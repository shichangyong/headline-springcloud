package org.fall.service.api;

import com.github.pagehelper.PageInfo;
import org.fall.entity.dto.ArticlePreviewDTO;
import org.fall.entity.dto.VideoDTO;
import org.fall.entity.po.ArticlePO;
import org.fall.entity.po.UserPO;
import org.fall.entity.po.UserPOExample;
import org.fall.entity.po.VideoPO;
import org.fall.entity.vo.PreviewByTimeVO;

import java.util.List;

public interface VideoService {
    void saveVideo(VideoPO videoePO);
    List<VideoPO> getListByTimeRemote(PreviewByTimeVO previewByTimeVO);
    VideoDTO getVideoPreview(Integer id);
    int addCommentNumber(Integer id);
    PageInfo<VideoDTO> getPageInfo(String keyword, Integer pageNum, Integer pageSize);

}
