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

import cn.mybase.ssm.bean.entity.Permission;
import cn.mybase.ssm.bean.entity.vo.PermissionVo;
import cn.mybase.ssm.bean.entity.vo.Result;
import cn.mybase.ssm.service.PermissionService;
import cn.mybase.ssm.service.impl.dao.PermissionDao;
import cn.mybase.ssm.util.base.Page;

@Service
public class PermissionServiceImpl implements PermissionService {

	@Autowired
	private PermissionDao dao;

	@Override
	public Result<Permission> query(Integer id) {
		Result<Permission> result = new Result<Permission>();
		if (id < 0) {
			result.setErrMsg("此id无效");
			return result;
		}
		Permission Permission = dao.selectById(id);
		if (Permission != null) {
			result.setErrCode(0);
			result.setStatus(true);
			result.setResultData(Permission);
			result.setErrMsg("查询成功");
			return result;
		}
		result.setErrMsg("查询失败");
		return result;
	}

	@Override
	public Result<Integer> save(Permission Permission) {
		Result<Integer> result = new Result<Integer>();
		if (!StringUtils.hasText(Permission.getPermissionsName())) {
			result.setErrMsg("权限名不能为空");
			return result;
		}
		if (!StringUtils.hasText(Permission.getPermissionsValue())) {
			result.setErrMsg("权限值不能为空");
			return result;
		}
		int resultNum = dao.insert(Permission);
		if (resultNum > 0) {
			result.setErrCode(0);
			result.setStatus(true);
			result.setErrMsg("添加成功");
			return result;
		}
		result.setErrMsg("添加失败");
		return result;
	}

	@Override
	public Result<Integer> update(Permission Permission) {
		Result<Integer> result = new Result<Integer>();
		if (!StringUtils.hasText(Permission.getPermissionsName())) {
			result.setErrMsg("权限名不能为空");
			return result;
		}
		if (!StringUtils.hasText(Permission.getPermissionsValue())) {
			result.setErrMsg("权限值不能为空");
			return result;
		}
		int resultNum = dao.update(Permission);
		if (resultNum > 0) {
			result.setErrCode(0);
			result.setStatus(true);
			result.setErrMsg("更新成功");
			return result;
		}
		result.setErrMsg("更新失败");
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
			result.setErrMsg("删除成功");
			return result;
		}
		result.setErrMsg("删除失败");
		return result;
	}

	@Override
	public Result<Page<Permission>> listForPage(int pageCurrent, int pageSize, String date, String seah) {
		Result<Page<Permission>> result = new Result<Page<Permission>>();
		if (pageCurrent < 1) {
			result.setErrMsg("pageCurrent有误");
			return result;
		}
		if (pageSize < 1) {
			result.setErrMsg("pageSize有误");
			return result;
		}
		Page<Permission> resultData = dao.listForPage(pageCurrent, pageSize, date, seah);
		result.setResultData(resultData);
		result.setErrCode(0);
		result.setStatus(true);
		result.setErrMsg("查询成功");
		return result;
	}

	@Override
	public Result<List<Permission>> listForId(List<Integer> idList) {
		Result<List<Permission>> result = new Result<List<Permission>>();
		if (idList.size() < 1) {
			result.setErrMsg("没有id需要查询");
			return result;
		}
		List<Permission> resultData = dao.listForId(idList);
		result.setErrCode(0);
		result.setStatus(true);
		result.setResultData(resultData);
		result.setErrMsg("查询成功");
		return result;
	}

	@Override
	public Result<List<Permission>> list() {
		Result<List<Permission>> result = new Result<List<Permission>>();
		List<Permission> resultData = dao.list();
		result.setErrCode(0);
		result.setStatus(true);
		result.setResultData(resultData);
		result.setErrMsg("查询成功");
		return result;
	}
}
