package cn.huanzi.qch.baseadmin.user.service;

import cn.huanzi.qch.baseadmin.common.pojo.Result;
import cn.huanzi.qch.baseadmin.common.service.CommonServiceImpl;
import cn.huanzi.qch.baseadmin.config.WeiXinPayConfigProperties;
import cn.huanzi.qch.baseadmin.user.pojo.UserOrder;
import cn.huanzi.qch.baseadmin.user.repository.UserOrderRepository;
import cn.huanzi.qch.baseadmin.user.vo.UserOrderVo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Slf4j
@Service
@AllArgsConstructor
public class UserOrderServiceImpl extends CommonServiceImpl<UserOrderVo, UserOrder, String> implements UserOrderService {
    @Autowired
    private WeiXinPayConfigProperties weiXinPayConfigProperties;
    private final UserOrderRepository userOrderRepository;

    @Override
    public UserOrderVo createUserOrder(String openId) {
        UserOrderVo userOrder = new UserOrderVo();
        userOrder.setOrderId(openId);
        userOrder.setOpenId(openId);
        BigDecimal bigDecimal = BigDecimal.valueOf(Double.parseDouble(weiXinPayConfigProperties.getTotal_amount()));

        userOrder.setAmount(bigDecimal);
        userOrder.setTotal(weiXinPayConfigProperties.getTotal_num());
        userOrder.setOrderTime(new Date());
        userOrder.setOrderStatus(0);
        userOrder.setCreateTime(new Date());
        userOrder.setUpdateTime(new Date());
        Result<UserOrderVo> save = this.save(userOrder);

        UserOrderVo data = save.getData();
        return data;
    }
}
