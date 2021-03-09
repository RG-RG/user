package kr.co.rgrg.user.blog.service;

import kr.co.rgrg.user.blog.dao.BlogPostDAO;
import kr.co.rgrg.user.blog.domain.PostDetailDomain;

public class BlogPostService {
	
	public PostDetailDomain getPostDetail(int post_num) {
		PostDetailDomain pdDomain=null;
		pdDomain=BlogPostDAO.getInstance().selectPostDetail(post_num);
		
		return pdDomain;
	}//PostDetailDomain
	
	
	
}//class
