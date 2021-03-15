package kr.co.rgrg.user.blog.service;

import java.util.List;

import org.json.simple.JSONObject;

import kr.co.rgrg.user.blog.dao.BlogPostDAO;
import kr.co.rgrg.user.blog.domain.CommDomain;
import kr.co.rgrg.user.blog.domain.PostDetailDomain;
import kr.co.rgrg.user.blog.domain.PostProfileDomain;
import kr.co.rgrg.user.blog.vo.AddCommVO;
import kr.co.rgrg.user.blog.vo.FollowerVO;
import kr.co.rgrg.user.blog.vo.LikePostVO;
import kr.co.rgrg.user.blog.vo.ModifyCommVO;
import kr.co.rgrg.user.blog.vo.PostDeleteVO;
import kr.co.rgrg.user.blog.vo.RemoveCommVO;

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
		if(!"".equals(id) && id!=null) {
			flag=true;
		}//end if
		return flag;
	}//getFollowFlag
	
	public Boolean getLikeFlag(LikePostVO lpVO) {
		Boolean flag=false;
		String id=BlogPostDAO.getInstance().selectLikePostFlag(lpVO);
		if(!"".equals(id) && id!=null) {
			flag=true;
		}//end if
		return flag;
	}//getLikeFlag
	
	public String addLikePost(LikePostVO lpVO) {
		JSONObject json=new JSONObject();
		json.put("flag", "fail");

		int cnt=BlogPostDAO.getInstance().insertLikePost(lpVO);
		if(cnt==1) {
			json.put("flag", "success");
		}//end if
		
		return json.toJSONString();
	}//addLikePost
	
	public String removeLikePost(LikePostVO lpVO) {
		JSONObject json=new JSONObject();
		json.put("flag", "fail");
		
		int cnt=BlogPostDAO.getInstance().deleteLikePost(lpVO);
		if(cnt==1) {
			json.put("flag", "success");
		}//end if
		
		return json.toJSONString();
	}//removeLikePost
	
	public String removePost(PostDeleteVO pdVO) {
		JSONObject json=new JSONObject();
		json.put("flag", "fail");

		int cnt=BlogPostDAO.getInstance().updatePostDeleteFlag(pdVO);
		if(cnt==1) {
			json.put("flag", "success");
		}//end if
		
		return json.toJSONString();
	}//removePost
	
	//¥Ò±€
	//¥Ò±€ ¿€º∫
	public String addComm(AddCommVO acVO) {
		JSONObject json=new JSONObject();
		json.put("flag", "fail");
		
		CommDomain cDomain=BlogPostDAO.getInstance().insertComm(acVO);
		if(cDomain!=null) {
			json.put("flag", "success");
			json.put("comm_num", cDomain.getComm_num());
			json.put("id", cDomain.getId());
			json.put("comm_content", cDomain.getComm_content());
			json.put("nickname", cDomain.getNickname());
			json.put("profile_img", cDomain.getProfile_img());
			json.put("input_date", cDomain.getInput_date());
		}//end if
		
		return json.toJSONString();
	}//addComm
	
	//¥Ò±€ ºˆ¡§
	public String modifyComm(ModifyCommVO mcVO) {
		JSONObject json=new JSONObject();
		json.put("flag", "fail");
		
		int cnt=BlogPostDAO.getInstance().updateComm(mcVO);
		if(cnt==1) {
			json.put("flag", "success");
		}//end if
		
		return json.toJSONString();
	}//modifyComm
	
	//¥Ò±€ ªË¡¶
	public String reomveComm(RemoveCommVO rcVO) {
		JSONObject json=new JSONObject();
		json.put("flag", "fail");
		
		int cnt=BlogPostDAO.getInstance().updateCommDeleteFlag(rcVO);
		if(cnt==1) {
			json.put("flag", "success");
		}//end if
		
		return json.toJSONString();
	}//removeComm
	
}//class
