<%@page import="com.kh.spring.member.model.vo.Member"%>
<%@page import="org.springframework.security.core.Authentication"%>
<%@page
	import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@page
	import="org.springframework.security.core.context.SecurityContext"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- taglib은 공유되지 않으니 jsp마다 작성할 것 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%
SecurityContext securityContext = SecurityContextHolder.getContext();
Authentication authentication = securityContext.getAuthentication();
Member loginMember = (Member) authentication.getPrincipal();
pageContext.setAttribute("loginMember", loginMember);
%>
<!-- 한글 깨지지 않게 처리 -->
<fmt:requestEncoding value="utf-8" />
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param value="티켓나눔터" name="title" />
</jsp:include>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/summernote/summernote-lite.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/resources/css/common/header.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/resources/css/common/nav.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/resources/css/question/questionUpdate.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/resources/css/common/footer.css" />

<!-- 메뉴 아래 nav? 영역입니다. nav 메뉴 가지고 있는 페이지는 전부 복사해주세요. -->
<div id="snb">
	<div class="container-xl">
		<ul class="list-inline snb_ul" id="snbul1">
			<li class="on_"><a
				href="${pageContext.request.contextPath }/notice/noticeList.do"
				target="_top">공지사항</a></li>
			<li class="on_"><a href="${pageContext.request.contextPath }/review/reviewList.do" target="_top">네티즌리뷰</a></li>
			<li class="on_"><a
				href="${pageContext.request.contextPath}/sharing/boardList.do"
				target="_top">티켓나눔터</a></li>
			<li class="on_"><a href="${pageContext.request.contextPath}/question/faq.do" target="_top">자주찾는 질문</a></li>
			<li class="on_"><a href="${pageContext.request.contextPath}/question/questionList.do" target="_top">1:1 문의</a></li>
		</ul>
	</div>
</div>
<!-- 여기까지 nav 입니다. -->
<script src="${pageContext.request.contextPath }/resources/js/summernote/summernote-lite.js"></script>
<script src="${pageContext.request.contextPath }/resources/js/summernote/lang/summernote-ko-KR.js"></script>

<script>

/* textarea에도 required 속성을 적용 가능하지만, 공백이 입력된 경우 대비 유효성검사를 실시함. */
function boardValidate(){
   var $content = $("[name=content]");
   if(/^(.|\n)+$/.test($content.val()) == false){
      alert("내용을 입력하세요");
      return false;
   }
   return true;
}

/* 첨부파일 선택 시 파일명 보이게  */
$(() => {
	$("[name=upFile]").change((e) => {
		// 1. 파일명 가져오기
		const file = $(e.target).prop("files")[0];
		const filename = file?.name;   
		console.log(e.target);
		console.log(filename);
		
		// 2. label에 설정하기
		const $label = $(e.target).next();  
		if(file != undefined)
			$label.html(filename);
		else
			$label.html("파일을 선택하세요.");
	

		const $file = $(e.target);
		const no = $file.data("no");
		if($file.val() != "") {
			// 파일을 새로 선택한 경우
			console.log($(`#delFile\${no}`));
			$(`#delFile\${no}`)
				.prop("checked", true)
				.hide()
				.next()
				.hide();
		}
		else {
			// 파일선택을 취소한 경우
			console.log($(`#delFile\${no}`));
			$(`#delFile\${no}`)
				.prop("checked", false)
				.show()
				.next()
				.show();
		}	

	});	
});



</script>

<!-- 해당 페이지 큰 글씨 -->
<div class="sub_title_wrap">
	<div class="container">
		<h2 class="en">티켓나눔터</h2>
	</div>
</div> 
<!-- 여기까지 해당 페이지 큰 글씨입니다. -->

	<%-- <div class="bd_header">
		<h2 class="bd_title">
			<img src="${pageContext.request.contextPath}/resources/upload/board/리뷰게시판 타이틀 로고.png" alt="" />
			<a href="${pageContext.request.contextPath}/question/questionList.do">1:1 문의</a>
		</h2>
	</div> --%>

<div id="board-container-out">

<div id="board-container">
	
	<div id="boardboardboard">

	<form name="boardFrm"
		action="${pageContext.request.contextPath}/sharing/boardUpdate.do?${_csrf.parameterName}=${_csrf.token}"
		method="POST" enctype="multipart/form-data"
		onsubmit="return boardValidate();">
		
		<input type="hidden" name="no" value="${board.no}" /> 
		<div class="input-group mb-3">
		  <div class="input-group-prepend">
		    <label class="input-group-text" for="inputGroupSelect01">종류</label>
		  </div>
		
		  <select class="custom-select" id="inputGroupSelect01" name="category" >
		    <option value="${board.category}" selected>${board.category == 'P' ? '티켓구함' : (board.category == 'S' ? '티켓양도' : '티켓교환') }</option>		    
		    <option value="P">티켓구함</option>
		    <option value="S">티켓양도</option>
		    <option value="R">거래완료</option>
		  </select>
		</div>
		<input type="text" class="form-control"
			name="title" id="title" value="${board.title}" required>
		<input style="margin: 13px 0;" type="text" class="form-control" name="memberId" value="${loginMember.id}" required readonly> 

		<c:forEach items="${board.attachments}" var="attach" varStatus="vs">
			<c:if test="${attach.attachNo ne 0 }">
			<div class="input-group mb-3" style="padding:0px;">
				<div class="input-group-prepend" style="padding:0px;">
				  <span class="input-group-text">첨부파일${vs.count }</span>
				</div>
				<div class="custom-file">
				  <input type="file" class="custom-file-input" name="upFile" id="upFile${vs.count }" data-no="${vs.count }" multiple>
				  <label class="custom-file-label" for="upFile1">${board.attachments[0].originalFilename}</label>
				</div>
		   		<div class="delFileArea" style="margin-left: 12px;">
				  <input type="checkbox" name="delFile" id="delFile" value="${attach.attachNo}" style="position: relative; top: 11px;"/>
				  <label for="delFile" style="position: relative; top: 11px;">기존파일삭제</label>
			    </div>
				</div>
			</c:if>
		</c:forEach>

			<c:if test="${board.attachments[0].attachNo == 0}">
			<div class="input-group mb-3" style="padding:0px;">
			  <div class="input-group-prepend" style="padding:0px;">
			    <span class="input-group-text">첨부파일</span>
			  </div>
			  <div class="custom-file">
			    <input type="file" class="custom-file-input" name="upFile" id="upFile1" multiple>
			    <label class="custom-file-label" for="upFile1">파일을 선택하세요</label>
			  </div>
			</div>
		</c:if>

		<textarea class="form-control" id="summernote" name="content" required>${board.content}</textarea>

		<br /> 
		<input type="button" value="취소" onclick="history.go(-1);"class="cancelBtn" /> 
		<input type="submit" class="btn btn-outline-success" value="저장">
	</form>
	</div>
</div>

</div>

<script>
$(document).ready(function() {
	//여기 아래 부분
	$('#summernote').summernote({
		  height: 300,                 // 에디터 높이
		  minHeight: null,             // 최소 높이
		  maxHeight: null,             // 최대 높이
		  focus: true,                  // 에디터 로딩후 포커스를 맞출지 여부
		  lang: "ko-KR",					// 한글 설정
		  placeholder: '최대 2048자까지 쓸 수 있습니다'	//placeholder 설정
          
	});
});
</script>

<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>

