package co.gov.ideam.sirh.fuentes.view;

import co.gov.ideam.sirh.red.ideam.model.SirhvPuntoMonitoreoFq;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.StandarBean;
import java.util.ArrayList;
import java.util.List;

public class ConflictosXFuente extends StandarBean{
    
    protected String accion;
    private List datosList;
    protected SirhvPuntoMonitoreoFq puntoSeleccionado;
    
    public void loadParametros() throws IdeamException{    
        
    }
    
    
    public void cargarDatos(String nombre) throws IdeamException{
        this.datosList = new ArrayList();
/*        RedMonitoreoDelegate rmd = RedMonitoreoDelegate.getInstance();
        
        if(nombre == null){
            this.datosList = rmd.getAllPuntos();
        }else{
            this.datosList = rmd.getAllPuntos(nombre);                              
        }
*/
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
