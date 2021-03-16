package kr.co.rgrg.user.blog.service;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import kr.co.rgrg.user.blog.dao.BlogMainDAO;
import kr.co.rgrg.user.blog.domain.BlogMainDomain;
import kr.co.rgrg.user.blog.domain.PostDomain;
import kr.co.rgrg.user.blog.domain.TagDomain;
import kr.co.rgrg.user.blog.vo.BlogMainVO;
import kr.co.rgrg.user.pagination.PaginationDAO;
import kr.co.rgrg.user.pagination.PostRangeVO;

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
	
	public List<PostDomain> getPostList(PostRangeVO prVO){
		List<PostDomain> list=null;
		
		list=BlogMainDAO.getInstance().selectPostList(prVO);
		
		return list;
	}//getPostList
	
	public String getBlogMainMore(PostRangeVO prVO, int cur_page) {
		JSONObject json=new JSONObject();
		json.put("flag", "fail");
		
		List<PostDomain> post_list=BlogMainDAO.getInstance().selectPostList(prVO);
		int total_cnt=PaginationDAO.getInstance().selectTotalPostCnt(prVO);
		if(post_list!=null) {
			json.put("flag", "success");
			json.put("cur_page", cur_page);
			json.put("total_cnt", total_cnt);
			json.put("end_num", prVO.getEnd_num());
			
			if("search".equals(prVO.getColumn_name())) {
				json.put("search_word",prVO.getColumn_value());
			}//end if
			if("tag".equals(prVO.getColumn_name())) {
				json.put("search_tag",prVO.getColumn_value());
			}//end if
			
			JSONArray ja=new JSONArray();
			JSONObject temp=null;
			for(PostDomain pDomain : post_list) {
				temp=new JSONObject();
				temp.put("post_num", pDomain.getPost_num());
				temp.put("thumbnail", pDomain.getThumbnail());
				temp.put("post_title", pDomain.getPost_title());
				temp.put("post_content", pDomain.getPost_content());
				temp.put("input_date", pDomain.getInput_date());
				temp.put("comment_cnt", pDomain.getComment_cnt());
				temp.put("hidden_flag", pDomain.getHidden_flag());
				temp.put("view_cnt", pDomain.getView_cnt());
				JSONArray tempArr=new JSONArray();
				JSONObject tempObj=null;
				for(String tag_name : pDomain.getTag_name()) {
					tempObj=new JSONObject();
					tempObj.put("tag_name", tag_name);
					tempArr.add(tempObj);
				}//end for
				temp.put("tag_list", tempArr);
				ja.add(temp);
			}//end for
			json.put("post_list", ja);
		}//end if
		
		return json.toJSONString();
	}//getBlogMainMore
	
}//class
