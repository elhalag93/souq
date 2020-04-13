package sql;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import ModelObjects.*;
import com.google.gson.Gson;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

public class loginCheck extends HttpServlet {

    DatabaseConnection db;
    Customer customer;
    String privilage;

    @Override
    public void init() throws ServletException {
        db = new DatabaseConnection();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        Cookie[] cookies = request.getCookies();
//       if(cookies.equals(null)){
//       
//       }

        PrintWriter out = response.getWriter();

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        HttpSession session = request.getSession(true);
        Cookie c1 = new Cookie("session_id", session.getId());
        c1.setMaxAge(60 * 60 * 24 * 7);
        response.addCookie(c1);
        customer = new Customer(email, password);
        privilage = db.loginCheck(customer);

        System.out.println("###############" + privilage + "#############");

        if (privilage.equals("admin")) {
            session.setAttribute("login","true");
            session.setAttribute("privilege", privilage);
            response.sendRedirect("/application/AdminPages/Checkbox/choice.html");
        } else if (privilage.equals("customer")) {
            
            int customer_id=db.getUserId(email);
           session.setAttribute("customer_id", customer_id);
           out.println(customer_id);
            Gson string_cart = new Gson();
            cart_object cart = new cart_object();
            session.setAttribute("cart", string_cart.toJson(cart));
            out.println("customer");
        } else {
            out.println(privilage);
        }

    }

}
