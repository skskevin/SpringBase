/*
 * Copyright 2015-2016 RonCoo(http://www.roncoo.com) Group.
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.mybase.ssm.service.impl.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.mybase.ssm.bean.entity.RolePermissions;
import cn.mybase.ssm.bean.entity.RolePermissionsExample;
import cn.mybase.ssm.bean.entity.RolePermissionsExample.Criteria;
import cn.mybase.ssm.service.impl.dao.RolePermissionsDao;
import cn.mybase.ssm.service.impl.dao.impl.mybatis.RolePermissionsMapper;

@Repository
public class RolePermissionsDaoImpl implements RolePermissionsDao {

	@Autowired
	private RolePermissionsMapper mapper;

	@Override
	public List<RolePermissions> selectByRoleId(Integer id) {
		RolePermissionsExample example = new RolePermissionsExample();
		Criteria criteria = example.createCriteria();
		criteria.andRoleIdEqualTo(id);
		return mapper.selectByExample(example);
	}

	@Override
	public int insert(RolePermissions RolePermissions) {
		Date date = new Date();
		RolePermissions.setCreateTime(date);
		RolePermissions.setUpdateTime(date);
		return mapper.insertSelective(RolePermissions);
	}

	@Override
	public int update(RolePermissions RolePermissions) {
		RolePermissions.setUpdateTime(new Date());
		return mapper.updateByPrimaryKeySelective(RolePermissions);
	}

	@Override
	public int delectByRolePermissions(RolePermissions RolePermissions) {
		RolePermissionsExample example = new RolePermissionsExample();
		Criteria criteria = example.createCriteria();
		criteria.andRoleIdEqualTo(RolePermissions.getRoleId());
		criteria.andPermissionIdEqualTo(RolePermissions.getPermissionId());
		return mapper.deleteByExample(example);
	}

	@Override
	public RolePermissions selectById(Integer id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public int deleteByRoleId(Integer roleId) {
		RolePermissionsExample example = new RolePermissionsExample();
		Criteria criteria = example.createCriteria();
		criteria.andRoleIdEqualTo(roleId);
		return mapper.deleteByExample(example);
	}

	@Override
	public int countByRoleId(Integer roleId) {
		RolePermissionsExample example = new RolePermissionsExample();
		Criteria criteria = example.createCriteria();
		criteria.andRoleIdEqualTo(roleId);
		return mapper.countByExample(example);
	}

	@Override
	public List<RolePermissions> listForRoleId(List<Integer> idList) {
		RolePermissionsExample example = new RolePermissionsExample();
		Criteria criteria = example.createCriteria();
		criteria.andRoleIdIn(idList);
		return mapper.selectByExample(example);
	}
}
