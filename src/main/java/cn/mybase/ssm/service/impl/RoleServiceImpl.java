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
import org.springframework.util.StringUtils;

import cn.mybase.ssm.bean.entity.Role;
import cn.mybase.ssm.bean.entity.vo.Result;
import cn.mybase.ssm.service.RoleService;
import cn.mybase.ssm.service.impl.dao.RoleDao;
import cn.mybase.ssm.util.base.Page;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao dao;

	@Override
	public Result<Role> query(Integer id) {
		Result<Role> result = new Result<Role>();
		if (id < 0) {
			result.setErrMsg("此id无效");
			return result;
		}
		Role role = dao.selectById(id);
		if (role != null) {
			result.setErrCode(0);
			result.setStatus(true);
			result.setResultData(role);
		}
		return result;
	}

	@Override
	public Result<Integer> save(Role Role) {
		Result<Integer> result = new Result<Integer>();
		if (!StringUtils.hasText(Role.getRoleName())) {
			result.setErrMsg("角色名不能为空");
			return result;
		}
		if (!StringUtils.hasText(Role.getRoleValue())) {
			result.setErrMsg("角色值不能为空");
			return result;
		}
		if (dao.selectByRoleName(Role.getRoleName()) != null) {
			result.setErrMsg("角色已经存在");
			return result;
		}
		int resultNum = dao.insert(Role);
		if (resultNum > 0) {
			result.setErrCode(0);
			result.setStatus(true);
		}
		return result;
	}

	@Override
	public Result<Integer> update(Role Role) {
		Result<Integer> result = new Result<Integer>();
		if (!StringUtils.hasText(Role.getRoleName())) {
			result.setErrMsg("角色名不能为空");
			return result;
		}
		if (!StringUtils.hasText(Role.getRoleValue())) {
			result.setErrMsg("角色值不能为空");
			return result;
		}
		int resultNum = dao.update(Role);
		if (resultNum > 0) {
			result.setErrCode(0);
			result.setStatus(true);
		}
		return result;
	}

	@Override
	public Result<Integer> delete(Integer id) {
		Result<Integer> result = new Result<Integer>();
		if (id < 0) {
			result.setErrMsg("此id无效");
			return result;
		}
		int resultNum = dao.deleteById(id);
		if (resultNum > 0) {
			result.setErrCode(0);
			result.setStatus(true);
		}
		return result;
	}

	@Override
	public Result<Page<Role>> listForPage(int pageCurrent, int pageSize, String date, String seah) {
		Result<Page<Role>> result = new Result<Page<Role>>();
		if (pageCurrent < 1) {
			result.setErrMsg("pageCurrent有误");
			return result;
		}
		if (pageSize < 1) {
			result.setErrMsg("pageSize有误");
			return result;
		}
		Page<Role> resultData = dao.listForPage(pageCurrent, pageSize, date, seah);
		result.setResultData(resultData);
		result.setErrCode(0);
		result.setStatus(true);
		result.setErrMsg("查询成功");
		return result;
	}

	@Override
	public Result<List<Role>> list() {
		Result<List<Role>> result = new Result<List<Role>>();
		List<Role> resultData = dao.list();
		if (resultData.size() > 0) {
			result.setResultData(resultData);
			result.setErrCode(0);
			result.setStatus(true);
			result.setErrMsg("查询成功");
			return result;
		}
		result.setErrMsg("查询失败");
		return result;
	}

	@Override
	public Result<Role> queryByRoleName(String roleName) {
		Result<Role> result = new Result<Role>();
		if (!StringUtils.hasText(roleName)) {
			result.setErrMsg("角色名不能为空");
			return result;
		}
		Role resultData = dao.selectByRoleName(roleName);
		if (resultData != null) {
			result.setResultData(resultData);
			result.setErrCode(0);
			result.setStatus(true);
			result.setErrMsg("查询成功");
			return result;
		}
		result.setErrMsg("查询失败");
		return result;
	}

	@Override
	public Result<List<Role>> listForId(List<Integer> idList) {
		Result<List<Role>> result = new Result<List<Role>>();
		if (idList.size() < 1) {
			result.setErrMsg("没有id需要查询");
			return result;
		}
		List<Role> resultData = dao.listForId(idList);
		result.setErrCode(0);
		result.setStatus(true);
		result.setResultData(resultData);
		result.setErrMsg("查询成功");
		return result;
	}
}
