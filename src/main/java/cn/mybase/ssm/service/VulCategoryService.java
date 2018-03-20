/**
 * 
 */
package cn.mybase.ssm.service;


import com.mybase.ssm.util.base.PagedResult;

import cn.mybase.ssm.VulCategory.po.VulCategory;
import cn.mybase.ssm.VulCategory.po.VulCategoryCustom;
import cn.mybase.ssm.VulCategory.po.VulCategoryQueryVo;

/**
 * <p>Title: VulCategoryService</p>
 * <p>Description: </p>
 * <p>Company: </p>
 * @author dongchuan
 * @date 2018年3月16日 下午4:43:53
 * @version 1.0
 */
public interface VulCategoryService {
	public PagedResult<VulCategoryCustom> queryByPage(String search, Integer pageNo,Integer length) throws Exception;
	public int insert(VulCategoryCustom vulCategoryCustom) throws Exception;
	public VulCategoryCustom selectById(Integer id) throws Exception;
	public int update(VulCategoryCustom vulCategoryCustom, Integer id) throws Exception;
}
