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

	// 업로드된 파일이 저장될 위치
	private final String PATH = "C:\\Users\\doyeon\\git\\user\\WebContent\\images\\post\\";
	
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
	
	public String saveFile(MultipartFile file) {
		//System.out.println(file.getContentType());
		UUID uuid = UUID.randomUUID(); // 파일 이름 변경
		String fileName = uuid + "_" + file.getOriginalFilename();
		
		logger.info("fileName: {}", fileName);
		
		//저장할  file 객체를 생성(껍데기파일)
		File target = new File(PATH, fileName);
		
		try {
			file.transferTo(target); // 업로드 파일에 tartget이라는 껍데기를 입힘
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
		return fileName;
	}
	
	/**
	 * 게시글 수정
	 * @param mpVO
	 * @return
	 */
	public boolean modifyPost(ModifyPostVO mpVO) {
		boolean result = false;
		
		PostDAO pDAO = PostDAO.getInstance();
		int posting_cnt = pDAO.updatePost(mpVO);
		int delete_tag_cnt = pDAO.deleteTag(mpVO.getPost_num());
		int insert_tag_cnt = pDAO.insertTag(mpVO);
		
		if(posting_cnt + delete_tag_cnt + insert_tag_cnt > 3) {
			result = true;
		}
		
		return result;
	}
	
	/**
	 * 임시저장된 글의 post_num을 검색
	 * @param id
	 * @return
	 */
	public String searchPublishPost(String id) {
		
		PostDAO pDAO = PostDAO.getInstance();
		String post_num = pDAO.selectPublishPost(id);
	
		return post_num;
	}
	
	/**
	 * 게시글 조회, 임시저장된 게시글 조회
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
