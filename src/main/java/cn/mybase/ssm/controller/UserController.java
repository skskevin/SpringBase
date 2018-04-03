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

import cn.mybase.ssm.bean.entity.Role;
import cn.mybase.ssm.bean.entity.User;
import cn.mybase.ssm.bean.entity.vo.Result;
import cn.mybase.ssm.bean.entity.vo.UserVo;
import cn.mybase.ssm.biz.UserBiz;
import cn.mybase.ssm.util.base.BaseController;
import cn.mybase.ssm.util.base.Page;
import cn.mybase.ssm.util.base.PageBean;
import cn.mybase.ssm.util.base.ParamUtil;

/**
 * @Title: UserController
 * @author dongchuan
 * @date 2018年3月30日 下午3:18:50
 * @Description: TODO
 * @version 1.0
 */
@Controller
@RequestMapping(value = "user", method = RequestMethod.POST)
public class UserController extends BaseController {

	@Autowired
	private UserBiz biz;

	@RequestMapping(value = LIST, method = RequestMethod.GET)
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
	public PageBean<UserVo> queryForPage(@RequestParam(value = "start", defaultValue = "1") int start,
			@RequestParam(value = "length", defaultValue = "10") int pageSize,
			@RequestParam(value = "date", required = false) String date,
			@RequestParam(value = "search", required = false) String search) {
		Result<Page<UserVo>> result = biz.listForPage((start / pageSize) + 1, pageSize, date, search);
		if (result.isStatus()) {
			return new PageBean<UserVo>(result.getResultData());
		}
		return new PageBean<UserVo>();
	}

	@RequestMapping(value = ADD, method = RequestMethod.GET)
	public void add(ModelMap modelMap) {
		Result<List<Role>> resultRole = biz.queryRoleList();
		if (resultRole.isStatus()) {
			modelMap.put("roles", resultRole.getResultData());
		}
	}

	@RequestMapping(value = SAVE)
	public String save(User user, @RequestParam(value = "role", required = false) String role) {
		List<Integer> roles = ParamUtil.toIntList(role, ",");
		Result<Integer> result = biz.save(user, roles);
		if (result.isStatus()) {
			return redirect("/user/list");
		}
		return null;
	}

	@RequestMapping(value = EDIT, method = RequestMethod.GET)
	public void edit(ModelMap modelMap, Integer id) {
		Result<UserVo> result = biz.query(id);
		if (result.isStatus()) {
			modelMap.put("bean", result.getResultData());
		}
		Result<List<Role>> resultRole = biz.queryRoleList();
		if (resultRole.isStatus()) {
			modelMap.put("roles", resultRole.getResultData());
		}
	}

	@ResponseBody
	@RequestMapping(value = UPDATE, method = RequestMethod.POST)
	public String update(User user, @RequestParam(value = "role", required = false) String role) {
		List<Integer> roles = ParamUtil.toIntList(role, ",");
		Result<Integer> result = biz.update(user, roles);
		if (result.isStatus()) {
			return "success";
		}
		return null;
	}

	@RequestMapping(value = DELETE, method = RequestMethod.GET)
	public String delete(Integer id) {
		Result<Integer> result = biz.delete(id);
		if (result.isStatus()) {
			return redirect("/user/list");
		}
		return null;
	}

	@RequestMapping(value = VIEW, method = RequestMethod.GET)
	public void view(ModelMap modelMap, Integer id) {
		Result<UserVo> result = biz.query(id);
		if (result.isStatus()) {
			modelMap.put("bean", result.getResultData());
		}
		
	}
}
