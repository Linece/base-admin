package cn.huanzi.qch.baseadmin.openapi.vo;

import cn.huanzi.qch.baseadmin.text.pojo.TextAnswer;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class WeixinAnswerVO {
    private String id;
    private String title;
    private String publish;

    List<TextAnswer> answersList;
}
