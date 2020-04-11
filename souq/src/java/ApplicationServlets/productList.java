package ApplicationServlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Database.DatabaseConnection;
import ModelObjects.*;
import java.util.Vector;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;


public class productList extends HttpServlet {
    
    Vector<Product> products;
    DatabaseConnection database;

    @Override
    public void init() throws ServletException {
       database = new DatabaseConnection();   
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
     
        HttpSession session=request.getSession(false);
        String status=(String) session.getAttribute("login");
        System.out.println("==>>>>>>>>>>"+status);
        
        if(status.equalsIgnoreCase("true"))
        {
        
            products = database.getAllProducts();
            
            RequestDispatcher header = request.getRequestDispatcher("/AdminPages/ProductList/listheader.html");
            header.include(request, response);

            for(Product prod : products){
                out.println( "<tr><td>" 
                + prod.getProductID() + "</td>"
                + "<td>" + prod.getProductName() + "</td>"
                + "<td>" + prod.getProductQuantity() + "</td>"
                + "<td>" + prod.getProductPrice()+ "</td>"
                + "<td>" + prod.getProductShortDesc() + "</td>"
                + "<td>" + prod.getProductFullDesc() + "</td>"
                + "<td>" + prod.getCategoryId() + "</td>"
                + "<td>" + prod.getProductURL() + "</td>"
                + "<td>" + "<a href='editProduct?id="+prod.getProductID()+"'>edit</a>" + "</td>"
                + "<td>" + "<a href='deleteProduct?id="+prod.getProductID()+"'>delete</a>" +
                        "</td></tr>" );
            }

            RequestDispatcher footer = request.getRequestDispatcher("/AdminPages/ProductList/listFooter.html");
            footer.include(request, response);
            
        }
        else{
            out.print("You are not logged");
        }

    }
    
    
}
