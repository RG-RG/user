package kr.co.rgrg.user.blog.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.rgrg.user.blog.domain.BlogMainDomain;
import kr.co.rgrg.user.blog.domain.PostDomain;
import kr.co.rgrg.user.blog.domain.TagDomain;
import kr.co.rgrg.user.blog.service.BlogMainService;
import kr.co.rgrg.user.blog.vo.BlogMainVO;
import kr.co.rgrg.user.pagination.PaginationService;
import kr.co.rgrg.user.pagination.PostRangeVO;

@Controller
public class BlogMainController {
	
	@RequestMapping(value="/{url_id}/blog.do", method= {RequestMethod.GET, RequestMethod.POST})
	public String getBlogMain(HttpSession session, Model model, @PathVariable("url_id") String url_id, String search, String tag) {
		String login_id=(String)session.getAttribute("id");
		
		int page=1;
		PostRangeVO prVO=new PostRangeVO(page);
		prVO.setId(url_id);
		if(search!=null && !"".equals(search)) {
			prVO.setColumn_name("search");
			prVO.setColumn_value(search);
			model.addAttribute("search_word", search);
		}//end if
		if(tag!=null && !"".equals(tag)) {
			prVO.setColumn_name("tag");
			prVO.setColumn_value(tag);
			model.addAttribute("search_tag", tag);
		}//end if
		
		BlogMainVO bmVO=new BlogMainVO();
		bmVO.setId(url_id);
		bmVO.setHidden_flag(false);
		prVO.setHidden_flag(false);
		if(!url_id.equals(login_id)) {
			bmVO.setHidden_flag(true);
			prVO.setHidden_flag(true);
		}//end if
		BlogMainService bms=new BlogMainService();
		BlogMainDomain bmDomain=bms.getBlogProfile(bmVO);
		List<TagDomain> tag_list=bms.getTagList(bmVO);
		List<PostDomain> post_list=bms.getPostList(prVO);
		
		model.addAttribute("blog_profile", bmDomain);
		model.addAttribute("tag_list", tag_list);
		model.addAttribute("post_list", post_list);
		//페이지네이션
		int total_cnt=new PaginationService().getTotalPostCnt(prVO);
		model.addAttribute("cur_page", page);
		model.addAttribute("end_num", prVO.getEnd_num());
		model.addAttribute("total_cnt", total_cnt);
			
		return "blog/blog_main";
	}//getBlogMain
	
	@RequestMapping(value="/{url_id}/blog/more.do", method=RequestMethod.POST, produces="application/json;charset=utf8")
	@ResponseBody
	public void getBlogMainMore(HttpSession session, @PathVariable("url_id") String url_id, String cur_page, 
			String search, String tag, HttpServletResponse response) throws IOException {
		
		String json=null;
		String login_id=(String)session.getAttribute("id");
		try {
			int page=1;
			if(cur_page!=null && !"".equals(cur_page)) {
				page=Integer.parseInt(cur_page);
			}//end if
			PostRangeVO prVO=new PostRangeVO(page);
			prVO.setId(url_id);
			if(search!=null && !"".equals(search)) {
				prVO.setColumn_name("search");
				prVO.setColumn_value(search);
			}//end if
			if(tag!=null && !"".equals(tag)) {
				prVO.setColumn_name("tag");
				prVO.setColumn_value(tag);
			}//end if
			if(!url_id.equals(login_id)) {
				prVO.setHidden_flag(true);
			}//end if
			json=new BlogMainService().getBlogMainMore(prVO, page);
		} catch (Exception e) {
			// TODO: handle exception
		}//end catch
		
		response.setHeader("Content-Type", "application/xml");
		response.setContentType("text/xml;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		response.getWriter().print(json);
	}//getBlogMainMore
	
}//class
