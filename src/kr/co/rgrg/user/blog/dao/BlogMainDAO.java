package kr.co.rgrg.user.blog.dao;


public class BlogMainDAO {
	
	private static BlogMainDAO bmDAO;
	
	private BlogMainDAO() {
	}//BlogMainDAO
	
	public static BlogMainDAO getInstance() {
		if(bmDAO==null) {
			bmDAO=new BlogMainDAO();
		}//end if
		return bmDAO;
	}//getInstance
	
//	public ProfileDomain selectProfile(String id) {
//		ProfileDomain pDomain=null;
//		
//		return pDomain;
//	}//selectProfile
	
}//class
