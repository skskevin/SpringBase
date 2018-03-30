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
package cn.mybase.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.mybase.ssm.bean.entity.UserRole;
import cn.mybase.ssm.bean.entity.vo.Result;
import cn.mybase.ssm.service.UserRoleService;
import cn.mybase.ssm.service.impl.dao.UserRoleDao;

@Service
public class UserRoleServiceImpl implements UserRoleService {

	@Autowired
	private UserRoleDao dao;

	@Override
	public Result<List<UserRole>> queryByUserId(long id) {
		Result<List<UserRole>> result = new Result<List<UserRole>>();
		if (id < 0) {
			result.setErrMsg("此id无效");
			return result;
		}
		List<UserRole> resultList = dao.selectByUserId(id);
		if (resultList.size() > 0) {
			result.setErrCode(0);
			result.setStatus(true);
			result.setResultData(resultList);
		}
		return result;
	}

	@Override
	public Result<UserRole> queryByUserRole(UserRole UserRole) {
		Result<UserRole> result = new Result<UserRole>();
		if (UserRole.getUserId() < 0) {
			result.setErrMsg("此用户id无效");
			return result;
		}
		if (UserRole.getRolesId() < 0) {
			result.setErrMsg("此角色id无效");
			return result;
		}
		UserRole userRole = dao.selectByUserRole(UserRole);
		if (userRole != null) {
			result.setErrCode(0);
			result.setStatus(true);
			result.setResultData(userRole);
		}
		return result;
	}

	@Override
	public Result<Integer> insert(UserRole UserRole) {
		Result<Integer> result = new Result<Integer>();
		if (UserRole.getUserId() < 0) {
			result.setErrMsg("此用户id无效");
			return result;
		}
		if (UserRole.getRolesId() < 0) {
			result.setErrMsg("此角色id无效");
			return result;
		}
		int resultNum = dao.insert(UserRole);
		if (resultNum > 0) {
			result.setErrCode(0);
			result.setStatus(true);
		}
		return result;
	}

	@Override
	public Result<Integer> update(UserRole UserRole) {
		Result<Integer> result = new Result<Integer>();
		if (UserRole.getUserId() < 0) {
			result.setErrMsg("此用户id无效");
			return result;
		}
		if (UserRole.getRolesId() < 0) {
			result.setErrMsg("此角色id无效");
			return result;
		}
		int resultNum = dao.update(UserRole);
		if (resultNum > 0) {
			result.setErrCode(0);
			result.setStatus(true);
		}
		return result;
	}

	@Override
	public Result<Integer> deleteByUserRole(UserRole UserRole) {
		Result<Integer> result = new Result<Integer>();
		if (UserRole.getUserId() < 0) {
			result.setErrMsg("此用户id无效");
			return result;
		}
		int resultNum = dao.delectByUserRole(UserRole);
		if (resultNum > 0) {
			result.setErrCode(0);
			result.setStatus(true);
		}
		return result;
	}

	@Override
	public Result<Integer> updateByUserId(long userId, List<Long> roles) {
		Result<Integer> result = new Result<Integer>();
		UserRole UserRole = new UserRole();
		if (userId < 1) {
			result.setErrMsg("用户id不能为空");
			return result;
		}
		dao.deleteByUserId(userId);
		for (Long rolesId : roles) {
			UserRole.setUserId(userId);
			UserRole.setRolesId(rolesId);
			dao.insert(UserRole);
		}
		result.setErrCode(0);
		result.setStatus(true);
		result.setErrMsg("更新成功");
		return result;
	}
}
