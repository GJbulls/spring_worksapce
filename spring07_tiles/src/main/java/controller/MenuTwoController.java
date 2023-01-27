package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//http://localhost:8090/myapp/menu2.do

@Controller
public class MenuTwoController {
	
	@RequestMapping("/menu2.do")
	public String execute() {
		return "menu2";
	}
}
