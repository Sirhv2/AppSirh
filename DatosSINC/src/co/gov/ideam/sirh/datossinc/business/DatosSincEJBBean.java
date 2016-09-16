package co.gov.ideam.sirh.datossinc.business;



import co.gov.ideam.sirh.datossinc.model.ConcesionSinc;
import co.gov.ideam.sirh.datossinc.model.PermisoVertimientoSinc;
import co.gov.ideam.sirh.datossinc.model.PredioSinc;
import co.gov.ideam.sirh.datossinc.model.UsuarioAguaSinc;
import co.gov.ideam.sirh.datossinc.model.RepresentanteSinc;

import co.gov.ideam.sirh.util.IdeamException;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;



@Stateless(name = "DatosSincEJB", mappedName = "Sirh-DatosSincEJB")
@Remote
public class DatosSincEJBBean implements DatosSincEJB {

    @PersistenceContext(unitName = "SirhPU")
    private EntityManager em;
   
    public DatosSincEJBBean() {

    }

    /**
     * Retorna datos consulta Sinc 
     * @return
     * @throws IdeamException
     */
    public List consultarDatosRUA() throws IdeamException {
        Query query = em.createNamedQuery("TramitesTO.findAll");
        return query.getResultList();  
    }



    public List consultarDatosRUAxAut(Long codigoAutoridad) throws IdeamException {
        Query query = em.createNamedQuery("TramitesTO.findByAutoridad");
        query.setParameter("codigoAutoridad", codigoAutoridad);
        List lista = query.getResultList();
        return lista;
    }




    public UsuarioAguaSinc consultarUsuarioRua(Long codigoUsuario) throws IdeamException {
        Query query = em.createNamedQuery("UsuarioAguaSinc.find");
        query.setParameter("codigo", codigoUsuario);
        UsuarioAguaSinc us = (UsuarioAguaSinc)query.getSingleResult();
        return us;
    }


    public RepresentanteSinc consultarRepresentanteRua(Long codigoUsuario) throws IdeamException {
        Query query = em.createNamedQuery("RepresentanteSinc.findByUsuario");
        query.setParameter("codigoUsuario", codigoUsuario);
        RepresentanteSinc rl = (RepresentanteSinc)query.getSingleResult();
        return rl;
    }
    
    public PredioSinc consultarPredioRua(Long codigoPredio) throws IdeamException {
        Query query = em.createNamedQuery("PredioSinc.find");
        query.setParameter("codigo", codigoPredio);
        PredioSinc us = (PredioSinc)query.getSingleResult();
        return us;
    }


    
    public ConcesionSinc consultarConcesionRua(Long codigoConcesion) throws IdeamException {
        Query query = em.createNamedQuery("ConcesionSinc.find");
        query.setParameter("codigo", codigoConcesion);
        ConcesionSinc us = (ConcesionSinc)query.getSingleResult();
        return us;
    }


    
    public PermisoVertimientoSinc consultarPermisoVertimientoRua(Long codigoPV) throws IdeamException {
        Query query = em.createNamedQuery("PermisoVertimientoSinc.find");
        query.setParameter("codigo", codigoPV);
        PermisoVertimientoSinc us = (PermisoVertimientoSinc)query.getSingleResult();
        return us;
    }
}//Fin clase

