package com.javalec.user_command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javalec.user.UserDAO;


public class LoginCommand implements Command {
	@Override
	public int execute(HttpServletRequest request, HttpServletResponse response) {
		
		String userID = request.getParameter("userID");
		String userPassword = request.getParameter("userPassword");
		UserDAO userDAO = new UserDAO();
		int rs = userDAO.login(userID, userPassword);		

		HttpSession session = request.getSession();
		if(rs == 1) {
			session.setAttribute("userID", userID);			
		}
		return rs;
	}
}
