package com.sy.mapper;

import com.sy.pojo.Positionig;
import com.sy.pojo.PositionigExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PositionigMapper {
    int countByExample(PositionigExample example);

    int deleteByExample(PositionigExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Positionig record);

    int insertSelective(Positionig record);

    List<Positionig> selectByExample(PositionigExample example);

    Positionig selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Positionig record, @Param("example") PositionigExample example);

    int updateByExample(@Param("record") Positionig record, @Param("example") PositionigExample example);

    int updateByPrimaryKeySelective(Positionig record);

    int updateByPrimaryKey(Positionig record);
}