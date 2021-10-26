package org.fall.handler;

import com.github.pagehelper.PageInfo;
import org.fall.entity.dto.MicroHeadlinesDTO;
import org.fall.entity.dto.RefreshLoadDTO;
import org.fall.entity.dto.VideoDTO;
import org.fall.entity.po.MicroHeadlinesPO;
import org.fall.entity.vo.PreviewByTimeVO;
import org.fall.service.api.MicroHeadlinesService;
import org.fall.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MicroHeadlinesProviderHandler {

    @Autowired
    MicroHeadlinesService microHeadlinesService;

    @RequestMapping("/save/headlines/remote")
    public ResultEntity<String> saveMicroHeadlinesRemote(@RequestBody MicroHeadlinesPO microHeadlinesPO){
        try {
            microHeadlinesService.saveMicroHeadlines(microHeadlinesPO);
            return ResultEntity.successWithData("上传成功!");
        } catch (Exception e){
            return ResultEntity.failed(e.getMessage());
        }
    }

    @RequestMapping("/get/headlines/remote")
    public ResultEntity<MicroHeadlinesDTO> getMicroHeadlinesRemote(@RequestParam("id")int id) {
        try {
            MicroHeadlinesDTO microHeadlinesPO = microHeadlinesService.getMicroHeadlinesRemote(id);
            return ResultEntity.successWithData(microHeadlinesPO);
        } catch (Exception e){
            return ResultEntity.failed(e.getMessage());
        }
    }

    @RequestMapping("/get/headlines/list/remote")
    public ResultEntity<RefreshLoadDTO> getHeadlinesListRemote(@RequestParam("time")String time, @RequestParam("type")String type) {
        PreviewByTimeVO previewByTimeVO = new PreviewByTimeVO(time, type);
        List<MicroHeadlinesPO> listByTimeRemote = microHeadlinesService.getListByTimeRemote(previewByTimeVO);
        RefreshLoadDTO refreshLoadDTO = new RefreshLoadDTO();
        if(listByTimeRemote.size() != 0){
            int length = listByTimeRemote.size();
            refreshLoadDTO.setLength(length);
            refreshLoadDTO.setBelong("headlines");
            refreshLoadDTO.setNewest(listByTimeRemote.get(length-1).getTime());
            refreshLoadDTO.setOldest(listByTimeRemote.get(0).getTime());
            List<Integer> listId = new ArrayList<>();

            for (MicroHeadlinesPO microHeadlinesPO : listByTimeRemote) {
                int id = microHeadlinesPO.getId();
                listId.add(id);
            }
            refreshLoadDTO.setListId(listId);
            return ResultEntity.successWithData(refreshLoadDTO);
        }else{
            return ResultEntity.failed("暂无数据!");
        }
    }

    @RequestMapping("/add/headlinesComment/remote")
    String addHeadlinesCommentRemote(@RequestParam("id")int id){
        try{
            int i = microHeadlinesService.addCommentNumber(id);
            if(i != 0){
                return "SUCCESS";
            }else{
                return "FAILED";
            }
        }catch (Exception e){
            return e.getMessage();
        }
    }

    @RequestMapping("/get/headlinesPage/remote")
    public ResultEntity<PageInfo<MicroHeadlinesDTO>> getHeadlinesPageRemote(
            // 传入的关键字，若未传入，默认值为一个空字符串（不是null）
            @RequestParam(value = "keyword", defaultValue = "") String keyword,
            // 传入的页码，默认值为1
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            // 传入的页面大小，默认值为5
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        //从AdminService中得到对应传参的列表
        try {
            PageInfo<MicroHeadlinesDTO> pageInfo = microHeadlinesService.getPageInfo(keyword, pageNum, pageSize);
            List<MicroHeadlinesDTO> list = pageInfo.getList();
            for (MicroHeadlinesDTO headlinesDTO:list) {
                headlinesDTO.getUserPO().setPassword(null);
            }
            return ResultEntity.successWithData(pageInfo);
        } catch (Exception e){
            return ResultEntity.failed(e.getMessage());
        }
    }
}
