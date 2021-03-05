package kr.co.rgrg.user.blog.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.rgrg.user.follow.vo.FollowVO;

@Controller
public class BlogMainController {
	
	@RequestMapping(value = "blog/profile.do", method=RequestMethod.POST)
	public String getProfile(HttpSession s, Model model, String id, FollowVO fVO) {
		
		return "";
	}//getProfile
	
	@RequestMapping(value = "blog/tag_list.do", method=RequestMethod.POST)
	public String getTagList() {
		
		return "";
	}//getTagList
	
	
}//class
