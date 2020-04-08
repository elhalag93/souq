package ModelObjects;
import ModelObjects.Category;

public class Product {
    
    int productId;
    String productName;
    String productShortDesc,productFullDesc;
    String productURL;
    int productQuantity,productPrice;
    int categoryId;
    
    public Product(){
        
    }
    
    public Product(int _productID){
        this.productId = _productID;
    }
    
    public Product(String _productName,int _productQuantity,int _productPrice,
            String _productShortDesc,String _productFullDesc,int _categoryId,String _productURL){
        
        this.productName = _productName;
        this.productQuantity = _productQuantity;
        this.productPrice = _productPrice;
        this.productShortDesc = _productShortDesc;
        this.productFullDesc = _productFullDesc;
        this.categoryId = _categoryId;
        this.productURL = _productURL;
    }
    
    public Product(int _productId,String _productName,int _productQuantity,int _productPrice,
            String _productShortDesc,String _productFullDesc,int _categoryId,String _productURL){
        
        this.productId = _productId;
        this.productName = _productName;
        this.productQuantity = _productQuantity;
        this.productPrice = _productPrice;
        this.productShortDesc = _productShortDesc;
        this.productFullDesc = _productFullDesc;
        this.categoryId = _categoryId;
        this.productURL = _productURL;
    }
    
    public void setProductID(int _productID){
        this.productId = _productID;
    }
    public void setProductName(String _productName){
        this.productName = _productName;
    }
    public void setProductQuantity(int _productQuantity){
        this.productQuantity = _productQuantity;
    }
    public void setProductPrice(int _productPrice){
        this.productPrice = _productPrice;
    }
    public void setProductShortDesc(String _productShortDesc){
        this.productShortDesc = _productShortDesc;
    }
    public void setProductFullDesc(String _productFullDesc){
        this.productFullDesc = _productFullDesc;
    }
    public void setCategoryId(int _categoryId){
        this.categoryId = _categoryId;
    }
    public void setProductURL(String _productURL){
        this.productURL = _productURL;
    }

    
    public int getProductID(){
        return productId;
    }
    public String getProductName(){
        return productName;
    }
    public int getProductQuantity(){
        return productQuantity;
    }
    public int getProductPrice(){
        return productPrice;
    }
    public String getProductShortDesc(){
        return productShortDesc;
    }
    public String getProductFullDesc(){
        return productFullDesc;
    }
    public int getCategoryId(){
        return categoryId;
    }
    public String getProductURL(){
        return productURL;
    }
    
}
