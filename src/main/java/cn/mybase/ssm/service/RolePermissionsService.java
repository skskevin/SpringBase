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
package cn.mybase.ssm.service;

import java.util.List;

import cn.mybase.ssm.bean.entity.RolePermissions;
import cn.mybase.ssm.bean.entity.vo.Result;


/**
 * 角色-权限Service
 * 
 * @author LYQ
 *
 */
public interface RolePermissionsService {
	Result<RolePermissions> query(Integer id);

	Result<List<RolePermissions>> queryByRoleId(Integer id);

	Result<Integer> save(Integer roleId, List<Integer> permissionList);

	Result<Integer> update(Integer roleId, List<Integer> permissionList);
	
	Result<Integer> delete(Integer roleId);

	Result<Integer> deleteByRolePermissions(RolePermissions RolePermissions);
	
	Result<List<RolePermissions>> listForRoleId(List<Integer> idList);

}
