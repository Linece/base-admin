package cn.huanzi.qch.baseadmin.openapi.controller;

import cn.huanzi.qch.baseadmin.common.pojo.Result;
import cn.huanzi.qch.baseadmin.openapi.vo.WeixinAnswerVO;
import cn.huanzi.qch.baseadmin.text.pojo.TextAnswer;
import cn.huanzi.qch.baseadmin.text.pojo.TextTitle;
import cn.huanzi.qch.baseadmin.text.repository.TextAnswerRepository;
import cn.huanzi.qch.baseadmin.text.repository.TextTitleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/openApi/")
@RequiredArgsConstructor
public class OpenApiController {
    private final TextTitleRepository textTitleRepository;
    private final TextAnswerRepository textAnswerRepository;
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
}
