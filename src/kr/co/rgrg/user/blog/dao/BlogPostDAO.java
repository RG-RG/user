package kr.co.rgrg.user.blog.dao;

import org.apache.ibatis.session.SqlSession;

import kr.co.rgrg.user.blog.domain.PostDetailDomain;
import kr.co.rgrg.user.dao.GetRgrgHandler;

public class BlogPostDAO {
	
	private static BlogPostDAO bpDAO;
	
	private BlogPostDAO() {
		
	}//BlogPostDAO
	
	public static BlogPostDAO getInstance() {
		if(bpDAO==null) {
			bpDAO=new BlogPostDAO();
		}//end if
		return bpDAO;
	}//getInstance
	
	public PostDetailDomain selectPostDetail(int post_num) {
		
		SqlSession ss=GetRgrgHandler.getInstance().getSqlSession();
		PostDetailDomain pdDomain=ss.selectOne("kr.co.rgrg.user.blogpost.selectPostDetail",post_num);
		pdDomain.setTag_name(ss.selectList("kr.co.rgrg.user.blogpost.selectPostTagList", post_num));
		ss.close();
		
		return pdDomain;
	}//selectPostDetail
	
	
	
}//class
