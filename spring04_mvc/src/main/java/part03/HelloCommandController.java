package part03;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

//http://localhost:8090/myapp/mem.htm

@Controller
public class HelloCommandController {
	
	public HelloCommandController() {
	
	}
	
	@GetMapping("/mem.htm")
	//@RequestMapping(value="/mem.htm",method=RequestMethod.GET)
	public String form() {
		return "part03/memForm"; //GET방식으로 사용됬을때 part03/memForm 사용 
	}
	
//	 @PostMapping("/mem.htm")
//	//@RequestMapping(value="/mem.htm",method=RequestMethod.POST)
//	public ModelAndView submit(String name, int age, ModelAndView mav) {
//		mav.addObject("name",name);
//		mav.addObject("age",age);
//		mav.setViewName("part03/memPro");
//		
//		return mav;
//
	
	
//	 @PostMapping("/mem.htm")
//	//@RequestMapping(value="/mem.htm",method=RequestMethod.POST)
//	public String submit(MemDTO dto) { //dto 선언 되어있는값을 return에서 바로 사용할수잇다
//		
//		return "part03/memPro";
//	}
	 
	 
	 @PostMapping("/mem.htm")
		//@RequestMapping(value="/mem.htm",method=RequestMethod.POST)
		public String submit(@ModelAttribute("mt") MemDTO dto) { 
			
			return "part03/memPro";
		}
	
}



















