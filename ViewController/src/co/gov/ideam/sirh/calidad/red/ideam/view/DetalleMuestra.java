package co.gov.ideam.sirh.calidad.red.ideam.view;

import co.gov.ideam.sirh.calidad.model.MuestrasIca;
import co.gov.ideam.sirh.calidad.view.CalidadDelegate;
import co.gov.ideam.sirh.calidad.view.FiltrosCalidad;
import co.gov.ideam.sirh.red.ideam.model.FqLaboratorios;
import co.gov.ideam.sirh.red.ideam.model.FqMuestras;
import co.gov.ideam.sirh.red.ideam.model.SirhvMuestrasFq;
import co.gov.ideam.sirh.red.ideam.model.SirhvPuntoMonitoreoFq;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.FacesUtils;
import co.gov.ideam.sirh.view.StandarBean;

import java.util.ArrayList;
import java.util.List;

public class DetalleMuestra extends StandarBean{
     
    private SirhvPuntoMonitoreoFq puntoSeleccionado;
    private FqMuestras muestraSeleccionada;
    private FqLaboratorios laboratorioMuestra;
    private List analisisList;
    private MuestrasIca muestrasIca;
    
    
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
    
    public void getMuestra() throws IdeamException{
        this.muestraSeleccionada = null;
        RedMonitoreoDelegate rmd = RedMonitoreoDelegate.getInstance();
        
        Object obj = FacesUtils.getFromSession("muestraIdeamSeleccionada");
        if (obj instanceof Integer) {
            Integer mtr = (Integer)obj;
            if (mtr != null) {
                this.muestraSeleccionada = rmd.getMuestra(mtr.longValue());
            }
        } else if (obj instanceof Long) {
            Long mtr = (Long)obj;
            if (mtr != null) {
                this.muestraSeleccionada = rmd.getMuestra(mtr);
            }
        } else if (obj instanceof String) {
            String mtr = (String)obj;
            if (mtr != null) {
                this.muestraSeleccionada = rmd.getMuestra(new Long(mtr));
            }
        } else if (obj instanceof FqMuestras){
            this.muestraSeleccionada = (FqMuestras) obj;
        }
        // Cargar los datos ICA de la muestra
        if(this.muestraSeleccionada!= null && 
           this.muestraSeleccionada.getCodigoMuestra()!=null){
            CalidadDelegate cd = CalidadDelegate.getInstance();            
            muestrasIca = cd.getMuestraIcaIdeam(this.muestraSeleccionada.getCodigoMuestra());
            if (muestrasIca!=null){
            }else{
                muestrasIca = new MuestrasIca();
                muestrasIca.setMensaje("No existen los parámetros requeridos para calcular PSOD e ICA");               
            }
        }
    }
    
    public void getLaboratorio() throws IdeamException{
        RedMonitoreoDelegate rmd = RedMonitoreoDelegate.getInstance();
        
        this.laboratorioMuestra = 
            rmd.getLaboratorio(new Long(this.muestraSeleccionada.getLaboIdLaboratorio()));
    }
    
    
    public void cargarAnalisis() throws IdeamException{
        this.analisisList = new ArrayList();
        
        RedMonitoreoDelegate rmd = RedMonitoreoDelegate.getInstance();
        
        this.analisisList = 
            rmd.getAllAnalisisByPuntoMuestra(this.puntoSeleccionado.getId(),
                                             this.muestraSeleccionada.getCodigoMuestra());                           

    }

    public void setPuntoSeleccionado(SirhvPuntoMonitoreoFq puntoSeleccionado) {
        this.puntoSeleccionado = puntoSeleccionado;
    }

    public SirhvPuntoMonitoreoFq getPuntoSeleccionado() {
        return puntoSeleccionado;
    }

    public void setMuestraSeleccionada(FqMuestras muestraSeleccionada) {
        this.muestraSeleccionada = muestraSeleccionada;
    }

    public FqMuestras getMuestraSeleccionada() {
        return muestraSeleccionada;
    }

    public void setAnalisisList(List analisisList) {
        this.analisisList = analisisList;
    }

    public List getAnalisisList() {
        return analisisList;
    }

    public void setLaboratorioMuestra(FqLaboratorios laboratorioMuestra) {
        this.laboratorioMuestra = laboratorioMuestra;
    }

    public FqLaboratorios getLaboratorioMuestra() {
        return laboratorioMuestra;
    }

    public MuestrasIca getMuestrasIca() {
        return muestrasIca;
    }

    public void setMuestrasIca(MuestrasIca muestrasIca) {
        this.muestrasIca = muestrasIca;
    }
}
