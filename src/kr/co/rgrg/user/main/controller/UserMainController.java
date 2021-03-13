package kr.co.rgrg.user.main.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.rgrg.user.main.domain.UserMainDomain;
import kr.co.rgrg.user.main.service.UserMainService;
import kr.co.rgrg.user.pagination.RangeVO;

@Controller
public class UserMainController {
	
	/**
	 * 메인화면을 보여주기
	 * @param s
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/main/main", method=RequestMethod.GET)
	public String main(HttpSession s, Model model) {
		
		int int_page = 1;
		
		RangeVO rVO = new RangeVO(int_page);
		
		
		List<UserMainDomain> main_list = new UserMainService().getUserMainList(rVO);
		model.addAttribute("main_list", main_list);
		
		return "main/main";
	}

	@RequestMapping(value="/main/main/{param_page}", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String see_more(@PathVariable("param_page") String param_page) {
		String json=null;
		int page = 1;
		if(param_page != null && !"".equals(param_page)) {
			page = Integer.parseInt(param_page); 
			//page가 null이 아닐경우 int로 바꿔줌 
		}
//		System.out.println("----------------------------------"+page);
		
		RangeVO rVO = new RangeVO(page);
//		System.out.println("----------------------------------"+rVO);
		json = new UserMainService().getUserMoreList(rVO);
		
		return json;
	}
}
