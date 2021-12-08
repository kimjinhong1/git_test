package chap01;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainForJava {

	public static void main(String[] args) {
		// 설정파일을 읽어와서 빈등록
		// greeter라는 이름으로 객체(빈)을 컨테이너에 등록(싱글톤으로 등록)
		AnnotationConfigApplicationContext ctx =
				new AnnotationConfigApplicationContext(AppContext.class);
				
		// 빈을 가져오기
		Greeter g = (Greeter)ctx.getBean("greeter");
		Greeter g2 = ctx.getBean("greeter", Greeter.class);
		
		System.out.println(g == g2); // 같은 객체
	}
}
