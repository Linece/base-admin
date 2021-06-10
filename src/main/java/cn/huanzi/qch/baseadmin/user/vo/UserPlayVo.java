package cn.huanzi.qch.baseadmin.user.vo;

import cn.huanzi.qch.baseadmin.common.pojo.PageCondition;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UserPlayVo extends PageCondition implements Serializable {

    private String openId;

    private Integer score;

    private Integer status;

    private Date createTime;

    private Date updateTime;

}
