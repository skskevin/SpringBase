package cn.mybase.ssm.service.impl.dao.impl.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.mybase.ssm.bean.entity.EmailInfo;
import cn.mybase.ssm.bean.entity.EmailInfoExample;
import cn.mybase.ssm.util.base.MyBatis;

@MyBatis
public interface EmailInfoMapper {
    int countByExample(EmailInfoExample example);
    

    int deleteByExample(EmailInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(EmailInfo record);

    int insertSelective(EmailInfo record);

    List<EmailInfo> selectByExample(EmailInfoExample example);

    EmailInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") EmailInfo record, @Param("example") EmailInfoExample example);

    int updateByExample(@Param("record") EmailInfo record, @Param("example") EmailInfoExample example);

    int updateByPrimaryKeySelective(EmailInfo record);

    int updateByPrimaryKey(EmailInfo record);
}