package ApplicationServlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ModelObjects.*;
import Database.DatabaseConnection;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

public class deleteCustomer extends HttpServlet {
    
    DatabaseConnection db;
    RequestDispatcher deletedCustomer;
    Boolean state;
    
    @Override
    public void init() throws ServletException {
        db = new DatabaseConnection();
    }
    
       @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        
        HttpSession session=request.getSession(false);
        
        String id = request.getParameter("id");
        int customerID = Integer.parseInt(id);
        
        Customer customer = new Customer(customerID);
        state = db.deleteCustomer(customer);
        
        
        if(state){        
           out.print("<p>Record has been deleted!</p>");
        }
        else{
           out.println("Sorry! unable to save record");
        }

       deletedCustomer = request.getRequestDispatcher("customerList");
       deletedCustomer.include(request, response);

    }
    
   
}
