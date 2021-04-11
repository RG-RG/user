package kr.co.rgrg.user.main.service;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import kr.co.rgrg.user.main.dao.UserMainDAO;
import kr.co.rgrg.user.main.domain.UserMainDomain;
import kr.co.rgrg.user.pagination.PaginationDAO;
import kr.co.rgrg.user.pagination.PaginationService;
import kr.co.rgrg.user.pagination.RangeVO;
import kr.co.rgrg.user.pagination.TotalCntVO;

public class UserMainService {
	
	public List<UserMainDomain> getUserMainList(RangeVO rVO) {
		List<UserMainDomain> mainList = null;
		
		UserMainDAO mDAO = UserMainDAO.getInstance();
		mainList = mDAO.selectMainList(rVO);
		
		return mainList;
	}
	
	
	
	/**
	 * 더보기 눌렀을 때
	 * @param rVO
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String getUserMoreList(RangeVO rVO, int cur_page) {
		JSONObject json=new JSONObject();
		
		json.put("flag", "false");
		
		List<UserMainDomain> mainList = null;
		UserMainDAO mDAO = UserMainDAO.getInstance();
		mainList = mDAO.selectMainList(rVO);
		
		int totalCnt=PaginationDAO.getInstance().selectMainTotalCnt(rVO);
		
		
		if(mDAO!=null) {
			json.put("flag", "success");
			json.put("last_flag", totalCnt > rVO.getEnd_num()); //last_flag==true 이면 마지막 페이지 라는 뜻
			
			JSONArray ja = new JSONArray();
			JSONObject tmp = null;
			
			json.put("cur_page", cur_page);
			json.put("total_cnt", totalCnt);
			json.put("end_num", rVO.getEnd_num());
			
			for(UserMainDomain umd : mainList) {
				tmp = new JSONObject();
				tmp.put("post_num", umd.getPost_num());
				tmp.put("id",umd.getId());
				tmp.put("nickname", umd.getNickname());
				tmp.put("post_title",umd.getPost_title());
				tmp.put("post_content",umd.getPost_content());
				tmp.put("thumbnail",umd.getThumbnail());
				tmp.put("input_date",umd.getInput_date());
				tmp.put("like_cnt",umd.getLike_cnt());
				
				ja.add(tmp);
			}
			json.put("main_list", ja);
		}

		return json.toJSONString();
	}

}
