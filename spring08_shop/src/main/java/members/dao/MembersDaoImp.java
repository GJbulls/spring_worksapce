package members.dao;
//서비스로 내보냄
//mapper에서 내보내서 dao에서 받음1
import org.apache.ibatis.session.SqlSession;

import members.dto.MembersDTO;

public class MembersDaoImp implements MembersDAO{
	private SqlSession sqlSession;
	
	public MembersDaoImp() {
		// TODO Auto-generated constructor stub
	}

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	@Override
	public int insertMember(MembersDTO dto) {
		return sqlSession.insert("members.insertMember",dto);
	}

	@Override
	public MembersDTO selectByEmail(String memberEmail) {
		return sqlSession.selectOne("members.selectByEmail",memberEmail);
	}

	@Override
	public void updateMember(MembersDTO dto) {
		sqlSession.update("members.updateMember",dto); //서비스에서 메소드 호출시킨다.
	}

	@Override
	public void updateByPass(MembersDTO dto) {
		sqlSession.update("members.updateByPass",dto);
		
	}
}
