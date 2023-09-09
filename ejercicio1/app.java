package ejercicio1;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class app {

    private static int[][] hoja;
    private static boolean[][] verificado;
    private static ArrayList<String> recorrido = new ArrayList<>();
    private static Stack<String> recorridoTemporal = new Stack<>();


    public static void main(String[] args) throws FileNotFoundException {
        Scanner fileScanner = new Scanner(new File("pruebaTecnicaArchivo.txt"));

        String[] dimensiones = fileScanner.nextLine().split(" ");
        int filas = Integer.parseInt(dimensiones[0]);
        int columnas = Integer.parseInt(dimensiones[1]);

        hoja = new int[filas][columnas];
        verificado = new boolean[filas][columnas];

        for (int i = 0; i < filas; i++) {
            String linea = fileScanner.next();
            for (int j = 0; j < columnas; j++) {
                hoja[i][j] = linea.charAt(j) - '0';
            }
        }

        Scanner valorEntrada = new Scanner(System.in);
        int inciarColor;

        while (true) {
            System.out.print("En qué posición deseas iniciar: ");
            inciarColor = valorEntrada.nextInt() - 1;
            
            if (inciarColor < 0 || inciarColor >= columnas) {
                System.out.println("La columna que indico no existe en el archivo intente con otra");
                continue;
            }

            if (hoja[0][inciarColor] == 1) {
                System.out.println("En posición que indico existe una pared intente con otra");
                continue;
            }

            if (validacionColumnas(new Posicion(0, inciarColor))) {
                while (!recorridoTemporal.isEmpty()) {
                    recorrido.add(recorridoTemporal.pop());
                }
                Collections.reverse(recorrido);
                break;
            } else {
                System.out.println("LLegamos a un punto sin salida no se puede avanzar intente con otra");
                recorridoTemporal.clear();
            }
        }
        for (String i : recorrido) {
            System.out.println(i);
        }
    }
   
    private static boolean validacionColumnas(Posicion pos) {
        if (pos.x < 0 || pos.y < 0 || pos.x >= hoja.length || pos.y >= hoja[0].length || hoja[pos.x][pos.y] == 1 || verificado[pos.x][pos.y]) {
            return false;
        }
        verificado[pos.x][pos.y] = true;
        recorridoTemporal.push("[FILA: " + (pos.x + 1) + " COLUMNA: " + (pos.y + 1) + "]");

        limpiarConsola();
        mostrarhoja();
        esperar();

        if (pos.x == hoja.length - 1) {
            return true;
        }

        for (Posicion dir : DIRECCIONES) {
            if (validacionColumnas(new Posicion(pos.x + dir.x, pos.y + dir.y))) {
                return true;
            }
        }

        recorridoTemporal.pop();
        return false;
    }

    private static void mostrarhoja() {
        for (int i = 0; i < hoja.length; i++) {
            for (int j = 0; j < hoja[0].length; j++) {
                if (verificado[i][j]) {
                    System.out.print("\033[32m" + hoja[i][j] + "\033[0m"); 
                } else if (hoja[i][j] == 1) {
                    System.out.print("\033[31m" + hoja[i][j] + "\033[0m"); 
                } else {
                    System.out.print(hoja[i][j]);
                }
            }
            System.out.println();
        }
    }

    private static void esperar() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void limpiarConsola() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    
    private static class Posicion {
        int x;
        int y;

        public Posicion(int x, int y) {
            this.x = x;
            this.y = y;
        }
       }
   private static final List<Posicion> DIRECCIONES = List.of(
        // Norte
        new Posicion(-1, 0), 
        // Este
        new Posicion(0, 1),  
        // Sur
        new Posicion(1, 0),  
        // Oeste
        new Posicion(0, -1)    
    );

}

