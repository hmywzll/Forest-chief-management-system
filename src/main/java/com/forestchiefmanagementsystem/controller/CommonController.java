package com.forestchiefmanagementsystem.controller;

import com.forestchiefmanagementsystem.util.R;
import com.forestchiefmanagementsystem.util.VerifyCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Objects;

@Controller
@RequestMapping
@Slf4j
@ResponseBody
public class CommonController {
    @Value("${forest_chief_management_system.img_url}")
    String imgUrl;
    static final String suffix = ".JPEG";

    static final String[] folder = {"verify", "head","label","example","task","patrol"};

    /**
     * 检查imgUrl路径，如果为空则创建
     */
    public void checkImgUrl(String url) {
        File file = new File(imgUrl + url);
        if (!file.exists()) file.mkdirs();
    }

    /**
     * 生成验证码
     *
     * @return 字符串数组 0验证码名字 1验证码值
     * @throws IOException
     */
    @GetMapping("/verify_code")
    public R<String[]> getVerificationCode() throws IOException {
        VerifyCode verifyCode = new VerifyCode();
        BufferedImage image = verifyCode.createImage();
        String text = verifyCode.getText();
        LocalDateTime.now();
        String imgName = "verify" + String.valueOf(System.currentTimeMillis());
        checkImgUrl("verify/");
        VerifyCode.output(image, Files.newOutputStream(Paths.get(imgUrl + "verify/" + imgName + ".JPEG")));
        return R.success(new String[]{imgName, text});
    }

    /**
     * 下载图片
     *
     * @param httpServletResponse
     * @param imgName
     * @throws IOException
     */
    @GetMapping("/download_img/{imgName}")
    public void downloadImg(HttpServletResponse httpServletResponse, @PathVariable String imgName) throws IOException {
        for (int i = 0; i <= folder.length; i++) {
            if (i == folder.length) return;
            if (imgName.contains(folder[i])) {
                imgName =folder[i] + "/"+imgName;
                break;
            }
        }
        String path = imgUrl + imgName + suffix;

        BufferedInputStream bufferedInputStream = new BufferedInputStream(Files.newInputStream(Paths.get(path)));
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(httpServletResponse.getOutputStream());
        byte[] bt = new byte[1024];
        int len = 0;
        while ((len = bufferedInputStream.read(bt)) != -1) {
            bufferedOutputStream.write(bt, 0, len);
        }
        bufferedInputStream.close();
        bufferedOutputStream.close();
    }

    /**
     * 上传图片
     *
     * @param multipartHttpServletRequest
     * @param address                     判断存放路径
     * @throws IOException
     */
    @PostMapping("/upload_img/{address}")
    public R<String> uploadImg(MultipartHttpServletRequest multipartHttpServletRequest, @PathVariable String address) throws IOException {
        String name = "";
        for (int i = 0; i <= folder.length; i++) {
            if (i == folder.length) return R.error("上传图片失败");
            if (Objects.equals(folder[i], address)) {
                name = folder[i] + System.currentTimeMillis();
                break;
            }
        }
        String path = imgUrl + address + "/";
        checkImgUrl(address);
        path += name + suffix;
        Iterator<String> fileNames = multipartHttpServletRequest.getFileNames();
        if (fileNames.hasNext()) {
            String next = fileNames.next();
            MultipartFile file = multipartHttpServletRequest.getFile(next);
            file.transferTo(new File(path));
        }
        return R.success(name);
    }
}