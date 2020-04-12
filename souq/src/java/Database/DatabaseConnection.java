package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import ModelObjects.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Vector;


public class DatabaseConnection {
    
    private final String url = "jdbc:postgresql://localhost:5432/souq";
    private final String user = "postgres";
    private final String password = "mohamed";
    
    private Connection connection;
    private String sqlcommand;
    private PreparedStatement preparedstatement;
    private ResultSet result;
    Boolean state =false;
    String privilage;
    Product product=new Product();

    
    public void connect(){        
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
            preparedstatement.setBigDecimal(3,customer.getUBalance());
            preparedstatement.setString(4,customer.getUJob());
            preparedstatement.setString(5,customer.getUEmail());
            preparedstatement.setString(6, customer.getUAddress());
            preparedstatement.setDate(7,customer.getUBirthdate());
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
                        result.getBigDecimal(4),result.getString(5),result.getString(6),
                        result.getString(7),result.getDate(8),result.getString(9));
                
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
    
    /////////////////////////////////////////////////////////////////////////////////////////
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
    
    ////////////////////////////////////////////////////////////////////////////////////////
    
    ///////////////////-------------Category--------------/////////////////////////////////
    public Boolean addCategory(Category category){
        connect();
        try {
            sqlcommand = "SELECT addNewCategory(?,?)";
            preparedstatement = connection.prepareStatement(sqlcommand);
            preparedstatement.setString(1,category.getCategoryName());
            preparedstatement.setString(2,category.getCategoryIcon());
            result = preparedstatement.executeQuery();
            
            while(result.next()){
                state=result.getBoolean(1);
            }
            
            System.out.println("Category added Successfully");
            return state;

        } catch (SQLException ex) {
            System.out.println("Something wrong happened");
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        finally{
            stop();
        }
    }
    
    public Boolean removeCategory(Category category){
        connect();
        
        try {
            sqlcommand = "SELECT removeCategory(?)";
            preparedstatement = connection.prepareStatement(sqlcommand);
            preparedstatement.setInt(1,category.getCategoryID());
            result = preparedstatement.executeQuery();
            
            while(result.next()){
                state=result.getBoolean(1);
            }
            
            System.out.println("Category has been deleted");
            return state;
        } catch (SQLException ex) {
            System.out.println("Something wrong happened");
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        finally{
              stop();
        }
        
      
    }
    ///////////////////////////////////////////////////////////////////////////////////////
    
    //////////////////--------------Products--------------///////////////////////////////////
    public Boolean addProduct(Product product){
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
//            System.out.println(product.getProductURL());
            result = preparedstatement.executeQuery();
            
            while(result.next()){
                state=result.getBoolean(1);
            }
            
            System.out.println("Product added Successfully");
            return state;
        } catch (SQLException ex) {
            System.out.println("Something wrong happened while adding product");
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        finally{
            stop();
        }    
    }
    
    public Boolean removeProduct(Product product){
        connect();
       
        try {
            sqlcommand = "SELECT removeProduct(?)";
            preparedstatement = connection.prepareStatement(sqlcommand);
            preparedstatement.setInt(1, product.getProductID());
            result = preparedstatement.executeQuery();
            
            while(result.next()){
                state = result.getBoolean(1);
            }
            
            System.out.println("Product has been deleted successfully");
            return state;
        } catch (SQLException ex) {
            System.out.println("Something wrong happened");
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        finally{
             stop();
        }
    }
    
    public Product getProductInfo(Product product){
        connect();
        try {
            sqlcommand = "SELECT * FROM getProductInfo(?)";
            preparedstatement = connection.prepareStatement(sqlcommand);
            preparedstatement.setInt(1,product.getProductID());
            result = preparedstatement.executeQuery();
            Product retrivedProduct = new Product();
            
            while(result.next()){
                retrivedProduct.setProductID(result.getInt(1));
                retrivedProduct.setProductName(result.getString(2));
                retrivedProduct.setProductQuantity(result.getInt(3));
                retrivedProduct.setProductPrice(result.getInt(4));
                retrivedProduct.setProductShortDesc(result.getString(5));
                retrivedProduct.setProductFullDesc(result.getString(6));
                retrivedProduct.setCategoryId(result.getInt(7));
                retrivedProduct.setProductURL(result.getString(8));
            }
            System.out.println("Product Info has been retrieved successfully");
            return retrivedProduct;
        } catch (SQLException ex) {
            System.out.println("Something wrong happened while retrieving product info");
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        finally{
            stop();
        } 
        
    }
    
    public Boolean editProduct(Product product){
        connect();
       
        try {
            sqlcommand = "SELECT editProduct(?,?,?,?,?,?,?,?)";
            preparedstatement = connection.prepareStatement(sqlcommand);
             preparedstatement.setInt(1,product.getProductID());
            preparedstatement.setString(2,product.getProductName());
            preparedstatement.setInt(3,product.getProductQuantity());
            preparedstatement.setInt(4,product.getProductPrice());
            preparedstatement.setString(5,product.getProductShortDesc());
            preparedstatement.setString(6,product.getProductFullDesc());
            preparedstatement.setInt(7,product.getCategoryId());
            preparedstatement.setString(8,product.getProductURL());
            result = preparedstatement.executeQuery();
            
            while(result.next()){
                state=result.getBoolean(1);
            }
            
            System.out.println("Product Edited Successfully");
            return state;
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
            
            System.out.println("All Products have been retrieved successfully");
                    
        } catch (SQLException ex) {
            System.out.println("Something wrong happened while retrieving products info");
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            stop();
            return products;  
        }
       
    }
    /////////////////////////////////////////////////////////////////////////////////////////
    private void stop(){
        try {
            connection.close();
            System.out.println("Database Closed");
        } catch (SQLException ex) {
            System.out.println("databaseconnection.database.stop()error");
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
      
    public static void main(String[] args){
        DatabaseConnection db = new DatabaseConnection(); 
        
//        Customer c1 = new Customer("sandy","ssasa",new BigDecimal("30.6778"),"Enr","sandyyy93@gmail.com","38swes",Date.valueOf("1997-01-01"),"ayhabel");
//        Boolean state=db.registerCheck(c1);
//        System.out.println(state);


//        Customer customer= new Customer("may93@gmail.com","345678");
//        String priv = db.loginCheck(customer);
//        System.out.println(priv);

//        Vector<Customer> vector=db.getAllCustomers();
//        for(Customer x:vector){
//            System.out.println(x.getUEmail() +"######"+x.getUName() +"########"+
//                    x.getUPasswd());
//        }

//        Boolean s=db.deleteCustomer(new Customer(5));
//        System.out.println(s+"#################");


//           Category cat1= new Category("headphones","icon1111");
//           db.addCategory(cat1);
           
//           Boolean s = db.removeCategory(new Category(4));
//           System.out.println(s);

//        Product product = new Product("youssef", 43, 4033333, "youssefProdshortdesc", "youssefProdFullDesc", 2, "YoussefProdurl");
//        Boolean res = db.addProduct(product);
//        System.out.println(res);


//          Boolean res = db.removeProduct(new Product(6));
//          System.out.println(res);


//        Product prod1 = db.getProductInfo(new Product(8));
//        System.out.println("##################"+prod1.getProductID() + prod1.getProductName()+ "############");
          

//        Vector<Product> vector=db.getAllProducts();
//        for(Product x:vector){
//            System.out.println("##############"+x.getProductName() +"##############"+ x.getProductQuantity()+ 
//                    "##############"+x.getProductPrice());
//        }
    

//        Boolean s= db.editProduct(new Product(8,"youssefProd", 1, 4440000, "youssefProdshortdesc","youssefProdFullDesc", 2,"YoussefProdurl"));
//        System.out.println("#########################"+ s+ "#################################");


    }
    
    
    
}
