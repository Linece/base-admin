package cn.huanzi.qch.baseadmin.text.service;

import cn.huanzi.qch.baseadmin.common.pojo.Result;
import cn.huanzi.qch.baseadmin.common.service.CommonService;
import cn.huanzi.qch.baseadmin.text.pojo.TextTitle;
import cn.huanzi.qch.baseadmin.text.vo.TextTitleVo;
import org.springframework.data.domain.Page;


public interface TextTitleService extends CommonService<TextTitleVo, TextTitle,String> {

	public Result<Page<TextTitle>> findPage(TextTitleVo entityVo);

	public  void del(String[] ids);

	public void fabu(String type,String[] ids);
}
