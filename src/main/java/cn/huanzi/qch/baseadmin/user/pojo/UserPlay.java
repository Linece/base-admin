package cn.huanzi.qch.baseadmin.user.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "user_play")
@Data
public class UserPlay implements Serializable {

    @Id
    private String openId;

    private Integer score;

    private Integer status;

    private Date createTime;

    private Date updateTime;


}
