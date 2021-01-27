<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/main.css" rel="stylesheet" type="text/css">

</head>

<body>
	<div id="wrap">

		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
		<!-- //header -->
		<!-- //nav -->

		<div id="aside">
			<h2>회원</h2>
			<ul>
				<li>회원정보</li>
				<li>로그인</li>
				<li>회원가입</li>
			</ul>
		</div>
		<!-- //aside -->

		<div id="content">

			<div id="content-head">
				<h3>회원정보</h3>
				<div id="location">
					<ul>
						<li>홈</li>
						<li>회원</li>
						<li class="last">회원정보</li>
					</ul>
				</div>
				<div class="clear"></div>
			</div>
			<!-- //content-head -->

			<div id="user">
				<div id="modifyForm">
					<form action="modify" method="get">

						<!-- 아이디 -->
						<div class="form-group">
							<label class="form-text" for="input-uid">아이디</label> 
							<span class="text-large bold">${authUser.id}</span>
							<input type="hidden" name="id" value="${authUser.id}"> 
						</div>

						<!-- 비밀번호 -->
						<div class="form-group">
							<label class="form-text" for="input-pass">패스워드</label> 
							<input type="text" id="input-pass" name="password" value="${authUser.password}" >
						</div>

						<!-- 이름 -->
						<div class="form-group">
							<label class="form-text" for="input-name">이름</label>
							<input type="text" id="input-name" name="name" value="${authUser.name}" >
						</div>

						<!-- 성별 -->
						<div class="form-group">
							<c:choose >
								<c:when test = "${authUser.gender eq 'male'}">
									<label for="rdo-male">남</label> 
									<input type="radio" id="rdo-male" name="gender" value="male" checked = "checked" > 
									
									<label for="rdo-female">여</label> 
									<input type="radio" id="rdo-female" name="gender" value="female" > 
								</c:when>
					
								<c:otherwise> 
								
									<label for="rdo-male">남</label> 
									<input type="radio" id="rdo-male" name="gender" value="male" >
									
									<label for="rdo-female">여</label> 
									<input type="radio" id="rdo-female" name="gender" value="female"  checked = "checked" > 
																											
							 </c:otherwise>
				
							</c:choose>		

						</div>
						<input type="hidden" name="no" value="${authUser.no}"> 
						
						<!-- 버튼영역 -->
						<div class="button-area">
							<button type="submit" id="btn-submit">회원정보수정</button>
						</div>

					</form>


				</div>
				<!-- //modifyForm -->
			</div>
			<!-- //user -->
		</div>
		<!-- //content  -->
		<div class="clear"></div>

		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		<!-- //footer -->

	</div>
	<!-- //wrap -->

</body>

</html>