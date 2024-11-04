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
        if(root == null){
            root = node;
        }else{
            int key = node.getClave();
            Node rootAux = root;
            while(true){
                if(key < rootAux.getClave()){
                    if(rootAux.getLeft_child() == null){
                        node.setParent(rootAux);
                        rootAux.setLeft_child(node);
                        break;
                    }else{
                        rootAux = rootAux.getLeft_child();
                    }
                }else if(key > rootAux.getClave()){
                    if(rootAux.getRight_child() == null){
                        node.setParent(rootAux);
                        rootAux.setRight_child(node);
                        break;
                    }else{
                        rootAux = rootAux.getRight_child();
                    }
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
