/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sql;

import java.util.ArrayList;

/**
 *
 * @author M Gamal
 */
public class cart_object {

    ArrayList<Product> cart;

    public cart_object() {
        this.cart = new ArrayList<Product>();
    }

    public void add(Product e) {
        this.cart.add(e);
    }

   public void remove(Integer j) {
        for (int i = 0; i < cart.size(); i++) {
            if(cart.get(i).productId==j){
            cart.remove(i);
            }
            
        }
    }
  public  void increase(int j){
     for (int i = 0; i < cart.size(); i++) {
            if(cart.get(i).productId==j){
                 Product temp=cart.get(j);
                 temp.productQuantity++;
                 cart.set(j, temp);
            }
           
            }
    
    }
    
  public  void decreaes(Integer j){
     for (int i = 0; i < cart.size(); i++) {
            if(cart.get(i).productId==j){
                 Product temp=cart.get(j);
                 temp.productQuantity--;
                 cart.set(j, temp);
            }

            }
    
    
    }

   


}
