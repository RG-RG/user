package kr.co.rgrg.user.post.service;


import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import kr.co.rgrg.user.post.dao.PostDAO;
import kr.co.rgrg.user.post.domain.PostDomain;
import kr.co.rgrg.user.post.vo.ModifyPostVO;
import kr.co.rgrg.user.post.vo.PostVO;

public class PostService {
	private static final Logger logger = LoggerFactory.getLogger(PostService.class);

	// ���ε�� ������ ����� ��ġ
	private final String PATH = "/var/lib/tomcat9/webapps/upload/post/";
	
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
	 * �ӽ����� �Խñ��� ������ Ȯ���ϰ� �ִٸ� ����
	 * - �ӽ����� �Խñ��� 1���� �����ϱ� ���ؼ�
	 * @param id
	 */
	public void tempCnt(String id) {
		
		PostDAO pDAO = PostDAO.getInstance();
		if(pDAO.selectTempCnt(id) == 1) {
			pDAO.deletePost(id);
		}
	}
	
	/**
	 * �Խñ� ����
	 * @param id
	 * @return
	 */
	public boolean removePost(String post_num) {
		
		PostDAO pDAO = PostDAO.getInstance();
		
		return pDAO.deletePost(post_num) == 1;
	}
	
	public String saveFile(String root_path, MultipartFile file) {
		//System.out.println(file.getContentType());
		UUID uuid = UUID.randomUUID(); // ���� �̸� ����
		String fileName = uuid + "_" + file.getOriginalFilename();
		
		logger.info("fileName: {}", fileName);
		
		//������  file ��ü�� ����(����������)
		File target = new File(PATH, fileName);
		
		try {
			file.transferTo(target); // ���ε� ���Ͽ� tartget�̶�� �����⸦ ����
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
		return fileName;
	}
	
	/**
	 * �Խñ� ����
	 * @param mpVO
	 * @return
	 */
	public boolean modifyPost(ModifyPostVO mpVO) {
		boolean result = false;
		
		PostDAO pDAO = PostDAO.getInstance();
		int posting_cnt = pDAO.updatePost(mpVO);
		
		int delete_tag_cnt = 0;
		int insert_tag_cnt = 0;
		if (mpVO.getTags() != null) {
			delete_tag_cnt = pDAO.deleteTag(mpVO.getPost_num());
			insert_tag_cnt = pDAO.insertTag(mpVO);			
		}
		
		if(posting_cnt + delete_tag_cnt + insert_tag_cnt > 0) {
			result = true;
		}
		
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
