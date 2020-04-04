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
import javax.servlet.http.HttpSession;

/**
 *
 * @author M Gamal
 */
public class all_users extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        HttpSession session=request.getSession(false);
        String privilege=(String) session.getAttribute("privilege");
        if(privilege.equals("admin")){
        PreparedStatement pst;
        connect con;
        Connection conn;
        con = new connect();
        try {
            conn = con.establish();
            pst = conn.prepareStatement("select * from users where privilege='customer'");
            ResultSet result = pst.executeQuery();
            String st ="<html>\n"
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
                    + " <tbody><tr>\n"
                    + "    <th>user name</th>\n"
                    + "    <th>password</th> \n"
                    + "    <th>balance</th>\n"
                    + "    <th>job</th>\n"
                    + "    <th>email</th>\n"
                    + "    <th>address</th>\n"
                    + "    <th>birth_date</th>\n"
                    + "    <th>intersts</th>\n"
                    + "    <th>delet</th>\n"
                    + "  </tr>";
            while (result.next()) {
                st += "<tr>\n"
                        + "<td>" + result.getString(3)
                        + "</td>\n";
                st +="<td>" + result.getString(5)
                        + "</td>\n" ;
                st +="<td>" + result.getInt(4)
                        + "</td>\n" ;
                st +=  "<td>" + result.getString(6)
                        + "</td>\n";
                st +=  "<td>" + result.getString(7)
                        + "</td>\n" ;
                st += "<td>" + result.getString(8)
                        + "</td>\n" ;
                st += "<td>" + result.getDate(9)
                        + "</td>\n";
                st +=  "<td>" + result.getString(10);
                st+= "<td><button onclick=\"window.location.href = 'http://localhost:8080/test/delet_user?id="+result.getString(1)+"';\">delet</button></td>"
                        + "</td>\n" + "</tr>\n";
           
            }
            st += "</table>\n"
                    + "</body>\n"
                    + "</html>\n";

            System.out.println(st);
            out.println(st);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(all_users.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(all_users.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        else{response.sendRedirect("login.html");}
    }
    
}


