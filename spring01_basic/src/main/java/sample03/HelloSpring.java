package sample03;

/*
 * factory를 사용하는방법! =>sample03
 * 객체를 생성하는 클래스가 별도로 되어있음. 참조하여 사용하여 결합도를 낮춤
 * 
 * factory를 이용하면 결합도는 인터페이스보다는 낮지만
 * factory가 정확히 구현되어 있지 않으면 참조하는 객체에 영향이 간다.
 */
public class HelloSpring {

	public static void main(String[] args) {
		MessageBean bean = null;
//		bean = MessageFactory.getInstance("ko");
//		display(bean,"스프링");
		
		bean = MessageFactory.getInstance("en");
		display(bean,"Spring");

		
		
	}//end main()
	public static void display(MessageBean bean, String name) {
		bean.sayHello(name);
	}
	
}//end class
