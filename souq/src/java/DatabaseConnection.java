package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseConnection {
    
    private final String url = "jdbc:postgresql://localhost:5432/souq";
    private final String user = "postgres";
    private final String password = "123456";
    Product product = new Product();
    Customer customer=new Customer();
   // addProduct add= new addProduct();
    private Connection connection;
    private String sqlcommand;
    private PreparedStatement preparedstatement;
    private ResultSet result;
    String privilage;
    Boolean state =false;    
    
    
    
    
    DatabaseConnection(){
        
            //addCategory();
            //removeCategory();
            //addProduct();
            //removeProduct();
    }
    
    private void connect(){
        
        try {
            
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connection to Database Succeeded");
            
        } catch (SQLException | ClassNotFoundException ex) {
            
            System.out.println("databaseconnection.dataBase.connect()error");
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    ////////////////////------------Customer-------------/////////////////////////////////
    public Boolean registerCheck(Customer customer){
        connect();
        
        try {
            sqlcommand = "SELECT registercheck(?,?,?,?,?,?,?,?)";
            preparedstatement = connection.prepareStatement(sqlcommand);
            preparedstatement.setString(1,customer.getUName());
            preparedstatement.setString(2,customer.getUPasswd());
            preparedstatement.setInt(3,customer.getUBalance());
            preparedstatement.setString(4,customer.getUJob());
            preparedstatement.setString(5,customer.getUEmail());
            preparedstatement.setString(6, customer.getUAddress());
            preparedstatement.setString(7,customer.getUBirthdate());
            preparedstatement.setString(8,customer.getUInterest());
            result = preparedstatement.executeQuery();
            
            while (result.next())
            {
                state = result.getBoolean(1);
            }
             
            System.out.println("Registration happened Successfully");
            return state;
        } catch (SQLException ex) {
            System.out.println("Registration has an exception");
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        finally{
            stop();
        }
   
    }    
       
    public String loginCheck(Customer customer){
        connect();    
        try {
            sqlcommand = "SELECT loginCheck(?,?)";
            preparedstatement = connection.prepareStatement(sqlcommand);
            preparedstatement.setString(1,customer.getUEmail());
            preparedstatement.setString(2,customer.getUPasswd());
            result = preparedstatement.executeQuery();

            while(result.next()){
                privilage = result.getString(1);
            }

            return privilage;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
            return "false";
        }
        finally{
            stop();
        }    
}
    
    public Vector<Customer> getAllCustomers(){
        Customer customerRow;
        Vector<Customer> customers = new Vector();
        
        connect();
        try {
            sqlcommand = "SELECT * FROM retrieveAllUsers()";
            preparedstatement = connection.prepareStatement(sqlcommand);
            result = preparedstatement.executeQuery();

            while(result.next()){
                customerRow=new Customer(result.getInt(1),result.getString(2),result.getString(3),
                        result.getInt(4),result.getString(5),result.getString(6),
                        result.getString(7),result.getString(8),result.getString(9));
                
                customers.add(customerRow);    
            }
            System.out.println("ALL USERS List Retrieved");
        } catch (SQLException ex) {
            System.out.println("Sorry Something wrong happened, retriving users list failed");
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
              stop();
              return customers;
        } 
    }
    
    public Boolean deleteCustomer(Customer customer){
        
        connect(); 
        try {
            sqlcommand = "SELECT removeUser(?)";
            preparedstatement = connection.prepareStatement(sqlcommand);
            preparedstatement.setInt(1,customer.getUID());
            result = preparedstatement.executeQuery();
            
            while(result.next())
            {
                state = result.getBoolean(1);
            }
            
            System.out.println("Customer has been removed");
            return state;
        } catch (SQLException ex) {
            System.out.println("Something wrong happpened while removing customer");
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        finally{
            stop();
        }  
    } 
  
    public int getUserId(String _email){
        connect();
        int _id=0;
        try {
            sqlcommand = "SELECT  user_id from users where email=?";
             
            
            preparedstatement = connection.prepareStatement(sqlcommand,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            preparedstatement.setString(1,_email);

            result = preparedstatement.executeQuery();
            result.first();

            _id=result.getInt(1);
           
            
            System.out.println("user data has been retrived Successfully" + _id);
           // System.out.println("Product has been deleted successfully");
        } catch (SQLException ex) {
            System.out.println("Something wrong happened");
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        stop(); 
        return _id;        
    } 
    public int getUserBalance(int _id){
        connect();
        int _balance=0;
        try {
            sqlcommand = "SELECT  balance from users where user_id=?";
             
            
            preparedstatement = connection.prepareStatement(sqlcommand,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            preparedstatement.setInt(1,_id);

            result = preparedstatement.executeQuery();
            result.first();

            _balance=result.getInt(1);
           
            
            System.out.println("user data has been retrived Successfully" + _balance);
           // System.out.println("Product has been deleted successfully");
        } catch (SQLException ex) {
            System.out.println("Something wrong happened");
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        stop(); 
        return _balance;        
    }      
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public Customer selectUser(int _id){
        connect();
       
        try {
            sqlcommand = "SELECT   user_name, password, balance, job, email, address, birth_date, interests from users where user_id=?";
             
            
            preparedstatement = connection.prepareStatement(sqlcommand,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            preparedstatement.setInt(1,_id);

            result = preparedstatement.executeQuery();
            result.first();
            customer.setUName(result.getString(1));
            customer.setUPasswd(result.getString(2));
            customer.setUBalance(result.getInt(3));
            customer.setUJob(result.getString(4));
            customer.setUEmail(result.getString(5));
            customer.setUAddress(result.getString(6));
            customer.setUBirthdate(result.getString(7));
            customer.setUInterest(result.getString(8));            
            
            //System.out.println("user data has been retrived Successfully" + customer.getUName());
           // System.out.println("Product has been deleted successfully");
        } catch (SQLException ex) {
            System.out.println("Something wrong happened");
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        stop(); 
        return customer;        
    }




    
    public void addCategory(){
        connect();
        try {
            sqlcommand = "SELECT addNewCategory(?,?)";
            preparedstatement = connection.prepareStatement(sqlcommand);
            preparedstatement.setString(1, "category1");
            preparedstatement.setString(2, "icon1");
            result = preparedstatement.executeQuery();
            System.out.println("Category added Successfully");

        } catch (SQLException ex) {
            System.out.println("Something wrong happened");
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        stop();
    }
    
    public void removeCategory(){
        connect();
        
        try {
            sqlcommand = "SELECT removeCategory(?)";
            preparedstatement = connection.prepareStatement(sqlcommand);
            preparedstatement.setInt(1,4);
            result = preparedstatement.executeQuery();
            System.out.println("Category has been deleted");
        } catch (SQLException ex) {
            System.out.println("Something wrong happened");
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        stop();
    }
    
    public void addProduct(){
        connect();
       
        try {
            sqlcommand = "SELECT addNewproduct(?,?,?,?,?,?,?)";
            preparedstatement = connection.prepareStatement(sqlcommand);
            preparedstatement.setString(1,product.getProductName());
            preparedstatement.setInt(2,product.getProductQuantity());
            preparedstatement.setInt(3,product.getProductPrice());
            preparedstatement.setString(4,product.getProductShortDesc());
            preparedstatement.setString(5,product.getProductFullDesc());
            preparedstatement.setInt(6,product.getCategoryId());
            preparedstatement.setString(7,product.getProductURL());
            result = preparedstatement.executeQuery();
            System.out.println("Product added Successfully" + result);
        } catch (SQLException ex) {
            System.out.println("Something wrong happened");
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
        stop();
    }
    
    public void removeProduct(){
        connect();
       
        try {
            sqlcommand = "SELECT removeProduct(?)";
            preparedstatement = connection.prepareStatement(sqlcommand);
            preparedstatement.setInt(1, 3);
            result = preparedstatement.executeQuery();
            System.out.println("Product has been deleted successfully");
        } catch (SQLException ex) {
            System.out.println("Something wrong happened");
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        stop();
    }
    
    public void editProduct(Product a){
        connect();
       
        try {
            sqlcommand = "UPDATE products SET product_name=?, product_quantity=?, price=?, short_des=?, full_des=?, category_id=?, product_url=? WHERE product_id=?";
            preparedstatement = connection.prepareStatement(sqlcommand);
            preparedstatement.setString(1,a.getProductName());
            preparedstatement.setInt(2,a.getProductQuantity());
            preparedstatement.setInt(3,a.getProductPrice());
            preparedstatement.setString(4,a.getProductShortDesc());
            preparedstatement.setString(5,a.getProductFullDesc());
            preparedstatement.setInt(6,a.getCategoryId());
            preparedstatement.setString(7,a.getProductURL());
            preparedstatement.setInt(8,a.getProductId());
            result = preparedstatement.executeQuery();
            System.out.println("Product Edited Successfully" + result);
            System.out.println("Product has been deleted successfully");
        } catch (SQLException ex) {
            System.out.println("Something wrong happened");
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        stop();        
        
        
    }
    public Product selectProduct(int _id){
        connect();
       
        try {
            sqlcommand = "SELECT  product_name, product_quantity, price, short_des, full_des, category_id, product_url from products where product_id=?";
             
            
            preparedstatement = connection.prepareStatement(sqlcommand,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            preparedstatement.setInt(1,_id);

            result = preparedstatement.executeQuery();
            result.first();
            product.setProductName(result.getString(1));
            product.setProductQuantity(result.getInt(2));
            product.setProductPrice(result.getInt(3));
            product.setProductShortDesc(result.getString(4));
            product.setProductFullDesc(result.getString(5));
            product.setCategoryId(result.getInt(6));
            product.setProductURL(result.getString(7));
            
            System.out.println("Product Edited Successfully" + product.getProductName());
           // System.out.println("Product has been deleted successfully");
        } catch (SQLException ex) {
            System.out.println("Something wrong happened");
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        stop(); 
        return product;
        
        
    }    
    public Product getProductInfo(int id){
        connect();
        try {
            sqlcommand = "SELECT * FROM getProductInfo(?)";
            preparedstatement = connection.prepareStatement(sqlcommand);
            preparedstatement.setInt(1, id);
            result = preparedstatement.executeQuery();
            Product retrivedProduct = new Product();
            
            while(result.next()){
                retrivedProduct.setProductId(result.getInt(1));
                retrivedProduct.setProductName(result.getString(2));
                retrivedProduct.setProductQuantity(result.getInt(3));
                retrivedProduct.setProductPrice(result.getInt(4));
                retrivedProduct.setProductShortDesc(result.getString(5));
                retrivedProduct.setProductFullDesc(result.getString(6));
                retrivedProduct.setCategoryId(result.getInt(7));
                retrivedProduct.setProductURL(result.getString(8));
            }
            
            return retrivedProduct;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        finally{
            stop();
        } 
        
    }
    public Boolean removeProduct(int id){
        connect();
       
        try {
            sqlcommand = "SELECT removeProduct(?)";
            preparedstatement = connection.prepareStatement(sqlcommand);
            preparedstatement.setInt(1, id);
            result = preparedstatement.executeQuery();
            System.out.println("Product has been deleted successfully");
            return true;
        } catch (SQLException ex) {
            System.out.println("Something wrong happened");
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        finally{
             stop();
        }
    }
        
    public Vector<Product> getAllProducts(){
        
        Product productRow;
        Vector<Product> products =new Vector(); 
        connect();
        
        try {
            sqlcommand = "SELECT * FROM retrieveallproducts()";
            preparedstatement = connection.prepareStatement(sqlcommand);
            result = preparedstatement.executeQuery();
            while(result.next()){
                productRow=new Product(result.getInt(1),result.getString(2),result.getInt(3),
                        result.getInt(4),result.getString(5),result.getString(6),
                        result.getInt(7),result.getString(8));
                
                products.add(productRow);
            }
                    
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        stop();
        return products;   
    }
    
    
//        public Vector<Product> getCartItems(){
//        
//        Product productRow;
//        Vector<Product> products =new Vector(); 
//        connect();
//        
//        try {
//            sqlcommand = "SELECT * FROM retrieveallproducts()";
//            preparedstatement = connection.prepareStatement(sqlcommand);
//            result = preparedstatement.executeQuery();
//            while(result.next()){
//                productRow=new Product(result.getInt(1),result.getString(2),result.getInt(3),
//                        result.getInt(4),result.getString(5),result.getString(6),
//                        result.getInt(7),result.getString(8));
//                
//                products.add(productRow);
//            }
//                    
//        } catch (SQLException ex) {
//            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        stop();
//        return products;   
//    }
//    
    
    
    
    
    
    
    
    
    
    
    
    private void stop(){
        
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static void main(String[] args){
        DatabaseConnection db = new DatabaseConnection();
       db.getUserBalance(2);
    }
    
    
    
}
