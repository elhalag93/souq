/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sql;


import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
public class cart_servlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(false);
        String product_id = request.getParameter("product_id");
        String action = request.getParameter("action");
        String privilege = (String) session.getAttribute("privilege");
        String session_cart=(String) session.getAttribute("cart");
        PreparedStatement pst;
        DatabaseConnection con;
        Connection conn;
        con = new DatabaseConnection();
        Product product = con.selectProduct(Integer.parseInt(product_id));
        Gson gson = new Gson();
        cart_object cart = gson.fromJson(session_cart, cart_object.class);
        cart.add(product); 
        ArrayList<Product> cartItems=cart.cart;
        session.setAttribute("cart", gson.toJson(cart));
        out.print(gson.toJson(cart));
//        if(product.getCategoryId()==1){
//        response.sendRedirect("");
//        }
//        //////////////////////
//        if(product.getCategoryId()==2){
//        response.sendRedirect("");
//        }
    }        

    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        PrintWriter out = response.getWriter();
//        HttpSession session = request.getSession(false);
//        String product_id = request.getParameter("product_id");
//        String action = request.getParameter("action");
//        String privilege = (String) session.getAttribute("privilege");
//        String session_cart=(String) session.getAttribute("cart");
//        PreparedStatement pst;
//        DatabaseConnection con;
//        Connection conn;
//        con = new DatabaseConnection();
//        Product product = con.selectProduct(Integer.parseInt(product_id));
//        Gson gson = new Gson();
//        cart_object cart = gson.fromJson(session_cart, cart_object.class);
//        cart.add(product); 
//        ArrayList<Product> cartItems=cart.cart;
//        session.setAttribute("cart", gson.toJson(cart));
//        if(product.getCategoryId()==1){
//        response.sendRedirect("");
//        }
//        //////////////////////
//        if(product.getCategoryId()==2){
//        response.sendRedirect("");
//        }
//    }


