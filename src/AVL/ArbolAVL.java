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
                    rootAux.setRight_child(node);
                    break;
                }
            }else{
                if(rootAux.getLeft_child() != null){
                    rootAux = rootAux.getLeft_child();
                }else{
                    rootAux.setLeft_child(node);
                    break;
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
