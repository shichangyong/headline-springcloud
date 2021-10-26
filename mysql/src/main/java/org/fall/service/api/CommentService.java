package org.fall.service.api;

import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.fall.entity.dto.CommentDTO;
import org.fall.entity.po.CommentPO;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

public interface CommentService {
    PageInfo<CommentDTO> getPageInfo(String targetType, Integer targetId, Integer pageNum, Integer pageSize);
    int saveComment(CommentPO commentPO);
    int addRepliesNumber(int id);
}