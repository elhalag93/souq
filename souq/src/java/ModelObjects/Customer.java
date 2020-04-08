package ModelObjects;

import java.math.BigDecimal;
import java.sql.Date;

public class Customer {
    
    int uID;
    String uName,upasswd;
    String uJob,uEmail,uAddress,interest;
    Date birthDate;
    BigDecimal uBalance;
   
   public Customer(String _uName,String _uPasswd,BigDecimal _uBalance,String _uJob,String _uEmail,String _uAddress,
   Date _birthDate,String _interest){
        this.uName = _uName;
        this.upasswd = _uPasswd;
        this.uBalance = _uBalance;
        this.uJob = _uJob;
        this.uEmail = _uEmail;
        this.uAddress = _uAddress;
        this.birthDate = _birthDate;
        this.interest = _interest;     
   }
   
   
   public Customer(String _uEmail,String _uPasswd){
       this.uEmail = _uEmail;
       this.upasswd = _uPasswd;
   }
   
   
   public Customer(int _uID){
       this.uID = _uID;
   }
   
   public Customer(int _uID,String _uName,String _uPasswd,BigDecimal _uBalance,String _uJob,String _uEmail,String _uAddress,
   Date _birthDate,String _interest){
        this.uID = _uID;
        this.uName = _uName;
        this.upasswd = _uPasswd;
        this.uBalance = _uBalance;
        this.uJob = _uJob;
        this.uEmail = _uEmail;
        this.uAddress = _uAddress;
        this.birthDate = _birthDate;
        this.interest = _interest;
       
   }
   
   
   
   public void setUName(String _uName){
       this.uName = _uName;
   }
   
   public void setUPasswd(String _uPasswd){
       this.upasswd = _uPasswd;
   }

   public void setUBalance(BigDecimal _uBalance){
       this.uBalance = _uBalance;
   }

   public void setUJob(String _uJob){
       this.uJob = _uJob;
   }

   public void setUEmail(String _uEmail){
       this.uEmail = _uEmail;
   }

   public void setUAddress(String _uAddress){
       this.uAddress = _uAddress;
   }

   public void setUBirthdate(Date _birthDate){
       this.birthDate = _birthDate;
   }

   public void setUInterest(String _interest){
       this.interest = _interest;
   }

   public void setUID(int _uID){
       this.uID = _uID;
   }
   
   
   
   public String getUName(){
       return this.uName;
   }
   
   public String getUPasswd(){
       return this.upasswd;
   }

   public BigDecimal getUBalance(){
       return this.uBalance;
   }

   public String getUJob(){
       return this.uJob;
   }

   public String getUEmail(){
       return this.uEmail;
   }

   public String getUAddress(){
       return this.uAddress;
   }

   public Date getUBirthdate(){
       return this.birthDate;
   }

   public String getUInterest(){
       return this.interest;
   }
   
   public int getUID(){
       return this.uID;
   }
    
}
