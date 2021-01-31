package com.javaex.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;
import com.javaex.vo.UserVo;

@Service("/BoardService")
public class BoardService {

	@Autowired
	BoardDao boardDao;
	
	//게시판 리스트
	public List<BoardVo> getBoardList() {
		List<BoardVo> boardList = boardDao.getBoardList();
		return boardList;
	}
	
	public void Boardwrite(BoardVo boardVo,HttpSession session) {
		UserVo userVo = (UserVo)session.getAttribute("authUser");
		boardVo.setNo(userVo.getNo());
		boardDao.boardwrite(boardVo);
	}
	
	public BoardVo getBoard(int no) {
		boardDao.hit(no);
		return boardDao.getBoard(no);
	}
	
	public BoardVo getreadBoard(int no) {
		return boardDao.getBoard(no);
	}
	
	public void Boardmodify(BoardVo boardVo) {
		boardDao.BoardUpedate(boardVo);
	}
	
	public void Boarddelete(int no) {
		boardDao.Boarddelete(no);
	}
	public List<BoardVo> getBoardsearch(String str) {
		return boardDao.getBoardsearch(str);
	}
	
	

}
