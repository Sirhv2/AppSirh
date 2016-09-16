package co.gov.ideam.sirh.archivos.model.validator;

import co.gov.ideam.sirh.archivos.model.CellTO;
import co.gov.ideam.sirh.fuentes.business.FuentesEJB;
import co.gov.ideam.sirh.fuentes.model.FnttFuente;
import co.gov.ideam.sirh.fuentes.model.FnttTramo;
import co.gov.ideam.sirh.parametros.business.ParametrosEJB;
import co.gov.ideam.sirh.parametros.model.Autoridades;

import co.gov.ideam.sirh.parametros.model.Lista;
import co.gov.ideam.sirh.util.IdeamException;

import java.io.Serializable;

import java.util.Iterator;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class TramoCompositeValidator implements CompositeValidatorInterface, RequiresAutoridadValidatorInterface, Serializable {

    private String error;
    private Long codigoCategoria;
    private List<CellTO> campos;
    private String columnaPadre;
    private String listaPadre;
    private Autoridades autoridad;

    public TramoCompositeValidator() {
        super();
    }

    public TramoCompositeValidator(String listaPadre, Long categoria, String columnaPadre) {
        super();
        this.columnaPadre = columnaPadre;
        this.listaPadre = listaPadre;
        this.codigoCategoria = categoria;
    }

    public Autoridades getAutoridad() {
        return this.autoridad;
    }

    public void setAutoridad(Autoridades autoridad) {
        this.autoridad = autoridad;
    }

    public boolean valid(Object value) {
        //System.out.println("============================== TramoCompositeValidator =================");
        if(this.campos !=null && listaPadre!=null && columnaPadre!=null){
            String nombreListaPadre = "";
            //System.out.println("listaPadre:"+listaPadre);
            Iterator it1 = this.campos.iterator();
            while(it1.hasNext()){
                CellTO celda = (CellTO)it1.next();
                if(celda.getTitulo().equalsIgnoreCase(listaPadre)){
                    nombreListaPadre = celda.getValue().toString();
                    break;
                }
            }

            String nombrePadre = "";
            //System.out.println("columnaPadre:"+columnaPadre);
            Iterator it2 = this.campos.iterator();
            while(it2.hasNext()){
                CellTO celda = (CellTO)it2.next();
                if(celda.getTitulo().equalsIgnoreCase(columnaPadre)){
                    nombrePadre = celda.getValue().toString();
                    break;
                }
            }

            //System.out.println("Lista validator nombreLista: "+nombreListaPadre);
            //System.out.println("Padre validator nombrePadre: "+nombrePadre);

            if( nombreListaPadre != null && nombreListaPadre.length()>0 &&
                    nombrePadre != null && nombrePadre.length()>0 ){
                FuentesEJB fuentesService = null;
                ParametrosEJB parametrosService = null;
                try{
                    fuentesService = this.getFuentesService();
                    parametrosService = this.getParametrosService();
                }catch(IdeamException e){
                    error = e.getMessage();
                    return false;
                }
                Lista tipo = null;
                FnttFuente existeFuente = null;
                try{
                    // Validar el tipo
                    tipo = this.getParametrosService().
                        getListaByDescripcion(nombreListaPadre.trim().
                                                                toUpperCase(),
                                                                codigoCategoria);

                    if(tipo==null){
                        error = "No Existe una lista con esta descripcion. ";
                        return false;
                    }
                }catch(IdeamException e){
                    error = "No se encuentra una lista con esta descripcion. ";
                    return false;
                }
                    //System.out.println("Validando desde TramoComposite Lista: " + tipo.getValor() + " " + tipo.getCodigo());

                    //System.out.println("Validando desde TramoComposite Buscar Fuente: " + nombrePadre + " " + tipo.getCodigo() +" "+ this.autoridad.getId());
                    //validar la fuente
                try{
                    existeFuente =
                        this.getFuentesService().getFuente(nombrePadre.trim().toUpperCase(),
                                                 this.getAutoridad(), tipo.getCodigo());
                    if(existeFuente==null){
                        error = "La fuente " + nombrePadre + " no se encuentra registrada para la autoridad " + this.getAutoridad().getNombre() + ". ";
                        return false;
                    }
                }catch(IdeamException e){
                    error = "No se encuentra una fuente con esta descripcion. ";
                    return false;
                }
                try{
                    FnttTramo existeTramo = fuentesService.getTramo(value.toString()
                                                                    .trim().toUpperCase(),
                                                                    existeFuente.getId());

                    if(existeTramo==null){
                        error = "El tramo " + value + " no se encuentra registrado para la fuente " + nombrePadre + ". ";
                        return false;
                    }
                    return true;
                }catch(IdeamException e){
                    error = "No se encuentra un tramo con esta descripcion. ";
                    return false;
                }
            }
            //System.out.println("==========================================Fin Normal");
        }
        error = "No Existen datos de referencia para validar";
        return false;

    }

    public String getMensajeError() {
        return error;
    }

    private FuentesEJB getFuentesService()throws IdeamException{
        try {
            Context ctx = new InitialContext();
            Object obj = ctx.lookup("java:app/Ideam-Ejb/FuentesEJB"); 
            return (FuentesEJB)obj;
        } catch (NamingException e) {
            throw new IdeamException("TramoCompositeValidator Imposible ubicar el EJB de Fuentes " +
                                     e.getMessage());
        }
    }

    private ParametrosEJB getParametrosService()throws IdeamException{
        try {
            Context ctx = new InitialContext();
            Object obj = ctx.lookup("java:app/Ideam-Ejb/ParametrosEJB");
            return (ParametrosEJB)obj;
        } catch (NamingException e) {
            throw new IdeamException("Imposible ubicar el EJB de Parametros " +
                                     e.getMessage());
        }
    }

    public void setValues(List<CellTO> campos) {
        this.campos = campos;
    }
}
