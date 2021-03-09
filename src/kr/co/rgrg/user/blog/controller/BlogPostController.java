package kr.co.rgrg.user.blog.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.rgrg.user.blog.domain.CommDomain;
import kr.co.rgrg.user.blog.domain.PostDetailDomain;
import kr.co.rgrg.user.blog.domain.PostProfileDomain;
import kr.co.rgrg.user.blog.service.BlogPostService;

@Controller
public class BlogPostController {
	
	@RequestMapping(value="blog/post_detail.do", method= {RequestMethod.GET, RequestMethod.POST})
	public String viewPostDetail(Model model, String param_post_num) {
		System.out.println(param_post_num);
		try {
			int post_num=Integer.parseInt(param_post_num);
			BlogPostService bps=new BlogPostService();
			PostDetailDomain pdDomain=bps.getPostDetail(post_num);
			PostProfileDomain ppDomain=bps.getPostProfile(post_num);
			List<CommDomain> list=bps.getCommList(post_num);
			if(pdDomain!=null && ppDomain!=null) {
				model.addAttribute("post_detail", pdDomain);
				model.addAttribute("post_profile", ppDomain);
				model.addAttribute("comm_list", list);
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
