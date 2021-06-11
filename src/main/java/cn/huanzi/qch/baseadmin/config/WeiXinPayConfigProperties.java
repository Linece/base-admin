package cn.huanzi.qch.baseadmin.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Data
@Component
@ConfigurationProperties(prefix = "weixin")
public class WeiXinPayConfigProperties {

    private String mch_id;
    private String wxappid;
    private String send_name;
    private String total_amount;
    private Integer total_num;
    private String wishing;
    private String client_ip;
    private String act_name;
    private String remark;


    private String key;


}
