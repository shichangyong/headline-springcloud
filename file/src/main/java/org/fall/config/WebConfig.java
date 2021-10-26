package org.fall.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * 读取项目相关配置
 *
 * @author 健康搬砖人
 */
@Component
@ConfigurationProperties(prefix = "file-service")
public class WebConfig {

    /**
     * 上传路径
     */
    private static String profile;

    public static String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        WebConfig.profile = profile;
    }
    
    // 获取上传图片路径
    public static String getAvatarPath() {
        return profile + "pictures/";
    }
	
    // 获取下载路径
    public static String getDownloadPath() {
        return profile + "download/";
    }
	
    // 获取上传视频路径
    public static String getVideoPath() {
        return profile + "videos/";
    }
}
