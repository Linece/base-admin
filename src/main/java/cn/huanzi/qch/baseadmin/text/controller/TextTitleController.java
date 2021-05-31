package cn.huanzi.qch.baseadmin.text.controller;

import cn.huanzi.qch.baseadmin.annotation.Decrypt;
import cn.huanzi.qch.baseadmin.annotation.Encrypt;
import cn.huanzi.qch.baseadmin.common.controller.CommonController;
import cn.huanzi.qch.baseadmin.common.pojo.PageInfo;
import cn.huanzi.qch.baseadmin.common.pojo.Result;
import cn.huanzi.qch.baseadmin.text.pojo.TextTitle;
import cn.huanzi.qch.baseadmin.text.service.TextTitleService;
import cn.huanzi.qch.baseadmin.text.vo.TextTitleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/text/title/")
public class TextTitleController extends CommonController<TextTitleVo,TextTitle,Integer> {

	@Autowired
	private TextTitleService textTitleService;


	@GetMapping("/index")
	public ModelAndView index(){
		return new ModelAndView("/text/text_title");
	}

	@PostMapping("/content")
	@Decrypt
	@Encrypt
	public Result<PageInfo<TextTitle>> content(TextTitleVo textTitleVo){
		PageInfo<TextTitle> pageInfo = new PageInfo<TextTitle>();
		Result<Page<TextTitle>> page = this.textTitleService.findPage(textTitleVo);
		pageInfo.setRows(page.getData().getContent());
		pageInfo.setRecords(page.getData().getNumberOfElements());
		pageInfo.setTotal(page.getData().getTotalPages());
		pageInfo.setPage(textTitleVo.getPage());
		pageInfo.setPageSize(textTitleVo.getRows());
		return Result.of(pageInfo);
	}


	@GetMapping("/answerIndex")
	public ModelAndView answerIndex(){
		return new ModelAndView("/text/text_content");
	}
}
