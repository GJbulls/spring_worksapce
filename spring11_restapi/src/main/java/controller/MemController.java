package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import dao.MemDAO;
import model.MemDTO;

//http://localhost:8090//myapp/mem/list

/*
 * @Controller + @ResponseBody  ==  @RestController
 */

@RestController
//@Controller
public class MemController {
	
		private MemDAO memDao;
		
		public MemController() {
			
		}
		
		public void setMemDao(MemDAO memDao) {
			this.memDao = memDao;
			
		}
		
		//http://localhost:8090//myapp/mem/list
		
		//@ResponseBody
		@RequestMapping(value="/list", method=RequestMethod.GET)
//		public List<MemDTO> process() {
//				return memDao.list();
//		}
//		public ResponseEntity<List<MemDTO>>process(){
//			List<MemDTO> aList = memDao.list();
//			try {
//			return new ResponseEntity<List<MemDTO>>(aList,HttpStatus.OK);
//			//									   (body, statue값)
//			}catch(Exception e) {
//				return new ResponseEntity<List<MemDTO>>(aList, HttpStatus.NOT_FOUND);
//			}
//		}
		
//		public ResponseEntity<Map<String, Object>> process(){
//		
//			try {
//				List<MemDTO> aList = memDao.list();
//				int cnt = aList.size();
//				
//				Map<String, Object> map = new HashMap<String, Object>();
//				map.put("aList", aList);
//				map.put("cnt", cnt);
//				return ResponseEntity.ok().body(map);
//			}catch(Exception e) {
//				return ResponseEntity.notFound().build();
//		}
//	}
		
		
		
		public ResponseEntity<Result<List<MemDTO>>> process(){
			
			try {
				List<MemDTO> aList = memDao.list();
				return ResponseEntity.ok().body(new Result<>(aList, aList.size()));
			}catch(Exception e) {
				return ResponseEntity.notFound().build();
		}
	}
		static class Result<T>{
			private T data;
			private int cnt;
			
			public Result(T data, int cnt) {
				this.data=data;
				this.cnt=cnt;
			}
			
			public T getData() {
				return data;
			}
			public int getCnt() {
				return cnt;
			}
		}
		
		//http://localhost:8090//myapp/mem/list/102
//		@ResponseBody
//		@RequestMapping(value="/list/{ss}", method=RequestMethod.GET)
//		public MemDTO(@PathVariable("ss") int num) {
//			
//		}
		
		//http://localhost:8090//myapp/mem/list/102/용칠이
		@ResponseBody
		@RequestMapping(value="/list/{ss}/{name}", method=RequestMethod.GET)
		public MemDTO process2(@PathVariable("ss") int num, @PathVariable("name") String name) { //102를 ss로 받고 ss값을 int num으로 넣어준다.
				return memDao.list(new MemDTO(num,name));
		}
		
		
		@ResponseBody
		@RequestMapping(value="/insert",method=RequestMethod.POST) //POSt방식 넘기는법 postman에서 포스트로 변경, body->raw->json 선택->입력창에 insert할 내용입력 후 send
		public ResponseEntity<String> insert(@RequestBody MemDTO dto) { //
			try {
			memDao.insertMethod(dto);
//			return new ResponseEntity<String>("SUCCESS",HttpStatus.CREATED);
			return ResponseEntity.ok().body("SUCCESS"); //권장사항 
			}catch (Exception e) {
				//return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
				return  ResponseEntity.badRequest().body(e.getMessage());//권장사항 
			}
			//sendredirect = redirect:
		}
		
		//@ResponseBody
		@RequestMapping(value="/update",method=RequestMethod.PUT)
		public ResponseEntity<String> update(@RequestBody MemDTO dto) {
			try {
				memDao.updateMethod(dto);
				return new ResponseEntity<String>("SUCCESS",HttpStatus.CREATED);
			}catch (Exception e) {
				return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
			}
		}
		
		@ResponseBody
		@RequestMapping(value="/delete/{num}")
		public ResponseEntity<String> delete(@PathVariable("num") int num) {
			try {
			memDao.deleteMethod(num);
			return new ResponseEntity<String>("SUCCESS", HttpStatus.CREATED);
			}catch (Exception e) {
				return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
			}
		}
		
		
		
}














