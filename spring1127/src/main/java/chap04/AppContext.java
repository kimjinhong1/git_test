package chap04;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration //spring 설정파일
@ComponentScan(basePackages = {"chap04"}) // basePackages의 배열값(패키지)를 싹~스캔해서 빈등록, 여러개 가능!
public class AppContext {
/*	
	// MemberController 빈으로 등록	
	@Bean
	public MemberController memberController() {
		MemberController con = new MemberController();
		//con.setService(memberServie());  Controller에 자동 주입함!!!! , 얘도 필요가 없어짐(메서드가 있지도 않음)
		return con;
	}
	
	// MemberDao 빈으로 등록
	@Bean
	public MemberDao memberDao() {
		return new MemberDao();
	}
	
	// MemberService 빈으로 등록
	
//	@Bean
//	public MemberService memberServie() {
//		MemberService service = new MemberService();
//		// MemberDao객체를 주입
//		service.setDao(memberDao());
//		return service;
//	}
	
	@Bean
	public MemberService memberServie() {	
		
		return new MemberService();
	}
	
*/
}
