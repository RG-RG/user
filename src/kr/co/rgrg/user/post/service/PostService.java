package kr.co.rgrg.user.post.service;


import kr.co.rgrg.user.post.dao.PostDAO;
import kr.co.rgrg.user.post.domain.PostDomain;
import kr.co.rgrg.user.post.vo.ModifyPostVO;
import kr.co.rgrg.user.post.vo.PostVO;

public class PostService {
	
	/**
	 * 게시글 저장
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
	 * 게시글 수정
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
	 * 게시글 조회
	 * @param post_num
	 * @return
	 */
	public PostDomain displayPost(String post_num) {
		PostDomain pDomain = null;
		
		PostDAO pDAO = PostDAO.getInstance();
		pDomain = pDAO.selectPost(post_num);
		
		return pDomain;
	}
	
	/**
	 * 게시글 삭제
	 * @param post_num
	 * @return
	 */
	public boolean deletePost(String post_num) {
		boolean result = false;
		
		PostDAO pDAO = PostDAO.getInstance();
		if(pDAO.deletePost(post_num) + pDAO.deleteTag(post_num) > 0) {
			result = true;
		}
		return result;
	}
}
