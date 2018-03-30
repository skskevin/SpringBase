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
package cn.mybase.ssm.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.springframework.session.config.annotation.web.http.EnableSpringHttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.mybase.ssm.shiro.CustomSessionManager;
import cn.mybase.ssm.util.Constants;
import cn.mybase.ssm.util.base.BaseController;



/**
 * 登录功能，集成了龙果学院的oauth登录
 * 
 * @author wujing
 */
@Controller
@RequestMapping
@EnableSpringHttpSession
public class LoginController extends BaseController {

	/**
	 * 进入登录页面
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getLogin(HttpSession session) {
		Object token = session.getAttribute(Constants.Token.FLAG);
		if (token == null) {
			return "login";
		}
		return redirect("/index");
	}

	/**
	 * 登录，跳转到龙果学院授权页面
	 * 
	 * @throws IOException
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public void postLogin(ModelMap modelMap) {
		Object token = CustomSessionManager.getVal2Session(Constants.Token.FLAG);
		if (token == null) {
			String errmsg = (String) CustomSessionManager.getVal2Session(Constants.Token.ERRMSG);
			System.out.println("errmsg:"+ errmsg);
			modelMap.put("errmsg", errmsg);
		}

	}


	/**
	 * 退出登陆操作
	 */
	@RequestMapping(value = "/exit", method = RequestMethod.GET)
	public String exit(RedirectAttributes redirectAttributes, HttpSession session) {
		session.invalidate();
		SecurityUtils.getSubject().logout();
		redirectAttributes.addFlashAttribute("msg", "您已经安全退出");
		return redirect("/login");
	}

	/**
	 * 权限不足
	 */
	@RequestMapping(value = "/error", method = RequestMethod.GET)
	public void error() {

	}

}
