package kr.co.rgrg.user.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BlogPostController {
	
	@RequestMapping(value="blog/post_detail.do", method= {RequestMethod.GET, RequestMethod.POST})
	public String viewPostDetail() {
		return "blog/blog_post";
	}//viewPostDetail
	
}//class
