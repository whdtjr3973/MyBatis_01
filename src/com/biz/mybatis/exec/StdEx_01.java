package com.biz.mybatis.exec;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.biz.mybatis.config.DBConncection;
import com.biz.mybatis.dao.StudentDao;
import com.biz.mybatis.model.StudentVO;

public class StdEx_01 {

	public static void main(String[] args) {

		/*
		 * SessionFactory에게 DBMS에 연결할 Session을 요청
		 * Session : DBMS에 연결할 Connection Pool
		 */
		SqlSession sqlSession = DBConncection.getSqlSessionFactory().openSession(true);
		
		/*
		 * Session에서 Mapper를 요청
		 * StudentDao interface를 이용해서
		 * std_mapper.xml에 설정된 SQL과 mapping(합성)하여
		 * StudenDao(Imp) 클래스를 생성하고
		 * 그 클래스로 stdDao를 초기화 해라
		 */
		StudentDao stdDao = (StudentDao) sqlSession.getMapper(StudentDao.class);
		
		List<StudentVO> stdList = null;
		stdList = stdDao.selectAll();
		
		for(StudentVO vo : stdList) {
			System.out.println(vo);
		}
		
		
		
	}

}
