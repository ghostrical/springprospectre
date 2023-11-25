<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  <script type="text/javascript">
  
  	$(document).ready(function(){
  		if(${!empty msgType}){
  				$("messageType").attr("class", "modal-content panel-warning");
  			$("#myMessage").modal("show");
  		}
  	});
  
  	function registerCheck(){
  		var memID=$("#memID").val();
  		$.ajax({
  			url: "${contextPath}/memRegisterCheck.do",
  			type: "get",
  			data: {"memID": memID},
  			success: function(result){
  				if(result==1){
  					$("#checkMessage").html("사용할 수 있는 아이디다.");
  					$("#checkType").attr("class","modal-content panel-success");
  				}
  				else {
  					$("#checkMessage").html("사용할 수 없는 아이디다.");
  					$("#checkType").attr("class","modal-content panel-success");
  				}
  				$("#myModal").modal("show");
  			},
  			error : function(){ alert("error");  }
  			
  		})
  	}
  	
  	function passwordCheck(){
  		var memPassword1 = $("#memPassword1").val();
  		var memPassword2 = $("#memPassword2").val();
  		if(memPassword1 != memPassword2){
  			$("#passMessage").html("비밀 불일치");
  		}
  		else {
  			$("#passMessage").html("");
  			$("#memPassword").val(memPassword1);
  		}
  	}
  	function goInsert(){
  		var memAge=$("#memAge").val();
  		if(memAge==null || memAge==""  || memAge==0){
  			alert("나이 입력");
  			return false;
  		}
  		document.frm.submit();
  		
  	}
  </script>
</head>
<body>
 

<div class="container">
 <jsp:include page="../common/header.jsp"/>
  <h2>spring mvc033</h2>
  <div class="panel panel-default">
    <div class="panel-heading">회원갑입</div>
    <div class="panel-body">

		<form name="frm" action="${contextPath }/memRegister.do" method="post">
		<input type="hidden" id = "memPassword" name="memPassword" value="" />
			<table class="table table-boardered" style="text-align: center; border: 1px solid #dddddd;">
				<tr>
					<td style="width: 110px; vertical-align: middle;">아이디
					</td>
					<td colspan="2"><input id="memID" name="memID" class="form-control" type="text" maxlength="20" placeholder="아이디를 입력"/>
					</td>
					<td style="width: 110px;"><button type="button" class="btn btn-primary btn-sm" onclick="registerCheck()">중복체크</button>
					</td>
					</tr>
				<tr>
					<td style="width: 110px; vertical-align: middle;">비밀번호
					</td>
					<td colspan="2"><input id="memPassword1" name="memPassword1" onkeyup="passwordCheck()" class="form-control" type="password" maxlength="20" placeholder="paaword를 입력"/>
					</td>
				</tr>					
				<tr>
					<td style="width: 110px; vertical-align: middle;">비밀번호확인
					</td>
					<td colspan="2"><input id="memPassword2" name="memPassword2" onkeyup="passwordCheck()" class="form-control" type="password" maxlength="20" placeholder="paaword확인를 입력"/>
					</td>
				</tr>		
				<tr>
					<td style="width: 110px; vertical-align: middle;">사용자이름
					</td>
					<td colspan="2"><input id="memName" name="memName" class="form-control" type="text" maxlength="20" placeholder="이름를 입력"/>
					</td>
				</tr>	
				<tr>
					<td style="width: 110px; vertical-align: middle;">나이
					</td>
					<td colspan="2"><input id="memAge" name="memAge" class="form-control" type="number" maxlength="20" placeholder="나이를 입력" />
					</td>
				</tr>		
				<tr>
					<td style="width: 110px; vertical-align: middle;">성별
					</td>
					<td colspan="2">
						<div class="form-group" style="text-align: center; margin: 0 auto;">
							<div class="btn-group" data-toggle="buttons">
								<label class="btn btn-primary active">
									<input type="radio" id="memGender" name="memGender" autocomplete="off" value="남자" checked/>남자
								</label>
								<label class="btn btn-primary">
									<input type="radio" id="memGender" name="memGender" autocomplete="off" value="여자" />
									여자
								</label>
							</div>
						</div>	
					</td>
				</tr>		
				<tr>
					<td style="width: 110px; vertical-align: middle;">이메일
					</td>
					<td colspan="3"><input id="memEmail" name="memEmail" class="form-control" type="text" maxlength="20" placeholder="나이메일를 입력"/>
					</td>
				</tr>	
				<tr>
					<td colspan="3" style="text-align: left;">
					<span id="passMessage" style="color:red"> </span>	<input type="button" class="btn btn-primary btn-sm pull-right" value="등록" onclick="goInsert()" /> 
					</td>
				</tr>																
			</table>
		</form>
	</div>
	
		<!-- Modal -->
	<div id="myModal" class="modal fade" role="dialog">
	  <div class="modal-dialog">
	
	    <!-- Modal content-->
	    <div id="checkType" class="modal-content panel-info">
	      <div class="modal-header panel-heading">
	        <button type="button" class="close" data-dismiss="modal">&times;</button>
	        <h4 class="modal-title">메시지 확인</h4>
	      </div>
	      <div class="modal-body">
	        <p id="checkMessage"></p>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	      </div>
	    </div>
	
	  </div>
	</div>
	
	<div id="myMessage" class="modal fade" role="dialog">
	  <div class="modal-dialog">
	
	    <!-- Modal content-->
	    <div  id = "messageType" class="modal-content panel-info">
	      <div class="modal-header panel-heading">
	        <button type="button" class="close" data-dismiss="modal">&times;</button>
	        <h4 class="modal-title">${msgType }</h4>
	      </div>
	      <div class="modal-body">
	        <p>${msg }</p>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	      </div>
	    </div>
	
	  </div>
	</div>
	
    <div class="panel-footer">나다</div>    
  </div>
</div>

</body>
</html>
