package org.fall.api;

import com.github.pagehelper.PageInfo;
import org.fall.entity.dto.*;
import org.fall.entity.po.*;
import org.fall.util.ResultEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("crowd-mysql")
public interface MySQLRemoteService {

    @RequestMapping("/save/headlines/remote")
    ResultEntity<String> saveMicroHeadlinesRemote(@RequestBody MicroHeadlinesPO microHeadlinesPO);

    @RequestMapping("/save/article/remote")
    String saveArticleRemote(@RequestBody ArticlePO articlePO);

    @RequestMapping("/get/headlines/remote")
    ResultEntity<MicroHeadlinesDTO> getMicroHeadlinesRemote(@RequestParam("id")int id);

    @RequestMapping("/get/articlePreview/remote")
    ResultEntity<ArticlePreviewDTO> getArticlePreviewRemote(@RequestParam("id")int id);

    @RequestMapping("/get/headlines/list/remote")
    ResultEntity<RefreshLoadDTO> getHeadlinesListRemote(@RequestParam("time")String time, @RequestParam("type")String type);

    @RequestMapping("/get/articleText/remote")
    ResultEntity<ArticleTextDTO> getArticleTextRemote(@RequestParam("id")int id);

    @RequestMapping("/get/commentPage/remote")
    ResultEntity<PageInfo<CommentDTO>> getCommentPageRemote(
            // 传入的关键字，若未传入，默认值为一个空字符串（不是null）
            @RequestParam(value = "targetType", defaultValue = "") String targetType,
            // 传入的关键字，若未传入，默认值为一个空字符串（不是null）
            @RequestParam(value = "targetId", defaultValue = "") Integer targetId,
            // 传入的页码，默认值为1
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            // 传入的页面大小，默认值为5
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize);

    @RequestMapping("/get/user/by/phone")
    ResultEntity<UserPO> getUserByPhone(@RequestParam("phoneNumber") String phoneNumber);

    @RequestMapping("/upload/comment/remote")
    String uploadCommentRemote(@RequestBody CommentPO commentPO);

    @RequestMapping("/add/articleComment/remote")
    String addArticleCommentRemote(@RequestParam("id")int id);

    @RequestMapping("/add/commentReplies/remote")
    String addCommentRepliesRemote(@RequestParam("id")int id) ;

    @RequestMapping("/add/headlinesComment/remote")
    String addHeadlinesCommentRemote(@RequestParam("id")int id);

    @RequestMapping("/get/article/list/remote")
    ResultEntity<RefreshLoadDTO> getArticleListRemote(@RequestParam("time")String time, @RequestParam("type")String type);

    @RequestMapping("/save/video/remote")
    String saveVideoRemote(@RequestBody VideoPO video);

    @RequestMapping("/get/video/list/remote")
    ResultEntity<RefreshLoadDTO> getVideoListRemote(@RequestParam("time")String time, @RequestParam("type")String type);

    @RequestMapping("/get/videoPreview/remote")
    ResultEntity<VideoDTO> getVideoPreviewRemote(@RequestParam("id")int id);

    @RequestMapping("/add/videoComment/remote")
    String addVideoCommentRemote(@RequestParam("id")int id);

    @RequestMapping("/get/videoPage/remote")
    ResultEntity<PageInfo<VideoDTO>> getVideoPageRemote(
            // 传入的关键字，若未传入，默认值为一个空字符串（不是null）
            @RequestParam(value = "keyword", defaultValue = "") String keyword,
            // 传入的页码，默认值为1
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            // 传入的页面大小，默认值为5
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize);

    @RequestMapping("/get/headlinesPage/remote")
    ResultEntity<PageInfo<MicroHeadlinesDTO>> getHeadlinesPageRemote(
            // 传入的关键字，若未传入，默认值为一个空字符串（不是null）
            @RequestParam(value = "keyword", defaultValue = "") String keyword,
            // 传入的页码，默认值为1
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            // 传入的页面大小，默认值为5
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize);

    @RequestMapping("/get/articlePage/remote")
    ResultEntity<PageInfo<ArticlePreviewDTO>> getArticlePageRemote(
            // 传入的关键字，若未传入，默认值为一个空字符串（不是null）
            @RequestParam(value = "keyword", defaultValue = "") String keyword,
            // 传入的页码，默认值为1
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            // 传入的页面大小，默认值为5
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize);

    @RequestMapping("/update/user/remoter")
    ResultEntity<String> updateUserRemoter(@RequestParam("id") String id,
                                           @RequestParam(value = "phoneNumber", required=false) String  phoneNumber,
                                           @RequestParam(value = "nickname", required=false) String  nickname,
                                           @RequestParam(value = "fileName", required = false) String fileName,
                                           @RequestParam(value = "password", required=false) String  password,
                                           @RequestParam(value = "authentication", required=false) String  authentication);
}
