package kr.co.rgrg.user.main.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.co.rgrg.user.dao.GetRgrgHandler;
import kr.co.rgrg.user.main.domain.UserMainDomain;
import kr.co.rgrg.user.pagination.RangeVO;

public class UserMainDAO {
	
	private static UserMainDAO umDAO;
	
	public UserMainDAO() {
		
	}
	
	public static UserMainDAO getInstance() {
		if (umDAO == null) {
			umDAO = new UserMainDAO();
		}
		return umDAO;
	} //getInstance
	
	
	public List<UserMainDomain> selectMainList(RangeVO rVO) {
		List<UserMainDomain> mainList = null;
		
		SqlSession ss = GetRgrgHandler.getInstance().getSqlSession();
		mainList = ss.selectList("kr.co.rgrg.user.main.selectMainList",rVO);
		ss.close();
		
		return mainList;
	}

}
