/**
 * 
 */
package cn.mybase.ssm.shiro.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import cn.mybase.ssm.shiro.UsernamePasswordCaptchaToken;

/**
 * @Title: FormAuthenticationCaptchaFilter
 * @author dongchuan
 * @date 2018年3月29日 下午12:40:08
 * @Description: TODO
 * @version 1.0
 */
public class FormAuthenticationCaptchaFilter extends FormAuthenticationFilter {
	/** 验证码参数名 */
	public static final String CAPTCHA_PARAM_NAME = "captcha";

	protected String getCaptcha(ServletRequest request) {
		return WebUtils.getCleanParam(request, CAPTCHA_PARAM_NAME);
	}

	/*
	 * 创建登陆认证token传值传递给UserRealm的shiro认证
	 * 
	 * @see
	 * org.apache.shiro.web.filter.authc.FormAuthenticationFilter#createToken(
	 * javax.servlet.ServletRequest, javax.servlet.ServletResponse)
	 */
	@Override
	protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
		String username = getUsername(request);
		String password = getPassword(request);
		String captcha = getCaptcha(request);// 获取验证码

		boolean rememberMe = isRememberMe(request);
		String host = getHost(request);

		return new UsernamePasswordCaptchaToken(username, password.toCharArray(), rememberMe, host, captcha);
	}

	/*
	 * 执行登陆
	 * 
	 * @see
	 * org.apache.shiro.web.filter.authc.AuthenticatingFilter#executeLogin(javax
	 * .servlet.ServletRequest, javax.servlet.ServletResponse)
	 */
	@Override
	protected boolean executeLogin(ServletRequest arg0, ServletResponse arg1) throws Exception {
//		System.out.println("----------executeLogin----------------");

		return super.executeLogin(arg0, arg1);
	}

	/*
	 * 设置失败属性（登陆失败）
	 * 
	 * @see org.apache.shiro.web.filter.authc.FormAuthenticationFilter#
	 * setFailureAttribute(javax.servlet.ServletRequest,
	 * org.apache.shiro.authc.AuthenticationException)
	 */
	@Override
	protected void setFailureAttribute(ServletRequest request, AuthenticationException ae) {
		// TODO Auto-generated method stub

//		System.out.println("------setFailureAttribute--------------");

		super.setFailureAttribute(request, ae);
	}

	/*
	 * 链接被否定
	 * 
	 * @see
	 * org.apache.shiro.web.filter.authc.FormAuthenticationFilter#onAccessDenied
	 * (javax.servlet.ServletRequest, javax.servlet.ServletResponse)
	 */
	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("------onAccessDenied--------------");

		return super.onAccessDenied(request, response);
	}
}
