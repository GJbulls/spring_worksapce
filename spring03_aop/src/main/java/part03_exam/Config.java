package part03_exam;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


@Configuration
@EnableAspectJAutoProxy
public class Config {
	public Config() {
	
	}
	
	@Bean
	public ServiceImp svc() {
		return new ServiceImp();
	}
	
	@Bean
	public StopWatchAdvcie stopWatch() {
		return new StopWatchAdvcie();
	}
	
}
