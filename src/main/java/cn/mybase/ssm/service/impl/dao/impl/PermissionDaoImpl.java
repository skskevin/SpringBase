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
package cn.mybase.ssm.service.impl.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import cn.mybase.ssm.bean.entity.Permission;
import cn.mybase.ssm.bean.entity.PermissionExample;
import cn.mybase.ssm.bean.entity.PermissionExample.Criteria;
import cn.mybase.ssm.service.impl.dao.PermissionDao;
import cn.mybase.ssm.service.impl.dao.impl.mybatis.PermissionMapper;
import cn.mybase.ssm.util.base.Page;
import cn.mybase.ssm.util.base.SqlUtil;


@Repository
public class PermissionDaoImpl implements PermissionDao {

	@Autowired
	private PermissionMapper mapper;

	@Override
	public Permission selectById(Integer id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public int insert(Permission Permission) {
		Date date = new Date();
		Permission.setCreateTime(date);
		Permission.setUpdateTime(date);
		return mapper.insertSelective(Permission);
	}

	@Override
	public int update(Permission Permission) {
		Permission.setUpdateTime(new Date());
		return mapper.updateByPrimaryKeySelective(Permission);
	}

	@Override
	public int deleteById(Integer id) {
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public Page<Permission> listForPage(int pageCurrent, int pageSize, String date, String seah) {
		PermissionExample example = new PermissionExample();
		example.setOrderByClause("id desc");
		Criteria criteria = example.createCriteria();
		if (StringUtils.hasText(date)) {
			Date time = SqlUtil.formatterDate(date);
			criteria.andCreateTimeBetween(time, SqlUtil.addDay(time, 1));
		}
		if (StringUtils.hasText(seah)) {
			criteria.andPermissionsNameLike(SqlUtil.like(seah));
		}
		int totalCount = mapper.countByExample(example);
		pageSize = SqlUtil.checkPageSize(pageSize);
		pageCurrent = SqlUtil.checkPageCurrent(totalCount, pageSize, pageCurrent);
		int totalPage = SqlUtil.countTotalPage(totalCount, pageSize);
		example.setPageSize(pageSize);
		example.setLimitStart(SqlUtil.countOffset(pageCurrent, pageSize));
		List<Permission> list = mapper.selectByExample(example);
		return new Page<>(totalCount, totalPage, pageCurrent, pageSize, list);
	}

	@Override
	public List<Permission> listForId(List<Integer> idList) {
		PermissionExample example = new PermissionExample();
		example.setOrderByClause("id desc");
		Criteria criteria = example.createCriteria();
		criteria.andIdIn(idList);
		return mapper.selectByExample(example);
	}

	@Override
	public List<Permission> list() {
		PermissionExample example = new PermissionExample();
		example.setOrderByClause("id desc");
		return mapper.selectByExample(example);
	}
}
