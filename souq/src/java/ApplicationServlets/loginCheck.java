package ApplicationServlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Database.DatabaseConnection;
import ModelObjects.*;
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
        PrintWriter out = response.getWriter();
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
         HttpSession session=request.getSession(true);
         
         customer = new Customer(email, password);
         privilage=db.loginCheck(customer);
         
         System.out.println("###############"+privilage+"#############");
         
         if(privilage.equalsIgnoreCase("admin"))
         {
                session.setAttribute("login", "true");
                session.setAttribute("privilege",privilage);
               response.sendRedirect("/application/AdminPages/Checkbox/choice.html");
         }
         else if(privilage.equalsIgnoreCase("customer"))
         {
             out.println("customer");
         }
         else{
             out.println("not admin");
         }

        
    }
    
    
}
