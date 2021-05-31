package cn.huanzi.qch.baseadmin.text.vo;

import cn.huanzi.qch.baseadmin.common.pojo.PageCondition;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class TextAnswerVo extends PageCondition implements Serializable {

	 private Integer id;

	private Integer textTitleId;

	private boolean sure;

	private Date createTime;

	private Date updateTime;

	private String createBy;

	private String updateBy;
}
