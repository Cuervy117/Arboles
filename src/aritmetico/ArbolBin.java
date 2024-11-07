import java.util.LinkedList;
import java.util.Queue;

public class ArbolBin {
    Nodo root;
    
    public ArbolBin(){
        root=null;
    }
    
    public ArbolBin(String val){
        root=new Nodo(val);
    }
    
    public ArbolBin(Nodo root){
        this.root=root;
    }
    
    public void add(Nodo padre, Nodo hijo, int lado){
	if(lado==0)
            padre.setIzq(hijo);
	else
            padre.setDer(hijo);
    }

    public void notPrefija(Nodo r) {
        if (r != null) {
            System.out.println(r.getValor());
            notPrefija(r.getIzq());
            notPrefija(r.getDer());
        }   
    }

    public void notInfija(Nodo r){
        if (r != null) {
            notInfija(r.getIzq());
            System.out.println(r.getValor());
            notInfija(r.getDer());
        }  
    }

    public void notPostfija(Nodo r) {
        if (r != null) {
            notPostfija(r.getIzq());
            notPostfija(r.getDer());
            System.out.println(r.getValor());
        }  
    } 

}
