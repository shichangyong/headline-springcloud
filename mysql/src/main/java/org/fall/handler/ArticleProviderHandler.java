package org.fall.handler;
import com.github.pagehelper.PageInfo;
import org.fall.entity.dto.ArticlePreviewDTO;
import org.fall.entity.dto.ArticleTextDTO;
import org.fall.entity.dto.RefreshLoadDTO;
import org.fall.entity.po.ArticlePO;
import org.fall.entity.vo.PreviewByTimeVO;
import org.fall.service.api.ArticleService;
import org.fall.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ArticleProviderHandler {
    @Autowired
    ArticleService articleService;
    @RequestMapping("/save/article/remote")
    public String saveArticleRemote(@RequestBody ArticlePO articlePO){
        try{
            articleService.saveArticle(articlePO);
            return "SUCCESS";
        } catch (Exception e){
            return e.getMessage();
        }
    }

    @RequestMapping("/get/articlePreview/remote")
    public ResultEntity<ArticlePreviewDTO> getArticlePreviewRemote(@RequestParam("id")int id) {
        try {
            ArticlePreviewDTO articlePreview = articleService.getArticlePreview(id);
            return ResultEntity.successWithData(articlePreview);
        } catch (Exception e){
            return ResultEntity.failed(e.getMessage());
        }
    }

    @RequestMapping("/get/articleText/remote")
    public ResultEntity<ArticleTextDTO> getArticleTextRemote(@RequestParam("id")int id) {
        try {
            ArticlePO articlePO = articleService.selectByPrimaryKey(id);
            ArticleTextDTO articleTextDTO = new ArticleTextDTO();
            articleTextDTO.setTitle(articlePO.getTitle());
            articleTextDTO.setHtmlContent(articlePO.getHtmlContent());
            return ResultEntity.successWithData(articleTextDTO);
        } catch (Exception e){
            return ResultEntity.failed(e.getMessage());
        }
    }

    @RequestMapping("/add/articleComment/remote")
    String addArticleCommentRemote(@RequestParam("id")int id){
        try{
            int i = articleService.addCommentNumber(id);
            if(i != 0){
                return "SUCCESS";
            }else{
                return "FAILED";
            }
        }catch (Exception e){
            return e.getMessage();
        }
    }

    @RequestMapping("/get/article/list/remote")
    public ResultEntity<RefreshLoadDTO> getArticleListRemote(@RequestParam("time")String time, @RequestParam("type")String type) {
        PreviewByTimeVO previewByTimeVO = new PreviewByTimeVO(time, type);
        List<ArticlePO> listByTimeRemote = articleService.getListByTimeRemote(previewByTimeVO);
        RefreshLoadDTO refreshLoadDTO = new RefreshLoadDTO();
        if(listByTimeRemote.size() != 0){
            int length = listByTimeRemote.size();
            refreshLoadDTO.setLength(length);
            refreshLoadDTO.setBelong("article");
            refreshLoadDTO.setNewest(listByTimeRemote.get(length-1).getTime());
            refreshLoadDTO.setOldest(listByTimeRemote.get(0).getTime());
            List<Integer> listId = new ArrayList<>();

            for (ArticlePO articlePO : listByTimeRemote) {
                int id = articlePO.getId();
                listId.add(id);
            }
            refreshLoadDTO.setListId(listId);
            return ResultEntity.successWithData(refreshLoadDTO);
        }else{
            return ResultEntity.failed("暂无数据!");
        }
    }

    @RequestMapping("/get/articlePage/remote")
    public ResultEntity<PageInfo<ArticlePreviewDTO>> getArticlePageRemote(
            // 传入的关键字，若未传入，默认值为一个空字符串（不是null）
            @RequestParam(value = "keyword", defaultValue = "") String keyword,
            // 传入的页码，默认值为1
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            // 传入的页面大小，默认值为5
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        //从AdminService中得到对应传参的列表
        try {
            PageInfo<ArticlePreviewDTO> pageInfo = articleService.getPageInfo(keyword, pageNum, pageSize);
            List<ArticlePreviewDTO> list = pageInfo.getList();
            for (ArticlePreviewDTO articlePreviewDTO:list) {
                articlePreviewDTO.getUserPO().setPassword(null);
            }
            return ResultEntity.successWithData(pageInfo);
        } catch (Exception e){
            return ResultEntity.failed(e.getMessage());
        }
    }
}
