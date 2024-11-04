/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AVL;

/**
 *
 * @author David
 */
public class Node {
    private int key;
    
    private Node parent = null;
    private Node left_child = null;
    private Node right_child = null;
    
    public boolean isLeaf(){
        return (left_child == null) && (right_child == null) ? true : false;
    }
    
    public boolean isRoot(){
        return parent == null ? true : false;
    }

    public int getClave() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node patern) {
        this.parent = patern;
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
    
    public static final class NodeBuilder{
        private Node node;
        
        public NodeBuilder(){
            this.node = new Node();
        }
        
        public NodeBuilder set_left_child(Node node){
            this.node.setLeft_child(node);
            return this;
        }
        
        public NodeBuilder set_right_child(Node node){
            this.node.setRight_child(node);
            return this;
        }
        
        public NodeBuilder set_parent(Node node){
            this.node.setParent(node);
            return this;
        }
        
        public NodeBuilder set_key(int key){
            this.node.setKey(key);
            return this;
        }
        
        public Node build(){
            Node nodeAux = this.node;
            this.node = new Node();
            return nodeAux;
        }
    }
}
