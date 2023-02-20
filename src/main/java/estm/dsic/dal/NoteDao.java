package estm.dsic.dal;

import estm.dsic.models.Note;
import estm.dsic.models.User;

import java.sql.*;
import java.util.Vector;

public class NoteDao {

	
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
				us.setName(rs.getString(1));
				us.setEmail(rs.getString(2));
				us.setPwd(rs.getString(3));
				us.setAdmin(rs.getBoolean(4));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return us;
		
	}

	public boolean add(Note u) {
		int rs = 0;
		try {
			Connection cnx = Database.getConnection();
			PreparedStatement stm = cnx.prepareStatement("INSERT INTO note(title, content, date, email) values(?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			stm.setString(1, u.getTitle());
			stm.setString(2, u.getContent());
			stm.setString(3, u.getDate());
			stm.setString(4, u.getEmail());
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

	public boolean update(Note u) {
		int rs = 0;
		try {
			System.out.println("updating in db");
			Connection cnx = Database.getConnection();
			PreparedStatement stm = cnx.prepareStatement("UPDATE note set title= ?, content = ?, date = ? where id= ?", Statement.RETURN_GENERATED_KEYS);
			stm.setString(1, u.getTitle());
			stm.setString(2, u.getContent());
			stm.setString(3, u.getDate());
			stm.setInt(4, u.getId());
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

	public boolean delete(Integer id) {
		int rs = 0;
		try {
			Connection cnx = Database.getConnection();
			PreparedStatement stm = cnx.prepareStatement("DELETE FROM note where id= ?", Statement.RETURN_GENERATED_KEYS);
			stm.setInt(1, id);
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

	public Vector<Note> getNotes(String email) {
		ResultSet rs = null;
		Connection conn = null;
		Note note = new Note();
		Vector<Note> notes = new Vector<>();
		System.out.println("passed email is: " + email);
		try {
			conn = Database.getConnection();
			if (conn != null) {
				PreparedStatement ps = conn.prepareStatement("SELECT * FROM note where email = ?");
				ps.setString(1, email);
				rs = ps.executeQuery();

				if (rs != null) {
					while (rs.next()) {
						note = new Note();
						note.setId(rs.getInt(1));
						note.setTitle(rs.getString(2));
						note.setContent(rs.getString(3));
						note.setDate(rs.getString(4));
						note.setEmail(rs.getString(5));
						notes.add(note);
					}
					System.out.println(notes);
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


		return notes;
	}



	public Note getNote(String id) {
		ResultSet rs = null;
		Connection conn = null;
		Note us = new Note();
		try {
			conn = Database.getConnection();
			if (conn != null) {
				PreparedStatement ps = conn.prepareStatement(
						"select * from note where id = ?");
				ps.setString(1, id);
				rs = ps.executeQuery();
				if (rs != null) {
					while (rs.next()) {
						us = new Note();
						us.setId(rs.getInt(1));
						us.setTitle(rs.getString(2));
						us.setContent(rs.getString(3));
						us.setDate(rs.getString(4));
						us.setEmail(rs.getString(5));
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
