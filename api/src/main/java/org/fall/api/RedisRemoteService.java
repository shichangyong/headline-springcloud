package org.fall.api;

import org.fall.entity.dto.ArticleTextDTO;
import org.fall.util.ResultEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("crowd-redis")
public interface RedisRemoteService {
    @RequestMapping("redis/get/articleText/remote")
    ResultEntity<ArticleTextDTO> getArticleTextRemote(@RequestParam("id")int id);

    @RequestMapping("redis/add/articleText/remote")
    ResultEntity<Boolean> setArticleTextRemote(@RequestParam("id")int id,
                                                      @RequestParam("htmlContent")String htmlContent,
                                                      @RequestParam("title")String title);
}
