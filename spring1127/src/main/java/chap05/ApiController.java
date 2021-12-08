package chap05;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/*
 * RestAPI 컨트롤러 
 */

@RestController
public class ApiController {
	
	@GetMapping("/api/test")
	public MemberVo test() {
		MemberVo vo = new MemberVo();
		vo.setName("홍길동");
		vo.setEmail("hong@gmail.com");
		vo.setNo(1);
		return vo;
	}
	
	/*
	 *  파라미터를 받는 4번째 방법
	 *  @PathVariable
	 *  /api/list/1/java -> 1페이지 -> page=1, serchWord=java
	 *  /api/list/2 -> 2페이지
	 *  json
	 */
	@GetMapping("/api/list/{page}/{searchWord}") //방법이 다름,  parameter : {}
	public List<MemberVo> list(@PathVariable int page, @PathVariable String searchWord) { // 매핑한 변수가 각각 담김
		System.out.println("page :  "+page);
		System.out.println("searchWord : "+searchWord);
		
		List<MemberVo> list = new ArrayList<MemberVo>();
		MemberVo vo = new MemberVo();
		vo.setName("홍길동");
		vo.setEmail("hong@gmail.com");
		vo.setNo(1);
		list.add(vo);
		list.add(vo);
		return list;
	}
}
