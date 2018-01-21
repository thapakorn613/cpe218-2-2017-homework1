
package hw1;
import java.util.Stack;
import java.util.ArrayList;
public class Homework1 {

	public static Node rootNode = new Node();
    public static Node rootKeep = new Node();
    public static void main(String[] args) {
        String str = "";
        str = args[0];
        char [] list;
        ArrayList<Node> newNode = new ArrayList<Node>();
        list = str.toCharArray();
        Node n =new Node() ;
        Stack<Character> keep = new Stack<Character>();
        for(char obj : list){
            keep.push(obj);
        }
        mergeNode(n,keep,rootNode,rootKeep);
        inorder.inorder(rootKeep);
        ///newNode.add(n);
        
        System.out.println("keep : "+keep);
        System.out.println("rootKeep.data : "+rootKeep.data);
        System.out.println("rootKeep.left : "+ rootKeep.left.data);
        System.out.println("rootKeep.right : "+ rootKeep.right.data);
        System.out.println("list : "+ list);
        System.out.println("newNode : "+newNode.size());
        System.out.println("Am not find.");
        
        
    }
    public static boolean isCheck = true;
    public static void mergeNode(Node n,Stack<Character> keep,Node root,Node node){
        addNode(n,keep);
            
    }
    public static void addNode(Node n,Stack<Character> keep){
            char keep1;
            keep1 = keep.pop();
            if(isOperand(keep1) && isCheck){// First Step
               Node root = new Node();
               root.data = keep1;
               rootKeep = root ;
               addNode(rootKeep,keep);
               isCheck = false ;
            }
            else if(isOperand(keep1)){ // Recursive Case
                Node root = new Node();
                root.data = keep1;
                n.data = root.data;
                addNode(n,keep);
            }
            else { // is Not Operand
                if(n.left == null){
                    Node root = new Node();
                    // เชื่อม root กับ Node Left
                    root.data = keep1;
                    n.left = root;
                    root.parent = n;
                    //
                    addNode(n,keep);
                }
                else if(n.right == null){
                    Node root = new Node();
                    // เชื่อม root กับ Node Right
                    root.data = keep1;
                    n.right = root;
                    root.parent = n;
                    //
                }
                else {
                    
                }
            }
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
}
