package cn.mybase.ssm.service.impl.dao.impl.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.mybase.ssm.bean.entity.RolePermissions;
import cn.mybase.ssm.bean.entity.RolePermissionsExample;
import cn.mybase.ssm.util.base.MyBatis;

@MyBatis
public interface RolePermissionsMapper {
    int countByExample(RolePermissionsExample example);

    int deleteByExample(RolePermissionsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RolePermissions record);

    int insertSelective(RolePermissions record);

    List<RolePermissions> selectByExample(RolePermissionsExample example);

    RolePermissions selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RolePermissions record, @Param("example") RolePermissionsExample example);

    int updateByExample(@Param("record") RolePermissions record, @Param("example") RolePermissionsExample example);

    int updateByPrimaryKeySelective(RolePermissions record);

    int updateByPrimaryKey(RolePermissions record);
}