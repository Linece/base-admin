package cn.huanzi.qch.baseadmin.openapi.controller;

import cn.huanzi.qch.baseadmin.common.pojo.Result;
import cn.huanzi.qch.baseadmin.openapi.vo.WeixinAnswerVO;
import cn.huanzi.qch.baseadmin.text.pojo.TextAnswer;
import cn.huanzi.qch.baseadmin.text.pojo.TextTitle;
import cn.huanzi.qch.baseadmin.text.repository.TextAnswerRepository;
import cn.huanzi.qch.baseadmin.text.repository.TextTitleRepository;
import cn.huanzi.qch.baseadmin.user.service.UserPlayService;
import cn.huanzi.qch.baseadmin.user.vo.UserPlayVo;
import com.alibaba.fastjson.JSONObject;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/openApi/")
@RequiredArgsConstructor
public class OpenApiController {
    private final TextTitleRepository textTitleRepository;
    private final TextAnswerRepository textAnswerRepository;
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
            Result<UserPlayVo> save = userPlayService.save(userPlay);
            userPlay = save.getData();
        }


        return userPlay;
    }


}
