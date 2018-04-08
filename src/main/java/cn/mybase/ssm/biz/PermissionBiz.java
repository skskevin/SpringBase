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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.mybase.ssm.bean.entity.Permission;
import cn.mybase.ssm.bean.entity.vo.PermissionVo;
import cn.mybase.ssm.bean.entity.vo.Result;
import cn.mybase.ssm.service.PermissionService;
import cn.mybase.ssm.util.base.Page;

@Component
public class PermissionBiz {

	@Autowired
	private PermissionService service;

	public Result<Page<PermissionVo>> listForPage(int pageCurrent, int pageSize, String date, String seah) {
		Result<Page<PermissionVo>> resultVo = new Result<Page<PermissionVo>>();
		Result<Page<Permission>> result = service.listForPage(pageCurrent, pageSize, date, seah);
		
		if (result.isStatus()) {
			ArrayList<PermissionVo> resultData = new ArrayList<PermissionVo>();
			PermissionVo permissionVo;
			for (Permission permission : result.getResultData().getList()) {
				permissionVo = new PermissionVo(permission);
				resultData.add(permissionVo);
			}
			Page<PermissionVo> page = new Page<PermissionVo>(result.getResultData().getTotalCount(), result.getResultData().getTotalPage(), result.getResultData().getPageCurrent(), result.getResultData().getPageSize(), resultData);
			resultVo.setErrCode(0);
			resultVo.setStatus(true);
			resultVo.setErrMsg("查询成功");
			resultVo.setResultData(page);
			return resultVo;
		}

		return resultVo;
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
