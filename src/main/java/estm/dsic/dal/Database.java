package estm.dsic.dal;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class Database {
	private static Connection cnx;

	public static Connection getConnection() {
		
		if (cnx==null)
		try {
			Context ctx=new InitialContext();
			DataSource ds=(DataSource)ctx.lookup("java:comp/env/jdbc/db_notes");
			cnx=ds.getConnection();
			if(cnx == null) {
					System.out.println("error connecction");
			}
			return cnx;
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cnx;
		
	}

	private void closeConnection() {
		if (cnx != null) {
			try {
				cnx.close();
				System.out.println("Connection closed successfully");
			} catch (SQLException ex) {
				System.out.println("Failed to close JDBC Connection !");
				ex.printStackTrace();
			}
		} else {

			System.out.println("No JDBC connection to close !");
		}
	}
}
