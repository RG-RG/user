package kr.co.rgrg.user.blog.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.rgrg.user.blog.domain.BlogMainDomain;
import kr.co.rgrg.user.blog.domain.PostDomain;
import kr.co.rgrg.user.blog.domain.TagDomain;
import kr.co.rgrg.user.blog.service.BlogMainService;
import kr.co.rgrg.user.blog.vo.BlogMainVO;
import kr.co.rgrg.user.follow.vo.FollowVO;
import kr.co.rgrg.user.pagination.PaginationDAO;
import kr.co.rgrg.user.pagination.PaginationService;
import kr.co.rgrg.user.pagination.PostRangeVO;

@Controller
public class BlogMainController {
	
	@RequestMapping(value="{url_id}/blog", method= {RequestMethod.GET, RequestMethod.POST})
	public String getBlogMain(HttpSession session, Model model, @PathVariable("url_id") String url_id, String search, String tag) {
		String login_id=(String)session.getAttribute("id");
		
		try {
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
			System.out.println("========================="+total_cnt);
			System.out.println("========================="+prVO.getEnd_num());
		} catch (Exception e) {
			// TODO: handle exception
		}//end catch
		
		return "blog/blog_main";
	}//getBlogMain
	
	@RequestMapping(value="{url_id}/blog/more/{cur_page}", method= {RequestMethod.GET, RequestMethod.POST})
	public String getBlogMainMore(HttpSession session, Model model, @PathVariable("url_id") String url_id, @PathVariable("cur_page") String cur_page, 
			String search, String tag) {
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
				model.addAttribute("search_word", search);
			}//end if
			if(tag!=null && !"".equals(tag)) {
				prVO.setColumn_name("tag");
				prVO.setColumn_value(tag);
				model.addAttribute("search_tag", tag);
			}//end if
			if(!url_id.equals(login_id)) {
				prVO.setHidden_flag(true);
			}//end if
			
			json=new BlogMainService().getBlogMainMore(prVO, page);
			
		} catch (Exception e) {
			// TODO: handle exception
		}//end catch
		
		return json;
	}//getBlogMainMore
	
	
	
	
}//class
