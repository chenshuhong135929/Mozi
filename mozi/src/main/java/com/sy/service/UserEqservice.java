package com.sy.service;

import com.sy.pojo.User;

public interface UserEqservice {

	/**根据设备（eq_id）id获取监护者
	 * @param id
	 * @return
	 */
	public User getuserimei0(Integer id);
	
	/**根据imei获取使用者id
	 * @param imei
	 * @return
	 */
	public Integer getimei(String imei);
	
	/**根据用户id获取用户的设备id号
	 * @param userid
	 * @return
	 */
	public Integer  geteqid(Integer userid);
	
	
	
}
