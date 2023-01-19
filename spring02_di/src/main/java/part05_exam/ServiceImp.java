package part05_exam;
/*
 * 멤버변수선언
 * setter 생성
 */


public class ServiceImp implements Service{
	private MemDaoImp memDao;
	public ServiceImp() {
		
	}
	
	
	public void setMemDao(MemDaoImp memDao) {
		this.memDao = memDao;
	}
	@Override
	public void prn() {
		memDao.selectMethod();
		
	}
}
