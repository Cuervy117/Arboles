/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AVL;
import java.math.*;
/**
 *
 * @author David
 */
public class Node {
    
    private int key;
    private Node left_child;
    private Node right_child; 
    private Node parent;
    private int weight;
    
    public Node(){
        this.key = 0;
        this.weight = 0;
        this.left_child = null;
        this.right_child = null;
    }
    
    public Node(int key){
        this.key = key;
        this.weight = 0;
        this.left_child = null;
        this.right_child = null;
    }
    
    public boolean isLeaf(){
        return (left_child == null) && (right_child == null) ? true : false;
    }
    
    public int getClave() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public Node getLeft_child() {
        return left_child;
    }

    public void setLeft_child(Node left_child) {
        this.left_child = left_child;
    }

    public Node getRight_child() {
        return right_child;
    }

    public void setRight_child(Node right_child) {
        this.right_child = right_child;
    }
    
    public Node getParent(){
        return parent;
    }
    
    public void setParent(Node parent){
        this.parent = parent;
    }
    
    public int getHeight(){
        if(this.isLeaf())return 0;
        else{
            return 1 + Math.max(right_child != null ? right_child.getHeight() : -1,
                    left_child != null ? left_child.getHeight() : -1);
        }
    }
    
    public int getWeight(){
        return (right_child != null ? right_child.getHeight() : 0) - (left_child != null ? left_child.getHeight() : 0);
    }
}
