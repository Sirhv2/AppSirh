package co.gov.ideam.sirh.auditoria.business;
// HOGO 20141030

import co.gov.ideam.sirh.auditoria.model.NovTransmitir;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Stateless(name = "NovTransmitirEJB", mappedName = "Sirh-NovTransmitirEJB")
@Remote
public class NovTransmitirEJBBean implements NovTransmitirEJB {
    @PersistenceContext (unitName="SirhPU")
    private EntityManager em;

    public NovTransmitirEJBBean() {
    }

    public List<NovTransmitir> getAllNovTransmitir() {
        Query query = em.createNamedQuery("NovTransmitir.findAll");
        List<NovTransmitir> novedades = query.getResultList();
        em.clear();
        return novedades;
    }

    public void agregarNovTransmitir(NovTransmitir novTransmitir) {
        em.persist(novTransmitir);
        
    }
}
