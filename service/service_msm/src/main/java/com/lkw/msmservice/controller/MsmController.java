package com.lkw.msmservice.controller;

import com.lkw.cmsservice.commonutils.R;
import com.lkw.cmsservice.commonutils.RandomUtil;
import com.lkw.msmservice.service.MsmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@CrossOrigin
@RestController
@RequestMapping("/edumsm/msm")
public class MsmController {

    @Autowired
    private MsmService msmService;

    @GetMapping("send/{phone}")
    public R sendMsm(@PathVariable String phone){
        String code ;
        //2、如果redis获取不到，进行阿里云发送

        //生成随机数，传递阿里云进行发送
        code = RandomUtil.getFourBitRandom();
        Map<String, Object> param = new HashMap<>();
        param.put("code",code);
        //调用service的方法，发送
        boolean isSend = msmService.send(param,phone);
        if (isSend) {
            //阿里云发送成功，把发送成功的验证码放入redis缓存中
            //设置有效时间
            //redisTemplate.opsForValue().set(phone,code,5, TimeUnit.MINUTES);
            return R.ok();
        }else
            return R.error().message("短信发送失败");
    }
}
