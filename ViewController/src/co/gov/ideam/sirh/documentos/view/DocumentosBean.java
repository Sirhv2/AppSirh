package co.gov.ideam.sirh.documentos.view;


import co.gov.ideam.sirh.documentos.model.Archivo;
import co.gov.ideam.sirh.documentos.model.Nodo;
import co.gov.ideam.sirh.fuentes.model.FnttFuente;
import co.gov.ideam.sirh.parametros.model.Autoridades;
import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;
import co.gov.ideam.sirh.usuarios.agua.model.GeneradorSeq;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.FacesUtils;
import co.gov.ideam.sirh.view.StandarBean;
import co.gov.ideam.sirh.view.util.TreeNode;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.input.RichInputFile;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.nav.RichCommandImageLink;
import oracle.adf.view.rich.component.rich.nav.RichCommandMenuItem;
import org.apache.myfaces.trinidad.model.UploadedFile;




public class DocumentosBean extends StandarBean {

    private RichDocument document1;
    private RichForm form1;

    private RichCommandMenuItem commandMenuItem1;
    private RichCommandMenuItem commandMenuItem2;

    private RichPopup p1;
    private RichCommandButton cb_regresar;

    private String contentType;
    private String fileName;
    
    private RichPopup popup1;
    private RichPopup popupExito;
    private RichInputFile if_dcoumento;
    
    
    
    public DocumentosBean() {
        FacesUtils.setActiveBean("documentos", "documentos", DocumentosDelegate.class);
        this.load();
    }

    public void load() {
    }


    public void if_archivo_valueChangeListener(ValueChangeEvent valueChangeEvent) throws IdeamException{
        UploadedFile file = (UploadedFile)valueChangeEvent.getNewValue();
        System.out.println("XXXXXXXXXxXXXXXXX File:"+ file.getLength());
        if (file!=null){
            /*String tipoArchivo = file.getContentType();
            if (!tipoArchivo.endsWith("pdf")){                     
                String params[] = { file.getFilename(), "PDF" };            
                showMessage("tipo_archivo_incorrecto", params, FacesMessage.SEVERITY_ERROR, if_dcoumento);
                this.if_dcoumento.resetValue();
                this.setUploadedFile(null);
                //this.archivoCargado=null;
                return;
            }*/
            this.setUploadedFile(file, null);
        }
    }
    
    
    public void descargarDocumento(ActionEvent actionEvent) throws IdeamException, IOException {
          String baseURL = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
          String nodoId  = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("nodoId");
          String displayInline = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("displayInline");
         
          String url = baseURL + "/downloadservlet?nodoId="
                       +nodoId
                       +"&displayInline=" 
                       + displayInline;
          try {
              String encodeURL = FacesContext.getCurrentInstance().getExternalContext().encodeResourceURL(url);
              FacesContext.getCurrentInstance().getExternalContext().redirect(encodeURL);
          } catch (Exception e) {
              System.out.println("-----------------------"+e+"-----------");
          } finally {
              FacesContext.getCurrentInstance().responseComplete();
          }
    }
    
    public void descargarArchivoId(FacesContext context, OutputStream out, Long idArchivo) throws IdeamException, IOException{
       DocumentosDelegate pomcad = DocumentosDelegate.getInstance();   
       if(idArchivo != null){
           
           Nodo nodo = pomcad.buscarNodo(idArchivo);
         
           if(nodo !=null){
               System.out.println(">>>>>>>>> Nodo: "+nodo.getMime());
               System.out.println(">>>>>>>>> Nodo: "+nodo.getDescripcion());
               this.setContentType(nodo.getMime());
               //this.setContentType("text/plain charset=UTF-8");
               this.setFileName(nodo.getDescripcion());
          
               System.out.println(">>>>>>> mire el id parce: "+idArchivo);
               Archivo aux = pomcad.descargarArchivo(nodo);
               out.write(aux.getArchivo());  
               out.flush();
               out.close();
               
               /*OutputStreamWriter w = new OutputStreamWriter(out, "UTF-8");
               w.write("Hi there!");
               //w.write(pomcad.descargarArchivo(nodo).getArchivo());
               w.flush();
               w.close();*/
          }
       }    
   }
    

    
    public Nodo setUploadedFile(UploadedFile uploadedFile, Long idArchivo) throws IdeamException {
       Nodo nodo = null;
       if (uploadedFile!=null){
            System.out.println(">>>>>>>>><<<ARCHIVOOO DOCUMENTOS setUploadedFile:"
                                + uploadedFile.getFilename()
                                +" - "
                                + uploadedFile.getContentType());   
            try {
                InputStream is = uploadedFile.getInputStream();
                byte[] archivoSave = new byte[new Long(uploadedFile.getLength()).intValue()];
                is.read(archivoSave);
                File file = new File(uploadedFile.getFilename());

                nodo = new Nodo();
                
                UsuarioConectadoTO usuarioConectado = (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
                Autoridades autoridadAmbiental =  usuarioConectado.getUsuario().getAutoridadAmbiental();
                nodo.setCodigoAutoridad(new Long(autoridadAmbiental.getId()));
                
               
                System.out.println(">>>>>>>>><<nodo.getId() setUploadedFile:"+nodo.getId());
                nodo.setDescripcion(uploadedFile.getFilename());
                nodo.setMime(uploadedFile.getContentType());
                nodo.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
                String ext = new Scanner(file.getName()).findInLine("\\.\\w*");
                nodo.setExt((ext != null) ? ext.toUpperCase() : "");
                
                Archivo archivo = new Archivo();
                archivo.setArchivo(archivoSave);
                
                if(idArchivo != null){
                    nodo.setId(idArchivo);
                    archivo.setId(idArchivo);
                }
                
                
                
                DocumentosDelegate pomcad = DocumentosDelegate.getInstance();
                nodo.setId(pomcad.cargarArchivo(nodo, archivo));
                return nodo;
            }  catch( IOException e){
                showMessage(e.getLocalizedMessage());
                System.out.println(e);
            }
       }
       return nodo;    
    }
    public Nodo setUploadedFilePlan(UploadedFile uploadedFile, Long idArchivo, Long idAutoridad) throws IdeamException {
       Nodo nodo = null;
       if (uploadedFile!=null){
            System.out.println(">>>>>>>>><<<ARCHIVOOO DOCUMENTOS setUploadedFilePlan:"
                                + uploadedFile.getFilename()
                                +" - "
                                + uploadedFile.getContentType());   
            try {
                InputStream is = uploadedFile.getInputStream();
                byte[] archivoSave = new byte[new Long(uploadedFile.getLength()).intValue()];
                is.read(archivoSave);
                File file = new File(uploadedFile.getFilename());

                nodo = new Nodo();
                System.out.println(">>>>>>>>><<idAutoridad setUploadedFilePlan:"+idAutoridad);
         
                nodo.setDescripcion(uploadedFile.getFilename());
                nodo.setMime(uploadedFile.getContentType());
                nodo.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
                String ext = new Scanner(file.getName()).findInLine("\\.\\w*");
                nodo.setExt((ext != null) ? ext.toUpperCase() : "");
                
                Archivo archivo = new Archivo();
                archivo.setArchivo(archivoSave);
                
                if(idArchivo != null){
                    nodo.setId(idArchivo);
                    archivo.setId(idArchivo);
                }
                
                UsuarioConectadoTO usuarioConectado = (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
                Autoridades autoridadAmbiental =  usuarioConectado.getUsuario().getAutoridadAmbiental();
                nodo.setCodigoAutoridad(new Long(autoridadAmbiental.getId()));
                
                DocumentosDelegate pomcad = DocumentosDelegate.getInstance();
                nodo.setId(pomcad.cargarArchivo(nodo, archivo));
                return nodo;
            }  catch( IOException e){
                showMessage(e.getLocalizedMessage());
                System.out.println(e);
            }
       }
       return nodo;    
    }
   
    public void cb_aceptar_actionListener(ActionEvent actionEvent) {
        showPopup(popup1, true);
    }

    public void cb_borrar_docto_actionListener(ActionEvent actionEvent) {
        showPopup(p1, true);
    }

    public void setCb_regresar(RichCommandButton cb1) {
        this.cb_regresar = cb1;
    }

    public RichCommandButton getCb_regresar() {
        return cb_regresar;
    }

    public RichDocument getDocument1() {
        return document1;
    }

    public void setDocument1(RichDocument document1) {
        this.document1 = document1;
    }

    public RichForm getForm1() {
        return form1;
    }

    public void setForm1(RichForm form1) {
        this.form1 = form1;
    }


    public RichCommandMenuItem getCommandMenuItem1() {
        return commandMenuItem1;
    }

    public void setCommandMenuItem1(RichCommandMenuItem commandMenuItem1) {
        this.commandMenuItem1 = commandMenuItem1;
    }

    public RichCommandMenuItem getCommandMenuItem2() {
        return commandMenuItem2;
    }

    public void setCommandMenuItem2(RichCommandMenuItem commandMenuItem2) {
        this.commandMenuItem2 = commandMenuItem2;
    }

    public RichPopup getPopup1() {
        return popup1;
    }

    public void setPopup1(RichPopup popup1) {
        this.popup1 = popup1;
    }

    public void setIf_dcoumento(RichInputFile if1) {
        this.if_dcoumento = if1;
    }

    public RichInputFile getIf_dcoumento() {
        return if_dcoumento;
    }

    public RichPopup getPopupExito() {
        return popupExito;
    }

    public void setPopupExito(RichPopup popupExito) {
        this.popupExito = popupExito;
    }


    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
