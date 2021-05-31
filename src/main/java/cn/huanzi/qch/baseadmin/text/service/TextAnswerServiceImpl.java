package cn.huanzi.qch.baseadmin.text.service;

import cn.huanzi.qch.baseadmin.common.pojo.PageInfo;
import cn.huanzi.qch.baseadmin.common.pojo.Result;
import cn.huanzi.qch.baseadmin.text.vo.TextAnswerVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TextAnswerServiceImpl implements TextAnswerService {
	@Override
	public Result<PageInfo<TextAnswerVo>> page(TextAnswerVo entityVo) {
		return null;
	}

	@Override
	public Result<List<TextAnswerVo>> list(TextAnswerVo entityVo) {
		return null;
	}

	@Override
	public Result<TextAnswerVo> get(Integer id) {
		return null;
	}

	@Override
	public Result<TextAnswerVo> save(TextAnswerVo entityVo) {
		return null;
	}

	@Override
	public Result<Integer> delete(Integer id) {
		return null;
	}
}
