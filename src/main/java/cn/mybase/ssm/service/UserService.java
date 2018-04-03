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

import cn.mybase.ssm.bean.entity.User;
import cn.mybase.ssm.bean.entity.vo.Result;
import cn.mybase.ssm.util.base.Page;

/**
 * 用户Service
 * 
 * @author LYQ
 *
 */
public interface UserService {

	Result<User> login(String userno, String password);

	Result<Page<User>> listForPage(int pageCurrent, int pageSize, String date, String search);

	Result<User> query(Integer id);

	Result<User> queryByUserNo(String userno);

	Result<Integer> save(User User);

	Result<Integer> update(User User);

	Result<Integer> delete(Integer id);

}
