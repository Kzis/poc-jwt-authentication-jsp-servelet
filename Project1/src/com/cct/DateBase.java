package com.cct;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DateBase {
	
	static final String DB_ORACLE_DRIVER  = "oracle.jdbc.driver.OracleDriver";  
	static final String DB_ORACLE_CONNECTION  = "jdbc:oracle:thin:@10.100.70.87:1521:ORCL";
	static final String DB_ORACLE_USER  = "cp_cambodia";
	static final String DB_ORACLE_PASSWORD  = "cp_Cambodia2016";
	
	static final String DB_MYSQL_DRIVER  = "com.mysql.jdbc.Driver";  
	static final String DB_MYSQL_DRIVER2  = "com.mysql.cj.jdbc.Driver";  
	static final String DB_MYSQL_CONNECTION = "jdbc:mysql://localhost:3306/inhouse_project";
	static final String DB_MYSQL_USER  = "root";
	static final String DB_MYSQL_PASSWORD  = "password";

	public static void main(String[]args) throws Exception{
		
		Connection dbConnection = null;
		Statement statement = null;
		
		String selectSQLTest = null;
		
		try {
			
			DataBaseTomcat dataBaseTomcat = new DataBaseTomcat();
			
			dataBaseTomcat.getConnection();
			
			//New Connection
			dbConnection = getDBConnection();
			
			if(dbConnection != null){
				
				//Get Query
				selectSQLTest = getSelectSQL();
				
				//New Statement
				statement = dbConnection.createStatement();
				
				System.out.println("SQL : " + selectSQLTest);
				
				//Execute select SQL stetement
				ResultSet rst = statement.executeQuery(selectSQLTest);	
				
				int i = 1;
				
				while (rst.next()) {
					System.out.println("i = "+ i + " : " + rst.getString("HRM_POSITION_NAME") );
					i++;
				}
				
			}else{
				System.out.println("Can't not get connections.");
			}
			
		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {
			
			//Close Statement
			if (statement != null) {
				statement.close();
			}
			
			//Close Connection
			if (dbConnection != null) {
				dbConnection.close();
			}

		}
		
	}
	
	public static Connection getDBConnection() throws Exception {
		
		Connection dbConnection = null;
		
		try {

			Class.forName(DB_MYSQL_DRIVER);

		} catch (ClassNotFoundException e) {

			System.out.println(e.getMessage());

		}
		
		try {

			dbConnection = DriverManager.getConnection(DB_MYSQL_CONNECTION
													,DB_MYSQL_USER
													,DB_MYSQL_PASSWORD
													);
			return dbConnection;

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		}
		
		return dbConnection;
		
	}
	
	public static String getSelectSQL() throws Exception{
		
		String sql = "SELECT HRM_POSITION_NAME"
					+ " FROM STD_POSITION sp"
					+ " WHERE sp.POSITION_ID = 5 or sp.POSITION_ID = 55";
		
		return sql;
	}
	
}
