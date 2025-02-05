package es.ies.puerto.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class EjemploFile {
    public static void main(String[] args) {
        String path = "C:/ejemplos/archivo.txt";
        path = "/Users/jpexposito/Downloads/java-ficheros/src/main/resources/archivo.txt";
        File archivo = new File(path);
        // Verifica si el archivo existe
        if (archivo.exists()) {
            System.out.println("El archivo existe.");
        } else {
            System.out.println("El archivo no existe.");
        }

    }    
}