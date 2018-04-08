/**
 * 
 */
package cn.mybase.ssm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.mybase.ssm.bean.entity.Permission;
import cn.mybase.ssm.bean.entity.vo.PermissionVo;
import cn.mybase.ssm.bean.entity.vo.Result;
import cn.mybase.ssm.biz.PermissionBiz;
import cn.mybase.ssm.util.base.BaseController;
import cn.mybase.ssm.util.base.Page;
import cn.mybase.ssm.util.base.PageBean;

/**
 * @Title: Permission
 * @author dongchuan
 * @date 2018年4月3日 下午2:32:37
 * @Description: TODO
 * @version 1.0
 */

@Controller
@RequestMapping("/permission")
public class PermissionController extends BaseController {

	@Autowired
	private PermissionBiz biz;

	@RequestMapping(value = LIST, method = RequestMethod.GET)
	public void list() {

	}

	@ResponseBody
	@RequestMapping(value = PAGE, method = RequestMethod.POST)
	public PageBean<PermissionVo> queryForPage(@RequestParam(value = "start", defaultValue = "1") int start,
			@RequestParam(value = "length", defaultValue = "10") int pageSize,
			@RequestParam(value = "date", required = false) String date,
			@RequestParam(value = "search", required = false) String search) {

		Result<Page<PermissionVo>> result = biz.listForPage((start / pageSize) + 1, pageSize, date, search);
		if (result.isStatus()) {
			return new PageBean<PermissionVo>(result.getResultData());
		}
		return new PageBean<PermissionVo>();

	}

	@RequestMapping(value = ADD, method = RequestMethod.GET)
	public void add() {

	}

	@RequestMapping(value = SAVE, method = RequestMethod.POST)
	public String save(Permission permission) {
		Result<Integer> result = biz.save(permission);
		if (result.isStatus()) {
			return redirect("/permission/list");
		}
		return null;
	}

	@RequestMapping(value = EDIT, method = RequestMethod.GET)
	public void edit(ModelMap modelMap, Integer id) {
		Result<Permission> result = biz.query(id);
		if (result.isStatus()) {
			modelMap.put("bean", result.getResultData());
		}
	}
	
	@RequestMapping(value=UPDATE, method = RequestMethod.POST)
	public String update(Permission permission){
		Result<Integer> result = biz.update(permission);
		if (result.isStatus()){
			return redirect("/permission/list");
		}
		return null;
	}
	
	@RequestMapping(value=VIEW, method= RequestMethod.GET)
	public void view(ModelMap modelMap, Integer id){
		Result<Permission> result = biz.query(id);
		if (result.isStatus()){
			modelMap.put("bean", result.getResultData());
		}
	}
	
	@RequestMapping(value=DELETE, method=RequestMethod.GET)
	public String delete(Integer id){
		Result<Integer> result = biz.delete(id);
		if (result.isStatus()){
			return redirect("/permission/list");
		}
		return null;
	}

}
