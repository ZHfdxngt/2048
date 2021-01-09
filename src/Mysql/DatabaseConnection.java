package Mysql;

import java.sql.*;

public class DatabaseConnection {
	private static final String DBDRIVER="com.mysql.cj.jdbc.Driver";
	private static final String DBURL="jdbc:mysql://localhost:3306/RLJ?serverTimezone=GMT%2B8";
	private static final String DBUSER="root";
	private static final String PASSWORD="babygirlZY020304";
	private Connection conn = null;
	
	/**
	 * 在构造方法调用时将进行数据库连接对象的取得
	 */
	public DatabaseConnection() {
		try {
			Class.forName(DBDRIVER);//加载数据库驱动程序
			conn=DriverManager.getConnection(DBURL, DBUSER, PASSWORD);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 取得数据库连接对象
	 * @return 实例化的Connection对象，如果返回null，表示没有连接成功
	 */
	public Connection getConnection() {
		return conn;
	}
	
	/**
	 * 进行数据库的关闭操作
	 */
	public void close() {
		if(conn!=null) {
			try {
				conn.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}




