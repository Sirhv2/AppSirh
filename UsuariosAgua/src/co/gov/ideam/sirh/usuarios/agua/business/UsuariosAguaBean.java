package co.gov.ideam.sirh.usuarios.agua.business;

import co.gov.ideam.sirh.archivos.model.ColumnTO;
import co.gov.ideam.sirh.archivos.model.RowTO;
import co.gov.ideam.sirh.calidad.business.CalidadEJB;
import co.gov.ideam.sirh.datossinc.model.ConcesionSinc;
import co.gov.ideam.sirh.datossinc.model.PermisoVertimientoSinc;
import co.gov.ideam.sirh.datossinc.model.PredioSinc;
import co.gov.ideam.sirh.datossinc.model.RepresentanteSinc;
import co.gov.ideam.sirh.datossinc.model.UsuarioAguaSinc;
import co.gov.ideam.sirh.fuentes.model.FnttTramo;
import co.gov.ideam.sirh.parametros.business.ParametrosEJB;
import co.gov.ideam.sirh.parametros.model.ActividadCIIU;
import co.gov.ideam.sirh.parametros.model.Autoridades;
import co.gov.ideam.sirh.usuarios.agua.model.ws.entidades.AutoridadesWS;
import co.gov.ideam.sirh.parametros.model.Divipola;
import co.gov.ideam.sirh.parametros.model.Lista;
import co.gov.ideam.sirh.remoto.business.RegistroRemotoLocal;
import co.gov.ideam.sirh.usuarios.agua.model.Captacion;
import co.gov.ideam.sirh.usuarios.agua.model.CaptacionDetalle;
import co.gov.ideam.sirh.usuarios.agua.model.CaptacionPOJO;
import co.gov.ideam.sirh.usuarios.agua.model.CaptacionSubTO;
import co.gov.ideam.sirh.usuarios.agua.model.CaptacionTO;
import co.gov.ideam.sirh.usuarios.agua.model.CaudalesConcesionadoTO;
import co.gov.ideam.sirh.usuarios.agua.model.Concesion;
import co.gov.ideam.sirh.usuarios.agua.model.ConcesionTO;
import co.gov.ideam.sirh.usuarios.agua.model.CondicionErrorTO;
import co.gov.ideam.sirh.usuarios.agua.model.ConsultarPermisosV;
import co.gov.ideam.sirh.usuarios.agua.model.ConsultasExtFuentesTO;
import co.gov.ideam.sirh.usuarios.agua.model.CriteriosBusquedaExternasTO;
import co.gov.ideam.sirh.usuarios.agua.model.CriteriosBusquedaTO;
import co.gov.ideam.sirh.usuarios.agua.model.DatosNodosTO;
import co.gov.ideam.sirh.usuarios.agua.model.DatosReporteAuditoriaTO;
import co.gov.ideam.sirh.usuarios.agua.model.DatosReporteTO;

import co.gov.ideam.sirh.usuarios.agua.model.GeneradorSeq;
import co.gov.ideam.sirh.usuarios.agua.model.PermisoVertimiento;
import co.gov.ideam.sirh.usuarios.agua.model.PermisoVertimientoTO;
import co.gov.ideam.sirh.usuarios.agua.model.PermisoVertimientoDetalle;
import co.gov.ideam.sirh.usuarios.agua.model.Predio;
import co.gov.ideam.sirh.usuarios.agua.model.PrediosTO;
import co.gov.ideam.sirh.usuarios.agua.model.PuntoVertimiento;
import co.gov.ideam.sirh.usuarios.agua.model.PuntoVertimientoPOJO;
import co.gov.ideam.sirh.usuarios.agua.model.PuntoVertimientoTO;
import co.gov.ideam.sirh.usuarios.agua.model.PuntoVertimientoTratamiento;
import co.gov.ideam.sirh.usuarios.agua.model.PuntoVertimientoTratamientoPOJO;
import co.gov.ideam.sirh.usuarios.agua.model.Representante;
import co.gov.ideam.sirh.usuarios.agua.model.RurtCapAforo;
import co.gov.ideam.sirh.usuarios.agua.model.RurtCaptacionComponentePOJO;
import co.gov.ideam.sirh.usuarios.agua.model.RurtCaptacionComponentes;
import co.gov.ideam.sirh.usuarios.agua.model.RurtCaptacionUso;
import co.gov.ideam.sirh.usuarios.agua.model.RurtCaptacionUsoPOJO;
import co.gov.ideam.sirh.usuarios.agua.model.SubttFunias;
import co.gov.ideam.sirh.usuarios.agua.model.SubttFuniasDocumentos;
import co.gov.ideam.sirh.usuarios.agua.model.SubttFuniasDocumentosPOJO;
import co.gov.ideam.sirh.usuarios.agua.model.SubttFuniasNivel;
import co.gov.ideam.sirh.usuarios.agua.model.SubttFuniasPOJO;
import co.gov.ideam.sirh.usuarios.agua.model.TipoModificacionTO;
import co.gov.ideam.sirh.usuarios.agua.model.UsuarioAgua;
import co.gov.ideam.sirh.usuarios.agua.model.UsuarioAguaTO;
import co.gov.ideam.sirh.usuarios.agua.model.ws.consultarPermisos.OE_ConsultarPermisos;
import co.gov.ideam.sirh.usuarios.agua.model.ws.entidades.DatosReporteWS;
import co.gov.ideam.sirh.usuarios.agua.model.ws.entidades.PermisoNovedad;
import co.gov.ideam.sirh.usuarios.agua.model.ws.entidades.PermisoWS;
import co.gov.ideam.sirh.usuarios.agua.model.ws.entidades.PredioWS;
import co.gov.ideam.sirh.usuarios.agua.model.ws.entidades.RepresentanteWS;
import co.gov.ideam.sirh.usuarios.agua.model.ws.entidades.UsuarioAguaWS;
import co.gov.ideam.sirh.usuarios.agua.model.ws.registrarNovedadPermiso.OE_RegistrarNovedadPermiso;
import co.gov.ideam.sirh.usuarios.agua.model.ws.registrarPermiso.OE_RegistrarPermiso;
import co.gov.ideam.sirh.usuarios.agua.model.ws.traspasarPermiso.OE_TraspasarPermiso;
import co.gov.ideam.sirh.util.Constantes;
import co.gov.ideam.sirh.util.IdeamException;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import javax.sql.DataSource;

@Stateless(name = "UsuariosAgua", mappedName = "Sirh-UsuariosAgua")
@Remote
public class UsuariosAguaBean implements UsuariosAguaEJB {

    @PersistenceContext(unitName = "SirhPU")
    private EntityManager em;
    @EJB
    private RegistroRemotoLocal registroRemoto;
    @EJB
    private ParametrosEJB parametrosService;
    @EJB
    private CalidadEJB calidadService;


    DatosReporteTO dTo = new DatosReporteTO();

    /**
     * Lista estatica para almacenar los tipos de modificacion que se
     * pueden realizar a las concesiones y permisos de vertimiento
     */
    private static List<TipoModificacionTO> listaTiposModificacion;

    /**
     * Lista estatica para almacenar las condiciones de error que se
     * pueden generar
     */
    private static List<CondicionErrorTO> listaCondicionesError;

    public UsuariosAguaBean() {
    }

    /**
     * Retorna los datos de gestion de especialistas requeridos para generar
     * la grafica
     * @param idAutoridad
     * @return
     * @throws IdeamException
     */
    public String[][] getGestionEspecialistas(Long idAutoridad) throws IdeamException {
        dTo.setIdAutoridad(idAutoridad);
        return getDatosReporte(dTo.getGestionEspecialistas());
    }

    /**
     * Retorna los datos de investigacion de especialistas requeridos para generar
     * la grafica
     * @param idAutoridad
     * @return
     * @throws IdeamException
     */
    public String[][] getInvestigacionEspecialistas(Long idAutoridad) throws IdeamException {
        dTo.setIdAutoridad(idAutoridad);
        return getDatosReporte(dTo.getInvestigacionEspecialistas());
    }

    /**
     * Retorna los datos de formacion de especialistas requeridos para generar
     * la grafica
     * @param idAutoridad
     * @return
     * @throws IdeamException
     */
    public String[][] getFormacionEspecialistas(Long idAutoridad) throws IdeamException {
        dTo.setIdAutoridad(idAutoridad);
        return getDatosReporte(dTo.getFormacionEspecialistas());
    }


    public List<RowTO> getDetalleCompletitud(CondicionErrorTO condicion,
                                             Autoridades autoridad) throws IdeamException,
                                                                           NamingException,
                                                                           SQLException {
        List<RowTO> lista = new ArrayList<RowTO>();
        String sentencia = condicion.getSqlDetalle();

        System.out.println("Detalle-------------------------------> " + sentencia);
        String cadenaSQL =
            sentencia.replaceAll("\\?1", autoridad.getId().toString());
        System.out.println("Detalle 2 ------------------------------------------------------- ->" + cadenaSQL);


        //Session session = (Session) em.getDelegate();
        //SessionFactoryImplementor sfi = (SessionFactoryImplementor) session.getSessionFactory();
        //ConnectionProvider cp = sfi.getConnectionProvider();
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet query = null;
        ResultSetMetaData metaData = null;
        try {
            //System.out.println(">>>>>>>>>>>>>>> entro a fillReportFromDataBase");
            Context c = new InitialContext();
            DataSource dataSource =
                (DataSource)c.lookup("java:jboss/datasources/sirhDS");
            //System.out.println(">>>>>>>>>>>>>>> entro lookup");
            con = dataSource.getConnection();

            int totalColumnas = 0;

            statement = con.prepareStatement(cadenaSQL);
            query = statement.executeQuery();

            metaData = query.getMetaData();
            totalColumnas = metaData.getColumnCount();

            if (totalColumnas == 0) {
                throw new IdeamException("No existen columnas para mostrar");
            }

            //System.out.println("Autoridad 3" + autoridad.getId().longValue());
            int indiceFila = 1;
            while (query.next()) {
                RowTO row = new RowTO();
                row.setIndice(indiceFila++);
                for (int i = 1; i <= totalColumnas; i++) {
                    Object value = query.getObject(i);
                    String nombreColumna = metaData.getColumnName(i);
                    ColumnTO col = new ColumnTO(nombreColumna);
                    col.setIndice(i);
                    row.addCell(col, value, autoridad);
                }
                lista.add(row);
            }
        } catch (SQLException e) {
            //System.out.println(e.getErrorCode() + " " + e.getMessage());
            e.printStackTrace(System.out);
            throw new IdeamException("Error ejecutando la siguiente sentencia :: " +
                                     sentencia + " :: Error => " +
                                     e.getMessage());
        } finally {
            try {
                if (query != null) {
                    query.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                throw new IdeamException("Error ejecutando la siguiente sentencia :: " +
                                         sentencia + " :: Error => " +
                                         e.getMessage());
            }
        }
        
      System.out.println("lista  sqlllllll 2 ------------------------------------------------------- ->" + lista);

        return lista;
    }

    private Long ejecutarConsulta(String sql,
                                  Autoridades autoridad) throws IdeamException {
        Query query = em.createNativeQuery(sql);
        query.setParameter(1, autoridad.getId().intValue());
        Number countResult = (Number)query.getSingleResult();
        Long valor = countResult.longValue();
        return valor;
    }

    public List<CondicionErrorTO> getTotalesCondicionesError(Autoridades autoridad) throws IdeamException {
        List lista = this.getAllCondicionesError();
        List<CondicionErrorTO> retorno = new ArrayList<CondicionErrorTO>();

        if (lista == null || lista.size() == 0) {
            throw new IdeamException("No existen condiciones registradas");
        }
        Iterator<CondicionErrorTO> it = lista.iterator();
        while (it.hasNext()) {
            CondicionErrorTO cond = it.next();

            Long total = this.ejecutarConsulta(cond.getSqlTotal(), autoridad);
            Long error = this.ejecutarConsulta(cond.getSqlError(), autoridad);

            CondicionErrorTO consulta =
                new CondicionErrorTO(cond.getCategoria(), cond.getTitulo(),
                                     total, error);
            consulta.setSqlDetalle(cond.getSqlDetalle());
            retorno.add(consulta);
        }
        return retorno;
    }

    public List<CondicionErrorTO> getAllCondicionesError() throws IdeamException {
        if (listaCondicionesError == null ||
            listaCondicionesError.size() == 0) {
            listaCondicionesError = new ArrayList<CondicionErrorTO>();
            CondicionErrorTO usuariosSinDireccion =
                new CondicionErrorTO("Usuarios del Agua",
                                     "Usuarios sin informaci�n de direcci�n de correspondencia",
                                     "select count(*) from rurt_usuarios_agua where id_autoridad = ?1",
                                     "select count(*) from rurt_usuarios_agua where id_autoridad = ?1 and direccion is null");
            usuariosSinDireccion.setSqlDetalle("select nombre, apellido, num_documento as documento from rurt_usuarios_agua where id_autoridad = ?1 and direccion is null");

            // CondicionErrorTO usuariosSinTelefono = new CondicionErrorTO();
            /*CondicionErrorTO usuariosSinTelefono = new CondicionErrorTO("Usuarios del Agua",
                                                                "Usuarios Sin Tel�fono",
                                                                "select count(*) from rurt_usuarios_agua where id_autoridad = ?1",
                                                                "select count(*) from rurt_usuarios_agua where id_autoridad = ?1 and telefono is null");
            usuariosSinTelefono.setSqlDetalle("select nombre, apellido, num_documento as documento from rurt_usuarios_agua where id_autoridad = ?1 and telefono is null");
            */
            CondicionErrorTO usuariosSinRepresentante =
                new CondicionErrorTO("Usuarios del Agua",
                                     "Usuarios de naturaleza jur�dica sin informaci�n de representante legal",
                                     "select count(*) from rurt_usuarios_agua where id_autoridad = ?1 and tipo_persona <> 5",
                                     "select count(*) from   rurt_usuarios_agua where id_autoridad = ?1 and tipo_persona <> 5 and id not in (select distinct id_usuario from rurt_representantes)");
            usuariosSinRepresentante.setSqlDetalle("select nombre, apellido, num_documento as documento from rurt_usuarios_agua where id_autoridad = ?1 and tipo_persona <> 5 and id not in (select distinct id_usuario from rurt_representantes)");

            CondicionErrorTO usuariosSinPredios =
                new CondicionErrorTO("Usuarios del Agua",
                                     "Usuarios sin predios asociados",
                                     "select count(*) from rurt_usuarios_agua where id_autoridad = ?1",
                                     "select count(*) from rurt_usuarios_agua where id_autoridad = ?1 and id not in (select distinct id_usuario from rurt_predios) ");
            usuariosSinPredios.setSqlDetalle("select nombre, apellido, num_documento as documento from rurt_usuarios_agua where id_autoridad = ?1 and id not in (select distinct id_usuario from rurt_predios)");

            CondicionErrorTO prediosSinMatricula =
                new CondicionErrorTO("Predios",
                                     "Predios sin matricula inmobiliaria",
                                     "select count(*) from rurt_predios where id_usuario in (select distinct id from rurt_usuarios_agua where id_autoridad = ?1)",
                                     "select count(*) from rurt_predios where id_usuario in (select distinct id from rurt_usuarios_agua where id_autoridad = ?1) and matricula_catastral is null");
            prediosSinMatricula.setSqlDetalle("select nombre_usuario as usuario, nombre_predio as predio, cedula_catastral from rurv_predios where id in ( select id from rurt_predios where id_usuario in (select distinct id from rurt_usuarios_agua where id_autoridad = ?1) and matricula_catastral is null )");

            CondicionErrorTO prediosSinClasificacion =
                new CondicionErrorTO("Predios",
                                     "Predios sin informaci�n  sobre  Clasificaci�n del Suelo",
                                     "select count(*) from rurt_predios where id_usuario in (select distinct id from rurt_usuarios_agua where id_autoridad = ?1)",
                                     "select count(*) from rurt_predios where id_usuario in (select distinct id from rurt_usuarios_agua where id_autoridad = ?1) and tipo_suelo is null");
            prediosSinClasificacion.setSqlDetalle("select nombre_usuario as usuario, nombre_predio as predio, cedula_catastral from rurv_predios where id in ( select id from rurt_predios where id_usuario in (select distinct id from rurt_usuarios_agua where id_autoridad = ?1) and tipo_suelo is null )");

            CondicionErrorTO prediosSinCoordenadas =
                new CondicionErrorTO("Predios",
                                     "Predios sin informaci�n de coordenadas geogr�ficas",
                                     "select count(*) from rurt_predios where id_usuario in (select distinct id from rurt_usuarios_agua where id_autoridad = ?1)",
                                     "select count(*) from rurt_predios where id_usuario in (select distinct id from rurt_usuarios_agua where id_autoridad = ?1) and (lat_grados is null or lat_minutos is null or lat_segundos is null or long_grados is null or long_minutos is null or long_segundos is null)");
            prediosSinCoordenadas.setSqlDetalle("select nombre_usuario as usuario, nombre_predio as predio, cedula_catastral from rurv_predios where id in ( select id from rurt_predios where id_usuario in (select distinct id from rurt_usuarios_agua where id_autoridad = ?1) and (lat_grados is null or lat_minutos is null or lat_segundos is null or long_grados is null or long_minutos is null or long_segundos is null) )");

            CondicionErrorTO prediosSinActos =
                new CondicionErrorTO("Predios", "Predios sin informaci�n de actos administrativos asociados (Concesiones o permisos de vertimiento)",
                                     "select count(*) from rurt_predios where id_usuario in (select distinct id from rurt_usuarios_agua where id_autoridad = ?1)",
                                     "select count(*) from rurt_predios where id_usuario in (select distinct id from rurt_usuarios_agua where id_autoridad = ?1) and id not in (select distinct id_predio from rurt_concesiones) and id not in (select distinct id_predio from rurt_permisos_vertimientos)");
            prediosSinActos.setSqlDetalle("select nombre_usuario as usuario, nombre_predio as predio, cedula_catastral from rurv_predios where id in ( select id from rurt_predios where id_usuario in (select distinct id from rurt_usuarios_agua where id_autoridad = ?1) and id not in (select distinct id_predio from rurt_concesiones) and id not in (select distinct id_predio from rurt_permisos_vertimientos) )");

            CondicionErrorTO concesionesSinPlanos =
                new CondicionErrorTO("Concesiones",
                                     "Concesiones sin informaci�n de resoluci�n de aprobaci�n de planos",
                                     "select count(*) from   rurt_concesiones where  id_predio in (select id from rurt_predios where id_usuario in (select distinct id from rurt_usuarios_agua where id_autoridad = ?1))",
                                     "select count(*) from   rurt_concesiones where  id_predio in (select id from rurt_predios where id_usuario in (select distinct id from rurt_usuarios_agua where id_autoridad = ?1)) and num_res_planos is null");
            concesionesSinPlanos.setSqlDetalle("select usuario, predio, num_expediente, num_res_caudal from rurv_concesiones where id in ( select id from rurt_concesiones where  id_predio in (select id from rurt_predios where id_usuario in (select distinct id from rurt_usuarios_agua where id_autoridad = ?1)) and num_res_planos is null )");

            CondicionErrorTO concesionesSinObras =
                new CondicionErrorTO("Concesiones",
                                     "Concesiones sin informaci�n de resoluci�n de aprobaci�n de obras",
                                     "select count(*) from   rurt_concesiones where  id_predio in (select id from rurt_predios where id_usuario in (select distinct id from rurt_usuarios_agua where id_autoridad = ?1))",
                                     "select count(*) from   rurt_concesiones where  id_predio in (select id from rurt_predios where id_usuario in (select distinct id from rurt_usuarios_agua where id_autoridad = ?1)) and num_res_obras is null");
            concesionesSinObras.setSqlDetalle("select usuario, predio, num_expediente, num_res_caudal from rurv_concesiones where id in ( select id from   rurt_concesiones where  id_predio in (select id from rurt_predios where id_usuario in (select distinct id from rurt_usuarios_agua where id_autoridad = ?1)) and num_res_obras is null )");

            CondicionErrorTO concesionesSinCaptaciones =
                new CondicionErrorTO("Concesiones",
                                     "Concesiones sin informaci�n de captaciones asociadas",
                                     "select count(*) from   rurt_concesiones where  id_predio in (select id from rurt_predios where id_usuario in (select distinct id from rurt_usuarios_agua where id_autoridad = ?1))",
                                     "select count(*) from   rurt_concesiones where  id_predio in (select id from rurt_predios where id_usuario in (select distinct id from rurt_usuarios_agua where id_autoridad = ?1)) and id not in (select distinct id_concesion from rurt_captacion) ");
            concesionesSinCaptaciones.setSqlDetalle("select usuario, predio, num_expediente, num_res_caudal from rurv_concesiones where id in ( select id from rurt_concesiones where  id_predio in (select id from rurt_predios where id_usuario in (select distinct id from rurt_usuarios_agua where id_autoridad = ?1)) and id not in (select distinct id_concesion from rurt_captacion) )");

            CondicionErrorTO permisosSinResolucionInicio =
                new CondicionErrorTO("Permisos de vertimiento",
                                     "Permisos sin informaci�n  sobre Resoluci�n Inicio Tr�mite",
                                     "select count(*) from rurt_permisos_vertimientos where  id_predio in (select id from rurt_predios where id_usuario in (select distinct id from rurt_usuarios_agua where id_autoridad = ?1))",
                                     "select count(*) from   rurt_permisos_vertimientos where  id_predio in (select id from rurt_predios where id_usuario in (select distinct id from rurt_usuarios_agua where id_autoridad = ?1)) and num_res_inicio_tramite is null ");
            permisosSinResolucionInicio.setSqlDetalle("select usuario, predio, num_expediente from rurv_permisos where id in ( select id from   rurt_permisos_vertimientos where  id_predio in (select id from rurt_predios where id_usuario in (select distinct id from rurt_usuarios_agua where id_autoridad = ?1)) and num_res_inicio_tramite is null )");

            // CondicionErrorTO  permisosNaturalesSinPC = new CondicionErrorTO();
            /* CondicionErrorTO  permisosNaturalesSinPC = new CondicionErrorTO("Permisos de vertimiento",
                                                        "Permisos de usuarios naturales in datos de Plan de Cumplimiento",
                                                        "select count(*) from rurt_permisos_vertimientos where  id_predio in (select id from rurt_predios where id_usuario in (select distinct id from rurt_usuarios_agua where id_autoridad = ?1 and tipo_persona = 5 ))",
                                                        "select count(*) from   rurt_permisos_vertimientos where  id_predio in (select id from rurt_predios where id_usuario in (select distinct id from rurt_usuarios_agua where id_autoridad = ?1 and tipo_persona = 5)) and num_res_solic_plan_cumpl is null ");
            permisosNaturalesSinPC.setSqlDetalle("select usuario, predio, num_expediente from rurv_permisos where id in ( select id from rurt_permisos_vertimientos where  id_predio in (select id from rurt_predios where id_usuario in (select distinct id from rurt_usuarios_agua where id_autoridad = ?1 and tipo_persona = 5)) and num_res_solic_plan_cumpl is null  )");
            */
            CondicionErrorTO permisosESPSinPSMV =
                new CondicionErrorTO("Permisos de vertimiento",
                                     "Permisos de vertimiento asociadas a empresas de servicios p�blicos, sin informaci�n del PSMV ",
                                     "select count(*) from rurt_permisos_vertimientos where  id_predio in (select id from rurt_predios where id_usuario in (select distinct id from rurt_usuarios_agua where id_autoridad = ?1 and tipo_persona = 2 ))",
                                     "select count(*) from   rurt_permisos_vertimientos where  id_predio in (select id from rurt_predios where id_usuario in (select distinct id from rurt_usuarios_agua where id_autoridad = ?1 and tipo_persona = 2)) and resolucion_psmv is null ");
            permisosESPSinPSMV.setSqlDetalle("select usuario, predio, num_expediente from rurv_permisos where id in ( select id from   rurt_permisos_vertimientos where  id_predio in (select id from rurt_predios where id_usuario in (select distinct id from rurt_usuarios_agua where id_autoridad = ?1 and tipo_persona = 2)) and resolucion_psmv is null )");

            CondicionErrorTO permisosSinAprobPlanos =
                new CondicionErrorTO("Permisos de vertimiento",
                                     "Permisos sin informaci�n  de resoluci�n de aprobaci�n de planos",
                                     "select count(*) from rurt_permisos_vertimientos where  id_predio in (select id from rurt_predios where id_usuario in (select distinct id from rurt_usuarios_agua where id_autoridad = ?1))",
                                     "select count(*) from   rurt_permisos_vertimientos where  id_predio in (select id from rurt_predios where id_usuario in (select distinct id from rurt_usuarios_agua where id_autoridad = ?1)) and num_res_aprob_planos is null ");
            permisosSinAprobPlanos.setSqlDetalle("select usuario, predio, num_expediente from rurv_permisos where id in ( select id from   rurt_permisos_vertimientos where  id_predio in (select id from rurt_predios where id_usuario in (select distinct id from rurt_usuarios_agua where id_autoridad = ?1)) and num_res_aprob_planos is null )");

            CondicionErrorTO permisosSinAprobObras =
                new CondicionErrorTO("Permisos de vertimiento",
                                     "Permisos sin informaci�n  de resoluci�n de aprobaci�n de obras",
                                     "select count(*) from rurt_permisos_vertimientos where  id_predio in (select id from rurt_predios where id_usuario in (select distinct id from rurt_usuarios_agua where id_autoridad = ?1))",
                                     "select count(*) from   rurt_permisos_vertimientos where  id_predio in (select id from rurt_predios where id_usuario in (select distinct id from rurt_usuarios_agua where id_autoridad = ?1)) and num_res_aprob_obras is null ");
            permisosSinAprobObras.setSqlDetalle("select usuario, predio, num_expediente from rurv_permisos where id in ( select id from rurt_permisos_vertimientos where  id_predio in (select id from rurt_predios where id_usuario in (select distinct id from rurt_usuarios_agua where id_autoridad = ?1)) and num_res_aprob_obras is null  )");

            CondicionErrorTO permisosSinVertimientos =
                new CondicionErrorTO("Permisos de vertimiento",
                                     "Permisos de vertimiento sin asociar vertimientos",
                                     "select count(*) from rurt_permisos_vertimientos where  id_predio in (select id from rurt_predios where id_usuario in (select distinct id from rurt_usuarios_agua where id_autoridad = ?1))",
                                     "select count(*) from rurt_permisos_vertimientos where  id_predio in (select id from rurt_predios where id_usuario in (select distinct id from rurt_usuarios_agua where id_autoridad = ?1)) and id not in (select distinct id_permiso_vertimiento from rurt_punto_vertimiento) ");
            permisosSinVertimientos.setSqlDetalle("select usuario, predio, num_expediente from rurv_permisos where id in ( select id from rurt_permisos_vertimientos where  id_predio in (select id from rurt_predios where id_usuario in (select distinct id from rurt_usuarios_agua where id_autoridad = ?1)) and id not in (select distinct id_permiso_vertimiento from rurt_punto_vertimiento)  )");


            CondicionErrorTO concesionesSinCaudalConcesionado =
                new CondicionErrorTO("Concesiones",
                                     "Concesiones sin informaci�n de caudal concesionado",
                                     "select count(*) from rurt_concesiones where  id_predio in (select id from rurt_predios where id_usuario in (select distinct id from rurt_usuarios_agua where id_autoridad = ?1))",
                                     "select count(*) from rurt_concesiones where  id_predio in (select id from rurt_predios where id_usuario in (select distinct id from rurt_usuarios_agua where id_autoridad = ?1)) and (caudal_concesionado is null or caudal_concesionado=0) ");
            concesionesSinCaudalConcesionado.setSqlDetalle("select usuario, predio, num_expediente from rurv_concesiones where id in ( select id from rurt_concesiones where  id_predio in (select id from rurt_predios where id_usuario in (select distinct id from rurt_usuarios_agua where id_autoridad = ?1)) and (caudal_concesionado is null or caudal_concesionado=0) )");


            CondicionErrorTO permisosSinCaudalVertido =
                new CondicionErrorTO("Permisos de vertimiento ",
                                     "Permisos de vertimiento sin informaci�n de caudal autorizado",
                                     "select count(*) from rurt_permisos_vertimientos where  id_predio in (select id from rurt_predios where id_usuario in (select distinct id from rurt_usuarios_agua where id_autoridad = ?1))",
                                     "select count(*) from rurt_permisos_vertimientos where  id_predio in (select id from rurt_predios where id_usuario in (select distinct id from rurt_usuarios_agua where id_autoridad = ?1)) and (caudal  is null or caudal=0) ");
            permisosSinCaudalVertido.setSqlDetalle("select usuario, predio, num_expediente from rurv_permisos where id in ( select id from rurt_permisos_vertimientos where  id_predio in (select id from rurt_predios where id_usuario in (select distinct id from rurt_usuarios_agua where id_autoridad = ?1)) and (caudal  is null or caudal=0) )");


            CondicionErrorTO captacionesSinOfertaTotal =
                new CondicionErrorTO("Captaciones ",
                                     "Captaciones sin informaci�n de oferta total de la fuente en el punto de captaci�n",
                                     "select count(*) from rurt_captacion where  id_concesion in (  select id from rurt_concesiones where  id_predio in (select id from rurt_predios where id_usuario in (select distinct id from rurt_usuarios_agua where id_autoridad = ?1)))",
                                     "select count(*) from rurt_captacion where  id_concesion in (  select id from rurt_concesiones where  id_predio in (select id from rurt_predios where id_usuario in (select distinct id from rurt_usuarios_agua where id_autoridad = ?1))) and (oferta_hidrica_total is null or oferta_hidrica_total =0) ");
            captacionesSinOfertaTotal.setSqlDetalle("select usuario, predio, num_expediente from sirh_captaciones_v where id in ( select id from rurt_captacion where  id_concesion in (  select id from rurt_concesiones where   id_predio in (select id from rurt_predios where id_usuario in (select distinct id from rurt_usuarios_agua where id_autoridad = ?1))) and (oferta_hidrica_total is null or oferta_hidrica_total =0))");


            CondicionErrorTO captacionesSinOfertaDisp =
                new CondicionErrorTO("Captaciones ",
                                     "Captaciones sin informaci�n de oferta disponible de la fuente en el punto de captaci�n",
                                     "select count(*) from rurt_captacion where  id_concesion in (  select id from rurt_concesiones where  id_predio in (select id from rurt_predios where id_usuario in (select distinct id from rurt_usuarios_agua where id_autoridad = ?1)))",
                                     "select count(*) from rurt_captacion where  id_concesion in (  select id from rurt_concesiones where  id_predio in (select id from rurt_predios where id_usuario in (select distinct id from rurt_usuarios_agua where id_autoridad = ?1))) and (oferta_disponible is null or oferta_disponible =0) ");
            captacionesSinOfertaDisp.setSqlDetalle("select usuario, predio, num_expediente from sirh_captaciones_v where id in ( select id from rurt_captacion where  id_concesion in (  select id from rurt_concesiones where   id_predio in (select id from rurt_predios where id_usuario in (select distinct id from rurt_usuarios_agua where id_autoridad = ?1))) and (oferta_disponible is null or oferta_disponible =0))");


            CondicionErrorTO captacionesSinCaudalDiseno =
                new CondicionErrorTO("Captaciones ",
                                     "Captaciones sin informaci�n de caudal de dise�o",
                                     "select count(*) from rurt_captacion where  id_concesion in (  select id from rurt_concesiones where  id_predio in (select id from rurt_predios where id_usuario in (select distinct id from rurt_usuarios_agua where id_autoridad = ?1)))",
                                     "select count(*) from rurt_captacion where  id_concesion in (  select id from rurt_concesiones where  id_predio in (select id from rurt_predios where id_usuario in (select distinct id from rurt_usuarios_agua where id_autoridad = ?1))) and (caudal_disegno  is null or caudal_disegno =0) ");
            captacionesSinCaudalDiseno.setSqlDetalle("select usuario, predio, num_expediente from sirh_captaciones_v where id in ( select id from rurt_captacion where  id_concesion in (  select id from rurt_concesiones where   id_predio in (select id from rurt_predios where id_usuario in (select distinct id from rurt_usuarios_agua where id_autoridad = ?1))) and (oferta_disponible is null or caudal_disegno =0))");

            CondicionErrorTO captacionesSinUsos =
                new CondicionErrorTO("Captaciones ",
                                     "Captaciones sin informaci�n de usos",
                                     "select count(*) from rurt_captacion where  id_concesion in (  select id from rurt_concesiones where  id_predio in (select id from rurt_predios where id_usuario in (select distinct id from rurt_usuarios_agua where id_autoridad = ?1)))",
                                     "select count(*) from rurt_captacion where  id_concesion in (  select id from rurt_concesiones where  id_predio in (select id from rurt_predios where id_usuario in (select distinct id from rurt_usuarios_agua where id_autoridad = ?1)))  and (id  not in  (select id_captacion from RURT_CAPTACION_USO )) ");
            captacionesSinUsos.setSqlDetalle("select usuario, predio, num_expediente from sirh_captaciones_v where id in ( select id from rurt_captacion where  id_concesion in (  select id from rurt_concesiones where   id_predio in (select id from rurt_predios where id_usuario in (select distinct id from rurt_usuarios_agua where id_autoridad = ?1))) and   (id  not in  (select id_captacion from RURT_CAPTACION_USO )))");


            CondicionErrorTO fuentesSinLongitud =
                new CondicionErrorTO("Fuentes ",
                                     "Fuentes sin informaci�n de longitud en los tramos",
                                     "select count(*) from fntt_fuente where cod_autoridad = ?1",
                                     "select count(*) from fntt_fuente where  cod_autoridad = ?1 and id in (select id_fuente from fntt_tramo  where (longitud is null or longitud=0) ) ");
            fuentesSinLongitud.setSqlDetalle(" select nombre, descripcion from  sirh_fuente_tramo_v_ where id in ( select id from fntt_fuente where  cod_autoridad = ?1 and  id in (select id_fuente from fntt_tramo where (longitud is null or longitud=0) ))");


            CondicionErrorTO fuentesSinTramos =
                new CondicionErrorTO("Fuentes ",
                                     "Fuentes sin delimitaci�n de tramos ",
                                     "select count(*) from fntt_fuente where cod_autoridad = ?1",
                                     "select count(*) from fntt_fuente where  cod_autoridad = ?1 and id not in (select id_fuente from fntt_tramo ) ");
            fuentesSinTramos.setSqlDetalle(" select nombre, descripcion from  sirh_fuente_tramo_v_ where id in (  select id from fntt_fuente where  cod_autoridad = ?1 and id not in (select id_fuente from fntt_tramo ))");

            /* CondicionErrorTO fuentesSinCodCuenca = new CondicionErrorTO("Fuentes ",
                                                        "Fuentes sin codigo cuenca",
                                                        "select count(*) from fntt_fuente where cod_autoridad = ?1",
                                                        "select count(*) from fntt_fuente where  cod_autoridad = ?1 and  (codigo_cuencaaa is null or codigo_cuencaaa='0') ");
            fuentesSinCodCuenca.setSqlDetalle(" select nombre, descripcion from  sirh_fuente_tramo_v_ where id in ( select id from fntt_fuente where  cod_autoridad = ?1 and (codigo_cuencaaa is null or codigo_cuencaaa='0') )");
*/
            CondicionErrorTO puntosSinMuestras =
                new CondicionErrorTO("Calidad ",
                                     "Puntos de monitoreo sin informaci�n de muestras asociadas",
                                     " select count(*) from calt_punto_monitoreo where id_autoridad = ?1",
                                     " select count(*) from calt_punto_monitoreo where id_autoridad = ?1 and id not in (select id_punto from calt_muestra ) ");
            puntosSinMuestras.setSqlDetalle(" select nombre, tipo_punto, ubicacion from  calt_puntosmonitoreo_v where id in (  select id from calt_punto_monitoreo where  id_autoridad = ?1 and id not in (select id_punto from calt_muestra ))");


            CondicionErrorTO muestrasSinCaudal =
                new CondicionErrorTO("Calidad ",
                                     "Muestras de calidad de agua sin informaci�n de caudal aforado",
                                     "  select count(*) from calt_muestra where id_punto in (select id from calt_punto_monitoreo where id_autoridad = ?1 )",
                                     "  select count(*) from calt_muestra where id_punto in (select id from calt_punto_monitoreo where id_autoridad = ?1) and (caudal is null or caudal =0) ");
            muestrasSinCaudal.setSqlDetalle("select nombre_punto, tipo_muestra, fecha_muestreo, hora_muestra from  calt_muestras_v  where id in (   select id  from calt_muestra  where id_punto in ( select id from calt_punto_monitoreo where id_autoridad = ?1 ) and (caudal is null or caudal =0) )");


            CondicionErrorTO muestrasSinMediciones =
                new CondicionErrorTO("Calidad ",
                                     "Muestras de calidad de agua sin informaci�n de par�metros asociados",
                                     "  select count(*) from calt_muestra where id_punto in (select id from calt_punto_monitoreo where id_autoridad = ?1)",
                                     "  select count(*) from calt_muestra where id_punto in (select id from calt_punto_monitoreo where id_autoridad = ?1) and id not in (select id_muestra from calt_medicion ) ");
            muestrasSinMediciones.setSqlDetalle("   select nombre_punto, tipo_muestra, fecha_muestreo, hora_muestra from  calt_muestras_v  where id in ( select id  from calt_muestra  where id_punto in ( select id from calt_punto_monitoreo where id_autoridad = ?1 )and id not in (select id_muestra from calt_medicion  ))");

            CondicionErrorTO vertimientosSinCaudal =
                new CondicionErrorTO("Vertimientos ",
                                     " Vertimientos sin informaci�n de caudal de dise�o del sistema de tratamiento de agua residual.",
                                     " Select count(*) from rurt_punto_vertimiento where  id_permiso_vertimiento in (  select id from rurt_permisos_vertimientos " +
                                     " where  id_predio in (select id from rurt_predios where id_usuario in (select distinct id from rurt_usuarios_agua where id_autoridad = ?1)))",
                                     "select count(*) from rurt_punto_vertimiento where  id_permiso_vertimiento in (  select id from rurt_permisos_vertimientos " +
                                     " where  id_predio in (select id from rurt_predios where id_usuario in (select distinct id from rurt_usuarios_agua where id_autoridad = ?1)))" +
                                     " and (caudal_disegno  is null or caudal_disegno =0) ");


            vertimientosSinCaudal.setSqlDetalle(" select usuario, predio, num_expediente from sirh_vertimientos_v where id in ( select id from rurt_punto_vertimiento " +
                                                "  where  id_permiso_vertimiento in (  select id from rurt_permisos_vertimientos " +
                                                "                                       where  id_predio in " +
                                                "                                         (select id from rurt_predios " +
                                                "                                           where id_usuario in" +
                                                "                                           (select distinct id " +
                                                "                                            from rurt_usuarios_agua " +
                                                "                                            where id_autoridad = ?1" +
                                                "                                           )" +
                                                "                                         )" +
                                                "                                     )" +
                                                "  and (caudal_disegno  is null or caudal_disegno =0) " +
                                                " )");


            listaCondicionesError.add(fuentesSinLongitud);
            listaCondicionesError.add(fuentesSinTramos);
            //  listaCondicionesError.add(fuentesSinCodCuenca);

            listaCondicionesError.add(usuariosSinDireccion);
            //listaCondicionesError.add(usuariosSinTelefono);
            listaCondicionesError.add(usuariosSinRepresentante);
            listaCondicionesError.add(usuariosSinPredios);
            listaCondicionesError.add(prediosSinMatricula);
            listaCondicionesError.add(prediosSinClasificacion);
            listaCondicionesError.add(prediosSinCoordenadas);
            listaCondicionesError.add(prediosSinActos);
            listaCondicionesError.add(concesionesSinPlanos);
            listaCondicionesError.add(concesionesSinObras);
            listaCondicionesError.add(concesionesSinCaptaciones);
            listaCondicionesError.add(concesionesSinCaudalConcesionado);
            listaCondicionesError.add(captacionesSinOfertaTotal);
            listaCondicionesError.add(captacionesSinOfertaDisp);
            listaCondicionesError.add(captacionesSinCaudalDiseno);

            listaCondicionesError.add(captacionesSinUsos);
            listaCondicionesError.add(permisosSinResolucionInicio);
            //listaCondicionesError.add(permisosNaturalesSinPC);
            listaCondicionesError.add(permisosESPSinPSMV);
            listaCondicionesError.add(permisosSinAprobPlanos);
            listaCondicionesError.add(permisosSinAprobObras);
            listaCondicionesError.add(permisosSinVertimientos);
            listaCondicionesError.add(permisosSinCaudalVertido);
            listaCondicionesError.add(vertimientosSinCaudal);
            listaCondicionesError.add(puntosSinMuestras);
            listaCondicionesError.add(muestrasSinCaudal);
            listaCondicionesError.add(muestrasSinMediciones);


        }
        return listaCondicionesError;
    }

    public List<TipoModificacionTO> getAllTiposModificacion() throws IdeamException {
        if (listaTiposModificacion == null ||
            listaTiposModificacion.size() == 0) {
            listaTiposModificacion = new ArrayList<TipoModificacionTO>();

            TipoModificacionTO modificacion = new TipoModificacionTO();
            modificacion.setCodigo(TipoModificacionTO.MODIFICACION);
            modificacion.setNombre("Modificaci�n");
            modificacion.setDescripcion("Por favor ingrese los datos del nuevo acto administrativo con el que se realiza la modificaci�n");

            TipoModificacionTO renovacion = new TipoModificacionTO();
            renovacion.setCodigo(TipoModificacionTO.RENOVACION);
            renovacion.setNombre("Renovaci�n");
            renovacion.setDescripcion("Por favor ingrese los datos del nuevo acto administrativo con el que se realiza la renovaci�n");

            TipoModificacionTO traspaso = new TipoModificacionTO();
            traspaso.setCodigo(TipoModificacionTO.TRASPASO);
            traspaso.setNombre("Traspaso");
            traspaso.setDescripcion("Para realizar esta acci�n, usted debi� haber verificado que el usuario y predio al que se le realiza el traspaso ya exista en el sistema. Si no existen por favor reg�strelos");

            TipoModificacionTO revocacion = new TipoModificacionTO();
            revocacion.setCodigo(TipoModificacionTO.REVOCACION);
            revocacion.setNombre("Revocaci�n");
            revocacion.setDescripcion("Por favor ingrese los datos del nuevo acto administrativo con el que se realiza la revocaci�n");

            listaTiposModificacion.add(modificacion);
            listaTiposModificacion.add(renovacion);
            listaTiposModificacion.add(traspaso);
            listaTiposModificacion.add(revocacion);
        }
        return listaTiposModificacion;
    }

    public Double getDemandaTramo(FnttTramo tramo) throws IdeamException {
        Query query = em.createNamedQuery("RurtCaptacion.totalCaudalTramo");
        query.setParameter("codigoTramo", tramo.getId());
        query.setParameter("codigoFuente", tramo.getIdFuente().getId());
        query.setParameter("tipoFuente", Constantes.TIPO_FUENTE_TOTALIZAR);
        Number total = (Number)query.getSingleResult();
        if (total == null) {
            return 0D;
        } else {
            return total.doubleValue();
        }
    }

    public List<Concesion> getConcesionesByEstado(String estado) throws IdeamException {
        return null;
    }

    public Concesion getConcesionByResolucion(String numeroResolucion) throws IdeamException {
        try {
            Query query = em.createNamedQuery("Concesion.findByResolucion");
            query.setParameter("resolucionCaudal",
                               numeroResolucion.toUpperCase());
            query.setParameter("resolucionCaudal",
                               numeroResolucion.toUpperCase());
            return (Concesion)query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<PrediosTO> getAllPredios(CriteriosBusquedaTO criterioBusqueda,
                                         Autoridades autoridad) throws IdeamException {
        if (criterioBusqueda == null && autoridad == null) {
            Query query = em.createNamedQuery("PrediosTO.findAllRevision");
            return query.getResultList();
        } else if (criterioBusqueda == null ||
                   criterioBusqueda.getClasificacionSuelo() == null) {
            Query query = em.createNamedQuery("PrediosTO.findAll");
            query.setParameter("codigoAutoridad",
                               autoridad.getId().longValue());
            return query.getResultList();
        } else {
            Query query = em.createNamedQuery("PrediosTO.findBySuelo");
            query.setParameter("tipoSuelo",
                               criterioBusqueda.getClasificacionSuelo().getValor());
            query.setParameter("codigoAutoridad",
                               autoridad.getId().longValue());
            return query.getResultList();
        }
    }

    public String[][] getUbicacionUsuarios(Long idAutoridad) throws IdeamException {
        //System.out.println("---------------------------getUbicacionUsuarios:"+idAutoridad);
        dTo.setIdAutoridad(idAutoridad);
        return getDatosReporte(dTo.getUbicacionUsuarios());
    }

    public String[][] getUbicacionUsuariosSubt(Long idAutoridad) throws IdeamException {
        //System.out.println("---------------------------getUbicacionUsuarios:"+idAutoridad);
        dTo.setIdAutoridad(idAutoridad);
        return getDatosReporte(dTo.getUbicacionUsuariosSubt());
    }

    public String[][] getUsoAguaAvtividad() throws IdeamException {
        return getDatosReporte(dTo.getUsoAguaActividad());
    }

    public List<PermisoVertimientoTO> getAllPermisos(String numeroExpediente,
                                                     String nroResol,
                                                     String fInicio,
                                                     String fFin,
                                                     Autoridades autoridad,
                                                     String estado) throws IdeamException {
        String sql = "";
        Query query = null;
        boolean existenCriterios = false;
        sql = "select * from rurv_permisos ";
        if (autoridad != null) {
            if (existenCriterios)
                sql += " and ";
            else
                sql += " where ";
            existenCriterios = true;
            sql = sql + "  autoridad =" + autoridad.getId().longValue();
        }

        if (numeroExpediente != null && numeroExpediente.length() > 0) {
            if (existenCriterios)
                sql += " and ";
            else
                sql += " where ";
            existenCriterios = true;
            sql +=
"   upper(num_expediente) like " + "'%" + numeroExpediente.toUpperCase() +
 "%'";
        }

        if (nroResol != null && nroResol.length() > 0) {
            if (existenCriterios)
                sql += " and ";
            else
                sql += " where ";
            existenCriterios = true;
            sql +=
"   upper(num_res_permiso_vert) like " + "'%" + nroResol.toUpperCase() + "%'";
        }

        if (fInicio != null && fInicio.length() > 0) {
            if (fInicio.length() < 8) {
                fInicio = "0" + fInicio;
            } else {
                fInicio = fInicio.toString();
            }
            if (existenCriterios)
                sql += " and ";
            else
                sql += " where ";
            existenCriterios = true;
            sql +=
"   vigencias_desde >= " + "to_date('" + fInicio + "','DD/MM/YYYY')";
        }
        if (fFin != null && fFin.length() > 0) {
            if (fFin.length() < 8) {
                fFin = "0" + fFin;
            } else {
                fFin = fFin.toString();
            }
            if (existenCriterios)
                sql += " and ";
            else
                sql += " where ";
            existenCriterios = true;
            sql +=
"   vigencia_hasta >= " + "to_date('" + fFin + "','DD/MM/YYYY')";
        }


        //System.out.println("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF------>"+sql);

        if (estado != null && estado.length() > 0) {
            if (existenCriterios)
                sql += " and ";
            else
                sql += " where ";
            existenCriterios = true;
            if (estado.equalsIgnoreCase("NO ESPECIFICADO")) {
                sql += "  vigencia_hasta is null";
            } else if (estado.equalsIgnoreCase("VENCIDOS")) {
                sql += "  vigencia_hasta < current_date";
            } else if (estado.equalsIgnoreCase("VIGENTES")) {
                sql += "  vigencia_hasta >= current_date";
            }
        }

        query = em.createNativeQuery(sql, PermisoVertimientoTO.class);
        return query.getResultList();
    }

    public List<PermisoVertimientoTO> getAllPermisos(String numeroExpediente,
                                                     String nroResol,
                                                     String fInicio,
                                                     String fFin,
                                                     String estado) throws IdeamException {
        String sql = "";
        Query query = null;
        boolean existenCriterios = false;


        if (numeroExpediente != null && numeroExpediente.length() > 0) {
            sql =
"select * from rurv_permisos  where  upper(num_expediente) like " + "'%" +
  numeroExpediente.toUpperCase() + "%'";

        }

        if (nroResol != null && nroResol.length() > 0) {
            sql +=
" and  upper(num_res_permiso_vert) like " + "'%" + nroResol + "%'";
        }
        if (fInicio != null && fInicio.length() > 0) {
            if (fInicio.length() < 8) {
                fInicio = "0" + fInicio;
            } else {
                fInicio = fInicio.toString();
            }
            sql += " and vigencias_desde >= " + "'" + fInicio + "'";
        }

        if (fFin != null && fFin.length() > 0) {
            if (fFin.length() < 8) {
                fFin = "0" + fFin;
            } else {
                fFin = fFin.toString();
            }
            sql += " and vigencias_hasta >= " + "'" + fFin + "'";
        }


        if (estado != null && estado.length() > 0) {
            if (estado.equalsIgnoreCase("NO ESPECIFICADO")) {
                sql += " and vigencias_hasta is null";
            } else if (estado.equalsIgnoreCase("VENCIDOS")) {
                sql += " and vigencias_hasta < current_date";
            } else if (estado.equalsIgnoreCase("VIGENTES")) {
                sql += " and vigencias_hasta >= current_date";
            }
        }

        query = em.createNativeQuery(sql, PermisoVertimientoTO.class);
        return query.getResultList();


    }

    public List<PermisoVertimientoTO> getAllPermisos(Autoridades autoridad,
                                                     String estado) throws IdeamException {
        if (estado != null && estado.length() > 0) {
            String sql =
                "select o from PermisoVertimientoTO o where o.codigoAutoridad =:codigoAutoridad and ";
            if (estado.equalsIgnoreCase("NO ESPECIFICADO")) {
                sql += " o.fechaFin is null";
            } else if (estado.equalsIgnoreCase("VENCIDOS")) {
                sql += " o.fechaFin < current_date";
            } else if (estado.equalsIgnoreCase("VIGENTES")) {
                sql += " o.fechaFin >= current_date";
            }
            Query query = em.createQuery(sql);
            query.setParameter("codigoAutoridad",
                               autoridad.getId().longValue());
            return query.getResultList();
        } else {
            Query query =
                em.createNamedQuery("PermisoVertimientoTO.findByAutoridad");
            query.setParameter("codigoAutoridad",
                               autoridad.getId().longValue());
            return query.getResultList();
        }
    }

    public List<PermisoVertimientoTO> getAllPermisos(String estado) throws IdeamException {
        if (estado != null && estado.length() > 0) {
            String sql = "select o from PermisoVertimientoTO o where ";
            if (estado != null && estado.length() > 0) {
                if (estado.equalsIgnoreCase("NO ESPECIFICADO")) {
                    sql += " o.fecha_fin is null";
                } else if (estado.equalsIgnoreCase("VENCIDOS")) {
                    sql += " o.fecha_fin < current_date";
                } else if (estado.equalsIgnoreCase("VIGENTES")) {
                    sql += " o.fecha_fin >= current_date";
                }
            }

            Query query = em.createQuery(sql);
            return query.getResultList();
        } else {
            Query query = em.createNamedQuery("PermisoVertimientoTO.findAll");
            return query.getResultList();
        }
    }

    public List<ConcesionTO> getAllConcesiones(String numeroExpediente,
                                               String estado) throws IdeamException {
        String sql =
            "select o from ConcesionTO o where upper(o.numeroExpediente) like :numeroExpediente ";
        if (estado != null && estado.length() > 0) {
            if (estado.equalsIgnoreCase("NO ESPECIFICADO")) {
                sql += " and o.fecha_fin is null";
            } else if (estado.equalsIgnoreCase("VENCIDAS")) {
                sql += " and o.fecha_fin < current_date";
            } else if (estado.equalsIgnoreCase("VIGENTES")) {
                sql += " and o.fecha_fin >= current_date";
            }
        }
        Query query = em.createQuery(sql);
        query.setParameter("numeroExpediente",
                           "%" + numeroExpediente.toUpperCase() + "%");
        return query.getResultList();
    }

    public List<ConcesionTO> getAllConcesiones(String numeroExpediente,
                                               String snroResolucion,
                                               String fInicio, String fFin,
                                               Autoridades autoridad,
                                               String estado) throws IdeamException {
        String sql = "";
        Query query = null;
        boolean existenCriterios = false;

        sql = "select * from rurv_concesiones  ";
        if (autoridad != null) {
            existenCriterios = true;
            sql = sql + " where  autoridad =" + autoridad.getId().longValue();
        }

        if (numeroExpediente != null && numeroExpediente.length() > 0) {
            if (existenCriterios)
                sql += " and ";
            else
                sql += " where ";
            existenCriterios = true;
            sql +=
"   upper(num_expediente) like  " + "'%" + numeroExpediente.toUpperCase() +
 "%'";
        }

        if (snroResolucion != null && snroResolucion.length() > 0) {
            if (existenCriterios)
                sql += " and ";
            else
                sql += " where ";
            existenCriterios = true;
            sql +=
"  upper(num_res_caudal) like " + "'%" + snroResolucion.toUpperCase() + "%'";
        }

        if (fInicio != null && fInicio.length() > 0) {
            if (fInicio.length() < 8) {
                fInicio = "0" + fInicio;
            } else {
                fInicio = fInicio.toString();
            }
            if (existenCriterios)
                sql += " and ";
            else
                sql += " where ";
            existenCriterios = true;
            sql +=
"  fecha_inicio >= " + "to_date('" + fInicio + "','DD/MM/YYYY')";
        }

        if (fFin != null && fFin.length() > 0) {
            if (fFin.length() < 8) {
                fFin = "0" + fFin;
            } else {
                fFin = fFin.toString();
            }
            if (existenCriterios)
                sql += " and ";
            else
                sql += " where ";
            existenCriterios = true;
            sql += "  fecha_fin  >= " + "to_date('" + fFin + "','DD/MM/YYYY')";
        }

        if (estado != null && estado.length() > 0) {
            if (existenCriterios)
                sql += " and ";
            else
                sql += " where ";
            existenCriterios = true;
            if (estado.equalsIgnoreCase("NO ESPECIFICADO")) {
                sql += "  fecha_fin is null";
            } else if (estado.equalsIgnoreCase("VENCIDAS")) {
                sql += "  fecha_fin < current_date";
            } else if (estado.equalsIgnoreCase("VIGENTES")) {
                sql += "  fecha_fin >= current_date";
            }
        }

        query = em.createNativeQuery(sql, ConcesionTO.class);
        return query.getResultList();

    }

    public List<ConcesionTO> getAllConcesiones(Autoridades autoridad) throws IdeamException {
        Query query = em.createNamedQuery("ConcesionTO.findByAutoridad");
        query.setParameter("codigoAutoridad", autoridad.getId().longValue());
        return query.getResultList();
    }

    public List<ConcesionTO> getAllConcesiones(String estado) throws IdeamException {
        if (estado != null && estado.length() > 0) {
            String sql = "select o from ConcesionTO o where ";
            if (estado != null && estado.length() > 0) {
                if (estado.equalsIgnoreCase("NO ESPECIFICADO")) {
                    sql += " o.fecha_fin is null";
                } else if (estado.equalsIgnoreCase("VENCIDAS")) {
                    sql += " o.fecha_fin < current_date";
                } else if (estado.equalsIgnoreCase("VIGENTES")) {
                    sql += " o.fecha_fin >= current_date";
                }
            }
            Query query = em.createQuery(sql);
            return query.getResultList();
        } else {
            Query query = em.createNamedQuery("ConcesionTO.findAll");
            return query.getResultList();
        }
    }

    public String[][] getEstadosVertimientos(Long idAutoridad) throws IdeamException {
        dTo.setIdAutoridad(idAutoridad);
        return getDatosReporte(dTo.getEstadosVertimientos());
    }

    public String[][] getEstadosConcesionesSubt(Long idAutoridad) throws IdeamException {
        dTo.setIdAutoridad(idAutoridad);
        return getDatosReporte(dTo.getEstadosConcesionesSubt());
    }

    public String[][] getEstadosConcesiones(Long idAutoridad) throws IdeamException {
        dTo.setIdAutoridad(idAutoridad);
        return getDatosReporte(dTo.getEstadosConcesiones());
    }

    public String[][] getNaturalezaUsuarios(Long idAutoridad) throws IdeamException {
        dTo.setIdAutoridad(idAutoridad);
        return getDatosReporte(dTo.getNaturalezaUsuarios());
    }

    public String[][] getNaturalezaUsuariosSubt(Long idAutoridad) throws IdeamException {
        dTo.setIdAutoridad(idAutoridad);
        return getDatosReporte(dTo.getNaturalezaUsuariosSubt());
    }

    public List getListaFuentesExternas() throws IdeamException {
        Query query = null;
        query = em.createNamedQuery("ConsultasExtFuentesTO.findAll");
        List lista = query.getResultList();
        return lista;
    }

    public List getListaFuentesExternasSubt() throws IdeamException {
        Query query = null;
        query = em.createNamedQuery("ConsultasExtFuentesTO.findAll2");
        List lista = query.getResultList();
        return lista;
    }

    public List getListaFuentesExternas(CriteriosBusquedaExternasTO criterios) throws IdeamException {


        String sql =
            "select * from sirh_consultaextfuentes_v where tipo_fuente not like 'Aguas subterraneas' and ";
        int totalParametros = 1;

        if (criterios.getArea() != null &&
            criterios.getArea().getId() != null) {
            sql +=
"  upper(area) = '" + criterios.getArea().getValor().toString().toUpperCase() +
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

        if (criterios.getAutoridad() != null &&
            criterios.getAutoridad().getId() != null) {
            sql += "  id_autoridad = " + criterios.getAutoridad().getId() + "";

        }


        if (criterios.getNombreFuente() != null &&
            criterios.getNombreFuente().length() > 0) {
            sql +=
"  upper(fuente) like'%" + criterios.getNombreFuente().toUpperCase() + "%'";
        }


        if (criterios.getCaudal() != null &&
            criterios.getCaudal().length() > 0) {
            if (criterios.getSigno() != null &&
                criterios.getSigno().length() > 0) {
                sql +=
"  caudal " + criterios.getSigno() + criterios.getCaudal();
                sql +=
"  and upper(tipo_tramite) like'%" + criterios.getTipo_tramite().toUpperCase() +
 "%'";
            }

        }


        Query q = em.createNativeQuery(sql, ConsultasExtFuentesTO.class);
        List lista = q.getResultList();

        return lista;
    }

    public List getListaFuentesExternasSubt(CriteriosBusquedaExternasTO criterios) throws IdeamException {


        String sql =
            "select * from sirh_consultaextfuentes_v where tipo_fuente like 'Aguas subterraneas' and ";
        int totalParametros = 1;

        if (criterios.getArea() != null &&
            criterios.getArea().getId() != null) {
            sql +=
"  upper(area) = '" + criterios.getArea().getValor().toString().toUpperCase() +
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

        if (criterios.getAutoridad() != null &&
            criterios.getAutoridad().getId() != null) {
            sql += "  id_autoridad = " + criterios.getAutoridad().getId() + "";

        }


        if (criterios.getNombreFuente() != null &&
            criterios.getNombreFuente().length() > 0) {
            sql +=
"  upper(fuente) like'%" + criterios.getNombreFuente().toUpperCase() + "%'";
        }


        if (criterios.getCaudal() != null &&
            criterios.getCaudal().length() > 0) {
            if (criterios.getSigno() != null &&
                criterios.getSigno().length() > 0) {
                sql +=
"  caudal " + criterios.getSigno() + criterios.getCaudal();
                sql +=
"  and upper(tipo_tramite) like'%" + criterios.getTipo_tramite().toUpperCase() +
 "%'";
            }

        }

        System.out.println("SQL getListaFuentesExternasSubt -> " + sql);
        Query q = em.createNativeQuery(sql, ConsultasExtFuentesTO.class);
        List lista = q.getResultList();

        return lista;
    }


    public List getListaCaudalesConcesionados(Long idAutoridad) throws IdeamException {
        Query query = null;
        if (idAutoridad != null) {
            query =
                    em.createNamedQuery("CaudalesConcesionadoTO.findAutoridadAll");
            query.setParameter("codigoAutoridad", idAutoridad);
        } else {
            query = em.createNamedQuery("CaudalesConcesionadoTO.findAll");
        }
        List lista = query.getResultList();
        if (lista != null) {
            Iterator it = lista.iterator();
            while (it.hasNext()) {
                CaudalesConcesionadoTO med = (CaudalesConcesionadoTO)it.next();
                this.loadAttributesCaudales(med);


            }
        }

        return lista;

    }


    /**
     * Carga los atributos @Transient de la clase CaudalesConcesionadoTO
     * @param CaudalesConcesionadoTO
     * @throws IdeamException
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    private void loadAttributesCaudales(CaudalesConcesionadoTO med) throws IdeamException {

        Double indice = med.getIUA();
        if (indice > 100) {
            med.setCategoria_IAU("Cr�tico");
            med.setImgcateg_IAU("/imgs/rangoCritico.png");
        } else if (indice > 50 && indice <= 100) {
            med.setCategoria_IAU("Muy alto");
            med.setImgcateg_IAU("/imgs/rangoMuyAlto.png");
        } else if (indice > 20 && indice <= 50) {
            med.setCategoria_IAU("Alto");
            med.setImgcateg_IAU("/imgs/rangoAlto.png");
        } else if (indice > 10 && indice <= 20) {
            med.setCategoria_IAU("Moderado");
            med.setImgcateg_IAU("/imgs/rangoModerado.png");
        } else if (indice > 1 && indice <= 10) {
            med.setCategoria_IAU("Bajo");
            med.setImgcateg_IAU("/imgs/rangoBajo.png");
        } else if (indice <= 1) {
            med.setCategoria_IAU("Muy bajo");
            med.setImgcateg_IAU("/imgs/rangoMuyBajo.png");
        }


    }


    public List getListaCaudalesVertidos(Long idAutoridad) throws IdeamException {
        Query query = null;
        if (idAutoridad != null) {
            query = em.createNamedQuery("CaudalesVertidosTO.findAutoridadAll");
            query.setParameter("codigoAutoridad", idAutoridad);
        } else {
            query = em.createNamedQuery("CaudalesVertidosTO.findAll");
        }
        List lista = query.getResultList();
        return lista;

    }


    public List getListaCaudalesConcesionadosDetalle(Long idAutoridad,
                                                     Long idFuente,
                                                     Long codTramo) throws IdeamException {
        Query query = null;
        if (idAutoridad != null) {
            query =
                    em.createNamedQuery("CaudalesConcesionadoDetalleTO.findAutoridadAll");
            query.setParameter("codigoAutoridad", idAutoridad);
            query.setParameter("id", idFuente);
            query.setParameter("tramo_id", codTramo);
        } else {
            query =
                    em.createNamedQuery("CaudalesConcesionadoDetalleTO.findAllFuente");
            query.setParameter("id", idFuente);
            query.setParameter("tramo_id", codTramo);
        }

        List lista = query.getResultList();
        return lista;

    }


    public List getListaCaudalesVertidosDetalle(Long idAutoridad,
                                                Long idFuente,
                                                Long codTramo) throws IdeamException {
        Query query = null;
        if (idAutoridad != null) {
            query =
                    em.createNamedQuery("CaudalesVertidosDetalleTO.findAutoridadAll");
            query.setParameter("codigoAutoridad", idAutoridad);
            query.setParameter("id", idFuente);
            query.setParameter("tramo_id", codTramo);
        } else {
            query =
                    em.createNamedQuery("CaudalesVertidosDetalleTO.findAllFuente");
            query.setParameter("id", idFuente);
            query.setParameter("tramo_id", codTramo);
        }

        List lista = query.getResultList();
        return lista;

    }

    public String[][] getCaptacionesFuentes(Long idAutoridad) throws IdeamException {
        dTo.setIdAutoridad(idAutoridad);
        String[][] datosListar =
            getDatosReporteCapXFuente(dTo.getCaptacionesFuentes());


        return datosListar;
    }

    public String[][] getCaptacionesFuentesSubt(Long idAutoridad) throws IdeamException {
        //System.out.println("---------------------------getCaptacionesFuentes:"+idAutoridad);
        dTo.setIdAutoridad(idAutoridad);
        String[][] datosListar =
            getDatosReporteCapXFuente(dTo.getCaptacionesFuentesSubt());


        return datosListar;
    }

    public String[][] getVertimientosFuentes(Long idAutoridad) throws IdeamException {
        dTo.setIdAutoridad(idAutoridad);
        String[][] datosListar =
            getDatosReporteVertXFuente(dTo.getVertimientosFuentes());
        return datosListar;
    }


    public String[][] getPuntosMonitoreoFuentes(Long idAutoridad) throws IdeamException {
        dTo.setIdAutoridad(idAutoridad);
        String[][] datosListar =
            getDatosReportePtosXFuente(dTo.getPuntosMonitoreoFuentes());
        return datosListar;
    }



    public List getAnioConcesiones(Long idAutoridad) throws IdeamException {
        Query q = null;
        try {
            if (idAutoridad != null) {
                q =
  em.createNativeQuery(DatosReporteTO.listaAnioConcesionesAut);
                q.setParameter(1, idAutoridad);
            } else {
                q = em.createNativeQuery(DatosReporteTO.listaAnioConcesiones);
            }
        } catch (Exception e) {
            System.out.println("--Error getAnioConcesiones");
        }
        return q.getResultList();

    }


    public List getAnioPermisos(Long idAutoridad) throws IdeamException {
        Query q = null;
        try {
            if (idAutoridad != null) {
                q = em.createNativeQuery(DatosReporteTO.listaAnioPermisosAut);
                q.setParameter(1, idAutoridad);
            } else {
                q = em.createNativeQuery(DatosReporteTO.listaAnioPermisos);
            }
        } catch (Exception e) {
            System.out.println("--Error getAnioPermisos");
        }
        return q.getResultList();

    }


    public String[][] getConcesionesAnio(Integer anio,
                                         Long idAutoridad) throws IdeamException {
        dTo.setIdAutoridad(idAutoridad);
        return getConcAnio(dTo.getConcesionesAnio(anio));
    }

    private String[][] getConcAnio(String sql) throws IdeamException {
        String retorno[][] = null;
        Query q = em.createNativeQuery(sql, DatosReporteTO.class);
        String meses[] =
        { "Enero", "Febreo", "Marzo", "Abril", "Mayo", "Junio", "Julio",
          "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" };

        List lista = q.getResultList();

        retorno = new String[2][12];
        int i = 0;
        int sw = 0;


        for (int y = 0; y < meses.length; y++) {

            for (int t = 0; t < lista.size(); t++) {
                DatosReporteTO datos = (DatosReporteTO)lista.get(t);
                //System.out.println("**Descripcion()--------------------------------"+datos.getDescripcion());
                int x = y + 1;
                sw = 0;
                if (x == new Integer(datos.getDescripcion())) {
                    //System.out.println("x---------"+x+"+ meses----------"+meses[y]);

                    //System.out.println("**datos.getCantidad().toString()---------"+datos.getCantidad().toString());
                    retorno[0][i] = meses[y];
                    retorno[1][i] = datos.getCantidad().toString();
                    sw = 1;
                    i++;
                    break;
                }
            }
            if (sw == 0) {
                //System.out.println("**************+*+++  meses-----sw----44----"+meses[y]);
                retorno[0][i] = meses[y];
                retorno[1][i] = "0";


                //System.out.println("**cantidad sw---------"+ retorno[1][i]);

                i++;
            }

        }
        return retorno;
    }


    public String[][] getPermisosAnio(Integer anio,
                                      Long idAutoridad) throws IdeamException {
        dTo.setIdAutoridad(idAutoridad);
        return getPermAnio(dTo.getPermisosAnio(anio));
    }

    private String[][] getPermAnio(String sql) throws IdeamException {
        String retorno[][] = null;
        Query q = em.createNativeQuery(sql, DatosReporteTO.class);
        String meses[] =
        { "Enero", "Febreo", "Marzo", "Abril", "Mayo", "Junio", "Julio",
          "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" };

        List lista = q.getResultList();

        retorno = new String[2][12];
        int i = 0;
        int sw = 0;


        for (int y = 0; y < meses.length; y++) {

            for (int t = 0; t < lista.size(); t++) {
                DatosReporteTO datos = (DatosReporteTO)lista.get(t);
                //System.out.println("**Descripcion()--------------------------------"+datos.getDescripcion());
                int x = y + 1;
                sw = 0;
                if (x == new Integer(datos.getDescripcion())) {
                    //System.out.println("x---------"+x+"+ meses----------"+meses[y]);

                    //System.out.println("**datos.getCantidad().toString()---------"+datos.getCantidad().toString());
                    retorno[0][i] = meses[y];
                    retorno[1][i] = datos.getCantidad().toString();
                    sw = 1;
                    i++;
                    break;
                }
            }
            if (sw == 0) {
                //System.out.println("**************+*+++  meses-----sw----44----"+meses[y]);
                retorno[0][i] = meses[y];
                retorno[1][i] = "0";


                //System.out.println("**cantidad sw---------"+ retorno[1][i]);

                i++;
            }

        }
        return retorno;
    }


    public String[][] getPrediosPorDepto(Long idAutoridad) throws IdeamException {
        //System.out.println("---------------------------getPrediosPorDepto:"+idAutoridad);
        dTo.setIdAutoridad(idAutoridad);
        return getDatosReporte(dTo.getPrediosPorDepartamento());
    }

    public String[][] getPrediosPorDeptoSubt(Long idAutoridad) throws IdeamException {
        //System.out.println("---------------------------getPrediosPorDepto:"+idAutoridad);
        dTo.setIdAutoridad(idAutoridad);
        return getDatosReporte(dTo.getPrediosPorDepartamentoSubt());
    }


    public String[][] getPrediosPorMcpio(Long idAutoridad) throws IdeamException {
        //System.out.println("---------------------------getPrediosPorMcpio:"+idAutoridad);
        dTo.setIdAutoridad(idAutoridad);
        return getDatosReporte(dTo.getPrediosPorMunicipio());
    }

    public String[][] getPrediosPorMcpioSubt(Long idAutoridad) throws IdeamException {
        //System.out.println("---------------------------getPrediosPorMcpio:"+idAutoridad);
        dTo.setIdAutoridad(idAutoridad);
        return getDatosReporte(dTo.getPrediosPorMunicipioSubt());
    }

    public String[][] getCaptacionesTipoFuente(Long idAutoridad) throws IdeamException {
        dTo.setIdAutoridad(idAutoridad);
        return getDatosReporte(dTo.getCaptacionesPorTipoDeFuente());
    }

    public String[][] getVertimientosTipo(Long idAutoridad) throws IdeamException {
        dTo.setIdAutoridad(idAutoridad);
        return getDatosReporte(dTo.getVertimientosPorTipo());
    }

    public String[][] getCaptacionesTipoFuenteDet(Long idAutoridad) throws IdeamException {
        dTo.setIdAutoridad(idAutoridad);
        return getDatosReporte(dTo.getCaptacionesPorTipoDeFuenteDet());
    }

    public String[][] getUsosCaptaciones(Long idAutoridad) throws IdeamException {
        dTo.setIdAutoridad(idAutoridad);
        return getDatosReporte(dTo.getUsosCaptaciones());
    }

    public String[][] getUsosCaptacionesSubt(Long idAutoridad) throws IdeamException {
        dTo.setIdAutoridad(idAutoridad);
        return getDatosReporte(dTo.getUsosCaptacionesSubt());
    }

    private String[][] getDatosReporte(String sql) throws IdeamException {
        String retorno[][] = null;
        Query q = em.createNativeQuery(sql, DatosReporteTO.class);
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

    private String[][] getDatosReporteCapXFuente(String sql) throws IdeamException {
        String retorno[][] = null;
        Query q = em.createNativeQuery(sql, DatosReporteTO.class);
        List lista = q.getResultList();
        Iterator it = lista.iterator();
        retorno = new String[2][17];
        int i = 0;
        int tope = 0;
        int suma = 0;

        while (it.hasNext()) {
            DatosReporteTO datos = (DatosReporteTO)it.next();
            if (tope <= 15) {
                retorno[0][i] = datos.getDescripcion();
                retorno[1][i] = datos.getCantidad().toString();
                tope++;
            } else {
                suma =
suma + new Integer(datos.getCantidad().toString()).intValue();
            }
            i++;
        }
        retorno[0][16] = "Otros";
        retorno[1][16] = new Integer(suma).toString();
        return retorno;
    }


    private String[][] getDatosReporteVertXFuente(String sql) throws IdeamException {
        String retorno[][] = null;
        Query q = em.createNativeQuery(sql, DatosReporteTO.class);
        List lista = q.getResultList();
        Iterator it = lista.iterator();
        retorno = new String[2][17];
        int i = 0;
        int tope = 0;
        int suma = 0;
        while (it.hasNext()) {
            DatosReporteTO datos = (DatosReporteTO)it.next();
            if (tope <= 15) {
                retorno[0][i] = datos.getDescripcion();
                retorno[1][i] = datos.getCantidad().toString();
                tope++;
            } else {
                suma =
suma + new Integer(datos.getCantidad().toString()).intValue();
            }
            i++;
        }
        retorno[0][16] = "Otros";
        retorno[1][16] = new Integer(suma).toString();
        return retorno;
    }


    private String[][] getDatosReportePtosXFuente(String sql) throws IdeamException {
        String retorno[][] = null;
        Query q = em.createNativeQuery(sql, DatosReporteTO.class);
        List lista = q.getResultList();
        Iterator it = lista.iterator();
        retorno = new String[2][17];
        int i = 0;
        int tope = 0;
        int suma = 0;
        while (it.hasNext()) {
            DatosReporteTO datos = (DatosReporteTO)it.next();
            if (tope <= 15) {
                retorno[0][i] = datos.getDescripcion();
                retorno[1][i] = datos.getCantidad().toString();
                tope++;
            } else {
                suma =
    suma + new Integer(datos.getCantidad().toString()).intValue();
            }
            i++;
        }
        retorno[0][16] = "Otros";
        retorno[1][16] = new Integer(suma).toString();
        return retorno;
    }

    public PermisoVertimiento getPermisoVertimiento(String numeroExpediente,
                                                    Long codigoAutoridad) throws IdeamException {
        try {
            Query query =
                em.createNamedQuery("PermisoVertimientoTO.findByOneExpedienteAutoridad");
            query.setParameter("numeroExpediente",
                               numeroExpediente.toUpperCase());
            query.setParameter("codigoAutoridad", codigoAutoridad);
            query.setMaxResults(1);
            PermisoVertimientoTO existe =
                (PermisoVertimientoTO)query.getSingleResult();
            if (existe != null) {
                return this.getPermisoVertimiento(existe.getCodigo());
            }
            return null;
        } catch (NoResultException e) {
            return null;
        }
    }

    public Concesion getConcesion(String numeroExpediente) throws IdeamException {
        try {
            Query query = em.createNamedQuery("Concesion.findByExpediente");
            query.setParameter("numeroExpediente",
                               numeroExpediente.toUpperCase());
            query.setMaxResults(1);
            return (Concesion)query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public Predio getPredio(String cedulaCatastral) throws IdeamException {
        try {
            Query query = em.createNamedQuery("Predio.findByCedula");
            query.setParameter("cedulaCatastral",
                               cedulaCatastral.toUpperCase());
            Predio predio = (Predio)query.getSingleResult();
            this.loadAttributes(predio);
            return predio;
        } catch (NoResultException e) {
            return null;
        }
    }

    public List getAllUsuarios(CriteriosBusquedaTO criterios) throws IdeamException {
        boolean existenCriterios = false;
        String sql = "select * from rurv_usuarios where ";
        int totalParametros = 1;
        if (criterios.getTipoIdentificacion() != null &&
            criterios.getTipoIdentificacion().getValor() != null) {
            if (existenCriterios) {
                sql += " and ";
            }
            existenCriterios = true;
            sql += " id_tipo_documento = ?" + totalParametros++;
        }
        if (criterios.getAutoridad() != null &&
            criterios.getAutoridad().getId() != null) {
            if (existenCriterios) {
                sql += " and ";
            }
            existenCriterios = true;
            sql += " id_autoridad = ?" + totalParametros++;
        }
        if (criterios.getNumeroIdentificacion() != null &&
            criterios.getNumeroIdentificacion().length() > 0) {
            if (existenCriterios) {
                sql += " and ";
            }
            existenCriterios = true;
            sql += " num_documento = ?" + totalParametros++;
        }
        if (criterios.getNombres() != null &&
            criterios.getNombres().length() > 0) {
            if (existenCriterios) {
                sql += " and ";
            }
            existenCriterios = true;
            sql +=
" upper(usuario) like'%" + criterios.getNombres().toUpperCase() + "%'";
        }
        if (criterios.getApellidos() != null &&
            criterios.getApellidos().length() > 0) {
            if (existenCriterios) {
                sql += " and ";
            }
            existenCriterios = true;
            sql +=
" upper(apellido) like'%" + criterios.getApellidos().toUpperCase() + "%'";
        }
        if (criterios.getDepartamento() != null &&
            criterios.getDepartamento().getId() != null) {
            if (existenCriterios) {
                sql += " and ";
            }
            existenCriterios = true;
            sql += " id_divipola_depto = ?" + totalParametros++;
        }
        if (criterios.getMunicipio() != null &&
            criterios.getMunicipio().getId() != null) {
            if (existenCriterios) {
                sql += " and ";
            }
            existenCriterios = true;
            sql += " id_divipola_municipio = ?" + totalParametros++;
        }
        if (criterios.getNaturalezaUsuario() != null &&
            criterios.getNaturalezaUsuario().getValor() != null) {
            if (existenCriterios) {
                sql += " and ";
            }
            existenCriterios = true;
            sql += " codigo_tipo_persona= ?" + totalParametros++;
        }
        Query q = em.createNativeQuery(sql, UsuarioAguaTO.class);
        totalParametros = 1;
        if (criterios.getTipoIdentificacion() != null &&
            criterios.getTipoIdentificacion().getValor() != null) {
            q.setParameter(totalParametros++,
                           criterios.getTipoIdentificacion().getCodigo());
        }
        if (criterios.getAutoridad() != null &&
            criterios.getAutoridad().getId() != null) {
            q.setParameter(totalParametros++,
                           criterios.getAutoridad().getId());
        }
        if (criterios.getNumeroIdentificacion() != null &&
            criterios.getNumeroIdentificacion().length() > 0) {
            q.setParameter(totalParametros++,
                           criterios.getNumeroIdentificacion());
        }
        if (criterios.getDepartamento() != null &&
            criterios.getDepartamento().getId() != null) {
            q.setParameter(totalParametros++,
                           criterios.getDepartamento().getId());
        }
        if (criterios.getMunicipio() != null &&
            criterios.getMunicipio().getId() != null) {
            q.setParameter(totalParametros++,
                           criterios.getMunicipio().getId());
        }
        if (criterios.getNaturalezaUsuario() != null &&
            criterios.getNaturalezaUsuario().getValor() != null) {
            q.setParameter(totalParametros++,
                           criterios.getNaturalezaUsuario().getCodigo());
        }

        List lista = q.getResultList();
        /*if (lista != null) {
            Iterator it = lista.iterator();
            while (it.hasNext()) {
                UsuarioAgua ua = (UsuarioAgua)it.next();
                this.loadAttributes(ua);
            }
        }*/
        return lista;
    }


    /**
     * Busqueda desde el listado de predios que permite hacer filtros
     * */
    public List getAllPredios(CriteriosBusquedaTO criterios) throws IdeamException {
        boolean existenCriterios = false;
        String sql = "select * from rurv_predios ";
        if (criterios.getNombres() != null &&
            criterios.getNombres().length() > 0) {
            existenCriterios = true;
            sql +=
" where upper(nombre_predio) like'%" + criterios.getNombres().toUpperCase() +
 "%'";
        }
        if (criterios.getAutoridad() != null &&
            criterios.getAutoridad().getId() != null) {
            if (existenCriterios)
                sql += " and ";
            else
                sql += " where ";
            existenCriterios = true;
            sql += " id_autoridad = " + criterios.getAutoridad().getId() + "";
        }
        if (criterios.getCedCatastral() != null &&
            criterios.getCedCatastral().length() > 0) {
            if (existenCriterios)
                sql += " and ";
            else
                sql += " where ";
            existenCriterios = true;
            sql +=
"  upper(cedula_catastral) like '%" + criterios.getCedCatastral().toUpperCase() +
 "%'";
        }


        if (criterios.getClasificacionSuelo() != null &&
            criterios.getClasificacionSuelo().getCodigo() != null) {
            if (existenCriterios)
                sql += " and ";
            else
                sql += " where ";
            existenCriterios = true;
            sql +=
"  upper(valor)= '" + criterios.getClasificacionSuelo().getValor().toString().toUpperCase() +
 "'";
        }
        if (criterios.getDepartamento() != null &&
            criterios.getDepartamento().getId() != null) {
            if (existenCriterios)
                sql += " and ";
            else
                sql += " where ";
            existenCriterios = true;
            sql +=
"  upper(departamento)= '" + criterios.getDepartamento().getNombre().toString().toUpperCase() +
 "'";
        }
        if (criterios.getMunicipio() != null &&
            criterios.getMunicipio().getId() != null) {
            if (existenCriterios)
                sql += " and ";
            else
                sql += " where ";
            existenCriterios = true;
            sql +=
"  upper(municipio)= '" + criterios.getMunicipio().getNombre().toString().toUpperCase() +
 "'";
        }

        Query q = em.createNativeQuery(sql, PrediosTO.class);
        List lista = q.getResultList();
        return lista;
    }


    public List getAllPrediosAut(CriteriosBusquedaTO criterios,
                                 Long codigoAutoridad) throws IdeamException {
        int totalParametros = 1;
        String sql = "select * from rurv_predios ";
        if (codigoAutoridad != null) {
            sql = sql + "where id_autoridad = ?1 ";
            totalParametros = 2;
        }

        if (criterios.getNombres() != null &&
            criterios.getNombres().length() > 0) {
            if (totalParametros == 1)
                sql += " where";
            else
                sql += " and";
            sql +=
" upper(nombre_predio) like '%" + criterios.getNombres().toUpperCase() + "%'";
        }

        if (criterios.getCedCatastral() != null &&
            criterios.getCedCatastral().length() > 0) {
            if (totalParametros == 1)
                sql += " where";
            else
                sql += " and";
            sql +=
"  upper(cedula_catastral) like '%" + criterios.getCedCatastral().toUpperCase() +
 "%'";
        }

        if (criterios.getClasificacionSuelo() != null &&
            criterios.getClasificacionSuelo().getCodigo() != null) {
            if (totalParametros == 1)
                sql += " where";
            else
                sql += " and";
            sql +=
"  upper(valor)= '" + criterios.getClasificacionSuelo().getValor().toString().toUpperCase() +
 "'";

        }

        if (criterios.getDepartamento() != null &&
            criterios.getDepartamento().getId() != null) {
            if (totalParametros == 1)
                sql += " where";
            else
                sql += " and";
            sql +=
"  upper(departamento)= '" + criterios.getDepartamento().getNombre().toString().toUpperCase() +
 "'";

        }

        if (criterios.getMunicipio() != null &&
            criterios.getMunicipio().getId() != null) {
            if (totalParametros == 1)
                sql += " where";
            else
                sql += " and";
            sql +=
"  upper(municipio)= '" + criterios.getMunicipio().getNombre().toString().toUpperCase() +
 "'";

        }

        Query q = em.createNativeQuery(sql, PrediosTO.class);
        if (codigoAutoridad != null)
            q.setParameter(1, codigoAutoridad);

        List lista = q.getResultList();
        return lista;
    }


    public List getAllUsuarios(CriteriosBusquedaTO criterios,
                               Long codigoAutoridad) throws IdeamException {
        String sql = "select * from rurv_usuarios where id_autoridad = ?1 ";
        int totalParametros = 2;
        if (criterios.getTipoIdentificacion() != null &&
            criterios.getTipoIdentificacion().getValor() != null) {
            sql += " and id_tipo_documento = ?" + totalParametros++;
        }
        if (criterios.getNumeroIdentificacion() != null &&
            criterios.getNumeroIdentificacion().length() > 0) {
            sql += " and num_documento = ?" + totalParametros++;
        }
        if (criterios.getNombres() != null &&
            criterios.getNombres().length() > 0) {
            sql +=
" and upper(usuario) like'%" + criterios.getNombres().toUpperCase() + "%'";
        }
        if (criterios.getApellidos() != null &&
            criterios.getApellidos().length() > 0) {
            sql +=
" and upper(apellido) like'%" + criterios.getApellidos().toUpperCase() + "%'";
        }
        if (criterios.getDepartamento() != null &&
            criterios.getDepartamento().getId() != null) {
            //System.out.println("busca popr departamento:"+criterios.getDepartamento().getId());
            sql += " and id_divipola_depto = ?" + totalParametros++;
        }
        if (criterios.getMunicipio() != null &&
            criterios.getMunicipio().getId() != null) {
            sql += " and id_divipola_municipio = ?" + totalParametros++;
        }
        if (criterios.getNaturalezaUsuario() != null &&
            criterios.getNaturalezaUsuario().getValor() != null) {
            sql += " and codigo_tipo_persona= ?" + totalParametros++;
        }
        Query q = em.createNativeQuery(sql, UsuarioAguaTO.class);
        q.setParameter(1, codigoAutoridad);
        totalParametros = 2;
        if (criterios.getTipoIdentificacion() != null &&
            criterios.getTipoIdentificacion().getValor() != null) {
            q.setParameter(totalParametros++,
                           criterios.getTipoIdentificacion().getCodigo());
        }
        if (criterios.getNumeroIdentificacion() != null &&
            criterios.getNumeroIdentificacion().length() > 0) {
            q.setParameter(totalParametros++,
                           criterios.getNumeroIdentificacion());
        }
        if (criterios.getDepartamento() != null &&
            criterios.getDepartamento().getId() != null) {
            q.setParameter(totalParametros++,
                           criterios.getDepartamento().getId());
        }
        if (criterios.getMunicipio() != null &&
            criterios.getMunicipio().getId() != null) {
            q.setParameter(totalParametros++,
                           criterios.getMunicipio().getId());
        }
        if (criterios.getNaturalezaUsuario() != null &&
            criterios.getNaturalezaUsuario().getValor() != null) {
            q.setParameter(totalParametros++,
                           criterios.getNaturalezaUsuario().getCodigo());
        }
        List lista = q.getResultList();
        /*if (lista != null) {
            Iterator it = lista.iterator();
            while (it.hasNext()) {
                UsuarioAgua ua = (UsuarioAgua)it.next();
                this.loadAttributes(ua);
            }
        }*/
        return lista;
    }

    public List<DatosNodosTO> getReporteNodos(Autoridades autoridad) throws IdeamException {
        try {


            String sql = "Select * from SIRH_DATOS_NODOS  ";


            if (autoridad != null && autoridad.getId() != null) {

                sql += "  where ID_AUTORIDAD=" + autoridad.getId();
            }

            Query q = em.createNativeQuery(sql, DatosNodosTO.class);


            List lista = q.getResultList();

            return lista;


        } catch (NoResultException e) {
            return null;
        }
    }


    public List getAuditoriaUsuarios(String fInicio, String fFin,
                                     Autoridades autoridad,
                                     Lista usuarioLogin) throws IdeamException {

        String sql =
            "Select distinct(ua.id), ua.nombre || ' '  ||  ua.apellido as nombre  , ua.apellido,ad.operacion ,us.usu_codigo,us.usu_login, US.USU_EMAIL ,aut.ID AS id_aut,  aut.sigla , fecha\n" +
            "from rurt_usuarios_agua ua, sirh_auditoria ad, sirh_usuarios us, autoridades aut  " +
            "where ua.id =id_objeto ";
        int totalParametros = 1;
        if (autoridad != null && autoridad.getId() != null) {

            sql += "  and aut.ID=" + autoridad.getId();
        }
        if (usuarioLogin != null && usuarioLogin.getCodigo() != null) {

            sql += "  and us.usu_codigo=" + usuarioLogin.getCodigo();
        }
        if (fInicio != null && fInicio.length() > 0) {

            sql +=
"and  (fecha>= to_date('" + fInicio.toString() + "','DD/MM/YYYY'))";
        }

        if (fFin != null && fFin.length() > 0) {

            sql +=
"and  (fecha<= to_date('" + fFin.toString() + "','DD/MM/YYYY'))";
        }


        sql +=
" and AD.ID_USUARIO=US.USU_CODIGO " + " and AUT.ID=US.USU_AUTORIDAD " +
 "  order by fecha  ";


        Query q = em.createNativeQuery(sql, DatosReporteAuditoriaTO.class);


        List lista = q.getResultList();

        return lista;
    }


    public List getUsuariosLogin(Autoridades autoridad) throws IdeamException {

        String sql =
            " select usu_codigo as id ,  usu_nombres  || ' ' || usu_apellidos || ' - ' ||  usu_login  as valor ," +
            " usu_autoridad as id_categoria from sirh_usuarios ";
        int totalParametros = 1;
        if (autoridad != null && autoridad.getId() != null) {
            sql += "   where usu_autoridad= " + autoridad.getId();
        }
        sql += "  order by  usu_nombres ";
        Query q = em.createNativeQuery(sql, Lista.class);
        List lista = q.getResultList();

        return lista;
    }


    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void deleteUsuario(UsuarioAgua usuario) throws IdeamException {
        UsuarioAgua existe = this.getUSuario(usuario.getCodigo());
        List listaPredios = this.getPredios(usuario);
        if (listaPredios != null) {
            Iterator it = listaPredios.iterator();
            while (it.hasNext()) {
                Predio predio = (Predio)it.next();
                this.borrarPredio(predio);
            }
        }
        Representante representante =
            this.getRepresentante(usuario.getCodigo());
        if (representante != null) {
            em.remove(representante);
        }

        em.remove(existe);
        registroRemoto.registrarEvento(this.getClass().getName(),
                                       "deleteUsuario", usuario);
    }

    public Representante getRepresentante(Long codigoUsuario) throws IdeamException {
        try {
            Query query = em.createNamedQuery("Representantes.findByUsuario");
            query.setParameter("codigoUsuario", codigoUsuario);
            Representante rep = (Representante)query.getSingleResult();
            this.loadAttributes(rep);
            return rep;
        } catch (NoResultException e) {
            return null;
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void deletePermisoVertimiento(PermisoVertimiento permiso) throws IdeamException {
        PermisoVertimiento existe =
            this.getPermisoVertimiento(permiso.getCodigo());
        if (existe != null) {
            em.remove(existe);
            registroRemoto.registrarEvento(this.getClass().getName(),
                                           "deletePermisoVertimiento",
                                           permiso);
        }
    }

    public PermisoVertimiento getPermisoVertimiento(Long codigo) throws IdeamException {
        try {
            Query query = em.createNamedQuery("PermisoVertimiento.find");
            query.setParameter("codigo", codigo);
            return (PermisoVertimiento)query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public PermisoVertimiento registrarPermiso(PermisoVertimiento permiso) throws IdeamException {
        if (permiso.getCodigo() == null) {
            permiso.setCodigo(GeneradorSeq.obtenerSequencia(permiso.getCodigoAutoridadAmbiental(),
                                                            "seq_usuario_agua",
                                                            em));
            em.persist(permiso);
            em.flush();
            em.refresh(permiso);
        } else {
            em.merge(permiso);
        }
        registroRemoto.registrarEvento(this.getClass().getName(),
                                       "registrarPermiso", permiso);
        return permiso;
    }


    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public Predio registrarPredio(UsuarioAgua usu,
                                  Predio predio) throws IdeamException {
        if (predio.getCodigo() == null) {
            predio.setCodigo(GeneradorSeq.obtenerSequencia(predio.getCodigoAutoridadAmbiental(),
                                                           "seq_usuario_agua",
                                                           em));
        }
        em.merge(predio);
        registroRemoto.registrarEvento(this.getClass().getName(),
                                       "updatePredio", usu, predio);
        return predio;
    }


    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void updateUsuario(UsuarioAgua usuario) throws IdeamException {
        if (usuario == null) {
            throw new IdeamException("Entidad Usuario no puede ser null");
        }
        if (usuario.getCodigo() == null) {
            usuario.setCodigo(GeneradorSeq.obtenerSequencia(usuario.getCodigoAutoridad(),
                                                            "seq_usuario_agua",
                                                            em));
        }
        em.merge(usuario);
        if (usuario.getRepresentanteLegal() != null) {

            em.merge(usuario.getRepresentanteLegal());
        }
        registroRemoto.registrarEvento(this.getClass().getName(),
                                       "updateUsuario", usuario);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void borrarPredio(Predio predio) throws IdeamException {
        //Pilar
        List concesionesSinConc =
            this.getConcesionesNoValidas(predio); // este medoto  trae las concesion No validas
        if (concesionesSinConc != null) {
            Iterator it = concesionesSinConc.iterator();
            while (it.hasNext()) {
                Concesion con = (Concesion)it.next();
                this.borrarConcesion(con);
            }
        }
        //Fin Pilar
        List concesiones =
            this.getConcesiones(predio); // este medoto solo trae las concesion validas
        if (concesiones != null) {
            Iterator it = concesiones.iterator();
            while (it.hasNext()) {
                Concesion con = (Concesion)it.next();
                this.borrarConcesion(con);
            }
        }


        List permisos = this.getPermisosVertimiento(predio);
        if (permisos != null) {
            Iterator it = permisos.iterator();
            while (it.hasNext()) {
                PermisoVertimiento per = (PermisoVertimiento)it.next();
                this.borrarPermiso(per);
            }
        }
        Predio existe = this.getPredio(predio.getCodigo());
        if (existe != null) {
            em.remove(existe);
        }
        registroRemoto.registrarEvento(this.getClass().getName(),
                                       "borrarPredio", predio);
    }

    public Predio getPredio(Long codigo) throws IdeamException {
        Query query = em.createNamedQuery("Predio.find");
        query.setParameter("codigo", codigo);
        Predio predio = (Predio)query.getSingleResult();
        this.loadAttributes(predio);
        return predio;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void updatePredio(UsuarioAgua usuario,
                             Predio predio) throws IdeamException {
        if (usuario.getCodigoTipoPersona() != UsuarioAgua.MUNICIPIO &&
            usuario.getCodigoTipoPersona() != UsuarioAgua.ACUEDUCTO &&
            usuario.getCodigoTipoPersona() != UsuarioAgua.MEGAPROYECTO) {
            PrediosTO existe =
                this.getPredioTO(predio.getCedulaCatastral(), usuario.getNumeroDocumento(),
                                 usuario.getCodigoAutoridad());
            if (existe != null) {
                if (predio.getCodigo() != null &&
                    predio.getCodigo().longValue() !=
                    existe.getCodigo().longValue()) {
                    throw new IdeamException("Este usuario ya cuenta con un predio con la misma cedula catastral");
                }
            }
        }
        if (predio.getTipoCentroPoblado() != null) {
            predio.setCodigoTipoCentroPoblado(predio.getTipoCentroPoblado().getCodigo().longValue());
        }
        if (predio.getCodigo() == null) {
            predio.setCodigo(GeneradorSeq.obtenerSequencia(predio.getCodigoAutoridadAmbiental(),
                                                           "seq_usuario_agua",
                                                           em));
        }
        em.merge(predio);
        registroRemoto.registrarEvento(this.getClass().getName(),
                                       "updatePredio", usuario, predio);
    }

    public Concesion getConcesion(Long codigo) throws IdeamException {
        try {
            Query query = em.createNamedQuery("Concesion.find");
            query.setParameter("codigo", codigo);
            return (Concesion)query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    //Pilar

    public Concesion getConcesionByAutoridadAmbiental(String codigoExpediente,
                                                      Long idAutoridad) throws IdeamException {
        try {
            Query query = em.createNamedQuery("Concesion.findByIdAndAA");
            query.setParameter("numeroExpediente", codigoExpediente);
            query.setParameter("idAutoridad", idAutoridad);
            return (Concesion)query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void deleteConcesion(Concesion concesion) throws IdeamException {
        Concesion existe = this.getConcesion(concesion.getCodigo());
        if (existe != null) {
            em.remove(existe);
        }
        registroRemoto.registrarEvento(this.getClass().getName(),
                                       "deleteConcesion", concesion);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public Concesion registrarConcesion(Concesion concesion) throws IdeamException {
        if (concesion.getCodigo() == null) {
            concesion.setCodigo(GeneradorSeq.obtenerSequencia(concesion.getCodigoAutoridadAmbiental(),
                                                              "seq_usuario_agua",
                                                              em));

            em.persist(concesion);
            em.flush();
            em.refresh(concesion);
        } else {
            concesion = em.merge(concesion);
        }
        registroRemoto.registrarEvento(this.getClass().getName(),
                                       "registrarConcesion", concesion);
        return concesion;
    }

    public List<PermisoVertimiento> getPermisosVertimiento(Predio predio) throws IdeamException {
        Query query = em.createNamedQuery("PermisoVertimiento.findAll");
        query.setParameter("codigoPredio", predio.getCodigo());
        return query.getResultList();
    }

    //Pilar

    public List<Captacion> getCaptacionesSinConcesion(Predio predio) throws IdeamException {
        Query query = em.createNamedQuery("RurtCaptacion.findBySinConcesion");

        if (predio != null && predio.getCodigo() != 0) {
            query.setParameter("idPredio", predio.getCodigo());
        }

        return query.getResultList();

    }


    public List<Concesion> getConcesiones(Predio predio) throws IdeamException {
        Query query = em.createNamedQuery("Concesion.findAll");
        query.setParameter("codigoPredio", predio.getCodigo());
        return query.getResultList();
    }

    public List<Concesion> getConcesionesNoValidas(Predio predio) throws IdeamException {
        Query query = em.createNamedQuery("Concesion.findByPredioSinConc");
        query.setParameter("codigoPredio", predio.getCodigo());
        return query.getResultList();
    }


    public List<Captacion> getCaptaciones(Concesion concesion) throws IdeamException {
        Query query = em.createNamedQuery("RurtCaptacion.findAllByConcesion");
        query.setParameter("idConcesion", concesion.getCodigo());
        return query.getResultList();
    }

    public List<Predio> getPredios(UsuarioAgua usuario) throws IdeamException {
        Query query = em.createNamedQuery("Predio.findAll");
        query.setParameter("codigoUsuario", usuario.getCodigo());
        List lista = query.getResultList();
        if (lista != null) {
            Iterator it = lista.iterator();
            while (it.hasNext()) {
                Predio p = (Predio)it.next();
                this.loadAttributes(p);
            }
        }
        return lista;
    }

    public UsuarioAgua getUsuario(Integer codigoTipoId, String numeroId,
                                  Long codigoAutoridad) throws IdeamException {
        try {
            Query query = em.createNamedQuery("UsurioAgua.findByDocumento");
            query.setParameter("codigoTipoDocumento", codigoTipoId);
            query.setParameter("numeroDocumento", numeroId);
            query.setParameter("codigoAutoridad", codigoAutoridad);
            UsuarioAgua usuario = (UsuarioAgua)query.getSingleResult();
            loadAttributes(usuario);
            return usuario;
        } catch (NoResultException e) {
            return null;
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public Object[] registrarUsuarioPredio(UsuarioAgua usuario, Predio predio,
                                           Representante representanteLegal,
                                           Autoridades autoridad) throws IdeamException {
        if (usuario.getTipoUsuario() != null) {
            usuario.setCodigoTipoPersona(usuario.getTipoUsuario().getCodigo());
        }
        if (usuario.getCodigoTipoPersona() == UsuarioAgua.JURIDICA_PRIVADA ||
            usuario.getCodigoTipoPersona() == UsuarioAgua.JURIDICA_PUBLICA) {
            if (representanteLegal == null) {
                throw new IdeamException("Falta la informaci�n del Representante Legal");
            }
        }

        if (usuario.getTipoDocumento() != null) {
            usuario.setCodigoTipoDocumento(usuario.getTipoDocumento().getCodigo());
        }
        usuario.setAutoridadAmbiental(autoridad);
        usuario.setCodigoAutoridad(autoridad.getId().longValue());
        if (usuario.getDepartamento() != null) {
            usuario.setCodigoDepartamento(usuario.getDepartamento().getId());
        }
        if (usuario.getMunicipio() != null) {
            usuario.setCodigoMunicipio(usuario.getMunicipio().getId());
        }
        if (usuario.getTipoUsuario() != null) {
            usuario.setCodigoTipoPersona(usuario.getTipoUsuario().getCodigo());
        }

        // Verificar si el usuario ya existe
        UsuarioAgua existe =
            this.getUsuario(usuario.getCodigoTipoDocumento(), usuario.getNumeroDocumento(),
                            autoridad.getId().longValue());
        Long codigoUsuario = null;
        if (existe == null) {
            usuario.setAutoridadAmbiental(autoridad);
            usuario.setCodigoAutoridad(autoridad.getId().longValue());
            if (usuario.getCodigo() == null) {
                usuario.setCodigo(GeneradorSeq.obtenerSequencia(usuario.getCodigoAutoridad(),
                                                                "seq_usuario_agua",
                                                                em));
                em.persist(usuario);
            } else {
                em.merge(usuario);
            }
            codigoUsuario = usuario.getCodigo();
            predio.setCodigoUsuario(codigoUsuario);
        } else {
            codigoUsuario = existe.getCodigo();
        }
        predio.setCodigoUsuario(codigoUsuario);
        predio.setAutoridadAmbiental(usuario.getAutoridadAmbiental());
        if (predio.getTipoCentroPoblado() != null) {
            predio.setCodigoTipoCentroPoblado(predio.getTipoCentroPoblado().getCodigo().longValue());
        }
        if (predio.getCodigo() == null) {
            predio.setCodigo(GeneradorSeq.obtenerSequencia(predio.getCodigoAutoridadAmbiental(),
                                                           "seq_usuario_agua",
                                                           em));

            em.persist(predio);
        } else {
            em.merge(predio);
        }

        if (usuario.getCodigoTipoPersona() == UsuarioAgua.CORPORACION ||
            usuario.getCodigoTipoPersona() == UsuarioAgua.JURIDICA_PRIVADA ||
            usuario.getCodigoTipoPersona() == UsuarioAgua.JURIDICA_PUBLICA ||
            usuario.getCodigoTipoPersona() == UsuarioAgua.MUNICIPIO ||
            usuario.getCodigoTipoPersona() == UsuarioAgua.ACUEDUCTO ||
            usuario.getCodigoTipoPersona() == UsuarioAgua.MEGAPROYECTO) {

            Representante representanteExiste =
                this.getRepresentante(codigoUsuario);
            if (representanteExiste != null) {
                representanteExiste.setApellidos(representanteLegal.getApellidos());
                representanteExiste.setCodigoDepartamento(representanteLegal.getCodigoDepartamento());
                representanteExiste.setCodigoMunicipio(representanteLegal.getCodigoMunicipio());
                representanteExiste.setCodigoTipoDocumento(representanteLegal.getCodigoTipoDocumento());
                representanteExiste.setCodigoAutoridadAmbiental(representanteLegal.getCodigoAutoridadAmbiental());
                representanteExiste.setDireccion(representanteLegal.getDireccion());
                representanteExiste.setEmail(representanteLegal.getEmail());
                representanteExiste.setNombres(representanteLegal.getNombres());
                representanteExiste.setNumeroDocumento(representanteLegal.getNumeroDocumento());
                representanteExiste.setTelefono(representanteLegal.getTelefono());
                em.merge(representanteExiste);
            } else {
                representanteLegal.setCodigoUsuario(codigoUsuario);
                representanteLegal.setCodigoAutoridadAmbiental(autoridad.getId().longValue());
                if (representanteLegal.getCodigo() == null) {
                    representanteLegal.setCodigo(GeneradorSeq.obtenerSequencia(representanteLegal.getCodigoAutoridadAmbiental(),
                                                                               "seq_usuario_agua",
                                                                               em));

                    em.persist(representanteLegal);
                } else {
                    em.merge(representanteLegal);
                }
            }
        }
        registroRemoto.registrarEvento(this.getClass().getName(),
                                       "updateUsuario", usuario);
        registroRemoto.registrarEvento(this.getClass().getName(),
                                       "updatePredio", usuario, predio);
        registroRemoto.registrarEvento(this.getClass().getName(),
                                       "registrarUsuarioPredio", usuario,
                                       predio, representanteLegal, autoridad);

        Object retorno[] = { codigoUsuario, predio.getCodigo() };
        return retorno;
    }

    public List getAllUsuarios() throws IdeamException {
        Query query = em.createNamedQuery("UsurioAguaTO.findAllUsers");
        List lista = query.getResultList();
        /*Iterator it = lista.iterator();
        while (it.hasNext()) {
            UsuarioAgua usuario = (UsuarioAgua)it.next();
            this.loadAttributes(usuario);
        }*/
        return lista;
    }

    public List getAllUsuarios(Long codigoAutoridad) throws IdeamException {
        Query query = em.createNamedQuery("UsurioAguaTO.findAll");
        query.setParameter("codigoAutoridad", codigoAutoridad);
        List lista = query.getResultList();
        /*Iterator it = lista.iterator();
        while (it.hasNext()) {
            UsuarioAgua usuario = (UsuarioAgua)it.next();
            this.loadAttributes(usuario);
        }*/
        return lista;
    }

    public UsuarioAgua getUSuario(Long codigo) throws IdeamException {
        try {
            Query query = em.createNamedQuery("UsurioAgua.find");
            query.setParameter("codigo", codigo);
            UsuarioAgua usuario = (UsuarioAgua)query.getSingleResult();
            loadAttributes(usuario);
            return usuario;
        } catch (NoResultException e) {
            return null;
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void updateUsuario(UsuarioAgua usuario,
                              Autoridades autoridad) throws IdeamException {
        if (usuario == null || autoridad == null) {
            throw new IdeamException("Parametros " + usuario + " " +
                                     autoridad);
        }
        usuario.setCodigoAutoridad(autoridad.getId().longValue());
        if (usuario.getCodigo() == null ||
            usuario.getCodigo().longValue() == 0) {
            usuario.setCodigo(GeneradorSeq.obtenerSequencia(usuario.getCodigoAutoridad(),
                                                            "seq_usuario_agua",
                                                            em));
            em.persist(usuario);
        } else {
            em.merge(usuario);
        }
        registroRemoto.registrarEvento(this.getClass().getName(),
                                       "updateUsuario", usuario, autoridad);
    }

    /**
     * Carga los atributos @Transient de la clase UsuarioAgua
     * @param usuario
     * @throws IdeamException
     */
    private void loadAttributes(UsuarioAgua usuario) throws IdeamException {
        Integer codigoTipoDocumento = usuario.getCodigoTipoDocumento();
        if (codigoTipoDocumento != null) {
            Lista tipoDocumento =
                parametrosService.getLista(codigoTipoDocumento);
            usuario.setTipoDocumento(tipoDocumento);
        }
        if (usuario.getCodigoMunicipio() != null) {
            Divipola municipio =
                parametrosService.getDivipolaById(usuario.getCodigoMunicipio());
            usuario.setMunicipio(municipio);
        }
        if (usuario.getCodigoDepartamento() != null) {
            Divipola depto =
                parametrosService.getDivipolaById(usuario.getCodigoDepartamento());
            usuario.setDepartamento(depto);
        }
        if (usuario.getCodigoAutoridad() != null) {
            Autoridades autoridadAmbiental =
                parametrosService.getAutoridad(usuario.getCodigoAutoridad().intValue());
            usuario.setAutoridadAmbiental(autoridadAmbiental);
        }
        if (usuario.getCodigoTipoPersona() != null) {
            Lista tipoUsuario =
                parametrosService.getLista(usuario.getCodigoTipoPersona());
            usuario.setTipoUsuario(tipoUsuario);
        }
        if (usuario.getCodigoActividadCiiu() != null) {
            ActividadCIIU ciiu =
                parametrosService.getActividadCiiu(usuario.getCodigoActividadCiiu());
            usuario.setActividadCiiu(ciiu);
        }
        if (usuario.getTipoUsuario().getCodigo().intValue() == 1 ||
            usuario.getTipoUsuario().getCodigo().intValue() == 3 ||
            usuario.getTipoUsuario().getCodigo().intValue() == 4 ||
            usuario.getTipoUsuario().getCodigo().intValue() == 2 ||
            usuario.getTipoUsuario().getCodigo().intValue() == 136 ||
            usuario.getTipoUsuario().getCodigo().intValue() == 956) {
            Representante representante =
                this.getRepresentante(usuario.getCodigo());
            if (representante != null) {
                usuario.setRepresentanteLegal(representante);
            } else {
                usuario.setErroresValidacion("Se requiere la informaci�n de representante legal");
            }
        }
        List predios = this.getPredios(usuario);
        if (predios == null || predios.size() == 0) {
            usuario.setErroresValidacion("El usuario no tiene informaci�n de predios");
        }
    }

    private void loadAttributes(Predio predio) throws IdeamException {
        if (predio.getCodigoMunicipio() != null) {
            Divipola municipio =
                parametrosService.getDivipolaById(predio.getCodigoMunicipio());
            predio.setMunicipio(municipio);
        }
        if (predio.getCodigoDepartamento() != null) {
            Divipola depto =
                parametrosService.getDivipolaById(predio.getCodigoDepartamento());
            predio.setDepartamento(depto);
        }
        if (predio.getCodigoAutoridadAmbiental() != null) {
            Autoridades autoridadAmbiental =
                parametrosService.getAutoridad(predio.getCodigoAutoridadAmbiental().intValue());
            predio.setAutoridadAmbiental(autoridadAmbiental);
        }
        if (predio.getCodigoTipoSuelo() != null) {
            Lista tipoSuelo =
                parametrosService.getLista(predio.getCodigoTipoSuelo().intValue());
            predio.setTipoSuelo(tipoSuelo);
        }
        if (predio.getCodigoSistemaReferencia() != null) {
            Lista sistema =
                parametrosService.getLista(predio.getCodigoSistemaReferencia().intValue());
            predio.setSistemaReferencia(sistema);
        }
        if (predio.getCodigoTipoCentroPoblado() != null) {
            Lista tipoSuelo =
                parametrosService.getLista(predio.getCodigoTipoCentroPoblado().intValue());
            predio.setTipoCentroPoblado(tipoSuelo);
        }
    }

    private void loadAttributes(Representante representante) throws IdeamException {
        if (representante.getCodigoTipoDocumento() != null) {
            Lista tipoDocumento =
                parametrosService.getLista(representante.getCodigoTipoDocumento());
            representante.setTipoDocumento(tipoDocumento);
        }
        if (representante.getCodigoDepartamento() != null) {
            Divipola depto =
                parametrosService.getDivipolaById(representante.getCodigoDepartamento());
            representante.setDepartamento(depto);
        }
        if (representante.getCodigoMunicipio() != null) {
            Divipola municipio =
                parametrosService.getDivipolaById(representante.getCodigoMunicipio());
            representante.setMunicipio(municipio);
        }

    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public Captacion createCaptacion(Captacion captacion) throws IdeamException {
        List<RurtCaptacionComponentes> componentes =
            captacion.getRurtCaptacionComponentesList();
        captacion.setCodigo(GeneradorSeq.obtenerSequencia(captacion.getCodigoAutoridad(),
                                                          "SEQ_CAPTACIONES",
                                                          em));

        em.persist(captacion);
        //captacion = em.merge(captacion);
        em.flush();
        em.refresh(captacion);


        CaptacionPOJO cpt = this.armarObjetoCaptacionCola(captacion);

        registroRemoto.registrarEvento(this.getClass().getName(),
                                       "createCaptacionPlano", cpt);


        if (componentes != null) {
            for (RurtCaptacionComponentes componente : componentes) {
                componente.setIdCaptacion(captacion.getCodigo());
                this.createCaptacionComponente(componente);

            }
        }
        /*
        //esto se hizo as� porque se presento un problema con la cola que
        //enviaba primero los componentes y por �ltimo la captaci�n,
        //dejando inconsistente la bd en el servidor.

        //consultar los componentes recien creados.
        List<RurtCaptacionComponentes> componentesCreados =
            this.getComponentesByCaptacion(captacion.getCodigo());

        if(componentesCreados!=null){
            for(RurtCaptacionComponentes componente : componentesCreados){
                componente.setCodigoAutoridad(codigoAutoridad);
                registroRemoto.registrarEvento(this.getClass().getName(),
                                               "createCaptacionComponente", componente);
            }
        }
        */
        return captacion;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public Captacion updateCaptacion(Captacion captacion) throws IdeamException {
        captacion = em.merge(captacion);
        em.flush();
        //em.refresh(captacion);

        CaptacionPOJO cpt = this.armarObjetoCaptacionCola(captacion);

        registroRemoto.registrarEvento(this.getClass().getName(),
                                       "updateCaptacionPlano", cpt);

        return captacion;
    }

    public Captacion getCaptacion(Long id) throws IdeamException {
        Captacion cp = null;
        try {
            Query query = em.createNamedQuery("RurtCaptacion.findById");
            query.setParameter("id", id);
            cp = (Captacion)query.getSingleResult();
            if (cp.getIdDepartamento() != null) {
                Long idDpto = new Long(cp.getIdDepartamento());

                Divipola dpto = parametrosService.getDivipolaById(idDpto);
                cp.setNombreDpto(dpto.getNombre());

            }
            if (cp.getIdMunicipio() != null) {
                Long idmunicipio = new Long(cp.getIdMunicipio());

                Divipola municipio =
                    parametrosService.getDivipolaById(idmunicipio);
                cp.setNombreMcpo(municipio.getNombre());

            }
        } catch (Exception e) {
            throw new IdeamException("Problemas! \\nno existe captacion con los datos de b�squedad.");
        } finally {
            return cp;
        }
    }

    public CaptacionTO getCaptacion(String identificadorPunto,
                                    Integer autoridad) throws IdeamException {
        Query query = em.createNamedQuery("CaptacionTO.findByIdPunto");
        query.setParameter("idPunto", identificadorPunto);
        query.setParameter("codigoAutoridad", autoridad);

        List<CaptacionTO> result = (List<CaptacionTO>)query.getResultList();

        if (result != null && !result.isEmpty()) {
            return result.get(0); //por acuerdo con Pilar, devolver el primer resultado siempre.
        } else {
            return null;
        }

    }

    //dl

    public List<CaptacionDetalle> getAllCaptacionDetalle(Autoridades autoridad) {
        Query query =
            em.createNamedQuery("CaptacionDetalle.findByIdAutoridad");
        query.setParameter("idAutoridad", autoridad.getId());

        return query.getResultList();
    }
    //dl

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void deleteCaptacion(Captacion captacion) throws IdeamException {
        try {
            //los usos asociados deben ser borrados.

            em.flush();
            captacion = em.merge(captacion);


            //consultar los componentes.
            List<RurtCaptacionComponentes> componentes =
                this.getComponentesByCaptacion(captacion.getCodigo());

            if (componentes != null) {
                for (RurtCaptacionComponentes componente : componentes) {
                    registroRemoto.registrarEvento(this.getClass().getName(),
                                                   "deleteCaptacionComponente",
                                                   componente);
                }
            }


            //CaptacionPOJO cpt = this.armarObjetoCaptacionCola(captacion);
            CaptacionPOJO cpt = new CaptacionPOJO();
            cpt.setCodigo(captacion.getCodigo());
            registroRemoto.registrarEvento(this.getClass().getName(),
                                           "deleteCaptacionPlano", cpt);

            em.remove(captacion);

        } finally {
        }
    }

    public Captacion getCaptacionWithComponentes(Long id) throws IdeamException,
                                                                 NoResultException {
        Captacion result = null;
        Query query =
            em.createNamedQuery("RurtCaptacion.findByIdWithComponentes");
        query.setParameter("id", id);
        try {
            result = (Captacion)query.getSingleResult();
        } catch (NoResultException e) {
            //result = this.getTramo(id);
        }
        return result;
    }

    public List getComponentesByCaptacion(Long id) throws IdeamException,
                                                          NoResultException {
        List result = null;
        Query query =
            em.createNamedQuery("RurtCaptacionComponentes.findByCaptacion");
        query.setParameter("id", id);
        result = query.getResultList();
        return result;
    }


    public List<Captacion> getCaptacionesByAutoridadConcesion(Concesion concesion,
                                                              Autoridades autoridad) throws IdeamException {
        Query query =
            em.createNamedQuery("RurtCaptacion.findAllByConcesionAutoridad");
        query.setParameter("idConcesion", concesion.getCodigo());
        //query.setParameter("idAutotidad", autoridad.getId());
        return query.getResultList();
    }


    public List<CaptacionTO> getAllCaptacionesBusqueda(CriteriosBusquedaTO criterios) throws IdeamException {

        String sql = " Select  vv.*  ";

        sql += " From sirh_captaciones_v vv ";
        sql += " Where ";


        if (criterios.getAutoridad() != null &&
            criterios.getAutoridad().getId() != null &&
            new Long(criterios.getAutoridad().getId()) == Constantes.IDEAM) {
            sql += " vv.autoridad <>?1 "; //si es ideam puede ver todo
        } else {
            sql += " vv.autoridad =?1 "; //si es autoridad, solo lo suyo.
        }


        int totalParametros = 1;

        if (criterios.getDepartamento() != null &&
            criterios.getDepartamento().getId() != null) {
            totalParametros++;
            sql += " and vv.depto_capt =?" + totalParametros;
        }
        if (criterios.getMunicipio() != null &&
            criterios.getMunicipio().getId() != null) {
            totalParametros++;
            sql += " and vv.mun_capt =?" + totalParametros;
        }
        if (criterios.getConcesion() != null &&
            criterios.getConcesion().getCodigo() != null) {
            totalParametros++;
            sql += " and vv.id_concesion_capt =?" + totalParametros;
        }
        if (criterios.getUso() != null &&
            criterios.getUso().getCodigo() != null) {
            totalParametros++;
            sql +=
" and vv.id in (select u.id_captacion from rurt_captacion_uso u where u.tipo_uso =?" +
 totalParametros + ")";
        }
        if (criterios.getIdFuente() != null) {
            totalParametros++;
            sql +=
" and vv.id_fuente_capt in (select u.id from fntt_fuente u where u.id=?" +
 totalParametros + ")";
        }
        if (criterios.getArea() != null &&
            criterios.getArea().getId() != null) {
            totalParametros++;
            sql += " and vv.id_area_capt =?" + totalParametros;
        }
        if (criterios.getZona() != null &&
            criterios.getZona().getId() != null) {
            totalParametros++;
            sql += " and vv.id_zona_capt =?" + totalParametros;
        }
        if (criterios.getSubzona() != null &&
            criterios.getSubzona().getId() != null) {
            totalParametros++;
            sql += " and vv.id_subzona_capt =?" + totalParametros;
        }

        Query q = em.createNativeQuery(sql, CaptacionTO.class);

        //ahora se adicionan los parametros
        q.setParameter(1,
                       criterios.getAutoridad().getId()); //ideam o autoridad
        totalParametros = 1;
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
        if (criterios.getConcesion() != null &&
            criterios.getConcesion().getCodigo() != null) {
            totalParametros++;
            q.setParameter(totalParametros,
                           criterios.getConcesion().getCodigo());
        }
        if (criterios.getUso() != null) {
            totalParametros++;
            q.setParameter(totalParametros, criterios.getUso().getCodigo());
        }
        if (criterios.getIdFuente() != null) {
            totalParametros++;
            q.setParameter(totalParametros, criterios.getIdFuente());
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
            totalParametros++;
            q.setParameter(totalParametros, criterios.getSubzona().getId());
        }

        List lista = q.getResultList();

        return lista;
    }


    public List<CaptacionSubTO> getAllCaptacionesBusquedaSub(CriteriosBusquedaTO criterios) throws IdeamException {

        String sql = "Select ";
        sql += " * ";
        sql += " From sirh_captaciones_sub_v ";
        sql += " Where ";


        if (criterios.getAutoridad() != null &&
            criterios.getAutoridad().getId() != null &&
            new Long(criterios.getAutoridad().getId()) == Constantes.IDEAM) {
            sql += " autoridad <>?1 "; //si es ideam puede ver todo
        } else {
            sql += " autoridad =?1 "; //si es autoridad, solo lo suyo.
        }

        int totalParametros = 1;

        if (criterios.getDepartamento() != null &&
            criterios.getDepartamento().getId() != null) {
            totalParametros++;
            sql += " and depto_capt =?" + totalParametros;
        }
        if (criterios.getMunicipio() != null &&
            criterios.getMunicipio().getId() != null) {
            totalParametros++;
            sql += " and mun_capt =?" + totalParametros;
        }
        if (criterios.getConcesion() != null &&
            criterios.getConcesion().getCodigo() != null) {
            totalParametros++;
            sql += " and id_concesion_capt =?" + totalParametros;
        }
        if (criterios.getUso() != null &&
            criterios.getUso().getCodigo() != null) {
            totalParametros++;
            sql +=
" and id in (select u.id_captacion from rurt_captacion_uso u where u.tipo_uso =?" +
 totalParametros + ")";
        }
        if (criterios.getIdFuente() != null) {
            totalParametros++;
            sql +=
" and id_fuente_capt in (select u.id from fntt_fuente u where u.id=?" +
 totalParametros + ")";
        }
        if (criterios.getArea() != null &&
            criterios.getArea().getId() != null) {
            totalParametros++;
            sql += " and id_area_capt =?" + totalParametros;
        }
        if (criterios.getZona() != null &&
            criterios.getZona().getId() != null) {
            totalParametros++;
            sql += " and id_zona_capt =?" + totalParametros;
        }
        if (criterios.getSubzona() != null &&
            criterios.getSubzona().getId() != null) {
            totalParametros++;
            sql += " and id_subzona_capt =?" + totalParametros;
        }

        if (criterios.getProvinciahidro() != null &&
            criterios.getProvinciahidro().getCodigo() != null) {
            totalParametros++;
            sql += " and provincia_hidrogeologica_capt =?" + totalParametros;
        }


        Query q = em.createNativeQuery(sql, CaptacionSubTO.class);

        //ahora se adicionan los parametros
        q.setParameter(1,
                       criterios.getAutoridad().getId()); //ideam o autoridad
        totalParametros = 1;
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
        if (criterios.getConcesion() != null &&
            criterios.getConcesion().getCodigo() != null) {
            totalParametros++;
            q.setParameter(totalParametros,
                           criterios.getConcesion().getCodigo());
        }
        if (criterios.getUso() != null) {
            totalParametros++;
            q.setParameter(totalParametros, criterios.getUso().getCodigo());
        }
        if (criterios.getIdFuente() != null) {
            totalParametros++;
            q.setParameter(totalParametros, criterios.getIdFuente());
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
            totalParametros++;
            q.setParameter(totalParametros, criterios.getSubzona().getId());
        }

        if (criterios.getProvinciahidro() != null &&
            criterios.getProvinciahidro().getCodigo() != null) {
            totalParametros++;
            q.setParameter(totalParametros,
                           criterios.getProvinciahidro().getCodigo());
        }
        List lista = q.getResultList();
        return lista;
    }

    /*
    public List<CaptacionTO> getAllCaptacionesBusqueda(CriteriosBusquedaTO criterios)throws IdeamException{
        Query query = em.createNamedQuery("CaptacionTO.findAll");
        //query.setParameter("idConcesion", concesion.getCodigo());
        //query.setParameter("idAutotidad", autoridad.getId());
        return query.getResultList();
    }
    */


    public List<PuntoVertimiento> getVertimientos(PermisoVertimiento permiso) throws IdeamException {
        Query query = em.createNamedQuery("PuntoVertimiento.findAllByPermiso");
        query.setParameter("idPermiso", permiso.getCodigo());
        return query.getResultList();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public PuntoVertimiento createVertimiento(PuntoVertimiento vertimiento) throws IdeamException {

        vertimiento.setId(GeneradorSeq.obtenerSequencia(vertimiento.getCodigoAutoridad(),
                                                        "SEQ_VERTIMIENTOS",
                                                        em));

        List<PuntoVertimientoTratamiento> tratamientos =
            vertimiento.getListTratamientos();

        em.persist(vertimiento);
        //captacion = em.merge(captacion);
        em.flush();
        em.refresh(vertimiento);

        try {
            PuntoVertimientoPOJO vrt =
                this.armarObjetoPuntoVertimientoCola(vertimiento);
            registroRemoto.registrarEvento(this.getClass().getName(),
                                           "createVertimientoPlano", vrt);
        } catch (Exception e) {
            System.out.println("XXXXXXXXXXXXXXXXX---ERROR EN LA TRANSMISI�N CREATE VERTIMIENTO : " +
                               e.getMessage());
        }
        try {
            if (tratamientos != null && !tratamientos.isEmpty()) {
                for (PuntoVertimientoTratamiento tratamiento : tratamientos) {
                    tratamiento.setIdPuntoVertimiento(vertimiento.getId());
                    this.createPuntoTratamiento(tratamiento);
                }
            }
        } catch (Exception e) {
            System.out.println("XXXXXXXXXXXXXXXXX---ERROR EN LA TRANSMISI�N CREATE TRATAMIENTO VERTIMIENTO los tratamientos: " +
                               e.getMessage());
        }

        return vertimiento;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public PuntoVertimiento updateVertimiento(PuntoVertimiento vertimiento) throws IdeamException {
        List<PuntoVertimientoTratamiento> tratamientos =
            vertimiento.getListTratamientos();

        vertimiento = em.merge(vertimiento);
        em.flush();
        em.refresh(vertimiento);

        try {


            PuntoVertimientoPOJO vrt =
                this.armarObjetoPuntoVertimientoCola(vertimiento);

            registroRemoto.registrarEvento(this.getClass().getName(),
                                           "updateVertimientoPlano", vrt);

            Thread.sleep(3000L); //espere 3 segundos para asegurar que se transmite el tramo.

            //borrar los tratamientos anteriores.
            List<PuntoVertimientoTratamiento> tratamientoExistentes =
                this.getTratamientosByPuntoVertimiento(vertimiento.getId());
            if (tratamientoExistentes != null) {
                for (PuntoVertimientoTratamiento tratamiento :
                     tratamientoExistentes) {
                    this.deletePuntoTratamiento(tratamiento);
                }
            }
            Thread.sleep(3000L); //espere 3 segundos para asegurar que se transmite el tramo.

            if (tratamientos != null) {
                for (PuntoVertimientoTratamiento tratamiento : tratamientos) {
                    tratamiento.setIdPuntoVertimiento(vertimiento.getId());
                    this.createPuntoTratamiento(tratamiento);
                }
            }
        } catch (Exception e) {
            System.out.println("XXXXXXXXXXXXXXXXX---ERROR EN LA TRANSMISI�N CREATE VERTIMIENTO los tratamientos: " +
                               e.getMessage());
        }
        return vertimiento;
    }

    public PuntoVertimiento getVertimiento(Long id) throws IdeamException {

        PuntoVertimiento vert = null;
        try {
            Query query = em.createNamedQuery("PuntoVertimiento.findById");
            query.setParameter("id", id);
            vert = (PuntoVertimiento)query.getSingleResult();


            Long idDpto = new Long(vert.getIdDepartamento());
            if (idDpto != null) {
                Divipola dpto = parametrosService.getDivipolaById(idDpto);
                vert.setNombreDpto(dpto.getNombre());
            }
            Long idmunicipio = new Long(vert.getIdMunicipio());
            if (idmunicipio != null) {
                Divipola municipio =
                    parametrosService.getDivipolaById(idmunicipio);
                vert.setNombreMcpo(municipio.getNombre());
            }
        } catch (Exception e) {

        }
        return vert;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void deleteVertimiento(PuntoVertimiento vertimiento) throws IdeamException {
        try {
            em.flush();
            vertimiento = em.merge(vertimiento);

            //PuntoVertimientoPOJO vrt = this.armarObjetoPuntoVertimientoCola(vertimiento);
            PuntoVertimientoPOJO ptovrt = new PuntoVertimientoPOJO();
            ptovrt.setId(vertimiento.getId());
            registroRemoto.registrarEvento(this.getClass().getName(),
                                           "deleteVertimientoPlano", ptovrt);

            em.remove(vertimiento);

            //borrar los tratamientos anteriores.
            List<PuntoVertimientoTratamiento> tratamientoExistentes =
                this.getTratamientosByPuntoVertimiento(vertimiento.getId());
            if (tratamientoExistentes != null) {
                for (PuntoVertimientoTratamiento tratamiento :
                     tratamientoExistentes) {
                    this.deletePuntoTratamiento(tratamiento);
                }
            }
        } finally {
        }
    }

    public List<PuntoVertimientoTO> getAllVertimientosBusqueda(CriteriosBusquedaTO criterios) throws IdeamException {

        String sql = "Select ";
        sql += " * ";
        sql += " From sirh_vertimientos_v ";
        sql += " Where ";


        if (criterios.getAutoridad() != null &&
            criterios.getAutoridad().getId() != null &&
            new Long(criterios.getAutoridad().getId()) == Constantes.IDEAM) {
            sql += " autoridad <>?1 "; //si es ideam puede ver todo
        } else {
            sql += " autoridad =?1 "; //si es autoridad, solo lo suyo.
        }

        int totalParametros = 2;

        if (criterios.getDepartamento() != null &&
            criterios.getDepartamento().getId() != null) {
            sql += " and departamento =?" + totalParametros++;
        }
        if (criterios.getMunicipio() != null &&
            criterios.getMunicipio().getId() != null) {
            sql += " and municipio =?" + totalParametros++;
        }
        if (criterios.getPermiso() != null &&
            criterios.getPermiso().getCodigo() != null) {
            sql += " and id_permiso =?" + totalParametros++;
        }
        if (criterios.getIdFuente() != null) {
            totalParametros++;
            sql +=
" and id_fuente in (select u.id from fntt_fuente u where u.id=?" +
 totalParametros + ")";
        }
        if (criterios.getArea() != null &&
            criterios.getArea().getId() != null) {
            totalParametros++;
            sql += " and id_area =?" + totalParametros;
        }
        if (criterios.getZona() != null &&
            criterios.getZona().getId() != null) {
            totalParametros++;
            sql += " and id_zona =?" + totalParametros;
        }
        if (criterios.getSubzona() != null &&
            criterios.getSubzona().getId() != null) {
            totalParametros++;
            sql += " and id_subzona =?" + totalParametros;
        }


        if (criterios.getTipoVertimiento() != null &&
            criterios.getTipoVertimiento().getCodigo() != null) {
            totalParametros++;
            sql += " and tipo_vertimiento =?" + totalParametros;
        }


        Query q = em.createNativeQuery(sql, PuntoVertimientoTO.class);

        //ahora se adicionan los parametros
        q.setParameter(1,
                       criterios.getAutoridad().getId()); //ideam o autoridad

        totalParametros = 2;
        if (criterios.getDepartamento() != null &&
            criterios.getDepartamento().getId() != null) {
            q.setParameter(totalParametros++,
                           criterios.getDepartamento().getId());
        }
        if (criterios.getMunicipio() != null &&
            criterios.getMunicipio().getId() != null) {
            q.setParameter(totalParametros++,
                           criterios.getMunicipio().getId());
        }
        if (criterios.getPermiso() != null &&
            criterios.getPermiso().getCodigo() != null) {
            q.setParameter(totalParametros++,
                           criterios.getPermiso().getCodigo());
        }
        if (criterios.getIdFuente() != null) {
            totalParametros++;
            q.setParameter(totalParametros, criterios.getIdFuente());
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
            totalParametros++;
            q.setParameter(totalParametros, criterios.getSubzona().getId());
        }

        if (criterios.getTipoVertimiento() != null &&
            criterios.getTipoVertimiento().getCodigo() != null) {
            totalParametros++;
            q.setParameter(totalParametros,
                           criterios.getTipoVertimiento().getCodigo());
        }

        List lista = q.getResultList();

        return lista;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public RurtCaptacionComponentes createCaptacionComponente(RurtCaptacionComponentes captacionComponente) throws IdeamException {

        if (captacionComponente.getId() == null) {
            captacionComponente.setId(GeneradorSeq.obtenerSequencia(captacionComponente.getCodigoAutoridad(),
                                                                    "SEQ_COMPONENTES_CAPTACION",
                                                                    em));

            em.persist(captacionComponente);
        } else {
            em.merge(captacionComponente);
        }
        //captacion = em.merge(captacion);
        em.flush();
        em.refresh(captacionComponente);

        RurtCaptacionComponentePOJO pojo = new RurtCaptacionComponentePOJO();

        pojo.setId(captacionComponente.getId());
        pojo.setIdCaptacion(captacionComponente.getIdCaptacion());
        pojo.setIdComponente(captacionComponente.getIdComponente());

        registroRemoto.registrarEvento(this.getClass().getName(),
                                       "createCaptacionComponentePlano", pojo);

        return captacionComponente;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void deleteCaptacionComponente(RurtCaptacionComponentes captacionComponente) throws IdeamException {
        try {
            em.flush();
            captacionComponente = em.merge(captacionComponente);

            registroRemoto.registrarEvento(this.getClass().getName(),
                                           "deleteCaptacionComponente",
                                           captacionComponente);

            em.remove(captacionComponente);
        } finally {
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public PuntoVertimientoTratamiento createPuntoTratamiento(PuntoVertimientoTratamiento puntoTratamiento) throws IdeamException {
        try {
            puntoTratamiento.setId(GeneradorSeq.obtenerSequencia(puntoTratamiento.getCodigoAutoridad(),
                                                                 "SEQ_TRATAMIENTO_VERTIMIENTO",
                                                                 em));
            em.persist(puntoTratamiento);

        } catch (Exception e) {
            //System.out.println("ERROR Creando punto tratamiento "+e.getMessage());
        }
        try {
            PuntoVertimientoTratamientoPOJO pojo =
                new PuntoVertimientoTratamientoPOJO();
            pojo.setId(puntoTratamiento.getId());
            pojo.setIdCategoria(puntoTratamiento.getIdCategoria());
            pojo.setIdPuntoVertimiento(puntoTratamiento.getIdPuntoVertimiento());
            pojo.setIdTratamiento(puntoTratamiento.getIdTratamiento());
            registroRemoto.registrarEvento(this.getClass().getName(),
                                           "createPuntoTratamientoPlano",
                                           pojo);
        } catch (Exception e) {
            //System.out.println("ERROR Creando punto tratamiento PLANO "+e.getMessage());
        }
        return puntoTratamiento;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void deletePuntoTratamiento(PuntoVertimientoTratamiento puntoTratamiento) throws IdeamException {
        try {
            em.flush();
            em.remove(puntoTratamiento);
            registroRemoto.registrarEvento(this.getClass().getName(),
                                           "deletePuntoTratamiento",
                                           puntoTratamiento);
        } catch (Exception e) {
            System.out.println("ERROR BORRANDO PUNTO DE TRATAMIENTO " +
                               e.getMessage());
        }
    }

    public List getTratamientosByPuntoVertimientoCategoria(Long idPunto,
                                                           Long idCategoria) throws IdeamException,
                                                                                    NoResultException {
        List result = null;
        Query query =
            em.createNamedQuery("PuntoVertimientoTratamiento.findByIdPuntoVertimientoCategoria");
        query.setParameter("idPunto", idPunto);
        query.setParameter("idCategoria", idCategoria.intValue());
        result = query.getResultList();
        return result;
    }

    public List getTratamientosByPuntoVertimiento(Long idPunto) throws IdeamException,
                                                                       NoResultException {
        List result = null;
        Query query =
            em.createNamedQuery("PuntoVertimientoTratamiento.findByIdPuntoVertimiento");
        query.setParameter("id", idPunto);
        result = query.getResultList();
        return result;
    }


    //listado de usos por captacion

    public List<RurtCaptacionUso> getUsos(Captacion captacion) throws IdeamException {
        Query query = em.createNamedQuery("RurtCaptacionUso.findByCaptacion");
        query.setParameter("id", captacion.getCodigo());

        List lista = query.getResultList();
        if (lista != null) {
            Iterator it = lista.iterator();
            while (it.hasNext()) {
                RurtCaptacionUso usos = (RurtCaptacionUso)it.next();
                Integer idTipoUso = usos.getTipoUso();
                if (idTipoUso != null) {
                    Lista tipoUso = parametrosService.getLista(idTipoUso);
                    usos.setNombreUso(tipoUso);
                }
            }
        }
        return lista;
    }

    public RurtCaptacionUso getUso(Long id) throws IdeamException {
        Query query = em.createNamedQuery("RurtCaptacionUso.findById");
        query.setParameter("id", id);
        return (RurtCaptacionUso)query.getSingleResult();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public RurtCaptacionUso createUso(RurtCaptacionUso uso) throws IdeamException {
        uso.setId(GeneradorSeq.obtenerSequencia(uso.getCodigoAutoridad(),
                                                "SEQ_USOS_CAPTACION", em));
        em.persist(uso);
        //captacion = em.merge(captacion);
        em.flush();
        em.refresh(uso);

        RurtCaptacionUsoPOJO pojo = new RurtCaptacionUsoPOJO();
        pojo.setAprovechamiento(uso.getAprovechamiento());
        pojo.setArea(uso.getArea());
        pojo.setCantidadAnimales(uso.getCantidadAnimales());
        pojo.setCantidadPersonasPermanentes(uso.getCantidadPersonasPermanentes());
        pojo.setCantidadPersonasTransitorias(uso.getCantidadPersonasTransitorias());
        pojo.setCaudalAsignado(uso.getCaudalAsignado());
        pojo.setCodigoAutoridad(uso.getCodigoAutoridad());
        pojo.setDescripcion(uso.getDescripcion());
        pojo.setDetalleUso(uso.getDetalleUso());
        pojo.setEficiencia(uso.getEficiencia());
        pojo.setId(uso.getId());
        pojo.setIdCaptacion(uso.getIdCaptacion());
        pojo.setOtro(uso.getOtro());
        pojo.setProduccion(uso.getProduccion());
        pojo.setTipo(uso.getTipo());
        pojo.setTipoUso(uso.getTipoUso());

        registroRemoto.registrarEvento(this.getClass().getName(),
                                       "createUsoPlano", pojo);

        return uso;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public RurtCaptacionUso updateUso(RurtCaptacionUso uso) throws IdeamException {
        uso = em.merge(uso);
        em.flush();
        //em.refresh(uso);

        registroRemoto.registrarEvento(this.getClass().getName(), "updateUso",
                                       uso);

        return uso;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void deleteUso(RurtCaptacionUso uso) throws IdeamException {
        try {
            em.flush();
            uso = em.merge(uso);

            registroRemoto.registrarEvento(this.getClass().getName(),
                                           "deleteUso", uso);

            em.remove(uso);
        } finally {
        }
    }

    public PrediosTO getPredioTO(String cedulaCatastral, String numDocumento,
                                 Long codigoAutoridad) throws IdeamException {
        try {
            Query query =
                em.createNamedQuery("PrediosTO.findByCedulaUsuarioAutoridad");
            if (cedulaCatastral == null) {
                cedulaCatastral = "";
            }
            query.setParameter("cedulaCatastral",
                               cedulaCatastral.toUpperCase());
            query.setParameter("numDocumento", numDocumento);
            query.setParameter("codigoAutoridad", codigoAutoridad);
            PrediosTO predio = (PrediosTO)query.getSingleResult();
            return predio;
        } catch (NoResultException e) {
            return null;
        }
    }


    public Predio getPredio(String cedulaCatastral, Long codigoUsuario,
                            Long codigoAutoridad) throws IdeamException {
        try {
         
    
          String sql = "select * from rurt_predios pp where  pp.cedula_catastral='"+ cedulaCatastral +"' and pp.id_usuario=" +codigoUsuario ; 
                      
          Query query = em.createNativeQuery(sql);
          Predio predio = (Predio)query.getSingleResult();
          
          System.out.println("predio existeeeeeeeeeee        .getCedulaCatastral()------------ "+predio.getCedulaCatastral());
          
            return predio;
        } catch (NoResultException e) {
            return null;
        }
    }


    public ConcesionTO getConcesionByExpedienteResolucionPredio(String numeroExpediente,
                                                                String numeroResolucion,
                                                                Long codigoPredio,
                                                                Long codigoAutoridad) throws IdeamException {
        try {
            Query query =
                em.createNamedQuery("ConcesionTO.findByExpedienteResolucionPredioAutoridad");
            query.setParameter("numeroExpediente",
                               numeroExpediente.toUpperCase());
            query.setParameter("resolucionCaudal",
                               numeroResolucion.toUpperCase());
            query.setParameter("codigoPredio", codigoPredio);
            query.setParameter("codigoAutoridad", codigoAutoridad);
            return (ConcesionTO)query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public CaptacionTO getCaptacion(Captacion captacion,
                                    Long codigoAutoridad) throws IdeamException {
        try {
            Query query =
                em.createNamedQuery("CaptacionTO.findByGeoreferencia");


            query.setParameter("gradosLat", captacion.getGradosLat());
            query.setParameter("minutosLat", captacion.getMinutosLat());
            query.setParameter("segundosLat", captacion.getSegundosLat());
            query.setParameter("gradosLong", captacion.getGradosLong());
            query.setParameter("minutosLong", captacion.getMinutosLong());
            query.setParameter("segundosLong", captacion.getSegundosLong());
            query.setParameter("idConcesion",
                               captacion.getIdConcesion().getCodigo());
            query.setParameter("codigoAutoridad", codigoAutoridad.intValue());
            // query.setParameter("nombreFuente", " ");
            //  query.setParameter("nombreTramo", " ");
            CaptacionTO captacionTO = (CaptacionTO)query.getSingleResult();
            return captacionTO;
        } catch (NoResultException e) {
            //System.out.println("error al validar captacion");
            return null;
        }
    }

    public RurtCaptacionComponentes getComponenteCaptacion(Long idComponente,
                                                           Long idCaptacion) throws IdeamException {
        try {
            Query query =
                em.createNamedQuery("RurtCaptacionComponentes.findByComponenteCaptacion");
            query.setParameter("idComponente", idComponente);
            query.setParameter("id", idCaptacion);
            RurtCaptacionComponentes captacionComponente =
                (RurtCaptacionComponentes)query.getSingleResult();
            return captacionComponente;
        } catch (NoResultException e) {
            return null;
        }
    }

    public PuntoVertimientoTO getVertimiento(PuntoVertimiento vertimiento,
                                             Long codigoAutoridad) throws IdeamException {
        try {
            Query query =
                em.createNamedQuery("PuntoVertimientoTO.findByGeoreferencia");
            query.setParameter("gradosLat", vertimiento.getGradosLat());
            query.setParameter("minutosLat", vertimiento.getMinutosLat());
            query.setParameter("segundosLat", vertimiento.getSegundosLat());
            query.setParameter("gradosLong", vertimiento.getGradosLong());
            query.setParameter("minutosLong", vertimiento.getMinutosLong());
            query.setParameter("segundosLong", vertimiento.getSegundosLong());
            query.setParameter("idPermiso",
                               vertimiento.getIdPermisoVertimiento().getCodigo());
            query.setParameter("codigoAutoridad", codigoAutoridad.intValue());
            PuntoVertimientoTO vertimientoTO =
                (PuntoVertimientoTO)query.getSingleResult();
            return vertimientoTO;
        } catch (NoResultException e) {
            return null;
        }
    }

    public PermisoVertimientoTO getVertimientoByExpedienteResolucionPredio(String numeroExpediente,
                                                                           String numeroResolucion,
                                                                           Long codigoPredio,
                                                                           Long codigoAutoridad) throws IdeamException {
        try {
            Query query =
                em.createNamedQuery("PermisoVertimientoTO.findByExpedienteResolucionPredioAutoridad");
            query.setParameter("numeroExpediente",
                               numeroExpediente.toUpperCase());
            query.setParameter("resolucionPermisoVertimiento",
                               numeroResolucion.toUpperCase());
            query.setParameter("codigoPredio", codigoPredio);
            query.setParameter("codigoAutoridad", codigoAutoridad);
            return (PermisoVertimientoTO)query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public PuntoVertimientoTratamiento getTratamientoPunto(PuntoVertimientoTratamiento tratamientoPunto) throws IdeamException {
        try {
            Query query =
                em.createNamedQuery("PuntoVertimientoTratamiento.findByIdPuntoCategoriaTratamiento");
            query.setParameter("idPunto",
                               tratamientoPunto.getIdPuntoVertimiento());
            query.setParameter("idCategoria",
                               tratamientoPunto.getIdCategoria());
            query.setParameter("idTratamiento",
                               tratamientoPunto.getIdTratamiento());
            PuntoVertimientoTratamiento punto =
                (PuntoVertimientoTratamiento)query.getSingleResult();
            return punto;
        } catch (NoResultException e) {
            return null;
        }
    }

    public List getCaptacionByFuente(Long idFuente) throws IdeamException {
        try {
            Query query = em.createNamedQuery("RurtCaptacion.findByFuente");
            query.setParameter("idFuente", idFuente);
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List getCaptacionByTramo(Long idTramo) throws IdeamException {
        try {
            Query query = em.createNamedQuery("RurtCaptacion.findByTramo");
            query.setParameter("idTramo", idTramo);
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }


    public List getVertimientoByFuente(Long idFuente) throws IdeamException {
        try {
            Query query = em.createNamedQuery("PuntoVertimiento.findByFuente");
            query.setParameter("idFuente", idFuente);
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List getVertimientoByTramo(Long idTramo) throws IdeamException {
        try {
            Query query = em.createNamedQuery("PuntoVertimiento.findByTramo");
            query.setParameter("idTramo", idTramo);
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }


    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void borrarConcesion(Concesion concesion) throws IdeamException {
        List<Captacion> captaciones = this.getCaptaciones(concesion);
        for (Captacion capt : captaciones) {
            //primero las captaciones
            this.borrarCaptacion(capt);
        }

        //finalmente la concesion
        Concesion existe = this.getConcesion(concesion.getCodigo());
        if (existe != null) {
            em.remove(existe);
        }

        registroRemoto.registrarEvento(this.getClass().getName(),
                                       "borrarConcesion", concesion);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void borrarPermiso(PermisoVertimiento permiso) throws IdeamException {

        List<PuntoVertimiento> puntos = this.getVertimientos(permiso);
        for (PuntoVertimiento punto : puntos) {
            //primero los puntos de vertimiento
            this.borrarVertimiento(punto);
        }

        //finalmente el permiso
        PermisoVertimiento existe =
            this.getPermisoVertimiento(permiso.getCodigo());
        if (existe != null) {
            em.remove(existe);
        }

        registroRemoto.registrarEvento(this.getClass().getName(),
                                       "borrarPermiso", permiso);

    }


    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void borrarCaptacion(Captacion captacion) throws IdeamException {

        //primero revisar si existen puntos de monitoreo
        List puntosMonitoreo =
            calidadService.getPuntosMonitoreoCaptacion(captacion);
        if (puntosMonitoreo != null && !puntosMonitoreo.isEmpty()) {
            throw new IdeamException("Existen puntos de monitoreo relacionados a una captaci�n de la concesi�n: " +
                                     captacion.getIdConcesion().getNumeroExpediente());
        }

        //primero los usos
        List<RurtCaptacionUso> usos = this.getUsos(captacion);
        for (RurtCaptacionUso uso : usos) {
            em.remove(uso);
        }

        //luego los funias
        List<SubttFunias> funias = this.getFuniasByCaptacion(captacion);
        for (SubttFunias fun : funias) {
            em.remove(fun);
        }


        //luego los componentes
        List<RurtCaptacionComponentes> componentes =
            this.getComponentesByCaptacion(captacion.getCodigo());
        for (RurtCaptacionComponentes componente : componentes) {
            em.remove(componente);
        }

        //finalmente la concesion
        Captacion existe = this.getCaptacion(captacion.getCodigo());
        if (existe != null) {
            CaptacionPOJO cpt = this.armarObjetoCaptacionCola(existe);

            registroRemoto.registrarEvento(this.getClass().getName(),
                                           "borrarCaptacionPlano", cpt);
            em.remove(existe);
        }

        registroRemoto.registrarEvento(this.getClass().getName(),
                                       "borrarCaptacion", captacion);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void borrarVertimiento(PuntoVertimiento punto) throws IdeamException {

        //primero revisar si existen puntos de monitoreo
        List puntosMonitoreo = calidadService.getPuntosMonitoreoVert(punto);
        if (puntosMonitoreo != null && !puntosMonitoreo.isEmpty()) {
            throw new IdeamException("Existen puntos de monitoreo relacionados a un vertimiento del permiso: " +
                                     punto.getIdPermisoVertimiento().getNumeroExpediente());
        }

        //primero los tratamientos
        List<PuntoVertimientoTratamiento> tratamientos =
            this.getTratamientosByPuntoVertimiento(punto.getId());
        for (PuntoVertimientoTratamiento tratamiento : tratamientos) {
            em.remove(tratamiento);
        }

        //finalmente el permiso
        PuntoVertimiento existe = this.getVertimiento(punto.getId());
        if (existe != null) {
            PuntoVertimientoPOJO pto =
                this.armarObjetoPuntoVertimientoCola(existe);

            registroRemoto.registrarEvento(this.getClass().getName(),
                                           "borrarVertimientoPlano", pto);

            em.remove(existe);
        }


    }


    public List<SubttFunias> getFuniasByCaptacion(Captacion captacion) throws IdeamException {
        Query query = em.createNamedQuery("SubttFunias.findByCaptacion");
        query.setParameter("id", captacion.getCodigo());
        return query.getResultList();
    }

    public SubttFunias getFuniasByCaptacionTipoFunias(Long idCaptacion,
                                                      Integer tipoFunias) throws IdeamException {
        SubttFunias result = null;
        try {
            Query query =
                em.createNamedQuery("SubttFunias.findByCaptacionTipoFunias");
            query.setParameter("tipoFunias", tipoFunias.intValue());
            query.setParameter("idCaptacion", idCaptacion.longValue());
            List resultados = (List)query.getResultList();
            if (resultados != null && !resultados.isEmpty()) {
                result =
                        (SubttFunias)resultados.get(0); //devolver siempre el primer resultado
            }
        } catch (javax.ejb.EJBTransactionRolledbackException e) {
            throw new IdeamException("Problemas! \\nno existe un funias con los datos de b�squeda.");
        } catch (Exception e) {
            throw new IdeamException("Problemas! \\nno existe un funias con los datos de b�squeda. " +
                                     e.getMessage());
        } finally {
            return result;
        }

    }

    public SubttFunias getFunias(Long idFunias) throws IdeamException {
        Query query = em.createNamedQuery("SubttFunias.findById");
        query.setParameter("id", idFunias);
        return (SubttFunias)query.getSingleResult();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public SubttFunias createFunias(SubttFunias funias) throws IdeamException {
        //System.out.println("------------------------Create Funias");
        funias.setId(GeneradorSeq.obtenerSequencia(funias.getCodigoAutoridad(),
                                                   "SEQ_FUNIAS", em));

        em.persist(funias);
        em.flush();
        em.refresh(funias);


        SubttFuniasPOJO pojo = new SubttFuniasPOJO();
        pojo.setAguaEstancada(funias.getAguaEstancada());
        pojo.setAlberca(funias.getAlberca());
        pojo.setBotaceroAbierto(funias.getBotaceroAbierto());
        pojo.setCalidadRegistro(funias.getCalidadRegistro());
        pojo.setCampoInfiltracion(funias.getCampoInfiltracion());
        pojo.setCantidadGravilla(funias.getCantidadGravilla());
        pojo.setCapacidadAlberca(funias.getCapacidadAlberca());
        pojo.setCapacidadEmbalse(funias.getCapacidadEmbalse());
        pojo.setCapacidadOtro(funias.getCapacidadOtro());
        pojo.setCapacidadTanque(funias.getCapacidadTanque());
        pojo.setCementerio(funias.getCementerio());
        pojo.setCercoAdecuado(funias.getCercoAdecuado());
        pojo.setCompaniaEjecutora(funias.getCompaniaEjecutora());
        pojo.setCompaniaPerforadora(funias.getCompaniaPerforadora());
        pojo.setCompostaje(funias.getCompostaje());
        pojo.setCriaderoGanado(funias.getCriaderoGanado());
        pojo.setCubiertaAdecuada(funias.getCubiertaAdecuada());
        pojo.setDiametro(funias.getDiametro());
        pojo.setDiametroExterior(funias.getDiametroExterior());
        pojo.setDiametroInterior(funias.getDiametroInterior());
        pojo.setDiametroPerforacion(funias.getDiametroPerforacion());
        pojo.setDiametroPromedioGravilla(funias.getDiametroPromedioGravilla());
        pojo.setDistancia(funias.getDistancia());
        pojo.setDistanciaCampo(funias.getDistanciaCampo());
        pojo.setDistanciaCementerio(funias.getDistanciaCementerio());
        pojo.setDistanciaCriadero(funias.getDistanciaCriadero());
        pojo.setDistanciaEstacionServicio(funias.getDistanciaEstacionServicio());
        pojo.setDistanciaFiltracion(funias.getDistanciaFiltracion());
        pojo.setDistanciaLagunas(funias.getDistanciaLagunas());
        pojo.setDistanciaLavadero(funias.getDistanciaLavadero());
        pojo.setDistanciaLetrina(funias.getDistanciaLetrina());
        pojo.setDistanciaPlantas(funias.getDistanciaPlantas());
        pojo.setDistanciaPozos(funias.getDistanciaPozos());
        pojo.setEmbalse(funias.getEmbalse());
        pojo.setEquipoUsado(funias.getEquipoUsado());
        pojo.setEstaColapsado(funias.getEstaColapsado());
        pojo.setEstaColmatado(funias.getEstaColmatado());
        pojo.setEstacionServicio(funias.getEstacionServicio());
        pojo.setEstructura(funias.getEstructura());
        pojo.setFechaConstruccion(funias.getFechaConstruccion());
        pojo.setFechaMedicion(funias.getFechaMedicion());
        pojo.setFiltracionAgua(funias.getFiltracionAgua());
        pojo.setFluidoUsado(funias.getFluidoUsado());
        pojo.setGeoforma(funias.getGeoforma());
        pojo.setId(funias.getId());
        pojo.setIdCaptacion(funias.getIdCaptacion());
        pojo.setIncineracion(funias.getIncineracion());
        pojo.setLagunasOxidacion(funias.getLagunasOxidacion());
        pojo.setLavaderoVehiculos(funias.getLavaderoVehiculos());
        pojo.setLetrinaCercana(funias.getLetrinaCercana());
        pojo.setLitologiaSuperficial(funias.getLitologiaSuperficial());
        pojo.setLocalizacionTopografica(funias.getLocalizacionTopografica());
        pojo.setLongitud(funias.getLongitud());
        pojo.setMaterial(funias.getMaterial());
        pojo.setMaterialUsado(funias.getMaterialUsado());
        pojo.setMetodoConstruccion(funias.getMetodoConstruccion());
        pojo.setMetodoExplotacion(funias.getMetodoExplotacion());
        pojo.setNivelEngravillado(funias.getNivelEngravillado());
        pojo.setNombreEstructura(funias.getNombreEstructura());
        pojo.setOtro(funias.getOtro());
        pojo.setPisoCemento(funias.getPisoCemento());
        pojo.setPlantasSacrificio(funias.getPlantasSacrificio());
        pojo.setPozosAbandonados(funias.getPozosAbandonados());
        pojo.setProfundidadEntubado(funias.getProfundidadEntubado());
        pojo.setProfundidadPerforacion(funias.getProfundidadPerforacion());
        pojo.setProfundidadRegistro(funias.getProfundidadRegistro());
        pojo.setReciclaje(funias.getReciclaje());
        pojo.setResiduosAgricolas(funias.getResiduosAgricolas());
        pojo.setResiduosDomestico(funias.getResiduosDomestico());
        pojo.setResiduosGanaderia(funias.getResiduosGanaderia());
        pojo.setResiduosHospitalarios(funias.getResiduosHospitalarios());
        pojo.setResiduosIndustriales(funias.getResiduosIndustriales());
        pojo.setResiduosMineros(funias.getResiduosMineros());
        pojo.setResistividadLodo(funias.getResistividadLodo());
        pojo.setSelloSanitario(funias.getSelloSanitario());
        pojo.setTanque(funias.getTanque());
        pojo.setTemperaturaLodo(funias.getTemperaturaLodo());
        pojo.setTipoAcabado(funias.getTipoAcabado());
        pojo.setTipoEnergia(funias.getTipoEnergia());
        pojo.setTipoFunias(funias.getTipoFunias());
        pojo.setTipoRegistro(funias.getTipoRegistro());
        pojo.setTratamientoEspecial(funias.getTratamientoEspecial());
        pojo.setUnidadGeologica(funias.getUnidadGeologica());

        registroRemoto.registrarEvento(this.getClass().getName(),
                                       "createFuniasPlano", pojo);


        if (funias.getSubttFuniasDocumentosList() != null) {
            for (SubttFuniasDocumentos doc :
                 funias.getSubttFuniasDocumentosList()) {
                doc.setIdFunias(funias.getId());
                this.createFileFunias(doc);
            }
        }


        //System.out.println("------------------------Termina Create Funias");
        return funias;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public SubttFunias updateFunias(SubttFunias funias) throws IdeamException {

        //System.out.println("------------------------Update Funias");
        if (funias.getSubttFuniasDocumentosList() != null) {
            //primero se borran los documentos relacionados
            List<SubttFuniasDocumentos> documentos =
                this.getFilesByFunias(funias.getId());
            for (SubttFuniasDocumentos doc : documentos) {
                this.deleteFileFunias(doc);
            }

            //luego se guardan lo otros documentos
            for (SubttFuniasDocumentos doc :
                 funias.getSubttFuniasDocumentosList()) {
                this.createFileFunias(doc);
            }
        }
        funias = em.merge(funias);
        em.flush();
        //em.refresh(uso);

        registroRemoto.registrarEvento(this.getClass().getName(),
                                       "updateFunias", funias);

        //System.out.println("------------------------Termina Update Funias");
        return funias;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void deleteFunias(SubttFunias funias) throws IdeamException {
        try {
            //System.out.println("------------------------Delete Funias");
            //primero se borran los documentos relacionados
            List<SubttFuniasDocumentos> documentos =
                this.getFilesByFunias(funias.getId());
            for (SubttFuniasDocumentos doc : documentos) {
                this.deleteFileFunias(doc);
            }

            em.flush();
            funias = em.merge(funias);

            registroRemoto.registrarEvento(this.getClass().getName(),
                                           "deleteFunias", funias);

            em.remove(funias);
            //System.out.println("------------------------Termina Delete Funias");
        } finally {
        }
    }

    public void setDTo(DatosReporteTO dTo) {
        this.dTo = dTo;
    }

    public DatosReporteTO getDTo() {
        return dTo;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public SubttFuniasDocumentos createFileFunias(SubttFuniasDocumentos funiasFile) throws IdeamException {
        //System.out.println("------------------------Create File Funias");
        if (funiasFile.getId() == null) {
            funiasFile.setId(GeneradorSeq.obtenerSequencia(funiasFile.getCodigoAutoridad(),
                                                           "SEQ_FILE_FUNIAS",
                                                           em));

            em.persist(funiasFile);
        } else {
            em.merge(funiasFile);
        }
        em.flush();
        em.refresh(funiasFile);


        SubttFuniasDocumentosPOJO pojo = new SubttFuniasDocumentosPOJO();
        pojo.setDocumento(funiasFile.getDocumento());
        pojo.setId(funiasFile.getId());
        pojo.setIdFunias(funiasFile.getIdFunias());
        registroRemoto.registrarEvento(this.getClass().getName(),
                                       "createFileFuniasPlano", pojo);

        //System.out.println("------------------------Termina Create File Funias");
        return funiasFile;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public SubttFuniasDocumentos updateFileFunias(SubttFuniasDocumentos funiasFile) throws IdeamException {

        //System.out.println("------------------------Update File Funias");

        funiasFile = em.merge(funiasFile);
        em.flush();
        //em.refresh(uso);

        registroRemoto.registrarEvento(this.getClass().getName(),
                                       "updateFileFunias", funiasFile);

        //System.out.println("------------------------Termina Update File Funias");
        return funiasFile;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void deleteFileFunias(SubttFuniasDocumentos funiasFile) throws IdeamException {
        try {
            //System.out.println("------------------------Borrar File Funias");
            em.flush();
            funiasFile = em.merge(funiasFile);

            registroRemoto.registrarEvento(this.getClass().getName(),
                                           "deleteFileFunias", funiasFile);

            em.remove(funiasFile);
            //System.out.println("------------------------Termina Borrar File Funias");
        } finally {
        }
    }

    public List getFilesByFunias(Long idFunias) throws IdeamException {
        Query query =
            em.createNamedQuery("SubttFuniasDocumentos.findByFunias");
        query.setParameter("idFunias", idFunias);
        return query.getResultList();
    }

    public void validarEntityManager() {
        try {
            if (em != null && em.isOpen()) {
                System.out.println("Entity Usuarios manager OK");
            } else {
                System.out.println("Entity Usuarios manager FALLO");
            }
        } catch (Exception e) {
            System.out.println("Error validarEntityManager:" + e);
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public CaptacionPOJO createCaptacionPlano(CaptacionPOJO captacion) throws IdeamException {
        //System.out.println("------------------------Create Captacion Plano");
        validarEntityManager();
        Captacion cap = this.getCaptacion(captacion.getCodigo());
        if (cap == null) {
            em.persist(captacion);
        } else {
            em.merge(captacion);
        }
        //captacion = em.merge(captacion);
        em.flush();
        em.refresh(captacion);

        //System.out.println("------------------------Termina Create Captacion Plano");

        return captacion;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public CaptacionPOJO updateCaptacionPlano(CaptacionPOJO captacion) throws IdeamException {
        //System.out.println("------------------------Update Captacion Plano");
        captacion = em.merge(captacion);
        em.flush();
        //em.refresh(captacion);
        //System.out.println("------------------------Termina Update Captacion Plano");
        return captacion;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void deleteCaptacionPlano(CaptacionPOJO captacionp) throws IdeamException {
        try {
            //los usos asociados deben ser borrados.
            //System.out.println("------------------------Delete Captacion Plano");
            em.flush();
            Captacion captacion = getCaptacion(captacionp.getCodigo());

            em.remove(captacion);
            //System.out.println("------------------------Termina Delete Captacion Plano");
        } finally {
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public PuntoVertimientoPOJO createVertimientoPlano(PuntoVertimientoPOJO vertimiento) throws IdeamException {
        //System.out.println("------------------------Create Vertimiento Plano");
        PuntoVertimiento vert = this.getVertimiento(vertimiento.getId());
        if (vert == null) {
            em.persist(vertimiento);
        } else {
            em.merge(vertimiento);
        }

        em.flush();
        em.refresh(vertimiento);

        //System.out.println("------------------------Termina Create Vertimiento Plano");
        return vertimiento;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public PuntoVertimientoPOJO updateVertimientoPlano(PuntoVertimientoPOJO vertimiento) throws IdeamException {

        //System.out.println("------------------------Update Vertimiento Plano");
        vertimiento = em.merge(vertimiento);
        em.flush();
        em.refresh(vertimiento);
        //System.out.println("------------------------Termina Update Vertimiento Plano");

        //borrar los tratamientos anteriores.
        List<PuntoVertimientoTratamiento> tratamientoExistentes =
            this.getTratamientosByPuntoVertimiento(vertimiento.getId());
        if (tratamientoExistentes != null) {
            for (PuntoVertimientoTratamiento tratamiento :
                 tratamientoExistentes) {
                this.deletePuntoTratamiento(tratamiento);
            }
        }

        return vertimiento;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void deleteVertimientoPlano(PuntoVertimientoPOJO vertimientop) throws IdeamException {
        try {
            //System.out.println("------------------------Delete Vertimiento Plano");
            em.flush();
            PuntoVertimiento vertimiento =
                getVertimiento(vertimientop.getId());
            em.remove(vertimiento);
            //System.out.println("------------------------Termina Delete Vertimiento Plano");

            //borrar los tratamientos anteriores.
            List<PuntoVertimientoTratamiento> tratamientoExistentes =
                this.getTratamientosByPuntoVertimiento(vertimiento.getId());
            if (tratamientoExistentes != null) {
                for (PuntoVertimientoTratamiento tratamiento :
                     tratamientoExistentes) {
                    this.deletePuntoTratamiento(tratamiento);
                }
            }
        } finally {
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void borrarCaptacionPlano(CaptacionPOJO cpt) throws IdeamException {
        //System.out.println("------------------------Borrar Captaci�n Plano Con todo");
        Captacion captacion =
            this.getCaptacion(cpt.getCodigo()); //consulto la captaci�n y continuo el proceso
        //primero revisar si existen puntos de monitoreo
        List puntosMonitoreo =
            calidadService.getPuntosMonitoreoCaptacion(captacion);
        if (puntosMonitoreo != null && !puntosMonitoreo.isEmpty()) {
            throw new IdeamException("Existen puntos de monitoreo relacionados a una captaci�n de la concesi�n: " +
                                     captacion.getIdConcesion().getNumeroExpediente());
        }

        //primero los usos
        List<RurtCaptacionUso> usos = this.getUsos(captacion);
        for (RurtCaptacionUso uso : usos) {
            em.remove(uso);
        }

        //luego los funias
        List<SubttFunias> funias = this.getFuniasByCaptacion(captacion);
        for (SubttFunias fun : funias) {
            em.remove(fun);
        }


        //luego los componentes
        List<RurtCaptacionComponentes> componentes =
            this.getComponentesByCaptacion(captacion.getCodigo());
        for (RurtCaptacionComponentes componente : componentes) {
            em.remove(componente);
        }

        //finalmente la concesion
        Captacion existe = this.getCaptacion(captacion.getCodigo());
        if (existe != null) {
            em.remove(existe);
        }

        //System.out.println("------------------------Termina Borrar Captacion Plano con todo");
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void borrarVertimientoPlano(PuntoVertimientoPOJO pto) throws IdeamException {
        //System.out.println("------------------------Borrar Vertimiento Plano Con todo");
        PuntoVertimiento punto =
            this.getVertimiento(pto.getId()); //consulto el punto y continuo con el proceso.
        //primero revisar si existen puntos de monitoreo
        List puntosMonitoreo = calidadService.getPuntosMonitoreoVert(punto);
        if (puntosMonitoreo != null && !puntosMonitoreo.isEmpty()) {
            throw new IdeamException("Existen puntos de monitoreo relacionados a un vertimiento del permiso: " +
                                     punto.getIdPermisoVertimiento().getNumeroExpediente());
        }

        //primero los tratamientos
        List<PuntoVertimientoTratamiento> tratamientos =
            this.getTratamientosByPuntoVertimiento(punto.getId());
        for (PuntoVertimientoTratamiento tratamiento : tratamientos) {
            em.remove(tratamiento);
        }

        //finalmente el permiso
        PuntoVertimiento existe = this.getVertimiento(punto.getId());
        if (existe != null) {
            em.remove(existe);
        }
        //System.out.println("------------------------Termina Borrar Vertimiento Plano Con todo");
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public RurtCaptacionComponentePOJO createCaptacionComponentePlano(RurtCaptacionComponentePOJO captacionComponente) throws IdeamException {
        em.persist(captacionComponente);
        em.flush();
        em.refresh(captacionComponente);

        return captacionComponente;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public PuntoVertimientoTratamientoPOJO createPuntoTratamientoPlano(PuntoVertimientoTratamientoPOJO puntoTratamiento) throws IdeamException {

        PuntoVertimientoTratamiento ptoTra = new PuntoVertimientoTratamiento();
        ptoTra.setCodigoAutoridad(puntoTratamiento.getCodigoAutoridad());
        ptoTra.setId(puntoTratamiento.getId());
        ptoTra.setIdCategoria(puntoTratamiento.getIdCategoria());
        ptoTra.setIdTratamiento(puntoTratamiento.getIdTratamiento());
        ptoTra.setIdPuntoVertimiento(puntoTratamiento.getIdPuntoVertimiento());

        em.persist(ptoTra);
        em.flush();
        em.refresh(ptoTra);

        return puntoTratamiento;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public RurtCaptacionUsoPOJO createUsoPlano(RurtCaptacionUsoPOJO uso) throws IdeamException {
        em.persist(uso);
        //captacion = em.merge(captacion);
        em.flush();
        em.refresh(uso);

        return uso;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public SubttFuniasDocumentosPOJO createFileFuniasPlano(SubttFuniasDocumentosPOJO funiasFile) throws IdeamException {
        em.persist(funiasFile);
        em.flush();
        em.refresh(funiasFile);
        return funiasFile;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public SubttFuniasPOJO createFuniasPlano(SubttFuniasPOJO funias) throws IdeamException {
        em.persist(funias);
        em.flush();
        em.refresh(funias);
        return funias;
    }


    private CaptacionPOJO armarObjetoCaptacionCola(Captacion captacion) {

        CaptacionPOJO cpt = new CaptacionPOJO();
        cpt.setCodigo(captacion.getCodigo());
        cpt.setAltitud(captacion.getAltitud());
        cpt.setAreaCaptacion(captacion.getAreaCaptacion());
        cpt.setAutoridad(captacion.getAutoridad());
        cpt.setCaudalDisegno(captacion.getCaudalDisegno());
        cpt.setCaudalVertimiento(captacion.getCaudalVertimiento());
        cpt.setCodigoAutoridad(captacion.getCodigoAutoridad());
        cpt.setContinuidad(captacion.getContinuidad());
        cpt.setDescripcionAcceso(captacion.getDescripcionAcceso());
        cpt.setEstadoCaptacion(captacion.getEstadoCaptacion());
        cpt.setGradosLat(captacion.getGradosLat());
        cpt.setGradosLong(captacion.getGradosLong());
        cpt.setIdArea((captacion.getIdArea() != null) ?
                      captacion.getIdArea().getId() : null);
        cpt.setIdConcesion((captacion.getIdConcesion() != null) ?
                           captacion.getIdConcesion().getCodigo() : null);
        cpt.setIdDepartamento(captacion.getIdDepartamento());
        cpt.setIdFuente((captacion.getIdFuente() != null) ?
                        captacion.getIdFuente().getId() : null);
        cpt.setIdMunicipio(captacion.getIdMunicipio());
        cpt.setIdSubzona((captacion.getIdSubzona() != null) ?
                         captacion.getIdSubzona().getId() : null);
        cpt.setIdTipoCentroPoblado(captacion.getIdTipoCentroPoblado());
        cpt.setIdTramo((captacion.getIdTramo() != null) ?
                       captacion.getIdTramo().getId() : null);
        cpt.setIdZona((captacion.getIdZona() != null) ?
                      captacion.getIdZona().getId() : null);
        cpt.setIdentificadorPunto(captacion.getIdentificadorPunto());
        cpt.setMetodoExtraccion(captacion.getMetodoExtraccion());
        cpt.setMinutosLat(captacion.getMinutosLat());
        cpt.setMinutosLong(captacion.getMinutosLong());
        cpt.setNombreCentroPoblado(captacion.getNombreCentroPoblado());
        cpt.setOfertaDisponible(captacion.getOfertaDisponible());
        cpt.setOfertaHidricaTotal(captacion.getOfertaHidricaTotal());
        cpt.setProvinciaHidrogeologica(captacion.getProvinciaHidrogeologica());
        cpt.setSegundosLat(captacion.getSegundosLat());
        cpt.setSegundosLong(captacion.getSegundosLong());
        cpt.setSistemaReferencia(captacion.getSistemaReferencia());
        cpt.setTieneMacroMedicion(captacion.getTieneMacroMedicion());
        cpt.setTieneServidumbre(captacion.getTieneServidumbre());
        cpt.setTipoCaptacion(captacion.getTipoCaptacion());
        cpt.setTipoFuenteCaptacion(captacion.getTipoFuenteCaptacion());
        cpt.setTipoPunto(captacion.getTipoPunto());
        cpt.setUnidadHidrogeologica(captacion.getUnidadHidrogeologica());

        return cpt;
    }

    private PuntoVertimientoPOJO armarObjetoPuntoVertimientoCola(PuntoVertimiento vertimiento) {

        PuntoVertimientoPOJO ptovrt = new PuntoVertimientoPOJO();
        ptovrt.setId(vertimiento.getId());
        ptovrt.setAltitud(vertimiento.getAltitud());
        ptovrt.setAutoridad(vertimiento.getAutoridad());
        ptovrt.setCaudalDisegno(vertimiento.getCaudalDisegno());
        ptovrt.setCodigoAutoridad(vertimiento.getCodigoAutoridad());
        ptovrt.setDescripcionAcceso(vertimiento.getDescripcionAcceso());
        ptovrt.setFrecuencia(vertimiento.getFrecuencia());
        ptovrt.setGradosLat(vertimiento.getGradosLat());
        ptovrt.setGradosLong(vertimiento.getGradosLong());
        ptovrt.setIdDepartamento(vertimiento.getIdDepartamento());
        ptovrt.setIdFuente((vertimiento.getIdFuente() != null) ?
                           vertimiento.getIdFuente().getId() : null);
        ptovrt.setIdMunicipio(vertimiento.getIdMunicipio());
        ptovrt.setIdPermisoVertimiento((vertimiento.getIdPermisoVertimiento() !=
                                        null) ?
                                       vertimiento.getIdPermisoVertimiento().getCodigo() :
                                       null);
        ptovrt.setIdTipoCentroPoblado(vertimiento.getIdTipoCentroPoblado());
        ptovrt.setIdTramo((vertimiento.getIdTramo() != null) ?
                          vertimiento.getIdTramo().getId() : null);
        ptovrt.setMinutosLat(vertimiento.getMinutosLat());
        ptovrt.setMinutosLong(vertimiento.getMinutosLong());
        ptovrt.setNombreCentroPoblado(vertimiento.getNombreCentroPoblado());
        ptovrt.setSegundosLat(vertimiento.getSegundosLat());
        ptovrt.setSegundosLong(vertimiento.getSegundosLong());
        ptovrt.setSistemaReferencia(vertimiento.getSistemaReferencia());
        ptovrt.setTiempoDescarga(vertimiento.getTiempoDescarga());
        ptovrt.setTipoFlujo(vertimiento.getTipoFlujo());
        ptovrt.setTipoVertimiento(vertimiento.getTipoVertimiento());

        return ptovrt;
    }


    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public SubttFuniasNivel createFuniasNivel(SubttFuniasNivel nivel) throws IdeamException {
        //System.out.println("------------------------Create Nivel");

        if (nivel.getId() == null || nivel.getId().intValue() == 0) {
            nivel.setId(GeneradorSeq.obtenerSequencia(nivel.getCodigoAutoridad(),
                                                      "SEQ_NIVELES", em));
        }

        em.persist(nivel);
        em.flush();
        em.refresh(nivel);

        registroRemoto.registrarEvento(this.getClass().getName(),
                                       "createFuniasNivel", nivel);

        //System.out.println("------------------------Termina Create Nivel");
        return nivel;
    }

    public SubttFuniasNivel getNivel(Long idNivel) throws IdeamException {
        Query query = em.createNamedQuery("SubttFuniasNivel.findById");
        query.setParameter("id", idNivel);
        return (SubttFuniasNivel)query.getSingleResult();
    }

    public List<SubttFuniasNivel> getNivelByFunias(SubttFunias funias) throws IdeamException {
        Query query = em.createNamedQuery("SubttFuniasNivel.findByFunias");
        query.setParameter("id", funias.getId());
        return query.getResultList();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void deleteNivel(SubttFuniasNivel nivel) throws IdeamException {
        try {
            //System.out.println("------------------------Delete Nivel");
            nivel = this.getNivel(nivel.getId());

            em.refresh(nivel);

            em.remove(nivel);

            registroRemoto.registrarEvento(this.getClass().getName(),
                                           "deleteNivel", nivel);


            //System.out.println("------------------------Termina Delete Nivel");
        } finally {
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public SubttFuniasNivel updateNivel(SubttFuniasNivel nivel) throws IdeamException {
        nivel = em.merge(nivel);
        em.flush();
        registroRemoto.registrarEvento(this.getClass().getName(),
                                       "updateNivel", nivel);
        return nivel;
    }


    public Long registrarUsuarioPredioSIAC(OE_RegistrarPermiso oe) throws IdeamException {

        UsuarioAguaWS usuario = oe.getUsuarioAgua();
        RepresentanteWS representanteLegal = oe.getRepresentanteLegal();
        PredioWS predio = oe.getPredio();
        PermisoWS permiso = oe.getPermiso();
        String origen = oe.getOrigen();

        UsuarioAguaSinc usuarioSIRH = new UsuarioAguaSinc();
        RepresentanteSinc RlegalSIRH = new RepresentanteSinc();
        PredioSinc PredioSIRH = new PredioSinc();
        ConcesionSinc concesionSIRH = new ConcesionSinc();
        PermisoVertimientoSinc permisoSIRH = new PermisoVertimientoSinc();

        Long codigoUsuario = null;

        if (usuario.getTipoPersona() != null) {
            usuarioSIRH.setCodigoTipoPersona(usuario.getTipoPersona());
        }
        if (usuario.getTipoPersona() == UsuarioAguaWS.JURIDICA_PRIVADA ||
            usuario.getTipoPersona() == UsuarioAguaWS.JURIDICA_PUBLICA) {
            if (usuario.getActividadCIIU() != null) {
                usuario.setActividadCIIU(usuario.getActividadCIIU());
            }
        }
        if (usuario.getPrimerNombre() != null) {
            usuario.setPrimerNombre(usuario.getPrimerNombre());
            System.out.println("usuario.getPrimerNombre()"+usuario.getPrimerNombre());
        }
        if (usuario.getSegundoNombre() != null) {
            usuario.setSegundoNombre(usuario.getSegundoNombre());
          System.out.println("usuario.getSegundoNombre()"+usuario.getSegundoNombre());
        }
        if (usuario.getPrimerApellido() != null) {
            usuario.setPrimerApellido(usuario.getPrimerApellido());
        }
        if (usuario.getSegundoApellido() != null) {
            usuario.setSegundoApellido(usuario.getSegundoApellido());
        }
        usuarioSIRH.setNombre(usuario.getPrimerNombre().toString() + " " +
                              usuario.getSegundoNombre().toString());
        usuarioSIRH.setApellido(usuario.getPrimerApellido() + " " +
                                usuario.getSegundoApellido());
        
      System.out.println("usuario.--------------------------------()"+usuarioSIRH.getNombre());

        if (usuario.getRazonSocial() != null) {
            usuario.setRazonSocial(usuario.getRazonSocial());
          System.out.println("usuario.--------usuario.getRazonSocial()55555------------------------()"+usuario.getRazonSocial());

            usuarioSIRH.setRazonSocial(usuario.getRazonSocial());
          System.out.println("usuario.--------usuario.getRazonSocial()55555-----------usuarioSIRH-------------()"+usuarioSIRH.getRazonSocial());

        }

        if (usuario.getDireccionCorreoElectronico() != null) {
            usuarioSIRH.setEmail(usuario.getDireccionCorreoElectronico());
        }

        if (usuario.getTelefonoFijo() != null) {
            usuarioSIRH.setTelefono(usuario.getTelefonoFijo());
        }

        if (usuario.getCodigoTipoIdentificacion() != null) {
            usuarioSIRH.setCodigoTipoDocumento(usuario.getCodigoTipoIdentificacion());
        }

        if (usuario.getIdentificacionPersona() != null) {
            usuarioSIRH.setNumeroDocumento(usuario.getIdentificacionPersona());
        }

        if (usuario.getCodAutoridadAmbiental() != null) {
            usuario.setCodAutoridadAmbiental(usuario.getCodAutoridadAmbiental());
        }

        Long aut = new Long(usuario.getCodAutoridadAmbiental());
        usuarioSIRH.setCodigoAutoridad(aut);

        if (usuario.getIdMunicipioCorrespondencia() != null) {
            usuarioSIRH.setCodigoMunicipio(new Long(usuario.getIdMunicipioCorrespondencia()));
        }

        if (usuario.getIdDeptoCorrespondencia() != null) {
            usuarioSIRH.setCodigoDepartamento(new Long(usuario.getIdDeptoCorrespondencia()));
        }

        if (usuario.getDireccionCorrespondencia() != null) {
            usuarioSIRH.setDireccion(usuario.getDireccionCorrespondencia());
        }

        // Verificar si el usuario ya existe
        UsuarioAgua existe =
            this.getUsuario(usuario.getCodigoTipoIdentificacion(),
                            usuario.getIdentificacionPersona(), aut);

        if (existe == null) {
            usuarioSIRH.setCodigo(GeneradorSeq.obtenerSequencia(aut,
                                                                "seq_usuario_agua",
                                                                em));

          System.out.println("---------add-----.usuarioSIRH.getNombre()()-------------------->" +
                             usuarioSIRH.getNombre());
            em.persist(usuarioSIRH);
        } else {
          System.out.println("---------edit-----.usuarioSIRH.getNombre()()-------------------->" +
                             usuarioSIRH.getNombre());
            usuarioSIRH.setCodigo(existe.getCodigo());
            usuarioSIRH = em.merge(usuarioSIRH);
        }

        codigoUsuario = usuarioSIRH.getCodigo();


        usuarioSIRH.setOrigen(origen);
        usuarioSIRH.setId_origen(codigoUsuario.toString());
        usuarioSIRH.setTrasmitido(0);
      System.out.println("---------------------------------------no  es jueridca"+usuarioSIRH.getCodigoTipoPersona());

        if (usuarioSIRH.getCodigoTipoPersona() == UsuarioAgua.CORPORACION ||
            usuarioSIRH.getCodigoTipoPersona() ==
            UsuarioAgua.JURIDICA_PRIVADA ||
            usuarioSIRH.getCodigoTipoPersona() ==
            UsuarioAgua.JURIDICA_PUBLICA ||
            usuarioSIRH.getCodigoTipoPersona() == UsuarioAgua.MUNICIPIO ||
            usuarioSIRH.getCodigoTipoPersona() == UsuarioAgua.ACUEDUCTO ||
            usuarioSIRH.getCodigoTipoPersona() == UsuarioAgua.MEGAPROYECTO) {
System.out.println("--------------------------------------- es jueridca"+usuarioSIRH.getCodigoTipoPersona());

            if (representanteLegal.getCodigoTipoIdentificacionRepLegal() !=
                null) {
                RlegalSIRH.setCodigoTipoDocumento(new Integer(representanteLegal.getCodigoTipoIdentificacionRepLegal().toString()));
            }
            if (representanteLegal.getIdentificacionRepLegal() != null) {
                RlegalSIRH.setNumeroDocumento(representanteLegal.getIdentificacionRepLegal().toString());
            }
            if (representanteLegal.getDireccionCorrespondencia() != null) {
                RlegalSIRH.setDireccion(representanteLegal.getDireccionCorrespondencia());
            }
            if (representanteLegal.getIdMunicipioCorrespondencia() != null) {
                RlegalSIRH.setCodigoMunicipio(new Long(representanteLegal.getIdMunicipioCorrespondencia()));
            }
            if (representanteLegal.getIdDeptoCorrespondencia() != null) {
                RlegalSIRH.setCodigoDepartamento(new Long(representanteLegal.getIdDeptoCorrespondencia()));
            }
            RlegalSIRH.setNombres(representanteLegal.getPrimerNombreRepLegal() +
                                  " " +
                                  representanteLegal.getSegundoNombreRepLegal());
            RlegalSIRH.setApellidos(representanteLegal.getPrimerApellidoRepLegal() +
                                    " " +
                                    representanteLegal.getSegundoApellidoRepLegal());
       

        Representante representanteExiste =
            this.getRepresentante(codigoUsuario);


        RlegalSIRH.setCodigoUsuario(codigoUsuario);
        if (representanteExiste != null) {

            RlegalSIRH.setCodigo(representanteExiste.getCodigo());
            em.merge(RlegalSIRH);
        } else {

            RlegalSIRH.setCodigoAutoridadAmbiental(aut);

            if (RlegalSIRH.getCodigo() == null) {
                RlegalSIRH.setCodigo(GeneradorSeq.obtenerSequencia(aut,
                                                                   "seq_usuario_agua",
                                                                   em));
                em.persist(RlegalSIRH);
            } else {
                em.merge(RlegalSIRH);
            }

        }

        RlegalSIRH.setOrigen(origen);
        RlegalSIRH.setId_origen(RlegalSIRH.getCodigo().toString());
        RlegalSIRH.setTrasmitido(0);
      }


        if (predio.getNombrePredio() != null) {
            PredioSIRH.setNombre(predio.getNombrePredio());
        }
        if (predio.getCedulaCatastral() != null) {
            PredioSIRH.setCedulaCatastral(predio.getCedulaCatastral());
        }
        if (predio.getMatriculaInmobiliaria() != null) {
            PredioSIRH.setMatriculaCatastral(predio.getMatriculaInmobiliaria());
        }
        if (predio.getDireccionPredio() != null) {
            PredioSIRH.setDireccion(predio.getDireccionPredio());
        }
        if (predio.getIdDeptoPredio() != null) {
            PredioSIRH.setCodigoDepartamento(new Long(predio.getIdDeptoPredio().toString()));

        }
        if (predio.getIdMunicipioPredio() != null) {
            PredioSIRH.setCodigoMunicipio(new Long(predio.getIdMunicipioPredio().toString()));

        }
        PredioSIRH.setCodigoUsuario(codigoUsuario);
        if (PredioSIRH.getCodigo() == null) {
            PredioSIRH.setCodigo(GeneradorSeq.obtenerSequencia(aut,
                                                               "seq_usuario_agua",
                                                               em));

            em.persist(PredioSIRH);
        } else {
            em.merge(PredioSIRH);
        }


        PredioSIRH.setOrigen(origen);
        PredioSIRH.setId_origen(PredioSIRH.getCodigo().toString());
        PredioSIRH.setTrasmitido(0);


        //Permiso
        if (permiso.getTipoPermiso() != null) {
            Calendar fv = null;
            Calendar fe = null;
            if (permiso.getFechaExpedicion() != null) {
                String fecha = permiso.getFechaExpedicion().toString();
                String[] fechArray = fecha.split("/");
                int dia = Integer.valueOf(fechArray[0]);
                int mes = Integer.valueOf(fechArray[1]) - 1;
                int anio = Integer.valueOf(fechArray[2]);
                fe = new GregorianCalendar(anio, mes, dia);
            }

            if (permiso.getFechaVencimiento() != null) {
                String fecha = permiso.getFechaVencimiento().toString();
                String[] fechArray = fecha.split("/");
                int dia = Integer.valueOf(fechArray[0]);
                int mes = Integer.valueOf(fechArray[1]) - 1;
                int anio = Integer.valueOf(fechArray[2]);
                fv = new GregorianCalendar(anio, mes, dia);
            }

            if (permiso.getTipoPermiso().equals("CO")) {

                System.out.println("--------------permiso.getTipoPermiso()-------------------->" +
                                   permiso.getTipoPermiso());
                concesionSIRH.setNumeroExpediente(permiso.getNumExpediente().toString());
                concesionSIRH.setResolucionCaudal(permiso.getNumResolucion().toString());
                concesionSIRH.setFechaExpedicionCaudal(fe);
                concesionSIRH.setFechaInicio(fe);
                concesionSIRH.setFechaFin(fv);
                concesionSIRH.setCaudalConcesionado(permiso.getCantidadRecursoOtorgado());
                concesionSIRH.setCodigoPredio(PredioSIRH.getCodigo());

                if (concesionSIRH.getCodigo() == null) {
                    concesionSIRH.setCodigo(GeneradorSeq.obtenerSequencia(aut,
                                                                          "seq_usuario_agua",
                                                                          em));

                    em.persist(concesionSIRH);
                } else {
                    em.merge(concesionSIRH);
                }

                concesionSIRH.setOrigen(origen);
                concesionSIRH.setId_origen(concesionSIRH.getCodigo().toString());
                concesionSIRH.setTrasmitido(0);


            }
            if (permiso.getTipoPermiso().equals("PV")) {
                System.out.println("-----------PV---permiso.getTipoPermiso()-------------------->" +
                                   permiso.getTipoPermiso());

                permisoSIRH.setNumeroExpediente(permiso.getNumExpediente().toString());
                permisoSIRH.setResolucionPermisoVertimiento(permiso.getNumResolucion().toString());
                permisoSIRH.setFechaExpedicionInicioTramite(fe);
                permisoSIRH.setFechaInicio(fe);
                permisoSIRH.setFechaFin(fv);
                permisoSIRH.setCaudal(permiso.getCantidadRecursoOtorgado());
                permisoSIRH.setCodigoPredio(PredioSIRH.getCodigo());


                if (permisoSIRH.getCodigo() == null) {
                    permisoSIRH.setCodigo(GeneradorSeq.obtenerSequencia(aut,
                                                                        "seq_usuario_agua",
                                                                        em));
                    em.persist(permisoSIRH);
                } else {
                    em.merge(permisoSIRH);
                }
                if (origen != null) {
                    permisoSIRH.setOrigen(origen);
                    permisoSIRH.setId_origen(permisoSIRH.getCodigo().toString());
                    permisoSIRH.setTrasmitido(0);
                }

            }
        }

        return codigoUsuario;

    }


    //CDONCEL

    /**
     * Metodo empleado por el Nodo IDEAM para guardar Aforo
     * @return
     * @throws IdeamException
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void updateAforo(RurtCapAforo aforo) throws IdeamException {

        if (aforo == null) {
            throw new IdeamException("Entidad aforo no puede ser null");
        }
        boolean persiste = false;
        if (aforo.getId() == null || aforo.getId().longValue() == 0) {
            aforo.setId(GeneradorSeq.obtenerSequencia(aforo.getCaptacionId().getIdTramo().getCodigoAutoridad(),
                                                      "seq_cap_aforo", em));

            em.persist(aforo);
            persiste = true;
        } else {
            aforo = em.merge(aforo);
        }
    }

    /**
     * Metodo empleado para Obtener listado de Aforo
     * @param capt

     * @throws IdeamException
     */

    public List<RurtCapAforo> getLsAforos(Captacion capt) throws IdeamException {
        System.out.println("++++++++++++++++++++ CAPT-----------");
        System.out.println(capt.getCodigo());
        String sql =
            "select a.CAUDAL, a.FECHA, a.OBSERVACION, a.CAPTACION_ID,  a.ID  " +
            "from RURT_CAP_AFORO a " + "WHERE a.CAPTACION_ID  = " +
            capt.getCodigo() + " ";
        System.out.println("++++++++++++++++++++ SQL");
        System.out.println(sql);
        Query q = em.createNativeQuery(sql, RurtCapAforo.class);

        List<RurtCapAforo> lsAforo = new ArrayList<RurtCapAforo>();
        lsAforo = (List<RurtCapAforo>)q.getResultList();
        for (RurtCapAforo rA : lsAforo) {
            System.out.println("Aforo: " + rA.getCaudal());
        }

        return lsAforo;

    }

    public void borrarAforo(String id) {
        Long l = Long.valueOf(id);
        String sql =
            "select *  " + "from RURT_CAP_AFORO a " + "WHERE a.ID  = " + l +
            " ";

        Query q = em.createNativeQuery(sql, RurtCapAforo.class);
        RurtCapAforo aforo = (RurtCapAforo)q.getSingleResult();
        System.out.println("************AFORO: " + aforo.getId());
        em.flush();
        aforo = em.merge(aforo);
        em.remove(aforo);
    }


    public PuntoVertimiento getVertimientobyPerm(PermisoVertimiento permiso) throws IdeamException {

        PuntoVertimiento vert = null;
        try {
            Query query =
                em.createNamedQuery("PuntoVertimiento.findAllByPermiso");
            query.setParameter("idPermiso", permiso.getCodigo());
            vert = (PuntoVertimiento)query.getSingleResult();


            Long idDpto = new Long(vert.getIdDepartamento());
            if (idDpto != null) {
                Divipola dpto = parametrosService.getDivipolaById(idDpto);
                vert.setNombreDpto(dpto.getNombre());
            }
            Long idmunicipio = new Long(vert.getIdMunicipio());
            if (idmunicipio != null) {
                Divipola municipio =
                    parametrosService.getDivipolaById(idmunicipio);
                vert.setNombreMcpo(municipio.getNombre());
            }
        } catch (Exception e) {
            e.toString();
        }
        return vert;
    }
    //FIN CDONCEL


    //REGISTROS RUA/VITAL **** LISBETH

    public Long registrarUsuarioPredioRUAPV(UsuarioAguaSinc usuario,
                                            RepresentanteSinc representanteLegal,
                                            PredioSinc predio,
                                            PermisoVertimientoSinc permisoVert) throws IdeamException {

        UsuarioAgua usuarioSIRH = new UsuarioAgua();
        Representante RlegalSIRH = new Representante();
        Predio PredioSIRH = new Predio();

        PermisoVertimiento permisoSIRH = new PermisoVertimiento();

        Long codigoUsuario = null;
      String origen =null;
      if (usuario.getOrigen() != null) {
       origen = usuario.getOrigen(); 
      }

        if (usuario.getTipoUsuario() != null) {
            usuarioSIRH.setCodigoTipoPersona(usuario.getTipoUsuario().getCodigo());
        }
          if (usuarioSIRH.getCodigoTipoPersona() == UsuarioAgua.CORPORACION ||
              usuarioSIRH.getCodigoTipoPersona() ==
              UsuarioAgua.JURIDICA_PRIVADA ||
              usuarioSIRH.getCodigoTipoPersona() ==
              UsuarioAgua.JURIDICA_PUBLICA ||
              usuarioSIRH.getCodigoTipoPersona() == UsuarioAgua.MUNICIPIO ||
              usuarioSIRH.getCodigoTipoPersona() == UsuarioAgua.ACUEDUCTO ||
              usuarioSIRH.getCodigoTipoPersona() == UsuarioAgua.MEGAPROYECTO) {
            
          if (usuario.getRazonSocial() != null) {
              usuarioSIRH.setRazonSocial(usuario.getRazonSocial());
          }
          }
        if (usuario.getCodigoTipoDocumento() != null) {
            usuarioSIRH.setCodigoTipoDocumento(usuario.getCodigoTipoDocumento());
        }
        if (usuario.getNumeroDocumento() != null) {
            usuarioSIRH.setNumeroDocumento(usuario.getNumeroDocumento());
        }
        if (usuario.getNombre() != null) {
            usuarioSIRH.setNombre(usuario.getNombre());
        }
        Long aut = new Long(usuario.getCodigoAutoridad());
        usuarioSIRH.setCodigoAutoridad(aut);
        if (usuario.getCodigoDepartamento() != null) {
            usuarioSIRH.setCodigoDepartamento(usuario.getCodigoDepartamento());
        }
        if (usuario.getCodigoMunicipio() != null) {
            usuarioSIRH.setCodigoMunicipio(usuario.getCodigoMunicipio());
        }
        if (usuario.getDireccion() != null) {
            usuarioSIRH.setDireccion(usuario.getDireccion());
        }
        if (usuario.getEmail() != null) {
            usuarioSIRH.setEmail(usuario.getEmail());
        }
        if (usuario.getTelefono() != null) {
            usuarioSIRH.setTelefono(usuario.getTelefono());
        }
        if (usuario.getCodigoTipoPersona() != null) {
            usuarioSIRH.setCodigoTipoPersona(usuario.getCodigoTipoPersona());
        }
        usuarioSIRH.setOrigen(origen);
        usuarioSIRH.setId_origen(usuario.getId_origen());

        // Verificar si el usuario ya existe
        UsuarioAgua existe =
            this.getUsuario(usuarioSIRH.getCodigoTipoDocumento(),
                            usuarioSIRH.getNumeroDocumento(), aut);

        if (existe == null) {
            usuarioSIRH.setCodigo(GeneradorSeq.obtenerSequencia(aut,
                                                                "seq_usuario_agua",
                                                                em));

            em.persist(usuarioSIRH);

            System.out.println("----------------------------agrego usuario");

        } else {
            usuarioSIRH.setCodigo(existe.getCodigo());
            usuarioSIRH = em.merge(usuarioSIRH);
            System.out.println("----------------------------------editar usuario");
        }
        codigoUsuario = usuarioSIRH.getCodigo();
        if (codigoUsuario != null) {
            usuario.setTrasmitido(1);
            usuario = em.merge(usuario);
        }

        if (usuarioSIRH.getCodigoTipoPersona() == UsuarioAgua.CORPORACION ||
            usuarioSIRH.getCodigoTipoPersona() ==
            UsuarioAgua.JURIDICA_PRIVADA ||
            usuarioSIRH.getCodigoTipoPersona() ==
            UsuarioAgua.JURIDICA_PUBLICA ||
            usuarioSIRH.getCodigoTipoPersona() == UsuarioAgua.MUNICIPIO ||
            usuarioSIRH.getCodigoTipoPersona() == UsuarioAgua.ACUEDUCTO ||
            usuarioSIRH.getCodigoTipoPersona() == UsuarioAgua.MEGAPROYECTO) {


            if (representanteLegal.getCodigoTipoDocumento() != null) {
                RlegalSIRH.setCodigoTipoDocumento(representanteLegal.getCodigoTipoDocumento());
            }
            if (representanteLegal.getNumeroDocumento() != null) {
                RlegalSIRH.setNumeroDocumento(representanteLegal.getNumeroDocumento());
            }
            if (representanteLegal.getDireccion() != null) {
                RlegalSIRH.setDireccion(representanteLegal.getDireccion());
            }
            if (representanteLegal.getCodigoMunicipio() != null) {
                RlegalSIRH.setCodigoMunicipio(representanteLegal.getCodigoMunicipio());
            }
            if (representanteLegal.getCodigoDepartamento() != null) {
                RlegalSIRH.setCodigoDepartamento(representanteLegal.getCodigoDepartamento());
            }
            RlegalSIRH.setOrigen(origen);
            RlegalSIRH.setId_origen(representanteLegal.getCodigo().toString());

       
        Representante representanteExiste =
            this.getRepresentante(codigoUsuario);

        RlegalSIRH.setCodigoUsuario(codigoUsuario);
        RlegalSIRH.setNombres(representanteLegal.getNombres());

        if (representanteExiste != null) {

            RlegalSIRH.setCodigo(representanteExiste.getCodigo());
            em.merge(RlegalSIRH);

            System.out.println("----------------------------agrego rep");

        } else {

            RlegalSIRH.setCodigoAutoridadAmbiental(aut);

            if (RlegalSIRH.getCodigo() == null) {
                RlegalSIRH.setCodigo(GeneradorSeq.obtenerSequencia(aut,
                                                                   "seq_usuario_agua",
                                                                   em));
                em.persist(RlegalSIRH);
            } else {
                em.merge(RlegalSIRH);
                System.out.println("----------------------------edi rep");
            }

        }

        if (RlegalSIRH.getCodigo() != null) {
            representanteLegal.setTrasmitido(1);
            representanteLegal = em.merge(representanteLegal);
        }
      }
        System.out.println("----------------------------ptrediooooooooo rep");

        if (predio.getNombre() != null) {
            PredioSIRH.setNombre(predio.getNombre());
        }
        if (predio.getCedulaCatastral() != null) {
            PredioSIRH.setCedulaCatastral(predio.getCedulaCatastral());
        }
        if (predio.getMatriculaCatastral() != null) {
            PredioSIRH.setMatriculaCatastral(predio.getMatriculaCatastral());
        }
        if (predio.getDireccion() != null) {
            PredioSIRH.setDireccion(predio.getDireccion());
        }
        if (predio.getCodigoDepartamento() != null) {
            PredioSIRH.setCodigoDepartamento(predio.getCodigoDepartamento());

        }
        if (predio.getCodigoMunicipio() != null) {
            PredioSIRH.setCodigoMunicipio(predio.getCodigoMunicipio());

        }
        PredioSIRH.setOrigen(origen);
        PredioSIRH.setId_origen(predio.getId_origen());

      //VERIFICA SI EL PREDIO EXISTE
       Predio PREDIOExiste =
          this.getPredio(predio.getCedulaCatastral(),codigoUsuario, aut);

      
      if (PREDIOExiste != null) {

          PredioSIRH.setCodigo(PREDIOExiste.getCodigo());
          em.merge(PredioSIRH);

          System.out.println("----------------------------agrego   edit predios");

      } else {
      
        PredioSIRH.setCodigoUsuario(codigoUsuario);
      
      if (PredioSIRH.getCodigo() == null) {
          PredioSIRH.setCodigo(GeneradorSeq.obtenerSequencia(aut, "seq_usuario_agua", em));
          em.persist(PredioSIRH);
      } 
      }

      if (PredioSIRH.getCodigo() != null) {
          predio.setTrasmitido(1);
          predio = em.merge(predio);
      }
        if (permisoVert != null) {
            permisoSIRH.setNumeroExpediente(permisoVert.getNumeroExpediente());
            permisoSIRH.setResolucionPermisoVertimiento(permisoVert.getResolucionPermisoVertimiento());
            permisoSIRH.setFechaExpedicionInicioTramite(permisoVert.getFechaInicio());
            permisoSIRH.setFechaInicio(permisoVert.getFechaInicio());
            permisoSIRH.setFechaFin(permisoVert.getFechaFin());
            permisoSIRH.setCaudal(permisoVert.getCaudal());
            permisoSIRH.setCodigoPredio(PredioSIRH.getCodigo());
            permisoSIRH.setOrigen(origen);
            permisoSIRH.setId_origen(usuario.getCodigo().toString());


          //VERIFICA SI el permiso vertimiento EXISTE
           PermisoVertimiento PVExiste =  this.getPermisoVertimiento(permisoVert.getNumeroExpediente(),aut);

          
          if (PVExiste != null) {

              permisoSIRH.setCodigo(PVExiste.getCodigo());
              em.merge(permisoSIRH);

              System.out.println("----------------------------edit   permisoSIRH");

          } else {
          
            permisoSIRH.setCodigoPredio(PredioSIRH.getCodigo());
          
          if (permisoSIRH.getCodigo() == null) {
              permisoSIRH.setCodigo(GeneradorSeq.obtenerSequencia(aut, "seq_usuario_agua", em));
              em.persist(permisoSIRH);
              
              
          } 
          }

          
            if (permisoSIRH.getCodigo() != null) {
                permisoVert.setTrasmitido(1);
                permisoVert = em.merge(permisoVert);
            }
        }


        return codigoUsuario;

    }


    //REGISTROS RUA/VITAL **** LISBETH

    public Long registrarUsuarioPredioRUACO(UsuarioAguaSinc usuario,
                                            RepresentanteSinc representanteLegal,
                                            PredioSinc predio,
                                            ConcesionSinc concesion) throws IdeamException {
        UsuarioAgua usuarioSIRH = new UsuarioAgua();
        Representante RlegalSIRH = new Representante();
        Predio PredioSIRH = new Predio();
        Concesion concesionSIRH = new Concesion();

        Long codigoUsuario = null;
        String origen =null;
      if (usuario.getOrigen() != null) {
         origen = usuario.getOrigen(); 
      }
      
        if (usuario.getCodigoTipoPersona()!= null) {
            usuarioSIRH.setCodigoTipoPersona(usuario.getCodigoTipoPersona());
          System.out.println("----------------------------getCodigoTipoPersona "+ usuarioSIRH.getCodigoTipoPersona());
        }
          if (usuarioSIRH.getCodigoTipoPersona() == UsuarioAgua.CORPORACION ||
              usuarioSIRH.getCodigoTipoPersona() ==
              UsuarioAgua.JURIDICA_PRIVADA ||
              usuarioSIRH.getCodigoTipoPersona() ==
              UsuarioAgua.JURIDICA_PUBLICA ||
              usuarioSIRH.getCodigoTipoPersona() == UsuarioAgua.MUNICIPIO ||
              usuarioSIRH.getCodigoTipoPersona() == UsuarioAgua.ACUEDUCTO ||
              usuarioSIRH.getCodigoTipoPersona() == UsuarioAgua.MEGAPROYECTO) {
            
          if (usuario.getRazonSocial() != null) {
              usuarioSIRH.setRazonSocial(usuario.getRazonSocial());
            System.out.println("------4444444444444444444----------------------setRazonSocial "+ usuarioSIRH.getRazonSocial());
          }
        }
        if (usuario.getCodigoTipoDocumento() != null) {
            usuarioSIRH.setCodigoTipoDocumento(usuario.getCodigoTipoDocumento());
        }
        if (usuario.getNumeroDocumento() != null) {
            usuarioSIRH.setNumeroDocumento(usuario.getNumeroDocumento());
        }
        if (usuario.getNombre() != null) {
          System.out.println("----------------------------edit   usuario.getNombre()"+usuario.getNombre());
            usuarioSIRH.setNombre(usuario.getNombre());
        }
      if (usuario.getApellido()!= null) {
        System.out.println("----------------------------edit   usuario.getApellido()"+usuario.getApellido());
          usuarioSIRH.setApellido(usuario.getApellido());
      }
        Long aut = new Long(usuario.getCodigoAutoridad());
        usuarioSIRH.setCodigoAutoridad(aut);
        if (usuario.getCodigoDepartamento() != null) {
            usuarioSIRH.setCodigoDepartamento(usuario.getCodigoDepartamento());
        }
        if (usuario.getCodigoMunicipio() != null) {
            usuarioSIRH.setCodigoMunicipio(usuario.getCodigoMunicipio());
        }
        if (usuario.getDireccion() != null) {
            usuarioSIRH.setDireccion(usuario.getDireccion());
        }
        if (usuario.getEmail() != null) {
            usuarioSIRH.setEmail(usuario.getEmail());
        }
        if (usuario.getTelefono() != null) {
            usuarioSIRH.setTelefono(usuario.getTelefono());
        }
        if (usuario.getCodigoTipoPersona() != null) {
            usuarioSIRH.setCodigoTipoPersona(usuario.getCodigoTipoPersona());
        }
        usuarioSIRH.setOrigen(origen);
        usuarioSIRH.setId_origen(usuario.getId_origen());
      System.out.println("------4444444444444444444---55-------------------setRazonSocial "+ usuarioSIRH.getRazonSocial());
        // Verificar si el usuario ya existe
        UsuarioAgua existe =
            this.getUsuario(usuarioSIRH.getCodigoTipoDocumento(),
                            usuarioSIRH.getNumeroDocumento(), aut);

        if (existe == null) {
            usuarioSIRH.setCodigo(GeneradorSeq.obtenerSequencia(aut,
                                                                "seq_usuario_agua",
                                                                em));

            em.persist(usuarioSIRH);

            System.out.println("----------------------------agrego usuario");

        } else {
            usuarioSIRH.setCodigo(existe.getCodigo());
            usuarioSIRH = em.merge(usuarioSIRH);
            System.out.println("----------------------------------editar usuario");
        }
        codigoUsuario = usuarioSIRH.getCodigo();
        if (codigoUsuario != null) {
            usuario.setTrasmitido(1);
            usuario = em.merge(usuario);
        }

        if (usuarioSIRH.getCodigoTipoPersona() == UsuarioAgua.CORPORACION ||
            usuarioSIRH.getCodigoTipoPersona() ==
            UsuarioAgua.JURIDICA_PRIVADA ||
            usuarioSIRH.getCodigoTipoPersona() ==
            UsuarioAgua.JURIDICA_PUBLICA ||
            usuarioSIRH.getCodigoTipoPersona() == UsuarioAgua.MUNICIPIO ||
            usuarioSIRH.getCodigoTipoPersona() == UsuarioAgua.ACUEDUCTO ||
            usuarioSIRH.getCodigoTipoPersona() == UsuarioAgua.MEGAPROYECTO) {


            if (representanteLegal.getCodigoTipoDocumento() != null) {
                RlegalSIRH.setCodigoTipoDocumento(representanteLegal.getCodigoTipoDocumento());
              System.out.println("-------------------22222222222---------  RlegalSIRH.setCodigoTipoDocumento "+   RlegalSIRH.getCodigoTipoDocumento());
            }
            if (representanteLegal.getNumeroDocumento() != null) {
                RlegalSIRH.setNumeroDocumento(representanteLegal.getNumeroDocumento());
            }
            if (representanteLegal.getDireccion() != null) {
                RlegalSIRH.setDireccion(representanteLegal.getDireccion());
            }
            if (representanteLegal.getCodigoMunicipio() != null) {
                RlegalSIRH.setCodigoMunicipio(representanteLegal.getCodigoMunicipio());
            }
            if (representanteLegal.getCodigoDepartamento() != null) {
                RlegalSIRH.setCodigoDepartamento(representanteLegal.getCodigoDepartamento());
            }
            RlegalSIRH.setOrigen(origen);
            RlegalSIRH.setId_origen(representanteLegal.getCodigo().toString());

        
        Representante representanteExiste =
            this.getRepresentante(codigoUsuario);

        RlegalSIRH.setCodigoUsuario(codigoUsuario);
        RlegalSIRH.setNombres(representanteLegal.getNombres());
        RlegalSIRH.setApellidos(representanteLegal.getApellidos());
        if (representanteExiste != null) {

            RlegalSIRH.setCodigo(representanteExiste.getCodigo());
            em.merge(RlegalSIRH);

            System.out.println("----------------------------agrego rep");

        } else {

            RlegalSIRH.setCodigoAutoridadAmbiental(aut);

            if (RlegalSIRH.getCodigo() == null) {
                RlegalSIRH.setCodigo(GeneradorSeq.obtenerSequencia(aut,
                                                                   "seq_usuario_agua",
                                                                   em));
                em.persist(RlegalSIRH);
            } else {
                em.merge(RlegalSIRH);
                System.out.println("----------------------------edi rep");
            }

        }

        if (RlegalSIRH.getCodigo() != null) {
            representanteLegal.setTrasmitido(1);
            representanteLegal = em.merge(representanteLegal);
        }
     }
        System.out.println("----------------------------ptrediooooooooo rep");


        if (predio.getNombre() != null) {
            PredioSIRH.setNombre(predio.getNombre());
        }
        if (predio.getCedulaCatastral() != null) {
            PredioSIRH.setCedulaCatastral(predio.getCedulaCatastral());
        }
        if (predio.getMatriculaCatastral() != null) {
            PredioSIRH.setMatriculaCatastral(predio.getMatriculaCatastral());
        }
        if (predio.getDireccion() != null) {
            PredioSIRH.setDireccion(predio.getDireccion());
        }
        if (predio.getCodigoDepartamento() != null) {
            PredioSIRH.setCodigoDepartamento(predio.getCodigoDepartamento());

        }
        if (predio.getCodigoMunicipio() != null) {
            PredioSIRH.setCodigoMunicipio(predio.getCodigoMunicipio());

        }
        
        
        
        PredioSIRH.setOrigen(origen);
        PredioSIRH.setId_origen(predio.getId_origen());

        PredioSIRH.setCodigoUsuario(codigoUsuario);
        
        //VERIFICA SI EL PREDIO EXISTE
         Predio PREDIOExiste =
            this.getPredio(predio.getCedulaCatastral(),codigoUsuario, aut);

       
        if (PREDIOExiste != null) {

            PredioSIRH.setCodigo(PREDIOExiste.getCodigo());
            em.merge(PredioSIRH);

            System.out.println("----------------------------agrego   rep");

        } else {
        
          PredioSIRH.setCodigoUsuario(codigoUsuario);
      
        if (PredioSIRH.getCodigo() == null) {
            PredioSIRH.setCodigo(GeneradorSeq.obtenerSequencia(aut, "seq_usuario_agua", em));
            em.persist(PredioSIRH);
        } 
        }

        if (PredioSIRH.getCodigo() != null) {
            predio.setTrasmitido(1);
            predio = em.merge(predio);
        }

        //Concesion
        if (concesion != null) {
            concesionSIRH.setNumeroExpediente(concesion.getNumeroExpediente());
            concesionSIRH.setResolucionCaudal(concesion.getResolucionCaudal());
            concesionSIRH.setFechaExpedicionCaudal(concesion.getFechaInicio());
            concesionSIRH.setFechaInicio(concesion.getFechaInicio());
            concesionSIRH.setFechaFin(concesion.getFechaFin());
            concesionSIRH.setCaudalConcesionado(concesion.getCaudalConcesionado());
            concesionSIRH.setCodigoPredio(PredioSIRH.getCodigo());
            concesionSIRH.setOrigen(origen);
            concesionSIRH.setId_origen(usuario.getCodigo().toString());


          //VERIFICA SI la concesion EXISTE
           Concesion ConcExiste =  this.getConcesion(concesion.getNumeroExpediente());

          
          if (ConcExiste != null) {

              concesionSIRH.setCodigo(ConcExiste.getCodigo());
              em.merge(concesionSIRH);

              System.out.println("----------------------------edit   concesionSIRH");

          } else {
          
            concesionSIRH.setCodigoPredio(PredioSIRH.getCodigo());
          
          if (concesionSIRH.getCodigo() == null) {
              concesionSIRH.setCodigo(GeneradorSeq.obtenerSequencia(aut, "seq_usuario_agua", em));
              em.persist(concesionSIRH);
              
              
          } 
          }


            if (concesionSIRH.getCodigo() != null) {
                concesion.setTrasmitido(1);
                concesion = em.merge(concesion);
            }
        }

        return codigoUsuario;

    }


    //diego lopez

    public List<PermisoVertimientoDetalle> getAllDetallePermisosVertimiento(Autoridades autoridad) throws IdeamException {
        try {
            if (autoridad == null || autoridad.getId() == null) {
                return new ArrayList();
            } else {
                Query query =
                    em.createNamedQuery("PermisoVertimientoDetalle.findByAutoridad");
                query.setParameter("codigoAutoridad", autoridad.getId());
                List lista = query.getResultList();
                return lista;
            }
        } catch (NoResultException e) {
            return null;
        }
    }


    //Consulta Permisos SIAC

    public List<ConsultarPermisosV> getPermisosV(OE_ConsultarPermisos oe) throws IdeamException {
        String sql = "select * from CONSULTAR_PERMISOS_V ";
        if (oe.getCodAutoridadAmbiental() != null) {
            sql +=
"  where upper(autoridad) =  '" + oe.getCodAutoridadAmbiental().toUpperCase() +
 "'";
        }
        if (oe.getFiltroConsulta() != null &&
            oe.getFiltroConsulta().equals("P")) {
            sql +=
"  and upper(ESTADOINFOTECNICA)= '" + oe.getFiltroConsulta().toUpperCase() +
 "'";
        }
        if (oe.getFechaInicio() != null && oe.getFechaInicio().length() == 0) {
            sql +=
"   AND  FECHAEXPEDICION >= TO_DATE(' " + oe.getFechaInicio() +
 "', 'DD/MM/YYYY') ";
        }
        if (oe.getFechaFin() != null && oe.getFechaFin().length() == 0) {
            sql +=
"   AND FECHAVENCIMIENTO <= TO_DATE(' " + oe.getFechaFin() + "', 'DD/MM/YYYY') ";
        }

        Query q = em.createNativeQuery(sql, ConsultarPermisosV.class);
        List lista = q.getResultList();
        return lista;
    }


    //REGISTROS Novedades  ** LISBETH

    public Long registrarNovedadPermisosSIAC(OE_RegistrarNovedadPermiso oeNovedadPermiso) throws IdeamException {

        Concesion concesionNovedad = new Concesion();
        PermisoVertimiento permisoNovedad = new PermisoVertimiento();
        Long codigoPermiso = null;
        String tipoPermiso = null;
        Long idAutoridad = null;
        Concesion concActual = new Concesion();
        PermisoVertimiento pvActual = new PermisoVertimiento();
        tipoPermiso = oeNovedadPermiso.getPermisoNovedad().getTipoPermiso();
        idAutoridad =
                this.getIdAutoridad(oeNovedadPermiso.getCodAutoridadAmbiental());
        Long permisoid = this.getConsultaTramiteActual(oeNovedadPermiso);
        if (permisoid != null) {
            if (tipoPermiso.equals("CO")) {
                concActual = this.getConcesion(permisoid);
                if (concActual != null) {

                    //Se actualizan los datos de la concesion existente con la novedad
                    concActual.setTipoNovedad(oeNovedadPermiso.getPermisoNovedad().getTipoNovedad());
                    concActual.setNumeroExpedienteNovedad(oeNovedadPermiso.getPermisoNovedad().getNumResolucionNuevo());
                    concActual.setFechaExpedicionNovedad(fechaCalendar(oeNovedadPermiso.getPermisoNovedad().getFechaResolucionNuevo()));
                    concActual.setObservacionesNovedad(oeNovedadPermiso.getPermisoNovedad().getObservacionesNovedad());
                    //Se asignan los nuevos datos de concesion para crear novedad
                    concesionNovedad.setCodigoAutoridadAmbiental(idAutoridad);
                    concesionNovedad.setNumeroExpediente(concActual.getNumeroExpediente());
                    concesionNovedad.setResolucionCaudal(oeNovedadPermiso.getPermisoNovedad().getNumResolucionNuevo());
                    concesionNovedad.setCaudalConcesionado(oeNovedadPermiso.getPermisoNovedad().getCantidadRecursoOtorgado());
                    concesionNovedad.setOrigen("VITAL");
                    concesionNovedad.setCodigoPredio(concActual.getCodigoPredio());

                    if (!oeNovedadPermiso.getPermisoNovedad().getTipoNovedad().equals("REV")) {
                        concesionNovedad.setFechaExpedicionCaudal(fechaCalendar(oeNovedadPermiso.getPermisoNovedad().getFechaResolucionNuevo().toString()));
                        concesionNovedad.setFechaInicio(fechaCalendar(oeNovedadPermiso.getPermisoNovedad().getFechaExpedicionNuevo().toString()));
                        concesionNovedad.setFechaFin(fechaCalendar(oeNovedadPermiso.getPermisoNovedad().getFechaVencimientoNuevo().toString()));
                    } else {
                        Calendar fecha =
                            fechaCalendar(oeNovedadPermiso.getPermisoNovedad().getFechaResolucionNuevo().toString());
                        concesionNovedad.setFechaExpedicionCaudal(fecha);
                        concesionNovedad.setFechaInicio(fecha);
                        concesionNovedad.setFechaFin(fecha);
                    }
                    //se crea Concesion Nueva con novedad
                    Concesion concesionNueva = new Concesion();
                    if (concesionNueva.getCodigo() == null) {
                        concesionNueva =
                                this.registrarConcesion(concesionNovedad);
                    }
                    //Actualiza la exixtente con la novedad
                    if (concActual.getCodigo() != null) {
                        em.merge(concActual);
                    }

                    codigoPermiso = concesionNueva.getCodigo();

                }

            } else if (tipoPermiso.equals("PV")) {

                pvActual = this.getPermisoVertimiento(permisoid);
                if (pvActual != null) {

                    //Se actualizan los datos de la concesion existente con la novedad
                    pvActual.setTipoNovedad(oeNovedadPermiso.getPermisoNovedad().getTipoNovedad());
                    pvActual.setNumeroExpedienteNovedad(oeNovedadPermiso.getPermisoNovedad().getNumResolucionNuevo());
                    pvActual.setFechaExpedicionNovedad(fechaCalendar(oeNovedadPermiso.getPermisoNovedad().getFechaResolucionNuevo()));
                    pvActual.setObservacionesNovedad(oeNovedadPermiso.getPermisoNovedad().getObservacionesNovedad());
                    //Se asignan los nuevos datos de concesion para crear novedad
                    permisoNovedad.setCodigoAutoridadAmbiental(idAutoridad);
                    permisoNovedad.setNumeroExpediente(pvActual.getNumeroExpediente());
                    permisoNovedad.setResolucionInicioTramite(oeNovedadPermiso.getPermisoNovedad().getNumResolucionNuevo());
                    permisoNovedad.setCaudal(oeNovedadPermiso.getPermisoNovedad().getCantidadRecursoOtorgado());
                    permisoNovedad.setOrigen("VITAL");
                    permisoNovedad.setCodigoPredio(pvActual.getCodigoPredio());

                    if (!oeNovedadPermiso.getPermisoNovedad().getTipoNovedad().equals("REV")) {
                        permisoNovedad.setFechaExpedicionInicioTramite(fechaCalendar(oeNovedadPermiso.getPermisoNovedad().getFechaResolucionNuevo().toString()));
                        permisoNovedad.setFechaInicio(fechaCalendar(oeNovedadPermiso.getPermisoNovedad().getFechaExpedicionNuevo().toString()));
                        permisoNovedad.setFechaFin(fechaCalendar(oeNovedadPermiso.getPermisoNovedad().getFechaVencimientoNuevo().toString()));
                    } else {

                        Calendar fecha =
                            fechaCalendar(oeNovedadPermiso.getPermisoNovedad().getFechaResolucionNuevo().toString());
                        permisoNovedad.setFechaExpedicionInicioTramite(fecha);
                        permisoNovedad.setFechaInicio(fecha);
                        permisoNovedad.setFechaFin(fecha);
                    }
                    //se crea PermisoVertimiento Nuevo con novedad
                    PermisoVertimiento PermisoVNuevo =
                        new PermisoVertimiento();
                    if (PermisoVNuevo.getCodigo() == null) {
                        PermisoVNuevo = this.registrarPermiso(permisoNovedad);
                    }
                    //Actualiza PermisoVertimiento existente con la novedad
                    if (pvActual.getCodigo() != null) {
                        em.merge(pvActual);
                    }
                    codigoPermiso = PermisoVNuevo.getCodigo();
                }
            }
        } else {
            codigoPermiso = 1L;
        }
        return codigoPermiso;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public Long getConsultaTramiteActual(OE_RegistrarNovedadPermiso oeNovedadPermiso) throws IdeamException {
        System.out.println("autoridad-------getConsultaTramiteActual-1----------->" +
                           oeNovedadPermiso.getCodAutoridadAmbiental());
        Long permisoid = null;
        Concesion concid = new Concesion();
        PermisoVertimiento pvid = new PermisoVertimiento();
        String sql = null;
        Query q = null;
        String tipoPermiso = null;
        tipoPermiso = oeNovedadPermiso.getPermisoNovedad().getTipoPermiso();
        System.out.println("tipoPermiso-------tipoPermiso-1----------->" +
                           tipoPermiso);

        if (tipoPermiso.equals("CO")) {
            // Verificar si la concesion existe
            sql =
" select cv.* from RURT_CONCESIONES cv where cv.id=" + "(SELECT CC.ID FROM RURT_USUARIOS_AGUA UU , AUTORIDADES AA, RURT_PREDIOS PP , RURT_CONCESIONES CC " +
  "  WHERE  UU.ID_AUTORIDAD= AA.ID AND UPPER(AA.SIGLA) = '" +
  oeNovedadPermiso.getCodAutoridadAmbiental().toUpperCase() + "'" +
  "  AND UU.TIPO_DOCUMENTO =   " +
  oeNovedadPermiso.getPermisoNovedad().getCodigoTipoIdentificacion() +
  "  AND UU.NUM_DOCUMENTO= '" +
  oeNovedadPermiso.getPermisoNovedad().getIdentificacionPersona() + "'" +
  "  AND PP.ID_USUARIO= UU.ID " + "  AND UPPER(PP.CEDULA_CATASTRAL)= '" +
  oeNovedadPermiso.getPermisoNovedad().getCedulaCatastral().toUpperCase() +
  "'" + "  AND UPPER(CC.NUM_EXPEDIENTE) =  '" +
  oeNovedadPermiso.getPermisoNovedad().getNumExpedienteAct().toUpperCase() +
  "'" + "  AND UPPER(CC.NUM_RES_CAUDAL)= '" +
  oeNovedadPermiso.getPermisoNovedad().getNumResolucionAct().toUpperCase() +
  "'" + "  AND UPPER(CC.TIPO_NOVEDAD)  IS NULL )";
            System.out.println("autoridad-------sql-1----------->" + sql);

            q = em.createNativeQuery(sql, Concesion.class);
            List results = q.getResultList();
            if (!results.isEmpty()) {
                concid = (Concesion)results.get(0);
            }
            permisoid = concid.getCodigo();

        } else if (tipoPermiso.equals("PV")) {
            // Verificar si el permiso vertimiento existe
            sql =
"SELECT pcc.* from RURT_PERMISOS_VERTIMIENTOS pcc  where id= (" +
  "  SELECT PV.ID FROM RURT_USUARIOS_AGUA UU , AUTORIDADES AA, RURT_PREDIOS PP , RURT_PERMISOS_VERTIMIENTOS PV " +
  "  WHERE  UU.ID_AUTORIDAD= AA.ID AND UPPER(AA.SIGLA) = '" +
  oeNovedadPermiso.getCodAutoridadAmbiental().toUpperCase() + "'" +
  "  AND UU.TIPO_DOCUMENTO =   " +
  oeNovedadPermiso.getPermisoNovedad().getCodigoTipoIdentificacion() +
  "  AND UU.NUM_DOCUMENTO= '" +
  oeNovedadPermiso.getPermisoNovedad().getIdentificacionPersona() + "'" +
  "  AND PP.ID_USUARIO= UU.ID " + "  AND UPPER(PP.CEDULA_CATASTRAL)= '" +
  oeNovedadPermiso.getPermisoNovedad().getCedulaCatastral().toUpperCase() +
  "'" + "  AND UPPER(PV.NUM_EXPEDIENTE) =  '" +
  oeNovedadPermiso.getPermisoNovedad().getNumExpedienteAct().toUpperCase() +
  "'" + "  AND UPPER(PV.NUM_RES_INICIO_TRAMITE)= '" +
  oeNovedadPermiso.getPermisoNovedad().getNumResolucionAct().toUpperCase() +
  "'" + "  AND UPPER(PV.TIPO_NOVEDAD)  IS NULL )";


            System.out.println("autoridad-------sql-pv 1----------->" + sql);

            q = em.createNativeQuery(sql, PermisoVertimiento.class);
            List results = q.getResultList();
            if (!results.isEmpty()) {
                pvid = (PermisoVertimiento)results.get(0);
            }
            permisoid = pvid.getCodigo();
        }

        System.out.println("autoridad-------permisoid-1----------->" +
                           permisoid);

        return permisoid;
    }


    public GregorianCalendar fechaCalendar(String fecha) {
        GregorianCalendar fechaCalendar = null;
        String[] fechArray = fecha.split("/");
        int dia = Integer.valueOf(fechArray[0]);
        int mes = Integer.valueOf(fechArray[1]) - 1;
        int anio = Integer.valueOf(fechArray[2]);
        fechaCalendar = new GregorianCalendar(anio, mes, dia);
        return fechaCalendar;
    }


    public Long getIdAutoridad(String sigla){
        Long idUtoridad = null;
        try {
            idUtoridad =
                    new Long(parametrosService.getAutoridadSigla(sigla).getId());
        } catch (IdeamException e) {
            e.getMessage();
        }
        return idUtoridad;
    }


    public Long registrarTraspasoPermisosSIAC(OE_TraspasarPermiso oeTraspasarPermiso) throws IdeamException {
        System.out.println("autoridad--------1----------->" +
                           oeTraspasarPermiso.getCodAutoridadAmbiental());
        OE_RegistrarNovedadPermiso oeNovedadTraspasoPermiso =
            new OE_RegistrarNovedadPermiso();
        OE_RegistrarPermiso oePermisoNuevoTraspaso = new OE_RegistrarPermiso();
        PermisoNovedad OE_PermisoNovedad = new PermisoNovedad();
        Long codigoPermiso = null;
        String tipoPermiso = null;
        Long idAutoridad = null;
        Long permisoid = null;
        tipoPermiso = oeTraspasarPermiso.getPermisoTraspaso().getTipoPermiso();
        idAutoridad =
                this.getIdAutoridad(oeTraspasarPermiso.getCodAutoridadAmbiental());
        //Casting de Objeto oeNovedadTraspasoPermiso/OE_PermisoNovedad
        OE_PermisoNovedad.setCodigoTipoIdentificacion(oeTraspasarPermiso.getPermisoTraspaso().getCodigoTipoIdentificacion());
        OE_PermisoNovedad.setIdentificacionPersona(oeTraspasarPermiso.getPermisoTraspaso().getIdentificacionPersona());
        OE_PermisoNovedad.setCedulaCatastral(oeTraspasarPermiso.getPermisoTraspaso().getCedulaCatastral());
        OE_PermisoNovedad.setNumExpedienteAct(oeTraspasarPermiso.getPermisoTraspaso().getNumExpedienteAct());
        OE_PermisoNovedad.setNumResolucionAct(oeTraspasarPermiso.getPermisoTraspaso().getNumResolucionAct());
        OE_PermisoNovedad.setTipoPermiso(oeTraspasarPermiso.getPermisoTraspaso().getTipoPermiso());
        oeNovedadTraspasoPermiso.setCodAutoridadAmbiental(oeTraspasarPermiso.getCodAutoridadAmbiental());
        oeNovedadTraspasoPermiso.setPermisoNovedad(OE_PermisoNovedad);


        oePermisoNuevoTraspaso.setUsuarioAgua(oeTraspasarPermiso.getPermisoTraspaso().getUsuarioTraspaso());
        
        if(oeTraspasarPermiso.getPermisoTraspaso().getRepresentanteTraspaso()!=null){
                    
        oePermisoNuevoTraspaso.setRepresentanteLegal(oeTraspasarPermiso.getPermisoTraspaso().getRepresentanteTraspaso());
        }
        else {
          System.out.println("----------------------oePermisoNuevoTraspaso getRepresentanteTraspaso es null");
          
          }
        oePermisoNuevoTraspaso.setPredio(oeTraspasarPermiso.getPermisoTraspaso().getPredioTraspaso());
        oePermisoNuevoTraspaso.setPermiso(oeTraspasarPermiso.getPermisoTraspaso().getPermisoTraspasoNuevo());
        oePermisoNuevoTraspaso.setOrigen("VITAL");

        permisoid = this.getConsultaTramiteActual(oeNovedadTraspasoPermiso);
        if (permisoid != null) {


            if (tipoPermiso.equals("CO")) {
                //***********************  Concesiones *********************************************
                Concesion concActual = this.getConcesion(permisoid);
                if (concActual != null) {
                    System.out.println("++++++++++++++++++++ concActual.getCodigo()" +
                                       concActual.getCodigo());
                    //Se actualizan los datos de la concesion existente con la novedad
                    concActual.setTipoNovedad("TRA");

                    concActual.setNumeroExpedienteNovedad(oeTraspasarPermiso.getPermisoTraspaso().getPermisoTraspasoNuevo().getNumExpediente());
                    concActual.setFechaExpedicionNovedad(fechaCalendar(oeTraspasarPermiso.getPermisoTraspaso().getPermisoTraspasoNuevo().getFechaExpedicion()));
                    //Actualiza la existente con la novedad
                    if (concActual.getCodigo() != null) {
                        em.merge(concActual);
                    }
                }
            } else if (tipoPermiso.equals("PV")) {
                //***********************  permisos vertimiento *********************************************
                PermisoVertimiento pvActual =
                    this.getPermisoVertimiento(permisoid);
                if (pvActual != null) {
                    //Se actualizan los datos de la concesion existente con la novedad
                    pvActual.setTipoNovedad("TRA");
                    pvActual.setNumeroExpedienteNovedad(oeTraspasarPermiso.getPermisoTraspaso().getPermisoTraspasoNuevo().getNumExpediente());
                    pvActual.setFechaExpedicionNovedad(fechaCalendar(oeTraspasarPermiso.getPermisoTraspaso().getPermisoTraspasoNuevo().getFechaExpedicion()));
                    //Actualiza PermisoVertimiento existente con la novedad
                    if (pvActual.getCodigo() != null) {


                        em.merge(pvActual);
                    }
                }

            }
            codigoPermiso =
                    this.registrarUsuarioPredioSIAC(oePermisoNuevoTraspaso);
        } else {
            codigoPermiso = 1L;
        }
        return codigoPermiso;

    }


    // Consultar capatacion por fuente  en cada autoridad ambiental
    public List<DatosReporteWS> consultarDatosXFuente(String codAutoridadAmbiental, String tipo) throws IdeamException {
        Long idAutoridad = this.getIdAutoridad(codAutoridadAmbiental);
        DatosReporteWS dWS = new DatosReporteWS();
        String[][] datos =null;
        List lista = new ArrayList();
        String[] seriesLabels;
        Object[][] data2;     
           
           
        if(tipo.equals("CAP")){
             datos=this.getCaptacionesFuentes(idAutoridad);
        }
        if(tipo.equals("VRT")){
             datos=this.getVertimientosFuentes(idAutoridad);
        }
       if(tipo.equals("PTO")){
             datos=this.getPuntosMonitoreoFuentes(idAutoridad);
        }
        
           if(datos[0][0]!=null){
      
        seriesLabels = new String[datos[0].length];
        data2 = new Object[1][datos[0].length];
        Long cant = 0L;
        for (int i = 0; i < datos[0].length; i++) {
                if (datos[1][i] != null) {
                    dWS = new DatosReporteWS();
                    seriesLabels[i] = datos[0][i];
                    data2[0][i] = new Integer(datos[1][i]);
                    cant = new Long(data2[0][i].toString());
                    dWS.setDescripcion(seriesLabels[i].toString());
                    dWS.setCantidad(cant);
                    lista.add(dWS);
                 }
            }
           }
                return lista;
    }
    
  public List<AutoridadesWS> getAutoridadesAmbientalesWS() throws IdeamException {
      Query query = null;
      try {
          query = em.createNamedQuery("AutoridadesWS.findAll");

          return query.getResultList();
      } catch (NoResultException e) {
          return new ArrayList<AutoridadesWS>();
      }
  }

}
