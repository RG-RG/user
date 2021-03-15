package kr.co.rgrg.user.blog.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.rgrg.user.follow.vo.FollowVO;

@Controller
public class BlogMainController {
	
	@RequestMapping(value="{url_id}/blog", method= {RequestMethod.GET, RequestMethod.POST})
	public String getBlogMain(HttpSession session, @PathVariable("url_id") String url_id) {
		String login_id=(String)session.getAttribute("id");
		//vo에 로그인아이디, 블로그 아이디가 필요함 (글 숨김 기능 때문)
		return "blog/blog_main";
	}//getBlogMain
	
	@RequestMapping(value = "blog/profile.do", method=RequestMethod.POST)
	public String getProfile(HttpSession s, Model model, String id, FollowVO fVO) {
		
		return "";
	}//getProfile
	
	@RequestMapping(value = "blog/tag_list.do", method=RequestMethod.POST)
	public String getTagList() {
		
		return "";
	}//getTagList
	
	
}//class
