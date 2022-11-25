package com.lkw.eduservice.client.impl;

import com.lkw.commonutils.R;
import com.lkw.eduservice.client.VodClient;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VodClientImpl implements VodClient {
    @Override
    public R removeAliyunVideo(String id) {
        return R.error().message("删除视频错误");
    }



    @Override
    public R deleteBatch(List videoIdList) {
        return R.error().message("删除多个视频错误");
    }
}

