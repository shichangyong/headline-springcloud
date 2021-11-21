package org.fall.handler;

import com.github.pagehelper.PageInfo;
import org.fall.api.MySQLRemoteService;
import org.fall.api.RedisRemoteService;
import org.fall.entity.dto.ArticlePreviewDTO;
import org.fall.entity.dto.ArticleTextDTO;
import org.fall.entity.dto.RefreshLoadDTO;
import org.fall.entity.po.ArticlePO;
import org.fall.entity.vo.ArticleVO;
import org.fall.util.ResultEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class ArticleHandler {

    @Autowired
    MySQLRemoteService mySQLRemoteService;

    @Autowired
    RedisRemoteService redisRemoteService;

    @RequestMapping("/upload/article/text")
    public String saveArticleText(ArticleVO articleVO) {
        Date time = new Date();
        ArticlePO articlePO = new ArticlePO();
        BeanUtils.copyProperties(articleVO, articlePO);
        articlePO.setTime(time);
        //存入数据库
        String saveResultEntity = mySQLRemoteService.saveArticleRemote(articlePO);
        //判断保存是否成功
        return saveResultEntity;
    }

    @RequestMapping("/get/article/preview")
    public ResultEntity<ArticlePreviewDTO> getArticlePreview(@RequestParam("id")int id) {
        ResultEntity<ArticlePreviewDTO> articlePreviewRemote = mySQLRemoteService.getArticlePreviewRemote(id);
        //判断保存是否成功
        return articlePreviewRemote;
    }

    @RequestMapping("/get/article/text")
    public ResultEntity<ArticleTextDTO> getArticleText(@RequestParam("id")int id){
        ResultEntity<ArticleTextDTO> redisArticleText = redisRemoteService.getArticleTextRemote(id);
        if(redisArticleText.getMessage() == null){
            return redisArticleText;
        }else{
            System.err.println(redisArticleText.getMessage());
            ResultEntity<ArticleTextDTO> articleTextRemote = mySQLRemoteService.getArticleTextRemote(id);
            String htmlContent = articleTextRemote.getData().getHtmlContent();
            String title = articleTextRemote.getData().getTitle();
            redisRemoteService.setArticleTextRemote(id, htmlContent, title);
            return  articleTextRemote;
        }


    }

    @RequestMapping("/get/article/list")
    ResultEntity<RefreshLoadDTO> getArticleList(@RequestParam("time")String time, @RequestParam("type")String type){
        ResultEntity<RefreshLoadDTO> articleListRemote = mySQLRemoteService.getArticleListRemote(time, type);
        return articleListRemote;
    }

    @RequestMapping("/get/article/page")
    ResultEntity<PageInfo<ArticlePreviewDTO>> getArticlePage(
            // 传入的关键字，若未传入，默认值为一个空字符串（不是null）
            @RequestParam(value = "keyword", defaultValue = "") String keyword,
            // 传入的页码，默认值为1
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            // 传入的页面大小，默认值为5
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize){
        ResultEntity<PageInfo<ArticlePreviewDTO>> articlePageRemote = mySQLRemoteService.getArticlePageRemote(keyword, pageNum, pageSize);
        return articlePageRemote;
    }
}
