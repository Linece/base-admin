package cn.huanzi.qch.baseadmin.config;

import cn.huanzi.qch.baseadmin.config.weixin.MyWXPayConfig;
import cn.huanzi.qch.baseadmin.config.weixin.WXPayDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;

@Configuration
public class AppConfig {

    @Autowired
    private WeiXinPayConfigProperties weiXinPayConfigProperties;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {

        return builder
                .setConnectTimeout(Duration.ofMillis(3000))
                .setReadTimeout(Duration.ofMillis(3000))
                .build();
    }


}
