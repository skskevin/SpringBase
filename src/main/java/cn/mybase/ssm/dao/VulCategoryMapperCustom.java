package cn.mybase.ssm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.mybase.ssm.po.VulCategoryCustom;


public interface VulCategoryMapperCustom {
	 public List<VulCategoryCustom> listForPage(@Param("search") String search) throws Exception;
	 public int insert(VulCategoryCustom vulCategoryCustom);
	 public int update(VulCategoryCustom vulCategoryCustom);
}