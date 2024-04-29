import java.util.ArrayList;

public class empresas {

    String nombre;
    String idEmpresa;
    String telefono;
    String email;
    String apellidoContacto;
    String nombreContacto;
    ArrayList<pagos> listaPagos;

    public empresas(String nombre , String idEmpresa , String telefono , String email , String apellidoContacto , String nombreContacto){
        this.nombre = nombre;
        this.idEmpresa = idEmpresa;
        this.telefono = telefono;
        this.email = email;
        this.apellidoContacto = apellidoContacto;
        this.nombreContacto = nombreContacto;
        this.listaPagos = new ArrayList<pagos>();
    }

    public void añadir_pago(pagos pago){
        this.listaPagos.add(pago);
    }

}
