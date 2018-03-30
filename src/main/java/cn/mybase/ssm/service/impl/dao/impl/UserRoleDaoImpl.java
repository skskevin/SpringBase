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

import cn.mybase.ssm.bean.entity.UserRole;
import cn.mybase.ssm.bean.entity.UserRoleExample;
import cn.mybase.ssm.bean.entity.UserRoleExample.Criteria;
import cn.mybase.ssm.service.impl.dao.UserRoleDao;
import cn.mybase.ssm.service.impl.dao.impl.mybatis.UserRoleMapper;

/**
 * 用户-角色关系Dao层
 * 
 * @author LYQ
 *
 */
@Repository
public class UserRoleDaoImpl implements UserRoleDao {

	@Autowired
	private UserRoleMapper mapper;

	@Override
	public List<UserRole> selectByUserId(long id) {
		UserRoleExample example = new UserRoleExample();
		Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(id);
		return mapper.selectByExample(example);
	}

	@Override
	public int insert(UserRole UserRole) {
		Date date = new Date();
		UserRole.setCreateTime(date);
		UserRole.setUpdateTime(date);
		return mapper.insertSelective(UserRole);
	}

	@Override
	public int update(UserRole UserRole) {
		UserRole.setUpdateTime(new Date());
		return mapper.updateByPrimaryKeySelective(UserRole);
	}

	@Override
	public int delectByUserRole(UserRole UserRole) {
		UserRoleExample example = new UserRoleExample();
		Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(UserRole.getUserId());
		if (UserRole.getRolesId() != null && UserRole.getRolesId() > 0) {
			criteria.andRolesIdEqualTo(UserRole.getRolesId());
		}
		return mapper.deleteByExample(example);
	}

	@Override
	public int deleteById(long id) {
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public UserRole selectById(long id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public UserRole selectByUserRole(UserRole UserRole) {
		UserRoleExample example = new UserRoleExample();
		Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(UserRole.getUserId());
		criteria.andRolesIdEqualTo(UserRole.getRolesId());
		List<UserRole> resultList = mapper.selectByExample(example);
		if (resultList.size() > 0) {
			return resultList.get(0);
		}
		return null;
	}

	@Override
	public int deleteByUserId(long userId) {
		UserRoleExample example = new UserRoleExample();
		Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		return mapper.deleteByExample(example);
	}
}
