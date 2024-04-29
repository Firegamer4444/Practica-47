import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void instanciar_objetos(ArrayList<pagos> listaPagos , ArrayList<empresas> listaEmpresas){
        String linea = null; 
        try (FileReader fileReader = new FileReader("src/Nulidad.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);){
        while((linea=bufferedReader.readLine()) != null) {
            String[] linea_c = linea.split("\t");
            String idCompra = linea_c[0];
            linea_c[2] = linea_c[2].replace("." , "");
            linea_c[2] = linea_c[2].replace("," , ".");
            Double cuantia = Double.parseDouble(linea_c[2]);
            String fecha = linea_c[3];
            pagos pago = new pagos(idCompra, cuantia , fecha);
            listaPagos.add(pago);
            if (buscar_empresa(linea_c[4] , listaEmpresas) == null){
                String nombre = linea_c[1];
                String idEmpresa = linea_c[4];
                String telefono = linea_c[6];
                String email = linea_c[7];
                String[] apellidoNombre = linea_c[8].split(",");
                String apellidoContacto = apellidoNombre[0];
                String nombreContacto = apellidoNombre[1];
                empresas empresa = new empresas(nombre, idEmpresa, telefono, email, apellidoContacto, nombreContacto);
                listaEmpresas.add(empresa);
                empresa.añadir_pago(pago);
            }
            else{
                empresas empresa = buscar_empresa(linea_c[4] , listaEmpresas);
                empresa.añadir_pago(pago);
            }
        }
        }
        catch (IOException e) {
            System.out.print("Error al leer el archivo");
        }
    }

    public static empresas buscar_empresa(String idEmpresa ,  ArrayList<empresas> listaEmpresas){
        for (int i = 0 ; i < listaEmpresas.size() ; i++){
            if (listaEmpresas.get(i).idEmpresa.equals(idEmpresa)){
                return listaEmpresas.get(i);
            }
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        ArrayList<pagos> listaPagos = new ArrayList<pagos>();
        ArrayList<empresas> listaEmpresas = new ArrayList<empresas>();
        instanciar_objetos(listaPagos , listaEmpresas);

}
}
