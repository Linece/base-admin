package cn.huanzi.qch.baseadmin.text.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "text_title")
@Data
public class TextTitle implements Serializable {

	@Id
	private Integer id;

	private String title;

	private boolean deleteFlag;

	private Date createTime;

	private Date updateTime;

	private String createBy;

	private String updateBy;
}
