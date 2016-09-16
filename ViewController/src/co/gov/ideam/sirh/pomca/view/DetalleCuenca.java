package co.gov.ideam.sirh.pomca.view;

import co.gov.ideam.sirh.fuentes.model.FnttFuente;
import co.gov.ideam.sirh.fuentes.view.FuenteDelegate;
import co.gov.ideam.sirh.parametros.model.Lista;
import co.gov.ideam.sirh.pomca.model.Cuenca;
import co.gov.ideam.sirh.pomca.model.PomtAfluentesCuenca;
import co.gov.ideam.sirh.pomca.model.PomtDetalleCuenca;
import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;
import co.gov.ideam.sirh.usuarios.agua.model.PuntoVertimientoTratamiento;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.FacesUtils;
import co.gov.ideam.sirh.view.StandarBean;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DetalleCuenca extends StandarBean{
    
    private List fuentesList;
    private List<FnttFuente> fuentesRelacionadasList;
    protected UsuarioConectadoTO usuarioConectado;
    private Cuenca cuencaSeleccionada;
    private PomtDetalleCuenca detalleCuenca;
    
    public void usuarioConectado(){
         this.usuarioConectado =
            (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");    
    }
    
    public void getCuenca() throws IdeamException{
        this.cuencaSeleccionada = null;
        PomcaDelegate pd = PomcaDelegate.getInstance();
        
        Object obj = FacesUtils.getFromSession("cuencaSeleccionada");
        if (obj instanceof Integer) {
            Integer pto = (Integer)obj;
            if (pto != null) {
                this.cuencaSeleccionada = pd.getCuenca(pto.longValue());
            }
        } else if (obj instanceof Long) {
            Long pto = (Long)obj;
            if (pto != null) {
                this.cuencaSeleccionada = pd.getCuenca(pto);
            }
        } else if (obj instanceof String) {
            String pto = (String)obj;
            if (pto != null) {
                this.cuencaSeleccionada = pd.getCuenca(new Long(pto));
            }
        } else if (obj instanceof Cuenca){
            this.cuencaSeleccionada = (Cuenca) obj;
        }
    }
    
    public void cargarDetalleCuenca() throws IdeamException{
        this.detalleCuenca = null;
        PomcaDelegate pd = PomcaDelegate.getInstance();
        this.detalleCuenca = pd.getDetalleCuenca(this.cuencaSeleccionada.getId());
        if(this.detalleCuenca == null){
            this.detalleCuenca = new PomtDetalleCuenca();
            //this.detalleCuenca.setCodigo("");
        }
    }
    
    public void getFuente() throws IdeamException{
        FuenteDelegate fd = FuenteDelegate.getInstance();
        if(this.detalleCuenca!=null && 
                this.detalleCuenca.getFuentePrincipal()!=null){
            
            FnttFuente fuente = fd.getFuente(this.detalleCuenca.getFuentePrincipal());
            this.detalleCuenca.setFuente(fuente);
        }
        
    }
    
    public void cargarFuentes() throws IdeamException{
        this.fuentesList = new ArrayList();
        FuenteDelegate fd = FuenteDelegate.getInstance();
        this.fuentesList = 
            this.getListaFuentes(this.usuarioConectado.
                                 getUsuario().getAutoridadAmbiental());
        
    }
    
    public void cargarAfluentes() throws IdeamException{
        this.fuentesRelacionadasList = new ArrayList();
        PomcaDelegate pd = PomcaDelegate.getInstance();
        FuenteDelegate fd = FuenteDelegate.getInstance();
        List<PomtAfluentesCuenca> asociadas = 
            pd.getAllAfluentesCuenca(this.cuencaSeleccionada.getId());
        if(asociadas!=null && !asociadas.isEmpty()){
            for(PomtAfluentesCuenca afluente : asociadas){
                FnttFuente fuente = fd.getFuente(afluente.getIdFuente());
                this.fuentesRelacionadasList.add(fuente);
            }
        } 
        
    }
    
    public void save(String codigo, 
                     FnttFuente fuentePrincipal, 
                     List afluentes) throws IdeamException{
        PomcaDelegate pd = PomcaDelegate.getInstance(); 
        this.detalleCuenca.setCodigo(codigo);
        this.detalleCuenca.setFuentePrincipal(fuentePrincipal.getId());
        this.detalleCuenca.setFuente(fuentePrincipal);
        this.detalleCuenca.setAfluentes(afluentes);
        this.detalleCuenca.setId(this.cuencaSeleccionada.getId());
        this.detalleCuenca.setCuenca(this.cuencaSeleccionada);
        
        this.detalleCuenca = pd.updateDetalleCuenca(this.detalleCuenca);
        
    }

    public void setFuentesList(List fuentesList) {
        this.fuentesList = fuentesList;
    }

    public List getFuentesList() {
        return fuentesList;
    }

    public void setCuencaSeleccionada(Cuenca cuencaSeleccionada) {
        this.cuencaSeleccionada = cuencaSeleccionada;
    }

    public Cuenca getCuencaSeleccionada() {
        return cuencaSeleccionada;
    }

    public void setDetalleCuenca(PomtDetalleCuenca detalleCuenca) {
        this.detalleCuenca = detalleCuenca;
    }

    public PomtDetalleCuenca getDetalleCuenca() {
        return detalleCuenca;
    }

    public void setFuentesRelacionadasList(List fuentesRelacionadasList) {
        this.fuentesRelacionadasList = fuentesRelacionadasList;
    }

    public List getFuentesRelacionadasList() {
        return fuentesRelacionadasList;
    }
}
