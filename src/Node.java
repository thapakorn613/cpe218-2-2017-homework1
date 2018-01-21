/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw1;

public class Node{
    Node left;
    Node right;
    Node parent;
    char data;
    char Operand;
    
    public Node(){
        
    }
    public Node(char data){
        this.data = data;
    }
    public static int height(Node node){
        if (node == null)
            return -1;
        else
            return 1 + Math.max(height(node.left), height(node.right));
    }
    
}


