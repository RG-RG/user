package kr.co.rgrg.user.blog.service;

import java.util.List;

import org.json.simple.JSONObject;

import kr.co.rgrg.user.blog.dao.BlogPostDAO;
import kr.co.rgrg.user.blog.domain.CommDomain;
import kr.co.rgrg.user.blog.domain.PostDetailDomain;
import kr.co.rgrg.user.blog.domain.PostProfileDomain;
import kr.co.rgrg.user.blog.vo.FollowerVO;
import kr.co.rgrg.user.blog.vo.PostDeleteVO;

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
	
	public Boolean getFollowFlag(FollowerVO fVO) {
		Boolean flag=false;
		String id=BlogPostDAO.getInstance().selectFollowFlag(fVO);
		if(!"".equals(id)) {
			flag=true;
		}//end if
		return flag;
	}//getFollowFlag
	
	public String removePost(PostDeleteVO pdVO) {
		JSONObject json=new JSONObject();
		json.put("flag", "fail");

		int cnt=BlogPostDAO.getInstance().updatePostDeleteFlag(pdVO);
		if(cnt==1) {
			json.put("flag", "success");
		}//end if
		
		return json.toJSONString();
	}//removePost
	
}//class
