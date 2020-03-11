package com.javalec.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.bbs_command.DeleteCommand;
import com.javalec.bbs_command.ModifyCommand;
import com.javalec.bbs_command.WriteCommand;
import com.javalec.user_command.Command;
import com.javalec.user_command.JoinCommand;
import com.javalec.user_command.LoginCommand;
import com.javalec.user_command.LogoutCommand;

/**
 * Servlet implementation class Controller
 */
@WebServlet("*.do")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet");
		actionDo(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost");
		actionDo(request, response);	
	}
	
	public void actionDo(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		System.out.println("actionDo");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String com = uri.substring(conPath.length());
//		HttpSession session = request.getSession();
		
		String viewPage = null;
		Command command = null;
		int rs = 0;
		if(com.equals("/main_view.do")) {
			viewPage="main_view.jsp";
		}else if(com.equals("/login.do")) {
			command = new LoginCommand();
			rs = command.execute(request, response);
			if(rs == 1) {
				viewPage="main_view.jsp";
			}else{
				viewPage="loginFail.jsp";				
			}
		}else if(com.equals("/logout.do")) {
			command = new LogoutCommand();
			rs = command.execute(request, response);
			viewPage ="main_view.jsp";
		}else if(com.equals("/join.do")) {
			command = new JoinCommand();
//			System.out.println("controller"+command.execute(request,response));
			rs = command.execute(request, response);
			System.out.println(rs);
			if(rs == 1) {
				viewPage="login_view.jsp";				
			}else if(rs == -1){
				viewPage="joinFail.jsp";
			}
		}else if(com.equals("/write.do")){
			command = new WriteCommand();
			rs = command.execute(request, response);
			if(rs == 1) {
				viewPage="bbs_view.jsp";
			}
		}else if(com.equals("/delete.do")) {
			command = new DeleteCommand();
			rs = command.execute(request, response);
			if(rs == 1) {
				viewPage ="bbs_view.jsp";
			}
		}else if(com.equals("/modify.do")) {
			command = new ModifyCommand();
			rs = command.execute(request, response);
			if(rs == 1) {
				viewPage="/bbs_view.jsp";
			}else {
				viewPage="/modifyFail.jsp";
			}
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}
}
