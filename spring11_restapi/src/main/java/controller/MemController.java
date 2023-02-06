package controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import dao.MemDAO;
import model.MemDTO;

//http://localhost:8090//myapp/mem/list


@Controller
public class MemController {
	
		private MemDAO memDao;
		
		public MemController() {
			
		}
		
		public void setMemDao(MemDAO memDao) {
			this.memDao = memDao;
			
		}
		
		//http://localhost:8090//myapp/mem/list
		
		@ResponseBody
		@RequestMapping(value="/list", method=RequestMethod.GET)
		public List<MemDTO> process() {
				return memDao.list();
		}
		
		//http://localhost:8090//myapp/mem/list/102
		@ResponseBody
		@RequestMapping(value="/list/{ss}", method=RequestMethod.GET)
		public MemDTO process2(@PathVariable("ss") int num) { //102를 ss로 받고 ss값을 int num으로 넣어준다.
				return memDao.one(num);
		}
		
		
		
		@RequestMapping(value="/insert.do",method=RequestMethod.GET)
		public String insert() {
			return "mem/insert";
		}
		
		
		@RequestMapping(value="/insert.do",method=RequestMethod.POST)
		public String insert(MemDTO dto) {
			memDao.insertMethod(dto);
			return "redirect:/list.do";
			//sendredirect = redirect:
		}
		
		
		@RequestMapping(value="/update.do",method=RequestMethod.GET)
		public ModelAndView update(int num, ModelAndView mav) {
			mav.addObject("dto",memDao.one(num));
			mav.setViewName("mem/update");
			return mav;
		}
		
		
		@RequestMapping(value="/update.do",method=RequestMethod.POST)
		public String update(MemDTO dto) {
			memDao.updateMethod(dto);
			return "redirect:/list.do";
		}
		
		@RequestMapping(value="/delete.do")
		public String delete(int num) {
			memDao.deleteMethod(num);
			return "redirect:/list.do";
		}
		
		
		
}














