package chap02;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx =
				new AnnotationConfigApplicationContext(AppContext.class);
		MemberController con = ctx.getBean("memberController", MemberController.class);
		for (int i=0; i<1000; i++) {
		con.insert("홍길동");
		}
	}

}
