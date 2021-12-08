package chap07;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import chap07.user.UserVo;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
public class BoardController {
	
	@Autowired
	BoardService boardService;
	
	@GetMapping({"/board/index.do","/user/mypage.do"}) // 접속하면 아래 메서드 실행
	public String index(Model model, HttpServletRequest req,BoardVo vo, HttpSession sess) {
		log.debug("111111");
		System.out.println(req.getRequestURI());
		System.out.println(req.getServletPath());	
		
		String view = "board/index";
		if("/user/mypage.do".equals(req.getServletPath())) {	 //마이페이지로 접속하는 경우			
			vo.setUserno(((UserVo)sess.getAttribute("loginInfo")).getUserno());
			view = "user/mypage";
		}	
		
		int totCount = boardService.count(vo); // 총개수
		int totPage = totCount / 10; //총페이지수
		if (totCount % 10 > 0) totPage++; // 인덱스가 20개면 2페이지, 21개면 10으로나눴을때 나머지가 생기므로 +1page함 3페이지
		System.out.println("totPage : "+totPage); //총 페이지  확인했음
			
		int startIdx = (vo.getPage()-1) * 10; //시작 인덱스값 구함, 10개씩나오게 할거니깐 1페이지면 0부터 9, 2페이지면 10~19
		vo.setStartIdx(startIdx);  // set
			
		List<BoardVo> list = boardService.selectList(vo); //테이블의 한 행이 BoardVo의 객체
		model.addAttribute("list", list);	
		model.addAttribute("totPage", totPage);	 //모델에 totpage라는 이름으로 값 등록, index.jsp에서${} 쓰기위함!
		return view;			
	}
	
	@RequestMapping("/board/write.do")
	public String write() {
		return "board/write";
	}
	
	@PostMapping("/board/insert.do")
	public String insert(BoardVo vo, HttpServletRequest req, MultipartFile file, HttpSession sess) {	
		vo.setUserno(((UserVo)sess.getAttribute("loginInfo")).getUserno());
		//파일저장
		if ( !file.isEmpty()) { // 사용자가 파일을 첨부했다면
			try {
				String path = req.getRealPath("/upload/");
				String filename = file.getOriginalFilename();
				file.transferTo(new File(path+filename)); // 경로에 파일을 저장
				vo.setFilename(filename);
		   	} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		
		int r = boardService.insert(vo); // r에는 결국 개수가 리턴됨
		System.out.println("r 개수: "+r);
		
		// 정상적으로 등록되었습니다. alert띄우고
		// index.do로 이동
		
		if (r > 0) {
			req.setAttribute("msg", "정상적으로 등록되었습니다.");
			req.setAttribute("url", "index.do");
		} else {
			req.setAttribute("msg", "등록오류.");
			req.setAttribute("url", "write.do");
		}
		
		return "include/result";
	}
	
	@GetMapping("/board/detail.do")// 2번쨰방법 파람
	public String detail(Model model, @RequestParam int boardno ) {		
		model.addAttribute("data", boardService.selectOne(boardno)); // data란 이름으로 model에 add , request와 같음
		return "board/detail";
	}
	// resultMap 쓴 예시
	@GetMapping("/board/detail2.do")// 2번쨰방법 파람
	public String detail2(Model model, @RequestParam int boardno) {
		model.addAttribute("data", boardService.selectOne2(boardno)); // data란 이름으로 model에 add , request와 같음
		return "board/detail2";
	}
	
	@GetMapping("/board/edit.do")
	public String edit(Model model, @RequestParam int boardno) {
		model.addAttribute("data", boardService.selectOne(boardno));		
		return "board/edit";		
	}
	
	@PostMapping("/board/update.do")
	public String update(Model model, BoardVo vo) {
		int r = boardService.update(vo);
		if (r > 0) {
			model.addAttribute("msg", "정상적으로 수정되었습니다.");  //이동
			model.addAttribute("url", "detail.do?boardno="+vo.getBoardno()); // 성공했을때 상세페이지로 이동
		} else {
			model.addAttribute("msg", "수정오류");  //이동
			model.addAttribute("url", "edit.do?boardno="+vo.getBoardno()); //실패했을때 수정페이지로 이동
		}
		return "include/result";
	}
	
	@GetMapping("/board/delete.do")
	public String delete(Model model, BoardVo vo) {
		int r = boardService.delete(vo);
		if (r > 0) {
			model.addAttribute("msg", "정상적으로 삭제되었습니다.");  //이동
			model.addAttribute("url", "index.do"); // 성공했을때 목록페이지로 이동
		} else {
			model.addAttribute("msg", "삭제오류");  //이동
			model.addAttribute("url", "detail.do?boardno="+vo.getBoardno()); //실패했을때 상세페이지로 이동
		}
		return "include/result";
	}
	
	
	
}
