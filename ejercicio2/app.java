package ejercicio2;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class app {

    public static void main(String[] args) {
        try {
            Scanner fileScanner = new Scanner(new File("pruebaTecnicaArchivo.txt"));
            while (fileScanner.hasNextLine()) {
                String cadena = fileScanner.nextLine();
                if (cadena.chars().filter(x -> x == '(').count()== cadena.chars().filter(x -> x== ')').count()) {
                    System.out.println(cadena + " CORRECTO");
                } else {
                    System.out.println(cadena + " INCORRECTO");
                }
            }
            fileScanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

