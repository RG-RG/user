package kr.co.rgrg.user.post.dao;

import org.apache.ibatis.session.SqlSession;

import kr.co.rgrg.user.dao.GetRgrgHandler;
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
	
	/**
	 * 게시글 저장
	 * @param pVO
	 * @return
	 */
	public int insertPost(PostVO pVO) {
		int result = 0;
		
		SqlSession ss = GetRgrgHandler.getInstance().getSqlSession();
		result = ss.insert("insertPost", pVO);
		ss.commit();
		ss.close();
		
		return result;
	}
	
	/**
	 * 최근에 저장된 게시글의 게시글 번호를 조회
	 * - 태그를 저장할  때 필요
	 * @param id
	 * @return
	 */
	public int selectPostNum(String id) {
		int post_num = 0;
		
		// selectKey 사용하는 방법 알아보기
		
		return post_num;
	}
	
	/**
	 * 태그테이블에 태그들을 저장
	 * @param pVO
	 * @return
	 */
	public int insertTag(PostVO pVO) {
		int result = 0;
		
		SqlSession ss = GetRgrgHandler.getInstance().getSqlSession();
		result = ss.insert("insertTag", pVO);
		ss.commit();
		ss.close();
		
		return result;
	}
	
	/**
	 * 게시글을 수정
	 * @param mpVO
	 * @return
	 */
	public int updatePost(ModifyPostVO mpVO) {
		int result = 0;
		
		SqlSession ss = GetRgrgHandler.getInstance().getSqlSession();
		result = ss.update("updatePost", mpVO);
		ss.close();
		
		return result;
	}
	
	/**
	 * 태그들을 수정
	 *  - 다 삭제하고 새로 저장해야하나?
	 * @param mpVO
	 * @return
	 */
	public int updateTag(ModifyPostVO mpVO) {
		int result = 0;
		// 다 삭제하고 다시 넣는 방식으로 해야하지 않을까....
		SqlSession ss = GetRgrgHandler.getInstance().getSqlSession();
		result = ss.update("updateTag", mpVO);
		ss.close();
		
		return result;
	}
	
	/**
	 * 임시저장, 수정할 게시글을 불러옴
	 * @param post_num
	 * @return
	 */
	public PostDomain selectPost(String post_num) {
		PostDomain pDomain = new PostDomain();
		
		SqlSession ss = GetRgrgHandler.getInstance().getSqlSession();
		pDomain = ss.selectOne("selectPost", post_num);
		ss.close();
		
		return pDomain;
	}
	
	/**
	 * 게시글 삭제
	 * @param post_num
	 * @return
	 */
	public int deletePost(String post_num) {
		int result = 0;
		
		SqlSession ss = GetRgrgHandler.getInstance().getSqlSession();
		result = ss.delete("deletePost", post_num);
		ss.close();
		
		return result;
	}
}
