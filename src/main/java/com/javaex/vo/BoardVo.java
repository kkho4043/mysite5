package com.javaex.vo;

public class BoardVo {
	public int no;
	public String title;
	public String content;
	public int hit;
	public String regdate;
	public int userno;
	
	public String name;
	
	public BoardVo() {
		
	}

	public BoardVo(int no, String title, String content, int hit, String regdate, int userno) {
		this.no = no;
		this.title = title;
		this.content = content;
		this.hit = hit;
		this.regdate = regdate;
		this.userno = userno;
	}
	
	public BoardVo(String title, String content, int userno) {// 글쓰기시 사용
		this.title = title;
		this.content = content;
		this.userno = userno;
	}
	
	public BoardVo(int no, String title, String name, int hit, String regdate) { //리스트 출력시 사용
		this.no = no;
		this.title = title;
		this.name = name;
		this.hit = hit;
		this.regdate = regdate;
	}
	
	public BoardVo(int no, int userno, String title, String name, int hit, String regdate) { //read 출력시 사용
		this.no = no;
		this.title = title;
		this.name = name;
		this.hit = hit;
		this.regdate = regdate;
		this.userno = userno;
	}
	
	public BoardVo(int no, String title, String name, int hit, String regdate,String content ,int userno) { //read 출력시 사용
		this.no = no;
		this.title = title;
		this.name = name;
		this.hit = hit;
		this.regdate = regdate;
		this.content = content;
		this.userno = userno;
	}

	
	
	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public int getUserno() {
		return userno;
	}

	public void setUserno(int userno) {
		this.userno = userno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
	
	
}
