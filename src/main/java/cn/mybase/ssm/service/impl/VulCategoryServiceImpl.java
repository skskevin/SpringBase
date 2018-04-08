/**
 * 
 */
package cn.mybase.ssm.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;

import cn.mybase.ssm.bean.entity.VulCategory;
import cn.mybase.ssm.bean.entity.vo.VulCategoryVo;
import cn.mybase.ssm.service.VulCategoryService;
import cn.mybase.ssm.service.impl.dao.impl.mybatis.VulCategoryMapper;
import cn.mybase.ssm.service.impl.dao.impl.mybatis.VulCategoryMapperCustom;
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
@Service
public class VulCategoryServiceImpl implements VulCategoryService {

	@Autowired
	private VulCategoryMapperCustom vulCategoryMapperCustom;

	@Autowired
	private VulCategoryMapper vulCategoryMapper;

	/**
	 * 分页信息
	 */
	public PagedResult<VulCategoryVo> queryByPage(String search,
			@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(value = "length", defaultValue = "10") Integer pageSize) throws Exception {
		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = pageSize == null ? 10 : pageSize;
		PageHelper.startPage(pageNo, pageSize); // startPage是告诉拦截器说我要开始分页了。分页参数是这两个。
		List<VulCategory> result = vulCategoryMapperCustom.listForPage(search);
		ArrayList<VulCategoryVo> resultVo = new ArrayList<VulCategoryVo>();
		
		for(VulCategory vulCategory : result){
			VulCategoryVo vulCategoryVo = new VulCategoryVo(vulCategory);
			resultVo.add(vulCategoryVo);
		}
		return  BeanUtil.toPagedResult(resultVo);
	}

	/**
	 * 新增记录
	 */
	@Override
	public int insert(VulCategory VulCategory) throws Exception {
		VulCategory.setCreateTime(new Date());
		return vulCategoryMapperCustom.insert(VulCategory);
	}

	/*
	 * 查看详情
	 */
	@Override
	public VulCategoryVo selectById(Integer id) throws Exception {
		VulCategory vulCategory = vulCategoryMapper.selectByPrimaryKey(id);
		VulCategoryVo vulCategoryVo = new VulCategoryVo(vulCategory);
		
		return vulCategoryVo;
	}

	/* 
	 * 修改
	 */
	@Override
	public int update(VulCategory VulCategory, Integer id) throws Exception {
		VulCategory.setId(id);
		return vulCategoryMapperCustom.update(VulCategory);
	}

	/* (non-Javadoc)
	 * @see cn.mybase.ssm.service.VulCategoryService#delete(java.lang.Integer)
	 */
	@Override
	public int delete(Integer id) throws Exception {
		return vulCategoryMapper.deleteByPrimaryKey(id);
	}

}
