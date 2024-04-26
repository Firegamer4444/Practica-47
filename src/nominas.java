import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class nominas {
    ArrayList<pagos> listaPagos;
    ArrayList<empresas> listaEmpresas;
    
    public nominas(){
        this.listaPagos = new ArrayList<pagos>();
        this.listaEmpresas = new ArrayList<empresas>();
    }
    
    public void instanciar_objetos(){
        String linea = null; 
        try (FileReader fileReader = new FileReader("");
        BufferedReader bufferedReader = new BufferedReader(fileReader);){
        while((linea=bufferedReader.readLine()) != null) {
            String[] linea_c = linea.split("\t");
            String nombre = linea_c[1];
            String idEmpresa = linea_c[4];
            String telefono = linea_c[6];
            String email = linea_c[7];
            String apellidoContacto = linea_c[8];
            String nombreContacto = linea_c[9];
            String idCompra = linea_c[0];
            String cuantia = linea_c[2];
            String fecha = linea_c[3];
            ArrayList<pagos> listaDePagos = new ArrayList<pagos>(new pagos(idCompra, cuantia , fecha));
        }
        }
        catch (IOException e) {
            System.out.print("Error al leer el archivo");
        }
    }
}
