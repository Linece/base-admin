package cn.huanzi.qch.baseadmin.text.vo;

import cn.huanzi.qch.baseadmin.common.pojo.PageCondition;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class TextAnswerVo extends PageCondition implements Serializable {

	 private String id;

	private String textTitleId;

	private String answerIndex;

	private String answerContent;

	private String sure;

	private Date createTime;

	private Date updateTime;

	private String createBy;

	private String updateBy;
}
