package com.sample.spring.controller.popup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sample.spring.domain.member.Member;
import com.sample.spring.service.member.MemberService;

@Controller
@RequestMapping("/popup/*")
public class PopupController {

	@Autowired
	MemberService memberService;
	
	@GetMapping("list.do")
	public String list(Model model) {
		model.addAttribute("list", memberService.list());
		return "popup/list";
	}
	
	@RequestMapping("edit.do")
    public String edit(String userid, Model model) {
        model.addAttribute("dto", memberService.view(userid));
        return "popup/edit";
    }
	
	@PostMapping("update.do") 
	@ResponseBody
	public Member update(@ModelAttribute Member dto, Model model) {
		memberService.update(dto);
		Member member = memberService.view(dto.getUserid());
		return member;
	}

}
