package ApplicationServlets;


import ModelObjects.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Database.DatabaseConnection;
import java.util.Vector;
import javax.servlet.http.HttpSession;


public class customerList extends HttpServlet {
        
    
    Vector<Customer> customers;
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
           customers = database.getAllCustomers();
       

            String st ="<html>\n"
                       + "<head>\n"
                       + "<style>\n"
                       + "#user {\n"
                       + "  font-family: \"Trebuchet MS\", Arial, Helvetica, sans-serif;\n"
                       + "  border-collapse: collapse;\n"
                       + "  text-align: center;\n"
                       + "  width: 100%;\n"
                       + "}\n"
                       + "\n"
                       + "#user td, #user th {\n"
                       + "  border: 1px solid #ddd;\n"
                       + "  padding: 8px;\n"
                       + "}\n"
                       + "\n"
                       + "#user tr:nth-child(even){background-color: #f2f2f2;}\n"
                       + "\n"
                       + "#user tr:hover {background-color: #ddd;}\n"
                       + "\n"
                       + "#user th {\n"
                       + "  padding-top: 12px;\n"
                       + "  padding-bottom: 12px;\n"
                       + "  text-align: left;\n"
                       + "  background-color: #4CAF50;\n"
                       + "  color: white;\n"
                       + "}\n"
                       + "</style>\n"
                       + "</head>\n"
                       + "<body>\n"
                       + "\n"
                       + "<h2>User List</h2>\n"
                       + "\n"
                       + "\n"
                       + "<table style=\"width:100%\" id=\"user\">\n"
                       + " <tbody><tr>\n"
                       + "    <th>user name</th>\n"
                       + "    <th>password</th> \n"
                       + "    <th>balance</th>\n"
                       + "    <th>job</th>\n"
                       + "    <th>email</th>\n"
                       + "    <th>address</th>\n"
                       + "    <th>birth_date</th>\n"
                       + "    <th>intersts</th>\n"
                       + "    <th>delet</th>\n"
                       + "  </tr>";


               for (Customer customer: customers) {
                   st += "<tr>\n"
                           + "<td>" + customer.getUID()
                           + "</td>\n";
                   st +="<td>" + customer.getUName()
                           + "</td>\n" ;
                   st +="<td>" + customer.getUPasswd()
                           + "</td>\n" ;
                   st +=  "<td>" + customer.getUBalance()
                           + "</td>\n";
                   st +=  "<td>" + customer.getUEmail()
                           + "</td>\n" ;
                   st += "<td>" + customer.getUAddress()
                           + "</td>\n" ;
                   st += "<td>" + customer.getUBirthdate()
                           + "</td>\n";
                   st +=  "<td>" + customer.getUInterest();
                   st+= "<td><button onclick=\"window.location.href = 'http://localhost:8084/application/deleteCustomer?id="+customer.getUID()+"';\">delet</button></td>"
                           + "</td>\n" + "</tr>\n";

               }


               st += "</table>\n"
                       + "</body>\n"
                       + "</html>\n";


               out.println(st); 
        }
        
        else{
            out.print("You are not logged");
        }

            
    }
    
}
