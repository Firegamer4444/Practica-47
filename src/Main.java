import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public void instanciar_objetos(){
        String linea = null; 
        try (FileReader fileReader = new FileReader("");
        BufferedReader bufferedReader = new BufferedReader(fileReader);){
        while((linea=bufferedReader.readLine()) != null) {
            String[] linea_c = linea.split("    ");

        }
        }
        catch (IOException e) {
            System.out.print("Error al leer el archivo");
        }
    }

    
    public static void main(String[] args) throws Exception {
        
    }
}
