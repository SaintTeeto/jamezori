/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author 5ia13jathomas
 */
@ApplicationScoped
@ManagedBean

public class UserDAO {
    
    
    Connection conn = null;
    
    
    public UserDAO() throws ClassNotFoundException, SQLException{
        
        
        Class.forName("com.mysql.jdbc.Driver");
        
        conn = DriverManager.getConnection("jdbc:mysql://localhost","root","");
        
    }
    
    private void createDatabase()  throws ClassNotFoundException, SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root","");
            Statement st = conn.createStatement();
            st.executeUpdate("CREATE DATABASE IF NOT EXISTS jamezori");
            System.out.println("Database created !");
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void createTable()  throws ClassNotFoundException, SQLException{
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jamezori","root","");
            Statement stat = conn.createStatement();
            
            stat.execute("create table IF NOT EXISTS user(userID varchar(255),pwd varchar(255));");
            
        } 
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public boolean registerUser(String username, String password) throws ClassNotFoundException, SQLException 
    {
        
        createDatabase();
        createTable();
        
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jamezori", "root", "");
            
            Statement stmt = conn.createStatement();
            
            String doesUserExist = "select userID from user;";
            String strSql = "insert into user(userID,pwd) values ('" + username + "','" + password + "');";
            
            ResultSet set = stmt.executeQuery(doesUserExist);
            
            while(set.next())
            {
                if(username.equals(set.getString("userID")))
                {
                    return false;
                }    
            }
            
            stmt.executeUpdate(strSql);
            
            return true;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return false;
        }
    }
    
}
