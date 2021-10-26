package org.fall.handler;

import com.github.pagehelper.PageInfo;
import org.fall.api.MySQLRemoteService;
import org.fall.entity.dto.CommentDTO;
import org.fall.entity.po.CommentPO;
import org.fall.entity.vo.CommentVO;
import org.fall.util.ResultEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class CommentHandler {
    @Autowired
    MySQLRemoteService mySQLRemoteService;
    
    @RequestMapping("/get/comment/page")
    public ResultEntity<PageInfo<CommentDTO>> getCommentPage(
            // 传入的关键字，若未传入，默认值为一个空字符串（不是null）
            @RequestParam(value = "targetType", defaultValue = "") String targetType,
            // 传入的关键字，若未传入，默认值为一个空字符串（不是null）
            @RequestParam(value = "targetId", defaultValue = "") Integer targetId,
            // 传入的页码，默认值为1
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            // 传入的页面大小，默认值为5
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize){
        ResultEntity<PageInfo<CommentDTO>> commentPageRemote = mySQLRemoteService.getCommentPageRemote(targetType, targetId, pageNum, pageSize);
        return  commentPageRemote;
    }

    @RequestMapping("/upload/comment")
    public String uploadComment(CommentVO commentVO){
        CommentPO commentPO = new CommentPO();
        BeanUtils.copyProperties(commentVO, commentPO);
        commentPO.setTime(new Date());
        String result = mySQLRemoteService.uploadCommentRemote(commentPO);
        String addResult = "";
        if(result.equals("SUCCESS")){
            Integer targetId = commentVO.getTargetId();
            if(commentVO.getTargetType().equals("comment")){
                addResult = mySQLRemoteService.addCommentRepliesRemote(targetId);
            }else{
                if(commentVO.getTargetType().equals("article")){
                    addResult = mySQLRemoteService.addArticleCommentRemote(targetId);
                }else{
                    if(commentVO.getTargetType().equals("headlines")){
                        addResult = mySQLRemoteService.addHeadlinesCommentRemote(targetId);
                    }else{
                        addResult = mySQLRemoteService.addVideoCommentRemote(targetId);
                    }
                }
            }
            return addResult;
        }else{
            return result;
        }
    }
}
