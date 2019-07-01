package com.biz.mybatis.service;

import java.util.List;
import java.util.Scanner;

import org.apache.ibatis.session.SqlSession;

import com.biz.mybatis.config.DBConncection;
import com.biz.mybatis.dao.ScoreDao;
import com.biz.mybatis.model.ScoreVO;

public class ScoreService {

	private String st_num = null;
	Scanner scan = null;

	SqlSession sqlSession = null;
	ScoreDao scDao = null;
	
	public ScoreService() {
		scan = new Scanner(System.in);
		sqlSession = DBConncection.getSqlSessionFactory().openSession();
		scDao = (ScoreDao)sqlSession.getMapper(ScoreDao.class);
	}
	public void setSt_num(String st_num) {
		this.st_num = st_num;
	}
	
	public void viewScore(String st_num) {
		if(st_num == null) {
			System.out.println("학번을 입력해야 합니다.");
			return;
		}
		System.out.println(st_num + " 학생의 성적리스트");
		System.out.println("-----------------------------");
		System.out.println("과목\t\t점수");
		System.out.println("-----------------------------");
		
		List<ScoreVO> scList = scDao.findByNum(st_num);
		for(ScoreVO vo : scList) {
			System.out.printf("%s\t\t%3d\n", vo.getSc_subject(),vo.getSc_score());
		}
		System.out.println("-----------------------------");
	}
	
	public void insertScore(String st_num) {
		while(true) {
		System.out.print("과목 (-E:종료) >>");
		String strSub = scan.nextLine();
		if(strSub.equals("-E")) break;
		
		System.out.print("점수 >>");
		String strScore = scan.nextLine();
		
		try {
			int intScore = Integer.valueOf(strScore);
			ScoreVO vo = new ScoreVO((long) 0,st_num,strSub,intScore);
			scDao.insert(vo);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("점수는 숫자로만 입력하세요");
			continue;
		}
		
		}
	}
}
