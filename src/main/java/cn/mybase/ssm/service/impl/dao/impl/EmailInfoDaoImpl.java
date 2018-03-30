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

import cn.mybase.ssm.bean.entity.EmailInfo;
import cn.mybase.ssm.bean.entity.EmailInfoExample;
import cn.mybase.ssm.bean.entity.EmailInfoExample.Criteria;
import cn.mybase.ssm.service.impl.dao.EmailInfoDao;
import cn.mybase.ssm.service.impl.dao.impl.mybatis.EmailInfoMapper;
import cn.mybase.ssm.util.DateUtil;
import cn.mybase.ssm.util.base.Page;
import cn.mybase.ssm.util.base.SqlUtil;


/**
 * @author wujing
 */
@Repository
public class EmailInfoDaoImpl implements EmailInfoDao {

	@Autowired
	private EmailInfoMapper mapper;

	@Override
	public int insert(EmailInfo EmailInfo) {
		EmailInfo.setStatusId("1");
		EmailInfo.setCreateTime(new Date());
		EmailInfo.setUpdateTime(new Date());
		return mapper.insert(EmailInfo);
	}

	@Override
	public Page<EmailInfo> listForPage(int pageCurrent, int pageSize,String premise,String datePremise) {
		EmailInfoExample example = new EmailInfoExample();
		example.setOrderByClause(" id desc ");

		Criteria criteria = example.createCriteria();
		if(StringUtils.hasText(premise)){
			criteria.andToUserLike(SqlUtil.like(premise));
		}
		if(StringUtils.hasText(datePremise)){
			criteria.andCreateTimeBetween(DateUtil.parseDate(datePremise), DateUtil.addDate(DateUtil.parseDate(datePremise), 1));
		}
		
		int count = mapper.countByExample(example);
		pageSize = SqlUtil.checkPageSize(pageSize);
		pageCurrent = SqlUtil.checkPageCurrent(count, pageSize, pageCurrent);
		int totalPage = SqlUtil.countTotalPage(count, pageSize);
		
		example.setLimitStart(SqlUtil.countOffset(pageCurrent, pageSize));
		example.setPageSize(pageSize);
		
		
		List<EmailInfo> list = mapper.selectByExample(example);
		return new Page<EmailInfo>(count, totalPage, pageCurrent, pageSize, list);
	}

	@Override
	public int deleteById(Long id) {
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public EmailInfo selectById(Long id) {
		return mapper.selectByPrimaryKey(id);
	}

}
