import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {

    
    
    /**
     * Funcion que leyendo la informacion del fichero de nulidad instancia objetos empresas y pagos
     * @param listaPagos lista de objetos pagos
     * @param listaProvedores lista de objetos provedores
     * @param nombreFichero nombre del fichero de nulidad
     */
    public static void instanciar_objetos(ArrayList<pagos> listaPagos , ArrayList<provedores> listaProvedores , String nombreFichero){
        String linea = null; 
        String rutaFichero = ("archivos/nulidades/" + nombreFichero);
        try (FileReader fileReader = new FileReader(rutaFichero);
        BufferedReader bufferedReader = new BufferedReader(fileReader);){
        String idEmpresa = nombreFichero.replace("Nulidad_", "");
        idEmpresa = idEmpresa.replace(".txt", "");
        while((linea=bufferedReader.readLine()) != null) {
            String[] linea_c = linea.split("\t");
            String idCompra = linea_c[0];
            linea_c[2] = linea_c[2].replace("." , "");
            linea_c[2] = linea_c[2].replace("," , ".");
            Double cuantia = Double.parseDouble(linea_c[2]);
            String fecha = linea_c[3];
            pagos pago = new pagos(idCompra, cuantia , fecha , idEmpresa);
            listaPagos.add(pago);
            if (BuscarProvedor(linea_c[4] , listaProvedores) == null){
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
                provedores Provedor = BuscarProvedor(linea_c[4] , listaProvedores);
                Provedor.añadir_pago(pago);
            }
        }
        }
        catch (IOException e) {
            System.out.print("Error al leer el archivo");
        }
    }

    /**
     * Funcion que genera los ficheros de las cartas y la carpeta en la que se guarda
     * @param carta string del contenido de la carta
     * @param nombreProvedor nombre del provedor
     * @param idEmpresa id de la empresa
     */
    public static void CrearArchivoCarta(String carta , String nombreProvedor , String idEmpresa){
        String rutaCarpetacarta = ("archivos/cartas" + "/" + idEmpresa);
        String rutaCarta = (rutaCarpetacarta  + "/" + nombreProvedor + ".txt");
        File carpetaCartas = new File(rutaCarpetacarta);
        carpetaCartas.mkdirs();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaCarta, true));){
            writer.write(carta);
        }
        catch (IOException e) {
            System.out.print("Error al leer el archivo");
        }
    }

    /**
     * Funcion que sustituye los campos de el modelo de carta por la informacion de la empresa y del provedor
     * @param provedor objeto provedor 
     * @param empresa objeto empresa
     * @param nombreUsuario nombre de usuario
     * @return el contenido de la carta contenido en un string
     */
    public static String crearCarta(provedores provedor , empresas empresa , String nombreUsuario){
        String carta = empresa.modeloCarta;
        String totalCuantia = String.valueOf(provedor.TotalCuantiaEmpresa(empresa.idEmpresa));
        String totalPagos = provedor.PagosDeEmpresa(empresa.idEmpresa);
        carta = carta.replace("[Nombre_cliente]", provedor.nombreContacto);
        carta = carta.replace("[Numero_nulidad]", empresa.numeroNulidad);
        carta = carta.replace("[nombre_empresa]", provedor.nombre);
        carta = carta.replace("[total_servicios]", totalCuantia);
        carta = carta.replace("[Lista_pago_servicios]", totalPagos);
        carta = carta.replace("[correo_empresa_nulidad]", empresa.emailEmpresa);
        carta = carta.replace("[su_nombre]" , nombreUsuario);
        return carta;
    }

    /**
     * Funcion que busca en la lista provedores el provedor con la id pasada por parametro
     * @param idProvedor id del provedor que quieres buscar
     * @param listaProvedores lista de provedores
     * @return el provedor encontrado o null si no existe
     */
    public static provedores BuscarProvedor(String idProvedor ,  ArrayList<provedores> listaProvedores){
        for (int i = 0 ; i < listaProvedores.size() ; i++){
            if (listaProvedores.get(i).idProvedor.equals(idProvedor)){
                return listaProvedores.get(i);
            }
        }
        return null;
    }

    /**
     * Funcion que busca en la lista empresas la empresa con el id pasado por parametro
     * @param idEmpresa id de la empresa que quieres buscar
     * @param listaEmpresas lista de empresas
     * @return la empresa encontrada o null si no existe
     */
    public static empresas BuscarEmpresa(String idEmpresa ,  ArrayList<empresas> listaEmpresas){
        for (int i = 0 ; i < listaEmpresas.size() ; i++){
            if (listaEmpresas.get(i).idEmpresa.equals(idEmpresa)){
                return listaEmpresas.get(i);
            }
        }
        return null;
    }

    /**
     * Funcion que busca en la lista de nulidades la nulidad perteneciente a la empresa del id pasado por parametro
     * @param idEmpresa id de la empresa de la nulidad que quieres buscar
     * @param listaNulidades lista de nulidades
     * @return la nulidad encontrada o null si no existe
     */
    public static String BuscarNulidad(String idEmpresa ,  String[] listaNulidades){
        for (int i = 0 ; i < listaNulidades.length ; i++){
            if (listaNulidades[i].equals(idEmpresa)){
                return listaNulidades[i];
            }
        }
        return null;
    }

    
    /**
     * menu principal donde se llaman al resto de funciones
     * @param listaPagos 
     * @param listaProvedores 
     * @param listaEmpresas  
     * @param listaNulidades 
     */
    public static void menu(ArrayList<pagos> listaPagos , ArrayList<provedores> listaProvedores , ArrayList<empresas> listaEmpresas , String[] listaNulidades){
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("\n--- Opciones: ---");
            System.out.println("1. Iniciar sesion");
            System.out.println("2. Cerrar programa");
            System.out.println("Seleccione una opcion: ");
            String opcion = scanner.nextLine();
            if (opcion.equals("1")){
                System.out.println("Introduzca su nombre: ");
                String nombreUsuario = scanner.nextLine();
                System.out.println("Empresas disponible:");
                for (int i = 0 ; i < listaEmpresas.size() ; i++){
                    System.out.println("id: " + listaEmpresas.get(i).idEmpresa + "    nombre: " + listaEmpresas.get(i).nombreEmpresa);
                }
                System.out.println("Introduzca el id de la empresa a la que pertenece: ");
                String idEmpresa = scanner.nextLine();
                empresas empresaActual = BuscarEmpresa(idEmpresa , listaEmpresas);
                String nulidadActual = BuscarNulidad(idEmpresa, listaNulidades);
                if (nulidadActual.equals(null)){
                    System.out.println("Su empresa no tiene nulidades");
                }
                else{
                    while (true){
                        System.out.println("\n--- Opciones: ---");
                        System.out.println("1. Enviar email");
                        System.out.println("2. Enviar fax");
                        System.out.println("3. volver al menu principal");
                        System.out.println("Seleccione una opcion: ");
                        opcion = scanner.nextLine();
                        if (opcion.equals("1") == true | opcion.equals("2") == true){
                            for (int i = 0 ; i < listaProvedores.size() ; i++){
                                if (listaProvedores.get(i).PagosDeEmpresa(empresaActual.idEmpresa) != ""){
                                    String carta = crearCarta(listaProvedores.get(i), empresaActual, nombreUsuario);
                                    CrearArchivoCarta(carta, listaProvedores.get(i).nombre , empresaActual.idEmpresa);
                                    if (opcion.equals("1")){
                                        System.out.println("email enviado al provedor " + listaProvedores.get(i).nombre);
                                    }
                                    else{
                                        System.out.println("fax enviado al provedor " + listaProvedores.get(i).nombre);
                                    }
                                }
                            }
                            
                        }
                        else {
                            break;
                        }
                    }
                }
            }
            else if (opcion.equals("2")){
                scanner.close();
                File directorioCartas = new File("archivos/cartas");
                File[] listaDirectoriosEmpresas = directorioCartas.listFiles();
                for (int i = 0 ; i < listaDirectoriosEmpresas.length; i++){
                    File[] listaCartas = listaDirectoriosEmpresas[i].listFiles();
                    for (int j = 0 ; j < listaCartas.length; j++){
                        listaCartas[j].delete();
                    }
                }
                return;
            }
        }
    }


    public static void main(String[] args) throws Exception {
        ArrayList<pagos> listaPagos = new ArrayList<pagos>();
        ArrayList<provedores> listaProvedores = new ArrayList<provedores>();
        ArrayList<empresas> listaEmpresas = new ArrayList<empresas>();
        File nulidades = new File("archivos/nulidades");
        String[] listaNulidades = nulidades.list();
        for (int i = 0 ; i < listaNulidades.length; i++ ){
            instanciar_objetos(listaPagos, listaProvedores, listaNulidades[i]);
            listaNulidades[i] = listaNulidades[i].replace("Nulidad_" , "");
            listaNulidades[i] = listaNulidades[i].replace(".txt" , "");
        }
        empresas empresa01 = new empresas("E01", "Empresa01", "empresa01@gmail.com" , "¡Hola [Nombre_cliente]!\n\n¡Te escribo para contarte que ya hemos preparado la nulidad [Numero_nulidad] para cuadrar lo del pago de los servicios de tu empresa [nombre_empresa]! El total a pagar es de [total_servicios], y aquí te dejo el desglose:\n\n[Lista_pago_servicios]\n\nCuando puedas, échale un vistazo y confírmame que todo está como debe ser. Solo necesitas mandarme un correo a [correo_empresa_nulidad] diciendo que todo está ok.\n\n¡Gracias y un saludo!\n\n[su_nombre]", "18-2021");
        empresas empresa02 = new empresas("E02", "Empresa02", "empresa02@gmail.com" , "Estimado/a [Nombre_cliente],\n\nPor medio de la presente, le informamos que hemos procedido a la creación de la nulidad [Numero_nulidad]. Esta acción corresponde al proceso de compensación económica por los servicios prestados por su compañía [nombre_empresa], cuyo monto asciende a [total_servicios]. A continuación, encontrará el detalle de los pagos correspondientes:\n\n[Lista_pago_servicios]\n\nEs imperativo que nos confirme la recepción de este documento y la aceptación de los términos contenidos en él. Para ello, solicitamos que se comunique con nosotros a través de nuestro correo electrónico [correo_empresa_nulidad] a la mayor brevedad posible.\n\nAgradecemos de antemano su colaboración y comprensión.\n\nAtentamente,\n\n[su_nombre]", "18-2021");
        listaEmpresas.add(empresa01);
        listaEmpresas.add(empresa02);
        menu(listaPagos, listaProvedores, listaEmpresas, listaNulidades);
}
}
