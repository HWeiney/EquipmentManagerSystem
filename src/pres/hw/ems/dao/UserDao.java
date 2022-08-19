package pres.hw.ems.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import pres.hw.ems.domain.User;
import pres.hw.ems.util.DBConnection;
import pres.hw.ems.util.MsgUtil;

public class UserDao {
	private static DBConnection dbConnection;
	
	private static Connection connection;
	
	private static List<User> users;
	
	private static String sql = "select * from users";
	
	private List<User> storeUsers;
	
	private static PreparedStatement ppst;
	
	public UserDao() {
		dbConnection = new DBConnection();
		connection = dbConnection.connection;
	}
	
	public static List<User> query(){
		dbConnection = new DBConnection();
		connection = dbConnection.connection;

		users = new ArrayList<User>();
		
		try {
			ppst = connection.prepareStatement(sql);
			ResultSet rs = ppst.executeQuery();
			while(rs.next()) {
				users.add(new User(rs.getString(2), rs.getString(3), rs.getString(5)));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (ppst != null) {
					
					ppst.close();
				}
				if (connection != null) {
					
					connection.close();
				}
				return users;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	//用户信息表集合
	@SuppressWarnings("finally")
	public List<User> queryUsers(){
		
		storeUsers = new ArrayList<User>();
		try {
			ppst = connection.prepareStatement(sql);
			ResultSet rs = ppst.executeQuery();
			
			while(rs.next()) {
				storeUsers.add(new User(rs.getInt(1),rs.getString(2), rs.getString(4), rs.getString(5)));
				//System.out.println(rs.getInt(1)+rs.getString(2)+ rs.getString(4)+ rs.getString(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if (ppst != null) {
				try {
					ppst.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return storeUsers;
		}
	}
	//显示所有信息
	@SuppressWarnings("finally")
	public List<User> queryAllinfo(int id){
		
		String selectSql = "select * from users where userid = '"+id+"'";
		storeUsers = new ArrayList<User>();
		try {
			ppst = connection.prepareStatement(selectSql);
			ResultSet rs = ppst.executeQuery();
			
			while(rs.next()) {
				storeUsers.add(new User(rs.getInt(1),rs.getString(2), 
						rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7)));
				//System.out.println(rs.getInt(1)+rs.getString(2)+ rs.getString(4)+ rs.getString(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if (ppst != null) {
				try {
					ppst.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return storeUsers;
		}
	}
	
	//修改用户信息,并更新时间
	public static Boolean update(int userid,String password,String phone,String note,int num) {
		dbConnection = new DBConnection();
		connection = dbConnection.connection;
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = sdf.format(date);
		
		String updateSql;
		if(num == 1) {
			updateSql = "update users set note = '" + note + "',updatetime = '"+dateStr+"' where userid = '" + userid + "'";
		}else if(num == 2) {
			updateSql = "update users set note = '" + note + "',updatetime = '"+dateStr
					+"',phone = '"+phone +"' where userid = '"+userid+"'";
		}else if(num == 3) {
			updateSql = "update users set note = '" + note + "',updatetime = '"+dateStr
					+"',password = '"+password +"' where userid = '"+userid+"'";
		}else {
			updateSql = "update users set note = '" + note + "',updatetime = '"+dateStr
					+"',phone = '"+phone +"', password = '"+password+"' where userid = '"+userid+"'";
		}
		
		try {
			ppst = connection.prepareStatement(updateSql);
			int i = ppst.executeUpdate();
			ppst.close();
			connection.close();
			return i==1 ? true:false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
		
	}
	/**
	 * 添加用户（添加信息）
	 */
	public static Boolean insert(int userid,String username,String password,String phone,String note ) {
		dbConnection = new DBConnection();
		connection = dbConnection.connection;
		
		String insertSql = "insert into users(userid,username,password,phone,note,createtime,updatetime) values(?,?,?,?,?,?,?)";
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = sdf.format(date);
		
		try {
			PreparedStatement ppst = connection.prepareStatement(insertSql);
			ppst.setInt(1, userid);
			ppst.setString(2, username);
			ppst.setString(3, password);
			ppst.setString(4, phone);
			ppst.setString(5,note);
			ppst.setString(6, dateStr);
			ppst.setString(7, dateStr);

			ppst.execute();
			//System.out.println("插入成功...");
			ppst.close();
			connection.close();
			return true;

		} catch (SQLException e) {
			//e.printStackTrace();
			//MsgUtil.showdb(false);
		}
		return false;
	}
	
	public static Boolean delete(int userid,String username) {
		
		dbConnection = new DBConnection();
		connection = dbConnection.connection;
		String delSql = "DELETE FROM users WHERE userid='" + userid + "' AND username='" + username + "' AND note<> '超级管理员'";
		
		try {
			ppst = connection.prepareStatement(delSql);
			int i = ppst.executeUpdate();
			ppst.close();
			connection.close();
			return i==1 ? true:false;
		} catch (SQLException e) {
			//e.printStackTrace();
			System.out.println("数据库删除操作失败");
		}
		return false;
	}
	
//	public static void main(String[] args) {
//		new UserDao();
//		if(!UserDao.delete(1, "Hanwei")) {
//			System.out.println("no success!");
//		}
//	}
}
