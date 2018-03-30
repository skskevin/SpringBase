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
package cn.mybase.ssm.cache;

import java.util.List;
import java.util.concurrent.ConcurrentMap;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.mybase.ssm.bean.entity.EmailAccountInfo;
import cn.mybase.ssm.bean.entity.vo.Result;
import cn.mybase.ssm.service.EmailAccountInfoService;
import cn.mybase.ssm.util.base.CachedImpl;


@Component
public class EmailAccountInfoCache extends CachedImpl<String, EmailAccountInfo> {

	@Autowired
	private EmailAccountInfoService emailAccountInfoService;

	private final static Long INITIALDELAY = 300L;
	private final static Long PERIOD = 300L;

	@PostConstruct
	public void init() {
		super.init(INITIALDELAY, PERIOD);
	}

	@Override
	public void reloadFromDb(ConcurrentMap<String, EmailAccountInfo> cached) {
		Result<List<EmailAccountInfo>> result = emailAccountInfoService.list();
		if (result.isStatus()) {
			for (EmailAccountInfo bean : result.getResultData()) {
				cached.putIfAbsent(String.valueOf(bean.getId()), bean);
			}
		}
	}

}
