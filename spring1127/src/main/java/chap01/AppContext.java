package chap01;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppContext {
	
	// Greeter 객체(빈)를 생성해서 컨테이너에 등록
		// 빈이름이 greeter인 리턴값이 객체로 등록
		@Bean
		public Greeter greeter() { // 메서드명이 빈이름
			Greeter g = new Greeter();
			g.setName("홍길동");
			return  g;
		}

}
