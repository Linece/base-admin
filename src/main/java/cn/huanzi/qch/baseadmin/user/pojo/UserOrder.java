package cn.huanzi.qch.baseadmin.user.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "user_order")
@Data
public class UserOrder implements Serializable {

    @Id
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
