package com.example.springmvcexample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import bdUtil.DBConnect;

@Controller
@RequestMapping("/trainee")
public class TraineeController {

    @GetMapping("/getAll")
    public String getAllTrainees() {
    	
    	String dbURL = "jdbc:mysql://localhost:3306/ip23db";
		String username = "root";
		String password = "";
		String result = null;
    	try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(dbURL, username, password);
			System.out.println("connection successfully opened :" + connection.getMetaData());
			
			//step 3 create JDBC statement
			String sql = "select * from trainee";
			Statement stmt = connection.createStatement();
			//Step 4 execute sql statement
			ResultSet rs = stmt.executeQuery(sql);
			
			//step 5
			while(rs.next()) {
				
				System.out.println("ID: "+rs.getInt("id"));
				System.out.println("name: "+rs.getString(2));
				System.out.println("weight: "+rs.getInt("weight"));
				System.out.println("bmi: "+rs.getDouble("weight"));
				
			}
			
			//step 6&7
			connection.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
        //return "trainee/getAll";
    	return "this is from getAll-trainee ";
    	
    }

    @RequestMapping("/getById")
    @ResponseBody()
    public String getById(HttpServletRequest request) {
    	
	    int id = Integer.parseInt(request.getParameter("id")); 
	    try {
		    Connection conn = DBConnect.openConnection(); 
		    Statement stmt = conn.createStatement();
		    String sql = "select * from trainee where id="+id; 
		    ResultSet rs = stmt.executeQuery(sql);
		    
		    while (rs.next()) {
			    System.out.println("ID: "+rs.getInt("id")); 
			    System.out.println("name: "+rs.getString(2));
		    }
	    } catch (SQLException e) {
	    	  e.printStackTrace();
	    }
	 
	    // TODO Auto-generated catch block
	  
	    	return "this is from getById - Trainee";
    }
    
    

   /* @GetMapping("/getById")
    public String getTraineeById() {
        return "trainee/getById";
    }*/

    @RequestMapping("/add")
    @ResponseBody()
    public String add(HttpServletRequest request) {
        //String name = request.getParameter("name");
       // int weight = Integer.parseInt(request.getParameter("weight"));
    	int rowsAffected = 0;
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DBConnect.openConnection();
            System.out.println("connection successfully opened: "+ conn.getMetaData());
            String sql = "INSERT INTO trainee (name, weight, height, bmi) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            stmt.setString(1,"ahmad");
            stmt.setDouble(2,80);
            stmt.setFloat(3,(float)1.80);
            stmt.setDouble(4,21.2);
            rowsAffected = stmt.executeUpdate();
           /* try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, name);
                pstmt.setInt(2, weight);
                pstmt.setString(3, height);
                pstmt.setInt(4, bmi);
                int rowsAffected = pstmt.executeUpdate();
                System.out.println("Rows affected: " + rowsAffected);
            }*/
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        } 
        catch (ClassNotFoundException ex) {
        	ex.printStackTrace();
        }
        return "this is from addTrainee - Trainee, rowsAffected= "+rowsAffected;
    }

    @RequestMapping("/update")
    @ResponseBody
    public String update(HttpServletRequest request) {
    	int id = Integer.parseInt(request.getParameter("id")); 
        int rowsAffected = 0;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DBConnect.openConnection();
            System.out.println("ID: " + id);
            System.out.println("Connection successfully opened: " + conn.getMetaData());

            String sql = "UPDATE trainee SET name = ?, weight = ?, height = ?, bmi = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, "abu");
                stmt.setDouble(2, 85);
                stmt.setFloat(3, (float) 1.75);
                stmt.setDouble(4, 23.5);
                stmt.setInt(5, id);
                rowsAffected = stmt.executeUpdate();
         
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return "This is from updateTrainee - Trainee, rowsAffected = " + rowsAffected;
    }


    @RequestMapping("/delete")
    @ResponseBody
    public String deleteTrainee(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        int rowsAffected = 0;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DBConnect.openConnection();
            System.out.println("Connection successfully opened: " + conn.getMetaData());

            String sql = "DELETE FROM trainee WHERE id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);
                rowsAffected = stmt.executeUpdate();
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return "This is from deleteTrainee - Trainee, rowsAffected = " + rowsAffected;
    }
}
