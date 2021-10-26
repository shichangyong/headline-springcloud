package org.fall.handler;

import com.github.pagehelper.PageInfo;
import org.fall.api.MySQLRemoteService;
import org.fall.entity.dto.MicroHeadlinesDTO;
import org.fall.entity.dto.RefreshLoadDTO;
import org.fall.entity.po.MicroHeadlinesPO;
import org.fall.entity.vo.MicroHeadlinesVO;
import org.fall.util.ResultEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class HeadlinesHandler {

    Logger logger = LoggerFactory.getLogger(HeadlinesHandler.class);
    @Autowired
    MySQLRemoteService mySQLRemoteService;

    @RequestMapping("/upload/headlines/text")
    public ResultEntity<String> saveHeaderText(MicroHeadlinesVO microHeadlinesVO) {
        Date time = new Date();
        MicroHeadlinesPO microHeadlinesPO = new MicroHeadlinesPO();
        BeanUtils.copyProperties(microHeadlinesVO, microHeadlinesPO);
        microHeadlinesPO.setTime(time);
        //存入数据库
        ResultEntity<String> resultEntity = mySQLRemoteService.saveMicroHeadlinesRemote(microHeadlinesPO);
        //判断保存是否成功
        return resultEntity;
    }

    @RequestMapping("/get/headlines/text")
    public ResultEntity<MicroHeadlinesDTO> getHeaderText(@RequestParam("id")int id) {
        ResultEntity<MicroHeadlinesDTO> resultEntity = mySQLRemoteService.getMicroHeadlinesRemote(id);
        //判断保存是否成功
        return resultEntity;
    }

    @RequestMapping("/get/headlines/list")
    public ResultEntity<RefreshLoadDTO> getHeadlinesList(@RequestParam("time")String time, @RequestParam("type")String type){
        ResultEntity<RefreshLoadDTO> resultEntity = mySQLRemoteService.getHeadlinesListRemote(time, type);
        //判断保存是否成功
        return resultEntity;
    }

    @RequestMapping("/get/headlines/page")
    public ResultEntity<PageInfo<MicroHeadlinesDTO>> getHeadlinesPage(
            // 传入的关键字，若未传入，默认值为一个空字符串（不是null）
            @RequestParam(value = "keyword", defaultValue = "") String keyword,
            // 传入的页码，默认值为1
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            // 传入的页面大小，默认值为5
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize){
        ResultEntity<PageInfo<MicroHeadlinesDTO>> headlinesPageRemote = mySQLRemoteService.getHeadlinesPageRemote(keyword, pageNum, pageSize);
        return headlinesPageRemote;
    }
}
