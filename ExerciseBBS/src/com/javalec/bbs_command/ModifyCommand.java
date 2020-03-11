package com.javalec.bbs_command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javalec.bbs.BbsDAO;
import com.javalec.user_command.Command;

public class ModifyCommand implements Command {

	@Override
	public int execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String bbsTitle = request.getParameter("bbsTitle");
		String bbsContent = request.getParameter("bbsContent");
		String bbsID = session.getAttribute("bbsID").toString();
		BbsDAO bbsDAO = new BbsDAO();
		int rs = bbsDAO.modifyBbs(bbsTitle, bbsContent, bbsID);
		return rs;
	}
}
