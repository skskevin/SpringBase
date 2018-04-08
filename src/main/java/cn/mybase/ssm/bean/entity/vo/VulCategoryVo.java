/**
 * 
 */
package cn.mybase.ssm.bean.entity.vo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import cn.mybase.ssm.bean.entity.VulCategory;

/**
 * @Title: VulCategoryVo
 * @author dongchuan
 * @date 2018年4月8日 下午2:33:11
 * @Description: TODO
 * @version 1.0
 */
public class VulCategoryVo implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;

    private String name;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

	/**
	 * 
	 */
	public VulCategoryVo(VulCategory vulCategory) {
		super();
		this.id = vulCategory.getId();
		this.name = vulCategory.getName();
		this.createTime = vulCategory.getCreateTime();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "VulCategoryVo [id=" + id + ", name=" + name + ", createTime=" + createTime + "]";
	}

}
