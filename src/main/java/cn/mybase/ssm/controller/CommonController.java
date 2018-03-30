package cn.mybase.ssm.controller;


import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cn.mybase.ssm.shiro.CustomSessionManager;
import cn.mybase.ssm.util.LoggerUtils;
import cn.mybase.ssm.util.VerifyCodeUtils;
import cn.mybase.ssm.util.base.BaseController;
import cn.mybase.ssm.util.vcode.Captcha;
import cn.mybase.ssm.util.vcode.GifCaptcha;
import cn.mybase.ssm.util.vcode.SpecCaptcha;

@Controller
@Scope(value="prototype")
@RequestMapping("open")
public class CommonController extends BaseController {
	/**
	 * 404错误
	 * @param request
	 * @return
	 */
	@RequestMapping("404")
	public ModelAndView _404(HttpServletRequest request){
		ModelAndView view = new ModelAndView("common/404");
		return view;
	}

	
	/**
	 * 获取验证码
	 * @param response
	 */
	@RequestMapping(value="getVCode",method=RequestMethod.GET)
	public void getVCode(HttpServletResponse response,HttpServletRequest request){
		try {
			response.setHeader("Pragma", "No-cache");  
	        response.setHeader("Cache-Control", "no-cache");  
	        response.setDateHeader("Expires", 0);  
	        response.setContentType("image/jpg");  
	        
	        //生成随机字串  
	        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);  
	        //存入Shiro会话session  
	        CustomSessionManager.setVal2Session(VerifyCodeUtils.V_CODE, verifyCode.toLowerCase()); 
	        System.out.println("验证码:" + verifyCode.toLowerCase()+"已存入Session");
	        //生成图片  
	        int w = 146, h = 33;  
	        VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode); 
		} catch (Exception e) {
			LoggerUtils.fmtError(getClass(),e, "获取验证码异常：%s",e.getMessage());
		}
	}
	
	/**
	 * 获取验证码（Gif版本）
	 * @param response
	 */
	@RequestMapping(value="getGifCode",method=RequestMethod.GET)
	public void getGifCode(HttpServletResponse response,HttpServletRequest request){
		try {
			response.setHeader("Pragma", "No-cache");  
	        response.setHeader("Cache-Control", "no-cache");  
	        response.setDateHeader("Expires", 0);  
	        response.setContentType("image/gif");  
	        /**
	         * gif格式动画验证码
	         * 宽，高，位数。
	         */
	        Captcha captcha = new GifCaptcha(146,42,4);
	        //输出
	        ServletOutputStream out = response.getOutputStream();
	        captcha.out(out);
	        out.flush();
	       //存入Shiro会话session  
	        System.out.println("生成验证码:" + captcha.text().toLowerCase());
	        CustomSessionManager.setVal2Session(VerifyCodeUtils.V_CODE, captcha.text().toLowerCase()); 
		} catch (Exception e) {
			LoggerUtils.fmtError(getClass(),e, "获取验证码异常：%s",e.getMessage());
		}
	}
	/**
	 * 获取验证码（jpg版本）
	 * @param response
	 */
	@RequestMapping(value="getJPGCode",method=RequestMethod.GET)
	public void getJPGCode(HttpServletResponse response,HttpServletRequest request){
		try {
			response.setHeader("Pragma", "No-cache");  
			response.setHeader("Cache-Control", "no-cache");  
			response.setDateHeader("Expires", 0);  
			response.setContentType("image/jpg");  
			/**
			 * jgp格式验证码
			 * 宽，高，位数。
			 */
			Captcha captcha = new SpecCaptcha(146,33,4);
			//输出
			captcha.out(response.getOutputStream());
//			HttpSession session = request.getSession(true);  
			//存入Session
//			session.setAttribute("_code",captcha.text().toLowerCase());  
			CustomSessionManager.setVal2Session(VerifyCodeUtils.V_CODE, captcha.text().toLowerCase()); 
	        System.out.println("验证码:" + captcha.text().toLowerCase()+"已存入Session");
		} catch (Exception e) {
			LoggerUtils.fmtError(getClass(),e, "获取验证码异常：%s",e.getMessage());
		}
	}

}
