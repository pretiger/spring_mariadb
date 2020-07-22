package com.sample.spring.controller.member;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sample.spring.domain.member.Member;
import com.sample.spring.service.member.MemberService;

@Controller
@RequestMapping("/member/*")
public class MemberController {

	private static final Logger logger=LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	MemberService memberService;
	
	@GetMapping("delete.do")
	public String delete(@RequestParam String userid) {
		memberService.delete(userid);
		return "redirct:/member/list.do";
	}
	
	@PostMapping("insert.do")
	public String insert(@ModelAttribute Member dto) {
		memberService.insert(dto);
		return "redirect:/member/list.do";
	}
	
	@GetMapping("write.do") 
	public String write() {
		return "member/write";
	}
	
	@GetMapping("adminLogin.do")
	public String adminLogin() {
		return "member/adminLogin";
	}
	
	@GetMapping("logout.do")
	public String logout(HttpSession session, Model model) {
		session.invalidate();
		model.addAttribute("message", "Logout!");
		return "redirect:/member/login.do";
	}
	
	@PostMapping("passChk.do")
	public ModelAndView passChk(@ModelAttribute Member dto, ModelAndView mav, HttpSession session) {
		String name=memberService.passChk(dto.getUserid(), dto.getPasswd());
		if(name != null) {
			session.setAttribute("userid", dto.getUserid());
			session.setAttribute("name", name);
			if(name.equals("관리자")) {
				session.setAttribute("admin_userid", dto.getUserid());
			}
			mav.setViewName("home");
			mav.addObject("message", "홈페이지 방문을 환영합니다.!");
		} else {
			if(dto.getUserid().equals("admin")) {
				mav.setViewName("redirect:/member/adminLogin.do");
			} else {
				mav.setViewName("redirect:/member/login.do");
			}
			mav.addObject("message", "ID 또는 비밀번호가 일치하지 않습니다!");
		}
		return mav;
	}
	
	@GetMapping("login.do")
	public String login() {
		return "member/login";
	}
	
	@PostMapping("update.do") 
	public String passChk(@ModelAttribute Member dto) {
		memberService.update(dto);
		return "redirect:/member/list.do";
	}
	
	@GetMapping("edit.do")
	public String edit(@RequestParam String userid, Model model) {
		model.addAttribute("dto", memberService.view(userid));
		return "member/edit";
	}
	
	@GetMapping("list.do")
	public String list(Model model) {
		model.addAttribute("list", memberService.list());
		return "member/list";
	}
}
