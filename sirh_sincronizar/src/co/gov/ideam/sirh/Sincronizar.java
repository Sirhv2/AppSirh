package co.gov.ideam.sirh;

import co.gov.ideam.sirh.webservices.businessl.ExportSirh;
import co.gov.ideam.sirh.webservices.businessl.ExportaDatosSirh;
import co.gov.ideam.sirh.webservices.businessl.IdeamException_Exception;
import co.gov.ideam.sirh.webservices.businessl.types.DataWebService;
import co.gov.ideam.sirh.webservices.businessl.types.SingleRowWebService;

import co.gov.ideam.sirh.webservices.receptor.*;
import co.gov.ideam.sirh.webservices.receptor.types.*;

import com.sun.org.apache.xerces.internal.dom.ElementNSImpl;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import java.math.BigDecimal;

import java.net.MalformedURLException;
import java.net.URL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import java.util.Map;

import java.util.Properties;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;

import java.util.logging.Level;

import javax.xml.ws.BindingProvider;
import javax.xml.ws.WebServiceRef;

import javax.xml.ws.handler.MessageContext;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.logging.Logger;
import java.util.logging.Formatter;
import java.util.logging.SimpleFormatter;

import javax.xml.namespace.QName;


public class Sincronizar {

    private static String rutaConfiguracion = "./SirhSincronizar.config";
    private static Properties propiedades = null;
    
    public Sincronizar() {
    }

    public static void main(String[] args) {


        try {
            
            configurarLog();
            
            LOGGER.info("Paso 00. Inicia sincronizacion novedades");
            
            LOGGER.info("Paso 01. Carga configuracion");
            cargarConfiguracion();

            LOGGER.info("Paso 02. Obtiene Autorizades a sincronizar");
            ArrayList<Autoridad> listaAutoridades =
                new ArrayList<Autoridad>();

            cargarAutoridades(listaAutoridades);
            
            for (Autoridad autoridad : listaAutoridades) {
                
                LOGGER.info("Procesa novedades de la autoridad:" + autoridad.getNombre());
                LOGGER.info("Url autoridad:" + autoridad.getEndPoint());
                LOGGER.info("Fecha Inicio:" + autoridad.getFechaInicio());
                LOGGER.info("Fecha Fin:" + autoridad.getFechaFin());

                aplicarNovedades(autoridad);
                
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
        fileHandler.setFormatter(new SimpleFormatter());

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
            String nomAutoridad = rs.getString("NOMBRE");
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

    public static  ArrayList<NovRecibida> obtenerNovedades(Autoridad autoridad) throws IdeamException_Exception,
                                                                                      MalformedURLException {
        
        LOGGER.info("Se conecta a endPoint:" + autoridad.getEndPoint());
        
        URL url_wsdl = new URL(autoridad.getEndPoint() + "?wsdl");
        
        ExportSirh exportSirh = new ExportSirh(url_wsdl, new QName("http://businessl.webservices.sirh.ideam.gov.co/", "ExportSirh"));
        
        ExportaDatosSirh exportaDatosSirh =
            exportSirh.getExportaDatosSirhPort();

        LOGGER.info("Conexion endPoint ok " + autoridad.getEndPoint());

        ArrayList parametros = new ArrayList();
        parametros.add(autoridad.getFechaInicio());
        parametros.add(autoridad.getFechaFin());

        LOGGER.info("Va a invocar endPoint " + autoridad.getEndPoint());

        List lista = exportaDatosSirh.getMultipleRows(new Long(propiedades.getProperty("idSentenciaNovedades")), parametros);

        LOGGER.info("Invocacion endPoint exitosa " + autoridad.getEndPoint());

        // Obtener lista de novedades recibidas

        ArrayList<NovRecibida> listaNovedades =
            new ArrayList<NovRecibida>();

        SingleRowWebService registro = null;
        Iterator itLista = lista.iterator();

        int numNovedad = 1;
        
        // Recorrer registros
        while (itLista.hasNext()) {
            
            LOGGER.info("Generar novedad:"+ numNovedad++);
            
            registro = (SingleRowWebService)itLista.next();

            String serviceName = "";
            String methodName = "";
            String data = "";

            Boolean indServiceName = false;
            Boolean indMethodName = false;
            Boolean indData = false;

            // Recorrer columnas
            Iterator it = registro.getFields().iterator();
            while (it.hasNext()) {
                ElementNSImpl dataCol = (ElementNSImpl)it.next();
                Node nodo = dataCol.getFirstChild();

                while (nodo != null) {

                    if (nodo.getNodeName().toUpperCase().equals("NAME") &&
                        nodo.getFirstChild().getNodeValue().toUpperCase().equals("SERVICENAME")) {
                        indServiceName = true;
                        indMethodName = false;
                        indData = false;

                    }

                    if (nodo.getNodeName().toUpperCase().equals("NAME") &&
                        nodo.getFirstChild().getNodeValue().toUpperCase().equals("METHODNAME")) {
                        indServiceName = false;
                        indMethodName = true;
                        indData = false;
                    }

                    if (nodo.getNodeName().toUpperCase().equals("NAME") &&
                        nodo.getFirstChild().getNodeValue().toUpperCase().equals("DATA")) {
                        indServiceName = false;
                        indMethodName = false;
                        indData = true;
                    }

                    if (nodo.getNodeName().toUpperCase().equals("VALUE") &&
                        indServiceName)
                        serviceName = nodo.getFirstChild().getNodeValue();

                    if (nodo.getNodeName().toUpperCase().equals("VALUE") &&
                        indMethodName)
                        methodName = nodo.getFirstChild().getNodeValue();

                    if (nodo.getNodeName().toUpperCase().equals("VALUE") && indData)
                        data = nodo.getFirstChild().getNodeValue();

                    nodo = nodo.getNextSibling();
                }

            }

            NovRecibida nov =
                new NovRecibida(serviceName, methodName, data);

            LOGGER.info("Novedad obtenidad: " + nov.getServiceName() + ":" +
                               nov.getMethodName() + ":" + nov.getData());
            
            listaNovedades.add(nov);

        }

        LOGGER.info("Total de novedades a procesar: " +
                    listaNovedades.size());
        
        return listaNovedades;

    }
    
    private static void procesarNovedades(ArrayList<NovRecibida> listaNovedades) throws IdeamException,
                                                                                        Exception {

        LOGGER.info("Se va a conectar endPoint:" + propiedades.getProperty("endPointRegistroRemotoSirh"));

            URL url_wsdl = new URL(propiedades.getProperty("endPointRegistroRemotoSirh")  + "?wsdl");

            Receiver receiver = new Receiver(url_wsdl, new QName("http://sirh.ideam.gov.co/", "Receiver"));
            RegistroRemotoSirh registroRemotoSirh =
                receiver.getRegistroRemotoSirhPort();

            LOGGER.info("Conexion ok endPoint:" + propiedades.getProperty("endPointRegistroRemotoSirh"));

            Map<String, Object> req_ctx =
                ((BindingProvider)registroRemotoSirh).getRequestContext();
            Map<String, List<String>> headers =
                new HashMap<String, List<String>>();
            headers.put("Username",
                        Collections.singletonList(propiedades.getProperty("usuarioWs")));
            headers.put("Password",
                        Collections.singletonList(propiedades.getProperty("claveWs")));
            
            req_ctx.put(MessageContext.HTTP_REQUEST_HEADERS, headers);
        Iterator itNov = listaNovedades.iterator();
        int numNovedad = 1;
        while (itNov.hasNext()) {

            LOGGER.info("Aplica novedad:" + numNovedad);
            //if (numNovedad>=421)
            //    LOGGER.info("llego a  novedad:" + numNovedad);
            
            numNovedad++;
            NovRecibida novRec = (NovRecibida)itNov.next();

            RecibirEvento recibirEvento = new RecibirEvento();
            EventoEntrada evento = new EventoEntrada();

            EventoEntrada.Data data = new EventoEntrada.Data();

            String[] objetos = novRec.getData().split("/>");

            int i = 0;
            boolean ignNovedad = false;
            String ignNameKey = "";
            String ignValueKey = "";
            String ignTypeKey = "";
                       
            while (i < objetos.length) {
                String nameKey = objetos[i++].substring(2);
                String valueKey = objetos[i++].substring(2);
                String typeKey = objetos[i++].substring(2);

                EventoEntrada.Data.Entry entry =
                    new EventoEntrada.Data.Entry();

                entry.setKey(nameKey);

                if (typeKey.contains("String")) {
                    String vlrString = valueKey;
                    entry.setValue(vlrString);
                }

                if (typeKey.contains("Long")) {
                    Long valueLong = new Long(valueKey);
                    entry.setValue(valueLong);
                }
                if (typeKey.contains("Integer")) {
                    Integer valueInt = new Integer(valueKey);
                    entry.setValue(valueInt);
                }

                if (typeKey.contains("Calendar")) {
                    Long valueLong = new Long(valueKey);
                    Calendar fecha = GregorianCalendar.getInstance();
                    fecha.setTimeInMillis(valueLong);
                    entry.setValue(fecha);
                }

                if (typeKey.contains("Timestamp")) {
                    Long valueLong = new Long(valueKey);
                    Timestamp fecha = new Timestamp(valueLong);
                    entry.setValue(fecha);
                }
                if (typeKey.contains("BigDecimal")) {
                    BigDecimal valueBig = new BigDecimal(valueKey);
                    entry.setValue(valueBig);
                }
                
                if (typeKey.contains("Double")) {
                    Double valuedouble = new Double(valueKey);
                    
                    entry.setValue(valuedouble);
                }

                if (!typeKey.contains("String") && 
                    !typeKey.contains("Long") &&
                    !typeKey.contains("Integer") &&
                    !typeKey.contains("Calendar") &&
                    !typeKey.contains("Double") &&
                    !typeKey.contains("BigDecimal") &&                    
                    !typeKey.contains("Timestamp") &&
                    !typeKey.contains("PartZonificListas") &&
                    !typeKey.contains("Concesion") &&
                    !typeKey.contains("FnttTramo") &&
                    !typeKey.contains("FnttFuente") 


                     ) 
                    throw new Exception("Tipo de dato en novedad no implementado. Favor implementar: " + typeKey);
                
                if (entry.getValue() != null)
                    data.getEntry().add(entry);
                else {
                    ignNameKey = nameKey;
                    ignValueKey = valueKey;
                    ignTypeKey = typeKey;
                    ignNovedad = true;
                }
                
            }

            if (!ignNovedad){
                
            evento.setServiceName(novRec.getServiceName());
            evento.setMethodName(novRec.getMethodName());
            evento.setData(data);

            recibirEvento.setArg0(evento);

            LOGGER.info("Va a invocar endPoint recibirEvento" );
            
            //if (numNovedad>=421)
               registroRemotoSirh.recibirEvento(recibirEvento);
            
            LOGGER.info("Invocacion endPoint recibirEvento ok" );
            
            // Esperar 1 seg entre novedades
            Thread.sleep(1000);

            }
            else {
             LOGGER.info("Se ignoro novedad ignNameKey " + ignNameKey );
             LOGGER.info("Se ignoro novedad ignValueKey " + ignValueKey );
             LOGGER.info("Se ignoro novedad ignTypeKey " + ignTypeKey );
            }
        }

    }
    
    private static void  actualizaControlAutoridad(Autoridad autoridad, int numNovedades) throws ClassNotFoundException,
                                                                              SQLException {
        LOGGER.info("Va a actualizar autoridad:" + autoridad.getNombre() );

        Statement stmt = null;
        Connection conn = null;
        
        Class.forName(propiedades.getProperty("DriverNameBD"));
        
        
        conn = DriverManager.getConnection(
                propiedades.getProperty("UrlBD"), propiedades.getProperty("UsuarioBD"),
                propiedades.getProperty("ClaveUsuarioBD"));
        

        String query = "update autoridades a "  +
            "set a.fechaultimasincronizacion = sysdate, a.numNovedades = " + numNovedades + 
            " where a.id = " + autoridad.getCodigo() ;
        
        stmt = conn.createStatement();

        LOGGER.info("Va a actualizar SQL:" + query);

        stmt.execute(query);

        LOGGER.info("Actualizacion exitosa");


    }

    public static void aplicarNovedades(Autoridad autoridad)  {
        
        try {
            ArrayList<NovRecibida> novedades = obtenerNovedades(autoridad);
            procesarNovedades(novedades);
            actualizaControlAutoridad(autoridad, novedades.size());
            
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

}
