package co.gov.ideam.sirh.porh.view;

import co.gov.ideam.sirh.fuentes.model.FnttFuente;
import co.gov.ideam.sirh.fuentes.model.FnttTramo;
import co.gov.ideam.sirh.fuentes.view.FuenteDelegate;
import co.gov.ideam.sirh.parametros.model.Autoridades;
import co.gov.ideam.sirh.parametros.model.Categoria;
import co.gov.ideam.sirh.parametros.model.Lista;
import co.gov.ideam.sirh.parametros.view.ParametrosDelegate;
import co.gov.ideam.sirh.porh.model.PorhOfertaDemanda;
import co.gov.ideam.sirh.porh.model.PorhParametrosTO;
import co.gov.ideam.sirh.porh.model.PorhPlanes;
import co.gov.ideam.sirh.porh.model.PorhTramosUsos;
import co.gov.ideam.sirh.porh.model.PorhTramosUsosObjetivo;
import co.gov.ideam.sirh.porh.model.PorhTramosUsosPlazos;
import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;
import co.gov.ideam.sirh.seguridad.model.UsuarioVO;
import co.gov.ideam.sirh.usuarios.agua.view.UsuariosAguaDelegate;
import co.gov.ideam.sirh.util.Constantes;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.FacesUtils;
import co.gov.ideam.sirh.view.StandarBean;

import java.math.BigDecimal;

import java.sql.Timestamp;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;

import javax.faces.component.UINamingContainer;
import javax.faces.component.UISelectItems;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.faces.event.ValueChangeEvent;

import javax.faces.model.SelectItem;

import oracle.adf.view.rich.component.rich.RichDialog;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.RichMenu;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.layout.RichPanelBox;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelSplitter;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.nav.RichCommandLink;
import oracle.adf.view.rich.component.rich.nav.RichCommandMenuItem;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.component.rich.output.RichPanelCollection;
import oracle.adf.view.rich.component.rich.output.RichSpacer;
import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.event.DialogEvent;

import org.apache.myfaces.trinidad.event.SelectionEvent;

public class UsosPermitidosBean extends StandarBean{
    private RichForm f2;
    private RichDocument d2;
    private RichPanelStretchLayout psl10;
    private RichPanelGroupLayout pgl2;
    private RichSpacer s1;
    private RichSpacer s2a;
    private RichSpacer s3;
    private RichCommandLink cl5;
    private RichCommandLink cl6;

    private String nombreFuente;
    private String nombreTramo;
    private List listaUsos;
    private List listaUsosTotales;
    private List listaParametros;
    private List listaUnidades;
    private List listaPlazos;
    private List listaObjetivosEcologicos;
    private List listaParametrosUso;
    
    private PorhPlanes planOrdenamiento;
    private FnttTramo tramo;
    private PorhTramosUsos usoSeleccionado;
    private PorhTramosUsosObjetivo parametroSeleccionado;
    
    private RichPanelSplitter ps1;
    private RichPanelBox pb_uso;
    private RichPanelBox pb_objetivos;
    private RichPanelCollection pc1;
    private RichPanelCollection pc2;
    private RichTable t_usos;
    private RichMenu m_menu;
    private RichCommandMenuItem cmi_editar_uso;
    private RichPanelGroupLayout pgl1;
    private RichPanelFormLayout pfl1;
    private RichSelectOneChoice soc_adicionar_uso;
    private UISelectItems si1;
    private RichPanelFormLayout pfl2;
    private RichSelectOneChoice soc_parametro;
    private UISelectItems si2;
    private RichInputDate id_fecha_cp;
    private RichInputDate id_fecha_mp;
    private RichInputDate id_fecha_lp;
    private RichPanelStretchLayout psl2;
    private RichPanelGroupLayout pgl5;
    private RichPanelFormLayout pfl6;
    private RichCommandButton cb_uso;
    private RichPanelGroupLayout pgl6;
    private RichSpacer s7;
    private RichPanelStretchLayout psl_derecho;
    private RichPanelGroupLayout pgl_detalle_parametro;
    private RichSelectOneChoice soc_plazo;
    private UISelectItems si3;
    private RichSelectOneChoice soc_objetivos_ecologicos;
    private UISelectItems siObjetivosEcologicos;
    private RichSelectOneChoice soc_unidad;
    private UISelectItems si4;
    private RichInputText it_valor;
    private RichPanelGroupLayout pgl3;
    private RichCommandButton cb_adicionar_parametro;
    private RichSpacer s4;
    private RichTable t_parametros;
    private RichPopup p1;
    private RichDialog d1;
    private UINamingContainer s5;
    private RichSpacer s6;
    private RichCommandButton cb_grafico;
    private RichSpacer s8;
    private RichPanelGroupLayout pgl4;
    private RichPopup p_borrar_calidad;
    private RichDialog d_borrar_calidad;
    private RichOutputText ot7;
    private RichSpacer s9;
    private RichCommandButton cb_borrar_calidad;
    private RichSpacer s10;
    private RichCommandButton cb_borrar_uso;
    private RichPopup p_borrar_uso;
    private RichDialog d_borrar_uso;
    private RichOutputText ot8;
    private RichPanelFormLayout panelFormLayout1;
    private RichInputText tactAdm;
    private RichInputDate dfecExp;
    private RichInputDate dvigIni;
    private RichInputDate dvigFin;
    private RichCommandButton bsalvar;

    public UsosPermitidosBean(){
        FacesUtils.setActiveBean("usosPermitidos",
                                 "usosPermitidos",
                                 UsuariosAguaDelegate.class);
        this.load();
    }
    
    private void load(){
        UsuarioConectadoTO usuarioConectado = 
            (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");

        Autoridades autoridadAmbiental = usuarioConectado.getUsuario().getAutoridadAmbiental();
        
        if(autoridadAmbiental==null ||
           autoridadAmbiental.getId().intValue() == Constantes.IDEAM){
            showMessage(getText("NO_EXISTE_AUTORIDAD_AMBIENTAL"),
                        FacesMessage.SEVERITY_ERROR);       
            return;
        }
        
        tramo = 
            (FnttTramo)FacesUtils.getFromSession("tramoSeleccionado");        
        FnttFuente fuente = 
            (FnttFuente)FacesUtils.getFromSession("fuenteSeleccionada");
        
        nombreFuente = fuente.getNombre();
        nombreTramo = tramo.getNombre();
        
        planOrdenamiento= (PorhPlanes)FacesUtils.getFromSession("planSeleccionado");
        
        try{
            PorhDelegate pd = PorhDelegate.getInstance();
            ParametrosDelegate pard = ParametrosDelegate.getInstance();        

            listaUsos =  pd.getAllUsosTramo(planOrdenamiento , tramo);
            
            listaUsosTotales = new ArrayList();
                    
            Categoria categoria = pard.getCategoria(Categoria.USOS_AGUA);
            if(categoria!=null && categoria.getLista()!=null){
                List listaCategoria = categoria.getLista();
                Iterator it = listaCategoria.iterator();
                while(it.hasNext()){
                    Lista l = (Lista)it.next();
                    if(!l.getValor().equalsIgnoreCase("Otro")){
                        SelectItem item = new SelectItem(l, l.getValor());                    
                        listaUsosTotales.add(item);                
                    }
                }
            }
            
            categoria = pard.getCategoria(Categoria.OTROS_USOS);
            if(categoria!=null && categoria.getLista()!=null){
                List listaCategoria = categoria.getLista();
                Iterator it = listaCategoria.iterator();
                while(it.hasNext()){
                    Lista l = (Lista)it.next();                    
                    SelectItem item = new SelectItem(l, l.getValor());                    
                    listaUsosTotales.add(item);                                    
                }
            }
                        
            Collections.sort( listaUsosTotales, new UsosComparator() );
            listaParametros = this.cargarParametro( Categoria.PARAMETROS );
            listaUnidades = this.cargarParametro( Categoria.UNIDADES );
        }catch(IdeamException e){
            showMessage(e);
        }
    }
    
    public void cb_adicionarAct_actionListener(ActionEvent actionEvent) {
        try {
            if (tactAdm.getValue() == null) {
                showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                            tactAdm);
                return;
            }

            if (dfecExp.getValue() == null) {
                showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                            dfecExp);
                return;
            }

            if (dvigIni.getValue() == null) {
                showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                            dvigIni);
                return;
            }

            if (dvigFin.getValue() == null) {
                showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                            dvigFin);
                return;
            }

            UsuarioConectadoTO usuarioConectado =
                (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");

            Autoridades autoridadAmbiental =
                usuarioConectado.getUsuario().getAutoridadAmbiental();

            tramo = (FnttTramo)FacesUtils.getFromSession("tramoSeleccionado");

            FuenteDelegate fd = FuenteDelegate.getInstance();
            
            tramo.setActoAdmU(tactAdm.getValue().toString());
            tramo.setFechaExpU((Date)dfecExp.getValue());
            tramo.setVigenciaIniU((Date)dvigIni.getValue());
            tramo.setVigenciaFinU((Date)dvigFin.getValue());
            
            fd.updateTramo(tramo);
            
            FacesUtils.setInSession("tramoSeleccionado",tramo );

            String params[] = { "de la meta global" };
            showMessage(getText("mensaje_registro_exitoso", params),
                        FacesMessage.SEVERITY_INFO);


        } catch (IdeamException e) {
            showMessage(e);
        }
    }
    
    public void cb_adicionar_uso_actionListener(ActionEvent actionEvent)  {
        boolean errores = false;
        // Verificar que se haya seleccionado un uso
        if(soc_adicionar_uso.getValue()==null){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,soc_adicionar_uso);
            errores = true;
        }
        Lista usoSeleccionado = (Lista)soc_adicionar_uso.getValue();
                
        // Verificar si se encuentra el uso relacionado al tramo        
        if (soc_adicionar_uso.getValue()!=null && listaUsos!=null && this.usoSeleccionado==null){
            Iterator<PorhTramosUsos> it = listaUsos.iterator();
            while(it.hasNext()){
                PorhTramosUsos tramoUso = it.next();
                if(tramoUso.getUsoPermitido().getCodigo().intValue()==
                   usoSeleccionado.getCodigo().intValue()){
                    showMessage("USO_YA_ESTA_RELACIONADO",FacesMessage.SEVERITY_ERROR,soc_adicionar_uso);
                    errores = true;
                    break;
                }
            } 
        }
                                
        if(id_fecha_cp.getValue()==null){
            errores = true;
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,id_fecha_cp);            
        }
        if(id_fecha_mp.getValue()==null){
            errores = true;
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,id_fecha_mp);            
        }
        if(id_fecha_lp.getValue()==null){
            errores = true;
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,id_fecha_lp);            
        }        
        if(errores){
            return;
        }
        
        UsuarioConectadoTO usuarioConectado = 
            (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");

        Autoridades autoridadAmbiental = usuarioConectado.getUsuario().getAutoridadAmbiental();
        
        // relacionar el uso con el tramo
        if(this.usoSeleccionado==null){
            PorhTramosUsos tramoNuevo = new PorhTramosUsos();
            tramoNuevo.setPorh_id(this.planOrdenamiento==null?null:this.planOrdenamiento.getCodigo());
            tramoNuevo.setTramo_id(this.tramo.getId());
            tramoNuevo.setUsoPermitido(usoSeleccionado);
            tramoNuevo.setCodigoAutoridad(autoridadAmbiental.getId().longValue());
            try{
                PorhDelegate pd = PorhDelegate.getInstance();
                PorhTramosUsos usoActualizado = pd.updateUsoTramo(tramoNuevo);
                
                PorhTramosUsosPlazos cortoPlazo = new PorhTramosUsosPlazos();
                cortoPlazo.setFecha((Date)id_fecha_cp.getValue());
                cortoPlazo.setObjetivo("CP");
                cortoPlazo.setPorhTramosUsos(usoActualizado);
                cortoPlazo.setCodigoAutoridad( autoridadAmbiental.getId().longValue() );
                pd.updateTramoUsoPlazo(cortoPlazo);
                
                PorhTramosUsosPlazos medianoPlazo = new PorhTramosUsosPlazos();
                medianoPlazo.setFecha((Date)id_fecha_mp.getValue());
                medianoPlazo.setObjetivo("MP");
                medianoPlazo.setPorhTramosUsos(usoActualizado);
                medianoPlazo.setCodigoAutoridad( autoridadAmbiental.getId().longValue() );
                pd.updateTramoUsoPlazo(medianoPlazo);            
    
                PorhTramosUsosPlazos largoPlazo = new PorhTramosUsosPlazos();
                largoPlazo.setFecha((Date)id_fecha_lp.getValue());
                largoPlazo.setObjetivo("LP");
                largoPlazo.setPorhTramosUsos(usoActualizado);
                largoPlazo.setCodigoAutoridad( autoridadAmbiental.getId().longValue() );
                pd.updateTramoUsoPlazo(largoPlazo);            
                
                this.listaUsos = pd.getAllUsosTramo(this.planOrdenamiento,
                            this.tramo);                                                
            }catch(IdeamException e){
                showMessage(e);
                return;
            }
        }else{
            try{
                PorhDelegate pd = PorhDelegate.getInstance();
                PorhTramosUsos usoActualizado = 
                    pd.updateUsoTramo(this.usoSeleccionado);
                
                PorhTramosUsosPlazos cp = this.usoSeleccionado.getPlazo("CP");
                if(cp!=null){
                    cp.setFecha((Date) id_fecha_cp.getValue() );
                    pd.updateTramoUsoPlazo(cp);            
                }
                
                PorhTramosUsosPlazos mp = this.usoSeleccionado.getPlazo("MP");
                if(mp!=null){
                    mp.setFecha((Date) id_fecha_mp.getValue() );
                    pd.updateTramoUsoPlazo(mp);            
                }            
                
                PorhTramosUsosPlazos lp = this.usoSeleccionado.getPlazo("LP");
                if(lp!=null){
                    lp.setFecha((Date) id_fecha_lp.getValue() );
                    pd.updateTramoUsoPlazo(lp);            
                }                       
                
                this.listaUsos = pd.getAllUsosTramo(this.planOrdenamiento,
                            this.tramo);
            }catch(IdeamException e){
                showMessage(e);
                return;
            }            
        }
        String params[] = {"del Uso"};
        showMessage(getText("mensaje_registro_exitoso",
                            params), FacesMessage.SEVERITY_INFO);
        
        AdfFacesContext.getCurrentInstance().addPartialTarget(t_usos);  
        soc_adicionar_uso.setValue(null);
        id_fecha_cp.setValue(null);
        id_fecha_mp.setValue(null);
        id_fecha_lp.setValue(null);
        cb_uso.setText("Adicionar");    
        soc_adicionar_uso.setDisabled(false);
        this.usoSeleccionado = null;
        t_usos.setSelectedRowKeys(null);        
        t_parametros.setSelectedRowKeys(null);
        AdfFacesContext.getCurrentInstance().addPartialTarget(pb_uso);
        AdfFacesContext.getCurrentInstance().addPartialTarget(pb_objetivos);
        
    }
    public void cmi_editar_uso_actionListener(ActionEvent actionEvent) {        
        if(this.usoSeleccionado==null){
            showMessage("Debe seleccionar un uso", FacesMessage.SEVERITY_ERROR);
            return;
        }
        this.soc_adicionar_uso.setValue( this.usoSeleccionado.getUsoPermitido() );
        PorhTramosUsosPlazos cortoPlazo = this.usoSeleccionado.getPlazo("CP");
        PorhTramosUsosPlazos medianoPlazo = this.usoSeleccionado.getPlazo("MP");
        PorhTramosUsosPlazos largoPlazo = this.usoSeleccionado.getPlazo("LP");

        if(cortoPlazo!=null){
            this.id_fecha_cp.setValue(cortoPlazo.getFecha());
        }
        if(medianoPlazo!=null){
            this.id_fecha_mp.setValue(medianoPlazo.getFecha());
        }
        if(largoPlazo!=null){
            this.id_fecha_lp.setValue(largoPlazo.getFecha());
        }
        
        AdfFacesContext.getCurrentInstance().addPartialTarget(pb_uso);
    }
    public void cb_adicionar_parametro_actionListener(ActionEvent actionEvent){
        boolean errores = false;
          try{
        if(soc_parametro.getValue()!=null && soc_objetivos_ecologicos.getValue()!=null){
            showMessage("Solo se puede seleccionar Objetivo Ecologico o Parametro no ambos");
            showMessage("error",FacesMessage.SEVERITY_ERROR,soc_parametro);
            showMessage("error",FacesMessage.SEVERITY_ERROR,soc_objetivos_ecologicos);
            errores = true;
        }   
        if(soc_parametro.getValue()==null && soc_objetivos_ecologicos.getValue()==null){
            showMessage("Tiene que seleccionar Objetivo Ecologico o Parametro ambos no pueden estar vacios");
            showMessage("error",FacesMessage.SEVERITY_ERROR,soc_parametro);
            showMessage("error",FacesMessage.SEVERITY_ERROR,soc_objetivos_ecologicos);
            errores = true;
        } 
        if(soc_plazo.getValue()==null){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,soc_plazo);
            errores = true;
        }
        if(soc_unidad.getValue()==null){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,soc_unidad);
            errores = true;
        }
        if(it_valor.getValue()==null){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,it_valor);
            errores = true;
        }
            
        if(errores){
            return;
        }
        
            UsuarioConectadoTO usuarioConectado = 
                (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");

            Autoridades autoridadAmbiental = usuarioConectado.getUsuario().getAutoridadAmbiental();
            PorhDelegate pd = PorhDelegate.getInstance();
            
            Lista parametro = 
                (Lista)soc_parametro.getValue();
            PorhTramosUsosPlazos plazo = 
                (PorhTramosUsosPlazos)soc_plazo.getValue();
            Lista unidad = 
                (Lista)soc_unidad.getValue();
            Lista objetivosEcologicos = 
                (Lista)soc_objetivos_ecologicos.getValue();
            PorhTramosUsosObjetivo objetivo = null;
            if (this.getParametroSeleccionado() == null){
                objetivo = new PorhTramosUsosObjetivo();
                objetivo.setCodigoAutoridad( autoridadAmbiental.getId().longValue() );
                objetivo.setId(0L);
            }else{
                objetivo = this.getParametroSeleccionado();
            }
            objetivo.setParametro( parametro );
            objetivo.setPorhTramosUsosPlazos( plazo );
            objetivo.setUnidad(unidad);
            objetivo.setValor(  ((BigDecimal)it_valor.getValue()).doubleValue()  );
            objetivo.setObjetivoEcologico(objetivosEcologicos);

            // Verificar si el parametro se encuentra registrado con el mismo plazo
            if(soc_parametro.getValue()!=null && parametro != null){
              Iterator<PorhParametrosTO> it = this.listaParametrosUso.iterator();
              while(it.hasNext()){
                  PorhParametrosTO par = it.next();
                
                par.getObjetivoId().longValue();
                objetivo.getId().longValue();
                par.getParametro_id().longValue();
                parametro.getCodigo().longValue();
                par.getObjetivoDescripcionCorta();
                plazo.getObjetivo();
                
                  if (par.getObjetivoId().longValue() != objetivo.getId().longValue() &&
                      par.getParametro_id().longValue() == parametro.getCodigo().longValue() &&
                      par.getObjetivoDescripcionCorta().equalsIgnoreCase( plazo.getObjetivo() )){
                      showMessage("El parámetro " + parametro.getValor() + " ya se encuentra relacionado con el uso y plazo seleccionados");
                      return;
                  }
              }
            }
            // Esta generando un error de PersistenceException por haber colocado 
            // la linea anterior con codigo 0 requerida para comparar y buscar duplicados
            if(objetivo.getId().longValue()==0){
                objetivo.setId(null);
            }
            PorhTramosUsosObjetivo  objetivoRegistrado = 
                    pd.updateObjetivo(objetivo);
            
            String params[] = {"del Parametro"};
            showMessage(getText("mensaje_registro_exitoso",
                                params), FacesMessage.SEVERITY_INFO);
            
            parametroSeleccionado = null;
            soc_plazo.setValue(null);
            soc_parametro.setValue(null);
            soc_unidad.setValue(null);
            it_valor.setValue(null);
            soc_objetivos_ecologicos.setValue(null);
            soc_parametro.setDisabled(false);
            cb_adicionar_parametro.setText("Adicionar");
            t_parametros.setSelectedRowKeys(null);
            
            this.listaParametrosUso = pd.getParametros(this.usoSeleccionado);
            //t_usos.setSelectedRowKeys(null);
            //soc_adicionar_uso.setDisabled(true);
            //parametroSeleccionado = null;
            AdfFacesContext.getCurrentInstance().addPartialTarget(pgl_detalle_parametro);
            AdfFacesContext.getCurrentInstance().addPartialTarget(psl_derecho);
            //this.actualizarObjetivos(this.usoSeleccionado);    
        }catch(Exception e){
          e.printStackTrace();
        }        
    }
    public void actualizarObjetivos(PorhTramosUsos uso){        
        String titulo = getText("OBJETIVOS_DE_CALIDAD_TRAMO");
        this.listaParametrosUso = new ArrayList();
        if(this.usoSeleccionado!=null){
            titulo += this.tramo.getNombre() + " Uso " + this.usoSeleccionado.getUsoPermitido().getValor();
            this.listaPlazos = new ArrayList<SelectItem>();
            if(this.usoSeleccionado.getPorhTramosUsosPlazosList()!=null){
                SimpleDateFormat sdf = new SimpleDateFormat("d/M/y");                
                Iterator<PorhTramosUsosPlazos> it = 
                    this.usoSeleccionado.getPorhTramosUsosPlazosList().iterator();
                while(it.hasNext()){
                    PorhTramosUsosPlazos plazo = it.next();          
                    String texto = plazo.getObjetivo() + " " +
                                   sdf.format( plazo.getFecha() );
                    SelectItem item = new SelectItem(plazo, texto);
                    this.listaPlazos.add(item);
                }
            }
            this.listaObjetivosEcologicos = new ArrayList<SelectItem>();
            try {
                this.listaObjetivosEcologicos =
                        this.cargarParametro(Categoria.OBJETIVOS_CALIDAD);
            } catch (IdeamException e) {
                showMessage(e);
            }
                        
            try{
                PorhDelegate pd = PorhDelegate.getInstance();                
                this.listaParametrosUso = pd.getParametros(this.usoSeleccionado);
            }catch(IdeamException e){
                showMessage(e);
            }            
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(pb_objetivos);
    }
    public void t_usos_selectionListener(SelectionEvent selectionEvent) {
        this.usoSeleccionado = (PorhTramosUsos)t_usos.getSelectedRowData();
        this.actualizarObjetivos(this.usoSeleccionado);   
        if(usoSeleccionado!=null){
            this.soc_adicionar_uso.setValue( this.usoSeleccionado.getUsoPermitido() );

            PorhTramosUsosPlazos cp = this.usoSeleccionado.getPlazo("CP");
            if(cp!=null){
                id_fecha_cp.setValue( cp.getFecha() );
            }else{
                id_fecha_cp.setValue( null );
            }
            
            PorhTramosUsosPlazos mp = this.usoSeleccionado.getPlazo("MP");
            if(mp!=null){
                id_fecha_mp.setValue( mp.getFecha() );
            }else{
                id_fecha_mp.setValue( null );
            }            
            
            PorhTramosUsosPlazos lp = this.usoSeleccionado.getPlazo("LP");
            if(lp!=null){
                id_fecha_lp.setValue( lp.getFecha() );
            }else{
                id_fecha_lp.setValue( null );
            }            
            cb_uso.setText("Actualizar");            
            soc_adicionar_uso.setDisabled(true);
        }else{
            this.soc_adicionar_uso.setValue(null);
            id_fecha_cp.setValue( null );
            id_fecha_mp.setValue( null );
            id_fecha_lp.setValue( null );
            cb_uso.setText("Adicionar");            
            soc_adicionar_uso.setDisabled(false);
        }
        soc_plazo.setValue(null);
        soc_parametro.setValue(null);
        soc_unidad.setValue(null);
        it_valor.setValue(null);
        t_parametros.setSelectedRowKeys(null);
        cb_adicionar_parametro.setText("Adicionar");
        this.parametroSeleccionado = null;
        
        AdfFacesContext.getCurrentInstance().addPartialTarget(t_parametros);
        AdfFacesContext.getCurrentInstance().addPartialTarget(pb_uso);
        AdfFacesContext.getCurrentInstance().addPartialTarget(pb_objetivos);
        
    }
    public void t_parametros_selectionListener(SelectionEvent selectionEvent) {        
        PorhParametrosTO parametro = 
            (PorhParametrosTO)this.t_parametros.getSelectedRowData();
        if(parametro!=null){        
            try{
                PorhDelegate pd = PorhDelegate.getInstance();
                PorhTramosUsosObjetivo objetivo = 
                    pd.getObjetivoCalidad( parametro.getObjetivoId() );

                this.soc_plazo.setValue(objetivo.getPorhTramosUsosPlazos());
                this.soc_parametro.setValue(objetivo.getParametro());
                this.soc_unidad.setValue(objetivo.getUnidad());
                this.it_valor.setValue(objetivo.getValor());     
                this.cb_adicionar_parametro.setText("Actualizar");
                this.soc_parametro.setDisabled(true);
                this.setParametroSeleccionado(objetivo);
            }catch(IdeamException e){
                showMessage(e);
            }
        }else{
            this.soc_plazo.setValue(null);
            this.soc_parametro.setValue(null);
            this.soc_unidad.setValue(null);
            this.it_valor.setValue(null);            
            this.soc_parametro.setDisabled(false);
            this.cb_adicionar_parametro.setText("Adicionar");
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.pgl_detalle_parametro);            
    }
    
  public void valueChangeListenerParametro(ValueChangeEvent event)
          throws IdeamException{
    event.getComponent().processUpdates(FacesContext.getCurrentInstance());
    
    if (event.getNewValue() !=null) {
      soc_objetivos_ecologicos.setVisible(false);
    }else{
      soc_objetivos_ecologicos.setVisible(true);
    }
    AdfFacesContext.getCurrentInstance().addPartialTarget(pfl2);
        
  }
  public void valueChangeListenerObjetivoEcologico(ValueChangeEvent event)
          throws IdeamException{
    event.getComponent().processUpdates(FacesContext.getCurrentInstance());
    if (event.getNewValue() !=null) {
      soc_parametro.setVisible(false);
      soc_unidad.setVisible(false);
    }else{
      soc_parametro.setVisible(true);
      soc_unidad.setVisible(true);
    }
    AdfFacesContext.getCurrentInstance().addPartialTarget(pfl2);
        
  }
  
    public void setF2(RichForm f2) {
        this.f2 = f2;
    }

    public RichForm getF2() {
        return f2;
    }

    public void setD2(RichDocument d2) {
        this.d2 = d2;
    }

    public RichDocument getD2() {
        return d2;
    }

    public void setPsl10(RichPanelStretchLayout psl1) {
        this.psl10 = psl1;
    }

    public RichPanelStretchLayout getPsl10() {
        return psl10;
    }


    public void setPgl2(RichPanelGroupLayout pgl2) {
        this.pgl2 = pgl2;
    }

    public RichPanelGroupLayout getPgl2() {
        return pgl2;
    }


    public void setS1(RichSpacer s1) {
        this.s1 = s1;
    }

    public RichSpacer getS1() {
        return s1;
    }

    public void setS2a(RichSpacer s2) {
        this.s2a = s2;
    }

    public RichSpacer getS2a() {
        return s2a;
    }

    public void setS3(RichSpacer s3) {
        this.s3 = s3;
    }

    public RichSpacer getS3() {
        return s3;
    }

    public void setCl5(RichCommandLink cl5) {
        this.cl5 = cl5;
    }

    public RichCommandLink getCl5() {
        return cl5;
    }

    public void setCl6(RichCommandLink cl6) {
        this.cl6 = cl6;
    }

    public RichCommandLink getCl6() {
        return cl6;
    }

    public String getNombreFuente() {
        return nombreFuente;
    }

    public void setNombreFuente(String nombreFuente) {
        this.nombreFuente = nombreFuente;
    }

    public String getNombreTramo() {
        return nombreTramo;
    }

    public void setNombreTramo(String nombreTramo) {
        this.nombreTramo = nombreTramo;
    }

    public void setPs1(RichPanelSplitter ps1) {
        this.ps1 = ps1;
    }

    public RichPanelSplitter getPs1() {
        return ps1;
    }


    public void setPb_uso(RichPanelBox pb1) {
        this.pb_uso = pb1;
    }

    public RichPanelBox getPb_uso() {
        return pb_uso;
    }

    public void setPb_objetivos(RichPanelBox pb2) {
        this.pb_objetivos = pb2;
    }

    public RichPanelBox getPb_objetivos() {
        return pb_objetivos;
    }

    public void setPc1(RichPanelCollection pc1) {
        this.pc1 = pc1;
    }

    public RichPanelCollection getPc1() {
        return pc1;
    }

    public void setPc2(RichPanelCollection pc2) {
        this.pc2 = pc2;
    }

    public RichPanelCollection getPc2() {
        return pc2;
    }

    public List getListaUsos() {
        return listaUsos;
    }

    public void setListaUsos(List listaUsos) {
        this.listaUsos = listaUsos;
    }

    public void setT_usos(RichTable t1) {
        this.t_usos = t1;
    }

    public RichTable getT_usos() {
        return t_usos;
    }

    public void setM_menu(RichMenu m2) {
        this.m_menu = m2;
    }

    public RichMenu getM_menu() {
        return m_menu;
    }

    public void setCmi_editar_uso(RichCommandMenuItem cmi2) {
        this.cmi_editar_uso = cmi2;
    }

    public RichCommandMenuItem getCmi_editar_uso() {
        return cmi_editar_uso;
    }

    public void setPgl1(RichPanelGroupLayout pgl1) {
        this.pgl1 = pgl1;
    }

    public RichPanelGroupLayout getPgl1() {
        return pgl1;
    }


    public void setPfl1(RichPanelFormLayout pfl1) {
        this.pfl1 = pfl1;
    }

    public RichPanelFormLayout getPfl1() {
        return pfl1;
    }

    public List getListaUsosTotales() {
        return listaUsosTotales;
    }

    public void setListaUsosTotales(List listaUsosTotales) {
        this.listaUsosTotales = listaUsosTotales;
    }

    public void setSoc_adicionar_uso(RichSelectOneChoice soc1) {
        this.soc_adicionar_uso = soc1;
    }

    public RichSelectOneChoice getSoc_adicionar_uso() {
        return soc_adicionar_uso;
    }

    public void setSi1(UISelectItems si1) {
        this.si1 = si1;
    }

    public UISelectItems getSi1() {
        return si1;
    }


    public void setPfl2(RichPanelFormLayout pfl2) {
        this.pfl2 = pfl2;
    }

    public RichPanelFormLayout getPfl2() {
        return pfl2;
    }

    public List getListaParametros() {
        return listaParametros;
    }

    public void setListaParametros(List listaParametros) {
        this.listaParametros = listaParametros;
    }

    public void setSoc_parametro(RichSelectOneChoice soc1) {
        this.soc_parametro = soc1;
    }

    public RichSelectOneChoice getSoc_parametro() {
        return soc_parametro;
    }

    public void setSi2(UISelectItems si2) {
        this.si2 = si2;
    }

    public UISelectItems getSi2() {
        return si2;
    }


    public void setId_fecha_cp(RichInputDate id1) {
        this.id_fecha_cp = id1;
    }

    public RichInputDate getId_fecha_cp() {
        return id_fecha_cp;
    }


    public void setId_fecha_mp(RichInputDate id2) {
        this.id_fecha_mp = id2;
    }

    public RichInputDate getId_fecha_mp() {
        return id_fecha_mp;
    }


    public void setId_fecha_lp(RichInputDate id3) {
        this.id_fecha_lp = id3;
    }

    public RichInputDate getId_fecha_lp() {
        return id_fecha_lp;
    }


    public PorhTramosUsos getUsoSeleccionado() {
        return usoSeleccionado;
    }

    public void setUsoSeleccionado(PorhTramosUsos usoSeleccionado) {
        this.usoSeleccionado = usoSeleccionado;
    }

    public void setPsl2(RichPanelStretchLayout psl2) {
        this.psl2 = psl2;
    }

    public RichPanelStretchLayout getPsl2() {
        return psl2;
    }

    public void setPgl5(RichPanelGroupLayout pgl5) {
        this.pgl5 = pgl5;
    }

    public RichPanelGroupLayout getPgl5() {
        return pgl5;
    }


    public void setPfl6(RichPanelFormLayout pfl6) {
        this.pfl6 = pfl6;
    }

    public RichPanelFormLayout getPfl6() {
        return pfl6;
    }

    public void setCb_uso(RichCommandButton cb1) {
        this.cb_uso = cb1;
    }

    public RichCommandButton getCb_uso() {
        return cb_uso;
    }


    public void setPgl6(RichPanelGroupLayout pgl6) {
        this.pgl6 = pgl6;
    }

    public RichPanelGroupLayout getPgl6() {
        return pgl6;
    }

    public void setS7(RichSpacer s7) {
        this.s7 = s7;
    }

    public RichSpacer getS7() {
        return s7;
    }

    public void setPsl_derecho(RichPanelStretchLayout psl3) {
        this.psl_derecho = psl3;
    }

    public RichPanelStretchLayout getPsl_derecho() {
        return psl_derecho;
    }

    public void setPgl_detalle_parametro(RichPanelGroupLayout pgl4) {
        this.pgl_detalle_parametro = pgl4;
    }

    public RichPanelGroupLayout getPgl_detalle_parametro() {
        return pgl_detalle_parametro;
    }

    public List getListaUnidades() {
        return listaUnidades;
    }

    public void setListaUnidades(List listaUnidades) {
        this.listaUnidades = listaUnidades;
    }

    public void setSoc_plazo(RichSelectOneChoice soc1) {
        this.soc_plazo = soc1;
    }

    public RichSelectOneChoice getSoc_plazo() {
        return soc_plazo;
    }
    
    public void setSi3(UISelectItems si3) {
        this.si3 = si3;
    }

    public UISelectItems getSi3() {
        return si3;
    }

    public void setSoc_unidad(RichSelectOneChoice soc2) {
        this.soc_unidad = soc2;
    }

    public RichSelectOneChoice getSoc_unidad() {
        return soc_unidad;
    }

    public void setSi4(UISelectItems si4) {
        this.si4 = si4;
    }

    public UISelectItems getSi4() {
        return si4;
    }

    public void setIt_valor(RichInputText it1) {
        this.it_valor = it1;
    }

    public RichInputText getIt_valor() {
        return it_valor;
    }

    public void setPgl3(RichPanelGroupLayout pgl3) {
        this.pgl3 = pgl3;
    }

    public RichPanelGroupLayout getPgl3() {
        return pgl3;
    }

    public void setCb_adicionar_parametro(RichCommandButton cb2) {
        this.cb_adicionar_parametro = cb2;
    }

    public RichCommandButton getCb_adicionar_parametro() {
        return cb_adicionar_parametro;
    }

    public List getListaPlazos() {
        return listaPlazos;
    }

    public void setListaPlazos(List listaPlazos) {
        this.listaPlazos = listaPlazos;
    }
    
    public void setS4(RichSpacer s4) {
        this.s4 = s4;
    }

    public RichSpacer getS4() {
        return s4;
    }

    public List getListaParametrosUso() {
        return listaParametrosUso;
    }

    public void setListaParametrosUso(List listaParametrosUso) {
        this.listaParametrosUso = listaParametrosUso;
    }

    public void setT_parametros(RichTable t1) {
        this.t_parametros = t1;
    }

    public RichTable getT_parametros() {
        return t_parametros;
    }

    public void setP1(RichPopup p1) {
        this.p1 = p1;
    }

    public RichPopup getP1() {
        return p1;
    }

    public void setD1(RichDialog d1) {
        this.d1 = d1;
    }

    public RichDialog getD1() {
        return d1;
    }

    public void setS5(UINamingContainer s5) {
        this.s5 = s5;
    }

    public UINamingContainer getS5() {
        return s5;
    }
    public void cb_grafico_actionListener(ActionEvent actionEvent){
        FacesUtils.removeManagedBeanFromSession("graficoEfectividad");        
        showPopup(p1, true);
        t_usos.setSelectedRowKeys(null);
        AdfFacesContext.getCurrentInstance().addPartialTarget(t_usos);
    }

    public void setS6(RichSpacer s6) {
        this.s6 = s6;
    }

    public RichSpacer getS6() {
        return s6;
    }

    public void setCb_grafico(RichCommandButton cb1) {
        this.cb_grafico = cb1;
    }

    public RichCommandButton getCb_grafico() {
        return cb_grafico;
    }

    public PorhTramosUsosObjetivo getParametroSeleccionado() {
        return parametroSeleccionado;
    }

    public void setParametroSeleccionado(PorhTramosUsosObjetivo parametroSeleccionado) {
        this.parametroSeleccionado = parametroSeleccionado;
    }

    public void setS8(RichSpacer s8) {
        this.s8 = s8;
    }

    public RichSpacer getS8() {
        return s8;
    }

    public PorhPlanes getPlanOrdenamiento() {
        return planOrdenamiento;
    }

    public void setPlanOrdenamiento(PorhPlanes planOrdenamiento) {
        this.planOrdenamiento = planOrdenamiento;
    }

    public void setPgl4(RichPanelGroupLayout pgl4) {
        this.pgl4 = pgl4;
    }

    public RichPanelGroupLayout getPgl4() {
        return pgl4;
    }

    public void setP_borrar_calidad(RichPopup p2) {
        this.p_borrar_calidad = p2;
    }

    public RichPopup getP_borrar_calidad() {
        return p_borrar_calidad;
    }

    public void setD_borrar_calidad(RichDialog d3) {
        this.d_borrar_calidad = d3;
    }

    public RichDialog getD_borrar_calidad() {
        return d_borrar_calidad;
    }

    public void setOt7(RichOutputText ot7) {
        this.ot7 = ot7;
    }

    public RichOutputText getOt7() {
        return ot7;
    }

    public void setS9(RichSpacer s9) {
        this.s9 = s9;
    }

    public RichSpacer getS9() {
        return s9;
    }

    public void setCb_borrar_calidad(RichCommandButton cb1) {
        this.cb_borrar_calidad = cb1;
    }

    public RichCommandButton getCb_borrar_calidad() {
        return cb_borrar_calidad;
    }
    
    public void cb_borrar_calidad_actionListener(ActionEvent actionEvent){
        showPopup(p_borrar_calidad, true);
    }
    
    public void d_borrar_calidad_dialogListener(DialogEvent dialogEvent) {
        try{
            PorhDelegate pd = PorhDelegate.getInstance();
            pd.deleteObjetivoCalidad( this.parametroSeleccionado );
            this.actualizarObjetivos(this.usoSeleccionado);
            this.parametroSeleccionado = null;
            
            this.soc_plazo.setValue(null);
            this.soc_parametro.setValue(null);
            this.soc_unidad.setValue(null);
            this.it_valor.setValue(null);            
            this.soc_parametro.setDisabled(false);
            this.cb_adicionar_parametro.setText("Adicionar");
            
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.pgl_detalle_parametro);

            AdfFacesContext.getCurrentInstance().addPartialTarget(pb_objetivos);
            String params[] = {"del Objetivo de Calidad"};
            showMessage(getText("mensaje_registro_exitoso",
                                params), FacesMessage.SEVERITY_INFO);
        }catch(IdeamException e){
            showMessage(e);
        }
    }

    public void cb_borrar_uso_actionListener(ActionEvent actionEvent){
        showPopup(p_borrar_uso, true);
    }

    public void d_borrar_uso_dialogListener(DialogEvent dialogEvent) {
        try{
            PorhDelegate pd = PorhDelegate.getInstance();
            pd.deleteUso( this.usoSeleccionado );

            String params[] = {"del Uso"};
            showMessage(getText("mensaje_registro_exitoso",
                                params), FacesMessage.SEVERITY_INFO);
            
            this.usoSeleccionado = null;
            this.parametroSeleccionado = null;
            
            listaUsos =  pd.getAllUsosTramo(planOrdenamiento, tramo);
            t_usos.setSelectedRowKeys(null);
            t_parametros.setSelectedRowKeys(null);
            AdfFacesContext.getCurrentInstance().addPartialTarget(pb_uso);
            AdfFacesContext.getCurrentInstance().addPartialTarget(pb_objetivos);
        }catch(IdeamException e){
            showMessage(e);
        }
    }
    public void setS10(RichSpacer s10) {
        this.s10 = s10;
    }

    public RichSpacer getS10() {
        return s10;
    }

    public void setCb_borrar_uso(RichCommandButton cb1) {
        this.cb_borrar_uso = cb1;
    }

    public RichCommandButton getCb_borrar_uso() {
        return cb_borrar_uso;
    }

    public void setP_borrar_uso(RichPopup p2) {
        this.p_borrar_uso = p2;
    }

    public RichPopup getP_borrar_uso() {
        return p_borrar_uso;
    }

    public void setD_borrar_uso(RichDialog d3) {
        this.d_borrar_uso = d3;
    }

    public RichDialog getD_borrar_uso() {
        return d_borrar_uso;
    }

    public void setOt8(RichOutputText ot8) {
        this.ot8 = ot8;
    }

    public RichOutputText getOt8() {
        return ot8;
    }

  public void setListaObjetivosEcologicos(List listaObjetivosEcologicos) {
    this.listaObjetivosEcologicos = listaObjetivosEcologicos;
  }

  public List getListaObjetivosEcologicos() {
    return listaObjetivosEcologicos;
  }

  public void setSoc_objetivos_ecologicos(RichSelectOneChoice soc_objetivos_ecologicos) {
    this.soc_objetivos_ecologicos = soc_objetivos_ecologicos;
  }

  public RichSelectOneChoice getSoc_objetivos_ecologicos() {
    return soc_objetivos_ecologicos;
  }

  public void setSiObjetivosEcologicos(UISelectItems siObjetivosEcologicos) {
    this.siObjetivosEcologicos = siObjetivosEcologicos;
  }

  public UISelectItems getSiObjetivosEcologicos() {
    return siObjetivosEcologicos;
  }

    public void setPanelFormLayout1(RichPanelFormLayout panelFormLayout1) {
        this.panelFormLayout1 = panelFormLayout1;
    }

    public RichPanelFormLayout getPanelFormLayout1() {
        return panelFormLayout1;
    }


    public void setTactAdm(RichInputText inputText1) {
        this.tactAdm = inputText1;
    }

    public RichInputText getTactAdm() {
        return tactAdm;
    }

    public void setDfecExp(RichInputDate inputDate1) {
        this.dfecExp = inputDate1;
    }

    public RichInputDate getDfecExp() {
        return dfecExp;
    }

    public void setDvigIni(RichInputDate inputDate1) {
        this.dvigIni = inputDate1;
    }

    public RichInputDate getDvigIni() {
        return dvigIni;
    }

    public void setDvigFin(RichInputDate inputDate1) {
        this.dvigFin = inputDate1;
    }

    public RichInputDate getDvigFin() {
        return dvigFin;
    }

    public void setBsalvar(RichCommandButton commandButton1) {
        this.bsalvar = commandButton1;
    }

    public RichCommandButton getBsalvar() {
        return bsalvar;
    }

    public void setTramo(FnttTramo tramo) {
        this.tramo = tramo;
    }

    public FnttTramo getTramo() {
        return tramo;
    }
}

