
package eelu;


import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import java.sql.DriverManager;
import java.sql.ResultSet;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean
@SessionScoped
public class User {

    String username;
    String password;
    
    static final String JBDC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://sql2.freemysqlhosting.net/sql2267232";
    
    static final String USER= "sql2267232";
    static final String PASS= "vP7%zJ1%";      
    
    
    public User() 
    {
  
  
    }

       public String verify() {
        
       Connection conn = null; 
       Statement stmt = null;
       boolean verified = false;
       
       try{
           
           Class.forName("com.mysql.jdbc.Driver");
           
           conn= (Connection) DriverManager.getConnection(DB_URL, USER , PASS );
           
           
           stmt= (Statement) conn.createStatement();
           String sql;
           sql= "SELECT `id` FROM `User` WHERE `username` = "
                  + " '"+username+"' AND `password` = '"+password+"'"; 
           
           ResultSet rs = stmt.executeQuery(sql);
           
           if(rs.next())
               verified = true;
           
           
           rs.close();
           stmt.close();
           conn.close();
           
       }
       catch (Exception se){
       return "error.xhml";
       }
  if (verified)
      return "Web_Application_todo.xhtml";
  else
      return null;
       

    }
       
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
}
