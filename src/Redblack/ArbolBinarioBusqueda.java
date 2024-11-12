package Redblack;

public class ArbolBinarioBusqueda {
    protected  Nodo raiz;
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
    
    /*public String notacionPrefija(){
        StringBuilder prefija = new StringBuilder();
        recorridoPreOrden(raiz, prefija);
        return prefija.toString().trim();
    }*/

    public String notacionInfija(){
        StringBuilder prefija = new StringBuilder();
        recorridoInOrden(raiz, prefija);
        return prefija.toString().trim();
    }

    public String notacionPostfija(){
        StringBuilder prefija = new StringBuilder();
        recorridoPostOrden(raiz, prefija);
        return prefija.toString().trim();
    }

    /*public static void recorridoPreOrden(Nodo raiz, StringBuilder notacion) {
        if(raiz != null){
            notacion.append(raiz.getDato()).append(" ");
            recorridoPreOrden(raiz.getIzquierda(), notacion);
            recorridoPreOrden(raiz.getDerecha(), notacion);
        }
    }*/
    
    public static void recorridoInOrden(Nodo raiz, StringBuilder notacion){
        if(raiz != null){
            recorridoInOrden(raiz.getIzquierda(), notacion);
            notacion.append(raiz.getDato()).append(" ");
            recorridoInOrden(raiz.getDerecha(), notacion);
        }
    }

    public static void recorridoPostOrden(Nodo raiz, StringBuilder notacion){
        if(raiz!= null){
            recorridoPostOrden(raiz.getIzquierda(), notacion);
            recorridoPostOrden(raiz.getDerecha(), notacion);
            notacion.append(raiz.getDato()).append(" ");
        }

    }

    public Nodo getRaiz(){
        return this.raiz;
    }
}
