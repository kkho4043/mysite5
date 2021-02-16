package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service("/UserService")
public class UserService {
	
	@Autowired
	UserDao userDao;
	
	public void join(UserVo userVo) {
		userDao.insert(userVo);		
	}
	
	public UserVo login(UserVo userVo) {
		return userDao.selectUser(userVo);
	}
	
	public UserVo modifyForm(UserVo userVo) {
		return userDao.selectOne(userVo);
	}
	
	public void modify(UserVo userVo) {
		userDao.update(userVo);
	}
	
	public String idcheck(String id) {
		System.out.println("service idcheck : "+id);
		UserVo userVo = userDao.selectid(id);
		
		String result = "";
		if(userVo == null) {
			//사용가능 id
			result = "can";
			
		}else {
			// 불가능 id
			result = "cant";
		}
		
		return result;
		
	}
	
	
	

}
