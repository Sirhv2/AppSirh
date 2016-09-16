package co.gov.ideam.sirh.visor.view;

import co.gov.ideam.sirh.parametros.view.ParametrosDelegate;
import co.gov.ideam.sirh.usuarios.agua.model.Captacion;
import co.gov.ideam.sirh.usuarios.agua.view.UsuariosAguaDelegate;
import co.gov.ideam.sirh.util.ConstantesParametros;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.FacesUtils;
import co.gov.ideam.sirh.view.StandarBean;
import java.util.List;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;

import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.RichMenu;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectBooleanRadio;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.layout.RichDecorativeBox;
import oracle.adf.view.rich.component.rich.layout.RichPanelBox;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelSplitter;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandLink;
import oracle.adf.view.rich.component.rich.output.RichActiveOutputText;
import oracle.adf.view.rich.component.rich.output.RichImage;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.component.rich.output.RichPanelCollection;
import oracle.adf.view.rich.component.rich.output.RichSpacer;




public class DetalleCaptacionVistaBean  extends StandarBean{

    private Captacion captacion;
    //lista de tipos de fuente en categoria.
    private List listaTiposFuente;
    //lista de tipos de fuente en categoria.
    private List listaTipos;
       //Lista de provincias hidrogeologicas
    private List listaProvincias;
    //Lista de tipos de centros problados en categorias.
    private List listaTipoCentroPoblado;
     //Lista de tipos de punto en subterráneas.
    private List listaTipoPunto;
    //Lista de tipos de captación en lluvias.
    private List listaTipoCaptacion;
    //Lista de estados de captación.
    private List listaEstadoCaptacion;
    //Lista continuidad del caudal
    private List listaContinuidad;
    //Lista de componentes en agua subterranea y superficial.
    //Lista de los metodos de extracción
    private List listaMetodoExtraccion;
    //atributo para saber si es fuente superficial
    private Boolean superficial;
    //atributo para saber si es fuente subterranea
    private Boolean subterranea;
    //atributo para saber si es fuente lluvia
    private Boolean lluvia;
    //atributo para saber si fuente mineral
    private Boolean mineral;
    //atributo para saber si es fuente servida
    private Boolean servida;
    private RichForm form1;
    private RichDocument document1;
    private RichPanelStretchLayout panelStretchLayout1;
    private RichPanelSplitter panelSplitter1;
    private RichPanelStretchLayout panelStretchLayout3;
    private RichPanelGroupLayout panelGroupLayout1;
    private RichPanelBox panelBox1;
  
    private RichPanelGroupLayout panelGroupLayout2;
    private RichPanelGroupLayout panelGroupLayout3;

    private RichPanelFormLayout panelFormLayout2;
    private RichSpacer spacer4;
    private RichPanelGroupLayout panelGroupLayout7;
    private RichOutputText outputText4;
    private RichSpacer spacer5;
    private RichPanelGroupLayout panelGroupLayout8;
    private RichOutputText outputText5;
    private RichDecorativeBox decorativeBox7;
    private RichPanelGroupLayout panelGroupLayout10;
    private RichPanelGroupLayout panelGroupLayout11;
    private UISelectItems si_tipo_fuente;
    private RichSelectOneChoice socProvinciaHidro;
    private UISelectItems siProvinciaHidro;
    private RichSelectOneChoice socUnidadHidro;
    private UISelectItems siUnidadHidro;
    private RichSelectOneChoice socTipoCP;
    private UISelectItems siTipoCP;
    private RichInputText itNombreCentroPoblado;
    private RichInputText itUnidadHidrogeologica;
    private RichSelectOneChoice socTipoPunto;
    private UISelectItems siTipoPunto;
    private RichSelectOneChoice socTipoCaptacion;
    private UISelectItems siTipoCaptacion;
    private RichInputText itOfertaEstimada;
    private RichInputText itOferta;
    private RichInputText itAreaCaptacion;
    private RichInputText itCaudalVer;
     private RichInputText itCaudalDisegno;
    //Pilar
    private RichSelectBooleanRadio sbrPerteneceRed1;
    private RichSelectBooleanRadio sbrPerteneceRed2;
    private RichInputText itOfertaTotal;
    private RichInputText itOfertaDisponible;
    private RichInputText itOfertaSubterranea;
    private RichPanelGroupLayout panelGroupLayout29;
    private RichSpacer spacer20;
    private RichSpacer spacer21;
    private RichSpacer spacer22;
    private RichSpacer spacer23;
    private RichSpacer spacer24;
    private RichSpacer spacer25;
    private RichCommandLink clCaptaciones;
    private RichImage i1;
    private RichPanelCollection panelCollection1;
    private RichMenu mfunias; 
    private RichPanelCollection pc2;
    private RichInputText itIdentificadorPuntoSubt;
    private RichSelectOneChoice socTipoFuente;
    private UISelectItems siTipoFuente;
    private RichPanelGroupLayout panelGroupLayout4;
    private RichActiveOutputText activeOutputText2;
    private List listaUsos;
    private RichTable table1;
    private RichActiveOutputText otTipoFuente;
    private RichInputText it_fuente;
    private RichInputText itArea;
    private RichInputText itZona;
    private RichInputText itSubzona;
    private RichInputText itTramo;
    private RichInputText itDpto;
    private RichInputText itMcpo;
    private RichInputText itAutoridad;
    private RichPanelBox panelBox2;
    private RichSpacer spacer3;
    private RichPanelGroupLayout panelGroupLayout5;
    private RichActiveOutputText activeOutputText1;
    private RichSpacer spacer6;

    public DetalleCaptacionVistaBean(){
        FacesUtils.setActiveBean("detalleCaptacionVistaBean", "Detalle Captación",
                                 UsuariosAguaDelegate.class);
       
     this.load();
    }

    public void load(){
        try{
            java.util.Map params = null;
             params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
            String codigo = (String)FacesUtils.getFromSession("captacionVisor");
            if (codigo!=null&&params != null && params.get("captacion") != null) { 
                codigo=params.get("captacion").toString();
                System.out.println("codigo de captacion recibido en DetalleCaptacionVistaBean:"+codigo);
            }
            UsuariosAguaDelegate uad = UsuariosAguaDelegate.getInstance();
            ParametrosDelegate pd = ParametrosDelegate.getInstance();
           
              
              Long cod=  Long.parseLong(codigo);
              System.out.println(" solicitada desde Visor:"+cod);

              this.captacion = uad.getCaptacion(cod);
            System.out.println(" Consulta la BD:"+this.captacion.getCodigo());
                 FacesUtils.setInSession("captacionSeleccionada", this.captacion);
        
        
            this.listaTipos = this.cargarParametro(ConstantesParametros.
                                                   CATEGORIA_TIPO_FUENTE_CAPTA);
            this.listaTiposFuente = this.cargarParametro(ConstantesParametros.
                                                   CATEGORIA_TIPO_FUENTE);
            this.listaTipoCentroPoblado = this.cargarParametro(ConstantesParametros.
                                                   CATEGORIA_TIPO_CENTRO_POBLADO);
            this.listaProvincias = this.cargarParametro(ConstantesParametros.
                                                   CATEGORIA_PROVINCIA_HIDROGEOLOGICA);
           
            this.listaTipoPunto = this.cargarParametro(ConstantesParametros.
                                                   CATEGORIA_TIPO_PUNTO);
            this.listaTipoCaptacion = this.cargarParametro(ConstantesParametros.
                                                   CATEGORIA_TIPO_CAPTACION);
            this.listaEstadoCaptacion = this.cargarParametro(ConstantesParametros.
                                                   CATEGORIA_ESTADO_CAPTACION);

          
            if(this.captacion.getIdTipoCentroPoblado()!=null){
                this.captacion.setObjTipoCentroPoblado(pd.getLista(this.captacion.
                                                                   getIdTipoCentroPoblado()));
            }

            if(this.captacion.getEstadoCaptacion()!=null){
                this.captacion.setObjEstadoCaptacion(pd.getLista(this.captacion.
                                                                 getEstadoCaptacion()));
            }

            if(this.captacion.getSistemaReferencia()!=null){
                this.captacion.setObjSistemaReferencia(pd.getLista(this.captacion.
                                                                   getSistemaReferencia()));
            }

            if(this.captacion.getProvinciaHidrogeologica()!=null){
                this.captacion.setObjProvinciaHidrogeologica(pd.getLista(this.captacion.
                                                                         getProvinciaHidrogeologica()));
            }

            if(this.captacion.getTipoPunto()!=null){
                this.captacion.setObjTipoPunto(pd.getLista(this.captacion.
                                                           getTipoPunto()));
            }

            if(this.captacion.getMetodoExtraccion()!=null){
                this.captacion.setObjMetodoExtraccion(pd.getLista(this.captacion.
                                                                  getMetodoExtraccion()));
            }

            if(this.captacion.getTipoCaptacion()!=null){
                this.captacion.setObjTipoCaptacion(pd.getLista(this.captacion.
                                                               getTipoCaptacion()));
            }

            if(this.captacion.getTipoFuenteCaptacion()!=null){
                this.captacion.setObjTipoFuenteCaptacion(pd.getLista(this.captacion.
                                                                     getTipoFuenteCaptacion()));
            }

            this.otTipoFuente = new RichActiveOutputText();
            if(this.captacion.getObjTipoFuenteCaptacion()!=null){
                this.otTipoFuente.setValue( this.getCaptacion().
                                           getObjTipoFuenteCaptacion().getValor());
            }

            this.listaUsos = uad.getUsos(this.captacion);
                   
                 
               
        }catch(IdeamException e){
            showMessage(e);
        }
       
    }


    public void setCaptacion(Captacion captacion) {
        this.captacion = captacion;
    }

    public Captacion getCaptacion() {
        return captacion;
    }

    public void setListaTipos(List listaTipos) {
        this.listaTipos = listaTipos;
    }

    public List getListaTipos() {
        return listaTipos;
    }

    public void setListaTiposFuente(List listaTiposFuente) {
        this.listaTiposFuente = listaTiposFuente;
    }

    public List getListaTiposFuente() {
        return listaTiposFuente;
    }


 

    public void setListaProvincias(List listaProvincias) {
        this.listaProvincias = listaProvincias;
    }

    public List getListaProvincias() {
        return listaProvincias;
    }

    public void setListaTipoCentroPoblado(List listaTipoCentroPoblado) {
        this.listaTipoCentroPoblado = listaTipoCentroPoblado;
    }

    public List getListaTipoCentroPoblado() {
        return listaTipoCentroPoblado;
    }

  

    public void setListaTipoPunto(List listaTipoPunto) {
        this.listaTipoPunto = listaTipoPunto;
    }

    public List getListaTipoPunto() {
        return listaTipoPunto;
    }

    public void setListaTipoCaptacion(List listaTipoCaptacion) {
        this.listaTipoCaptacion = listaTipoCaptacion;
    }

    public List getListaTipoCaptacion() {
        return listaTipoCaptacion;
    }

    public void setListaEstadoCaptacion(List listaEstadoCaptacion) {
        this.listaEstadoCaptacion = listaEstadoCaptacion;
    }

    public List getListaEstadoCaptacion() {
        return listaEstadoCaptacion;
    }

    public void setListaContinuidad(List listaContinuidad) {
        this.listaContinuidad = listaContinuidad;
    }

    public List getListaContinuidad() {
        return listaContinuidad;
    }

  

    public void setListaMetodoExtraccion(List listaMetodoExtraccion) {
        this.listaMetodoExtraccion = listaMetodoExtraccion;
    }

    public List getListaMetodoExtraccion() {
        return listaMetodoExtraccion;
    }


    public void setSuperficial(Boolean superficial) {
        this.superficial = superficial;
    }

    public Boolean getSuperficial() {
        if( this.captacion.getTipoFuenteCaptacion().longValue()==ConstantesParametros.LISTA_AGUAS_SUPERFICIALES){
            return true;
        }else{
            return false;
        }
        //return superficial;
    }

    public void setSubterranea(Boolean subterranea) {
        this.subterranea = subterranea;
    }

    public Boolean getSubterranea() {
        if(this.captacion.getTipoFuenteCaptacion().longValue()==ConstantesParametros.LISTA_AGUAS_SUBTERRANEAS){
            return true;
        }else{
            return false;
        }
        //return subterranea;
    }

    public void setLluvia(Boolean lluvia) {
        this.lluvia = lluvia;
    }

    public Boolean getLluvia() {
        if(this.captacion.getTipoFuenteCaptacion().longValue()==ConstantesParametros.LISTA_AGUAS_LLUVIAS){
            return true;
        }else{
            return false;
        }
        //return lluvia;
    }

    public void setMineral(Boolean mineral) {
        this.mineral = mineral;
    }

    public Boolean getMineral() {
        if(this.captacion.getTipoFuenteCaptacion().longValue()==ConstantesParametros.LISTA_AGUAS_MINERALES){
            return true;
        }else{
            return false;
        }
        //return mineral;
    }

    public void setServida(Boolean servida) {
        this.servida = servida;
    }

    public Boolean getServida() {
        if(this.captacion.getTipoFuenteCaptacion().longValue()==ConstantesParametros.LISTA_AGUAS_SERVIDAS){
            return true;
        }else{
            return false;
        }
        //return servida;
    }

   

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


    public void setPanelStretchLayout3(RichPanelStretchLayout panelStretchLayout3) {
        this.panelStretchLayout3 = panelStretchLayout3;
    }

    public RichPanelStretchLayout getPanelStretchLayout3() {
        return panelStretchLayout3;
    }

    public void setPanelGroupLayout1(RichPanelGroupLayout panelGroupLayout1) {
        this.panelGroupLayout1 = panelGroupLayout1;
    }

    public RichPanelGroupLayout getPanelGroupLayout1() {
        return panelGroupLayout1;
    }

    public void setPanelBox1(RichPanelBox panelBox1) {
        this.panelBox1 = panelBox1;
    }

    public RichPanelBox getPanelBox1() {
        return panelBox1;
    }

  

    public void setPanelGroupLayout2(RichPanelGroupLayout panelGroupLayout2) {
        this.panelGroupLayout2 = panelGroupLayout2;
    }

    public RichPanelGroupLayout getPanelGroupLayout2() {
        return panelGroupLayout2;
    }

    public void setPanelGroupLayout3(RichPanelGroupLayout panelGroupLayout3) {
        this.panelGroupLayout3 = panelGroupLayout3;
    }

    public RichPanelGroupLayout getPanelGroupLayout3() {
        return panelGroupLayout3;
    }


    public void setPanelFormLayout2(RichPanelFormLayout panelFormLayout2) {
        this.panelFormLayout2 = panelFormLayout2;
    }

    public RichPanelFormLayout getPanelFormLayout2() {
        return panelFormLayout2;
    }


    public void setDecorativeBox7(RichDecorativeBox decorativeBox7) {
        this.decorativeBox7 = decorativeBox7;
    }

    public RichDecorativeBox getDecorativeBox7() {
        return decorativeBox7;
    }

    public void setPanelGroupLayout10(RichPanelGroupLayout panelGroupLayout10) {
        this.panelGroupLayout10 = panelGroupLayout10;
    }

    public RichPanelGroupLayout getPanelGroupLayout10() {
        return panelGroupLayout10;
    }

    public void setPanelGroupLayout11(RichPanelGroupLayout panelGroupLayout11) {
        this.panelGroupLayout11 = panelGroupLayout11;
    }

    public RichPanelGroupLayout getPanelGroupLayout11() {
        return panelGroupLayout11;
    }

    


    public void setSi_tipo_fuente(UISelectItems si_tipo_fuente) {
        this.si_tipo_fuente = si_tipo_fuente;
    }

    public UISelectItems getSi_tipo_fuente() {
        return si_tipo_fuente;
    }

    public void setSocProvinciaHidro(RichSelectOneChoice socProvinciaHidro) {
        this.socProvinciaHidro = socProvinciaHidro;
    }

    public RichSelectOneChoice getSocProvinciaHidro() {
        return socProvinciaHidro;
    }

    public void setSiProvinciaHidro(UISelectItems siProvinciaHidro) {
        this.siProvinciaHidro = siProvinciaHidro;
    }

    public UISelectItems getSiProvinciaHidro() {
        return siProvinciaHidro;
    }

    public void setSocUnidadHidro(RichSelectOneChoice socUnidadHidro) {
        this.socUnidadHidro = socUnidadHidro;
    }

    public RichSelectOneChoice getSocUnidadHidro() {
        return socUnidadHidro;
    }

    public void setSiUnidadHidro(UISelectItems siUnidadHidro) {
        this.siUnidadHidro = siUnidadHidro;
    }

    public UISelectItems getSiUnidadHidro() {
        return siUnidadHidro;
    }


    public void setSocTipoCP(RichSelectOneChoice socTipoCP) {
        this.socTipoCP = socTipoCP;
    }

    public RichSelectOneChoice getSocTipoCP() {
        return socTipoCP;
    }

    public void setSiTipoCP(UISelectItems siTipoCP) {
        this.siTipoCP = siTipoCP;
    }

    public UISelectItems getSiTipoCP() {
        return siTipoCP;
    }

    public void setItNombreCentroPoblado(RichInputText itNombreCentroPoblado) {
        this.itNombreCentroPoblado = itNombreCentroPoblado;
    }

    public RichInputText getItNombreCentroPoblado() {
        return itNombreCentroPoblado;
    }

    public void setItUnidadHidrogeologica(RichInputText itUnidadHidrogeologica) {
        this.itUnidadHidrogeologica = itUnidadHidrogeologica;
    }

    public RichInputText getItUnidadHidrogeologica() {
        return itUnidadHidrogeologica;
    }

    public void setSocTipoPunto(RichSelectOneChoice socTipoPunto) {
        this.socTipoPunto = socTipoPunto;
    }

    public RichSelectOneChoice getSocTipoPunto() {
        return socTipoPunto;
    }

    public void setSiTipoPunto(UISelectItems siTipoPunto) {
        this.siTipoPunto = siTipoPunto;
    }

    public UISelectItems getSiTipoPunto() {
        return siTipoPunto;
    }

    public void setSocTipoCaptacion(RichSelectOneChoice socTipoCaptacion) {
        this.socTipoCaptacion = socTipoCaptacion;
    }

    public RichSelectOneChoice getSocTipoCaptacion() {
        return socTipoCaptacion;
    }

    public void setSiTipoCaptacion(UISelectItems siTipoCaptacion) {
        this.siTipoCaptacion = siTipoCaptacion;
    }

    public UISelectItems getSiTipoCaptacion() {
        return siTipoCaptacion;
    }

    public void setItOfertaEstimada(RichInputText itOfertaEstimada) {
        this.itOfertaEstimada = itOfertaEstimada;
    }

    public RichInputText getItOfertaEstimada() {
        return itOfertaEstimada;
    }

    public void setItOferta(RichInputText itOferta) {
        this.itOferta = itOferta;
    }

    public RichInputText getItOferta() {
        return itOferta;
    }

    public void setItAreaCaptacion(RichInputText itAreaCaptacion) {
        this.itAreaCaptacion = itAreaCaptacion;
    }

    public RichInputText getItAreaCaptacion() {
        return itAreaCaptacion;
    }

    public void setItCaudalVer(RichInputText itCaudalVer) {
        this.itCaudalVer = itCaudalVer;
    }

    public RichInputText getItCaudalVer() {
        return itCaudalVer;
    }


    public void setItOfertaTotal(RichInputText itOfertaTotal) {
        this.itOfertaTotal = itOfertaTotal;
    }

    public RichInputText getItOfertaTotal() {
        return itOfertaTotal;
    }

    public void setItOfertaDisponible(RichInputText itOfertaDisponible) {
        this.itOfertaDisponible = itOfertaDisponible;
    }

    public RichInputText getItOfertaDisponible() {
        return itOfertaDisponible;
    }

    public void setItOfertaSubterranea(RichInputText itOfertaSubterranea) {
        this.itOfertaSubterranea = itOfertaSubterranea;
    }

    public RichInputText getItOfertaSubterranea() {
        return itOfertaSubterranea;
    }


    public void setI1(RichImage i1) {
        this.i1 = i1;
    }

    public RichImage getI1() {
        return i1;
    }


    public void setPanelGroupLayout29(RichPanelGroupLayout panelGroupLayout29) {
        this.panelGroupLayout29 = panelGroupLayout29;
    }

    public RichPanelGroupLayout getPanelGroupLayout29() {
        return panelGroupLayout29;
    }

    public void setSpacer20(RichSpacer spacer20) {
        this.spacer20 = spacer20;
    }

    public RichSpacer getSpacer20() {
        return spacer20;
    }

    public void setSpacer21(RichSpacer spacer21) {
        this.spacer21 = spacer21;
    }

    public RichSpacer getSpacer21() {
        return spacer21;
    }

    public void setSpacer22(RichSpacer spacer22) {
        this.spacer22 = spacer22;
    }

    public RichSpacer getSpacer22() {
        return spacer22;
    }

    public void setSpacer23(RichSpacer spacer23) {
        this.spacer23 = spacer23;
    }

    public RichSpacer getSpacer23() {
        return spacer23;
    }

    public void setSpacer24(RichSpacer spacer24) {
        this.spacer24 = spacer24;
    }

    public RichSpacer getSpacer24() {
        return spacer24;
    }

    public void setSpacer25(RichSpacer spacer25) {
        this.spacer25 = spacer25;
    }

    public RichSpacer getSpacer25() {
        return spacer25;
    }



    public void setClCaptaciones(RichCommandLink clCaptaciones) {
        this.clCaptaciones = clCaptaciones;
    }

    public RichCommandLink getClCaptaciones() {
        return clCaptaciones;
    }


    public void setPanelCollection1(RichPanelCollection panelCollection1) {
        this.panelCollection1 = panelCollection1;
    }

    public RichPanelCollection getPanelCollection1() {
        return panelCollection1;
    }


    public void setMfunias(RichMenu menu2) {
        this.mfunias = menu2;
    }

    public RichMenu getMfunias() {
        return mfunias;
    }

  

  

    public void setPc2(RichPanelCollection panelCollection2) {
        this.pc2 = panelCollection2;
    }

    public RichPanelCollection getPc2() {
        return pc2;
    }


    public void setItIdentificadorPuntoSubt(RichInputText inputText1) {
        this.itIdentificadorPuntoSubt = inputText1;
    }

    public RichInputText getItIdentificadorPuntoSubt() {
        return itIdentificadorPuntoSubt;
    }

   

    public void setSocTipoFuente(RichSelectOneChoice selectOneChoice1) {
        this.socTipoFuente = selectOneChoice1;
    }

    public RichSelectOneChoice getSocTipoFuente() {
        return socTipoFuente;
    }

    public void setSiTipoFuente(UISelectItems selectItems1) {
        this.siTipoFuente = selectItems1;
    }

    public UISelectItems getSiTipoFuente() {
        return siTipoFuente;
    }

    public void setSbrPerteneceRed1(RichSelectBooleanRadio sbrPerteneceRed1) {
        this.sbrPerteneceRed1 = sbrPerteneceRed1;
    }

    public RichSelectBooleanRadio getSbrPerteneceRed1() {
        return sbrPerteneceRed1;
    }

    public void setSbrPerteneceRed2(RichSelectBooleanRadio sbrPerteneceRed2) {
        this.sbrPerteneceRed2 = sbrPerteneceRed2;
    }

    public RichSelectBooleanRadio getSbrPerteneceRed2() {
        return sbrPerteneceRed2;
    }


    public void setItCaudalDisegno(RichInputText itCaudalDisegno) {
        this.itCaudalDisegno = itCaudalDisegno;
    }

    public RichInputText getItCaudalDisegno() {
        return itCaudalDisegno;
    }

    public Boolean getSuperficial1() {
        return superficial;
    }

    public Boolean getSubterranea1() {
        return subterranea;
    }

    public Boolean getLluvia1() {
        return lluvia;
    }

    public Boolean getMineral1() {
        return mineral;
    }

    public Boolean getServida1() {
        return servida;
    }

    public void setSpacer4(RichSpacer spacer4) {
        this.spacer4 = spacer4;
    }

    public RichSpacer getSpacer4() {
        return spacer4;
    }

    public void setPanelGroupLayout7(RichPanelGroupLayout panelGroupLayout7) {
        this.panelGroupLayout7 = panelGroupLayout7;
    }

    public RichPanelGroupLayout getPanelGroupLayout7() {
        return panelGroupLayout7;
    }

    public void setOutputText4(RichOutputText outputText4) {
        this.outputText4 = outputText4;
    }

    public RichOutputText getOutputText4() {
        return outputText4;
    }

    public void setSpacer5(RichSpacer spacer5) {
        this.spacer5 = spacer5;
    }

    public RichSpacer getSpacer5() {
        return spacer5;
    }

    public void setPanelGroupLayout8(RichPanelGroupLayout panelGroupLayout8) {
        this.panelGroupLayout8 = panelGroupLayout8;
    }

    public RichPanelGroupLayout getPanelGroupLayout8() {
        return panelGroupLayout8;
    }

    public void setOutputText5(RichOutputText outputText5) {
        this.outputText5 = outputText5;
    }

    public RichOutputText getOutputText5() {
        return outputText5;
    }


    public void setPanelGroupLayout4(RichPanelGroupLayout panelGroupLayout4) {
        this.panelGroupLayout4 = panelGroupLayout4;
    }

    public RichPanelGroupLayout getPanelGroupLayout4() {
        return panelGroupLayout4;
    }


    public void setActiveOutputText2(RichActiveOutputText activeOutputText2) {
        this.activeOutputText2 = activeOutputText2;
    }

    public RichActiveOutputText getActiveOutputText2() {
        return activeOutputText2;
    }




    public void setListaUsos(List listaUsos) {
        this.listaUsos = listaUsos;
    }

    public List getListaUsos() {
        return listaUsos;
    }


    public void setTable1(RichTable table1) {
        this.table1 = table1;
    }

    public RichTable getTable1() {
        return table1;
    }


    public void setOtTipoFuente(RichActiveOutputText activeOutputText3) {
        this.otTipoFuente = activeOutputText3;
    }

    public RichActiveOutputText getOtTipoFuente() {
        return otTipoFuente;
    }

    public void setIt_fuente(RichInputText inputText1) {
        this.it_fuente = inputText1;
    }

    public RichInputText getIt_fuente() {
        return it_fuente;
    }

    public void setItArea(RichInputText inputText1) {
        this.itArea = inputText1;
    }

    public RichInputText getItArea() {
        return itArea;
    }

    public void setItZona(RichInputText inputText1) {
        this.itZona = inputText1;
    }

    public RichInputText getItZona() {
        return itZona;
    }

    public void setItSubzona(RichInputText inputText1) {
        this.itSubzona = inputText1;
    }

    public RichInputText getItSubzona() {
        return itSubzona;
    }

    public void setItTramo(RichInputText inputText1) {
        this.itTramo = inputText1;
    }

    public RichInputText getItTramo() {
        return itTramo;
    }

    public void setItDpto(RichInputText inputText1) {
        this.itDpto = inputText1;
    }

    public RichInputText getItDpto() {
        return itDpto;
    }

    public void setItMcpo(RichInputText inputText2) {
        this.itMcpo = inputText2;
    }

    public RichInputText getItMcpo() {
        return itMcpo;
    }

    public void setItAutoridad(RichInputText inputText1) {
        this.itAutoridad = inputText1;
    }

    public RichInputText getItAutoridad() {
        return itAutoridad;
    }


    public void setPanelBox2(RichPanelBox panelBox2) {
        this.panelBox2 = panelBox2;
    }

    public RichPanelBox getPanelBox2() {
        return panelBox2;
    }


    public void setSpacer3(RichSpacer spacer3) {
        this.spacer3 = spacer3;
    }

    public RichSpacer getSpacer3() {
        return spacer3;
    }

    public void setPanelGroupLayout5(RichPanelGroupLayout panelGroupLayout5) {
        this.panelGroupLayout5 = panelGroupLayout5;
    }

    public RichPanelGroupLayout getPanelGroupLayout5() {
        return panelGroupLayout5;
    }

    public void setActiveOutputText1(RichActiveOutputText activeOutputText1) {
        this.activeOutputText1 = activeOutputText1;
    }

    public RichActiveOutputText getActiveOutputText1() {
        return activeOutputText1;
    }

    public void setSpacer6(RichSpacer spacer6) {
        this.spacer6 = spacer6;
    }

    public RichSpacer getSpacer6() {
        return spacer6;
    }
}
