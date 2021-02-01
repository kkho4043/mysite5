<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/board.css" rel="stylesheet" type="text/css">

</head>


<body>
	<div id="wrap">

		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
		<!-- //header -->
		<!-- //nav -->
		<!-- aside 옮김 -->
		<c:import url ="/WEB-INF/views/include/aside.jsp"></c:import >


		<div id="content">

			<div id="content-head">
				<h3>댓글 게시판</h3>
				<div id="location">
					<ul>
						<li>홈</li>
						<li>게시판</li>
						<li class="last">댓글게시판</li>
					</ul>
				</div>
				<div class="clear"></div>
			</div>
			<!-- //content-head -->

			<div id="board">
				<div id="list">
					<form action="${pageContext.request.contextPath}/rboard/search" method="get">
						<div class="form-group text-right">
							<c:if test="${param.action == 'search'}">
								<a href="${pageContext.request.contextPath}/rboard/list">[원본으로]</a>
							</c:if>
							${fn:length(rblist)}건 조회됨 <input type="text" name="str"> <input type="hidden" name="action" value="search">
							<button type="submit" id=btn_search>검색</button>
						</div>
					</form>
					<table>
						<thead>
							<tr>
								<th>번호</th>
								<th>제목</th>
								<th>글쓴이</th>
								<th>조회수</th>
								<th>작성일</th>
								<th>관리</th>
							</tr>
						</thead>
						<tbody>

							<c:forEach items="${rblist}" var="vo" varStatus="status">
								<tr>
									<td>${vo.no}</td>
									
									
									<td class="text-left"><c:forEach begin="1" end="${vo.depth}" step="1">&nbsp;&nbsp;&nbsp;</c:forEach><a href="${pageContext.request.contextPath}/rboard/read?no=${vo.no}">${vo.title}</a></td>
									
									
									
									<td>${vo.name}</td>
									<td>${vo.hit} // ${vo.groupno} //${vo.orderno}//${vo.depth}</td>
									<td>${vo.regdate}</td>
									<td><c:if test="${authUser.no == vo.userno}">
											<a href="${pageContext.request.contextPath}/rboard/delete?no=${vo.no}">[삭제]</a>
										</c:if></td>

								</tr>
							</c:forEach>
						</tbody>
					</table>

					<div id="paging">
						<ul>
							<li><a href="">◀</a></li>
							<li><a href="">1</a></li>
							<li><a href="">2</a></li>
							<li><a href="">3</a></li>
							<li><a href="">4</a></li>
							<li class="active"><a href="">5</a></li>
							<li><a href="">6</a></li>
							<li><a href="">7</a></li>
							<li><a href="">8</a></li>
							<li><a href="">9</a></li>
							<li><a href="">10</a></li>
							<li><a href="">▶</a></li>
						</ul>


						<div class="clear"></div>
					</div>
					<c:if test="${!empty sessionScope.authUser}">
						<a id="btn_write" href="${pageContext.request.contextPath}/rboard/writeForm?groupno=0">글쓰기</a>
					</c:if>

				</div>
				<!-- //list -->
			</div>
			<!-- //board -->
		</div>
		<!-- //content  -->
		<div class="clear"></div>

		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		<!-- //footer -->

	</div>
	<!-- //wrap -->

</body>

</html>