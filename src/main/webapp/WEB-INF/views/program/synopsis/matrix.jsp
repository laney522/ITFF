<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<fmt:requestEncoding value="utf-8" />   <!-- 이거 없으면 이 밑에 jsp: -->
<jsp:include page="/WEB-INF/views/common/header.jsp">
   <jsp:param value="프로그램" name="title"/>
</jsp:include>

<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/common/header.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/common/nav.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/program/synopsis.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/common/footer.css" />

<!-- 메뉴 아래 nav? 영역입니다. nav 메뉴 가지고 있는 페이지는 전부 복사해주세요. -->
<div id="snb">
	<div class="container-xl">
		<ul class="list-inline snb_ul" id="snbul1">
			<li class="on_">
				<a href="${pageContext.request.contextPath }/program/openClose.do" target="_top" style="font-weight: bold;">개·폐막작</a>
			</li>
			<li class="on_">
				<a href="${pageContext.request.contextPath }/program/ai.do" target="_top">인공지능</a>
			</li>
			<li class="on_">
				<a href="${pageContext.request.contextPath}/program/security.do">보안·암호학</a>
			</li>
			<li class="on_">
				<a href="${pageContext.request.contextPath}/program/deepRunning.do" target="_top">가상현실·딥 러닝</a>
			</li>
			<li class="on_">
				<a href="${pageContext.request.contextPath}/program/bigData.do" target="_top">빅 데이터·컴퓨팅</a>
			</li>
			<li class="on_">
				<a href="${pageContext.request.contextPath}/program/flatform.do" target="_top">플랫폼·사물 인터넷</a>
			</li>
			<li class="on_">
				<a href="${pageContext.request.contextPath}/program/debugging.do" target="_top">사고력·디버깅</a>
			</li>
			<li class="on_">
				<a href="${pageContext.request.contextPath}/program/programSearch.do" target="_top">#작품검색</a>
			</li>
		</ul>
	</div>
</div>
<!-- 여기까지 nav 입니다. -->

<div id="slide">
  <input type="radio" name="pos" id="pos1" checked>
  <input type="radio" name="pos" id="pos2">
  <input type="radio" name="pos" id="pos3">
  <input type="radio" name="pos" id="pos4">
  
  
  <ul>
    <li style="background-image: url(https://i.imgur.com/uhpvEm9.jpg);">
    	
		<button 
			type="button" 
			class="btn btn-primary video-btn btn_play_trailer js-video-button" 
			data-toggle="modal" 
			data-src="https://www.youtube.com/embed/CSFUIGl0BL4" 
			data-target="#myModal">
			▶
		</button>
	</li>
    <li style="background-image: url(https://i.imgur.com/dHsX70M.jpg);"></li>
    <li style="background-image: url(https://i.imgur.com/AVwSdUT.jpg); "></li>
    <li style="background-image: url(https://i.imgur.com/0TBTHgJ.jpg);"></li>
  </ul>
  <p class="pos">
 
    <label for="pos1"></label>
    <label for="pos2"></label>
    <label for="pos3"></label>
    <label for="pos4"></label>
  </p>
</div>



<div class="film_info">
   <div class="container_">
      <div class="film_info_top">
      	 <!-- <h1 class="tit_movie">매트릭스<span>The Matrix</span></h1> -->
         <p class="director_name">
            매트릭스
         </p>
         <br />
		 <span>The Matrix</span>
         <ul class="list_film_info list-inline">
            <li>미국</li>
            <li>1999</li>
            <li>136min</li>
            <li>12 +</li>
            <li>color</li>
         </ul>

         <p class="">
            <strong class="premiere en">virtual reality·deep running</strong>
         </p>
      </div>
      <div class="synopsis">
         <h4>SYNOPSIS</h4>
         <div class="desc_">
         인간의 기억마저 AI에 의해 입력되고 삭제 되는 세상.진짜보다 더 진짜 같은 가상 현실 ‘매트릭스’ 그 속에서 진정한 현실을 인식할 수 없게 재배되는 인간들. 그 ‘매트릭스’를 빠져 나오면서 AI에게 가장 위험한 인물이 된 '모피어스’는 자신과 함께 인류를 구할 마지막 영웅 ‘그’를 찾아 헤맨다. 마침내 ‘모피어스’는 낮에는 평범한 회사원으로, 밤에는 해커로 활동하는 청년 ‘네오’를 ‘그’로 지목하는데… 꿈에서 깨어난 자들, 이제 그들이 만드는 새로운 세상이 열린다. 
         </div>
      </div>

      <div id="pg_note" class="pg_note">
         <h4>PROGRAM NOTE</h4>
         <div class="desc_">
            <p>&lt;매트릭스&gt;
            는 1999년 개봉한 미국의 SF 액션 영화이다. 워쇼스키 형제가 제작, 감독하고 키아누 리브스, 로렌스 피시번, 캐리앤 모스, 휴고 위빙 및 글로리아 포스터가 출연하였으며, 매트릭스 시리즈의 첫 번째 작품이다. 인공지능 컴퓨터와 기계에 의해 인간이 양육되는 2199년의 미래세계를 배경으로 하고 있다.
			영화는 인간에 의해 감지되는 현실이 "매트릭스"라고 불리는 모의 현실(1999년의 모의 현실)인 상황에서, 인체의 열과 전기 활동을 자신의 생명 연장을 위한 에너지원으로 사용하는 인공지능 컴퓨터(sentient machines)가 인류를 지배하는 디스토피아 적 미래를 묘사한다. 컴퓨터 프로그래머인 네오(Neo)는 이러한 진실을 알게 되고, "꿈의 세계(dream world)"에서 벗어난 다른 사람들과 함께 기계에 대한 반란을 일으킨다.
			CG(computer graphic)를 다양하고 신선한 영상으로 영화계에 혁명을 일으키면서, 동시에 은유나 암시로 가득 찬 스토리로 신앙과 철학이라는 깊숙한 테마의 표현을 양립시킨 작품이며, 1999년의 아카데미상으로는 시각 효과상, 편집상, 음향상, 음향 편집상을 수상하고 와이어 액션이나 블릿 타임 등의 VFX도 화제가 되었다. 윌리엄 깁슨에서부터 일본 애니메이션에 이르기까지 여러 가지 것에 영향을 받고 특히 장 보드리야르의 철학을 기조로 했다고 워쇼스키 형제는 말했다.
			1999년 3월에 미국 현지에서 5월에 대한민국에 개봉되었다.
            </p>
         </div>
      </div>
   </div>
</div>

<div class="schedule">
	<div class="container_">
		<table class=" tbl_schedule2 wid100">
			<thead>
				<tr>
					<th class="en">Code</th>
					<th class="en">Time</th>
					<th class="en">Theater</th>
					<th class="en">Grade</th>
					<th class="en">Event</th>
				</tr><tr>
			</tr></thead>
			<tbody>
				<c:forEach var="schedule" items="${movieSchedule}" varStatus="vs" >
					<tr onclick="goBooking('${schedule.startDate}', '${schedule.movieId}');">
						<td class="code en"><span class="code_wrap">${fn:substring(schedule.movieScheduleId,9,12) }</span></td>
						<th class="time en">${schedule.startDate} | ${schedule.startTime }</th>
						<td class="theater">KH ITFF ${schedule.theaterId}관</td>
						<td class="film_tit">
							<c:if test="${ageLimit eq '12 +'}">
								<img src="https://siwff.or.kr/kor/img/cont/schedule/ico_12_x2.png" style="width:40px" alt="관람가"> 
							</c:if>
							<c:if test="${ageLimit eq '15 +'}">
								<img src="https://siwff.or.kr/kor/img/cont/schedule/ico_15_x2.png" style="width:40px" alt="관람가"> 
							</c:if>
							<c:if test="${ageLimit eq '19 +'}">
								<img src="https://siwff.or.kr/kor/img/cont/schedule/ico_18_x2.png" style="width:40px" alt="관람가"> 
							</c:if>
							<img src="https://siwff.or.kr/kor/img/cont/schedule/A.png" style="width:40px" alt="언어">&nbsp;
						</td>
					</tr>				
					</c:forEach>
			</tbody>
		</table>

	</div>
</div>


<div class="container_">
	<div class="row">
		<div class="col-md-12 col-sm-12 col-xs-12">
			<div class="director">
				<h4>Director</h4>
				<ul class="director_list">
					<li>
						<div class="col-md-3 col-sm-3 col-xs-12">
							<div class="dir_img">
								<img src="https://i.ibb.co/J3vWyYB/unnamed-1.jpg" width="175" height="180" border="0" "alt="감독사진">
							</div>
						</div>
						<div class="col-md-9 col-sm-9 col-xs-12 dir_info">
							
							<div class="dir_name"><strong class="kor">앤디 워쇼스키, 래리 워쇼스키</strong><span class="mt10 en">The Wachowskis</span></div>
							<div class="desc">
							<p style="text-align: justify; line-height: 1.8;" align="justify">
								<span style="font-family: 나눔고딕코딩, NanumGothicCoding, sans-serif;">
								1965년 출생. 미국의 영화감독이자 각본가. &lt;어쌔신&gt;(1995), &lt;바운드&gt;(1996), &lt;브이 포 벤테타&gt;(2006), &lt;인베이젼&gt;(2007)등을 각본/감독하였다.
								</span>
							</p>
							</div>
						</div>
					</li>

				</ul>
			</div> <!-- // director -->
		</div>
	</div>	
		
	<div class="row">
		<div class="col-md-12 col-sm-12 col-xs-12">
			<div class="credit">
				<h4>Credit</h4>
				<ul>
					<li>
						<strong class="en">Producer</strong>
						<span class="en">Barrie M. Osborne</span>
					</li>
					<li>
						<strong class="en">Cast</strong>
						<span class="en"> Keanu Reeves, Laurence Fishburne, Carrie-Anne Moss</span>
					</li>
					<li>
						<strong class="en">Cinematographer</strong>
						<span class="en"> Bill Pope</span>
					</li>
					<li>
						<strong class="en">Editor</strong>
						<span class="en"> The Wachowskis</span>
					</li>
					<li>
						<strong class="en">Music</strong>
						<span class="en">  Bruce Berman</span>
					</li>
				</ul>
			</div> <!-- // credit -->
		</div>
	</div> <!-- // row -->
</div>


<!-- 모달비디오 -->

<div class="container">
	<!-- Modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-body">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          		<span aria-hidden="true">&times;</span>
        		</button>        
	        	<!-- 16:9 aspect ratio -->
				<div class="embed-responsive embed-responsive-16by9">
 					<iframe class="embed-responsive-item" src="" id="video"  allowscriptaccess="always" allow="autoplay"></iframe>
				</div>
         </div>
	    </div>
	  </div>
	</div> 
</div>

<script>
$(document).ready(function() {

	// Gets the video src from the data-src on each button

	var $videoSrc;  
	$('.video-btn').click(function() {
	    $videoSrc = $(this).data( "src" );
	});

	  
	  
	// when the modal is opened autoplay it  
	$('#myModal').on('shown.bs.modal', function (e) {
	    
	// set the video src to autoplay and not to show related video. Youtube related video is like a box of chocolates... you never know what you're gonna get
	$("#video").attr('src',$videoSrc + "?autoplay=1&amp;modestbranding=1&amp;showinfo=0" ); 
	})
	  


	// stop playing the youtube video when I close the modal
	$('#myModal').on('hide.bs.modal', function (e) {
	    // a poor man's stop video
	    $("#video").attr('src',$videoSrc); 
	}) 
	    
	    


	  
	  
	// document ready  
	});

	// 상영일정 클릭하면 해당 정보를 담은 예매페이지로 이동
	function goBooking(startDate, movieId) {
		window.location.href=`${pageContext.request.contextPath}/movie/booking.do?playdate=\${startDate}&movieId=\${movieId}`;
	};


</script>

<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>

   