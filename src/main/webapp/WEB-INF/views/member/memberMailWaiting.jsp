<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<fmt:requestEncoding value="utf-8"/>

<script src="http://code.jquery.com/jquery-latest.min.js"></script>

	<h1>메일이 전송되었습니다. 받으신 인증키를 입력해주세요.</h1>
	<div>남은시간 : <div id="demo"></div>
	
		<%-- <input type="text" value="${authKey}" /> --%>
		<input type="text" id="authKey" name="authKey" placeholder="인증키 입력..."/>	
		<input type="button" onclick="checkCode();" value="제출" />
	</div>
	<%-- <form
		name="memberEmailCheckFrm"
		action="${pageContext.request.contextPath}/" 
		class="chk_email" 
		method="GET">
		<input type="text" value="${authKey }" />
		<input type="text" id="authKey" name="authKey" placeholder="인증키를 넣어주세요"/>	
		<input type="button" id="re-authkey" name="re-authkey" value="재전송"/>
		<input type="submit" placeholder="제출" />
	</form> --%>

<script>
var time = 600; //기준시간 작성
var min = ""; //분
var sec = ""; //초

//setInterval(함수, 시간) : 주기적인 실행
var time = 600; //기준시간 작성
	var min = ""; //분
	var sec = ""; //초

	//setInterval(함수, 시간) : 주기적인 실행
	var x = setInterval(function() {
		//parseInt() : 정수를 반환
		min = parseInt(time/60); //몫을 계산
		sec = time%60; //나머지를 계산

		document.getElementById("demo").innerHTML = min + "분" + sec + "초";
		time--;

		//타임아웃 시
		if (time < 0) {
			clearInterval(x); //setInterval() 실행을 끝냄
			document.getElementById("demo").innerHTML = "시간초과";
		}
	}, 1000);
 
// 폼 제출시 검사	
/* $("[name=memberEmailCheckFrm]").submit(function(){
	
	
	
	console.log($(authKey).val());
	
	if($(authKey).val() != "${authKey}") {
	
		
		alert("인증번호가 틀립니다. 다시입력해주세요.");
		$(authKey).focus();
		return false;
	}
	return true;
}); */

const checkCode = () => {
	
	if($(authKey).val() != "${authKey}") {
		
		alert("인증번호가 틀립니다. 다시입력해주세요.");
		$(authKey).focus();
		return false;
	}
	
	$("#emailValid", opener.document).val(1);
	close();
};

</script>
		