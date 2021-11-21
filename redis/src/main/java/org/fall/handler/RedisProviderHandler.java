package org.fall.handler;

import org.fall.entity.dto.ArticleTextDTO;
import org.fall.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
public class RedisProviderHandler {

    @Autowired
    RedisTemplate redisTemplate;

    @RequestMapping("redis/get/articleText/remote")
    public ResultEntity<ArticleTextDTO> getArticleTextRemote(@RequestParam("id")int id) {
        try {
            Map<String, String>  mapResult = null;
            String  key = "articleText_" + id;
            HashOperations hashOperations = redisTemplate.opsForHash();
            mapResult = hashOperations.entries(key);
            if(mapResult.get("htmlContent") != null && mapResult.get("title") != null){

                ArticleTextDTO articleTextDTO = new ArticleTextDTO();
                articleTextDTO.setTitle(mapResult.get("htmlContent"));
                articleTextDTO.setHtmlContent(mapResult.get("title"));
                return ResultEntity.successWithData(articleTextDTO);
            }else{
                return ResultEntity.failed("键不存在!");
            }

        } catch (Exception e){
            return ResultEntity.failed(e.getMessage());
        }
    }

    @RequestMapping("redis/add/articleText/remote")
    public ResultEntity<Boolean> setArticleTextRemote(@RequestParam("id")int id,
                                                             @RequestParam("htmlContent")String htmlContent,
                                                             @RequestParam("title")String title) {
        try {
            HashMap<String, String> value = new HashMap<>();
            value.put("htmlContent", htmlContent);
            value.put("title", title);
            String  key = "articleText_" + id;
            Long expireTime = Long.valueOf(5);
            TimeUnit timeUnit = TimeUnit.MINUTES;
            boolean result = false;
            try {
                HashOperations hashOperations = redisTemplate.opsForHash();
                hashOperations.putAll(key, value);
                redisTemplate.expire(key, expireTime, timeUnit);
                result = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return ResultEntity.successWithData(result);
        } catch (Exception e){
            return ResultEntity.failed(e.getMessage());
        }
    }
}
