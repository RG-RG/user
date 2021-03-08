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
	 * �ȷο� ����� �������� ��
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
//		rVO.setColumn_value(id); //���� �ִ� �������� ���̵�� ��ȸ
		rVO.setColumn_value("guest1");
		
		List<FollowDomain> follower_list = new FollowService().getFollowerList(rVO);
		model.addAttribute("follower_list", follower_list);
		
		return "follow/follower";
	}//getMyFollower
	
	/**
	 * �ȷ��� ����� �������� ��
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
//		rVO.setColumn_value(id); ���� �ִ� �������� ���̵�
		
		List<FollowDomain> following_list = new FollowService().getFollowingList(rVO);
		model.addAttribute("following_list", following_list);
		
		return "follow/following";
	}//getMyFollowing
	
	/**
	 * �ȷο츦 �ϴ� ��
	 * @param session
	 * @param fVO
	 * @return
	 */
	@RequestMapping(value="/follow/follow.do", method=GET)
	public String follow(HttpSession session, FollowVO fVO) {
		fVO.setId("guest1");
		fVO.setFollowing_id("guest2");
//		fVO.setId((String)session.getAttribute("id"));
//		fVO.setFollowing_id(following_id); //Ŭ�� �� ������ ���̵�
		boolean follow_flag = new FollowService().follow(fVO);
		
		return "follow/follow";
	}//follow
	
	/**
	 * ���ȷο츦 �ϴ� ��
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
//		fVO.setFollowing_id(following_id);  //Ŭ�� �� ������ ���̵�
		boolean unfollow_flag = new FollowService().unfollow(fVO);
		
		return "follow/unfollow";
	}//unfollow
	
}//FollowController
