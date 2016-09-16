package co.gov.ideam.sirh.parametros.business;

import co.gov.ideam.sirh.parametros.model.ActividadCIIU;
import co.gov.ideam.sirh.parametros.model.Autoridades;
import co.gov.ideam.sirh.parametros.model.Categoria;
import co.gov.ideam.sirh.parametros.model.Divipola;
import co.gov.ideam.sirh.parametros.model.Lista;
import co.gov.ideam.sirh.parametros.model.OfertaEstacSubzonas;
import co.gov.ideam.sirh.parametros.model.OfertaSubzonas;
import co.gov.ideam.sirh.parametros.model.Parametro;
import co.gov.ideam.sirh.parametros.model.PartZonificListas;
import co.gov.ideam.sirh.parametros.model.TipoParametro;
import co.gov.ideam.sirh.util.IdeamException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless(name = "ParametrosEJB", mappedName = "Sirh-ParametrosEJB")
@Remote
public class ParametrosEJBBean implements ParametrosEJB {

    @PersistenceContext(unitName = "SirhPU")
    private EntityManager em;

    /**
     * Map para almacenar en cache los parametros que se van cargando desde la
     * base de datos
     */
    private static HashMap<Long, TipoParametro> cacheParametros =
        new HashMap<Long, TipoParametro>();

    private static HashMap<Integer, Lista> cacheListas =
        new HashMap<Integer, Lista>();

    private static HashMap<Integer, Autoridades> cacheAutoridades =
        new HashMap<Integer, Autoridades>();

    private static HashMap<Long, Divipola> cacheDivipola =
        new HashMap<Long, Divipola>();

    public ParametrosEJBBean() {
    }

    public PartZonificListas getZonificacionByName(String nombre,
                                                   PartZonificListas padre) throws IdeamException {
        try {
            if (padre != null) {
                Query query =
                    em.createNamedQuery("PartZonificListas.findByNameParent");
                query.setParameter("valor", nombre.toUpperCase());
                query.setParameter("id_padre", padre.getId());
                query.setMaxResults(1);
                return (PartZonificListas)query.getSingleResult();
            } else {
                Query query =
                    em.createNamedQuery("PartZonificListas.findByName");
                query.setParameter("valor", nombre.toUpperCase());
                query.setMaxResults(1);
                return (PartZonificListas)query.getSingleResult();
            }
        } catch (NoResultException e) {
            return null;
        }
    }

    public Divipola getDivipolaByName(String nombre,
                                      Divipola padre) throws IdeamException {
        try {
            Query query = em.createNamedQuery("Divipola.findChildByName");
            String nombreDivipola = nombre.toUpperCase().trim();
            nombreDivipola = nombreDivipola.replaceAll("Á", "A");
            nombreDivipola = nombreDivipola.replaceAll("É", "E");
            nombreDivipola = nombreDivipola.replaceAll("Í", "I");
            nombreDivipola = nombreDivipola.replaceAll("Ó", "O");
            nombreDivipola = nombreDivipola.replaceAll("Ú", "U");

            query.setParameter("nombre", nombreDivipola);
            query.setParameter("padreId", padre.getId());
            return (Divipola)query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public Divipola getDivipolaByName(String nombre,
                                      String clase) throws IdeamException {
        try {
            Query query = em.createNamedQuery("Divipola.findByName");
            String nombreDivipola = nombre.toUpperCase().trim();
            nombreDivipola = nombreDivipola.replaceAll("Á", "A");
            nombreDivipola = nombreDivipola.replaceAll("É", "E");
            nombreDivipola = nombreDivipola.replaceAll("Í", "I");
            nombreDivipola = nombreDivipola.replaceAll("Ó", "O");
            nombreDivipola = nombreDivipola.replaceAll("Ú", "U");

            query.setParameter("nombre", nombreDivipola);
            query.setParameter("clase", clase);
            return (Divipola)query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public Lista getListaByDescripcion(String descripcion,
                                       Long categoria) throws IdeamException {
        Query query = null;
        try {
            query = em.createNamedQuery("Lista.findByValor");
            String dato = descripcion.toUpperCase().trim();
            dato = dato.replaceAll("Á", "A");
            dato = dato.replaceAll("É", "E");
            dato = dato.replaceAll("Í", "I");
            dato = dato.replaceAll("Ó", "O");
            dato = dato.replaceAll("Ú", "U");

            query.setParameter("categoria", categoria);
            query.setParameter("valor", descripcion.toUpperCase().trim());
            return (Lista)query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public ActividadCIIU getActividadCiiu(Long codigo) throws IdeamException {
        try {
            Query query = em.createNamedQuery("ActividadCIIU.find");
            query.setParameter("codigo", codigo);
            return (ActividadCIIU)query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List getCiiuByName(String nombre) throws IdeamException {
        Query query = em.createNamedQuery("ActividadCIIU.findByName");
        String nombreBuscar = "%" + nombre.toUpperCase() + "%";
        query.setParameter("descripcion", nombreBuscar);
        return query.getResultList();
    }

    public List getCiiuByCode(String codigo) throws IdeamException {
        Query query = em.createNamedQuery("ActividadCIIU.findByCode");
        query.setParameter("codigo", codigo);
        return query.getResultList();
    }

    public List<Divipola> getAllDivipola() throws IdeamException {
        Query query = null;
        try {
            query = em.createNamedQuery("Divipola.findAll");
            return query.getResultList();
        } catch (NoResultException e) {
            return new ArrayList<Divipola>();
        }
    }

    public Divipola getDivipolaById(Long codigo) throws IdeamException {
        if (cacheDivipola.containsKey(codigo)) {
            return cacheDivipola.get(codigo);
        } else {
            try {
                Query query = em.createNamedQuery("Divipola.find");
                query.setParameter("codigo", codigo);
                Divipola pola = (Divipola)query.getSingleResult();
                cacheDivipola.put(codigo, pola);
                return pola;
            } catch (NoResultException e) {
                return null;
            }
        }
    }

    public Lista getLista(Integer codigo) throws IdeamException {
        if (cacheListas.containsKey(codigo)) {
            return cacheListas.get(codigo);
        } else {
            try {
                Query query = em.createNamedQuery("Lista.find");
                query.setParameter("codigo", codigo);
                Lista retorno = (Lista)query.getSingleResult();
                cacheListas.put(codigo, retorno);
                return retorno;
            } catch (NoResultException e) {
                throw new IdeamException("No existe lista con codigo " +
                                         codigo);
            }
        }
    }

    public Categoria getCategoria(Long codigo) throws IdeamException {
        try {
            Query query = em.createNamedQuery("Categoria.find");
            query.setParameter("codigo", codigo);
            return (Categoria)query.getSingleResult();
        } catch (NoResultException e) {
            throw new IdeamException("No existe la lista con codigo " +
                                     codigo);
        }
    }

    public List<Divipola> getDivipola(Long codigo,
                                      Autoridades autoridad) throws IdeamException {
        if (autoridad == null || autoridad.getId() == null) {
            return new ArrayList();
        }
        if (codigo == null) {
            String sql =
                "select * " + " from   part_divipola p" + " where  p.divipola_id in (select distinct divipola_padre_id " +
                "                           from sirh_autoridad_municipio_v " +
                "                           where autoridad_id = ?1)" +
                " order by nombre ASC";
            Query query = em.createNativeQuery(sql, Divipola.class);
            query.setParameter(1, autoridad.getId());
            return query.getResultList();
        } else {
            String sql =
                "select * " + " from   part_divipola p" + " where  p.divipola_id in (select distinct divipola_id " +
                "                           from sirh_autoridad_municipio_v " +
                "                           where autoridad_id = ?1" +
                "                             and divipola_padre_id = ?2 )" +
                " order by nombre ASC ";
            Query query = em.createNativeQuery(sql, Divipola.class);
            query.setParameter(1, autoridad.getId());
            query.setParameter(2, codigo);
            return query.getResultList();
        }
    }

    public List<Divipola> getDivipola(Long codigo) throws IdeamException {
        Query query = null;
        try {
            if (codigo != null) {
                query = em.createNamedQuery("Divipola.findChild");
                query.setParameter("padre", codigo);
            } else {
                query = em.createNamedQuery("Divipola.findAllParents");
            }
            return query.getResultList();
        } catch (NoResultException e) {
            return new ArrayList<Divipola>();
        }
    }

    public TipoParametro getTipoParametro(Long codigo) throws IdeamException {
        if (cacheParametros.containsKey(codigo)) {
            return cacheParametros.get(codigo);
        }
        try {
            System.out.println(":::: Load Parameter from database ::::");
            Query query = em.createNamedQuery("TipoParametro.find");
            query.setParameter("codigo", codigo);
            TipoParametro tipoParametro =
                (TipoParametro)query.getSingleResult();
            cacheParametros.put(codigo, tipoParametro);
            return tipoParametro;
        } catch (NoResultException e) {
            return null;
        }
    }

    public Autoridades getAutoridad(Integer codigo) throws IdeamException {
        if (cacheAutoridades.containsKey(codigo)) {
            return cacheAutoridades.get(codigo);
        } else {
            try {
                Query query = em.createNamedQuery("Autoridades.find");
                query.setParameter("codigo", codigo);
                Autoridades retorno = (Autoridades)query.getSingleResult();
                cacheAutoridades.put(codigo, retorno);
                return retorno;
            } catch (NoResultException e) {
                return null;
            }
        }
    }

    public Autoridades getAutoridadSigla(String sigla) throws IdeamException {
        
            try {
                Query query = em.createNamedQuery("Autoridades.findSIGLA");
                query.setParameter("sigla", sigla);
                Autoridades retorno = (Autoridades)query.getSingleResult();
                return retorno;
            } catch (NoResultException e) {
                return null;
            
        }
    }



    public List getAllAutoridades() throws IdeamException {
        Query query = em.createNamedQuery("Autoridades.findAll");
        return query.getResultList();
    }

    public List getAllAutoridadesNodos() throws IdeamException {
        Query query = em.createNamedQuery("AutoridadesNodos.find");
        return query.getResultList();
    }

    public List<PartZonificListas> getZonificacion(Integer codigo) throws IdeamException {
        Query query = null;
        try {
            if (codigo != null) {
                query = em.createNamedQuery("PartZonificListas.findAllChild");
                query.setParameter("padre", codigo);
            } else {
                query =
                        em.createNamedQuery("PartZonificListas.findAllParents");
            }
            return query.getResultList();
        } catch (NoResultException e) {
            return new ArrayList<PartZonificListas>();
        }
    }

    public List<PartZonificListas> getZonificacion(Integer codigo,
                                                   Autoridades autoridad) throws IdeamException {
        if (autoridad == null || autoridad.getId() == null) {
            return new ArrayList();
        }

        if (codigo != null) { //hijos: zona o subzona
            String sql =
                "Select * " + " From part_zonific_listas " + " Where id IN( " +
                "     Select id_zonificacion From part_zonific_divipolamun " +
                "     Where id_divipola IN ( " +
                "         Select id_municipio_dane " +
                "         From sirh_autoridad_municipio " +
                "         Where id_autoridad = ?1 " +
                "         Group By id_municipio_dane " +
                "     ) Group By id_zonificacion " + " )" +
                " And id_padre = ?2 ";

            Query query = em.createNativeQuery(sql, PartZonificListas.class);
            query.setParameter(1, autoridad.getId());
            query.setParameter(2, codigo);
            return query.getResultList();

        } else { //padres: area
            String sql =
                "Select * " + " From part_zonific_listas " + " Where id IN( " +
                "     Select id_zonificacion From part_zonific_divipolamun " +
                "     Where id_divipola IN ( " +
                "         Select id_municipio_dane " +
                "         From sirh_autoridad_municipio " +
                "         Where id_autoridad = ?1 " +
                "         Group By id_municipio_dane " +
                "     ) Group By id_zonificacion " + " ) " +
                " and id_padre is null ";
            Query query = em.createNativeQuery(sql, PartZonificListas.class);
            query.setParameter(1, autoridad.getId());
            return query.getResultList();
        }
    }

    public List<PartZonificListas> getAllZonificacion() throws IdeamException {
        Query query = null;
        try {
            query = em.createNamedQuery("PartZonificListas.findAll");
            return query.getResultList();
        } catch (NoResultException e) {
            return new ArrayList<PartZonificListas>();
        }
    }

    public List<PartZonificListas> getAllZonificacionByCategoria(Integer categoria) throws IdeamException {
        Query query = null;
        try {

            query =
                    em.createNamedQuery("PartZonificListas.findAllByCategoria");
            query.setParameter("categoria", categoria);

            return query.getResultList();
        } catch (NoResultException e) {
            return new ArrayList<PartZonificListas>();
        }
    }

    public PartZonificListas getZonificacionById(Integer id) throws IdeamException {
        Query query = null;
        try {

            query = em.createNamedQuery("PartZonificListas.findById");
            query.setParameter("id", id);

            return (PartZonificListas)query.getSingleResult();
        } catch (NoResultException e) {
            return new PartZonificListas();
        }
    }

    public List<Lista> getAllListaByCategoria(Long categoria) throws IdeamException {
        Query query = null;
        try {

            query = em.createNamedQuery("Lista.findByCategoria");
            query.setParameter("categoria", categoria);

            return query.getResultList();
        } catch (NoResultException e) {
            return new ArrayList<Lista>();
        }
    }

    public List<Lista> getAllLista() throws IdeamException {
        Query query = null;
        try {
            query = em.createNamedQuery("Lista.findAll");

            return query.getResultList();
        } catch (NoResultException e) {
            return new ArrayList<Lista>();
        }
    }


    public List<OfertaSubzonas> getAllOfertaEnaBySubzona(Integer codigoSubzona) throws IdeamException {
        Query query = null;
        try {
            query = em.createNamedQuery("OfertaSubzonas.findByIdSubzona");
            query.setParameter("idSubzona", codigoSubzona);

            return query.getResultList();
        } catch (NoResultException e) {
            return new ArrayList<OfertaSubzonas>();
        }
    }

    public List<OfertaEstacSubzonas> getAllEstacionesBySubzona(Integer codigoSubzona,
                                                               Integer codigoEstacion) throws IdeamException {
        Query query = null;
        try {
            if (codigoEstacion != null) {
                query =
                        em.createNamedQuery("OfertaEstacSubzonas.findByIdEstacion");
                query.setParameter("idEstacion", codigoEstacion);

            } else {
                query =
                        em.createNamedQuery("OfertaEstacSubzonas.findByIdSubzona");
                query.setParameter("idSubzona", codigoSubzona);
            }
            return query.getResultList();
        } catch (NoResultException e) {
            return new ArrayList<OfertaEstacSubzonas>();
        }
    }

    public Parametro getParametro(Long id) throws IdeamException {
        Query query = null;
        try {

            query = em.createNamedQuery("Parametro.findById");
            query.setParameter("codigo", id);

            return (Parametro)query.getSingleResult();
        } catch (NoResultException e) {
            return new Parametro();
        }
    }


public List<Lista> getTipoFuentes(Long codigo) throws IdeamException {
    return this.getAllListaByCategoria(codigo);
  }

}
