/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AVL;

import arbolBinario.*;

/**
 *
 * @author David
 */
public class ArbolAVL<T extends Comparable<T>> extends ArbolBin<T>{
    private static final long serialVersionUID = 3346824320569853901L;
    private NodeAVL<T> root;

    public ArbolAVL(){
    }
    
    public ArbolAVL(NodeAVL<T> root){
        this.root = root;
    }
    
    public NodeAVL<T> getRoot(){
        return root;
    }
    
    public void add(NodeAVL<T> node){
        NodeAVL<T> rootAux = root;
        if(root == null){
            this.root = node;
            return;
        }
        while(true){
            int comparacion = node.getClave().compareTo(rootAux.getClave());
            if(rootAux.getClave() == node.getClave()) break;
            else if(comparacion > 0){
                if(rootAux.getRightChild() != null){
                    rootAux = rootAux.getRightChild();
                }else{
                    node.setParent(rootAux);
                    rootAux.setRightChild(node);
                    break;
                }
            }else{
                if(rootAux.getLeftChild() != null){
                    rootAux = rootAux.getLeftChild();
                }else{
                    node.setParent(rootAux);
                    rootAux.setLeftChild(node);
                    break;
                }                    
            }
        }
        this.restructuring();
    }
    
    public void simpleRightRotation(NodeAVL<T> node){
        if(node == null) return;
        NodeAVL<T> child = node.getLeftChild();
        if(child == null)return;
        
        NodeAVL<T> parent = node.getParent();
        
        node.setLeftChild(child.getRightChild());
        if(child.getRightChild() != null) child.getRightChild().setParent(node);
        
        child.setParent(parent);
        if(parent == null)root = child;
        else if(node == parent.getLeftChild()) parent.setLeftChild(child);
        else parent.setRightChild(child);
        
        child.setRightChild(node);
        node.setParent(child);
    }
    
    public void simpleLeftRotation(NodeAVL<T> node){
        if(node == null) return;
        NodeAVL<T> child = node.getRightChild();
        if(child == null) return;
        
        NodeAVL<T> parent = node.getParent();
        
        node.setRightChild(child.getLeftChild());
        if(child.getLeftChild() != null) child.getLeftChild().setParent(node);
        
        child.setParent(parent);
        if(parent == null)root = child;
        else if(node == parent.getLeftChild()) parent.setLeftChild(child);
        else parent.setRightChild(child);
        
        child.setLeftChild(node);
        node.setParent(child);
    }
    
    public void doubleRightRotation(NodeAVL<T> node){
        this.simpleLeftRotation(node.getLeftChild());
        this.simpleRightRotation(node);
    }
    
    public void doubleLeftRotation(NodeAVL<T> node){
        this.simpleRightRotation(node.getRightChild());
        this.simpleLeftRotation(node);
    }
    
    public NodeAVL<T> balanced(NodeAVL<T> node){
        if(node == null) return null;
        int actualWeight = node.getWeight();
        if(actualWeight > 1 || actualWeight < -1) return node;
        else{ 
            NodeAVL<T> leftChild = balanced(node.getLeftChild());
            if(leftChild != null) return leftChild;
            NodeAVL<T> rightChild = balanced(node.getRightChild());
            if( rightChild != null) return rightChild;
            return null; 
        }
    }

    public void restructuring(){
        NodeAVL<T> unbalanced;
        do {
            unbalanced = balanced(this.getRoot()); 
            if(unbalanced != null){
                if(unbalanced.getWeight() < -1){
                    if(unbalanced.getLeftChild().getWeight() < 0){
                        this.simpleRightRotation(unbalanced);
                    }else{
                        this.doubleRightRotation(unbalanced); 
                    }
                }else if(unbalanced.getWeight() > 1){
                    if(unbalanced.getRightChild().getWeight() > 0){
                        this.simpleLeftRotation(unbalanced);
                    }else{
                        this.doubleLeftRotation(unbalanced);
                    }
                }
            }
        } while (unbalanced != null);
    }

    public static <T> void preOrder(NodeAVL<T> node){
        if(node == null){
            return;
        }
        System.out.println(node.getClave());
        preOrder(node.getLeftChild());
        preOrder(node.getRightChild());
    }
}
