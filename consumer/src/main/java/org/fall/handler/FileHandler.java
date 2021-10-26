package org.fall.handler;

import feign.Response;
import org.fall.api.FileRemoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;


@RestController
public class FileHandler {
    @Autowired
    FileRemoteService fileRemoteService;

    @PostMapping(value = "/update/File",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    String updateFile(@RequestPart("avatarfile") MultipartFile file, @RequestParam("filename") String fileName){
        String result = fileRemoteService.updateFileRemote(file, fileName);
        return  result;
    }

    @RequestMapping("/get/file")
    @ResponseBody
    void preview(@RequestParam("url")String url, HttpServletResponse servletResponse){
        Response response = fileRemoteService.previewRemote(url);
        Response.Body body = response.body();

        InputStream fileInputStream = null;
        OutputStream outStream;
        try {
            fileInputStream = body.asInputStream();
            outStream = servletResponse.getOutputStream();

            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = fileInputStream.read(bytes)) != -1) {
                outStream.write(bytes, 0, len);
            }
            fileInputStream.close();
            outStream.close();
            outStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
