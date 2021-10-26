package org.fall.handler;

import com.github.pagehelper.PageInfo;
import org.fall.entity.dto.ArticlePreviewDTO;
import org.fall.entity.dto.CommentDTO;
import org.fall.entity.dto.RefreshLoadDTO;
import org.fall.entity.dto.VideoDTO;
import org.fall.entity.po.ArticlePO;
import org.fall.entity.po.VideoPO;
import org.fall.entity.vo.PreviewByTimeVO;
import org.fall.service.api.VideoService;
import org.fall.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class VideoProviderHandler {
    @Autowired
    VideoService videoService;

    @RequestMapping("/save/video/remote")
    public String saveVideoRemote(@RequestBody VideoPO videoPO){
        try{
            videoService.saveVideo(videoPO);
            return "SUCCESS";
        } catch (Exception e){
            return e.getMessage();
        }
    }

    @RequestMapping("/get/video/list/remote")
    public ResultEntity<RefreshLoadDTO> getVideoListRemote(@RequestParam("time")String time, @RequestParam("type")String type) {
        PreviewByTimeVO previewByTimeVO = new PreviewByTimeVO(time, type);
        List<VideoPO> listByTimeRemote = videoService.getListByTimeRemote(previewByTimeVO);
        RefreshLoadDTO refreshLoadDTO = new RefreshLoadDTO();
        if(listByTimeRemote.size() != 0){
            int length = listByTimeRemote.size();
            refreshLoadDTO.setLength(length);
            refreshLoadDTO.setBelong("video");
            refreshLoadDTO.setNewest(listByTimeRemote.get(length-1).getTime());
            refreshLoadDTO.setOldest(listByTimeRemote.get(0).getTime());
            List<Integer> listId = new ArrayList<>();

            for (VideoPO videoPO : listByTimeRemote) {
                int id = videoPO.getId();
                listId.add(id);
            }
            refreshLoadDTO.setListId(listId);
            return ResultEntity.successWithData(refreshLoadDTO);
        }else{
            return ResultEntity.failed("暂无数据!");
        }
    }

    @RequestMapping("/get/videoPreview/remote")
    public ResultEntity<VideoDTO> getVideoPreviewRemote(@RequestParam("id")int id) {
        try {
            VideoDTO videoPreview = videoService.getVideoPreview(id);
            return ResultEntity.successWithData(videoPreview);
        } catch (Exception e){
            return ResultEntity.failed(e.getMessage());
        }
    }

    @RequestMapping("/add/videoComment/remote")
    String addVideoCommentRemote(@RequestParam("id")int id){
        try{
            int i = videoService.addCommentNumber(id);
            if(i != 0){
                return "SUCCESS";
            }else{
                return "FAILED";
            }
        }catch (Exception e){
            return e.getMessage();
        }
    }

    @RequestMapping("/get/videoPage/remote")
    public ResultEntity<PageInfo<VideoDTO>> getVideoPageRemote(
            // 传入的关键字，若未传入，默认值为一个空字符串（不是null）
            @RequestParam(value = "keyword", defaultValue = "") String keyword,
            // 传入的页码，默认值为1
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            // 传入的页面大小，默认值为5
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        //从AdminService中得到对应传参的列表
        try {
            PageInfo<VideoDTO> pageInfo = videoService.getPageInfo(keyword, pageNum, pageSize);
            List<VideoDTO> list = pageInfo.getList();
            for (VideoDTO videoDTO:list) {
                videoDTO.getUserPO().setPassword(null);
            }
            return ResultEntity.successWithData(pageInfo);
        } catch (Exception e){
            return ResultEntity.failed(e.getMessage());
        }
    }
}
