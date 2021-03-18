package kr.co.rgrg.user.post.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import kr.co.rgrg.user.post.domain.PostDomain;
import kr.co.rgrg.user.post.service.PostService;
import kr.co.rgrg.user.post.vo.ModifyPostVO;
import kr.co.rgrg.user.post.vo.PostVO;

@Controller
public class PostController {
	private static final Logger logger = LoggerFactory.getLogger(PostService.class);


	//json �����ͷ� ������ ������ ����
	@Autowired
	MappingJackson2JsonView jsonView;
	
	/**
	 * �Խñ� �ۼ��ϱ� - �ӽ������ ���� �ִٸ� �ӽ������ �� ���� �ҷ�����
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
	 * �Ⱓ�ϱ� form
	 * @return
	 */
	@RequestMapping(value="/post/post_publish.do", method= RequestMethod.POST)
	public String publishPost() {
		
		return "post/edit_publish";
	}
	
	/**
	 * �Խñ� ���� �����ϱ�
	 * @param pVO
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/post/new_post.do", method= RequestMethod.POST)
	public String saveNewPost(PostVO pVO, HttpSession session, MultipartFile thumbnail_img, Model model) throws Exception {
		pVO.setId("user1");
		PostService ps = new PostService();
		
		String upload_result = "";
		boolean post_result = false;
		try {
			upload_result = ps.saveFile(thumbnail_img);
			pVO.setThumbnail(upload_result);
			post_result = ps.savePost(pVO);
			
		} catch (NullPointerException e) {
			upload_result = "no_file";
		}
		
		if(post_result && upload_result != null) {
			model.addAttribute("posting_result", "success");
		} else {
			model.addAttribute("posting_result", "fail");
		}
		return "post/edit";
	}
	

	/**
	 * ������ �Խñ� �޾ƿ���
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
	 * �Խñ� ���� �����ϱ�
	 * @param mpVO
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/post/save_modify_post.do", method= RequestMethod.GET)
	public String saveModifyPost(ModifyPostVO mpVO, HttpSession session) {
		
		PostService ps = new PostService();
		boolean result = ps.modifyPost(mpVO);
		// result�� ���� ���� �̵�����
		
		return "post/save_modify";
	}
	
	@RequestMapping(value="/post/cancel.do", method= RequestMethod.GET)
	public String cancelPost(String post_num) {
		
		PostService ps = new PostService();
		
		return "post/cancel";
	}
}
