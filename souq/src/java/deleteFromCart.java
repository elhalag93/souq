/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sql;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author mo
 */
public class deleteFromCart extends HttpServlet {

  

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        PrintWriter out = response.getWriter();  
        
         HttpSession session = request.getSession(false);
    //String _cartItems=session.getAttribute("cartItems").toString();

        String session_cart=(String) session.getAttribute("cart");     
        
        Gson gson = new Gson();
        cart_object cart = gson.fromJson(session_cart, cart_object.class); 
        ArrayList<Product> cartItems=cart.cart; 
        
        
        
        
        String prd_id =request.getParameter("id"); 
        int id=Integer.parseInt(prd_id);
        cart.remove(id);
        
        
        session.setAttribute("cart", gson.toJson(cart));
        response.sendRedirect("listCart");
    }


}
