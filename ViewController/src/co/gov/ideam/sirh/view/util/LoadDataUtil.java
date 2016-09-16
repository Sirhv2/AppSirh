package co.gov.ideam.sirh.view.util;

import co.gov.ideam.sirh.archivos.model.ModelConverter;
import co.gov.ideam.sirh.archivos.model.RowTO;
import co.gov.ideam.sirh.archivos.model.UsariosAguaCaptacionesNaturalConverter;
import co.gov.ideam.sirh.archivos.model.UsuariosAguaCaptacionesJuridicaConverter;
import co.gov.ideam.sirh.archivos.model.UsuariosAguaCaptacionesServiciosConverter;
import co.gov.ideam.sirh.archivos.model.UsuariosAguaPermisosJuridicaConverter;
import co.gov.ideam.sirh.archivos.model.UsuariosAguaPermisosNaturalConverter;
import co.gov.ideam.sirh.archivos.model.UsuariosAguaPermisosServiciosConverter;
import co.gov.ideam.sirh.calidad.model.Medicion;
import co.gov.ideam.sirh.calidad.model.Muestra;
import co.gov.ideam.sirh.calidad.model.PuntoMonitoreo;
import co.gov.ideam.sirh.calidad.view.CalidadDelegate;
import co.gov.ideam.sirh.datos.referencia.model.DemtEmpresa;
import co.gov.ideam.sirh.datos.referencia.model.DemtTarifa;
import co.gov.ideam.sirh.datos.referencia.view.DatosReferenciaDelegate;
import co.gov.ideam.sirh.fuentes.model.FnttFuente;
import co.gov.ideam.sirh.fuentes.model.FnttTramo;
import co.gov.ideam.sirh.fuentes.view.FuenteDelegate;
import co.gov.ideam.sirh.parametros.model.Autoridades;
import co.gov.ideam.sirh.parametros.model.Lista;
import co.gov.ideam.sirh.parametros.view.ParametrosDelegate;
import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;
import co.gov.ideam.sirh.usuarios.agua.model.Captacion;
import co.gov.ideam.sirh.usuarios.agua.model.CaptacionTO;
import co.gov.ideam.sirh.usuarios.agua.model.Concesion;
import co.gov.ideam.sirh.usuarios.agua.model.ConcesionTO;
import co.gov.ideam.sirh.usuarios.agua.model.PermisoVertimiento;
import co.gov.ideam.sirh.usuarios.agua.model.PermisoVertimientoTO;
import co.gov.ideam.sirh.usuarios.agua.model.Predio;
import co.gov.ideam.sirh.usuarios.agua.model.PrediosTO;
import co.gov.ideam.sirh.usuarios.agua.model.PuntoVertimiento;
import co.gov.ideam.sirh.usuarios.agua.model.PuntoVertimientoTO;
import co.gov.ideam.sirh.usuarios.agua.model.PuntoVertimientoTratamiento;
import co.gov.ideam.sirh.usuarios.agua.model.Representante;
import co.gov.ideam.sirh.usuarios.agua.model.RurtCaptacionComponentes;
import co.gov.ideam.sirh.usuarios.agua.model.RurtCaptacionUso;
import co.gov.ideam.sirh.usuarios.agua.model.SubttFunias;
import co.gov.ideam.sirh.usuarios.agua.model.UsuarioAgua;
import co.gov.ideam.sirh.usuarios.agua.view.UsuariosAguaDelegate;
import co.gov.ideam.sirh.util.ConstantesParametros;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.FacesUtils;

import java.math.BigDecimal;

import java.text.DateFormat;

import java.util.Date;
import java.util.List;

public class LoadDataUtil {
    
    public static int registrarUsuarioAguaPermisosServicios(ModelConverter converter, 
                                                            RowTO fila, Autoridades autoridad)throws IdeamException{
        boolean usuarioRegistrado = false;
        int registrosCargados = 0;
        
        UsuariosAguaDelegate uad = UsuariosAguaDelegate.getInstance();

        converter.setRow( fila );

        UsuarioAgua usuario = new UsuarioAgua();
        converter.getModel(usuario);
        
        Predio predio = new Predio();
        converter.getModel(predio);

        // Determinar si el usuario ya se encuentra registrado
        UsuarioAgua usuarioExiste = uad.getUsuario(usuario.getTipoDocumento().
                                                    getCodigo(),
                                                   usuario.getNumeroDocumento(),
                                                   autoridad.getId().longValue());
        Representante representante = new Representante();
        converter.getModel(representante);

        if(usuarioExiste!=null){
            usuario = usuarioExiste;
            usuarioRegistrado = true;
            
            PrediosTO predioExiste = uad.getPredioTO(predio.getCedulaCatastral(), 
                                                    usuario.getNumeroDocumento(), 
                                                    autoridad.getId().longValue());
            if(predioExiste!=null){
                predio = uad.getPredio(predioExiste.getCodigo());   
            }     
        }

        if(!usuarioRegistrado){
            usuario.setDepartamento( predio.getDepartamento() );
            usuario.setMunicipio( predio.getMunicipio() );
        }

        Object retorno[] = uad.registrarUsuarioPredio(usuario,
                                   predio,
                                   representante,
                                   autoridad);
        PermisoVertimiento permiso = new PermisoVertimiento();
        converter.getModel(permiso);

        permiso.setCodigoAutoridadAmbiental(autoridad.getId().longValue() );
        permiso.setCodigoPredio((Long)retorno[1]);
        
        PermisoVertimientoTO permisoExiste = 
            uad.getVertimientoByExpedienteResolucionPredio(permiso.getNumeroExpediente(), 
                                permiso.getResolucionPermisoVertimiento(),
                                predio.getCodigo(),
                                autoridad.getId().longValue());
        if(permisoExiste!=null){
            permiso = uad.getPermisoVertimiento(permisoExiste.getCodigo());
        }
        
        permiso = uad.registrarPermiso(permiso);
        
        //EDUIN
        //proceso para el punto de vertimiento
        PuntoVertimiento vertimiento = new PuntoVertimiento ();
        vertimiento.setAutoridad(autoridad);
        vertimiento.setCodigoAutoridad(autoridad.getId().longValue());
        vertimiento.setIdPermisoVertimiento(permiso);
        vertimiento = (PuntoVertimiento)converter.getModel(vertimiento);
        
        //verificar que no exista
        PuntoVertimientoTO vertimientoExiste = uad.getVertimiento(vertimiento, 
                                                        autoridad.getId().longValue());
        if(vertimientoExiste!=null){
            vertimiento = uad.getVertimiento(vertimientoExiste.getId());
            //actualizar
            vertimiento =  uad.updateVertimiento(vertimiento);
        }else{
            //registrar
            vertimiento = uad.createVertimiento(vertimiento);
        }
        
        vertimiento.setCodigoAutoridad(autoridad.getId().longValue());
        
        //TRATAMIENTOS
        //pretratamiento
        PuntoVertimientoTratamiento pretratamiento = 
            (PuntoVertimientoTratamiento)((UsuariosAguaPermisosServiciosConverter)converter).
                                                getModel("PRETRATAMIENTO", vertimiento);
        if(pretratamiento!=null){
            pretratamiento.setCodigoAutoridad(autoridad.getId().longValue());
            pretratamiento.setIdPuntoVertimiento(vertimiento.getId());
            //validar si no se repite el tratamiento
            PuntoVertimientoTratamiento existe = uad.getTratamientoPunto(pretratamiento);
            if(existe==null){
                uad.createPuntoTratamiento(pretratamiento);
            }
        }
        
        //primario
        PuntoVertimientoTratamiento primario = 
            (PuntoVertimientoTratamiento)((UsuariosAguaPermisosServiciosConverter)converter).
                                                getModel("PRIMARIO", vertimiento);
        if(primario!=null){
            primario.setCodigoAutoridad(autoridad.getId().longValue());
            primario.setIdPuntoVertimiento(vertimiento.getId());
            //validar si no se repite el tratamiento
            PuntoVertimientoTratamiento existe = uad.getTratamientoPunto(primario);
            if(existe==null){
                uad.createPuntoTratamiento(primario);
            }
        }
        
        //secundario
        PuntoVertimientoTratamiento secundario = 
            (PuntoVertimientoTratamiento)((UsuariosAguaPermisosServiciosConverter)converter).
                                                getModel("SECUNDARIO", vertimiento);
        if(secundario!=null){
            secundario.setCodigoAutoridad(autoridad.getId().longValue());
            secundario.setIdPuntoVertimiento(vertimiento.getId());
            //validar si no se repite el tratamiento
            PuntoVertimientoTratamiento existe = uad.getTratamientoPunto(secundario);
            if(existe==null){
                uad.createPuntoTratamiento(secundario);
            }
        }
        
        //terciario
        PuntoVertimientoTratamiento terciario = 
            (PuntoVertimientoTratamiento)((UsuariosAguaPermisosServiciosConverter)converter).
                                                getModel("TERCIARIO", vertimiento);
        if(terciario!=null){
            terciario.setCodigoAutoridad(autoridad.getId().longValue());
            terciario.setIdPuntoVertimiento(vertimiento.getId());
            //validar si no se repite el tratamiento
            PuntoVertimientoTratamiento existe = uad.getTratamientoPunto(terciario);
            if(existe==null){
                uad.createPuntoTratamiento(terciario);
            }
        }
        
        //otro
        PuntoVertimientoTratamiento otro = 
            (PuntoVertimientoTratamiento)((UsuariosAguaPermisosServiciosConverter)converter).
                                                getModel("OTRO", vertimiento);
        if(otro!=null){
            otro.setCodigoAutoridad(autoridad.getId().longValue());
            otro.setIdPuntoVertimiento(vertimiento.getId());
            //validar si no se repite el tratamiento
            PuntoVertimientoTratamiento existe = uad.getTratamientoPunto(otro);
            if(existe==null){
                uad.createPuntoTratamiento(otro);
            }
        }        
      
        return ++registrosCargados; 
    }
    
    public static int registrarUsuarioAguaCaptacionesServicios(ModelConverter converter, 
                                                               RowTO fila, 
                                                               Autoridades autoridad)throws IdeamException{
        boolean usuarioRegistrado = false;
        int registrosCargados = 0;

        UsuariosAguaDelegate uad = UsuariosAguaDelegate.getInstance();

        converter.setRow( fila );

        UsuarioAgua usuario = new UsuarioAgua();
        converter.getModel(usuario);       
        
        Predio predio = new Predio();
        converter.getModel(predio);
        
        // Determinar si el usuario ya se encuentra registrado
        UsuarioAgua usuarioExiste = uad.getUsuario(usuario.getTipoDocumento().
                                                    getCodigo(),
                                                   usuario.getNumeroDocumento(),
                                                   autoridad.getId().longValue());
                
        Representante reresentante = new Representante();
        converter.getModel(reresentante);

        if(usuarioExiste!=null){
            usuario = usuarioExiste;
            usuarioRegistrado = true;
            
            PrediosTO predioExiste = uad.getPredioTO(predio.getCedulaCatastral(), 
                                                    usuario.getNumeroDocumento(),  
                                                    autoridad.getId().longValue());
            if(predioExiste!=null){
                predio = uad.getPredio(predioExiste.getCodigo());   
            }               
        }


        if(!usuarioRegistrado){
            usuario.setDepartamento( predio.getDepartamento() );
            usuario.setMunicipio( predio.getMunicipio() );
        }

        Object retorno[] = uad.registrarUsuarioPredio(usuario,
                                   predio,
                                   reresentante,
                                   autoridad);
        Concesion concesion = new Concesion();
        concesion = (Concesion)converter.getModel(concesion);

        concesion.setCodigoAutoridadAmbiental(autoridad.getId().longValue() );
        concesion.setCodigoPredio((Long)retorno[1]);
        
        //toca validar si  la concesion existe
        ConcesionTO concesionExiste = 
            uad.getConcesionByExpedienteResolucionPredio(concesion.getNumeroExpediente(),
                                                         concesion.getResolucionCaudal(),
                                                         predio.getCodigo(),
                                                         autoridad.getId().longValue());
        if(concesionExiste!=null){
            concesion = uad.getConcesion(concesionExiste.getCodigo());
        }
        
        concesion = uad.registrarConcesion(concesion);
        //Eduin
        //proceso para la captacion
        System.out.println("Autoridad:"+ autoridad.getId().longValue());
        System.out.println("Concesion que se asigna a la nueva captacion:"+concesion.getCodigo());
        Captacion captacion = new Captacion ();
        captacion.setAutoridad(autoridad);
        captacion.setCodigoAutoridad(autoridad.getId().longValue());
        captacion.setIdConcesion(concesion);
        captacion = (Captacion)converter.getModel(captacion);
        //verificar que no exista
        System.out.println(" captacion grados lat:"+captacion.getGradosLat());
        CaptacionTO captacionExiste = uad.getCaptacion(captacion, 
                                                        autoridad.getId().longValue());
        if(captacionExiste!=null){
            captacion = uad.getCaptacion(captacionExiste.getId());
            //actualizar
            captacion =  uad.updateCaptacion(captacion);
        }else{
            //registrar
            captacion = uad.createCaptacion(captacion);
        }
        
        captacion.setAutoridad(autoridad);
        captacion.setCodigoAutoridad(autoridad.getId().longValue());
        //proceso para los componentes
        List<RurtCaptacionComponentes> componentes = 
            (List<RurtCaptacionComponentes>)((UsuariosAguaCaptacionesServiciosConverter)converter).getModel("COMPONENTES", captacion);
        for( RurtCaptacionComponentes componente : componentes){
            //consultar si ya existe el componente para la captacion.
            RurtCaptacionComponentes captacionComponente = 
                uad.getComponenteCaptacion(componente.getId(), captacion.getCodigo());
            if(captacionComponente==null){//si no existe la relación
                uad.createCaptacionComponente(componente);//registrar
            }
        }
        
        //proceso para los usos
        RurtCaptacionUso usoDomestico = 
            (RurtCaptacionUso)((UsuariosAguaCaptacionesServiciosConverter)converter).
                                                getModel("USO_DOMESTICO", captacion);
        if(usoDomestico!=null){
            usoDomestico.setCodigoAutoridad(autoridad.getId().longValue());
            usoDomestico.setIdCaptacion(captacion.getCodigo());
            //validar si no se repite el uso?
            uad.createUso(usoDomestico);
        }
        
        RurtCaptacionUso usoAgricola = 
            (RurtCaptacionUso)((UsuariosAguaCaptacionesServiciosConverter)converter).
                                                getModel("USO_AGRICOLA", captacion);
        if(usoAgricola!=null){
            usoAgricola.setCodigoAutoridad(autoridad.getId().longValue());
            usoAgricola.setIdCaptacion(captacion.getCodigo());
            //validar si no se repite el uso?
            uad.createUso(usoAgricola);
        }
        
        RurtCaptacionUso usoPecuario = 
            (RurtCaptacionUso)((UsuariosAguaCaptacionesServiciosConverter)converter).
                                                getModel("USO_PECUARIO", captacion);
        if(usoPecuario!=null){
            usoPecuario.setCodigoAutoridad(autoridad.getId().longValue());
            usoPecuario.setIdCaptacion(captacion.getCodigo());
            //validar si no se repite el uso?
            uad.createUso(usoPecuario);
        }
        
        RurtCaptacionUso usoAcuicola = 
            (RurtCaptacionUso)((UsuariosAguaCaptacionesServiciosConverter)converter).
                                                getModel("USO_ACUICOLA", captacion);
        if(usoAcuicola!=null){
            usoAcuicola.setCodigoAutoridad(autoridad.getId().longValue());
            usoAcuicola.setIdCaptacion(captacion.getCodigo());
            //validar si no se repite el uso?
            uad.createUso(usoAcuicola);
        }
        
        RurtCaptacionUso usoOtro = 
            (RurtCaptacionUso)((UsuariosAguaCaptacionesServiciosConverter)converter).
                                                getModel("USO_OTRO", captacion);
        if(usoOtro!=null){
            usoOtro.setCodigoAutoridad(autoridad.getId().longValue());
            usoOtro.setIdCaptacion(captacion.getCodigo());
            //validar si no se repite el uso?
            uad.createUso(usoOtro);
        }

        return ++registrosCargados;
    }
    
    public static int registrarUsuarioAguaPermisosJuridica(ModelConverter converter, 
                                                           RowTO fila, 
                                                           Autoridades autoridad)throws IdeamException{
        boolean usuarioRegistrado = false;
        int registrosCargados = 0;

        UsuariosAguaDelegate uad = UsuariosAguaDelegate.getInstance();

        converter.setRow( fila );

        UsuarioAgua usuario = new UsuarioAgua();
        converter.getModel(usuario);
        
        Predio predio = new Predio();
        converter.getModel(predio);

        // Determinar si el usuario ya se encuentra registrado
        String numDoc=quitarPuntoCero(usuario.getNumeroDocumento());
        UsuarioAgua usuarioExiste = uad.getUsuario(usuario.getTipoDocumento().
                                                    getCodigo(),
                                                   numDoc,
                                                   autoridad.getId().longValue());
        Representante representante = new Representante();
        converter.getModel(representante);

        if(usuarioExiste!=null){
            usuario = usuarioExiste;
            usuarioRegistrado = true;
            
            PrediosTO predioExiste = uad.getPredioTO(predio.getCedulaCatastral(), 
                                                    usuario.getNumeroDocumento(), 
                                                    autoridad.getId().longValue());
            if(predioExiste!=null){
                predio = uad.getPredio(predioExiste.getCodigo());   
            }     
        }

        

        if(!usuarioRegistrado){
            usuario.setDepartamento( predio.getDepartamento() );
            usuario.setMunicipio( predio.getMunicipio() );
        }
        
        
        Object retorno[] = uad.registrarUsuarioPredio(usuario,
                                   predio,
                                   representante,
                                   autoridad);
        PermisoVertimiento permiso = new PermisoVertimiento();
        converter.getModel(permiso);

        permiso.setCodigoAutoridadAmbiental(autoridad.getId().longValue() );
        permiso.setCodigoPredio((Long)retorno[1]);
    
        PermisoVertimientoTO permisoExiste = 
            uad.getVertimientoByExpedienteResolucionPredio(permiso.getNumeroExpediente(), 
                                permiso.getResolucionPermisoVertimiento(),
                                predio.getCodigo(), autoridad.getId().longValue());
        if(permisoExiste!=null){
            permiso = uad.getPermisoVertimiento(permisoExiste.getCodigo());
        }
        
        permiso = uad.registrarPermiso(permiso);
        
        
        //EDUIN
        //proceso para el punto de vertimiento
        PuntoVertimiento vertimiento = new PuntoVertimiento ();
        vertimiento.setAutoridad(autoridad);
        vertimiento.setCodigoAutoridad(autoridad.getId().longValue());
        vertimiento.setIdPermisoVertimiento(permiso);
        vertimiento = (PuntoVertimiento)converter.getModel(vertimiento);
        
        //verificar que no exista
        PuntoVertimientoTO vertimientoExiste = uad.getVertimiento(vertimiento, 
                                                        autoridad.getId().longValue());
        if(vertimientoExiste!=null){
            vertimiento = uad.getVertimiento(vertimientoExiste.getId());
            //actualizar
            vertimiento =  uad.updateVertimiento(vertimiento);
        }else{
            //registrar
            vertimiento = uad.createVertimiento(vertimiento);
        }
        
        vertimiento.setCodigoAutoridad(autoridad.getId().longValue());
        
        //TRATAMIENTOS
        //pretratamiento
        PuntoVertimientoTratamiento pretratamiento = 
            (PuntoVertimientoTratamiento)((UsuariosAguaPermisosJuridicaConverter)converter).
                                                getModel("PRETRATAMIENTO", vertimiento);
        if(pretratamiento!=null){
            pretratamiento.setCodigoAutoridad(autoridad.getId().longValue());
            pretratamiento.setIdPuntoVertimiento(vertimiento.getId());
            //validar si no se repite el tratamiento
            PuntoVertimientoTratamiento existe = uad.getTratamientoPunto(pretratamiento);
            if(existe==null){
                uad.createPuntoTratamiento(pretratamiento);
            }
        }
        
        //primario
        PuntoVertimientoTratamiento primario = 
            (PuntoVertimientoTratamiento)((UsuariosAguaPermisosJuridicaConverter)converter).
                                                getModel("PRIMARIO", vertimiento);
        if(primario!=null){
            primario.setCodigoAutoridad(autoridad.getId().longValue());
            primario.setIdPuntoVertimiento(vertimiento.getId());
            //validar si no se repite el tratamiento
            PuntoVertimientoTratamiento existe = uad.getTratamientoPunto(primario);
            if(existe==null){
                uad.createPuntoTratamiento(primario);
            }
        }
        
        //secundario
        PuntoVertimientoTratamiento secundario = 
            (PuntoVertimientoTratamiento)((UsuariosAguaPermisosJuridicaConverter)converter).
                                                getModel("SECUNDARIO", vertimiento);
        if(secundario!=null){
            secundario.setCodigoAutoridad(autoridad.getId().longValue());
            secundario.setIdPuntoVertimiento(vertimiento.getId());
            //validar si no se repite el tratamiento
            PuntoVertimientoTratamiento existe = uad.getTratamientoPunto(secundario);
            if(existe==null){
                uad.createPuntoTratamiento(secundario);
            }
        }
        
        //terciario
        PuntoVertimientoTratamiento terciario = 
            (PuntoVertimientoTratamiento)((UsuariosAguaPermisosJuridicaConverter)converter).
                                                getModel("TERCIARIO", vertimiento);
        if(terciario!=null){
            terciario.setCodigoAutoridad(autoridad.getId().longValue());
            terciario.setIdPuntoVertimiento(vertimiento.getId());
            //validar si no se repite el tratamiento
            PuntoVertimientoTratamiento existe = uad.getTratamientoPunto(terciario);
            if(existe==null){
                uad.createPuntoTratamiento(terciario);
            }
        }
        
        //otro
        PuntoVertimientoTratamiento otro = 
            (PuntoVertimientoTratamiento)((UsuariosAguaPermisosJuridicaConverter)converter).
                                                getModel("OTRO", vertimiento);
        if(otro!=null){
            otro.setCodigoAutoridad(autoridad.getId().longValue());
            otro.setIdPuntoVertimiento(vertimiento.getId());
            //validar si no se repite el tratamiento
            PuntoVertimientoTratamiento existe = uad.getTratamientoPunto(otro);
            if(existe==null){
                uad.createPuntoTratamiento(otro);
            }
        }        
        return ++registrosCargados;
    }
    
    public static int registrarUsuarioAguaPermisosNatural(ModelConverter converter, 
                                                          RowTO fila, 
                                                          Autoridades autoridad)throws IdeamException{
        
        int registrosCargados = 0;

        UsuariosAguaDelegate uad = UsuariosAguaDelegate.getInstance();

        converter.setRow( fila );

        UsuarioAgua usuario = new UsuarioAgua();
        converter.getModel(usuario);
        
        Predio predio = new Predio();
        converter.getModel(predio);

        // Determinar si el usuario ya se encuentra registrado
        String numDoc=quitarPuntoCero(usuario.getNumeroDocumento());
        UsuarioAgua usuarioExiste = uad.getUsuario(usuario.getTipoDocumento().
                                                    getCodigo(),
                                                   numDoc,
                                                   autoridad.getId().longValue());
        if(usuarioExiste!=null){
            usuario = usuarioExiste;
            
            PrediosTO predioExiste = uad.getPredioTO(predio.getCedulaCatastral(), 
                                                    usuario.getNumeroDocumento(),  
                                                    autoridad.getId().longValue());
            if(predioExiste!=null){
                predio = uad.getPredio(predioExiste.getCodigo());   
            }     
        }

        Object retorno[] = uad.registrarUsuarioPredio(usuario,
                                   predio,
                                   null,
                                   autoridad);
        PermisoVertimiento permiso = new PermisoVertimiento();
        converter.getModel(permiso);

        permiso.setCodigoAutoridadAmbiental(
            autoridad.getId().longValue() );
        permiso.setCodigoPredio((Long)retorno[1]);

        //validar si el permiso existe.
        PermisoVertimientoTO permisoExiste = 
            uad.getVertimientoByExpedienteResolucionPredio(permiso.getNumeroExpediente(), 
                                permiso.getResolucionPermisoVertimiento(),
                                predio.getCodigo(),
                                autoridad.getId().longValue());
        if(permisoExiste!=null){
            permiso = uad.getPermisoVertimiento(permisoExiste.getCodigo());
        }
        
        permiso = uad.registrarPermiso(permiso);
        
        //EDUIN
        //proceso para el punto de vertimiento
        PuntoVertimiento vertimiento = new PuntoVertimiento ();
        vertimiento.setAutoridad(autoridad);
        vertimiento.setCodigoAutoridad(autoridad.getId().longValue());
        vertimiento.setIdPermisoVertimiento(permiso);
        vertimiento = (PuntoVertimiento)converter.getModel(vertimiento);
        
        //verificar que no exista
        PuntoVertimientoTO vertimientoExiste = uad.getVertimiento(vertimiento, 
                                                        autoridad.getId().longValue());
        if(vertimientoExiste!=null){
            vertimiento = uad.getVertimiento(vertimientoExiste.getId());
            //actualizar
            vertimiento =  uad.updateVertimiento(vertimiento);
        }else{
            //registrar
            vertimiento = uad.createVertimiento(vertimiento);
        }
        
        vertimiento.setCodigoAutoridad(autoridad.getId().longValue());
        
        //TRATAMIENTOS
        //pretratamiento
        PuntoVertimientoTratamiento pretratamiento = 
            (PuntoVertimientoTratamiento)((UsuariosAguaPermisosNaturalConverter)converter).
                                                getModel("PRETRATAMIENTO", vertimiento);
        if(pretratamiento!=null){
            pretratamiento.setCodigoAutoridad(autoridad.getId().longValue());
            pretratamiento.setIdPuntoVertimiento(vertimiento.getId());
            //validar si no se repite el tratamiento
            PuntoVertimientoTratamiento existe = uad.getTratamientoPunto(pretratamiento);
            if(existe==null){
                uad.createPuntoTratamiento(pretratamiento);
            }
        }
        
        //primario
        PuntoVertimientoTratamiento primario = 
            (PuntoVertimientoTratamiento)((UsuariosAguaPermisosNaturalConverter)converter).
                                                getModel("PRIMARIO", vertimiento);
        if(primario!=null){
            primario.setCodigoAutoridad(autoridad.getId().longValue());
            primario.setIdPuntoVertimiento(vertimiento.getId());
            //validar si no se repite el tratamiento
            PuntoVertimientoTratamiento existe = uad.getTratamientoPunto(primario);
            if(existe==null){
                uad.createPuntoTratamiento(primario);
            }
        }
        
        //secundario
        PuntoVertimientoTratamiento secundario = 
            (PuntoVertimientoTratamiento)((UsuariosAguaPermisosNaturalConverter)converter).
                                                getModel("SECUNDARIO", vertimiento);
        if(secundario!=null){
            secundario.setCodigoAutoridad(autoridad.getId().longValue());
            secundario.setIdPuntoVertimiento(vertimiento.getId());
            //validar si no se repite el tratamiento
            PuntoVertimientoTratamiento existe = uad.getTratamientoPunto(secundario);
            if(existe==null){
                uad.createPuntoTratamiento(secundario);
            }
        }
        
        //terciario
        PuntoVertimientoTratamiento terciario = 
            (PuntoVertimientoTratamiento)((UsuariosAguaPermisosNaturalConverter)converter).
                                                getModel("TERCIARIO", vertimiento);
        if(terciario!=null){
            terciario.setCodigoAutoridad(autoridad.getId().longValue());
            terciario.setIdPuntoVertimiento(vertimiento.getId());
            //validar si no se repite el tratamiento
            PuntoVertimientoTratamiento existe = uad.getTratamientoPunto(terciario);
            if(existe==null){
                uad.createPuntoTratamiento(terciario);
            }
        }
        
        //otro
        PuntoVertimientoTratamiento otro = 
            (PuntoVertimientoTratamiento)((UsuariosAguaPermisosNaturalConverter)converter).
                                                getModel("OTRO", vertimiento);
        if(otro!=null){
            otro.setCodigoAutoridad(autoridad.getId().longValue());
            otro.setIdPuntoVertimiento(vertimiento.getId());
            //validar si no se repite el tratamiento
            PuntoVertimientoTratamiento existe = uad.getTratamientoPunto(otro);
            if(existe==null){
                uad.createPuntoTratamiento(otro);
            }
        }
        
        return ++registrosCargados;
    }
    
    public static int registrarUsuarioAguaCaptacionesJuridica(ModelConverter converter, 
                                                              RowTO fila, 
                                                              Autoridades autoridad)throws IdeamException{
        boolean usuarioRegistrado = false;
        int registrosCargados = 0;

        UsuariosAguaDelegate uad = UsuariosAguaDelegate.getInstance();

        converter.setRow( fila );

        UsuarioAgua usuario = new UsuarioAgua();
        converter.getModel(usuario);       
        
        Predio predio = new Predio();
        converter.getModel(predio);
        
        // Determinar si el usuario ya se encuentra registrado
        String numDoc=quitarPuntoCero(usuario.getNumeroDocumento());
        UsuarioAgua usuarioExiste = uad.getUsuario(usuario.getTipoDocumento().
                                                    getCodigo(),
                                                   numDoc,
                                                   autoridad.getId().longValue());
                
        Representante reresentante = new Representante();
        converter.getModel(reresentante);

        if(usuarioExiste!=null){
            usuario = usuarioExiste;
            usuarioRegistrado = true;
            
            PrediosTO predioExiste = uad.getPredioTO(predio.getCedulaCatastral(), 
                                                    usuario.getNumeroDocumento(),  
                                                    autoridad.getId().longValue());
            if(predioExiste!=null){
                predio = uad.getPredio(predioExiste.getCodigo());   
            }               
        }


        if(!usuarioRegistrado){
            usuario.setDepartamento( predio.getDepartamento() );
            usuario.setMunicipio( predio.getMunicipio() );
        }

        Object retorno[] = uad.registrarUsuarioPredio(usuario,
                                   predio,
                                   reresentante,
                                   autoridad);
        Concesion concesion = new Concesion();
        concesion = (Concesion)converter.getModel(concesion);

        concesion.setCodigoAutoridadAmbiental(
            autoridad.getId().longValue() );
        concesion.setCodigoPredio((Long)retorno[1]);
        
        //toca validar si  la concesion existe
        ConcesionTO concesionExiste = 
            uad.getConcesionByExpedienteResolucionPredio(concesion.getNumeroExpediente(),
                                                         concesion.getResolucionCaudal(),
                                                         predio.getCodigo(),
                                                         autoridad.getId().longValue());
        if(concesionExiste!=null){
            concesion = uad.getConcesion(concesionExiste.getCodigo());
        }
        
        concesion = uad.registrarConcesion(concesion);
        
        //Eduin
        //proceso para la captacion
        Captacion captacion = new Captacion ();
        captacion.setAutoridad(autoridad);
        captacion.setCodigoAutoridad(autoridad.getId().longValue());
        captacion.setIdConcesion(concesion);
        captacion = (Captacion)converter.getModel(captacion);
        //verificar que no exista
        CaptacionTO captacionExiste = uad.getCaptacion(captacion, 
                                                        autoridad.getId().longValue());
        if(captacionExiste!=null){
            captacion = uad.getCaptacion(captacionExiste.getId());
            //actualizar
            captacion =  uad.updateCaptacion(captacion);
        }else{
            //registrar
            captacion = uad.createCaptacion(captacion);
        }
        
        captacion.setAutoridad(autoridad);
        captacion.setCodigoAutoridad(autoridad.getId().longValue());
        
        //proceso para los componentes
        List<RurtCaptacionComponentes> componentes = 
            (List<RurtCaptacionComponentes>)((UsuariosAguaCaptacionesJuridicaConverter)converter).getModel("COMPONENTES", captacion);
        for( RurtCaptacionComponentes componente : componentes){
            //consultar si ya existe el componente para la captacion.
            RurtCaptacionComponentes captacionComponente = 
                uad.getComponenteCaptacion(componente.getId(), captacion.getCodigo());
            if(captacionComponente==null){//si no existe la relación
                uad.createCaptacionComponente(componente);//registrar
            }
        }
        
        //proceso para los usos
        RurtCaptacionUso usoDomestico = 
            (RurtCaptacionUso)((UsuariosAguaCaptacionesJuridicaConverter)converter).
                                                getModel("USO_DOMESTICO", captacion);
        if(usoDomestico!=null){
            usoDomestico.setCodigoAutoridad(autoridad.getId().longValue());
            usoDomestico.setIdCaptacion(captacion.getCodigo());
            //validar si no se repite el uso?
            uad.createUso(usoDomestico);
        }
        
        RurtCaptacionUso usoAgricola = 
            (RurtCaptacionUso)((UsuariosAguaCaptacionesJuridicaConverter)converter).
                                                getModel("USO_AGRICOLA", captacion);
        if(usoAgricola!=null){
            usoAgricola.setCodigoAutoridad(autoridad.getId().longValue());
            usoAgricola.setIdCaptacion(captacion.getCodigo());
            //validar si no se repite el uso?
            uad.createUso(usoAgricola);
        }
        
        RurtCaptacionUso usoPecuario = 
            (RurtCaptacionUso)((UsuariosAguaCaptacionesJuridicaConverter)converter).
                                                getModel("USO_PECUARIO", captacion);
        if(usoPecuario!=null){
            usoPecuario.setCodigoAutoridad(autoridad.getId().longValue());
            usoPecuario.setIdCaptacion(captacion.getCodigo());
            //validar si no se repite el uso?
            uad.createUso(usoPecuario);
        }
        
        RurtCaptacionUso usoAcuicola = 
            (RurtCaptacionUso)((UsuariosAguaCaptacionesJuridicaConverter)converter).
                                                getModel("USO_ACUICOLA", captacion);
        if(usoAcuicola!=null){
            usoAcuicola.setCodigoAutoridad(autoridad.getId().longValue());
            usoAcuicola.setIdCaptacion(captacion.getCodigo());
            //validar si no se repite el uso?
            uad.createUso(usoAcuicola);
        }
        
        RurtCaptacionUso usoOtro = 
            (RurtCaptacionUso)((UsuariosAguaCaptacionesJuridicaConverter)converter).
                                                getModel("USO_OTRO", captacion);
        if(usoOtro!=null){
            usoOtro.setCodigoAutoridad(autoridad.getId().longValue());
            usoOtro.setIdCaptacion(captacion.getCodigo());
            //validar si no se repite el uso?
            uad.createUso(usoOtro);
        }
        
        
        return ++registrosCargados;
    }
    
    public static int registrarFuente(ModelConverter converter, 
                                      RowTO fila, 
                                      Autoridades autoridad)throws IdeamException{
        int registrosCargados = 0;

        FuenteDelegate fd = FuenteDelegate.getInstance();

        converter.setRow( fila );

        FnttFuente fuente = new FnttFuente();
        converter.getModel(fuente);
        fuente.setCodAutoridad(autoridad);

        FnttFuente fuenteExiste = fd.getFuente(fuente.getNombre(),
                                               autoridad, 
                                               fuente.getIdTipoFuente().getCodigo());
        FnttFuente fuenteActualizada = null;
        if(fuenteExiste!=null){
            fuenteActualizada = fuenteExiste;
            fuenteActualizada.setDescripcion(fuente.getDescripcion());
            fuenteActualizada.setCodigoCuencaAA(fuente.getCodigoCuencaAA());
            fuenteActualizada = fd.updateFuente(fuenteActualizada);
        }else{
            fuenteActualizada = fd.updateFuente(fuente);
        }

        FnttTramo tramo = new FnttTramo();
        converter.getModel(tramo);

        FnttTramo tramoExiste = fd.getTramo(tramo.getNombre(),
                                            fuenteActualizada,
                                            autoridad);
        if(tramoExiste==null){
            tramo.setIdFuente(fuenteActualizada);
            tramo.setCodigoAutoridad(
                    autoridad.getId().longValue() );
            tramo.setOfertaHidricaTotal(new BigDecimal(0));
            fd.updateTramo(tramo);
        }

        return ++registrosCargados;
    }
    public static String quitarPuntoCero(String numDocRecv){
        String numDoc=numDocRecv;
        System.out.println("Load DataUtil Debe quitar .0 de numDoc?????:"+numDoc);
        if(numDoc.indexOf(".0")!=-1&&numDoc.indexOf(".0")==numDoc.length()-2){
           System.out.println("Debe quitar .0 de numDoc:"+numDoc);
           numDoc= numDoc.substring(0,numDoc.indexOf(".0"));
           System.out.println("Queda:"+numDoc);
        }
        return numDoc;
    }
    
    public static int registrarUsuarioAguaCaptacionesNatural(ModelConverter converter, 
                                                             RowTO fila, 
                                                             Autoridades autoridad)throws IdeamException{
        int registrosCargados = 0;

        UsuariosAguaDelegate uad = UsuariosAguaDelegate.getInstance();

        converter.setRow( fila );

        UsuarioAgua usuario = new UsuarioAgua();
        converter.getModel(usuario);
        
        
        Predio predio = new Predio();
        converter.getModel(predio);

        // Determinar si el usuario ya se encuentra registrado
        
        String numDoc=quitarPuntoCero(usuario.getNumeroDocumento());
        UsuarioAgua usuarioExiste = uad.getUsuario(usuario.getTipoDocumento().getCodigo(),
                                                    numDoc,
                                                   autoridad.getId().longValue());
        
        
        if(usuarioExiste!=null){
            usuario = usuarioExiste;
            
            PrediosTO predioExiste = uad.getPredioTO(predio.getCedulaCatastral(), 
                                                    usuario.getNumeroDocumento(), 
                                                    autoridad.getId().longValue());
            if(predioExiste!=null){
                predio = uad.getPredio(predioExiste.getCodigo());   
            }               
        }


        Object retorno[] = uad.registrarUsuarioPredio(usuario,
                                   predio,
                                   null,
                                   autoridad);
        Concesion concesion = new Concesion();
        concesion = (Concesion)converter.getModel(concesion);

        concesion.setCodigoAutoridadAmbiental(
            autoridad.getId().longValue() );
        concesion.setCodigoPredio((Long)retorno[1]);
        
        //toca validar si  la concesion existe
        ConcesionTO concesionExiste = 
            uad.getConcesionByExpedienteResolucionPredio(concesion.getNumeroExpediente(),
                                                         concesion.getResolucionCaudal(),
                                                         predio.getCodigo(),
                                                         autoridad.getId().longValue());
        if(concesionExiste!=null){
            concesion = uad.getConcesion(concesionExiste.getCodigo());
        }
        
        concesion = uad.registrarConcesion(concesion);


        //Eduin
        //proceso para la captacion
        Captacion captacion = new Captacion ();
        captacion.setAutoridad(autoridad);
        captacion.setCodigoAutoridad(autoridad.getId().longValue());
        captacion.setIdConcesion(concesion);
        captacion = (Captacion)converter.getModel(captacion);
        
        //verificar que no exista
        CaptacionTO captacionExiste = uad.getCaptacion(captacion, 
                                                        autoridad.getId().longValue());
        if(captacionExiste!=null){
            captacion = uad.getCaptacion(captacionExiste.getId());
            //actualizar
            captacion =  uad.updateCaptacion(captacion);
        }else{
            //registrar
            captacion = uad.createCaptacion(captacion);
        }
        
        captacion.setAutoridad(autoridad);
        captacion.setCodigoAutoridad(autoridad.getId().longValue());
        
        //proceso para los componentes
        List<RurtCaptacionComponentes> componentes = 
            (List<RurtCaptacionComponentes>)((UsariosAguaCaptacionesNaturalConverter)converter).getModel("COMPONENTES", captacion);
        for( RurtCaptacionComponentes componente : componentes){
            //consultar si ya existe el componente para la captacion.
            RurtCaptacionComponentes captacionComponente = 
                uad.getComponenteCaptacion(componente.getId(), captacion.getCodigo());
            if(captacionComponente==null){//si no existe la relación
                uad.createCaptacionComponente(componente);//registrar
            }
        }
        
        //proceso para los usos
        RurtCaptacionUso usoDomestico = 
            (RurtCaptacionUso)((UsariosAguaCaptacionesNaturalConverter)converter).
                                                getModel("USO_DOMESTICO", captacion);
        if(usoDomestico!=null){
            usoDomestico.setCodigoAutoridad(autoridad.getId().longValue());
            usoDomestico.setIdCaptacion(captacion.getCodigo());
            //validar si no se repite el uso?
            uad.createUso(usoDomestico);
        }
        
        RurtCaptacionUso usoAgricola = 
            (RurtCaptacionUso)((UsariosAguaCaptacionesNaturalConverter)converter).
                                                getModel("USO_AGRICOLA", captacion);
        if(usoAgricola!=null){
            usoAgricola.setCodigoAutoridad(autoridad.getId().longValue());
            usoAgricola.setIdCaptacion(captacion.getCodigo());
            //validar si no se repite el uso?
            uad.createUso(usoAgricola);
        }
        
        RurtCaptacionUso usoPecuario = 
            (RurtCaptacionUso)((UsariosAguaCaptacionesNaturalConverter)converter).
                                                getModel("USO_PECUARIO", captacion);
        if(usoPecuario!=null){
            usoPecuario.setCodigoAutoridad(autoridad.getId().longValue());
            usoPecuario.setIdCaptacion(captacion.getCodigo());
            //validar si no se repite el uso?
            uad.createUso(usoPecuario);
        }
        
        RurtCaptacionUso usoAcuicola = 
            (RurtCaptacionUso)((UsariosAguaCaptacionesNaturalConverter)converter).
                                                getModel("USO_ACUICOLA", captacion);
        if(usoAcuicola!=null){
            usoAcuicola.setCodigoAutoridad(autoridad.getId().longValue());
            usoAcuicola.setIdCaptacion(captacion.getCodigo());
            //validar si no se repite el uso?
            uad.createUso(usoAcuicola);
        }
        
        RurtCaptacionUso usoOtro = 
            (RurtCaptacionUso)((UsariosAguaCaptacionesNaturalConverter)converter).
                                                getModel("USO_OTRO", captacion);
        if(usoOtro!=null){
            usoOtro.setCodigoAutoridad(autoridad.getId().longValue());
            usoOtro.setIdCaptacion(captacion.getCodigo());
            //validar si no se repite el uso?
            uad.createUso(usoOtro);
        }
        
        return ++registrosCargados;
    }



    public static int registrarPuntosMonitoreo(ModelConverter converter, 
                                               RowTO fila, 
                                               Autoridades autoridad)throws IdeamException{
        int registrosCargados = 0;

       CalidadDelegate cld= CalidadDelegate.getInstance();
       FuenteDelegate fd = FuenteDelegate.getInstance();
       ParametrosDelegate p= ParametrosDelegate.getInstance();
        converter.setRow( fila );

        PuntoMonitoreo puntoMonitoreo = new PuntoMonitoreo();
        converter.getModel(puntoMonitoreo);
        
        
        String nombretipof = puntoMonitoreo.getTipofuente().trim();
        String nombrefuente = puntoMonitoreo.getNombreFuente().trim();
        String nombretramo = puntoMonitoreo.getNombreTramo().trim();

        Lista tipo = p.getListaByDescripcion(nombretipof.toUpperCase(), 12L);
        
        System.out.println("tipo fuente"+tipo.getCodigo());
        FnttFuente objfuente = fd.getFuente(nombrefuente,
                                            autoridad, tipo.getCodigo());
        
        System.out.println("-------------------------> fuente"+objfuente.getId());
        System.out.println("-------------------------> nombretramo"+nombretramo);
        System.out.println("-------------------------> fuente"+objfuente.getId());
        
        
        FnttTramo objtramo = fd.getTramo(nombretramo,
                                            objfuente,
                                            autoridad);
        System.out.println(" TRAMO----------------------"+objtramo.getId());
        puntoMonitoreo.setCodigoAutoridad(autoridad.getId().longValue());

        puntoMonitoreo.setIdFuente(objfuente);
        puntoMonitoreo.setIdTramo(objtramo);

        PuntoMonitoreo puntoExiste = cld.getPuntoMonitoreoExiste(puntoMonitoreo.getNombre(),
                                                                 objfuente.getId(),
                                                                 objtramo.getId() );


        PuntoMonitoreo puntoActualizado = null;
        if(puntoExiste!=null){
            puntoActualizado = puntoExiste;
        }else{
            puntoActualizado = cld.updatePuntoMonitoreo(puntoMonitoreo);
        }

        return ++registrosCargados;
    }




    public static int registrarMediciones(ModelConverter converter, 
                                          RowTO fila, 
                                          Autoridades autoridad)throws IdeamException{
        int registrosCargados = 0;

        CalidadDelegate cld = CalidadDelegate.getInstance();

        converter.setRow( fila );

        Muestra muestra = new Muestra();
        converter.getModel(muestra);

        String nombrePunto = muestra.getNombrePunto();
        PuntoMonitoreo objPunto = cld.getPuntosMonitoreoNomAut(nombrePunto,autoridad.getId().longValue());
        System.out.println("Punto----------------------"+objPunto.getId());
        muestra.setIdPuntoMonitoreo(objPunto);
        muestra.setCodigoAutoridad(autoridad.getId().longValue());

        Date fecham = muestra.getFechaMuestreo().getTime();
        DateFormat df =  DateFormat.getDateInstance(DateFormat.SHORT);
          
        String fechamuestra = df.format(fecham);
        System.out.println("FECHA---------------------------------------+"+fechamuestra);    
        
        if(fechamuestra.length()==8){
           
            fechamuestra=fechamuestra;
        }else{
            
                fechamuestra="0"+fechamuestra;
            }
        Muestra muestraExiste = cld.getMuestraExiste(objPunto.getId(),
                                                     muestra.getIdTipoMuestra(),
                                                     muestra.getHoraMuestreo(),
                                                     muestra.getMinMuestreo(),
                                                     muestra.getHorario(),
                                                     muestra.getIdLaboratorio(),
                                                     fechamuestra);
        System.out.println("muestraExiste sql----------------------"+muestraExiste);
        
        Muestra muestraActualizada = null;
        
       if(muestraExiste!=null){
            muestraActualizada = muestraExiste;
        }else{
           muestraActualizada = cld.updateMuestra(muestra);
        }

        Medicion med  = new Medicion();
        converter.getModel(med);
        med.setCodigoAutoridad(autoridad.getId().longValue());
        med.setIdMuestra(muestraActualizada);


        Medicion medExiste = cld.getMedicionExiste(muestraActualizada.getId(),
                                                   med.getIdParametro(),
                                                   med.getIdUnidad(),
                                                   med.getIdSigno(),
                                                   med.getValor()
                                                   );


        String error="";
         try{

        Medicion medicionActualizada = null;
        if(medExiste!=null){
            medicionActualizada = medExiste;
        }else{
             System.out.println("XXXXXXXXXXXXXXXXX-update "); 
           cld.updateMedicion(med);
         }
              
        }catch(Exception e){
        
        
              error= error+"--- "+med.getIdMuestra();
        
            System.out.println("XXXXXXXXXXXXXXXXX---ERROR EN LA IMPORTACION DATOS: carga "+error);    
        }
         
       return ++registrosCargados;
    }
    
    
    public static int registrarFuniasGeologia (ModelConverter converter, 
                                               RowTO fila, 
                                               Autoridades autoridad)throws IdeamException{
        int registrosCargados = 0;

        UsuariosAguaDelegate uad = UsuariosAguaDelegate.getInstance();

        converter.setRow( fila );
                
        SubttFunias funias = new SubttFunias();
        
        funias.setAutoridad(autoridad);
        funias.setTipoFunias(ConstantesParametros.LISTA_GEOLOGIA_GEOMORFOLOGIA.intValue());
        funias.setCodigoAutoridad(autoridad.getId().longValue());
        funias = (SubttFunias)converter.getModel(funias);
        
        
        if(funias!=null){
            if(funias.getId() != null){
                System.out.println("========================================Actualiza Funias:");
                uad.updateFunias(funias);
            }else{
                System.out.println("========================================Crea Funias:");
                uad.createFunias(funias);
            }
            registrosCargados++;
        }
        
        return registrosCargados;
    }
    
    public static int registrarFuniasConstruccion (ModelConverter converter, 
                                                   RowTO fila, 
                                                   Autoridades autoridad)throws IdeamException{
        int registrosCargados = 0;

        UsuariosAguaDelegate uad = UsuariosAguaDelegate.getInstance();

        converter.setRow( fila );
                
        SubttFunias funias = new SubttFunias();
        
        funias.setAutoridad(autoridad);
        funias.setTipoFunias(ConstantesParametros.LISTA_CONSTRUCCION_DISENO.intValue());
        funias.setCodigoAutoridad(autoridad.getId().longValue());
        funias = (SubttFunias)converter.getModel(funias);
        
        

        if(funias!=null){
            if(funias.getId() != null){
                System.out.println("========================================Actualiza Funias:");
                uad.updateFunias(funias);
            }else{
                System.out.println("========================================Crea Funias:");
                uad.createFunias(funias);
            }
            registrosCargados++;
        }
        return registrosCargados;
    }
    
    public static int registrarFuniasGeofisica (ModelConverter converter, 
                                                RowTO fila, 
                                                Autoridades autoridad)throws IdeamException{
        int registrosCargados = 0;

        UsuariosAguaDelegate uad = UsuariosAguaDelegate.getInstance();

        converter.setRow( fila );
                
        SubttFunias funias = new SubttFunias();
        
        funias.setAutoridad(autoridad);
        funias.setTipoFunias(ConstantesParametros.LISTA_GEOFISICA.intValue());
        funias.setCodigoAutoridad(autoridad.getId().longValue());
        funias = (SubttFunias)converter.getModel(funias);
        
        

        if(funias!=null){
            if(funias.getId() != null){
                System.out.println("========================================Actualiza Funias:");
                uad.updateFunias(funias);
            }else{
                System.out.println("========================================Crea Funias:");
                uad.createFunias(funias);
            }
            registrosCargados++;
        }
        return registrosCargados;
    }
    
    public static int registrarFuniasDiagnostico (ModelConverter converter, 
                                                  RowTO fila, 
                                                  Autoridades autoridad)throws IdeamException{
        int registrosCargados = 0;

        UsuariosAguaDelegate uad = UsuariosAguaDelegate.getInstance();

        converter.setRow( fila );
                
        SubttFunias funias = new SubttFunias();
        
        funias.setAutoridad(autoridad);
        funias.setTipoFunias(ConstantesParametros.LISTA_DIAGNOSTICO_SANITARIO.intValue());
        funias.setCodigoAutoridad(autoridad.getId().longValue());
        funias = (SubttFunias)converter.getModel(funias);
        
        

        if(funias!=null){
            if(funias.getId() != null){
                System.out.println("========================================Actualiza Funias:");
                uad.updateFunias(funias);
            }else{
                System.out.println("========================================Crea Funias:");
                uad.createFunias(funias);
            }
            registrosCargados++;
        }
        return registrosCargados;
    }
    
    
    public static int registrarTarifaAcueducto(ModelConverter converter, 
                                      RowTO fila)throws IdeamException{
        int registrosCargados = 0;

        DatosReferenciaDelegate drd = DatosReferenciaDelegate.getInstance();

        converter.setRow( fila );
        
        
        //primero la empresa
  
        DemtEmpresa empresa = new DemtEmpresa();
        converter.getModel(empresa);
        
        DemtEmpresa empresaExiste = drd.getEmpresaByNit(empresa.getNit());
        DemtEmpresa empresaActualizada = null;
        if(empresaExiste!=null){
            empresaActualizada = empresaExiste;
        }else{
            empresaActualizada = drd.createEmpresa(empresa);
        }
        
        //luego el acueducto
        
        
        //luego la tarifa

        DemtTarifa tarifa = new DemtTarifa();
        converter.getModel(tarifa);
        
        DemtTarifa tarifaExiste = 
            drd.getTarifaByFiltros(tarifa.getMunicipio(),
                                   tarifa.getDepartamento(), tarifa.getAgno(), 
                                   tarifa.getMes(), tarifa.getIdEmpresa(), 
                                   tarifa.getEstrato(), tarifa.getClase());
        DemtTarifa tarifaActualizada = null;
        if(tarifaExiste!=null){
            tarifaActualizada = tarifaExiste;
        }else{
            tarifa.setIdEmpresa(empresaActualizada.getId());
            tarifaActualizada = drd.createTarifa(tarifa);
        }
    
        return ++registrosCargados;
    }
}
