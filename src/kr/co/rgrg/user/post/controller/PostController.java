package kr.co.rgrg.user.post.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.rgrg.user.post.vo.ModifyPostVO;
import kr.co.rgrg.user.post.vo.PostVO;

@Controller
public class PostController {
	
	@RequestMapping(value="/post/post_form.do", method= RequestMethod.GET)
	public String writePostForm() {
		
		return "";
	}
	
	@RequestMapping(value="/post/new_post.do", method= RequestMethod.GET)
	public String saveNewPost(PostVO pVO, HttpSession session) {
		
		return "";
	}
	
	@RequestMapping(value="/post/save_modify_post.do", method= RequestMethod.GET)
	public String saveModifyPost(ModifyPostVO mpVO, HttpSession session) {
		
		return "";
	}
	
	@RequestMapping(value="/post/get_modify_post.do", method= RequestMethod.GET)
	public String getModifyPost(String postNum, Model model,HttpSession session) {
		
		return "";
	}
	
	@RequestMapping(value="/post/cancel.do", method= RequestMethod.GET)
	public String cancelPost(String postNum) {
		
		return "";
	}
}
