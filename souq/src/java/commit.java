/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sql;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author mo
 */
public class commit extends HttpServlet {


    DatabaseConnection db;
    //Customer customer;
    @Override
    public void init() throws ServletException {
       db = new DatabaseConnection();
       
    }    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
       // out.print("hi");
        
        HttpSession session = request.getSession(false);
        String _total=session.getAttribute("total").toString();
       // String _id=session.getAttribute("customer_id").toString();
        String _id=session.getAttribute("customer_id").toString();      
        int id = Integer.parseInt(_id);        
        int total = Integer.parseInt(_total);
        int balance=db.getUserBalance(id);
        out.println(id);
        if(total<=balance){
            balance -= total;
            out.println("successful Payment");
            out.print("Thanks for choosing us ^_^");

        }
        else{
            out.print("Error has just happened..plz check your balance");
        }
    }





}
