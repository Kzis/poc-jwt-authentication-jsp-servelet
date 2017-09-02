package com.cct;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Wrapper;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DataBaseTomcat {

	@SuppressWarnings("unused")
	public void getConnection() throws SQLException, NamingException{
		
		 System.setProperty(Context.INITIAL_CONTEXT_FACTORY,
                 "org.apache.naming.java.javaURLContextFactory");
		 
		Context initialContext = new InitialContext();
        
		if ( initialContext == null){
        	System.out.println("initialContext null");
        }
        else {
        	System.out.println("initialContext");
        }
		
		// Get DataSource
        Context environmentContext = (Context)initialContext.lookup("java:/comp/env");
        if ( environmentContext == null){
        	System.out.println("envContext null.");
        }
        else {
        	System.out.println("envContext");
        }

        DataSource ds = (DataSource)initialContext.lookup("jdbc/xxx");
	
        System.out.println("\n -------- JDBC Connection Testing ------");

        try {

            Connection jdbcConnection = ((Statement) ds).getConnection();
            DataSource ods = ((Wrapper) ds).unwrap(DataSource.class);
            jdbcConnection.close();

        } catch (SQLException e) {

            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return;

        }

        String message = "You are connected!";
        System.out.println(message);
	
	}	

}
