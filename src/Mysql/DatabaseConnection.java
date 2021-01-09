package Mysql;

import java.sql.*;

public class DatabaseConnection {
	private static final String DBDRIVER="com.mysql.cj.jdbc.Driver";
	private static final String DBURL="jdbc:mysql://localhost:3306/RLJ?serverTimezone=GMT%2B8";
	private static final String DBUSER="root";
	private static final String PASSWORD="babygirlZY020304";
	private Connection conn = null;
	
	/**
	 * �ڹ��췽������ʱ���������ݿ����Ӷ����ȡ��
	 */
	public DatabaseConnection() {
		try {
			Class.forName(DBDRIVER);//�������ݿ���������
			conn=DriverManager.getConnection(DBURL, DBUSER, PASSWORD);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ȡ�����ݿ����Ӷ���
	 * @return ʵ������Connection�����������null����ʾû�����ӳɹ�
	 */
	public Connection getConnection() {
		return conn;
	}
	
	/**
	 * �������ݿ�Ĺرղ���
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




