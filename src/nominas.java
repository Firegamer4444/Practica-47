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
            this.listaPagos.add(pago);
            if (buscar_empresa(linea_c[4]) == null){
                String nombre = linea_c[1];
                String idEmpresa = linea_c[4];
                String telefono = linea_c[6];
                String email = linea_c[7];
                String[] apellidoNombre = linea_c[8].split(",");
                String apellidoContacto = apellidoNombre[0];
                String nombreContacto = apellidoNombre[1];
                empresas empresa = new empresas(nombre, idEmpresa, telefono, email, apellidoContacto, nombreContacto);
                this.listaEmpresas.add(empresa);
                empresa.añadir_pago(pago);
            }
            else{
                empresas empresa = buscar_empresa(linea_c[4]);
                empresa.añadir_pago(pago);
            }
            System.out.println("hola");
        }
        }
        catch (IOException e) {
            System.out.print("Error al leer el archivo");
        }
    }

    public empresas buscar_empresa(String idEmpresa){
        for (int i = 0 ; i < this.listaEmpresas.size() ; i++){
            if (this.listaEmpresas.get(i).idEmpresa == idEmpresa){
                return this.listaEmpresas.get(i);
            }
        }
        return null;
    }

}
