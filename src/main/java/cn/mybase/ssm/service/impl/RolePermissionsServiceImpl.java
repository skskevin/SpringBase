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

import cn.mybase.ssm.bean.entity.RolePermissions;
import cn.mybase.ssm.bean.entity.vo.Result;
import cn.mybase.ssm.service.RolePermissionsService;
import cn.mybase.ssm.service.impl.dao.RolePermissionsDao;

@Service
public class RolePermissionsServiceImpl implements RolePermissionsService {

	@Autowired
	private RolePermissionsDao dao;

	@Override
	public Result<RolePermissions> query(Integer id) {
		Result<RolePermissions> result = new Result<RolePermissions>();
		if (id < 0) {
			result.setErrMsg("此id无效");
			return result;
		}
		RolePermissions RolePermissions = dao.selectById(id);
		if (RolePermissions != null) {
			result.setErrCode(0);
			result.setStatus(true);
			result.setResultData(RolePermissions);
		}
		return result;
	}

	@Override
	public Result<List<RolePermissions>> queryByRoleId(Integer id) {
		Result<List<RolePermissions>> result = new Result<List<RolePermissions>>();
		if (id < 0) {
			result.setErrMsg("此id无效");
			return result;
		}
		List<RolePermissions> resultList = dao.selectByRoleId(id);
		if (resultList.size() > 0) {
			result.setErrCode(0);
			result.setStatus(true);
			result.setResultData(resultList);
		}
		return result;
	}

	@Override
	public Result<Integer> save(Integer roleId, List<Integer> permissionList) {
		Result<Integer> result = new Result<Integer>();
		if (roleId < 1) {
			result.setErrMsg("此角色id无效");
			return result;
		}
		if (permissionList.size() == 0) {
			result.setErrCode(0);
			result.setStatus(true);
			result.setErrMsg("没有需要插入的数据");
			return result;
		}
		RolePermissions RolePermissions = new RolePermissions();
		int i = 0;
		for (Integer permissionId : permissionList) {
			RolePermissions.setPermissionId(permissionId);
			RolePermissions.setRoleId(roleId);
			i = i + dao.insert(RolePermissions);
		}
		if (i == permissionList.size()) {
			result.setErrMsg("操作成功");
			result.setErrCode(0);
			result.setStatus(true);
			return result;
		}
		result.setErrMsg("操作失败");
		return result;
	}

	@Override
	public Result<Integer> delete(Integer roleId) {
		Result<Integer> result = new Result<Integer>();
		if (roleId < 0) {
			result.setErrMsg("此id无效");
			return result;
		}
		int resultNum = dao.deleteByRoleId(roleId);
		if (resultNum > 0) {
			result.setErrCode(0);
			result.setStatus(true);
		}
		return result;
	}

	@Override
	public Result<Integer> deleteByRolePermissions(RolePermissions RolePermissions) {
		Result<Integer> result = new Result<Integer>();
		if (RolePermissions.getRoleId() < 0) {
			result.setErrMsg("此角色id无效");
			return result;
		}
		if (RolePermissions.getPermissionId() < 0) {
			result.setErrMsg("此权限id无效");
			return result;
		}
		int resultNum = dao.delectByRolePermissions(RolePermissions);
		if (resultNum > 0) {
			result.setErrCode(0);
			result.setStatus(true);
		}
		return result;
	}

	@Override
	public Result<Integer> update(Integer roleId, List<Integer> permissionList) {
		Result<Integer> result = new Result<Integer>();
		if (roleId < 1) {
			result.setErrMsg("此角色id无效");
			return result;
		}
		if (dao.countByRoleId(roleId) != dao.deleteByRoleId(roleId)) {
			result.setErrMsg("操作失败");
			return result;
		}
		return save(roleId, permissionList);
	}

	@Override
	public Result<List<RolePermissions>> listForRoleId(List<Integer> idList) {
		Result<List<RolePermissions>> result = new Result<List<RolePermissions>>();
		if (idList.size() < 1) {
			result.setErrMsg("没有id需要查询");
			return result;
		}
		List<RolePermissions> resultData = dao.listForRoleId(idList);
		result.setErrCode(0);
		result.setStatus(true);
		result.setResultData(resultData);
		result.setErrMsg("查询成功");
		return result;
	}
}
