package co.gov.ideam.sirh.archivos.model;

import co.gov.ideam.sirh.fuentes.business.FuentesEJB;
import co.gov.ideam.sirh.fuentes.model.FnttFuente;
import co.gov.ideam.sirh.fuentes.model.FnttTramo;
import co.gov.ideam.sirh.parametros.business.ParametrosEJB;
import co.gov.ideam.sirh.parametros.model.Divipola;
import co.gov.ideam.sirh.parametros.model.Lista;
import co.gov.ideam.sirh.usuarios.agua.model.PermisoVertimiento;
import co.gov.ideam.sirh.usuarios.agua.model.Predio;
import co.gov.ideam.sirh.usuarios.agua.model.PuntoVertimiento;
import co.gov.ideam.sirh.usuarios.agua.model.PuntoVertimientoTratamiento;
import co.gov.ideam.sirh.usuarios.agua.model.Representante;
import co.gov.ideam.sirh.usuarios.agua.model.UsuarioAgua;
import co.gov.ideam.sirh.util.ConstantesParametros;
import co.gov.ideam.sirh.util.IdeamException;

public class UsuariosAguaPermisosServiciosConverter extends ModelConverter{
    public UsuariosAguaPermisosServiciosConverter() {
        super();
    }

    public Object getModel(Object model)throws IdeamException {
        if(model instanceof UsuarioAgua){
            return this.getUsuarioAgua( (UsuarioAgua)model );
        }else if(model instanceof Predio){
            return this.getPredio( (Predio)model );
        }else if(model instanceof PermisoVertimiento){
            return this.getPermisoVertimiento((PermisoVertimiento)model);
        }else if(model instanceof Representante){
            return this.getRepresentante((Representante)model);
        }else if(model instanceof PuntoVertimiento){
            return this.getVertimiento((PuntoVertimiento)model);
        }          
        return null;
    }
    
    public Object getModel(Object model, Object aux)throws IdeamException {
       if(model instanceof String){
            if(model.toString().equalsIgnoreCase("PRETRATAMIENTO")){
                return this.getPretratamiento((PuntoVertimiento)aux);   
            }else if(model.toString().equalsIgnoreCase("PRIMARIO")){
                return this.getPrimario((PuntoVertimiento)aux);   
            }else if(model.toString().equalsIgnoreCase("SECUNDARIO")){
                return this.getSecundario((PuntoVertimiento)aux);   
            }else if(model.toString().equalsIgnoreCase("TERCIARIO")){
                return this.getTerciario((PuntoVertimiento)aux);   
            }else if(model.toString().equalsIgnoreCase("OTRO")){
                return this.getOtro((PuntoVertimiento)aux);   
            }
        }         
        return null;
    }
    /**
     * Retorna un Permioso de vertimiento con los datos registrados en el objeto
     * RowTO
     * @param permiso
     * @return
     * @throws IdeamException
     */
    private PermisoVertimiento getPermisoVertimiento(PermisoVertimiento permiso)throws IdeamException{
        
        permiso.setResolucionInicioTramite( this.row.getCellValue("Acto Administrativo").toString() );
                
        String fechaExpResolucion = 
                this.row.getCellValue("Fecha Expedicion").toString();
        permiso.setFechaExpedicionInicioTramite( this.getDate(fechaExpResolucion) );
        
        permiso.setNumeroExpediente( this.row.getCellValue("Numero Expediente").toString() );

        String tmp=this.row.getCellValue("Caudal").toString();
        if(tmp.indexOf(",")!=-1)
        tmp=tmp.replaceAll(",",".");
        permiso.setCaudal( Double.parseDouble( tmp ) );

        permiso.setEvaluacionAmbiental( this.row.getCellValue("Evaluacion Ambiental").toString() );        
        
        permiso.setResolucionSolicitudPlanCumplimiento(
            this.row.getCellValue("Acto Administrativo PC").toString());
        
        String fechaExpPC = this.row.getCellValue("Fecha Expedicion PC").toString();
        permiso.setFechaExpedicionPlan( this.getDate( fechaExpPC ) );
        
        permiso.setResolucionAprobacionPlan( this.row.getCellValue("Acto Administrativo Aprobacion PC").toString() );
        
        String fechaExpAprobPC = this.row.getCellValue("Fecha Expedicion Aprobacion PC").toString();
        permiso.setFechaExpedicionAprobacionPlan( this.getDate(fechaExpAprobPC) );
        
        String fechaInicioPC = this.row.getCellValue("Fecha Inicial Plan Cumplimiento").toString();
        permiso.setFechaInicoPlan( this.getDate(fechaInicioPC) );
        
        String fechaFinPC = this.row.getCellValue("Fecha Final Plan Cumplimiento").toString();
        permiso.setFechaFinPlan( this.getDate( fechaFinPC ) );
        
        permiso.setResolucionPermisoVertimiento( this.row.getCellValue("Resolucion Permiso Vertimiento").toString() );
        
        String fechaExpPV = this.row.getCellValue("Fecha Expedicion Permiso Vertimiento").toString();
        permiso.setFechaExpedicionPermiso( this.getDate( fechaExpPV ) );
        
        String fechaInicioPV = this.row.getCellValue("Fecha Inicial Permiso Vertimiento").toString();
        permiso.setFechaInicio(this.getDate(fechaInicioPV));
        
        String fechaFinPV = this.row.getCellValue("Fecha Final Permiso Vertimiento").toString();
        permiso.setFechaFin( this.getDate( fechaFinPV ) );
        
        permiso.setResolucionAprobacionPlanos( this.row.getCellValue("Resolucion Aprobacion Planos").toString() );
        
        String fechaExpPlanos = this.row.getCellValue("Fecha Expedicion Aprobacion Planos").toString();
        permiso.setFechaExpedicionPlanos( this.getDate(fechaExpPlanos) );
        
        permiso.setResolucionAprobacionObras( this.row.getCellValue("Resolucion Aprobacion Obras").toString() );
        
        String fechaExpObras = this.row.getCellValue("Fecha Expedicion Aprobacion Obras").toString();
        permiso.setFechaExpedicionObras( this.getDate( fechaExpObras ) );
        
        String fechaNotObras = this.row.getCellValue("Fecha Notificacion Aprobacion Obras").toString();
        permiso.setFechaNotificacionObrasPsmv( this.getDate(fechaNotObras) );
        
        permiso.setResolucionPsmv( this.row.getCellValue("Acto Administrativo PSMV").toString() );
        
        String fechaInicioPsmv = this.row.getCellValue("Fecha Inicio PSMV").toString();
        permiso.setFechaInicioPsmv( this.getDate( fechaInicioPsmv ) );
        
        String fechaFinPsmv = this.row.getCellValue("Fecha Inicio PSMV").toString();
        permiso.setFechaFinPsmv( this.getDate( fechaFinPsmv ) );
        
        permiso.setResolucionPlanosPsmv( this.row.getCellValue("Resolucion Planos PSMV").toString() );
        
        String fechaExpPlanosPsmv = this.row.getCellValue("Fecha Expedicion Planos PSMV").toString();
        permiso.setFechaResolucionPlanosPsmv( this.getDate( fechaExpPlanosPsmv ) );
        
        permiso.setResolucionObrasPsmv( this.row.getCellValue("Resolucion Obras PSMV").toString() );
        
        String fechaExpObrasPsmv = this.row.getCellValue("Fecha Expedicion Obras PSMV").toString();
        permiso.setFechaResolucionObrasPsmv( this.getDate( fechaExpObrasPsmv ) );        
        
        String fechaNotObrasPsmv = this.row.getCellValue("Fecha Notificacion Obras PSMV").toString();
        permiso.setFechaNotificacionObrasPsmv( this.getDate( fechaNotObrasPsmv ) );
        
        permiso.setObervacionesPsmv( this.row.getCellValue("Observaciones").toString() );
        return permiso;
    }  
    
    private Representante getRepresentante(Representante representante)throws IdeamException{
        ParametrosEJB parametrosService = this.getParametrosService();
        
        representante.setNombres( this.row.getCellValue("Nombre Representante").toString() );
        representante.setApellidos( this.row.getCellValue("Apellido Representante").toString() );
        
        String tipoDocTxt = this.row.getCellValue("Tipo Documento Representante").toString();
        Lista tipoDocumento = 
            parametrosService.getListaByDescripcion(tipoDocTxt, 2L);
        representante.setTipoDocumento( tipoDocumento );
        
        representante.setNumeroDocumento( this.row.getCellValue("Numero Documento Representante").toString() );
        
        String nombreDeptoTxt = this.row.getCellValue("Departamento Representante").toString();
        Divipola depto = 
            parametrosService.getDivipolaByName(nombreDeptoTxt,"DEP");
        representante.setDepartamento(depto);
        representante.setCodigoDepartamento( depto.getId() );
        
        String nombreMunTxt = this.row.getCellValue("Municipio Representante").toString();
        Divipola mun = 
            parametrosService.getDivipolaByName(nombreMunTxt, depto);
        representante.setMunicipio(mun);
        representante.setCodigoMunicipio( mun.getId() );
        
        representante.setDireccion( this.row.getCellValue("Direccion Representante").toString() );        
        representante.setTelefono( this.row.getCellValue("Telefono Representante").toString() );
        
        return null;
    }

    /**
     * Retorna un predio con base en los datos encapsulados en el objeto RowTO
     * @param predio
     * @return
     * @throws IdeamException
     */
    private Predio getPredio(Predio predio)throws IdeamException{
        ParametrosEJB parametrosService = this.getParametrosService();
        
        predio.setNombre( row.getCellValue("Nombre Predio").toString() );
        
        String tipoTenenciaTxt = row.getCellValue("Tipo Tenencia").toString();
        Lista tipoTenecia = parametrosService.getListaByDescripcion(
                           tipoTenenciaTxt, 3L);
        predio.setCodigoTipoTenencia( tipoTenecia.getCodigo().longValue() );
     
        String nombreDeptoTxt = this.row.getCellValue("Departamento Predio").toString();
        Divipola depto = 
            parametrosService.getDivipolaByName(nombreDeptoTxt,"DEP");
        predio.setDepartamento(depto);
        predio.setCodigoDepartamento( depto.getId() );
        
        String nombreMunTxt = this.row.getCellValue("Municipio Predio").toString();
        Divipola mun = 
            parametrosService.getDivipolaByName(nombreMunTxt, depto);
        predio.setMunicipio(mun);
        predio.setCodigoMunicipio( mun.getId() );
        
        String tipoCentroPobladoTxt = this.row.getCellValue("Tipo Centro Poblado").toString();
        Lista tipoCentroPoblado = 
            parametrosService.getListaByDescripcion(tipoCentroPobladoTxt, 4L);
        predio.setTipoCentroPoblado( tipoCentroPoblado );
        
        predio.setNombreCentroPoblado( this.row.getCellValue("Nombre Centro Poblado").toString() );        
        
        String cedulaCatastral =  this.row.getCellValue("Cedula Catastral").toString();
        predio.setCedulaCatastral((cedulaCatastral==null) 
                                  ? "" : cedulaCatastral);
        
        predio.setMatriculaCatastral( this.row.getCellValue("Matricula Catastral").toString() );                
        predio.setDireccion( this.row.getCellValue("Direccion Predio").toString() );
        if(this.row.getCellValue("Area")!=null &&
           this.row.getCellValue("Area").toString().length()>0){
            Double areaPredio = Double.parseDouble( 
                                     this.row.getCellValue("Area").toString());
            predio.setArea( areaPredio );
        }        
        
        String clasificacionTxt = this.row.getCellValue("Clasificacion Suelo").toString();
        Lista clasificacion = 
            parametrosService.getListaByDescripcion(clasificacionTxt, 5L);
        predio.setTipoSuelo(clasificacion);
        predio.setCodigoTipoSuelo( clasificacion.getCodigo().longValue() );
        
        String sistemaReferenciaTxt = this.row.getCellValue("Sistema Referencia").toString();
        Lista sistemaReferencia = parametrosService.getListaByDescripcion(
                                      sistemaReferenciaTxt, 6L);
        predio.setSistemaReferencia(sistemaReferencia);
        
        Double gradosLatitud = Double.parseDouble( 
                                 this.row.getCellValue("Grados Latitud").toString());
        predio.setGradosLatitud(gradosLatitud.intValue());
        
        Double minutosLatitud = Double.parseDouble(
                                  this.row.getCellValue("Minutos Latitud").toString());
        predio.setMinutosLatitud(minutosLatitud.intValue());
        
        Double segundosLatitud = Double.parseDouble(
                                     this.row.getCellValue("Segundos Latitud").toString());
        predio.setSegundosLatitud(segundosLatitud);
        
        
        Double gradosLongitud = Double.parseDouble( 
                                 this.row.getCellValue("Grados Longitud").toString());
        predio.setGradosLongitud(gradosLongitud.intValue());
        
        Double minutosLongitud = Double.parseDouble(
                                  this.row.getCellValue("Minutos Longitud").toString());
        predio.setMinutosLongitud(minutosLongitud.intValue());
        
        Double segundosLongitud = Double.parseDouble(
                                     this.row.getCellValue("Segundos Longitud").toString());
        predio.setSegundosLongitud(segundosLongitud);
        
        Double altitud = Double.parseDouble(
                                    this.row.getCellValue("Altitud").toString());
        predio.setAltitud( altitud );
                
        return null;
    }
    /**
     * Retorna un usuarioAgua con base en los datos encapsulados en el 
     * objeto RowTO
     * @param usuario
     * @return
     * @throws IdeamException
     */
    private UsuarioAgua getUsuarioAgua(UsuarioAgua usuario)throws IdeamException{
        ParametrosEJB parametrosService = this.getParametrosService();
        
        String tipoPersonaTxt = this.row.getCellValue("Tipo Persona").toString();
        Lista tipoPersona = 
            parametrosService.getListaByDescripcion(tipoPersonaTxt, 1L);
        usuario.setTipoUsuario( tipoPersona );
        
        usuario.setNombre( this.row.getCellValue("Razon Social").toString() );
                
        String tipoDocTxt = this.row.getCellValue("Tipo Documento").toString();
        Lista tipoDocumento = 
            parametrosService.getListaByDescripcion(tipoDocTxt, 2L);
        usuario.setTipoDocumento( tipoDocumento );
        
        usuario.setNumeroDocumento( this.row.getCellValue("Numero Documento").toString() );
                
        usuario.setDireccion( this.row.getCellValue("Direccion").toString() );
        usuario.setEmail( this.row.getCellValue("Email").toString() );
        usuario.setTelefono( this.row.getCellValue("Telefono").toString() );
        
        return null;
    }
    
    
    /**
     * Retorna un vertimiento con los datos registrados en RowTO
     * @param vertimiento
     * @return
     * @throws IdeamException
     */
    private PuntoVertimiento getVertimiento(PuntoVertimiento vertimiento)throws IdeamException{
        ParametrosEJB parametrosService = this.getParametrosService();
        FuentesEJB fuenteService = this.getFuentesService();
        
        String tipoVertimiento = this.row.getCellValue("Tipo Vertimiento").toString();
        Lista tipo = parametrosService.getListaByDescripcion(tipoVertimiento, 
                                                    ConstantesParametros.
                                                     CATEGORIA_TIPO_VERTIMIENTO);;
        vertimiento.setTipoVertimiento(tipo.getCodigo());
        
        String nombreDeptoTxt = this.row.getCellValue("Departamento Vertimiento").toString();
        Divipola depto = 
            parametrosService.getDivipolaByName(nombreDeptoTxt,"DEP");
        vertimiento.setIdDepartamento(depto.getId().intValue());
        
        String nombreMunTxt = this.row.getCellValue("Municipio Vertimiento").toString();
        Divipola mun = 
            parametrosService.getDivipolaByName(nombreMunTxt,depto);
        vertimiento.setIdMunicipio(mun.getId().intValue());

        String tipoCentroPobladoTxt = this.row.getCellValue("Tipo centro Vertimiento").toString();
        Lista tipoCentroPoblado = 
            parametrosService.getListaByDescripcion(tipoCentroPobladoTxt, 4L);
        vertimiento.setIdTipoCentroPoblado( tipoCentroPoblado.getCodigo() );
        
        vertimiento.setNombreCentroPoblado(this.row.getCellValue("Nombre centro Vertimiento").toString());
        
        String tipoFuente = this.row.getCellValue("Tipo Fuente").toString(); 
        Lista listaTipoFuente = parametrosService.getListaByDescripcion(tipoFuente.trim(), 
                                                    ConstantesParametros.
                                                     CATEGORIA_TIPO_FUENTE);
        
        String fuenteNombre = this.row.getCellValue("Fuente").toString(); 
        FnttFuente fuente = fuenteService.getFuente(fuenteNombre.toUpperCase().trim(), 
                                                    vertimiento.getAutoridad(), 
                                                    listaTipoFuente.getCodigo());
        
        vertimiento.setIdFuente(fuente);
        
        String tramoNombre = this.row.getCellValue("Tramo").toString();         
        FnttTramo tramo = fuenteService.getTramo(tramoNombre.toUpperCase().trim(), 
                                                 fuente, vertimiento.getAutoridad());
        vertimiento.setIdTramo(tramo);
        
        String nombreTipoFlujo = this.row.getCellValue("Tipo Flujo").toString();
        Lista tipoFlujo = parametrosService.getListaByDescripcion(nombreTipoFlujo, 
                                                    ConstantesParametros.
                                                     CATEGORIA_TIPO_FLUJO_VERTIMIENTO);;
        vertimiento.setTipoFlujo(tipoFlujo.getCodigo());
        
        
        String tiempoDescarga = this.row.getCellValue("Tiempo Descarga").toString();
        tiempoDescarga = replaceFloatingSeparator(tiempoDescarga);
        vertimiento.setTiempoDescarga((tiempoDescarga!=null)
                                      ?Double.parseDouble(tiempoDescarga) : null);
        
        String frecuencia = this.row.getCellValue("Frecuencia").toString();
        frecuencia = replaceFloatingSeparator(frecuencia);
        vertimiento.setFrecuencia((frecuencia!=null)
                                  ?new Double(frecuencia).intValue() :null);
        
        String caudal = this.row.getCellValue("Caudal Descarga").toString();
        caudal = replaceFloatingSeparator(caudal);
        vertimiento.setCaudalDisegno((caudal!=null) ?new Double(caudal) :null);
        
        
        String nombreSistemaReferencia = this.row.getCellValue("Sistema Referencia").toString();
        Lista sistemaReferencia = parametrosService.getListaByDescripcion(
                                      nombreSistemaReferencia, ConstantesParametros.
                                                            CATEGORIA_SISTEMA_REFERENCIA);
        vertimiento.setSistemaReferencia(sistemaReferencia.getCodigo());
        
        Double gradosLatitud = Double.parseDouble( 
                                 this.row.getCellValue("Grados Latitud").toString());
        vertimiento.setGradosLat(gradosLatitud.intValue());
        
        Double minutosLatitud = Double.parseDouble(
                                  this.row.getCellValue("Minutos Latitud").toString());
        vertimiento.setMinutosLat(minutosLatitud.intValue());
        
        Double segundosLatitud = Double.parseDouble(
                                     this.row.getCellValue("Segundos Latitud").toString());
        vertimiento.setSegundosLat(segundosLatitud);
        
        
        Double gradosLongitud = Double.parseDouble( 
                                 this.row.getCellValue("Grados Longitud").toString());
        vertimiento.setGradosLong(gradosLongitud.intValue());
        
        Double minutosLongitud = Double.parseDouble(
                                  this.row.getCellValue("Minutos Longitud").toString());
        vertimiento.setMinutosLong(minutosLongitud.intValue());
        
        Double segundosLongitud = Double.parseDouble(
                                     this.row.getCellValue("Segundos Longitud").toString());
        vertimiento.setSegundosLong(segundosLongitud);
        
        Double altitud = Double.parseDouble(
                                    this.row.getCellValue("Altitud").toString());
        vertimiento.setAltitud( altitud );
        
        vertimiento.setDescripcionAcceso(this.row.getCellValue("Descripcion").toString());
        
        
        return vertimiento;
    }
    
    /**
     * Retorna eltratamiento de un vertimiento con los datos registrados en RowTO
     * @param vertimiento
     * @return
     * @throws IdeamException
     */
    private PuntoVertimientoTratamiento getPretratamiento(PuntoVertimiento vertimiento)throws IdeamException{
        ParametrosEJB parametrosService = this.getParametrosService();
        PuntoVertimientoTratamiento tratamiento = new PuntoVertimientoTratamiento();

        String nombreTratamiento = this.row.getCellValue("Pretratamiento").toString();
        
        if(nombreTratamiento==null || nombreTratamiento.trim().equalsIgnoreCase("")){
            tratamiento = null;
        }else{
            Lista tipoTratamiento = 
            parametrosService.getListaByDescripcion(nombreTratamiento, 
                                                    ConstantesParametros.
                                                     CATEGORIA_PRETRATAMIENTO);
            tratamiento.setIdTratamiento(tipoTratamiento.getCodigo());
            tratamiento.setIdCategoria(ConstantesParametros.CATEGORIA_PRETRATAMIENTO.intValue());
        }
        return tratamiento;
    }
    
    /**
     * Retorna el tratamiento de un vertimiento con los datos registrados en RowTO
     * @param vertimiento
     * @return
     * @throws IdeamException
     */
    private PuntoVertimientoTratamiento getPrimario(PuntoVertimiento vertimiento)throws IdeamException{
        ParametrosEJB parametrosService = this.getParametrosService();
        PuntoVertimientoTratamiento tratamiento = new PuntoVertimientoTratamiento();

        String nombreTratamiento = this.row.getCellValue("Primario").toString();
        
        if(nombreTratamiento==null || nombreTratamiento.trim().equalsIgnoreCase("")){
            tratamiento = null;
        }else{
            Lista tipoTratamiento = 
            parametrosService.getListaByDescripcion(nombreTratamiento, 
                                                    ConstantesParametros.
                                                     CATEGORIA_TRATAMIENTO_PRIMARIO);
            tratamiento.setIdTratamiento(tipoTratamiento.getCodigo());
            tratamiento.setIdCategoria(ConstantesParametros.CATEGORIA_TRATAMIENTO_PRIMARIO.intValue());
        }
        return tratamiento;
    }
    
    /**
     * Retorna el tratamiento de un vertimiento con los datos registrados en RowTO
     * @param vertimiento
     * @return
     * @throws IdeamException
     */
    private PuntoVertimientoTratamiento getSecundario(PuntoVertimiento vertimiento)throws IdeamException{
        ParametrosEJB parametrosService = this.getParametrosService();
        PuntoVertimientoTratamiento tratamiento = new PuntoVertimientoTratamiento();

        String nombreTratamiento = this.row.getCellValue("Secundario").toString();
        
        if(nombreTratamiento==null || nombreTratamiento.trim().equalsIgnoreCase("")){
            tratamiento = null;
        }else{
            Lista tipoTratamiento = 
            parametrosService.getListaByDescripcion(nombreTratamiento, 
                                                    ConstantesParametros.
                                                     CATEGORIA_TRATAMIENTO_SECUNDARIO);
            tratamiento.setIdTratamiento(tipoTratamiento.getCodigo());
            tratamiento.setIdCategoria(ConstantesParametros.CATEGORIA_TRATAMIENTO_SECUNDARIO.intValue());
        }
        return tratamiento;
    }
    
    /**
     * Retorna el tratamiento de un vertimiento con los datos registrados en RowTO
     * @param vertimiento
     * @return
     * @throws IdeamException
     */
    private PuntoVertimientoTratamiento getTerciario(PuntoVertimiento vertimiento)throws IdeamException{
        ParametrosEJB parametrosService = this.getParametrosService();
        PuntoVertimientoTratamiento tratamiento = new PuntoVertimientoTratamiento();

        String nombreTratamiento = this.row.getCellValue("Terciario").toString();
        
        if(nombreTratamiento==null || nombreTratamiento.trim().equalsIgnoreCase("")){
            tratamiento = null;
        }else{
            Lista tipoTratamiento = 
            parametrosService.getListaByDescripcion(nombreTratamiento, 
                                                    ConstantesParametros.
                                                     CATEGORIA_TRATAMIENTO_TERCIARIO);
            tratamiento.setIdTratamiento(tipoTratamiento.getCodigo());
            tratamiento.setIdCategoria(ConstantesParametros.CATEGORIA_TRATAMIENTO_TERCIARIO.intValue());
        }
        return tratamiento;
    }
    
    /**
     * Retorna el tratamiento de un vertimiento con los datos registrados en RowTO
     * @param vertimiento
     * @return
     * @throws IdeamException
     */
    private PuntoVertimientoTratamiento getOtro(PuntoVertimiento vertimiento)throws IdeamException{
        ParametrosEJB parametrosService = this.getParametrosService();
        PuntoVertimientoTratamiento tratamiento = new PuntoVertimientoTratamiento();

        String nombreTratamiento = this.row.getCellValue("Otros").toString();
        
        if(nombreTratamiento==null || nombreTratamiento.trim().equalsIgnoreCase("")){
            tratamiento = null;
        }else{
            Lista tipoTratamiento = 
            parametrosService.getListaByDescripcion(nombreTratamiento, 
                                                    ConstantesParametros.
                                                     CATEGORIA_TRATAMIENTO_OTRO);
            tratamiento.setIdTratamiento(tipoTratamiento.getCodigo());
            tratamiento.setIdCategoria(ConstantesParametros.CATEGORIA_TRATAMIENTO_OTRO.intValue());
        }
        return tratamiento;
    }
    
    private String replaceFloatingSeparator(String tmp){
        if(tmp!=null && !tmp.trim().equalsIgnoreCase("")){
            if(tmp.indexOf(",")!=-1)
            tmp = tmp.replaceAll(",",".");
        }else{
            tmp=null;
        }
        return tmp;
    }    
}


