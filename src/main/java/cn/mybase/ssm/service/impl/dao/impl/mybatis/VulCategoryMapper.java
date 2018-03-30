package cn.mybase.ssm.service.impl.dao.impl.mybatis;

import cn.mybase.ssm.bean.entity.VulCategory;
import cn.mybase.ssm.util.base.MyBatis;

@MyBatis
public interface VulCategoryMapper {

	int deleteByPrimaryKey(Integer id);

	int insert(VulCategory record);

	int insertSelective(VulCategory record);

	VulCategory selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(VulCategory record);

	int updateByPrimaryKey(VulCategory record);
}