package com.javalec.bbs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BbsDAO {
	private Connection conn;
	private ResultSet rs;

	public BbsDAO() {
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
	
	public int write(String userID, String bbsTitle, String bbsContent) {
		String SQL = "insert into bbs values(?,?,?,?,?,?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, getNext());
			pstmt.setString(2, bbsTitle);
			pstmt.setString(3, userID);
			pstmt.setString(4, getDate());
			pstmt.setString(5, bbsContent);
			pstmt.setInt(6, 1);
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return 0;

	}
	
	public int getNext() {
		String SQL = "SELECT bbsID FROM BBS ORDER BY bbsID DESC";
		try {	
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1)+1;
			}else {
				return 1;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public String getDate() {
		String SQL = "SELECT NOW()";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getString(1);				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public ArrayList<BbsDTO> getList(int pageNumber){		
		ArrayList<BbsDTO> list = new ArrayList<BbsDTO>();
		String SQL = "SELECT *FROM BBS WHERE bbsID < ? AND bbsAvailable =1 ORDER BY bbsID DESC LIMIT 10";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, getNext()-(pageNumber-1)*10);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BbsDTO bbsDTO = new BbsDTO();
				bbsDTO.setBbsID(rs.getInt(1));
				bbsDTO.setBbsTitle(rs.getString(2));
				bbsDTO.setUserID(rs.getString(3));
				bbsDTO.setBbsDate(rs.getString(4));
				bbsDTO.setBbsContent(rs.getString(5));
				bbsDTO.setBbsAvailable(rs.getInt(6));
				list.add(bbsDTO);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}		
		return list;
	}
	
	public boolean nextPage(int pageNumber) {
		String SQL = "SELECT *FROM BBS WHERE bbsID < ? AND bbsAvailable = 1";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, getNext()-(pageNumber-1)*10);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	
	public BbsDTO getBbs(int bbsID) {
		String SQL = "SELECT *FROM BBS WHERE bbsID = ?";
		BbsDTO bbsDTO = new BbsDTO();
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, bbsID);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				bbsDTO.setBbsID(rs.getInt(1));
				bbsDTO.setBbsTitle(rs.getString(2));
				bbsDTO.setUserID(rs.getString(3));
				bbsDTO.setBbsDate(rs.getString(4));
				bbsDTO.setBbsContent(rs.getString(5));
				bbsDTO.setBbsAvailable(rs.getInt(6));
				return bbsDTO;
			}	
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public int deleteBbs(String bbsID) {
		String SQL ="UPDATE bbs SET bbsAvailable='0' WHERE bbsId=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, Integer.parseInt(bbsID));
			return pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {			
		}
		return 1;
	}
	
	public int modify() {
		
		return 0;
	}
	
	public int modifyBbs(String bbsTitle, String bbsContent, String bbsID) {
		String SQL = "UPDATE bbs SET bbsTitle = ?, bbsContent = ? where bbsID=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, bbsTitle);
			pstmt.setString(2, bbsContent);
			pstmt.setInt(3, Integer.parseInt(bbsID));
			return pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
}
