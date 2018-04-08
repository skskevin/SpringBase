/**
 * 
 */
package cn.mybase.ssm.bean.entity.vo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import cn.mybase.ssm.bean.entity.Permission;

/**
 * @Title: PermissionVo
 * @author dongchuan
 * @date 2018年4月8日 上午9:16:11
 * @Description: TODO
 * @version 1.0
 */
public class PermissionVo implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;

    private String status;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private String permissionsName;

    private String permissionsValue;

	public PermissionVo(Permission permission) {
		super();
		this.id = permission.getId();
		this.status = permission.getStatus();
		this.createTime = permission.getCreateTime();
		this.updateTime = permission.getUpdateTime();
		this.permissionsName = permission.getPermissionsName();
		this.permissionsValue = permission.getPermissionsValue();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getPermissionsName() {
		return permissionsName;
	}

	public void setPermissionsName(String permissionsName) {
		this.permissionsName = permissionsName;
	}

	public String getPermissionsValue() {
		return permissionsValue;
	}

	public void setPermissionsValue(String permissionsValue) {
		this.permissionsValue = permissionsValue;
	}

	@Override
	public String toString() {
		return "PermissionVo [id=" + id + ", status=" + status + ", createTime=" + createTime + ", updateTime="
				+ updateTime + ", permissionsName=" + permissionsName + ", permissionsValue=" + permissionsValue + "]";
	}
    
    

}
