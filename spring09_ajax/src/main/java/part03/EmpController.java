package part03;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

//http://localhost:8090/myapp/empsearch.do


@Controller
public class EmpController {
	
	private EmployeesDAO dao;
	
	public EmpController() {
		
	}
	
	public void setDao(EmployeesDAO dao) {
		this.dao = dao;
	}
	
	
	@RequestMapping(value="/empsearch.do", method=RequestMethod.GET)
	public String execute() {
		return "part03/empList";
	}

	
	//DB에서 들고온 데이터형 그래도 넘겨주는 방법
	@RequestMapping(value="/process.do")
	@ResponseBody  //java를 json형태로 바꿔줌
	public List<EmployeesDTO> process(String data){
//		System.out.println(dao.search(data));
		
		
		return dao.search(data); // EmployeesDTO값은 객체 상속값(리터럴값)으로 바뀌고 jquery로 넘어갈땐 배열값으로 바뀌어서 넘어간다.
	}
	
}








































