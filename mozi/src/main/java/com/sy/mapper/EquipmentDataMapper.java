package com.sy.mapper;

import com.sy.pojo.EquipmentData;
import com.sy.pojo.EquipmentDataExample;
import com.sy.pojo.User;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface EquipmentDataMapper {
    int countByExample(EquipmentDataExample example);

    int deleteByExample(EquipmentDataExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EquipmentData record);

    int insertSelective(EquipmentData record);

    List<EquipmentData> selectByExample(EquipmentDataExample example);

    EquipmentData selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EquipmentData record, @Param("example") EquipmentDataExample example);

    int updateByExample(@Param("record") EquipmentData record, @Param("example") EquipmentDataExample example);

    int updateByPrimaryKeySelective(EquipmentData record);

    int updateByPrimaryKey(EquipmentData record);
    
    public Integer getcount(String keyWord);

	public List<EquipmentData> list(Map map);

}