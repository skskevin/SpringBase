package cn.mybase.ssm.VulCategory.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import cn.mybase.ssm.VulCategory.po.VulCategory;
import cn.mybase.ssm.VulCategory.po.VulCategoryExample;

public interface VulCategoryMapper {
    long countByExample(VulCategoryExample example);

    int deleteByExample(VulCategoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(VulCategory record);

    int insertSelective(VulCategory record);

    List<VulCategory> selectByExample(VulCategoryExample example);

    VulCategory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") VulCategory record, @Param("example") VulCategoryExample example);

    int updateByExample(@Param("record") VulCategory record, @Param("example") VulCategoryExample example);

    int updateByPrimaryKeySelective(VulCategory record);

    int updateByPrimaryKey(VulCategory record);
}