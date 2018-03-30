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

import cn.mybase.ssm.bean.entity.EmailAccountInfo;
import cn.mybase.ssm.bean.entity.vo.Result;
import cn.mybase.ssm.util.base.Page;


/**
 * 邮件账号服务功能
 * 
 * @author LYQ
 */
public interface EmailAccountInfoService {

	/**
	 * 分页查询
	 * 
	 * @param page
	 * @param example
	 * @return
	 */
	Result<Page<EmailAccountInfo>> listForPage(int pageCurrent, int pageSize,String premise,String datePremise);

	/**
	 * 根据id查询
	 * 
	 * @param id
	 * @return
	 */
	Result<EmailAccountInfo> query(Long id);

	/**
	 * 添加
	 * 
	 * @param info
	 */
	Result<EmailAccountInfo> save(EmailAccountInfo info);

	/**
	 * 根据id删除
	 * 
	 * @param id
	 */
	Result<EmailAccountInfo> delete(Long id);

	/**
	 * 更新
	 * 
	 * @param info
	 * @return
	 */
	Result<EmailAccountInfo> update(EmailAccountInfo info);

	/**
	 * @return
	 */
	Result<List<EmailAccountInfo>> list();
}
