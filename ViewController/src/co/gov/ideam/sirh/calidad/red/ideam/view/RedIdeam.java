package co.gov.ideam.sirh.calidad.red.ideam.view;

import co.gov.ideam.sirh.calidad.model.CriteriosBusquedaMuestrasTO;
import co.gov.ideam.sirh.red.ideam.model.SirhvPuntoMonitoreoFq;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.StandarBean;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RedIdeam extends StandarBean{
    
    protected String accion;
    private List datosList;
    protected SirhvPuntoMonitoreoFq puntoSeleccionado;
    
    public void loadParametros() throws IdeamException{    
        
    }
    
 /*   
    public void cargarDatos(String nombre) throws IdeamException{
        this.datosList = new ArrayList();
        RedMonitoreoDelegate rmd = RedMonitoreoDelegate.getInstance();
        
        if(nombre == null){
            this.datosList = rmd.getAllPuntos();
            if (datosList != null) {                             
                  Integer i=1;                  
                  Iterator<SirhvPuntoMonitoreoFq> it = datosList.iterator();
                  while (it.hasNext()) {
                      SirhvPuntoMonitoreoFq reg = it.next();
                      reg.setNum(i);
                      i++;
                  } 
            }      
        }else{
            this.datosList = rmd.getAllPuntos(nombre);                              
        }
    }
*/
    public void cargarDatosCriterios(CriteriosBusquedaMuestrasTO criterios) throws IdeamException{
        this.datosList = new ArrayList();
        this.datosList = null;
        RedMonitoreoDelegate rmd = RedMonitoreoDelegate.getInstance();
        this.datosList = rmd.getAllPuntosCriterios(criterios);                              
       
        if (datosList != null) {                             
              Integer i=1;                  
              Iterator<SirhvPuntoMonitoreoFq> it = datosList.iterator();
              while (it.hasNext()) {
                  SirhvPuntoMonitoreoFq reg = it.next();
                  reg.setNum(i);
                  i++;
              }    
        }
        return;
    }
   
    public String getCriteriosConsultasCalidad(CriteriosBusquedaMuestrasTO criterios) throws IdeamException{
        String sql =  "";    
        boolean whereIncluido = false;
        
        if(criterios.getAutoridad()!=null){
            if(!whereIncluido){
                sql += " where ";
                whereIncluido = true;
            }else{
                sql += " and ";
            }                
            sql += " id_autoridad = " + criterios.getAutoridad().getId();
        }
        if(criterios.getArea()!=null){
            if(!whereIncluido){
                sql += " where ";
                whereIncluido = true;
            }else{
                sql += " and ";
            }          
            sql = sql + " id_area = " + criterios.getArea().getId();
        }
    
        if(criterios.getZona()!=null){
            if(!whereIncluido){
                sql += " where ";
                whereIncluido = true;
            }else{
                sql += " and ";
            }          
            sql = sql + " id_zona = " + criterios.getZona().getId();
        }
        
        if(criterios.getSubZona()!=null){
            if(!whereIncluido){
                sql += " where ";
                whereIncluido = true;
            }else{
                sql += " and ";
            }          
            sql = sql + " id_subzona = " + criterios.getSubZona().getId();       
        }
        
        if(criterios.getDepartamento()!=null){
            if(!whereIncluido){
                sql += " where ";
                whereIncluido = true;
            }else{
                sql += " and ";
            }          
            sql = sql + " id_departamento = " + criterios.getDepartamento().getId();
            
        }
        
        if(criterios.getMunicipio()!=null){
            if(!whereIncluido){
                sql += " where ";
                whereIncluido = true;
            }else{
                sql += " and ";
            }          
            sql = sql + " id_municipio = " + criterios.getMunicipio().getId();
            
        }
        
        if(criterios.getFuente()!=null) {
            if(!whereIncluido){
                sql += " where ";
                whereIncluido = true;
            }else{
                sql += " and ";
            }          
            sql = sql + " id_fuente = " + criterios.getFuente().getId();
        }      
        
        if(criterios.getPtoMonitoreo()!=null) {
            if(!whereIncluido){
                sql += " where ";
                whereIncluido = true;
            }else{
                sql += " and ";
            }  
                sql = sql + " id_punto = " + criterios.getPtoMonitoreo().getId();
        }  
        
        if(criterios.getParametro()!=null) {
            if(!whereIncluido){
                sql += " where ";
                whereIncluido = true;
            }else{
                sql += " and ";
            }          
            sql = sql + " id_parametro = " + criterios.getParametro().getCodigo();
        }                  
        
    //       if(anio==0) {
            if(criterios.getFechaInicial()!=null) {
               if(!whereIncluido){
                   sql += " where ";
                   whereIncluido = true;
               }else{
                   sql += " and ";
               }          
               SimpleDateFormat sdf = new SimpleDateFormat("d/M/y");
               sql = sql + " fecha_ini >= '" + sdf.format( criterios.getFechaInicial().getTime() ) + "'";
               
            }
    
            if(criterios.getFechaFinal()!=null ) {
               if(!whereIncluido){
                   sql += " where ";
                   whereIncluido = true;
               }else{
                   sql += " and ";
               }          
               SimpleDateFormat sdf = new SimpleDateFormat("d/M/y");
               sql = sql + " fecha_fin <= '" + sdf.format( criterios.getFechaFinal().getTime() ) + "'";
            }

    System.out.println("SQL_WHERE CRITERIOSREDIDEAM = " + sql);
             
        return sql;  
        
        }
    
    
    public void setDatosList(List datosList) {
        this.datosList = datosList;
    }

    public List getDatosList() {
        return datosList;
    }


    public void setPuntoSeleccionado(SirhvPuntoMonitoreoFq puntoSeleccionado) {
        this.puntoSeleccionado = puntoSeleccionado;
    }

    public SirhvPuntoMonitoreoFq getPuntoSeleccionado() {
        return puntoSeleccionado;
    }


    public void setAccion(String accion) {
        this.accion = accion;
    }

    public String getAccion() {
        return accion;
    }
}
