/**
 * 
 */
package cn.mybase.ssm.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.mybase.ssm.bean.entity.VulCategory;
import cn.mybase.ssm.bean.entity.vo.VulCategoryVo;
import cn.mybase.ssm.service.VulCategoryService;
import cn.mybase.ssm.util.base.BaseController;
import cn.mybase.ssm.util.base.PagedResult;

/**
 * 
 * <p>
 * Title: VulCategoryController
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author dongchuan
 * @date 2018年3月19日 上午10:02:34
 * @version 1.0
 */
@Controller
@Scope(value = "prototype")
@RequestMapping(value = "VulCategory")
public class VulCategoryController extends BaseController {
	@Autowired
	private VulCategoryService vulCategoryService;

	@RequestMapping(value = LIST, method = RequestMethod.GET)
	public void list() {

	}

	/**
	 * 分页查询
	 * 
	 * @param start
	 * @param length
	 * @param search
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = PAGE)
	public PagedResult<VulCategoryVo> queryForPage(Integer start, Integer length, String search) throws Exception {
		length = length < 1 ? 10 : length;
		int pageNo = start < length ? 1 : start / length + 1;
		PagedResult<VulCategory> pagedResult = vulCategoryService.queryByPage(search, pageNo, length);
		List<VulCategoryVo> resultVo = new ArrayList<VulCategoryVo>();
		for (VulCategory vulCategory : pagedResult.getData()) {
			VulCategoryVo VulCategoryVo = new VulCategoryVo(vulCategory);
			resultVo.add(VulCategoryVo);
		}
		PagedResult<VulCategoryVo> pagedResultVo = new PagedResult<VulCategoryVo>(resultVo, pagedResult.getPageNo(),
				pagedResult.getPageSize(), pagedResult.getRecordsTotal(), pagedResult.getRecordsFiltered(),
				pagedResult.getPages());
		
		return pagedResultVo;
	}

	@RequestMapping(value = ADD)
	public void add() {

	}

	/**
	 * 
	 * @Title: save
	 * @Description: 保存新增
	 * @param vulCategoryCustom
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = SAVE)
	public String save(VulCategory vulCategory) throws Exception {
		int rs = vulCategoryService.insert(vulCategory);
		if (rs > 0) {
			return redirect("/VulCategory/list");
		}
		return null;
	}

	/**
	 * 
	 * @Title: view
	 * @Description: 查看详情
	 * @param modelMap
	 * @param id
	 * @throws Exception
	 */
	@RequestMapping(value = VIEW, method = RequestMethod.GET)
	public void view(ModelMap modelMap, Integer id) throws Exception {
		VulCategory vulCategory = vulCategoryService.selectById(id);
		modelMap.put("result", vulCategory);
	}

	@RequestMapping(value = EDIT, method = RequestMethod.GET)
	public void edit(ModelMap modelMap, Integer id) throws Exception {
		VulCategory vulCategory = vulCategoryService.selectById(id);
		modelMap.put("result", vulCategory);
	}

	@RequestMapping(value = UPDATE, method = RequestMethod.POST)
	@ResponseBody
	public String update(VulCategory vulCategory, Integer id) throws Exception {
		int rs = vulCategoryService.update(vulCategory, id);
		if (rs > 0) {
			return redirect("/VulCategory/list");
		}
		return null;
	}

	@RequestMapping(value = DELETE, method = RequestMethod.GET)
	@ResponseBody
	public String delete(Integer id) throws Exception {
		int rs = vulCategoryService.delete(id);
		if (rs > 0) {
			return redirect("/VulCategory/list");
		}
		return null;
	}
}
