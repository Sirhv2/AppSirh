package co.gov.ideam.sirh.datos.referencia.view;

import co.gov.ideam.sirh.archivos.model.AbstractValidator;
import co.gov.ideam.sirh.archivos.model.ColumnTO;
import co.gov.ideam.sirh.archivos.model.FuentesConverter;
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
import co.gov.ideam.sirh.archivos.model.PuntosMonitoreoConverter;
import co.gov.ideam.sirh.archivos.model.RowTO;
import co.gov.ideam.sirh.archivos.model.TarifasAcueductoConverter;
import co.gov.ideam.sirh.archivos.model.TipoArchivoTO;
import co.gov.ideam.sirh.archivos.model.UsariosAguaCaptacionesNaturalConverter;
import co.gov.ideam.sirh.archivos.model.UsuariosAguaCaptacionesJuridicaConverter;
import co.gov.ideam.sirh.archivos.model.UsuariosAguaCaptacionesServiciosConverter;
import co.gov.ideam.sirh.archivos.model.UsuariosAguaPermisosJuridicaConverter;
import co.gov.ideam.sirh.archivos.model.UsuariosAguaPermisosNaturalConverter;
import co.gov.ideam.sirh.archivos.model.UsuariosAguaPermisosServiciosConverter;
import co.gov.ideam.sirh.archivos.view.ArchivosDelegate;
import co.gov.ideam.sirh.archivos.view.CargarArchivoBean;
import co.gov.ideam.sirh.parametros.model.Autoridades;
import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;
import co.gov.ideam.sirh.usuarios.agua.view.UsuariosAguaDelegate;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.FacesUtils;
import co.gov.ideam.sirh.view.StandarBean;
import co.gov.ideam.sirh.view.util.ArchivoCargadoTO;
import co.gov.ideam.sirh.view.util.LoadDataUtil;

import java.io.File;
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
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import oracle.adf.view.rich.component.rich.RichDialog;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.RichMenu;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichColumn;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputFile;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.input.RichSelectOneRadio;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.nav.RichCommandMenuItem;
import oracle.adf.view.rich.component.rich.output.RichMessage;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.component.rich.output.RichPanelCollection;
import oracle.adf.view.rich.component.rich.output.RichSpacer;
import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.event.DialogEvent;

import org.apache.myfaces.trinidad.model.UploadedFile;

public class DatosReferenciaBacking extends StandarBean{
    private RichForm f2;
    private RichDocument d2;
    private RichPanelStretchLayout psl1;
    private RichInputFile if_archivo;
    private RichPanelGroupLayout pgl2;

    private List listaTiposArchivos;
    private TipoArchivoTO tipoArchivoSeleccionado;
    private UploadedFile uploadedFile;
    private ArchivoCargadoTO archivoCargado;
    private List listaHojas;
    private String hojaSeleccionada;
    private byte[] contenidoArchivo;
    private List listaDatos;

    private RichSelectOneChoice soc_tipo_archivo;
    private UISelectItems si1;
    private RichCommandButton cb_cargar;
    private RichPanelCollection pc1;
    private RichMessage m2;
    private RichPanelGroupLayout pgl3;
    private RichSpacer s2;
    private RichSpacer s3;
    private RichPanelGroupLayout pgl4;
    private RichPopup p_seleccion;
    private RichDialog d_seleccion;
    private RichPanelFormLayout pfl1;
    private RichOutputText ot1;
    private RichSelectOneRadio sor_hojas;
    private UISelectItems si2;
    private RichTable t_datos;
    private RichMenu m_archivo;
    private RichCommandMenuItem cmi_borrar;
    private RichPopup p_borrar_fila;
    private RichDialog d_borrar_fila;
    private RichOutputText ot2;
    private RichCommandMenuItem cmi_errores;
    private RichPopup p_errores;
    private RichDialog d_errores;
    private RichPanelFormLayout pfl2;
    private RichInputText it_fila;
    private RichInputText it_errores;
    private RichSpacer s1;
    private RichCommandButton cb_cargar_data;
    private boolean habilitarCarga = false;
    private int registrosCargados = 0;

    public DatosReferenciaBacking(){
        FacesUtils.setActiveBean("datosReferenciaBacking",
                                 "Datos Referencia",
                                 DatosReferenciaDelegate.class);
        this.load();
    }

    public void load(){
        try{
            ArchivosDelegate ad = ArchivosDelegate.getInstance();
            this.listaTiposArchivos = new ArrayList();
            List lista = ad.getAllTipoArchivoDatosReferencia();
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

    public void setPsl1(RichPanelStretchLayout psl1) {
        this.psl1 = psl1;
    }

    public RichPanelStretchLayout getPsl1() {
        return psl1;
    }


    public void setIf_archivo(RichInputFile if1) {
        this.if_archivo = if1;
    }

    public RichInputFile getIf_archivo() {
        return if_archivo;
    }

    public void setPgl2(RichPanelGroupLayout pgl2) {
        this.pgl2 = pgl2;
    }

    public RichPanelGroupLayout getPgl2() {
        return pgl2;
    }


    public List getListaTiposArchivos() {
        return listaTiposArchivos;
    }

    public void setListaTiposArchivos(List listaTiposArchivos) {
        this.listaTiposArchivos = listaTiposArchivos;
    }

    public TipoArchivoTO getTipoArchivoSeleccionado() {
        return tipoArchivoSeleccionado;
    }

    public void setTipoArchivoSeleccionado(TipoArchivoTO tipoArchivoSeleccionado) {
        this.tipoArchivoSeleccionado = tipoArchivoSeleccionado;
    }

    public void setSoc_tipo_archivo(RichSelectOneChoice soc1) {
        this.soc_tipo_archivo = soc1;
    }

    public RichSelectOneChoice getSoc_tipo_archivo() {
        return soc_tipo_archivo;
    }

    public void setSi1(UISelectItems si1) {
        this.si1 = si1;
    }

    public UISelectItems getSi1() {
        return si1;
    }


    public void setCb_cargar(RichCommandButton cb1) {
        this.cb_cargar = cb1;
    }

    public RichCommandButton getCb_cargar() {
        return cb_cargar;
    }

    public void setPc1(RichPanelCollection pc1) {
        this.pc1 = pc1;
    }

    public RichPanelCollection getPc1() {
        return pc1;
    }


    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
        if (uploadedFile!=null){
            ArchivoCargadoTO ac = new ArchivoCargadoTO();
            ac.setFile(new File(uploadedFile.getFilename()));
            ac.setFileName(uploadedFile.getFilename());
            try {
                ac.setInputStream(uploadedFile.getInputStream());
            } catch (IOException e) {
                archivoCargado=null;
                showMessage(new IdeamException(e));
            }
            ac.setSize(uploadedFile.getLength());
            ac.setType(uploadedFile.getContentType());
            archivoCargado = ac;
        }
    }

    public ArchivoCargadoTO getArchivoCargado() {
        return archivoCargado;
    }

    public void setArchivoCargado(ArchivoCargadoTO archivoCargado) {
        this.archivoCargado = archivoCargado;
    }

    public void if_archivo_valueChangeListener(ValueChangeEvent valueChangeEvent) {
        this.borrarColumnas();
        UploadedFile file = (UploadedFile)valueChangeEvent.getNewValue();
        if (file!=null){
            String tipoArchivo = file.getContentType();
            if (!tipoArchivo.endsWith("ms-excel")){
                String params[] = { file.getFilename(), "Excel Version 2007" };
                showMessage("tipo_archivo_incorrecto",
                            params,
                            FacesMessage.SEVERITY_ERROR,
                            if_archivo);
                this.if_archivo.resetValue();
                this.setUploadedFile(null);
                this.archivoCargado=null;
                return;
            }
            this.setUploadedFile(file);
        }
        this.setHabilitarCarga(false);
        AdfFacesContext.getCurrentInstance().addPartialTarget(cb_cargar_data);
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

    public void cb_cargar_actionListener(ActionEvent actionEvent) {
        boolean errores = false;
        if(archivoCargado==null){
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,if_archivo);
            errores = true;
        }
        if(tipoArchivoSeleccionado == null){
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,soc_tipo_archivo);
            errores = true;
        }
        if(errores){
            return;
        }
        try{
            this.borrarColumnas();
            this.listaHojas = new ArrayList();
            ArchivosDelegate ad = ArchivosDelegate.getInstance();
            byte archivo[] = new byte[archivoCargado.getSize().intValue()];
            System.arraycopy(archivoCargado.getContent(),0,
                             archivo,0,
                             archivoCargado.getSize().intValue());

            contenidoArchivo = new byte[archivoCargado.getSize().intValue()];
            System.arraycopy(archivo,0,
                             contenidoArchivo,0,
                             archivoCargado.getSize().intValue());

            List listaHojas = ad.getSheets(archivo);

            Iterator it = listaHojas.iterator();
            while(it.hasNext()){
                String nombre = (String)it.next();
                SelectItem item = new SelectItem(nombre);
                this.listaHojas.add(item);
            }
            showPopup(p_seleccion, true);//es el dialogo de hoja del archivo a cargar.
        }catch(IdeamException e){
            showMessage(e);
        }
    }

    public void setM2(RichMessage m2) {
        this.m2 = m2;
    }

    public RichMessage getM2() {
        return m2;
    }

    public void setPgl3(RichPanelGroupLayout pgl3) {
        this.pgl3 = pgl3;
    }

    public RichPanelGroupLayout getPgl3() {
        return pgl3;
    }

    public void setS2(RichSpacer s2) {
        this.s2 = s2;
    }

    public RichSpacer getS2() {
        return s2;
    }

    public void setS3(RichSpacer s3) {
        this.s3 = s3;
    }

    public RichSpacer getS3() {
        return s3;
    }

    public void setPgl4(RichPanelGroupLayout pgl4) {
        this.pgl4 = pgl4;
    }

    public RichPanelGroupLayout getPgl4() {
        return pgl4;
    }

    public void setP_seleccion(RichPopup p1) {
        this.p_seleccion = p1;
    }

    public RichPopup getP_seleccion() {
        return p_seleccion;
    }

    public void setD_seleccion(RichDialog d1) {
        this.d_seleccion = d1;
    }

    public RichDialog getD_seleccion() {
        return d_seleccion;
    }

    public void setPfl1(RichPanelFormLayout pfl1) {
        this.pfl1 = pfl1;
    }

    public RichPanelFormLayout getPfl1() {
        return pfl1;
    }

    public List getListaHojas() {
        return listaHojas;
    }

    public void setListaHojas(List listaHojas) {
        this.listaHojas = listaHojas;
    }

    public void setOt1(RichOutputText ot1) {
        this.ot1 = ot1;
    }

    public RichOutputText getOt1() {
        return ot1;
    }

    public void setSor_hojas(RichSelectOneRadio sor1) {
        this.sor_hojas = sor1;
    }

    public RichSelectOneRadio getSor_hojas() {
        return sor_hojas;
    }

    public void setSi2(UISelectItems si2) {
        this.si2 = si2;
    }

    public UISelectItems getSi2() {
        return si2;
    }

    public String getHojaSeleccionada() {
        return hojaSeleccionada;
    }

    public void setHojaSeleccionada(String hojaSeleccionada) {
        this.hojaSeleccionada = hojaSeleccionada;
    }
    
    /**
     * Se ejecuta cuando selecciona una hoja a cargar en la ventana de 
     * dialogo p_seleccion.
     * @param dialogEvent.  Evento asociado al dialogo.
     * */
    public void d_seleccion_dialogListener(DialogEvent dialogEvent) {
        if(hojaSeleccionada == null){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR, sor_hojas);
            return;
        }
        try{
            this.borrarColumnas();
            UsuarioConectadoTO usuarioConectado =
                (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");

            Autoridades autoridadAmbiental = usuarioConectado.getUsuario().getAutoridadAmbiental();

            ArchivosDelegate ad = ArchivosDelegate.getInstance();
            List<RowTO> lista = ad.validateSheet(tipoArchivoSeleccionado,
                                                hojaSeleccionada,
                                                contenidoArchivo,
                                                autoridadAmbiental);
            if (lista!=null){
                AbstractValidator validator =
                    tipoArchivoSeleccionado.getValidatorInstance();

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
                List<ColumnTO> cols = validator.getColumns();
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
                    /*
                    System.out.println("-------");
                    List<ColumnTO> cols2 = validator.getColumns();
                    Iterator<ColumnTO> itCols2 = cols2.iterator();
                    while(itCols2.hasNext()){
                        ColumnTO col = itCols2.next();
                        Object value = row.getCellValue(col);
                        System.out.print("->" + value.toString());
                    } */
                }
                t_datos.setRendered(true);
                this.setHabilitarCarga(true);
                AdfFacesContext.getCurrentInstance().addPartialTarget(pc1);
                AdfFacesContext.getCurrentInstance().addPartialTarget(t_datos);
                AdfFacesContext.getCurrentInstance().addPartialTarget(cb_cargar_data);
            }
        }catch(IdeamException e){
            this.setHabilitarCarga(false);
            showMessage(e);
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
    
    public List getListaDatos() {
        return listaDatos;
    }

    public void setListaDatos(List listaDatos) {
        this.listaDatos = listaDatos;
    }

    public void setT_datos(RichTable t1) {
        this.t_datos = t1;
    }

    public RichTable getT_datos() {
        return t_datos;
    }

    public void setM_archivo(RichMenu m3) {
        this.m_archivo = m3;
    }

    public RichMenu getM_archivo() {
        return m_archivo;
    }

    public void setCmi_borrar(RichCommandMenuItem cmi2) {
        this.cmi_borrar = cmi2;
    }

    public RichCommandMenuItem getCmi_borrar() {
        return cmi_borrar;
    }

    public void setP_borrar_fila(RichPopup p1) {
        this.p_borrar_fila = p1;
    }

    public RichPopup getP_borrar_fila() {
        return p_borrar_fila;
    }

    public void setD_borrar_fila(RichDialog d1) {
        this.d_borrar_fila = d1;
    }

    public RichDialog getD_borrar_fila() {
        return d_borrar_fila;
    }

    public void setOt2(RichOutputText ot2) {
        this.ot2 = ot2;
    }

    public RichOutputText getOt2() {
        return ot2;
    }

    public void cmi_borrar_actionListener(ActionEvent actionEvent) {
        // Verificar que se haya seleccionado un registro de la tabla
        if(t_datos.getSelectedRowData() == null){
            showMessage(getText("seleccionar_registro"),
                        FacesMessage.SEVERITY_ERROR);
            return;
        }
        showPopup(p_borrar_fila, true);
    }

    public void d_borrar_fila_dialogListener(DialogEvent dialogEvent) {
        // Borrar la fila
        this.listaDatos.remove(t_datos.getSelectedRowData());
        AdfFacesContext.getCurrentInstance().addPartialTarget(pc1);
    }

    public void setCmi_errores(RichCommandMenuItem cmi2) {
        this.cmi_errores = cmi2;
    }

    public RichCommandMenuItem getCmi_errores() {
        return cmi_errores;
    }

    public void cmi_errores_actionListener(ActionEvent actionEvent) {
        if(t_datos.getSelectedRowKeys() == null){
            showMessage(getText("seleccionar_registro"),
                        FacesMessage.SEVERITY_ERROR);
            return;
        }
        RowTO fila = (RowTO)t_datos.getSelectedRowData();
        it_fila.setValue( fila.getIndice() );
        it_errores.setValue( fila.getMensaje() );
        AdfFacesContext.getCurrentInstance().addPartialTarget(p_errores);
        showPopup(p_errores, true);
    }

    public void setP_errores(RichPopup p1) {
        this.p_errores = p1;
    }

    public RichPopup getP_errores() {
        return p_errores;
    }

    public void setD_errores(RichDialog d1) {
        this.d_errores = d1;
    }

    public RichDialog getD_errores() {
        return d_errores;
    }

    public void setPfl2(RichPanelFormLayout pfl2) {
        this.pfl2 = pfl2;
    }

    public RichPanelFormLayout getPfl2() {
        return pfl2;
    }


    public void setIt_fila(RichInputText it1) {
        this.it_fila = it1;
    }

    public RichInputText getIt_fila() {
        return it_fila;
    }

    public void setIt_errores(RichInputText it2) {
        this.it_errores = it2;
    }

    public RichInputText getIt_errores() {
        return it_errores;
    }

    public void setS1(RichSpacer s1) {
        this.s1 = s1;
    }

    public RichSpacer getS1() {
        return s1;
    }

    public void setCb_cargar_data(RichCommandButton cb1) {
        this.cb_cargar_data = cb1;
    }

    public RichCommandButton getCb_cargar_data() {
        return cb_cargar_data;
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
            while(it.hasNext()){
                UsuarioConectadoTO usuarioConectado =
                    (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
                RowTO fila = (RowTO)it.next();
                ModelConverter converter =
                    tipoArchivoSeleccionado.getConverterInstance();
                if (converter instanceof TarifasAcueductoConverter) {
                    this.registrosCargados += LoadDataUtil.registrarTarifaAcueducto(converter, fila);
                }
            }
        }catch(IdeamException e){
            showMessage(e);
        }finally{
            String mensaje = "Se procesaron " + this.registrosCargados + " registros ";
            showMessage(mensaje,FacesMessage.SEVERITY_INFO);
            //this.registrosCargados = 0;
            this.listaDatos = new ArrayList();
            this.borrarColumnas();
            AdfFacesContext.getCurrentInstance().addPartialTarget(pc1);
        }
    }
    

    public boolean isHabilitarCarga() {
        return habilitarCarga;
    }

    public void setHabilitarCarga(boolean habilitarCarga) {
        this.habilitarCarga = habilitarCarga;
    }

    public int getRegistrosCargados() {
        return registrosCargados;
    }

    public void setRegistrosCargados(int registrosCargados) {
        this.registrosCargados = registrosCargados;
    }
    }
