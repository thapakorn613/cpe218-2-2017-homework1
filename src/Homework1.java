import javax.swing.*;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeSelectionModel;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import java.awt.*;
import java.net.URL;
import java.io.IOException;
import java.util.Stack;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.UIManager;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import java.net.URL;
import java.io.IOException;
import java.awt.Dimension;
import java.awt.GridLayout;

public class Homework1 extends JPanel
        implements TreeSelectionListener {
    private JEditorPane htmlPane;
    private JTree tree;
    private URL helpURL;
    private static boolean DEBUG = false;
    static Node  root;
    static Stack<Character> keep = new Stack<Character>();
    //Optionally play with line styles.  Possible values are
    //"Angled" (the default), "Horizontal", and "None".
    private static boolean playWithLineStyle = false;
    private static String lineStyle = "Horizontal";

    //Optionally set the look and feel.
    private static boolean useSystemLookAndFeel = false;
    public static Node rootKeep = root;
    public Homework1() {
        super(new GridLayout(1,0));

        //Create the nodes.
        DefaultMutableTreeNode top = new DefaultMutableTreeNode(root.data);
        createNodes(top,root);

        //Create a tree that allows one selection at a time.
        tree = new JTree(top);
        tree.getSelectionModel().setSelectionMode
                (TreeSelectionModel.SINGLE_TREE_SELECTION);

        //Listen for when the selection changes.
        tree.addTreeSelectionListener(this);

        if (playWithLineStyle) {
            System.out.println("line style = " + lineStyle);
            tree.putClientProperty("JTree.lineStyle", lineStyle);
        }

        //Create the scroll pane and add the tree to it.
        JScrollPane treeView = new JScrollPane(tree);

        //Create the HTML viewing pane.
        htmlPane = new JEditorPane();
        htmlPane.setEditable(false);
        initHelp();
        JScrollPane htmlView = new JScrollPane(htmlPane);

        //Add the scroll panes to a split pane.
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.setTopComponent(treeView);
        splitPane.setBottomComponent(htmlView);

        Dimension minimumSize = new Dimension(100, 50);
        htmlView.setMinimumSize(minimumSize);
        treeView.setMinimumSize(minimumSize);
        splitPane.setDividerLocation(100);
        splitPane.setPreferredSize(new Dimension(500, 300));

        //Add the split pane to this panel.
        add(splitPane);
    }

    /** Required by TreeSelectionListener interface. */
    public void valueChanged(TreeSelectionEvent e) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)
                tree.getLastSelectedPathComponent();

        if (node == null) return;

        Object nodeInfo = node.getUserObject();
        if (node.isLeaf()) {
            BookInfo book = (BookInfo)nodeInfo;
            displayURL(book.bookURL);
            if (DEBUG) {
                System.out.print(book.bookURL + ":  \n    ");
            }
        } else {
            displayURL(helpURL);
        }
        if (DEBUG) {
            System.out.println(nodeInfo.toString());
        }
    }

    private class BookInfo {
        public String bookName;
        public URL bookURL;

        public BookInfo(String book, String filename) {
            bookName = book;
            bookURL = getClass().getResource(filename);
            if (bookURL == null) {
                System.err.println("Couldn't find file: "
                        + filename);
            }
        }

        public String toString() {
            return bookName;
        }
    }

    private void initHelp() {
        String s = "TreeDemoHelp.html";
        helpURL = getClass().getResource(s);
        if (helpURL == null) {
            System.err.println("Couldn't open help file: " + s);
        } else if (DEBUG) {
            System.out.println("Help URL is " + helpURL);
        }

        displayURL(helpURL);
    }

    private void displayURL(URL url) {
        try {
            if (url != null) {
                htmlPane.setPage(url);
            } else { //null url
                htmlPane.setText("File Not Found");
                if (DEBUG) {
                    System.out.println("Attempted to display a null URL.");
                }
            }
        } catch (IOException e) {
            System.err.println("Attempted to read a bad URL: " + url);
        }
    }

    private void createNodes(DefaultMutableTreeNode top,Node rootKeepInCreate) {
        if(isOperand(rootKeepInCreate.data)){
            DefaultMutableTreeNode left = new DefaultMutableTreeNode( rootKeepInCreate.left.data);
            DefaultMutableTreeNode right = new DefaultMutableTreeNode(rootKeepInCreate.right.data);
            top.add(left);
            createNodes(left,rootKeepInCreate.left);
            top.add(right);
            createNodes(right,rootKeepInCreate.right);
        }
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    private static void createAndShowGUI() {
        if (useSystemLookAndFeel) {
            try {
                UIManager.setLookAndFeel(
                        UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                System.err.println("Couldn't use system look and feel.");
            }
        }

        //Create and set up the window.
        JFrame frame = new JFrame("TreeDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add content to the window.
        frame.add(new Homework1());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
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
            return false ; // awdfev
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
    public static void main(String[] args) {
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
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

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
