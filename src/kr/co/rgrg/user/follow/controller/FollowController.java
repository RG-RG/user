package kr.co.rgrg.user.follow.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.rgrg.user.follow.dao.FollowDAO;
import kr.co.rgrg.user.follow.domain.FollowDomain;
import kr.co.rgrg.user.follow.service.FollowService;
import kr.co.rgrg.user.follow.vo.FollowVO;
import kr.co.rgrg.user.pagination.RangeVO;

@Controller
public class FollowController {
	
	/**
	 * 팔로워 목록을 가져오는 일
	 * @param session
	 * @param model
	 * @param param_page
	 * @return
	 */
	@RequestMapping(value="/follow/get_follower.do", method=GET)
	public String getFollower(HttpSession session, Model model, String param_page) {
		int current_page = 1;
		if (param_page != null) {
			current_page = Integer.parseInt(param_page);
		}//end if
		RangeVO rVO = new RangeVO(current_page);
//		rVO.setColumn_name(column_name);
//		rVO.setColumn_value(id); //현재 있는 페이지의 아이디로 조회
		rVO.setColumn_value("guest1");
		
		List<FollowDomain> follower_list = new FollowService().getFollowerList(rVO);
		model.addAttribute("follower_list", follower_list);
		
		return "follow/follower";
	}//getMyFollower
	
	/**
	 * 팔로잉 목록을 가져오는 일
	 * @param session
	 * @param model
	 * @param param_page
	 * @return
	 */
	@RequestMapping(value="/follow/get_following.do", method=GET)
	public String getFollowing(HttpSession session, Model model, String param_page) {
		int current_page = 1;
		if (param_page != null) {
			current_page = Integer.parseInt(param_page);
		}//end if
		RangeVO rVO = new RangeVO(current_page);
		rVO.setColumn_value("guest2");
//		rVO.setColumn_value(id); 현재 있는 페이지의 아이디
		
		List<FollowDomain> following_list = new FollowService().getFollowingList(rVO);
		model.addAttribute("following_list", following_list);
		
		return "follow/following";
	}//getMyFollowing
	
	/**
	 * 팔로우를 하는 일
	 * @param session
	 * @param fVO
	 * @return
	 */
	@RequestMapping(value="/follow/follow.do", method=GET)
	public String follow(HttpSession session, FollowVO fVO) {
		fVO.setId("guest1");
		fVO.setFollowing_id("guest2");
//		fVO.setId((String)session.getAttribute("id"));
//		fVO.setFollowing_id(following_id); //클릭 시 얻어오는 아이디
		boolean follow_flag = new FollowService().follow(fVO);
		
		return "follow/follow";
	}//follow
	
	/**
	 * 언팔로우를 하는 일
	 * @param session
	 * @param model
	 * @param fVO
	 * @return
	 */
	@RequestMapping(value="/follow/unfollow.do", method=GET)
	public String unfollow(HttpSession session, Model model, FollowVO fVO) {
		fVO.setId("guest1");
		fVO.setFollowing_id("guest2");
//		fVO.setId((String)session.getAttribute("id"));
//		fVO.setFollowing_id(following_id);  //클릭 시 얻어오는 아이디
		boolean unfollow_flag = new FollowService().unfollow(fVO);
		
		return "follow/unfollow";
	}//unfollow
	
}//FollowController
