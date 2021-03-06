package kr.co.rgrg.user.blog.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.rgrg.user.blog.domain.LikeDomain;
import kr.co.rgrg.user.blog.service.LikeService;
import kr.co.rgrg.user.blog.vo.LikePostVO;
import kr.co.rgrg.user.pagination.PaginationService;
import kr.co.rgrg.user.pagination.RangeVO;
import kr.co.rgrg.user.pagination.TotalCntVO;

@Controller
public class LikeController {
	
	@RequestMapping(value="/like.do", method=RequestMethod.GET)
	public String getLikeList(HttpSession session, Model model) {
		
		int current_page=1;
		
		String login_id=(String)session.getAttribute("id");
		RangeVO rVO=new RangeVO(current_page);
		rVO.setColumn_name("id");
		rVO.setColumn_value(login_id);
		
		List<LikeDomain> like_list=new LikeService().getLikeList(rVO);
		model.addAttribute("like_list", like_list);
		
		//페이지네이션
		TotalCntVO tcVO=new TotalCntVO("like_post", "id", login_id);
		int total_cnt=new PaginationService().getTotalLikeCnt(tcVO);
		model.addAttribute("cur_page", current_page);
		model.addAttribute("end_num",rVO.getEnd_num());
		model.addAttribute("total_cnt", total_cnt);
		
		return "like/like";
	}//getLikeList
	
	@RequestMapping(value="/like/more.do", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public void getMoreLikeList(HttpSession session, String page, HttpServletResponse response) throws IOException {
		String json=null;
		
		int current_page=1;
		if(page!=null) {
			current_page=Integer.parseInt(page);
		}//end if
		
		String login_id=(String)session.getAttribute("id");
		RangeVO rVO=new RangeVO(current_page);
		rVO.setColumn_name("id");
		rVO.setColumn_value(login_id);
		
		json=new LikeService().getMoreLikeList(rVO, current_page);
		System.out.println(json);
		response.setHeader("Content-Type", "application/xml");
		response.setContentType("text/xml;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		response.getWriter().print(json);
		
		//return json;
	}//getMoreLikeList
	
	@RequestMapping(value="/like/add.do", method=RequestMethod.POST)
	@ResponseBody
	public String addLikePost(HttpSession session, String post) {
		String json=null;
		
		String login_id=(String)session.getAttribute("id");
		try {
			int post_num=Integer.parseInt(post);
			LikePostVO lpVO=new LikePostVO();
			lpVO.setId(login_id);
			lpVO.setPost_num(post_num);
			json=new LikeService().addLikePost(lpVO);
		} catch (Exception e) {
			// TODO: handle exception
		}//end catch
		return json;
	}//addLikePost
	
	@RequestMapping(value="/like/remove.do", method=RequestMethod.POST)
	@ResponseBody
	public String removeLikePost(HttpSession session, String post) {
		String json=null;
		String login_id=(String)session.getAttribute("id");
		try {
			int post_num=Integer.parseInt(post);
			LikePostVO lpVO=new LikePostVO();
			lpVO.setId(login_id);
			lpVO.setPost_num(post_num);
			json=new LikeService().removeLikePost(lpVO);
		} catch (Exception e) {
			// TODO: handle exception
		}//end catch
		return json;
	}//removeLikePost
	
}//class
