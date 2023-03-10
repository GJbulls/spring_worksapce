package part03_exam;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;

public class StopWatchAdvcie {
	public StopWatchAdvcie() {
		// TODO Auto-generated constructor stub
	}
	
	@Around(value="execution(* part03_exam.ServiceImp.prn(..))")
	public void process(ProceedingJoinPoint point) {
		long start = System.currentTimeMillis();
			
		try {
			point.proceed();
		} catch (Throwable e) {
		
			e.printStackTrace();
		}
		
		long end = System.currentTimeMillis();
		
		String time = String.format("%s__메서드 실행시간:%d\n", point.getSignature().getName(), (end-start));
		System.out.println(time);
	}
}
