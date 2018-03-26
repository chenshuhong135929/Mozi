package com.sy.service;

import com.sy.pojo.Equipment;
import com.sy.pojo.EquipmentData;
import com.sy.utils.PageModel;
import com.sy.vo.EquipmentDataimei;

public interface EquipmentDataservice {
	
	/**硬件注册或跟新数据
	 * @param eqStatus
	 * @param eqtype
	 * @return
	 */
	public Equipment equipmentstatus(String eqStatus,String eqtype,String imei);
	
	/**
	 * 根据imei获取设备
	 * @param imei
	 * @return
	 */
	public Equipment getimeiid(String imei );
	
	
	/**添加设备数据
	 * @param e
	 * @return
	 */
	public boolean addEquipmentData(EquipmentData e);
	
	/**分页获取数据
	 * @param pageNo
	 * @param keyword
	 * @return
	 */
	public PageModel<EquipmentDataimei>  getusersone(Integer pageNo,String keyword);
	

}
