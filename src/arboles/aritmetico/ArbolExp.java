package arboles.aritmetico;

import java.util.List;
import java.util.Stack;

import arboles.binario.Nodo;
import arboles.deBusqueda.ABB;

/**
 * Clase que representa un árbol de expresiones aritméticas.
 * Este incluye los métodos necesarios para crear el árbol.
 */
public class ArbolExp extends ABB<String> {

    private static final List<String> operadores = List.of("+", "-", "*", "/");

    /**
     * Constructor que inicializa el árbol de expresiones basado en una arreglo de elementos.
     * @param elementos Arreglo de Strings que representan la expresión aritmética en notación infija.
     */
    public ArbolExp(String[] elementos) {
        root = crearArbolDeExpresion(elementos);
    }

    /**
     * Crea un árbol de expresiones aritméticas a partir de un arreglo de elementos.
     * @param elementos Array de strings que representa la expresión en notación infija.
     * @return El nodo raíz del árbol de expresión generado.
     */
    public Nodo<String> crearArbolDeExpresion(String[] elementos) {
        Stack<String> operadores = new Stack<>();
        Stack<Nodo<String>> operandos = new Stack<>();

        for (String elemento : elementos) {
            if (esOperando(elemento)) {
                operandos.push(new Nodo<String>(elemento));
            } else if (esOperador(elemento)) {
                while (!operadores.isEmpty() && definirPrioridad(operadores.peek()) >= definirPrioridad(elemento)) {
                    obtenerSubarbol(operadores, operandos);
                }
                operadores.push(elemento);
            } else if (elemento.equals("(")) {
                operadores.push(elemento);
            } else if (elemento.equals(")")) {
                while (!operadores.isEmpty() && !operadores.peek().equals("(")) {
                    obtenerSubarbol(operadores, operandos);
                }
                operadores.pop();
            }
        }

        while (!operadores.isEmpty()) {
            obtenerSubarbol(operadores, operandos);
        }

        return operandos.pop();
    }

    /**
     * Verifica si el elemento dado es un operador aritmético.
     * @param elemento String que representa el elemento que se quiere verificar.
     * @return true si el elemento es un operador, false en caso contrario.
     */
    public static boolean esOperador(String elemento) {
        return operadores.contains(elemento);
    }

    /**
     * Verifica si el elemento dado es un operando numérico.
     * @param elemento String que representa el elemento que se quiere verificar.
     * @return true si el elemento es un operando, false en caso contrario.
     */
    public static boolean esOperando(String elemento) {
        try {
            Double.parseDouble(elemento);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Define la prioridad de los operadores aritméticos para respetar su jerarquía.
     * @param operador String que representa el operador.
     * @return un valor entero que es la prioridad del operador.
     */
    public int definirPrioridad(String operador) {
        if ("+".equals(operador) || "-".equals(operador)) {
            return 1;
        } else if ("*".equals(operador) || "/".equals(operador)) {
            return 2;
        } else {
            return -1;
        }
    }

    /**
     * Crea un subárbol de expresión aritmética usando los operadores y operandos almacenados.
     * Extrae un operador y dos operandos para formar un nuevo nodo y agregarlo a la pila de operandos.
     * @param operadores Pila que contiene los operadores como String.
     * @param operandos Pila que contiene los nodos operandos.
     */
    public void obtenerSubarbol(Stack<String> operadores, Stack<Nodo<String>> operandos) {
        if (operadores.isEmpty() || operandos.size() < 2) return;

        String operador = operadores.pop();
        Nodo<String> der = operandos.pop();
        Nodo<String> izq = operandos.pop();

        Nodo<String> nodoOperador = new Nodo<>(operador);
        nodoOperador.setHijoIzquierdo(izq);
        nodoOperador.setHijoDerecho(der);

        operandos.push(nodoOperador);
    }
}