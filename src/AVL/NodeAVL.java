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
public class NodeAVL<T> extends Nodo<T>{
    private static final long serialVersionUID = 123456789L;

    private NodeAVL<T> leftChild;
    private NodeAVL<T> rightChild; 
    private NodeAVL<T> parent;
    
    public NodeAVL(){

    }
    
    public NodeAVL(T clave){
        this.clave = clave;
    }
    
    public boolean isLeaf(){
        return (leftChild == null) && (rightChild == null) ? true : false;
    }
    
    public T getClave() {
        return clave;
    }

    public void setclave(T clave) {
        this.clave = clave;
    }

    public NodeAVL<T> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(NodeAVL<T> leftChild) {
        this.leftChild = leftChild;
    }

    public NodeAVL<T> getRightChild() {
        return rightChild;
    }

    public void setRightChild(NodeAVL<T> rightChild) {
        this.rightChild = rightChild;
    }
    
    public NodeAVL<T> getParent(){
        return parent;
    }
    
    public void setParent(NodeAVL<T> parent){
        this.parent = parent;
    }
    public int getHeight(){
        if(this.isLeaf())return 0;
        else{
            return 1 + Math.max(rightChild != null ? rightChild.getHeight() : -1,
                    leftChild != null ? leftChild.getHeight() : -1);
        }
    }
    
    public int getWeight(){
        return (rightChild != null ? rightChild.getHeight() : -1) 
        - (leftChild != null ? leftChild.getHeight() : -1);
    }
}
