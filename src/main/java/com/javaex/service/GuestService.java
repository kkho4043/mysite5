package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GuestDao;
import com.javaex.vo.GuestVo;

@Service("/GuestService")
public class GuestService {

	@Autowired
	GuestDao guestDao;

	// 게스트 리스트
	public List<GuestVo> getGuestList() {

		List<GuestVo> guestList = guestDao.getguestList();
		System.out.println("guestservice guestList = " + guestList.get(0).getDate());
		return guestList;
	}

	// 게스트 저장
	public void guestInsert(GuestVo guestVo) {
		guestDao.guestInsert(guestVo);
	}

	// 삭제시 비밀번호 가져오기
	public String getpassword(int no) {
		return guestDao.getPassword(no);
	}

	public void guestdelete(int no) {
		guestDao.guestdelete(no);
	}

	// 게스트 저장 저장글 리턴
	public GuestVo guestInsertSelectKey(GuestVo guestVo) {
		
		guestDao.guestInsertSelectKey(guestVo);
		guestVo.getGuestno();
		
		GuestVo guestOne =guestDao.selectOne(guestVo.getGuestno());
		System.out.println("select One : "+guestOne);
		return guestOne;
	}
	
	
	public int apidelete(GuestVo guestVo) {
		
		String pwd2 = guestDao.getPassword(guestVo.getGuestno());
		
		if(guestVo.getPassword().equals(pwd2)) {
			guestDao.guestdelete(guestVo.getGuestno());
			return 1;
		}else {
			return 0;
		}
		
	}


}
