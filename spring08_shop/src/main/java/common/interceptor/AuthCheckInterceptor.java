package common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import members.dto.AuthInfo;

public class AuthCheckInterceptor extends HandlerInterceptorAdapter{
		public AuthCheckInterceptor() {
			
		}
		
		@Override//컨트롤러가 수행되기전
		public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)//요청,응답,handler
			throws Exception {
				//컨트롤러를 수행하게 하려면 true값을 주면 됌 
				//false이며 세션이없으면 null이고,  true이며 세션이 없으면 세션 생성
			HttpSession session = request.getSession(false);
			
			if(session != null) {
				AuthInfo authInfo = (AuthInfo)session.getAttribute("authInfo");
				if(authInfo != null){
					return true; //수행이안되면 로그인 안되어있는 상태
				}
			}
				response.sendRedirect(request.getContextPath()+"/member/login.do"); //로그인이 안되어있는 상태이므로 로그인세션을 불러옴
				return false;
			
		}
		
		@Override//컨트롤러가 수행되기후
		public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		super.postHandle(request, response, handler, modelAndView);
		}
		
		@Override//컨트롤러가 수행되기후 클라이언트로 뷰 페이지를 응답하기 직전
		public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
		super.afterCompletion(request, response, handler, ex);
		}
		
}//end class


















