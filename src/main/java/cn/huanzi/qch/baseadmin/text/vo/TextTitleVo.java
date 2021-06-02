package cn.huanzi.qch.baseadmin.text.vo;

import cn.huanzi.qch.baseadmin.common.pojo.PageCondition;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class TextTitleVo extends PageCondition implements Serializable {

	private String id;

	private String title;

	private String deleteFalg;

	private Date createTime;

	private Date updateTime;

	private String createBy;

	private String updateBy;
}
