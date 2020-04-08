package ApplicationServlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ModelObjects.*;
import Database.DatabaseConnection;
import java.math.BigDecimal;
import javax.servlet.RequestDispatcher;


public class registerCheck extends HttpServlet {
    BigDecimal uBalance;
    DatabaseConnection db;
    Customer customer;
    Boolean state;
    
    
    @Override
    public void init() throws ServletException {
        uBalance =new BigDecimal("0");
        db = new DatabaseConnection();    
    } 
    
     @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            
        PrintWriter out = response.getWriter();
        
        String username = request.getParameter("user_name");
        String password = request.getParameter("password");
        String job = request.getParameter("job");
        String email = request.getParameter("email");
        String birth_date = request.getParameter("birth_date");
        //(2015-03-31) date_format
        Date date = Date.valueOf(birth_date);
        
        String address = request.getParameter("address");
        String interests = request.getParameter("interests");
        
//        HttpSession session = request.getSession(true);
        
        customer = new Customer(username, password, uBalance, job, email, address, date, interests);
        System.out.println("###############"+customer.getUInterest()+"##########");
        
        state=db.registerCheck(customer);
        
        if(state){
//            out.print("<p>Welcome To Our Website !</p>");
             response.sendRedirect("/application/LoginPage/login.html");
        }
        else{
//            out.println("<p>Sorry, This account already exists !</p>");
//            RequestDispatcher inc = request.getRequestDispatcher("/RegisterPage/register.html");
//            inc.forward(request, response);
             response.sendRedirect("/application/RegisterPage/register.html");
        }
        
        
    }
    
    
}
