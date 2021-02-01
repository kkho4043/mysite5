package com.javaex.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.RboardDao;
import com.javaex.vo.RboardVo;
import com.javaex.vo.UserVo;

@Service("/RboardService")
public class RboardService {

	@Autowired
	RboardDao rboardDao;
	
	//게시판 리스트
	public List<RboardVo> getRboardList() {
		List<RboardVo> rboardList = rboardDao.getRboardList();
		return rboardList;
	}
	
	//글쓰기 및 댓글 달기
	public void Rboardwrite(RboardVo rboardVo,HttpSession session) {
		UserVo userVo = (UserVo)session.getAttribute("authUser");
		
		rboardVo.setUserno(userVo.getNo());
		
		if(rboardVo.getGroupno() == 0) { //rboardVo 에 no로 넣어줘서 0이면 그냥 글 0이아니면 댓글.
			rboardDao.rboardwrite(rboardVo);
		}else {//댓글 일때에 다오
			rboardDao.rboardorderplus(rboardVo);//댓글을 달때에 order값을 1씩 더해준다(아래댓글) order값이 클때.
			rboardDao.rboardcommnet(rboardVo);
		}
		
	}
	//읽기시 값 가져오기
	public RboardVo getRboard(int no) {
		rboardDao.hit(no);
		return rboardDao.getRboard(no);
	}
	
	//업데이트시 값 가져오기
	public RboardVo getreadRboard(int no) {
		return rboardDao.getRboard(no);
	}
	//수정
	public void Rboardmodify(RboardVo rboardVo) {
		rboardDao.RboardUpedate(rboardVo);
	}
	
	public void Rboarddelete(int no) {
		rboardDao.Rboarddelete(no);
	}
	
	public List<RboardVo> getRboardsearch(String str) {
		return rboardDao.getRboardsearch(str);
	}
	
	

}
