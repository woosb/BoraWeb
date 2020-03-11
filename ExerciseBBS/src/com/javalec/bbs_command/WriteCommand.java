package com.javalec.bbs_command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javalec.bbs.BbsDAO;
import com.javalec.user_command.Command;

public class WriteCommand implements Command{
	public int execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String bbsTitle = request.getParameter("bbsTitle");
		String bbsContent = request.getParameter("bbsContent");
		String userID = (String)session.getAttribute("userID");
		BbsDAO bbsDAO = new BbsDAO();
		bbsDAO.write(userID, bbsTitle, bbsContent);
		return 1;		
	}
}
