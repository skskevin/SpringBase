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

import cn.mybase.ssm.bean.entity.UserRole;
import cn.mybase.ssm.bean.entity.vo.Result;


/**
 * 用户-角色Service
 * 
 * @author LYQ
 *
 */
public interface UserRoleService {

	Result<List<UserRole>> queryByUserId(long id);

	Result<UserRole> queryByUserRole(UserRole UserRole);

	Result<Integer> insert(UserRole UserRole);

	Result<Integer> update(UserRole UserRole);

	Result<Integer> updateByUserId(long userId, List<Long> roles);
	
	Result<Integer> deleteByUserRole(UserRole UserRole);
}
