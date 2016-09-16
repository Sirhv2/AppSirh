package co.gov.ideam.sirh.importar.view;

import co.gov.ideam.sirh.archivos.model.AbstractValidator;
import co.gov.ideam.sirh.archivos.model.ColumnTO;
import co.gov.ideam.sirh.archivos.model.FuentesConverter;
import co.gov.ideam.sirh.archivos.model.FuentesValidator;
import co.gov.ideam.sirh.archivos.model.FuniasConstruccionesAdicionalesConverter;
import co.gov.ideam.sirh.archivos.model.FuniasConstruccionesConverter;
import co.gov.ideam.sirh.archivos.model.FuniasDiagnosticoConverter;
import co.gov.ideam.sirh.archivos.model.FuniasDiagnosticoDisposicionConverter;
import co.gov.ideam.sirh.archivos.model.FuniasDiagnosticoFuentesConverter;
import co.gov.ideam.sirh.archivos.model.FuniasDiagnosticoResiduosConverter;
import co.gov.ideam.sirh.archivos.model.FuniasGeofisicaConverter;
import co.gov.ideam.sirh.archivos.model.FuniasGeologiaConverter;
import co.gov.ideam.sirh.archivos.model.FuniasUnidadGeologicaConverter;
import co.gov.ideam.sirh.archivos.model.ModelConverter;
import co.gov.ideam.sirh.archivos.model.MuestrasMedicionesConverter;
import co.gov.ideam.sirh.archivos.model.MuestrasMedicionesValidator;
import co.gov.ideam.sirh.archivos.model.PuntosMonitoreoConverter;
import co.gov.ideam.sirh.archivos.model.PuntosMonitoreoValidator;
import co.gov.ideam.sirh.archivos.model.RowTO;
import co.gov.ideam.sirh.archivos.model.TipoArchivoTO;

import co.gov.ideam.sirh.archivos.model.UsariosAguaCaptacionesNaturalConverter;
import co.gov.ideam.sirh.archivos.model.UsuariosAguaCaptacionesJuridicaConverter;
import co.gov.ideam.sirh.archivos.model.UsuariosAguaCaptacionesJuridicaValidator;
import co.gov.ideam.sirh.archivos.model.UsuariosAguaCaptacionesNaturalValidator;
import co.gov.ideam.sirh.archivos.model.UsuariosAguaCaptacionesServiciosConverter;
import co.gov.ideam.sirh.archivos.model.UsuariosAguaPermisosJuridicaConverter;
import co.gov.ideam.sirh.archivos.model.UsuariosAguaPermisosJuridicaValidator;
import co.gov.ideam.sirh.archivos.model.UsuariosAguaPermisosNaturalConverter;
import co.gov.ideam.sirh.archivos.model.UsuariosAguaPermisosNaturalValidator;
import co.gov.ideam.sirh.archivos.model.UsuariosAguaPermisosServiciosConverter;
import co.gov.ideam.sirh.archivos.view.ArchivosDelegate;
import co.gov.ideam.sirh.dataimport.ImportProxy;
import co.gov.ideam.sirh.dataimport.client.generate.FntvFuentesTramos;
import co.gov.ideam.sirh.dataimport.client.generate.MuestrasMediciones;
import co.gov.ideam.sirh.dataimport.client.generate.PuntosMonitoreoCalidad;
import co.gov.ideam.sirh.dataimport.client.generate.RegUserJuridicoConcesion;
import co.gov.ideam.sirh.dataimport.client.generate.RegUserJuridicoVertimiento;
import co.gov.ideam.sirh.dataimport.client.generate.RegUserNaturalConcesion;
import co.gov.ideam.sirh.dataimport.client.generate.RegUserNaturalVertimiento;
import co.gov.ideam.sirh.dataimport.util.FileUtil;
import co.gov.ideam.sirh.parametros.model.Autoridades;
import co.gov.ideam.sirh.parametros.model.Parametro;
import co.gov.ideam.sirh.parametros.view.ParametrosDelegate;
import co.gov.ideam.sirh.usuarios.agua.view.UsuariosAguaDelegate;
import co.gov.ideam.sirh.util.ConstantesImportacion;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.FacesUtils;
import co.gov.ideam.sirh.view.StandarBean;

import co.gov.ideam.sirh.view.util.LoadDataUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;

import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.component.UISelectItems;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import javax.xml.ws.WebServiceRef;

import oracle.adf.view.rich.component.rich.RichDialog;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.RichMenu;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichColumn;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.nav.RichCommandMenuItem;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.component.rich.output.RichPanelCollection;
import oracle.adf.view.rich.component.rich.output.RichSpacer;

import oracle.adf.view.rich.context.AdfFacesContext;

import org.apache.myfaces.trinidad.model.UploadedFile;

public class DatosServicioWebBacking extends StandarBean{
    
    private List listaTiposArchivos;
    private TipoArchivoTO tipoArchivoSeleccionado;
    private List listaAutoridades;
    private Autoridades autoridadSeleccionada;
    private List listaDatos;
    private boolean habilitarCarga = false;
    private int registrosCargados = 0;
    
    private RichForm form1;
    private RichDocument document1;
    private RichPanelStretchLayout panelStretchLayout1;
    private RichPanelCollection pc1;
    private RichMenu menu1;
    private RichCommandMenuItem commandMenuItem1;
    private RichCommandMenuItem commandMenuItem2;
    private RichTable t_datos;
    private RichPanelGroupLayout panelGroupLayout1;
    private RichPopup p_borrar_fila;
    private RichPopup p_errores;
    private RichDialog d_borrar_fila;
    private RichDialog d_errores;
    private RichOutputText ot1;
    private RichPanelFormLayout panelFormLayout1;
    private RichInputText it_fila;
    private RichInputText it_errores;
    private RichPanelGroupLayout panelGroupLayout2;
    private RichPanelGroupLayout panelGroupLayout3;
    private RichSelectOneChoice soc_tipo_archivo;
    private UISelectItems si1;
    private RichSpacer spacer1;
    private RichCommandButton cb_cargar;
    private RichSpacer spacer2;
    private RichCommandButton cb_cargar_data;
    private RichSelectOneChoice soc_corporacion;
    private UISelectItems siAutoridades;
    private RichSpacer spacer3;
    private RichPanelFormLayout pflCarga;

    public DatosServicioWebBacking(){
        FacesUtils.setActiveBean("datosServicioWeb",
                                 "Usuarios Del Agua",
                                 UsuariosAguaDelegate.class);
        this.load();
    }

    public void load(){
        try{
            
            ArchivosDelegate ad = ArchivosDelegate.getInstance();
            
            this.listaAutoridades = this.cargarListaAutoridades();
            
            this.listaTiposArchivos = new ArrayList();
            List lista = ad.getAllTipoArchivo();
            Iterator it = lista.iterator();
            while(it.hasNext()){
                TipoArchivoTO tipo = (TipoArchivoTO)it.next();
                SelectItem item = new SelectItem(tipo, tipo.getNombre());
                this.listaTiposArchivos.add(item);
            }
        }catch(IdeamException e){
            showMessage(e);
        }
    }
    
    public void cb_cargar_actionListener(ActionEvent actionEvent) {
        try{
            ArchivosDelegate ad = ArchivosDelegate.getInstance();
            Autoridades autoridadAmbiental = (Autoridades)this.soc_corporacion.getValue();
            Integer idAutoridad = this.autoridadSeleccionada.getId();
            String hojaSeleccionada = "";
            boolean errores = false;
            
            if(this.soc_corporacion.getValue() == null){
                showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,soc_corporacion);
                errores = true;
            }

            if(this.soc_tipo_archivo.getValue() == null){
                showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,soc_tipo_archivo);
                errores = true;
            }

            if(errores){
                return;
            }
                      
            AbstractValidator validator = null;
                        
            try{
                
                validator = tipoArchivoSeleccionado.getValidatorInstance();
                
            //este swich se realiza porque hay que hacer un proyecto cliente por cada corporacion con la cual importemos a traves de ws.
            switch (idAutoridad)
            {
              case 5 : // AMVA  
                  if(validator instanceof MuestrasMedicionesValidator){
                     
                                             List<MuestrasMediciones> result = ImportProxy.getAllMediciones(idAutoridad);
            //                                                List<MuestrasMediciones> result = ImportProxy.getAllMediciones(this.autoridadSeleccionada.getId().toString());
                  System.out.println("AMVA Lista MEDICIONES: "+result.size());                            
                                              if(result!=null){                                
                                                  FileUtil.loadDataMuestrasMedicionesXls(result);                                 
                                              }
                                              hojaSeleccionada = ConstantesImportacion.HOJA_MEDICIONES;

                  }    
                  break; 
            
              case 7 : // CAR para pruebas - WS para simulación una Corporación             
                  if(validator instanceof FuentesValidator){
                      List<FntvFuentesTramos> result = ImportProxy.getAllFuentes(idAutoridad); 
                      
                      System.out.println(idAutoridad.toString()+" - "+"CAR Lista FUENTES: "+result.size());
                      
                      if(result!=null){ 
                              FileUtil.loadDataFuentesXls(result);
                      }                       
                      hojaSeleccionada = ConstantesImportacion.HOJA_FUENTES;                                           
                      
                  }else if(validator instanceof UsuariosAguaCaptacionesNaturalValidator){                      
                          List<RegUserNaturalConcesion> result = ImportProxy.getAllUsuariosNaturalConcesion(idAutoridad);
            System.out.println(idAutoridad.toString()+" - "+"CAR Lista CNATURAL: "+result.size());
                          if(result!=null){                          
                              FileUtil.loadDataUserNaturalCaptacionesXls(result);   
                          }                        
                          hojaSeleccionada = ConstantesImportacion.HOJA_CAPTACIONES_NATURALES;
                          
                  }else if(validator instanceof UsuariosAguaCaptacionesJuridicaValidator){
                          List<RegUserJuridicoConcesion> result = ImportProxy.getAllUsuariosJuridicoConcesion(idAutoridad);
            System.out.println("CAR Lista CJURIDICO: "+result.size());
                          if(result!=null){                          
                              FileUtil.loadDataUserJuridicoCaptacionesXls(result);   
                          }                        
                          hojaSeleccionada = ConstantesImportacion.HOJA_CAPTACIONES_JURIDICAS;

                  }else if(validator instanceof UsuariosAguaPermisosNaturalValidator){
                          List<RegUserNaturalVertimiento> result = ImportProxy.getAllUsuariosNaturalVertimiento(idAutoridad);
            System.out.println("CAR Lista PVNATURAL: "+result.size());
                          if(result!=null){                          
                              FileUtil.loadDataUserNaturalPermisosVertimientosXls(result);   
                          }                        
                          hojaSeleccionada = ConstantesImportacion.HOJA_VERTIMIENTOS_NATURALES;

                   }else if(validator instanceof UsuariosAguaPermisosJuridicaValidator){
                            List<RegUserJuridicoVertimiento> result = ImportProxy.getAllUsuariosJuridicoVertimiento(idAutoridad);
            System.out.println("CAR Lista PVJURIDICO: "+result.size());
                            if(result!=null){                          
                                FileUtil.loadDataUserJuridicoPermisosVertimientosXls(result);   
                            }                        
                            hojaSeleccionada = ConstantesImportacion.HOJA_VERTIMIENTOS_JURIDICAS;

                  }else if(validator instanceof PuntosMonitoreoValidator){
                          List<PuntosMonitoreoCalidad> result = ImportProxy.getAllPuntosMonitoreo(idAutoridad);
            System.out.println(idAutoridad.toString()+" - "+"CAR Lista PUNTOS: "+result.size());
                          if(result!=null){                                
                              FileUtil.loadDataPuntosMonitoreoCalidadXls(result);                                 
                          }                           
                         hojaSeleccionada = ConstantesImportacion.HOJA_PUNTOS;
                      
                  }else if(validator instanceof MuestrasMedicionesValidator){
                          List<MuestrasMediciones> result = ImportProxy.getAllMediciones(idAutoridad);
            System.out.println(idAutoridad.toString()+" - "+"CAR Lista MEDICIONES: "+result.size());
                          if(result!=null){                                
                              FileUtil.loadDataMuestrasMedicionesXls(result);                                 
                          }
                          hojaSeleccionada = ConstantesImportacion.HOJA_MEDICIONES;                        
                  }  
                  break;
            
              case 15 : // CORANTIOQUIA                           
                  if(validator instanceof FuentesValidator){                     
                      List<FntvFuentesTramos> result = ImportProxy.getAllFuentes(idAutoridad); 
            System.out.println(idAutoridad.toString()+" - "+"CORANTIOQUIA Lista FUENTES: "+result.size());
                      if(result!=null){ 
                          FileUtil.loadDataFuentesXls(result);   
                      }                       
                      hojaSeleccionada = ConstantesImportacion.HOJA_FUENTES;                                           
                      
                  }else if(validator instanceof UsuariosAguaCaptacionesNaturalValidator){     

System.out.println(idAutoridad.toString()+" - "+"CORANTIOQUIA Validador Lista UCNATURAL");
                         List<RegUserNaturalConcesion> result = ImportProxy.getAllUsuariosNaturalConcesion(idAutoridad);
 System.out.println(idAutoridad.toString()+" - "+"CORANTIOQUIA Lista UCNATURAL: "+result.size());
                          if(result!=null){                          
                             FileUtil.loadDataUserNaturalCaptacionesXls(result);
                          }                        
                          hojaSeleccionada = ConstantesImportacion.HOJA_CAPTACIONES_NATURALES;                         
                  }    
                  break;
            
              case 21 : // CORPOCALDAS
              if(validator instanceof FuentesValidator){                   
                 List<co.gov.corpocaldas.generated.FntvFuentesTramos> result = ImportProxy.getAllFuentesCorpocaldas(); 
System.out.println(idAutoridad.toString()+" - "+"CORPOCALDAS Lista FUENTES: "+result.size());
                  if(result!=null){ 
                      FileUtil.loadDataFuentesXlsCorpocaldas(result); 
                  }                       
                  hojaSeleccionada = ConstantesImportacion.HOJA_FUENTES;                                                             
              }
                  break;
            
            }
            }catch(Exception e){
              System.out.println("ERROR en WS: "+e.fillInStackTrace());
            }
        
            
            ///////////////
            //validación del archivo
            
            List<RowTO> lista = ad.validateSheetClient(tipoArchivoSeleccionado,
                                               hojaSeleccionada, 
                                               FileUtil.getFile(),
                                               autoridadAmbiental);
System.out.println("LISTAWS VALIDADA: "+lista.size());           
            
            if (lista!=null){
                RichOutputText textoIndice = new RichOutputText();
                textoIndice.setId("text_indice" );
                textoIndice.setValueExpression("value", resolveExpression("#{row.indice}"));
                RichColumn dataColIndice = new RichColumn();
                dataColIndice.setId("col_indice");
                dataColIndice.setSortable(true);
                dataColIndice.setHeaderText("Fila");
                dataColIndice.setWidth("50");
  //dataColIndice.setValueExpression("inlineStyle", resolveExpression("#{row.valido ? 'background-color:rgb(0,181,90)' : 'background-color:rgb(255,148,148)'}"));
                dataColIndice.getChildren().add(textoIndice);
                t_datos.getChildren().add(dataColIndice);

                // Crear las columnas de la tabla
                List<ColumnTO> cols = null;
                cols = validator.getColumns();
                Iterator<ColumnTO> itCols = cols.iterator();
                int contadorColumnas = 0;
                while(itCols.hasNext()){
                    ColumnTO col = itCols.next();

                    RichOutputText texto = new RichOutputText();
                    texto.setId("text_" + col.getIndice());
                    texto.setValueExpression("value",
                                             resolveExpression("#{row.celdas[" + contadorColumnas + "].value}"));

                    RichColumn dataCol = new RichColumn();
                    dataCol.setId("col_" + col.getIndice());
                    dataCol.setSortable(true);
                    dataCol.setHeaderText(col.getTitulo());
                    //dataCol.setValueExpression("inlineStyle", resolveExpression("#{row.valido ? 'background-color:rgb(0,181,90)' : 'background-color:rgb(255,148,148)'}"));
                    dataCol.getChildren().add(texto);

                    t_datos.getChildren().add(dataCol);
                    contadorColumnas++;
                }

                RichOutputText textoMensaje = new RichOutputText();
                textoMensaje.setId("text_mensaje" );
                textoMensaje.setValueExpression("value", resolveExpression("#{row.mensaje}"));
                RichColumn dataColMensaje = new RichColumn();
                dataColMensaje.setId("col_mensaje");
                dataColMensaje.setSortable(true);
                dataColMensaje.setHeaderText("Mensajes");
                dataColMensaje.setWidth("200");
                //dataColMensaje.setValueExpression("inlineStyle", resolveExpression("#{row.valido ? 'background-color:rgb(0,181,90)' : 'background-color:rgb(255,148,148)'}"));
                dataColMensaje.getChildren().add(textoMensaje);
                t_datos.getChildren().add(dataColMensaje);

                this.listaDatos = new ArrayList();

                Iterator<RowTO> it = lista.iterator();
                while(it.hasNext()){
                    RowTO row = it.next();
                    this.listaDatos.add(row);                 
                }                
                t_datos.setRendered(true);
                this.setHabilitarCarga(true);
                AdfFacesContext.getCurrentInstance().addPartialTarget(pc1);
                AdfFacesContext.getCurrentInstance().addPartialTarget(t_datos);
                AdfFacesContext.getCurrentInstance().addPartialTarget(cb_cargar_data);
            }
            
        }catch(IdeamException e){
            showMessage(e);
        }catch(Exception e){
            showMessage("ERROR: "+e.getMessage()+".  La causa del error es: "+e.getCause()+". ");
        }        
    }  
    
    public void cb_cargar_data_actionListener(ActionEvent actionEvent) {
        List registrosValidos = new ArrayList();
        boolean valido = false;
        if(this.listaDatos!=null){
            Iterator it = this.listaDatos.iterator();
            while(it.hasNext()){
                RowTO fila = (RowTO)it.next();
                if (fila.isValido()){
                    valido = true;
                    registrosValidos.add( fila );
                }
            }
        }
        
        if(!valido){
            showMessage(getText("SIN_REGISTROS_VALIDOS"),
                        FacesMessage.SEVERITY_ERROR);
            return;
        }
        try{
            this.registrosCargados = 0;
            Iterator it = registrosValidos.iterator();
            while (it.hasNext()) {
                RowTO fila = (RowTO)it.next();
                ModelConverter converter =
                    tipoArchivoSeleccionado.getConverterInstance();
                if (converter instanceof
                    UsariosAguaCaptacionesNaturalConverter) {
                    this.registrosCargados =
                            this.registrosCargados + LoadDataUtil.registrarUsuarioAguaCaptacionesNatural(converter,
                                                                                                         fila,
                                                                                                         this.autoridadSeleccionada);
                } else if (converter instanceof
                           UsuariosAguaCaptacionesJuridicaConverter) {
                    this.registrosCargados =
                            this.registrosCargados + LoadDataUtil.registrarUsuarioAguaCaptacionesJuridica(converter,
                                                                                                          fila,
                                                                                                          this.autoridadSeleccionada);
                } else if (converter instanceof
                           UsuariosAguaPermisosNaturalConverter) {
                    this.registrosCargados =
                            this.registrosCargados + LoadDataUtil.registrarUsuarioAguaPermisosNatural(converter,
                                                                                                      fila,
                                                                                                      this.autoridadSeleccionada);
                } else if (converter instanceof
                           UsuariosAguaPermisosJuridicaConverter) {
                    this.registrosCargados =
                            this.registrosCargados + LoadDataUtil.registrarUsuarioAguaPermisosJuridica(converter,
                                                                                                       fila,
                                                                                                       this.autoridadSeleccionada);
                } else if (converter instanceof FuentesConverter) {
                    this.registrosCargados =
                            this.registrosCargados + LoadDataUtil.registrarFuente(converter,
                                                                                  fila,
                                                                                  this.autoridadSeleccionada);
                } else if (converter instanceof PuntosMonitoreoConverter) {
                    this.registrosCargados =
                            this.registrosCargados + LoadDataUtil.registrarPuntosMonitoreo(converter,
                                                                                           fila,
                                                                                           this.autoridadSeleccionada);
                } else if (converter instanceof MuestrasMedicionesConverter) {
                    this.registrosCargados =
                            this.registrosCargados + LoadDataUtil.registrarMediciones(converter,
                                                                                      fila,
                                                                                      this.autoridadSeleccionada);
                } else if (converter instanceof
                           UsuariosAguaCaptacionesServiciosConverter) {
                    this.registrosCargados =
                            this.registrosCargados + LoadDataUtil.registrarUsuarioAguaCaptacionesServicios(converter,
                                                                                                           fila,
                                                                                                           this.autoridadSeleccionada);
                } else if (converter instanceof
                           UsuariosAguaPermisosServiciosConverter) {
                    this.registrosCargados =
                            this.registrosCargados + LoadDataUtil.registrarUsuarioAguaPermisosServicios(converter,
                                                                                                        fila,
                                                                                                        this.autoridadSeleccionada);
                } else if (converter instanceof FuniasGeologiaConverter) {
                    this.registrosCargados =
                            this.registrosCargados + LoadDataUtil.registrarFuniasGeologia(converter,
                                                                                          fila,
                                                                                          this.autoridadSeleccionada);
                } else if (converter instanceof
                           FuniasUnidadGeologicaConverter) {
                    this.registrosCargados =
                            this.registrosCargados + LoadDataUtil.registrarFuniasGeologia(converter,
                                                                                          fila,
                                                                                          this.autoridadSeleccionada);
                } else if (converter instanceof
                           FuniasConstruccionesConverter) {
                    this.registrosCargados =
                            this.registrosCargados + LoadDataUtil.registrarFuniasConstruccion(converter,
                                                                                              fila,
                                                                                              this.autoridadSeleccionada);
                } else if (converter instanceof
                           FuniasConstruccionesAdicionalesConverter) {
                    this.registrosCargados =
                            this.registrosCargados + LoadDataUtil.registrarFuniasConstruccion(converter,
                                                                                              fila,
                                                                                              this.autoridadSeleccionada);
                } else if (converter instanceof FuniasGeofisicaConverter) {
                    this.registrosCargados =
                            this.registrosCargados + LoadDataUtil.registrarFuniasGeofisica(converter,
                                                                                           fila,
                                                                                           this.autoridadSeleccionada);
                } else if (converter instanceof FuniasDiagnosticoConverter) {
                    this.registrosCargados =
                            this.registrosCargados + LoadDataUtil.registrarFuniasDiagnostico(converter,
                                                                                             fila,
                                                                                             this.autoridadSeleccionada);
                } else if (converter instanceof
                           FuniasDiagnosticoFuentesConverter) {
                    this.registrosCargados =
                            this.registrosCargados + LoadDataUtil.registrarFuniasDiagnostico(converter,
                                                                                             fila,
                                                                                             this.autoridadSeleccionada);
                } else if (converter instanceof
                           FuniasDiagnosticoResiduosConverter) {
                    this.registrosCargados =
                            this.registrosCargados + LoadDataUtil.registrarFuniasDiagnostico(converter,
                                                                                             fila,
                                                                                             this.autoridadSeleccionada);
                } else if (converter instanceof
                           FuniasDiagnosticoDisposicionConverter) {
                    this.registrosCargados =
                            this.registrosCargados + LoadDataUtil.registrarFuniasDiagnostico(converter,
                                                                                             fila,
                                                                                             this.autoridadSeleccionada);
                }
            }
        }catch(IdeamException e){
            showMessage(e);
        }finally{
            String mensaje = "Se procesaron " + registrosCargados + " registros ";
            showMessage(mensaje,FacesMessage.SEVERITY_INFO);
            this.registrosCargados = 0;
            this.listaDatos = new ArrayList();
            this.borrarColumnas();
            AdfFacesContext.getCurrentInstance().addPartialTarget(pc1);
        }
        
    }
    
    public static ValueExpression resolveExpression(String attributeName) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Application app = facesContext.getApplication();
        ExpressionFactory elFactory = app.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        ValueExpression valueExp =
        elFactory.createValueExpression(elContext, attributeName, Object.class);
        return valueExp;
    }
    
    private void borrarColumnas(){
        this.setHabilitarCarga(false);
        if(t_datos != null){
            for(int i=0; i<t_datos.getChildren().size(); i++){
                t_datos.getChildren().remove(i);
            }
        }
        this.listaDatos = new ArrayList();
        AdfFacesContext.getCurrentInstance().addPartialTarget( pc1 );
        AdfFacesContext.getCurrentInstance().addPartialTarget( t_datos );
        AdfFacesContext.getCurrentInstance().addPartialTarget( cb_cargar_data );
    }

    public void setListaTiposArchivos(List listaTiposArchivos) {
        this.listaTiposArchivos = listaTiposArchivos;
    }

    public List getListaTiposArchivos() {
        return listaTiposArchivos;
    }

    public void setTipoArchivoSeleccionado(TipoArchivoTO tipoArchivoSeleccionado) {
        this.tipoArchivoSeleccionado = tipoArchivoSeleccionado;
    }

    public TipoArchivoTO getTipoArchivoSeleccionado() {
        return tipoArchivoSeleccionado;
    }
    
    public void setListaAutoridades(List listaAutoridades) {
        this.listaAutoridades = listaAutoridades;
    }

    public List getListaAutoridades() {
        return listaAutoridades;
    }

    public void setAutoridadSeleccionada(Autoridades autoridadSeleccionada) {
        this.autoridadSeleccionada = autoridadSeleccionada;
    }

    public Autoridades getAutoridadSeleccionada() {
        return autoridadSeleccionada;
    }
    
    public void setListaDatos(List listaDatos) {
        this.listaDatos = listaDatos;
    }

    public List getListaDatos() {
        return listaDatos;
    }

    public void setHabilitarCarga(boolean habilitarCarga) {
        this.habilitarCarga = habilitarCarga;
    }

    public boolean isHabilitarCarga() {
        return habilitarCarga;
    }

    public void setRegistrosCargados(int registrosCargados) {
        this.registrosCargados = registrosCargados;
    }

    public int getRegistrosCargados() {
        return registrosCargados;
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

    public void setPc1(RichPanelCollection panelCollection1) {
        this.pc1 = panelCollection1;
    }

    public RichPanelCollection getPc1() {
        return pc1;
    }

    public void setMenu1(RichMenu menu1) {
        this.menu1 = menu1;
    }

    public RichMenu getMenu1() {
        return menu1;
    }

    public void setCommandMenuItem1(RichCommandMenuItem commandMenuItem1) {
        this.commandMenuItem1 = commandMenuItem1;
    }

    public RichCommandMenuItem getCommandMenuItem1() {
        return commandMenuItem1;
    }

    public void setCommandMenuItem2(RichCommandMenuItem commandMenuItem2) {
        this.commandMenuItem2 = commandMenuItem2;
    }

    public RichCommandMenuItem getCommandMenuItem2() {
        return commandMenuItem2;
    }

    public void setT_datos(RichTable table1) {
        this.t_datos = table1;
    }

    public RichTable getT_datos() {
        return t_datos;
    }

    public void setPanelGroupLayout1(RichPanelGroupLayout panelGroupLayout1) {
        this.panelGroupLayout1 = panelGroupLayout1;
    }

    public RichPanelGroupLayout getPanelGroupLayout1() {
        return panelGroupLayout1;
    }

    public void setP_borrar_fila(RichPopup popup1) {
        this.p_borrar_fila = popup1;
    }

    public RichPopup getP_borrar_fila() {
        return p_borrar_fila;
    }

    public void setP_errores(RichPopup popup2) {
        this.p_errores = popup2;
    }

    public RichPopup getP_errores() {
        return p_errores;
    }

    public void setD_borrar_fila(RichDialog dialog1) {
        this.d_borrar_fila = dialog1;
    }

    public RichDialog getD_borrar_fila() {
        return d_borrar_fila;
    }

    public void setD_errores(RichDialog dialog2) {
        this.d_errores = dialog2;
    }

    public RichDialog getD_errores() {
        return d_errores;
    }

    public void setOt1(RichOutputText outputText1) {
        this.ot1 = outputText1;
    }

    public RichOutputText getOt1() {
        return ot1;
    }

    public void setPanelFormLayout1(RichPanelFormLayout panelFormLayout1) {
        this.panelFormLayout1 = panelFormLayout1;
    }

    public RichPanelFormLayout getPanelFormLayout1() {
        return panelFormLayout1;
    }

    public void setIt_fila(RichInputText inputText1) {
        this.it_fila = inputText1;
    }

    public RichInputText getIt_fila() {
        return it_fila;
    }

    public void setIt_errores(RichInputText inputText2) {
        this.it_errores = inputText2;
    }

    public RichInputText getIt_errores() {
        return it_errores;
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

    public void setSoc_tipo_archivo(RichSelectOneChoice selectOneChoice1) {
        this.soc_tipo_archivo = selectOneChoice1;
    }

    public RichSelectOneChoice getSoc_tipo_archivo() {
        return soc_tipo_archivo;
    }

    public void setSi1(UISelectItems selectItems1) {
        this.si1 = selectItems1;
    }

    public UISelectItems getSi1() {
        return si1;
    }

    public void setSpacer1(RichSpacer spacer1) {
        this.spacer1 = spacer1;
    }

    public RichSpacer getSpacer1() {
        return spacer1;
    }

    public void setCb_cargar(RichCommandButton commandButton1) {
        this.cb_cargar = commandButton1;
    }

    public RichCommandButton getCb_cargar() {
        return cb_cargar;
    }

    public void setSpacer2(RichSpacer spacer2) {
        this.spacer2 = spacer2;
    }

    public RichSpacer getSpacer2() {
        return spacer2;
    }

    public void setCb_cargar_data(RichCommandButton commandButton2) {
        this.cb_cargar_data = commandButton2;
    }

    public RichCommandButton getCb_cargar_data() {
        return cb_cargar_data;
    }

    public void setSoc_corporacion(RichSelectOneChoice selectOneChoice1) {
        this.soc_corporacion = selectOneChoice1;
    }

    public RichSelectOneChoice getSoc_corporacion() {
        return soc_corporacion;
    }

    public void setSiAutoridades(UISelectItems selectItems1) {
        this.siAutoridades = selectItems1;
    }

    public UISelectItems getSiAutoridades() {
        return siAutoridades;
    }

    public void setSpacer3(RichSpacer spacer3) {
        this.spacer3 = spacer3;
    }

    public RichSpacer getSpacer3() {
        return spacer3;
    }

    public void setPflCarga(RichPanelFormLayout panelFormLayout2) {
        this.pflCarga = panelFormLayout2;
    }

    public RichPanelFormLayout getPflCarga() {
        return pflCarga;
    }

    
}
