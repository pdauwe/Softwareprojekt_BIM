package awk.persistence.impl;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Persistence {
	 
	public static Connection getConnection(){
		Connection aConnection = null;
		try {
			/*
			 * Hier den Namen des JDBC-Treibers angeben. Damit der auch gefunden wird, muss
			 * das jar-File in Eclipde unter 
			 * "Project->Properties->Java Build Path->Libraries->Add External Jars" angegeben werden
			 */
			Class.forName ("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
				e.printStackTrace();
		}
		try {
			/* 
			 * Form: @Rechnername:Port, "Db-User", "Password"
			 */
			aConnection = 
					DriverManager.getConnection (
							// TODO: ip, benutzername und passwort
							//"jdbc:oracle:thin:@131.173.88.204:1521:XE", "STUNDENPLAN", "NewYork89");
							"jdbc:oracle:thin:@localhost:1521", "STUNDENPLAN", "NewYork89");
		} catch (SQLException e) {
				e.printStackTrace();
		}
		
		return(aConnection);	
	}
	
	public static void closeConnection(Connection aConnection){
		try {
			aConnection.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}	
	}
	
	
	public static void executeUpdateStatement(Connection aConnection, String aStatement) throws SQLException{
		System.out.println(aStatement);
		Statement stmt = aConnection.createStatement();
		stmt.executeUpdate(aStatement);
		
	}
	
	public static ResultSet executeQueryStatement(Connection aConnection, String aStatement) throws SQLException{
		ResultSet aResultSet = null;
		System.out.println(aStatement);
		Statement stmt = aConnection.createStatement();
		aResultSet =  stmt.executeQuery(aStatement);
		
		return aResultSet;
	}
	
}
