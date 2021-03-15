package kr.co.rgrg.user.blog.service;

import java.util.List;

import kr.co.rgrg.user.blog.dao.BlogMainDAO;
import kr.co.rgrg.user.blog.domain.BlogMainDomain;
import kr.co.rgrg.user.blog.domain.TagDomain;
import kr.co.rgrg.user.blog.vo.BlogMainVO;

public class BlogMainService {
	
	public BlogMainDomain getBlogProfile(BlogMainVO bmVO) {
		BlogMainDomain bmDomain=null;
		
		bmDomain=BlogMainDAO.getInstance().selectBlogProfile(bmVO);
		
		return bmDomain;
	}//getProfile
	
	public List<TagDomain> getTagList(BlogMainVO bmVO){
		List<TagDomain> list=null;
		
		list=BlogMainDAO.getInstance().selectTagList(bmVO);
		
		return list;
	}//getTagList
	
}//class
