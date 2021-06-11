package cn.huanzi.qch.baseadmin.user.vo;

import cn.huanzi.qch.baseadmin.common.pojo.PageCondition;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class UserOrderVo extends PageCondition implements Serializable {

    private String orderId;
    private String openId;
    private Date orderTime;
    private BigDecimal amount;
    private Integer total;
    private Date createTime;
    private Date updateTime;
    private Integer orderStatus;
    private Date orderFinishTime;
}
