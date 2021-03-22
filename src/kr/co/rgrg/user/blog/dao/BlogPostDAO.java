package kr.co.rgrg.user.blog.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.co.rgrg.user.blog.domain.CommDomain;
import kr.co.rgrg.user.blog.domain.PostDetailDomain;
import kr.co.rgrg.user.blog.domain.PostProfileDomain;
import kr.co.rgrg.user.blog.vo.AddCommVO;
import kr.co.rgrg.user.blog.vo.FollowerVO;
import kr.co.rgrg.user.blog.vo.LikePostVO;
import kr.co.rgrg.user.blog.vo.ModifyCommVO;
import kr.co.rgrg.user.blog.vo.PostDeleteVO;
import kr.co.rgrg.user.blog.vo.RemoveCommVO;
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
		if(pdDomain!=null) {
			List<String> list=ss.selectList("kr.co.rgrg.user.blogpost.selectPostTagList", post_num);
			pdDomain.setTag_name(list);
		}//end if
		ss.close();
		
		return pdDomain;
	}//selectPostDetail
	
	public PostProfileDomain selectPostProfile(int post_num) {
		
		SqlSession ss=GetRgrgHandler.getInstance().getSqlSession();
		PostProfileDomain ppDomain=ss.selectOne("kr.co.rgrg.user.blogpost.selectPostProfile",post_num);
		ss.close();
		
		return ppDomain;
	}//selectPostProfile
	
	public List<CommDomain> selectCommList(int post_num){
		
		SqlSession ss=GetRgrgHandler.getInstance().getSqlSession();
		List<CommDomain> list=ss.selectList("kr.co.rgrg.user.blogpost.selectCommList",post_num);
		ss.close();
		
		return list;
	}//selectCommList
	
	public String selectFollowFlag(FollowerVO fVO) {
		
		SqlSession ss=GetRgrgHandler.getInstance().getSqlSession();
		String id=ss.selectOne("kr.co.rgrg.user.blogpost.selectFollow",fVO);
		ss.close();
		
		return id;
	}//selectFollowFlag
	
	public String selectLikePostFlag(LikePostVO lpVO) {
		
		SqlSession ss=GetRgrgHandler.getInstance().getSqlSession();
		String id=ss.selectOne("kr.co.rgrg.user.blogpost.selectLikePost",lpVO);
		ss.close();
		
		return id;
	}//selectLikePostFlag
	
	public int insertLikePost(LikePostVO lpVO) {
		
		SqlSession ss=GetRgrgHandler.getInstance().getSqlSession();
		int cnt=ss.insert("kr.co.rgrg.user.blogpost.insertLikePost",lpVO);
		ss.commit();
		ss.close();
		
		return cnt;
	}//insertLikePost
	
	public int deleteLikePost(LikePostVO lpVO) {
		
		SqlSession ss=GetRgrgHandler.getInstance().getSqlSession();
		int cnt=ss.delete("kr.co.rgrg.user.blogpost.deleteLikePost",lpVO);
		ss.commit();
		ss.close();
		
		return cnt;
	}//deleteLikePost
	
	public int updatePostDeleteFlag(PostDeleteVO pdVO) {
		
		SqlSession ss=GetRgrgHandler.getInstance().getSqlSession();
		int cnt=ss.update("kr.co.rgrg.user.blogpost.updatePostDeletFlag",pdVO);
		ss.commit();
		ss.close();
		
		return cnt;
	}//deletePost
	
	//���
	//��� �ۼ�
	public CommDomain insertComm(AddCommVO acVO) {
		CommDomain cDomain=null;
		
		SqlSession ss=GetRgrgHandler.getInstance().getSqlSession();
		int cnt=ss.insert("kr.co.rgrg.user.blogpost.insertComm",acVO);
		if(cnt==1) {
			cDomain=ss.selectOne("kr.co.rgrg.user.blogpost.selectAddComm",acVO);
		}//end if
		System.out.println(cDomain);
		ss.commit();
		ss.close();
		
		return cDomain;
	}//insertComm
	
	//��� ����
	public int updateComm(ModifyCommVO mcVO) {
		
		SqlSession ss=GetRgrgHandler.getInstance().getSqlSession();
		int cnt=ss.update("kr.co.rgrg.user.blogpost.updateComm",mcVO);
		ss.commit();
		ss.close();
		
		return cnt;
	}//updateComm
	
	//��� ����
	public int updateCommDeleteFlag(RemoveCommVO rcVO) {
		
		SqlSession ss=GetRgrgHandler.getInstance().getSqlSession();
		int cnt=ss.update("kr.co.rgrg.user.blogpost.updateCommDeleteFlag",rcVO);
		ss.commit();
		ss.close();
		System.out.println(cnt);
		return cnt;
	}//updateCommDeleteFlag
	
}//class
