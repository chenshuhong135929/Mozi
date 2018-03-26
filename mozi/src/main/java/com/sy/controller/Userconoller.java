package com.sy.controller;

import java.io.File;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.sy.common.ResultBase;
import com.sy.common.ResultData;
import com.sy.pojo.Management;
import com.sy.pojo.User;
import com.sy.service.Userservice;
import com.sy.utils.DeleteFileUtil;
import com.sy.utils.PageModel;

@Controller
@RequestMapping(value = "user", method = RequestMethod.POST)
public class Userconoller {

	@Autowired
	private Userservice userservice;

	/**添加用户
	 * @param u
	 * @return
	 */
	@RequestMapping("addUser")
	@ResponseBody
	public ResultBase addUser(@RequestBody User u) {
		ResultBase re = new ResultBase();
		if (userservice.ifUser(u.getAccount())) {
			boolean status = userservice.addUser(u);

			if (status) {
				re.setMessage("添加成功！！！");
				re.setCode(200);
			} else {
				re.setMessage("添加失败！！！");
				re.setCode(400);
			}
		} else {
			re.setMessage("添加失败！！！");
			re.setCode(405);
		}

		return re;

	}

	/**用户登陆
	 * @param m
	 * @return
	 */
	@RequestMapping("landingUser")
	@ResponseBody
	public ResultData<User> landingUser(@RequestBody Map m) {
		ResultData<User> re = new ResultData<User>();
		User u = userservice.landingUser((String) m.get("account"), (String) m.get("password"));
		if (u != null) {
			re.setCode(200);
			re.setData(u);
			re.setMessage("登陆成功！！！");
		} else {
			re.setCode(305);
			re.setData(u);
			re.setMessage("登陆失败！！！");
		}
		return re;

	}

	/**上传头像
	 * @param avatar
	 * @param id
	 * @return
	 */
	@RequestMapping("uploadavatar")
	@ResponseBody
	public ResultBase uploadavatar(@RequestParam(value = "avatar", required = false) CommonsMultipartFile avatar,
			Integer id) {
		ResultBase re = new ResultBase();
		new File("E:\\Project\\avatars").mkdirs();
		com.sy.utils.StringUtil.arrayUploadFile("E:\\Project\\avatars", avatar);
		boolean status = userservice.uploadavatar("avatars/" + avatar.getOriginalFilename(), id);
		if (status) {
			re.setCode(200);
			re.setMessage("头像上传成功！！!");
		} else {
			re.setMessage("头像上传失败！！！");
			re.setCode(400);

		}

		return re;
	}

	/**更新头像
	 * @param avatar
	 * @param id
	 * @return
	 */
	@RequestMapping("updateavatar")
	@ResponseBody
	public ResultBase updateavatar(@RequestParam(value = "avatar", required = false) CommonsMultipartFile avatar,
			Integer id) {
		ResultBase re = new ResultBase();
		User u = userservice.getuserid(id);
		String[] st = u.getAvatar().split("/");
		DeleteFileUtil.deleteFile("E:/Project/" + st[3] + "/" + st[4]);
		new File("E:\\Project\\avatars").mkdirs();
		com.sy.utils.StringUtil.arrayUploadFile("E:\\Project\\avatars", avatar);
		boolean status = userservice.uploadavatar("avatars/" + avatar.getOriginalFilename(), id);
		if (status) {
			re.setCode(200);
			re.setMessage("修改头像成功！！!");
		} else {
			re.setMessage("修改头像失败！！！");
			re.setCode(400);

		}
		return re;
	}

	/**更新用户信息
	 * @param u
	 * @return
	 */
	@RequestMapping("updateUser")
	@ResponseBody
	public ResultBase updateUser(@RequestBody User u ) {
		ResultBase re = new ResultBase();
		boolean status = userservice.updateUser(u);
		if (status) {
			re.setCode(200);
			re.setMessage("修改成功！！!");
		} else {
			re.setMessage("修改失败！！！");
			re.setCode(400);

		}
		return re;
	}
	/**更新密码
	 * @param m
	 * @return
	 */
	@RequestMapping("updatepassword")
	@ResponseBody
	public ResultBase updatepassword(@RequestBody Map m ){
		ResultBase re = new ResultBase();
		boolean status = userservice.updatepassword((String)m.get("password"),(String)m.get("newpassword"),Integer.parseInt((String)m.get("id")));
		if (status) {
			re.setCode(200);
			re.setMessage("修改成功！！!");
		} else {
			re.setMessage("修改失败！！！");
			re.setCode(400);

		}
		
		return re;
	}
	
	@RequestMapping(value = "list")
	public ModelAndView list(Integer pageNo, String keyword) {
		ModelAndView mo = new ModelAndView();
		PageModel<User> pagemodel = userservice.getusersone(pageNo,
				keyword);
		mo.setViewName("user");
		mo.addObject("pagemodel", pagemodel);
		return mo;
	}
}
