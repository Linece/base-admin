package cn.huanzi.qch.baseadmin.text.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "text_answer")
@Data
public class TextAnswer implements Serializable {

	@Id
	private Integer id;

	private Integer textTitleId;

	private boolean sure;

	private Date createTime;

	private Date updateTime;

	private String createBy;

	private String updateBy;
}
