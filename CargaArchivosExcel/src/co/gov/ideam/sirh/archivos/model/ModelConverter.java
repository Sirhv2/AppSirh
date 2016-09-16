package co.gov.ideam.sirh.archivos.model;

import co.gov.ideam.sirh.datos.referencia.business.DatosReferenciaEJB;
import co.gov.ideam.sirh.fuentes.business.FuentesEJB;
import co.gov.ideam.sirh.parametros.business.ParametrosEJB;
import co.gov.ideam.sirh.usuarios.agua.business.UsuariosAguaEJB;
import co.gov.ideam.sirh.util.IdeamException;

import java.io.Serializable;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Calendar;

import java.util.Date;

import java.util.GregorianCalendar;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * Define metodos genericos par las clases encargadas de convertir
 * los datos que se encapsulan en RowTO en objetos correspondientes
 * a las entidades del sistema
 * @author Jairo Montealegre
 */
public abstract class ModelConverter implements Serializable{
    
    protected RowTO row;
    
    public ModelConverter() {
        super();
    }
    
    public void setRow(RowTO row){
        this.row = row;
    }
    /**
     * Carga en el objeto model recibido por parametro los datos que vienen en 
     * RowTO, objeto se debe ser sido astablecido antes de ejecutar este metodos
     * @param model
     * @return
     * @throws IdeamException Cuando existen errores de conversion de datos o 
     * validaciones de negocio propias de la conversion de los datos a model
     */
    public abstract Object getModel(Object model)throws IdeamException;
    /**
     * Retorna la fecha convertida segun el patron d/M/y
     * @param fecha
     * @return
     * @throws IdeamException
     */
    protected Calendar getDate(String fecha)throws IdeamException{
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("d/M/y");
            Date date = sdf.parse(fecha);
            Calendar cal = GregorianCalendar.getInstance();
            cal.setTime(date);
            return cal;
        }catch(ParseException e){
            System.out.println("Error Convirtiendo fecha " + e.getMessage() + " Valor " + fecha);
            return null;
        }
    }
    protected Calendar getDateDDMMAAAA(String fecha)throws IdeamException{
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyyy");
            Date date = sdf.parse(fecha);
            Calendar cal = GregorianCalendar.getInstance();
            cal.setTime(date);
            return cal;
        }catch(ParseException e){
            System.out.println("Error Convirtiendo fecha " + e.getMessage() + " Valor " + fecha);
            return null;
        }
    }
    
    /**
     *  Retorna una instancia al EJB de Parametros
     * @return
     * @throws IdeamException
     */
    protected ParametrosEJB getParametrosService()throws IdeamException{
        try {
            Context ctx = new InitialContext();
            Object obj = ctx.lookup("java:app/Ideam-Ejb/ParametrosEJB");        
            return (ParametrosEJB)obj;
        } catch (NamingException e) {
            throw new IdeamException("Imposible ubicar el EJB de Parametros " + 
                                     e.getMessage());
        }        
    }  
    
    /**
     *  Retorna una instancia al EJB de Fuentes
     * @return
     * @throws IdeamException
     */
    protected FuentesEJB getFuentesService()throws IdeamException{
        try {
            Context ctx = new InitialContext();
            Object obj = ctx.lookup("java:app/Ideam-Ejb/FuentesEJB");        
            return (FuentesEJB)obj;
        } catch (NamingException e) {
            throw new IdeamException("ModelConverter Imposible ubicar el EJB de Fuentes " + 
                                     e.getMessage());
        }        
    } 
    
    /**
     *  Retorna una instancia al EJB de Usuarios
     * @return
     * @throws IdeamException
     */
    protected UsuariosAguaEJB getUsuariosAguaService()throws IdeamException{
        try {
            Context ctx = new InitialContext();
            Object obj = ctx.lookup("java:app/Ideam-Ejb/UsuariosAgua");        
            return (UsuariosAguaEJB)obj;
        } catch (NamingException e) {
            throw new IdeamException("Imposible ubicar el EJB de Usuarios del Agua " + 
                                     e.getMessage());
        }        
    } 
    
    /**
     *  Retorna una instancia al EJB de Parametros
     * @return
     * @throws IdeamException
     */
    protected DatosReferenciaEJB getDatosReferenciaService()throws IdeamException{
        try {
            Context ctx = new InitialContext();
            Object obj = ctx.lookup("java:app/Ideam-Ejb/DatosReferenciaEJB");        
            return (DatosReferenciaEJB)obj;
        } catch (NamingException e) {
            throw new IdeamException("Imposible ubicar el EJB de Parametros " + 
                                     e.getMessage());
        }        
    }  
}
