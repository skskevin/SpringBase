/**
 * 
 */
package cn.mybase.ssm.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.mybase.ssm.util.base.BaseController;

/**
 * @Title: LoginController
 * @author dongchuan
 * @date 2018年3月23日 下午4:21:37
 * @Description: TODO
 * @version 1.0
 */
@Controller
@Scope(value="prototype")
public class LoginController extends BaseController {
	@RequestMapping("/login")
	private void login(){
		
	}
}
