package cn.mybase.ssm.dao;

import cn.mybase.ssm.po.VulCategory;

public interface VulCategoryMapper {

	int deleteByPrimaryKey(Integer id);

	int insert(VulCategory record);

	int insertSelective(VulCategory record);

	VulCategory selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(VulCategory record);

	int updateByPrimaryKey(VulCategory record);
}