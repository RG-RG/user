package kr.co.rgrg.user.blog.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.rgrg.user.blog.domain.CommDomain;
import kr.co.rgrg.user.blog.domain.PostDetailDomain;
import kr.co.rgrg.user.blog.domain.PostProfileDomain;
import kr.co.rgrg.user.blog.service.BlogPostService;
import kr.co.rgrg.user.blog.vo.AddCommVO;
import kr.co.rgrg.user.blog.vo.FollowerVO;
import kr.co.rgrg.user.blog.vo.LikePostVO;
import kr.co.rgrg.user.blog.vo.ModifyCommVO;
import kr.co.rgrg.user.blog.vo.PostDeleteVO;
import kr.co.rgrg.user.blog.vo.RemoveCommVO;

@CrossOrigin(origins="*")
@Controller
public class BlogPostController {
	
	@RequestMapping(value="{url_id}/blog/post.do", method= {RequestMethod.GET, RequestMethod.POST})
	public String viewPostDetail(Model model, HttpSession session,
			@PathVariable("url_id") String url_id, String post) {
		
		try {
			String session_id=(String)session.getAttribute("id");
			if(session_id==null) {
				session_id="";
			}//end if
			
			int post_num=Integer.parseInt(post);
			BlogPostService bps=new BlogPostService();
			PostDetailDomain pdDomain=bps.getPostDetail(post_num);
			PostProfileDomain ppDomain=bps.getPostProfile(post_num);
			
			FollowerVO fVO=new FollowerVO();
			fVO.setId(url_id);
			fVO.setFollower_id(session_id);
			
			LikePostVO lpVO=new LikePostVO();
			lpVO.setId(session_id);
			lpVO.setPost_num(post_num);
			
			List<CommDomain> list=bps.getCommList(post_num);
			if(pdDomain!=null && ppDomain!=null && url_id.equals(ppDomain.getId())) {
				model.addAttribute("post_detail", pdDomain);
				model.addAttribute("post_profile", ppDomain);
				model.addAttribute("comm_list", list);
				model.addAttribute("follow_flag", bps.getFollowFlag(fVO));
				model.addAttribute("like_flag", bps.getLikeFlag(lpVO));
			}else {
				model.addAttribute("post_detail_fail","fail");
			}//end else
		} catch (NumberFormatException nfe) {
			model.addAttribute("post_detail_fail","fail");
		}//end catch
		
		return "blog/blog_post";
	}//viewPostDetail

	
	@RequestMapping(value="/{url_id}/blog/post/remove.do", method=RequestMethod.POST)
	@ResponseBody
	public String removePost(HttpSession session, @PathVariable("url_id") String url_id, String post) {
		String json=null;
		String login_id=(String)session.getAttribute("id");
		try {
			int post_num=Integer.parseInt(post);
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
	
	//¥Ò±€
	//¥Ò±€ ¿€º∫
	@RequestMapping(value="/comm/add.do", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String addComm(HttpSession session, String post, AddCommVO acVO) {
		String json=null;
		try {
			int post_num=Integer.parseInt(post);
			String login_id=(String)session.getAttribute("id");
			acVO.setPost_num(post_num);
			acVO.setId(login_id);
			json=new BlogPostService().addComm(acVO);
		} catch (Exception e) {
			// TODO: handle exception
		}//end catch
		return json;
	}//addComm
	
	//¥Ò±€ ºˆ¡§
	@RequestMapping(value="/comm/modify.do", method=RequestMethod.POST)
	@ResponseBody
	public String modifyComm(String comm, ModifyCommVO mcVO) {
		String json=null;
		try {
			int comm_num=Integer.parseInt(comm);
			mcVO.setComm_num(comm_num);
			json=new BlogPostService().modifyComm(mcVO);
		} catch (Exception e) {
			// TODO: handle exception
		}//end catch
		
		return json;
	}//modifyComm
	
	//¥Ò±€ ªË¡¶
	@RequestMapping(value="/comm/remove.do", method=RequestMethod.POST)
	@ResponseBody
	public String removeComm(HttpSession session, String comm) {
		String json=null;
		String login_id=(String)session.getAttribute("id");
		try {
			int comm_num=Integer.parseInt(comm);
			RemoveCommVO rcVO=new RemoveCommVO();
			rcVO.setComm_num(comm_num);
			rcVO.setId(login_id);
			if(login_id!=null) {
				json=new BlogPostService().reomveComm(rcVO);
			}//end if
		} catch (Exception e) {
			// TODO: handle exception
		}//end catch
		
		return json;
	}//removeComm
	
}//class
