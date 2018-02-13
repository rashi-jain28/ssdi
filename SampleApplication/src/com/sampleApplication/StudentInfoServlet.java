package com.sampleApplication;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class Hello
 */
@WebServlet("/StudentInfoServlet")
public class StudentInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public StudentInfoServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
        PrintWriter out  = response.getWriter();
        out.println("<h2>Student information!</h2>");
        
		out.println("<html><body><table border='4'><tr>");
		out.println("<td>Name</td><td>Email</td>"
				+ "<td>Course</td><td>University</td></tr>");
		
		Statement stmt = null;
		Connection con = null;
 
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = (Connection) DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/Student", "root", "Qwerty@123");

			 stmt = (Statement) con.createStatement();
	         String sql;
	         sql = "SELECT * FROM studentdetails";
	         ResultSet rs = stmt.executeQuery(sql);
	         
	         // Extract data from result set
	         while(rs.next()){
	            //Retrieve by column name
	            String name  = rs.getString("name");
	            String email = rs.getString("email");
	            String course = rs.getString("course");
	            String university = rs.getString("university");
	                      

	            //Display values
	            out.println("<tr>");
	            out.println("<td>" + name + "</td>");
	            out.println("<td>" + email + "</td>");
	            out.println("<td>" + course + "</td>");
	            out.println("<td>" + university + "</td>");
	            out.println("</tr>");
	            
	         }
	         out.println("</body></html>");
	         
	         // Clean-up environment
	         rs.close();
	         stmt.close();
	         con.close();
	      } catch(SQLException se) {
	         //Handle errors for JDBC
	         se.printStackTrace();
	      } catch(Exception e) {
	         //Handle errors for Class.forName
	         e.printStackTrace();
	      } finally {
	         //finally block used to close resources
	         try {
	            if(stmt!=null)
	               stmt.close();
	         } catch(SQLException se2) {
	         } // nothing we can do
	         try {
	            if(con!=null)
	            con.close();
	         } catch(SQLException se) {
	            se.printStackTrace();
	         } //end finally try
	      } //end try
	   }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
	}

}
