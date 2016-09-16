package co.gov.ideam.sirh.calidad.red.ideam.view;

import co.gov.ideam.sirh.red.ideam.model.SirhvPuntoMonitoreoFq;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.FacesUtils;
import co.gov.ideam.sirh.view.StandarBean;

import java.util.ArrayList;
import java.util.List;

import org.apache.myfaces.trinidad.model.TreeModel;

public class DetallePunto extends StandarBean{
    
    private SirhvPuntoMonitoreoFq puntoSeleccionado;
    protected List muestrasList;
    protected TreeModel muestraTreeModel;
    
    
    
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


    public void setPuntoSeleccionado(SirhvPuntoMonitoreoFq puntoSeleccionado) {
        this.puntoSeleccionado = puntoSeleccionado;
    }

    public SirhvPuntoMonitoreoFq getPuntoSeleccionado() {
        return puntoSeleccionado;
    }


    public void setMuestrasList(List muestrasList) {
        this.muestrasList = muestrasList;
    }

    public List getMuestrasList() {
        return muestrasList;
    }


    public void setMuestraTreeModel(TreeModel muestraTreeModel) {
        this.muestraTreeModel = muestraTreeModel;
    }

    public TreeModel getMuestraTreeModel() {
        return muestraTreeModel;
    }

}
