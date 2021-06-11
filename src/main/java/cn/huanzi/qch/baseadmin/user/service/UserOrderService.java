package cn.huanzi.qch.baseadmin.user.service;

import cn.huanzi.qch.baseadmin.common.service.CommonService;
import cn.huanzi.qch.baseadmin.user.pojo.UserOrder;
import cn.huanzi.qch.baseadmin.user.vo.UserOrderVo;

public interface UserOrderService extends CommonService<UserOrderVo, UserOrder, String> {
    UserOrderVo createUserOrder(String openId);
}
