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

    public ArbolAVL(){
    }
    
    public ArbolAVL(Nodo<T> root){
        super(root);
    }
    
    public NodeAVL<T> getAVLRoot(){
        return (NodeAVL<T>) root;
    }

    @Override
    public void add(Nodo<T> node){
        super.add(node);
        this.restructuring();
    }
    
    public void simpleRightRotation(NodeAVL<T> node){
        if(node == null) return;
        NodeAVL<T> child = (NodeAVL<T>) node.getHijoIzquierdo();
        if(child == null)return;
        
        NodeAVL<T> Padre = (NodeAVL<T>) node.getPadre();
        
        node.setHijoIzquierdo(child.getHijoDerecho());
        if(child.getHijoDerecho() != null) child.getHijoDerecho().setPadre((Nodo<T>) node);
        
        child.setPadre((Nodo<T>) Padre);
        if(Padre == null)root = (Nodo<T>) child;
        else if(node == Padre.getHijoIzquierdo()) Padre.setHijoIzquierdo((Nodo<T>) child);
        else Padre.setHijoDerecho((Nodo<T>) child);
        
        child.setHijoDerecho((Nodo<T>) node);
        node.setPadre((Nodo<T>) child);
    }
    
    public void simpleLeftRotation(NodeAVL<T> node){
        if(node == null) return;
        NodeAVL<T> child = (NodeAVL<T>) node.getHijoDerecho();
        if(child == null) return;
        
        NodeAVL<T> padre = (NodeAVL<T>) node.getPadre();
        
        node.setHijoDerecho(child.getHijoIzquierdo());
        if(child.getHijoIzquierdo() != null) child.getHijoIzquierdo().setPadre((Nodo<T>) node);
        
        child.setPadre((Nodo<T>) padre);
        if(padre == null)root = (Nodo<T>) child;
        else if(node == padre.getHijoIzquierdo()) padre.setHijoIzquierdo((Nodo<T>) child);
        else padre.setHijoDerecho((Nodo<T>) child);
        
        child.setHijoIzquierdo((Nodo<T>) node);
        node.setPadre((Nodo<T>) child);
    }
    
    public void doubleRightRotation(NodeAVL<T> node){
        this.simpleLeftRotation((NodeAVL<T>) node.getHijoIzquierdo());
        this.simpleRightRotation(node);
    }
    
    public void doubleLeftRotation(NodeAVL<T> node){
        this.simpleRightRotation((NodeAVL<T>) node.getHijoDerecho());
        this.simpleLeftRotation(node);
    }
    
    public NodeAVL<T> balanced(NodeAVL<T> node){
        if(node == null) return null;
        int actualWeight = node.getWeight();
        if(actualWeight > 1 || actualWeight < -1) return node;
        else{ 
            NodeAVL<T> hijoIzquierdo = balanced((NodeAVL<T>) node.getHijoIzquierdo());
            if(hijoIzquierdo != null) return hijoIzquierdo;
            NodeAVL<T> hijoDerecho = balanced((NodeAVL<T>) node.getHijoDerecho());
            if( hijoDerecho != null) return hijoDerecho;
            return null; 
        }
    }

    public void restructuring(){
        NodeAVL<T> unbalanced;
        do {
            unbalanced =  balanced(this.getAVLRoot()); 
            if(unbalanced != null){
                if(unbalanced.getWeight() < -1){
                    if(((NodeAVL<T>) unbalanced.getHijoIzquierdo()).getWeight() < 0){
                        this.simpleRightRotation(unbalanced);
                    }else{
                        this.doubleRightRotation(unbalanced); 
                    }
                }else if(unbalanced.getWeight() > 1){
                    if(((NodeAVL<T>) unbalanced.getHijoDerecho()).getWeight() > 0){
                        this.simpleLeftRotation(unbalanced);
                    }else{
                        this.doubleLeftRotation(unbalanced);
                    }
                }
            }
        } while (unbalanced != null);
    }

    public static <T> void preOrder(Nodo<T> node){
        if(node == null){
            return;
        }
        System.out.println(node.getClave());
        preOrder((Nodo<T>)node.getHijoIzquierdo());
        preOrder((Nodo<T>)node.getHijoDerecho());
    }
}
