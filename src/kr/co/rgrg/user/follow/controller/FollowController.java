package kr.co.rgrg.user.follow.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FollowController {

	/**
	 * 팔로워 목록을 가져오는 일
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value="", method=GET)
	public String getMyFollower(HttpSession session, Model model) {
		return "";
	}//getMyFollower
	
	/**
	 * 팔로잉 목록을 가져오는 일
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value="", method=GET)
	public String getMyFollowing(HttpSession session, Model model) {
		return "";
	}//getMyFollowing
	
	/**
	 * 팔로우를 하는 일
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value="", method=GET)
	public String follow(HttpSession session, Model model) {
		return "";
	}//follow
	
	/**
	 * 언팔로우를 하는 일
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value="", method=GET)
	public String unfollow(HttpSession session, Model model) {
		return "";
	}//unfollow
	
}//FollowController
