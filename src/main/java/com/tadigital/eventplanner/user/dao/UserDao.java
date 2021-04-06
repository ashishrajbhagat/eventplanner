package com.tadigital.eventplanner.user.dao;

import com.tadigital.eventplanner.user.entity.User;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;

import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UserDao {
	public boolean searchUserByEmailAndPassword(User user) {
		boolean status = false;
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/event_planner", "root", "");
			stmt = con.createStatement();
			
			String sql = "SELECT * FROM user_information WHERE email='" + user.getEmail() + "' AND password='"+ user.getPassword() +"'";
			rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				status = true;
				
				user.setName(rs.getString(2));
				user.setMobile(rs.getString(3));
				user.setEmail(rs.getString(4));
				user.setPassword(rs.getString(5));
				user.setProfilePicture(rs.getString(7));
			}
		} catch (ClassNotFoundException cnfEx) {
			cnfEx.printStackTrace();
		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException sqlEx) {
				sqlEx.printStackTrace();
			}
		}
		
		return status;
	}
	
	public User searchUserByCookieValue(String cValue) {
		User user = null;
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/event_planner", "root", "");
			stmt = con.createStatement();
			
			String sql = "SELECT * FROM user_information WHERE sess_id='" + cValue + "'";
			rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				user = new User();
				user.setName(rs.getString(2));
				user.setMobile(rs.getString(3));
				user.setEmail(rs.getString(4));
				user.setPassword(rs.getString(5));
				user.setProfilePicture(rs.getString(7));
			}
		} catch (ClassNotFoundException cnfEx) {
			cnfEx.printStackTrace();
		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException sqlEx) {
				sqlEx.printStackTrace();
			}
		}
		
		return user;
	}
	
	public boolean insertUser(User user) {
		boolean status = false;
		
		Connection con = null;
		Statement stmt = null;
			
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/event_planner", "root", "");
			stmt = con.createStatement();
			
			String sql = "INSERT INTO user_information(name, mobile, email, password) VALUES('" + user.getName() + "', '" + user.getMobile() + "', '" + user.getEmail() + "', '" + user.getPassword() + "')";
			int row = stmt.executeUpdate(sql);
			
			if(row != 0) {
				status = true;
			}
		} catch (ClassNotFoundException cnfEx) {
			cnfEx.printStackTrace();
		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException sqlEx) {
				sqlEx.printStackTrace();
			}
		}
		
		return status;
	}
	
	public boolean updatePassword(User user, String newPassword) {
		boolean status = false;
		
		Connection con = null;
		Statement stmt = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/event_planner", "root", "");
			stmt = con.createStatement();
			
			String sql = "UPDATE user_information set password='" + newPassword + "' WHERE email='" + user.getEmail() + "'";
			
			//String sql = "UPDATE user_information set password='" + newPassword + "', sess_id='" + newSessId + "' WHERE email='" + user.getEmail() + "'";
			
			int row = stmt.executeUpdate(sql);
			
			if (row != 0) {
				user.setPassword(newPassword);
				status = true;
			}
		} catch (ClassNotFoundException cnfEx) {
			cnfEx.printStackTrace();
		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException sqlEx) {
				sqlEx.printStackTrace();
			}
		}
		
		return status;
	}
	
	public String updateSessionId(String sessId, String email) {		
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/event_planner", "root", "");
			stmt = con.createStatement();
			
			String sql = "SELECT sess_id FROM user_information WHERE email='" + email + "'";
			rs = stmt.executeQuery(sql);
			
			String dbSessId = null;
			if (rs.next()) {
				dbSessId = rs.getString(1);
			}
			
			if (dbSessId == null) {				
				sql = "UPDATE user_information set sess_id='" + sessId + "' WHERE email='" + email + "'";
				stmt.executeUpdate(sql);
			} else {
				sessId = dbSessId;
			}
		} catch (ClassNotFoundException cnfEx) {
			cnfEx.printStackTrace();
		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException sqlEx) {
				sqlEx.printStackTrace();
			}
		}
		
		return sessId;
	}
	
	public boolean updateProfile(User user) {
		boolean status = false;
		
		Connection con = null;
		Statement stmt = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/event_planner", "root", "");
			stmt = con.createStatement();
			
			String sql = "UPDATE user_information set profile_picture='" + user.getProfilePicture() + "' WHERE email='" + user.getEmail() + "'";
			int row = stmt.executeUpdate(sql);
			
			if (row != 0) {
				status = true;
			}
		} catch (ClassNotFoundException cnfEx) {
			cnfEx.printStackTrace();
		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException sqlEx) {
				sqlEx.printStackTrace();
			}
		}
		
		return status;
	}
	
	public User selectEmail(String email) {
		User user = null;
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/event_planner", "root", "");
			stmt = con.createStatement();
			
			String sql = "SELECT name, email FROM user_information";
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				if(rs.getString(2).equals(email)) {
					user = new User();
					
					user.setName(rs.getString(1));
					user.setEmail(rs.getString(2));
				}
			}
		} catch (ClassNotFoundException cnfEx) {
			cnfEx.printStackTrace();
		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException sqlEx) {
				sqlEx.printStackTrace();
			}
		}
		
		return user;
	}
}