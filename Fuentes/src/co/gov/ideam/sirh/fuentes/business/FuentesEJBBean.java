package co.gov.ideam.sirh.fuentes.business;

import co.gov.ideam.sirh.fuentes.model.ActorConflicto;
import co.gov.ideam.sirh.fuentes.model.CriteriosBusquedaTO;
import co.gov.ideam.sirh.fuentes.model.DatosConflictoTO;
import co.gov.ideam.sirh.fuentes.model.DatosTablaAlertDisp;
import co.gov.ideam.sirh.fuentes.model.DatosTablaAlertDispDet;
import co.gov.ideam.sirh.fuentes.model.DatosTablaAlertIca;
import co.gov.ideam.sirh.fuentes.model.DatosTablaAlertIcaDet;
import co.gov.ideam.sirh.fuentes.model.DerechoConflicto;
import co.gov.ideam.sirh.fuentes.model.FnttActividadesEconomicas;
import co.gov.ideam.sirh.fuentes.model.FnttActores;
import co.gov.ideam.sirh.fuentes.model.FnttConflicto;
import co.gov.ideam.sirh.fuentes.model.FnttBuenasPracticas;
import co.gov.ideam.sirh.fuentes.model.FuenteTO;
import co.gov.ideam.sirh.fuentes.model.FnttDetalleFuentesV;

import co.gov.ideam.sirh.fuentes.model.FnttAutoridadFuentePK;
import co.gov.ideam.sirh.fuentes.model.FnttCategorias;
import co.gov.ideam.sirh.fuentes.model.FnttCostos;
import co.gov.ideam.sirh.fuentes.model.FnttFuente;

import co.gov.ideam.sirh.fuentes.model.FnttFuenteTramoMunicipio;
import co.gov.ideam.sirh.fuentes.model.FnttFuenteTramoMunicipioPK;
import co.gov.ideam.sirh.fuentes.model.FnttLogros;
import co.gov.ideam.sirh.fuentes.model.FnttMotivaciones;
import co.gov.ideam.sirh.fuentes.model.FnttPrincipios;
import co.gov.ideam.sirh.fuentes.model.FnttProyectosEducativos;
import co.gov.ideam.sirh.fuentes.model.FnttTramo;
import co.gov.ideam.sirh.fuentes.model.FnttTrmAforo;
import co.gov.ideam.sirh.fuentes.model.FnttTramoAfluente;
import co.gov.ideam.sirh.fuentes.model.FnttTramoRiesgos;
import co.gov.ideam.sirh.fuentes.model.FuentePOJO;

import co.gov.ideam.sirh.fuentes.model.FuenteTramoMunicipioPOJO;
import co.gov.ideam.sirh.fuentes.model.GestionConflicto;
import co.gov.ideam.sirh.fuentes.model.OrigenConflicto;
import co.gov.ideam.sirh.fuentes.model.PoblacionConflicto;
import co.gov.ideam.sirh.fuentes.model.TipoConflicto;
import co.gov.ideam.sirh.fuentes.model.TramoPOJO;
import co.gov.ideam.sirh.parametros.business.ParametrosEJB;


import co.gov.ideam.sirh.parametros.model.Autoridades;

import co.gov.ideam.sirh.parametros.model.Divipola;
import co.gov.ideam.sirh.parametros.model.Lista;
import co.gov.ideam.sirh.parametros.model.PartZonificListas;
import co.gov.ideam.sirh.remoto.business.RegistroRemotoLocal;

import co.gov.ideam.sirh.usuarios.agua.model.DatosReporteTO;
import co.gov.ideam.sirh.usuarios.agua.model.GeneradorSeq;
import co.gov.ideam.sirh.util.Constantes;
import co.gov.ideam.sirh.util.ConstantesParametros;
import co.gov.ideam.sirh.util.EstadisticasQry;
import co.gov.ideam.sirh.util.IdeamException;


import java.math.BigDecimal;

import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.EJBTransactionRolledbackException;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import javax.ejb.TransactionAttribute;

import javax.ejb.TransactionAttributeType;


import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.From;


@Stateless(name = "FuentesEJB", mappedName = "Sirh-FuenteEJB")
@Remote
public class FuentesEJBBean implements FuentesEJB {

    @PersistenceContext(unitName = "SirhPU")
    private EntityManager em;
    @EJB
    private RegistroRemotoLocal registroRemoto;
    @EJB
    private ParametrosEJB parametrosService;

    //TODO: Mover a una clase utilitaria
    private static final String BP_X_DEPTO =
                                    "SELECT COUNT(bp.ID_DEPARTAMENTO) cantidad ,\n" +
                                    "  dpt.nombre descripcion\n" +
                                    "FROM FNTT_BUENAS_PRACTICAS bp\n" +
                                    "JOIN PART_DIVIPOLA dpt\n" +
                                    "ON (bp.ID_DEPARTAMENTO = dpt.DIVIPOLA_ID)\n" +
                                    "GROUP BY bp.ID_DEPARTAMENTO,\n" +
                                    "  dpt.nombre\n" +
                                    "ORDER BY COUNT(bp.ID_DEPARTAMENTO) DESC";
    
    private static final String BP_X_ACTOR = 
                                    "SELECT COUNT(bp.IDACTOR) cantidad ,\n" + 
                                    "  dpt.actor descripcion\n" + 
                                    "FROM FNTT_BUENAS_PRACTICAS bp\n" + 
                                    "JOIN FNTT_ACTORES dpt\n" + 
                                    "ON (bp.IDACTOR= dpt.IDACTOR)\n" + 
                                    "GROUP BY bp.IDACTOR,\n" + 
                                    "  dpt.actor\n" + 
                                    "ORDER BY COUNT(bp.IDACTOR) DESC";
    
    private static final String FREC_PRINCIPIO = 
                                    "SELECT COUNT(bp.IDPRINCIPIO) cantidad ,\n" + 
                                    "  dpt.PRINCIPIO descripcion\n" + 
                                    "FROM FNTT_PRINCIPIOS_PRACTICAS bp\n" + 
                                    "JOIN FNTT_PRINCIPIOS dpt\n" + 
                                    "ON (bp.IDPRINCIPIO= dpt.IDPRINCIPIO)\n" + 
                                    "GROUP BY bp.IDPRINCIPIO,\n" + 
                                    "  dpt.PRINCIPIO\n" + 
                                    "ORDER BY COUNT(bp.IDPRINCIPIO) DESC";
    
    private static final String BP_X_COSTO = 
                                    "SELECT COUNT(bp.IDCOSTO) cantidad ,\n" + 
                                    "  dpt.COSTO descripcion\n" + 
                                    "FROM FNTT_BUENAS_PRACTICAS bp\n" + 
                                    "JOIN FNTT_COSTOS dpt\n" + 
                                    "ON (bp.IDCOSTO= dpt.IDCOSTO)\n" + 
                                    "GROUP BY bp.IDCOSTO,\n" + 
                                    "  dpt.COSTO\n" + 
                                    "ORDER BY COUNT(bp.IDCOSTO) DESC";
    
    private static final String FREC_LOGRO = 
                                    "SELECT COUNT(bp.IDLOGRO) cantidad ,\n" + 
                                    "  dpt.LOGRO descripcion\n" + 
                                    "FROM FNTT_LOGROS_PRACTICAS bp\n" + 
                                    "JOIN FNTT_LOGROS dpt\n" + 
                                    "ON (bp.IDLOGRO= dpt.IDLOGRO)\n" + 
                                    "GROUP BY bp.IDLOGRO,\n" + 
                                    "  dpt.LOGRO\n" + 
                                    "ORDER BY COUNT(bp.IDLOGRO) DESC";
    
    private static final String BP_X_PROYECTO = 
                                    "SELECT COUNT(bp.IDPROYECTO) cantidad ,\n" + 
                                    "  dpt.DESCRIPCION descripcion\n" + 
                                    "FROM FNTT_BUENAS_PRACTICAS bp\n" + 
                                    "JOIN FNTT_PROYECTOS_EDUCATIVOS dpt\n" + 
                                    "ON (bp.IDPROYECTO= dpt.IDPROYECTO)\n" + 
                                    "GROUP BY bp.IDPROYECTO,\n" + 
                                    "  dpt.DESCRIPCION\n" + 
                                    "ORDER BY COUNT(bp.IDPROYECTO) DESC";
    
    private static final String BP_X_CATEG = 
                                    "SELECT COUNT(bp.IDCATEGORIA) cantidad ,\n" + 
                                    "  dpt.CATEGORIA descripcion\n" + 
                                    "FROM FNTT_BUENAS_PRACTICAS bp\n" + 
                                    "JOIN FNTT_CATEGORIAS dpt\n" + 
                                    "ON (bp.IDCATEGORIA= dpt.IDCATEGORIA)\n" + 
                                    "GROUP BY bp.IDCATEGORIA,\n" + 
                                    "  dpt.CATEGORIA\n" + 
                                    "ORDER BY COUNT(bp.IDCATEGORIA) DESC";
    
    private static final String FREC_MOTIVACION = 
                                    "SELECT COUNT(bp.IDMOTIVACION) cantidad ,\n" + 
                                    "  dpt.MOTIVACION descripcion\n" + 
                                    "FROM FNTT_MOTIVACIONES_PRACTICAS bp\n" + 
                                    "JOIN FNTT_MOTIVACIONES dpt\n" + 
                                    "ON (bp.IDMOTIVACION= dpt.IDMOTIVACION)\n" + 
                                    "GROUP BY bp.IDMOTIVACION,\n" + 
                                    "  dpt.MOTIVACION\n" + 
                                    "ORDER BY COUNT(bp.IDMOTIVACION) DESC";
    
    private static final String CF_X_DEPARTAMENTO =
                                    "SELECT COUNT(cf.ID_DEPARTAMENTO) cantidad ,\n" + 
                                    "  dpt.nombre descripcion\n" + 
                                    "FROM FNTT_CONFLICTO cf\n" + 
                                    "JOIN PART_DIVIPOLA dpt\n" + 
                                    "ON (cf.ID_DEPARTAMENTO = dpt.DIVIPOLA_ID)\n" + 
                                    "GROUP BY cf.ID_DEPARTAMENTO,\n" + 
                                    "  dpt.nombre\n" + 
                                    "ORDER BY COUNT(cf.ID_DEPARTAMENTO) DESC";
    
    private static final String FREC_TIPO_CONF = 
                                    "SELECT COUNT(t1.ID_TIPO_CONFLICTO) cantidad ,\n" + 
                                    "  t2.VALOR descripcion\n" + 
                                    "FROM FNTT_CONFLICTO_TIPO t1\n" + 
                                    "JOIN PART_LISTAS t2\n" + 
                                    "ON (t1.ID_TIPO_CONFLICTO = t2.id and t2.ID_CATEGORIA = 80)\n" + 
                                    "GROUP BY t1.ID_TIPO_CONFLICTO,\n" + 
                                    "  t2.VALOR\n" + 
                                    "ORDER BY COUNT(t1.ID_TIPO_CONFLICTO) DESC";
    
    private static final String CF_X_POBL_AFECT =
                                    "SELECT COUNT(t1.ID_POBLACION_AFECTADA) cantidad ,\n" + 
                                    "  t2.VALOR descripcion\n" + 
                                    "FROM FNTT_CONFLICTO_POBLACION t1\n" + 
                                    "JOIN PART_LISTAS t2\n" + 
                                    "ON (t1.ID_POBLACION_AFECTADA = t2.id and t2.ID_CATEGORIA = 83)\n" + 
                                    "GROUP BY t1.ID_POBLACION_AFECTADA,\n" + 
                                    "  t2.VALOR\n" + 
                                    "ORDER BY COUNT(t1.ID_POBLACION_AFECTADA) DESC";
    
    private static final String CF_X_ACTORES =
                                    "SELECT COUNT(t1.ACTOR_CONFLICTO) cantidad ,\n" + 
                                    "  t2.VALOR descripcion\n" + 
                                    "FROM FNTT_CONFLICTO_ACTOR t1\n" + 
                                    "JOIN PART_LISTAS t2\n" + 
                                    "ON (t1.ACTOR_CONFLICTO = t2.id and t2.ID_CATEGORIA = 82)\n" + 
                                    "GROUP BY t1.ACTOR_CONFLICTO,\n" + 
                                    "  t2.VALOR\n" + 
                                    "ORDER BY COUNT(t1.ACTOR_CONFLICTO) DESC";
    
    private static final String CF_X_GESTION =
                                    "SELECT COUNT(t1.ID_TIPO_GESTION) cantidad ,\n" + 
                                    "  t2.VALOR descripcion\n" + 
                                    "FROM FNTT_CONFLICTO_GESTION t1\n" + 
                                    "JOIN PART_LISTAS t2\n" + 
                                    "ON (t1.ID_TIPO_GESTION = t2.id and t2.ID_CATEGORIA = 85)\n" + 
                                    "GROUP BY t1.ID_TIPO_GESTION,\n" + 
                                    "  t2.VALOR\n" + 
                                    "ORDER BY COUNT(t1.ID_TIPO_GESTION) DESC";
    
    private static final String CF_X_SUB_GESTION =
                                    "SELECT COUNT(t1.ID_SUBTIPO_GESTION) cantidad ,\n" + 
                                    "  t2.VALOR descripcion\n" + 
                                    "FROM FNTT_CONFLICTO_GESTION t1\n" + 
                                    "JOIN PART_LISTAS t2\n" + 
                                    "ON (t1.ID_SUBTIPO_GESTION = t2.id and t2.ID_CATEGORIA IN (86,87,88,89))\n" + 
                                    "GROUP BY t1.ID_SUBTIPO_GESTION,\n" + 
                                    "  t2.VALOR\n" + 
                                    "ORDER BY COUNT(t1.ID_SUBTIPO_GESTION) DESC";
    
    private static final String CF_X_CUENCA =
                                    "SELECT COUNT(cf.ID_AREA) cantidad ,\n" + 
                                    "  dpt.VALOR descripcion\n" + 
                                    "FROM sirh_fuente_tramo_v_ cf\n" + 
                                    "JOIN PART_ZONIFIC_LISTAS dpt\n" + 
                                    "ON (cf.ID_AREA = dpt.ID AND dpt.ID_PADRE IS NULL)\n" + 
                                    "GROUP BY cf.ID_AREA,\n" + 
                                    "  dpt.VALOR\n" + 
                                    "ORDER BY COUNT(cf.ID_AREA) DESC";

    private static final String CF_X_SUBZONA =
                                    "SELECT COUNT(cf.ID_SUBZONA) cantidad ,\n" + 
                                    "  dpt.VALOR descripcion\n" + 
                                    "FROM sirh_fuente_tramo_v_ cf\n" + 
                                    "JOIN PART_ZONIFIC_LISTAS dpt\n" + 
                                    "ON (cf.ID_SUBZONA = dpt.ID)\n" + 
                                    "GROUP BY cf.ID_SUBZONA,\n" + 
                                    "  dpt.VALOR\n" + 
                                    "ORDER BY COUNT(cf.ID_SUBZONA) DESC";
    
    public FuentesEJBBean() {
    }

    public List<FnttFuente> getAllFuentes() throws IdeamException {
        try {

            Query query = em.createNamedQuery("FnttFuente.findAll");
            List lista = query.getResultList();
            if (lista != null) {
                Iterator it = lista.iterator();
                while (it.hasNext()) {
                    FnttFuente ft = (FnttFuente)it.next();
                    this.loadAttributesFuentes(ft);

                }
            }
            return lista;


        } catch (NoResultException e) {
            return null;
        }
    }

    public List<FnttFuente> getAllFuentes(Autoridades autoridad) throws IdeamException {
        try {
            if (autoridad == null || autoridad.getId() == null) {
                return new ArrayList();
            } else {
                Query query =
                    em.createNamedQuery("FnttFuente.findByAutoridad");
                query.setParameter("id", autoridad.getId());

                List lista = query.getResultList();
                if (lista != null) {
                    Iterator it = lista.iterator();
                    while (it.hasNext()) {
                        FnttFuente ft = (FnttFuente)it.next();
                        this.loadAttributesFuentes(ft);

                    }
                }
                return lista;

            }

        } catch (NoResultException e) {
            return null;
        }
    }


    /**
     * Carga los atributos @Transient de la clase PuntosMonitoreo
     * @param PuntosMonitoreo
     * @throws IdeamException
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    private void loadAttributesFuentes(FnttFuente ff) throws IdeamException {
        Integer idTipoFuente = ff.getIdTipoFuente().getCodigo();
        if (idTipoFuente != null) {
            Lista tipoFuente = parametrosService.getLista(idTipoFuente);
            ff.setNombretipoFuente(tipoFuente.getValor());
            ff.setSiglaAutoridad(ff.getCodAutoridad().getSigla());
            if (ff.getIdTipoFuente().getCodigo().longValue() ==
                ConstantesParametros.LISTA_FUENTE_SUBTERRANEA) {
                if (ff.getId_provinciahidro() != null) {


                    Integer idprovincia =
                        ff.getId_provinciahidro().getCodigo();
                    Lista provincia = parametrosService.getLista(idprovincia);
                    ff.setProvinciaH(provincia.getValor());
                } else {
                    ff.setProvinciaH(null);
                }

            }


        }

    }


    /**
     * Metodo empleado por el Nodo IDEAM para recibir fuentes de nodos
     * @param fuente
     * @return
     * @throws IdeamException
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void updateFuentePlano(FuentePOJO fuente) throws IdeamException {
        //if(fuente.getId()==null || fuente.getId().longValue()==0){
        System.out.println("--------------------------------------entra por el IF de updateFuentePlano:" +
                           fuente.getId());
        em.merge(fuente);
        //}else{
        //  fuente = em.merge(fuente);
        //}
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void persistFuentePlano(FuentePOJO fuente) throws IdeamException {
        try {
            System.out.println("--------------------------------------entra por el IF de persistFuentePlano:" +
                               fuente.getId());
            em.persist(fuente);

        } catch (Exception ei) {
            System.out.println("------:" + ei);
        }
    }

    /**
     *Metodo empleado por el nodo cliente para guar fuente
     * @param fuente
     * @return
     * @throws IdeamException
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public FnttFuente updateFuente(FnttFuente fuente) throws IdeamException {
        System.out.println("XXXXXXXXXXXXXXXXX---AUTORIDAD para fuente: " +
                           fuente.getCodigoAutoridad());
        if (fuente == null) {
            throw new IdeamException("Entidad fuente no puede ser null");
        }
        boolean persiste = false;
        if (fuente.getId() == null || fuente.getId().longValue() == 0) {
            fuente.setId(GeneradorSeq.obtenerSequencia(fuente.getCodigoAutoridad(),
                                                       "SEQ_FUENTES", em));
            em.persist(fuente);
            persiste = true;
        } else {
            fuente = em.merge(fuente);
        }

        //generar el objeto plano a transmitir
        try {
            FuentePOJO fnt = new FuentePOJO();
            fnt.setCodAutoridad(fuente.getCodigoAutoridad());
            fnt.setCodigoCuencaAA(fuente.getCodigoCuencaAA());
            fnt.setDescripcion(fuente.getDescripcion());
            fnt.setEsCompartida(fuente.getEsCompartida());
            fnt.setId(fuente.getId());
            fnt.setIdTipoFuente(fuente.getIdTipoFuente().getCodigo());
            fnt.setNombre(fuente.getNombre());
            fnt.setCodigoAutoridad(fuente.getCodigoAutoridad());
            //envío por cola
            if (persiste) {
                registroRemoto.registrarEvento(this.getClass().getName(),
                                               "persistFuentePlano", fnt);


                // cuando la fuente es subterranea debe crear auomaticamente el tramo.
                if (fuente.getIdTipoFuente().getCodigo().longValue() ==
                    ConstantesParametros.LISTA_FUENTE_SUBTERRANEA) {
                    try {
                        Thread.sleep(3000);

                        FnttTramo tramo = new FnttTramo();
                        tramo.setNombre(fuente.getNombre());
                        tramo.setDescripcion(null);
                        tramo.setLongitud(new BigDecimal(0L));
                        tramo.setOfertaHidricaTotal(new BigDecimal(0L));
                        tramo.setTipoFlujo(null);
                        tramo.setSistemaReferencia(null);
                        tramo.setIdArea(null);
                        tramo.setIdZona(null);
                        tramo.setIdSubzona(null);
                        tramo.setGradosPi(0);
                        tramo.setMinutosPi(0);
                        tramo.setSegundosPi(new BigDecimal(0L));
                        tramo.setAltitudPi(new BigDecimal(0L));
                        tramo.setGradosLongPi(0);
                        tramo.setMinutosLongPi(0);
                        tramo.setSegundosLongPi(new BigDecimal(0L));
                        tramo.setGradosPf(0);
                        tramo.setMinutosPf(0);
                        tramo.setSegundosPf(new BigDecimal(0L));
                        tramo.setAltitudPf(new BigDecimal(0L));
                        tramo.setGradosLongPf(0);
                        tramo.setMinutosLongPf(0);
                        tramo.setSegundosLongPf(new BigDecimal(0L));
                        tramo.setCodigoAutoridad(fuente.getCodAutoridad().getId().longValue());
                        tramo.setIdFuente(fuente);
                        this.updateTramo(tramo);
                    } catch (Exception e) {
                        System.out.println("ERROR ALMACENANDO TRAMO DE FUENTE SUBTERRANEA");
                    }
                }

            } else {
                registroRemoto.registrarEvento(this.getClass().getName(),
                                               "updateFuentePlano", fnt);
            }
        } catch (Exception e) {
            System.out.println("XXXXXXXXXXXXXXXXX---ERROR EN LA TRANSMISIÓN UPDATE FUENTE: " +
                               e);
        }


        //continua el proceso
        return fuente;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void deleteFuentePlano(FuentePOJO fuente) throws IdeamException {
        try {
            em.flush();
            fuente = em.merge(fuente);
            List tramos = this.getAllTramosByFuente(fuente.getId());
            if (tramos != null && tramos.size() > 0) {
                throw new IdeamException("Problemas! \\nla fuente tiene tramos asociados, \\nno es posible borrarla.\"");
            }
            em.remove(fuente);
        } catch (Exception e) {
            System.out.println("XXXXXXXXXXXXXXXXX---ERROR EN BORRAR FUENTE: " +
                               e.getMessage());
        } finally {
        }
    }

    public void deleteFuente(FnttFuente fuente) throws IdeamException {
        try {
            em.flush();
            fuente = em.merge(fuente);
            if (fuente.getFnttTramoList() != null &&
                fuente.getFnttTramoList().size() > 0) {
                throw new IdeamException("Problemas! \\nla fuente tiene tramos asociados, \\nno es posible borrarla.\"");
            }
            em.remove(fuente);
        } catch (Exception e) {
            System.out.println("XXXXXXXXXXXXXXXXX---ERROR AL BORRAR FUENTE: " +
                               e.getMessage());
        } finally {
        }
        try {
            FuentePOJO fnt = new FuentePOJO();
            fnt.setCodAutoridad(fuente.getCodigoAutoridad());
            fnt.setCodigoCuencaAA(fuente.getCodigoCuencaAA());
            fnt.setDescripcion(fuente.getDescripcion());
            fnt.setEsCompartida(fuente.getEsCompartida());
            fnt.setId(fuente.getId());
            fnt.setIdTipoFuente(fuente.getIdTipoFuente().getCodigo());
            fnt.setNombre(fuente.getNombre());
            fnt.setCodigoAutoridad(fuente.getCodigoAutoridad());
            //envío por cola
            registroRemoto.registrarEvento(this.getClass().getName(),
                                           "deleteFuentePlano", fnt);
        } catch (Exception e) {
            System.out.println("XXXXXXXXXXXXXXXXX---ERROR EN LA TRANSMISIÓN BORRAR FUENTE: " +
                               e.getMessage());
        }
    }

    public FnttFuente getFuente(Long id) throws IdeamException {
        FnttFuente result = null;
        try {
            Query query = em.createNamedQuery("FnttFuente.findById");
            query.setParameter("id", id);
            result = (FnttFuente)query.getSingleResult();
        } catch (javax.ejb.EJBTransactionRolledbackException e) {
            throw new IdeamException("Problemas! \\nno existe una fuente con los datos de búsqueda.");
        } catch (Exception e) {
            throw new IdeamException("Problemas! \\nno existe una fuente con los datos de búsqueda.");
        } finally {
            return result;
        }
    }

    public FnttFuente getFuente(String nombre,
                                Autoridades autoridad) throws IdeamException {
        FnttFuente result = null;
        try {
            Query query =
                em.createNamedQuery("FnttFuente.findByNombreAndAutoridad");
            query.setParameter("nombre", nombre.toUpperCase());
            query.setParameter("idAutoridad", autoridad.getId());
            result = (FnttFuente)query.getSingleResult();
        } catch (javax.ejb.EJBTransactionRolledbackException e) {
            throw new IdeamException("Problemas! \\nno existe una fuente con los datos de búsqueda.");
        } catch (Exception e) {
            throw new IdeamException("Problemas! \\nno existe una fuente con los datos de búsqueda.");
        } finally {
            return result;
        }
    }

    public FnttFuente getFuente(String nombre, Autoridades autoridad,
                                Integer idTipoFuente) throws IdeamException {
        FnttFuente result = null;
        try {
            System.out.println("VALIDANDO FUENTE nombre: " +
                               nombre.toUpperCase() + "  autoridad.getId():" +
                               autoridad.getId() + " idTipo:" +
                               idTipoFuente.intValue());
            Query query =
                em.createNamedQuery("FnttFuente.findByNombreTipoAutoridad");
            query.setParameter("nombre", nombre.toUpperCase());
            query.setParameter("idAutoridad", autoridad.getId());
            query.setParameter("idTipo", idTipoFuente.intValue());
            result = (FnttFuente)query.getSingleResult();
            System.out.println("ENCUENTRA FUENTE:" + result.getId());
        } catch (javax.ejb.EJBTransactionRolledbackException e) {
            throw new IdeamException("Problemas! \\nno existe una fuente con los datos de búsqueda.");
        } catch (Exception e) {
            throw new IdeamException("Problemas! \\nno existe una fuente con los datos de búsqueda.");
        } finally {
            return result;
        }
    }

    public List getFuente(Autoridades autoridad,
                          Integer idTipoFuente) throws IdeamException {
        Query query = em.createNamedQuery("FnttFuente.findByTipoAutoridad");
        query.setParameter("idAutoridad", autoridad.getId());
        query.setParameter("idTipo", idTipoFuente.intValue());
        return query.getResultList();
    }

    public List<FnttTramo> getAllTramosByFuente(FnttFuente fuente) throws IdeamException {
        Query query = em.createNamedQuery("FnttTramo.findByIdFuente");
        query.setParameter("id", fuente.getId());
        return query.getResultList();
    }
    
  public List<FnttTramoAfluente> getAllAfluentesByTramo(FnttTramo tramo)throws IdeamException{
      Query query = em.createNamedQuery("FnttTramoAfluente.findAllByTramo");
      query.setParameter("idTramo",tramo.getId());
      return query.getResultList();
  }

    public List<FnttTramo> getAllTramosByFuente(long idFuente) throws IdeamException {
        Query query = em.createNamedQuery("FnttTramo.findByIdFuente");
        query.setParameter("id", idFuente);
        return query.getResultList();
    }

    /**
     * Metodo empleado por el Nodo IDEAM para recibir fuentes de nodos
     * @param tramo
     * @return
     * @throws IdeamException
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void updateTramoPlano(TramoPOJO tramo) throws IdeamException {
        System.out.println("XXXXXXXXXXXXXXXXXXXXXX---UPDATE TRAMO PLANO ");

        List<FnttFuenteTramoMunicipio> municipios =
            this.getAllMunicipiosByTramo(tramo.getId());
        System.out.println("----------Municipios al borrar remoto: " +
                           municipios.size());
        if (municipios != null && municipios.size() > 0) {
            for (FnttFuenteTramoMunicipio mun : municipios) {
                this.deleteMunicipios(mun);
            }
        }

        System.out.println("XXXXXXXXXXXXXXXXXXXXXX---MERGE TRAMO PLANO ");
        FnttTramo trm =
            this.getTramo(tramo.getId()); //se hace esto debido a perdida de objeto en sessión del em.
        System.out.println("XXXXXXXXXXXXXXXXXXXXXX---PASO TRAMO PLANO ");

        if (trm == null) {
            System.out.println("XXXXXXXXXXXXXXXXXXXXXX---TRAMO PERSIS ");
            em.persist(tramo);
        } else {
            System.out.println("XXXXXXXXXXXXXXXXXXXXXX---TRAMO MERGE ");
            trm = em.merge(trm);
        }
        System.out.println("XXXXXXXXXXXXXXXXXXXXXX---TERMINA TRAMO PLANO ");

    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void persisteTramoPlano(TramoPOJO tramo) throws IdeamException {
        System.out.println("XXXXXXXXXXXXXXXXXXXXXX---persisteTramoPlano TRAMO PLANO");
        FnttTramo trm = this.getTramo(tramo.getId());
        if (trm == null) {
            System.out.println("XXXXXXXXXXXXXXXXXXXXXX---persisteTramoPlano PERSIS ");
            em.persist(tramo);
        } else {
            System.out.println("XXXXXXXXXXXXXXXXXXXXXX---persisteTramoPlano MERGE ");
            trm = em.merge(trm);
        }

    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public FnttTramo updateTramo(FnttTramo tramo) throws IdeamException {
        boolean persiste = false;
        System.out.println("XXXXXXXXXXXXXXXXXXX--UPDATE TRAMO");

        if (tramo == null) {
            throw new IdeamException("Entidad tramo no puede ser null");
        }

        List<FnttFuenteTramoMunicipio> municipiosArelacionar =
            tramo.getFnttFuenteTramoMunicipioList(); //almaceno los municipios relacionados en la vista
        tramo.setFnttFuenteTramoMunicipioList(null);

        if (tramo.getId() == null || tramo.getId().longValue() == 0) {
            tramo.setId(GeneradorSeq.obtenerSequencia(tramo.getCodigoAutoridad(),
                                                      "SEQ_TRAMOS", em));
            em.persist(tramo);

            persiste = true;

        } else {
            List<FnttFuenteTramoMunicipio> municipios =
                this.getAllMunicipiosByTramo(tramo);
            System.out.println("----------Municipios desde consulta: " +
                               municipios.size());
            if (municipios != null && municipios.size() > 0) {
                for (FnttFuenteTramoMunicipio mun : municipios) {
                    this.deleteMunicipios(mun);
                }
            }

            System.out.println("XXXXXXX=========MERGE y FLUSH SESSION");
            em.merge(tramo);
            em.flush();

            tramo =
                    this.getTramo(tramo.getId()); //se hace esto debido a perdida de objeto en sessión del em.

        }
        //generar el objeto plano a transmitir
        try {

            System.out.println("XXXXXXXXXXXXXXXXXXX--ARMA OBJETO PLANO ");
            TramoPOJO tm = new TramoPOJO();
            tm.setNombre(tramo.getNombre());
            ;
            tm.setId(tramo.getId());
            tm.setDescripcion(tramo.getDescripcion());
            tm.setLongitud(tramo.getLongitud());
            tm.setOfertaHidricaTotal(tramo.getOfertaHidricaTotal());
            tm.setId_fuente(tramo.getIdFuente().getId());
            tm.setId_area((tramo.getIdArea() != null) ?
                          tramo.getIdArea().getId() : null);
            tm.setId_zona((tramo.getIdZona() != null) ?
                          tramo.getIdZona().getId() : null);
            tm.setId_subzona((tramo.getIdSubzona() != null) ?
                             tramo.getIdSubzona().getId() : null);
            tm.setSistema_referencia((tramo.getSistemaReferencia() != null) ?
                                     tramo.getSistemaReferencia().getCodigo() :
                                     null);
            tm.setTipo_flujo((tramo.getIdTipoFlujo() != null) ?
                             tramo.getIdTipoFlujo() : null);
            tm.setAltitudPi(tramo.getAltitudPi());
            tm.setAltitudPf(tramo.getAltitudPf());
            tm.setGradosPi(tramo.getGradosPi());
            tm.setMinutosPi(tramo.getMinutosPi());
            tm.setSegundosPi(tramo.getSegundosPi());
            tm.setGradosPf(tramo.getGradosPf());
            tm.setMinutosPf(tramo.getMinutosPf());
            tm.setSegundosPf(tramo.getSegundosPf());
            tm.setGradosLongPi(tramo.getGradosLongPi());
            tm.setSegundosLongPi(tramo.getSegundosLongPi());
            tm.setMinutosLongPi(tramo.getMinutosLongPi());
            tm.setGradosLongPf(tramo.getGradosLongPf());
            tm.setSegundosLongPf(tramo.getSegundosLongPf());
            tm.setMinutosLongPf(tramo.getMinutosLongPf());
            tm.setCodigoAutoridad(tramo.getCodigoAutoridad());


            System.out.println("XXXXXXXXXXXXXXXXXXX--REGISTRA EN LA COLA UPDATE TRAMO");
            //envío por cola
            if (persiste) {
                registroRemoto.registrarEvento(this.getClass().getName(),
                                               "persisteTramoPlano", tm);
                System.out.println("XXXXXXXXXXXXXXXXXXX--persisteTramoPlano ");
            } else {
                registroRemoto.registrarEvento(this.getClass().getName(),
                                               "updateTramoPlano", tm);
                System.out.println("XXXXXXXXXXXXXXXXXXX--persisteTramoPlano ");
            }

            System.out.println("XXXXXXXXXXXXXXXXXXX--TERMINA REGISTRO TRAMO ");

            Thread.sleep(3000L); //espere 3 segundos para asegurar que se transmite el tramo.

            if (municipiosArelacionar != null) {
                System.out.println("XXXXXXXXXXXXXXXXXXX--Hay Municipios: " +
                                   municipiosArelacionar.size());
                for (FnttFuenteTramoMunicipio municipio :
                     municipiosArelacionar) {
                    municipio.setFnttTramo(tramo);
                    municipio.getFntFuenteTramoMunicipioPK().setIdTramo(tramo.getId());
                    this.saveTramoMunicipio(municipio);
                }
            }


        } catch (Exception e) {
            System.out.println("XXXXXXXXXXXXXXXXX---ERROR EN LA TRANSMISIÓN UPDATE PLANO: " +
                               e.getMessage());
        }

        //continua el proceso
        return tramo;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void deleteTramoPlano(TramoPOJO tramo) throws IdeamException {
        try {
            System.out.println("------------TRAMO a borrar en remoto:" +
                               tramo.getId());
            List<FnttFuenteTramoMunicipio> municipios =
                this.getAllMunicipiosByTramo(tramo.getId());
            System.out.println("------------Municipios en remoto:" +
                               municipios.size());
            if (municipios != null && municipios.size() > 0) {
                for (FnttFuenteTramoMunicipio mun : municipios) {
                    this.deleteMunicipios(mun);
                }
            }

            FnttTramo trm =
                this.getTramo(tramo.getId()); //se hace esto debido a perdida de objeto en sessión del em.
            em.remove(trm);
            System.out.println("------------ELIMINA CORRECTAMENTE EL TRAMO");
        } catch (Exception e) {
            System.out.println("------------ERROR AL BORRAR TRAMO SOLICITADO DESDE COLA:" +
                               e);
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void deleteTramo(FnttTramo tramo) throws IdeamException {
        try {
            System.out.println("XXXXXXXXXXXXXXXXXXX--DELETE TRAMO");
            List<FnttFuenteTramoMunicipio> municipios =
                this.getAllMunicipiosByTramo(tramo);

            try {
                System.out.println("----------Municipios a borrar: " +
                                   municipios.size());
                if (municipios != null && municipios.size() > 0) {
                    for (FnttFuenteTramoMunicipio mun : municipios) {
                        this.deleteMunicipios(mun);
                    }
                }
            } catch (Exception e) {
                System.out.println("XXXXXXXXXXXXXXXXX---ERROR AL BORRAR MUNICIPIOS DEL TRAMO: " +
                                   e.getMessage());
            }

            Thread.sleep(2000L); //espere 2 segundos para continuar.


            TramoPOJO tm = new TramoPOJO();
            tm.setId(tramo.getId());
            tm.setNombre(tramo.getNombre());
            tm.setDescripcion(tramo.getDescripcion());
            tm.setLongitud(tramo.getLongitud() != null ? tramo.getLongitud() :
                           null);
            System.out.println("XXXXXXXXXXXXXXXXXXX--ARMA OBJETO TRAMO PLANO ");
            tm.setOfertaHidricaTotal(tramo.getOfertaHidricaTotal() != null ?
                                     tramo.getOfertaHidricaTotal() : null);
            tm.setId_fuente(tramo.getIdFuente().getId());
            tm.setId_area(tramo.getIdArea() != null ?
                          tramo.getIdArea().getId() : null);
            tm.setId_zona(tramo.getIdZona() != null ?
                          tramo.getIdZona().getId() : null);
            tm.setId_subzona(tramo.getIdSubzona() != null ?
                             tramo.getIdSubzona().getId() : null);
            System.out.println("XXXXXXXXXXXXXXXXXXX--ARMA OBJETO TRAMO SUBZON ");
            tm.setTipo_flujo(tramo.getIdTipoFlujo() != null ?
                             tramo.getIdTipoFlujo() : null);
            if (tramo.getSistemaReferencia() != null) {
                tm.setSistema_referencia(tramo.getSistemaReferencia().getCodigo());
                tm.setAltitudPi(tramo.getAltitudPi());
                tm.setAltitudPf(tramo.getAltitudPf());
                tm.setGradosPi(tramo.getGradosPi());
                tm.setMinutosPi(tramo.getMinutosPi());
                tm.setSegundosPi(tramo.getSegundosPi());
                tm.setGradosPf(tramo.getGradosPf());
                tm.setMinutosPf(tramo.getMinutosPf());
                tm.setSegundosPf(tramo.getSegundosPf());
                tm.setGradosLongPi(tramo.getGradosLongPi());
                tm.setSegundosLongPi(tramo.getSegundosLongPi());
                tm.setMinutosLongPi(tramo.getMinutosLongPi());
                tm.setGradosLongPf(tramo.getGradosLongPf());
                tm.setSegundosLongPf(tramo.getSegundosLongPf());
                tm.setMinutosLongPf(tramo.getMinutosLongPf());
            }
            System.out.println("XXXXXXXXXXXXXXXXXXX--ARMA OBJETO TRAMO SEG PI ");
            //tm.setCodigoAutoridad(tramo.getCodigoAutoridad());


            tramo =
                    this.getTramo(tramo.getId()); //se hace esto debido a perdida de objeto en sessión del em.

            em.remove(tramo);

            //envío por cola

            registroRemoto.registrarEvento(this.getClass().getName(),
                                           "deleteTramoPlano", tm);

        } catch (Exception e) {
            System.out.println("XXXXXXXXXXXXXXXXX---ERROR EN LA TRANSMISIÓN DELETE TRAMO: " +
                               e);
        }
    }

    public FnttTramo getTramo(Long id) throws IdeamException {
        FnttTramo tramo = null;
        try {
            Query query = em.createNamedQuery("FnttTramo.findById");
            query.setParameter("id", id);
            tramo = (FnttTramo)query.getSingleResult();
            if (tramo != null)
                loadAttributes(tramo);
        } catch (javax.ejb.EJBTransactionRolledbackException e) {
            throw new IdeamException("Problemas! \\nno existe un tramo con los datos de búsquedad.");
        } catch (Exception e) {
            throw new IdeamException("Problemas! \\nno existe resultado con los datos de búsquedad.");
        } finally {
            return tramo;
        }
    }
    
  public FnttTramoRiesgos getTramoRiesgos(Long id)throws IdeamException{
      FnttTramoRiesgos tramoRiesgos = null;
      try{
          Query query = em.createNamedQuery("FnttTramoRiesgos.findAllByTramo");
          query.setParameter("idTramo", id);
          tramoRiesgos = (FnttTramoRiesgos)query.getSingleResult();
      }catch(javax.ejb.EJBTransactionRolledbackException e){
          throw new IdeamException("Problemas! \\nno existe un tramoRiesgos con los datos de búsquedad.");
      }catch(Exception e){
          throw new IdeamException("Problemas! \\nno existe resultado con los datos de búsquedad.");
      }finally{
          return tramoRiesgos;
      }
  }
  
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    private void loadAttributes(FnttTramo pm) throws IdeamException {
        Integer idTipoFlujo = pm.getIdTipoFlujo();
        if (idTipoFlujo != null) {
            Lista tipoFlujo = parametrosService.getLista(idTipoFlujo);
            pm.setTipoFlujo(tipoFlujo);
        }

    }

    public FnttTramo getTramo(String nombre, FnttFuente fuente,
                              Autoridades autoridad) throws IdeamException {
        FnttTramo result = null;
        try {
            Query query =
                em.createNamedQuery("FnttTramo.findByNombreAndFuenteAndAutoridad");
            query.setParameter("nombre", nombre.toUpperCase().trim());
            query.setParameter("idFuente", fuente.getId());
            query.setParameter("idAutoridad", autoridad.getId());
            result = (FnttTramo)query.getSingleResult();
            loadAttributes(result);
        } catch (javax.ejb.EJBTransactionRolledbackException e) {
            throw new IdeamException("Problemas! \\nno existe un tramo con los datos de búsquedad.");
        } catch (Exception e) {
            throw new IdeamException("Problemas! \\nno existe resultado con los datos de búsquedad.");
        } finally {
            return result;
        }
    }

    public FnttTramo getTramoWithMunicipios(Long id) throws IdeamException,
                                                            NoResultException {
        FnttTramo result = null;
        Query query = em.createNamedQuery("FnttTramo.findByIdWithDivipola");
        query.setParameter("id", id);
        try {
            result = (FnttTramo)query.getSingleResult();
            loadAttributes(result);
        } catch (javax.ejb.EJBTransactionRolledbackException e) {
            throw new IdeamException("Problemas! \\nno existe resultado con los datos de búsquedad.");
        } catch (Exception e) {
            throw new IdeamException("Problemas! \\nno existe resultado con los datos de búsquedad.");
        } finally {
            return result;
        }
    }

    public List getMunicipiosByTramo(Long id) throws IdeamException,
                                                     NoResultException {
        Query query =
            em.createNamedQuery("FnttFuenteTramoMunicipio.findByIdTramo");
        query.setParameter("idTramo", id);
        return query.getResultList();
    }

    public FnttTramo getTramo(String nombreTramo,
                              Long idFuente) throws IdeamException {
        FnttTramo result = null;
        try {
            Query query =
                em.createNamedQuery("FnttTramo.findByNombreAndFuente");
            query.setParameter("nombre", nombreTramo);
            query.setParameter("idFuente", idFuente);

            result = (FnttTramo)query.getSingleResult();
            loadAttributes(result);
        } catch (javax.ejb.EJBTransactionRolledbackException e) {
            throw new IdeamException("Problemas! \\nno existe un tramo con los datos de búsquedad.");
        } catch (Exception e) {
            throw new IdeamException("Problemas! \\nno existe resultado con los datos de búsquedad.");
        } finally {
            return result;
        }
    }

    public List<FnttFuenteTramoMunicipio> getAllMunicipiosByTramo(FnttTramo tramo) throws IdeamException {
        Query query =
            em.createNamedQuery("FnttFuenteTramoMunicipio.findByIdTramo");
        query.setParameter("idTramo", tramo.getId());
        return query.getResultList();
    }

    private List<FnttFuenteTramoMunicipio> getAllMunicipiosByTramo(Long idTramo) throws IdeamException {
        Query query =
            em.createNamedQuery("FnttFuenteTramoMunicipio.findByIdTramo");
        query.setParameter("idTramo", idTramo);
        return query.getResultList();
    }

    //este metodo no se transmite porque se consulta localmente los municipios relacionados cuando se borra o actualiza el tramo.

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void deleteMunicipios(FnttFuenteTramoMunicipio fnttFuenteTramoMunicipio) throws IdeamException {

        System.out.println("XXXXXXX==========Borrar municipio");

        try {
            System.out.println("XXXXXXX==========municipio:" +
                               fnttFuenteTramoMunicipio);
            em.remove(fnttFuenteTramoMunicipio);

            System.out.println("XXXXXXX==========Termina Borra municipio");
        } catch (Exception e) {
            System.out.println("XXXXXXX==========ERROR Borrando municipio" +
                               e);
        }


    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void deleteMunicipiosPlano(FuenteTramoMunicipioPOJO fnttMunicipioPOJO) throws IdeamException {
        System.out.println("XXXXXXX========== municipio Plano remoto: " +
                           fnttMunicipioPOJO);
        em.flush();

        em.remove(fnttMunicipioPOJO);

        System.out.println("XXXXXXX==========Termina Borra deleteMunicipiosPlano");
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public FuenteTramoMunicipioPOJO saveTramoMunicipioPlano(FuenteTramoMunicipioPOJO municipio) throws IdeamException {
        System.out.println("XXXXXXX==========Persiste municipio PLANO");

        System.out.println("XXXXXXX==========Arma objeto municipio pk normal");
        FnttFuenteTramoMunicipioPK pk = new FnttFuenteTramoMunicipioPK();
        pk.setIdDepartamento(municipio.getIdDepartamento());
        pk.setIdFuente(municipio.getIdFuente());
        pk.setIdMunicipio(municipio.getIdMunicipio());
        pk.setIdTramo(municipio.getIdTramo());

        System.out.println("XXXXXXX==========Arma municipio con la pk");
        FnttFuenteTramoMunicipio mun = new FnttFuenteTramoMunicipio(pk);

        System.out.println("-------Arma municipio: " + mun);

        System.out.println("XXXXXXX==========Persiste municipio PLANO, pero normal");
        em.persist(mun);

        System.out.println("XXXXXXX==========Finaliza municipio");
        return municipio;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public FnttFuenteTramoMunicipio saveTramoMunicipio(FnttFuenteTramoMunicipio municipio) throws IdeamException {

        System.out.println("XXXXXXX==========Persiste municipio");
        System.out.println("-------Recibe mun: " + municipio);
        em.persist(municipio);
        System.out.println("XXXXXXX==========Almacenó municipio");

        FuenteTramoMunicipioPOJO mun = new FuenteTramoMunicipioPOJO();

        System.out.println("XXXXXXX==========A transmitir");

        mun.setIdTramo(new Long(municipio.getFntFuenteTramoMunicipioPK().getIdTramo()));
        mun.setIdFuente(new Long(municipio.getFntFuenteTramoMunicipioPK().getIdFuente()));
        mun.setIdMunicipio(new Integer(municipio.getFntFuenteTramoMunicipioPK().getIdMunicipio()));
        mun.setIdDepartamento(new Integer(municipio.getFntFuenteTramoMunicipioPK().getIdDepartamento()));

        System.out.println("-------Transmite mun plano: " + mun);
        System.out.println("XXXXXXX==========Registra en cola de municipio");
        //envío por cola
        registroRemoto.registrarEvento(this.getClass().getName(),
                                       "saveTramoMunicipioPlano", mun);

        return municipio;
    }


    public List<FnttFuente> getAllFuentes(CriteriosBusquedaTO criterios) throws IdeamException {
        String sql = "Select ";
        sql += " id_fuente as id, nombre_fuente as nombre, descripcion, id_tipo_fuente, ";
        sql += " es_compartida, cod_autoridad, codigo_cuencaaa, id_provinciahidro, unidadhidro, FUENTE_CAR  ";
        sql += " From sirh_fuente_tramo_v_ ";

        int totalParametros = 0;
        if (criterios.getAutoridad() != null &&
            criterios.getAutoridad().getId() != null) {
            System.out.println("consulta por codigo de la autoridad:" +
                               criterios.getAutoridad().getId());
            totalParametros++;
            sql += " Where ";
            sql +=
" cod_autoridad =?" + totalParametros; //si es autoridad, solo lo suyo.
        }

        if (criterios.getArea() != null &&
            criterios.getArea().getId() != null) {

            if (totalParametros == 0)
                sql += " Where ";
            else
                sql += " and ";
            totalParametros++;
            sql += "  id_area =?" + totalParametros;
        }
        if (criterios.getZona() != null &&
            criterios.getZona().getId() != null) {
            if (totalParametros == 0)
                sql += " Where ";
            else
                sql += " and ";
            totalParametros++;
            sql += "  id_zona =?" + totalParametros;
        }
        if (criterios.getSubzona() != null &&
            criterios.getSubzona().getId() != null) {
            if (totalParametros == 0)
                sql += " Where ";
            else
                sql += " and ";
            totalParametros++;
            sql += "  id_subzona =?" + totalParametros;
        }
        if (criterios.getDepartamento() != null &&
            criterios.getDepartamento().getId() != null) {
            System.out.println("-------busca por departamento: " +
                               criterios.getDepartamento().getId());
            if (totalParametros == 0)
                sql += " Where ";
            else
                sql += " and ";
            totalParametros++;
            sql += "  id_departamento =?" + totalParametros;
        }
        if (criterios.getMunicipio() != null &&
            criterios.getMunicipio().getId() != null) {
            if (totalParametros == 0)
                sql += " Where ";
            else
                sql += " and ";
            totalParametros++;
            sql += "  id_municipio =?" + totalParametros;
        }
        if (criterios.getTipoFuente() != null &&
            criterios.getTipoFuente().getCodigo() != null) {
            if (totalParametros == 0)
                sql += " Where ";
            else
                sql += " and ";
            totalParametros++;
            sql += "  id_tipo_fuente =?" + totalParametros;
        }
        if (criterios.getNombreFuente() != null &&
            criterios.getNombreFuente().length() > 0) {
            System.out.println("-------busca por nombre de la fuente: " +
                               criterios.getNombreFuente());
            if (totalParametros == 0)
                sql += " Where ";
            else
                sql += " and ";
            sql += "  nombre like '%" + criterios.getNombreFuente() + "%' ";
        }


        sql += " group By ";
        sql += " id_fuente, nombre_fuente, descripcion, id_tipo_fuente, ";
        sql += 
" es_compartida, cod_autoridad ,codigo_cuencaaa, id_provinciahidro, unidadhidro, FUENTE_CAR  ";

        Query q = em.createNativeQuery(sql, FnttFuente.class);

        //ahora se adicionan los parametros


        totalParametros = 0;
        if (criterios.getAutoridad() != null &&
            criterios.getAutoridad().getId() != null) {
            System.out.println("AUTORIDAD:" +
                               criterios.getAutoridad().getId());
            totalParametros++;
            q.setParameter(totalParametros,
                           criterios.getAutoridad().getId()); //ideam o autoridad
        }
        if (criterios.getArea() != null &&
            criterios.getArea().getId() != null) {
            totalParametros++;
            q.setParameter(totalParametros, criterios.getArea().getId());
        }
        if (criterios.getZona() != null &&
            criterios.getZona().getId() != null) {
            totalParametros++;
            q.setParameter(totalParametros, criterios.getZona().getId());
        }
        if (criterios.getSubzona() != null &&
            criterios.getSubzona().getId() != null) {
            System.out.println("SUBZONA:" + criterios.getSubzona().getId());
            totalParametros++;
            q.setParameter(totalParametros, criterios.getSubzona().getId());
        }
        if (criterios.getDepartamento() != null &&
            criterios.getDepartamento().getId() != null) {
            totalParametros++;
            q.setParameter(totalParametros,
                           criterios.getDepartamento().getId());
        }
        if (criterios.getMunicipio() != null &&
            criterios.getMunicipio().getId() != null) {
            totalParametros++;
            q.setParameter(totalParametros, criterios.getMunicipio().getId());
        }
        if (criterios.getTipoFuente() != null &&
            criterios.getTipoFuente().getCodigo() != null) {
            totalParametros++;
            q.setParameter(totalParametros,
                           criterios.getTipoFuente().getCodigo());
        }

        List lista = q.getResultList();
        System.out.println("encuentra datos, list:" + lista.size());
        return lista;
    }

    public List<FnttTramo> getAllTramos(CriteriosBusquedaTO criterios) throws IdeamException {
        String sql = "Select * ";
      /*      sql += " id_tramo id, nombre_tramo nombre, id_subzona, id_zona, ";
    sql +=
" id_area, descripcon_tramo descripcion, longitud, tipo_flujo, ";
        sql += " oferta_hidrica_total, ";
        sql += " grados_pi, minutos_pi, segundos_pi,";
        sql += " grados_pf, minutos_pf, segundos_pf, altitud_pi, ";
        sql += " grados_long_pi, minutos_long_pi, segundos_long_pi,";
        sql += " grados_long_pf, minutos_long_pf, segundos_long_pf, altitud_pf, ";
        sql += " sistema_refencia sistema_referencia, id id_fuente, observacion_tramo ";
*/

        sql += " From sirh_fuente_tramo_v_ ";
        sql += " Where ";

        if (criterios.getAutoridad() != null &&
            criterios.getAutoridad().getId() != null) {
            sql += " cod_autoridad =" + criterios.getAutoridad().getId();
        } else {
            sql += " cod_autoridad <>" + criterios.getAutoridad().getId();
        }


        if (criterios.getArea() != null &&
            criterios.getArea().getId() != null) {
            System.out.println("id_area:" + criterios.getArea().getId());
            sql += " and id_area =" + criterios.getArea().getId();
        }
        if (criterios.getZona() != null &&
            criterios.getZona().getId() != null) {
            System.out.println("id_zona:" + criterios.getZona().getId());
            sql += " and id_zona =" + criterios.getZona().getId();
        }
        if (criterios.getSubzona() != null &&
            criterios.getSubzona().getId() != null) {
            System.out.println("id_subzona:" + criterios.getSubzona().getId());
            sql += " and id_subzona =" + criterios.getSubzona().getId();
        }
        if (criterios.getDepartamento() != null &&
            criterios.getDepartamento().getId() != null) {
            System.out.println("id_departamento:" +
                               criterios.getDepartamento().getId());
            sql += " and id_departamento =" + criterios.getDepartamento().getId();
        }
        if (criterios.getMunicipio() != null &&
            criterios.getMunicipio().getId() != null) {
            sql += " and id_municipio =" + criterios.getMunicipio().getId();
        }
        if (criterios.getFuente() != null &&
            criterios.getFuente().getId() != null) {
            sql += " and id_fuente = " + criterios.getFuente().getId();
        }

        if (criterios.getNombreFuente() != null &&
            criterios.getNombreFuente().length() > 0) {
            System.out.println("busca por nombre de la fuente:" +
                               criterios.getNombreFuente());
            sql += " and nombre_fuente like '%" + criterios.getNombreFuente() + "%' ";
        }
        if (criterios.getNombreTramo() != null &&
            criterios.getNombreTramo().length() > 0) {
            sql += " and nombre like '%" + criterios.getNombreTramo() + "%' ";
        }
    /*   sql += " Group By ";
        sql += " id_tramo, nombre_tramo, id_subzona, id_zona, ";
        sql += " id_area, descripcon_tramo, longitud, tipo_flujo, ";
        sql += " oferta_hidrica_total, ";
        sql += " grados_pi, minutos_pi, segundos_pi,";
        sql += " grados_pf, minutos_pf, segundos_pf, altitud_pi, ";
        sql += " grados_long_pi, minutos_long_pi, segundos_long_pi,";
        sql += " grados_long_pf, minutos_long_pf, segundos_long_pf, altitud_pf, ";
        sql += " sistema_refencia, id, observacion_tramo ";*/

        System.out.println("HCP ---------"+sql);

        Query q = em.createNativeQuery(sql, FnttTramo.class);


        List lista = q.getResultList();
        return lista;
    }

    public List<FnttFuente> getListaFuentes(Integer codigo) throws IdeamException {
        String sql = "";
        if (codigo != null) { //subzona
            sql =
"Select * From fntt_fuente " + " Where id IN( Select id_fuente From fntt_tramo " +
  "               Where id_subzona =  ?1 )";
        }
        Query q = em.createNativeQuery(sql, FnttFuente.class);
        q.setParameter(1, codigo);
        return q.getResultList();
    }

    public List<FnttFuente> getListaFuentesIdeam(Integer codigo) throws IdeamException {
        String sql = "";
        if (codigo != null) { //subzona
            sql =
"Select  id, punto as nombre,null as id_tipo_fuente, null as descripcion, " +
  " id_autoridad as cod_autoridad, null as es_compartida,null as codigo_cuencaaa, " +
  " null as id_provinciahidro, null as unidadhidro, null fuente_car" +
  " From sirhv_punto_monitoreo_fq   " +
  " Where id_fuente IN( Select id_fuente From part_ref_pto_mon_subzonas  " +
  "                       Where id_fuente is not NULL and id_subzona  =  ?1 ) ";
        }
        System.out.println("SQLCRITERIOS getListaFuentesIdeam = " +
                           codigo.toString() + " -->" + sql);
        Query q = em.createNativeQuery(sql, FnttFuente.class);
        q.setParameter(1, codigo);
        return q.getResultList();
    }

    public List<FnttTramo> getListaTramos(Integer codigoFuente) throws IdeamException {
        String sql = "";
        if (codigoFuente != null) { //fuenteid
            sql = "Select *  From fntt_tramo  Where  id_fuente =  ?1 ";
        }
        Query q = em.createNativeQuery(sql, FnttTramo.class);
        q.setParameter(1, codigoFuente.longValue());
        List<FnttTramo> l = q.getResultList();
        System.out.println(l.size() + " tramos encontrados");
        return l;
    }

    // HUGO 20141030

    public List<DatosTablaAlertDisp> getListaAlertasDisponibilidad(int idArea,
                                                                   int idZona,
                                                                   int idSubzona) throws IdeamException {
        String sql = FuenteTO.qAlertasDisponibilidad;
        Query q = em.createNativeQuery(sql, DatosTablaAlertDisp.class);

        q.setParameter(1, idArea);
        q.setParameter(2, idZona);
        q.setParameter(3, idSubzona);

        List<DatosTablaAlertDisp> lista = q.getResultList();
        return lista;
    }

    public List<DatosTablaAlertDispDet> getListaAlertasDisponibilidadDet(Long idTramo) throws IdeamException {
        String sql = FuenteTO.qAlertasDisponibilidadDet;
        Query q = em.createNativeQuery(sql, DatosTablaAlertDispDet.class);

        q.setParameter(1, idTramo);

        List<DatosTablaAlertDispDet> lista = q.getResultList();
        return lista;
    }

    public List<Object[]> getListaOfertaXFuenteTramo(Long idFuente) throws IdeamException {

        String sql = FuenteTO.qOfertaXFuenteTramo;
        Query q = em.createNativeQuery(sql);

        q.setParameter(1, idFuente);

        return q.getResultList();
    }

    public List<DatosTablaAlertIca> getListaAlertasIca(int idArea, int idZona,
                                                       int idSubzona,
                                                       long idFuente,
                                                       String nivel) throws IdeamException {
        String sql = FuenteTO.qAlertasIca;
        Query q = em.createNativeQuery(sql, DatosTablaAlertIca.class);

        q.setParameter(1, idArea);
        q.setParameter(2, idZona);
        q.setParameter(3, idSubzona);
        q.setParameter(4, idFuente);

        String[] limites = nivel.split("-");

        Double limInf = Double.parseDouble(limites[0]);
        Double limSup = Double.parseDouble(limites[1]);

        q.setParameter(5, limInf);
        q.setParameter(6, limSup);

        List<DatosTablaAlertIca> lista = q.getResultList();

        return lista;
    }

    public List<DatosTablaAlertIca> getListaAlertasIca(Long idPunto) throws IdeamException {

        System.out.println("Punto a consultar" + idPunto);

        String sql = FuenteTO.qAlertasIcaXPunto;
        Query q = em.createNativeQuery(sql, DatosTablaAlertIca.class);

        q.setParameter(1, idPunto);

        List<DatosTablaAlertIca> lista = q.getResultList();

        return lista;
    }

    public List<DatosTablaAlertIca> getListaAlertasIcaFuente(Long idFuente) throws IdeamException {

        System.out.println("Fuente a consultar" + idFuente);

        String sql = FuenteTO.qAlertasIcaXFuente;
        Query q = em.createNativeQuery(sql, DatosTablaAlertIca.class);

        q.setParameter(1, idFuente);

        List<DatosTablaAlertIca> lista = q.getResultList();

        return lista;
    }

    public List<Object[]> getListaMuestrasIca(Long idMuestra) {
        String sql = FuenteTO.qMuestrasIca;
        Query q = em.createNativeQuery(sql);

        q.setParameter(1, idMuestra);

        return q.getResultList();
    }

    public List<DatosTablaAlertIcaDet> getListaMuestrasIcaDet(Long idMuestra) {
        String sql = FuenteTO.qMuestrasIca;
        Query q = em.createNativeQuery(sql, DatosTablaAlertIcaDet.class);

        q.setParameter(1, idMuestra);

        List<DatosTablaAlertIcaDet> lista = q.getResultList();

        return lista;

    }

    public List<FnttConflicto> getConflictosXFuente(Long idFuente) throws IdeamException {
        try {
            Query query = em.createNamedQuery("FnttConflicto.findByFuente");
            query.setParameter("idFuente", idFuente);

            List lista = query.getResultList();

            return lista;

        } catch (NoResultException e) {
            return null;
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public FnttConflicto updateConflicto(FnttConflicto conflicto) throws IdeamException {

        if (conflicto.getId() == null || conflicto.getId().longValue() == 0) {
            conflicto.setId(GeneradorSeq.obtenerSequencia(new Long(conflicto.getCodAutoridad()),
                                                          "SEQ_CONFLICTOS",
                                                          em));
            em.persist(conflicto);
        } else {
            conflicto = em.merge(conflicto);
        }

        return conflicto;
    }

    public List<TipoConflicto> getTipoConflictoXConflicto(Long idConflicto) throws IdeamException,
                                                                                   NoResultException {
        List result = null;
        Query query = em.createNamedQuery("TipoConflicto.findByIdConflicto");
        query.setParameter("idConflicto", idConflicto);
        result = query.getResultList();
        return result;
    }

    public void deleteTipoConflicto(TipoConflicto tipoConflicto) throws IdeamException {
        try {
            em.flush();
            tipoConflicto = em.merge(tipoConflicto);
            em.remove(tipoConflicto);

        } catch (Exception e) {
            System.out.println("XXXXXXXXXXXXXXXXX---ERROR AL BORRAR TIPO CONFLICTO: " +
                               e.getMessage());
        } finally {
        }

    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public TipoConflicto createTipoConflicto(TipoConflicto tipoConflicto) throws IdeamException {
        try {
            tipoConflicto.setId(GeneradorSeq.obtenerSequencia(tipoConflicto.getCodigoAutoridad(),
                                                              "SEQ_TIPO_CONFLICTO",
                                                              em));
            em.persist(tipoConflicto);

            return tipoConflicto;

        } catch (Exception e) {
            System.out.println("ERROR Creando tipo conflicto " +
                               e.getMessage());
        }

        return null;
    }

    public List<OrigenConflicto> getOrigenConflictoXConflicto(Long idConflicto) throws IdeamException,
                                                                                       NoResultException {
        List result = null;
        Query query = em.createNamedQuery("OrigenConflicto.findByIdConflicto");
        query.setParameter("idConflicto", idConflicto);
        result = query.getResultList();
        return result;
    }

    public void deleteOrigenConflicto(OrigenConflicto origenConflicto) throws IdeamException {
        try {
            em.flush();
            origenConflicto = em.merge(origenConflicto);
            em.remove(origenConflicto);

        } catch (Exception e) {
            System.out.println("XXXXXXXXXXXXXXXXX---ERROR AL BORRAR ORIGEN CONFLICTO: " +
                               e.getMessage());
        } finally {
        }

    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public OrigenConflicto createOrigenConflicto(OrigenConflicto origenConflicto) throws IdeamException {
        try {
            origenConflicto.setId(GeneradorSeq.obtenerSequencia(origenConflicto.getCodigoAutoridad(),
                                                                "SEQ_ORIGEN_CONFLICTO",
                                                                em));
            em.persist(origenConflicto);

            return origenConflicto;

        } catch (Exception e) {
            System.out.println("ERROR Creando origen conflicto " +
                               e.getMessage());
        }

        return null;
    }

    public List<ActorConflicto> getActorConflictoXConflicto(Long idConflicto) throws IdeamException,
                                                                                     NoResultException {
        List result = null;
        Query query = em.createNamedQuery("ActorConflicto.findByIdConflicto");
        query.setParameter("idConflicto", idConflicto);
        result = query.getResultList();
        return result;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public ActorConflicto updateActorConflicto(ActorConflicto actorConflicto) throws IdeamException {

        if (actorConflicto.getId() == null ||
            actorConflicto.getId().longValue() == 0) {
            actorConflicto.setId(GeneradorSeq.obtenerSequencia(new Long(actorConflicto.getCodigoAutoridad()),
                                                               "SEQ_ACTOR_CONFLICTO",
                                                               em));
            em.persist(actorConflicto);
        } else {
            actorConflicto = em.merge(actorConflicto);
        }

        return actorConflicto;
    }

    public void deleteActorConflicto(ActorConflicto actorConflicto) throws IdeamException {
        try {
            em.flush();
            actorConflicto = em.merge(actorConflicto);
            em.remove(actorConflicto);

        } catch (Exception e) {
            System.out.println("XXXXXXXXXXXXXXXXX---ERROR AL BORRAR ACTOR CONFLICTO: " +
                               e.getMessage());
        } finally {
        }

    }

    public List<PoblacionConflicto> getPoblacionConflictoXConflicto(Long idConflicto) throws IdeamException,
                                                                                             NoResultException {
        List result = null;
        Query query =
            em.createNamedQuery("PoblacionConflicto.findByIdConflicto");
        query.setParameter("idConflicto", idConflicto);
        result = query.getResultList();
        return result;
    }

    public void deletePoblacionConflicto(PoblacionConflicto poblacionConflicto) throws IdeamException {
        try {
            em.flush();
            poblacionConflicto = em.merge(poblacionConflicto);
            em.remove(poblacionConflicto);

        } catch (Exception e) {
            System.out.println("XXXXXXXXXXXXXXXXX---ERROR AL BORRAR POBLACION CONFLICTO: " +
                               e.getMessage());
        } finally {
        }

    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public PoblacionConflicto createPoblacionConflicto(PoblacionConflicto poblacionConflicto) throws IdeamException {
        try {
            poblacionConflicto.setId(GeneradorSeq.obtenerSequencia(poblacionConflicto.getCodigoAutoridad(),
                                                                   "SEQ_POBLACION_CONFLICTO",
                                                                   em));
            em.persist(poblacionConflicto);

            return poblacionConflicto;

        } catch (Exception e) {
            System.out.println("ERROR Creando poblacion conflicto " +
                               e.getMessage());
        }

        return null;
    }

    public List<DerechoConflicto> getDerechoConflictoXConflicto(Long idConflicto) throws IdeamException,
                                                                                         NoResultException {
        List result = null;
        Query query =
            em.createNamedQuery("DerechoConflicto.findByIdConflicto");
        query.setParameter("idConflicto", idConflicto);
        result = query.getResultList();
        return result;
    }

    public void deleteDerechoConflicto(DerechoConflicto derechoConflicto) throws IdeamException {
        try {
            em.flush();
            derechoConflicto = em.merge(derechoConflicto);
            em.remove(derechoConflicto);

        } catch (Exception e) {
            System.out.println("XXXXXXXXXXXXXXXXX---ERROR AL BORRAR DERECHO CONFLICTO: " +
                               e.getMessage());
        } finally {
        }

    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public DerechoConflicto createDerechoConflicto(DerechoConflicto derechoConflicto) throws IdeamException {
        try {
            derechoConflicto.setId(GeneradorSeq.obtenerSequencia(derechoConflicto.getCodigoAutoridad(),
                                                                 "SEQ_DERECHO_CONFLICTO",
                                                                 em));
            em.persist(derechoConflicto);

            return derechoConflicto;

        } catch (Exception e) {
            System.out.println("ERROR Creando derecho conflicto " +
                               e.getMessage());
        }

        return null;
    }

    public List<GestionConflicto> getGestionConflictoXConflicto(Long idConflicto) throws IdeamException,
                                                                                         NoResultException {
        List result = null;
        Query query =
            em.createNamedQuery("GestionConflicto.findByIdConflicto");
        query.setParameter("idConflicto", idConflicto);
        result = query.getResultList();
        return result;
    }

    public void deleteGestionConflicto(GestionConflicto gestionConflicto) throws IdeamException {
        try {
            em.flush();
            gestionConflicto = em.merge(gestionConflicto);
            em.remove(gestionConflicto);

        } catch (Exception e) {
            System.out.println("XXXXXXXXXXXXXXXXX---ERROR AL BORRAR GESTION CONFLICTO: " +
                               e.getMessage());
        } finally {
        }

    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public GestionConflicto createGestionConflicto(GestionConflicto gestionConflicto) throws IdeamException {
        try {
            gestionConflicto.setId(GeneradorSeq.obtenerSequencia(gestionConflicto.getCodigoAutoridad(),
                                                                 "SEQ_GESTION_CONFLICTO",
                                                                 em));
            em.persist(gestionConflicto);

            return gestionConflicto;

        } catch (Exception e) {
            System.out.println("ERROR Creando gestion conflicto " +
                               e.getMessage());
        }

        return null;
    }

    public String[][] getConflictosXTipo(Long idAutoridad) throws IdeamException {

        DatosConflictoTO datosConflicto = new DatosConflictoTO();
        datosConflicto.setIdAutoridad(idAutoridad);
        return getConflictosXTipo(datosConflicto.getSqlConflictosXTipo());

    }

    private String[][] getConflictosXTipo(String sql) throws IdeamException {

        System.out.println("HCP va ejecutar " + sql);

        String retorno[][] = null;
        Query q = em.createNativeQuery(sql, DatosConflictoTO.class);

        List lista = q.getResultList();

        Iterator it = lista.iterator();
        retorno = new String[2][lista.size()];

        int i = 0;
        while (it.hasNext()) {
            DatosConflictoTO datos = (DatosConflictoTO)it.next();
            retorno[0][i] = datos.getDescripcion();
            retorno[1][i] = datos.getCantidad().toString();

            System.out.println("HCP retorno  " + datos.getDescripcion());

            i++;
        }
        return retorno;
    }

    public List<FnttFuente> getFuentesConflicto(Long idAutoridad) throws IdeamException {

        String sql =
            "select f.id , aa.sigla || ' - ' || pl.valor || ' ' || f.nombre as nombre , f.id_tipo_fuente, f.descripcion , " +
            "f.cod_autoridad , f.es_compartida , f.codigo_cuencaaa, f.ID_PROVINCIAHIDRO, f.UNIDADHIDRO, f.fuente_car  " +
            "from fntt_fuente f , autoridades aa, part_listas pl " +
            "where f.id in ( select distinct id_fuente from  fntt_conflicto ) " +
            "and f.cod_autoridad = aa.id " + "and pl.id= f.id_tipo_fuente ";

        if (idAutoridad != null)
            sql += " and  f.cod_autoridad = " + idAutoridad.toString() + " ";
        else
            sql += " order by f.cod_autoridad ";

        Query q = em.createNativeQuery(sql, FnttFuente.class);
        return q.getResultList();

    }


   
    





//CDONCEL
    /**
     * Metodo empleado por el Nodo IDEAM para guardar Aforo
     * @return
     * @throws IdeamException
     */
     @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void updateAforo(FnttTrmAforo aforo)throws IdeamException{
   
      if (aforo==null){
          throw new IdeamException("Entidad aforo no puede ser null");
      }
      boolean persiste=false;
      if(aforo.getId()==null || aforo.getId().longValue()==0){         
          aforo.setId( GeneradorSeq.obtenerSequencia(aforo.getTramoId().getIdFuente().getCodigoAutoridad(), "SEQ_TRM_AFORO", em));
          em.persist(aforo);
          persiste=true;
      }else{
          aforo = em.merge(aforo);
      }
    }
    
  /**
   * Metodo empleado para Obtener listado de Aforo
   * @param tramo
   * @return
   * @throws IdeamException
   */
  public List<FnttTrmAforo> getLsAforos(FnttTramo tramo) throws IdeamException {
      
      String sql= "select a.CAUDAL, a.FECHA, a.GRADOS_LAT, a.GRADOS_LONG, a.ID, " +
        "a.MINUTOS_LAT, a.MINUTOS_LONG, a.SEGUNDOS_LAT, a.SEGUNDOS_LONG, a.TRAMO_ID  " +
        "from FNTT_TRM_AFORO a " + 
      "WHERE a.TRAMO_ID  = "+ tramo.getId() + " ";
      //System.out.println("++++++++++++++++++++ SQL");
      //System.out.println(sql);
      Query q = em.createNativeQuery(sql, FnttTrmAforo.class);
      List lista = q.getResultList();
      List<FnttTrmAforo> lsAforo = new ArrayList<FnttTrmAforo>();
      lsAforo.addAll(lista);
      return lsAforo;

  }
  public void borrarAforo(String id) {
    Long l = Long.valueOf(id);
    String sql= "select *  " +
      "from FNTT_TRM_AFORO a " + 
    "WHERE a.ID  = "+ l + " ";
    //System.out.println("++++++++++++++++++++ SQL");
    //System.out.println(sql);
    Query q = em.createNativeQuery(sql, FnttTrmAforo.class);
    FnttTrmAforo aforo = (FnttTrmAforo)q.getSingleResult();
    System.out.println("************AFORO: " + aforo.getId());
    em.flush();
    aforo = em.merge(aforo);
     em.remove(aforo);
  }
    //FIN CDONCEL

    //Buenas Practicas

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public FnttBuenasPracticas updateBuenaPractica(FnttBuenasPracticas practica) throws IdeamException {

        if (practica.getIdbuenapraactica() == null ||
            practica.getIdbuenapraactica().longValue() == 0) {
            //practica.setIdbuenapraactica(GeneradorSeq.obtenerSequencia(new Long(practica.getFnttFuente().getCodAutoridad().getId()), "SEQ_BUENASPRACTICAS", em));
            em.persist(practica);
        } else {
            practica = em.merge(practica);
        }

        return practica;
    }

    public FnttBuenasPracticas initializeFnttBuenasPracticas(FnttBuenasPracticas practica) throws IdeamException {
        try {

            //em.find(FnttBuenasPracticas.class, practica.getIdbuenapraactica());

            Query q =
                this.em.createQuery("" + "SELECT b FROM FnttBuenasPracticas b " +
                                    "   left join fetch b.fnttPrincipiosList " +
                                    "   left join fetch b.fnttMotivacionesList " +
                                    "   left join fetch b.fnttLogrosList " +
                                    "   left join fetch b.fnttCostos " +
                                    "WHERE b.idbuenapraactica = :id");
            q.setParameter("id", practica.getIdbuenapraactica());
            FnttBuenasPracticas b = (FnttBuenasPracticas)q.getSingleResult();

            return b;

        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            return null;
        }
    }


    public FnttConflicto initializeFnttConflicto(FnttConflicto conflicto) throws IdeamException {
        try {

            //em.find(FnttBuenasPracticas.class, practica.getIdbuenapraactica());

            Query q =
                this.em.createQuery("" + "SELECT c FROM FnttConflicto c " +
                                    "   left join fetch c.fnttActividadesEconomicasList " +
                                    "   left join fetch c.fnttLogrosList " +
                                    "WHERE c.id = :id");
            q.setParameter("id", conflicto.getId());
            FnttConflicto c = (FnttConflicto)q.getSingleResult();

            return c;

        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public List<FnttBuenasPracticas> getFnttBuenasPracticasXFuente(Long idFuente) throws IdeamException {
        try {
            Query query =
                em.createNamedQuery("FnttBuenasPracticas.findByFuente");
            query.setParameter("idFuente", idFuente);

            List lista = query.getResultList();

            return lista;

        } catch (NoResultException e) {
            return null;
        }
    }

    public List<FnttPrincipios> getPrincipiosBuenasPracticas() throws IdeamException {
        try {
            Query query = em.createNamedQuery("FnttPrincipios.findAll");

            List lista = query.getResultList();

            return lista;

        } catch (NoResultException e) {
            return null;
        }
    }

    public List<FnttCategorias> getCategoriasBuenasPracticas() throws IdeamException {
        try {
            Query query = em.createNamedQuery("FnttCategorias.findAll");

            List lista = query.getResultList();

            return lista;

        } catch (NoResultException e) {
            return null;
        }
    }

    public List<FnttProyectosEducativos> getProyectosEducativosBuenasPracticas() throws IdeamException {
        try {
            Query query =
                em.createNamedQuery("FnttProyectosEducativos.findAll");

            List lista = query.getResultList();

            return lista;

        } catch (NoResultException e) {
            return null;
        }
    }

    public List<FnttMotivaciones> getMotivacionesBuenasPracticas() throws IdeamException {
        try {
            Query query = em.createNamedQuery("FnttMotivaciones.findAll");

            List lista = query.getResultList();

            return lista;

        } catch (NoResultException e) {
            return null;
        }
    }

    public List<FnttActores> getActoresBuenasPracticas() throws IdeamException {
        try {
            Query query = em.createNamedQuery("FnttActores.findAll");

            List lista = query.getResultList();

            return lista;

        } catch (NoResultException e) {
            return null;
        }
    }

    public List<FnttCostos> getCostosBuenasPracticas() throws IdeamException {
        try {
            Query query = em.createNamedQuery("FnttCostos.findAll");

            List lista = query.getResultList();

            return lista;

        } catch (NoResultException e) {
            return null;
        }
    }


    public List<FnttLogros> getLogrosByTipo(String tipo) throws IdeamException {
        try {
            Query query = em.createNamedQuery("FnttLogros.findAllByTipo");
            query.setParameter("tipo", tipo);

            List lista = query.getResultList();

            return lista;

        } catch (NoResultException e) {
            return null;
        }
    }

    public List<FnttActividadesEconomicas> getActividadesEconomicas() throws IdeamException {
        try {
            Query query =
                em.createNamedQuery("FnttActividadesEconomicas.findAll");

            List lista = query.getResultList();

            return lista;

        } catch (NoResultException e) {
            return null;
        }
    }

    private String[][] getDatosReporte(final EstadisticasQry qry) throws IdeamException {
        return getDatosReporte(qry.getQuery());
    }
    
    private String[][] getDatosReporte(final String qry) throws IdeamException {
        String retorno[][] = null;
        Query q = em.createNativeQuery(qry, DatosReporteTO.class);
        List lista = q.getResultList();
        Iterator it = lista.iterator();
        retorno = new String[2][lista.size()];
        int i = 0;
        while (it.hasNext()) {
            DatosReporteTO datos = (DatosReporteTO)it.next();
            retorno[0][i] = datos.getDescripcion();
            retorno[1][i] = datos.getCantidad().toString();
            i++;
        }
        return retorno;
    }

    public String[][] getEstadistica(EstadisticasQry qry) throws IdeamException {
        return getDatosReporte(qry);
    }
	
	public void deleteBuenaPractica(FnttBuenasPracticas practica) throws IdeamException{
        FnttBuenasPracticas _practica = em.getReference(FnttBuenasPracticas.class, practica.getIdbuenapraactica());
        em.remove(_practica);
    }



/**
       * Elimina un objeto de cualquier tipo
       * @param Objeto a eliminar
       * @throws IdeamException
       */
      @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
      public void delete(Object objeto) throws IdeamException {

        Transaction tr = null;
        Session session = null;
        try {
          session = (Session)em.getDelegate();
          tr = session.beginTransaction();
          em.remove(em.contains(objeto) ? objeto : em.merge(objeto));
          session.flush();
          tr.commit();
        } catch (Throwable t) {
          tr.rollback();
          t.printStackTrace();
        }
      }

      /**
       * Gelimina objetos de cualquier tipo contenidos en un array
       * @param Array de objetos a eliminar
       * @throws IdeamException
       */
      @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
      public void deleteArray(final Object[] objeto) throws IdeamException {

        Transaction tr = null;
        Session session = null;
        int i = 0;
        try {
          session = (Session)em.getDelegate();
          tr = session.beginTransaction();
          for (; i < objeto.length; i++) {
            Object objetoAEliminar = objeto[i];
            em.remove(em.contains(objetoAEliminar) ? objetoAEliminar :
                      em.merge(objetoAEliminar));
          }
          session.flush();
          tr.commit();
        } catch (Throwable t) {
          tr.rollback();
          t.printStackTrace();
        }
      }

      /**
       * Guarda o actualiza un objeto de cualquier tipo
       * @param Objeto a guardar
       * @throws IdeamException
       */
      @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
      public void saveOrUpdate(Object objeto) throws IdeamException {

        Transaction tr = null;
        Session session = null;
        try {
          session = (Session)em.getDelegate();
          tr = session.beginTransaction();
          session.saveOrUpdate(objeto);
          session.flush();
          tr.commit();

        } catch (Throwable t) {
          tr.rollback();
          t.printStackTrace();
        }

      }

      /**
       * Guarda o actualiza objetos de cualquier tipo contenidos en un array
       * @param Array de objetos a guardar
       * @throws IdeamException
       */
      @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
      public void saveOrUpdateArray(final Object[] objeto) throws IdeamException {

        Transaction tr = null;
        Session session = null;
        int i = 0;
        try {
          session = (Session)em.getDelegate();
          tr = session.beginTransaction();
          for (; i < objeto.length; i++) {
            em.persist(objeto[i]);
          }
          session.flush();
          tr.commit();
        } catch (Throwable t) {
          tr.rollback();
          t.printStackTrace();
        }
      }


//diego lopez
    public List<FnttDetalleFuentesV> getDetalleTramosRelacionados(Autoridades autoridad) throws IdeamException {
      try {
        if (autoridad == null || autoridad.getId() == null) {
          return new ArrayList();
        } else {
          Query query =
            em.createNamedQuery("FnttDetalleFuentesV.findByCodigoAutoridad");
            query.setParameter("codigoAutoridad", autoridad.getId());

          List lista = query.getResultList();

          return lista;

        }

      } catch (NoResultException e) {
        return null;
      }
    }

}

