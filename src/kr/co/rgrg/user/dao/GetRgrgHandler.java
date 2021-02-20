package kr.co.rgrg.user.dao;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class GetRgrgHandler {
	private static GetRgrgHandler grh;
	private static SqlSessionFactory ssf;
	
	private GetRgrgHandler() {
		
	}//GetRgrgHandler
	
	public static GetRgrgHandler getInstance() {
		if(grh==null) {
			grh=new GetRgrgHandler();
		}//end if
		return grh;
	}//getInstance
	
	public SqlSession getSqlSession() {
		SqlSession ss=null;
		
		try {
			if(ssf==null) {
				//1.xml과 연결(buildpath에서 included와 Excluded룰 모두 삭제 해야 함)
				String xmlConfig="kr/co/rgrg/user/dao/rgrg_config.xml";
				Reader reader=Resources.getResourceAsReader(xmlConfig);
				//2.MyBatis Framework 생성
				ssf=new SqlSessionFactoryBuilder().build(reader);
				reader.close();//xml을 읽어들인 스트림을 끊는다.
			}//end if
			ss=ssf.openSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return ss;
	}//getSqlSession
	
}//class
