package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestVo;


@Repository
public class GuestDao {
	
	@Autowired
	private SqlSession sqlSession;

//guestlist============
	public List<GuestVo> getguestList() {
		return sqlSession.selectList("guest.selectList");
	}
	
	public void guestInsert(GuestVo guestVo) {
		sqlSession.insert("guest.insert",guestVo);
	}
	
	public String getPassword(int no) {
		return sqlSession.selectOne("guest.selectpwd",no);
	}
	
	public void guestdelete(int no) {
		sqlSession.delete("guest.delete",no);
	}
	
	
	//글저장(selectkey)
	public GuestVo guestInsertSelectKey(GuestVo guestVo) {
		System.out.println(guestVo.toString());
		sqlSession.insert("guest.insertSelectKey",guestVo);
		System.out.println(guestVo.toString());
		return guestVo;
		
	}
	
	public GuestVo selectOne(int no) {
		return sqlSession.selectOne("guest.select",no);
	}

}
