package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Bill {
	private Connection connect()
	{
		Connection con = null;
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
 
			con = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/electricbill", "root", "mathssin90");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return con;
	}
	
	public String readBills()
	{
		String output = "";
		
		try
		{
			Connection con = connect();
			
			if (con == null)
			{
				return "Error while connecting to the database for reading.";
			}
 
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Name</th><th>Address</th><th>Unitsconsumed</th>"+"<th>Billamount</th><th>Date</th><th>Update</th><th>Remove</th></tr>";
 
			String query = "select * from customer";
			
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery(query);
 
			// iterate through the rows in the result set
			while (rs.next())
			{
				String id = Integer.toString(rs.getInt("id"));
				String name = rs.getString("name"); 
				String address = rs.getString("address");
				String unitsconsumed = rs.getString("unitsconsumed");
				String billamount = rs.getString("billamount");
				String date = rs.getString("date");
 
				// Add into the html table
				output += "<tr><td><input id='hidItemIDUpdate'name='hidItemIDUpdate'type='hidden' value='" + id+ "'>" + name + "</td>";
				output += "<td>" + address + "</td>";
				output += "<td>" + unitsconsumed + "</td>";
				output += "<td>" + billamount + "</td>";
				output += "<td>" + date + "</td>";
 
				// buttons
				output += "<td><input name='btnUpdate'type='button' value='Update'class='btnUpdate btn btn-secondary'></td>"+ "<td><input name='btnRemove'type='button' value='Remove'class='btnRemove btn btn-danger'data-itemid='"+ id + "'>" + "</td></tr>";
			}
 
			con.close();
 
			// Complete the html table
			output += "</table>";
		}
 
		catch (Exception e)
		{
			output = "Error while reading the bills.";
			System.err.println(e.getMessage());
		}
 
		return output;
	}
	
	public String insertBills(String Billname,String Billaddress, String Billunitsconsumed,String Billbillamount,String Billdate)
    {
		String output = "";

		try
		{
			Connection con = connect();
			
			if (con == null)
			{
				return "Error while connecting to the database for inserting.";
			}
 
			// create a prepared statement
			String query = " insert into customer(`id`,`name`,`address`,`unitsconsumed`,`billamount`,`date`)"+ " values (?, ?, ?, ?, ?, ?)";
		 
			PreparedStatement preparedStmt = con.prepareStatement(query);
		 
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, Billname);
			preparedStmt.setString(3, Billaddress);
			preparedStmt.setString(4, Billunitsconsumed);
			preparedStmt.setString(5, Billbillamount);
			preparedStmt.setString(6, Billdate);
		 
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newItems = readBills();
			output = "{\"status\":\"success\", \"data\": \"" +newItems + "\"}";
		 }
		
		 catch (Exception e)
		 {
			 output = "{\"status\":\"error\", \"data\":\"Error while inserting the bills.\"}";
			 System.err.println(e.getMessage());
		 }
		
		 return output;
		 
		 }
	
		 public String updateBills(String Billid, String Billname, String Billaddress, String Billunitsconsumed, String Billbillamount,String Billdate)
		 {
			 String output = "";
			 try
			 {
				 Connection con = connect();
				 if (con == null)
				 {
					 return "Error while connecting to the database for updating.";
				 }
				 
				 // create a prepared statement
				 String query = "UPDATE customer SET name=?,address=?,unitsconsumed=?,billamount=?,date=? WHERE id=?";
				 PreparedStatement preparedStmt = con.prepareStatement(query);
		 
				 // binding values
				 preparedStmt.setString(1, Billname);
				 preparedStmt.setString(2, Billaddress);
				 preparedStmt.setString(3, Billunitsconsumed);
				 preparedStmt.setString(4, Billbillamount);
				 preparedStmt.setString(5, Billdate);
				 preparedStmt.setInt(6, Integer.parseInt(Billid)); 
		
				 // execute the statement
				 preparedStmt.execute();
				 con.close();
		
				 String newItems = readBills();
				 output = "{\"status\":\"success\", \"data\": \"" +
				 newItems + "\"}";
		 }
			 
		 catch (Exception e)
		 {
			 output = "{\"status\":\"error\", \"data\": \"Error while updating the bills.\"}";
			 System.err.println(e.getMessage());
		 }
		 
		return output;
	}
		
	public String deleteBills(String id)
	{
		 String output = "";
		 try
		 {
			 Connection con = connect();
			 if (con == null)
			 {
				 return "Error while connecting to the database for deleting.";
			 }
		 
			 // create a prepared statement
			 String query = "delete from customer where id=?";
			 PreparedStatement preparedStmt = con.prepareStatement(query);
		 
			 // binding values
			 preparedStmt.setInt(1, Integer.parseInt(id));
		 
			 // execute the statement
			 preparedStmt.execute();
			 con.close();
			 String newItems = readBills();
			 output = "{\"status\":\"success\", \"data\": \"" +
			 newItems + "\"}";
		 }
		 
		 catch (Exception e)
		 {
			 output = "{\"status\":\"error\", \"data\": \"Error while deleting the bills.\"}";
			 System.err.println(e.getMessage());
		 }
		 
		 return output;
		 
		 }

}
