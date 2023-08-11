package com.iu.winter.bankBook;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.iu.main.util.Pager;

//저장소 역할 
@Repository //해당 클래스의 객체 생성하세요(스프링한테)
public class BankBookDAO {
	@Autowired
	private SqlSession sqlSession; //SqlSessonTemplate는 SqlSession을 구현한 것 
	//sqlSession은 DB연결, mapper들의 위치만 알고 있음
	
	//어떤 mapper를 사용할지 지정(내가 사용하려는 mapper의 NAMESPACE와 동일하게)
	private final String NAMESPACE="com.iu.main.bankBook.BankBookDAO.";
	
	//total
	public Long getTotal(Pager pager) throws Exception {
		return sqlSession.selectOne(NAMESPACE+"getTotal", pager);
	}
	
	//List
	public List<BankBookDTO> getList(Pager pager) throws Exception {
		return sqlSession.selectList(NAMESPACE+"getList", pager);
	}
	
	//Detail
	public BankBookDTO getDetail(BankBookDTO bankBookDTO) throws Exception {
		//selectOne : executeQuery로 보내는 것 
		//NAMESPACE+id : 어느 mapper의 어느 id를 실행할 것이냐
		//파라미터로 bankBookDTO 
		//파라미터는 안 보내거나 하나만 보낼 수 있다 
		return sqlSession.selectOne(NAMESPACE+"getDetail", bankBookDTO);
	}
	
	public long getSequence() throws Exception {
		return sqlSession.selectOne(NAMESPACE+"getSequence");
	}
	
	//add
	public int setAdd(BankBookDTO bankBookDTO) throws Exception {
		return sqlSession.insert(NAMESPACE+"setAdd", bankBookDTO);
	}
	
	public int setFileAdd(BankFileDTO bankFileDTO) throws Exception {
		return sqlSession.insert(NAMESPACE+"setFileAdd", bankFileDTO);
	}
	
	//update
	public int setUpdate(BankBookDTO bankBookDTO) throws Exception {
		return sqlSession.update(NAMESPACE+"setUpdate", bankBookDTO);
	}
	
	//delete
	public int setDelete(Long num) throws Exception {
		return sqlSession.delete(NAMESPACE+"setDelete", num);
	}
}
