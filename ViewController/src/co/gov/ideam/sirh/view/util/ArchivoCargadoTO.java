package co.gov.ideam.sirh.view.util;

import co.gov.ideam.sirh.util.IdeamException;

import co.gov.ideam.sirh.view.FacesUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
/**
 * Recoge la informacion de un archivo seleccionado para cargar y luego
 * permite la carga del mismo 
 */
public class ArchivoCargadoTO {
    
    /**
     * Carpeta en la cual deben ser almacenados los archivos cargados
     */
    public static final String folder = "archivosTemporalesApp";
    
    
    private File file;
    private Long size;
    private InputStream inputStream;
    private String fileName;
    private String type;
    
    private String tipoCarga;
    
    private static String FILE_NAME = "__TempFile__";
    private static Long   NUMBER_FILE_NAME = 1L;
    
    public ArchivoCargadoTO() {
        super();
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    
    public byte[] getContent()throws IdeamException{
        try{                      
            String folderName = FacesUtils.getFolferFiles() + 
                                        File.separator + ArchivoCargadoTO.folder +
                                        File.separator;
            
            File destination = new File(folderName);               
            if (!destination.exists()){
                destination.mkdirs();
            }            
            
            String fileName = FILE_NAME + (NUMBER_FILE_NAME++) + ".__sirh__";
            File archivo = new File(folderName, fileName);                
            if (archivo.exists()){
                archivo.delete();
            }
            
            InputStream in = this.getInputStream();
            FileOutputStream out = new FileOutputStream(archivo);
            int bytes = -1; 
            while ((bytes = in.read()) > -1){
                out.write(bytes); 
            }
            in.close();
            out.close();
            
            FileInputStream fin = new FileInputStream(archivo);
            byte fileContent[] = new byte[(int)archivo.length()];
            fin.read(fileContent);
            if (archivo!=null){
                archivo.delete();
            }
                        
            return fileContent;
        }catch(IOException e){
            throw new IdeamException(e);
        }
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    /**
     * Alamcena el archivo en el disco del servidor
     * @param content
     * @throws IdeamException
     */
    public String saveFile(byte content[])throws IdeamException{
        String folderName = FacesUtils.getFolferFiles() + 
                                    File.separator + ArchivoCargadoTO.folder +
                                    File.separator;
        
        File destination = new File(folderName);               
        if (!destination.exists()){
            destination.mkdirs();
        }            
        try {
            String fileName = FILE_NAME + (NUMBER_FILE_NAME++) + ".__fenix__";
            File archivo = new File(destination, fileName);
            FileOutputStream out = new FileOutputStream(archivo);
            out.write(content);
            out.close();
            return archivo.getName();
        } catch (IOException e) {
            throw new IdeamException(e);
        }        
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof ArchivoCargadoTO)) {
            return false;
        }
        final ArchivoCargadoTO other = (ArchivoCargadoTO)object;
        if (!(file == null ? other.file == null : file.equals(other.file))) {
            return false;
        }
        if (!(fileName == null ? other.fileName == null : fileName.equals(other.fileName))) {
            return false;
        }
        if (!(type == null ? other.type == null : type.equals(other.type))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 37;
        int result = 1;
        result = PRIME * result + ((file == null) ? 0 : file.hashCode());
        result = PRIME * result + ((fileName == null) ? 0 : fileName.hashCode());
        result = PRIME * result + ((type == null) ? 0 : type.hashCode());
        return result;
    }

    public void setTipoCarga(String tipoCarga) {
        this.tipoCarga = tipoCarga;
    }

    public String getTipoCarga() {
        return tipoCarga;
    }
}
