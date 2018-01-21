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
public class inorder {
     public static void inorder(Node n){
        if( n != null){
            inorder(n.left);
            System.out.println(n.data);
            inorder(n.right);
        }
        
    }
}
