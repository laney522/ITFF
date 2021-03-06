package com.kh.spring.question.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.spring.common.HiSpringUtils;
import com.kh.spring.member.model.vo.Member;
import com.kh.spring.notice.model.vo.Notice;
import com.kh.spring.question.model.service.QuestionService;
import com.kh.spring.question.model.vo.Question;
import com.kh.spring.question.model.vo.QuestionComment;
import com.kh.spring.sharing.model.vo.Attachment;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/question")
@Slf4j
public class QuestionController {

	@Autowired
	private QuestionService questionService;
	
	@Autowired
	ServletContext application;
	
	@Autowired
	ResourceLoader resourceLoader;
	
	@GetMapping("/faq.do")
	public void faq() {}
	
	@GetMapping("/questionForm.do")
	public void questionForm() {}
	
	@PostMapping("/questionEnroll.do")
	public String questionEnroll(
			@RequestParam(value= "upFile", required =false) MultipartFile upFile,
			@ModelAttribute Question question,
			RedirectAttributes redirectAttr
			) {
		
		try {
			String saveDirectory = application.getRealPath("/resources/upload/question");
			
			List<Attachment> attachments = new ArrayList<>();
			
			if(!upFile.isEmpty() && upFile.getSize() != 0) {
				
				log.debug("upFile = {}", upFile);
				log.debug("upFile.name = {}", upFile.getOriginalFilename());
				log.debug("upFile.size = {}", upFile.getSize());
				
				// ????????? ???????????? ????????????
				String originalFilename = upFile.getOriginalFilename();
			    String renamedFilename = HiSpringUtils.getRenamedFilename(originalFilename);
				
				File dest = new File(saveDirectory, renamedFilename);
				log.debug("dest  = {}", dest);
				upFile.transferTo(dest);
				
				// ???????????? attachment ???????????? ??????????????? ???.
			    // 2. db??? attachment ????????? ??????
			    Attachment attach = new Attachment();
			    attach.setRenamedFilename(renamedFilename);
			    attach.setOriginalFilename(originalFilename);
			    
			    attachments.add(attach);
			    
			    log.debug("??????");
			}	
			
			if(!attachments.isEmpty()) {
				question.setAttachments(attachments);
			}
			
			int result = questionService.insertQuestion(question);
			
			String msg = result > 0 ? "?????? ?????? ??????" : "?????? ?????? ??????";
			log.debug("?????? db ?????? ???? {}", msg);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/question/questionList.do";
		
	}
	
	@GetMapping("/questionList.do")
	public String questionList(
			Model model,
			@RequestParam(defaultValue = "1") int cPage,
			HttpServletRequest request,
			Authentication authentication
			) {
		
		try {
			Member member = (Member) authentication.getPrincipal();
			log.debug("[principal] member = {}", member);

			String id = member.getId();
			log.debug("id {}", id);
			
			log.debug("model {}", model); // ???????
			log.debug("cPage = {}", cPage);
			
			
			
			int limit = 10;
			int offset = (cPage - 1) * limit;
			
			// map??? ??????
			Map<String, Object> param = new HashMap<>();
			param.put("memberId", id);
			param.put("limit", limit);
			param.put("offset", offset);
			
			// ?????? ????????? ??????
			List<Question> questionList = questionService.selectQuestionList(param);
			log.debug("questionList = {}", questionList);
			model.addAttribute("questionList",questionList);
			
			// ?????????????????? ??? ????????? ?????????
			List<Question> qlByAdmin = questionService.selectQuestionListByAdmin(limit, offset);
			log.debug("qlByAdmin = {}", qlByAdmin);
			model.addAttribute("qlByAdmin", qlByAdmin);
			
			// ?????? ????????? ??? ????????? by id
			int totalContent = questionService.countTotalContent(id);
			log.debug("totalContent {}", totalContent + "???");
			
			// ???????????? ?????? ????????? ??? ?????????
			int totalContentByAdmin = questionService.countTotalContentByAdmin();
			log.debug("totalContentByAdmin = {}", totalContentByAdmin + "???");
			
			String url = request.getRequestURI();
			
			// pagebar by user
			String pagebar = HiSpringUtils.getPagebar(cPage, limit, totalContent, url);
//			log.debug("pagebar ?{}", pagebar);
			model.addAttribute("pagebar", pagebar);
			
			// pagebar by admin
			String pagebarByAdmin = HiSpringUtils.getPagebar(cPage, limit, totalContentByAdmin, url);
//			log.debug("pagebarByAdmin ? {}", pagebarByAdmin);
			model.addAttribute("pagebarByAdmin",pagebarByAdmin);
			
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "question/questionList";
	}
	
	
	@GetMapping("/questionDetail.do")
	public void questionDetail(@RequestParam int no,Model model) {
		log.debug("????????? ?????????? {}", no);
		
		Question question = questionService.selectQuestionCollection(no);
		log.debug("????????? question {}", question);
		model.addAttribute("question", question);
		
		// ???????????? ?????? ?????? ????????????
		QuestionComment qc = questionService.selectQuestionComment(no);
		log.debug("qc = {}", qc);
		model.addAttribute("qc", qc);
		
	}
	
	
	/*
	 * ???????????? 
	 */
	@PostMapping("/questionCommentEnroll.do")
	public String questionCommentEnroll(
			@RequestParam String content,
			@RequestParam String writer,
			@RequestParam int questionNo,
			RedirectAttributes redirectAttr
			) {
		log.debug("content = {}", content);
		log.debug("writer = {}", writer);
		log.debug("questionNo = {}", questionNo);
		
		// map??? ??????
		Map<String, Object> param = new HashMap<>();
		param.put("content", content);
		param.put("writer", writer);
		param.put("questionNo", questionNo);
		
		int result = questionService.insertQuestionComment(param);
		
		if (result > 0) {
			// ????????? ????????? question_board answer N -> Y??? ?????????
			int result2 = questionService.updateQuestionAnswer(questionNo);
			String msg = result2 > 0 ? "???????????? Y??? ??????" : "???????????? ?????????";
			log.debug(msg);
			
		}
		
		
		return "redirect:/question/questionDetail.do?no=" + questionNo;
	}
	
	
	@GetMapping (
			value = "/fileDownload.do",
			produces = MediaType.APPLICATION_OCTET_STREAM_VALUE
	)
	@ResponseBody
	public Resource fileDownload(@RequestParam int no, HttpServletResponse response) {
		
		// resource ?????? ??????
		Resource resource = null;
		try {
			
			log.debug("????????? int no = {}", no);
			
			// db attachment ??? ????????????
			Attachment attach = questionService.selectOneAttachment(no);
			log.debug("attach = {}", attach);
			
			// ?????? ???????????? ??? ???????????? ????????????
			String saveDirectory = application.getRealPath("/resources/upload/question");
			log.debug("saveDirectory ?????????? {}", saveDirectory);
			
			File downFile = new File(saveDirectory, attach.getRenamedFilename());
			
			resource = resourceLoader.getResource("file:" + downFile);
			log.debug("file : {}", downFile);
			
			// ????????? ??????
			String filename = new String(attach.getOriginalFilename().getBytes("utf-8"), "iso-8859-1");
			response.addHeader("Content-Disposition", "attachment; filename=" + filename);
			
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resource;
		
	}
	
	/**
    * URL Resource ??????
    * - ??????????????? ?????? ????????? ????????? ??? ??????
    * - ??????????????? ??? ???????????? ??????.
    */
	@GetMapping(
			value = "/resource.do",
			produces = MediaType.APPLICATION_OCTET_STREAM_VALUE
	)
	@ResponseBody
	public Resource resource(HttpServletResponse response) {
		
		Resource resource = resourceLoader.getResource("https://docs.oracle.com/javase/8/docs/api/java/io/File.html"); // html??? ???????????? ??? html ????????? ??????
		log.debug("resource = {}", resource);
		// ????????? ??????
		response.addHeader("Content-Disposition", "attachment; filename=File.html");
		
		return resource;
	}
	
	
	// ????????? ????????????
	@GetMapping("/questionUpdate.do")
	public void questionUpdate(@RequestParam int no, Model model) {
		log.debug("?????? ??? ????????? = {}", no);
		
		Question question = questionService.selectQuestionCollection(no);
		
		log.debug("question = {}", question);
		model.addAttribute("question", question);
	}
	
	@PostMapping("/questionUpdate.do")
	public String questionUpdate(
			 @RequestParam(value = "upFile", required = false) MultipartFile upFile,
	         @ModelAttribute Question question,
	         @RequestParam int delFile,
	         RedirectAttributes redirectAttr
			) {
				log.debug("question???????????{}", question);
		try {
			int result = 0;
			String saveDirectory = application.getRealPath("/resources/upload/question");
			log.debug("sevaDirectory = {}", saveDirectory);
			
			List<Attachment> attachments = new ArrayList<>();
			
			if(!upFile.isEmpty() && upFile.getSize() != 0) {
				
				log.debug("upFile = {}", upFile);
			    log.debug("upFile.name = {}", upFile.getOriginalFilename());
			    log.debug("upFile.size = {}", upFile.getSize());
				
			    // ??? ?????? ???????????? ????????????
			    String originalFilename = upFile.getOriginalFilename();
			    String renamedFilename = HiSpringUtils.getRenamedFilename(originalFilename);
			    
			    File dest = new File(saveDirectory, renamedFilename);
			    log.debug("dest = {}", dest);
			    upFile.transferTo(dest);
			    
			    // ???????????? attachment ???????????? ??????????????? ???.
			    // 2. db??? attachment ????????? ??????
			    Attachment attach = new Attachment();
			    attach.setRenamedFilename(renamedFilename);
			    attach.setOriginalFilename(originalFilename);
			    
			    attachments.add(attach);
			    
			}
			
			
			log.debug("delFile ???? {}", delFile);
			
			if(delFile != 0) {
				int attachNo1 = delFile;
				Attachment attach = questionService.selectOneAttachment(attachNo1);
				
				// ????????????????????? ????????????
				File targetFile = new File(saveDirectory, attach.getRenamedFilename());
				targetFile.delete();
				
				// db ????????? ??????
				result = questionService.deleteQuestionAttachment(attachNo1);
				String yn = result > 0 ? "???????????? db?????? ?????????" : "???????????? db?????? ?????? ??????";
				log.debug("delFile ???????????????? {}", yn);
				
			}
			
			if(!attachments.isEmpty()) {
				
				question.setAttachments(attachments);
			}
			log.debug("?????? question ?????? {}", question);
			result = questionService.updateQuestion(question);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/question/questionDetail.do?no=" + question.getQuestionNo();
	}
	
	/*
	 * ?????? ??????
	 */
	@GetMapping("/questionCommentDelete.do")
	public String questionCommentDelete(@RequestParam int commentNo, @RequestParam int questionNo) {
		log.debug("commentNo = {}", commentNo);
		log.debug("questionNo = {}", questionNo);
		
		int result = questionService.deleteQuestionComment(commentNo);
		
		// ?????? ???????????? question_board ??? answer -> N?????? ??????
		int result2 = questionService.updateQuestionAnswerToN(questionNo);
		
		
		return "redirect:/question/questionDetail.do?no=" + questionNo;
	}
	
	/*
	 * ????????? ??????
	 */
	@PostMapping("/questionDelete.do")
	public String questionDelete(@RequestParam int no) {
		Attachment attach = questionService.selectOneAttachment(no);
		log.debug("attach : {}", attach);
		
		// ????????? ??????????????? ??????
		if(attach != null) {
			String saveDirectory = application.getRealPath("/resources/upload/question");
			File targetFile = new File(saveDirectory, attach.getRenamedFilename());
			boolean isDelete = targetFile.delete();
			log.debug("isDelete in server? = {}", isDelete);
		}
		
		int result = questionService.deleteOneQuestion(no);
		String msg = result > 0 ? no + "??? ??? ?????????" : no + "??? ??? ?????? ??????";
		log.debug(msg);
		
		
		return "redirect:/question/questionList.do";
	}
	
}
