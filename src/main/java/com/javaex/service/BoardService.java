package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	// 게시판 리스트
	public List<BoardVo> getBoardList() {
		List<BoardVo> boardList = boardDao.getBoardList();
		return boardList;
	}

	public void Boardwrite(BoardVo boardVo, HttpSession session) {
		UserVo userVo = (UserVo) session.getAttribute("authUser");
		boardVo.setNo(userVo.getNo());

		for (int i = 1; i <= 1000; i++) {
			boardVo.setTitle(i + "번째 글입니다.");
			boardVo.setContent(i + "번째 내용 입니다.");
			boardDao.boardwrite(boardVo);
		}
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

	// 검색List2
	public List<BoardVo> getBoardList2(String keyward) {
		System.out.println(keyward);
		return boardDao.getBoardsearch(keyward);
	}

	// 검색 + 번호 List3
	public Map<String,Object> getBoardList3(String keyward, int crtPage) {
		System.out.println(keyward);
		System.out.println(crtPage);
		
		crtPage = (crtPage > 0)? crtPage : (crtPage = 1); 
		
		int listCnt = 10;
		int startNum = (crtPage - 1) * listCnt + 1;
		int endNum = (crtPage * listCnt);
		
		//전체 글 갯수
		int totalCount = boardDao.selectTotalCnt(keyward);
		
		
		//버튼번호
		int pageBtnCount = 5;
		int endPageBtnNo = (int)Math.ceil(crtPage/(double)pageBtnCount) * pageBtnCount;
		int startPageBtnNo = endPageBtnNo - (pageBtnCount - 1);
		
		//다음버튼
		boolean next;
		if(endPageBtnNo * listCnt < totalCount) {
			next = true;
		}else {
			next = false;
			endPageBtnNo = (int)Math.ceil(totalCount/(double)listCnt);
			}
		
		boolean prev;
		
		if(startPageBtnNo != 1) {
			prev = true;
		}else {
			prev = false;	
		}
		
		//prev startPageBtnNo endPageBtnNo next
		
		List<BoardVo> boardList = boardDao.getBoardList3(keyward, startNum, endNum);
		
		Map <String ,Object> pMap = new HashMap<String ,Object>();
		
		
		pMap.put("blist",boardList );
		pMap.put("prev", prev);
		pMap.put("startPageBtnNo", startPageBtnNo);
		pMap.put("endPageBtnNo", endPageBtnNo);
		pMap.put("next", next);
		
		return pMap;
	}

}
