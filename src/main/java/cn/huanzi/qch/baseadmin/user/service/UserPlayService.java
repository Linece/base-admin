package cn.huanzi.qch.baseadmin.user.service;

import cn.huanzi.qch.baseadmin.common.service.CommonService;
import cn.huanzi.qch.baseadmin.user.pojo.UserPlay;
import cn.huanzi.qch.baseadmin.user.vo.UserPlayVo;

public interface UserPlayService extends CommonService<UserPlayVo, UserPlay, String> {
    UserPlayVo findByOpenId(String openid);
}
