package com.lkw.vod.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface VodService {
    String uploadAliyunVideo(MultipartFile file);
}
