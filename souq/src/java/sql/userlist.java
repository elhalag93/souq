/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sql;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Mohamed Ibrahim
 */
public class userlist extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out=resp.getWriter();
        Connection conn;
        try {
            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/webapp", "mohamed", "123");
            PreparedStatement pst;
            pst = conn.prepareStatement("SELECT NAME FROM MOHAMED.REG");
            ResultSet rs = pst.executeQuery();
            rs.next();
            String str = "<html>\n"
                    + "<head>\n"
                    + "<style>\n"
                    + "#user {\n"
                    + "  font-family: \"Trebuchet MS\", Arial, Helvetica, sans-serif;\n"
                    + "  border-collapse: collapse;\n"
                    + "  text-align: center;\n"
                    + "  width: 100%;\n"
                    + "}\n"
                    + "\n"
                    + "#user td, #user th {\n"
                    + "  border: 1px solid #ddd;\n"
                    + "  padding: 8px;\n"
                    + "}\n"
                    + "\n"
                    + "#user tr:nth-child(even){background-color: #f2f2f2;}\n"
                    + "\n"
                    + "#user tr:hover {background-color: #ddd;}\n"
                    + "\n"
                    + "#user th {\n"
                    + "  padding-top: 12px;\n"
                    + "  padding-bottom: 12px;\n"
                    + "  text-align: left;\n"
                    + "  background-color: #4CAF50;\n"
                    + "  color: white;\n"
                    + "}\n"
                    + "</style>\n"
                    + "</head>\n"
                    + "<body>\n"
                    + "\n"
                    + "<h2>User List</h2>\n"
                    + "\n"
                    + "\n"
                    + "<table style=\"width:100%\" id=\"user\">\n"
                    + "  <tr>\n"
                    + "    <th>Name</th>\n"
                    + "  </tr>\n";
                    while(rs.next()){
                        str+= "  <tr>\n"
                        + "<td>"+rs.getString(1)
                        +"</td>\n"
                        + "  </tr>\n";
                    }
                    
                    
                    
                    str+= "</table>\n"
                    + "\n"
                    + "</body>\n"
                    + "</html>";
               out.println(str);
               System.out.println(str);
//            System.out.println(rs.getString(1));
//            while (rs.next() == true) {
//            PrintWriter out=resp.getWriter();
////                System.out.println(rs.getString(1));
//
//            }

        } catch (SQLException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
