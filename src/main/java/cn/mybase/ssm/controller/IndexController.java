/**
 * 
 */
package cn.mybase.ssm.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.mybase.ssm.shiro.CustomSessionManager;
import cn.mybase.ssm.util.Constants;

/**
 * <p>Title: IndexController</p>
 * <p>Description: 首页</p>
 * <p>Company: </p>
 * @author dongchuan
 * @date 2018年3月19日 上午9:45:28
 * @version 1.0
 */

@Controller
@Scope(value="prototype")
public class IndexController {
	
	@RequestMapping("/index")
	public void index(ModelMap modelMap){
		 modelMap.put("nick_name",CustomSessionManager.getVal2Session(Constants.Token.NICK_NAME));
	}

}
