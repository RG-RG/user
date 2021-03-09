package kr.co.rgrg.user.blog.service;

import java.util.List;

import kr.co.rgrg.user.blog.dao.BlogPostDAO;
import kr.co.rgrg.user.blog.domain.CommDomain;
import kr.co.rgrg.user.blog.domain.PostDetailDomain;
import kr.co.rgrg.user.blog.domain.PostProfileDomain;

public class BlogPostService {
	
	public PostDetailDomain getPostDetail(int post_num) {
		PostDetailDomain pdDomain=null;
		pdDomain=BlogPostDAO.getInstance().selectPostDetail(post_num);
		
		return pdDomain;
	}//PostDetailDomain
	
	public PostProfileDomain getPostProfile(int post_num) {
		PostProfileDomain ppDomain=null;
		ppDomain=BlogPostDAO.getInstance().selectPostProfile(post_num);
		
		return ppDomain;
	}//getPostProfile
	
	public List<CommDomain> getCommList(int post_num) {
		List<CommDomain> list=null;
		list=BlogPostDAO.getInstance().selectCommList(post_num);
		
		return list;
	}//getCommList
	
}//class
