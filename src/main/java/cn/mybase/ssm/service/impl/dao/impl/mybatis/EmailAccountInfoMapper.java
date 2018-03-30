package cn.mybase.ssm.service.impl.dao.impl.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.mybase.ssm.bean.entity.EmailAccountInfo;
import cn.mybase.ssm.bean.entity.EmailAccountInfoExample;
import cn.mybase.ssm.util.base.MyBatis;

@MyBatis
public interface EmailAccountInfoMapper {
    int countByExample(EmailAccountInfoExample example);

    int deleteByExample(EmailAccountInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(EmailAccountInfo record);

    int insertSelective(EmailAccountInfo record);

    List<EmailAccountInfo> selectByExample(EmailAccountInfoExample example);

    EmailAccountInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") EmailAccountInfo record, @Param("example") EmailAccountInfoExample example);

    int updateByExample(@Param("record") EmailAccountInfo record, @Param("example") EmailAccountInfoExample example);

    int updateByPrimaryKeySelective(EmailAccountInfo record);

    int updateByPrimaryKey(EmailAccountInfo record);
}