/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw1;

/**
 *
 * @author ArtRonin
 */
public class infix {
    static Node n;
   
    public static void infix(){
        infix(n);
    }
    public static void infix(Node n){
        if(n != null){
            if(n.left != null && n.right != null  ){
                System.out.println("(");
            }
            infix(n.left);
            System.out.println(n.data);
            infix(n.right);
            if(n.left != null && n.right != null){
                System.out.println("(");
            }
        }
    }
}
