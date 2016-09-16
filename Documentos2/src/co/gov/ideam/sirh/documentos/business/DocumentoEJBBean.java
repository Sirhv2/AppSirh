package co.gov.ideam.sirh.documentos.business;

import co.gov.ideam.sirh.documentos.model.Archivo;
import co.gov.ideam.sirh.documentos.model.Nodo;
import co.gov.ideam.sirh.usuarios.agua.model.GeneradorSeq;
import co.gov.ideam.sirh.util.IdeamException;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Stateless(name = "DocumentoEJB", mappedName = "Sirh-DocumentoEJB")
@Remote
public class DocumentoEJBBean implements DocumentoEJB {
    @PersistenceContext (unitName="SirhPU")
    private EntityManager em;

    public DocumentoEJBBean() {
    }
    public Long cargarArchivo(Nodo nodo, Archivo archivo)  throws IdeamException {
        
        
        System.out.println("ooo>>>>>> Descripcion: "  + nodo.getDescripcion());
        System.out.println("ooo>>>>>> Extension: "  + nodo.getExt());
        System.out.println("ooo>>>>>> Id: "  + nodo.getId());
        System.out.println("ooo>>>>>> Keywords: "  + nodo.getKeywords());
        System.out.println("oooo>>>>> Mime: " + nodo.getMime());
        System.out.println("ooo>>>>>> Codigo Autoridad: "  + nodo.getCodigoAutoridad());
        boolean nuevo = false;
        if (nodo.getId() == null) {
             nodo.setId( GeneradorSeq.obtenerSequencia(nodo.getCodigoAutoridad(), "seq_archivos", em));
             em.persist(nodo);
             nuevo = true;
          } else {
            em.merge(nodo);
        }
        archivo.setId(nodo.getId());
        if(nuevo){
            em.persist(archivo);
        }else{
            em.merge(archivo);
        }
        return nodo.getId();
    }

    public List<Integer> cargarListaArchivos(List<Nodo> listaNodos) throws IdeamException {
        return null;
    }

    public Archivo buscarArchivo(Nodo nodo) throws IdeamException{
        try {
            Query query = em.createNamedQuery("Archivo.findById");
            query.setParameter("id", nodo.getId());
            return (Archivo)query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public Nodo buscarNodo(Long id) throws IdeamException{
            Query query = em.createNamedQuery("Nodo.findById");
            query.setParameter("id", id);
            return (Nodo)query.getSingleResult();
    }
    
    public void eliminarDocumento(Nodo nodo) throws IdeamException {
        Archivo archivo = new Archivo();
        archivo = this.buscarArchivo(nodo);
        archivo = em.merge(archivo);
        em.remove(archivo);
        nodo = em.merge(nodo);
        em.remove(nodo);
    }

}
