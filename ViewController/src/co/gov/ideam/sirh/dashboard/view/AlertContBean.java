package co.gov.ideam.sirh.dashboard.view;

import co.gov.ideam.sirh.calidad.model.DatosGrafico;
import co.gov.ideam.sirh.calidad.model.NormaLimites;
import co.gov.ideam.sirh.calidad.model.PuntoMonitoreo;
import co.gov.ideam.sirh.calidad.view.CalidadDelegate;
import co.gov.ideam.sirh.fuentes.view.FuenteDelegate;
import co.gov.ideam.sirh.parametros.model.Lista;
import co.gov.ideam.sirh.parametros.model.PartZonificListas;

import co.gov.ideam.sirh.parametros.view.ParametrosDelegate;
import co.gov.ideam.sirh.pomca.view.PomcaDelegate;
import co.gov.ideam.sirh.seguridad.model.PerfilVO;
import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;
import co.gov.ideam.sirh.seguridad.view.SeguridadDelegate;
import co.gov.ideam.sirh.usuarios.agua.view.UsuariosAguaDelegate;
import co.gov.ideam.sirh.util.ConstantesCalidad;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.FacesUtils;

import co.gov.ideam.sirh.view.StandarBean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UISelectItems;

import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import javax.faces.model.SelectItem;

import oracle.adf.view.faces.bi.component.graph.UIGraph;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.layout.RichPanelBox;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelSplitter;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandLink;
import oracle.adf.view.rich.component.rich.output.RichActiveOutputText;
import oracle.adf.view.rich.component.rich.output.RichSpacer;
import oracle.adf.view.rich.context.AdfFacesContext;

public class AlertContBean extends StandarBean {

    private List listaParametro;
    private List listaPuntos;

    private Lista parametroActual;
    private PuntoMonitoreo puntoActual;

    private List<Object[]> listObjectGrafico;

    private RichForm form1;
    private RichDocument document1;
    private RichPanelStretchLayout panelStretchLayout1;
    private RichPanelSplitter panelSplitter1;
    private RichPanelGroupLayout panelGroupLayout2;
    private RichPanelFormLayout panelFormLayout2;

    private RichSpacer spacer4;
    private RichCommandLink clink_inicio;
    private RichSpacer spacer5;
    private RichCommandLink clink_grafico1;
    private RichSpacer spacer6;
    private RichPanelGroupLayout panelGroupLayout3;
    private RichPanelBox panelBoxCalidadGraf1;
    private RichPanelSplitter panelSplitter2;
    private RichPanelGroupLayout panelGroupLayout4;
    private RichPanelFormLayout panelFormLayout12;
    private RichPanelGroupLayout panelGroupLayout1;
    private RichActiveOutputText aot_pto;
    private RichActiveOutputText datos;
    private RichActiveOutputText aot_parametro;

    private RichActiveOutputText aot_oferta;
    private RichCommandLink commandLink1;
    private RichSpacer spacer1;
    private RichCommandLink commandLink2;
    private RichSpacer spacer2;
    private RichCommandLink commandLink3;
    private RichSpacer spacer3;
    private RichCommandLink commandLink4;
    private RichSelectOneChoice seletPar;
    private UISelectItems selectItems3;
    private RichSelectOneChoice sc_puntos;
    private UISelectItems selectpuntos;
    private RichPanelFormLayout panelFormGrafico;
    private RichActiveOutputText datos1;
    private RichActiveOutputText dparamtro;
    private RichActiveOutputText dunidad;
    private UIGraph lineGraph11;

    public void setForm1(RichForm form1) {
        this.form1 = form1;
    }

    public RichForm getForm1() {
        return form1;
    }

    public void setDocument1(RichDocument document1) {
        this.document1 = document1;
    }

    public RichDocument getDocument1() {
        return document1;
    }

    public void setPanelStretchLayout1(RichPanelStretchLayout panelStretchLayout1) {
        this.panelStretchLayout1 = panelStretchLayout1;
    }

    public RichPanelStretchLayout getPanelStretchLayout1() {
        return panelStretchLayout1;
    }

    public void setPanelSplitter1(RichPanelSplitter panelSplitter1) {
        this.panelSplitter1 = panelSplitter1;
    }

    public RichPanelSplitter getPanelSplitter1() {
        return panelSplitter1;
    }

    public void setPanelGroupLayout2(RichPanelGroupLayout panelGroupLayout2) {
        this.panelGroupLayout2 = panelGroupLayout2;
    }

    public RichPanelGroupLayout getPanelGroupLayout2() {
        return panelGroupLayout2;
    }

    public void setPanelFormLayout2(RichPanelFormLayout panelFormLayout2) {
        this.panelFormLayout2 = panelFormLayout2;
    }

    public RichPanelFormLayout getPanelFormLayout2() {
        return panelFormLayout2;
    }

    public void setSpacer4(RichSpacer spacer4) {
        this.spacer4 = spacer4;
    }

    public RichSpacer getSpacer4() {
        return spacer4;
    }

    public void setClink_inicio(RichCommandLink clink_inicio) {
        this.clink_inicio = clink_inicio;
    }

    public RichCommandLink getClink_inicio() {
        return clink_inicio;
    }

    public void setSpacer5(RichSpacer spacer5) {
        this.spacer5 = spacer5;
    }

    public RichSpacer getSpacer5() {
        return spacer5;
    }

    public void setClink_grafico1(RichCommandLink clink_grafico1) {
        this.clink_grafico1 = clink_grafico1;
    }

    public RichCommandLink getClink_grafico1() {
        return clink_grafico1;
    }

    public void setSpacer6(RichSpacer spacer6) {
        this.spacer6 = spacer6;
    }

    public RichSpacer getSpacer6() {
        return spacer6;
    }


    public void setPanelGroupLayout3(RichPanelGroupLayout panelGroupLayout3) {
        this.panelGroupLayout3 = panelGroupLayout3;
    }

    public RichPanelGroupLayout getPanelGroupLayout3() {
        return panelGroupLayout3;
    }

    public void setPanelBoxCalidadGraf1(RichPanelBox panelBoxCalidadGraf1) {
        this.panelBoxCalidadGraf1 = panelBoxCalidadGraf1;
    }

    public RichPanelBox getPanelBoxCalidadGraf1() {
        return panelBoxCalidadGraf1;
    }

    public void setPanelSplitter2(RichPanelSplitter panelSplitter2) {
        this.panelSplitter2 = panelSplitter2;
    }

    public RichPanelSplitter getPanelSplitter2() {
        return panelSplitter2;
    }

    public void setPanelGroupLayout4(RichPanelGroupLayout panelGroupLayout4) {
        this.panelGroupLayout4 = panelGroupLayout4;
    }

    public RichPanelGroupLayout getPanelGroupLayout4() {
        return panelGroupLayout4;
    }

    public void setPanelFormLayout12(RichPanelFormLayout panelFormLayout12) {
        this.panelFormLayout12 = panelFormLayout12;
    }

    public RichPanelFormLayout getPanelFormLayout12() {
        return panelFormLayout12;
    }


    public void setPanelGroupLayout1(RichPanelGroupLayout panelGroupLayout1) {
        this.panelGroupLayout1 = panelGroupLayout1;
    }

    public RichPanelGroupLayout getPanelGroupLayout1() {
        return panelGroupLayout1;
    }

    public void setAot_pto(RichActiveOutputText aot_pto) {
        this.aot_pto = aot_pto;
    }

    public RichActiveOutputText getAot_pto() {
        return aot_pto;
    }

    public void setDatos(RichActiveOutputText datos) {
        this.datos = datos;
    }

    public RichActiveOutputText getDatos() {
        return datos;
    }

    public void setAot_parametro(RichActiveOutputText aot_parametro) {
        this.aot_parametro = aot_parametro;
    }

    public RichActiveOutputText getAot_parametro() {
        return aot_parametro;
    }


    public void setAot_oferta(RichActiveOutputText aot_oferta) {
        this.aot_oferta = aot_oferta;
    }

    public RichActiveOutputText getAot_oferta() {
        return aot_oferta;
    }


    public void setListObjectGrafico(List<Object[]> listObjectGrafico) {
        this.listObjectGrafico = listObjectGrafico;
    }

    public List<Object[]> getListObjectGrafico() {
        return listObjectGrafico;
    }

    public AlertContBean() {

        FacesUtils.setActiveBean("AlertContBean","AlertContBean", AlertContBean.class);
        FacesUtils.removeManagedBeanFromSession("AlertaCalidadBean");
        FacesUtils.removeManagedBeanFromSession("AlertDispBean");

        this.load();
    }

    public void load() {

        try {


            this.listaPuntos = this.getListaPuntosMediciones(null);
            this.listaParametro= new ArrayList();
            
            listObjectGrafico = new ArrayList<Object[]>();

        } catch (IdeamException e) {
            showMessage(e);
        }

    }


    public void sc_punto_valueChangeListener(ValueChangeEvent event) {
        try {

            Object punto = event.getNewValue();
            
            this.listaParametro= new ArrayList();
            
            if (punto != null) {
                puntoActual = (PuntoMonitoreo)punto;
                System.out.println("HCP Punto " + puntoActual.getNombre() + ":" + puntoActual.getId());
                this.listaParametro = this.getListaParametrosPunto(puntoActual.getId());
                
                AdfFacesContext.getCurrentInstance().addPartialTarget(seletPar);
                
            } else {
                puntoActual = null;
                System.out.println("HCP Punto nulo");
            }


        } catch (Exception e) {
            showMessage(e.getMessage());
        }

    }

    public void sc_parametro_valueChangeListener(ValueChangeEvent event) {
        try {
            
            listObjectGrafico = new ArrayList<Object[]>();
            
            Object par = event.getNewValue();

            if (par != null) {
                parametroActual = (Lista)par;
                System.out.println("HCP parametro " +
                                   parametroActual.getValor() + ":" +  parametroActual.getCodigo());
                
                graficar();
            } else {
                parametroActual = null;
                System.out.println("HCP parametro nulo");
            }

        } catch (Exception e) {
            showMessage(e.getMessage());
        }

    }


    public void graficar() {

        CalidadDelegate cld;

        try {
            cld = CalidadDelegate.getInstance();
            List listaMediciones = null;

            if (parametroActual != null && puntoActual != null)
                listaMediciones =
                        cld.getListaMedicionsPorParametro(parametroActual.getCodigo().longValue(),
                                                          puntoActual.getCodigoAutoridad());

            if (listaMediciones != null) {
                System.out.println("HCP lista mediciones ok");
                if (listaMediciones.size() > 0) {
                    System.out.println("HCP lista mediciones reg " +
                                       listaMediciones.size());
                } else {
                    showMessage("No day datos para el parametro y punto de monitoreo");
                    System.out.println("HCP lista mediciones en cero ");
                }
            } else {
                showMessage("No day datos para el parametro y punto de monitoreo");
                System.out.println("HCP lista mediciones nula ");
            }

            this.panelFormGrafico.setVisible(true);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.panelFormGrafico);

            pintaGrafica();

            AdfFacesContext.getCurrentInstance().addPartialTarget(this.lineGraph11);

        } catch (IdeamException e) {
            showMessage(e);
        }
    }


    public void pintaGrafica() {

        try {

            puntoActual = (PuntoMonitoreo)sc_puntos.getValue();
            parametroActual = (Lista)seletPar.getValue();

            if (puntoActual == null)
                System.out.println("HCP punto a graficar nulo ");

            if (parametroActual == null)
                System.out.println("HCP parametro a graficar nulo ");

            listObjectGrafico = new ArrayList<Object[]>();
            CalidadDelegate cald = CalidadDelegate.getInstance();

            List<Object[]> lDatosGrafico = null;

            if (puntoActual != null && parametroActual != null)
                lDatosGrafico =
                        cald.getPararametroFechaPuntoM(puntoActual.getId(),
                                                       parametroActual.getCodigo().longValue());

            List<Lista> lUnidad =
                cald.getUnidadPararametro(parametroActual.getCodigo().longValue(),
                                          puntoActual.getCodigoAutoridad());

            System.out.println("HCP va a consultar limites a graficar " );

            List<Object[]> limitesPar = cald.getListaLimitesParametro(parametroActual.getCodigo(),puntoActual.getCodigoAutoridad().intValue());
            System.out.println("HCP cantidad de limites a graficar " +
                               limitesPar.size());
            
            //NormaLimites nl = cald.getNormaLimitesId(this.normaLimites.getId()) ;

            String nombrePar = "Parámetro: " + parametroActual.getValor();

            Lista l = lUnidad.get(0);

            dparamtro.setValue(nombrePar.toString());
            AdfFacesContext.getCurrentInstance().addPartialTarget(dparamtro);

            this.dunidad.setValue("Unidad: " + l.getValor().toString());
            AdfFacesContext.getCurrentInstance().addPartialTarget(dunidad);

            System.out.println("HCP cantidad de datos a graficar " +
                               lDatosGrafico.size());

            for (int j = 0; j < lDatosGrafico.size(); j++) {
                Object[] dat = lDatosGrafico.get(j);
                
                String fecha = (String)dat[0];
                Double cant = new Double(dat[1].toString());

                Object[] punto =
                { fecha, " Medición de Calidad ",cant };

                listObjectGrafico.add(punto);
            }

            // ciclo de todos los limites
            for (int i = 0; i < limitesPar.size(); i++) {
                Object[] lim = limitesPar.get(i);

                String nomNivel = lim[1].toString(); // norma
                nomNivel += "-" + lim[2].toString(); // uso
                if (!lim[3].toString().equals("En riesgo"))
                    nomNivel += "-" + lim[3].toString();
                
                for (int j = 0; j < lDatosGrafico.size(); j++) {
                    Object[] dat = lDatosGrafico.get(j);

                    String fecha = (String)dat[0];
                    Double cant = new Double(lim[6].toString());

                    Object[] punto =
                    { fecha, nomNivel , cant };

                    listObjectGrafico.add(punto);
                }
                    
            }

        } catch (Exception e) {
            showMessage(e.getMessage());
        }
    }

    protected List getListaPorCategoria(Long idCategoria) throws IdeamException {

        CalidadDelegate cld = CalidadDelegate.getInstance();
        List lista = new ArrayList(); //carga el selectItem.
        List result = null;
        result = cld.getListaPorCategoria(idCategoria);

        if (result != null) {
            Iterator it = result.iterator();
            while (it.hasNext()) {
                Lista result1 = (Lista)it.next();
                SelectItem si = new SelectItem(result1, result1.getValor());
                lista.add(si);
            }
        }
        return lista;
    }

    protected List getListaPuntosMediciones(Long idAutoridad) throws IdeamException {

        CalidadDelegate cld = CalidadDelegate.getInstance();
        List lista = new ArrayList(); //carga el selectItem.
        List result = null;
        result = cld.getPuntosMonitoreoMediciones(idAutoridad);

        if (result != null) {
            Iterator it = result.iterator();
            while (it.hasNext()) {
                PuntoMonitoreo result1 = (PuntoMonitoreo)it.next();
                SelectItem si = new SelectItem(result1, result1.getNombre());
                lista.add(si);
            }
        }
        return lista;
    }

    public void setCommandLink1(RichCommandLink commandLink1) {
        this.commandLink1 = commandLink1;
    }

    public RichCommandLink getCommandLink1() {
        return commandLink1;
    }

    public void setSpacer1(RichSpacer spacer1) {
        this.spacer1 = spacer1;
    }

    public RichSpacer getSpacer1() {
        return spacer1;
    }

    public void setCommandLink2(RichCommandLink commandLink2) {
        this.commandLink2 = commandLink2;
    }

    public RichCommandLink getCommandLink2() {
        return commandLink2;
    }

    public void setSpacer2(RichSpacer spacer2) {
        this.spacer2 = spacer2;
    }

    public RichSpacer getSpacer2() {
        return spacer2;
    }

    public void setCommandLink3(RichCommandLink commandLink3) {
        this.commandLink3 = commandLink3;
    }

    public RichCommandLink getCommandLink3() {
        return commandLink3;
    }

    public void setSpacer3(RichSpacer spacer3) {
        this.spacer3 = spacer3;
    }

    public RichSpacer getSpacer3() {
        return spacer3;
    }

    public void setCommandLink4(RichCommandLink commandLink4) {
        this.commandLink4 = commandLink4;
    }

    public RichCommandLink getCommandLink4() {
        return commandLink4;
    }

    public void setSeletPar(RichSelectOneChoice seletPar) {
        this.seletPar = seletPar;
    }

    public RichSelectOneChoice getSeletPar() {
        return seletPar;
    }

    public void setSelectItems3(UISelectItems selectItems3) {
        this.selectItems3 = selectItems3;
    }

    public UISelectItems getSelectItems3() {
        return selectItems3;
    }

    public void setListaParametro(List listaParametro) {
        this.listaParametro = listaParametro;
    }

    public List getListaParametro() {
        return listaParametro;
    }

    public void setSc_puntos(RichSelectOneChoice sc_puntos) {
        this.sc_puntos = sc_puntos;
    }

    public RichSelectOneChoice getSc_puntos() {
        return sc_puntos;
    }

    public void setSelectpuntos(UISelectItems selectpuntos) {
        this.selectpuntos = selectpuntos;
    }

    public UISelectItems getSelectpuntos() {
        return selectpuntos;
    }

    public void setListaPuntos(List listaPuntos) {
        this.listaPuntos = listaPuntos;
    }

    public List getListaPuntos() {
        return listaPuntos;
    }


    public void setPanelFormGrafico(RichPanelFormLayout panelFormGrafico) {
        this.panelFormGrafico = panelFormGrafico;
    }

    public RichPanelFormLayout getPanelFormGrafico() {
        return panelFormGrafico;
    }

    public void setDatos1(RichActiveOutputText datos1) {
        this.datos1 = datos1;
    }

    public RichActiveOutputText getDatos1() {
        return datos1;
    }

    public void setDparamtro(RichActiveOutputText dparamtro) {
        this.dparamtro = dparamtro;
    }

    public RichActiveOutputText getDparamtro() {
        return dparamtro;
    }

    public void setDunidad(RichActiveOutputText dunidad) {
        this.dunidad = dunidad;
    }

    public RichActiveOutputText getDunidad() {
        return dunidad;
    }

    public void setLineGraph11(UIGraph lineGraph11) {
        this.lineGraph11 = lineGraph11;
    }

    public UIGraph getLineGraph11() {
        return lineGraph11;
    }
}
