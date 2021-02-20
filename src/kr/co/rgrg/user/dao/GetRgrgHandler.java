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
				//1.xml�� ����(buildpath���� included�� Excluded�� ��� ���� �ؾ� ��)
				String xmlConfig="kr/co/rgrg/user/dao/rgrg_config.xml";
				Reader reader=Resources.getResourceAsReader(xmlConfig);
				//2.MyBatis Framework ����
				ssf=new SqlSessionFactoryBuilder().build(reader);
				reader.close();//xml�� �о���� ��Ʈ���� ���´�.
			}//end if
			ss=ssf.openSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return ss;
	}//getSqlSession
	
}//class
