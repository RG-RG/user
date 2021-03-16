package kr.co.rgrg.user.blog.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.co.rgrg.user.blog.domain.BlogMainDomain;
import kr.co.rgrg.user.blog.domain.PostDomain;
import kr.co.rgrg.user.blog.domain.TagDomain;
import kr.co.rgrg.user.blog.vo.BlogMainVO;
import kr.co.rgrg.user.dao.GetRgrgHandler;
import kr.co.rgrg.user.pagination.PostRangeVO;

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
	
	public BlogMainDomain selectBlogProfile(BlogMainVO bmVO) {
		BlogMainDomain pDomain=null;
		
		SqlSession ss=GetRgrgHandler.getInstance().getSqlSession();
		pDomain=ss.selectOne("kr.co.rgrg.user.blogmain.selectProfile",bmVO);
		ss.close();
		
		return pDomain;
	}//selectBlogProfile
	
	public List<TagDomain> selectTagList(BlogMainVO bmVO) {
		List<TagDomain> list=null;
		
		SqlSession ss=GetRgrgHandler.getInstance().getSqlSession();
		list=ss.selectList("kr.co.rgrg.user.blogmain.selectTagList",bmVO);
		ss.close();
		
		return list;
	}//selectTagList
	
	public List<PostDomain> selectPostList(PostRangeVO prVO){
		List<PostDomain> list=null;
		
		SqlSession ss=GetRgrgHandler.getInstance().getSqlSession();
		if("tag".equals(prVO.getColumn_name())) {
			list=ss.selectList("kr.co.rgrg.user.blogmain.selectPostListTagSearch",prVO);
		}else {
			list=ss.selectList("kr.co.rgrg.user.blogmain.selectPostList",prVO);
		}//end else
		
		if(list!=null) {
			List<String> tag_list=null;
			for(PostDomain pDomain : list) {
				tag_list=ss.selectList("kr.co.rgrg.user.blogmain.selectPostTagList",pDomain.getPost_num());
				pDomain.setTag_name(tag_list);
			}//end for
		}//end if
		ss.close();
		
		return list;
	}//selectPostList
	
}//class
