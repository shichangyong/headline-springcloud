package org.fall.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@FeignClient("crowd-file")
public interface FileRemoteService {

    @PostMapping(value = "/update/File/remote",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    String updateFileRemote(@RequestPart("avatarfile") MultipartFile file, @RequestParam("filename") String fileName);

    @GetMapping("/get/file/remote")
    @ResponseBody
    feign.Response previewRemote(@RequestParam("url")String url);


}
