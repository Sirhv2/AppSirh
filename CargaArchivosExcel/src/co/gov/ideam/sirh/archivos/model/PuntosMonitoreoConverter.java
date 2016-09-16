package co.gov.ideam.sirh.archivos.model;

import co.gov.ideam.sirh.archivos.model.validator.ListasValidator;
import co.gov.ideam.sirh.archivos.model.validator.Obligatorio;
import co.gov.ideam.sirh.calidad.model.PuntoMonitoreo;
import co.gov.ideam.sirh.parametros.business.ParametrosEJB;
import co.gov.ideam.sirh.parametros.model.Divipola;
import co.gov.ideam.sirh.parametros.model.Lista;
import co.gov.ideam.sirh.parametros.model.PartZonificListas;
import co.gov.ideam.sirh.util.ConstantesCalidad;
import co.gov.ideam.sirh.util.IdeamException;

import java.math.BigDecimal;


public class PuntosMonitoreoConverter extends ModelConverter{
    public PuntosMonitoreoConverter() {
          super();
        }
        
        public Object getModel(Object model)throws IdeamException {
            if(model instanceof PuntoMonitoreo){
                return this.getPuntoMonitoreo( (PuntoMonitoreo)model );
            }       
            return null;
        }
        private PuntoMonitoreo getPuntoMonitoreo(PuntoMonitoreo puntoMonitoreo)throws IdeamException{
            ParametrosEJB parametrosService = this.getParametrosService();
            
            puntoMonitoreo.setNombre( row.getCellValue("Nombre Punto").toString() );
            puntoMonitoreo.setDescripcion_acceso( row.getCellValue("Descripcion de Acceso").toString() );
            
            String tipoPuntoTxt= row.getCellValue("Tipo Punto").toString();
            Lista tipoPunto = parametrosService.getListaByDescripcion(
                               tipoPuntoTxt, ConstantesCalidad.TIPOS_PUNTO_MONITOREO);
            puntoMonitoreo.setTipoPunto(tipoPunto);
            puntoMonitoreo.setIdTipo_punto(tipoPunto.getCodigo());
            
            
            String ubicacionTxt= row.getCellValue("Ubicacion").toString();
            Lista ubicacion = parametrosService.getListaByDescripcion(
                               ubicacionTxt, ConstantesCalidad.UBICACION_PUNTO_MONITOREO);
            puntoMonitoreo.setUbicacion(ubicacion);
            puntoMonitoreo.setIdubicacion(ubicacion.getCodigo());
            
            
            String sistRefTxt= row.getCellValue("Sistema Referencia").toString();
            Lista sistRef = parametrosService.getListaByDescripcion(
                               sistRefTxt, ConstantesCalidad.SISTEMA_REFERENCIA);
            puntoMonitoreo.setSistemaRef(sistRef);
            puntoMonitoreo.setIdSist_ref(sistRef.getCodigo());
            
          
            String nombreDeptoTxt = this.row.getCellValue("Departamento").toString();
            Divipola depto = 
                parametrosService.getDivipolaByName(nombreDeptoTxt,"DEP");
            puntoMonitoreo.setDepartamento(depto);
            puntoMonitoreo.setIdDepartamento( depto.getId() );
            
            String nombreMunTxt = this.row.getCellValue("Municipio").toString();
          
            Divipola mun = 
                parametrosService.getDivipolaByName(nombreMunTxt, depto);
            puntoMonitoreo.setMunicipio(mun);
            puntoMonitoreo.setIdmunicipio( mun.getId() );
          
           
            
            
            String nombreArea = row.getCellValue("Area").toString();
            PartZonificListas area = parametrosService.getZonificacionByName(
                                  nombreArea, null);
            puntoMonitoreo.setIdArea(area);
            
            String nombreZona = row.getCellValue("Zona").toString();
            PartZonificListas zona = parametrosService.getZonificacionByName(
                                         nombreZona, area);
            if(zona==null){
                throw new IdeamException("No es posible encontrar una Zona con nombre " + 
                                         nombreZona + ", asociada al area " + nombreArea);
            }
            puntoMonitoreo.setIdZona(zona);
            
            String nombreSubzona = row.getCellValue("Subzona").toString();
            PartZonificListas subZona = parametrosService.getZonificacionByName(
                                            nombreSubzona, zona);
            if(subZona==null){
                throw new IdeamException("No es posible encontrar una Subzona con nombre " + 
                                         nombreSubzona + ", asociada a la zona " + nombreZona);            
            }
            puntoMonitoreo.setIdSubzona(subZona);
            
        
        
            puntoMonitoreo.setTipofuente(row.getCellValue("Tipo").toString());
            puntoMonitoreo.setNombreFuente(row.getCellValue("Fuente").toString());
            puntoMonitoreo.setNombreTramo(row.getCellValue("Tramo").toString());
            
            String alt= this.row.getCellValue("Altitud").toString();
            alt = replaceFloatingSeparator(alt);
            puntoMonitoreo.setAltitud(Double.parseDouble(alt));
            
           
          
            puntoMonitoreo.setLatitud_grados(this.parseValor("Grados Latitud"));
            puntoMonitoreo.setLatitud_minutos(this.parseValor("Minutos Latitud"));
            
            String segundosLat= this.row.getCellValue("Segundos Latitud").toString();
            segundosLat = replaceFloatingSeparator(segundosLat);
            puntoMonitoreo.setLatitud_segundos(Double.parseDouble(segundosLat));
           
            puntoMonitoreo.setLongitud_grados(this.parseValor("Grados Longitud"));
            puntoMonitoreo.setLongitud_minutos(this.parseValor("Minutos Longitud"));
           
            String segundosLong= this.row.getCellValue("Segundos Longitud").toString();
            segundosLong = replaceFloatingSeparator(segundosLong);
            puntoMonitoreo.setLongitud_segundos(Double.parseDouble(segundosLong));
            
           
            return puntoMonitoreo;
        }
                
        private int parseValor(String nombreColumna){
            String texto = 
                row.getCellValue(nombreColumna).toString();
            Double valor = Double.parseDouble( texto );
            return valor.intValue();
        }
        
     private String replaceFloatingSeparator(String tmp){
         if(tmp!=null && !tmp.trim().equalsIgnoreCase("")){
             if(tmp.indexOf(",")!=-1)
             tmp = tmp.replaceAll(",",".");
         }else{
             tmp=null;
         }
         return tmp;
     }
        
   
  
 }//Fin clase
