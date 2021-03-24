package kr.co.rgrg.user.post.dao;


import java.util.List;

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
	 * �±����̺� �±׵��� ����
	 * @param pVO
	 * @return
	 */
	public int insertTag(ModifyPostVO mpVO) {
		int result = 0;
		
		SqlSession ss = GetRgrgHandler.getInstance().getSqlSession();
		result = ss.insert("insertTag", mpVO);
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
		ss.commit();
		ss.close();
		
		return result;
	}
	
	/**
	 * �±׵� ����
	 * @param mpVO
	 * @return
	 */
	public int deleteTag(String post_num) {
		int result = 0;
		
		SqlSession ss = GetRgrgHandler.getInstance().getSqlSession();
		result = ss.delete("deleteTag", post_num);
		ss.commit();
		ss.close();
		
		return result;
	}
	
	/**
	 * �ӽ������ ���� �ִ��� �˻�
	 * @param id
	 * @return
	 */
	public String selectPublishPost(String id) {
		String post_num = "";
		
		SqlSession ss = GetRgrgHandler.getInstance().getSqlSession();
		post_num = ss.selectOne("selectPublishCnt", id);
		ss.close();
		
		return post_num; 
	}
	
	/**
	 * �ӽ�����, ������ �Խñ��� �ҷ���
	 *  - �±� ��ȸ ���� ���Ծȵ�
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
	 * ����� ���� �±׵� ��ȸ�ϱ�
	 * @param pDomain
	 * @return
	 */
	public List<String> selectTags(PostDomain pDomain) {
		
		
		SqlSession ss = GetRgrgHandler.getInstance().getSqlSession();
		List<String> tag_name = ss.selectList("selectTag", pDomain);
		ss.close();
		
		return tag_name;
	}
	
	/**
	 * �Խñ� ����
	 * @param post_num
	 * @return
	 */
	public int deletePost(String id) {
		
		SqlSession ss = GetRgrgHandler.getInstance().getSqlSession();
		int result = ss.update("deletePost", id);
		ss.commit();
		ss.close();
		
		return result;
	}
	
	/**
	 * �ӽ����� �Խñ� ���� Ȯ��
	 * @param id
	 * @return
	 */
	public int selectTempCnt(String id) {
		SqlSession ss = GetRgrgHandler.getInstance().getSqlSession();
		int result = ss.selectOne("selectTempCnt", id);
		ss.close();
		
		return result;
	}
	
}
