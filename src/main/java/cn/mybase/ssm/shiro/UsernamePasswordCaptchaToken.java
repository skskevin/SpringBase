/**
 * 
 */
package cn.mybase.ssm.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * @Title: UsernamePasswordCaptchaToken
 * @author dongchuan
 * @date 2018年3月29日 下午12:33:25
 * @Description: 扩展UsernamePasswordToken，添加验证码属性
 * @version 1.0
 */
public class UsernamePasswordCaptchaToken extends UsernamePasswordToken {

	private static final long serialVersionUID = 1L;
	private String captcha;
	
	public UsernamePasswordCaptchaToken() {  
        super();  
  
    }  
  
    public UsernamePasswordCaptchaToken(String username, char[] password, boolean rememberMe, String host, String captcha) {  
        super(username, password, rememberMe, host);  
        this.captcha = captcha;  
    }

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	} 

}
