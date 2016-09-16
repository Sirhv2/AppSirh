package co.gov.ideam.sirh.usuarios.agua.model;


import co.gov.ideam.sirh.util.IdeamException;

import java.io.Serializable;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import java.lang.reflect.InvocationTargetException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Enumeration;
import java.util.Properties;

import java.util.UUID;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.dialect.Dialect;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.id.SequenceGenerator;
import org.hibernate.id.TableGenerator;
import org.hibernate.type.Type;
import org.hibernate.util.PropertiesHelper;

public class IdeamSirhGenerator extends SequenceGenerator implements Configurable{
    String autoridadAmbientalId;
    private String codigo;
    
    public IdeamSirhGenerator() {
       super();
        System.out.println("-------------------CONSTRUCTOR IDEAMSIRHGENERATOS------------------------");
        
        
    }
    /**
     *
     * @param type Clase que debe retornar generalmnente org.hibernate.type.LongType
     * @param params
     * @param d
     * @throws MappingException
     */
    public void configure(Type type, Properties params, Dialect d) throws MappingException {
        super.configure(type, params, d);
        System.out.println("HCP -->Configurando.... " + type.getClass().getName());
        /*Enumeration enume = params.keys();
        while(enume.hasMoreElements()){                
            String key = (String)enume.nextElement();
            System.out.println(key + " " + params.getProperty(key));
        }*/
        this.autoridadAmbientalId = params.getProperty("AutoridadAmbientalId");
        this.codigo = params.getProperty("Codigo");
        if (this.autoridadAmbientalId == null) {
            throw new MappingException("Falta el atributo AutoridadAmbientalId");
        }
        if (this.codigo == null) {
            throw new MappingException("Falta el atributo Codigo");
        }
    }

    public synchronized Serializable generate(SessionImplementor sessionImplementor,
                                 Object object) {      
        System.out.println("-------------------entra al generate------------------------");
        try{
            Object codigoActual = this.getValue(object, this.codigo);
            if (codigoActual!=null) {
                if(!(codigoActual instanceof Long)){
                    throw new RuntimeException("Codigo de la entidad debe ser numerico. Encontrado " + 
                                               codigoActual.getClass().getName());
                }
                if (((Long)codigoActual).longValue()>0){
                    System.out.println("::::Retorna codigo actual::::");
                    return (Long)codigoActual;                
                }
            }            
        }catch(Exception e){
            throw new RuntimeException("No es posible determinar el codigo Actual " +
                                       e.getMessage());            
        }
        
        Object codigoAutoridadAmbiental = null;
        try{
            codigoAutoridadAmbiental = this.getValue(object, this.autoridadAmbientalId);
        }catch(Exception e){
            throw new RuntimeException("No es posible determinar el codigo de la autoridad Ambiental " +
                                       e.getMessage());
        }
        if(codigoAutoridadAmbiental==null){
            throw new RuntimeException("No es posible determinar el codigo de la autoridad Ambiental");
        }
        if(!(codigoAutoridadAmbiental instanceof Long)){
            throw new RuntimeException("Codigo de la autoridad Ambiental debe ser numerico. Encontrado " + codigoAutoridadAmbiental.getClass().getName());
        }
        System.out.println("----------------------------------------------codigoAutoridadAmbiental:"+codigoAutoridadAmbiental);
        
        
        //Serializable id = super.generate(sessionImplementor, object) ;        
        //System.out.println("----------------------------------------------Identificador creado:"+((Long)codigoAutoridadAmbiental).longValue() * 1000000 + ((Number)id).longValue());
        
        
        Connection connection = sessionImplementor.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Integer id = null;
        try {
            ps = connection.prepareStatement("select SEQ_FUENTES.nextval from dual");
            rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt("nextval");
                System.out.println("------------------- Encontrado el codigo " + id);
            }            
        } catch (SQLException e) {                
            throw new HibernateException(
                    "Unable to generate Code Sequence");
        } finally{
            try{
                if(rs!=null){
                    rs.close();
                }
                if(ps!=null){
                    ps.close();
                }
                if(connection!=null){
                    connection.close();
                }
            }catch(SQLException e){
                throw new HibernateException(
                        "Error closing Object to generate Code Sequence");
            }
        }
        System.out.println("codigoAutoridadAmbiental " + codigoAutoridadAmbiental);
        return ((Long)codigoAutoridadAmbiental).longValue() * 1000000 + ((Number)id).longValue();
              
    }
    
    private Object getValue(Object obj, String attributeName)throws Exception{        
        String nombreMetodo = "get" + 
                              attributeName.substring(0,1).toUpperCase() + 
                              attributeName.substring(1);
        System.out.println(" getValue nombreMetodo " + nombreMetodo);
        Object value = "";
        
        try {
            value = obj.getClass().getMethod(nombreMetodo, null).invoke(obj, null);
            
        } catch (NoSuchMethodException e) {
            System.out.println(e.getMessage());
            e.printStackTrace(System.out);
            throw new Exception(e);
        } catch (IllegalAccessException e) {
            System.out.println(e.getMessage());
            e.printStackTrace(System.out);            
            throw new Exception(e);
        } catch (InvocationTargetException e) {
            System.out.println(e.getMessage());
            e.printStackTrace(System.out);            
            throw new Exception(e);
        }
        return value;
    }

    public void setAutoridadAmbientalId(String autoridadAmbientalId) {
        this.autoridadAmbientalId = autoridadAmbientalId;
    }

    public String getAutoridadAmbientalId() {
        return autoridadAmbientalId;
    }
}
