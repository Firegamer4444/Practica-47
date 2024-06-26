import java.util.ArrayList;

public class provedores {

    String nombre;
    String idProvedor;
    String telefono;
    String emailProvedor;
    String apellidoContacto;
    String nombreContacto;
    ArrayList<pagos> listaPagos;

    public provedores(String nombre , String idProvedor , String telefono , String emailProvedor , String apellidoContacto , String nombreContacto){
        this.nombre = nombre;
        this.idProvedor = idProvedor;
        this.telefono = telefono;
        this.emailProvedor = emailProvedor;
        this.apellidoContacto = apellidoContacto;
        this.nombreContacto = nombreContacto;
        this.listaPagos = new ArrayList<pagos>();
    }

    /**
     * metodo que retorna la lista de pagos de x empresa
     * @param idEmpresa 
     * @return
     */
    public String PagosDeEmpresa(String idEmpresa){
        String pagosDeEmpresa = "";
        for (int i = 0 ; i < this.listaPagos.size(); i++){
            if (this.listaPagos.get(i).idEmpresa.equals(idEmpresa)){
                pagosDeEmpresa = (pagosDeEmpresa+"\nidcompra: "+this.listaPagos.get(i).idCompra+"\ncuantia: "+this.listaPagos.get(i).cuantia+"\nfecha: "+this.listaPagos.get(i).fecha);
            }
        }
        return pagosDeEmpresa;
    }

    /**
     * metodo que retorna la suma de los pagos de x empresa
     * @param idEmpresa
     * @return
     */
    public Double TotalCuantiaEmpresa(String idEmpresa){
        Double totalCuantiaEmpresa = 0.0;
        for (int i = 0 ; i < this.listaPagos.size(); i++){
            if (this.listaPagos.get(i).idEmpresa.equals(idEmpresa)){
                totalCuantiaEmpresa = (totalCuantiaEmpresa + this.listaPagos.get(i).cuantia);
            }
        }
        return totalCuantiaEmpresa;
    }

    /**
     * metodo que añade un pago a la lista de pagos que pasa por parametro
     * @param pago pago que se va a añadir
     */
    public void añadir_pago(pagos pago){
        this.listaPagos.add(pago);
    }

}
