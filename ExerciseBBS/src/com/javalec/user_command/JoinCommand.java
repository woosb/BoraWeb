package com.javalec.user_command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.user.UserDAO;
import com.javalec.user.UserDTO;

public class JoinCommand implements Command {

	@Override
	public int execute(HttpServletRequest request, HttpServletResponse response) {
		
		UserDAO userDAO = new UserDAO();
		UserDTO userDTO = new UserDTO();
		String userID = request.getParameter("userID");
		String userPassword = request.getParameter("userPassword");
		String userName = request.getParameter("userName");
		String userGender = request.getParameter("userGender");
		String userEmail = request.getParameter("userEmail");
		userDTO.setUserID(userID);
		userDTO.setUserPassword(userPassword);
		userDTO.setUserName(userName);
		userDTO.setUserGender(userGender);
		userDTO.setUserEmail(userEmail);
		
		int rs = userDAO.join(userDTO);
//		System.out.println(rs);
		if(rs == 1) {
//			System.out.println("회원가입이 성공적으로 이루어 졌습니다.");
			return 1;
		}else {
//			System.out.println("회원가입에 실패 하였습니다.");
			return -1;
		}
	}

}
