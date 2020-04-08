package ApplicationServlets;

import Database.DatabaseConnection;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ModelObjects.*;

public class deleteProduct extends HttpServlet {

    DatabaseConnection db;
    Product product;
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
        
        product = new Product(customerID);
        
        state=db.removeProduct(product);
        
        if(state){        
           out.print("<p>Record has been deleted!</p>");
        }
        else{
           out.println("Sorry! unable to save record");
        }

       deletedCustomer = request.getRequestDispatcher("productList");
       deletedCustomer.include(request, response);
         
        


    }
    
    
}
