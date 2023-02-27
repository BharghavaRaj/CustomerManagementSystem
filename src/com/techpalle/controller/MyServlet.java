package com.techpalle.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.techpalle.dao.DataAccess;
import com.techpalle.model.Customer;
import com.techpalle.util.SuccessPage;

@WebServlet("/")
public class MyServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String path = request.getServletPath();
		
		switch(path) 
		{
		case "/logCustomer":
			validateCustomer(request, response);
			break;
		case "/regCustomer":
			insertCustomer(request, response);
			break;
		case "/log":
			getLoginPage(request,response);
			break;
		case "/reg":
			getRegistrationPage(request, response);
			break;	
			default:
				getStartUpPage(request ,response);
				break;
		}
	}
	
	private void validateCustomer(HttpServletRequest request, HttpServletResponse response) 
	{
		//Read the email and password from login page and call the method in DAO
		boolean res=DataAccess.validateCustomer(request.getParameter("tbEmail"), request.getParameter("tbPass"));
		
		//Condition and Redirect user to destination page (success)
		if(res==true) 
		{
			try 
			{
				RequestDispatcher rd=request.getRequestDispatcher("success.jsp");
				//Store the success page class data inside RD
				
				SuccessPage sp = new SuccessPage();
				request.setAttribute("successDetails",sp);//as the data in JSP 
				
				rd.forward(request, response);
			} 
			catch (ServletException e) 
			{
				e.printStackTrace();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
		else 
		{
			getLoginPage(request, response);
		}
	}
	private void insertCustomer(HttpServletRequest request, HttpServletResponse response) 
	{
//		Read the data from jsp page
		
		String n=request.getParameter("tbName");
		String e=request.getParameter("tbEmail");
		String p=request.getParameter("tbPass");
		long m=Long.parseLong(request.getParameter("tbMobile"));
		String s=request.getParameter("ddlState");
		
//		store that data in customer object
		
		Customer c = new Customer(n,e,p,m,s);
		
//		call insetCutomer method present in dao by passing above 
		
		DataAccess.insertCustomer(c);
		
//		Readirect user to login page with email and password
		try 
		{
			RequestDispatcher rd=request.getRequestDispatcher("customer_login.jsp");
			rd.forward(request, response);
		} 
		catch (ServletException e1) 
		{
			e1.printStackTrace();
		} 
		catch (IOException e1) 
		{
			e1.printStackTrace();
		}
	}
	private void getLoginPage(HttpServletRequest request, HttpServletResponse response) 
	{
		try 
		{
			RequestDispatcher rd=request.getRequestDispatcher("customer_login.jsp");
			rd.forward(request, response);
		} 
		catch (ServletException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	private void getRegistrationPage(HttpServletRequest request, HttpServletResponse response) 
	{
		try 
		{
			RequestDispatcher rd=request.getRequestDispatcher("customer_registration.jsp");
			rd.forward(request, response);
		} 
		catch (ServletException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	private void getStartUpPage(HttpServletRequest request, HttpServletResponse response) 
	{		
		try 
		{
			RequestDispatcher rd=request.getRequestDispatcher("customer_management.jsp");
			rd.forward(request, response);
		} 
		catch (ServletException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}