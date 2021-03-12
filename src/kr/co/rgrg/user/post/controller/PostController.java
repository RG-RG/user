package kr.co.rgrg.user.post.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.rgrg.user.post.domain.PostDomain;
import kr.co.rgrg.user.post.service.PostService;
import kr.co.rgrg.user.post.vo.ModifyPostVO;
import kr.co.rgrg.user.post.vo.PostVO;

@Controller
public class PostController {
	
	/**
	 * 게시글 작성하기 - 임시저장된 글이 있다면 임시저장된 글 부터 불러오기
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/post/post_form.do", method= RequestMethod.GET)
	public String writePostForm(Model model, HttpSession session) {
		
		PostService ps = new PostService();
		String post_num = ps.searchPublishPost("user1");
		if(!("".equals(post_num))) {
			PostDomain pdomain = ps.searchEditPost(post_num);
			model.addAttribute("post_data", pdomain);
		}
		
		return "post/edit";
	}
	
	/**
	 * 출간하기 form
	 * @return
	 */
	@RequestMapping(value="/post/post_publish.do", method= RequestMethod.POST)
	public String publishPost() {
		
		return "post/edit_publish";
	}
	
	/**
	 * 게시글 최초 저장하기
	 * @param pVO
	 * @param session
	 * @return
	 */
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

	/**
	 * 수정할 게시글 받아오기
	 * @param post_num
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/post/get_modify_post.do", method= RequestMethod.GET)
	public String getModifyPost(String post_num, Model model,HttpSession session) {
		
		PostService ps = new PostService();
		PostDomain pdomain = ps.searchEditPost(post_num);
		model.addAttribute("post_data", pdomain);
		
		return "post/edit";
	}
	
	/**
	 * 게시글 수정 저장하기
	 * @param mpVO
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/post/save_modify_post.do", method= RequestMethod.GET)
	public String saveModifyPost(ModifyPostVO mpVO, HttpSession session) {
		
		PostService ps = new PostService();
		boolean result = ps.modifyPost(mpVO);
		// result에 따라 어디로 이동할지
		
		return "post/save_modify";
	}
	
	@RequestMapping(value="/post/cancel.do", method= RequestMethod.GET)
	public String cancelPost(String post_num) {
		
		PostService ps = new PostService();
		
		return "post/cancel";
	}
}
