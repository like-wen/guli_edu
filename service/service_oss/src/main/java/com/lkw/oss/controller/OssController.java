package com.lkw.oss.controller;

import com.lkw.cmsservice.commonutils.R;
import com.lkw.oss.service.OssService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/eduoss/fileoss")
@CrossOrigin
public class OssController {

    @Autowired
    private OssService ossService;

    //上传头像的方法
    @PostMapping(value = "/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public R uploadOssFile(@ApiParam(value = "讲师图片", required = true)@RequestPart("file")MultipartFile file){
        //获取文件上传 MultipartFile
        //返回上传到oss的路径
        String url = ossService.uploadFileAvatar(file);

        return R.ok().data("url",url);
    }
}