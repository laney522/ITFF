<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!-- css -->
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/header.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/member/member.css" />

<!-- 한글 깨지지 않게 하는 설정-->
<fmt:requestEncoding value="utf-8" />

<!-- header -->
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param value="ITFF" name="title" />
</jsp:include>

<!-- 제목 -->
<div class="sub_title_wrap">
	<div class="container">
		<h2 class="en">LOGIN</h2>
	</div>
</div>
	
<!-- 메인 콘텐츠 섹션 -->
<section class="main-content">

	
<div class="input_form">
	<form:form action="${pageContext.request.contextPath}/member/memberLogin.do" method="post">
	  <div class="mb-3">
	    <input type="text" name="id" class="form-control" id="exampleInputId" aria-describedby="emailHelp">
	  </div>
	  <div class="mb-3">
	    <input type="password" name="password" class="form-control" id="exampleInputPassword">
	  </div>
<!-- 	  
	  <div class="mb-3 form-check">
	    <input type="checkbox" class="form-check-input" id="exampleCheck1">
	    <label class="form-check-label" for="exampleCheck1">아이디 저장</label>
	  </div>
-->	  
	<div>
		<input type="checkbox" name="remember-me" id="remember-me" class="form-check-input" />   <!-- 부트스트랩에 있는 클래스 -->
		<label for="remember-me" class="form-check-label">Remember me</label>
	</div>
	 <button type="submit" class="btn btn-primary">로그인</button>
	</form:form>
</div>

<br/>
<br/>

<div>
	<ul>
		<li><a href="${pageContext.request.contextPath}/member/memberSearch.do">아이디/비밀번호 찾기></a></li>
		<li><a href="${pageContext.request.contextPath}/member/memberEnroll.do">회원가입></a></li>
	</ul>
</div>

<br/>
<br/>

<p>
아이디가 없으신 분은 회원가입 후 이용하실 수 있습니다. <br>
아이디/비밀번호를 분실하신 경우, 아이디/비밀번호 찾기를 이용해 주시기 바랍니다. <br> 
공용 PC를 이용하시는 경우 보안을 위하여 반드시 로그아웃 해주시기 바랍니다.
</p>

</section>

<!-- footer -->
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>