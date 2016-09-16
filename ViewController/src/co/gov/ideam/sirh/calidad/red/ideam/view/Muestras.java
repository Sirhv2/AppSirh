package co.gov.ideam.sirh.calidad.red.ideam.view;

import co.gov.ideam.sirh.red.ideam.model.FqMuestras;
import co.gov.ideam.sirh.red.ideam.model.SirhvMuestrasFq;
import co.gov.ideam.sirh.red.ideam.model.SirhvPuntoMonitoreoFq;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.FacesUtils;
import co.gov.ideam.sirh.view.StandarBean;

import java.util.ArrayList;
import java.util.List;

public class Muestras extends StandarBean{
    protected String accion;
    protected List muestrasList;
    private SirhvPuntoMonitoreoFq puntoSeleccionado;
    protected FqMuestras muestraSeleccionada;
    
    public void getPuntoMonitoreo() throws IdeamException{
        this.puntoSeleccionado = null;
        RedMonitoreoDelegate rmd = RedMonitoreoDelegate.getInstance();
        
        Object obj = FacesUtils.getFromSession("puntoIdeam");
        if (obj instanceof Integer) {
            Integer pto = (Integer)obj;
            if (pto != null) {
                this.puntoSeleccionado = rmd.getPunto(pto.longValue());
            }
        } else if (obj instanceof Long) {
            Long pto = (Long)obj;
            if (pto != null) {
                this.puntoSeleccionado = rmd.getPunto(pto);
            }
        } else if (obj instanceof String) {
            String pto = (String)obj;
            if (pto != null) {
                this.puntoSeleccionado = rmd.getPunto(new Long(pto));
            }
        } else if (obj instanceof SirhvPuntoMonitoreoFq){
            this.puntoSeleccionado = (SirhvPuntoMonitoreoFq) obj;
        }
    }
    
    public void cargarMuestras() throws IdeamException{
        this.muestrasList = new ArrayList();
        
        RedMonitoreoDelegate rmd = RedMonitoreoDelegate.getInstance();
        
        this.muestrasList = rmd.getAllMuestrasByPunto(this.puntoSeleccionado.getId());                              

    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public String getAccion() {
        return accion;
    }

    public void setMuestrasList(List muestrasList) {
        this.muestrasList = muestrasList;
    }

    public List getMuestrasList() {
        return muestrasList;
    }

    public void setMuestraSeleccionada(FqMuestras muestraSeleccionada) {
        this.muestraSeleccionada = muestraSeleccionada;
    }

    public FqMuestras getMuestraSeleccionada() {
        return muestraSeleccionada;
    }

    public void setPuntoSeleccionado(SirhvPuntoMonitoreoFq puntoSeleccionado) {
        this.puntoSeleccionado = puntoSeleccionado;
    }

    public SirhvPuntoMonitoreoFq getPuntoSeleccionado() {
        return puntoSeleccionado;
    }
}
