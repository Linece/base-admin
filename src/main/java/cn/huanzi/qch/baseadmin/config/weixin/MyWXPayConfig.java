package cn.huanzi.qch.baseadmin.config.weixin;

import lombok.Builder;
import lombok.Data;

import java.io.InputStream;

@Data
@Builder
public class MyWXPayConfig extends WXPayConfig {
    private String appId;
    private String mchId;
    private String key;
    private InputStream certStream;
    private WXPayDomain wxPayDomain;


    @Override
    protected String getAppID() {
        return this.appId;
    }

    @Override
    protected String getMchID() {
        return this.mchId;
    }

    @Override
    protected String getKey() {
        return this.key;
    }

    @Override
    InputStream getCertStream() {
        return this.certStream;
    }

    @Override
    IWXPayDomain getWXPayDomain() {
        return this.wxPayDomain;
    }
}
