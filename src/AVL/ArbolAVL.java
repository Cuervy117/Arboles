/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AVL;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 *
 * @author David
 */
public class ArbolAVL implements Serializable{
    private Node root;
    
    public ArbolAVL(){
    }
    
    public ArbolAVL(Node root){
        this.root = root;
    }
    
    public Node getRoot(){
        return root;
    }
    
    public void add(Node node){
        Node rootAux = root;
        if(root == null){
            this.root = node;
            return;
        }
        while(true){
            if(rootAux.getClave() == node.getClave()) break;
            else if(node.getClave() > rootAux.getClave()){
                if(rootAux.getRight_child() != null){
                    rootAux = rootAux.getRight_child();
                }else{
                    node.setParent(rootAux);
                    rootAux.setRight_child(node);
                    break;
                }
            }else{
                if(rootAux.getLeft_child() != null){
                    rootAux = rootAux.getLeft_child();
                }else{
                    node.setParent(rootAux);
                    rootAux.setLeft_child(node);
                    break;
                }                    
            }
        }
        this.restructuring();
    }
    
    public static void preOrder(Node node){
        if(node == null){
            return;
        }
        System.out.println(node.getClave());
        preOrder(node.getLeft_child());
        preOrder(node.getRight_child());
    }
    
    public void simple_right_rotation(Node node){
        if(node == null) return;
        Node child = node.getLeft_child();
        if(child == null)return;
        
        Node parent = node.getParent();
        
        node.setLeft_child(child.getRight_child());
        if(child.getRight_child() != null) child.getRight_child().setParent(node);
        
        child.setParent(parent);
        if(parent == null)root = child;
        else if(node == parent.getLeft_child()) parent.setLeft_child(child);
        else parent.setRight_child(child);
        
        child.setRight_child(node);
        node.setParent(child);
    }
    
    public void simple_left_rotation(Node node){
        if(node == null) return;
        Node child = node.getRight_child();
        if(child == null) return;
        
        Node parent = node.getParent();
        
        node.setRight_child(child.getLeft_child());
        if(child.getLeft_child() != null) child.getLeft_child().setParent(node);
        
        child.setParent(parent);
        if(parent == null)root = child;
        else if(node == parent.getLeft_child()) parent.setLeft_child(child);
        else parent.setRight_child(child);
        
        child.setLeft_child(node);
        node.setParent(child);
    }
    
    public void double_right_rotation(Node node){
        this.simple_left_rotation(node.getLeft_child());
        this.simple_right_rotation(node);
    }
    
    public void double_left_rotation(Node node){
        this.simple_right_rotation(node.getRight_child());
        this.simple_left_rotation(node);
    }
    
    public static Node balanced(Node node){
        if(node == null) return null;
        int actualWeight = node.getWeight();
        if(actualWeight > 1 || actualWeight < -1) return node;
        else{ 
            Node left_child = balanced(node.getLeft_child());
            if(left_child != null) return left_child;
            Node right_child = balanced(node.getRight_child());
            if( right_child != null) return right_child;
            return null; 
        }
    }

    public void restructuring(){
        Node unbalanced;
        do {
            unbalanced = balanced(this.getRoot()); 
            if(unbalanced != null){
                if(unbalanced.getWeight() < -1){
                    if(unbalanced.getLeft_child().getWeight() < 0){
                        this.simple_right_rotation(unbalanced);
                    }else{
                        this.double_right_rotation(unbalanced); 
                    }
                }else if(unbalanced.getWeight() > 1){
                    if(unbalanced.getRight_child().getWeight() > 0){
                        this.simple_left_rotation(unbalanced);
                    }else{
                        this.double_left_rotation(unbalanced);
                    }
                }
            }
        } while (unbalanced != null);
    }

    public void guardarArbol(String filePath) {
        try (FileOutputStream fileOut = new FileOutputStream(filePath);
                ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(this);
            System.out.println("Árbol guardado exitosamente en " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
