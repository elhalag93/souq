/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sql;
import java.text.SimpleDateFormat;  
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

/**
 *
 * @author M Gamal
 */
public class sign_up extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        String username = request.getParameter("user_name");
        String password = request.getParameter("password");
        String job = request.getParameter("job");
        String email = request.getParameter("email");
        String birth_date = request.getParameter("birth_date");
        String address = request.getParameter("address");
        String interests = request.getParameter("interests");
        HttpSession session = request.getSession(true);
        PreparedStatement pst;
        connect con;
        Connection conn;
        con = new connect();
        try {
            conn = con.establish();
            pst = conn.prepareStatement("insert into users(privilege,user_name,balance"
                    + ",password,job,email,address,birth_date,interests) "
                    + "values('customer',?,10000,?,?,?,?,?,?)");
            pst.setString(1, username);
            pst.setString(2, password);
            pst.setString(3, job);
            pst.setString(4, email);
            pst.setString(5, address);
            //(2015-03-31) date_format
            Date date = Date.valueOf(birth_date);
            pst.setDate(6, date);
            pst.setString(7, interests);
            boolean state = true;
            try {
                state = pst.execute();
            } catch (SQLException e) {
                out.println(e.getLocalizedMessage());
            }
            if (state == false) {
                out.println("sucess");
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(sign_up.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(sign_up.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

   
}
