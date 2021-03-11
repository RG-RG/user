package kr.co.rgrg.user.blog.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.rgrg.user.blog.domain.CommDomain;
import kr.co.rgrg.user.blog.domain.PostDetailDomain;
import kr.co.rgrg.user.blog.domain.PostProfileDomain;
import kr.co.rgrg.user.blog.service.BlogPostService;
import kr.co.rgrg.user.blog.vo.FollowerVO;
import kr.co.rgrg.user.blog.vo.PostDeleteVO;

@Controller
public class BlogPostController {
	
	@RequestMapping(value="{url_id}/blog/post/{url_post_num}", method= {RequestMethod.GET, RequestMethod.POST})
	public String viewPostDetail(Model model, HttpSession session,
			@PathVariable("url_id") String url_id, @PathVariable("url_post_num") String url_post_num) {
		session.setAttribute("id", "park");
		
		try {
			int post_num=Integer.parseInt(url_post_num);
			BlogPostService bps=new BlogPostService();
			PostDetailDomain pdDomain=bps.getPostDetail(post_num);
			PostProfileDomain ppDomain=bps.getPostProfile(post_num);
			
			FollowerVO fVO=new FollowerVO();
			fVO.setId(url_id);
			fVO.setFollower_id((String)session.getAttribute("id"));
			
			List<CommDomain> list=bps.getCommList(post_num);
			if(pdDomain!=null && ppDomain!=null && url_id.equals(ppDomain.getId())) {
				model.addAttribute("post_detail", pdDomain);
				model.addAttribute("post_profile", ppDomain);
				model.addAttribute("comm_list", list);
				model.addAttribute("follow_flag", bps.getFollowFlag(fVO));
			}else {
				model.addAttribute("post_detail_fail","fail");
			}//end else
		} catch (NumberFormatException nfe) {
			model.addAttribute("post_detail_fail","fail");
		}//end catch
		
		return "blog/blog_post";
	}//viewPostDetail
	
	@RequestMapping(value="rgrg/{url_id}/blog/post/remove/{url_post_num}", method=RequestMethod.POST)
	@ResponseBody
	public String removePost(HttpSession session, @PathVariable("url_id") String url_id,
			@PathVariable("url_post_num") String url_post_num) {
		String json=null;
		
		String login_id=(String)session.getAttribute("id");
		try {
			int post_num=Integer.parseInt(url_post_num);
			if(url_id.equals(login_id)) {
				PostDeleteVO pdVO=new PostDeleteVO();
				pdVO.setId(login_id);
				pdVO.setPost_num(post_num);
				json=new BlogPostService().removePost(pdVO);
			}//end if
		} catch (Exception e) {
			// TODO: handle exception
		}//end catch
		return json;
	}//removePost
	
}//class
