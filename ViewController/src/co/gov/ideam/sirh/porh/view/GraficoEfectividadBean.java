package co.gov.ideam.sirh.porh.view;

import co.gov.ideam.sirh.fuentes.model.FnttFuente;
import co.gov.ideam.sirh.fuentes.model.FnttTramo;
import co.gov.ideam.sirh.parametros.model.Lista;
import co.gov.ideam.sirh.porh.model.PorhParametrosTO;
import co.gov.ideam.sirh.porh.model.PorhTramosUsosObjetivo;
import co.gov.ideam.sirh.porh.model.PorhTramosUsosPlazos;
import co.gov.ideam.sirh.porh.model.PorvEfectividad;
import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;
import co.gov.ideam.sirh.usuarios.agua.view.UsuariosAguaDelegate;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.FacesUtils;
import co.gov.ideam.sirh.view.StandarBean;

import java.math.BigDecimal;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;

import oracle.adf.view.faces.bi.component.graph.UIGraph;
import oracle.adf.view.faces.bi.model.GraphDataModel;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.component.rich.output.RichSpacer;

public class GraficoEfectividadBean extends StandarBean{
    private RichPanelStretchLayout pslg1;
    private RichPanelGroupLayout pgl1;
    private RichOutputText ot_prametro;
    private UIGraph lg1;    
    private List<Object[]> data;
    
    private Lista parametroSeleccionado;
    private String titulo = "";
    private String nombreUso = "";
    private String nombrePlazo = "";
    private RichOutputText ot_uso;
    private RichOutputText ot_plazo;
    private RichSpacer s1;
    private RichSpacer s2;

    public GraficoEfectividadBean(){        
        initGraphDataModel();
    }
    public void initGraphDataModel() {
        Object obj = FacesUtils.getManagedBeanValue("usosPermitidos");
        if (obj==null || !(obj instanceof UsosPermitidosBean)){
            showMessage("No existe pantalla de Usos", FacesMessage.SEVERITY_FATAL);
            return;
        }
        
        UsosPermitidosBean bean = (UsosPermitidosBean)obj;
        if(bean!=null && 
           bean.getSoc_parametro()!=null &&
           bean.getSoc_parametro().getValue() !=null ){
            this.parametroSeleccionado = (Lista)bean.getSoc_parametro().getValue();
            if (this.parametroSeleccionado==null){
                showMessage("No se encuentra el parametro seleccionado", FacesMessage.SEVERITY_FATAL);
                return;
            }
            titulo = getText("PAR_NOMBRE");
            titulo += " " + parametroSeleccionado.getValor();
                        
            Lista uso = (Lista)bean.getSoc_adicionar_uso().getValue();
            nombreUso = uso.getValor();
            
            PorhTramosUsosPlazos plazo = (PorhTramosUsosPlazos)bean.getSoc_plazo().getValue();
            SimpleDateFormat sdf = new SimpleDateFormat("d/M/yyyy");
            nombrePlazo = sdf.format( plazo.getFecha() );
            
            Double valorMeta = null;
            if (bean.getIt_valor().getValue() instanceof BigDecimal){
                valorMeta = ((BigDecimal)bean.getIt_valor().getValue()).doubleValue();
            }else if (bean.getIt_valor().getValue() instanceof Double){
                valorMeta = (Double)bean.getIt_valor().getValue();
            }
            
            FnttTramo tramo = 
                (FnttTramo)FacesUtils.getFromSession("tramoSeleccionado");        
            FnttFuente fuente = 
                (FnttFuente)FacesUtils.getFromSession("fuenteSeleccionada");

            setData(new ArrayList<Object[]>());
            try{
                PorhDelegate pd = PorhDelegate.getInstance();
                List<PorvEfectividad> listaMediciones = 
                    pd.getDatosEfectividad(this.parametroSeleccionado.getCodigo().longValue(),
                                           plazo.getFecha(),
                                           fuente.getId(),
                                           tramo.getId());
                
                if (listaMediciones==null || listaMediciones.size()>0){
                    // showMessage("No existen datos para graficar");
                    //return;                        
                }
                
                Iterator<PorvEfectividad> it = listaMediciones.iterator();
                String categoria = "Valor Esperado";
                String parametro = parametroSeleccionado.getValor();
                while(it.hasNext()){                
                    PorvEfectividad med = it.next();
                    System.out.println("Medicion " + med.getValor()  );
                    Double valor = med.getValor().doubleValue();
                
                    Calendar fecha = GregorianCalendar.getInstance();                    
                    fecha.setTime( med.getFechaMuestreo() );
                    
                    getData().add(new Object[]{fecha.getTime(), categoria, valorMeta});
                    getData().add(new Object[]{fecha.getTime(), parametro, valor});
                }
            }catch(IdeamException e){
                showMessage(e);
            }            
        }
    }
    public void setPslg1(RichPanelStretchLayout psl1) {
        this.pslg1 = psl1;
    }

    public RichPanelStretchLayout getPslg1() {
        return pslg1;
    }

    public void setPgl1(RichPanelGroupLayout pgl1) {
        this.pgl1 = pgl1;
    }

    public RichPanelGroupLayout getPgl1() {
        return pgl1;
    }

    public void setOt_prametro(RichOutputText ot1) {
        this.ot_prametro = ot1;
    }

    public RichOutputText getOt_prametro() {
        return ot_prametro;
    }

    public void setLg1(UIGraph lg1) {
        this.lg1 = lg1;
    }

    public UIGraph getLg1() {
        return lg1;
    }

    public List<Object[]> getData() {
        return data;
    }

    public void setData(List<Object[]> data) {
        this.data = data;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }


    public void setOt_uso(RichOutputText ot1) {
        this.ot_uso = ot1;
    }

    public RichOutputText getOt_uso() {
        return ot_uso;
    }

    public void setOt_plazo(RichOutputText ot2) {
        this.ot_plazo = ot2;
    }

    public RichOutputText getOt_plazo() {
        return ot_plazo;
    }

    public void setS1(RichSpacer s1) {
        this.s1 = s1;
    }

    public RichSpacer getS1() {
        return s1;
    }

    public void setS2(RichSpacer s2) {
        this.s2 = s2;
    }

    public RichSpacer getS2() {
        return s2;
    }

    public String getNombreUso() {
        return nombreUso;
    }

    public void setNombreUso(String nombreUso) {
        this.nombreUso = nombreUso;
    }

    public String getNombrePlazo() {
        return nombrePlazo;
    }

    public void setNombrePlazo(String nombrePlazo) {
        this.nombrePlazo = nombrePlazo;
    }
}
