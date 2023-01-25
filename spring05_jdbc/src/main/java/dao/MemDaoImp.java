package dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import model.MemDTO;

public class MemDaoImp implements MemDAO {
	
	private SqlSessionTemplate sqlSession;
	
	public MemDaoImp() {
		// TODO Auto-generated constructor stub
	}
	
	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	

	@Override //MemDAO상속 받음 오버라이드 시켜줌
	public List<MemDTO> list() {
		return sqlSession.selectList("mem.all");
	}

	@Override //MemDAO상속 받음 오버라이드 시켜줌
	public void insertMethod(MemDTO dto) {
		sqlSession.insert("mem.ins",dto);
		
	}

	@Override //MemDAO상속 받음 오버라이드 시켜줌
	public MemDTO updateMethod(int num) {
		return null;
		
	}

	@Override //MemDAO상속 받음 오버라이드 시켜줌
	public void updateMethod(MemDTO dto) {
		sqlSession.update("mem.upt",dto);
		
	}

	@Override //MemDAO상속 받음 오버라이드 시켜줌
	public void deleteMethod(int num) {
		sqlSession.delete("mem.del",num);
		
	}

	@Override //MemDAO상속 받음 오버라이드 시켜줌
	public MemDTO one(int num) {
		return sqlSession.selectOne("mem.one",num);
	}

	@Override //MemDAO상속 받음 오버라이드 시켜줌
	public int countMethod() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
