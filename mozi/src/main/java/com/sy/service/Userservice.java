package com.sy.service;

import com.sy.pojo.User;
import com.sy.utils.PageModel;

public interface Userservice {

	/**����û�
	 * @param u
	 * @return
	 */
	public boolean addUser(User u );
	
	/**判断该用户是否存在
	 * @param account
	 * @return
	 */
	public boolean ifUser(String account);
	
	/**用户登陆
	 * @param account
	 * @param password
	 * @return
	 */
	public User landingUser(String account  ,String password );
	
	/**头像上传
	 * @param avatar
	 * @param id
	 * @return
	 */
	public boolean uploadavatar(String avatar ,Integer id);
	
	/**根据id获取用户数据
	 * @return
	 */
	public User getuserid(Integer id);
	
	/**修改用户数据
	 * @param u
	 * @return
	 */
	public boolean updateUser(User u );
	
	/**修改用戶
	 * @param password
	 * @param newpassword
	 * @return
	 */
	public boolean updatepassword(String password,String newpassword,Integer id);
	
	/**分页获取用户数据
	 * @param pageNo
	 * @param keyword
	 * @return
	 */
	public PageModel<User>  getusersone(Integer pageNo,String keyword);
	
	
}
