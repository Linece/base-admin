package cn.huanzi.qch.baseadmin.user.service;

import cn.huanzi.qch.baseadmin.common.service.CommonServiceImpl;
import cn.huanzi.qch.baseadmin.user.pojo.UserPlay;
import cn.huanzi.qch.baseadmin.user.repository.UserPlayRepository;
import cn.huanzi.qch.baseadmin.user.vo.UserPlayVo;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class UserPlayServiceImpl extends CommonServiceImpl<UserPlayVo, UserPlay, String> implements UserPlayService {
    private final UserPlayRepository userPlayRepository;

    @Override
    public UserPlayVo findByOpenId(String openid) {
        UserPlay byOpenId = userPlayRepository.findByOpenId(openid);
        UserPlayVo vo = new UserPlayVo();
        BeanUtils.copyProperties(byOpenId, vo);
        return vo;
    }
}
