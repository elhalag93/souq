package ModelObjects;

public class Category {
    
    int categoryID;
    String categoryName;
    String categoryIcon;
    
    public Category(){
        
    }
    
    public Category(int _categoryID){
        this.categoryID = _categoryID; 
    }
    
    public Category(String _categoryName,String _categoryIcon){
        this.categoryName = _categoryName;
        this.categoryIcon = _categoryIcon;
    }
    
    public void setCategoryID(int _categoryID){
        this.categoryID = _categoryID;
    }
    
    public void setCategoryName(String _categoryName){
        this.categoryName = _categoryName;
    }
    
    public void setCategoryIcon(String _categoryIcon){
        this.categoryIcon = _categoryIcon;
    }
    
    public int getCategoryID(){
        return this.categoryID;
    }
    
    public String getCategoryName(){
        return categoryName;
    }
    
    public String getCategoryIcon(){
        return categoryIcon;
    }
    
    
}
