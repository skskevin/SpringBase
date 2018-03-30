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
package cn.mybase.ssm.biz;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.mybase.ssm.bean.entity.Permission;
import cn.mybase.ssm.bean.entity.Role;
import cn.mybase.ssm.bean.entity.RolePermissions;
import cn.mybase.ssm.bean.entity.vo.Result;
import cn.mybase.ssm.bean.entity.vo.RoleVo;
import cn.mybase.ssm.service.PermissionService;
import cn.mybase.ssm.service.RolePermissionsService;
import cn.mybase.ssm.service.RoleService;
import cn.mybase.ssm.util.base.Page;


@Component
public class RoleBiz {

	@Autowired
	private RoleService service;

	@Autowired
	private RolePermissionsService rolePermissionsService;

	@Autowired
	private PermissionService permissionService;

	public Result<Page<Role>> listForPage(int pageCurrent, int pageSize, String date, String seah) {
		return service.listForPage(pageCurrent, pageSize, date, seah);
	}

	public Result<RoleVo> query(long id) {
		Result<Role> result = service.query(id);
		Result<RoleVo> resultRoleVo = new Result<RoleVo>();
		if (result.isStatus()) {
			RoleVo RoleVo = new RoleVo(result.getResultData());
			Result<List<RolePermissions>> resultRolePermissions = rolePermissionsService.queryByRoleId(id);
			if (resultRolePermissions.isStatus()) {
				ArrayList<Long> idList = new ArrayList<Long>();
				for (RolePermissions RolePermissions : resultRolePermissions.getResultData()) {
					idList.add(RolePermissions.getPermissionId());
				}
				Result<List<Permission>> resultPermission = permissionService.listForId(idList);
				RoleVo.setPermissionList(resultPermission.getResultData());
				resultRoleVo.setErrCode(0);
				resultRoleVo.setStatus(true);
				resultRoleVo.setResultData(RoleVo);
				resultRoleVo.setErrMsg("查询成功");
				return resultRoleVo;
			}
		}
		return resultRoleVo;
	}

	public Result<List<Permission>> queryPermissionList() {
		return permissionService.list();
	}

	@Transactional
	public Result<Integer> save(Role Role, List<Long> permissionList) {
		Result<Integer> result = service.save(Role);
		if (result.isStatus()) {
			Result<Role> resultRole = service.queryByRoleName(Role.getRoleName());
			if (result.isStatus()) {
				return rolePermissionsService.save(resultRole.getResultData().getId(), permissionList);
			}
		}
		return result;
	}

	@Transactional
	public Result<Integer> delete(long id) {
		Result<Integer> result = rolePermissionsService.delete(id);
		if (result.isStatus()) {
			return service.delete(id);
		}
		return result;
	}

	@Transactional
	public Result<Integer> update(Role Role, List<Long> permissionList) {
		Result<Integer> result = rolePermissionsService.update(Role.getId(), permissionList);
		if (result.isStatus()) {
			return service.update(Role);
		}
		return result;
	}
}
