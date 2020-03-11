package com.javalec.user;

import java.sql.*;

public class UserDAO {
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	public UserDAO() {
		try {
			String dbURL= "jdbc:mysql://localhost:3306/bbs";
			String dbID="root";
			String dbPassword="111111";
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public int login(String userID, String userPassword) {
		String SQL ="SELECT userPassword FROM user WHERE userID = ?";
		
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString(1).equals(userPassword)){
					System.out.println("로그인 성공!");
					return 1;
				}else if(!rs.getString(1).equals(userPassword)){
					System.out.println("DAO : 비밀번호가 틀립니다.");
					return -1;
				}
			}else {
				System.out.println("DAO : 아이디가 존재하지 않습니다.");
					return -2;					
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -2;
	}
	
	public int join(UserDTO userDTO) {
		String SQL = "INSERT INTO user VALUES(?,?,?,?,?)";
		String userID = userDTO.getUserID();
		String userPassword = userDTO.getUserPassword();
		String userName = userDTO.getUserName();
		String userGender = userDTO.getUserGender();
		String userEmail = userDTO.getUserEmail();
//		System.out.println(userID+ userPassword+ userName+userGender+ userEmail);
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			pstmt.setString(2, userPassword);
			pstmt.setString(3, userName);
			pstmt.setString(4, userGender);
			pstmt.setString(5, userEmail);
			int rs = pstmt.executeUpdate();
			System.out.println("DAO : 성공적으로 데이터베이스에 입력 하였습니다."+rs);
			return rs;
		}catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("DAO : 데이터 입력에 실패 하였습니다.");
		return -1;
	}
}
