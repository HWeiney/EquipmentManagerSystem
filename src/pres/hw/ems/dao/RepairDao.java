package pres.hw.ems.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pres.hw.ems.domain.BuyEm;
import pres.hw.ems.domain.RepairEm;
import pres.hw.ems.util.DBConnection;

public class RepairDao {
	
	private static DBConnection dbConnection;
	private static Connection connection;
	private static PreparedStatement ppst;
	private static List<RepairEm> repairList;

	public RepairDao() {
		// TODO Auto-generated constructor stub
		dbConnection = new DBConnection();
		connection = dbConnection.connection;
	}
	
	@SuppressWarnings("finally")
	public static List<RepairEm> queryRepairList(){
		
		repairList = new ArrayList<RepairEm>();
		String slelectSql = "select * from er_view";
		
		try {
			ppst = connection.prepareStatement(slelectSql);
			ResultSet rs = ppst.executeQuery();
			while(rs.next()) {
				repairList.add(new RepairEm(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getString(4),rs.getInt(5),
						rs.getString(6),rs.getString(7)));
			}
		} catch(SQLException e) {
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
			return repairList;
		}
	}
	
	@SuppressWarnings("finally")
	public static int queryRepairSize() {
		int size = 0;
		String slelectSql = "select COUNT(*) from e_repair";
		
		try {
			ppst = connection.prepareStatement(slelectSql);
			ResultSet rs = ppst.executeQuery();
			rs.next();
			size = rs.getInt(1);
			
		} catch(SQLException e) {
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
			return size;
		}
	}
	
	@SuppressWarnings("finally")
	public static List<RepairEm> queryList(){
		
		repairList = new ArrayList<RepairEm>();
		String slelectSql = "select * from list_view";
		
		try {
			ppst = connection.prepareStatement(slelectSql);
			ResultSet rs = ppst.executeQuery();
			while(rs.next()) {
				repairList.add(new RepairEm(rs.getInt(1),rs.getString(2),rs.getInt(3)));
				//System.out.println(rs.getInt(1)+" "+ rs.getString(2)+" "+ rs.getInt(3));
			}
		} catch(SQLException e) {
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
			return repairList;
		}
	}
	/**
	 * 添加报修（添加信息）
	 */
	public static Boolean insert(String Repairno,String Repairdate,int Eno,int Rcount,String Rreason,String username ) {
		dbConnection = new DBConnection();
		connection = dbConnection.connection;
		
		String insertSql = "INSERT INTO e_repair(Repairno,Repairdate,Eno,Rcount,Rreason,username) values(?,?,?,?,?,?)";
		
		try {
			PreparedStatement ppst = connection.prepareStatement(insertSql);
			ppst.setString(1, Repairno);
			ppst.setString(2, Repairdate);
			ppst.setInt(3, Eno);
			ppst.setInt(4, Rcount);
			ppst.setString(5,Rreason);
			ppst.setString(6, username);

			ppst.execute();
			//System.out.println("插入成功...");
			ppst.close();
			connection.close();
			return true;

		} catch (SQLException e) {
			//e.printStackTrace();
			//System.out.println("数据库插入数据失败");
		}
		return false;
	}
	
	@SuppressWarnings("finally")
	public static int queryLendSize(String Ename) {
		int count = 0;
		String slelectSql = "select StoreCount from storelist where Ename='"+Ename+"'";
		
		try {
			ppst = connection.prepareStatement(slelectSql);
			ResultSet rs = ppst.executeQuery();
			rs.next();
			count = rs.getInt(1);
			
		} catch(SQLException e) {
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
			return count;
		}
	}
	public static Boolean delete(String Repairno) {
		
		dbConnection = new DBConnection();
		connection = dbConnection.connection;
		String delSql = "DELETE FROM e_repair WHERE Repairno='" + Repairno + "'";
		
		try {
			ppst = connection.prepareStatement(delSql);
			int i = ppst.executeUpdate();
			ppst.close();
			connection.close();
			return i==1 ? true:false;
		} catch (SQLException e) {
			//e.printStackTrace();
			//System.out.println("数据库删除操作失败");
		}
		return false;
	}
//	public static void main(String[] args) {
//		new RepairDao();
//		int x = RepairDao.queryLendSize();
//		System.out.println(x);
//	}
}
