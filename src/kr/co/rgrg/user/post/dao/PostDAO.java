package kr.co.rgrg.user.post.dao;

import kr.co.rgrg.user.post.domain.PostDomain;
import kr.co.rgrg.user.post.vo.ModifyPostVO;
import kr.co.rgrg.user.post.vo.PostVO;

public class PostDAO {
	private static PostDAO pDAO;
	
	public PostDAO() {
		org.apache.ibatis.logging.LogFactory.useLog4JLogging();
	}
	
	public static PostDAO getInstance() {
		if(pDAO == null) {
			pDAO = new PostDAO();
		}//end if
		
		return pDAO;
	}//getInstance
	
	public int insertPost(PostVO pVO) {
		
		return 0;
	}
	
	public String selectPostNum(String post_num) {
		
		return "";
	}
	
	public int insertTag(PostVO pVO) {
		
		return 0;
	}
	
	public int updatePost(ModifyPostVO mpVO) {
		
		return 0;
	}
	
	public int updateTag(ModifyPostVO mpVO) {
		
		return 0;
	}
	
	public PostDomain selectPost(int post_num) {
		PostDomain pDomain = new PostDomain();
		
		return pDomain;
	}
	
	public int deletePost(int post_num) {
		
		return 0;
	}
}
