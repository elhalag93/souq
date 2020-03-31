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
            String st = "<html>\n"
                    + "    <head>\n"
                    + "        <title>TODO supply a title</title>\n"
                    + "        <meta charset=\"UTF-8\">\n"
                    + "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                    + "    </head>\n"
                    + "    <body>\n"
                    + "<table>\n"
                    + " <tbody><tr>\n"
                    + "    <th>user name</th>\n"
                    + "    <th>password</th> \n"
                    + "    <th>balance</th>\n"
                    + "    <th>job</th>\n"
                    + "    <th>email</th>\n"
                    + "    <th>address</th>\n"
                    + "    <th>birth_date</th>\n"
                    + "    <th>intersts</th>\n"
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


