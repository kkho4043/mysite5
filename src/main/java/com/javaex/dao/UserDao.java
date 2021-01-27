package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {
	@Autowired
	private SqlSession sqlSession;

//가입----------------------

	public void insert(UserVo userVo) {
		System.out.println("\nuserDao.insert In \n : " + userVo.toString());
		sqlSession.insert("user.insert", userVo);
		System.out.println("\nuserDao.insert OK \n: " + userVo.toString());
	}

	// 로그인----->회원 정보 가져오기-----------------
	public UserVo selectUser(UserVo userVo) {
		System.out.println("\nuserDao.selectUser IN: \n" + userVo.toString());
		UserVo vo = sqlSession.selectOne("user.selectUser", userVo);
		System.out.println("\nuserDao.selectUser OUT \n: " + vo);
		return vo;

	}

	// 수정----->회원 정보 가져오기-----------------
	public UserVo selectOne(UserVo userVo) {
		System.out.println("\nuserDao.seleceOne IN : \n" + userVo.toString());
		UserVo vo = sqlSession.selectOne("user.selectOne", userVo);
		System.out.println("\nuserDao.selectOne out : \n" + vo.toString());
		return vo;
	}
	// 수정----->
		public void update(UserVo userVo) {
			System.out.println("\nuserDao.update IN: \n" + userVo.toString());
			sqlSession.update("user.update",userVo);
			System.out.println("\nuserDao.update OK: \n" + userVo.toString());
		}

}
