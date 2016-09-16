package co.gov.ideam.sirh.calidad.view;

import co.gov.ideam.sirh.calidad.model.CriteriosBusquedaPuntos;
import co.gov.ideam.sirh.calidad.model.PuntoMonitoreo;
import co.gov.ideam.sirh.calidad.model.PuntoMonitoreoTO;
import co.gov.ideam.sirh.fuentes.model.FnttFuente;
import co.gov.ideam.sirh.parametros.model.Autoridades;

import co.gov.ideam.sirh.util.ConstantesCalidad;
import co.gov.ideam.sirh.parametros.model.Lista;
import co.gov.ideam.sirh.parametros.model.PartZonificListas;
import co.gov.ideam.sirh.reportes.view.ReporteDelegate;
import co.gov.ideam.sirh.seguridad.model.PerfilVO;
import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;

import co.gov.ideam.sirh.seguridad.view.SeguridadDelegate;
import co.gov.ideam.sirh.usuarios.agua.model.UsuarioAgua;
import co.gov.ideam.sirh.util.Constantes;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.FacesUtils;

import co.gov.ideam.sirh.view.StandarBean;

import java.util.ArrayList;


import java.util.HashMap;

import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.output.RichPanelCollection;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UISelectItems;
import javax.faces.event.ActionEvent;

import javax.faces.event.ValueChangeEvent;

import javax.faces.model.SelectItem;

import oracle.adf.view.rich.component.rich.RichMenu;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.nav.RichCommandMenuItem;
import oracle.adf.view.rich.component.rich.output.RichActiveOutputText;
import oracle.adf.view.rich.component.rich.output.RichSpacer;
import oracle.adf.view.rich.context.AdfFacesContext;


public class PuntosdeMonitoreoBean  extends StandarBean{
  
  private RichForm form1;
  private RichDocument document1;
  private RichPanelStretchLayout panelStretchLayout1;
  private RichPanelGroupLayout panelGroupLayout1;
  private RichPanelCollection panelCollection1;
  private List listaPuntosMonitoreo;
  private RichTable t_puntosMonitoreo;
  private RichMenu m_archivo;
  private RichCommandMenuItem cmi_adicionar;
  private RichCommandMenuItem cmi_editar;
  private String accionAdicionar;
  private RichSelectOneChoice selectOneChoiceTipoPunto;
  private RichSelectOneChoice selectOneChoiceArea;
  private UISelectItems selectItems1;

  private RichCommandButton cb_filtrar;

  private RichInputText filtro_nombre;
  private List listaTipoPuntos;
  private List listaUbicacion;
  private List listaArea;
  private List listaZona;
  private List listaSubzona;
  private List listaFuentes;
  private RichSelectOneChoice selectOneChoiceZona;
  private UISelectItems selectItems5;
  private RichSelectOneChoice selectOneChoiceSubzona;
  private UISelectItems selectItems3;
    private RichPanelGroupLayout panelGroupLayout2;
    private RichActiveOutputText titulo;
    private RichSpacer spacer1;
    private RichSelectOneChoice selectOneChoiceFuente;
    private UISelectItems selectItems2;
    private RichCommandMenuItem cmiImprimir;
    private RichCommandMenuItem cmi_ImprimirListado;

    private List listaPar;
    private List listaMedicionesPunto;
    private RichCommandMenuItem cmi_listadoMediciones;
    private String visiblePerfil=null;
    
    public PuntosdeMonitoreoBean(){
       FacesUtils.setActiveBean("puntosdeMonitoreo",
                                "PuntosdeMonitoreo",
                                CalidadDelegate.class);
       FacesUtils.removeManagedBeanFromSession("muestrasPunto");
       FacesUtils.removeManagedBeanFromSession("detallePuntoMonitoreo");
       FacesUtils.removeManagedBeanFromSession("adicionarPuntosMonitoreo");
       FacesUtils.removeManagedBeanFromSession("MuestrasTreeHandler");
       FacesUtils.removeFromSession("tramoSeleccionado");
       
     
       this.load();        
   }
   public void load(){
     
       try{      
         
         this.listaTipoPuntos = this.getListaPorCategoria(ConstantesCalidad.TIPOS_PUNTO_MONITOREO);
         this.listaUbicacion = this.getListaPorCategoria(ConstantesCalidad.UBICACION_PUNTO_MONITOREO);
         this.listaArea = this.cargarZonificacion(null);
         this.listaZona = new ArrayList();
         this.listaSubzona = new ArrayList();
        this.listaFuentes = new ArrayList();
           cmi_adicionar= new RichCommandMenuItem();
           cmi_editar= new RichCommandMenuItem();
           
           
           UsuarioConectadoTO usuarioConectado = 
               (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
           
         CalidadDelegate cald = CalidadDelegate.getInstance();
         if (usuarioConectado.getUsuario().getAutoridadAmbiental()==null ||
             usuarioConectado.getUsuario().getAutoridadAmbiental().getId() == null ||
             usuarioConectado.getUsuario().getAutoridadAmbiental().getId().longValue() ==0 ||
             usuarioConectado.getUsuario().getAutoridadAmbiental().getId().longValue() == Constantes.IDEAM){
           listaPuntosMonitoreo = cald.getAllPuntosMonitoreo();
             } else {   
             
                         SeguridadDelegate seg = SeguridadDelegate.getInstance(); 
                         PerfilVO pp= new PerfilVO();
                         pp=   seg.getPerfilUsuario(usuarioConectado.getUsuario().getCodigo());
                             
                         if (pp.getCodigo()==ConstantesCalidad.PERFIL_CONSULTA){
                                listaPuntosMonitoreo = cald.getAllPuntosMonitoreo();
                                this.visiblePerfil="false";
                        }else{  
                                this.visiblePerfil="true";
                                listaPuntosMonitoreo = cald.getAllPuntosMonitoreo(usuarioConectado.getUsuario().
                                                            getAutoridadAmbiental().getId().longValue());
                             }
                   } 
           listaPar=this.cargarAliasParametros();
                
       }catch(IdeamException e){
           showMessage(e);
       }
   }
   
   
   
    public String sqlCroosTab(Long idpunto){
        
             System.out.println("+++++++++++++++++++++ entre a nn +++++++++++++++++++++++++++++++++++++++");
        String sql ="";
        int nro= 2;
            sql= sql+ "SELECT c1.fecha, c1.nombre_punto,  ";
            
        for(int i= 0; i<this.listaPar.size(); i++){
                SelectItem item = (SelectItem)this.listaPar.get(i);
                sql= sql+ "c"+nro+".valor as "+ item.getLabel() +",c"+nro+".unidad as "+"unidad"+nro+",";
                nro=nro+1;
            }
        sql= sql.substring(0, sql.length()-1);
        sql = sql + "  from calt_mediciones_v c1 ";
            nro= 2;
            for(int j= 0; j<this.listaPar.size(); j++){
                    SelectItem item = (SelectItem)this.listaPar.get(j);
                    sql= sql+ " left join calt_mediciones_v c"+nro+" on c1.fecha=c"+nro+".fecha and c"+nro+".id_parametro='"+item.getValue()+"' and c1.id=c"+nro+".id and c1.idpunto="+idpunto    ;
                    nro=nro+1;
                }
            
            nro= 2;
            sql= sql + " where c1.idpunto="+idpunto+" \n  group by c1.fecha,  c1.nombre_punto,";
            for(int j= 0; j<this.listaPar.size(); j++){
                   
                    sql= sql+ " c"+nro+".id_parametro , c"+nro+".valor, c"+nro+".unidad,"  ;             
                    nro=nro+1;
                }
            sql= sql.substring(0, sql.length()-1);
            
            sql= sql + "   order by  c1.nombre_punto, c1.fecha ";
             

          System.out.println("sqlCroosTab = "+sql);
        return sql;
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

  public void setPanelGroupLayout1(RichPanelGroupLayout panelGroupLayout1) {
    this.panelGroupLayout1 = panelGroupLayout1;
  }

  public RichPanelGroupLayout getPanelGroupLayout1() {
    return panelGroupLayout1;
  }

  public void setPanelCollection1(RichPanelCollection panelCollection1) {
    this.panelCollection1 = panelCollection1;
  }

  public RichPanelCollection getPanelCollection1() {
    return panelCollection1;
  }


  public void setListaPuntosMonitoreo(List listaPuntosMonitoreo) {
    this.listaPuntosMonitoreo = listaPuntosMonitoreo;
  }

  public List getListaPuntosMonitoreo() {
    return listaPuntosMonitoreo;
  }

  public void setT_puntosMonitoreo(RichTable t_puntosMonitoreo) {
    this.t_puntosMonitoreo = t_puntosMonitoreo;
  }

  public RichTable getT_puntosMonitoreo() {
    return t_puntosMonitoreo;
  }

  public void setCmi_adicionar(RichCommandMenuItem cmi_adicionar) {
    this.cmi_adicionar = cmi_adicionar;
  }

  public RichCommandMenuItem getCmi_adicionar() {
    return cmi_adicionar;
  }

  public void setCmi_editar(RichCommandMenuItem cmi_editar) {
    this.cmi_editar = cmi_editar;
  }

  public RichCommandMenuItem getCmi_editar() {
    return cmi_editar;
  }

  public void setArchivo(RichMenu m_archivo) {
    this.m_archivo = m_archivo;
  }

  public RichMenu getArchivo() {
    return m_archivo;
  }
  
  
  public void cmi_adicionar_actionListener(ActionEvent actionEvent) {
      accionAdicionar = "";
      // Si el usuario conectado no tiene autoridad ambiental o 
      // la autoridad es Ideam, no se permite realizar la accion
      UsuarioConectadoTO usuarioConectado = 
          (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
      
      Autoridades autoridadAmbiental = usuarioConectado.getUsuario().getAutoridadAmbiental();
      if(autoridadAmbiental==null ||
         autoridadAmbiental.getId().intValue() == Constantes.IDEAM){
          showMessage(getText("NO_EXISTE_AUTORIDAD_AMBIENTAL"),
                      FacesMessage.SEVERITY_ERROR);       
      }else{
          accionAdicionar = "adicionarpunto";
      }
  }
  
  public String getAccionAdicionar() {
      return accionAdicionar;
  }

  public void setAccionAdicionar(String accionAdicionar) {
      this.accionAdicionar = accionAdicionar;
  }

  public void setSelectOneChoiceTipoPunto(RichSelectOneChoice tipo_punto) {
    this.selectOneChoiceTipoPunto = tipo_punto;
  }

  public RichSelectOneChoice getSelectOneChoiceTipoPunto() {
    return selectOneChoiceTipoPunto;
  }

  public void setCb_filtrar(RichCommandButton cb_filtrar) {
    this.cb_filtrar = cb_filtrar;
  }

  public RichCommandButton getCb_filtrar() {
    return cb_filtrar;
  }
  

  public void setListaTipoPuntos(List listaTipoPuntos) {
    this.listaTipoPuntos = listaTipoPuntos;
  }

  public List getListaTipoPuntos() {
    return listaTipoPuntos;
  }

  public void setListaUbicacion(List listaUbicacion) {
    this.listaUbicacion = listaUbicacion;
  }

  public List getListaUbicacion() {
    return listaUbicacion;
  }


  public void setFiltro_nombre(RichInputText filtro_nombre) {
    this.filtro_nombre = filtro_nombre;
  }

  public RichInputText getFiltro_nombre() {
    return filtro_nombre;
  }
  
  
  public void cb_filtrar_actionListener(ActionEvent actionEvent) {
      CriteriosBusquedaPuntos criterios = new CriteriosBusquedaPuntos();
    
      if(this.selectOneChoiceTipoPunto.getValue()!=null){
          criterios.setListatipoPunto((Lista)selectOneChoiceTipoPunto.getValue());
      }
  
        if(this.filtro_nombre.getValue()!=null &&
           this.filtro_nombre.getValue().toString().length()>0){
            criterios.setNombre(filtro_nombre.getValue().toString());       
        }
    
      if(this.selectOneChoiceArea.getValue()!=null){
          criterios.setArea(
              (PartZonificListas)this.selectOneChoiceArea.getValue());
      }
      if(this.selectOneChoiceZona.getValue()!=null){
          criterios.setZona(
              (PartZonificListas)this.selectOneChoiceZona.getValue());
      }
    
      if(this.selectOneChoiceSubzona.getValue()!=null){
        criterios.setSubzona(
            (PartZonificListas)this.selectOneChoiceSubzona.getValue());
    }
    
    
      if(this.selectOneChoiceFuente.getValue()!=null){
         criterios.setListaFuente(
                (FnttFuente)this.selectOneChoiceFuente.getValue());
     }
      
    
      try{
          UsuarioConectadoTO usuarioConectado = 
              (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
              CalidadDelegate cald = CalidadDelegate.getInstance();
         if (usuarioConectado.getUsuario().getAutoridadAmbiental()==null ||
              usuarioConectado.getUsuario().getAutoridadAmbiental().getId() == null ||
              usuarioConectado.getUsuario().getAutoridadAmbiental().getId().longValue() ==0 ||
              usuarioConectado.getUsuario().getAutoridadAmbiental().getId().longValue() == Constantes.IDEAM){
             this.listaPuntosMonitoreo = cald.getPuntosMonitoreo(criterios);
              } else {   
              
              SeguridadDelegate seg = SeguridadDelegate.getInstance(); 
              PerfilVO pp= new PerfilVO();
              pp=   seg.getPerfilUsuario(usuarioConectado.getUsuario().getCodigo());
                  
              if (pp.getCodigo()==ConstantesCalidad.PERFIL_CONSULTA){
                  this.listaPuntosMonitoreo = cald.getPuntosMonitoreo(criterios);
                    
              }else{  
          this.listaPuntosMonitoreo= new ArrayList();
          this.listaPuntosMonitoreo = cald.getPuntosMonitoreo(criterios, usuarioConectado.getUsuario().
                                                                   getAutoridadAmbiental().
                                                                   getId().
                                                                   longValue());
              }   }
        AdfFacesContext.getCurrentInstance().addPartialTarget(t_puntosMonitoreo);
      }catch(IdeamException e){
          showMessage(e);
      }
  }

  public void setListaArea(List listaArea) {
    this.listaArea = listaArea;
  }

  public List getListaArea() {
    return listaArea;
  }

  public void setListaZona(List listaZona) {
    this.listaZona = listaZona;
  }

  public List getListaZona() {
    return listaZona;
  }

  public void setListaSubzona(List listaSubzona) {
    this.listaSubzona = listaSubzona;
  }

  public List getListaSubzona() {
    return listaSubzona;
  }


  public void setSelectOneChoiceArea(RichSelectOneChoice selectOneChoice1) {
    this.selectOneChoiceArea = selectOneChoice1;
  }

  public RichSelectOneChoice getSelectOneChoiceArea() {
    return selectOneChoiceArea;
  }

  public void setSelectItems1(UISelectItems selectItems1) {
    this.selectItems1 = selectItems1;
  }

  public UISelectItems getSelectItems1() {
    return selectItems1;
  }


    //ChangeListener Area
  public void selectArea_valueChangeListener(ValueChangeEvent event)
          throws IdeamException{
      Object area = event.getNewValue();
      this.listaZona = new ArrayList();
  
      try{
          if(area!=null){
              this.listaZona = this.cargarZonificacion(((PartZonificListas)area).getId());
              
          }
      }catch(IdeamException e){            
          showMessage(e);
      }
      AdfFacesContext.getCurrentInstance().addPartialTarget(selectOneChoiceZona);

  }
  
    //ChangeListener Zona
    public void selectZona_valueChangeListener(ValueChangeEvent event) 
           throws IdeamException{
           Object zona = event.getNewValue();
          this.listaSubzona = new ArrayList();
          try{
              if(zona!=null){
                  this.listaSubzona = this.cargarZonificacion(((PartZonificListas)zona).getId());
              }
          }catch(IdeamException e){            
              showMessage(e);
          }
          AdfFacesContext.getCurrentInstance().addPartialTarget(selectOneChoiceSubzona);
      

    }
  
  
    //ChangeListener Subzona
    public void selecSubzona_valueChangeListener(ValueChangeEvent event) {
      Object subzona = event.getNewValue();
      this.listaFuentes = new ArrayList();
    
      try{
          if(subzona!=null){
              this.listaFuentes = this.getListaFuentes(((PartZonificListas)subzona).getId());
             
          }
      }catch(IdeamException e){            
          showMessage(e);
      }
      AdfFacesContext.getCurrentInstance().addPartialTarget(selectOneChoiceFuente);

    }



  public void setSelectOneChoiceZona(RichSelectOneChoice selectOneChoice5) {
    this.selectOneChoiceZona = selectOneChoice5;
  }

  public RichSelectOneChoice getSelectOneChoiceZona() {
    return selectOneChoiceZona;
  }

  public void setSelectItems5(UISelectItems selectItems5) {
    this.selectItems5 = selectItems5;
  }

  public UISelectItems getSelectItems5() {
    return selectItems5;
  }


  public void setSelectOneChoiceSubzona(RichSelectOneChoice selectOneChoice3) {
    this.selectOneChoiceSubzona = selectOneChoice3;
  }

  public RichSelectOneChoice getSelectOneChoiceSubzona() {
    return selectOneChoiceSubzona;
  }

  public void setSelectItems3(UISelectItems selectItems3) {
    this.selectItems3 = selectItems3;
  }

  public UISelectItems getSelectItems3() {
    return selectItems3;
  }


  public void selectOneChoice5_valueChangeListener(ValueChangeEvent event) 
         throws IdeamException{
         Object zona = event.getNewValue();
        this.listaSubzona = new ArrayList();
        try{
            if(zona!=null){
                this.listaSubzona = this.cargarZonificacion(((PartZonificListas)zona).getId());
            }
        }catch(IdeamException e){            
            showMessage(e);
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(selectOneChoiceSubzona);
    

  }
  
  
  public void cmi_editar_actionListener(ActionEvent actionEvent) {
      accionAdicionar = "";
      if (t_puntosMonitoreo.getSelectedRowData()==null){
          showMessage(getText("seleccionar_registro"),
                      FacesMessage.SEVERITY_ERROR);
          return;
      }
      
     
      PuntoMonitoreoTO pm = (PuntoMonitoreoTO)t_puntosMonitoreo.getSelectedRowData();
      FacesUtils.setInSession("puntoSeleccionado", pm);
      accionAdicionar = "detallepunto";
     
          
      
  }


    public void setPanelGroupLayout2(RichPanelGroupLayout panelGroupLayout2) {
        this.panelGroupLayout2 = panelGroupLayout2;
    }

    public RichPanelGroupLayout getPanelGroupLayout2() {
        return panelGroupLayout2;
    }

    public void setTitulo(RichActiveOutputText activeOutputText1) {
        this.titulo = activeOutputText1;
    }

    public RichActiveOutputText getTitulo() {
        return titulo;
    }

    public void setSpacer1(RichSpacer spacer1) {
        this.spacer1 = spacer1;
    }

    public RichSpacer getSpacer1() {
        return spacer1;
    }

    public void setSelectOneChoiceFuente(RichSelectOneChoice selectOneChoice2) {
        this.selectOneChoiceFuente = selectOneChoice2;
    }

    public RichSelectOneChoice getSelectOneChoiceFuente() {
        return selectOneChoiceFuente;
    }

    public void setSelectItems2(UISelectItems selectItems2) {
        this.selectItems2 = selectItems2;
    }

    public UISelectItems getSelectItems2() {
        return selectItems2;
    }

    public void setListaFuentes(List listaFuentes) {
        this.listaFuentes = listaFuentes;
    }

    public List getListaFuentes() {
        return listaFuentes;
    }

    public void setCmiImprimir(RichCommandMenuItem commandMenuItem1) {
        this.cmiImprimir = commandMenuItem1;
    }

    public RichCommandMenuItem getCmiImprimir() {
        return cmiImprimir;
    }

    public void cmiImprimir_actionListener(ActionEvent actionEvent) {
        // Add event code here...
        if (t_puntosMonitoreo.getSelectedRowData()==null){
            showMessage(getText("seleccionar_registro"),
                        FacesMessage.SEVERITY_ERROR);
            return;
        }
        PuntoMonitoreoTO punto = (PuntoMonitoreoTO)t_puntosMonitoreo.getSelectedRowData();
        try {
            HashMap parametros = new HashMap();
            parametros.put("p_modulo","Puntos de monitoreo");
            parametros.put("p_titulo","Información del punto ");            
            parametros.put("p_id_punto",punto.getId());    
            this.generateReport("PuntoMonitoreo.jasper",                                                                 
                                parametros,
                                ReporteDelegate.PDF);
        } catch (IdeamException e) {
            showMessage(e);
        }   
    }

    public void setCmi_ImprimirListado(RichCommandMenuItem commandMenuItem1) {
        this.cmi_ImprimirListado = commandMenuItem1;
    }

    public RichCommandMenuItem getCmi_ImprimirListado() {
        return cmi_ImprimirListado;
    }

    public void cmiImprimirListado_actionListener(ActionEvent actionEvent) {
        try {
            HashMap parametros = new HashMap();
            parametros.put("p_modulo", "Calidad del Agua");
            parametros.put("p_titulo", "Puntos de Monitoreo");
            this.generateReport("ListadoPuntos.jasper", this.listaPuntosMonitoreo,
                                parametros, ReporteDelegate.EXCEL);
        } catch (IdeamException e) {
            showMessage(e);
        }
        }
    
    
    public void cmiImprimirListadoMediciones_actionListener(ActionEvent actionEvent) {
        if (t_puntosMonitoreo.getSelectedRowData()==null){
            showMessage(getText("seleccionar_registro"),
                        FacesMessage.SEVERITY_ERROR);
            return;
        }
        PuntoMonitoreoTO punto = (PuntoMonitoreoTO)t_puntosMonitoreo.getSelectedRowData();
        
        try{
            
        
        
        String sqlcroosTad=   this.sqlCroosTab(punto.getId());
        
        
        CalidadDelegate cald = CalidadDelegate.getInstance();
        listaMedicionesPunto= cald.getDatosCroosTab(sqlcroosTad) ;
        System.out.println("++++++listaMedicionesPunto++++tam+++++  "+listaMedicionesPunto.size() );
          
            HashMap parametros = new HashMap();
            parametros.put("p_modulo", "Calidad del Agua");
            parametros.put("p_titulo", "Mediciones de Calidad ");
                                 
            this.generateReport("ListadoMedicionesPorPunto.jasper", this.listaMedicionesPunto,
                                parametros, ReporteDelegate.EXCEL);
        } catch (IdeamException e) {
            showMessage(e);
        }
    }

    public void setListaPar(List listaPar) {
        this.listaPar = listaPar;
    }

    public List getListaPar() {
        return listaPar;
    }

    public void setListaMedicionesPunto(List listaMedicionesPunto) {
        this.listaMedicionesPunto = listaMedicionesPunto;
    }

    public List getListaMedicionesPunto() {
        return listaMedicionesPunto;
    }

    public void setCmi_listadoMediciones(RichCommandMenuItem commandMenuItem1) {
        this.cmi_listadoMediciones = commandMenuItem1;
    }

    public RichCommandMenuItem getCmi_listadoMediciones() {
        return cmi_listadoMediciones;
    }


    public void setVisiblePerfil(String visiblePerfil) {
        this.visiblePerfil = visiblePerfil;
    }

    public String getVisiblePerfil() {
        return visiblePerfil;
    }
}
