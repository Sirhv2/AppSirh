package co.gov.ideam.sirh.datosena.business;
import co.gov.ideam.sirh.datosena.model.DatosAreaPomcas;
import co.gov.ideam.sirh.datosena.model.DatosAvanceSIRH;
import co.gov.ideam.sirh.datosena.model.DatosCargaContaminante;
import co.gov.ideam.sirh.datosena.model.DatosCuerposLenticosTO;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.datosena.model.DatosOfertaAreaTO;
import co.gov.ideam.sirh.datosena.model.DatosSIRH;
import co.gov.ideam.sirh.datosena.model.DatosSIRHAutoridad;
import co.gov.ideam.sirh.datosena.model.DatosSIRHDep;
import co.gov.ideam.sirh.datosena.model.DatosSectoresArea;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;
import javax.persistence.Query;



@Stateless(name = "DatosEnaEJB", mappedName = "Sirh-DatosEnaEJB")
@Remote
public class DatosEnaEJBBean implements DatosEnaEJB {

    @PersistenceContext(unitName = "SirhPU")
    private EntityManager em;
   
    public DatosEnaEJBBean() {

    }


    /**
     * Retorna una lista con los DatosOfertaAreaTO en el sistema
     * recibida como parametro
     * @return
     * @throws IdeamException
     */

    public List<DatosOfertaAreaTO> consultarOfertaArea() throws IdeamException{
        Query query = em.createNamedQuery("DatosOfertaAreaTO.findAll");
        return query.getResultList();     
    }

   
    /**
     * Retorna una lista con los DatosCuerposLenticosTO en el sistema
     * recibida como parametro
     * @return
     * @throws IdeamException
     */

    public List<DatosCuerposLenticosTO> consultarCuerposLenticos() throws IdeamException{
        Query query = em.createNamedQuery("DatosCuerposLenticosTO.findAll");
        return query.getResultList();     
    }


    public List<DatosSectoresArea> consultarSectoresArea() throws IdeamException{
        Query query = em.createNamedQuery("DatosSectoresArea.findAll");
        return query.getResultList();     
    }

    
    /**
     * Retorna una lista con los DatosAreaPomcas en el sistema
     * recibida como parametro
     * @return
     * @throws IdeamException
     */

    public List<DatosAreaPomcas> consultarDatosAreaPomcas() throws IdeamException{
        Query query = em.createNamedQuery("DatosAreaPomcas.findAll");
        return query.getResultList();     
    }

    /**
     * Retorna una lista con los DatosAreaPomcas en el sistema
     * recibida como parametro
     * @return
     * @throws IdeamException
     */

    public List<DatosCargaContaminante> consultarDatosCargaContaminante() throws IdeamException{
        Query query = em.createNamedQuery("DatosCargaContaminante.findAll");
        return query.getResultList();     
    }


    /**
     * Retorna datos consulta SIRH 
     * @return
     * @throws IdeamException
     */
    public List <DatosSIRH> consultarDatosSIRH() throws IdeamException {
        Query query = em.createNamedQuery("DatosSIRH.findAll");
        return query.getResultList();  
    }


    /**
     * Retorna datos consulta datos capTvert por departamento SIRH 
     * @return
     * @throws IdeamException
     */
    public List <DatosSIRHDep> consultarDatosSIRHDep() throws IdeamException {
        Query query = em.createNamedQuery("DatosSIRHDep.findAll");
        return query.getResultList();  
    }




    /**
     * Retorna datos consulta datos avance SIRH 
     * @return
     * @throws IdeamException
     */
    public List <DatosAvanceSIRH> consultarDatosAvanceSIRH() throws IdeamException {
        Query query = em.createNamedQuery("DatosAvanceSIRH.findAll");
       
        return query.getResultList();  
    }


    /**
     * Retorna datos consulta datos  SIRH por autoridad
     * @return
     * @throws IdeamException
     */
    public List <DatosSIRHAutoridad> consultarDatosSIRHAutoridad(String sigla) throws IdeamException {
        Query query = null;
        if (sigla!=null && sigla.length()!=0){
            query = em.createNamedQuery("DatosSIRHAutoridad.findSigla");
            query.setParameter("sigla", sigla);
        }else{
           query = em.createNamedQuery("DatosSIRHAutoridad.findAll");
       
        }
         
        
        return query.getResultList();  
    }






}//Fin clase

