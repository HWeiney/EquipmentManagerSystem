package pres.hw.ems.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pres.hw.ems.domain.BuyEm;
import pres.hw.ems.domain.LendEm;
import pres.hw.ems.util.DBConnection;

public class LendDao {

private static DBConnection dbConnection;
	
	private static Connection connection;
	private static List<LendEm> lendList,emList;
	private static PreparedStatement ppst;
	
	public LendDao() {
		// TODO Auto-generated constructor stub
		dbConnection = new DBConnection();
		connection = dbConnection.connection;
	}
	
	@SuppressWarnings("finally")
	public static List<LendEm> queryLendList(){
		
		lendList = new ArrayList<LendEm>();
		String slelectSql = "select * from el_view";
		
		try {
			ppst = connection.prepareStatement(slelectSql);
			ResultSet rs = ppst.executeQuery();
			while(rs.next()) {
				lendList.add(new LendEm(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getString(4),rs.getInt(5),
						rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9)));
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
			return lendList;
		}
	}
	
	@SuppressWarnings("finally")
	public static List<LendEm> queryList(){
		
		emList = new ArrayList<LendEm>();
		String slelectSql = "select * from lendlist";
		
		try {
			ppst = connection.prepareStatement(slelectSql);
			ResultSet rs = ppst.executeQuery();
			while(rs.next()) {
				emList.add(new LendEm(rs.getInt(1),rs.getString(2),rs.getInt(3)));
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
			return emList;
		}
	}
	
	/**
	 * 借用设备（添加信息）
	 */
	public static Boolean insert(String Lendno,String Lenddate,int Eno,int Lcount,String Lendname,String Lendphone,String Returndate,String username) {
		dbConnection = new DBConnection();
		connection = dbConnection.connection;
		
		String insertSql = "INSERT INTO e_lend(Lendno,Lenddate,Eno,Lcount,Lendname,Lendphone,Returndate,username) values(?,?,?,?,?,?,?,?)";
		
		try {
			PreparedStatement ppst = connection.prepareStatement(insertSql);
			ppst.setString(1, Lendno);
			ppst.setString(2, Lenddate);
			ppst.setInt(3, Eno);
			ppst.setInt(4, Lcount);
			ppst.setString(5,Lendname);
			ppst.setString(6, Lendphone);
			ppst.setString(7, Returndate);
			ppst.setString(8, username);

			ppst.execute();
			//System.out.println("插入成功...");
			ppst.close();
			connection.close();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			//System.out.println("数据库插入数据失败");
		}
		return false;
	}
	
	@SuppressWarnings("finally")
	public static int queryLendSize() {
		int size = 0;
		String slelectSql = "select COUNT(*) from e_lend";
		
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
	public static Boolean delete(String Lendno) {
		
		dbConnection = new DBConnection();
		connection = dbConnection.connection;
		String delSql = "DELETE FROM e_lend WHERE Lendno='" + Lendno + "'";
		
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
//		new LendDao();
//		int x = LendDao.queryLendSize("显微镜");
//		System.out.println(x);
//	}
}
