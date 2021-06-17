package cn.huanzi.qch.baseadmin.config.weixin;

public class WXPayDomain implements IWXPayDomain {
    @Override
    public void report(String domain, long elapsedTimeMillis, Exception ex) {

    }

    @Override
    public DomainInfo getDomain(WXPayConfig config) {
        DomainInfo domainInfo = new DomainInfo("api.mch.weixin.qq.com",true);
        return domainInfo;
    }
}
