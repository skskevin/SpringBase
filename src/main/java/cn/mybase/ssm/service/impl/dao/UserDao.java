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

import cn.mybase.ssm.bean.entity.User;
import cn.mybase.ssm.util.base.Page;

/**
 * 用户Dao
 * @author LYQ
 *
 */
public interface UserDao {
	
	User selectUser(String userno,String password);
	
	User selectByUserNo(String userno);
	
	int insert(User user);
	
	int update(User user);
	
	int deleteById(Long id);
	
	Page<User> listForPage(int pageCurrent, int pageSize, String date, String seah);
	
	User select(long id);
}
