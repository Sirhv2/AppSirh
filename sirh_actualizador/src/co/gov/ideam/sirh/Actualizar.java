package co.gov.ideam.sirh;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Actualizar {
    private static String rutaConfiguracion = "./SirhActualizar.config";
    private static Properties propiedades = null;

    public Actualizar() {
    }

    public static void main(String[] args) {
        try {
            
            configurarLog();
            
            LOGGER.info("Paso 00. Inicia proceso actualizar");
            
            if (args.length >0)
                for (int i=0; i<args.length; i++)
                    LOGGER.info("Argumento " + i + ":" + args[i]);
            
            LOGGER.info("Paso 01. Carga configuracion");
            cargarConfiguracion();
            
            LOGGER.info("Paso 02. Obtener conexion BD");
            Connection conn = obtenerConexionBD();
            
            if (args[0].equals("actualizarIca"))
                actualizarIca(conn);
            
            if (args[0].equals("actualizarFews"))
                actualizarFews(conn);

            if (args[0].equals("actualizarMorfo"))
                actualizarMorfo(conn);
            
            conn.close();
            
            
        } catch (Exception e) {

            LOGGER.info("Error General en sincronizar :" + e.getMessage() +
                        ":");

            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);

            LOGGER.info("Trace:" + sw.toString() + ":");
        }

    }

    private static final Logger LOGGER = Logger.getLogger("SirhActualizar");

    public static void configurarLog() throws IOException {
        Handler consoleHandler = null;
        Handler fileHandler = null;
        // Oobtener fecha del sistema
        Calendar cal1 = Calendar.getInstance();    
        
        // Asignar nombre Log
        String nombreLog = "./SirhActualizar_" +
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

    public static Connection obtenerConexionBD() throws ClassNotFoundException,
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
        
        return conn;

    }

    public static void actualizarIca(Connection conn) throws SQLException {
        
        String query = "select mu.id idMuestra, pm.altitud from calt_muestra mu, calt_punto_monitoreo pm " + 
                "where pm.id = mu.id_punto " + 
                "order by mu.id ";
        
        LOGGER.info("Va a buscar muestras a actualizar:" + query);

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        
        Long cantidadMuestras = 0l;
        while (rs.next()) {
            
            LOGGER.info("Procesa muestra: " + rs.getLong("idMuestra"));
            procesarMuestra(conn, rs.getLong("idMuestra"), rs.getDouble("altitud"));
            
            cantidadMuestras++;
        }

        LOGGER.info("Muestras procesadas: " + cantidadMuestras);

        rs.close();
        stmt.close();
        
    }
    
    public static void procesarMuestra(Connection conn, Long idMuestra, double altitudPunto) throws SQLException {
        
        String query = "select mu.id idMuestra, me.id idMedicion, me.id_parametro, me.valor from calt_muestra mu, calt_medicion me " + 
        "where me.id_muestra = mu.id " +
         "and me.id_muestra = " + idMuestra + " " +
        "order by mu.id ";
        
        LOGGER.info("Va a buscar mediciones muestrar:" + query);

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        
        Long cantidadMedidas = 0l;
        
        int numParReq = 0;
        boolean estaTemperatura = false;
        boolean posibleIca7 = false;

        double temperatura = 0;
        double oxigenoDisuelto = 0;
        double solidosSuspendidos = 0;
        double demandaOxigeno = 0;
        double nitrogenoTotal = 0;
        double conductividadElectrica =0;
        double fosforoTotal = 0;
        double coliformes = 0;

        while (rs.next()) {
            
            LOGGER.info("Procesa medicion: " + rs.getLong("idMedicion"));
            
            if (rs.getInt("id_parametro") == OXIGENO_DISULETO) {
                oxigenoDisuelto = rs.getDouble("valor");
                numParReq++;
            }
                
            if (rs.getInt("id_parametro") == SOLIDOS_SUSPENDIDOS) {
                solidosSuspendidos = rs.getDouble("valor");
                numParReq++;
            }

            if (rs.getInt("id_parametro") == DEMANDA_OXIGENO) {
                demandaOxigeno = rs.getDouble("valor");
                numParReq++;
            }

            if (rs.getInt("id_parametro") == NITROGENO_TOTAL) {
                nitrogenoTotal = rs.getDouble("valor");
                numParReq++;
            }

            if (rs.getInt("id_parametro") == CONDUCTIVIDAD_ELECTRICA) {
                conductividadElectrica = rs.getDouble("valor");
                numParReq++;
            }


            if (rs.getInt("id_parametro") == FOSFORO_TOTAL) {
                fosforoTotal = rs.getDouble("valor");
                numParReq++;
            }

            if (rs.getInt("id_parametro") == COLIFORMES) {
                coliformes = rs.getDouble("valor");
                posibleIca7 = true;
            }

            if (rs.getInt("id_parametro") == TEMPERATURA) {
                temperatura = rs.getDouble("valor");
                estaTemperatura = true;
            }

            cantidadMedidas++;
        }

        LOGGER.info("Medidas procesadas: " + cantidadMedidas);

        // Si no esta temperatura no es posible ICA
        if (!estaTemperatura) {
            LOGGER.info("Muestra sin temperatura");
            rs.close();
            stmt.close();
            return;
        }
        
        // valida cantidade parametros
        if (numParReq < 6) {
            LOGGER.info("Muestra sin parametros completos");
            rs.close();
            stmt.close();
            return;
        }
        
        // calcular valores intermedios
        double q2 = 273.15 + temperatura;
        double p2 = 0.000975-(1.426 * Math.pow(10,-5)*temperatura)+(6.436*Math.pow (10,-8) * Math.pow (temperatura,2));
        double o2 = Math.exp( 11.8571 - (3840.7/q2) - (216961.0 / Math.pow (q2,2)));
        double n2 = 1.0 - (0.02667 * altitudPunto /0.3048/760.0);
        double m2 = Math.exp((-139.3441+(157570.1/q2)-(66423080.0/Math.pow (q2,2) )+(12438000000.0/Math.pow (q2,3))-(862194900000.0/Math.pow (q2,4) )));
        double l2 = m2*n2*(((1-o2/n2)*(1-p2*n2))/((1-o2)*(1-p2)));
        double k2 = (oxigenoDisuelto * 100)/l2;
        
        // calcular valores variables
        double h2 = (k2>100) ? 1-(0.01*k2-1) : 1-(1-0.01*k2);
        double h3 = (solidosSuspendidos<=4.5)? 1: (solidosSuspendidos>=320) ? 0 : (1-(-0.02+0.003*solidosSuspendidos));
        double h4 = (demandaOxigeno<=20) ? 0.91 : (20<demandaOxigeno && demandaOxigeno<=25) ? 0.71 : ( 25<demandaOxigeno && demandaOxigeno<=40) ? 0.51 : (40<demandaOxigeno && demandaOxigeno<=80) ? 0.26 : (demandaOxigeno>80) ? 0.125: 0;
        double h5 = ((nitrogenoTotal/fosforoTotal) >= 15 )  ? 0.8: ( ((nitrogenoTotal/fosforoTotal) > 10) && ((nitrogenoTotal/fosforoTotal)< 15)) ? 0.6 : ( ((nitrogenoTotal/fosforoTotal) > 5) &&  ((nitrogenoTotal/fosforoTotal) <=10 )) ? 0.35 : ( (nitrogenoTotal/fosforoTotal)<=5 ) ? 0.15 : 0;
        double h7 = (1- Math.pow (10, (-3.26+ 1.34* Math.log10(conductividadElectrica) ))<0)? 0 : 1-Math.pow(10,(-3.26+1.34*Math.log10(conductividadElectrica) ));                                                                                                                                                                                                                                 

        // calcular ica                                                                                                                                             
        double ica = ( h2 * 0.17 ) + (h3 * 0.17) + (h4 * 0.17) + (h5* 0.17) + (h7 * 0.17) + 0.15;
        
        if (posibleIca7) {
            double h8 = (coliformes < 50) ? 0.98 : (50<=coliformes && coliformes <1600) ? 0.98* Math.exp((coliformes-50)*-0.0009917754) : (coliformes >=1600) ? 0.1 : 0;
            ica = ( h2 * 0.16 ) + (h3 * 0.14) + (h4 * 0.14) + (h5* 0.14) + (h7 * 0.14) + 0.14  + (h8 * 0.14);
        }
                         
        // actualizar ica
        LOGGER.info("Valor ICA muestra:" + idMuestra + "=" + ica);
        guardarIca(conn,idMuestra, ica);
        
        rs.close();
        stmt.close();
    }

    public static void guardarIca(Connection conn, Long idMuestra, double ica) throws SQLException {
        
        String query = "update calt_muestra mu " + 
        "set mu.ica = " + ica +
         "where mu.id = " + idMuestra + " " ;
        
        LOGGER.info("Va a actualizar muestrar:" + query);

        Statement stmt = conn.createStatement();
        stmt.executeUpdate(query);
        
        stmt.close();
        
        
    }

    public static void actualizarFews(Connection conn) throws SQLException,
                                                             FileNotFoundException,
                                                             IOException {
        
        LOGGER.info("Va a procesar archivo" + propiedades.getProperty("archivoFews"));

        FileInputStream fstream = new FileInputStream(propiedades.getProperty("archivoFews"));
        // Creamos el objeto de entrada
        DataInputStream entrada = new DataInputStream(fstream);
        // Creamos el Buffer de Lectura
        BufferedReader buffer = new BufferedReader(new InputStreamReader(entrada));
        String strLinea;
        
        // Leer el archivo linea por linea
        int numLinea = 0;
        String urlFews = "";
        
        while ((strLinea = buffer.readLine()) != null)   {
            numLinea++;
            
            if (numLinea == 1) {
                urlFews = strLinea;
                LOGGER.info("urlFews:" + urlFews);        
            }
            
            if (numLinea >1 ) {
                String url = urlFews.replace("<idEstacion>",strLinea);
                actualizarFews(conn, strLinea,url);
            }
        }
        // Cerramos el archivo
        entrada.close();

        LOGGER.info("Actualizacion FEWS ok");
    }

    public static void actualizarFews(Connection conn, String idEstacion, String urlFews) throws SQLException {
        
        String query = "update part_ref_oferta_estac_subzonas p " + 
        "set p.urlFews = '" + urlFews + "'" +
         "where p.id_estacion = " + idEstacion + " " ;
        
        LOGGER.info("Va a actualizar estacion:" + query);

        Statement stmt = conn.createStatement();
        stmt.executeUpdate(query);
        
        stmt.close();
        
    }

    public static void actualizarMorfo(Connection conn) throws SQLException,
                                                             FileNotFoundException,
                                                             IOException {
        
        LOGGER.info("Va a procesar archivo" + propiedades.getProperty("archivoMorfo"));

        FileInputStream fstream = new FileInputStream(propiedades.getProperty("archivoMorfo"));
        // Creamos el objeto de entrada
        DataInputStream entrada = new DataInputStream(fstream);
        // Creamos el Buffer de Lectura
        BufferedReader buffer = new BufferedReader(new InputStreamReader(entrada));
        String strLinea;
        
        // Leer el archivo linea por linea
        int numLinea = 0;
        String urlFews = "";
        
        while ((strLinea = buffer.readLine()) != null)   {
            numLinea++;
            
            if (numLinea == 1) {
                LOGGER.info("encabezado:" + strLinea);        
            }
            
            if (numLinea >1 ) {
                strLinea = strLinea.replaceAll(",", ".");
                String[] campos = strLinea.split("\t");
                actualizarMorfo(conn, campos);
            }
        }
        // Cerramos el archivo
        entrada.close();

        LOGGER.info("Actualizacion MORFO ok. Registros:" + numLinea);
    }

    public static void actualizarMorfo(Connection conn, String[] campos) throws SQLException {
        
        String query = "update part_ref_oferta_estac_subzonas p " + 
                       "set p.AREACUENCA = " + campos[4] + "," + 
        "p.PERIMETRO = " + campos[5] + "," + 
        "p.PENDIENTEMEDIACUENCA = " + campos[6] + "," + 
        "p.ELEVACIONMEDIA = " + campos[7] + "," + 
        "p.DESNIVEL = " + campos[8] + "," + 
        "p.COMPACIDAD = " + campos[9] + "," + 
        "p.DENSIDADDRENEJE = " + campos[10] + "," + 
        "p.DENSIDADCORRIENTE = " + campos[11] + "," + 
        "p.DEFICITAGUA = " + campos[12] + "," + 
        "p.PENDIENTEMEDIACAUCE = " + campos[13] + "," + 
        "p.LONGITUDCAUCE = " + campos[14] + "," + 
        "p.DESNIVELCAUCE = " + campos[15] + ",";
        
        if (campos[16].equals("0"))
            query += "p.BOSQUE = null";
        else
            query +=  "p.BOSQUE = " + campos[16] ;
        
         query += "  where p.id_estacion = " + campos[0] + " " ;
        
        LOGGER.info("Va a actualizar estacion:" + query);

        Statement stmt = conn.createStatement();
        stmt.executeUpdate(query);
        
        stmt.close();
        
    }
    
    // PARAMETROS ICA
    public static final Integer OXIGENO_DISULETO = 322;
    public static final Integer SOLIDOS_SUSPENDIDOS = 325;
    public static final Integer DEMANDA_OXIGENO = 323;
    public static final Integer NITROGENO_TOTAL = 435;
    public static final Integer CONDUCTIVIDAD_ELECTRICA = 312;
    public static final Integer FOSFORO_TOTAL = 321;
    public static final Integer COLIFORMES = 360;
    public static final Integer TEMPERATURA = 566;

}
