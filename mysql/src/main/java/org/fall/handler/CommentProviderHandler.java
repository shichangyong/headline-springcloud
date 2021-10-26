package org.fall.handler;

import com.github.pagehelper.PageInfo;
import org.fall.entity.dto.ArticlePreviewDTO;
import org.fall.entity.dto.CommentDTO;

import org.fall.entity.po.CommentPO;
import org.fall.service.api.ArticleService;
import org.fall.service.api.CommentService;
import org.fall.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentProviderHandler {

    @Autowired
    CommentService commentService;


    @RequestMapping("/get/commentPage/remote")
    public ResultEntity<PageInfo<CommentDTO>> getCommentPageRemote(
            // 传入的关键字，若未传入，默认值为一个空字符串（不是null）
            @RequestParam(value = "targetType", defaultValue = "") String targetType,
            // 传入的关键字，若未传入，默认值为一个空字符串（不是null）
            @RequestParam(value = "targetId", defaultValue = "") Integer targetId,
            // 传入的页码，默认值为1
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            // 传入的页面大小，默认值为5
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        //从AdminService中得到对应传参的列表
        try {
            PageInfo<CommentDTO> pageInfo = commentService.getPageInfo(targetType, targetId, pageNum, pageSize);
            return ResultEntity.successWithData(pageInfo);
        } catch (Exception e){
            return ResultEntity.failed(e.getMessage());
        }
    }

    @RequestMapping("/upload/comment/remote")
    public String uploadCommentRemote(@RequestBody CommentPO commentPO){
        try{
            int i = commentService.saveComment(commentPO);
            if(i != 0){
                return "SUCCESS";
            }else{
                return "FAILED";
            }
        }catch (Exception e){
            return e.getMessage();
        }
    }

    @RequestMapping("/add/commentReplies/remote")
    String addCommentRepliesRemote(@RequestParam("id")int id) {
        try{
            int i = commentService.addRepliesNumber(id);
            if(i != 0){
                return "SUCCESS";
            }else{
                return "FAILED";
            }
        }catch (Exception e){
            return e.getMessage();
        }
    }


}
