package pres.hw.ems.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pres.hw.ems.domain.BuyEm;
import pres.hw.ems.domain.LendEm;
import pres.hw.ems.util.DBConnection;

public class BuyDao {
	
	private static DBConnection dbConnection;
	
	private static Connection connection;
	
	private static List<BuyEm> emList,storeList,buyList;
	
	private static PreparedStatement ppst;
	
	public BuyDao() {
		// TODO Auto-generated constructor stub
		dbConnection = new DBConnection();
		connection = dbConnection.connection;
	}
	
	@SuppressWarnings("finally")
	public static List<BuyEm> queryList(){
		
		emList = new ArrayList<BuyEm>();
		String slelectSql = "select * from list_view";
		
		try {
			ppst = connection.prepareStatement(slelectSql);
			ResultSet rs = ppst.executeQuery();
			while(rs.next()) {
				emList.add(new BuyEm(rs.getInt(1),rs.getString(2),rs.getInt(3)));
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
	
	@SuppressWarnings("finally")
	public static int queryListSize() {
		int size = 0;
		String slelectSql = "select COUNT(*) from e_store";
		
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
	
	/**
	 * 添加设备（添加信息）
	 */
	public static Boolean insert(String Listno,int Eno,String username,int Ecount,String Buydate,int BuyPrice,String SaleName,String SalePhone,String Estatus ) {
		dbConnection = new DBConnection();
		connection = dbConnection.connection;
		
		String insertSql = "INSERT INTO e_store(Listno,Eno,username,Ecount,Buydate,BuyPrice,SaleName,SalePhone,Estatus) values(?,?,?,?,?,?,?,?,?)";
		
		try {
			PreparedStatement ppst = connection.prepareStatement(insertSql);
			ppst.setString(1, Listno);
			ppst.setInt(2, Eno);
			ppst.setString(3, username);
			ppst.setInt(4, Ecount);
			ppst.setString(5,Buydate);
			ppst.setInt(6, BuyPrice);
			ppst.setString(7, SaleName);
			ppst.setString(8, SalePhone);
			ppst.setString(9, Estatus);

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
	public static List<BuyEm> queryStoreList(){
		
		storeList = new ArrayList<BuyEm>();
		String slelectSql = "select * from Storelist";
		
		try {
			ppst = connection.prepareStatement(slelectSql);
			ResultSet rs = ppst.executeQuery();
			while(rs.next()) {
				storeList.add(new BuyEm(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getInt(4),rs.getInt(5)));
				//System.out.println(rs.getInt(1)+" "+ rs.getString(2)+" "+ rs.getInt(3)+" "+rs.getInt(4) + " "+rs.getInt(5));
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
			return storeList;
		}
	}
	
	@SuppressWarnings("finally")
	public static List<BuyEm> queryBuyList(){
		
		buyList = new ArrayList<BuyEm>();
		String slelectSql = "select * from es_view";
		
		try {
			ppst = connection.prepareStatement(slelectSql);
			ResultSet rs = ppst.executeQuery();
			while(rs.next()) {
				buyList.add(new BuyEm(rs.getString(1),rs.getInt(2),rs.getString(3),rs.getString(4),
						rs.getString(5),rs.getString(6),rs.getInt(7),rs.getInt(8),rs.getString(9),rs.getString(10)));
				//System.out.println(rs.getInt(1)+" "+ rs.getString(2)+" "+ rs.getInt(3)+" "+rs.getInt(4) + " "+rs.getInt(5));
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
			return buyList;
		}
	}
	
	
	
//	public static void main(String[] args) {
//		new BuyDao();
//		//BuyDao.insert("20220605006",1009,"Hanwei",4,"2022-06-05 23:35:20",60,"小悦","15177776666","在库");
//		//System.out.println(BuyDao.queryStoreList());
//	}
}
