/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package homework1;

/**
 *
 * @author ArtRonin
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author ArtRonin
 */
public class homework1 {
    static Node  root;
    static Stack<Character> keep = new Stack<Character>();
    public static void main(String[] args) {
        String str = args[0];
        char [] list;
        list = str.toCharArray();
        for(char obj : list){
            keep.push(obj);
        }
        root = new Node(keep.pop());
        inorder(root);
        infix(root);
        int sum = calculator(root);
        System.out.println(" : "+sum);
    }
    public static void inorder(Node n){
        if(isOperand(n.data)){
            n.right = new Node(keep.pop()); // New Right
            inorder(n.right);
            n.left = new Node(keep.pop()); // New Left
            inorder(n.left);
        }
    }
    public static void infix(Node n){
        if(n != null){
            if(n.left != null && n.right != null ){
                System.out.print("(");
            }
            infix(n.left);
            System.out.print(n.data);
            infix(n.right);
            if(n.left != null && n.right != null ){
                System.out.print(")");
            }
        }
    }
    public static int calculator(Node n){
        if(n.data == '+'){  return calculator(n.left) + calculator(n.right);}
        if(n.data == '-'){  return calculator(n.left) - calculator(n.right);}
        if(n.data == '*'){  return calculator(n.left) * calculator(n.right);}
        if(n.data == '/'){  return calculator(n.left) / calculator(n.right);}
        else { return Character.getNumericValue(n.data); }
    }
    public static boolean isOperand(char target){
        if(target == '+'){
            return true;
        }
        else if(target == '-'){
            return true;
        }
        else if(target == '*'){
            return true;
        }
        else if(target == '/'){
            return true;
        }
        else {
            return false ;
        }
    }
    public static class Node{
        Node left;
        Node right;
        Node parent;
        char data;
        public Node(){

        }
        public Node(char data){
            this.data = data;
        }
    }
}
