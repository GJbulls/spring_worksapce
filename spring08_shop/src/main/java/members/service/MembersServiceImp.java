package members.service;
import board.dao.BoardDAO;
//dao를 호출한다.
import common.exception.WrongEmailPasswordException;
import members.dao.MembersDAO;
import members.dto.AuthInfo;
import members.dto.ChangePwdCommand;
import members.dto.MembersDTO;

public class MembersServiceImp implements MembersService {
	private MembersDAO membersDao;
	
	public MembersServiceImp() {
	
	}
	
	public void setMembersDao(MembersDAO membersDao) {
		this.membersDao = membersDao;
	}

	@Override
	public AuthInfo addMemberProcess(MembersDTO dto) {
		membersDao.insertMember(dto);
		return new AuthInfo(dto.getMemberEmail(),dto.getMemberName(),dto.getMemberPass());
	}

	@Override
	public AuthInfo loginProcess(MembersDTO dto) {
		
		MembersDTO member = membersDao.selectByEmail(dto.getMemberEmail());
		if(member==null) {
			//System.out.println("회원이 아닙니다.");
			throw new WrongEmailPasswordException();
		}
		
		if(!member.matchPassword(dto.getMemberPass())) {
			//System.out.println("비밀번호가 다릅니다.");
			throw new WrongEmailPasswordException();
		}
		
		return new AuthInfo(member.getMemberEmail(), member.getMemberName(), member.getMemberPass());
	}

	@Override
	public MembersDTO updateMemberProcess(String memberEmail) {
		
		return membersDao.selectByEmail(memberEmail); //email에대한 데이터값을 가져옴 dao로부터
	}

	@Override
	public AuthInfo updateMemberProcess(MembersDTO dto) { //dao에서 메소드 불러옴
		membersDao.updateMember(dto);
		MembersDTO member = membersDao.selectByEmail(dto.getMemberEmail());
		return new AuthInfo(member.getMemberEmail(), member.getMemberName(), member.getMemberPass());
	}

	@Override
	public void updatePassProcess(String memberEmail, ChangePwdCommand changePwd) {
		MembersDTO member = membersDao.selectByEmail(memberEmail);  //email정보를  MembersDTO member여기서 받는다.
		if(member == null)
			throw new WrongEmailPasswordException();
		
		member.changePassword(changePwd.getCurrentPassword(),changePwd.getNewPassword());
		membersDao.updateByPass(member);

		
	}
}



