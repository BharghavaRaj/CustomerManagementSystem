package com.techpalle.dao;

import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.techpalle.model.Customer;

public class DataAccess 
{
	private static final String dburl ="jdbc:mysql://localhost:3306/bharghav";
	private static final String dbusername ="root";
	private static final String dbpassword ="admin";
	
	private static Connection con=null;
	private static PreparedStatement ps=null;
	private static ResultSet rs=null;

	private static final String inserQuery ="insert into customer(name,email,password,mobile,state) values(?,?,?,?,?)";
	private static final String validateQuery="select email,password from customer where email = ? and password = ?";
	
	public static boolean validateCustomer(String email,String password) 
	{
		boolean b=false;
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			con=DriverManager.getConnection(dburl, dbusername, dbpassword);
			
			ps=con.prepareStatement(validateQuery);
			ps.setString(1, email);
			ps.setString(2, password);
			
			rs=ps.executeQuery();
			
			b=rs.next();
			
			/*if(rs !=null) 
			{
				rs.next();
				String e = rs.getString("tbEmail");
				String p = rs.getString("tbPass");
				if(e.equals(email)&&p.equals(password)) 
				{
					b=true;
				}
			}*/			
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(rs != null) 
				{
					rs.close();
				}
				if(ps != null) 
				{
					ps.close();
				}
				if(con != null) 
				{
					con.close();
				}
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		return b;
	}
	
	public static void insertCustomer(Customer customer) 
	{
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			con=DriverManager.getConnection(dburl, dbusername, dbpassword);
			
			ps=con.prepareStatement(inserQuery);
			ps.setString(1, customer.getName());
			ps.setNString(2, customer.getEmail());
			ps.setString(3, customer.getPassword());
			ps.setLong(4, customer.getMobile());
			ps.setString(5, customer.getState());
			
			ps.executeUpdate();
			
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			try 
			{	
				if(ps != null) 
				{
					ps.close();
				}
				if(con != null) 
				{
					con.close();
				}
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
	}
}