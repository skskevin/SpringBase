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

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.mybase.ssm.bean.entity.EmailAccountInfo;
import cn.mybase.ssm.bean.entity.vo.Result;
import cn.mybase.ssm.service.EmailAccountInfoService;
import cn.mybase.ssm.util.base.Page;


/**
 * 邮件账号逻辑功能
 * 
 * @author LYQ
 */
@Component
public class EmailAccountInfoBiz {

	@Autowired
	private EmailAccountInfoService emailAccountInfoService;


	/**
	 * 分页查询
	 * 
	 * @param pageCurrent
	 * @param pageSize
	 * @return
	 */
	public Result<Page<EmailAccountInfo>> listForPage(int pageCurrent, int pageSize,String premise,String datePremise) {
		return emailAccountInfoService.listForPage(pageCurrent, pageSize,premise,datePremise);
	}

	/**
	 * 添加
	 * 
	 * @param EmailAccountInfo
	 */
	public Result<EmailAccountInfo> save(EmailAccountInfo info) {
		return emailAccountInfoService.save(info);
	}

	/**
	 * 根据id删除
	 * 
	 * @param id
	 */
	public Result<EmailAccountInfo> delete(Long id) {
		return emailAccountInfoService.delete(id);
	}

	/**
	 * 根据id查询
	 * 
	 * @param id
	 * @return
	 */
	public Result<EmailAccountInfo> query(Long id) {
		return emailAccountInfoService.query(id);
	}

	/**
	 * 更新
	 * 
	 * @param info
	 */
	public Result<EmailAccountInfo> update(EmailAccountInfo info) {
		return emailAccountInfoService.update(info);
	}

}
