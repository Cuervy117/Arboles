package principal;
import java.util.ArrayList;

import AVL.*;
import archivos.*;
/**
 *
 * @author David
 */
public class Principal {
    public static void main(String[] args) {
        
        ArrayList<ArbolAVL> arboles = new ArrayList<>();
        Archivos.leerBaseDeDatos(arboles);
        ArbolAVL.preOrder(arboles.get(0).getRoot());
        Archivos.guardarDatos(arboles);
    }
}
