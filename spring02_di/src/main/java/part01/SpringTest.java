package part01;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/* Spring에서는 "bean = 객체" 라고 정의함. 
 * =============================================================
 * Dependency Injection(DI 의존성 주입)
 * -객체를 직접 생성하지 않고 외부에서 생성된 객체를 주입받아 이용하는 기능이다.
 * -DI는 컨데이너를 통해 서로 결합되어 있는 두 클래스를 분리하고 두 객체간의 관계를 결정해 줌으로써 결합도를 낮추고 유연성 확보를 위해서다.
 * 
 * =============================================================
 * 
 * IoC(Inversion of Control : 제어의 역전/제어의 흐름을 바꾼다.)
 * -객체생성이나 메소드 호출을 개발자가 직접하는 것이 아니라, 스프링 프레임워크에게 제어권을 넘기는 기능이다.
 * -대부분의 프레임워크에서 IoC를 적용하고 있다.
 */
public class SpringTest {

	public static void main(String[] args) {

		String path ="part01/di.xml"; //경로생성
		//ApplicationContext : Bean의 라이프 사이클을 관리해주는 컨테이너이다.
		ApplicationContext context = new ClassPathXmlApplicationContext(path); //di.xml 경로를 가져옴
		
		//20~27행까지 안녕,스프링!!! 실행문장
		MessageBean bean = (MessageBean)context.getBean("mb");
//		display(bean,"스프링");
		display(bean,"Spring");
		
	}//end main()
	
	public static void display(MessageBean bean, String name) {
		bean.sayHello(name);
	}
	
	
}//end class




