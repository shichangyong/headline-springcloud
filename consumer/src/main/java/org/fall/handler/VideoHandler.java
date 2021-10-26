package org.fall.handler;

import com.github.pagehelper.PageInfo;
import org.fall.api.MySQLRemoteService;
import org.fall.entity.dto.RefreshLoadDTO;
import org.fall.entity.dto.VideoDTO;
import org.fall.entity.po.VideoPO;
import org.fall.entity.vo.VideoVO;
import org.fall.util.ResultEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class VideoHandler {
    @Autowired
    MySQLRemoteService mySQLRemoteService;

    @RequestMapping("/upload/video")
    String saveVideo(VideoVO videoVO){
        Date time = new Date();
        VideoPO videoPO = new VideoPO();
        BeanUtils.copyProperties(videoVO, videoPO);
        videoPO.setTime(time);
        String saveResultEntity = mySQLRemoteService.saveVideoRemote(videoPO);
        return saveResultEntity;
    }

    @RequestMapping("/get/video/list")
    public ResultEntity<RefreshLoadDTO> getVideoList(@RequestParam("time")String time, @RequestParam("type")String type) {
        ResultEntity<RefreshLoadDTO> videoListRemote = mySQLRemoteService.getVideoListRemote(time, type);
        return videoListRemote;
    }

    @RequestMapping("/get/video/preview")
    ResultEntity<VideoDTO> getVideoPreview(@RequestParam("id")int id){
        ResultEntity<VideoDTO> videoPreviewRemote = mySQLRemoteService.getVideoPreviewRemote(id);
        return videoPreviewRemote;
    }

    @RequestMapping("/get/video/page")
    ResultEntity<PageInfo<VideoDTO>> getVideoPage(
            // 传入的关键字，若未传入，默认值为一个空字符串（不是null）
            @RequestParam(value = "keyword", defaultValue = "") String keyword,
            // 传入的页码，默认值为1
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            // 传入的页面大小，默认值为5
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize){
        ResultEntity<PageInfo<VideoDTO>> videoPageRemote = mySQLRemoteService.getVideoPageRemote(keyword, pageNum, pageSize);
        return videoPageRemote;
    }
}
