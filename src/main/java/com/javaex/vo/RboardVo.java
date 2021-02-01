package com.javaex.vo;

public class RboardVo {
	public int no;
	public String title;
	public String content;
	public int hit;
	public String regdate;
	public int userno;
	public int groupno;
	public int orderno;
	public int depth;

	public String name;

	public RboardVo() {

	}
	
	
	
	public RboardVo(int no, String title, String content, int hit, String regdate, int userno, int groupno, int orderno,int depth, String name) {

		this.no = no;
		this.title = title;
		this.content = content;
		this.hit = hit;
		this.regdate = regdate;
		this.userno = userno;
		this.groupno = groupno;
		this.orderno = orderno;
		this.depth = depth;
		this.name = name;
	}



	public RboardVo(int no, String title, String content, int hit, String regdate, int userno, int depth) {
		this.no = no;
		this.title = title;
		this.content = content;
		this.hit = hit;
		this.regdate = regdate;
		this.userno = userno;
	}

	public RboardVo(String title, String content, int userno, int groupno) {// 글쓰기시 사용
		this.title = title;
		this.content = content;
		this.userno = userno;
		this.groupno = groupno;
	}
	/*
	 * public RboardVo(int no, String title, String name, int hit, String regdate ,
	 * int depth) { //리스트 출력시 사용 this.no = no; this.title = title; this.name = name;
	 * this.hit = hit; this.regdate = regdate; this.depth = depth; }
	 * 
	 * public RboardVo(int no, int userno, String title, String name, int hit,
	 * String regdate) { //read 출력시 사용 this.no = no; this.title = title; this.name =
	 * name; this.hit = hit; this.regdate = regdate; this.userno = userno; }
	 * 
	 * public RboardVo(int no, String title, String name, int hit, String
	 * regdate,String content ,int userno) { //read 출력시 사용 this.no = no; this.title
	 * = title; this.name = name; this.hit = hit; this.regdate = regdate;
	 * this.content = content; this.userno = userno; }
	 */

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

	public int getGroupno() {
		return groupno;
	}

	public void setGroupno(int groupno) {
		this.groupno = groupno;
	}

	public int getOrderno() {
		return orderno;
	}

	public void setOrderno(int orderno) {
		this.orderno = orderno;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

}
