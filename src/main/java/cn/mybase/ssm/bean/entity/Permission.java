package cn.mybase.ssm.bean.entity;

import java.util.Date;

public class Permission {
    private Long id;

    private String status;

    private Date createTime;

    private Date updateTime;

    private String permissionsName;

    private String permissionsValue;

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
        this.status = status == null ? null : status.trim();
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
        this.permissionsName = permissionsName == null ? null : permissionsName.trim();
    }

    public String getPermissionsValue() {
        return permissionsValue;
    }

    public void setPermissionsValue(String permissionsValue) {
        this.permissionsValue = permissionsValue == null ? null : permissionsValue.trim();
    }
}