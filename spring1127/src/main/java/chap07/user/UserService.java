package chap07.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public interface UserService {
	boolean insert(UserVo vo, HttpServletRequest req);
	int idcheck(String id);
	boolean login(UserVo vo, HttpSession sess);
	boolean logout(HttpSession sess);

}
