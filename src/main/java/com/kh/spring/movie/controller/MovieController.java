package com.kh.spring.movie.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.spring.member.model.service.MemberService;
import com.kh.spring.member.model.vo.Member;
import com.kh.spring.movie.exception.MovieException;
import com.kh.spring.movie.model.service.MovieService;
import com.kh.spring.movie.model.vo.Movie;
import com.kh.spring.movie.model.vo.MoviePlus;
import com.kh.spring.movie.model.vo.MovieReservation;
import com.kh.spring.movie.model.vo.MovieSchedule;
import com.kh.spring.movie.model.vo.Seat;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/movie")
@Slf4j
public class MovieController {

	@Autowired
	private MovieService movieService;
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping("/booking.do")
	public String booking(@RequestParam(required=false) String playdate, @RequestParam(required=false) String movieId, Model model) {
		try {
			List<Movie> totalMovieList = movieService.selectTotalMovieList();
			log.debug("totalMovieList = {}", totalMovieList);

			Map<String, Object> param = new HashMap<>();
			
			if(playdate != null) {
				param.put("playdate", playdate);
				if(movieId != null) {
					param.put("movieId", movieId);
				}	
				List<MoviePlus> list = movieService.selectMovieSchedule(param);
				log.debug("list = {}", list);
				model.addAttribute("list", list);
			}
			
			if(movieId != null) {
				Movie oneMovie = movieService.selectOneMovie(movieId);
				model.addAttribute("oneMovie", oneMovie);
			}
			
			model.addAttribute("playdate", playdate);
			model.addAttribute("totalMovieList", totalMovieList);
		} catch (Exception e) {
			throw new MovieException("예매페이지 불러오기 오류", e);
		}
		
		return "movie/booking";
	}

	@GetMapping("/selectMovieSchedule.do")
	public String selectMovieSchedule(@RequestParam(required=false) String playdate, @RequestParam(required=false) String movieId, Model model) {
		try {
			log.debug("playdate = {}", playdate);
			log.debug("movieId = {}", movieId);
			
			Map<String, Object> param = new HashMap<>();
			param.put("playdate", playdate);
			param.put("movieId", movieId);
			
			List<MoviePlus> list = movieService.selectMovieSchedule(param);
			log.debug("list = {}", list);
			
			model.addAttribute("list", list);
		} catch (Exception e) {
			throw new MovieException("상영일정 불러오기 오류", e);
		}
		
		return "movie/movieScheduleList";
	}
	
	@GetMapping("/selectSeats.do")
	public String selectSeats(@RequestParam String scheduleId, @RequestParam String movieId, Model model) {
		try {
			log.debug("scheduleId = {}", scheduleId);
			log.debug("movieId = {}", movieId);
			
			MovieSchedule schedule = movieService.selectOneSchedule(scheduleId);
			Movie movie = movieService.selectOneMovie(movieId);
			List<Seat> seatList = movieService.selectSeatList(scheduleId);
			
			log.debug("schedule = {}", schedule);
			log.debug("movie = {}", movie);
			log.debug("seatList = {}", seatList);
			
			model.addAttribute("schedule", schedule);
			model.addAttribute("movie", movie);
			model.addAttribute("seatList", seatList);
		} catch (Exception e) {
			throw new MovieException("좌석선택페이지 오류", e);
		}
		
		return "movie/seatMap";
	}
	
	@GetMapping("/payment.do")
	public void payment(@RequestParam String scheduleId, @RequestParam String movieId, @RequestParam String total, @RequestParam(value="selectedSeats") List<String> selectedSeats, Model model) {
		try {
			log.debug("scheduleId = {}", scheduleId);
			log.debug("movieId = {}", movieId);
			log.debug("total = {}", total);
			log.debug("selectedSeats = {}", selectedSeats);
			
			MovieSchedule schedule = movieService.selectOneSchedule(scheduleId);
			Movie movie = movieService.selectOneMovie(movieId);
			
			model.addAttribute("schedule", schedule);
			model.addAttribute("movie", movie);
			model.addAttribute("total", total);
			model.addAttribute("selectedSeats", selectedSeats);
		} catch (Exception e) {
			throw new MovieException("결제페이지 오류", e);
		}
		
	}	
	
	@PostMapping("/reservation.do")
	public String reservation(
						MovieReservation reservation,
						@RequestParam String movieImage,
						@RequestParam String finalSeat,
						@RequestParam String totalPoint,
						@RequestParam String usedPoint,
						Authentication oldAuthentication,
						Model model) {
		
		try {
			log.debug("movieImage = {}", movieImage);
			log.debug("reservation = {}", reservation);
			log.debug("finalSeat = {}", finalSeat);
			log.debug("totalPoint = {}", totalPoint);
			log.debug("usedPoint = {}", usedPoint);
			
			// 예약한 좌석정보 업데이트
			String seatsStr = finalSeat.substring(1, finalSeat.length() - 1);
			log.debug("seatsStr = {}", seatsStr);
			String[] seatList = seatsStr.split(", ");
			int count = seatList.length;   // jsp로 보낼거임
			for(String seatNo : seatList) {
				log.debug("seatNo = {}", seatNo);
				Map<String, Object> param = new HashMap<>();
				param.put("seatNo", seatNo);
				param.put("scheduleId", reservation.getMovieScheduleId());
				int result = movieService.bookSeats(param);
			}
			
			// 예약 테이블에 insert
			reservation.setSelectedSeat(seatsStr);
			reservation.setCount(count);
			int result = movieService.insertReservation(reservation);
			
			// 방금 들어간 예약의 예약번호 가져오기
			String reserveId = movieService.selectLatestReservId();
			log.debug("reserveId = {}", reserveId);
			reservation.setMovieReservationId(reserveId);
			Map<String, Object> param3 = new HashMap<>();
			param3.put("movieImage", movieImage);
			param3.put("usedPoint", Integer.parseInt(usedPoint));
			param3.put("totalBefore", reservation.getAmount() + Integer.parseInt(usedPoint));
			param3.put("totalAfter", reservation.getAmount());
			param3.put("count", count);
			
			// 포인트 사용한거 업데이트
			Map<String, Object> param2 = new HashMap<>();
			param2.put("memberId", reservation.getMemberId());
			param2.put("point", Integer.parseInt(totalPoint) - Integer.parseInt(usedPoint));
			int result2 = movieService.updatePoint(param2);
			Member principal = (Member) oldAuthentication.getPrincipal();
			principal.setPoint(Integer.parseInt(totalPoint) - Integer.parseInt(usedPoint));
			
			// 포인트 이용내역 db에 기록
			if(Integer.parseInt(usedPoint) != 0) {
				Map<String, Object> history = new HashMap<>();
				history.put("id", reservation.getMemberId());
				history.put("reason", "영화 예매 포인트 사용	" );
				history.put("change", "-" + usedPoint);
				history.put("point", Integer.parseInt(totalPoint) - Integer.parseInt(usedPoint));
				memberService.insertPointHistory(history);
			}
			
			
			log.debug("reservation = {}", reservation);
			log.debug("param3 = {}", param3);
			
			model.addAttribute("reservation", reservation);
			model.addAttribute("param3", param3);
		} catch (NumberFormatException e) {
			throw new MovieException("결제/예매내역 생성 오류", e);
		}
		
		return "movie/reservationComplete";
	}
	
	@GetMapping("/selectMovieInfo.do")
	public String movieInfo(@RequestParam String movieId, Model model) {
		try {
			Movie oneMovie = movieService.selectOneMovie(movieId);
			model.addAttribute("oneMovie", oneMovie);
		} catch (Exception e) {
			throw new MovieException("영화정보 불러오기 오류", e);
		}
		
		return "movie/movieInfo";
	}
}
