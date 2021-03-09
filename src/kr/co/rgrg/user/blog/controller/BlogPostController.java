package kr.co.rgrg.user.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.rgrg.user.blog.domain.PostDetailDomain;
import kr.co.rgrg.user.blog.service.BlogPostService;

@Controller
public class BlogPostController {
	
	@RequestMapping(value="blog/post_detail.do", method= {RequestMethod.GET, RequestMethod.POST})
	public String viewPostDetail(Model model, String param_post_num) {
		System.out.println(param_post_num);
		try {
			int post_num=Integer.parseInt(param_post_num);
			
			PostDetailDomain pdDomain=new BlogPostService().getPostDetail(post_num);
			if(pdDomain!=null) {
				model.addAttribute("post_detail", pdDomain);
			}else {
				model.addAttribute("post_detail_fail","fail");
			}//end else
//			System.out.println(pdDomain);
		} catch (NumberFormatException nfe) {
			model.addAttribute("post_detail_fail","fail");
		}//end catch
		
		return "blog/blog_post";
	}//viewPostDetail
	
}//class
