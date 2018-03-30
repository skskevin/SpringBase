package cn.mybase.ssm.bean.entity.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import cn.mybase.ssm.bean.entity.Permission;
import cn.mybase.ssm.bean.entity.Role;

public class RoleVo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5657330758447750715L;

	private Long id;

	private String status;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;

	private String roleName;

	private String roleValue;
	
	private List<Permission> permissionList;

	public RoleVo(Role Role) {
		this.id = Role.getId();
		this.status = Role.getStatus();
		this.createTime = Role.getCreateTime();
		this.updateTime = Role.getUpdateTime();
		this.roleName = Role.getRoleName();
		this.roleValue = Role.getRoleValue();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleValue() {
		return roleValue;
	}

	public void setRoleValue(String roleValue) {
		this.roleValue = roleValue;
	}

	public List<Permission> getPermissionList() {
		return permissionList;
	}

	public void setPermissionList(List<Permission> permissionList) {
		this.permissionList = permissionList;
	}
}