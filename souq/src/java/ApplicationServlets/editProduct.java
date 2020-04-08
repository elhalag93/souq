package ApplicationServlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Database.DatabaseConnection;
import ModelObjects.*;


public class editProduct extends HttpServlet {
        
    RequestDispatcher prod;
    DatabaseConnection db;
    Product product;

    @Override
    public void init() throws ServletException {
        db = new DatabaseConnection();   
    }
    
        @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
              
       PrintWriter out = response.getWriter();
       response.setContentType("text/html"); 
       HttpSession session=request.getSession(false);
       
       String id = request.getParameter("id");
       int prodID = Integer.parseInt(id);
        System.out.println(id);
       session.setAttribute("id",id);

       
//        Cookie updateCookie = new Cookie("productID", id);
//        response.addCookie(updateCookie);
       
       
       product = db.getProductInfo(new Product(prodID));
       
       out.print("<form action='updateProduct' method='Get'>"); 
         
       out.print("<label for=\"name\">Product Name</label><br>");  
       out.print("<input type=\"text\" id=\"productName\" name=\"productName\" value="+product.getProductName()+"><br>");
       
       out.print("<label for=\"password\">Quantity</label><br>");
       out.print("<input type=\"text\" id=\"quantity\" name=\"quantity\" value="+product.getProductQuantity()+"><br>");
       
       out.print("<label for=\"name\">Price</label><br>");
       out.print("<input type=\"text\" id=\"price\" name=\"price\" value="+product.getProductPrice()+"><br>");  

       out.print("<label for=\"password\">Short Description</label><br>");
       out.print("<input type=\"text\" id=\"shortDesc\" name=\"shortDesc\" value="+product.getProductShortDesc()+"><br>");  

       out.print("<label for=\"password\">Full Description</label><br>");
       out.print("<input type=\"text\" id=\"fullDesc\" name=\"fullDesc\" value="+product.getProductFullDesc()+"><br>"); 
       
       out.print("<label for=\"password\">Category ID </label><br>");
       out.print("<input type=\"text\" id=\"categoryID\" name=\"categoryID\" value="+product.getCategoryId()+"><br>");  

       out.print("<label for=\"password\">Product URL </label><br>");  
       out.print("<input type=\"text\" id=\"productURL\" name=\"productURL\" value="+product.getProductURL()+"><br>");  
         
       out.print("<input type='submit' value='Edit & Save '/>");  
 
       out.print("</form>"); 
   }
}
