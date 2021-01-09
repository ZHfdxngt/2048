package impl;

import java.sql.*;
import Interface.User;
import Interface.UserDao;
import Mysql.DatabaseConnection;

/**
 * 这是用户功能实现类
 * @author 27402
 *
 */
public class UserDaoImpl implements UserDao {

	@Override
	public boolean login(String username, String password) {
		boolean flag = false; //登陆成功为true

		DatabaseConnection conn = new DatabaseConnection();
		Connection hcon = conn.getConnection();

		try {
			Statement sql = hcon.createStatement();
			ResultSet rs = sql.executeQuery("select * from  message");
			while (rs.next()) {
				if (username.equals(rs.getString(1)) && password.equals(rs.getString(2))) {
					flag = true;
					break;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		conn.close();

		return flag;
	}

	@Override
	public void regist(User user) {
		DatabaseConnection conn = new DatabaseConnection();
		Connection hcon = conn.getConnection();
		String sql = "insert into message(user,password) values (?,?)";
		try {

			PreparedStatement ps = hcon.prepareStatement(sql);

			ps.setObject(1, user.getUsername());
			ps.setObject(2, user.getPassword());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		conn.close();
	}

	@Override
	public boolean update(User user) throws SQLException {
		DatabaseConnection conn = new DatabaseConnection();
		Connection hcon = conn.getConnection();
		String sql = "update message set password=? where user=?";
			PreparedStatement ps = hcon.prepareStatement(sql);
			ps.setObject(1, user.getPassword());
			ps.setObject(2, user.getUsername());

			return ps.executeUpdate()>0;

	}
	
	@Override
	public boolean check(String username) {
		
		boolean flag = false;

		DatabaseConnection conn = new DatabaseConnection();
		Connection hcon = conn.getConnection();

		try {
			Statement sql = hcon.createStatement();
			ResultSet rs = sql.executeQuery("select * from  message");
			while (rs.next()) {
				if (username.equals(rs.getString(1))) {
					flag = true;
					break;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		conn.close();

		return flag;
	}
	
	public void insert(Connection co) {
		DatabaseConnection conn = new DatabaseConnection();
		Connection hcon = conn.getConnection();
		
		try {

			
			for(int i=1;i<=10000;i++) {
				String sql = "insert into score(iduser,scorecol) values (?,?)";
				PreparedStatement ps = hcon.prepareStatement(sql);
				ps.setObject(1, i);
				ps.setObject(2,i*2);
				ps.execute();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		conn.close();
	}
	
}