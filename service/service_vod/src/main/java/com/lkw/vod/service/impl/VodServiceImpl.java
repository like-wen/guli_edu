package com.lkw.vod.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.DeleteVideoResponse;
import com.lkw.servicebase.exceptionhandler.GuliException;
import com.lkw.vod.Utils.ConstantVodUtils;
import com.lkw.vod.Utils.InitVodCilent;
import com.lkw.vod.service.VodService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
@Slf4j
public class VodServiceImpl implements VodService {



    @Override
    public String uploadAliyunVideo(MultipartFile file) {
        try {
            log.info(ConstantVodUtils.ACCESS_KEY_ID + ConstantVodUtils.ACCESS_KEY_SECRET);
            //创建文件读取流
            String fileName = file.getOriginalFilename();
            String title=fileName.split("\\.")[0];
            InputStream inputStream = null;
            inputStream = file.getInputStream();
            UploadStreamRequest request = new UploadStreamRequest(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET, title, fileName, inputStream );
            /* 可指定分片上传时每个分片的大小，默认为2M字节 */
            UploadVideoImpl uploader = new UploadVideoImpl();
            UploadStreamResponse response = uploader.uploadStream(request);
            System.out.print("RequestId=" + response.getRequestId() + "\n");  //请求视频点播服务的请求ID
            String videoId = null;
            if (response.isSuccess()) {
                videoId = response.getVideoId();
            } else {
                videoId = response.getVideoId();
            }
            return videoId;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void removeMoreAlyVideo(List videoIdList) {
        try {
            DefaultAcsClient defaultAcsClient = InitVodCilent.initVodClient(ConstantVodUtils.ACCESS_KEY_ID,ConstantVodUtils.ACCESS_KEY_SECRET);
            //创建一个删除视频request对象
            DeleteVideoRequest deleteVideoRequest = new DeleteVideoRequest();
            //遍历用逗号拼接
            String videoIds = StringUtils.join(videoIdList.toArray(), ",");//数组加分隔符拼接成字符串//或者听说String.join(",",list)也行
            //设置id
            deleteVideoRequest.setVideoIds(videoIds);
            //获取结果
            DeleteVideoResponse acsResponse = defaultAcsClient.getAcsResponse(deleteVideoRequest);
        }catch(ClientException e){
            throw new GuliException(20001,"视频删除失败");
        }
    }
}
