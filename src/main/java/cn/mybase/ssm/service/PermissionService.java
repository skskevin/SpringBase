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

import cn.mybase.ssm.bean.entity.Permission;
import cn.mybase.ssm.bean.entity.vo.Result;
import cn.mybase.ssm.util.base.Page;


/**
 * 权限Service
 * 
 * @author LYQ
 *
 */
public interface PermissionService {

	Result<Permission> query(long id);

	Result<Integer> save(Permission Permission);

	Result<Integer> update(Permission Permission);

	Result<Integer> delete(long id);
	
	Result<Page<Permission>> listForPage(int pageCurrent, int pageSize, String date, String seah);
	
	Result<List<Permission>> listForId(List<Long> idList);
	
	Result<List<Permission>> list();
}
