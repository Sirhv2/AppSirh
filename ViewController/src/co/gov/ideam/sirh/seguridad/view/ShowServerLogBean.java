package co.gov.ideam.sirh.seguridad.view;

import co.gov.ideam.sirh.view.StandarBean;

import java.io.BufferedReader;
import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.layout.RichPanelBox;

public class ShowServerLogBean extends StandarBean{
    private RichForm f2;
    private RichDocument d2;
    private RichPanelBox pb1;
    private RichInputText it_contenido;

    private String fileName = "server.log";
    private String folderName = "";
    private String contenidoArchivo;

    public ShowServerLogBean(){
        this.load();
    }
    
    public void load(){
        folderName = System.getProperty("jboss.server.log.dir");         
        File archivo = new File(folderName + File.separatorChar + fileName);
        if (!archivo.exists()){
            showMessage("Archivo no Existe");
            return;
        }
        BufferedReader br = null;
        try { 
            br = new BufferedReader(new FileReader(archivo)); 
            StringBuilder sb = new StringBuilder(); 
            String line = br.readLine(); 
     
            while (line != null) { 
                sb.append(line); 
                sb.append("\n"); 
                line = br.readLine(); 
            } 
            contenidoArchivo = sb.toString(); 
        }catch(FileNotFoundException e) {
            showMessage(e.getMessage());   
        }catch(IOException e) {
            showMessage(e.getMessage());   
        }finally { 
            try{
                if(br!=null){
                    br.close(); 
                }
            }catch(IOException e){
                showMessage(e.getMessage());   
            }
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

    public void setPb1(RichPanelBox pb1) {
        this.pb1 = pb1;
    }

    public RichPanelBox getPb1() {
        return pb1;
    }

    public void setIt_contenido(RichInputText it1) {
        this.it_contenido = it1;
    }

    public RichInputText getIt_contenido() {
        return it_contenido;
    }

    public String getContenidoArchivo() {
        return contenidoArchivo;
    }

    public void setContenidoArchivo(String contenidoArchivo) {
        this.contenidoArchivo = contenidoArchivo;
    }
}
