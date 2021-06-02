package cn.huanzi.qch.baseadmin.text.controller;

import cn.huanzi.qch.baseadmin.common.controller.CommonController;
import cn.huanzi.qch.baseadmin.text.pojo.TextAnswer;
import cn.huanzi.qch.baseadmin.text.service.TextAnswerService;
import cn.huanzi.qch.baseadmin.text.vo.TextAnswerVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/text/answer")
public class TextAnswerController extends CommonController<TextAnswerVo,TextAnswer,String> {

	@Autowired
	private TextAnswerService textAnswerService;
}
