package co.gov.ideam.sirh.publicaciones.business;

import co.gov.ideam.sirh.documentos.model.Archivo;
import co.gov.ideam.sirh.documentos.model.Nodo;
import co.gov.ideam.sirh.parametros.business.ParametrosEJB;
import co.gov.ideam.sirh.parametros.model.Lista;
import co.gov.ideam.sirh.publicaciones.model.ArchivosXPublicacion;
import co.gov.ideam.sirh.publicaciones.model.Publicacion;
import co.gov.ideam.sirh.usuarios.agua.model.GeneradorSeq;
import co.gov.ideam.sirh.util.IdeamException;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Stateless(name = "PublicacionEJB", mappedName = "Sirh-PublicacionEJB")
@Remote
public class PublicacionEJBBean implements PublicacionEJB {
    @PersistenceContext (unitName="SirhPU")
    private EntityManager em;
    @EJB private ParametrosEJB parametrosService;

    public PublicacionEJBBean() {
    }

   public Publicacion updatePomtPublicacion(Publicacion publicacion) throws IdeamException {
        
        System.out.println(">>>>>>>>>> Paso 1 "+publicacion.getId()+" - "+publicacion.getNombrePublicacion());
        Query query = em.createNamedQuery("Publicacion.findByNombre");
        query.setParameter("nombrePublicacion", publicacion.getNombrePublicacion());
        try{
            Publicacion aux = (Publicacion)query.getSingleResult();
            if(aux==null){
               publicacion.setId(  GeneradorSeq.obtenerSequencia(new Long("1"), "seq_pomt_publicaciones", em));
               System.out.println(">>>>>>>>>> Paso 2.1 Entro TRY secuencia "+publicacion.getId());
               em.persist(publicacion); 
               System.out.println(">>>>>>>>>> Paso 2.1 OK ");
            }else{
               System.out.println(">>>>>>>>>> Paso 2.2 "+publicacion.getId());
               publicacion = em.merge(publicacion); 
               System.out.println(">>>>>>>>>> Paso 2.2 OK");
            }
            System.out.println(">>>>>>>>>>>>><<<<<<<< ID Publicacion en el entity: "+ publicacion.getId()+" - "+publicacion.getNombrePublicacion());

        }catch(Exception e){
         //     em.persist(publicacion);
            System.out.println(">>>>>>>>>>>>><<<<<<<< CATCH Publicacion : "+ e.getMessage());
            Long secuencia = GeneradorSeq.obtenerSequencia(new Long("1"), "seq_pomt_publicaciones", em);
            if(publicacion.getId()==null){
                publicacion.setId(secuencia);
                em.persist(publicacion); 
                System.out.println(">>>>>>>>>> Paso 3.1 Entro TRY secuencia "+publicacion.getId());
            
            }else{
                em.merge(publicacion); 
                System.out.println(">>>>>>>>>> Paso 3.1 OK ");
            }
                     
        } 
        em.flush();
       return publicacion;
    }
    
    public List<Publicacion> getAllPomtPublicacionByNombre(Publicacion publicacion) throws IdeamException {
        Query query = em.createNamedQuery("Publicacion.findByNombre");
        //query.setParameter("pomtPublicacion", publicacion)
        query.setParameter("nombrePublicacion", "%" + publicacion.getNombrePublicacion() + "%");
        List<Publicacion> publicaciones = query.getResultList();
        em.clear();
        return publicaciones;
    }
    
  public List<Publicacion> getAllPomtPublicacionByTipoRecurso(Integer tipoRecurso) throws IdeamException {
      Query query = em.createNamedQuery("Publicacion.findByTipoRecurso");
      //query.setParameter("pomtPublicacion", publicacion)
      query.setParameter("tipoRecurso",  tipoRecurso );
      List<Publicacion> publicaciones = query.getResultList();
      em.clear();
      return publicaciones;
  }
  
  public List<Publicacion> getAllPomtPublicacionByAH(String ah) throws IdeamException {
      Query query = em.createNamedQuery("Publicacion.findByAH");
      //query.setParameter("pomtPublicacion", publicacion)
      query.setParameter("ah" ,  ah  );
     // System.out.println("SQL  getAllPomtPublicacionByAH ->" + query.toString());
      List<Publicacion> publicaciones = query.getResultList();
      em.clear();
      return publicaciones;
  }

  public List<Publicacion> getAllPomtPublicacionBySistemaAcuifero(String sistemasAcuiferos) throws IdeamException {
      Query query = em.createNamedQuery("Publicacion.findBySistemaAcuifero");
      //query.setParameter("pomtPublicacion", publicacion)
      query.setParameter("sistemaAcuifero" ,  sistemasAcuiferos  );
      System.out.println("SQL  getAllPomtPublicacionBySistemaAcuifero ->" + query.toString());
      List<Publicacion> publicaciones = query.getResultList();
    System.out.println("CONTEO   getAllPomtPublicacionBySistemaAcuifero ->" + publicaciones.size());
      em.clear();
      return publicaciones;
  }
    public List<Publicacion> getAllPomtPublicaciones() throws IdeamException {
        Query query = em.createNamedQuery("Publicacion.findAll");
        List<Publicacion> lPublicaciones = query.getResultList();
        List<Publicacion> lAjustadas= new ArrayList<Publicacion>();
        for(int i=0;i<lPublicaciones.size();i++){
            Publicacion p= (Publicacion)lPublicaciones.get(i);
            Integer idTipoRecurso = p.getTipoRecurso();
            if(idTipoRecurso!=null){
                Lista tipo = parametrosService.getLista(idTipoRecurso);
                p.setLTipoRecurso(tipo);  
                lAjustadas.add(p);
            }
        }
       
        em.clear();
        return lAjustadas;
    }

    public void removePomtPublicacion(Publicacion publicacion) throws IdeamException{
        
        Query queryPublicacion = em.createNamedQuery("ArchivosXPublicacion.findByPublicacion");
        queryPublicacion.setParameter("publicacion", publicacion);
        try{
            ArchivosXPublicacion archiPub = (ArchivosXPublicacion)queryPublicacion.getSingleResult();
            if(archiPub != null){
                System.out.println(">>>>>> Encuntra; "+ archiPub.getId());
                Nodo nodo = null;
                Query queryNodo = em.createNamedQuery("Nodo.findById");
                System.out.println(">>>>>> Busca nodo del archivo; "+ archiPub.getId());
                queryNodo.setParameter("id", archiPub.getId());
                try{
                    nodo = (Nodo) queryNodo.getSingleResult(); 
                    System.out.println(">>>>>> Nodo "+ nodo.getId()+ " - "+nodo.getDescripcion());
                    if(nodo !=null){
                        em.remove(nodo);
                        Query queryArch = em.createNamedQuery("Archivo.findById");
                        queryArch.setParameter("id", nodo.getId());
                        Archivo arc = (Archivo)queryArch.getSingleResult();
                        em.remove(arc);
                    }
                }catch(Exception e){
                    System.out.println(">>>>>>No se encuentra el Nodo ");                
                }
                
                em.remove(archiPub);
            }           
        }catch(Exception e){
            System.out.println("No hay archivos asociados que borrar");
        }
        publicacion = em.merge(publicacion);
        em.remove(publicacion);  
    }
    
    public void updateArchivosXPublicacion(ArchivosXPublicacion archipubli) throws IdeamException{
        if (archipubli.getId() == null ||
            archipubli.getId() == 0) {
             em.persist(archipubli);
        } else { 
           // archipubli = em.merge(archipubli);
             em.merge(archipubli);
         }
    }
    
}
