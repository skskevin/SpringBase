/**
 * 
 */
package cn.mybase.ssm.service;


import cn.mybase.ssm.bean.entity.VulCategory;
import cn.mybase.ssm.bean.entity.vo.VulCategoryVo;
import cn.mybase.ssm.util.base.PagedResult;

/**
 * <p>Title: VulCategoryService</p>
 * <p>Description: </p>
 * <p>Company: </p>
 * @author dongchuan
 * @date 2018年3月16日 下午4:43:53
 * @version 1.0
 */
public interface VulCategoryService {
	public PagedResult<VulCategoryVo> queryByPage(String search, Integer pageNo,Integer length) throws Exception;
	public int insert(VulCategory vulCategory) throws Exception;
	public VulCategoryVo selectById(Integer id) throws Exception;
	public int update(VulCategory vulCategory, Integer id) throws Exception;
	public int delete(Integer id) throws Exception;
}
