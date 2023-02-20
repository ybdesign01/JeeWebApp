package estm.dsic.dal;

import java.sql.*;
import java.util.*;

import estm.dsic.models.User;

public class UserDao {

	
	public User login(User u) {
		User us = null;
		try {
			Connection cnx = Database.getConnection();
			PreparedStatement stm = cnx.prepareStatement("SELECT email,name,  password, isAdmin FROM user WHERE email like ? AND password like ?");
			stm.setString(1, u.getEmail());
			stm.setString(2, u.getPwd());
			ResultSet rs=stm.executeQuery();
			while(rs.next()){
				us = new User();
				us.setName(rs.getString(2));
				us.setEmail(rs.getString(1));
				us.setPwd(rs.getString(3));
				us.setAdmin(rs.getBoolean(4));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return us;
		
	}

	public boolean register(User u) {
		int rs = 0;
		try {
			Connection cnx = Database.getConnection();
			PreparedStatement stm = cnx.prepareStatement("INSERT INTO user(email, name, password, isAdmin) values(?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			stm.setString(1, u.getEmail());
			stm.setString(2, u.getName());
			stm.setString(3, u.getPwd());
			stm.setBoolean(4, u.isAdmin());
			rs=stm.executeUpdate();
			if (rs != 0){
				System.out.println("updated");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs!=0;

	}

	public boolean update(User u) {
		int rs = 0;
		try {
			Connection cnx = Database.getConnection();
			PreparedStatement stm = cnx.prepareStatement("UPDATE user set email= ?, name = ?, password = ?, isAdmin = ? where email= ?", Statement.RETURN_GENERATED_KEYS);
			stm.setString(1, u.getEmail());
			stm.setString(2, u.getName());
			stm.setString(3, u.getPwd());
			stm.setBoolean(4, u.isAdmin());
			stm.setString(5, u.getEmail());

			rs=stm.executeUpdate();
			if (rs != 0){
				System.out.println("updated");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs!=0;

	}

	public boolean delete(User u) {
		int rs = 0;
		try {
			Connection cnx = Database.getConnection();
			PreparedStatement stm = cnx.prepareStatement("DELETE FROM user where email= ?", Statement.RETURN_GENERATED_KEYS);
			stm.setString(1, u.getEmail());
			rs=stm.executeUpdate();
			if (rs != 0){
				System.out.println("deleted");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs!=0;

	}

	public Vector<User> getUsers() {
		ResultSet rs = null;
		Connection conn = null;
		User us = new User();
		Vector<User> users = new Vector<User>();
		try {
			conn = Database.getConnection();
			if (conn != null) {

				java.sql.PreparedStatement ps = conn.prepareStatement(
						"select * from user");
				rs = ps.executeQuery();

				if (rs != null) {
					while (rs.next()) {
						us = new User();

						us.setName(rs.getString(2));
						us.setEmail(rs.getString(1));
						us.setPwd(rs.getString(3));
						us.setAdmin(rs.getBoolean(4));
						if (!us.isAdmin()){
							users.add(us);
						}
					}
				}

			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if(rs!=null)
				{
					rs.close();
				}

			} catch (SQLException ex) {
				ex.printStackTrace();
			}

		}


		return users;
	}

	public User getUser(String email) {
		ResultSet rs = null;
		Connection conn = null;
		User us = new User();
		try {
			conn = Database.getConnection();
			if (conn != null) {

				PreparedStatement ps = conn.prepareStatement(
						"select * from user where email = ?");
				ps.setString(1, email);
				rs = ps.executeQuery();

				if (rs != null) {
					while (rs.next()) {
						us = new User();
						us.setName(rs.getString(2));
						us.setEmail(rs.getString(1));
						us.setPwd(rs.getString(3));
						us.setAdmin(rs.getBoolean(4));
					}
				}

			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if(rs!=null)
				{
					rs.close();
				}

			} catch (SQLException ex) {
				ex.printStackTrace();
			}

		}
		return us;
	}
}
