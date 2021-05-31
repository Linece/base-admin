package cn.huanzi.qch.baseadmin.text.service;

import cn.huanzi.qch.baseadmin.common.pojo.Result;
import cn.huanzi.qch.baseadmin.common.service.CommonServiceImpl;
import cn.huanzi.qch.baseadmin.text.pojo.TextTitle;
import cn.huanzi.qch.baseadmin.text.repository.TextTitleRepository;
import cn.huanzi.qch.baseadmin.text.vo.TextTitleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TextTitleServiceImpl extends CommonServiceImpl<TextTitleVo,TextTitle,Integer> implements TextTitleService {

	@Autowired
	private TextTitleRepository textTitleRepository;


	@Override
	public Result<Page<TextTitle>> findPage(TextTitleVo entityVo) {

		Page<TextTitle> all = this.textTitleRepository.findAll(new PageRequest(0,10 ));

		return Result.of(all);
	}
}
