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
import cn.mybase.ssm.bean.entity.User;
import cn.mybase.ssm.bean.entity.UserRole;
import cn.mybase.ssm.bean.entity.vo.Result;
import cn.mybase.ssm.bean.entity.vo.UserVo;
import cn.mybase.ssm.service.PermissionService;
import cn.mybase.ssm.service.RolePermissionsService;
import cn.mybase.ssm.service.RoleService;
import cn.mybase.ssm.service.UserRoleService;
import cn.mybase.ssm.service.UserService;
import cn.mybase.ssm.util.base.Page;


/**
 * 用户逻辑业务
 * 
 * @author LYQ
 *
 */
@Component
public class UserBiz {

	@Autowired
	private UserService userService;

	@Autowired
	private UserRoleService userRoleService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private RolePermissionsService rolePermissionsService;

	@Autowired
	private PermissionService permissionService;

	/**
	 * 用户登录
	 * 
	 * @param userno
	 * @param password
	 * @return
	 */
	public Result<User> login(String userno, String password) {
		return userService.login(userno, password);
	}
	

	/**
	 * 分页查询用户信息
	 * 
	 * @param pageCurrent
	 * @param pageSize
	 * @param date
	 * @param seah
	 * @return
	 */
	public Result<Page<UserVo>> listForPage(int pageCurrent, int pageSize, String date, String seah) {
		Result<Page<UserVo>> resultVo = new Result<Page<UserVo>>();
		Result<Page<User>> result = userService.listForPage(pageCurrent, pageSize, date, seah);
		if (result.isStatus()) {
			ArrayList<UserVo> resultData = new ArrayList<UserVo>();
			UserVo UserVo;
			for (User User : result.getResultData().getList()) {
				UserVo = new UserVo(User);
				Result<List<Role>> resultRole = queryRoles(User.getId());
				if (resultRole.isStatus()) {
					UserVo.setRoleList(resultRole.getResultData());
					resultData.add(UserVo);
				}
			}
			Page<UserVo> page = new Page<UserVo>(result.getResultData().getTotalCount(), result.getResultData().getTotalPage(), result.getResultData().getPageCurrent(), result.getResultData().getPageSize(), resultData);
			resultVo.setErrCode(0);
			resultVo.setStatus(true);
			resultVo.setErrMsg("查询成功");
			resultVo.setResultData(page);
			return resultVo;
		}
		return resultVo;
	}

	/**
	 * 根据id查询用户的信息
	 * 
	 * @param id
	 * @return
	 */
	public Result<UserVo> query(long id) {
		Result<UserVo> resultVo = new Result<UserVo>();
		Result<User> result = userService.query(id);
		if (result.isStatus()) {
			UserVo UserVo = new UserVo(result.getResultData());
			Result<List<Role>> resultR = queryRoles(id);
			if (result.isStatus()) {
				UserVo.setRoleList(resultR.getResultData());
				resultVo.setErrCode(0);
				resultVo.setStatus(true);
				resultVo.setErrMsg("查询成功");
				resultVo.setResultData(UserVo);
				return resultVo;
			}
			resultVo.setErrMsg("查询失败");
		}
		return resultVo;
	}

	/**
	 * 根据账号查询用户信息
	 * 
	 * @param userno
	 * @return
	 */
	public Result<User> queryByUserNo(String userno) {
		return userService.queryByUserNo(userno);
	}

	/**
	 * 获取角色列表
	 * 
	 * @return
	 */
	public Result<List<Role>> queryRoleList() {
		return roleService.list();
	}

	/**
	 * 获取用户角色
	 * 
	 * @param userId
	 * @return
	 */
	public Result<List<Role>> queryRoles(long userId) {
		Result<List<UserRole>> resultUR = userRoleService.queryByUserId(userId);
		ArrayList<Long> params = new ArrayList<Long>();
		for (UserRole UserRole : resultUR.getResultData()) {
			params.add(UserRole.getRolesId());
		}
		Result<List<Role>> result = roleService.listForId(params);
		return result;
	}

	/**
	 * 获取角色权限
	 * 
	 * @param roles
	 * @return
	 */
	public Result<List<Permission>> queryPermissions(List<Role> roles) {
		ArrayList<Long> roleParams = new ArrayList<Long>();
		for (Role Role : roles) {
			roleParams.add(Role.getId());
		}
		Result<List<RolePermissions>> resultRP = rolePermissionsService.listForRoleId(roleParams);
		if (resultRP.isStatus()) {
			ArrayList<Long> params = new ArrayList<Long>();
			for (RolePermissions RolePermissions : resultRP.getResultData()) {
				params.add(RolePermissions.getPermissionId());
			}
			return permissionService.listForId(params);
		}
		return null;
	}

	@Transactional
	public Result<Integer> save(User User, List<Long> roles) {
		Result<Integer> result = userService.save(User);
		if (result.isStatus()) {
			Result<User> resultUser = userService.queryByUserNo(User.getUserNo());
			if (resultUser.isStatus()) {
				return userRoleService.updateByUserId(resultUser.getResultData().getId(), roles);
			}
		}
		return result;
	}

	@Transactional
	public Result<Integer> delete(long userId) {
		UserRole UserRole = new UserRole();
		UserRole.setUserId(userId);
		Result<Integer> result = userRoleService.deleteByUserRole(UserRole);
		if (result.isStatus()) {
			return userService.delete(userId);
		}
		return result;
	}

	@Transactional
	public Result<Integer> update(User User, List<Long> roles) {
		Result<Integer> result = userService.update(User);
		if (result.isStatus()) {
			return userRoleService.updateByUserId(User.getId(), roles);
		}
		return result;
	}
}
