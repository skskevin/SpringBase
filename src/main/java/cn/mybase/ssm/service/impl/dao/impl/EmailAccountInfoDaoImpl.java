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

import cn.mybase.ssm.bean.entity.EmailAccountInfo;
import cn.mybase.ssm.bean.entity.EmailAccountInfoExample;
import cn.mybase.ssm.bean.entity.EmailAccountInfoExample.Criteria;
import cn.mybase.ssm.service.impl.dao.EmailAccountInfoDao;
import cn.mybase.ssm.service.impl.dao.impl.mybatis.EmailAccountInfoMapper;
import cn.mybase.ssm.util.Base64Util;
import cn.mybase.ssm.util.DateUtil;
import cn.mybase.ssm.util.base.Page;
import cn.mybase.ssm.util.base.SqlUtil;


@Repository
public class EmailAccountInfoDaoImpl implements EmailAccountInfoDao {

	@Autowired
	private EmailAccountInfoMapper mapper;

	@Override
	public int deleteById(Long id) {
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(EmailAccountInfo info) {
		String passwd = Base64Util.encrypt(info.getPasswd());
		info.setPasswd(passwd);
		Date date = new Date();
		info.setCreateTime(date);
		info.setUpdateTime(date);
		return mapper.insertSelective(info);
	}

	@Override
	public Page<EmailAccountInfo> listForPage(int pageCurrent, int pageSize, String premise, String datePremise) {
		EmailAccountInfoExample example = new EmailAccountInfoExample();
		example.setOrderByClause("id desc");
		Criteria criteria = example.createCriteria();
		if (StringUtils.hasText(premise)) {
			criteria.andFromUserLike(SqlUtil.like(premise));
		}
		if (StringUtils.hasText(datePremise)) {
			criteria.andCreateTimeBetween(DateUtil.parseDate(datePremise), DateUtil.addDate(DateUtil.parseDate(datePremise), 1));
		}
		int totalCount = mapper.countByExample(example);
		pageSize = SqlUtil.checkPageSize(pageSize);
		pageCurrent = SqlUtil.checkPageCurrent(totalCount, pageSize, pageCurrent);
		int totalPage = SqlUtil.countTotalPage(totalCount, pageSize);
		example.setLimitStart(SqlUtil.countOffset(pageCurrent, pageSize));
		example.setPageSize(pageSize);
		
		List<EmailAccountInfo> list = mapper.selectByExample(example);
		Page<EmailAccountInfo> page = new Page<>(totalCount, totalPage, pageCurrent, pageSize, list);
		return page;
	}

	@Override
	public EmailAccountInfo selectById(Long id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateById(EmailAccountInfo info) {
		String passwd = Base64Util.encrypt(info.getPasswd());
		info.setPasswd(passwd);
		info.setUpdateTime(new Date());
		return mapper.updateByPrimaryKeySelective(info);
	}

	@Override
	public List<EmailAccountInfo> list() {
		EmailAccountInfoExample example = new EmailAccountInfoExample();
		example.setOrderByClause("id desc");
		return mapper.selectByExample(example);
	}
}
