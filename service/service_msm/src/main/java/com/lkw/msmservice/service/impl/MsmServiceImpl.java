package com.lkw.msmservice.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.teaopenapi.models.Config;
import com.lkw.msmservice.Utils.ConstantMsmUtil;
import com.lkw.msmservice.service.MsmService;
import com.lkw.servicebase.exceptionhandler.GuliException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Transactional
@Service
public class MsmServiceImpl implements MsmService {

    @Override
    public boolean send(Map<String, Object> param, String phone){
        try {
            //创建客户端
            Client client = new Client(new Config()
                    .setEndpoint("dysmsapi.aliyuncs.com")
                    .setAccessKeyId(ConstantMsmUtil.ACCESS_KEY_ID)
                    .setAccessKeySecret(ConstantMsmUtil.ACCESS_KEY_SECRET));

            //创建请求
            SendSmsRequest sendSmsRequest = new SendSmsRequest()
                    .setSignName("阿里云短信测试")
                    .setTemplateCode("SMS_154950909")
                    .setPhoneNumbers(phone)
                    .setTemplateParam(JSONObject.toJSONString(param));

            //查看客户端
            SendSmsResponse sendSmsResponse = client.sendSms(sendSmsRequest);
            //输出打印信息
            //System.out.println(sendSmsResponse.getBody().getMessage());
        } catch (Exception e) {
            throw new GuliException();
        }
        return true;
    }
}
