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
//			System.out.println("ȸ�������� ���������� �̷�� �����ϴ�.");
			return 1;
		}else {
//			System.out.println("ȸ�����Կ� ���� �Ͽ����ϴ�.");
			return -1;
		}
	}

}
