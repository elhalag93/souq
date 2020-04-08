package ApplicationServlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Database.DatabaseConnection;
import ModelObjects.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;


public class updateProduct extends HttpServlet {

     
    RequestDispatcher updatedproduct;
    DatabaseConnection db; 
    Product product;
    Boolean state;

    @Override
    public void init() throws ServletException {
            db=new DatabaseConnection();
    }
    
    
    
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        

//    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {        
        PrintWriter out = response.getWriter();
        
        HttpSession session = request.getSession(false);
        String _id=(String) session.getAttribute("id");
        int id = Integer.parseInt(_id);

        
//        System.out.println("############## gabooooo " + _id);

        product = new Product();
        
        product.setProductID(id);
        product.setProductName(request.getParameter("productName"));
        product.setProductQuantity(Integer.parseInt(request.getParameter("quantity"))); 
        product.setProductPrice(Integer.parseInt(request.getParameter("price")));
        product.setProductShortDesc(request.getParameter("shortDesc")); 
        product.setProductFullDesc(request.getParameter("fullDesc"));
        product.setCategoryId(Integer.parseInt(request.getParameter("categoryID")));
        product.setProductURL(request.getParameter("productURL"));      


        state=db.editProduct(product);    
        
         
      if(state){        
           out.print("<p>Record has been edited!</p>");
        }
        else{
           out.println("Sorry! unable to edit record");
        }

       updatedproduct = request.getRequestDispatcher("productList");
       updatedproduct.include(request, response);
        
    
    }
     
     
}
