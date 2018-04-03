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
package cn.mybase.ssm.service.impl.dao;

import java.util.List;

import cn.mybase.ssm.bean.entity.UserRole;


/**
 * 用户-角色Dao
 * @author LYQ
 *
 */
public interface UserRoleDao {
	
	UserRole selectById(long id);
 
	List<UserRole> selectByUserId(Integer id);
	
	UserRole selectByUserRole(UserRole UserRole);
	
	int insert(UserRole UserRole);

	int update(UserRole UserRole);

	int delectByUserRole(UserRole UserRole);
	
	int deleteById(long id);
	
	int deleteByUserId(Integer userId);
	
}
