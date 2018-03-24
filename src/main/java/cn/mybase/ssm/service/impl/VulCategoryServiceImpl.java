/**
 * 
 */
package cn.mybase.ssm.service.impl;

import java.util.Date;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;

import cn.mybase.ssm.dao.VulCategoryMapper;
import cn.mybase.ssm.dao.VulCategoryMapperCustom;
import cn.mybase.ssm.po.VulCategory;
import cn.mybase.ssm.po.VulCategoryCustom;
import cn.mybase.ssm.service.VulCategoryService;
import cn.mybase.ssm.util.base.BeanUtil;
import cn.mybase.ssm.util.base.PagedResult;

/**
 * 
 * @Title: VulCategoryServiceImpl
 * @author dongchuan
 * @date 2018年3月20日 上午11:17:43
 * @Description: TODO
 * @version 1.0
 */
public class VulCategoryServiceImpl implements VulCategoryService {

	@Autowired
	private VulCategoryMapperCustom vulCategoryMapperCustom;

	@Autowired
	private VulCategoryMapper vulCategoryMapper;

	/**
	 * 分页信息
	 */
	public PagedResult<VulCategoryCustom> queryByPage(String search,
			@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(value = "length", defaultValue = "10") Integer pageSize) throws Exception {
		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = pageSize == null ? 10 : pageSize;
		PageHelper.startPage(pageNo, pageSize); // startPage是告诉拦截器说我要开始分页了。分页参数是这两个。
		return BeanUtil.toPagedResult(vulCategoryMapperCustom.listForPage(search));
	}

	/**
	 * 新增记录
	 */
	@Override
	public int insert(VulCategoryCustom vulCategoryCustom) throws Exception {
		vulCategoryCustom.setCreateTime(new Date());
		return vulCategoryMapperCustom.insert(vulCategoryCustom);
	}

	/*
	 * 查看详情
	 */
	@Override
	public VulCategoryCustom selectById(Integer id) throws Exception {
		VulCategory vulCategory = vulCategoryMapper.selectByPrimaryKey(id);
		VulCategoryCustom vulCategoryCustom = null;
		vulCategoryCustom = new VulCategoryCustom();
		ConvertUtils.register(new DateConverter(null), java.util.Date.class); 
		BeanUtils.copyProperties(vulCategoryCustom,vulCategory);
		return vulCategoryCustom;
	}

	/* 
	 * 修改
	 */
	@Override
	public int update(VulCategoryCustom vulCategoryCustom, Integer id) throws Exception {
		vulCategoryCustom.setId(id);
		return vulCategoryMapperCustom.update(vulCategoryCustom);
	}

	/* (non-Javadoc)
	 * @see cn.mybase.ssm.service.VulCategoryService#delete(java.lang.Integer)
	 */
	@Override
	public int delete(Integer id) throws Exception {
		return vulCategoryMapper.deleteByPrimaryKey(id);
	}

}
