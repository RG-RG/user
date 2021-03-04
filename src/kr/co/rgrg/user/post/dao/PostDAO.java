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
	 * �Խñ� ����
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
	 * �ֱٿ� ����� �Խñ��� �Խñ� ��ȣ�� ��ȸ
	 * - �±׸� ������  �� �ʿ�
	 * @param id
	 * @return
	 */
	public int selectPostNum(String id) {
		int post_num = 0;
		
		// selectKey ����ϴ� ��� �˾ƺ���
		
		return post_num;
	}
	
	/**
	 * �±����̺� �±׵��� ����
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
	 * �Խñ��� ����
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
	 * �±׵��� ����
	 *  - �� �����ϰ� ���� �����ؾ��ϳ�?
	 * @param mpVO
	 * @return
	 */
	public int updateTag(ModifyPostVO mpVO) {
		int result = 0;
		// �� �����ϰ� �ٽ� �ִ� ������� �ؾ����� ������....
		SqlSession ss = GetRgrgHandler.getInstance().getSqlSession();
		result = ss.update("updateTag", mpVO);
		ss.close();
		
		return result;
	}
	
	/**
	 * �ӽ�����, ������ �Խñ��� �ҷ���
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
	 * �Խñ� ����
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
