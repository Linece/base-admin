package cn.huanzi.qch.baseadmin.text.service;

import cn.huanzi.qch.baseadmin.common.pojo.PageInfo;
import cn.huanzi.qch.baseadmin.common.pojo.Result;
import cn.huanzi.qch.baseadmin.common.service.CommonServiceImpl;
import cn.huanzi.qch.baseadmin.text.pojo.TextAnswer;
import cn.huanzi.qch.baseadmin.text.repository.TextAnswerRepository;
import cn.huanzi.qch.baseadmin.text.vo.TextAnswerVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
@Transactional
public class TextAnswerServiceImpl extends CommonServiceImpl<TextAnswerVo,TextAnswer,String> implements TextAnswerService {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private TextAnswerRepository textAnswerRepository;

	@Override
	public List<TextAnswer> getTextAnswers(String textTitleId) {
		Sort sort = new Sort(Sort.Direction.ASC,"answerIndex");
		TextAnswer textAnswer = new TextAnswer();
				textAnswer.setTextTitleId(textTitleId);
		ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("text_title_id", ExampleMatcher.GenericPropertyMatchers.exact());
		return textAnswerRepository.findAll(Example.of(textAnswer,matcher),sort);
	}

	@Override
	public void deleteByTextTitleId(String textTitleId) {
		List<TextAnswer> textAnswers = getTextAnswers(textTitleId);
		for (TextAnswer textAnswer:textAnswers){
			this.textAnswerRepository.deleteById(textAnswer.getId());
		}

}
}
