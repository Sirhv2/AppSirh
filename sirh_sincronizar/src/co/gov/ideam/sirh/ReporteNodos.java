package co.gov.ideam.sirh;

import co.gov.ideam.sirh.webservices.businessl.ExportSirh;
import co.gov.ideam.sirh.webservices.businessl.ExportaDatosSirh;
import co.gov.ideam.sirh.webservices.businessl.IdeamException_Exception;
import co.gov.ideam.sirh.webservices.businessl.types.SingleRowWebService;
import com.sun.org.apache.xerces.internal.dom.ElementNSImpl;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import org.w3c.dom.Node;
import java.util.logging.Logger;
import javax.xml.namespace.QName;


public class ReporteNodos {

    private static String rutaConfiguracion = "./SirhSincronizar.config";
    private static Properties propiedades = null;
    
    public ReporteNodos() {
    }

    public static void main(String[] args) {
         try {
            configurarLog();
            LOGGER.info("Paso 00. Inicia sincronizacion novedades");
            LOGGER.info("Paso 01. Carga configuracion");
            cargarConfiguracion();
            LOGGER.info("Paso 02. Obtiene Autorizades a sincronizar");
            ArrayList<Autoridad> listaAutoridades = new ArrayList<Autoridad>();
            cargarAutoridades(listaAutoridades);
            for (Autoridad autoridad : listaAutoridades) {
                LOGGER.info("Procesa novedades de la autoridad:" + autoridad.getNombre());
                LOGGER.info("Url autoridad:" + autoridad.getEndPoint());
                LOGGER.info("Fecha Inicio:" + autoridad.getFechaInicio());
                LOGGER.info("Fecha Fin:" + autoridad.getFechaFin());
                aplicarReporteDatos(autoridad);
            }
             LOGGER.info("Proceso ok");

        } catch (Exception e) {

            LOGGER.info("Error General en sincronizar :" + e.getMessage() +
                        ":");

            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);

            LOGGER.info("Trace:" + sw.toString() + ":");
        }


    }

    private static final Logger LOGGER = Logger.getLogger("SirhSincronizar");

    public static void configurarLog() throws IOException {
        Handler consoleHandler = null;
        Handler fileHandler = null;
        // Oobtener fecha del sistema
        Calendar cal1 = Calendar.getInstance();    
        
        // Asignar nombre Log
        String nombreLog = "./SirhSincronizar_" +
                           cal1.get(Calendar.YEAR)+ "_" +
                           ( cal1.get(Calendar.MONTH) + 1 ) + "_" + 
                           cal1.get(Calendar.DATE) + " " + 
                           cal1.get(Calendar.HOUR) + "_" + 
                           cal1.get(Calendar.MINUTE) + "_" + 
                           cal1.get(Calendar.SECOND) + "_" +
                           cal1.get(Calendar.MILLISECOND) + ".log" ;
        
        //Creating consoleHandler and fileHandler
        consoleHandler = new ConsoleHandler();
        fileHandler = new FileHandler(nombreLog);
        
        // Poner formato del log, se deja el defecto que es xml
        //fileHandler.setFormatter(new SimpleFormatter());

        //Assigning handlers to LOGGER object
        LOGGER.addHandler(consoleHandler);
        LOGGER.addHandler(fileHandler);

        //Setting levels to handlers and LOGGER
        consoleHandler.setLevel(Level.ALL);
        fileHandler.setLevel(Level.ALL);
        LOGGER.setLevel(Level.ALL);
        
    }
    
    public static void cargarConfiguracion() throws IOException {
        propiedades = new Properties();
        propiedades.load(new FileInputStream(rutaConfiguracion));

    }

    public static void cargarAutoridades(ArrayList<Autoridad> listaAutoridades) throws ClassNotFoundException,
                                                                                       SQLException {
        Statement stmt = null;
        Connection conn = null;
        
        LOGGER.info("Va a cargar driver BD");
        LOGGER.info("DriverNameBD:" + propiedades.getProperty("DriverNameBD") + ":");
        LOGGER.info("UrlBD:" + propiedades.getProperty("UrlBD") + ":");
        LOGGER.info("UsuarioBD:" + propiedades.getProperty("UsuarioBD") + ":");
        LOGGER.info("ClaveUsuarioBD:" + propiedades.getProperty("ClaveUsuarioBD") + ":");
        
        Class.forName(propiedades.getProperty("DriverNameBD"));
        
        
        LOGGER.info("Obtiene conexion a BD");

        conn = DriverManager.getConnection(
                propiedades.getProperty("UrlBD"), propiedades.getProperty("UsuarioBD"),
                propiedades.getProperty("ClaveUsuarioBD"));
        

        String query = "select id, nombre, sigla, endpointnovedades, "  +
            "nvl(to_char(fechaultimasincronizacion,'yyyymmdd'),'20140101') as fecUlt " +
            "from autoridades a where not a.endpointnovedades is null ";
        
        LOGGER.info("Va a consultar SQL:" + query);

        stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            int codAutoridad = rs.getInt("ID");
            String nomAutoridad = rs.getString("SIGLA");
            String endPoint = rs.getString("ENDPOINTNOVEDADES");
            String fecUltSin = rs.getString("FECULT");
            
            Date today = new Date();
            SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMdd");
            String fecHoy = DATE_FORMAT.format(today);

            LOGGER.info("Autoridad a procesar:" + nomAutoridad  );
            LOGGER.info("EndPoint:" + endPoint  );
            LOGGER.info("Fecha Inicio:" + fecUltSin  );
            LOGGER.info("Fecha Fin:" + fecHoy  );

            Autoridad autoridad = new Autoridad(nomAutoridad,codAutoridad, endPoint, new Integer(fecUltSin),new Integer(fecHoy));
            
            listaAutoridades.add(autoridad);
                
        }
        stmt.close();
        conn.close();

    }

    public static  ArrayList<NovDatosNodos> obtenerDatosReporteNodos(Autoridad autoridad) throws IdeamException_Exception,
                                                                                      MalformedURLException {
        
        LOGGER.info("Se conecta a endPoint:" + autoridad.getEndPoint());
        
        URL url_wsdl = new URL(autoridad.getEndPoint() + "?wsdl");
        
        ExportSirh exportSirh = new ExportSirh(url_wsdl, new QName("http://businessl.webservices.sirh.ideam.gov.co/", "ExportSirh"));
        
        ExportaDatosSirh exportaDatosSirh =
            exportSirh.getExportaDatosSirhPort();

        LOGGER.info("Conexion endPoint ok " + autoridad.getEndPoint());

        ArrayList parametros = new ArrayList();
        //parametros.add(autoridad.getFechaInicio());
        //parametros.add(autoridad.getFechaFin());
        LOGGER.info("parametros size: " + parametros.size());
        LOGGER.info("Va a invocar endPoint " + autoridad.getEndPoint());

        List lista = exportaDatosSirh.getMultipleRows(new Long(propiedades.getProperty("idSentenciaNodos")), parametros);

        LOGGER.info("Invocacion endPoint exitosa " + autoridad.getEndPoint());

        // Obtener lista de novedades recibidas

        ArrayList<NovDatosNodos> listaDatosNodos =  new ArrayList<NovDatosNodos>();

        SingleRowWebService registro = null;
        Iterator itLista = lista.iterator();

        int numNovedad = 1;
        
        // Recorrer registros
        while (itLista.hasNext()) {
            
            LOGGER.info("Generar novedad:"+ numNovedad++);
            
            registro = (SingleRowWebService)itLista.next();

            String modulo = "";
           
            String data = "";

            Boolean indmodulo = false;
           
            Boolean indData = false;

            // Recorrer columnas
            Iterator it = registro.getFields().iterator();
            while (it.hasNext()) {
                ElementNSImpl dataCol = (ElementNSImpl)it.next();
                Node nodo = dataCol.getFirstChild();

                while (nodo != null) {

                    String x = nodo.getNodeName().toUpperCase();
                    String y = nodo.getFirstChild().getNodeValue().toUpperCase();
                    
                    if (nodo.getNodeName().toUpperCase().equals("NAME") &&
                        nodo.getFirstChild().getNodeValue().toUpperCase().equals("CANTIDAD_REGISTROS")) {
                        indmodulo = false;
                        indData = true;

                    }

                    if (nodo.getNodeName().toUpperCase().equals("NAME") &&
                        nodo.getFirstChild().getNodeValue().toUpperCase().equals("MODULO")) {
                        indmodulo = true;
                       
                        indData = false;
                    }

                    if (nodo.getNodeName().toUpperCase().equals("VALUE") &&
                        indmodulo)
                        modulo = nodo.getFirstChild().getNodeValue();

                  
                    if (nodo.getNodeName().toUpperCase().equals("VALUE") && indData)
                        data = nodo.getFirstChild().getNodeValue();

                    nodo = nodo.getNextSibling();
                }

            }

            NovDatosNodos nov =
                new NovDatosNodos(modulo,  data);

            LOGGER.info("Novedad obtenidad: " + nov.getModulo() + ":" 
                               + nov.getData());
            
            listaDatosNodos.add(nov);

        }

        LOGGER.info("Total de novedades a procesar: " +
                    listaDatosNodos.size());
        
        return listaDatosNodos;

    }
    
   
    public static void aplicarReporteDatos(Autoridad autoridad)  {
        
        try {
           // ArrayList<NovDatosNodos> lNovedadNodos = new  ArrayList<NovDatosNodos>();
           ArrayList<NovDatosNodos> lNovedadNodos = obtenerDatosReporteNodos(autoridad);
              
          
            eliminarDatosReporteNodos(autoridad);
           // actualizaReporteNodos(autoridad, lNovedadNodos);
            
          //recorrer el  ArrayList<NovDatosNodos>
            if (lNovedadNodos != null) {
               Iterator<NovDatosNodos> it = lNovedadNodos.iterator();
                while (it.hasNext()) {
                    NovDatosNodos dato = it.next();
                    System.out.println("Dato:"+dato.getModulo());
                    //OBTENER DATO IDEAM 
                    actualizaReporteNodos(autoridad, dato);
                 //   obtenerDatosReporteIDEAM(autoridad);
                } 
            }
           
           
           
            
        } catch (Exception e) {
            LOGGER.info("Se presento error en autoridad: " + autoridad.getNombre());
            LOGGER.info("Rango de fechas queda pendiente para proxima ejecucion " + autoridad.getFechaInicio() + "-" + autoridad.getFechaFin());
            LOGGER.info("Mensaje error: " + e.getMessage());
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            LOGGER.info("Mensaje Trace:" + sw.toString() + ":");
        }
    }

    private static void  eliminarDatosReporteNodos(Autoridad autoridad) throws ClassNotFoundException,
                                                                              SQLException {
        LOGGER.info("Va a eliminar los datos del nodo  autoridad:" + autoridad.getNombre() );

        Statement stmt = null;
        Connection conn = null;
        
        Class.forName(propiedades.getProperty("DriverNameBD"));
        
        
        conn = DriverManager.getConnection(
                propiedades.getProperty("UrlBD"), propiedades.getProperty("UsuarioBD"),
                propiedades.getProperty("ClaveUsuarioBD"));
        
        String queryDel = "DELETE  FROM SIRH_DATOS_NODOS WHERE ID_AUTORIDAD =" + autoridad.getCodigo() ;

        stmt = conn.createStatement();

        LOGGER.info("Va a eliminar SQL:" + queryDel);

        stmt.execute(queryDel);

        LOGGER.info("Delete exitoso");

        conn.close();
        
        
    }

    
    private static void  actualizaReporteNodos(Autoridad autoridad,   NovDatosNodos  NovNodos) throws ClassNotFoundException,
                                                                              SQLException {
        LOGGER.info("Va a actualizar autoridad:" + autoridad.getNombre() );

        Statement stmt = null;
        Connection conn = null;
        
        Class.forName(propiedades.getProperty("DriverNameBD"));
        
        
        conn = DriverManager.getConnection(
                propiedades.getProperty("UrlBD"), propiedades.getProperty("UsuarioBD"),
                propiedades.getProperty("ClaveUsuarioBD"));
        
       
        String contador =  "   SELECT max(id) as idd FROM SIRH_DATOS_NODOS";
        stmt = conn.createStatement();
        
        ResultSet rs = stmt.executeQuery(contador);
        int cont= 0;
        while (rs.next()) {
            cont = rs.getInt("idd")+1;
        }
      
       

        String datoIdeam=   obtenerDatosReporteIDEAM(autoridad,  NovNodos.getModulo());

        String query =  "Insert INTO SIRH_DATOS_NODOS (ID, MODULO, CANTIDAD_REGISTROS, ID_AUTORIDAD, AUTORIDAD, CANT_REGISTRO_IDEAM, FECHA_REGISTRO)" +
            "   Values     (" + cont+ ", '"+ NovNodos.getModulo()+"'," + NovNodos.getData()+" ,"+ autoridad.getCodigo()+"," 
            + " '"+ autoridad.getNombre()+"',"+datoIdeam+",  sysDate)";
    
         
        
        stmt = conn.createStatement();

        LOGGER.info(" insert  SQL:" + query);

        stmt.execute(query);

        LOGGER.info("Actualizacion exitosa");

        conn.commit();
        conn.close();
        
        
    }


   public static  String  obtenerDatosReporteIDEAM(Autoridad autoridad, String modulo) throws ClassNotFoundException,
                                                                              SQLException {
        LOGGER.info("Va a actualizar autoridad:" + autoridad.getNombre() );

        Statement stmt = null;
        Connection conn = null;
    ArrayList<NovDatosNodos> listaDatosIDEAM =
        new ArrayList<NovDatosNodos>();
        Class.forName(propiedades.getProperty("DriverNameBD"));
        
      
        conn = DriverManager.getConnection(
                propiedades.getProperty("UrlBD"), propiedades.getProperty("UsuarioBD"),
                propiedades.getProperty("ClaveUsuarioBD"));
        String qdatosIDEAM ="";
        
        
        if (modulo.equals("FUENTES")){
             qdatosIDEAM =  " Select count(*) AS CANTIDAD_REGISTROS,  'FUENTES' as modulo from fntt_fuente where cod_autoridad= " + autoridad.getCodigo();
        }else if (modulo.equals("USUARIOS")){
             qdatosIDEAM =  " select count(*) AS CANTIDAD_REGISTROS ,  'USUARIOS' as modulo from rurt_usuarios_agua uu WHERE uu.id_autoridad=" + autoridad.getCodigo();
        }else if (modulo.equals("PREDIOS")){
             qdatosIDEAM =  " select count(*) AS CANTIDAD_REGISTROS ,  'PREDIOS' as modulo  from  rurt_predios pp where id_usuario in (   select id  from rurt_usuarios_agua uu WHERE uu.id_autoridad=" + autoridad.getCodigo()+")" ;
        }else if (modulo.equals("CONCESIONES")){      
             qdatosIDEAM =   "select count(*) AS CANTIDAD_REGISTROS ,  'CONCESIONES' as modulo from rurt_concesiones cc where cc.id_predio in (select id from  rurt_predios pp  where id_usuario in (   select id from rurt_usuarios_agua uu WHERE uu.id_autoridad=" + autoridad.getCodigo()+"))" ;
        }else if (modulo.equals("CAPTACIONES")){      
             qdatosIDEAM =   "select count(*) AS CANTIDAD_REGISTROS ,  'CAPTACIONES' as modulo  from rurt_captacion where id_concesion in (select id from rurt_concesiones cc  where cc.id_predio in (select id from  rurt_predios pp where id_usuario in ( select id from rurt_usuarios_agua uu WHERE uu.id_autoridad=" + autoridad.getCodigo()+" )))" ;
        }else if (modulo.equals("PERMISOS DE VERTIMIENTO")){      
             qdatosIDEAM =  "select count(*) AS CANTIDAD_REGISTROS,  'PERMISOS DE VERTIMIENTO' as modulo from rurt_permisos_vertimientos cc  where cc.id_predio in (select id from  rurt_predios pp  where id_usuario in (   select id  from rurt_usuarios_agua uu WHERE uu.id_autoridad=" + autoridad.getCodigo()+"  )   )" ;
        }else if (modulo.equals("VERTIMIENTOS")){      
             qdatosIDEAM =  "select count(*) AS CANTIDAD_REGISTROS ,  'VERTIMIENTOS' as modulo from rurt_punto_vertimiento where id_permiso_vertimiento in (select id from rurt_permisos_vertimientos cc   where cc.id_predio in (select id from  rurt_predios pp  where id_usuario in (   select id from rurt_usuarios_agua uu WHERE uu.id_autoridad=" + autoridad.getCodigo()+"    )  )  )" ;
        }    
            
            
       
        
        LOGGER.info("Va a consultar SQL:" + qdatosIDEAM);

        stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(qdatosIDEAM);
        String  data=null ;

        while (rs.next()) {
              data = rs.getString("CANTIDAD_REGISTROS");
              LOGGER.info("Autoridad a IDEAM:" + data  );
        }

        LOGGER.info("Actualizacion exitosa");

        conn.commit();
        conn.close();
        
        return data;
    }
   
   
   
    
    private static void  actualizaReporteIDEAM(Autoridad autoridad,   NovDatosNodos  datosIDEAM) throws ClassNotFoundException,
                                                                              SQLException {
        LOGGER.info("Va a actualizar autoridad:" + autoridad.getNombre() );

        Statement stmt = null;
        Connection conn = null;
        
        Class.forName(propiedades.getProperty("DriverNameBD"));
        
        
        conn = DriverManager.getConnection(
                propiedades.getProperty("UrlBD"), propiedades.getProperty("UsuarioBD"),
                propiedades.getProperty("ClaveUsuarioBD"));
        
       
        String query =  " UPDATE SIRH_DATOS_NODOS SET CANT_REGISTRO_IDEAM=" + datosIDEAM.getData()+
        " WHERE UPPER(modulo) LIKE '%" + datosIDEAM.getModulo().toUpperCase() + "%'" + 
       " AND ID_AUTORIDAD= " + autoridad.getCodigo();
    
         
        
        stmt = conn.createStatement();

        LOGGER.info(" insert  SQL:" + query);

        stmt.execute(query);

        LOGGER.info("Actualizacion exitosa");

        conn.commit();
        conn.close();
        
        
    }

   
   
   
   
}
