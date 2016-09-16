package co.gov.ideam.sirh.documentos.business;

import co.gov.ideam.sirh.documentos.model.Archivo;
import co.gov.ideam.sirh.documentos.model.Nodo;
import co.gov.ideam.sirh.util.IdeamException;

import java.util.List;
import javax.ejb.Remote;

@Remote
public interface DocumentoEJB {

   public Long cargarArchivo(Nodo nodo, Archivo archivo) throws IdeamException;

   public List<Integer> cargarListaArchivos(List<Nodo> listaNodos) throws IdeamException;

   public Archivo buscarArchivo(Nodo nodo) throws IdeamException;

   public Nodo buscarNodo(Long id) throws IdeamException;

   public void eliminarDocumento(Nodo nodo) throws IdeamException;
   
}
