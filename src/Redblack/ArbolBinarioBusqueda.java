package Redblack;

public class ArbolBinarioBusqueda {
    protected Nodo raiz;
    public ArbolBinarioBusqueda(Nodo raiz){
        this.raiz = raiz;
    }
    public ArbolBinarioBusqueda(){
        this.raiz = null;
    }

    protected void añadir(Nodo nodo){
        Nodo rootAux = raiz;
        if(raiz == null){
            this.raiz = nodo;
            return;
        }
        while(true){
            if(rootAux.getDato() == nodo.getDato()) break;
            else if(nodo.getDato() > rootAux.getDato()){
                if(rootAux.getDerecha() != null){
                    rootAux = rootAux.getDerecha();
                }else{
                    nodo.setPadre(rootAux);
                    rootAux.setDerecha(nodo);
                    break;
                }
            }else{
                if(rootAux.getIzquierda() != null){
                    rootAux = rootAux.getIzquierda();
                }else{
                    nodo.setPadre(rootAux);
                    rootAux.setIzquierda(nodo);
                    break;
                }                    
            }
        }
    }
}
