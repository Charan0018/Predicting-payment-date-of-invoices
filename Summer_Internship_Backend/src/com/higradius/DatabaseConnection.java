package com.higradius;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc: mysql://localhost:3306/mysql";
	// Database credentials
	static final String USER = "root";
	static final String PASS = "Raashi18!";
	public static void main(String[] args) {
	Connection conn = null;
	Statement stmt = null;
	try{
	//STEP 2: Register JDBC driver
	Class.forName("com.mysql.cj.jdbc.Driver");
	//STEP 3: Open a connection
	conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql?user=root&password=Raashi18!");
	//STEP 4: Execute a query
	stmt = conn.createStatement();
	String sql;
	sql = "SELECT name_customer,cust_number,invoice_id,converted_amount,due_in_date,predicted_payment_date FROM mytable where predicted_payment_date is NOT NULL";
	ResultSet rs = stmt.executeQuery(sql);
	//STEP 5: Extract data from result set
	while(rs.next()){
	//Retrieve by column name
	String customer_name = rs.getString("name_customer");
	String customer_number = rs.getString("cust_number");
	int invoice = rs.getInt("invoice_id");
	int amount = rs.getInt("converted_amount");
	String date=rs.getString("due_in_date");
	String final_date=rs.getString("predicted_payment_date");
	//Display values
	System.out.print("customer_name: " + customer_name);
	System.out.print(", customer_number: " + customer_number);
	System.out.print(", invoice: " + invoice);
	System.out.print(", amount: " + amount);
	System.out.print("date: " + date);
	System.out.println("final_date: " + final_date);

	}
	//STEP 6: Clean-up environment
	rs.close();
	stmt.close();
	conn.close();
	}catch(SQLException se) {
	//Handle errors for JDBC
	se.printStackTrace();
	}catch(Exception e) {
	//Handle errors for class. forName
	e.printStackTrace();
	}finally{
	//finally block used to close resources
	try{
	if(stmt !=null)
	stmt.close();
	}catch(SQLException se2){
	}// nothing we can do
	try{
	if(conn!=null)
	conn.close();
	}catch(SQLException se) {
	se.printStackTrace();
	}
	}
	}
}

