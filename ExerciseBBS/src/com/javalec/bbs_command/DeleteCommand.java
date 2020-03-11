package com.javalec.bbs_command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javalec.bbs.BbsDAO;
import com.javalec.user_command.Command;

public class DeleteCommand implements Command{
	public int execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String bbsID = session.getAttribute("bbsID").toString();
//		int bbsID = (Integer)session.getAttribute("bbsID"); 
		BbsDAO bbsDAO = new BbsDAO();
//		System.out.println("bbsID1 : "+bbsID1);
//		System.out.println("bbsID : "+bbsID);
		bbsDAO.deleteBbs(bbsID);
		return 1;		
	}
}


//Integer userid = (Integer) session.getAttribute("user");
//User user = null;
//if (userid != null) {
//    user = new UserDAO().getUser(userid);
//}
//// here user will be null if no userid has been stored on the session,
//// and it wil be loaded from your persistence layer otherwise.