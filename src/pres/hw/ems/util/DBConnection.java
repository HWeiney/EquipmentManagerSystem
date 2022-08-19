package pres.hw.ems.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	public Connection connection;
	private static String url = "jdbc:mariadb://localhost:3306/equipmentmanagersystem?useUnicode=true&characterEncoding=utf8&useSSL=false";
	private static String user = "root";//用户名
	private static String password = "";//此处填写连接MariaDB数据库密码

	public DBConnection() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			connection = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
