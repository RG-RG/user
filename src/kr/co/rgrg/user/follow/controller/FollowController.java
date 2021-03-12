package kr.co.rgrg.user.follow.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.rgrg.user.follow.dao.FollowDAO;
import kr.co.rgrg.user.follow.domain.FollowDomain;
import kr.co.rgrg.user.follow.service.FollowService;
import kr.co.rgrg.user.follow.vo.FollowVO;
import kr.co.rgrg.user.pagination.FollowRangeVO;
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
	@RequestMapping(value="{url_id}/follow/get_follower", method=GET)
	public String getFollower(@PathVariable("url_id") String url_id, HttpSession session, Model model, String param_page) {
		int current_page = 1;
		if (param_page != null) {
			current_page = Integer.parseInt(param_page);
		}//end if
		FollowRangeVO frVO = new FollowRangeVO(current_page);
		frVO.setColumn_value(url_id); //�ȷο� ����� ��ȸ�� ���̵�
		frVO.setColumn_value2((String)session.getAttribute("id")); //���� ���ǿ� ����Ǿ� �ִ� ���̵�
		
		List<FollowDomain> follower_list = new FollowService().getFollowerList(frVO);
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
	@RequestMapping(value="{url_id}/follow/get_following", method=GET)
	public String getFollowing(@PathVariable("url_id") String url_id, HttpSession session, Model model, String param_page) {
		int current_page = 1;
		if (param_page != null) {
			current_page = Integer.parseInt(param_page);
		}//end if
		FollowRangeVO frVO = new FollowRangeVO(current_page);
		frVO.setColumn_value(url_id); //�ȷ��� ����� ��ȸ�� ���̵�
		frVO.setColumn_value2((String)session.getAttribute("id")); //���� ���ǿ� ����Ǿ� �ִ� ���̵�
		
		List<FollowDomain> following_list = new FollowService().getFollowingList(frVO);
		model.addAttribute("following_list", following_list);
		
		return "follow/following";
	}//getMyFollowing
	
	/**
	 * �ȷο츦 �ϴ� ��
	 * @param session
	 * @param fVO
	 * @return
	 */
	@RequestMapping(value="{url_id}/follow/follow", method=POST)
	@ResponseBody
	public String follow(@PathVariable("url_id") String url_id, HttpSession session, FollowVO fVO) {
		String json = "";
		fVO.setId((String)session.getAttribute("id"));
		fVO.setFollowing_id(fVO.getFollowing_id()); //Ŭ�� �� ������ ���̵�
		json = new FollowService().follow(fVO);
		
		return json;
	}//follow
	
	/**
	 * ���ȷο츦 �ϴ� ��
	 * @param session
	 * @param model
	 * @param fVO
	 * @return
	 */
	@RequestMapping(value="{url_id}/follow/unfollow", method=POST)
	@ResponseBody
	public String unfollow(@PathVariable("url_id") String url_id, HttpSession session, Model model, FollowVO fVO) {
		String json = "";
		fVO.setId((String)session.getAttribute("id"));
		fVO.setFollowing_id(fVO.getFollowing_id());  //Ŭ�� �� ������ ���̵�
		json = new FollowService().unfollow(fVO);
		
		return json;
	}//unfollow
	
}//FollowController
