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
 * @author M Gamal
 */
public class delet_user extends HttpServlet {

    

  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
         PrintWriter out = response.getWriter();
         
         Integer id=Integer.valueOf(request.getParameter("id"));
         
        PreparedStatement pst;
        connect con;
        Connection conn;
        con = new connect();
        try {
            conn = con.establish();
            pst = conn.prepareStatement("delete from users where user_id=?;");
            pst.setInt(1, id);
            System.out.println(pst.execute());
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(delet_user.class.getName()).log(Level.SEVERE, null, ex);
        }
           // change it in deployment link
        response.sendRedirect("http://localhost:8080/test/all_users");
    }
}
