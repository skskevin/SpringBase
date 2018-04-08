package cn.mybase.ssm.shiro.realm;

import java.util.HashSet;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import cn.mybase.ssm.bean.entity.Permission;
import cn.mybase.ssm.bean.entity.Role;
import cn.mybase.ssm.bean.entity.User;
import cn.mybase.ssm.bean.entity.vo.Result;
import cn.mybase.ssm.biz.UserBiz;
import cn.mybase.ssm.shiro.CustomSessionManager;
import cn.mybase.ssm.shiro.UsernamePasswordCaptchaToken;
import cn.mybase.ssm.util.Constants;
import cn.mybase.ssm.util.VerifyCodeUtils;

public class UserRealm extends AuthorizingRealm {

	@Autowired
	private UserBiz biz;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		String userno = (String) principals.getPrimaryPrincipal();
		Result<User> result = biz.queryByUserNo(userno);
		if(result.isStatus()){
			Result<List<Role>> resultRole = biz.queryRoles(result.getResultData().getId());
			if(resultRole.isStatus()){
				//获取角色
				HashSet<String> roles = new HashSet<String>();
				for (Role Role : resultRole.getResultData()) {
					roles.add(Role.getRoleValue());
				}
				System.out.println("角色："+roles);
				authorizationInfo.setRoles(roles);
				
				//获取权限
				Result<List<Permission>> resultPermission = biz.queryPermissions(resultRole.getResultData());
				if(resultPermission.isStatus()){
					HashSet<String> permissions = new HashSet<String>();
					for (Permission Permission : resultPermission.getResultData()) {
						permissions.add(Permission.getPermissionsValue());
					}
					System.out.println("权限："+permissions);
					authorizationInfo.setStringPermissions(permissions);
				}
			}
		}
		return authorizationInfo;
		
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		UsernamePasswordCaptchaToken token = (UsernamePasswordCaptchaToken)authenticationToken;
		String userno = (String) token.getPrincipal();
		String password = new String((char[]) token.getCredentials());
		String captcha = (String)token.getCaptcha();
		System.out.println("username:"+ userno + ", captcha:" + captcha);
		if(!VerifyCodeUtils.verifyCode(captcha)){
			CustomSessionManager.setVal2Session(Constants.Token.ERRMSG, "验证码不正确!");
			System.out.println("验证码不正确!");
			return null;
		}
		Result<User> result = biz.login(userno, password);
		if (result.isStatus()) {
			CustomSessionManager.setVal2Session(Constants.Token.FLAG, "isLogin");
			User user = result.getResultData();
			CustomSessionManager.setVal2Session(Constants.Token.NICK_NAME, user.getNickName());
			return new SimpleAuthenticationInfo(user.getUserNo(), user.getPassword(), getName());
		}
		CustomSessionManager.setVal2Session(Constants.Token.ERRMSG, "帐号或密码错误!");
		return null;
	}
}
