/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sql;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
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
public class login extends HttpServlet {

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         PrintWriter out = response.getWriter();
        String username = request.getParameter("use_name");
        String password = request.getParameter("password");
        if(username.equals("")||password.equals("")){
            response.sendRedirect("login.html");

        }
        else{
         HttpSession session=request.getSession(true);
        PreparedStatement pst;
        connect con;
        Connection conn;
        con = new connect();
        try {
            conn = con.establish();
            pst = conn.prepareStatement("select * from users where user_name=?");
            pst.setString(1, username);
            ResultSet result = pst.executeQuery();
            result.next();
            out.println(result.getString("password"));
            if (result.getString("password").equals(password)) {
                  session.setAttribute("login", "true");
                  session.setAttribute("privilege",result.getString("privilege") );
                response.sendRedirect("all_users");
            } else {
                session.setAttribute("login", "false");
                response.sendRedirect("login.html");
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }   
}
}