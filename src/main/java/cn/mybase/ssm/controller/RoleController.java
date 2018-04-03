/**
 * 
 */
package cn.mybase.ssm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.mybase.ssm.bean.entity.Permission;
import cn.mybase.ssm.bean.entity.Role;
import cn.mybase.ssm.bean.entity.vo.Result;
import cn.mybase.ssm.biz.RoleBiz;
import cn.mybase.ssm.util.base.BaseController;
import cn.mybase.ssm.util.base.Page;
import cn.mybase.ssm.util.base.PageBean;

/**
 * @Title: RoleController
 * @author dongchuan
 * @date 2018年4月2日 下午2:47:29
 * @Description: 角色管理
 * @version 1.0
 */
@Controller
@RequestMapping(value = "/role", method = RequestMethod.POST)
public class RoleController extends BaseController {
	@Autowired
	private RoleBiz biz;

	@RequestMapping(value = LIST, method=RequestMethod.GET)
	public void list() {

	}
	
	/**
	 * 
	* @Title: queryForPage  
	* @Description: 分页查询 
	* @param start
	* @param pageSize
	* @param date
	* @param search
	* @return
	 */
	@ResponseBody
	@RequestMapping(value = PAGE)
	public PageBean<Role> queryForPage(@RequestParam(value = "start", defaultValue = "1") int start,
			@RequestParam(value = "length", defaultValue = "10") int pageSize,
			@RequestParam(value = "date", required = false) String date,
			@RequestParam(value = "search", required = false) String search) {

		Result<Page<Role>> result = biz.listForPage((start / pageSize) + 1, pageSize, date, search);
		if (result.isStatus()) {
			return new PageBean<Role>(result.getResultData());
		}
		return new PageBean<Role>();

	}
	
	@RequestMapping(value = ADD, method=RequestMethod.GET)
	public void add(ModelMap modelMap){
		Result<List<Permission>> resultPermission = biz.queryPermissionList();
		if (resultPermission.isStatus()){
			modelMap.put("permissions", resultPermission.getResultData());
		}
	}
}
