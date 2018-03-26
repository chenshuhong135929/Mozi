package com.sy.mapper;

import com.sy.pojo.User;
import com.sy.pojo.UserEq;
import com.sy.pojo.UserEqExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface UserEqMapper {
    int countByExample(UserEqExample example);

    int deleteByExample(UserEqExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserEq record);

    int insertSelective(UserEq record);

    List<UserEq> selectByExample(UserEqExample example);

    UserEq selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserEq record, @Param("example") UserEqExample example);

    int updateByExample(@Param("record") UserEq record, @Param("example") UserEqExample example);

    int updateByPrimaryKeySelective(UserEq record);

    int updateByPrimaryKey(UserEq record);
    
    public UserEq getuserimei0(Integer  eqId) ;
    
    public UserEq getuserimei2(Integer  eqId) ;
    
    public Integer getimei(String imei);
    
    public Integer geteqid(Integer userId);
}