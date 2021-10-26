package org.fall.service.api;

import com.github.pagehelper.PageInfo;
import org.fall.entity.dto.MicroHeadlinesDTO;
import org.fall.entity.dto.VideoDTO;
import org.fall.entity.po.MicroHeadlinesPO;
import org.fall.entity.vo.PreviewByTimeVO;

import java.util.List;

public interface MicroHeadlinesService {
    void saveMicroHeadlines(MicroHeadlinesPO microHeadlinesPO);
    MicroHeadlinesDTO getMicroHeadlinesRemote(int id);
    List<MicroHeadlinesPO> getListByTimeRemote(PreviewByTimeVO previewByTimeVO);
    int addCommentNumber(Integer id);
    PageInfo<MicroHeadlinesDTO> getPageInfo(String keyword, Integer pageNum, Integer pageSize);
}
