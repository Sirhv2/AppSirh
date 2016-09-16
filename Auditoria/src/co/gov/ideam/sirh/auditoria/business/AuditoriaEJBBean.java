package co.gov.ideam.sirh.auditoria.business;

import co.gov.ideam.sirh.auditoria.model.Auditoria;
import co.gov.ideam.sirh.util.IdeamException;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Stateless(name = "AuditoriaEJB", mappedName = "Sirh-AuditoriaEJB")
@Remote
public class AuditoriaEJBBean implements AuditoriaEJB {
    @PersistenceContext (unitName="SirhPU")
    private EntityManager em;

    public AuditoriaEJBBean() {
    }

   public Auditoria updateAuditoria(Auditoria auditoria) throws IdeamException {
        //if (auditoria.getIdUsuario() == null ||
        //    auditoria.getIdUsuario().longValue() == 0) {
          em.persist(auditoria);
        //} else {
            auditoria = em.merge(auditoria);
        //}
        return auditoria;
    }
    
    public List<Auditoria> getAllAuditoriasByObjeto(Auditoria auditoria) throws IdeamException {
        Query query = em.createNamedQuery("Auditoria.findByObjeto");
        query.setParameter("objeto", "%" + auditoria.getObjeto() + "%");
        List<Auditoria> auditorias = query.getResultList();
        em.clear();
        return auditorias;
    }

    public List<Auditoria> getAllAuditorias() throws IdeamException {
        Query query = em.createNamedQuery("Auditoria.findAll");
        List<Auditoria> auditorias = query.getResultList();
        em.clear();
        return auditorias;
    }

    public void deleteAuditoria(Auditoria auditoria) {
        auditoria = em.merge(auditoria);
        em.remove(auditoria);
    }

}
