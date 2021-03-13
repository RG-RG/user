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
import kr.co.rgrg.user.pagination.PaginationService;
import kr.co.rgrg.user.pagination.RangeVO;

@Controller
public class FollowController {
	
	/**
	 * 팔로워 목록을 가져오는 일
	 * @param session
	 * @param model
	 * @param page
	 * @return
	 */
	@RequestMapping(value="{url_id}/follow/get_follower", method= {GET, POST})
	public String getFollower(@PathVariable("url_id") String url_id, HttpSession session, Model model) {
		int current_page = 1;
		FollowRangeVO frVO = new FollowRangeVO(current_page);
		frVO.setColumn_value(url_id); //팔로워 목록을 조회할 아이디
		frVO.setColumn_value2((String)session.getAttribute("id")); //현재 세션에 저장되어 있는 아이디
		
		List<FollowDomain> follower_list = new FollowService().getFollowerList(frVO);
		model.addAttribute("follower_list", follower_list);
		
		int end_num = new PaginationService().endNum(current_page);
		model.addAttribute("list_end", end_num);
		int follower_cnt = new FollowService().getFollowerCnt(frVO);
		model.addAttribute("follower_cnt", follower_cnt);
		
		return "follow/follower";
	}//getFollower
	
	/**
	 * 팔로워 목록을 더보기로 가져오는 일
	 * @param session
	 * @param page
	 * @return
	 */
	@RequestMapping(value="{url_id}/follow/get_more_follower", method= POST, produces="application/text; charset=UTF-8")
	@ResponseBody
	public String getMoreFollower(@PathVariable("url_id") String url_id, HttpSession session, String page) {
		String json = "";
		
		int current_page = 1;
		if (page != null) {
			current_page = Integer.parseInt(page);
		}//end if
		FollowRangeVO frVO = new FollowRangeVO(current_page);
		frVO.setColumn_value(url_id); //팔로워 목록을 조회할 아이디
		frVO.setColumn_value2((String)session.getAttribute("id")); //현재 세션에 저장되어 있는 아이디
		
		json = new FollowService().getMoreFollowerList(frVO);
		
		return json;
	}//getMoreFollower
	
	/**
	 * 팔로잉 목록을 가져오는 일
	 * @param session
	 * @param model
	 * @param param_page
	 * @return
	 */
	@RequestMapping(value="{url_id}/follow/get_following", method={GET, POST})
	public String getFollowing(@PathVariable("url_id") String url_id, HttpSession session, Model model, String page) {
		int current_page = 1;
		FollowRangeVO frVO = new FollowRangeVO(current_page);
		frVO.setColumn_value(url_id); //팔로잉 목록을 조회할 아이디
		frVO.setColumn_value2((String)session.getAttribute("id")); //현재 세션에 저장되어 있는 아이디
		
		List<FollowDomain> following_list = new FollowService().getFollowingList(frVO);
		model.addAttribute("following_list", following_list);
		
		int end_num = new PaginationService().endNum(current_page);
		model.addAttribute("list_end", end_num);
		int following_cnt = new FollowService().getFollowingCnt(frVO);
		model.addAttribute("following_cnt", following_cnt);

		return "follow/following";
	}//getFollowing
	
	/**
	 * 팔로잉 목록을 더보기로 가져오는 일
	 * @param session
	 * @param page
	 * @return
	 */
	@RequestMapping(value="{url_id}/follow/get_more_following", method= POST, produces="application/text; charset=UTF-8")
	@ResponseBody
	public String getMoreFollowing(@PathVariable("url_id") String url_id, HttpSession session, String page) {
		String json = "";
		
		int current_page = 1;
		if (page != null) {
			current_page = Integer.parseInt(page);
		}//end if
		FollowRangeVO frVO = new FollowRangeVO(current_page);
		frVO.setColumn_value(url_id); //팔로워 목록을 조회할 아이디
		frVO.setColumn_value2((String)session.getAttribute("id")); //현재 세션에 저장되어 있는 아이디
		
		json = new FollowService().getMoreFollowingList(frVO);
		
		return json;
	}//getMoreFollowing
		
	/**
	 * 팔로우를 하는 일
	 * @param session
	 * @param fVO
	 * @return
	 */
	@RequestMapping(value="{url_id}/follow/follow", method=POST)
	@ResponseBody
	public String follow(@PathVariable("url_id") String url_id, HttpSession session, FollowVO fVO) {
		String json = "";
		fVO.setId((String)session.getAttribute("id"));
		fVO.setFollowing_id(fVO.getFollowing_id()); //클릭 시 얻어오는 아이디
		json = new FollowService().follow(fVO);
		
		return json;
	}//follow
	
	/**
	 * 언팔로우를 하는 일
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
		fVO.setFollowing_id(fVO.getFollowing_id());  //클릭 시 얻어오는 아이디
		json = new FollowService().unfollow(fVO);
		
		return json;
	}//unfollow
	
}//FollowController
