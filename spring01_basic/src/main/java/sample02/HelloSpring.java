package sample02;
/*
 * Interface를 사용하여 결합도를 낮췄지만 여전히 결합도는 높음=>sample02
 * 더 낮추는 방법이 있다.
 * 
 * 
 * factory를 사용하는방법! =>sample03
 * 객체를 생성하는 클래스가 별도로 되어있음. 참조하여 사용하여 결합도를 낮춤
 */
public class HelloSpring {

	public static void main(String[] args) {
		MessageBean bean = null;
//		bean = new MessageBeanKorea();
//		display(bean,"스프링");
		
		bean = new MessageBeanEnglish();
		display(bean,"Spring");

	}
	public static void display(MessageBean bean, String name) {
		bean.sayHello(name);
	}

}
