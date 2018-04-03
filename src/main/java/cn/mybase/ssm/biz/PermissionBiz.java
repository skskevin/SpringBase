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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.mybase.ssm.bean.entity.Permission;
import cn.mybase.ssm.bean.entity.vo.Result;
import cn.mybase.ssm.service.PermissionService;
import cn.mybase.ssm.util.base.Page;


@Component
public class PermissionBiz {

	@Autowired
	private PermissionService service;
	

	public Result<Page<Permission>> listForPage(int pageCurrent, int pageSize, String date, String seah) {
		return service.listForPage(pageCurrent, pageSize, date, seah);
	}

	public Result<Permission> query(Integer id) {
		return service.query(id);
	}

	public Result<Integer> save(Permission Permission) {
		return service.save(Permission);
	}
	

	public Result<Integer> delete(Integer id) {
		return service.delete(id);
	}

	public Result<Integer> update(Permission Permission) {
		return service.update(Permission);
	}
}
