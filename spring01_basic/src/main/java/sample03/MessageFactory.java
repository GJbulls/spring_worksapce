package sample03;
/*
 * factory라는 클래스를 생성하여 다른 클래스에 값을 참조한다.
 * 
 * 
 */
public class MessageFactory {
	public MessageFactory() {
	
	}
	public static MessageBean getInstance(String type) {
		if(type.equals("ko")) {
			return new MessageBeanKorea();
		}else if(type.equals("en")) {
			return new MessageBeanEnglish();
		}else
			return null;
		
	}//end getInstance()
	
}//end class
