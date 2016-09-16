package co.gov.ideam.sirh.usuarios.agua.business;

import co.gov.ideam.sirh.archivos.model.RowTO;
import co.gov.ideam.sirh.datossinc.model.ConcesionSinc;
import co.gov.ideam.sirh.datossinc.model.PermisoVertimientoSinc;
import co.gov.ideam.sirh.datossinc.model.PredioSinc;
import co.gov.ideam.sirh.datossinc.model.RepresentanteSinc;
import co.gov.ideam.sirh.datossinc.model.UsuarioAguaSinc;
import co.gov.ideam.sirh.fuentes.model.FnttTramo;
import co.gov.ideam.sirh.parametros.model.Autoridades;
import co.gov.ideam.sirh.usuarios.agua.model.ws.entidades.AutoridadesWS;
import co.gov.ideam.sirh.parametros.model.Lista;
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
import co.gov.ideam.sirh.usuarios.agua.model.CriteriosBusquedaExternasTO;
import co.gov.ideam.sirh.usuarios.agua.model.CriteriosBusquedaTO;
import co.gov.ideam.sirh.usuarios.agua.model.DatosNodosTO;

import co.gov.ideam.sirh.usuarios.agua.model.PermisoVertimiento;
import co.gov.ideam.sirh.usuarios.agua.model.PermisoVertimientoTO;
import co.gov.ideam.sirh.usuarios.agua.model.Predio;
import co.gov.ideam.sirh.usuarios.agua.model.PrediosTO;
import co.gov.ideam.sirh.usuarios.agua.model.PermisoVertimientoDetalle;
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
import co.gov.ideam.sirh.usuarios.agua.model.SubttFuniasDocumentosPOJO;
import co.gov.ideam.sirh.usuarios.agua.model.SubttFuniasNivel;
import co.gov.ideam.sirh.usuarios.agua.model.SubttFuniasPOJO;
import co.gov.ideam.sirh.usuarios.agua.model.TipoModificacionTO;
import co.gov.ideam.sirh.usuarios.agua.model.UsuarioAgua;
import co.gov.ideam.sirh.usuarios.agua.model.ws.consultarPermisos.OE_ConsultarPermisos;
import co.gov.ideam.sirh.usuarios.agua.model.ws.entidades.DatosReporteWS;
import co.gov.ideam.sirh.usuarios.agua.model.ws.registrarNovedadPermiso.OE_RegistrarNovedadPermiso;
import co.gov.ideam.sirh.usuarios.agua.model.ws.registrarPermiso.OE_RegistrarPermiso;
import co.gov.ideam.sirh.usuarios.agua.model.ws.traspasarPermiso.OE_TraspasarPermiso;
import co.gov.ideam.sirh.util.IdeamException;
import java.sql.SQLException;
import java.util.List;
import javax.ejb.Remote;
import javax.naming.NamingException;
import javax.persistence.NoResultException;


@Remote
public interface UsuariosAguaEJB {
    /**
     * Retorna los datos de gestion de especialistas requeridos para generar
     * la grafica
     * @param idAutoridad
     * @return
     * @throws IdeamException
     */
    public String[][] getGestionEspecialistas(Long idAutoridad) throws IdeamException;
    /**
     * Retorna los datos de investigacion de especialistas requeridos para generar
     * la grafica
     * @param idAutoridad
     * @return
     * @throws IdeamException
     */
    public String[][] getInvestigacionEspecialistas(Long idAutoridad) throws IdeamException;
    /**
     * Retorna los datos de formacion de especialistas requeridos para generar
     * la grafica
     * @param idAutoridad
     * @return
     * @throws IdeamException
     */
    public String[][] getFormacionEspecialistas(Long idAutoridad) throws IdeamException;
    /**
     * Retorna los datos de detalle del reporte de completitud
     * @param condicion
     * @param autoridad
     * @return
     * @throws IdeamException
     */
    public List<RowTO> getDetalleCompletitud(CondicionErrorTO condicion,
                                                         Autoridades autoridad)throws IdeamException,
                                                                           NamingException,
                                                                           SQLException;
    /**
     * Retorna una lista con todas las condiciones de error registradas en el
     * sistema
     * @return
     * @throws IdeamException
     */
    public List<CondicionErrorTO> getTotalesCondicionesError(Autoridades autoridad)throws IdeamException;
    /**
     * Retorna una lista con todos los tipos de modificacion permitidos
     * @return
     * @throws IdeamException
     */
    public List<TipoModificacionTO> getAllTiposModificacion()throws IdeamException;
    /**
     * Retorna la suma del campo caudal_disegno correspondientes a las captaciones
     * asociadas al tramo recibido como parametro
     * @param tramo
     * @return
     * @throws IdeamException
     */
    public Double getDemandaTramo(FnttTramo tramo)throws IdeamException;
    /**
     * Retorna la concesion asociada al numero de resolucion
     * @param numeroResolucion
     * @return
     * @throws IdeamException
     */
    public Concesion getConcesionByResolucion(String numeroResolucion)throws IdeamException;
    /**
     * Retorna una lista de los predios registrados en el sistema
     * @param criterioBusqueda Solo ser tiene en cuenta el atributo clasificacionSuelo
     * @param autoridad
     * @return
     * @throws IdeamException
     */
    public List<PrediosTO> getAllPredios(CriteriosBusquedaTO criterioBusqueda,
                                          Autoridades autoridad) throws IdeamException;
    /**
     * Retorna la cantidad de predios por su ubicacion
     * @return
     * @throws IdeamException
     */
    public String[][] getUbicacionUsuarios(Long idAutoridad)throws IdeamException;
    
  public String[][] getUbicacionUsuariosSubt(Long idAutoridad)throws IdeamException;

    /**
     * Retorna la cantidad de usos definidos a captaciones
     * @return
     * @throws IdeamException
     */
    public String[][] getUsosCaptaciones(Long idAutoridad)throws IdeamException;
public String[][] getUsosCaptacionesSubt(Long idAutoridad)throws IdeamException;
    /**
     * Retorna la cantidad de usuarios registrados por actividad CIIU
     * @return
     * @throws IdeamException
     */
    public String[][] getUsoAguaAvtividad()throws IdeamException;
    /**
     * Retorna una lista de permisos que contienen la cadena recibida como
     * parametro y que pertenecen a la autoridad
     * @param numeroExpediente
     * @param autoridad
     * @return
     * @throws IdeamException
     */
    public List<PermisoVertimientoTO> getAllPermisos(String numeroExpediente,String nroResol, String fInicio, String fFin,
                                                     Autoridades autoridad,
                                                     String estado)throws IdeamException;
    /**
     * Retorna una lista de permisos que contienen la cadena recibida
     * como parametro
     * @param numeroExpediente
     * @return
     * @throws IdeamException
     */
    public List<PermisoVertimientoTO> getAllPermisos(String numeroExpediente,String nroResol, String fInicio, String fFin,
                                                     String estado)throws IdeamException;
    /**
     * Retorna una lista con todos los permisos de vertimiento asociados a la
     * autoridad ambiental recibidos como parametro
     * @param autoridad
     * @return
     * @throws IdeamException
     */
    public List<PermisoVertimientoTO> getAllPermisos(Autoridades autoridad, String estado)throws IdeamException;
    
    //dl
    public List<PermisoVertimientoDetalle> getAllDetallePermisosVertimiento(Autoridades autoridad)throws IdeamException;
    
    /**
     * Retorna una lista con todos los permisos de vertimiento registrados
     * @return
     * @throws IdeamException
     */
    public List<PermisoVertimientoTO> getAllPermisos(String estado)throws IdeamException;
    /**
     * Retorna una lista con las concesiones que contienen la cadena recibida
     * por parametro
     * @param numeroExpediente
     * @return
     * @throws IdeamException
     */
    public List<ConcesionTO> getAllConcesiones(String numeroExpediente,  String estado)throws IdeamException;
    /**
     * Retorna una lista con todas las concesiones asociadas a la autoridad
     * ambiental y que contienen la cadena recibida como parametro
     * @param numeroExpediente
     * @param autoridad
     * @return
     * @throws IdeamException
     */
    public List<ConcesionTO> getAllConcesiones(String numeroExpediente,String snroResolucion, String fInicio, String fFin,
                                               Autoridades autoridad,
                                               String estado)throws IdeamException;
    /**
     * Retorna una lista con todas las concesiones asociadas a la autoridad
     * ambiental recibida como parametro
     * @param autoridad
     * @return
     * @throws IdeamException
     */
    public List<ConcesionTO> getAllConcesiones(Autoridades autoridad)throws IdeamException;
    /**
     * Retorna una lista con todas las concesiones registradas en el sistema
     * @return
     * @throws IdeamException
     */
    public List<ConcesionTO> getAllConcesiones(String estado)throws IdeamException;
    /**
     * Retorna una matriz con los datos correspondientes al estado de los
     * permisos de vertimientos
     * @return
     * @throws IdeamException
     */
    public String[][] getEstadosVertimientos(Long idAutoridad)throws IdeamException;
    /**
     * Retorna una matriz con los datos correspondientes al estado de la concesiones
     * @return
     * @throws IdeamException
     */
    public String[][] getEstadosConcesiones(Long idAutoridad)throws IdeamException;
    
  public String[][] getEstadosConcesionesSubt(Long idAutoridad)throws IdeamException;
    /**
     * Retorna una matriz con los datos correspondientes a la naturaleza
     * de los usuarios
     * @return
     * @throws IdeamException
     */
    public String[][] getNaturalezaUsuarios(Long idAutoridad)throws IdeamException;
    public String[][] getNaturalezaUsuariosSubt(Long idAutoridad)throws IdeamException;

    public List<CaudalesConcesionadoTO> getListaCaudalesConcesionados(Long idAutoridad) throws IdeamException ;
    public List getListaCaudalesVertidos(Long idAutoridad)throws IdeamException;
    
    public List getListaFuentesExternas() throws IdeamException ;
    public List getListaFuentesExternas(CriteriosBusquedaExternasTO criterios) throws IdeamException ;
    
public List getListaFuentesExternasSubt() throws IdeamException ;
public List getListaFuentesExternasSubt(CriteriosBusquedaExternasTO criterios) throws IdeamException ;
    public List getListaCaudalesConcesionadosDetalle(Long idAutoridad, Long idFuente, Long codTramo)throws IdeamException;
    public List getListaCaudalesVertidosDetalle(Long idAutoridad, Long idFuente, Long codTramo)throws IdeamException;

    /**
     * Retorna el permiso correspondiente al numero de expediente recibida
     * @param numeroExpediente
     * @return
     * @throws IdeamException
     */
    public PermisoVertimiento getPermisoVertimiento(String numeroExpediente,
                                                    Long codigoAutoridad)throws IdeamException;
    
     /**
      * Retorna el permiso correspondiente a los parametros recbidos.
      * @param numeroExpediente
      * @param numeroResolucion
      * @param codigoPredio
      * @param codigoAutoridad
      * @return
      * @throws IdeamException
      */
    public PermisoVertimientoTO getVertimientoByExpedienteResolucionPredio(
            String numeroExpediente, String numeroResolucion, Long codigoPredio,
            Long codigoAutoridad) throws IdeamException;
    
    /**
     * Retorna la concesion correspondiente al numero de expediente recibida
     * como parametro
     * @param numeroExpediente
     * @return
     * @throws IdeamException
     */
    public Concesion getConcesion(String numeroExpediente)throws IdeamException;
    
    
    public Concesion getConcesionByAutoridadAmbiental(String numeroExpediente, Long idAutoridad)throws IdeamException;
    /**
     * Retorna el predio correspondiente a la cedula catastral recibida
     * como parametro
     * @param cedulaCatastral
     * @return
     * @throws IdeamException
     */
    public Predio getPredio(String cedulaCatastral)throws IdeamException;

    /**
     * Retorna una lista con todos los usuarios del agua que cumplen con los
     * criterios de busqueda relacionados
     * @param criterios
     
     * @return
     * @throws IdeamException
     */
    public List getAllUsuarios(CriteriosBusquedaTO criterios)throws  IdeamException;
   
    /**
     * Retorna una lista con todos los predios del agua que cumplen con los
     * criterios de busqueda relacionados
     * @param criterios
    
     * @return
     * @throws IdeamException
     */
   
    public List getAllPredios(CriteriosBusquedaTO criterios)throws  IdeamException;
    /**
     * Retorna una lista con todos los predios  que cumplen con los
     * criterios de busqueda relacionados
     * @param criterios
     * @param codigoAutoridad
     * @return
     * @throws IdeamException
     */
    public List getAllPrediosAut(CriteriosBusquedaTO criterios,
                               Long codigoAutoridad)throws  IdeamException;
    /**
     * Retorna una lista con todos los usuarios del agua que cumplen con los
     * criterios de busqueda relacionados
     * @param criterios
     * @param codigoAutoridad
     * @return
     * @throws IdeamException
     */
    public List getAllUsuarios(CriteriosBusquedaTO criterios,
                               Long codigoAutoridad)throws  IdeamException;
    /**
     * Borra el usuario recibido como parametro
    
     * @throws IdeamException
     */
    
     public List getAuditoriaUsuarios(String  fInicio,String  fFin,  Autoridades autoridad , Lista usuarioLogin ) throws IdeamException ;
    
    public List<DatosNodosTO> getReporteNodos( Autoridades autoridad  ) throws IdeamException ;
       
    public List getUsuariosLogin(Autoridades autoridad ) throws IdeamException ;
    
    public void deleteUsuario(UsuarioAgua usuario)throws IdeamException;
    /**
     * Retorna el representante legal correspondiente al codigo recibido como
     * parametro
     * @param codigoUsuario
     * @return
     * @throws IdeamException
     */
    public Representante getRepresentante(Long codigoUsuario)throws IdeamException;
    /**
     * Borra el permiso de vertimiento recibido como paranetro
     * @param permiso
     * @throws IdeamException
     */
    public void deletePermisoVertimiento(PermisoVertimiento permiso)throws IdeamException;
    /**
     * Retorna el permiso de vertimiento correspondiente al codigo recibido como
     * parametro
     * @param codigo
     * @return
     * @throws IdeamException
     */
    public PermisoVertimiento getPermisoVertimiento(Long codigo)throws IdeamException;
    /**
     * Registra o actualiza la informacion del permiso de vertimiento
     * recibido como parametro
     * @param permiso
     * @return
     * @throws IdeamException
     */
    public PermisoVertimiento registrarPermiso(PermisoVertimiento permiso)throws IdeamException;
    public Predio registrarPredio(UsuarioAgua usu, Predio predio)throws IdeamException;
    /**
     * Actualiza la informacion del usuario recibido como parametro
     * @param usuario
     * @throws IdeamException
     */
    public void updateUsuario(UsuarioAgua usuario)throws IdeamException;
    /**
     * Borra el predio recibido como parametro y las concesiones o
     * permisos asociados al mismo
     * @param predio
     * @throws IdeamException
     */
    public void borrarPredio(Predio predio)throws IdeamException;
    /**
     * Retorna el Predio correspondiente al codigo recibido como parametro
     * @param codigo
     * @return
     * @throws IdeamException
     */
    public Predio getPredio(Long codigo)throws IdeamException;
    /**
     * Actualiza la informacion del predio recibido como parametro
     * @param predio
     * @param usuario
     * @throws IdeamException
     */
    public void updatePredio(UsuarioAgua usuario, Predio predio)throws IdeamException;
    /**
     * Retorna la concesion correspondiente al codigo recibido por parametro
     * @param codigo
     * @return
     * @throws IdeamException
     */
    public Concesion getConcesion(Long codigo)throws IdeamException;
    /**
     * Borra la concesion recibida por parametro
     * @param concesion
     * @throws IdeamException
     */
    public void deleteConcesion(Concesion concesion)throws IdeamException;
    /**
     * Registra una nueva concesion en la base de datos
     * @param concesion
     * @return Concesion actualizada
     * @throws IdeamException
     */
    public Concesion registrarConcesion(Concesion concesion)throws IdeamException;
    /**
     * Retorna una lista con los permisos de vertimiento asociados al predio
     * recibido como parametro
     * @param predio
     * @return
     * @throws IdeamException
     */
    public List<PermisoVertimiento> getPermisosVertimiento(Predio predio)throws IdeamException;

    /**
     * Retorna una lista con las captaciones ilegales de un predio //Pilar
     * recibido como parametro
     * @param predio
     * @return
     * @throws IdeamException
     */
    public List<Captacion> getCaptacionesSinConcesion(Predio predio)throws IdeamException; //Pilar


    /**
     * Retorna una lista con los predios asociados al predio recibido como
     * parametro
     * @param predio
     * @return
     * @throws IdeamException
     */
    public List<Concesion> getConcesiones(Predio predio)throws IdeamException;

    /**
     * Retorna una lista con las captaciones asociados a una concesion recibido como
     * parametro
     * @param concesion
     * @return
     * @throws IdeamException
     */
    public List<Captacion> getCaptaciones(Concesion concesion)throws IdeamException;
    /**
     * Retorna una lista con todos los predios asociados al usuario recibido
     * como parametro
     * @param usuario
     * @return
     * @throws IdeamException
     */
    public List<Predio> getPredios(UsuarioAgua usuario)throws IdeamException;
    /**
     * Retorna una lista con los usuarios registrados en el sistema
     * recibida como parametro
     * @return
     * @throws IdeamException
     */
    public List getAllUsuarios()throws  IdeamException;
    /**
     * Retorna una lista con los usuarios del agua correspondientes a la autoridad
     * recibida como parametro
     * @param codigoAutoridad
     * @return
     * @throws IdeamException
     */
    public List getAllUsuarios(Long codigoAutoridad)throws  IdeamException;
    /**
     * Retorna el usuario correspondiente a los parametros recibidos
     * @param codigoTipoId
     * @param numeroId
     * @param codigoAutoridad
     * @return
     * @throws IdeamException
     */
    public UsuarioAgua getUsuario(Integer codigoTipoId,
                                  String numeroId,
                                  Long codigoAutoridad)throws IdeamException;
    /**
     * Registra el usuario y predio recibidos como parametro
     * @param usuario
     * @param predio
     * @param representanteLegal
     * @param autoridad
     * @throws IdeamException
     */
     public Object[] registrarUsuarioPredio(UsuarioAgua usuario,
                                       Predio predio,
                                       Representante representanteLegal,
                                       Autoridades autoridad)throws IdeamException;
    
    
  
    /**
     * Retorna el usuario del agua correspondiente al codigo recibido como parametro
     * @param codigo
     * @return
     * @throws IdeamException
     */
    public UsuarioAgua getUSuario(Long codigo)throws IdeamException;
    /**
     * Inserta o actualiza un nuevo usuario en la base de datos
     * @param usuario
     * @param autoridad
     * @throws IdeamException
     */
    public void updateUsuario(UsuarioAgua usuario, Autoridades autoridad)throws IdeamException;

    /**
     * Inserta una nueva captación en la base de datos
     * @param captacion
     * @return Captacion
     * @throws IdeamException
     */
    public Captacion createCaptacion(Captacion captacion)throws IdeamException;

    /**
     * Actualiza una captación en la base de datos
     * @param captacion
     * @return Captacion
     * @throws IdeamException
     */
    public Captacion updateCaptacion(Captacion captacion)throws IdeamException;

    /**
     * Retorna una captación  correspondiente al codigo recibido como parametro
     * @param id
     * @return
     * @throws IdeamException
     */
    public Captacion getCaptacion(Long id)throws IdeamException;

    /**
     * Retorna una captación  correspondiente al identificador texto recibido como parametro
     * @param identificadorPunto
     * @param autoridad
     * @return
     * @throws IdeamException
     */
     public CaptacionTO getCaptacion(String identificadorPunto, 
                                     Integer autoridad) throws IdeamException;

    /**
     * Elimina de la bd la captación recibida.
     * @param captacion
     * @return
     * @throws IdeamException
     */
    public void deleteCaptacion(Captacion captacion)throws IdeamException;

    /**
     * Retorna una captación  correspondiente al codigo recibido como parametro,
     * asociando los componentes de la captación
     * @param id
     * @return
     * @throws IdeamException
     */
    public Captacion getCaptacionWithComponentes(Long id)throws IdeamException, NoResultException;

    /**
     * Retorna la lista de componentes de la captacion,
     * @param id
     * @return
     * @throws IdeamException
     */
    public List getComponentesByCaptacion(Long id)throws IdeamException, NoResultException;

    /**
     * Retorna una lista con las captaciones correspondientes a la autoridad y
     * concesión recibidos como parametro.
     * @param concesion
     * @param autoridad
     * @return
     * @throws IdeamException
     */
    public List<Captacion> getCaptacionesByAutoridadConcesion(Concesion concesion, Autoridades autoridad)throws IdeamException;

    /**
     * Retorna una lista con las captaciones que cumplan con los filtros.
     * @param criterios
     * @return
     * @throws IdeamException
     */
    public List<CaptacionTO> getAllCaptacionesBusqueda(CriteriosBusquedaTO criterios)throws IdeamException;
    
    //dl
    public List<CaptacionDetalle> getAllCaptacionDetalle(Autoridades autoridad)throws IdeamException;
  /**
   * -
   */
  //dl
    /**
     * Retorna una lista con las captaciones subterrneas que cumplan con los filtros.
     * @param criterios
     * @return
     * @throws IdeamException
     */
    public List<CaptacionSubTO> getAllCaptacionesBusquedaSub(CriteriosBusquedaTO criterios)throws IdeamException;

    /**
     * Retorna una lista con los puntos de vertimiento asociados a un permiso
     * recibido como parametro.
     * @param permiso
     * @return
     * @throws IdeamException
     */
    public List<PuntoVertimiento> getVertimientos(PermisoVertimiento permiso)throws IdeamException;

    /**
     * Inserta un nuevo vertimiento en la base de datos
     * @param vertimiento
     * @return PuntoVertimiento
     * @throws IdeamException
     */
    public PuntoVertimiento createVertimiento(PuntoVertimiento vertimiento)throws IdeamException;

    /**
     * Actualiza un vertimiento en la base de datos
     * @param vertimiento
     * @return PuntoVertimiento
     * @throws IdeamException
     */
    public PuntoVertimiento updateVertimiento(PuntoVertimiento vertimiento)throws IdeamException;

    /**
     * Retorna un vertimiento correspondiente al codigo recibido como parametro
     * @param id
     * @return
     * @throws IdeamException
     */
    public PuntoVertimiento getVertimiento(Long id)throws IdeamException;

    /**
     * Elimina de la bd el vertimiento recibido.
     * @param vertimiento
     * @return
     * @throws IdeamException
     */
    public void deleteVertimiento(PuntoVertimiento vertimiento)throws IdeamException;


    public String[][] getCaptacionesFuentes(Long idAutoridad) throws IdeamException;
public String[][] getCaptacionesFuentesSubt(Long idAutoridad) throws IdeamException;
    public String[][] getVertimientosFuentes(Long idAutoridad) throws IdeamException;
    public String[][] getPrediosPorDepto(Long idAutoridad) throws IdeamException;
    public String[][] getPrediosPorMcpio(Long idAutoridad) throws IdeamException;
public String[][] getPrediosPorDeptoSubt(Long idAutoridad) throws IdeamException;
    public String[][] getPrediosPorMcpioSubt(Long idAutoridad) throws IdeamException;


    /**
     * Retorna una lista con los puntos de vertimiento que cumplan con los filtros.
     * @param criterios
     * @return
     * @throws IdeamException
     */
    public List<PuntoVertimientoTO> getAllVertimientosBusqueda(CriteriosBusquedaTO criterios)throws IdeamException;

    /**
     * Inserta un componente para captación en la base de datos
     * @param captacionComponente
     * @return RurtCaptacionComponentes
     * @throws IdeamException
     */
    public RurtCaptacionComponentes createCaptacionComponente(RurtCaptacionComponentes captacionComponente)throws IdeamException;

    /**
     * Elimina de la bd el componente de la captacion recibido.
     * @param captacionComponente
     * @throws IdeamException
     */
    public void deleteCaptacionComponente(RurtCaptacionComponentes captacionComponente)throws IdeamException;

    /**
     * Inserta un tratamiento para un punto de vertimiento en la base de datos
     * @param puntoTratamiento
     * @return PuntoVertimientoTratamiento
     * @throws IdeamException
     */
    public PuntoVertimientoTratamiento createPuntoTratamiento(PuntoVertimientoTratamiento puntoTratamiento)throws IdeamException;

    /**
     * Elimina de la bd el tratamiento del punto de vertimiento recibido.
     * @param puntoTratamiento
     * @throws IdeamException
     */
    public void deletePuntoTratamiento(PuntoVertimientoTratamiento puntoTratamiento)throws IdeamException;

    /**
     * Retorna una lista con los tratamiento asociados a un punto de vertimiento.
     * @param idPunto
     * @param idCategoria
     * @return
     * @throws IdeamException
     */
    public List getTratamientosByPuntoVertimientoCategoria(Long idPunto, Long idCategoria)throws IdeamException, NoResultException;


    /**
     * Retorna una lista con los usos asociados a una captacion
     * recibido como parametro.
     * @param captacion
     * @return
     * @throws IdeamException
     */
    public List<RurtCaptacionUso> getUsos(Captacion captacion)throws IdeamException;

    /**
     * Retorna un uso correspondiente al codigo recibido como parametro
     * @param id
     * @return
     * @throws IdeamException
     */
    public RurtCaptacionUso getUso(Long id)throws IdeamException;


    /**
     * Inserta un nuevo uso en la base de datos
     * @param uso
     * @return RurtCaptacionUso
     * @throws IdeamException
     */
    public RurtCaptacionUso createUso(RurtCaptacionUso uso)throws IdeamException;

    /**
     * Actualiza un uso en la base de datos
     * @param uso
     * @return RurtCaptacionUso
     * @throws IdeamException
     */
    public RurtCaptacionUso updateUso(RurtCaptacionUso uso)throws IdeamException;

    /**
     * Elimina de la bd el uso recibido.
     * @param uso
     * @return
     * @throws IdeamException
     */
    public void deleteUso(RurtCaptacionUso uso)throws IdeamException;

    /**
     * Retorna el predio correspondiente a la cedula catastral y codigo de usuario
     * recibidos como parametro, ademas de la autoridad.
     * @param cedulaCatastral
     * @param codigoUsuario
     * @param codigoAutoridad
     * @return
     * @throws IdeamException
     */
    public PrediosTO getPredioTO(String cedulaCatastral, String codigoUsuario,
                            Long codigoAutoridad)throws IdeamException;



    /**
     * Retorna el predio correspondiente a la cedula catastral y codigo de usuario
     * recibidos como parametro, ademas de la autoridad.
     * @param cedulaCatastral
     * @param codigoUsuario
     * @param codigoAutoridad
     * @return
     * @throws IdeamException
     */
    public Predio getPredio(String cedulaCatastral, Long codigoUsuario,
                            Long codigoAutoridad)throws IdeamException;


    /**
     * Retorna la concesion correspondiente a los parametros recbidos.
     * @param numeroExpediente
     * @param numeroResolucion
     * @param codigoPredio
     * @param codigoAutoridad
     * @return
     * @throws IdeamException
     */
    public ConcesionTO getConcesionByExpedienteResolucionPredio(
            String numeroExpediente, String numeroResolucion, Long codigoPredio,
            Long codigoAutoridad) throws IdeamException;
    


    /**
     * Retorna la captacion correspondiente a los parametros recbidos.
     * @param captacion
     * @param codigoAutoridad
     * @return
     * @throws IdeamException
     */
    public CaptacionTO getCaptacion(Captacion captacion, Long codigoAutoridad) throws IdeamException;

    /**
     * Retorna el componente en la captacion correspondiente a los
     * parametros recibidos.
     * @param idComponente
     * @param idCaptacion
     * @return
     * @throws IdeamException
     */
    public RurtCaptacionComponentes getComponenteCaptacion(Long idComponente, Long idCaptacion) throws IdeamException;

    /**
     * Retorna la captacion correspondiente a los parametros recbidos.
     * @param vertimiento
     * @param codigoAutoridad
     * @return
     * @throws IdeamException
     */
    public PuntoVertimientoTO getVertimiento(PuntoVertimiento vertimiento, Long codigoAutoridad) throws IdeamException;

    /**
     * Retorna el tratmiento en el punto de vertimiento correspondiente a los
     * parametros recibidos.
     * @param tratamientoPunto
     * @return
     * @throws IdeamException
     */
    public PuntoVertimientoTratamiento getTratamientoPunto(PuntoVertimientoTratamiento tratamientoPunto) throws IdeamException;

    /**
     * Retorna un listado de captaciones de una fuente especifica.
     * @param idFuente
     * @return
     * @throws IdeamException
     */
    public List getCaptacionByFuente(Long idFuente) throws IdeamException;

    /**
     * Retorna un listado de captaciones de un tramo especifico.
     * @param idTramo
     * @return
     * @throws IdeamException
     */
    public List getCaptacionByTramo(Long idTramo) throws IdeamException;

    /**
     * Retorna un listado de vertimientos de una fuente especifica.
     * @param idFuente
     * @return
     * @throws IdeamException
     */
    public List getVertimientoByFuente(Long idFuente) throws IdeamException;

    /**
     * Retorna un listado de vertimientos de un tramo especifico.
     * @param idTramo
     * @return
     * @throws IdeamException
     */
    public List getVertimientoByTramo(Long idTramo) throws IdeamException;


    /**
     * Borra la concesion recibido como parametro y las captaciones asociadas.
     * @param concesion
     * @throws IdeamException
     */
    public void borrarConcesion(Concesion concesion)throws IdeamException;

    /**
     * Borra el permiso recibido como parametro y los puntos de vertimiento asociadas.
     * @param permiso
     * @throws IdeamException
     */
     public void borrarPermiso(PermisoVertimiento permiso) throws IdeamException;

    /**
     * Borra la captacion recibida como parametro y todo lo asociado a ella.
     * @param captacion
     * @throws IdeamException
     */
    public void borrarCaptacion(Captacion captacion) throws IdeamException;

    /**
     * Borra el vertimiento recibido como parametro y los asociado a el.
     * @param punto
     * @throws IdeamException
     */
     public void borrarVertimiento(PuntoVertimiento punto) throws IdeamException;

    /**
     * Retorna la lista de funias asociados a una captacion.
     * @param captacion
     * @return
     * @throws IdeamException
     */
    public List<SubttFunias> getFuniasByCaptacion(Captacion captacion) throws IdeamException;

    /**
     * Retorna el funias de la captacion y el tipo correspondiente.
     * @param idCaptacion
     * @param tipoFunias
     * @return
     * @throws IdeamException
     */
    public SubttFunias getFuniasByCaptacionTipoFunias(Long idCaptacion, Integer tipoFunias) throws IdeamException;

    /**
     * Retorna un punto funias.
     * @param idFunias
     * @return
     * @throws IdeamException
     */
    public SubttFunias getFunias(Long idFunias) throws IdeamException;

    /**
     * Crea o persiste el funias recibido.
     * @param funias
     * @return
     * @throws IdeamException
     */
    public SubttFunias createFunias(SubttFunias funias) throws IdeamException;

    /**
     * Actualiza el funias recibido.
     * @param funias
     * @return
     * @throws IdeamException
     */
    public SubttFunias updateFunias(SubttFunias funias) throws IdeamException;

    /**
     * Borra el funias recibido como parametro.
     * @param funias
     * @return
     * @throws IdeamException
     */
    public void deleteFunias(SubttFunias funias) throws IdeamException;

    /**
     * Retorna todos los archivos de un funias.
     * @param idFunias
     * @return
     * @throws IdeamException
     */
    public List getFilesByFunias(Long idFunias) throws IdeamException;
    /**
     *consulta para Dashboard sobre el tipo de fuentes superfial o subterránea en las que hay captaciones
     * @param idAutoridad
     * @return
     * @throws IdeamException
     */
    public String[][] getCaptacionesTipoFuente(Long idAutoridad) throws IdeamException;
    public String[][] getVertimientosTipo(Long idAutoridad) throws IdeamException;
    
    
    public List getAnioConcesiones(Long idAutoridad )throws IdeamException;
    public List getAnioPermisos(Long idAutoridad )throws IdeamException;
    
    public String[][] getConcesionesAnio(Integer anio, Long idAutoridad) throws IdeamException;
    public String[][] getPermisosAnio(Integer anio, Long idAutoridad) throws IdeamException;
    
   
    
    /**
     * consulta para Dashboard sobre el tipo de fuentes en las que hay captaciones
     * @param idAutoridad
     * @return
     * @throws IdeamException
     */

    public String[][] getCaptacionesTipoFuenteDet(Long idAutoridad) throws IdeamException;

    /**
         * usado en el nodo ideam para sincronizar con el nodo de las corporaciones
         * cuando se crea una captación.
         * @param captacion
         * @return
         * @throws IdeamException
         * */
    public CaptacionPOJO createCaptacionPlano(CaptacionPOJO captacion) throws IdeamException;

        /**
         * usado en el nodo ideam para sincronizar con el nodo de las corporaciones
         * cuando se actualiza una captación.
         * @param captacion
         * @return
         * @throws IdeamException
         * */
    public CaptacionPOJO updateCaptacionPlano(CaptacionPOJO captacion) throws IdeamException;

        /**
         * usado en el nodo ideam para sincronizar con el nodo de las corporaciones
         * cuando se elimina una captación.
         * @param captacion
         * @throws IdeamException
         * */
    public void deleteCaptacionPlano(CaptacionPOJO captacion) throws IdeamException;

        /**
         * usado en el nodo ideam para sincronizar con el nodo de las corporaciones
         * cuando se crea un punto de vertimiento.
         * @param vertimiento
         * @return
         * @throws IdeamException
         * */
    public PuntoVertimientoPOJO createVertimientoPlano(PuntoVertimientoPOJO vertimiento) throws IdeamException;

        /**
         * usado en el nodo ideam para sincronizar con el nodo de las corporaciones
         * cuando se actualiza un punto de vertimiento.
         * @param vertimiento
         * @return
         * @throws IdeamException
         * */
    public PuntoVertimientoPOJO updateVertimientoPlano(PuntoVertimientoPOJO vertimiento) throws IdeamException;

        /**
         * usado en el nodo ideam para sincronizar con el nodo de las corporaciones
         * cuando se elimina un punto de vertimiento.
         * @param vertimiento
         * @throws IdeamException
         * */
    public void deleteVertimientoPlano(PuntoVertimientoPOJO vertimiento) throws IdeamException;

        /**
         * usado en el nodo ideam para sincronizar con el nodo de las corporaciones
         * cuando se elimina una captación.
         * @param cpt
         * @throws IdeamException
         * */
    public void borrarCaptacionPlano(CaptacionPOJO cpt) throws IdeamException;

        /**
         * usado en el nodo ideam para sincronizar con el nodo de las corporaciones
         * cuando se elimina un punto de vertimiento.
         * @param pto
         * @throws IdeamException
         * */
    public void borrarVertimientoPlano(PuntoVertimientoPOJO pto) throws IdeamException;

    
    /**
     * usado en el nodo ideam para sincronizar con el nodo de las corporaciones.
     * cuando se crea un un componente relacionado a una captación.
     * @param captacionComponente
     * @return
     * @throws IdeamException
     * */  
    public RurtCaptacionComponentePOJO createCaptacionComponentePlano(RurtCaptacionComponentePOJO captacionComponente) throws IdeamException;  
        
    /**
     * usado en el nodo ideam para sincronizar con el nodo de las corporaciones.
     * cuando se crea un un pnto de tratamiento relacionado a un punto de vertimiento.
     * @param puntoTratamiento
     * @return
     * @throws IdeamException
     * */   
    public PuntoVertimientoTratamientoPOJO createPuntoTratamientoPlano(PuntoVertimientoTratamientoPOJO puntoTratamiento) throws IdeamException;
    
    /**
     * usado en el nodo ideam para sincronizar con el nodo de las corporaciones.
     * cuando se crea un un uso.
     * @param uso
     * @return
     * @throws IdeamException
     * */
    public RurtCaptacionUsoPOJO createUsoPlano(RurtCaptacionUsoPOJO uso) throws IdeamException;
    
    /**
     * usado en el nodo ideam para sincronizar con el nodo de las corporaciones.
     * cuando se crea un documento relacionado con un funias.
     * @param funiasFile
     * @return
     * @throws IdeamException
     * */
    public SubttFuniasDocumentosPOJO createFileFuniasPlano(SubttFuniasDocumentosPOJO funiasFile) throws IdeamException;
    
    /**
     * usado en el nodo ideam para sincronizar con el nodo de las corporaciones.
     * cuando se crea un funias.
     * @param funias
     * @return
     * @throws IdeamException
     * */
    public SubttFuniasPOJO createFuniasPlano(SubttFuniasPOJO funias) throws IdeamException;
    
    /**
     * Crea o persiste un nivel asociado a un funias.
     * @param nivel
     * @return
     * @throws IdeamException
     */
    public SubttFuniasNivel createFuniasNivel(SubttFuniasNivel nivel) throws IdeamException;
    
    /**
     * Consulta un  nivel por codigo.
     * @param idNivel
     * @return
     * @throws IdeamException
     */
    public SubttFuniasNivel getNivel(Long idNivel) throws IdeamException;
    
    /**
     * Consulta todos los niveles asociados a un funias.
     * @param funias
     * @return
     * @throws IdeamException
     */
    public List<SubttFuniasNivel> getNivelByFunias(SubttFunias funias) throws IdeamException;
    
    /**
     * Borra el nivel recibido como parametro.
     * @param nivel
     * @throws IdeamException
     */
    public void deleteNivel(SubttFuniasNivel nivel) throws IdeamException;
    
    /**
     * Actualiza el nivel recibido como parametro.
     * @param nivel
     * @return
     * @throws IdeamException
     */
    public SubttFuniasNivel updateNivel(SubttFuniasNivel nivel) throws IdeamException;
    
    /**
     * Registra el usuario y predio recibidos como parametro para integracion SIAC
    
     * @throws IdeamException
     */
     public Long registrarUsuarioPredioSIAC(OE_RegistrarPermiso oe)throws IdeamException;
    
 //CDONCEL
    public void updateAforo(RurtCapAforo aforo)throws IdeamException;
   
    public List<RurtCapAforo> getLsAforos(Captacion capt) throws IdeamException;  
 
   
     public void borrarAforo(String id)throws IdeamException;
  
    public PuntoVertimiento getVertimientobyPerm(PermisoVertimiento permiso) throws IdeamException;  
  
    //REGISTROS RUA ** LISBETH
     public Long registrarUsuarioPredioRUACO(UsuarioAguaSinc usuario,
                                           RepresentanteSinc representanteLegal,
                                           PredioSinc predio,
                                           ConcesionSinc concesion
                                           ) throws IdeamException ;
    //REGISTROS RUA ** LISBETH
     public Long registrarUsuarioPredioRUAPV(UsuarioAguaSinc usuario,
                                           RepresentanteSinc representanteLegal,
                                           PredioSinc predio,
                                           PermisoVertimientoSinc permisoVert) throws IdeamException ;
     
    //Consulta Permisos SIAC
     public    List<ConsultarPermisosV> getPermisosV(OE_ConsultarPermisos oe) throws IdeamException;

    //Registra Novedad Permisos SIAC
    public Long registrarNovedadPermisosSIAC(OE_RegistrarNovedadPermiso oeNovedadPermiso) throws IdeamException;

    //Registrar Novedad Traspaso Permisos SIAC
    public Long registrarTraspasoPermisosSIAC(OE_TraspasarPermiso oeTraspasarPermiso) throws IdeamException ;
    
    //*Consultar capatacion por fuente  en cada autoridad ambiental 
    public List<DatosReporteWS> consultarDatosXFuente(String codAutoridadAmbiental,String tipo) throws IdeamException ;
    public Long getIdAutoridad(String sigla);
    
   public List<AutoridadesWS> getAutoridadesAmbientalesWS() throws IdeamException ;
}
