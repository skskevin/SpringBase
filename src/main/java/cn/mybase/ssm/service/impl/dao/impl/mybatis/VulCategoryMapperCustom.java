package cn.mybase.ssm.service.impl.dao.impl.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.mybase.ssm.bean.entity.VulCategoryCustom;
import cn.mybase.ssm.util.base.MyBatis;

@MyBatis
public interface VulCategoryMapperCustom {
	 public List<VulCategoryCustom> listForPage(@Param("search") String search) throws Exception;
	 public int insert(VulCategoryCustom vulCategoryCustom);
	 public int update(VulCategoryCustom vulCategoryCustom);
}