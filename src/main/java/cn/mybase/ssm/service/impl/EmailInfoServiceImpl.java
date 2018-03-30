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
package cn.mybase.ssm.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import cn.mybase.ssm.bean.entity.EmailAccountInfo;
import cn.mybase.ssm.bean.entity.EmailInfo;
import cn.mybase.ssm.bean.entity.vo.Result;
import cn.mybase.ssm.service.EmailInfoService;
import cn.mybase.ssm.service.impl.dao.EmailInfoDao;
import cn.mybase.ssm.util.Base64Util;
import cn.mybase.ssm.util.base.Page;
import freemarker.template.Template;

/**
 * @author wujing
 */
@Service
public class EmailInfoServiceImpl implements EmailInfoService {

	private static final String TEMPLATE = "mail/roncoo.ftl";

	@Autowired
	private EmailInfoDao dao;

	@Autowired
	private JavaMailSenderImpl javaMailSender;
	@Autowired
	private FreeMarkerConfigurer FreeMarkerConfigurer;
	@Autowired
	private ThreadPoolTaskExecutor threadPoolTaskExecutor;

	@Override
	public Result<Page<EmailInfo>> listForPage(int pageCurrent, int pageSize,String premise,String datePremise) {
		Result<Page<EmailInfo>> result = new Result<>();
		if (pageCurrent < 1) {
			result.setErrMsg("参数pageCurrent有误,pageCurrent=" + pageCurrent);
			return result;
		}
		if (pageSize < 1) {
			result.setErrMsg("参数pageSize有误,pageSize=" + pageSize);
			return result;
		}
		Page<EmailInfo> resultData = dao.listForPage(pageCurrent, pageSize,premise,datePremise);
		result.setResultData(resultData);
		result.setStatus(true);
		result.setErrCode(0);
		return result;
	}

	@Override
	public Result<EmailInfo> delete(Long id) {
		Result<EmailInfo> result = new Result<>();
		if (id < 1) {
			result.setErrMsg("此操作的id：" + id + "为无效id");
			return result;
		}
		if (dao.deleteById(id) > 0) {
			result.setStatus(true);
			result.setErrCode(0);
		}
		return result;
	}

	@Override
	public Result<EmailInfo> query(Long id) {
		Result<EmailInfo> result = new Result<>();
		if (id < 1) {
			result.setErrMsg("此操作的id：" + id + "为无效id");
			return result;
		}
		EmailInfo resultData = dao.selectById(id);
		result.setResultData(resultData);
		result.setStatus(true);
		result.setErrCode(0);
		return result;
	}

	@Override
	public Result<EmailInfo> sendMail(EmailAccountInfo accountInfo, EmailInfo EmailInfo) {
		Result<EmailInfo> result = new Result<>();
		if(!StringUtils.hasText(EmailInfo.getToUser())){
			result.setErrMsg("收件人不能为空");
			return result;
		}
		if(!StringUtils.hasText(EmailInfo.getSubject())){
			result.setErrMsg("主题不能为空");
			return result;
		}
		if(!StringUtils.hasText(EmailInfo.getTitle())){
			result.setErrMsg("标题不能为空");
			return result;
		}
		// 编辑发送器
		createMailSender(accountInfo);

		HashMap<String, Object> map = new HashMap<>();
		map.put("content", EmailInfo.getContent());
		// 发送邮件
		send(accountInfo.getFromUser(), EmailInfo.getToUser(), EmailInfo.getSubject(), map, TEMPLATE);
		EmailInfo.setFromUser(accountInfo.getFromUser());
		// 保存记录
		if(dao.insert(EmailInfo)>0){
			result.setStatus(true);
			result.setErrCode(0);
		}
		return result;
	}

	/**
	 * 
	 * @param to
	 * @param subject
	 * @param map
	 * @param templatePath
	 */
	private void send(String fromUser, String to, String subject, Map<String, Object> map, String templatePath) {
		try {
			// 从FreeMarker模板生成邮件内容
			Template template = FreeMarkerConfigurer.getConfiguration().getTemplate(templatePath);
			String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
			this.threadPoolTaskExecutor.execute(new SendMailThread(fromUser, to, subject, text));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void createMailSender(EmailAccountInfo info) {
		javaMailSender.setHost(info.getHost());
		javaMailSender.setUsername(info.getFromUser());
		String passwd = Base64Util.decode(info.getPasswd());
		javaMailSender.setPassword(passwd);
	}

	// 内部线程类，利用线程池异步发邮件。
	private class SendMailThread implements Runnable {
		private String fromUser;
		private String to;
		private String subject;
		private String content;

		private SendMailThread(String fromUser, String to, String subject, String content) {
			super();
			this.fromUser = fromUser;
			this.to = to;
			this.subject = subject;
			this.content = content;
		}

		@Override
		public void run() {
			send(fromUser, to, subject, content);
		}

		private static final String NAME = "龙果学院";

		public void send(String fromUser, String to, String subject, String text) {
			try {
				MimeMessage message = javaMailSender.createMimeMessage();
				MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
				InternetAddress from = new InternetAddress();
				from.setAddress(fromUser);
				from.setPersonal(NAME, "UTF-8");
				helper.setFrom(from);
				helper.setTo(to);
				helper.setSubject(subject);
				helper.setText(text, true);
				javaMailSender.send(message);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
