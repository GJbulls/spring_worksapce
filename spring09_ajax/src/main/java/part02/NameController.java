package part02;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

//http://localhost:8090/myapp/name.do


@Controller
public class NameController {
	
	public NameController() {
		
	}
	
	@RequestMapping(value="/name.do", method=RequestMethod.GET)
	public String execute() {
		return "part02/form";
	}
	
	//버튼을 클릭하면 처리되도록
	@RequestMapping(value="/name.do",method=RequestMethod.POST)
	public ModelAndView process(String name, ModelAndView mav) {
		mav.addObject("name",name);
		mav.setViewName("part02/res");
		return mav;
	}
	
	
}//class end
