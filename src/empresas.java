import java.util.ArrayList;

public class empresas {

    String nombre;
    String idEmpresa;
    String telefono;
    String email;
    String apellidoContacto;
    String nombreContacto;
    ArrayList<pagos> pagos;

    public empresas(String nombre , String idEmpresa , String telefono , String email , String apellidoContacto , String nombreContacto){
        this.nombre = nombre;
        this.idEmpresa = idEmpresa;
        this.telefono = telefono;
        this.email = email;
        this.apellidoContacto = apellidoContacto;
        this.nombreContacto = nombreContacto;
        this.pagos = new ArrayList<pagos>();
    }

    public void añadir_pago(pagos pago){
        this.pagos.add(pago);
    }

}
