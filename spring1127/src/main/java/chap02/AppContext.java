package chap02;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //spring 설정파일
public class AppContext {
	
	// MemberController 빈으로 등록	
	@Bean
	public MemberController memberController() {
		MemberController con = new MemberController();
		con.setService(memberServie());
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
		
		return new MemberService().setDao(memberDao());
	}
	

}
