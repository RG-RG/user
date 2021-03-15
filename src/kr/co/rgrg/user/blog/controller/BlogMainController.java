package kr.co.rgrg.user.blog.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.rgrg.user.blog.domain.BlogMainDomain;
import kr.co.rgrg.user.blog.domain.TagDomain;
import kr.co.rgrg.user.blog.service.BlogMainService;
import kr.co.rgrg.user.blog.vo.BlogMainVO;
import kr.co.rgrg.user.follow.vo.FollowVO;
import kr.co.rgrg.user.pagination.PostRangeVO;

@Controller
public class BlogMainController {
	
	@RequestMapping(value="{url_id}/blog", method= {RequestMethod.GET, RequestMethod.POST})
	public String getBlogMain(HttpSession session, Model model, @PathVariable("url_id") String url_id, String param_page, String search, String tag_name) {
		String login_id=(String)session.getAttribute("id");
		
		try {
			int page=0;
			if(param_page!=null && !"".equals(param_page)) {
				page=Integer.parseInt(param_page);
			}//end if
			PostRangeVO prVO=new PostRangeVO(page);
			prVO.setId(url_id);
			if(search!=null && !"".equals(search)) {
				prVO.setColumn_name("search");
				prVO.setColumn_value(search);
			}//end if
			if(tag_name!=null && !"".equals(tag_name)) {
				prVO.setColumn_name("tag");
				prVO.setColumn_value(tag_name);
			}//end if
			
			BlogMainVO bmVO=new BlogMainVO();
			bmVO.setId(url_id);
			if(!url_id.equals(login_id)) {
				bmVO.setHidden_flag(true);
				prVO.setHidden_flag(true);
			}//end if
			BlogMainService bms=new BlogMainService();
			BlogMainDomain bmDomain=bms.getBlogProfile(bmVO);
			List<TagDomain> tag_list=bms.getTagList(bmVO);
			
			
			
			model.addAttribute("blog_profile", bmDomain);
			model.addAttribute("tag_list", tag_list);
			
		} catch (Exception e) {
			// TODO: handle exception
		}//end catch
		
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
