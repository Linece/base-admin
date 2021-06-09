package cn.huanzi.qch.baseadmin.text.repository;

import cn.huanzi.qch.baseadmin.common.repository.CommonRepository;
import cn.huanzi.qch.baseadmin.text.pojo.TextTitle;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TextTitleRepository extends CommonRepository<TextTitle, String> {
    List<TextTitle> findByPublish(String publish_status);

}
