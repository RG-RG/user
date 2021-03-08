package kr.co.rgrg.user.post.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.rgrg.user.post.service.PostService;
import kr.co.rgrg.user.post.vo.ModifyPostVO;
import kr.co.rgrg.user.post.vo.PostVO;

@Controller
public class PostController {
	
	@RequestMapping(value="/post/post_form.do", method= RequestMethod.GET)
	public String writePostForm() {
		
		PostService ps = new PostService();
		
		
		return "post/post";
	}
	
	@RequestMapping(value="/post/new_post.do", method= RequestMethod.GET)
	public String saveNewPost(PostVO pVO, HttpSession session) {
		
		PostService ps = new PostService();
		
		
		return "post/new";
	}
	
	@RequestMapping(value="/post/save_modify_post.do", method= RequestMethod.GET)
	public String saveModifyPost(ModifyPostVO mpVO, HttpSession session) {
		
		PostService ps = new PostService();
		
		
		return "post/save_modify";
	}
	
	@RequestMapping(value="/post/get_modify_post.do", method= RequestMethod.GET)
	public String getModifyPost(String postNum, Model model,HttpSession session) {
		
		PostService ps = new PostService();
		
		return "post/get_modify";
	}
	
	@RequestMapping(value="/post/cancel.do", method= RequestMethod.GET)
	public String cancelPost(String postNum) {
		
		PostService ps = new PostService();
		
		return "post/cancel";
	}
}
