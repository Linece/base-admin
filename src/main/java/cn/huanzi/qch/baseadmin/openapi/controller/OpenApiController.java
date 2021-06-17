package cn.huanzi.qch.baseadmin.openapi.controller;

import cn.huanzi.qch.baseadmin.common.pojo.Result;
import cn.huanzi.qch.baseadmin.config.WeiXinPayConfigProperties;
import cn.huanzi.qch.baseadmin.config.weixin.MyWXPayConfig;
import cn.huanzi.qch.baseadmin.config.weixin.WXPayDomain;
import cn.huanzi.qch.baseadmin.config.weixin.WXPayRequest;
import cn.huanzi.qch.baseadmin.config.weixin.WXPayUtil;
import cn.huanzi.qch.baseadmin.openapi.vo.WeixinAnswerVO;
import cn.huanzi.qch.baseadmin.text.pojo.TextAnswer;
import cn.huanzi.qch.baseadmin.text.pojo.TextTitle;
import cn.huanzi.qch.baseadmin.text.repository.TextAnswerRepository;
import cn.huanzi.qch.baseadmin.text.repository.TextTitleRepository;
import cn.huanzi.qch.baseadmin.user.service.UserOrderService;
import cn.huanzi.qch.baseadmin.user.service.UserPlayService;
import cn.huanzi.qch.baseadmin.user.vo.UserOrderVo;
import cn.huanzi.qch.baseadmin.user.vo.UserPlayVo;
import cn.huanzi.qch.baseadmin.util.UUIDUtil;
import com.alibaba.fastjson.JSONObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.*;

@Slf4j
@RestController
@RequestMapping("/openApi/")
@RequiredArgsConstructor
public class OpenApiController {
    private final WeiXinPayConfigProperties weiXinPayConfigProperties;
    private final TextAnswerRepository textAnswerRepository;
    private final TextTitleRepository textTitleRepository;
    private final UserOrderService userOrderService;
    private final UserPlayService userPlayService;
    private final RestTemplate restTemplate;
    private final String PUBLISH_STATUS = "已发布";


    @GetMapping("/get/answer/list")
    public Result<List<WeixinAnswerVO>> test() {

        List<WeixinAnswerVO> result = new ArrayList<>();
        List<TextTitle> list = textTitleRepository.findByPublish(PUBLISH_STATUS);
        for (TextTitle textTitle : list) {
            WeixinAnswerVO vo = new WeixinAnswerVO();
            BeanUtils.copyProperties(textTitle, vo);
            List<TextAnswer> answersList = textAnswerRepository.findByTextTitleId(textTitle.getId());
            answersList.sort(Comparator.comparing(TextAnswer::getAnswerIndex));
            vo.setAnswersList(answersList);
            result.add(vo);
        }
        return Result.of(result);
    }

    /**
     * 微信登录 获取openId
     *
     * @param code
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/login")
    public UserPlayVo getUserInfo(@RequestParam(name = "code") String code) throws Exception {

        String url = "https://api.weixin.qq.com/sns/jscode2session";
        url += "?appid=wxe391560158d7a624";//自己的appid
        url += "&secret=e7f63b53b68e2bf81a6d725f2fdb6490";//密匙
        url += "&js_code=" + code;
        url += "&grant_type=authorization_code";


        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        String body = response.getBody();

        JSONObject jsonObject = JSONObject.parseObject(body);
        String openid = (String) jsonObject.get("openid");


        UserPlayVo userPlay = userPlayService.findByOpenId(openid);
        if (ObjectUtils.isEmpty(userPlay)) {
            userPlay = new UserPlayVo();
            userPlay.setOpenId(openid);
            userPlay.setScore(0);
            userPlay.setStatus(0);
            userPlay.setCreateTime(new Date());
            userPlay.setUpdateTime(new Date());
            userPlayService.save(userPlay);
        }


        return userPlay;
    }

    /**
     * 发红包
     *
     * @param openId
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/send")
    public String send(
            @RequestParam(name = "openId") String openId,
            @RequestParam(name = "score") Integer score
    ) throws Exception {

        // 生成订单
        UserOrderVo userOrder = userOrderService.createUserOrder(openId);

        String nonceStr = UUIDUtil.getUUID();
        // 发送红包
        String url = "/mmpaymkttransfers/sendredpack";

        Map<String, String> paramMap = new HashMap<>();
        // 随机串
        paramMap.put("nonce_str", nonceStr);
        // 商户订单号
        paramMap.put("mch_billno", userOrder.getOrderId());
        // 商户号
        paramMap.put("mch_id", weiXinPayConfigProperties.getMch_id());
        // 公众账号appid
        paramMap.put("wxappid", weiXinPayConfigProperties.getWxappid());
        // 商户名称
        paramMap.put("send_name", weiXinPayConfigProperties.getSend_name());
        // 用户openid
        paramMap.put("re_openid", openId);
        // 付款金额
        paramMap.put("total_amount", weiXinPayConfigProperties.getTotal_amount());
        // 红包发放总人数
        paramMap.put("total_num", weiXinPayConfigProperties.getTotal_num().toString());
        // 红包祝福语
        paramMap.put("wishing", weiXinPayConfigProperties.getWishing());
        // ip
        paramMap.put("client_ip", weiXinPayConfigProperties.getClient_ip());
        // 活动名称
        paramMap.put("act_name", weiXinPayConfigProperties.getAct_name());
        // remark
        paramMap.put("remark", weiXinPayConfigProperties.getRemark());


        // 签名
        String sign = WXPayUtil.generateSignature(paramMap, weiXinPayConfigProperties.getKey());
        // 签名
        paramMap.put("sign", sign);

        String s = WXPayUtil.mapToXml(paramMap);
        WXPayRequest wxPayRequest = new WXPayRequest(this.wxPayConfig());
        // 使用证书
        String s1 = wxPayRequest.requestWithCert(url, nonceStr, s, true);
        log.info(s1);
        // 回调修改订单状态
        Map<String, String> map = WXPayUtil.xmlToMap(s1);

        String result_code = map.get("result_code");
        if (result_code.equalsIgnoreCase("SUCCESS")) {
            // 更新 用户play
            UserPlayVo byOpenId = userPlayService.findByOpenId(openId);
            byOpenId.setScore(score);
            byOpenId.setStatus(1);
            userPlayService.save(byOpenId);
            // 更新订单状态
            userOrder.setOrderFinishTime(new Date());
            userOrder.setOrderStatus(1);
            userOrderService.save(userOrder);
        } else {
            userOrder.setOrderStatus(2);
            userOrderService.save(userOrder);
        }

        return "";
    }

    public MyWXPayConfig wxPayConfig() throws Exception {

        InputStream inputStream = Objects.requireNonNull(MyWXPayConfig.class.getClassLoader()
                .getResourceAsStream("static/wx/apiclient_cert.p12"));

        byte[] bytes = IOUtils.toByteArray(inputStream);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);

        WXPayDomain wxPayDomain = new WXPayDomain();


        return MyWXPayConfig.builder()
                .appId(weiXinPayConfigProperties.getWxappid())
                .mchId(weiXinPayConfigProperties.getMch_id())
                .key(weiXinPayConfigProperties.getKey())
                .certStream(byteArrayInputStream)
                .wxPayDomain(wxPayDomain)
                .build();
    }

}
