package kr.co.rgrg.user.main.service;

import java.util.List;

import kr.co.rgrg.user.main.dao.UserMainDAO;
import kr.co.rgrg.user.main.domain.UserMainDomain;

public class UserMainService {
	
	public List<UserMainDomain> getUserMainList() {
		List<UserMainDomain> mainList = null;
		
		UserMainDAO mDAO = UserMainDAO.getInstance();
		mainList = mDAO.selectMainList();
		
		return mainList;
	}

}
