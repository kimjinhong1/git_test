package chap07.user;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class UserController {
	
	@Autowired //자동 주입!
	UserService service;
//	@Autowired
//	BoardService boardService;

	@GetMapping("/user/regist.do")
	public String regist() {
		return "user/regist";
	}
	
	@PostMapping("/user/regist.do")
	public String regist(Model model, UserVo vo, HttpServletRequest req) {
		//System.out.println("등록전 userno : "+vo.getUserno());
		log.info("등록전 userno : "+vo.getUserno());
		boolean r = service.insert(vo, req);
		log.info("등록후 userno : "+vo.getUserno());
		
		if(r) {
			model.addAttribute("msg", "정상적으로 가입되었습니다.");
			model.addAttribute("url", "/spring/board/index.do");
		} else {
			model.addAttribute("msg", "가입오류");
			model.addAttribute("url", "regist.do");
		}
		
		return "include/result";
	}
	
	@GetMapping("/user/idcheck.do")
	public String idcheck(Model model, @RequestParam String id) {
		int r = service.idcheck(id);
		model.addAttribute("ret", r);
		return "include/return";
	}
	
	//처음에는 쿠키가 없기때문에 required를 false를 줘야함
	@GetMapping("/user/login.do")
	public String login(@CookieValue(value="cookieId", required=false) Cookie cookie, UserVo vo) { 
		if (cookie != null) { // 이전에 아이디 저장 체크하고 로그인한적이 있다는 거임
			vo.setId(cookie.getValue());
			//vo.setCheckId("check"); <- 굳이 필요없음 
		}
		return "user/login";
	}
	
	@PostMapping("/user/loginProcess.do")
	public String loginProcess(UserVo vo, HttpSession sess, Model model, HttpServletResponse res) {
		
		if (service.login(vo, sess)) {
			// 쿠키객체 생성, 로그인이 성공했을때
			Cookie cookie = new Cookie("cookieId", vo.getId());
			if ("check".equals(vo.getCheckId())) { //사용자가 아이디저장 체크
				cookie.setMaxAge(60*60*24*30);// 30일동안 보관
				//유효시간, 얼마나 쿠키를 보관할지
			} else {
				cookie.setMaxAge(0); // 0이면 쿠키 날려버리는거임, 즉시 만료
			}
			res.addCookie(cookie); // response객체에 쿠키 추가
			return "redirect:/board/index.do";
		} else {
			model.addAttribute("msg", "아이디, 비밀번호가 올바르지 않습니다.");
			model.addAttribute("url", "login.do");
		}		
		return "include/result";		
	}
	
	@GetMapping("/user/logout.do")
	public String logout(Model model, HttpSession sess) {
		service.logout(sess);
		
		return "redirect:/board/index.do";		
	}
	
//	@RequestMapping("/user/mypage.do")
//	public String mypage(Model model, HttpSession sess, BoardVo vo) {
//		vo.setUserno(((UserVo)sess.getAttribute("loginInfo")).getUserno());
//		
//		
//		int totCount = boardService.count(vo); // 총개수
//		int totPage = totCount / 10; //총페이지수
//		if (totCount % 10 > 0) totPage++; // 인덱스가 20개면 2페이지, 21개면 10으로나눴을때 나머지가 생기므로 +1page함 3페이지
//		System.out.println("totPage : "+totPage); //총 페이지  확인했음
//		
//		int startIdx = (vo.getPage()-1) * 10; //시작 인덱스값 구함, 10개씩나오게 할거니깐 1페이지면 0부터 9, 2페이지면 10~19
//		vo.setStartIdx(startIdx);  // set
//		
//		List<BoardVo> list = boardService.selectList(vo); //테이블의 한 행이 BoardVo의 객체
//		model.addAttribute("list", list);	
//		model.addAttribute("totPage", totPage);	 //모델에 totpage라는 이름으로 값 등록, index.jsp에서${} 쓰기위함!
//		return "user/mypage";
//	}
}
