package com.sy.mapper;

import com.sy.pojo.Eqsms;
import com.sy.pojo.EqsmsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EqsmsMapper {
    int countByExample(EqsmsExample example);

    int deleteByExample(EqsmsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Eqsms record);

    int insertSelective(Eqsms record);

    List<Eqsms> selectByExample(EqsmsExample example);

    Eqsms selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Eqsms record, @Param("example") EqsmsExample example);

    int updateByExample(@Param("record") Eqsms record, @Param("example") EqsmsExample example);

    int updateByPrimaryKeySelective(Eqsms record);

    int updateByPrimaryKey(Eqsms record);
}