package co.gov.ideam.sirh.calidad.business;


import co.gov.ideam.sirh.calidad.model.CriteriosBusquedaMuestrasTO;
import co.gov.ideam.sirh.calidad.model.CriteriosBusquedaPuntos;
import co.gov.ideam.sirh.calidad.model.CriteriosBusquedaPuntosTO;
import co.gov.ideam.sirh.calidad.model.DatosGrafico;
import co.gov.ideam.sirh.calidad.model.DatosGraficoCalidad;
import co.gov.ideam.sirh.calidad.model.DatosReporteCalidadPOJO;
import co.gov.ideam.sirh.calidad.model.DatosReporteCalidadParametrosPuntoPOJO;
import co.gov.ideam.sirh.calidad.model.DatosReporteCalidadTO;
import co.gov.ideam.sirh.calidad.model.DatosReporteCroosTab;
import co.gov.ideam.sirh.calidad.model.JSDemandaTO;
import co.gov.ideam.sirh.red.ideam.model.DatosReporteIcaCroosTabIdeam;
import co.gov.ideam.sirh.calidad.model.Medicion;
import co.gov.ideam.sirh.calidad.model.MedicionPOJO;
import co.gov.ideam.sirh.calidad.model.Muestra;

//import co.gov.ideam.sirh.red.ideam.model.MedicionIdeam;
//import co.gov.ideam.sirh.red.ideam.model.MuestraIdeam;

import co.gov.ideam.sirh.calidad.model.MuestraTO;
import co.gov.ideam.sirh.calidad.model.MuestrasIca;
import co.gov.ideam.sirh.calidad.model.MuestrasPOJO;
import co.gov.ideam.sirh.calidad.model.NormaLimites;
import co.gov.ideam.sirh.calidad.model.NormaUsos;
import co.gov.ideam.sirh.calidad.model.PuntoMonitoreo;
import co.gov.ideam.sirh.calidad.model.PuntoMonitoreoPOJO;
import co.gov.ideam.sirh.calidad.model.PuntoMonitoreoTO;
import co.gov.ideam.sirh.fuentes.model.CriteriosBusquedaTO;
import co.gov.ideam.sirh.fuentes.model.FnttFuente;
import co.gov.ideam.sirh.fuentes.model.FnttTramo;
import co.gov.ideam.sirh.fuentes.model.FuentePOJO;
import co.gov.ideam.sirh.parametros.business.ParametrosEJB;
import co.gov.ideam.sirh.parametros.model.Autoridades;
import co.gov.ideam.sirh.parametros.model.Categoria;
import co.gov.ideam.sirh.parametros.model.Divipola;
import co.gov.ideam.sirh.parametros.model.Lista;
import co.gov.ideam.sirh.parametros.model.Parametro;
import co.gov.ideam.sirh.parametros.model.PartZonificListas;
import co.gov.ideam.sirh.red.ideam.model.SirhvPuntoMonitoreoFq;
import co.gov.ideam.sirh.remoto.business.RegistroRemotoLocal;
import co.gov.ideam.sirh.usuarios.agua.business.UsuariosAguaEJB;
import co.gov.ideam.sirh.usuarios.agua.model.Captacion;
import co.gov.ideam.sirh.usuarios.agua.model.DatosReporteTO;
import co.gov.ideam.sirh.usuarios.agua.model.GeneradorSeq;
import co.gov.ideam.sirh.usuarios.agua.model.PuntoVertimiento;
import co.gov.ideam.sirh.usuarios.agua.model.ws.entidades.DatosReporteWS;
import co.gov.ideam.sirh.util.Constantes;
import co.gov.ideam.sirh.util.ConstantesCalidad;
import co.gov.ideam.sirh.util.IdeamException;

import java.math.BigDecimal;
import java.text.DateFormat;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.faces.model.SelectItem;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import utils.system;


@Stateless(name = "CalidadEJB", mappedName = "Sirh-CalidadEJB")
@Remote
public class CalidadEJBBean implements CalidadEJB {

    @PersistenceContext(unitName = "SirhPU")
    private EntityManager em;
    @EJB
    private RegistroRemotoLocal registroRemoto;
    @EJB
    private ParametrosEJB parametrosService;
    @EJB
    private UsuariosAguaEJB usuariosAguaService;
    DatosReporteCalidadTO dCalTo = new DatosReporteCalidadTO();
    JSDemandaTO djsCalTo = new JSDemandaTO();

    public CalidadEJBBean() {

    }


    /**
     * Retorna una lista con los puntos de monitoreo registrados en el sistema
     * recibida como parametro
     * @return
     * @throws IdeamException
     */


    public List<PuntoMonitoreoTO> getAllPuntosMonitoreo() throws IdeamException {
        Query query = em.createNamedQuery("PuntoMonitoreoTO.findAllPuntos");
        return query.getResultList();
    }


    public List<PuntoMonitoreoTO> getAllPuntosMonitoreo(Long codigoAutoridad) throws IdeamException {
        Query query = em.createNamedQuery("PuntoMonitoreoTO.findAutoridadAll");
        query.setParameter("codigoAutoridad", codigoAutoridad);
        List lista = query.getResultList();

        return lista;
    }


    public PuntoMonitoreo getPuntosMonitoreoNomAut(String nombrePunto,
                                                   Long codigoAutoridad) throws IdeamException {

        try {

            Query query = em.createNamedQuery("PuntoMonitoreo.finByNombAut");
            query.setParameter("codigoAutoridad", codigoAutoridad);
            query.setParameter("nombre", nombrePunto.toUpperCase());
            PuntoMonitoreo pm = (PuntoMonitoreo)query.getSingleResult();
            loadAttributes(pm);
            return pm;
        } catch (NoResultException e) {
            return null;
        }
    }


    public List<NormaUsos> getNormaUsos() throws IdeamException {

        try {

            Query query = em.createNamedQuery("NormaUsos.findAll");
            List lista = query.getResultList();
            if (lista != null) {
                Iterator it = lista.iterator();
                while (it.hasNext()) {
                    NormaUsos nUsos = (NormaUsos)it.next();
                    Integer usoid = nUsos.getUso();
                    if (usoid != null) {
                        Lista listaUso = parametrosService.getLista(usoid);
                        nUsos.setListaUsos(listaUso);
                    }
                    Integer riesgoid = nUsos.getRiesgo();
                    if (riesgoid != null) {
                        Lista listaRiesgo = parametrosService.getLista(riesgoid);
                        nUsos.setListaRiesgo(listaRiesgo);
                    }
                }
            }

            return lista;


        } catch (NoResultException e) {
            return null;
        }
    }

    public List<NormaUsos> getNormaUsosXAutoridad(Long idAutoridad) throws IdeamException {

        try {
            
            System.out.println("Entro a getNormaUsosXAutoridad");
            Query query = em.createNamedQuery("NormaUsos.findXAutoridad");
            query.setParameter("codigoAutoridad", idAutoridad);

            List lista = query.getResultList();
            if (lista != null) {
                Iterator it = lista.iterator();
                while (it.hasNext()) {
                    NormaUsos nUsos = (NormaUsos)it.next();
                    Integer usoid = nUsos.getUso();
                    if (usoid != null) {
                        Lista listaUso = parametrosService.getLista(usoid);
                        nUsos.setListaUsos(listaUso);
                    }
                    Integer riesgoid = nUsos.getRiesgo();
                    if (riesgoid != null) {
                        Lista listaRiesgo = parametrosService.getLista(riesgoid);
                        nUsos.setListaRiesgo(listaRiesgo);
                    }
                }
            }

            return lista;


        } catch (NoResultException e) {
            return null;
        }
    }

    public NormaUsos getNormaUsosId(Long id) throws IdeamException {

        try {

            Query query = em.createNamedQuery("NormaUsos.findId");
            query.setParameter("id", id);

            NormaUsos nUsos = (NormaUsos)query.getSingleResult();

            Integer usoid = nUsos.getUso();
            if (usoid != null) {
                Lista listaUso = parametrosService.getLista(usoid);
                nUsos.setListaUsos(listaUso);
            }
            Integer riesgoid = nUsos.getRiesgo();
            if (riesgoid != null) {
                Lista listaRiesgo = parametrosService.getLista(riesgoid);
                nUsos.setListaRiesgo(listaRiesgo);
            }

            return nUsos;
        } catch (NoResultException e) {
            return null;
        }


    }


    public NormaLimites getNormaLimitesId(Long id) throws IdeamException {

        try {

            Query query = em.createNamedQuery("NormaLimites.findId");
            query.setParameter("id", id);

            NormaLimites nlim = (NormaLimites)query.getSingleResult();
            loadAttributesNormaLimites(nlim);

            return nlim;
        } catch (NoResultException e) {
            return null;
        }


    }


    public void deleteNormaLimites(NormaLimites nl) throws IdeamException {
        em.flush();
        nl = em.merge(nl);
        em.remove(nl);
    }

    public List<NormaLimites> getNormaLimites(Integer idNorma,
                                              Integer idUso,
                                              Integer idRiesgo) throws IdeamException {

        try {

            Query query = em.createNamedQuery("NormaLimites.findNormaUso");
            query.setParameter("idNorma", idNorma);
            query.setParameter("idUso", idUso);
            query.setParameter("idRiesgo", idRiesgo);


            List lista = query.getResultList();

            if (lista != null) {
                Iterator it = lista.iterator();
                while (it.hasNext()) {
                    NormaLimites lims = (NormaLimites)it.next();
                    this.loadAttributesNormaLimites(lims);
                }
            }

            return lista;


        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * Retorna una lista con los puntos de monitoreo con los criterios de busqueda solicitados
     * recibida como parametro
     * @return
     * @throws IdeamException
     */

    public List getPuntosMonitoreo(CriteriosBusquedaPuntos criterios) throws IdeamException {
        boolean existenCriterios = false;
        String sql = "select * from calt_puntosMonitoreo_v where ";
        if (criterios.getListatipoPunto() != null &&
            criterios.getListatipoPunto().getValor() != null) {
            sql +=
" upper(tipo_punto) = '" + criterios.getListatipoPunto().getValor().toString().toUpperCase() +
 "'";
        }

        if (criterios.getArea() != null &&
            criterios.getArea().getId() != null) {
            sql +=
" and  upper(area) = '" + criterios.getArea().getValor().toString().toUpperCase() +
 "'";

        }

        if (criterios.getZona() != null &&
            criterios.getZona().getId() != null) {
            sql +=
" and upper(zona) = '" + criterios.getZona().getValor().toString().toUpperCase() +
 "'";

        }

        if (criterios.getSubzona() != null &&
            criterios.getSubzona().getId() != null) {
            sql +=
" and upper(subzona) = '" + criterios.getSubzona().getValor().toString().toUpperCase() +
 "'";

        }

        if (criterios.getListaFuente().getNombre() != null) {
            sql +=
" and upper(fuente) = '" + criterios.getListaFuente().getNombre().toString().toUpperCase() +
 "'";

        }


        if (criterios.getNombre() != null &&
            criterios.getNombre().length() > 0) {
            sql +=
" and upper(nombre) like'%" + criterios.getNombre().toUpperCase() + "%'";
        }


        Query q = em.createNativeQuery(sql, PuntoMonitoreoTO.class);
        List lista = q.getResultList();


        return lista;
    }


    public List getPuntosMonitoreo(CriteriosBusquedaPuntos criterios,
                                   Long codigoAutoridad) throws IdeamException {

        String sql =
            "select * from calt_puntosMonitoreo_v where autoridad = ?1 ";

        if (criterios.getListatipoPunto() != null &&
            criterios.getListatipoPunto().getValor() != null) {
            sql +=
" and upper(tipo_punto) = '" + criterios.getListatipoPunto().getValor().toString().toUpperCase() +
 "'";
        }

        if (criterios.getArea() != null &&
            criterios.getArea().getId() != null) {
            sql +=
" and  upper(area) = '" + criterios.getArea().getValor().toString().toUpperCase() +
 "'";

        }

        if (criterios.getZona() != null &&
            criterios.getZona().getId() != null) {
            sql +=
" and upper(zona) = '" + criterios.getZona().getValor().toString().toUpperCase() +
 "'";

        }

        if (criterios.getSubzona() != null &&
            criterios.getSubzona().getId() != null) {
            sql +=
" and upper(subzona) = '" + criterios.getSubzona().getValor().toString().toUpperCase() +
 "'";

        }

        if (criterios.getListaFuente() != null &&
            criterios.getListaFuente().getNombre() != null) {
            sql +=
" and upper(fuente) = '" + criterios.getListaFuente().getNombre().toString().toUpperCase() +
 "'";

        }


        if (criterios.getNombre() != null &&
            criterios.getNombre().length() > 0) {
            sql +=
" and upper(nombre) like'%" + criterios.getNombre().toUpperCase() + "%'";
        }


        Query q = em.createNativeQuery(sql, PuntoMonitoreoTO.class);
        q.setParameter(1, codigoAutoridad);

        List lista = q.getResultList();

        return lista;
    }


    /**
     * Listado de puntos de monitoreo por tramos y criterios de busqueda
     * @param criterios y idtramo
     * @throws FenixException
     */
    public List getPuntosMonitoreoTramo(CriteriosBusquedaPuntos criterios,
                                        Long codigoTramo) throws IdeamException {
        String sql = "select * from calt_punto_monitoreo where id_tramo = ?1 ";
        int totalParametros = 2;
        if (criterios.getListatipoPunto() != null &&
            criterios.getListatipoPunto().getValor() != null) {
            sql += " and tipo_punto = ?" + totalParametros++;
        }

        if (criterios.getNombre() != null &&
            criterios.getNombre().length() > 0) {
            sql +=
" and upper(nombre) like'%" + criterios.getNombre().toUpperCase() + "%'";
        }

        Query q = em.createNativeQuery(sql, PuntoMonitoreo.class);
        q.setParameter(1, codigoTramo);
        totalParametros = 2;
        if (criterios.getListatipoPunto() != null &&
            criterios.getListatipoPunto().getValor() != null) {
            q.setParameter(totalParametros++,
                           criterios.getListatipoPunto().getCodigo());
        }

        List lista = q.getResultList();
        if (lista != null) {
            Iterator it = lista.iterator();
            while (it.hasNext()) {
                PuntoMonitoreo ptos = (PuntoMonitoreo)it.next();
                this.loadAttributes(ptos);
            }
        }

        return lista;
    }


    /**
     * Listado de puntos de monitoreo por vertimientos y criterios de busqueda
     * @param criterios y idtramo
     * @throws FenixException
     */
    public List getPuntosMonitoreoVert(CriteriosBusquedaPuntos criterios,
                                       Long codigoVert) throws IdeamException {
        String sql =
            "select * from calt_punto_monitoreo where id_Vertimiento = ?1 ";
        int totalParametros = 2;
        if (criterios.getListatipoPunto() != null &&
            criterios.getListatipoPunto().getValor() != null) {
            sql += " and tipo_punto = ?" + totalParametros++;
        }

        if (criterios.getNombre() != null &&
            criterios.getNombre().length() > 0) {
            sql +=
" and upper(nombre) like'%" + criterios.getNombre().toUpperCase() + "%'";
        }

        Query q = em.createNativeQuery(sql, PuntoMonitoreo.class);
        q.setParameter(1, codigoVert);
        totalParametros = 2;
        if (criterios.getListatipoPunto() != null &&
            criterios.getListatipoPunto().getValor() != null) {
            q.setParameter(totalParametros++,
                           criterios.getListatipoPunto().getCodigo());
        }

        List lista = q.getResultList();
        if (lista != null) {
            Iterator it = lista.iterator();
            while (it.hasNext()) {
                PuntoMonitoreo ptos = (PuntoMonitoreo)it.next();
                this.loadAttributes(ptos);
            }
        }

        return lista;
    }

    public void validarEntityManager(){
        try{
        if(em!=null&&em.isOpen()){
            System.out.println("Entity manager OK");
        }else{
            System.out.println("Entity manager FALLO");
        }
        }catch(Exception e){System.out.println("Error validarEntityManager:"+e);}
    }

    /**
     * Metodo empleado por el Nodo IDEAM para recibir puntos monitoreo de nodos
     * @param punto
     * @return
     * @throws IdeamException
     */

    public void updatePuntoMonitoreoPlano(PuntoMonitoreoPOJO puntom) throws IdeamException {
        validarEntityManager();
        PuntoMonitoreo pp=this.getPuntoMonitoreo(puntom.getId()); 
        if (pp==null) {
            System.out.println("PERSISTE PUNTO MONITOREO");
            em.persist(puntom);
        } else {
            System.out.println("MERGE PUNTO MONITOREO");
            puntom = em.merge(puntom);
        }
    }


    /**
     *Metodo empleado por el nodo cliente para guar puntos monitoreo
     * @param punto
     * @return
     * @throws IdeamException
     */

    public PuntoMonitoreo updatePuntoMonitoreo(PuntoMonitoreo puntoM) throws IdeamException {

        if (puntoM.getId() == null) {
            puntoM.setId(GeneradorSeq.obtenerSequencia(puntoM.getCodigoAutoridad(),
                                                       "seq_puntosmonitoreo",
                                                       em));
            em.persist(puntoM);
        } else {
            puntoM = em.merge(puntoM);
        }

        //generar el objeto plano a transmitir
        try {

            PuntoMonitoreoPOJO punto = new PuntoMonitoreoPOJO();
            punto.setId(puntoM.getId());
            punto.setCodigoAutoridad(puntoM.getCodigoAutoridad());
            punto.setNombre(puntoM.getNombre());
            punto.setAltitud(puntoM.getAltitud());

            punto.setIdDepartamento(puntoM.getIdDepartamento());

            punto.setIdmunicipio(punto.getIdmunicipio());

            punto.setId_tramo(puntoM.getIdTramo().getId());
            punto.setId_fuente(puntoM.getIdFuente().getId());
            punto.setId_area(puntoM.getIdArea().getId());
            punto.setId_zona(puntoM.getIdZona().getId());
            punto.setId_subzona(puntoM.getIdSubzona().getId());
            punto.setIdSist_ref(puntoM.getIdSist_ref());
            punto.setDescripcion_acceso(puntoM.getDescripcion_acceso());
            punto.setIdubicacion(puntoM.getIdubicacion());

            punto.setIdTipo_punto(puntoM.getIdTipo_punto());

            punto.setLatitud_grados(puntoM.getLatitud_grados());
            punto.setLatitud_minutos(puntoM.getLatitud_minutos());
            punto.setLatitud_segundos(puntoM.getLatitud_segundos());
            punto.setLongitud_grados(puntoM.getLongitud_grados());
            punto.setLongitud_minutos(puntoM.getLongitud_minutos());
            punto.setLongitud_segundos(puntoM.getLongitud_segundos());
            if (puntoM.getIdCaptacion() != null) {
                punto.setIdCaptacion(puntoM.getIdCaptacion().getCodigo());
            } else {
                punto.setIdCaptacion(null);
            }
            if (puntoM.getIdVertimiento() != null) {
                punto.setIdVertimiento(puntoM.getIdVertimiento().getId());
            } else {
                punto.setIdVertimiento(null);
            }


            //envío por cola
            registroRemoto.registrarEvento(this.getClass().getName(),
                                           "updatePuntoMonitoreoPlano", punto);
        } catch (Exception e) {
            System.out.println("XXXXXXXXXXXXXXXXX-edit punto--ERROR EN LA TRANSMISIÓN: " +
                               e.getMessage());
        }


        //continua el proceso

        return puntoM;


    }

    /**
     * Elimina de IDEAM un punto de monitoreo existente en la bd.
     * @param puntomonitoreo
     * @throws FenixException
     */
    public void deletePuntoMonitoreoPlano(PuntoMonitoreoPOJO puntom) throws IdeamException {


        try {

            if (puntom.getId() != null && puntom.getId().longValue() != 0) {
                em.flush();
                puntom = em.merge(puntom);
                em.remove(puntom);
            }
        } catch (Exception e) {
            System.out.println("XXXXXXXXXXXXXXXXX-mmm1--ERROR EN LA TRANSMISIÓN: " +
                               e.getMessage());
        }
    }


    /**
     * Elimina un punto de monitoreo existente en la bd.
     * @param puntomonitoreo
     * @throws FenixException
     */
    public void deletePuntoMonitoreo(PuntoMonitoreo puntoM) throws IdeamException {

        //System.out.println("Nombre punto original en LA TRANSMISIÓN: " +
        //puntoM.getNombre());
        PuntoMonitoreoPOJO punto = new PuntoMonitoreoPOJO();
        try {
            em.flush();
            puntoM = em.merge(puntoM);
            System.out.println("Nombre punto 1 en LA TRANSMISIÓN: " +
                               puntoM.getNombre());
            
            punto.setId(puntoM.getId());
            punto.setCodigoAutoridad(puntoM.getCodigoAutoridad());
            punto.setNombre(puntoM.getNombre());
            punto.setAltitud(puntoM.getAltitud());

            //System.out.println("Nombre punto 11 en LA TRANSMISIÓN: ");

            punto.setIdDepartamento(puntoM.getIdDepartamento());
            //System.out.println("Nombre punto 11 yy en LA TRANSMISIÓN: ");

            punto.setIdmunicipio(punto.getIdmunicipio());

            //System.out.println("Nombre punto 11rr en LA TRANSMISIÓN: ");

            punto.setId_tramo(puntoM.getIdTramo().getId());
            punto.setId_fuente(puntoM.getIdFuente().getId());
            punto.setId_area(puntoM.getIdArea().getId());
            punto.setId_zona(puntoM.getIdZona().getId());
            punto.setId_subzona(puntoM.getIdSubzona().getId());
            //System.out.println("Nombre punto 113 en LA TRANSMISIÓN: ");

            punto.setIdSist_ref(puntoM.getIdSist_ref());

            //System.out.println("Nombre punto 113 en LA TRANSMISIÓN: ");

            punto.setDescripcion_acceso(puntoM.getDescripcion_acceso());
            System.out.println("Nombre punto 113 des en LA TRANSMISIÓN: ");

            punto.setIdubicacion(puntoM.getIdubicacion());
            System.out.println("Nombre punto 113 ubi en LA TRANSMISIÓN: ");


            punto.setIdTipo_punto(puntoM.getIdTipo_punto());
            System.out.println("Nombre punto 115 en LA TRANSMISIÓN: ");

            punto.setLatitud_grados(puntoM.getLatitud_grados());
            punto.setLatitud_minutos(puntoM.getLatitud_minutos());
            punto.setLatitud_segundos(puntoM.getLatitud_segundos());
            punto.setLongitud_grados(puntoM.getLongitud_grados());
            punto.setLongitud_minutos(puntoM.getLongitud_minutos());
            punto.setLongitud_segundos(puntoM.getLongitud_segundos());


            if (puntoM.getIdCaptacion() != null) {
                System.out.println("Nombre punto 3 en LA TRANSMISIÓN: " +
                                   punto.getNombre());

                punto.setIdCaptacion(puntoM.getIdCaptacion().getCodigo());

            } else {
                System.out.println("Nombre punto  4en LA TRANSMISIÓN: " +
                                   punto.getNombre());

                punto.setIdCaptacion(null);
            }
            if (puntoM.getIdVertimiento() != null) {

                punto.setIdVertimiento(puntoM.getIdVertimiento().getId());

            } else {

                punto.setIdVertimiento(null);
            }
        } catch (Exception e) {
            System.out.println("error instanciando punto a borrar " +
                               e);
        } 
        try {
            em.remove(puntoM);
            System.out.println("Nombre punto  fff en LA TRANSMISIÓN: " +
                               punto.getNombre());
            //envío por cola
            registroRemoto.registrarEvento(this.getClass().getName(),
                                           "deletePuntoMonitoreoPlano", punto);
        } catch (Exception e) {
            System.out.println("XXXXXXXXXXXXXXXXERROR AL BORRAR PUNTO " +
                               e);
        } 
        
    }


    /**
     * Retorna el punto de monitoreo correspondiente al codigo recibido como parametro
     * @param codigo
     * @return
     * @throws IdeamException
     */
    public PuntoMonitoreo getPuntoMonitoreo(Long codigo) throws IdeamException {
       
       
        PuntoMonitoreo pm =null;
        try{
            Query query = em.createNamedQuery("PuntoMonitoreo.findById");
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ Codigo punto de monitoreo: " + codigo);
            query.setParameter("id", codigo);
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ Query: " + query.getSingleResult());
             pm = (PuntoMonitoreo)query.getSingleResult();
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ pm: " + pm);
            if(pm!=null)
                loadAttributes(pm);
      
        }catch(javax.ejb.EJBTransactionRolledbackException e){
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ excepcion: " + e);
            throw new IdeamException("Problemas! o existe un punto con los datos de búsquedad.");
        }catch(Exception e){
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ excepcion2: " + e);
            throw new IdeamException("Problemas! o existe resultado con los datos de búsquedad.");
        }finally{
            return pm;
        }
    }


    /**
     * Retorna el punto de monitoreo existente en base de datos
     * @param nombre, fuente y tramo
     * @return
     * @throws IdeamException
     */

    public PuntoMonitoreo getPuntoMonitoreoExiste(String nombre, Long idFuente,
                                                  Long idTramo) throws IdeamException {
        try {
            Query query = em.createNamedQuery("PuntoMonitoreo.findByParam");
            query.setParameter("nombre", nombre);
            query.setParameter("idFuente", idFuente);
            query.setParameter("idTramo", idTramo);
            PuntoMonitoreo pm = (PuntoMonitoreo)query.getSingleResult();
            loadAttributes(pm);
            return pm;
        } catch (NoResultException e) {
            return null;
        }
    }


    /**
     * Carga los atributos  de la clase PuntosMonitoreo
     * @param PuntosMonitoreo
     * @throws IdeamException
     */

    public PuntoMonitoreo loadLista(Integer idUbicacion) throws IdeamException {
        PuntoMonitoreo pm = new PuntoMonitoreo();
        if (idUbicacion != null) {
            Lista ubicacion = parametrosService.getLista(idUbicacion);
            pm.setUbicacion(ubicacion);
        }

        return pm;

    }

    /**
     * Carga los atributos  de la clase muestra
     * @param muestra
     * @throws IdeamException
     */

    public Muestra loadMuestra(Integer idtipoMuestra) throws IdeamException {
        Muestra mm = new Muestra();
        if (idtipoMuestra != null) {
            Lista tipom = parametrosService.getLista(idtipoMuestra);
            mm.setTipoMuestra(tipom);
        }

        return mm;

    }


    /**
     * Carga los atributos @Transient de la clase PuntosMonitoreo
     * @param PuntosMonitoreo
     * @throws IdeamException
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    private void loadAttributes(PuntoMonitoreo pm) throws IdeamException {
        Integer idTipoPunto = pm.getIdTipo_punto();
        if (idTipoPunto != null) {
            Lista tipoPunto = parametrosService.getLista(idTipoPunto);
            pm.setTipoPunto(tipoPunto);
        }

        Integer idUbicacion = pm.getIdubicacion();
        if (idUbicacion != null) {
            Lista ubicacion = parametrosService.getLista(idUbicacion);
            pm.setUbicacion(ubicacion);
        }

        Integer idSist_ref = pm.getIdSist_ref();
        if (idSist_ref != null) {
            Lista sistemaRef = parametrosService.getLista(idSist_ref);
            pm.setSistemaRef(sistemaRef);
        }


        Long idDpto = pm.getIdDepartamento();
        if (idDpto != null) {
            Divipola dpto = parametrosService.getDivipolaById(idDpto);
            pm.setDepartamento(dpto);
        }


        Long idmunicipio = pm.getIdmunicipio();
        if (idmunicipio != null) {
            Divipola municipio =
                parametrosService.getDivipolaById(idmunicipio);
            pm.setMunicipio(municipio);

        }


    }


    /**
     * Carga los atributos @Transient de la clase NormaLimites
     * @param NormaLimites
     * @throws IdeamException
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    private void loadAttributesNormaLimites(NormaLimites nl) throws IdeamException {
        Integer idparametro = nl.getIdParametro();
        if (idparametro != null) {
            Lista parametros = parametrosService.getLista(idparametro);
            nl.setListaParametros(parametros);
        }

        Integer idunidad = nl.getIdUnidad();
        if (idunidad != null) {
            Lista unidades = parametrosService.getLista(idunidad);
            nl.setListaUnidad(unidades);
        }

        Integer idsigno = nl.getIdSigno();
        String valor_param = null;
        if (idsigno != null) {
            Lista signo = parametrosService.getLista(idsigno);
            nl.setListaSignos(signo);


            if (idsigno == 582) {
                valor_param =
                        "(" + nl.getValor() + " - " + nl.getValor2() + ") ";

            } else if (idsigno == 583) {

                valor_param = " '" + nl.getValorChar() + "' ";


            } else {
                valor_param = nl.getValor().toString();
            }


            nl.setValor_parametro(valor_param);

        }


    }


    /**
     * Carga los atributos @Transient de la clase Muestras
     * @param Muestra
     * @throws IdeamException
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    private void loadAttributesMuestra(Muestra mm) throws IdeamException {
        Integer idLab = mm.getIdLaboratorio();
        if (idLab != null) {
            Lista laboratorio = parametrosService.getLista(idLab);
            mm.setLaboratorio(laboratorio);
        }

        Integer idTipoMuestra = mm.getIdTipoMuestra();
        if (idTipoMuestra != null) {
            Lista tipoMuestra = parametrosService.getLista(idTipoMuestra);
            mm.setTipoMuestra(tipoMuestra);
        }


    }


    /**
     * Carga los atributos @Transient de la clase Medicion
     * @param Medicion
     * @throws IdeamException
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    private void loadAttributesMedicion(Medicion med) throws IdeamException {
        Integer idParam = med.getIdParametro();
        if (idParam != null) {
            Lista parametro = parametrosService.getLista(idParam);
            med.setParametro(parametro);
        }

        Integer idsigno = med.getIdSigno();
        String valor_param = null;
        if (idsigno != null) {
            Lista signo = parametrosService.getLista(idsigno);
            med.setSigno(signo);

            if (idsigno == 582) {
                valor_param =
                        med.getSigno().getValor() + "(" + med.getValor() +
                        " - " + med.getValor2() + ") ";

            } else if (idsigno == 583) {

                valor_param =
                        med.getSigno().getValor() + " '" + med.getValorchar() +
                        "' ";


            } else {
                valor_param = med.getSigno().getValor() + " " + med.getValor();
            }


            med.setValor_parametro(valor_param);

        }

        Integer idund = med.getIdUnidad();
        if (idund != null) {
            Lista und = parametrosService.getLista(idund);
            med.setUnidad(und);
        }

        Integer idacreditado = med.getEsAcreditado();
        if (idacreditado != null) {

            if (idacreditado == 1) {
                med.setAcreditado(true);
            } else {
                med.setAcreditado(false);
            }


        }


    }

    /**
     * Retorna una lista de las muestras registradas en el sistema

     * @return
     * @throws IdeamException
     */
    public List<MuestraTO> getAllMuestras() throws IdeamException {
        Query query = em.createNamedQuery("MuestraTO.findAll");
        return query.getResultList();
    }


    public List<MuestraTO> getAllMuestras(Long codigoAutoridad) throws IdeamException {
        Query query = em.createNamedQuery("MuestraTO.findAutoridadAll");
        query.setParameter("codigoAutoridad", codigoAutoridad);
        List lista = query.getResultList();
        return lista;
    }


    /**
     * Retorna una lista con las muestras con los criterios de busqueda solicitados
     * recibida como parametro
     * @return
     * @throws IdeamException
     */

    public List getMuestras(CriteriosBusquedaPuntos criterios) throws IdeamException {
        boolean existenCriterios = false;
        String sql = "select * from calt_punto_monitoreo where ";
        int totalParametros = 1;
        if (criterios.getListatipoPunto() != null &&
            criterios.getListatipoPunto().getValor() != null) {
            if (existenCriterios) {
                sql += " and ";
            }
            existenCriterios = true;
            sql += " tipo_punto = ? " + totalParametros++;
        }

        if (criterios.getNombre() != null &&
            criterios.getNombre().length() > 0) {
            if (existenCriterios) {
                sql += " and ";
            }
            existenCriterios = true;
            sql +=
" upper(nombre) like '%" + criterios.getNombre().toUpperCase() + "%'";
        }

        Query q = em.createNativeQuery(sql, PuntoMonitoreo.class);

        totalParametros = 1;
        if (criterios.getListatipoPunto() != null &&
            criterios.getListatipoPunto().getValor() != null) {
            q.setParameter(totalParametros++,
                           criterios.getListatipoPunto().getCodigo());
        }

        List lista = q.getResultList();
        if (lista != null) {
            Iterator it = lista.iterator();
            while (it.hasNext()) {
                PuntoMonitoreo ptos = (PuntoMonitoreo)it.next();
                this.loadAttributes(ptos);
            }
        }
        return lista;
    }


    public List getMuestrasPunto(CriteriosBusquedaPuntos criterios,
                                 Long idPunto) throws IdeamException {

        String sql = "select * from calt_muestra where id_punto = ?1 ";
        int totalParametros = 2;

        if (criterios.getTipoMuestra() != null &&
            criterios.getTipoMuestra().getValor() != null) {
            sql += " and tipo_muestra = ?" + totalParametros++;
        }

        if (criterios.getFechaMuestra() != null &&
            criterios.getFechaMuestra().length() > 0) {
            sql +=
" and  fecha_muestreo = '" + criterios.getFechaMuestra().toString() + "'";
        }
        //System.out.println("sql------------------1-----------" + sql);
        Query q = em.createNativeQuery(sql, Muestra.class);
        q.setParameter(1, idPunto);
        totalParametros = 2;
        if (criterios.getTipoMuestra() != null &&
            criterios.getTipoMuestra().getValor() != null) {
            q.setParameter(totalParametros++,
                           criterios.getTipoMuestra().getCodigo());
        }


        List lista = q.getResultList();
        if (lista != null) {
            Iterator it = lista.iterator();
            while (it.hasNext()) {
                Muestra mm = (Muestra)it.next();
                this.loadAttributesMuestra(mm);
            }
        }

        return lista;
    }


    public List getMuestras(CriteriosBusquedaPuntos criterios,
                            Long codigoAutoridad) throws IdeamException {

        //System.out.println(" FECHA----entro 11------------------");

        String sql = "select * from calt_muestra where id_autoridad = ?1 ";
        int totalParametros = 3;

        if (criterios.getTipoMuestra() != null &&
            criterios.getTipoMuestra().getValor() != null) {
            sql += " and tipo_muestra = ?" + totalParametros++;
        }

        if (criterios.getFechaMuestra() != null &&
            criterios.getFechaMuestra().length() > 0) {
            sql +=
" and  fecha_muestreo = '" + criterios.getFechaMuestra().toString() + "'";
        }
        //System.out.println("sql------------------2-----------" + sql);
        if (criterios.getNroMuestra() != null &&
            criterios.getNroMuestra().length() > 0) {
            sql +=
" and  upper(nro_muestra) like '%" + criterios.getNroMuestra().toString().toUpperCase() +
 "%'";

        }


        if (criterios.getListapuntos() != null &&
            criterios.getListapuntos().getId() != null) {
            sql += " and id_punto = ?" + totalParametros++;
        }


        Query q = em.createNativeQuery(sql, Muestra.class);
        q.setParameter(1, codigoAutoridad);
        totalParametros = 3;
        if (criterios.getTipoMuestra() != null &&
            criterios.getTipoMuestra().getValor() != null) {
            q.setParameter(totalParametros++,
                           criterios.getTipoMuestra().getCodigo());
        }

        if (criterios.getListapuntos() != null &&
            criterios.getListapuntos().getId() != null) {
            q.setParameter(totalParametros++,
                           criterios.getListapuntos().getId());
        }

        List lista = q.getResultList();
        if (lista != null) {
            Iterator it = lista.iterator();
            while (it.hasNext()) {
                Muestra mm = (Muestra)it.next();
                this.loadAttributesMuestra(mm);
            }
        }

        return lista;
    }


    public List getMuestrasVista(CriteriosBusquedaPuntos criterios,
                                 Long codigoAutoridad) throws IdeamException {

        //System.out.println(" FECHA----entro 1------------------");

        String sql = "select * from calt_muestras_v where autoridad = ?1 ";

        if (criterios.getTipoMuestra() != null &&
            criterios.getTipoMuestra().getValor() != null) {
            sql +=
" and upper(tipo_muestra) = '" + criterios.getTipoMuestra().getValor().toString().toUpperCase() +
 "'";
        }

        if (criterios.getFechaMuestra() != null &&
            criterios.getFechaMuestra().length() > 0) {
            sql +=
" and  fecha_muestreo = '" + criterios.getFechaMuestra().toString() + "'";
        }
        //System.out.println("sql------------------13-----------" + sql);
        if (criterios.getNroMuestra() != null &&
            criterios.getNroMuestra().length() > 0) {
            sql +=
" and  upper(nro_muestra) like '%" + criterios.getNroMuestra().toString().toUpperCase() +
 "%'";

        }


        if (criterios.getListapuntos() != null &&
            criterios.getListapuntos().getNombre() != null) {
            sql +=
" and upper(nombre_punto) =  '" + criterios.getListapuntos().getNombre().toString().toUpperCase() +
 "'";
        }

       
        Query q = em.createNativeQuery(sql, MuestraTO.class);
        q.setParameter(1, codigoAutoridad);


        List lista = q.getResultList();
        return lista;
    }


    public List getMuestrasVista(CriteriosBusquedaPuntos criterios) throws IdeamException {

        //System.out.println(" FECHA----entro 1-3-----------------");

        String sql = "select * from calt_muestras_v  where ";

        if (criterios.getTipoMuestra() != null &&
            criterios.getTipoMuestra().getValor() != null) {
            sql +=
" upper(tipo_muestra) = '" + criterios.getTipoMuestra().getValor().toString().toUpperCase() +
 "'";
        }

        if (criterios.getFechaMuestra() != null &&
            criterios.getFechaMuestra().length() > 0) {
            sql +=
" and fecha_muestreo = '" + criterios.getFechaMuestra().toString() + "'";
        }
        //System.out.println("sql------------------1-4----------" + sql);
        if (criterios.getNroMuestra() != null &&
            criterios.getNroMuestra().length() > 0) {
            sql +=
" and  upper(nro_muestra) like '%" + criterios.getNroMuestra().toString().toUpperCase() +
 "%'";

        }


        if (criterios.getListapuntos() != null &&
            criterios.getListapuntos().getNombre() != null) {
            sql +=
" and upper(nombre_punto) =  '" + criterios.getListapuntos().getNombre().toString().toUpperCase() +
 "'";
        }


        Query q = em.createNativeQuery(sql, MuestraTO.class);

        List lista = q.getResultList();
        return lista;
    }


    /**
     * Retorna las muestras de un punto de monitoreo
     * @param PuntoMonitoreo
     * @return
     * @throws IdeamException
     */

    public List<Muestra> getMuestras(PuntoMonitoreo pm) throws IdeamException {
        Query query = em.createNamedQuery("Muestra.findPunto");
        //System.out.println("----------getMuestras(PuntoMonitoreo pm) para punto:"+pm.getId()+"-------------");
        query.setParameter("idPunto", pm.getId());
        List lista = query.getResultList();
        if (lista != null) {
            Iterator it = lista.iterator();
            while (it.hasNext()) {
                Muestra mm = (Muestra)it.next();
                this.loadAttributesMuestra(mm);
            }
        }
        return lista;
    }


    /**
     * Metodo empleado por el Nodo IDEAM para recibir medicion de nodos
     * @param punto
     * @return
     * @throws IdeamException
     */

    public void updateMuestraPlano(MuestrasPOJO mm) throws IdeamException {
        validarEntityManager();
        Muestra mu=this.getMuestra(mm.getId());
        if (mm.getId() == null || mm.getId().longValue() == 0) {
            System.out.println("PERSISTE mm");
            em.persist(mm);
        } else {
            System.out.println("MERGE mm");
            mm = em.merge(mm);
        }
    }

    /**
     * Inserta o actualiza la informacion de las muestras de un punto de monitoreo en el sistema
     * @param
     * @throws IdeamxException
     */

    public Muestra updateMuestra(Muestra mm) throws IdeamException {
        if (mm.getId() == null) {
            mm.setId(GeneradorSeq.obtenerSequencia(mm.getCodigoAutoridad(),
                                                   "seq_muestras", em));
            em.persist(mm);
        } else {
            em.merge(mm);
        }

        //generar el objeto plano a transmitir
        try {
            MuestrasPOJO muestra = new MuestrasPOJO();
            muestra.setId(mm.getId());
            muestra.setNro_muestra(mm.getNro_muestra());
            muestra.setIdPuntoMonitoreo(mm.getIdPuntoMonitoreo().getId());
            muestra.setIdLaboratorio(mm.getIdLaboratorio());
            if (muestra.getIdLaboratorio().equals(ConstantesCalidad.OTRO_LAB)) {
                muestra.setOtroLaboratorio(mm.getOtroLaboratorio());
            }

            muestra.setIdTipoMuestra(mm.getIdTipoMuestra());
            muestra.setCodigoAutoridad(mm.getCodigoAutoridad());
            if (mm.getNroSubmuestras() != null) {
                muestra.setNroSubmuestras(mm.getNroSubmuestras());
            }
            if (mm.getIca() != null) {
                muestra.setIca(mm.getIca());
            }
            if (mm.getDuracion() != null) {
                muestra.setDuracion(mm.getDuracion());
            }
            if (mm.getIntervaloTiempo() != null) {
                muestra.setIntervaloTiempo(mm.getIntervaloTiempo());
            }
            if (mm.getNroVerticales() != null) {
                muestra.setNroVerticales(mm.getNroVerticales());
            }
            muestra.setCaudal(mm.getCaudal());
            muestra.setFechaMuestreo(mm.getFechaMuestreo());
            muestra.setHoraMuestreo(mm.getHoraMuestreo());
            muestra.setHorario(mm.getHorario());
            muestra.setMinMuestreo(mm.getMinMuestreo());
            //envío por cola
            registroRemoto.registrarEvento(this.getClass().getName(),
                                           "updateMuestraPlano", muestra);
        } catch (Exception e) {
            System.out.println("XXXXXXXXXXXXXXXXX---ERROR EN LA TRANSMISIÓN: " +
                               e.getMessage());
        }


        //continua el proceso
        return mm;
    }


    /**
     * Retorna la muestra correspondiente al codigo recibido como parametro
     * @param id
     * @return
     * @throws IdeamException
     */
    public Muestra getMuestra(Long id) throws IdeamException {
        Muestra mm =null;
        try{
        Query query = em.createNamedQuery("Muestra.findById");
        query.setParameter("id", id);
        mm = (Muestra)query.getSingleResult();
        if (mm != null)
            loadAttributesMuestra(mm);
        }catch(javax.ejb.EJBTransactionRolledbackException e){
            throw new IdeamException("Problemas! o existe un tramo con los datos de búsquedad.");
        }catch(Exception e){
            throw new IdeamException("Problemas! o existe resultado con los datos de búsquedad.");
        }finally{
            return mm;
        }
    }


    /**
     * Retorna la muestra correspondiente a los parametros
     * @param id
     * @return
     * @throws IdeamException
     */
    public Muestra getMuestraExiste(Long idPunto, Integer tipoMuestra,
                                    Integer hora, Integer min, String horario,
                                    Integer laboratorio,
                                    String fecha) throws IdeamException {
        try {


            String fech = fecha.toString();
            if (fech.length() < 8) {
                fech = "0"  + fech;
            } else {
                fech = fech.toString();
            }


            String sql =
                "select * from calt_muestra where id_punto =" + idPunto +
                " and tipo_muestra=" + tipoMuestra + " and hora_muestreo=" +
                hora + " and min_muestreo=" + min + " and id_laboratorio=" +
                laboratorio;

            sql +=
" and upper(horario) ='" + horario.toString().toUpperCase() + "'";
            sql +=
" and  fecha_muestreo =to_date('" + fech.toString() + "','DD/MM/YYYY')";

           

            Query q = em.createNativeQuery(sql, Muestra.class);


            Muestra mm = (Muestra)q.getSingleResult();
            loadAttributesMuestra(mm);

            
            return mm;


        } catch (NoResultException e) {
            return null;
        }
    }


    /**
     * Metodo empleado por el Nodo IDEAM para recibir medicion de nodos
     * @param punto
     * @return
     * @throws IdeamException
     */

    public void updateMedicionPlano(MedicionPOJO medic) throws IdeamException {
        validarEntityManager();
        Medicion med = this.getMedicion(medic.getId());
        if(med==null) {
            System.out.println("PERSISTE MED");
            em.persist(medic);
        } else {
            System.out.println("MERGE MED");
            medic = em.merge(medic);
        }
        calcularIcaMuestra (medic.getIdMuestra());
    }


    /**
     * Inserta o actualiza la informacion de las mediciones de las muestras de un punto de monitoreo en el sistema
     * @param
     * @throws IdeamxException
     */

    public void updateMedicion(Medicion med) throws IdeamException {
        String error = "";
        try {

            if (med.getId() == null) {
                med.setId(GeneradorSeq.obtenerSequencia(med.getCodigoAutoridad(),
                                                        "seq_medicion", em));
                em.persist(med);
            } else {
                em.merge(med);
            }
            System.out.println("--------------------PROCEDE A CALCULAR EL ICA-------------------");
            calcularIcaMuestra (med.getIdMuestra());
        } catch (Exception e) {


            error = error + "--- " + med.getIdMuestra();

            System.out.println("XXXXXXXXXXXXXXXXX---ERROR EN LA IMPORTACION DATOS: " +
                               error);
        }


        //generar el objeto plano a transmitir
        try {
            MedicionPOJO medic = new MedicionPOJO();
            medic.setId(med.getId());
            medic.setIdMuestra(med.getIdMuestra().getId());
            medic.setIdParametro(med.getIdParametro());
            medic.setIdUnidad(med.getIdUnidad());
            medic.setIdSigno(med.getIdSigno());
            medic.setEsAcreditado(med.getEsAcreditado());

            if (med.getValor() != null) {
                medic.setValor(med.getValor());
            }
            if (med.getValor2() != null) {
                medic.setValor2(med.getValor2());
            }

            if (med.getValorchar() != null) {
                medic.setValorchar(med.getValorchar());
            }

            medic.setLimiteDeteccion(med.getLimiteDeteccion());
            medic.setMetodoDeterminacion(med.getMetodoDeterminacion());


            //envío por cola
            registroRemoto.registrarEvento(this.getClass().getName(),
                                           "updateMedicionPlano", medic);
        } catch (Exception e) {
            System.out.println("XXXXXXXXXXXXXXXXX---ERROR EN LA TRANSMISIÓN: " +
                               e.getMessage());
        }


    }
	
    private List<Medicion> getMedicionesIdeam(Long codigoMuestra)throws IdeamException{        
        String sql = "select supacodigoparametro, parametro, ROUND(AVG(valor),2) as valor from sirhv_muestras_fq " + 
                     "where codigomuestra = ?1 "+
                     "group by supacodigoparametro, parametro " +
                     "order by supacodigoparametro";
        Query query = em.createNativeQuery(sql);
        query.setParameter(1, codigoMuestra);
        List<Object[]> mediciones = query.getResultList();
        List<Medicion> lista = new ArrayList<Medicion>();
        Iterator it = mediciones.iterator();
        while (it.hasNext()) {
            Object[] datos = (Object[])it.next();
            Medicion med = new Medicion();
            med.setIdParametro( ((BigDecimal)datos[0]).intValue() );
            med.setValor( ((BigDecimal)datos[2]).doubleValue());            
            lista.add(med);
        }        
        return lista;
    }
    
    /**
     * Realiza el calculo de ICA 5 6 y 7 para la muestra IDEAM correspondiente al codigo
     * recibido como parametro.
     * @param codigoMuestra
     * @return
     * @throws IdeamException
     */    
    public boolean calcularIcaMuestraIdeam ( Long codigoMuestra ) throws IdeamException {
        System.out.println("procesando muestra : "+ codigoMuestra +"-------------------");             
        // verificar si la muestra tiene los parametros requeridos
        List<Medicion> mediciones = getMedicionesIdeam(codigoMuestra); 

        
        // Si no hay mediciones no es posible calcular ICA        
        if (mediciones == null) 
            return false;
        // Si el numero de mediciones es suficiente
        if (mediciones.size() < 5 ){            
            System.out.println("-------------------MEDICIONES DEBEN SER MINIMO 5, NO CALCULA EL ICA-------------------");
            return false;
        }    
        // Validar parametros requeridos
        int numParReqIca5 = 0;
        int numParReqIca6 = 0;
        boolean estaTemperatura = false;
        boolean esPosibleIca7 = false;


        double temperatura = 0;
        double altitudPunto = 0;
        double oxigenoDisuelto = 0;
        double solidosSuspendidos = 0;
        double demandaOxigeno = 0;
        double nitrogenoTotal = 0;
        double conductividadElectrica =0;
        double fosforoTotal = 0;
        double coliformes = 0;
        
        double ph = 0;

        Iterator it = mediciones.iterator();
        
        while (it.hasNext()) {
            Medicion med = (Medicion)it.next();
            //loadAttributesMedicion(med);   
            if (med.getIdParametro().compareTo(Constantes.TEMPERATURA_IDEAM) == 0) {
                temperatura = med.getValor();
                if(temperatura!=0){
                    estaTemperatura = true;                   
                }
            }else if (med.getIdParametro().compareTo(Constantes.OXIGENO_DISULETO_IDEAM) == 0 ) {
                oxigenoDisuelto = med.getValor();
                if(oxigenoDisuelto!=0){
                    numParReqIca5++;  
                }
            }else if (med.getIdParametro().compareTo( Constantes.SOLIDOS_SUSPENDIDOS_IDEAM) == 0) {
                solidosSuspendidos = med.getValor();
                if(solidosSuspendidos!=0){
                    numParReqIca5++;                
                }
            }else if (med.getIdParametro().compareTo(Constantes.DEMANDA_OXIGENO_IDEAM) == 0       ) {
                demandaOxigeno  = med.getValor();
                if(demandaOxigeno!=0){
                    numParReqIca5++;           
                }
            }else if (med.getIdParametro().compareTo(Constantes.CONDUCTIVIDAD_ELECTRICA_IDEAM) == 0) {
                conductividadElectrica =  med.getValor();
                if(conductividadElectrica!=0){
                    numParReqIca5++;
                }
            }else if (med.getIdParametro().compareTo(Constantes.PH_IDEAM) == 0) {
                ph  = med.getValor();
                if(ph!=0){
                    numParReqIca5++;    
                }
            }else if (med.getIdParametro().compareTo(Constantes.NITROGENO_TOTAL_IDEAM) == 0) {
                nitrogenoTotal =  med.getValor();
                if(nitrogenoTotal!=0){
                    numParReqIca6++;                
                }
            }else if (med.getIdParametro().compareTo(Constantes.FOSFORO_TOTAL_IDEAM)== 0) {
                fosforoTotal = med.getValor();
                if(fosforoTotal!=0){
                    numParReqIca6++;
                }
            }else if (med.getIdParametro().compareTo(Constantes.COLIFORMES_IDEAM) == 0) {
                coliformes = med.getValor();
                if(coliformes!=0){
                    esPosibleIca7 = true;                
                }
            }
            
        }
        
        // Si no esta temperatura no es posible ICA
        if (!estaTemperatura){
            System.out.println("-------------------NO EXISTE TEMPERATURA, NO CALCULA EL ICA-------------------");      
            return false;
        }
        // valida cantidad de parametros
        if (numParReqIca5 < 5){
            System.out.println("-------------------NO EXISTE LOS 5 PARAMETROS REQUERIDOS, NO CALCULA EL ICA5-------------------"+numParReqIca5);
            return false;
        }


        System.out.println("------------------- CALCULA EL ICA5-------------------");      
        MuestrasIca muestraIca = new MuestrasIca ();
        muestraIca.setCodigoMuestra( codigoMuestra );    

            
        // Calcular ICA 5

        altitudPunto = getAltitudPuntoMonitoreoIdeam(codigoMuestra).getAltitud();
         
        double q2 = 273.15 + temperatura;
        double p2 = 0.000975-(1.426 * Math.pow(10,-5)*temperatura)+(6.436*Math.pow (10,-8) * Math.pow (temperatura,2));
        double o2 = Math.exp( 11.8571 - (3840.7/q2) - (216961.0 / Math.pow (q2,2)));
        double n2 = 1.0 - (0.02667 * altitudPunto /0.3048/760.0);
        double m2 = Math.exp((-139.3441+(157570.1/q2)-(66423080.0/Math.pow (q2,2) )+(12438000000.0/Math.pow (q2,3))-(862194900000.0/Math.pow (q2,4) )));            
        double l2 = m2*n2*(((1-o2/n2)*(1-p2*n2))/((1-o2)*(1-p2)));
        double k2 = (oxigenoDisuelto * 100)/l2;
                    
      // calcular valores variables
        double h2 = (k2>100) ? 1-(0.01*k2-1) : 1-(1-0.01*k2);
        double h3 = (solidosSuspendidos<=4.5)? 1: (solidosSuspendidos>=320) ? 0 : (1-(-0.02+0.003*solidosSuspendidos));
        double h4 = (demandaOxigeno<=20) ? 0.91 : (20<demandaOxigeno && demandaOxigeno<=25) ? 0.71 : ( 25<demandaOxigeno && demandaOxigeno<=40) ? 0.51 : (40<demandaOxigeno && demandaOxigeno<=80) ? 0.26 : (demandaOxigeno>80) ? 0.125: 0;
        double h7 = (1- Math.pow (10, (-3.26+ 1.34* Math.log10(conductividadElectrica) ))<0)? 0 : 1-Math.pow(10,(-3.26+1.34*Math.log10(conductividadElectrica) ));
        double h9 = (ph<4) ? 0.1 : ((4<=ph) && (ph<=7) ) ? 0.02628419*Math.exp(ph*0.520025) : ( (7 <ph) && (ph<=8) ) ? 1 : ( (8<ph) && (ph<=11) ) ? 1* Math.exp( (ph-8)*-0.5187742 )  : (ph>11) ? 0.1 : 0;
   
        double ica = ( h2 * 0.2 ) + (h3 * 0.2) + (h4 * 0.2) + (h7 * 0.2) +  (h9 *0.2) ;   
System.out.println("ICA5 Variables:"+ica+ " PSOD: " + k2 );             
        muestraIca.setPresionAtm(n2);
        muestraIca.setPw(o2);
        muestraIca.setTeta(p2);
        muestraIca.setSod(l2);
        muestraIca.setPsod(k2);
        muestraIca.setIpsod(h2);
        muestraIca.setIsst(h3);
        muestraIca.setIdqo(h4);
        muestraIca.setIcond(h7);
        muestraIca.setIph(h9);
        muestraIca.setIca5(ica);            
  
        if(numParReqIca5 == 5 && numParReqIca6 == 2){
            // calcular ica  6
           double h5 = ((nitrogenoTotal/fosforoTotal) >= 15 )  ? 0.8: ( ((nitrogenoTotal/fosforoTotal) > 10) && ((nitrogenoTotal/fosforoTotal)< 15)) ? 0.6 : ( ((nitrogenoTotal/fosforoTotal) > 5) &&  ((nitrogenoTotal/fosforoTotal) <=10 )) ? 0.35 : ( (nitrogenoTotal/fosforoTotal)<=5 ) ? 0.15 : 0;
           ica = ( h2 * 0.17 ) + (h3 * 0.17) + (h4 * 0.17) + (h5* 0.17) + (h7 * 0.17) +  (h9 * 0.15);
           
System.out.println("ICA6 Variables: "+ ica);             
           muestraIca.setInp(h5);            
           muestraIca.setIca6(ica);                         
           
           if (esPosibleIca7){
                // calcular ica  7                                                                                    
                    double h8 = (coliformes < 50) ? 0.98 : (50<=coliformes && coliformes <1600) ? 0.98* Math.exp((coliformes-50)*-0.0009917754) : (coliformes >=1600) ? 0.1 : 0;                
                    ica = ( h2 * 0.16 ) + (h3 * 0.14) + (h4 * 0.14) + (h5* 0.14) + (h7 * 0.14) + (h9 * 0.14 )  + (h8 * 0.14);
                    muestraIca.setIecoli(h8);                               
                          
System.out.println("ICA7:"+ica);             
                muestraIca.setIca7(ica);            
            }
          } 
        
        if(muestraIca!=null){
            try{
                boolean valido = muestraIca.isValid();
                if(valido){
                    em.merge(muestraIca);
                    return true;
                }else{
                    System.out.println("Datos no validos para la muestra " + codigoMuestra);
                }
            }catch(PersistenceException e){
                System.out.println("Error procesando ICA muestra " + codigoMuestra + " " + e.getMessage());
            }
        }
        return false;
    }
    
    public SirhvPuntoMonitoreoFq getAltitudPuntoMonitoreoIdeam(Long codigoMuestra) throws IdeamException {
        String sql = SirhvPuntoMonitoreoFq.altitudPuntoMonitoreoIdeam;
        Query q = em.createNativeQuery(sql, SirhvPuntoMonitoreoFq.class);
        q.setParameter(1, codigoMuestra);
    System.out.println("getAltitud = "+sql);        
        q.setMaxResults(1);        
        return (SirhvPuntoMonitoreoFq)q.getSingleResult();

    }    

    /**
     * Realiza una actualizacion masiva de todas las muestras del Ideam para las
     * cuales no se ha calculado el valor del ICA.
     * @throws IdeamException
     */    
    public Boolean actualizarIcaIdeamMasivo()throws IdeamException{
        // Seleccionar todas las muestras de >= 5 parametros !=0 y que no tienen un valor de ICA5       
        String sql = "Select mm.codigomuestra   " + 
                    "From (select Distinct codigomuestra, estacionid from sirhv_muestras_fq Order by codigomuestra) mm, " + 
                    "      sirhv_punto_monitoreo_fq pm, " + 
                    "     (select codigomuestra, supacodigoparametro from sirhv_muestras_fq " + 
                    "     where supacodigoparametro = 4 and valor !=0" + 
                    "     group by codigomuestra, supacodigoparametro Order by codigomuestra) mp1, " + 
                    "     (select codigomuestra, supacodigoparametro from sirhv_muestras_fq" + 
                    "     where supacodigoparametro = 37 and valor !=0" + 
                    "     group by codigomuestra, supacodigoparametro Order by codigomuestra) mp2, " + 
                    "     (select codigomuestra, supacodigoparametro from sirhv_muestras_fq" + 
                    "     where supacodigoparametro = 44 and valor !=0" + 
                    "     group by codigomuestra, supacodigoparametro Order by codigomuestra) mp3, " + 
                    "     (select codigomuestra, supacodigoparametro from sirhv_muestras_fq" + 
                    "     where supacodigoparametro = 39 and valor !=0" + 
                    "     group by codigomuestra, supacodigoparametro Order by codigomuestra) mp4, " + 
                    "     (select codigomuestra, supacodigoparametro from sirhv_muestras_fq" + 
                    "     where supacodigoparametro = 20 and valor !=0" + 
                    "     group by codigomuestra, supacodigoparametro Order by codigomuestra) mp5, " + 
                    "     (select codigomuestra, supacodigoparametro from sirhv_muestras_fq" + 
                    "     where supacodigoparametro = 5 and valor !=0" + 
                    "     group by codigomuestra, supacodigoparametro Order by codigomuestra) mp6 " + 
                    "Where pm.id = mm.estacionid and mm.codigomuestra not in (select codigo_muestra from calt_muestras_ica_fq )  " + 
                    "     and mm.codigomuestra = mp1.codigomuestra and mm.codigomuestra = mp2.codigomuestra and mm.codigomuestra = mp3.codigomuestra " +
                    "     and mm.codigomuestra = mp4.codigomuestra and mm.codigomuestra = mp5.codigomuestra and mm.codigomuestra = mp6.codigomuestra " +
                    "Order by mm.codigomuestra" ;
        Query query = em.createNativeQuery(sql);
        List muestras = query.getResultList();
        Iterator it = muestras.iterator();
        Integer n = 0;
        while(it.hasNext()){
            Long codigoMuestra = ((BigDecimal)it.next()).longValue();  
            if(n == 400){ return true;}
            boolean calculado = this.calcularIcaMuestraIdeam(codigoMuestra);
            System.out.println("CALCULO_ICA Muestra = "+codigoMuestra.toString());
           n++;
        }
        System.out.println("ICA 5, 6 y 7 para Muestras del IDEAM actualizados...");  
        return true;
    }
    
    /**
         * Retorna los parametros de ICA calculados para la muestra recibida como
         * parametro.
         * @param codigoMuestra
         * @return
         * @throws IdeamException
         */
    public MuestrasIca getVariablesIcaMuestraIdeam(Long codigoMuestra)throws IdeamException{
        MuestrasIca ica = getMuestraIca(codigoMuestra);
        if(ica == null){
            boolean calculado = calcularIcaMuestraIdeam(codigoMuestra);
            if(calculado){
                ica = getMuestraIca(codigoMuestra);
            }
        }
        return ica;
    }
    
    /**
     * Retorna los parametros de ICA calculados para la muestra recibida como
     * parametro.
     * @param codigoMuestra
     * @return
     * @throws IdeamException
     */
    public MuestrasIca getMuestraIca(Long codigoMuestra)throws IdeamException{        
        try{
            Query query = em.createNamedQuery("MuestrasIca.findByMuestra");
            query.setParameter("codigo", codigoMuestra);
            query.setMaxResults(1);           
            return (MuestrasIca)query.getSingleResult();
        }catch(NoResultException e){
            return null;            
        }
    }

    private void calcularIcaMuestra ( Muestra muestra) throws IdeamException {
        // verificar si la muestra tiene los parametros requeridos
             List<Medicion> mediciones = getMediciones(muestra); 
             
             // Si no hay mediciones no es posible calcular ICA        
             if (mediciones == null) 
                 return;

             // Si el numero de mediciones es suficiente        
             if (mediciones.size() < 7){
                 System.out.println("-------------------NO CUENTA AL MENOS 7 MEDICIONES, NO CALCULA EL ICA-------------------");
                 return;
             }    
             // Validar parametros requeridos
             int numParReq = 0;
             boolean estaTemperatura = false;
             boolean posibleIca7 = false;

             double temperatura = 0;
             double altitudPunto = 0;
             double oxigenoDisuelto = 0;
             double solidosSuspendidos = 0;
             double demandaOxigeno = 0;
             double nitrogenoTotal = 0;
             double conductividadElectrica =0;
             double fosforoTotal = 0;
             double coliformes = 0;
             

             double ph = 7;

             Iterator it = mediciones.iterator();
             
             while (it.hasNext()) {
                 Medicion med = (Medicion)it.next();
                 loadAttributesMedicion(med);
                 System.out.println("----------------getIdParametro:"+med.getIdParametro());
                 if (med.getIdParametro().compareTo(Constantes.OXIGENO_DISULETO) == 0 ) {
                     oxigenoDisuelto = med.getValor();
                     numParReq++;
                 }else if (med.getIdParametro().compareTo( Constantes.SOLIDOS_SUSPENDIDOS) == 0) {
                     solidosSuspendidos = med.getValor();
                     numParReq++;
                 }else if (med.getIdParametro().compareTo(Constantes.DEMANDA_OXIGENO) == 0       ) {
                     demandaOxigeno  = med.getValor();
                     numParReq++;
                 }else if (med.getIdParametro().compareTo(Constantes.NITROGENO_TOTAL) == 0) {
                     nitrogenoTotal =  med.getValor();
                     numParReq++;
                 }else if (med.getIdParametro().compareTo(Constantes.CONDUCTIVIDAD_ELECTRICA) == 0) {
                     conductividadElectrica =  med.getValor();
                     numParReq++;
                 }else if (med.getIdParametro().compareTo(Constantes.FOSFORO_TOTAL)== 0) {
                     fosforoTotal = med.getValor();
                     numParReq++;
                 }else if (med.getIdParametro().compareTo(Constantes.COLIFORMES) == 0) {
                     coliformes  = med.getValor();
                     posibleIca7 = true;
                 }else if (med.getIdParametro().compareTo(Constantes.TEMPERATURA) == 0) {
                     temperatura  = med.getValor();
                     estaTemperatura = true;

                 }else if (med.getIdParametro().compareTo(Constantes.PH) == 0) {
                     ph  = med.getValor();
                 }
                 
             }

             
             altitudPunto = muestra.getIdPuntoMonitoreo().getAltitud();
             
             // Si no esta temperatura no es posible ICA
             if (!estaTemperatura){
                 System.out.println("-------------------NO CUENTA TEMPERATURA, NO CALCULA EL ICA-------------------"+numParReq);      
                 return;
             }
             // valida cantidade parametros
             if (numParReq < 6){
                 System.out.println("-------------------NO CUENTA CON EL NUM. DE PARAMETROS REQUERIDOS, NO CALCULA EL ICA-------------------"+numParReq);
                 return;
             }

             System.out.println("------------------- CALCULA EL ICA-------------------");      

             // calcular valores intermedios
             double q2 = 273.15 + temperatura;
             double p2 = 0.000975-(1.426 * Math.pow(10,-5)*temperatura)+(6.436*Math.pow (10,-8) * Math.pow (temperatura,2));
             double o2 = Math.exp( 11.8571 - (3840.7/q2) - (216961.0 / Math.pow (q2,2)));
             double n2 = 1.0 - (0.02667 * altitudPunto /0.3048/760.0);
             double m2 = Math.exp((-139.3441+(157570.1/q2)-(66423080.0/Math.pow (q2,2) )+(12438000000.0/Math.pow (q2,3))-(862194900000.0/Math.pow (q2,4) )));
             double l2 = m2*n2*(((1-o2/n2)*(1-p2*n2))/((1-o2)*(1-p2)));
             double k2 = (oxigenoDisuelto * 100)/l2;
             
             // calcular valores variables
             double h2 = (k2>100) ? 1-(0.01*k2-1) : 1-(1-0.01*k2);
             double h3 = (solidosSuspendidos<=4.5)? 1: (solidosSuspendidos>=320) ? 0 : (1-(-0.02+0.003*solidosSuspendidos));
             double h4 = (demandaOxigeno<=20) ? 0.91 : (20<demandaOxigeno && demandaOxigeno<=25) ? 0.71 : ( 25<demandaOxigeno && demandaOxigeno<=40) ? 0.51 : (40<demandaOxigeno && demandaOxigeno<=80) ? 0.26 : (demandaOxigeno>80) ? 0.125: 0;
             double h5 = ((nitrogenoTotal/fosforoTotal) >= 15 )  ? 0.8: ( ((nitrogenoTotal/fosforoTotal) > 10) && ((nitrogenoTotal/fosforoTotal)< 15)) ? 0.6 : ( ((nitrogenoTotal/fosforoTotal) > 5) &&  ((nitrogenoTotal/fosforoTotal) <=10 )) ? 0.35 : ( (nitrogenoTotal/fosforoTotal)<=5 ) ? 0.15 : 0;
             double h7 = (1- Math.pow (10, (-3.26+ 1.34* Math.log10(conductividadElectrica) ))<0)? 0 : 1-Math.pow(10,(-3.26+1.34*Math.log10(conductividadElectrica) ));                                                                                                                                                                                                                                 

             double h9 = (ph<4) ? 0.1 : ((4<=ph) && (ph<=7) ) ? 0.02628419*Math.exp(ph*0.520025) : ( (7 <ph) && (ph<=8) ) ? 1 : ( (8<ph) && (ph<=11) ) ? 1* Math.exp( (ph-8)*-0.5187742 )  : (ph>11) ? 0.1 : 0;

             // calcular ica                                                                                                                                             
             double ica = ( h2 * 0.17 ) + (h3 * 0.17) + (h4 * 0.17) + (h5* 0.17) + (h7 * 0.17) +  (h9 * 0.15);
             
             if (posibleIca7) {
                 double h8 = (coliformes < 50) ? 0.98 : (50<=coliformes && coliformes <1600) ? 0.98* Math.exp((coliformes-50)*-0.0009917754) : (coliformes >=1600) ? 0.1 : 0;
                 ica = ( h2 * 0.16 ) + (h3 * 0.14) + (h4 * 0.14) + (h5* 0.14) + (h7 * 0.14) + (h9 * 0.14 )  + (h8 * 0.14);
             }
             
             System.out.println("-------------------ICA:"+ica+"-------------------");             
             muestra.setIca(ica);                 
             // actualizar ica
             updateMuestra(muestra);
         
         }
         
         private void calcularIcaMuestra ( Long idMuestra) throws IdeamException {
         
             // obtener muestra dado su id
             Muestra muestra = getMuestra(idMuestra);
             
             // calcular ica
             calcularIcaMuestra(muestra);
    
    }

    /**
     * Retorna las mediciones de una muestra de un punto de monitoreo
     * @param muestra
     * @return
     * @throws IdeamException
     */

    public List<Medicion> getMediciones(Muestra mm) throws IdeamException {
        Query query = em.createNamedQuery("Medicion.findMuestra");
        query.setParameter("idMuestra", mm.getId());
        List lista = query.getResultList();
        if (lista != null) {
            Iterator it = lista.iterator();
            while (it.hasNext()) {
                Medicion med = (Medicion)it.next();
                this.loadAttributesMedicion(med);
            }
        }

        return lista;
    }
  
    /**
     * Retorna la mediciones correspondiente a los parametros
     * @param id
     * @return
     * @throws IdeamException
     */
    public Medicion getMedicionExiste(Long idMuestra, Integer parametro,
                                      Integer unidad, Integer signo,
                                      Double valor) throws IdeamException {

        try {
            String sql =
                "select * from calt_medicion where id_muestra = ?1 " + " and id_parametro= ?2 and unidad= ?3 " +
                " and signo= ?4 " + " and valor= ?5 ";

            Query q = em.createNativeQuery(sql, Medicion.class);
            q.setParameter(1, idMuestra);
            q.setParameter(2, parametro);
            q.setParameter(3, unidad);
            q.setParameter(4, signo);
            q.setParameter(5, valor);

            Medicion med = (Medicion)q.getSingleResult();
            loadAttributesMedicion(med);
            return med;
        } catch (NoResultException e) {
            return null;
        }

    }
    
  /**
   * ejecuta un qquery nativo y retorna el resultado
   * @param QUERY
   * @throws IdeamException
   */
  public List<Object[]> ejecutaQueryNativo(String sql) throws IdeamException {
      try {
          Query q = em.createNativeQuery(sql);
          return q.getResultList();
      } catch (NoResultException e) {
          return null;
      }catch (Exception e) {

    System.out.println("ERROR EN getMedicionesTramoParametroAnno " +
                       e.getMessage());
      }
      return null;

  }


    /**
     * Retorna los  puntos de monitoreo  con muestras y mediciones
     * @param
     * @return
     * @throws IdeamException
     */

    public List<PuntoMonitoreo> getListaPuntosMuestra() throws IdeamException {
        String sql =
            "Select * From calt_punto_monitoreo " + " Where id IN( Select distinct(id_punto) From calt_muestra ) ";
        Query q = em.createNativeQuery(sql, PuntoMonitoreo.class);

        return q.getResultList();

    }

    public List<PuntoMonitoreo> getListaPuntosMuestra(Long idAutoridad) throws IdeamException {
        String sql =
            "Select * From calt_punto_monitoreo " + " Where id IN( Select distinct(id_punto) From calt_muestra ) and id_autoridad= " +
            idAutoridad;
        Query q = em.createNativeQuery(sql, PuntoMonitoreo.class);

        return q.getResultList();

    }


    /**
     * Retorna la medicion correspondiente al codigo recibido como parametro
     * @param id
     * @return
     * @throws IdeamException
     */
    public Medicion getMedicion(Long id) throws IdeamException {
        Medicion med = null;
        try{
        Query query = em.createNamedQuery("Medicion.findId");
        query.setParameter("id", id);
        med = (Medicion)query.getSingleResult();
        if (med != null)
             loadAttributesMedicion(med);
        
        }catch(javax.ejb.EJBTransactionRolledbackException e){
            throw new IdeamException("Problemas! o existe un tramo con los datos de búsquedad.");
        }catch(Exception e){
            throw new IdeamException("Problemas! o existe resultado con los datos de búsquedad.");
        }finally{
            return med;
        }
    }


    /**
     * Elimina de IDEAM una muestra existente en la bd.
     * @param muestra
     * @throws FenixException
     */
    public void deleteMuestraPlano(MuestrasPOJO mm) throws IdeamException {
        try {

            if (mm.getId() != null && mm.getId().longValue() != 0) {
                em.flush();
                mm = em.merge(mm);
                em.remove(mm);
            }
        } catch (Exception e) {
            System.out.println("XXXXXXXXXXXXXXXXX-mmm1--ERROR EN LA TRANSMISIÓN: " +
                               e.getMessage());
        }
    }

    /**
     * Elimina la muestra y sus mediciones existente en la bd.
     * @param Muestra
     * @throws FenixException
     */
    public void deleteMuestra(Muestra muestra) throws IdeamException {
        em.flush();
        Query query = em.createNamedQuery("Medicion.findMuestra");
        query.setParameter("idMuestra", muestra.getId());
        List lista = query.getResultList();
        if (lista != null) {
            Iterator it = lista.iterator();
            while (it.hasNext()) {
                Medicion med = (Medicion)it.next();
                med = em.merge(med);
                try {
                    MedicionPOJO medic = new MedicionPOJO();


                    medic.setId(med.getId());
                    medic.setIdMuestra(med.getIdMuestra().getId());
                    medic.setIdParametro(med.getIdParametro());
                    medic.setIdUnidad(med.getIdUnidad());
                    medic.setIdSigno(med.getIdSigno());
                    medic.setEsAcreditado(med.getEsAcreditado());

                    if (med.getValor() != null) {
                        medic.setValor(med.getValor());
                    }
                    if (med.getValor2() != null) {
                        medic.setValor2(med.getValor2());
                    }

                    if (med.getValorchar() != null) {
                        medic.setValorchar(med.getValorchar());
                    }

                    medic.setLimiteDeteccion(med.getLimiteDeteccion());
                    medic.setMetodoDeterminacion(med.getMetodoDeterminacion());


                    

                    em.remove(med);
                    registroRemoto.registrarEvento(this.getClass().getName(),
                                                   "deleteMedicionPlano",
                                                   medic);

                } catch (Exception e) {
                    System.out.println("XXXXXXXXXXXXXXXXX-mmm1--ERROR EN LA TRANSMISIÓN: " +
                                       e.getMessage());
                }


            }
        }


        muestra = em.merge(muestra);

        try {
            MuestrasPOJO mm = new MuestrasPOJO();
            mm.setId(muestra.getId());
            mm.setNro_muestra(muestra.getNro_muestra());
            mm.setIdPuntoMonitoreo(muestra.getIdPuntoMonitoreo().getId());
            mm.setIdLaboratorio(muestra.getIdLaboratorio());
            if (muestra.getIdLaboratorio().equals(ConstantesCalidad.OTRO_LAB)) {
                mm.setOtroLaboratorio(muestra.getOtroLaboratorio());
            }

            mm.setIdTipoMuestra(muestra.getIdTipoMuestra());
            mm.setCodigoAutoridad(muestra.getCodigoAutoridad());
            if (muestra.getNroSubmuestras() != null) {
                muestra.setNroSubmuestras(muestra.getNroSubmuestras());
            }
            if (muestra.getDuracion() != null) {
                mm.setDuracion(muestra.getDuracion());
            }
            if (muestra.getIntervaloTiempo() != null) {
                mm.setIntervaloTiempo(muestra.getIntervaloTiempo());
            }
            if (muestra.getNroVerticales() != null) {
                mm.setNroVerticales(muestra.getNroVerticales());
            }
            mm.setCaudal(muestra.getCaudal());
            mm.setFechaMuestreo(muestra.getFechaMuestreo());
            mm.setHoraMuestreo(muestra.getHoraMuestreo());
            mm.setHorario(muestra.getHorario());
            mm.setMinMuestreo(muestra.getMinMuestreo());

            System.out.println("MuestrasPOJO: " + mm.getId() + "--Xmmm---" +
                               muestra.getId());

            //envío por cola
            registroRemoto.registrarEvento(this.getClass().getName(),
                                           "deleteMuestraPlano", mm);
        } catch (Exception e) {


            System.out.println("XXXXXXXXXXXXXXXXX-mmmm--ERROR EN LA TRANSMISIÓN: " +
                               e.getMessage());
        }


        em.remove(muestra);

    }

    /**
     * Elimina de IDEAM una muestra existente en la bd.
     * @param muestra
     * @throws FenixException
     */
    public void deleteMedicionPlano(MedicionPOJO medm) throws IdeamException {

        try {
            if (medm.getId() != null && medm.getId().longValue() != 0) {
                em.flush();
                medm = em.merge(medm);
                em.remove(medm);

            }

        } catch (Exception e) {
            System.out.println("XXXXXXXXXXXXX ERROR EN EL BORRADO: " +
                               e.getMessage());
        }
    }

    /**
     * Elimina un parametro de las  mediciones de una muestra  existente en la bd.
     * @param Medicion
     * @throws FenixException
     */
    public void deleteMedicion(Medicion med) throws IdeamException {
        em.flush();
        med = em.merge(med);


        try {
            MedicionPOJO medic = new MedicionPOJO();
            medic.setId(med.getId());
            medic.setIdMuestra(med.getIdMuestra().getId());
            medic.setIdParametro(med.getIdParametro());
            medic.setIdUnidad(med.getIdUnidad());
            medic.setIdSigno(med.getIdSigno());
            medic.setEsAcreditado(med.getEsAcreditado());

            if (med.getValor() != null) {
                medic.setValor(med.getValor());
            }
            if (med.getValor2() != null) {
                medic.setValor2(med.getValor2());
            }

            if (med.getValorchar() != null) {
                medic.setValorchar(med.getValorchar());
            }

            medic.setLimiteDeteccion(med.getLimiteDeteccion());
            medic.setMetodoDeterminacion(med.getMetodoDeterminacion());
            em.remove(med);


            //envío por cola
            registroRemoto.registrarEvento(this.getClass().getName(),
                                           "deleteMedicionPlano", medic);

        } catch (Exception e) {
            System.out.println("XXXXXXXXXXXXXXXXX-222--ERROR EN LA TRANSMISIÓN: " +
                               e.getMessage());
        }


    }


    /**
     * Retorna los puntos de monitoreo asociados a un tramo
     * @param Tramo
     * @return
     * @throws IdeamException
     */

    public List<PuntoMonitoreo> getPuntosMonitoreo(FnttTramo tramo) throws IdeamException {
        Query query = em.createNamedQuery("PuntoMonitoreo.findByTramo");
        query.setParameter("idTramo", tramo.getId());
        List lista = query.getResultList();
        if (lista != null) {
            Iterator it = lista.iterator();
            while (it.hasNext()) {
                PuntoMonitoreo pto = (PuntoMonitoreo)it.next();
                this.loadAttributes(pto);
            }
        }


        return lista;
    }


    /**
     * Retorna los puntos de monitoreo asociados a un vertimiento
     * @param Tramo
     * @return
     * @throws IdeamException
     */

    public List<PuntoMonitoreo> getPuntosMonitoreoVert(PuntoVertimiento vert) throws IdeamException {
        Query query = em.createNamedQuery("PuntoMonitoreo.findByVert");
        query.setParameter("idVertimiento", vert.getId());

        List lista = query.getResultList();
        if (lista != null) {
            Iterator it = lista.iterator();
            while (it.hasNext()) {
                PuntoMonitoreo pto = (PuntoMonitoreo)it.next();
                this.loadAttributes(pto);
            }
        }

        return lista;
    }
    
  

    /**
     * Retorna los puntos de monitoreo asociados a una captacion
     * @param captacion
     * @return
     * @throws IdeamException
     */

    public List<PuntoMonitoreo> getPuntosMonitoreoCaptacion(Captacion captacion) throws IdeamException {
    
       
        Query query = em.createNamedQuery("PuntoMonitoreo.findByCaptacion");
        query.setParameter("idCaptacion", captacion.getCodigo());

        List lista = query.getResultList();
        if (lista != null) {
            Iterator it = lista.iterator();
            while (it.hasNext()) {
                PuntoMonitoreo pto = (PuntoMonitoreo)it.next();
                this.loadAttributes(pto);
            }
        }

    
        return lista;
    }
    
    


    /**
     * Retorna los puntos de monitoreo con mediciones de parametros asociadas
     * @param
     * @return
     * @throws IdeamException
     */

    public List<PuntoMonitoreo> getPuntosMonitoreoMediciones(Long idAutoridad) throws IdeamException {
        dCalTo.setIdAutoridad(idAutoridad);
        String sql = dCalTo.getListaPuntosMedicion();
        Query q = em.createNativeQuery(sql, PuntoMonitoreo.class);
        return q.getResultList();

    }

    /**
     * Retorna los puntos de monitoreo con mediciones de parametros asociadas
     * @param
     * @return
     * @throws IdeamException
     */

    public List<Autoridades> getAutoridadesConMuestras() throws IdeamException {
        String sql = DatosReporteCalidadTO.listaAutoridadesConMuestras;
        Query q = em.createNativeQuery(sql, Autoridades.class);
        return q.getResultList();

    }       
        
    public List<PuntoMonitoreo> getListaPuntosMedicionsPorParametro(Long idParametro,
                                                                    Long idAutoridad) throws IdeamException {

        String sql = DatosReporteCalidadTO.listaPuntosPorParametro;
        Query q = em.createNativeQuery(sql, PuntoMonitoreo.class);
        q.setParameter(1, idParametro);
        q.setParameter(2, idAutoridad);

        return q.getResultList();


    }


    /**
     * Retorna los parametros de un puntos de monitoreo
     * @param
     * @return
     * @throws IdeamException
     */

    public List<Lista> getParametrosxPuntoM(Long idPunto) throws IdeamException {
        String sql = DatosReporteCalidadTO.listaParametrosPuntos;
        Query q = em.createNativeQuery(sql, Lista.class);
        q.setParameter(1, idPunto);
        return q.getResultList();

    }

    /**
     * Retorna los parametros de un puntos de monitoreo
     * @param
     * @return
     * @throws IdeamException
     */
    
    public List<Lista> getParametrosPuntoMCalidad(Integer idAutoridad, Long idPunto) throws IdeamException {
        String sql = "";    
System.out.println("getParametrosPuntoMCalidad antes de idAutoridad!= 1") ;       
        if(idAutoridad!= 1){
            sql = DatosReporteCalidadTO.listaParametrosPuntos;

        } else{
             sql = DatosReporteCalidadTO.listaParametrosPuntosIdeam;
         }  
System.out.println("AUTORIDAD getParametrosPuntoMCalidad ="+idAutoridad);                
    System.out.println("SQLCRITERIOS getParametrosPuntoMCalidad = "+idPunto.toString()+" -->"+ sql);
        Query q = em.createNativeQuery(sql, Lista.class);
        q.setParameter(1, idPunto);
        return q.getResultList();

    }
    /**
     * Retorna los parametros medidos en los puntos de monitoreo de una fuente
     * @param
     * @return
     * @throws IdeamException
     */

    public List<Lista> getParametrosxFuente(Long idFuente) throws IdeamException {
        String sql = DatosReporteCalidadTO.listaParametrosFuente;       
        Query q = em.createNativeQuery(sql, Lista.class);
        q.setParameter(1, idFuente);
        return q.getResultList();

    }
    /**
     * Retorna los parametros de una fuente con criterios
     * @param
     * @return
     * @throws IdeamException
     */ 
    
    public List<Lista> getParametrosFuenteCalidad(Integer idAutoridad, Long idFuente) throws IdeamException {
        String sql = "";    
        
        if(idAutoridad!= 1){
            sql = DatosReporteCalidadTO.listaParametrosFuente;

        } else{
             sql = DatosReporteCalidadTO.listaParametrosFuenteIdeam;
         }  
System.out.println("AUTORIDAD getParametrosFuenteCalidad ="+idAutoridad);        
System.out.println("SQLCRITERIOS getParametrosFuenteCalidad = "+idFuente.toString()+" -->"+ sql);            
        Query q = em.createNativeQuery(sql, Lista.class);
        q.setParameter(1, idFuente);
        return q.getResultList();

    }
    
    public List<Object[]> getPararametroFechaPuntoM(Long idPunto,
                                                    Long idParametro) throws IdeamException {
        String sql = DatosReporteCalidadTO.parametroPuntoMonitoreo;
        
System.out.println("SQLCriterios ***  getPararametroFechaPuntoM = "+sql);     
        Query q = em.createNativeQuery(sql);
        q.setParameter(1, idPunto);
        q.setParameter(2, idParametro);

        return q.getResultList();

    }


    public List getListaPararametroFechaPuntoM(Long idPunto,
                                               Long idParametro) throws IdeamException {
        String sql = DatosReporteCalidadTO.listaparametroPuntoMonitoreo;
        System.out.println("SQLCriterios ***  getListaPararametroFechaPuntoM = "+sql);   
        Query q = em.createNativeQuery(sql, DatosGraficoCalidad.class);
        q.setParameter(1, idPunto);
        q.setParameter(2, idParametro);

        return q.getResultList();

    }


    public List<Lista> getUnidadPararametro(Long idParametro, Long idAutoridad) throws IdeamException {
        String sql = DatosReporteCalidadTO.unidadParametro;
        Query q = em.createNativeQuery(sql, Lista.class);
        q.setParameter(1, idParametro);
        q.setParameter(2, idAutoridad);
        List lista = q.getResultList();

        return lista;


    }

    public List<Object[]> getPararametroPromedioAnual(Long idfuente,
                                                      Long idParametro,
                                                      Long anio) throws IdeamException {
        
        String sql = DatosReporteCalidadTO.parametrosPromedioAnio;        
        
System.out.println("PararametroPromedioAnual---- idfuente="+idfuente.toString()+ " idParametro "+idParametro.toString() +" Año= "+ anio.toString());
System.out.println("PararametroPromedioAnual_SQL = "+sql);        
        
        Query q = em.createNativeQuery(sql);
        q.setParameter(1, idfuente);
        q.setParameter(2, idParametro);
        q.setParameter(3, anio);

        return q.getResultList();

    }

    public List getListaPararametroPromedioAnual(Long idfuente,
                                                 Long idParametro, Long anio,
                                                 Long anio2,
                                                 Long anio3) throws IdeamException {
        String sql = DatosReporteCalidadTO.listaparametrosPromedioAnio;
        Query q = em.createNativeQuery(sql, DatosGraficoCalidad.class);
        q.setParameter(1, idfuente);
        q.setParameter(2, idParametro);
        q.setParameter(3, anio);
        q.setParameter(4, idfuente);
        q.setParameter(5, idParametro);
        q.setParameter(6, anio2);
        q.setParameter(7, idfuente);
        q.setParameter(8, idParametro);
        q.setParameter(9, anio3);


        return q.getResultList();

    }
    
//Consultas Módulo Calidad Muestras    
           
    public String getCriteriosConsultasCalidad(CriteriosBusquedaMuestrasTO criterios) throws IdeamException{
        String sql =  "";    
        boolean whereIncluido = false;
        
        if(criterios.getAutoridad()!=null){
            if(!whereIncluido){
                sql += " where ";
                whereIncluido = true;
            }else{
                sql += " and ";
            }                
            sql += " id_autoridad = " + criterios.getAutoridad().getId();
        }
        if(criterios.getArea()!=null){
            if(!whereIncluido){
                sql += " where ";
                whereIncluido = true;
            }else{
                sql += " and ";
            }          
            sql = sql + " id_area = " + criterios.getArea().getId();
        }
    
        if(criterios.getZona()!=null){
            if(!whereIncluido){
                sql += " where ";
                whereIncluido = true;
            }else{
                sql += " and ";
            }          
            sql = sql + " id_zona = " + criterios.getZona().getId();
        }
        
        if(criterios.getSubZona()!=null){
            if(!whereIncluido){
                sql += " where ";
                whereIncluido = true;
            }else{
                sql += " and ";
            }          
            sql = sql + " id_subzona = " + criterios.getSubZona().getId();       
        }
        
        if(criterios.getDepartamento()!=null){
            if(!whereIncluido){
                sql += " where ";
                whereIncluido = true;
            }else{
                sql += " and ";
            }          
            sql = sql + " id_departamento = " + criterios.getDepartamento().getId();
            
        }
        
        if(criterios.getMunicipio()!=null){
            if(!whereIncluido){
                sql += " where ";
                whereIncluido = true;
            }else{
                sql += " and ";
            }          
            sql = sql + " id_municipio = " + criterios.getMunicipio().getId();
            
        }
        
        if(criterios.getFuente()!=null) {
            if(!whereIncluido){
                sql += " where ";
                whereIncluido = true;
            }else{
                sql += " and ";
            }          
            sql = sql + " id_fuente = " + criterios.getFuente().getId();
        }      
        
        if(criterios.getPtoMonitoreo()!=null) {
            if(!whereIncluido){
                sql += " where ";
                whereIncluido = true;
            }else{
                sql += " and ";
            }  
                sql = sql + " id_punto = " + criterios.getPtoMonitoreo().getId();
        }  
        
        if(criterios.getParametro()!=null) {
            if(!whereIncluido){
                sql += " where ";
                whereIncluido = true;
            }else{
                sql += " and ";
            }          
            sql = sql + " id_parametro = " + criterios.getParametro().getCodigo();
        }                  
        
            if(criterios.getAnio()!=null) {
            /*if(criterios.getFechaInicial()!=null) {
               if(!whereIncluido){
                   sql += " where ";
                   whereIncluido = true;
               }else{
                   sql += " and ";
               }          
               SimpleDateFormat sdf = new SimpleDateFormat("d/M/y");
               sql = sql + " fecha_ini >= '" + sdf.format( criterios.getFechaInicial().getTime() ) + "'";
               
            }
    
            if(criterios.getFechaFinal()!=null ) {
               if(!whereIncluido){
                   sql += " where ";
                   whereIncluido = true;
               }else{
                   sql += " and ";
               }          
               SimpleDateFormat sdf = new SimpleDateFormat("d/M/y");
               sql = sql + " fecha_fin <= '" + sdf.format( criterios.getFechaFinal().getTime() ) + "'";
            }*/
            
        //}else{
            if(!whereIncluido){
                sql += " where ";
                whereIncluido = true;
            }else{
                sql += " and ";
            }          
            sql = sql + " EXTRACT(YEAR from fecha_ini) = "+criterios.getAnio();
        }

 System.out.println("SQL_WHERE CRITERIOSCalidad = " + sql);     
             
        return sql;  
        
        }
        
        
        private String[][] getMuestrasConsultasCalidad(String sql) throws IdeamException {
            String retorno[][] = null;
            Query q = em.createNativeQuery(sql, DatosReporteCalidadTO.class);
    
            List lista = q.getResultList();
            Iterator it = lista.iterator();
            retorno = new String[2][lista.size()];
            int i = 0;
            while (it.hasNext()) {
                DatosReporteCalidadTO datos = (DatosReporteCalidadTO)it.next();
                retorno[0][i] = datos.getDescripcion();
                retorno[1][i] = datos.getCantidad().toString();
                i++;
            }
        return retorno;
    }
    
    public String[][]  getCantidadTipoMuestrasCriterios(CriteriosBusquedaMuestrasTO criterios) throws IdeamException{
        String sql = DatosReporteCalidadTO.nroMuestrasPorTipoCriterios+getCriteriosConsultasCalidad(criterios);
        sql += " group by descripcion order by cantidad desc";        
        String datos[][] = getMuestrasConsultasCalidad(sql);
        datos = getMuestrasConsultasCalidad(sql);        
        return datos;
    }
    
    public String[][] getCantidadMuestrasAnioCriterios(CriteriosBusquedaMuestrasTO criterios) throws IdeamException{   
        String sql = DatosReporteCalidadTO.nroMuestrasPorAnoCriterios+getCriteriosConsultasCalidad(criterios);
        sql += " group by descripcion order by cantidad desc";              
        String datos[][] = getMuestrasConsultasCalidad(sql);
        datos = getMuestrasConsultasCalidad(sql);                     
        return datos;
    }
    
    public String[][]  getCantidadTipoMuestrasCriteriosIdeam(CriteriosBusquedaMuestrasTO criterios) throws IdeamException{
        String sql = DatosReporteCalidadTO.nroMuestrasPorTipoCriteriosIdeam+getCriteriosConsultasCalidad(criterios);
        sql += " group by descripcion order by cantidad desc";        
        String datos[][] = getMuestrasConsultasCalidad(sql);
        datos = getMuestrasConsultasCalidad(sql);        
        return datos;
    }
    
    public String[][] getCantidadMuestrasAnioCriteriosIdeam(CriteriosBusquedaMuestrasTO criterios) throws IdeamException{   
        String sql = DatosReporteCalidadTO.nroMuestrasPorAnoCriteriosIdeam+getCriteriosConsultasCalidad(criterios);
        sql += " group by descripcion order by cantidad desc";              
        String datos[][] = getMuestrasConsultasCalidad(sql);
        datos = getMuestrasConsultasCalidad(sql);                     
        return datos;
    }

    public String[][] getNroMuestrasPorAnioFuente(CriteriosBusquedaMuestrasTO criterios, Integer idAutoridad) throws IdeamException{  
        String sql = "";
        if(idAutoridad!= 1){ 
             sql = DatosReporteCalidadTO.nroMuestrasPorAnioFuente+getCriteriosConsultasCalidad(criterios);                         
        }else{
             sql = DatosReporteCalidadTO.nroMuestrasPorAnioFuenteIdeam+getCriteriosConsultasCalidad(criterios);                     
        }
        sql += " group by descripcion order by cantidad desc";  
 System.out.println("AUTORIDAD getNroMuestrasPorAnioFuente ="+idAutoridad);                   
        String datos[][] = getMuestrasConsultasCalidad(sql);
        datos = getMuestrasConsultasCalidad(sql);  
        return datos;
    }    
    
    public List<DatosReporteCalidadPOJO> getNroMuestrasPorAnioFuenteDatos(CriteriosBusquedaMuestrasTO criterios, Integer idAutoridad) throws IdeamException{    
        String sql = "";
        if(idAutoridad!= 1){ 
            sql = DatosReporteCalidadTO.nroMuestrasPorAnioFuenteDatos+getCriteriosConsultasCalidad(criterios);
        }else{
            sql = DatosReporteCalidadTO.nroMuestrasPorAnioFuenteIdeamDatos+getCriteriosConsultasCalidad(criterios); 
        }    
        sql += " order by puntomonitoreo, fecha_ini desc";       
System.out.println("AUTORIDAD getNroMuestrasPorAnioFuenteDatos ="+idAutoridad);    
System.out.println("SQLCriterios getNroMuestrasPorAnioFuenteDatos= " + sql);       
        Query q = em.createNativeQuery(sql, DatosReporteCalidadPOJO.class);
        return q.getResultList();
    }    
    
    public List<Object[]> getPararametroEnPuntoMonitoreo(CriteriosBusquedaMuestrasTO criterios, Integer idAutoridad) throws IdeamException {
        String sql = "";
       if(idAutoridad!= 1){ 
            sql = DatosReporteCalidadTO.parametroEnPuntoMonitoreo+getCriteriosConsultasCalidad(criterios);
        }else{
            sql = DatosReporteCalidadTO.parametroEnPuntoMonitoreoIdeam+getCriteriosConsultasCalidad(criterios); 
        }         
        sql += " order by TO_CHAR(fecha_ini, 'yyyy/mm/dd') , cantidad asc";                
       
System.out.println("SQLCriterios getPararametroEnPuntoMonitoreo= " + sql);
        Query q = em.createNativeQuery(sql);       
        return q.getResultList();
    }
     
     public List<DatosReporteCalidadParametrosPuntoPOJO> getParametroEnPuntoMonitoreoDatos(CriteriosBusquedaMuestrasTO criterios, Integer idAutoridad) throws IdeamException {
        String sql = "";        
        if(idAutoridad!= 1){       
            sql = DatosReporteCalidadTO.parametroEnPuntoMonitoreoDatos+getCriteriosConsultasCalidad(criterios);
        }else{
            sql = DatosReporteCalidadTO.parametroEnPuntoMonitoreoIdeamDatos+getCriteriosConsultasCalidad(criterios); 
        }  
        sql += " order by TO_CHAR(fecha_ini, 'yyyy/mm/dd') desc, cantidad asc";                
        
System.out.println("SQLCriterios getParametroEnPuntoMonitoreoDatos= " + sql +"-----");
System.out.println("AUTORIDAD getPararametroEnPuntoMonitoreoDatos ="+idAutoridad);      
        Query q = em.createNativeQuery(sql, DatosReporteCalidadParametrosPuntoPOJO.class);
        q.setParameter(1, criterios.getPtoMonitoreo().getId().intValue());
        q.setParameter(2, criterios.getParametro().getCodigo());
        return q.getResultList();
    }
    
    public List<Object[]> getParametroPuntoFuente(CriteriosBusquedaMuestrasTO criterios, Integer idAutoridad, Integer anio) throws IdeamException {
        String sql = "";
        
            if(idAutoridad!= 1){  
            sql = DatosReporteCalidadTO.listaParametroPuntoFuente+getCriteriosConsultasCalidad(criterios);
        }else{
            sql = DatosReporteCalidadTO.listaParametroPuntoFuenteIdeam+getCriteriosConsultasCalidad(criterios); 
        }   
        sql +=  " and EXTRACT(YEAR from fecha_ini) = "+anio;    
        sql += " group by nombre, cantidad order by cantidad asc";                
        
System.out.println("SQLCriterios getParametroPuntoFuente= "+anio.toString()+"  "+sql);      
        Query q = em.createNativeQuery(sql);
        return q.getResultList();
    }
     
     public List<DatosReporteCalidadParametrosPuntoPOJO> getParametroPuntoFuenteDatos(CriteriosBusquedaMuestrasTO criterios, Integer idAutoridad) throws IdeamException {
        String sql = "";
        Integer anio1,anio2,anio3;
        anio1 = criterios.getAnio();
        anio2 = criterios.getAnio()-1;
        anio3 = criterios.getAnio()-2;
        if(idAutoridad!= 1){  
            sql = DatosReporteCalidadTO.listaParametroPuntoFuenteDatos+getCriteriosConsultasCalidad(criterios);
        }else{
            sql = DatosReporteCalidadTO.listaParametroPuntoFuenteIdeamDatos+getCriteriosConsultasCalidad(criterios); 
        }                   
        sql += " and valor in ("+anio1+","+anio2+","+anio3+") "; 
        sql += " order by valor desc, cantidad desc";                

System.out.println("getlistaParametroPuntoFuenteDatos ANO1= " + anio1);       
System.out.println("getlistaParametroPuntoFuenteDatos ANO2= " + anio2);       
System.out.println("getlistaParametroPuntoFuenteDatos ANO3= " + anio3);       
System.out.println("SQLCriterios getlistaParametroPuntoFuenteDatos= " + sql);      

        Query q = em.createNativeQuery(sql, DatosReporteCalidadParametrosPuntoPOJO.class);
        return q.getResultList();
    }     

     
    // Metodo Nro de  Muestras por tipo
    public String[][] getCantidadTipoMuestras(Long idAutoridad) throws IdeamException {
        dCalTo.setIdAutoridad(idAutoridad);
        return getCantidadTipoMuestras(dCalTo.getNroMuestrasPorTipo());

    }
    
  // Metodo Nro de  Muestras por tipo Subt
  public String[][] getCantidadTipoMuestrasSubt(Long idAutoridad) throws IdeamException {
    String sql = "SELECT aux.TIPOMUESTRA as descripcion, count(*) as cantidad FROM ( " + djsCalTo.listaParametrosCalidadXTipoPunto+" ) AUX ";
    if(idAutoridad != null){
      sql = sql + " WHERE  AUX.id_autoridad =  " + idAutoridad +" ";
      }
    
    sql = sql + " GROUP BY aux.TIPOMUESTRA ORDER BY 2 DESC ";
      return getCantidadTipoMuestrasSubt(sql);
  }

    private String[][] getCantidadTipoMuestras(String sql) throws IdeamException {
        String retorno[][] = null;
        Query q = em.createNativeQuery(sql, DatosReporteCalidadTO.class);

        List lista = q.getResultList();
        Iterator it = lista.iterator();
        retorno = new String[2][lista.size()];
        int i = 0;
        while (it.hasNext()) {
            DatosReporteCalidadTO datos = (DatosReporteCalidadTO)it.next();
            retorno[0][i] = datos.getDescripcion();
            retorno[1][i] = datos.getCantidad().toString();
            i++;
        }
        return retorno;
    }
    
  private String[][] getCantidadTipoMuestrasSubt(String sql) throws IdeamException {
      String retorno[][] = null;
      Query q = em.createNativeQuery(sql, DatosReporteCalidadTO.class);

      List lista = q.getResultList();
      Iterator it = lista.iterator();
      retorno = new String[2][lista.size()];
      int i = 0;
      while (it.hasNext()) {
          DatosReporteCalidadTO datos = (DatosReporteCalidadTO)it.next();
          retorno[0][i] = datos.getDescripcion();
          retorno[1][i] = datos.getCantidad().toString();
          i++;
      }
      return retorno;
  }

    public String[][] getCantidadTipoMuestrasIdeam() throws IdeamException {
        String sql = DatosReporteCalidadTO.nroMuestrasPorTipoIdeam;            
        
        System.out.println("SQLCriterios getNroMuestrasTipoIdeam= " + sql);

        String datos[][] = getMuestrasConsultasCalidad(sql);
        datos = getMuestrasConsultasCalidad(sql);                     
        return datos;
    }

  
    // Metodo Nro de  Muestras por Años

    public String[][] getCantidadMuestrasAnio(Long idAutoridad) throws IdeamException {
        dCalTo.setIdAutoridad(idAutoridad);
        return getCantidadMuestrasAnio(dCalTo.getNroMuestrasPorAnioORA());

    }
    
  public String[][] getCantidadMuestrasAnioSubt(Long idAutoridad) throws IdeamException {
      String sql = " SELECT EXTRACT(YEAR FROM AUX.fecha_muestreo) as descripcion, COUNT(*) AS CANTIDAD FROM (" + djsCalTo.listaParametrosCalidadXTipoPunto +" ) AUX ";
      if(idAutoridad != null){
        sql = sql + " WHERE AUX.id_autoridad = " + idAutoridad +" ";
        }
      sql = sql + " GROUP BY EXTRACT(YEAR FROM AUX.fecha_muestreo) ORDER BY 2 DESC ";
      System.out.println("getCantidadMuestrasAnioSubt SQL -> " +sql);
      return getCantidadMuestrasAnioSubt(sql);

  }

    private String[][] getCantidadMuestrasAnio(String sql) throws IdeamException {
        String retorno[][] = null;
        Query q = em.createNativeQuery(sql, DatosReporteCalidadTO.class);

        List lista = q.getResultList();
        Iterator it = lista.iterator();
        retorno = new String[2][lista.size()];
        int i = 0;
        while (it.hasNext()) {
            DatosReporteCalidadTO datos = (DatosReporteCalidadTO)it.next();
            retorno[0][i] = datos.getDescripcion();
            retorno[1][i] = datos.getCantidad().toString();
            i++;
        }
        return retorno;
    }
    
  private String[][] getCantidadMuestrasAnioSubt(String sql) throws IdeamException {
      String retorno[][] = null;
      Query q = em.createNativeQuery(sql, DatosReporteCalidadTO.class);

      List lista = q.getResultList();
      Iterator it = lista.iterator();
      retorno = new String[2][lista.size()];
      int i = 0;
      while (it.hasNext()) {
          DatosReporteCalidadTO datos = (DatosReporteCalidadTO)it.next();
          retorno[0][i] = datos.getDescripcion();
          retorno[1][i] = datos.getCantidad().toString();
          i++;
      }
      return retorno;
  }

    public String[][] getCantidadMuestrasAnioIdeam() throws IdeamException {
        String sql = DatosReporteCalidadTO.nroMuestrasPorAnioIdeam;            
        
        System.out.println("SQLCriterios getNroMuestrasAnioIdeam= " + sql);

        String datos[][] = getMuestrasConsultasCalidad(sql);
        datos = getMuestrasConsultasCalidad(sql);                     
        return datos;
    }


    public List getDatosCroosTab(String sql) throws IdeamException {
        List lista = new ArrayList();
        Query q = em.createNativeQuery(sql, DatosReporteCroosTab.class);

        lista = q.getResultList();

        return lista;
    }
    
    public List<DatosReporteIcaCroosTabIdeam> getDatosCroosTabIdeam(Long idPuntoSeleccionado, List listaParametros) throws IdeamException {
        String sql ="";
        
        sql = "Select dat.codigo_muestra,dat.fecha,dat.hora,dat.punto,dat.fuente,dat.subcategoria,dat.departamento,dat.municipio, " + 
            "  dat.longitud,dat.direccion_Longitud,dat.latitud,direccion_latitud,dat.altitud,(c48.valor+273.15) as temperatura_kelvin, " + 
            "  round(dat.presion_atmosferica_mmhg, 2) as presion_atmosferica_mmhg,dat.presion_atmosferica,dat.pw,dat.teta,dat.psod as porcentaje_saturacion,dat.ipsod,dat.isst, " + 
            "  dat.idqo,dat.icond, dat.iph,dat.ica5,dat.descriptor5,round(c37.valor/c21.valor,2) as np,dat.inp,dat.ica6,dat.descriptor6,dat.icoliformes,dat.ica7,dat.descriptor7 ,  ";
        int nro= 1;
        for(int i= 0; i<listaParametros.size(); i++){
            SelectItem item = (SelectItem)listaParametros.get(i);
            sql= sql+ "(c"+nro+".dlimite||c"+nro+".valor) as "+ item.getLabel() +",c"+nro+".valor as "+"valor"+nro+",";
            nro=nro+1;
            }
        sql= sql.substring(0, sql.length()-1);
        sql +=  " From(select mm.codigomuestra as codigo_muestra, to_char(mm.fechamuestreo , 'dd/mm/yyyy') as fecha, to_char(mm.fechamuestreo ,'hh:mi am') as hora, pm.punto, pm.fuente, mm.subcategoria, pm.departamento, pm.municipio, " + 
                    "   pm.longitud, pm.direccion_longitud, pm.latitud, pm.direccion_latitud, pm.altitud,(mica.presionatm*760) as presion_atmosferica_mmhg ," + 
                    "   mica.presionatm as presion_atmosferica, mica.pw, mica.teta, mica.psod, mica.ipsod , mica.isst, mica.idqo, mica.icond," + 
                    "   mica.iph, mica.ica5, (case when (ica5 > 0 and ica5 <= 0.25 ) then 'Muy Malo' when ( ica5 > 0.25 and ica5 <= 0.5 ) then 'Malo'when ( ica5 > 0.5 and ica5 <= 0.7 ) then 'Regular' " + 
                    "   when ( ica5 > 0.7 and ica5 <= 0.9 ) then 'Aceptable' when ( ica5 > 0.9 and ica5 <= 1 ) then 'Bueno' end) as descriptor5,mica.inp,mica.ica6, " + 
                    "   (case when ( ica6 > 0 and ica6 <= 0.25 ) then 'Muy Malo' when ( ica6 > 0.25 and ica6 <= 0.5 ) then 'Malo' when ( ica6 > 0.5 and ica6 <= 0.7 ) then 'Regular' " + 
                    "   when ( ica6 > 0.7 and ica6 <= 0.9 ) then 'Aceptable' when ( ica6 > 0.9 and ica6 <= 1 ) then 'Bueno'end) as descriptor6, mica.iecoli as icoliformes, mica.ica7, " + 
                    "   (case when ( ica7 > 0 and ica7 <= 0.25 ) then 'Muy Malo' when ( ica7 > 0.25 and ica7 <= 0.5 ) then 'Malo'when ( ica7 > 0.5 and ica7 <= 0.7 ) then 'Regular' " + 
                    "   when ( ica7 > 0.7 and ica7 <= 0.9 ) then 'Aceptable' when ( ica7 > 0.9 and ica7 <= 1 ) then 'Bueno' end) as descriptor7, " +
                    "   pm.id_area,pm.id_zona,pm.id_subzona,pm.id_departamento,pm.id_municipio,pm.id_fuente" +             
                    " from sirhv_muestras_fq mm " + 
                    "     left join sirhv_punto_monitoreo_fq pm on  pm.id = mm.estacionid " + 
                    "     left join calt_muestras_ica_fq mica on mica.codigo_muestra = mm.codigomuestra where mica.ica5 is not null and pm.id = "+ idPuntoSeleccionado.toString()+" ) dat ";
        nro= 1;
        for(int j= 0; j<listaParametros.size(); j++){
                SelectItem item = (SelectItem)listaParametros.get(j);                                                            
                sql += " left join sirhv_muestras_fq c"+nro+" on c"+nro+".codigomuestra = dat.codigo_muestra and c"+nro+".supacodigoparametro= "+item.getValue();
                nro=nro+1;
         }      

        sql +=  " group by dat.codigo_muestra,dat.fecha,dat.hora,dat.punto,dat.fuente,dat.subcategoria,dat.departamento,dat.municipio, " + 
                " dat.longitud,dat.direccion_Longitud,dat.latitud,direccion_latitud,dat.altitud,(c48.valor+273.15), " + 
                " dat.presion_atmosferica_mmhg,dat.presion_atmosferica,dat.pw,dat.teta,dat.psod,dat.ipsod,dat.isst,dat.idqo,dat.icond," + 
                " dat.iph,dat.ica5,dat.descriptor5,(c37.valor/c21.valor),dat.inp,dat.ica6,dat.descriptor6,dat.icoliformes,dat.ica7,dat.descriptor7 ,";
        nro= 1;
        for(int j= 0; j<listaParametros.size(); j++){                       
                sql += "(c"+nro+".dlimite||c"+nro+".valor),c"+nro+".valor," ;             
                nro=nro+1;
            }
        sql= sql.substring(0, sql.length()-1);        
        sql+= " order by dat.punto,dat.fecha";             
  
System.out.println("SQLICA= " + sql);  

        Query q = em.createNativeQuery(sql, DatosReporteIcaCroosTabIdeam.class);
        return q.getResultList();

    }
  
    public String[][] getMuestrasFuente(Long idfuente, Integer anio,
                                        Long idAutoridad) throws IdeamException {
        dCalTo.setIdAutoridad(idAutoridad);
        return getMuestrasFuente(dCalTo.getMuestrasFuentes(idfuente, anio));
    }

    private String[][] getMuestrasFuente(String sql) throws IdeamException {
        String retorno[][] = null;
        Query q = em.createNativeQuery(sql, DatosReporteCalidadTO.class);
        List lista = q.getResultList();
        Iterator it = lista.iterator();
        retorno = new String[2][lista.size()];
        int i = 0;
        while (it.hasNext()) {
            DatosReporteCalidadTO datos = (DatosReporteCalidadTO)it.next();
            retorno[0][i] = datos.getDescripcion();
            retorno[1][i] = datos.getCantidad().toString();
            i++;
        }
        return retorno;
    }


    /**
     * Retorna las fuentes de las autoridades segun parametro
     * @param tipoLista  0 Autoridades ambientales e IDEAM
     *                          1 Autoridades ambientales
     *                          2 IDEAM
     * @return
     * @throws IdeamException
     */
     
    public List<FnttFuente> getFuentesCriterios(CriteriosBusquedaTO criterios)throws IdeamException{
        String sql = "";
        Integer tipoAutoridad = 0; // 0=Autoridades Ambientales 1=IDEAM
        
        if(criterios.getAutoridad()!=null){
            if(criterios.getAutoridad().getId()!=1){    
                sql = DatosReporteCalidadTO.listaFuentesMuestrasAutoridades+getCriterioslistaFuente(criterios,tipoAutoridad);
            }else{
                tipoAutoridad = 1;
                sql = DatosReporteCalidadTO.listaFuentesMuestrasIdeam+getCriterioslistaFuente(criterios,tipoAutoridad);                
            }
        }else {               
            sql = "Select * From ( "+DatosReporteCalidadTO.listaFuentesMuestrasAutoridades+getCriterioslistaFuente(criterios,tipoAutoridad);
            tipoAutoridad = 1;
            sql += " Union all "+DatosReporteCalidadTO.listaFuentesMuestrasIdeam+getCriterioslistaFuente(criterios,tipoAutoridad)+" )";
        }        
            
        sql += " order by nombre " ;
    System.out.println("SQLFuentesCriterios= "+sql);

       Query q = em.createNativeQuery(sql, FnttFuente.class);
       return q.getResultList();
    }
        
    public String getCriterioslistaFuente(CriteriosBusquedaTO criterios, Integer tipoAutoridad) throws IdeamException{
        String sql =  "";        
        boolean whereIncluido = false;
        
        if(criterios.getAutoridad()!=null){
            if(!whereIncluido){
                sql += " where  ";
                whereIncluido = true;
            }else{
                sql += " and ";
            }                
            sql += " cod_autoridad = " + criterios.getAutoridad().getId();
        }
    
        if(criterios.getArea()!=null&&criterios.getZona()==null&&criterios.getSubzona()==null){
            if(!whereIncluido){
                sql += " where ";
                whereIncluido = true;
            }else{
                sql += " and ";
            } 
            if(tipoAutoridad==0){
                sql = sql + " id in (Select id_fuente From calt_punto_monitoreo  Where id_area = " + criterios.getArea().getId()+" group by id_fuente)";
            }else{
                sql = sql + " id in (Select id_fuente From sirhv_punto_monitoreo_fq  Where id_area = " + criterios.getArea().getId()+" and id_fuente is not null group by id_fuente)";
            }    
        }else if(criterios.getZona()!=null&&criterios.getSubzona()==null){
            if(!whereIncluido){
                sql += " where ";
                whereIncluido = true;
            }else{
                sql += " and ";
            }
            if(tipoAutoridad==0){
                sql = sql + " id in (Select id_fuente From calt_punto_monitoreo  Where id_zona = " + criterios.getZona().getId()+" group by id_fuente)";
            }else{
                sql = sql + " id in (Select id_fuente From sirhv_punto_monitoreo_fq  Where id_zona = " + criterios.getZona().getId()+" and id_fuente is not null group by id_fuente)";
            }
          
        }else  if(criterios.getSubzona()!=null){
            if(!whereIncluido){
                sql += " where ";
                whereIncluido = true;
            }else{
                sql += " and ";
            }          
            if(tipoAutoridad==0){
                sql = sql + " id in (Select id_fuente From calt_punto_monitoreo  Where id_subzona =  " + criterios.getSubzona().getId()+" group by id_fuente)";   
            }else{
                sql = sql + " id in (Select id_fuente From sirhv_punto_monitoreo_fq  Where id_subzona = " + criterios.getSubzona().getId()+" and id_fuente is not null group by id_fuente)";
            }               
        }
               
        if(criterios.getDepartamento()!=null){
            if(!whereIncluido){
                sql += " where ";
                whereIncluido = true;
            }else{
                sql += " and ";
            }     
            if(criterios.getMunicipio()!=null){
                if(tipoAutoridad==0){
                    sql = sql + "id in (Select id_fuente  from calt_punto_monitoreo where id_departamento = " + criterios.getDepartamento().getId()+
                            " and id_municipio = "+criterios.getMunicipio().getId()+" )";
                }else{
                    sql = sql + "id in (Select id_fuente  from sirhv_punto_monitoreo_fq where id_departamento = " + criterios.getDepartamento().getId()+
                            " and id_municipio = "+criterios.getMunicipio().getId()+" and id_fuente is not null)";
                }    
            }else{ 
                if(tipoAutoridad==0){
                sql = sql + "id in (Select id_fuente  from calt_punto_monitoreo where id_departamento = " + criterios.getDepartamento().getId()+" group by id_fuente)";
                }else{
                    sql = sql + "id in (Select id_fuente  from sirhv_punto_monitoreo_fq where id_departamento = " + criterios.getDepartamento().getId()+" and id_fuente is not null group by id_fuente)";
                }
            } 
        }
System.out.println("SQLWHEREFuentesCriterios= "+sql);        
        return sql;
    }
             
    /**
     * Retorna los Puntos Monitoreo de las autoridades segun parametro
     * @param tipoLista  0 Autoridades ambientales e IDEAM
     *                   1 Autoridades ambientales
     *                   2 IDEAM
     * @return
     * @throws IdeamException
     */

    public List<PuntoMonitoreo> getPuntosMonitoreoCriterios(CriteriosBusquedaTO criterios) throws IdeamException {              
        String sql = "";
        Integer tipoAutoridad = 0; // 0=Autoridades Ambientales 1=IDEAM
        
        if(criterios.getAutoridad()!=null){
            if(criterios.getAutoridad().getId()!=1){    
                sql = DatosReporteCalidadTO.listaPuntosMonitoreoMuestrasAutoridades+getCriterioslistaPuntoMonitoreo(criterios,tipoAutoridad);
            }else{
                tipoAutoridad = 1;
                sql = DatosReporteCalidadTO.listaPuntosMonitoreoMuestrasIdeam+getCriterioslistaPuntoMonitoreo(criterios,tipoAutoridad);                
            }
        }else {               
            sql = "Select * From ( "+DatosReporteCalidadTO.listaPuntosMonitoreoMuestrasAutoridades+getCriterioslistaPuntoMonitoreo(criterios,tipoAutoridad);
            tipoAutoridad = 1;
            sql += " Union all "+DatosReporteCalidadTO.listaPuntosMonitoreoMuestrasIdeam+getCriterioslistaPuntoMonitoreo(criterios,tipoAutoridad)+" )";
        }        
            
        sql += " order by nombre " ;
        
        
    System.out.println("SQLPuntosMonitoreoCriterios= "+ sql);
        Query q = em.createNativeQuery(sql, PuntoMonitoreo.class);
        return q.getResultList();        
    }         
            


    public String getCriterioslistaPuntoMonitoreo(CriteriosBusquedaTO criterios, Integer tipoAutoridad) throws IdeamException {
        String sql = "";
        boolean whereIncluido = false;
        
        if(criterios.getAutoridad()!=null){
            if(!whereIncluido){
                sql += " where  ";
                whereIncluido = true;
            }else{
                sql += " and ";
            }                
            sql += " id_autoridad = " + criterios.getAutoridad().getId();
        } 
        
        if(criterios.getArea()!=null){
            if(!whereIncluido){
                sql += " where ";
                whereIncluido = true;
            }else{
                sql += " and ";
            }
            sql +=  " id_area = " + criterios.getArea().getId();
        }

        if(criterios.getZona()!=null){
            if(!whereIncluido){
                sql += " where ";
                whereIncluido = true;
            }else{
                sql += " and ";
            }
                sql += " id_zona = " + criterios.getZona().getId();
        }
        
        if(criterios.getSubzona()!= null){
            if(!whereIncluido){
                sql += " where ";
                whereIncluido = true;
            }else{
                sql += " and ";
            }
            sql += " id_subzona = " + criterios.getSubzona().getId();       
        }
              
        if(criterios.getFuente()!= null) {
            if(!whereIncluido){
                sql += " where ";
                whereIncluido = true;
            }else{
                sql += " and ";
            }
            sql +=  " id_fuente = " + criterios.getFuente().getId() ;
        }    
        
        if(criterios.getDepartamento()!=null){
            if(!whereIncluido){
                sql += " where ";
                whereIncluido = true;
            }else{
                sql += " and ";
            }     
            if(criterios.getMunicipio()!=null){
                if(tipoAutoridad==0){
                    sql = sql + "id_departamento = " + criterios.getDepartamento().getId()+
                            " and id_municipio = "+criterios.getMunicipio().getId();
                }else{
                    sql = sql + "id_departamento = " + criterios.getDepartamento().getId()+
                            " and id_municipio = "+criterios.getMunicipio().getId()+" and id_fuente is not null ";
                }    
            }else{ 
                if(tipoAutoridad==0){
                sql = sql + "id_departamento = " + criterios.getDepartamento().getId();
                }else{
                    sql = sql + "id_departamento = " + criterios.getDepartamento().getId()+" and id_fuente is not null ";
                }
            } 
        }       
        System.out.println("SQLWHERECriteriosPuntos = "+sql);    
        
        return sql;    

    }
    
    public List getAniosFuenteParametrosCalidad(Long idparametro, Long idFuente, Integer idAutoridad) throws IdeamException {
        String sql = "";
        if(idAutoridad!= 1){  
            sql = DatosReporteCalidadTO.listaAnioParametroFuente;
        }else{
            sql = DatosReporteCalidadTO.listaAnioParametroFuenteIdeam; 
        }           
        
System.out.println("SQLCriterios getAnioFuenteParametrosCalidad= " + sql);

        Query q = em.createNativeQuery(sql);
        q.setParameter(1, idparametro);
        q.setParameter(2, idFuente);
        return q.getResultList();
    }  
    
    public List getAniosFuenteCalidad( Long idFuente, Integer idAutoridad) throws IdeamException {
        String sql = "";
        if(idAutoridad!= 1){  
            sql = DatosReporteCalidadTO.listaAnioFuente;
        }else{
            sql = DatosReporteCalidadTO.listaAnioFuenteIdeam; 
        }           
        
    System.out.println("SQLCriterios getAnioFuenteCalidad= " + sql);

        Query q = em.createNativeQuery(sql);
       
        q.setParameter(1, idFuente);
        return q.getResultList();
    }  
            
            
    /**
     * Retorna los parametros de un puntos de monitoreo
     * @param
     * @return
     * @throws IdeamException
     */

    public List<FnttFuente> getFuentesMuestras(Long idAutoridad) throws IdeamException {
        dCalTo.setIdAutoridad(idAutoridad);
        String sql = dCalTo.getListaFuentesMuestras();
        Query q = em.createNativeQuery(sql, FnttFuente.class);
        return q.getResultList();

    }

    /**
     * Retorna las fuentes con su tipo de fuente
     * @param
     * @return
     * @throws IdeamException
     */

    public List<FnttFuente> getListaFuentesTipo(Long idAutoridad) throws IdeamException {
        dCalTo.setIdAutoridad(idAutoridad);
        String sql = dCalTo.getListaFuentesTipo();
        Query q = em.createNativeQuery(sql, FnttFuente.class);
        return q.getResultList();

    }
    public List getAnioMuestras(Long idfuente) throws IdeamException {

        Query q = null;
        try {
            q = em.createNativeQuery(DatosReporteCalidadTO.listaAnioMuestrasORA);
            q.setParameter(1, idfuente);
        } catch (Exception e) {

        }
        return q.getResultList();

    }

    public List getAnioParametros(Long idparametro) throws IdeamException {
        String sql = DatosReporteCalidadTO.listaAnioParametro;
        Query q = em.createNativeQuery(sql);
        q.setParameter(1, idparametro);
        return q.getResultList();

    }

    public List getAnioParametrosFuente(Long idparametro,
                                        Long idFuente) throws IdeamException {
        String sql = DatosReporteCalidadTO.listaAnioParametroFuente;
        Query q = em.createNativeQuery(sql);
        q.setParameter(1, idparametro);
        q.setParameter(2, idFuente);
        return q.getResultList();

    }


    public List getListaMedicionsPorParametro(Long idparametro,
                                              Long idAutoridad) throws IdeamException {
        String sql = "";
        if(idAutoridad!= 1){
            sql = DatosReporteCalidadTO.listaMedicionParametro;
        }else{
            sql = DatosReporteCalidadTO.listaMedicionParametroIdeam; 
        }         
//        sql += " order by TO_CHAR(fecha_ini, 'yyyy/mm/dd') , cantidad asc";                
        
        System.out.println("SQLCriterios getListaMedicionsPorParametro= " + sql);
              
        Query q = em.createNativeQuery(sql);
        q.setParameter(1, idparametro);
        q.setParameter(2, idAutoridad);
        return q.getResultList();

    }


    /**
     * Inserta o actualiza la informacion de las norma del decreto 1594
     * @param
     * @throws IdeamxException
     */

    public NormaUsos updateNormaUsos(NormaUsos nn) throws IdeamException {

        System.out.println("NormaUsos.getId()---------------------------------" +
                           nn.getId());

        if (nn.getId() == null) {
            nn.setId(GeneradorSeq.obtenerSequencia(nn.getCodigoAutoridad(),
                                                   "seq_norma_usos", em));
            em.persist(nn);
        } else {

            System.out.println("elsee  .getId()---------------------------------" +
                               nn.getNombreNorma());

            em.merge(nn);
        }
        return nn;
    }


    /**
     * Inserta o actualiza la informacion de los limites de norma del decreto 1594
     * @param
     * @param
     * @throws IdeamxException
     */

    public NormaLimites updateNormaLimites(NormaLimites lim) throws IdeamException {


        System.out.println("lim.getId()---------------------------------" +
                           lim.getId());
        if (lim.getId() == null) {
            lim.setId(GeneradorSeq.obtenerSequencia(lim.getCodigoAutoridad(),
                                                    "seq_norma_limites", em));
            em.persist(lim);
        } else {
            em.merge(lim);
        }
        return lim;
    }

    public void setDCalTo(DatosReporteCalidadTO dCalTo) {
        this.dCalTo = dCalTo;
    }

    public DatosReporteCalidadTO getDCalTo() {
        return dCalTo;
    }


    /**
     * Elimina una norma existente en la bd.
     * @param nn
 
     */
    public void deleteNormaUsos(NormaUsos nn) throws IdeamException {
        em.flush();
        nn = em.merge(nn);
        em.remove(nn);
    }


    public List<Lista> getListaPorCategoria(Long idcategoria) throws IdeamException {

        String sql =
            "Select * From part_listas" + " Where id_categoria = " + idcategoria +
            "  order by valor ";
        Query q = em.createNativeQuery(sql, Lista.class);

        return q.getResultList();


    }

    public List<Object[]> getListaLimitesParametro(Integer idParametro, 
                                         Integer idAutoridad) throws IdeamException {


        String sql = NormaLimites.listaLimitesParametro;
        
        Query q = em.createNativeQuery(sql);
        
        q.setParameter(1, idParametro.intValue());
        q.setParameter(2, idAutoridad.intValue());

        
        List<Object[]> resul = q.getResultList();


        return resul;

    }
    
    //  * Consultar capatacion por fuente  en cada autoridad ambiental 
    public List<DatosReporteWS> consultarDatosCalidad(String codAutoridadAmbiental, String tipo) throws IdeamException {
        System.out.println("consultarDatosCalidad"+ codAutoridadAmbiental + "---"+tipo);
        Long idAutoridad = usuariosAguaService.getIdAutoridad(codAutoridadAmbiental);
        DatosReporteWS dWS = new DatosReporteWS();
        String[][] datos =null;
        List lista = new ArrayList();
        String[] seriesLabels;
        Object[][] data2;     
           
           
        if(tipo.equals("MST")){
             datos=this.getCantidadMuestrasAnio(idAutoridad);
        }
       
        
           if(datos[0][0]!=null){
        
        seriesLabels = new String[datos[0].length];
        data2 = new Object[1][datos[0].length];
        Long cant = 0L;
        for (int i = 0; i < datos[0].length; i++) {
                if (datos[1][i] != null) {
                    dWS = new DatosReporteWS();
                    seriesLabels[i] = datos[0][i];
                     System.out.println("consultarDatosCalidad seriesLabels[i] "+ seriesLabels[i] );
                    data2[0][i] = new Integer(datos[1][i]);
                    cant = new Long(data2[0][i].toString());
                    dWS.setDescripcion(seriesLabels[i].toString());
                    
                     System.out.println("consultarDatosCalidad -----cant "+ cant );
                    dWS.setCantidad(cant);
                    lista.add(dWS);
                 }
            }
           }
        
        return lista;
        }


}//Fin clase

