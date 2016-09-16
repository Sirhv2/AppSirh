package co.gov.ideam.sirh.directorio.business;


import co.gov.ideam.sirh.directorio.model.ActEspecialistaFormacion;
import co.gov.ideam.sirh.directorio.model.ActEspecialistas;
import co.gov.ideam.sirh.directorio.model.ActEspecilistasPropiedades;
import co.gov.ideam.sirh.directorio.model.ActOrganizaciones;

import co.gov.ideam.sirh.directorio.model.ActOrganizacionesPropiedades;
import co.gov.ideam.sirh.directorio.model.CriterioBusquedaEspecialistaTO;
import co.gov.ideam.sirh.parametros.business.ParametrosEJB;
import co.gov.ideam.sirh.parametros.model.Autoridades;

import co.gov.ideam.sirh.parametros.model.Divipola;
import co.gov.ideam.sirh.parametros.model.Lista;
import co.gov.ideam.sirh.util.IdeamException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import co.gov.ideam.sirh.usuarios.agua.model.GeneradorSeq;

@Stateless(name = "DirectorioEJB", mappedName = "Sirh-DirectorioEJB")
@Remote
public class DirectorioEJBBean implements DirectorioEJB {
    
    @PersistenceContext (unitName="SirhPU")
    private EntityManager em;  
    @EJB 
    private ParametrosEJB parametrosService;
    
    
    public DirectorioEJBBean() {
    }  
    
    /**
     * Retorna el especialista correspondiente al tipo y numero de identificacion
     * recibido como parametro
     * @param tipoId
     * @param numeroId
     * @return
     * @throws IdeamException
     */
    public ActEspecialistas getEspecialista(Long tipoId, String numeroId)throws IdeamException{
        try{
            Query query = em.createNamedQuery("ActEspecialistas.findIdentificacion");
            query.setParameter("tipoId", tipoId);
            query.setParameter("numeroId", numeroId);
            return (ActEspecialistas)query.getSingleResult();
            
            
            
            
            
        }catch(NoResultException e){
            return null;
        }
    }
    
    
    public ActEspecialistas getEspecialistaId(Long ident)throws IdeamException{
        try{
            Query query = em.createNamedQuery("ActEspecialistas.find");
            query.setParameter("codigo", ident);
         
            
            ActEspecialistas esp = (ActEspecialistas)query.getSingleResult();
            this.loadAtributtes(esp);
            return esp;
            
        }catch(NoResultException e){
            return null;
        }
    }
    /**
     * Borra la organizacion recibida por parametro
     * @param organizacion
     * @throws IdeamException
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void deleteOrganizacion(ActOrganizaciones organizacion)throws IdeamException{
        Query query = em.createNamedQuery("ActEspecialistas.updateOrganizacion");
        query.setParameter("codigoOrganizacion", organizacion.getId());
        query.executeUpdate();
        
        query = em.createNamedQuery("ActOrganizacionesPropiedades.deleteByOrganizacion");
        query.setParameter("codigoOrganizacion", organizacion.getId());
        query.executeUpdate();

        try{
            query = em.createNamedQuery("ActOrganizaciones.find");
            query.setParameter("id", organizacion.getId());
            ActOrganizaciones existe = (ActOrganizaciones)query.getSingleResult();
            if(existe!=null){
                em.remove( existe );
            }
        }catch(NoResultException e){
            ;
        }
    }
    /**
     * Borra el especialista recibido como parametro
     * @param especialista
     * @throws IdeamException
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void deleteEspecialista(ActEspecialistas especialista)throws IdeamException{
        
        Query query = em.createNamedQuery("ActEspecilistasPropiedades.deleteByEspecialista");
        query.setParameter("idEspecialista", especialista.getId());
        query.executeUpdate();
        
        query = em.createNamedQuery("ActEspecialistaFormacion.deleteByEspecialista");
        query.setParameter("codigoEspecialista", especialista.getId());
        query.executeUpdate();
        
        try{
            query = em.createNamedQuery("ActEspecialistas.find");
            query.setParameter("codigo", especialista.getId());        
            ActEspecialistas existe = (ActEspecialistas)query.getSingleResult();
            if(existe!=null){
                em.remove( existe );
            }
        }catch(NoResultException e){
            ;
        }
    }
    /**
     * Retorna una lista con las organizaciones que cumplen con los criterios de
     * busqueda recibidos por parametro
     * @param criterios
     * @return
     * @throws IdeamException
     */
    public List<ActOrganizaciones> getAllOrganizaciones(CriterioBusquedaEspecialistaTO criterios)throws IdeamException{
        String sql = "select * from act_organizaciones where ";
        String where = "";
        
        if (criterios.getListaDeptos()!=null && criterios.getListaDeptos().size()>0){
            where += " id_departamento in (" + criterios.getDeptos() + " )";
        }
        if (criterios.getListaMunicipios()!=null && criterios.getListaMunicipios().size()>0){
            if (where.length()>0){
                where += " and ";
            }
            where += " id_municipio in (" + criterios.getMunicipios() + " )";
        }

        if (criterios.getListaGestion()!=null && criterios.getListaGestion().size()>0){
            if (where.length()>0){
                where += " and ";
            }
            where += " id in (select id_organizacion from act_organizaciones_prop where id_lista in ( " +
                     criterios.getGestion() + " ) and id_categoria = 58)";
        }

        if (criterios.getListaInvestigacion()!=null && criterios.getListaInvestigacion().size()>0){
            if (where.length()>0){
                where += " and ";
            }
            where += " id in (select id_organizacion from act_organizaciones_prop where id_lista in ( " +
                     criterios.getInvestigacion() + " ) and id_categoria = 59)";
        }

        if (criterios.getNombre()!=null){
            if (where.length()>0){
                where += " and ";
            }
            where += " upper(nombre) like '%" + criterios.getNombre().toUpperCase() + "%'";        
        }
        
        sql = sql + where;        
        
        Query query = em.createNativeQuery(sql, ActOrganizaciones.class);
        List lista = query.getResultList();
        Iterator<ActOrganizaciones> it = lista.iterator();
        while(it.hasNext()){
            ActOrganizaciones org = it.next();
            this.loadAtributtes(org);
        }
        return lista;
    }
    /**
     * Retorna una lista con los especialistas que cumplen los criterios de consulta
     * enviados por parametro
     * @param criterios
     * @return
     * @throws IdeamException
     */
    public List<ActEspecialistas> getAllEspecialistas(CriterioBusquedaEspecialistaTO criterios)throws IdeamException{
        String sql = "select * from act_especialistas where ";
        String where = "";
        
        if (criterios.getListaDeptos()!=null && criterios.getListaDeptos().size()>0){
            where += " depto in (" + criterios.getDeptos() + " )";
        }
        if (criterios.getListaMunicipios()!=null && criterios.getListaMunicipios().size()>0){
            if (where.length()>0){
                where += " and ";
            }
            where += " municipio in (" + criterios.getMunicipios() + " )";
        }

        if (criterios.getListaGestion()!=null && criterios.getListaGestion().size()>0){
            if (where.length()>0){
                where += " and ";
            }
            where += " id in (select id_especialista from act_especilistas_prop where id_lista in ( " +
                     criterios.getGestion() + " ) and id_categoria = 58)";
        }

        if (criterios.getListaInvestigacion()!=null && criterios.getListaInvestigacion().size()>0){
            if (where.length()>0){
                where += " and ";
            }
            where += " id in (select id_especialista from act_especilistas_prop where id_lista in ( " +
                     criterios.getInvestigacion() + " ) and id_categoria = 59)";
        }
        
        if(criterios.getNombre()!=null){
            if (where.length()>0){
                where += " and ";
            }
            where += " upper(nombre) like '%" + criterios.getNombre().toUpperCase() + "%'";
        }
        sql = sql + where;        
        
        Query query = em.createNativeQuery(sql, ActEspecialistas.class);
        List lista = query.getResultList();
        Iterator<ActEspecialistas> it = lista.iterator();
        while(it.hasNext()){
            ActEspecialistas esp = it.next();
            this.loadAtributtes(esp);
        }
        return lista;
    }
    /**
     * Inserta o actualiza un nivel de formacion de un especialista
     * @param formacion
     * @throws IdeamException
     */
    private void upateFormacion(ActEspecialistaFormacion formacion)throws IdeamException{
        if (formacion.getId()==null || formacion.getId().longValue()==0){
            em.persist(formacion);
        }else{
            em.merge(formacion);
        }
    }
    
    /**
     * Retorna una lista con la formacion asociada al especialista recibido
     * como parametro
     * @param especialista
     * @return
     * @throws IdeamException
     */
    public List<ActEspecialistaFormacion> getAllFormacion(ActEspecialistas especialista)throws IdeamException{
        Query query = em.createNamedQuery("ActEspecialistaFormacion.findAllByEspecialista");
        query.setParameter("codigoEspecialista", especialista.getId());
        List lista = query.getResultList();
        Iterator<ActEspecialistaFormacion> it = lista.iterator();
        while(it.hasNext()){
            ActEspecialistaFormacion frm = it.next();
            this.loadAtributtes( frm );
        }
        return lista;
    }
    /**
     * Retorna la organizacion correspondiente al codigo recibido como
     * parametro
     * @param codigo
     * @return
     * @throws IdeamException
     */
    private ActOrganizaciones getOrganizacion(Long codigo)throws IdeamException{
        Query query = em.createNamedQuery("ActOrganizaciones.find");
        query.setParameter("id", codigo);
        ActOrganizaciones org = (ActOrganizaciones)query.getSingleResult();
        this.loadAtributtes(org);
        return org;
    }
    /**
     * Retorna una lista con las propiedades del especialista recibido
     * como parametro
     * @param especialista
     * @return
     * @throws IdeamException
     */
    private List<ActEspecilistasPropiedades> getAllPropiedades(ActEspecialistas especialista)throws IdeamException{
        Query query = em.createNamedQuery("ActEspecilistasPropiedades.findAllByEspecialista");
        query.setParameter("idEspecialista", especialista.getId());
        return query.getResultList();
    }    
    /**
     * Inserta las propiedades del especialista recibido como
     * parametro
     * @param especialista
     * @throws IdeamException
     */
    private void updatePropiedades(ActEspecialistas especialista)throws IdeamException{        
        if (especialista.getPropiedades()!=null){
            Iterator<ActEspecilistasPropiedades> it = 
                especialista.getPropiedades().iterator();
            while(it.hasNext()){
                ActEspecilistasPropiedades propiedad = it.next();
                ActEspecilistasPropiedades nueva = new 
                    ActEspecilistasPropiedades();
                nueva.setCodigoAutoridad( especialista.getIdAutoridad() );
                nueva.setIdCategoria( propiedad.getIdCategoria() );
                nueva.setIdLista( propiedad.getIdLista() );                
                nueva.setIdEspecialista(especialista.getId());
                em.persist(nueva);
            }
        }
    }    
    /**
     * Borra todas las propiedades asociadas al especialista recibido como
     * parametro
     * @param especialista
     * @throws IdeamException
     */
    private void deletePropiedades(ActEspecialistas especialista)throws IdeamException{
        Query query = em.createNamedQuery("ActEspecilistasPropiedades.deleteByEspecialista");
        query.setParameter("idEspecialista", especialista.getId());
        query.executeUpdate();
    }    
    /**
     * Inserta o actualiza el especialista recibido como parametro
     * @param especialista
     * @return
     * @throws IdeamException
     */
    public ActEspecialistas updateEspecialista(ActEspecialistas especialista, List<ActEspecialistaFormacion> formacion)throws IdeamException{
        ActEspecialistas actualizada = null;
        if(especialista.getId()==null || especialista.getId().longValue()==0){
            especialista.setId( GeneradorSeq.obtenerSequencia(especialista.getIdAutoridad(), "seq_actores", em));

            em.persist(especialista);
            actualizada = especialista;
        }else{
            actualizada = em.merge(especialista);                        
        }        
        actualizada.setPropiedades( especialista.getPropiedades() );
        this.deletePropiedades(actualizada);
        em.flush();
        this.updatePropiedades(actualizada);
        
        if (formacion!=null){
            Iterator<ActEspecialistaFormacion> it = formacion.iterator();
            while(it.hasNext()){
                ActEspecialistaFormacion frm = it.next();
                frm.setIdEspecialista( actualizada.getId() );
                frm.setIdFormacion( frm.getListaFormacion().getCodigo().longValue() );
                this.upateFormacion( frm );
            }
        }
        
        actualizada.setPropiedades( this.getAllPropiedades( actualizada ) );
        return actualizada;
    }
    /**
     * Retorna una lista con todos los especialistas asociados a la autoridad
     * ambiental recibida como parametro
     * @param autoridadAmbiental
     * @return
     * @throws IdeamException
     */
    public List<ActEspecialistas> getAllEsoecialistas(Autoridades autoridadAmbiental)throws IdeamException{
        Query query = em.createNamedQuery("ActEspecialistas.findAllByAutoridad");
        query.setParameter("codigoAutoridad", autoridadAmbiental.getId().longValue());
        List lista = query.getResultList();
        Iterator<ActEspecialistas> it = lista.iterator();
        while(it.hasNext()){
            ActEspecialistas esp = it.next();
            this.loadAtributtes( esp );
        }        
        return lista;
    }

    /**
     * Retorna una lista con las propiedades de la organizacion recibida
     * como parametro
     * @param organizacion
     * @return
     * @throws IdeamException
     */
    private List<ActOrganizacionesPropiedades> getAllPropiedades(ActOrganizaciones organizacion)throws IdeamException{
        Query query = em.createNamedQuery("ActOrganizacionesPropiedades.findAllByOrganizacion");
        query.setParameter("codigoOrganizacion", organizacion.getId());
        return query.getResultList();
    }
    /**
     * Inserta las propiedades de la organizacion recibida como
     * parametro
     * @param organizacion
     * @throws IdeamException
     */
    private void updatePropiedades(ActOrganizaciones organizacion)throws IdeamException{        
        if (organizacion.getPropiedades()!=null){
            Iterator<ActOrganizacionesPropiedades> it = organizacion.getPropiedades().iterator();
            while(it.hasNext()){
                ActOrganizacionesPropiedades propiedad = it.next();
                ActOrganizacionesPropiedades nueva = new 
                    ActOrganizacionesPropiedades();
                nueva.setCodigoAutoridad( organizacion.getCodigoAutoridad() );
                nueva.setIdCategoria( propiedad.getIdCategoria() );
                nueva.setIdLista( propiedad.getIdLista() );                
                nueva.setIdOrganizacion(organizacion.getId());
                em.persist(nueva);
            }
        }
    }
    /**
     * Borra todas las propiedades asociadas a la organizacion recibida como
     * parametro
     * @param organizacion
     * @throws IdeamException
     */
    private void deletePropiedades(ActOrganizaciones organizacion)throws IdeamException{
        Query query = em.createNamedQuery("ActOrganizacionesPropiedades.deleteByOrganizacion");
        query.setParameter("codigoOrganizacion", organizacion.getId());
        query.executeUpdate();
    }
    /**
     * Inserta o actualiza la organizacion recibida como parametro
     * @param organizacion
     * @return
     * @throws IdeamException
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public ActOrganizaciones updateOrganizacion(ActOrganizaciones organizacion)throws IdeamException{
        ActOrganizaciones actualizada = null;
        if(organizacion.getId()==null || organizacion.getId().longValue()==0){
            organizacion.setId( GeneradorSeq.obtenerSequencia(organizacion.getCodigoAutoridad(), "seq_actores", em));

            em.persist(organizacion);
            actualizada = organizacion;
        }else{
            actualizada = em.merge(organizacion);                        
        }        
        actualizada.setPropiedades( organizacion.getPropiedades() );
        this.deletePropiedades(actualizada);
        em.flush();
        this.updatePropiedades(actualizada);
        actualizada.setPropiedades( this.getAllPropiedades( actualizada ) );
        return actualizada;
    }
    /**
     * Retorna una lista de de todas las organizaciones asociadas a la 
     * autoridad recibida como parametro
     * @param autoridad
     * @return
     * @throws IdeamException
     */
    public List<ActOrganizaciones> getAllOrganizaciones(Autoridades autoridad)throws IdeamException{
        Query query = em.createNamedQuery("ActOrganizaciones.findAllByAutoridad");
        query.setParameter("codigoAutoridad", autoridad.getId().longValue());
        List<ActOrganizaciones> lista = query.getResultList();
        Iterator<ActOrganizaciones> it = lista.iterator();
        while(it.hasNext()){
            ActOrganizaciones org = it.next();
            this.loadAtributtes( org );
        }
        return lista;
    }
    
    public List<ActOrganizaciones> getAllOrganizaciones()throws IdeamException{
        Query query = em.createNamedQuery("ActOrganizaciones.findAll");
    
        List<ActOrganizaciones> lista = query.getResultList();
        Iterator<ActOrganizaciones> it = lista.iterator();
        while(it.hasNext()){
            ActOrganizaciones org = it.next();
            this.loadAtributtes( org );
        }
        return lista;
    }
    /**
     * Carga los atributos adicionales a la clase que fueron definidos como
     * transaient
     * @param organizacion
     * @throws IdeamException
     */
    private void loadAtributtes(ActOrganizaciones organizacion)throws IdeamException{
        Lista naturaleza = parametrosService.getLista(organizacion.getNaturaleza().intValue());
        organizacion.setListaNAturaleza( naturaleza );
        
        Lista tipoOrganizacion = parametrosService.getLista(organizacion.getTipoOrganizacion().intValue());
        organizacion.setListaTipoOrganizacion( tipoOrganizacion );
        
        Divipola depto = parametrosService.getDivipolaById( organizacion.getDepto() );
        organizacion.setDivipolaDepto( depto );
        
        Divipola municipio = parametrosService.getDivipolaById( organizacion.getMunicipio() );
        organizacion.setDivipolaMunicipio( municipio );
        
        organizacion.setPropiedades( this.getAllPropiedades( organizacion ) );
    }
    
    /**
     * Carga los atributos adicionales a la clase que fueron definidos como
     * transaient
     * @param especialista
     * @throws IdeamException
     */
    private void loadAtributtes(ActEspecialistas especialista)throws IdeamException{        
        Lista tipoId = parametrosService.getLista( especialista.getTipoId().intValue() );
        especialista.setListaTipoId( tipoId );
        
        Divipola depto = parametrosService.getDivipolaById( especialista.getDepto() );
        especialista.setDivipolaDepartamento( depto );
        
        Divipola municipio = parametrosService.getDivipolaById( especialista.getMunicipio() );
        especialista.setDivipolaMunicipio( municipio );
        
        especialista.setPropiedades( this.getAllPropiedades( especialista ) );
        
        if (especialista.getIdOrganizacion()!=null &&
            especialista.getIdOrganizacion().longValue() >0 ){
            ActOrganizaciones organizacion = this.getOrganizacion( especialista.getIdOrganizacion() );
            especialista.setOrganizacion( organizacion );
        }
    }    
    /**
     * Asocia los atributos correspondientes a la formacion recibida como
     * parametro
     * @param formacion
     * @throws IdeamException
     */
    private void loadAtributtes(ActEspecialistaFormacion formacion)throws IdeamException{        
        Lista frm = parametrosService.getLista( formacion.getIdFormacion().intValue() );
        formacion.setListaFormacion( frm );
    }
}
