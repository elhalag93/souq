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
import java.util.Vector;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author mo
 */
public class checkOut extends HttpServlet {

    Vector<Product> cartItems;
    DatabaseConnection database;
    Customer customer;
    int total=0;
    @Override
    public void init() throws ServletException {
       database = new DatabaseConnection();
       
    }
    
    
    
    
    
    
    
    
    
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
        
        
        
        
        
        
        
        
        
        
        //cartItems = database.getAllProducts();
        
        RequestDispatcher header = request.getRequestDispatcher("/checkoutHeader.html");
        header.include(request, response);
        
        for(Product prod : cartItems){
            out.println( "<tr><td>" 
             + prod.getProductName() + "</td>"
            + "<td>" + prod.getProductShortDesc() + "</td>"
            + "<td>" + prod.getProductPrice()+ "</td>"
            
            + "<td>" + "<a href='deleteFromCart?id="+prod.getProductId()+"'>delete</a>" +
                    "</td>" );
            total+=prod.getProductPrice();
        }
        
        out.print("<td>"+total+"</td></tr>");
        RequestDispatcher footer = request.getRequestDispatcher("/checkoutFooter.html");
        footer.include(request, response);
        
        HttpSession updateSession = request.getSession();
        updateSession.setAttribute("total",total);        
        
        
    }



}
