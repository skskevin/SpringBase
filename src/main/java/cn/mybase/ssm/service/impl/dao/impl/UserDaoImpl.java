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

import cn.mybase.ssm.bean.entity.User;
import cn.mybase.ssm.bean.entity.UserExample;
import cn.mybase.ssm.bean.entity.UserExample.Criteria;
import cn.mybase.ssm.service.impl.dao.UserDao;
import cn.mybase.ssm.service.impl.dao.impl.mybatis.UserMapper;
import cn.mybase.ssm.util.Base64Util;
import cn.mybase.ssm.util.base.Page;
import cn.mybase.ssm.util.base.SqlUtil;



@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private UserMapper mapper;

	@Override
	public User selectUser(String userno, String password) {
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUserNoEqualTo(userno);
		criteria.andPasswordEqualTo(Base64Util.encrypt(password));
		List<User> result = mapper.selectByExample(example);
		if (result.size() > 0) {
			User user = result.get(0);
			user.setPassword(password);
			return user;
		}
		return null;
	}

	@Override
	public User selectByUserNo(String userno) {
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUserNoEqualTo(userno);
		List<User> result = mapper.selectByExample(example);
		if (result.size() > 0) {
			return result.get(0);
		}
		return null;
	}

	@Override
	public int insert(User User) {
		Date date = new Date();
		String password = Base64Util.encrypt(User.getPassword());
		User.setPassword(password);
		User.setCreateTime(date);
		User.setUpdateTime(date);
		return mapper.insertSelective(User);
	}

	@Override
	public int update(User User) {
		User.setUpdateTime(new Date());
		return mapper.updateByPrimaryKeySelective(User);
	}

	@Override
	public int deleteById(Integer id) {
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public Page<User> listForPage(int pageCurrent, int pageSize, String date, String seah) {
		UserExample example = new UserExample();
		example.setOrderByClause("id desc");
		Criteria criteria = example.createCriteria();
		if(StringUtils.hasText(date)){
			Date time = SqlUtil.formatterDate(date);
			criteria.andCreateTimeBetween(time, SqlUtil.addDay(time, 1));
		}
		if(StringUtils.hasText(seah)){
			criteria.andUserNoLike(SqlUtil.like(seah));
		}
		int totalCount = mapper.countByExample(example);
		pageSize = SqlUtil.checkPageSize(pageSize);
		pageCurrent = SqlUtil.checkPageCurrent(totalCount, pageSize, pageCurrent);
		int totalPage = SqlUtil.countTotalPage(totalCount, pageSize);
		example.setPageSize(pageSize);
		example.setLimitStart(SqlUtil.countOffset(pageCurrent, pageSize));
		List<User> list = mapper.selectByExample(example);
		return new Page<>(totalCount, totalPage, pageCurrent, pageSize, list);
	}

	@Override
	public User select(Integer id) {
		return mapper.selectByPrimaryKey(id);
	}

}
