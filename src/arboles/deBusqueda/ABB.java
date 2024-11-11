package arboles.deBusqueda;

import arboles.binario.ArbolBinario;
import arboles.binario.Nodo;

public class ABB<T extends Comparable<T>> extends ArbolBinario<T> {

    public ABB(){
        super();
    }
    
    public ABB(T clave){
        super(clave);
    }
    
    public ABB(Nodo<T> root){
        super(root);
    }

    @Override
    public void add(Nodo<T> nodo){

        try{
            search(nodo.getClave());
            System.out.println("El valor ya se encuentra en el arbol");
            return;
        }catch (Exception e){

        }
        if(nodo == null) return;
        Nodo<T> rootAux = getRoot();
        
        if(rootAux == null ){
            setRoot(nodo);;
            return;
        }

        while(true){
            int comparacion = nodo.getClave().compareTo(rootAux.getClave());
            if(comparacion == 0) break;
            else if(comparacion > 0){
                if(rootAux.getHijoDerecho() != null){
                    rootAux = rootAux.getHijoDerecho();
                }else{
                    nodo.setPadre(rootAux);
                    rootAux.setHijoDerecho(nodo);
                    break;
                }
            }else{
                if(rootAux.getHijoIzquierdo() != null){
                    rootAux = rootAux.getHijoIzquierdo();
                }else{
                    nodo.setPadre(rootAux);
                    rootAux.setHijoIzquierdo(nodo);
                    break;
                }                    
            }
        }
    }

    @Override
    public void delete(T clave) throws Exception{
        if(root == null) return;
        Nodo<T> nodo;
        nodo = search(clave);
        if(nodo.isLeaf()){
            eliminarHoja(nodo);
        } else {
            nodeSwap(nodo, nodo.getNodoSucesor());
            eliminarHoja(nodo);
        }
    }

    @Override
    public Nodo<T> search(T clave) throws Exception{
        Nodo<T> auxRoot = this.root;

        while (auxRoot != null) {
            int comparacion = auxRoot.getClave().compareTo(clave);
            if(comparacion == 0) return auxRoot;
            else if(comparacion > 0) auxRoot = auxRoot.getHijoIzquierdo();
            else auxRoot = auxRoot.getHijoDerecho();
        }
        throw new Exception("El nodo con dicho valor no existe");
    }

}
