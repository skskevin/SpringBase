package cn.mybase.ssm.service.impl.dao.impl.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.mybase.ssm.bean.entity.VulCategory;
import cn.mybase.ssm.util.base.MyBatis;

@MyBatis
public interface VulCategoryMapperCustom {
	 public List<VulCategory> listForPage(@Param("search") String search) throws Exception;
	 public int insert(VulCategory vulCategory);
	 public int update(VulCategory vulCategory);
}