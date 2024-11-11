package principal;

import java.util.Scanner;

/**
 *
 * @author David
 */
public class Principal {
    public static void main(String[] args) throws Exception {
        
        menus.MenuHeap.ejecutarMenu(new Scanner(System.in));

        menus.MenuAVL.ejecutarMenu(new Scanner(System.in));
        
    }
}
