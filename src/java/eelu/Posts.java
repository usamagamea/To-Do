package eelu;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class Posts {

    ArrayList<Post> postsList = new ArrayList<>();

    static final String JBDC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://sql2.freemysqlhosting.net/sql2267232";

    static final String USER = "sql2267232";
    static final String PASS = "vP7%zJ1%";

    public Posts() {

        fillPosts();
    }

    public String fillPosts() {

        Connection conn = null;
        Statement stmt = null;

        try {

            Class.forName("com.mysql.jdbc.Driver");

            conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = (Statement) conn.createStatement();
            String sql;
            sql = "SELECT * FROM `Post`; ";

            ResultSet rs = stmt.executeQuery(sql);
            String sql2;
            while (rs.next()) {
                Post p = new Post();

                p.text = rs.getString("text");

                p.time = rs.getTimestamp(3).toString();

                Statement s = (Statement) conn.createStatement();

                sql2 = "SELECT `fristName`,`lastName`  FROM `User` WHERE `id` = "
                        + rs.getInt("user");

                ResultSet rs2 = stmt.executeQuery(sql2);
                rs2.next();
                p.name = rs2.getString("fristName") + " " + rs2.getString("lastName");

                postsList.add(p);

            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception se) {
            System.out.println("Error");
        }

        return "Web_Application_todo.xhtml";

    }

    public ArrayList<Post> getPostsList() {
        return postsList;
    }

    public void setPostsList(ArrayList<Post> postsList) {
        this.postsList = postsList;
    }

}
