package ApplicationServlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Database.DatabaseConnection;
import ModelObjects.*;
import javax.servlet.http.HttpSession;

public class saveProduct extends HttpServlet {

   
    RequestDispatcher newProd;
    DatabaseConnection db;
    Product product;
    Boolean state;
    @Override
    public void init() throws ServletException {
            db = new DatabaseConnection();
    }
    
     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        
        HttpSession session=request.getSession(false);
        
        String pordName = request.getParameter("productName");
        
        String pordQuantity = request.getParameter("quantity");
        int quantity = Integer.parseInt(pordQuantity);
        
        String pordPrice = request.getParameter("price");
        int price = Integer.parseInt(pordPrice);
        
        String pordShortDesc = request.getParameter("shortDesc");
        String pordFullDesc = request.getParameter("fullDesc");
        
        String category= request.getParameter("choices-single-defaul");
        
         System.out.println(category);
        
        String pordURL = request.getParameter("productURL");
       
               
        if(category.equalsIgnoreCase("mobile"))
        {
            System.out.println("enter mobile fi cond");
            product = new Product(pordName, quantity, price, pordShortDesc, pordFullDesc,1, pordURL);
        }
        else if(category.equalsIgnoreCase("laptop"))
        {
            System.out.println("enter laptop if condition");
            product = new Product(pordName, quantity, price, pordShortDesc, pordFullDesc,2, pordURL);
        }
        else
        {
            System.out.println("Big error");
        }
        
        System.out.println(product.getProductName() +" "+ product.getCategoryId() + "price:"+  product.getProductPrice());
        
        state=db.addProduct(product);
        
        System.out.println("############"+ state + "########");
        
        if(state){
            out.print("<p>Record saved successfully!</p>");          
            newProd = request.getRequestDispatcher("productList");
            newProd.forward(request, response);
        }
        else{
            out.println("<p>Sorry! unable to save record</p>");  
           response.sendRedirect("/application/AddProduct/newProduct.html");
       
        }
        
    }
}
