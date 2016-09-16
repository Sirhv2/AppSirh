package co.gov.ideam.sirh.webservices.businessl;

// HUGO 20141030
import co.gov.ideam.sirh.util.IdeamException;

import co.gov.ideam.sirh.webservices.model.DataWebService;
import co.gov.ideam.sirh.webservices.model.Sentencia;

import co.gov.ideam.sirh.webservices.model.SingleRowWebService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import java.sql.Types;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import java.util.Map;

import javax.annotation.Resource;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import javax.naming.Context;
import javax.naming.InitialContext;

import javax.naming.NamingException;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import javax.sql.DataSource;

import javax.xml.ws.WebServiceContext;

import javax.xml.ws.handler.MessageContext;

//import oracle.xml.parser.v2.XMLElement;


@Stateless(name = "ExportaDatosSirhV2WS", mappedName = "Sirh-ExportaDatosWS")
@Remote
@WebService(name = "ExportaDatosSirh", serviceName = "ExportSirh", portName = "ExportaDatosSirhPort")
public class ExportaDatosWSBean implements ExportaDatosWS {
    
    @PersistenceContext (unitName="SirhPU")
    private EntityManager em;    
    @Resource private WebServiceContext wsctx;
    /***
     * Se elimina verificacion de seguridad por solicitud verbal del Lider Tecnico,
     * Pilar Galindo
     * */
    //private static final String username = "";
    //private static final String password = "";
    
    public ExportaDatosWSBean() {
    }
    
    private void setParameter(Object parameter, PreparedStatement statement, int pos)throws SQLException{
        if(parameter instanceof Long){
            if (parameter!=null){
                statement.setLong(pos, (Long)parameter);
            }else{
                statement.setNull(pos, Types.NUMERIC);
            }
        }
        if(parameter instanceof Integer){
            if (parameter!=null){
                statement.setInt(pos, (Integer)parameter);
            }else{
                statement.setNull(pos, Types.INTEGER);
            }
        }
        if(parameter instanceof String){
            if (parameter!=null){
                statement.setString(pos, (String)parameter);
            }else{
                statement.setNull(pos, Types.VARCHAR);
            }
        }        
    }
    private int verParametro(Object parameter)throws SQLException{
      
        System.out.println("Parameter get Class:"+parameter.getClass().toString());
        int valor=0;
        
   /*     
        if(parameter instanceof oracle.xml.parser.v2.XMLElement){
            if (parameter!=null){
                XMLElement l=(XMLElement)parameter;
                valor=Integer.parseInt(l.getTextContent());
            }
        }
        */
        if (parameter instanceof Integer)
            valor = (Integer) parameter;

        System.out.println("Valor parametro:"+valor);
        
        return valor;
    }
    /*
    private void validate()throws IdeamException{
        MessageContext mctx = wsctx.getMessageContext();        
        Map http_headers = (Map) mctx.get(MessageContext.HTTP_REQUEST_HEADERS);
        List userList = (List) http_headers.get("username");
        List passList = (List) http_headers.get("password");
 
        String username = "";
        String password = "";
 
        if(userList!=null){
            //get username
            username = userList.get(0).toString();
        }
 
        if(passList!=null){
            //get password
            password = passList.get(0).toString();            
        }
            
        if (!username.equals(this.username) || !password.equals(this.password)){
            throw new IdeamException("Usuario  / Password no valido");
        }        
    }*/

/**
     *Este método debe ser utilizado cuando se ejecute una consulta SQL que retorna únicamente un dato. Este método retorna un objeto de tipo DataWebService.
     * @param sentenciaId
     * @param parameters
     * @return DataWebService
     * @throws IdeamException
     */
    @WebMethod(operationName = "getSingleData")
    @WebResult(name = "singleData")
    public DataWebService getSingleData(Long sentenciaId, Object... parameters)throws IdeamException{        
        //this.validate();

        String sentencia = "";
        DataWebService data = null;
        try{
            Query query = em.createNamedQuery("Sentencia.find");
            query.setParameter("codigo", sentenciaId);
            Sentencia st = (Sentencia)query.getSingleResult();
            if(st!=null && st.getWs_sentencia()!=null){
                sentencia = st.getWs_sentencia();                
            }else{
                throw new IdeamException("No existe una sentencia con codigo " + sentenciaId);
            }
        }catch(NoResultException e){
            throw new IdeamException("No existe una sentencia con codigo " + sentenciaId);
        }
        
        /*Session session = (Session) em.getDelegate(); 
        SessionFactoryImplementor sfi = (SessionFactoryImplementor) session.getSessionFactory(); 
        ConnectionProvider cp = sfi.getConnectionProvider(); */
        
        
        java.sql.Connection connection=null;

        PreparedStatement statement = null;
        ResultSet query = null;
        ResultSetMetaData metaData = null;
        int totalColumnas = 0;
        
        Connection con = null;
        
        try {
            Context c = new InitialContext();
            DataSource dataSource =(DataSource)c.lookup("java:jboss/datasources/sirhDS");

            connection = dataSource.getConnection();
            
            // connection = cp.getConnection();
            statement = connection.prepareStatement(sentencia);
            if(parameters!=null && parameters.length>0){
                    int j=0;
                    for(int i=0; i<parameters.length; i++){
                        Object value = parameters[i];
                        j=i+1;
                        this.setParameter(verParametro(parameters[i]), statement,j);
                    }
            }
            query = statement.executeQuery();
            metaData = query.getMetaData();
            totalColumnas = metaData.getColumnCount();

            if (totalColumnas== 0){
                throw new IdeamException("No existen columnas para mostrar");
            }
            
            while(query.next()){                
                Object value = query.getObject(1);
                String nombreColumna = metaData.getColumnName(1);                    
                if (value!=null){
                    data = new DataWebService(nombreColumna, value);                                                                
                }else{
                    data = new DataWebService(nombreColumna, "");                                                                
                }
            }            
        }catch (NamingException e) {
           throw new IdeamException(e);
        }
        
        catch(SQLException e){
            throw new IdeamException("Error ejecutando la siguiente sentencia :: " + 
                                     sentencia + " :: Error => " + 
                                     e.getMessage());
        }finally{
            try{                
                if(query != null){
                    query.close();
                }
                if(statement != null){
                    statement.close();
                }
                if(connection != null){
                    connection.close();
                }
            }catch(SQLException e){
                throw new IdeamException("Error ejecutando la siguiente sentencia :: " + 
                                         sentencia + " :: Error => " + 
                                         e.getMessage());                
            }
        }
        return data;        
    }
    /*
    public DataWebService getSingleData(Long sentenciaId)throws IdeamException{
        this.validate();
        String sentencia = "";
        DataWebService data = null;
        try{
            Query query = em.createNamedQuery("Sentencia.find");
            query.setParameter("codigo", sentenciaId);
            Sentencia st = (Sentencia)query.getSingleResult();
            if(st!=null && st.getWs_sentencia()!=null){
                sentencia = st.getWs_sentencia();
            }else{
                throw new IdeamException("No existe una sentencia con codigo " + sentenciaId);
            }
        }catch(NoResultException e){
            throw new IdeamException("No existe una sentencia con codigo " + sentenciaId);
        }

        Session session = (Session) em.getDelegate();
        SessionFactoryImplementor sfi = (SessionFactoryImplementor) session.getSessionFactory();
        ConnectionProvider cp = sfi.getConnectionProvider();
        java.sql.Connection connection=null;

        PreparedStatement statement = null;
        ResultSet query = null;
        ResultSetMetaData metaData = null;
        int totalColumnas = 0;
        try {
            connection = cp.getConnection();
            statement = connection.prepareStatement(sentencia);
            query = statement.executeQuery();
            metaData = query.getMetaData();
            totalColumnas = metaData.getColumnCount();

            if (totalColumnas== 0){
                throw new IdeamException("No existen columnas para mostrar");
            }

            while(query.next()){
                Object value = query.getObject(1);
                String nombreColumna = metaData.getColumnName(1);
                if (value!=null){
                    data = new DataWebService(nombreColumna, value);
                }else{
                    data = new DataWebService(nombreColumna, "");
                }
            }
        }catch(SQLException e){
            throw new IdeamException("Error ejecutando la siguiente sentencia :: " +
                                     sentencia + " :: Error => " +
                                     e.getMessage());
        }finally{
            try{
                if(query != null){
                    query.close();
                }
                if(statement != null){
                    statement.close();
                }
                if(connection != null){
                    connection.close();
                }
            }catch(SQLException e){
                throw new IdeamException("Error ejecutando la siguiente sentencia :: " +
                                         sentencia + " :: Error => " +
                                         e.getMessage());
            }
        }
        return data;
    }
*/

    @WebMethod(operationName = "getOneRow")
    @WebResult(name = "singleRow")
    public SingleRowWebService getOneRow(Long sentenciaId, Object... parameters)throws IdeamException{
        //this.validate();
        String sentencia = "";
        SingleRowWebService row = new SingleRowWebService();
        try{
            Query query = em.createNamedQuery("Sentencia.find");
            query.setParameter("codigo", sentenciaId);
            Sentencia st = (Sentencia)query.getSingleResult();
            if(st!=null && st.getWs_sentencia()!=null){
                sentencia = st.getWs_sentencia();
            }else{
                throw new IdeamException("No existe una sentencia con codigo " + sentenciaId);
            }
        }catch(NoResultException e){
            throw new IdeamException("No existe una sentencia con codigo " + sentenciaId);
        }
        
        /*Session session = (Session) em.getDelegate(); 
        SessionFactoryImplementor sfi = (SessionFactoryImplementor) session.getSessionFactory(); 
        ConnectionProvider cp = sfi.getConnectionProvider(); */
        
        java.sql.Connection connection=null;

        Connection con = null;
        
        PreparedStatement statement = null;
        ResultSet query = null;
        ResultSetMetaData metaData = null;
        int totalColumnas = 0;
        try {
            Context c = new InitialContext();
            DataSource dataSource =(DataSource)c.lookup("java:jboss/datasources/sirhDS");

            connection = dataSource.getConnection();
            
            //connection = cp.getConnection();
            statement = connection.prepareStatement(sentencia);
          
            if(parameters!=null && parameters.length>0){
                int j=0;
                for(int i=0; i<parameters.length; i++){
                    Object value = parameters[i];
                    j=i+1;
                    System.out.println("Parametro del webservice:"+verParametro(parameters[i])+" POS:"+j);
                    this.setParameter(verParametro(parameters[i]), statement,j);
                }
            }       
            System.out.println("Query del Statement:"+statement.toString());         
            query = statement.executeQuery();
            metaData = query.getMetaData();
            totalColumnas = metaData.getColumnCount();

            if (totalColumnas== 0){
                throw new IdeamException("No existen columnas para mostrar");
            }
            
            if(query.next()){
                for (int i=1; i<=totalColumnas; i++){
                    Object value = query.getObject(i);
                    String nombreColumna = metaData.getColumnName(i);
                    DataWebService map = null;
                    if (value!=null){
                        map = new DataWebService(nombreColumna, value);                                                                
                    }else{
                        map = new DataWebService(nombreColumna, "");                                                                
                    }
                    row.addData(map);
                }                
            }
        }catch (NamingException e) {
           throw new IdeamException(e);
        }
        catch(SQLException e){
            throw new IdeamException("Error ejecutando la siguiente sentencia :: " + 
                                     sentencia + " :: Error => " + 
                                     e.getMessage());
        }finally{
            try{                
                if(query != null){
                    query.close();
                }
                if(statement != null){
                    statement.close();
                }
                if(connection != null){
                    connection.close();
                }
            }catch(SQLException e){
                throw new IdeamException("Error ejecutando la siguiente sentencia :: " + 
                                         sentencia + " :: Error => " + 
                                         e.getMessage());                
            }
        }
        return row;
    }

    @WebMethod(operationName = "getMultipleRows")
    @WebResult(name = "multipleRows")
    public List<SingleRowWebService> getMultipleRows(Long sentenciaId, Object... parameters)throws IdeamException{
        //this.validate();
        String sentencia = "";
        List lista = new ArrayList<SingleRowWebService>();
        try{
            Query query = em.createNamedQuery("Sentencia.find");
            query.setParameter("codigo", sentenciaId);
            Sentencia st = (Sentencia)query.getSingleResult();
            if(st!=null && st.getWs_sentencia()!=null){
                sentencia = st.getWs_sentencia();
            }else{
                throw new IdeamException("No existe una sentencia con codigo " + sentenciaId);
            }
        }catch(NoResultException e){
            throw new IdeamException("No existe una sentencia con codigo " + sentenciaId);
        }
        
        /* HCP 
         
        Session session = (Session) em.getDelegate(); 
        SessionFactoryImplementor sfi = (SessionFactoryImplementor) session.getSessionFactory(); 
        ConnectionProvider cp = sfi.getConnectionProvider(); */

        java.sql.Connection connection=null;

        PreparedStatement statement = null;
        ResultSet query = null;
        ResultSetMetaData metaData = null;
        int totalColumnas = 0;
        Connection con = null;
        
        
        try {
            Context c = new InitialContext();
            DataSource dataSource =(DataSource)c.lookup("java:jboss/datasources/sirhDS");

            connection = dataSource.getConnection();
            // HCP connection = cp.getConnection();
            statement = connection.prepareStatement(sentencia);
            if(parameters!=null && parameters.length>0){
                int j=0;
                for(int i=0; i<parameters.length; i++){
                    Object value = parameters[i];
                    j=i+1;
                    this.setParameter(verParametro(parameters[i]), statement,j);
                }
            }                        
            query = statement.executeQuery();
            metaData = query.getMetaData();
            totalColumnas = metaData.getColumnCount();

            if (totalColumnas== 0){
                throw new IdeamException("No existen columnas para mostrar");
            }
            
            while(query.next()){
                SingleRowWebService row = new SingleRowWebService();
                for (int i=1; i<=totalColumnas; i++){
                    Object value = query.getObject(i);
                    String nombreColumna = metaData.getColumnName(i);
                    DataWebService map = null;
                    if (value!=null){
                        map = new DataWebService(nombreColumna, value);                                                                
                    }else{
                        map = new DataWebService(nombreColumna, "");                                                                
                    }
                    row.addData(map);
                }
                lista.add(row);
            }            
        } catch (NamingException e) {
           throw new IdeamException(e);
        } catch(SQLException e){
            throw new IdeamException("Error ejecutando la siguiente sentencia :: " + 
                                     sentencia + " :: Error => " + 
                                     e.getMessage());
        }finally{
            try{                
                if(query != null){
                    query.close();
                }
                if(statement != null){
                    statement.close();
                }
                if(connection != null){
                    connection.close();
                }
            }catch(SQLException e){
                throw new IdeamException("Error ejecutando la siguiente sentencia :: " + 
                                         sentencia + " :: Error => " + 
                                         e.getMessage());                
            }
        }
        return lista;
    }    

}
