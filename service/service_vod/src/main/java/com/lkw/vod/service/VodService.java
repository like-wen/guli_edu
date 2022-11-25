package com.lkw.vod.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface VodService {
    String uploadAliyunVideo(MultipartFile file);

    void removeMoreAlyVideo(List videoIdList);
}
