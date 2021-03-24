package kr.co.rgrg.user.post.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import kr.co.rgrg.user.post.domain.PostDomain;
import kr.co.rgrg.user.post.service.PostService;
import kr.co.rgrg.user.post.vo.ModifyPostVO;
import kr.co.rgrg.user.post.vo.PostVO;

@SessionAttributes("id")
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
	public String writePostForm(HttpSession session, Model model) {
		String id = (String)session.getAttribute("id");
		
		if (id != null) {
			model.addAttribute("temp_post_flag", new PostService().searchPublishPost(id));			
		} else {
			return "redirect:main/main";
		}
		model.addAttribute("id", id);

		return "post/edit";
	}
	
	@RequestMapping(value="/post/temp_post_form.do", method= RequestMethod.POST)
	public String getTempPostForm(Model model, HttpSession session) {
		String id = (String)session.getAttribute("id");
		
		if(id != null) {
			PostService ps = new PostService();
			String post_num = ps.searchPublishPost(id);
			PostDomain pdomain = ps.searchEditPost(post_num);
			System.out.println(post_num);
			model.addAttribute("post_data", pdomain);			
		}
		model.addAttribute("id", id);
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
	public String saveNewPost(PostVO pVO, HttpSession session, MultipartFile thumbnail_img, MultipartHttpServletRequest request, Model model) throws Exception {
		String id = (String)session.getAttribute("id");
		
		if(id != null) {
			pVO.setId("user1");
			PostService ps = new PostService();
			
			String upload_result = "";
			boolean post_result = false;
			try {
				if(thumbnail_img != null) {
					System.out.println("����� ������ ����");
					
					String root_path = request.getSession().getServletContext().getRealPath("/");  
					upload_result = ps.saveFile(root_path, thumbnail_img);
					pVO.setThumbnail(upload_result);				
				}
				
				post_result = ps.savePost(pVO);
				System.out.println("�Խñ� ���� ��� "+post_result);
			} catch (NullPointerException e) {
				upload_result = "no_file";
			}
			
			if(post_result && upload_result != null) {
				model.addAttribute("posting_result", "success");
			} else {
				model.addAttribute("posting_result", "fail");
			}
		}
		
		return "redirect:/rgrg/main/main";
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
		String id = (String)session.getAttribute("id");
		
		if(id != null) {
			PostService ps = new PostService();
			PostDomain pdomain = ps.searchEditPost(post_num);
			model.addAttribute("post_data", pdomain);			
		}
		model.addAttribute("id", id);
		
		return "post/edit";
	}
	
	/**
	 * �Խñ� ���� �����ϱ�
	 * @param mpVO
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/post/save_modify_post.do", method= RequestMethod.POST)
	public String saveModifyPost(ModifyPostVO mpVO, MultipartFile thumbnail_img, MultipartHttpServletRequest request, HttpSession session, Model model) {
		String id = (String)session.getAttribute("id");
		
		if (id != null) {
			mpVO.setId("user1");
			PostService ps = new PostService();
			// result�� ���� ���� �̵�����
			
			String upload_result = "";
			boolean post_result = false;
			try {
				if(thumbnail_img != null) {
					String root_path = request.getSession().getServletContext().getRealPath("/");  
					upload_result = ps.saveFile(root_path, thumbnail_img);
					mpVO.setThumbnail(upload_result);				
				}
				
				post_result = ps.modifyPost(mpVO);
				System.out.println(post_result);
			} catch (NullPointerException e) {
				upload_result = "no_file";
			}
			
			if(post_result && upload_result != null) {
				model.addAttribute("posting_result", "success");
			} else {
				model.addAttribute("posting_result", "fail");
			}
			
		}
		
		return "redirect:/rgrg/main/main";
	}
	
	/**
	 * �Խñ� �����ϴ� ��
	 * @param post_num
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/post/cancel.do", method= RequestMethod.GET)
	public String cancelPost(String post_num, HttpSession session) {
		String id = (String)session.getAttribute("id");
		boolean result = false;
		if(id != null) {
			result = new PostService().removePost(id);			
		}
		
		logger.info("�Խñ� ���� ��� : " + result);
		
		return "redirect:/main/main";
	}
}
