import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {

    public static void instanciar_objetos(ArrayList<pagos> listaPagos , ArrayList<provedores> listaProvedores , String nombreFichero){
        String linea = null; 
        try (FileReader fileReader = new FileReader(nombreFichero);
        BufferedReader bufferedReader = new BufferedReader(fileReader);){
        String idEmpresa = nombreFichero.replaceAll("[a-d][f-z]" , "");
        while((linea=bufferedReader.readLine()) != null) {
            String[] linea_c = linea.split("\t");
            String idCompra = linea_c[0];
            linea_c[2] = linea_c[2].replace("." , "");
            linea_c[2] = linea_c[2].replace("," , ".");
            Double cuantia = Double.parseDouble(linea_c[2]);
            String fecha = linea_c[3];
            pagos pago = new pagos(idCompra, cuantia , fecha , idEmpresa);
            listaPagos.add(pago);
            if (buscar_Provedor(linea_c[4] , listaProvedores) == null){
                String nombre = linea_c[1];
                String idProvedor = linea_c[4];
                String telefono = linea_c[6];
                String email = linea_c[7];
                String[] apellidoNombre = linea_c[8].split(",");
                String apellidoContacto = apellidoNombre[0];
                String nombreContacto = apellidoNombre[1];
                provedores Provedor = new provedores(nombre, idProvedor, telefono, email, apellidoContacto, nombreContacto);
                listaProvedores.add(Provedor);
                Provedor.añadir_pago(pago);
            }
            else{
                provedores Provedor = buscar_Provedor(linea_c[4] , listaProvedores);
                Provedor.añadir_pago(pago);
            }
        }
        }
        catch (IOException e) {
            System.out.print("Error al leer el archivo");
        }
    }

    public static void crearCarta(provedores provedor , empresas empresa){
        String carta = empresa.modeloCarta;
        carta.replace("[Nombre_cliente]", provedor.nombreContacto);
        carta.replace("[Numero_nulidad]", empresa.numeroNulidad);
    }


    public static provedores buscar_Provedor(String idProvedor ,  ArrayList<provedores> listaProvedores){
        for (int i = 0 ; i < listaProvedores.size() ; i++){
            if (listaProvedores.get(i).idProvedor.equals(idProvedor)){
                return listaProvedores.get(i);
            }
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        ArrayList<pagos> listaPagos = new ArrayList<pagos>();
        ArrayList<provedores> listaProvedores = new ArrayList<provedores>();


}
}
