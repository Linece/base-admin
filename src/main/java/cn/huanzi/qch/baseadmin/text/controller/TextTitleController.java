package cn.huanzi.qch.baseadmin.text.controller;

import cn.huanzi.qch.baseadmin.annotation.Decrypt;
import cn.huanzi.qch.baseadmin.annotation.Encrypt;
import cn.huanzi.qch.baseadmin.common.controller.CommonController;
import cn.huanzi.qch.baseadmin.common.pojo.PageInfo;
import cn.huanzi.qch.baseadmin.common.pojo.Result;
import cn.huanzi.qch.baseadmin.text.pojo.TextAnswer;
import cn.huanzi.qch.baseadmin.text.pojo.TextTitle;
import cn.huanzi.qch.baseadmin.text.service.TextAnswerService;
import cn.huanzi.qch.baseadmin.text.service.TextTitleService;
import cn.huanzi.qch.baseadmin.text.vo.TextAnswerVo;
import cn.huanzi.qch.baseadmin.text.vo.TextTitleVo;
import cn.huanzi.qch.baseadmin.util.CopyUtil;
import cn.huanzi.qch.baseadmin.util.StringToolUtil;
import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/text/title/")
public class TextTitleController extends CommonController<TextTitleVo,TextTitle,String> {

	@Autowired
	private TextTitleService textTitleService;

	@Autowired
	private TextAnswerService textAnswerService;



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

	@GetMapping("/view")
	public ModelAndView view(String id){
		Map<String,Object> dataMap = new HashMap<String,Object>();
		TextTitleVo textTitleVo = this.textTitleService.get(id).getData();
		List<TextAnswer> textAnswers = this.textAnswerService.getTextAnswers(id);
		dataMap.put("textTitle", textTitleVo);
		dataMap.put("textAnswers", textAnswers);
		return new ModelAndView("/text/text_content_view",dataMap);
	}

	@GetMapping("/edit")
	public ModelAndView edit(String id){
		Map<String,Object> dataMap = new HashMap<String,Object>();
		TextTitleVo textTitleVo = this.textTitleService.get(id).getData();
		List<TextAnswer> textAnswers = this.textAnswerService.getTextAnswers(id);
		dataMap.put("textTitle", textTitleVo);
		dataMap.put("textAnswers", textAnswers);
		return new ModelAndView("/text/text_content_edit",dataMap);
	}

	@PostMapping("/save/answer")
	@Decrypt
	@Encrypt
	public Result save(String data){
		TextTitleVo textTitle = new TextTitleVo();
		if(!StringUtils.isEmpty(data)){
			String[] dataArr = data.split("&");
			textTitle.setTitle(StringToolUtil.dataMap(dataArr[1]).get("sourceValue"));
			Result<TextTitleVo> titleVoResult = textTitleService.save(textTitle);
			TextAnswerVo textAnswer = new TextAnswerVo();
			for(int i=2;i<dataArr.length;i++){
				String sourceName = StringToolUtil.dataMap(dataArr[i]).get("sourceName");
				String sourceValue = StringToolUtil.dataMap(dataArr[i]).get("sourceValue");
				if("answerIndex".equals(sourceName)){
					textAnswer.setAnswerIndex(sourceValue);
				}
				if("answerContent".equals(sourceName)){
					textAnswer.setAnswerContent(sourceValue);
				}
				if("sure".equals(sourceName)){
					textAnswer.setSure(sourceValue);
					textAnswer.setTextTitleId(titleVoResult.getData().getId());
					textAnswerService.save(textAnswer);
					textAnswer = new TextAnswerVo();
				}

			}


		}
		return Result.of(null);
	}

	@PostMapping("/update/answer")
	@Decrypt
	@Encrypt
	public Result update(String data){
		TextTitleVo textTitle = new TextTitleVo();
		if(!StringUtils.isEmpty(data)){
			String[] dataArr = data.split("&");
			this.textAnswerService.deleteByTextTitleId(StringToolUtil.dataMap(dataArr[0]).get("sourceValue"));
			textTitle.setTitle(StringToolUtil.dataMap(dataArr[1]).get("sourceValue"));
			textTitle.setId(StringToolUtil.dataMap(dataArr[0]).get("sourceValue"));
			Result<TextTitleVo> titleVoResult = textTitleService.save(textTitle);
			TextAnswerVo textAnswer = new TextAnswerVo();
			for(int i=1;i<dataArr.length;i++){
				String sourceName = StringToolUtil.dataMap(dataArr[i]).get("sourceName");
				String sourceValue = StringToolUtil.dataMap(dataArr[i]).get("sourceValue");
				if("answerIndex".equals(sourceName)){
					textAnswer.setAnswerIndex(sourceValue);
				}
				if("answerContent".equals(sourceName)){
					textAnswer.setAnswerContent(sourceValue);
				}
				if("sure".equals(sourceName)){
					textAnswer.setSure(sourceValue);
					textAnswer.setTextTitleId(titleVoResult.getData().getId());
					textAnswerService.save(textAnswer);
					textAnswer = new TextAnswerVo();
				}

			}


		}
		return Result.of(null);
	}

	@GetMapping("/del")
	public Result del(String[] ids){
		this.textTitleService.del(ids);
		return Result.of(null);
	}

  public static void main(String[] args) {
	  String aa = "title=委任为v&answerIndex=A&answerContent=二万人";
		String[] arr = aa.split("&");
	  for(int i=0;i<arr.length;i++){
	  	System.out.println(arr[i].split("=")[1]);
	  };
  }
}
