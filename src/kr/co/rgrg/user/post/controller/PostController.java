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
		
		return "post/edit";
	}
	
	@RequestMapping(value="/post/post_publish.do", method= RequestMethod.POST)
	public String publishPost() {
		
		return "post/edit_publish";
	}
	
	@RequestMapping(value="/post/new_post.do", method= RequestMethod.POST)
	public String saveNewPost(PostVO pVO, HttpSession session) {
		pVO.setId("user1");
		PostService ps = new PostService();
		boolean result = ps.savePost(pVO);
		if(result) {
			return "post/edit";			
		}else{
			return "mypage/index";
		}
	}

	@RequestMapping(value="/post/get_modify_post.do", method= RequestMethod.GET)
	public String getModifyPost(String postNum, Model model,HttpSession session) {
		
		PostService ps = new PostService();
		
		return "post/get_modify";
	}
	
	@RequestMapping(value="/post/save_modify_post.do", method= RequestMethod.GET)
	public String saveModifyPost(ModifyPostVO mpVO, HttpSession session) {
		
		PostService ps = new PostService();
		
		
		return "post/save_modify";
	}
	
	@RequestMapping(value="/post/cancel.do", method= RequestMethod.GET)
	public String cancelPost(String postNum) {
		
		PostService ps = new PostService();
		
		return "post/cancel";
	}
}
