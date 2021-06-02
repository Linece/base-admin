package cn.huanzi.qch.baseadmin.text.service;

import cn.huanzi.qch.baseadmin.common.service.CommonService;
import cn.huanzi.qch.baseadmin.text.pojo.TextAnswer;
import cn.huanzi.qch.baseadmin.text.vo.TextAnswerVo;
import org.springframework.stereotype.Service;

import java.util.List;


public interface TextAnswerService extends CommonService<TextAnswerVo,TextAnswer,String> {
	public List<TextAnswer> getTextAnswers(String textTitleId);

	public  void deleteByTextTitleId(String textTitleId);
}
