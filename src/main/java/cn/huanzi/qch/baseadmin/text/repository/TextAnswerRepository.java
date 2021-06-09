package cn.huanzi.qch.baseadmin.text.repository;

import cn.huanzi.qch.baseadmin.common.repository.CommonRepository;
import cn.huanzi.qch.baseadmin.text.pojo.TextAnswer;

import java.util.List;

public interface TextAnswerRepository extends CommonRepository<TextAnswer,String> {
    List<TextAnswer> findByTextTitleId(String id);
}
