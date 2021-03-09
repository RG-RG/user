package kr.co.rgrg.user.main.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.rgrg.user.main.domain.UserMainDomain;
import kr.co.rgrg.user.main.service.UserMainService;

@Controller
public class UserMainController {
	
	/**
	 * 메인화면을 보여주기
	 * @param s
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/main/main.do", method=RequestMethod.GET)
	public String main(HttpSession s, Model model) {
		
		List<UserMainDomain> main_list = new UserMainService().getUserMainList();
		model.addAttribute("main_list", main_list);
		
		return "main/main";
	}
}
