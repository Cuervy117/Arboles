package Redblack;

public class Arbolredblack extends ArbolBinarioBusqueda{
    public Arbolredblack(){
        super();
    }
    public Arbolredblack(Nodo raiz){
        super(raiz);
    }

    public void insertar(Nodo añadido){
        //Recordando que true = rojo, false = negro
        añadir(añadido); 
        Nodo auxiliar = añadido;
        System.out.println("Padre del nodo añadido " + añadido.getDato() + " :" + añadido.getPadre());
        while(Nodo.color(auxiliar.getPadre()) == true ){
            int caso = IdentificarCaso(añadido);
            switch (caso) {
                case 1 -> {
                    System.out.println("Caso 1 detectado");
                    auxiliar = corregirCaso1(añadido);
                }
                case 2 -> {
                    System.out.println("Caso 2 detectado");
                    auxiliar = corregirCaso2(añadido);
                }
                case 3 -> {
                    System.out.println("Caso 3 detectado");
                    auxiliar = corregirCaso3(añadido);
                }
                default -> System.out.println("Puede que se esté iniciando");
            }
        }
        if(auxiliar != null){
            while(auxiliar.getPadre() != null){
                auxiliar = auxiliar.getPadre();
                raiz = auxiliar;
            }
        }
        this.raiz.setColor(false);
    }

    private int IdentificarCaso(Nodo añadido){
        try {
            Nodo tio = Nodo.getTio(añadido);
            if(tio.esRojo() && !añadido.getPadre().esRojo()){  
                return 1;
            }else{
                if(añadido.getPadre().esRojo() && !añadido.esHijoIzquierdo()){
                    return 2;
                }else{
                    return 3;
                }
            }
        } catch (NullPointerException e) {
            System.out.println("Error en Identificar Caso(): " + e.getMessage());
            return -1;
        }


    }

    private Nodo corregirCaso1(Nodo añadido){
        Nodo padre = añadido.getPadre();
        padre.getDerecha().setColor(false);
        padre.getIzquierda().setColor(false);
        return añadido;
    }

    private Nodo corregirCaso2(Nodo añadido){
        Nodo apuntador = añadido.getPadre();
        añadido.setPadre(apuntador.getPadre());
        añadido.setIzquierda(apuntador);
        apuntador.setPadre(añadido);
        return apuntador;
    }

    private Nodo corregirCaso3(Nodo añadido){
        //Cambio de colores
        añadido.getPadre().getPadre().setColor(true);
        añadido.getPadre().setColor(false);
        //Rotando a la derecha
        Nodo abuelo = añadido.getPadre().getPadre();
        Nodo huerfano = añadido.getPadre().getDerecha();
        huerfano.setPadre(abuelo);
        abuelo.setIzquierda(huerfano);

        abuelo.setPadre(añadido.getPadre());
        abuelo.getPadre().setDerecha(abuelo);
        return añadido;
    }

    public static void recorridoPreOrden(Nodo raiz) {
        if(raiz != null){
            System.out.println(raiz.getDato() + " " + raiz.esRojo());
            recorridoPreOrden(raiz.getIzquierda());
            recorridoPreOrden(raiz.getDerecha());
        }
    }

}
