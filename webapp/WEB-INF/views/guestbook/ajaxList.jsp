<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>




<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/guestbook.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.js"></script>

</head>

<body>
	<div id="wrap">

		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
		<!-- //header -->
		<!-- //nav -->

		<c:import url="/WEB-INF/views/include/aside.jsp"></c:import>
		<!-- //aside -->

		<div id="content">

			<div id="content-head">
				<h3>ajax방명록</h3>
				<div id="location">
					<ul>
						<li>홈</li>
						<li>방명록</li>
						<li class="last">ajax방명록</li>
					</ul>
				</div>
				<div class="clear"></div>
			</div>
			<!-- //content-head -->

			<div id="guestbook">
				<form action="" method="">
					<table id="guestAdd">
						<colgroup>
							<col style="width: 70px;">
							<col>
							<col style="width: 70px;">
							<col>
						</colgroup>
						<tbody>
							<tr>
								<th><label class="form-text" for="input-uname">이름</label>
								<td><input id="input-uname" type="text" name="name"></td>
								<th><label class="form-text" for="input-pass">패스워드</label>
								<td><input id="input-pass" type="password" name="password"></td>
							</tr>
							<tr>
								<td colspan="4"><textarea name="content" cols="72" rows="5"></textarea></td>
							</tr>
							<tr class="button-area">
								<td colspan="4"><button type="button" id="btnSubmit">등록</button></td>
							</tr>
						</tbody>

					</table>
					<!-- //guestWrite -->

				</form>

				<div id="gListArea">
					<!--방명록 리스트 -->
				</div>
			</div>
			<!-- //guestbook -->
		</div>
		<!-- //content  -->
		<div class="clear"></div>

		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		<!-- //footer -->


		<!-- //wrap -->
		<!--모달창  -->
		<div class="modal fade" id="delModal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title">Modal title</h4>
					</div>
					<div class="modal-body">
						<label>비밀번호</label> 
						<input type="text" id = "modalPassword" name="" value="">
						<input type="text" id = "modalNo" name="" value="">
						<p>One fine body&hellip;</p>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
						<button id ="modaldel" type="button" class="btn btn-primary" >삭제</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
		<!-- /.modal -->
	</div>


</body>

<script type="text/javascript">
	//리스트 출럭 
	$("#documet").ready(function() {
		fetchList();
	});
	
	
	//삭제버튼 클릭.비밀번호 입력창 호출
	$("#gListArea").on("click", "a", function() {
		event.preventDefault();
			
		
		$("#delModal").modal();
		$("#modalPassword").val("");
		
		
		var no = $(this).data("no");
		$("#modalNo").val(no);
		
		
		console.log("삭제");
	});
	
	$("#modaldel").on("click", function() {
		var password = $("#modalPassword").val();
		var no = $("#modalNo").val();
		
		console.log(password);
		console.log(no);
		
		$.ajax({

			url : "${pageContext.request.contextPath}/api/guest/delete",
			type : "post",
			//contentType : "application/json",
			data : {
				guestno : no,
				password : password
			},

			dataType : "json",
			success : function(count) {
				console.log(count);
				if(count == 1){
					$("#delModal").modal("hide");
					
					$("#t-"+no).remove();
				}else if(count == 0){
					alert("비밀번호가 틀렸습니다.");
					$("#modalPassword").val("");
					console.log(count);
				}
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		}); 
		
	   
	})
	
	//등록버튼
	$("#btnSubmit").on("click", function() {

		console.log("방명록 등록버튼");
		
		var guestVo = {
			name : $("[name='name']").val(),
		    password : $("[name='password']").val(),
		    content : $("[name='content']").val()
		}
		
		console.log(guestVo);

		$.ajax({

			url : "${pageContext.request.contextPath}/api/guest/write2",
			type : "post",
			contentType : "application/json",
			data : JSON.stringify(guestVo),
			dataType : "json",
			success : function(guestVo) {
				console.log(guestVo);
				render(guestVo, "down");//게스트 정보 출력
				/*성공시 처리해야될 코드 작성*/
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});

		return false;
	});

	function render(guestVo, updown) {
		var str = "";
		str += '';
		str += '<table id = "t-'+guestVo.guestno+'" class="guestRead">';
		str += '	<colgroup>';
		str += '		<col style="width: 10%;">';
		str += '		<col style="width: 40%;">';
		str += '		<col style="width: 40%;">';
		str += '		<col style="width: 10%;">';
		str += '	</colgroup>';
		str += '	<tr>';
		str += '		<td>' + guestVo.guestno + '</td>';
		str += '		<td>' + guestVo.name + '</td>';
		str += '		<td>' + guestVo.date + '</td>';
		str += '		<td><a href="" data-no="'+ guestVo.guestno +'">[삭제]</a></td>';
		str += '	</tr>';
		str += '	<tr>';
		str += '		<td colspan=4 class="text-left">' + guestVo.content + '</td>';
		str += '	</tr>';
		str += '</table>';

		if (updown == "down") {
			$("#gListArea").prepend(str);
		} else if (updown == "up") {
			$("#gListArea").append(str)
		} else {

		}

		/*{guestno: 104, name: "123", password: null, content: "123", date: "2021-02-09 16:53:17.0"}  */
	}

	function fetchList() {
		$.ajax({

			url : "${pageContext.request.contextPath }/api/guest/list",
			type : "post",
			//contentType : "application/json",
			//data : {name: ”홍길동"},

			dataType : "json",
			success : function(gbookList) {
				console.log(gbookList);

				for (var i = 0; i < gbookList.length; i++) {
					render(gbookList[i], "up");
				}
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});

	}
</script>




</html>