package ikujo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ikujo.command.Command;
import ikujo.model.MemberDAO;
import ikujo.model.MemberDTO;

public class Login implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("[Login]");
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");

		System.out.println(id);
		System.out.println(pw);

		MemberDTO dto = new MemberDTO(id, pw);
		MemberDTO info = new MemberDAO().Login(dto);
		String moveURL = "";
		if (info != null) {
			moveURL = "./showMember.jsp";
			HttpSession session = request.getSession();
			session.setAttribute("info", info);
			session.setAttribute("id", id);
		} else {
			moveURL = "./Main.jsp";
		}
		return moveURL;
	}

}
