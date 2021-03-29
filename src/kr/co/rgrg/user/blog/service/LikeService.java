package kr.co.rgrg.user.blog.service;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import kr.co.rgrg.user.blog.dao.LikeDAO;
import kr.co.rgrg.user.blog.domain.LikeDomain;
import kr.co.rgrg.user.pagination.PaginationService;
import kr.co.rgrg.user.pagination.RangeVO;
import kr.co.rgrg.user.pagination.TotalCntVO;

public class LikeService {
	
	public List<LikeDomain> getLikeList(RangeVO rVO){
		List<LikeDomain> list=null;
		
		LikeDAO lDAO=LikeDAO.getInstance();
		list=lDAO.selectLikeList(rVO);
		
		return list;
	}//getLikeList
	
	public String getMoreLikeList(RangeVO rVO, int cur_page) {
		JSONObject json=new JSONObject();
		json.put("flag", "fail");
		
		List<LikeDomain> list=LikeDAO.getInstance().selectLikeList(rVO);
		if(list!=null) {
			json.put("flag", "success");
			json.put("cur_page",cur_page);
			json.put("end_num",rVO.getEnd_num());
			TotalCntVO tcVO=new TotalCntVO("like_post", "id", rVO.getColumn_value());
			int total_cnt=new PaginationService().getTotalLikeCnt(tcVO);
			json.put("total_cnt",total_cnt);
			
			JSONArray ja=new JSONArray();
			JSONObject temp=null;
			for(LikeDomain lDomain : list) {
				temp=new JSONObject();
				temp.put("id", lDomain.getId());
				temp.put("input_date", lDomain.getInput_date());
				temp.put("like_cnt", lDomain.getLike_cnt());
				temp.put("nickname", lDomain.getNickname());
				temp.put("post_content", lDomain.getPost_content());
				temp.put("post_num", lDomain.getPost_num());
				temp.put("post_title", lDomain.getPost_title());
				temp.put("thumbnail", lDomain.getThumbnail());
				
				ja.add(temp);
			}//end for
			json.put("like_list", ja);
		}//end if
		
		return json.toJSONString();
	}//getMoreLikeList
	
}//class
