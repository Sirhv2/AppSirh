package co.gov.ideam.sirh.usuarios.agua.view;

import co.gov.ideam.sirh.archivos.model.RowTO;
import co.gov.ideam.sirh.fuentes.model.FnttTramo;
import co.gov.ideam.sirh.fuentes.model.FnttTrmAforo;
import co.gov.ideam.sirh.parametros.model.Autoridades;
import co.gov.ideam.sirh.parametros.model.Divipola;
import co.gov.ideam.sirh.parametros.model.Lista;
import co.gov.ideam.sirh.seguridad.business.SeguridadEJB;
import co.gov.ideam.sirh.seguridad.view.SeguridadDelegate;
import co.gov.ideam.sirh.servlet.ServletLocator;

import co.gov.ideam.sirh.usuarios.agua.business.UsuariosAguaBean;
import co.gov.ideam.sirh.datossinc.model.ConcesionSinc;
import co.gov.ideam.sirh.datossinc.model.PermisoVertimientoSinc;
import co.gov.ideam.sirh.datossinc.model.PredioSinc;
import co.gov.ideam.sirh.datossinc.model.RepresentanteSinc;
import co.gov.ideam.sirh.datossinc.model.UsuarioAguaSinc;
import co.gov.ideam.sirh.usuarios.agua.business.UsuariosAguaEJB;
import co.gov.ideam.sirh.usuarios.agua.model.Captacion;
import co.gov.ideam.sirh.usuarios.agua.model.CaptacionDetalle;
import co.gov.ideam.sirh.usuarios.agua.model.CaptacionSubTO;
import co.gov.ideam.sirh.usuarios.agua.model.CaptacionTO;
import co.gov.ideam.sirh.usuarios.agua.model.CaudalesConcesionadoTO;
import co.gov.ideam.sirh.usuarios.agua.model.Concesion;
import co.gov.ideam.sirh.usuarios.agua.model.ConcesionTO;
import co.gov.ideam.sirh.usuarios.agua.model.CondicionErrorTO;
import co.gov.ideam.sirh.usuarios.agua.model.CriteriosBusquedaExternasTO;
import co.gov.ideam.sirh.usuarios.agua.model.CriteriosBusquedaTO;
import co.gov.ideam.sirh.usuarios.agua.model.DatosNodosTO;
import co.gov.ideam.sirh.usuarios.agua.model.PermisoVertimiento;
import co.gov.ideam.sirh.usuarios.agua.model.PermisoVertimientoDetalle;
import co.gov.ideam.sirh.usuarios.agua.model.PermisoVertimientoTO;
import co.gov.ideam.sirh.usuarios.agua.model.Predio;
import co.gov.ideam.sirh.usuarios.agua.model.PrediosTO;
import co.gov.ideam.sirh.usuarios.agua.model.PuntoVertimiento;
import co.gov.ideam.sirh.usuarios.agua.model.PuntoVertimientoTO;
import co.gov.ideam.sirh.usuarios.agua.model.PuntoVertimientoTratamiento;
import co.gov.ideam.sirh.usuarios.agua.model.Representante;
import co.gov.ideam.sirh.usuarios.agua.model.RurtCapAforo;
import co.gov.ideam.sirh.usuarios.agua.model.RurtCaptacionComponentes;
import co.gov.ideam.sirh.usuarios.agua.model.RurtCaptacionUso;
import co.gov.ideam.sirh.usuarios.agua.model.TipoModificacionTO;
import co.gov.ideam.sirh.usuarios.agua.model.SubttFunias;
import co.gov.ideam.sirh.usuarios.agua.model.SubttFuniasNivel;
import co.gov.ideam.sirh.usuarios.agua.model.UsuarioAgua;
import co.gov.ideam.sirh.util.IdeamException;

import java.sql.SQLException;

import java.util.List;

import javax.naming.NamingException;

import javax.persistence.NoResultException;

public class UsuariosAguaDelegate {
    /**
     * Referencia al EJB
     */
    private static UsuariosAguaEJB usuariosService = null;
    /**
    * Utilizada para implementar singleton
    */
    private static UsuariosAguaDelegate instance;
    /**
     * Retorna una instancia a la clase
     * @return
     */
    public static UsuariosAguaDelegate getInstance() throws IdeamException {
        if (instance==null){
            instance = new UsuariosAguaDelegate();
        }
        return instance;
    }
    /**
     * Retorna los datos de gestion de especialistas requeridos para generar
     * la grafica
     * @param idAutoridad
     * @return
     * @throws IdeamException
     */
    public String[][] getGestionEspecialistas(Long idAutoridad) throws IdeamException{
        return usuariosService.getGestionEspecialistas(idAutoridad);
    }
    /**
     * Retorna los datos de investigacion de especialistas requeridos para generar
     * la grafica
     * @param idAutoridad
     * @return
     * @throws IdeamException
     */
    public String[][] getInvestigacionEspecialistas(Long idAutoridad) throws IdeamException{
        return usuariosService.getInvestigacionEspecialistas(idAutoridad);
    }
    /**
     * Retorna los datos de formacion de especialistas requeridos para generar
     * la grafica
     * @param idAutoridad
     * @return
     * @throws IdeamException
     */
    public String[][] getFormacionEspecialistas(Long idAutoridad) throws IdeamException{
        return usuariosService.getFormacionEspecialistas(idAutoridad);
    }
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
                                                                           SQLException{
        return usuariosService.getDetalleCompletitud(condicion,
                                                     autoridad);
    }
    /**
     * Retorna una lista con todas las condiciones de error registradas en el
     * sistema
     * @return
     * @throws IdeamException
     */
    public List<CondicionErrorTO> getTotalesCondicionesError(Autoridades autoridad)throws IdeamException{
        return usuariosService.getTotalesCondicionesError(autoridad);
    }
    public List<TipoModificacionTO> getAllTiposModificacion()throws IdeamException{
        return usuariosService.getAllTiposModificacion();
    }
    /**
     * Retorna la suma del campo caudal_disegno correspondientes a las captaciones
     * asociadas al tramo recibido como parametro
     * @param tramo
     * @return
     * @throws IdeamException
     */
    public Double getDemandaTramo(FnttTramo tramo)throws IdeamException{
        return usuariosService.getDemandaTramo(tramo);
    }
    /**
     * Retorna la concesion asociada al numero de resolucion
     * @param numeroResolucion
     * @return
     * @throws IdeamException
     */
    public Concesion getConcesionByResolucion(String numeroResolucion)throws IdeamException{
        return usuariosService.getConcesionByResolucion(numeroResolucion);
    }
    /**
     * Retorna la cantidad de predios por su ubicacion
     * @return
     * @throws IdeamException
     */
    public String[][] getUbicacionUsuarios(Long idAutoridad)throws IdeamException{
        return usuariosService.getUbicacionUsuarios(idAutoridad);
    }
    
  public String[][] getUbicacionUsuariosSubt(Long idAutoridad)throws IdeamException{
      return usuariosService.getUbicacionUsuariosSubt(idAutoridad);
  }
    /**
     * Retorna una lista de los predios registrados en el sistema
     * @param criterioBusqueda Solo ser tiene en cuenta el atributo clasificacionSuelo
     * @param autoridad
     * @return
     * @throws IdeamException
     */
    public List<PrediosTO> getAllPredios(CriteriosBusquedaTO criterioBusqueda,
                                          Autoridades autoridad) throws IdeamException{
        return usuariosService.getAllPredios(criterioBusqueda, autoridad);
    }
    /**
     * Retorna la cantidad de usuarios registrados por actividad CIIU
     * @return
     * @throws IdeamException
     */
    public String[][] getUsoAguaAvtividad()throws IdeamException{
        return usuariosService.getUsoAguaAvtividad();
    }

    /**
     * Retorna una lista de permisos que contienen la cadena recibida como
     * parametro y que pertenecen a la autoridad
     * @param numeroExpediente
     * @param autoridad
     * @return
     * @throws IdeamException
     */
    public List<PermisoVertimientoTO> getAllPermisos(String numeroExpediente,String nroResol,String  fInicio,String  fFin,
                                                     Autoridades autoridad,
                                                     String estado)throws IdeamException{
        return usuariosService.getAllPermisos(numeroExpediente,nroResol,  fInicio,  fFin, autoridad, estado);
    }
    /**
     * Retorna una lista de permisos que contienen la cadena recibida
     * como parametro
     * @param numeroExpediente
     * @return
     * @throws IdeamException
     */
    public List<PermisoVertimientoTO> getAllPermisos(String numeroExpediente, String nroResol, String fInicio, String fFin, String estado)throws IdeamException{
        return usuariosService.getAllPermisos(numeroExpediente,nroResol,  fInicio,  fFin, estado);
    }

    /**
     * Retorna una lista con todos los permisos de vertimiento asociados a la
     * autoridad ambiental recibidos como parametro
     * @param autoridad
     * @return
     * @throws IdeamException
     */
    public List<PermisoVertimientoTO> getAllPermisos(Autoridades autoridad, String estado)throws IdeamException{
        return usuariosService.getAllPermisos(autoridad, estado);
    }
    /**
     * Retorna una lista con todos los permisos de vertimiento registrados
     * @return
     * @throws IdeamException
     */
    
    //dl
    
     public List<PermisoVertimientoDetalle> getDetallePermisosVertimiento(Autoridades autoridad)throws IdeamException{
         return usuariosService.getAllDetallePermisosVertimiento(autoridad);    
     }
      
  /**
   * Retorna una lista el detalle de los permisos de vertimiento registrados con vertimineto

   */
    
    public List<PermisoVertimientoTO> getAllPermisos(String estado)throws IdeamException{
        return usuariosService.getAllPermisos(estado);
    }

    /**
     * Retorna una lista con las concesiones que contienen la cadena recibida
     * por parametro
     * @param numeroExpediente
     * @return
     * @throws IdeamException
     */
    public List<ConcesionTO> getAllConcesiones(String numeroExpediente, String estado)throws IdeamException{
        return usuariosService.getAllConcesiones(numeroExpediente, estado);
    }
    /**
     * Retorna una lista con todas las concesiones asociadas a la autoridad
     * ambiental y que contienen la cadena recibida como parametro
     * @param numeroExpediente
     * @param autoridad
     * @return
     * @throws IdeamException
     */
    public List<ConcesionTO> getAllConcesiones(String numeroExpediente, String snroResolucion, String fInicio, String fFin,
                                               Autoridades autoridad,
                                               String estado)throws IdeamException{
        return usuariosService.getAllConcesiones(numeroExpediente,snroResolucion,fInicio,  fFin, autoridad,estado);
    }
    /**
     * Retorna una lista con todas las concesiones asociadas a la autoridad
     * ambiental recibida como parametro
     * @param autoridad
     * @return
     * @throws IdeamException
     */
    public List<ConcesionTO> getAllConcesiones(Autoridades autoridad)throws IdeamException{
        return usuariosService.getAllConcesiones(autoridad);
    }
    /**
     * Retorna una lista con todas las concesiones registradas en el sistema
     * @return
     * @throws IdeamException
     */
    public List<ConcesionTO> getAllConcesiones(String estado)throws IdeamException{
        return usuariosService.getAllConcesiones(estado);
    }
    /**
     * Retorna una matriz con los datos correspondientes al estado de los
     * permisos de vertimientos
     * @return
     * @throws IdeamException
     */
    public String[][] getEstadosVertimientos(Long idAutoridad)throws IdeamException{
        return usuariosService.getEstadosVertimientos( idAutoridad);
    }
    /**
     * Retorna una matriz con los datos correspondientes a los usos registrados por cada captacion
     * @return
     * @throws IdeamException
     */
    public String[][] getUsosCaptaciones(Long idAutoridad)throws IdeamException{
        return usuariosService.getUsosCaptaciones(idAutoridad);
    }
 public String[][] getUsosCaptacionesSubt(Long idAutoridad)throws IdeamException{
      return usuariosService.getUsosCaptacionesSubt(idAutoridad);
  }
    /**
     * Retorna una matriz con los datos correspondientes al estado de la concesiones
     * @return
     * @throws IdeamException
     */
    public String[][] getEstadosConcesiones(Long idAutoridad)throws IdeamException{
        return usuariosService.getEstadosConcesiones(idAutoridad);
    }
    
  public String[][] getEstadosConcesionesSubt(Long idAutoridad)throws IdeamException{
      return usuariosService.getEstadosConcesionesSubt(idAutoridad);
  }

    /**
     * Retorna una matriz con los datos correspondientes a la naturaleza
     * de los usuarios
     * @return
     * @throws IdeamException
     */
    public String[][] getNaturalezaUsuarios(Long idAutoridad)throws IdeamException{
        return usuariosService.getNaturalezaUsuarios(idAutoridad);
    }
public String[][] getNaturalezaUsuariosSubt(Long idAutoridad)throws IdeamException{
      return usuariosService.getNaturalezaUsuariosSubt(idAutoridad);
  }


    public List<CaudalesConcesionadoTO> getListaCaudalesConcesionados(Long idAutoridad) throws IdeamException {
        return usuariosService.getListaCaudalesConcesionados(idAutoridad);
    }
    
    
    public List getListaCaudalesVertidos(Long idAutoridad)throws IdeamException{
        return usuariosService.getListaCaudalesVertidos(idAutoridad);
    }
    public List getListaFuentesExternas() throws IdeamException {
        return usuariosService.getListaFuentesExternas();
    }
    
public List getListaFuentesExternasSubt() throws IdeamException {
      return usuariosService.getListaFuentesExternasSubt();
  }
    public List getListaFuentesExternas(CriteriosBusquedaExternasTO criterios) throws IdeamException {
        return usuariosService.getListaFuentesExternas(criterios);
    }
    
public List getListaFuentesExternasSubt(CriteriosBusquedaExternasTO criterios) throws IdeamException {
      return usuariosService.getListaFuentesExternasSubt(criterios);
  }
    
    
    /**
     * Retorna una matriz con los caudales concesionados por fuente, en una autoridad ambiental
     * de los usuarios
     * @return
     * @throws IdeamException
     */
    public List getListaCaudalesConcesionadosDetalle(Long idAutoridad,Long idFuente, Long codTramo)throws IdeamException{
        return usuariosService.getListaCaudalesConcesionadosDetalle(idAutoridad, idFuente, codTramo);
    }
    
    /**
     * Retorna una matriz con los caudales VERTIDOS por fuente, en una autoridad ambiental
     * de los usuarios
     * @return
     * @throws IdeamException
     */
    public List getListaCaudalesVertidosDetalle(Long idAutoridad,Long idFuente, Long codTramo)throws IdeamException{
        return usuariosService.getListaCaudalesVertidosDetalle(idAutoridad, idFuente, codTramo);
    }
    /**
     * Retorna el permiso correspondiente al numero de expediente recibida
     * @param numeroExpediente
     * @return
     * @throws IdeamException
     */
    public PermisoVertimiento getPermisoVertimiento(String numeroExpediente,
                                                    Long codigoAutoridad)throws IdeamException{
        return usuariosService.getPermisoVertimiento(numeroExpediente, codigoAutoridad);
    }

    /**
     * Retorna la concesion correspondiente al numero de expediente recibida
     * como parametro
     * @param numeroExpediente
     * @return
     * @throws IdeamException
     */
    public Concesion getConcesion(String numeroExpediente)throws IdeamException{
        return usuariosService.getConcesion(numeroExpediente);
    }
    public Concesion getConcesionByAutoridadAmbiental(String numeroExpediente, Long idAutoridad)throws IdeamException{
        return usuariosService.getConcesionByAutoridadAmbiental(numeroExpediente, idAutoridad);
    }
    
    /**
     * Retorna el predio correspondiente a la cedula catastral recibida
     * como parametro
     * @param cedulaCatastral
     * @return
     * @throws IdeamException
     */
    public Predio getPredio(String cedulaCatastral)throws IdeamException{
        return usuariosService.getPredio(cedulaCatastral);
    }

    /**
     * Retorna una lista con todos los usuarios del agua que cumplen con los
     * criterios de busqueda relacionados
     * @param criterios
     * @param codigoAutoridad
     * @return
     * @throws IdeamException
     */
    public List getAllUsuarios(CriteriosBusquedaTO criterios)throws  IdeamException{
        return usuariosService.getAllUsuarios(criterios);
    }
    
    /**
     * Retorna una lista con todos los predios cumplen con los
     * criterios de busqueda relacionados
     * @param criterios
     * @param 
     * @return
    
     */
    public List getAllPredios(CriteriosBusquedaTO criterios)throws  IdeamException{
        return usuariosService.getAllPredios(criterios);
    }
    
    
    /**
     * Retorna una lista con todos los predios cumplen con los
     * criterios de busqueda relacionados
     * @param criterios
     * @param 
     * @return
     * @throws IdeamException
     */
    public List getAllPrediosAut(CriteriosBusquedaTO criterios,
                               Long codigoAutoridad)throws  IdeamException{
        return usuariosService.getAllPrediosAut(criterios,codigoAutoridad);
    }
    
    
    /**
     * Retorna una lista con todos los usuarios del agua que cumplen con los
     * criterios de busqueda relacionados
     * @param criterios
     * @param codigoAutoridad
     * @return
     * @throws IdeamException
     */
    public List getAllUsuarios(CriteriosBusquedaTO criterios,
                               Long codigoAutoridad)throws  IdeamException{
        return usuariosService.getAllUsuarios(criterios,codigoAutoridad);
    }
    /**
     * Borra el usuario recibido como parametro
     * @param usuario
     * @throws IdeamException
     */
    public void deleteUsuario(UsuarioAgua usuario)throws IdeamException{
        usuariosService.deleteUsuario(usuario);
    }



    public List getAuditoriaUsuarios(String  fInicio,String  fFin,  Autoridades autoridad  , Lista usuarioLogin   ) throws IdeamException {
       return  usuariosService.getAuditoriaUsuarios(fInicio,  fFin,   autoridad, usuarioLogin );
       }
     
     
    public List<DatosNodosTO> getReporteNodos( Autoridades autoridad  ) throws IdeamException {
       return  usuariosService.getReporteNodos( autoridad );
       }
     
     
    public List getUsuariosLogin(Autoridades autoridad ) throws IdeamException {
    return  usuariosService.getUsuariosLogin( autoridad );
    }
    /**
     * Borra el permiso de vertimiento recibido como paranetro
     * @param permiso
     * @throws IdeamException
     */
    public void deletePermisoVertimiento(PermisoVertimiento permiso)throws IdeamException{
        usuariosService.deletePermisoVertimiento(permiso);
    }
    /**
     * Retorna el permiso de vertimiento correspondiente al codigo recibido como
     * parametro
     * @param codigo
     * @return
     * @throws IdeamException
     */
    public PermisoVertimiento getPermisoVertimiento(Long codigo)throws IdeamException{
        return usuariosService.getPermisoVertimiento(codigo);
    }

    /**
     * Registra o actualiza la informacion del permiso de vertimiento
     * recibido como parametro
     * @param permiso
     * @throws IdeamException
     */
    public PermisoVertimiento registrarPermiso(PermisoVertimiento permiso)throws IdeamException{
        return usuariosService.registrarPermiso(permiso);

    }

    public Predio registrarPredio(UsuarioAgua usu, Predio predio)throws IdeamException{
        return usuariosService.registrarPredio( usu,  predio);

    }
    /**
     * Actualiza la informacion del usuario recibido como parametro
     * @param usuario
     * @throws IdeamException
     */
    public void updateUsuario(UsuarioAgua usuario)throws IdeamException{
        usuariosService.updateUsuario(usuario);
    }

    /**
     * Borra el predio recibido como parametro y las concesiones o
     * permisos asociados al mismo
     * @param predio
     * @throws IdeamException
     */
    public void borrarPredio(Predio predio)throws IdeamException{
        usuariosService.borrarPredio(predio);
    }
    /**
     * Retorna el Predio correspondiente al codigo recibido como parametro
     * @param codigo
     * @return
     * @throws IdeamException
     */
    public Predio getPredio(Long codigo)throws IdeamException{
        return usuariosService.getPredio(codigo);
    }

    /**
     * Actualiza la informacion del predio recibido como parametro
     * @param predio
     * @param usuario
     * @throws IdeamException
     */
    public void updatePredio(UsuarioAgua usuario, Predio predio)throws IdeamException{
        usuariosService.updatePredio(usuario, predio);
    }

    /**
     * Retorna la concesion correspondiente al codigo recibido por parametro
     * @param codigo
     * @return
     * @throws IdeamException
     */
    public Concesion getConcesion(Long codigo)throws IdeamException{
        return usuariosService.getConcesion(codigo);
    }
    /**
     * Borra la concesion recibida por parametro
     * @param concesion
     * @throws IdeamException
     */
    public void deleteConcesion(Concesion concesion)throws IdeamException{
        usuariosService.deleteConcesion(concesion);
    }
    /**
     * Registra una nueva concesion en la base de datos
     * @param concesion
     * @return
     * @throws IdeamException
     */
    public Concesion registrarConcesion(Concesion concesion)throws IdeamException{
        return usuariosService.registrarConcesion(concesion);
    }

    /**
     * Retorna una lista con los permisos de vertimiento asociados al predio
     * recibido como parametro
     * @param predio
     * @return
     * @throws IdeamException
     */
    public List<PermisoVertimiento> getPermisosVertimiento(Predio predio)throws IdeamException{
        return usuariosService.getPermisosVertimiento(predio);
    }
    /**
     * Retorna una lista con las captaciones ilegles de un predio//Pilar
     * recibido como parametro
     * @param predio
     * @return
     * @throws IdeamException
     */
    public List<Captacion> getCaptacionesSinConcesion(Predio predio)throws IdeamException{//Pilar
        return usuariosService.getCaptacionesSinConcesion(predio);
    }


    /**
     * Retorna una lista con los predios asociados al predio recibido como
     * parametro
     * @param predio
     * @return
     * @throws IdeamException
     */
    public List<Concesion> getConcesiones(Predio predio)throws IdeamException{
        return usuariosService.getConcesiones(predio);
    }


    /**
        * Retorna una lista con las captaciones asociados a una concesion recibido como
        * parametro
        * @param concoesion
        * @return
        * @throws IdeamException
        */
       public List<Captacion> getCaptaciones(Concesion concesion)throws IdeamException{
           return usuariosService.getCaptaciones(concesion);
       }
    /**
     * Retorna una lista con todos los predios asociados al usuario recibido
     * como parametro
     * @param usuario
     * @return
   
     */
    public List<Predio> getPredios(UsuarioAgua usuario)throws IdeamException{
        return usuariosService.getPredios(usuario);
    }

    /**
     * Retorna una lista con los usuarios registrados
     * recibida como parametro
     * @return
     * @throws IdeamException
     */
    public List getAllUsuarios()throws IdeamException{
        return usuariosService.getAllUsuarios();
    }
    /**
     * Retorna una lista con los usuarios del agua correspondientes a la autoridad
     * recibida como parametro
     * @param codigoAutoridad
     * @return
     * @throws IdeamException
     */
    public List getAllUsuarios(Long codigoAutoridad)throws IdeamException{
        return usuariosService.getAllUsuarios(codigoAutoridad);
    }

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
                                  Long codigoAutoridad)throws IdeamException{
        return usuariosService.getUsuario(codigoTipoId,
                                          numeroId,
                                          codigoAutoridad);
    }
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
                                       Autoridades autoridad)throws IdeamException{
        Object retorno [] = usuariosService.registrarUsuarioPredio(usuario,
                                                                    predio,
                                                                    representanteLegal,
                                                                    autoridad);
        return retorno;
    }

    public UsuarioAgua getUsuarioAgua(Long codigo)throws IdeamException{
        return usuariosService.getUSuario(codigo);
    }

    public void updateUsuarioAgua(UsuarioAgua usuario, Autoridades autoridad)throws IdeamException{
        usuariosService.updateUsuario(usuario, autoridad);
    }

    /**
     * Inserta una nueva captación en la base de datos
     * @param captacion
     * @return Captacion
     * @throws IdeamException
     */
    public Captacion createCaptacion(Captacion captacion)throws IdeamException{
        return usuariosService.createCaptacion(captacion);
    }

    /**
     * Actualiza una captación en la base de datos
     * @param captacion
     * @return Captacion
     * @throws IdeamException
     */
    public Captacion updateCaptacion(Captacion captacion)throws IdeamException{
        return usuariosService.updateCaptacion(captacion);
    }

    /**
     * Retorna una captación  correspondiente al codigo recibido como parametro
     * @param id
     * @return
     * @throws IdeamException
     */
    public Captacion getCaptacion(Long id)throws IdeamException{
        return usuariosService.getCaptacion(id);
    }

    /**
     * Elimina de la bd la captación recibida.
     * @param captacion
     * @return
     * @throws IdeamException
     */
    public void deleteCaptacion(Captacion captacion)throws IdeamException{
        usuariosService.deleteCaptacion(captacion);
    }

    /**
     * Retorna una captación  correspondiente al codigo recibido como parametro,
     * asociando los componentes de la captación
     * @param id
     * @return
     * @throws IdeamException
     */
    public Captacion getCaptacionWithComponentes(Long id)throws IdeamException, NoResultException{
        return usuariosService.getCaptacionWithComponentes(id);
    }

    /**
     * Retorna la lista de componentes de la captacion,
     * @param id
     * @return
     * @throws IdeamException
     */
    public List getComponentesByCaptacion(Long id)throws IdeamException, NoResultException{
        return usuariosService.getComponentesByCaptacion(id);
    }

    /**
     * Retorna una lista con las captaciones correspondientes a la autoridad y
     * concesión recibidos como parametro.
     * @param concesion
     * @param autoridad
     * @return
     * @throws IdeamException
     */
    public List<Captacion> getCaptacionesByAutoridadConcesion(Concesion concesion, Autoridades autoridad)throws IdeamException{
        return usuariosService.getCaptacionesByAutoridadConcesion(concesion, autoridad);
    }

    /**
     * Retorna una lista con las captaciones que cumplan con los filtros.
     * @param criterios
     * @return
     * @throws IdeamException
     */
    public List<CaptacionTO> getAllCaptacionesBusqueda(CriteriosBusquedaTO criterios)throws IdeamException{
        return usuariosService.getAllCaptacionesBusqueda(criterios);
    }
  // dl
    
  public List<CaptacionDetalle> getCaptacionDetalleUsos(Autoridades autoridad)throws IdeamException{
    return usuariosService.getAllCaptacionDetalle(autoridad);
  }
 
  // dl
  
    
    public List<CaptacionSubTO> getAllCaptacionesBusquedaSub(CriteriosBusquedaTO criterios)throws IdeamException{
        return usuariosService.getAllCaptacionesBusquedaSub(criterios);
    }

    /**
     * Retorna una lista con los puntos de vertimiento asociados a un permiso
     * recibido como parametro.
     * @param permiso
     * @return
     * @throws IdeamException
     */
    public List<PuntoVertimiento> getVertimientos(PermisoVertimiento permiso)throws IdeamException{
        return usuariosService.getVertimientos(permiso);
    }

    /**
     * Inserta un nuevo vertimiento en la base de datos
     * @param vertimiento
     * @return PuntoVertimiento
     * @throws IdeamException
     */
    public PuntoVertimiento createVertimiento(PuntoVertimiento vertimiento)throws IdeamException{
        return usuariosService.createVertimiento(vertimiento);
    }

    /**
     * Actualiza un vertimiento en la base de datos
     * @param vertimiento
     * @return PuntoVertimiento
     * @throws IdeamException
     */
    public PuntoVertimiento updateVertimiento(PuntoVertimiento vertimiento)throws IdeamException{
        return usuariosService.updateVertimiento(vertimiento);
    }

    /**
     * Retorna un vertimiento correspondiente al codigo recibido como parametro
     * @param id
     * @return PuntoVertimiento
     * @throws IdeamException
     */
    public PuntoVertimiento getVertimiento(Long id)throws IdeamException{
        return usuariosService.getVertimiento(id);
    }

    /**
     * Elimina de la bd el vertimiento recibido.
     * @param vertimiento
     * @return
     * @throws IdeamException
     */
    public void deleteVertimiento(PuntoVertimiento vertimiento)throws IdeamException{
        usuariosService.deleteVertimiento(vertimiento);
    }

    /**
     * Retorna una lista con los puntos de vertimiento que cumplan con los filtros.
     * @param criterios
     * @return
     * @throws IdeamException
     */
    public List<PuntoVertimientoTO> getAllVertimientosBusqueda(CriteriosBusquedaTO criterios)throws IdeamException{
        return usuariosService.getAllVertimientosBusqueda(criterios);
    }

    /**
     * Inserta un componente para captación en la base de datos
     * @param captacionComponente
     * @return RurtCaptacionComponentes
     * @throws IdeamException
     */
    public RurtCaptacionComponentes createCaptacionComponente(RurtCaptacionComponentes captacionComponente)throws IdeamException{
        return usuariosService.createCaptacionComponente(captacionComponente);
    }

    /**
     * Elimina de la bd el componente de la captacion recibido.
     * @param captacionComponente
     * @return
     * @throws IdeamException
     */
    public void deleteCaptacionComponente(RurtCaptacionComponentes captacionComponente)throws IdeamException{
        usuariosService.deleteCaptacionComponente(captacionComponente);
    }

    /**
     * Inserta un tratamiento para un punto de vertimiento en la base de datos
     * @param puntoTratamiento
     * @return PuntoVertimientoTratamiento
     * @throws IdeamException
     */
    public PuntoVertimientoTratamiento createPuntoTratamiento(PuntoVertimientoTratamiento puntoTratamiento)throws IdeamException{
        return usuariosService.createPuntoTratamiento(puntoTratamiento);
    }

    /**
     * Elimina de la bd el tratamiento del punto de vertimiento recibido.
     * @param puntoTratamiento
     * @throws IdeamException
     */
    public void deletePuntoTratamiento(PuntoVertimientoTratamiento puntoTratamiento)throws IdeamException{
        usuariosService.deletePuntoTratamiento(puntoTratamiento);
    }

    /**
     * Retorna una lista con los tratamiento asociados a un punto de vertimiento.
     * @param idPunto
     * @param idCategoria
     * @return
     * @throws IdeamException
     */
    public List getTratamientosByPuntoVertimientoCategoria(Long idPunto, Long idCategoria)throws IdeamException, NoResultException{
        return usuariosService.getTratamientosByPuntoVertimientoCategoria(idPunto, idCategoria);
    }

    /**
     * Retorna una lista con los usos asociados a una captacion
     * recibido como parametro.
     * @param captacion
     * @return
     * @throws IdeamException
     */
    public List<RurtCaptacionUso> getUsos(Captacion captacion)throws IdeamException{
        return usuariosService.getUsos(captacion);
    }

    /**
     * Retorna un uso correspondiente al codigo recibido como parametro
     * @param id
     * @return
     * @throws IdeamException
     */
    public RurtCaptacionUso getUso(Long id)throws IdeamException{
        return usuariosService.getUso(id);
    }


    /**
     * Inserta un nuevo uso en la base de datos
     * @param uso
     * @return RurtCaptacionUso
     * @throws IdeamException
     */
    public RurtCaptacionUso createUso(RurtCaptacionUso uso)throws IdeamException{
        return usuariosService.createUso(uso);
    }

    /**
     * Actualiza un uso en la base de datos
     * @param uso
     * @return RurtCaptacionUso
     * @throws IdeamException
     */
    public RurtCaptacionUso updateUso(RurtCaptacionUso uso)throws IdeamException{
        return usuariosService.updateUso(uso);
    }

    /**
     * Elimina de la bd el uso recibido.
     * @param uso
     * @return
     * @throws IdeamException
     */
    public void deleteUso(RurtCaptacionUso uso)throws IdeamException{
         usuariosService.deleteUso(uso);;
    }


    /**
     * Retorna el predio correspondiente a la cedula catastral y codigo de usuario
     * recibidos como parametro.
     * @param cedulaCatastral
     * @param codigoUsuario
     * @return
     * @throws IdeamException
     */
    public PrediosTO getPredioTO(String cedulaCatastral, String codigoUsuario, Long codigoAutoridad)throws IdeamException{
        return usuariosService.getPredioTO(cedulaCatastral, codigoUsuario, codigoAutoridad);
    }
    
    /**
     * Retorna el predio correspondiente a la cedula catastral y codigo de usuario
     * recibidos como parametro.
     * @param cedulaCatastral
     * @param codigoUsuario
     * @return
     * @throws IdeamException
     */
    public Predio getPredio(String cedulaCatastral, Long codigoUsuario, Long codigoAutoridad)throws IdeamException{
        return usuariosService.getPredio(cedulaCatastral, codigoUsuario, codigoAutoridad);
    }

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
            Long codigoAutoridad) throws IdeamException{

        return usuariosService.getConcesionByExpedienteResolucionPredio(
            numeroExpediente, numeroResolucion, codigoPredio, codigoAutoridad);
    }
    
    
    public PermisoVertimientoTO getVertimientoByExpedienteResolucionPredio(
            String numeroExpediente, String numeroResolucion, Long codigoPredio,
            Long codigoAutoridad) throws IdeamException{

                return usuariosService.getVertimientoByExpedienteResolucionPredio(
                    numeroExpediente, numeroResolucion, codigoPredio, codigoAutoridad);
                
            }

    /**
     * Retorna la captacion correspondiente a los parametros recbidos.
     * @param captacion
     * @param codigoAutoridad
     * @return
     * @throws IdeamException
     */
    public CaptacionTO getCaptacion(Captacion captacion,
                                    Long codigoAutoridad) throws IdeamException{
        return usuariosService.getCaptacion(captacion, codigoAutoridad);
    }

    /**
     * Retorna el componente en la captacion correspondiente a los
     * parametros recibidos.
     * @param idComponente
     * @param idCaptacion
     * @return
     * @throws IdeamException
     */
    public RurtCaptacionComponentes getComponenteCaptacion(Long idComponente,
                                                           Long idCaptacion) throws IdeamException{
        return usuariosService.getComponenteCaptacion(idComponente, idCaptacion);
    }

    /**
     * Retorna el vertimiento correspondiente a los parametros recbidos.
     * @param vertimiento
     * @param codigoAutoridad
     * @return
     * @throws IdeamException
     */
    public PuntoVertimientoTO getVertimiento(PuntoVertimiento vertimiento,
                                    Long codigoAutoridad) throws IdeamException{
        return usuariosService.getVertimiento(vertimiento, codigoAutoridad);
    }

    /**
     * Retorna el tratmiento en el punto de vertimiento correspondiente a los
     * parametros recibidos.
     * @param tratamientoPunto
     * @return
     * @throws IdeamException
     */
    public PuntoVertimientoTratamiento getTratamientoPunto(PuntoVertimientoTratamiento
                                                           tratamientoPunto) throws IdeamException{
        return usuariosService.getTratamientoPunto(tratamientoPunto);
    }


    /**
     * Retorna un listado de captaciones de una fuente especifica.
     * @param idFuente
     * @return
     * @throws IdeamException
     */
    public List getCaptacionByFuente(Long idFuente) throws IdeamException{
        return usuariosService.getCaptacionByFuente(idFuente);
    }

    /**
     * Retorna un listado de captaciones de un tramo especifico.
     * @param idTramo
     * @return
     * @throws IdeamException
     */
    public List getCaptacionByTramo(Long idTramo) throws IdeamException{
        return usuariosService.getCaptacionByTramo(idTramo);
    }

    /**
     * Retorna un listado de vertimientos de una fuente especifica.
     * @param idFuente
     * @return
     * @throws IdeamException
     */
    public List getVertimientoByFuente(Long idFuente) throws IdeamException{
        return usuariosService.getVertimientoByFuente(idFuente);
    }

    /**
     * Retorna un listado de vertimientos de un tramo especifico.
     * @param idTramo
     * @return
     * @throws IdeamException
     */
    public List getVertimientoByTramo(Long idTramo) throws IdeamException{
        return usuariosService.getVertimientoByTramo(idTramo);
    }

    /**
     * Borra la concesion recibido como parametro y las captaciones asociadas.
     * @param concesion
     * @throws IdeamException
     */
    public void borrarConcesion(Concesion concesion)throws IdeamException{
        usuariosService.borrarConcesion(concesion);
    }

    /**
     * Borra el permiso recibido como parametro y los puntos de vertimiento asociadas.
     * @param permiso
     * @throws IdeamException
     */
     public void borrarPermiso(PermisoVertimiento permiso) throws IdeamException{
        usuariosService.borrarPermiso(permiso);
    }

    /**
     * Borra la captacion recibida como parametro y todo lo asociado a ella.
     * @param captacion
     * @throws IdeamException
     */
    public void borrarCaptacion(Captacion captacion) throws IdeamException{
        usuariosService.borrarCaptacion(captacion);
    }

    /**
     * Borra el vertimiento recibido como parametro y los asociado a el.
     * @param punto
     * @throws IdeamException
     */
     public void borrarVertimiento(PuntoVertimiento punto) throws IdeamException{
        usuariosService.borrarVertimiento(punto);
    }

    /**
     * Retorna la lista de funias asociados a una captacion.
     * @param captacion
     * @return
     * @throws IdeamException
     */
    public List<SubttFunias> getFuniasByCaptacion(Captacion captacion) throws IdeamException{
        return usuariosService.getFuniasByCaptacion(captacion);
    }

	/**
	     * Retorna el funias de la captacion y el tipo correspondiente.
	     * @param idCaptacion
	     * @param tipoFunias
	     * @return
	     * @throws IdeamException
	     */
	    public SubttFunias getFuniasByCaptacionTipoFunias(Long idCaptacion,
	                                                  Integer tipoFunias) throws IdeamException{
	        return usuariosService.getFuniasByCaptacionTipoFunias(idCaptacion, tipoFunias);
    }

    /**
     * Retorna un punto funias.
     * @param idFunias
     * @return
     * @throws IdeamException
     */
    public SubttFunias getFunias(Long idFunias) throws IdeamException{
        return usuariosService.getFunias(idFunias);
    }

    /**
     * Crea o persiste el funias recibido.
     * @param funias
     * @return
     * @throws IdeamException
     */
    public SubttFunias createFunias(SubttFunias funias) throws IdeamException{
        return usuariosService.createFunias(funias);
    }

    /**
     * Actualiza el funias recibido.
     * @param funias
     * @return
     * @throws IdeamException
     */
    public SubttFunias updateFunias(SubttFunias funias) throws IdeamException{
        return usuariosService.updateFunias(funias);
    }

    /**
     * Borra el funias recibido como parametro.
     * @param funias
     * @return
     * @throws IdeamException
     */
    public void deleteFunias(SubttFunias funias) throws IdeamException{
        usuariosService.deleteFunias(funias);
    }

    public List getAnioConcesiones(Long idAutoridad )throws IdeamException{
        return usuariosService.getAnioConcesiones(idAutoridad);
    }
    public List getAnioPermisos(Long idAutoridad )throws IdeamException{
        return usuariosService.getAnioPermisos(idAutoridad);
    }
    
    public String[][] getConcesionesAnio( Integer anio, Long idAutoridad) throws IdeamException{
    return usuariosService.getConcesionesAnio( anio, idAutoridad);
    }
    public String[][] getPermisosAnio( Integer anio, Long idAutoridad) throws IdeamException{
    return usuariosService.getPermisosAnio( anio, idAutoridad);
    }
    
    /**
     * Retorna todos los archivos de un funias.
     * @param idFunias
     * @return
     * @throws IdeamException
     */
    public List getFilesByFunias(Long idFunias) throws IdeamException{
        return usuariosService.getFilesByFunias(idFunias);
    }





    /**
    * Construcutor privado para implementar singleton
    */
    private UsuariosAguaDelegate() throws IdeamException {
        usuariosService = ServletLocator.getUsuariosAguaService();
    }
    /**
     * Retorna relacion de captaciones por fuente
     * @return
     * @throws IdeamException
     */
    public String[][] getCaptacionesFuentes(Long idAutoridad) throws IdeamException{
        return usuariosService.getCaptacionesFuentes(idAutoridad);
    }
  public String[][] getCaptacionesFuentesSubt(Long idAutoridad) throws IdeamException{
      return usuariosService.getCaptacionesFuentesSubt(idAutoridad);
  }


    public String[][] getVertimientosFuentes(Long idAutoridad) throws IdeamException{
        return usuariosService.getVertimientosFuentes(idAutoridad);
    }
    
    
    public String[][] getPrediosPorDepto(Long idAutoridad) throws IdeamException{
        return usuariosService.getPrediosPorDepto(idAutoridad);
    }
    
 public String[][] getPrediosPorDeptoSubt(Long idAutoridad) throws IdeamException{
      return usuariosService.getPrediosPorDeptoSubt(idAutoridad);
  }
    public String[][] getPrediosPorMcpio(Long idAutoridad) throws IdeamException{
        return usuariosService.getPrediosPorMcpio(idAutoridad);
    }
public String[][] getPrediosPorMcpioSubt(Long idAutoridad) throws IdeamException{
      return usuariosService.getPrediosPorMcpio(idAutoridad);
  }

    public String[][] getCaptacionesTipoFuente(Long idAutoridad) throws IdeamException{
        return usuariosService.getCaptacionesTipoFuente(idAutoridad);
    }
    
    public String[][] getVertimientosTipo(Long idAutoridad) throws IdeamException{
        return usuariosService.getVertimientosTipo(idAutoridad);
    }
    public String[][] getCaptacionesTipoFuenteDet(Long idAutoridad) throws IdeamException{
        return usuariosService.getCaptacionesTipoFuenteDet(idAutoridad);
    }

     /**
      * Crea o persiste un nivel asociado a un funias.
      * @param nivel
      * @return
      * @throws IdeamException
      */
     public SubttFuniasNivel createFuniasNivel(SubttFuniasNivel nivel) throws IdeamException{
        return usuariosService.createFuniasNivel(nivel);
    }
     
    /**
     * Consulta un  nivel por codigo.
     * @param idNivel
     * @return
     * @throws IdeamException
     */
    public SubttFuniasNivel getNivel(Long idNivel) throws IdeamException{
        return usuariosService.getNivel(idNivel);
    }
    
    /**
     * Consulta todos los niveles asociados a un funias.
     * @param funias
     * @return
     * @throws IdeamException
     */
    public List<SubttFuniasNivel> getNivelByFunias(SubttFunias funias) throws IdeamException{
        return usuariosService.getNivelByFunias(funias);
    }
    
    /**
     * Borra el nivel recibido como parametro.
     * @param nivel
     * @throws IdeamException
     */
    public void deleteNivel(SubttFuniasNivel nivel) throws IdeamException{
        usuariosService.deleteNivel(nivel);
    }
    
    /**
     * Actualiza el nivel recibido como parametro.
     * @param nivel
     * @return
     * @throws IdeamException
     */
    public SubttFuniasNivel updateNivel(SubttFuniasNivel nivel) throws IdeamException{
        return usuariosService.updateNivel(nivel);
    }

  //CDONCEL
  public void updateAforo(RurtCapAforo aforo)throws IdeamException{
    //UsuariosAguaBean usr = new UsuariosAguaBean();
     //usr.updateAforo(aforo);
    System.out.println("Aforo Delegte: " + aforo.getFecha());
   usuariosService.updateAforo(aforo);
  }
    
  /**
  * Obtener listado de aforos.
  * @param captacion
  * @return List
  * @throws IdeamException
  */
  public List<RurtCapAforo> getLsAforos(Captacion capt) throws IdeamException{
    //UsuariosAguaBean usr = new UsuariosAguaBean();
    //return usr.getLsAforos(capt);
    return usuariosService.getLsAforos(capt);
    } 
  
   public void borrarAforo(String id)throws IdeamException{
      usuariosService.borrarAforo( id);
   }
   
   public PuntoVertimiento getVertimientobyPerm(PermisoVertimiento permiso) throws IdeamException{
      return usuariosService.getVertimientobyPerm(permiso);      
   }
   
   
  //FIN CDONCEL 
  




    /**
     * Registra el usuario y predio recibidos como parametro para integracion RUA
     * @param usuario
     * @param predio
     * @param representanteLegal
     * @param autoridad
     * @throws IdeamException
     */
    
     public Long registrarUsuarioPredioRUACO(UsuarioAguaSinc usuario,
                                          RepresentanteSinc representanteLegal,
                                          PredioSinc predio, ConcesionSinc concesion
                                          )throws IdeamException{
        Long idusuario= usuariosService.registrarUsuarioPredioRUACO(usuario, representanteLegal, predio, concesion);
        return idusuario;
    }
    
    public Long registrarUsuarioPredioRUAPV(UsuarioAguaSinc usuario,
                                         RepresentanteSinc representanteLegal,
                                         PredioSinc predio, 
                                         PermisoVertimientoSinc permisoVert)throws IdeamException{
       Long idusuario= usuariosService.registrarUsuarioPredioRUAPV(usuario, representanteLegal, predio,permisoVert);
       return idusuario;
    }

}
