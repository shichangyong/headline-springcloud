package org.fall.handler;

import org.fall.config.WebConfig;
import org.fall.util.FileUploadUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
public class FileHandler {
    private static final Logger log = LoggerFactory.getLogger(FileHandler.class);

    /**
     * 保存文件
     *
     * @param file 图像文件或视频文件
     * @return 结果
     */
    @PostMapping(value = "/update/File/remote",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    public String updateFileRemote(@RequestPart("avatarfile") MultipartFile file,
                             @RequestParam("filename") String fileName) {
        try {
            if (!file.isEmpty()) {
                String baseDir = WebConfig.getProfile();
                String avatar = FileUploadUtils.uploadFile(baseDir, file,fileName);
                System.out.println("avatar:" + avatar);
                // TODO: 此处可以根据项目需求的业务进行操作，例如此处操作的是头像，可以将头像url保存到数据库，用户登录后，获取相应的url获取头像图片。
            }
            return "success";
        } catch (Exception e) {
            return "error";
        }
    }



    /**
     * 预览文件
     *
     * @param url 图像文件或视频文件地址
     */
    @GetMapping("/get/file/remote")
    @ResponseBody
    public void previewFileRemote(@RequestParam("url")String url, HttpServletResponse response) {
        //HttpServletResponse response =((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
        url = "E:/HeadlinesFiles/"+url;
        log.error("文件预览[{}]",url);
        InputStream in = null;
        ServletOutputStream sos = null;
        File file = new File(url);
        try {
            in = new FileInputStream(file);// TODO 中文可能出现乱码
            sos = response.getOutputStream();
            byte[] b = new byte[1024];
            while (in.read(b) != -1) {
                sos.write(b);    //输出
            }
            sos.flush();           //刷新
        } catch (Exception ex) {
            log.error("文件预览失败，异常信息=[{}]", ex);
            ex.printStackTrace();
        } finally {
            try {
                in.close(); //关闭文件读取流，输出流
                sos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 查看文件
     *
     * @param url 图片url
     * @param response 请求响应
     */
    @RequestMapping(value = "/noLogin/readImageFile",method = RequestMethod.GET)
    @ResponseBody
    public void getUrlFile(String url, HttpServletResponse response) {
        // 这里的url，我为了测试，直接就写静态的。
        url = "E:/HeadlinesFiles/pictures/2021/09/30/078f37bc9d62a642b0b4b3f49f77e887.jpg";
        File file = new File(url);
        // 后缀名
        String suffixName = url.substring(url.lastIndexOf("."));
        //判断文件是否存在如果不存在就返回默认图片或者进行异常处理
        if (!(file.exists() && file.canRead())) {
//            file = new File("xxx/xxx.jpg");
            System.out.println("文件不存在");
        }
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            byte[] data = new byte[(int) file.length()];
            inputStream.close();
            response.setContentType("image/jpg;charset=utf-8");
            OutputStream stream = response.getOutputStream();
            stream.write(data);
            stream.flush();
            stream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 下载文件
     *
     * @param url 文件url
     * @param response 请求响应
     */
    @RequestMapping(value = "/noLogin/downloadFile",method =RequestMethod.GET)
    @ResponseBody
    public void getUrlDownload(String url, HttpServletResponse response) {
        url = "C:/Users/Administrator/Pictures/ff6e90eeb2bac1ce6279eded47dd1672.jpg";
        File file = new File(url);
        // 后缀名
        String suffixName = url.substring(url.lastIndexOf("."));
        //判断文件是否存在如果不存在就进行异常处理
        if (!(file.exists() && file.canRead())) {
            System.out.println("文件不存在");
        }
        FileInputStream inputStream = null;
        BufferedInputStream bufferedInputStream = null;
        try {
            inputStream = new FileInputStream(file);
            byte[] data = new byte[(int) file.length()];
            int length = inputStream.read(data);
            inputStream.close();
            response.setContentType("application/force-download");
            //通过设置头信息给文件命名，也即是，在前端，文件流被接受完还原成原文件的时候会以你传递的文件名来命名
            response.addHeader("Content-Disposition",String.format("attachment; filename=\"%s\"", file.getName()));
            OutputStream stream = response.getOutputStream();
            stream.write(data);
            stream.flush();
            stream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
