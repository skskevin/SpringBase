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

import cn.mybase.ssm.bean.entity.EmailAccountInfo;
import cn.mybase.ssm.bean.entity.EmailInfo;
import cn.mybase.ssm.bean.entity.vo.Result;
import cn.mybase.ssm.util.base.Page;

/**
 * @author wujing
 */
public interface EmailInfoService {

	/**
	 * 分页获取
	 * 
	 * @param pageCurrent
	 * @param pageSize
	 * @return
	 */
	Result<Page<EmailInfo>> listForPage(int pageCurrent, int pageSize ,String premise,String datePremise);

	/**
	 * 根据id删除
	 * 
	 * @param id
	 */
	Result<EmailInfo> delete(Long id);

	/**
	 * 根据id查询
	 * 
	 * @param id
	 * @return
	 */
	Result<EmailInfo> query(Long id);
	
	/**
	 * 发送邮件
	 * 
	 * @param EmailInfo
	 */
	Result<EmailInfo> sendMail(EmailAccountInfo accountInfo, EmailInfo EmailInfo);
}
