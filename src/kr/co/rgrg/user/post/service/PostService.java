package kr.co.rgrg.user.post.service;


import kr.co.rgrg.user.post.dao.PostDAO;
import kr.co.rgrg.user.post.domain.PostDomain;
import kr.co.rgrg.user.post.vo.ModifyPostVO;
import kr.co.rgrg.user.post.vo.PostVO;

public class PostService {
	
	
	/**
	 * �Խñ� ����
	 * @param pVO
	 * @return
	 */
	public boolean savePost(PostVO pVO) {
		boolean result = false;
		
		PostDAO pDAO = PostDAO.getInstance();
		if(pDAO.insertPost(pVO) > 0) {
			result = true;
		}
		
		return result;
	}
	
	/**
	 * �Խñ� ����
	 * @param mpVO
	 * @return
	 */
	public boolean modifyPost(ModifyPostVO mpVO) {
		boolean result = false;
		
		PostDAO pDAO = PostDAO.getInstance();
		pDAO.updatePost(mpVO);
		pDAO.deleteTag(mpVO.getPost_num());
		pDAO.insertTag(mpVO);
		
		return result;
	}
	
	/**
	 * �ӽ������ ���� post_num�� �˻�
	 * @param id
	 * @return
	 */
	public String searchPublishPost(String id) {
		
		PostDAO pDAO = PostDAO.getInstance();
		String post_num = pDAO.selectPublishPost(id);
	
		return post_num;
	}
	
	/**
	 * �Խñ� ��ȸ, �ӽ������ �Խñ� ��ȸ
	 * @param post_num
	 * @return
	 */
	public PostDomain searchEditPost(String post_num) {
		PostDomain pDomain = null;
		
		PostDAO pDAO = PostDAO.getInstance();
		pDomain = pDAO.selectPost(post_num);
		pDomain.setTag_name(pDAO.selectTags(pDomain));
		return pDomain;
	}
	

}
