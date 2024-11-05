/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AVL;

/**
 *
 * @author David
 */
public class ArbolAVL {
    private Node root;

    
    public ArbolAVL(){

    }
    
    public ArbolAVL(Node root){
        this.root = root;
    }
    
    public Node getRoot(){
        return root;
    }
    
    public void insert(Node node){
        Node rootAux = root;
        if(root == null){
            this.root = node;
            return;
        }
        if(root == node){
            return;
        }else{
            while(true){
                if(node.getClave() < rootAux.getClave()){
                    if(rootAux.getRight_child() != null){
                        rootAux = rootAux.getRight_child();
                    }else{
                        rootAux.getRight_child().setRight_child(node);
                        break;
                    }
                }else if(node.getClave() > rootAux.getClave()){
                    
                }
            }
        }
        
        
    }
    
    public static void preOrder(Node node){
        if(node == null){
            return;
        }
        System.out.println(node.getClave());
        preOrder(node.getLeft_child());
        preOrder(node.getRight_child());
    }
    
    
}
