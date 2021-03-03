package kr.co.rgrg.user.blog.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.rgrg.user.blog.domain.LikeDomain;
import kr.co.rgrg.user.blog.service.LikeService;
import kr.co.rgrg.user.pagination.RangeVO;

@Controller
public class LikeController {
	
	@RequestMapping(value="like/get_like.do", method=GET)
	public String getLikeList(HttpSession session, Model model, String param_page) {
		int current_page=1;
		if(param_page!=null) {
			current_page=Integer.parseInt(param_page);
		}//end if
		
		RangeVO rVO=new RangeVO(current_page);
		rVO.setColumn_name("id");
		rVO.setColumn_value((String)session.getAttribute("id"));
		
		List<LikeDomain> like_list=new LikeService().getLikeList(rVO);
		model.addAttribute("like_list", like_list);
		
		return "like/like";
	}//getLikeList
	
}//class
