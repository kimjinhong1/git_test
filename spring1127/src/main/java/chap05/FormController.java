package chap05;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FormController {
	
	@GetMapping("/form.do")
	public String form() {
		return "form"; // web-inf/view 안에 form.jsp로 리턴
	}
	
	/*
	 * 파라미터를 받는 방법
	 * 1. HttpServletRequest 객체, parameter에 존재하지않으면 null 리턴
	 * 2. @RequestParam : 스프링이 알아서 담아줌,
	 * 					  parameter에 존재하지 않으면 에러가 남(value="~~", required=false) -> 이렇게 적으면 에러안나고 null 리턴
	 * 3. 커맨드객체 (parameter가 1~2개가 아니라면 대부분 이거 사용)
	 * 		- 클래스로 만들어진 객체
	 * 		- 파라미터의 이름과 동일한 필드에 자동으로 값을 넣어줌(알아서)
	 */
	
	@PostMapping("/send.do")
	public String send(HttpServletRequest req, @RequestParam("email") String email,
						@RequestParam(value="tel", required=false) String tel,
						MemberVo vo) { // 커맨드객체 MemberVo vo를 담아줌
		String name = req.getParameter("name"); // 1번째 방법
		req.setAttribute("msg", name+"님, 안녕하세요"); // msg라는 이름으로 저장
		req.setAttribute("msg2", "이메일 : " + email);
		req.setAttribute("tel", tel); //form에 없음
		
		System.out.println(vo.getName()+ " " + vo.getEmail() + " " + vo.getNo());
		if (vo.getHobby() != null) {
			for (int i=0; i<vo.getHobby().length; i++) {
				System.out.println(vo.getHobby()[i]);
			}		
		
			for (String hobby : vo.getHobby()) {
			System.out.println(hobby);
			}
		}
		
		// 커맨드객체 없이 취미값을 vo2의 hobby에 저장하려면?
		MemberVo vo2 = new MemberVo();
		vo2.setHobby(req.getParameterValues("hobby"));
//		String[] hobbys = new String[req.getParameterValues("hobby").length];
//		for (int i=0; i<req.getParameterValues("hobby").length; i++) {
//			hobbys[i] = req.getParameterValues("hobby")[i];
//		}
		
		
		return "send";
	}
	
	/*
	 * 뷰에서 사용하기 위한 값 컨트롤러에서 저장하는 방법
	 * - request에 set
	 * - session에 set
	 * - model add
	 * - ModelAndView에 add
	 */
	
	// ModelAndView 객체
	// model + view
	@GetMapping("/test9.do")
	public ModelAndView test9() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("name", "홍길동");
		mav.setViewName("test9");
		return mav; // spring이 알아서 꺼내서 함
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
