package co.gov.ideam.sirh.archivos.model;


import co.gov.ideam.sirh.calidad.model.Medicion;
import co.gov.ideam.sirh.calidad.model.Muestra;
import co.gov.ideam.sirh.parametros.business.ParametrosEJB;

import co.gov.ideam.sirh.parametros.model.Lista;
import co.gov.ideam.sirh.util.ConstantesCalidad;
import co.gov.ideam.sirh.util.IdeamException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class MuestrasMedicionesConverter  extends ModelConverter{
    public MuestrasMedicionesConverter() {
          super();
        }
        
        public Object getModel(Object model)throws IdeamException {
            if(model instanceof Muestra){
                return this.getMuestra( (Muestra)model );
            }  
            else if(model instanceof Medicion){
                return this.getMedicion( (Medicion)model );
            } 
            
            return null;
        }
        private Muestra getMuestra(Muestra muestra)throws IdeamException{
            ParametrosEJB parametrosService = this.getParametrosService();
            
            
            muestra.setNro_muestra(null);
            muestra.setNombrePunto(row.getCellValue("Nombre Punto").toString());
           
            String labtxt= row.getCellValue("Laboratorio").toString();
            Lista laboratorio = parametrosService.getListaByDescripcion(
                               labtxt, ConstantesCalidad.LABORATORIO);
            muestra.setLaboratorio(laboratorio);
            muestra.setIdLaboratorio(laboratorio.getCodigo());
            if(muestra.getIdLaboratorio().equals(ConstantesCalidad.OTRO_LAB)){
               String otroLab=row.getCellValue("Cual?").toString();
                    if(otroLab.length()>0){
                          muestra.setOtroLaboratorio(otroLab);
                       }
               
            }
            
            String tipoMuestratxt= row.getCellValue("Tipo Muestra").toString();
            Lista tipoMuestra = parametrosService.getListaByDescripcion(
                               tipoMuestratxt, ConstantesCalidad.TIPO_MUESTRA);
            muestra.setTipoMuestra(tipoMuestra);
            muestra.setIdTipoMuestra(tipoMuestra.getCodigo());
            
            String fechaMuestra = this.row.getCellValue("Fecha").toString();
            muestra.setFechaMuestreo(this.getDateDDMMAAAA( fechaMuestra ));
            System.out.println("---- --- --- ---Fecha de muestra en el Converter:"+muestra.getFechaMuestreo());
            
            
        
            String hora=row.getCellValue("Hora").toString();
            if(hora.indexOf(".0")!=-1)
                hora=hora.substring(0,hora.indexOf(".0"));
            muestra.setHoraMuestreo(Integer.parseInt(hora));
            String min=row.getCellValue("Min").toString();
            if(min.indexOf(".0")!=-1)
                min=hora.substring(0,min.indexOf(".0"));
            muestra.setMinMuestreo(Integer.parseInt(min));
            
          
            muestra.setHorario(row.getCellValue("Horario").toString());
            
            muestra.setCaudal(Double.parseDouble(row.getCellValue("Caudal").toString()));
           
           
           if(muestra.getTipoMuestra().getCodigo().equals(ConstantesCalidad.MUESTRA_INTEGRADA)){
               muestra.setNroVerticales(this.parseValor("Nro de verticales"));
           }else if(muestra.getTipoMuestra().getCodigo().equals(ConstantesCalidad.MUESTRA_COMPUESTA)){
               muestra.setDuracion(Double.parseDouble(row.getCellValue("Duracion").toString()));  
               muestra.setNroSubmuestras(this.parseValor("Nro de Submuestras"));
               muestra.setIntervaloTiempo(Double.parseDouble(row.getCellValue("Intervalo de Tiempo").toString()));  
            }
           
           
            return muestra;
        }
                
        private Medicion getMedicion(Medicion med)throws IdeamException{
            ParametrosEJB parametrosService = this.getParametrosService();
            
            String parametrotxt= row.getCellValue("Parametros").toString();
            Lista parametroLista = parametrosService.getListaByDescripcion(
                               parametrotxt, ConstantesCalidad.PARAMETROS_CALIDAD);
            
             med.setParametro(parametroLista);
             med.setIdParametro(parametroLista.getCodigo());
        
          
            String signotxt= row.getCellValue("Signo").toString();
            Lista singoLista = parametrosService.getListaByDescripcion(
                               signotxt, ConstantesCalidad.SIGNOS);
            
             med.setSigno(singoLista);
             med.setIdSigno(singoLista.getCodigo());
            
            
            String undtxt= row.getCellValue("Unidad").toString();
            Lista unidadLista = parametrosService.getListaByDescripcion(
                               undtxt, ConstantesCalidad.UNIDADES_CALIDAD);
            
             med.setUnidad(unidadLista);
             med.setIdUnidad(unidadLista.getCodigo());
             String valor =row.getCellValue("Valor").toString();
            if(valor.length()>0){
            String s_valor= valor.substring(0, valor.length());
            s_valor = s_valor.replace(',', '.');    
            med.setValor(Double.parseDouble(s_valor));
            
            }
            
             
             
             
             
          String metodo = row.getCellValue("Metodo de determinación").toString();
          
          if(metodo.length()>0){
             med.setMetodoDeterminacion(metodo);
             }
             String lim= row.getCellValue("Limite de detección").toString();
             if(lim.length()>0){
             String lim_valor= lim.substring(0, lim.length());
             lim_valor = lim_valor.replace(',', '.');    
                  
             med.setLimiteDeteccion(Double.parseDouble(lim_valor));
             }
          

            String acreditado= row.getCellValue("Acreditado").toString();
            if (acreditado.equals("SI")||acreditado.equals("Si")){
                
                med.setEsAcreditado(1);
                med.setAcreditado(true);
                
                }else if (acreditado.equals("NO")||acreditado.equals("")){
                
                    med.setEsAcreditado(0);
                    med.setAcreditado(false);
                }
          
        return med;
        }
        
    
    
                
        private int parseValor(String nombreColumna){
            String texto = 
                row.getCellValue(nombreColumna).toString();
            Double valor = Double.parseDouble( texto );
            return valor.intValue();
        }
        /**
         *  Retorna una instancia al EJB de Parametros
         * @return
         * @throws IdeamException
         */
        /*protected ParametrosEJB getParametrosService()throws IdeamException{
            try {
                Context ctx = new InitialContext();
                Object obj = ctx.lookup("java:app/Ideam-Ejb/ParametrosEJB");        
                return (ParametrosEJB)obj;
            } catch (NamingException e) {
                throw new IdeamException("Imposible ubicar el EJB de Parametros " + 
                                         e.getMessage());
            }        
        }    */
    }//Fin clase
