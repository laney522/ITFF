﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param value="게시판" name="title"/>
</jsp:include>
<style>
/*글쓰기버튼*/
input#btn-add{float:right; margin: 0 0 15px;}
</style>
<script>
function goBoardForm(){
	location.href = "${pageContext.request.contextPath}/board/boardForm.do";
}

$(() => {
	/**
	 * event bubbling 기반 핸들링
	 * tr 핸들링 -> td 발생 및 전파
	 */
	$("tr[data-no]").click((e) => {
		// console.log(e.target);
		// console.log($(e.target).data("no"));
		const $tr = $(e.target).parents("tr");
		const no = $tr.data("no");
		location.href = `${pageContext.request.contextPath}/board/boardDetail.do?no=\${no}`;    // 마지막의 \는 그 뒤의 $가 el이 아니라고 이스케이핑 하는것
	});
	
});
</script>
<section id="board-container" class="container">
	<p>총 ${totalContent}개의 게시물이 있습니다.</p>
	<input type="button" value="글쓰기" id="btn-add" class="btn btn-outline-success" onclick="goBoardForm();"/>
	<table id="tbl-board" class="table table-striped table-hover">
		<tr>
			<th class="text-center">번호</th>
			<th class="text-center">제목</th>
			<th class="text-center">작성자</th>
			<th class="text-center">작성일</th>
			<th class="text-center">첨부파일</th> <!-- 첨부파일 있을 경우, /resources/images/file.png 표시 width: 16px-->
			<th class="text-center">조회수</th>
		</tr>
		
		<c:forEach items="${list}" var="board">
			<tr data-no="${board.no}">
				<td>${board.no}</td>
				<td>${board.title}</td>
				<td>${board.memberId}</td>
				<td><fmt:formatDate value="${board.regDate}" pattern="yyyy-MM-dd HH:mm"></fmt:formatDate></td>
				<td class="text-center">
					<c:if test="${board.attachCount gt 0}">
						<img src="${pageContext.request.contextPath}/resources/images/file.png" alt="" width="16px" />
					</c:if> 
				</td>
				<td>${board.readCount}</td>
			</tr>
		</c:forEach>
		
	</table>
	
	${pagebar}
	
</section> 

<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
