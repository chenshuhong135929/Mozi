package com.sy.service;

import com.sy.pojo.Equipment;

public interface Equipmentservice {

	/**更新设备基本数据
	 * @param e
	 * @return
	 */
	public boolean updateEquipment(Equipment e);
	
	/**根据id获取设备数据
	 * @param id
	 * @return
	 */
	public Equipment selectequipment(Integer id);
	
	/**根据imei获取数据
	 * @param imei
	 * @return
	 */
	public Equipment selectquipmentimei(String imei);
}
