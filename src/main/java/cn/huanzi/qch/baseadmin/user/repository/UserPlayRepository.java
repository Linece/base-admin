package cn.huanzi.qch.baseadmin.user.repository;

import cn.huanzi.qch.baseadmin.common.repository.CommonRepository;
import cn.huanzi.qch.baseadmin.user.pojo.UserPlay;
import cn.huanzi.qch.baseadmin.user.vo.UserPlayVo;

public interface UserPlayRepository extends CommonRepository<UserPlay, String> {


    UserPlayVo findByOpenId(String openid);
}


