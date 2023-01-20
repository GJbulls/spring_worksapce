package part02_annotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
//@Configuration : 환경설정하는 파일이라고 설정을 의미
//@EnableAspectJAutoProxy : 클래스를 이용해서 환경설정을 잡음
@Configuration
@EnableAspectJAutoProxy
public class Config {
	
	@Bean
	//<bean id="svc" class="part02_annotation.ServiceImp" /> 과 같다.
	public ServiceImp svc() {
		return new ServiceImp();
	}
	
	//<bean id="common" class="part02_annotation.AdviceCommon" />
	@Bean
	public AdviceCommon commone() {
		return new AdviceCommon();
	}
	
	//<bean id="pn" class="part02_annotation.ServiceImp" /> 과 같다.
	@Bean(name = "pn")
	public ServiceImp point() {
		return new ServiceImp();
	}
}
