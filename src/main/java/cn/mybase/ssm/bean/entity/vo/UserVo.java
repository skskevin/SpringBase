package cn.mybase.ssm.bean.entity.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import cn.mybase.ssm.bean.entity.Role;
import cn.mybase.ssm.bean.entity.User;

/**
 * 用户实体拓展类
 * 
 * @author LYQ
 *
 */
public class UserVo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1439710637271708310L;

	private Long id;

	private String status;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;

	private String userNo;

	private String password;

	private String nickName;

	private Integer sex;

	private String permission;
	
	private List<Role> roleList;
	
	public UserVo(User User){
		this.id = User.getId();
		this.status = User.getStatus();
		this.createTime = User.getCreateTime();
		this.updateTime = User.getUpdateTime();
		this.userNo = User.getUserNo();
		this.password = User.getPassword();
		this.nickName = User.getNickName();
		this.sex = User.getSex();
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

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}
}