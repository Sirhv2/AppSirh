package co.gov.ideam.sirh.archivos.model;

import co.gov.ideam.sirh.fuentes.business.FuentesEJB;
import co.gov.ideam.sirh.fuentes.model.FnttFuente;
import co.gov.ideam.sirh.fuentes.model.FnttTramo;
import co.gov.ideam.sirh.parametros.business.ParametrosEJB;
import co.gov.ideam.sirh.parametros.model.Divipola;
import co.gov.ideam.sirh.parametros.model.Lista;
import co.gov.ideam.sirh.parametros.model.PartZonificListas;
import co.gov.ideam.sirh.usuarios.agua.model.Captacion;
import co.gov.ideam.sirh.usuarios.agua.model.CaptacionTO;
import co.gov.ideam.sirh.usuarios.agua.model.Concesion;
import co.gov.ideam.sirh.usuarios.agua.model.ConcesionTO;
import co.gov.ideam.sirh.usuarios.agua.model.Predio;
import co.gov.ideam.sirh.usuarios.agua.model.RurtCaptacionComponentes;
import co.gov.ideam.sirh.usuarios.agua.model.RurtCaptacionUso;
import co.gov.ideam.sirh.usuarios.agua.model.UsuarioAgua;
import co.gov.ideam.sirh.util.Constantes;
import co.gov.ideam.sirh.util.ConstantesParametros;
import co.gov.ideam.sirh.util.IdeamException;

import java.util.ArrayList;
import java.util.List;

/**
 * Convierte los datos de un objeto RowTO a una emtidad de Model
 */
public class UsariosAguaCaptacionesNaturalConverter extends ModelConverter{
    public UsariosAguaCaptacionesNaturalConverter() {
        super();
    }

    public Object getModel(Object model)throws IdeamException {
        if(model instanceof UsuarioAgua){
            return this.getUsuarioAgua( (UsuarioAgua)model );
        }else if(model instanceof Predio){
            return this.getPredio( (Predio)model );
        }else if(model instanceof Concesion){
            return this.getConcesion((Concesion)model);
        }else if(model instanceof Captacion){
            return this.getCaptacion( (Captacion)model );
        }
                    
        return null;
    }
    
    public Object getModel(Object model, Object aux)throws IdeamException {
       if(model instanceof String){
            if(model.toString().equalsIgnoreCase("COMPONENTES")){
                return this.getCaptacionComponentes((Captacion)aux);   
            }else if(model.toString().equalsIgnoreCase("USO_DOMESTICO")){
                return this.getCaptacionUsoDomestico((Captacion)aux);   
            }else if(model.toString().equalsIgnoreCase("USO_AGRICOLA")){
                return this.getCaptacionUsoAgricola((Captacion)aux);   
            }else if(model.toString().equalsIgnoreCase("USO_PECUARIO")){
                return this.getCaptacionUsoPecuario((Captacion)aux);   
            }else if(model.toString().equalsIgnoreCase("USO_ACUICOLA")){
                return this.getCaptacionUsoAcuicola((Captacion)aux);   
            }else if(model.toString().equalsIgnoreCase("USO_OTRO")){
                return this.getCaptacionUsoOtro((Captacion)aux);   
            }
        }
                    
        return null;
    }
    /**
     * Retorna ua concesion con los datos registrados en RowTO
     * @param concesion
     * @return
     * @throws IdeamException
     */
    private Concesion getConcesion(Concesion concesion)throws IdeamException{
        
        concesion.setNumeroExpediente( 
                    this.row.getCellValue("Numero Expediente").toString() );
        concesion.setResolucionCaudal( 
                    this.row.getCellValue("Numero Resolucion").toString() );
        
        String fechaExpResolucion = 
                this.row.getCellValue("Fecha Expedicion Resolucion").toString();
        concesion.setFechaExpedicionCaudal( this.getDate(fechaExpResolucion) );
        
        String fechaNotResolucion = 
                this.row.getCellValue("Fecha Notificacion").toString();
        concesion.setFechaNotificacionCaudal( this.getDate(fechaNotResolucion) );
        String tmp=this.row.getCellValue("Caudal").toString();
        if(tmp.indexOf(",")!=-1)
        tmp=tmp.replaceAll(",",".");
       
        concesion.setCaudalConcesionado( 
                Double.parseDouble(  tmp) );
        
        concesion.setResolucionPlanos( this.row.getCellValue("Resolucion Aprobacion Planos").toString() );
        
        String fechaExpPlanos = this.row.getCellValue("Fecha Expedicion Aprobacion Planos").toString();
        concesion.setFechaExpedicionPlanos( this.getDate(fechaExpPlanos) );
        
        String fechaNotPlanos = this.row.getCellValue("Fecha Notificacion Aprobacion Planos").toString();
        concesion.setFechaNotificacionPlanos( this.getDate(fechaNotPlanos) );
        
        concesion.setResolucionObras( this.row.getCellValue("Resolucion Obras").toString() );
        
        String fechaExpObras = this.row.getCellValue("Fecha Expedicion Obras").toString();
        concesion.setFechaExpedicionObras( this.getDate(fechaExpObras) );
        
        String fechaNotObras = this.row.getCellValue("Fecha Notificacion Obras").toString();
        concesion.setFechaNotificacionObras( this.getDate(fechaNotObras) );
        
        String fechaInicio = this.row.getCellValue("Vigencia Desde").toString();
        concesion.setFechaInicio( this.getDate(fechaInicio) );
        
        String fechaFin = this.row.getCellValue("Vigencia Hasta").toString();
        concesion.setFechaFin( this.getDate(fechaFin) );
        
        return concesion;
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
        
        predio.setCedulaCatastral( this.row.getCellValue("Cedula Catastral").toString() );
        predio.setMatriculaCatastral( this.row.getCellValue("Matricula Catastral").toString() );                
        predio.setDireccion( this.row.getCellValue("Direccion Predio").toString() );
        
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
        
        usuario.setNombre( this.row.getCellValue("Nombre").toString() );
        usuario.setApellido( this.row.getCellValue("Apellido").toString() );
        
        String tipoDocTxt = this.row.getCellValue("Tipo Documento").toString();
        Lista tipoDocumento = 
            parametrosService.getListaByDescripcion(tipoDocTxt, 2L);
        usuario.setTipoDocumento( tipoDocumento );
        
        usuario.setNumeroDocumento( this.row.getCellValue("Numero Documento").toString() );
        
        String nombreDeptoTxt = this.row.getCellValue("Departamento").toString();
        Divipola depto = 
            parametrosService.getDivipolaByName(nombreDeptoTxt,"DEP");
        usuario.setDepartamento(depto);
        usuario.setCodigoDepartamento( depto.getId() );
        
        String nombreMunTxt = this.row.getCellValue("Municipio").toString();
        Divipola municipio = 
            parametrosService.getDivipolaByName(nombreMunTxt, depto);
        usuario.setMunicipio(municipio);
        usuario.setCodigoMunicipio( municipio.getId() );
        
        usuario.setDireccion( this.row.getCellValue("Direccion").toString() );
        usuario.setEmail( this.row.getCellValue("Email").toString() );
        usuario.setTelefono( this.row.getCellValue("Telefono").toString() );
        
        return null;
    }
    
    //EDUIN
    
    /**
     * Retorna ua captacion con los datos registrados en RowTO
     * @param captacion
     * @return
     * @throws IdeamException
     */
    private Captacion getCaptacion(Captacion captacion)throws IdeamException{
        ParametrosEJB parametrosService = this.getParametrosService();
        FuentesEJB fuenteService = this.getFuentesService();
        
        //estos tres campos son obligatorios en la bd, pero en la plantilla 
        //no vienen, por eso, tocó cogerlos del predio.
        //======================
        String nombreDeptoTxt = this.row.getCellValue("Departamento Predio").toString();
        Divipola depto = 
            parametrosService.getDivipolaByName(nombreDeptoTxt,"DEP");
        captacion.setIdDepartamento(depto.getId().intValue());
        
        String nombreMunTxt = this.row.getCellValue("Municipio Predio").toString();
        Divipola mun = 
            parametrosService.getDivipolaByName(nombreMunTxt, depto);
        captacion.setIdMunicipio(mun.getId().intValue());

        String tipoCentroPobladoTxt = this.row.getCellValue("Tipo Centro Poblado").toString();
        Lista tipoCentroPoblado = 
            parametrosService.getListaByDescripcion(tipoCentroPobladoTxt, 4L);
        captacion.setIdTipoCentroPoblado( tipoCentroPoblado.getCodigo() );
        ////====================
        
        String tipoFuenteCapta = this.row.getCellValue("Tipo Fuente Captacion").toString();
        Lista tipofuentesCapta = 
            parametrosService.getListaByDescripcion(tipoFuenteCapta, 
                                                    ConstantesParametros.
                                                     CATEGORIA_TIPO_FUENTE_CAPTA);;
        captacion.setTipoFuenteCaptacion(tipofuentesCapta.getCodigo());
        
        String areaHidro = this.row.getCellValue("Area Captacion").toString();       
        PartZonificListas area = parametrosService.getZonificacionByName(
                              areaHidro, null);
        captacion.setIdArea(area);
        
        String zonaHidro = this.row.getCellValue("Zona Captacion").toString();       
        PartZonificListas zona = parametrosService.getZonificacionByName(
                              zonaHidro, null);
        captacion.setIdZona(zona);
        
        String subzonaHidro = this.row.getCellValue("Subzona Captacion").toString();       
        PartZonificListas subzona = parametrosService.getZonificacionByName(
                              subzonaHidro, null);
        captacion.setIdSubzona(subzona);
        
        String tipoFuente = this.row.getCellValue("Tipo Fuente").toString(); 
        Lista listaTipoFuente = parametrosService.getListaByDescripcion(tipoFuente.trim(), 
                                                    ConstantesParametros.
                                                     CATEGORIA_TIPO_FUENTE);
        
        String fuenteNombre = this.row.getCellValue("Nombre Fuente Captacion").toString(); 
        FnttFuente fuente = fuenteService.getFuente(fuenteNombre.toUpperCase().trim(), 
                                                    captacion.getAutoridad(), 
                                                    listaTipoFuente.getCodigo());
        captacion.setIdFuente(fuente);
        
        String tramoNombre = this.row.getCellValue("Nombre Tramo").toString();         
        FnttTramo tramo = fuenteService.getTramo(tramoNombre.toUpperCase().trim(), 
                                                 fuente.getId());
        captacion.setIdTramo(tramo);
        
        String identificadorPunto = this.row.getCellValue("Id Punto Subterranea").toString();         
        captacion.setIdentificadorPunto((identificadorPunto!=null) 
                                        ?identificadorPunto : null);
        
        String provinciaHidro = this.row.getCellValue("Provincia Hidrologica").toString();         
        Lista provincia = parametrosService.getListaByDescripcion(provinciaHidro,ConstantesParametros.CATEGORIA_PROVINCIA_HIDROGEOLOGICA);
        captacion.setProvinciaHidrogeologica((provincia!=null) 
                                             ? provincia.getCodigo() : null);
        
        String unidadHidro = this.row.getCellValue("Unidad Hidrologica").toString();         
        captacion.setUnidadHidrogeologica((unidadHidro!=null) ? unidadHidro : null);
        
        String nombreTipoCaptacion = this.row.getCellValue("Tipo Captacion").toString();
        Lista tipoCaptacion = null;
        if(tipofuentesCapta.getCodigo().longValue()==ConstantesParametros.LISTA_AGUAS_LLUVIAS){
            tipoCaptacion = 
            parametrosService.getListaByDescripcion(nombreTipoCaptacion, 
                                                    ConstantesParametros.
                                                     CATEGORIA_TIPO_CAPTACION);
        }else if(tipofuentesCapta.getCodigo().longValue()==ConstantesParametros.LISTA_AGUAS_SUPERFICIALES){
            tipoCaptacion = 
            parametrosService.getListaByDescripcion(nombreTipoCaptacion, 
                                                    ConstantesParametros.
                                                     CATEGORIA_TIPO_CAPTACION_SUPERFICIAL);
        }
        captacion.setTipoCaptacion((tipoCaptacion!=null)
                                   ?tipoCaptacion.getCodigo()
                                   :null);
        
        String ofertaTotal=this.row.getCellValue("Oferta Total").toString();
        ofertaTotal = replaceFloatingSeparator(ofertaTotal);
        captacion.setOfertaHidricaTotal((ofertaTotal!=null)
                                        ?Double.parseDouble(ofertaTotal)
                                        :null);
        
        
        String areaLluvia = this.row.getCellValue("Area Captacion Lluvia").toString();
        areaLluvia = replaceFloatingSeparator(areaLluvia);
        captacion.setAreaCaptacion((areaLluvia!=null)
                                   ?Double.parseDouble(areaLluvia) 
                                   :null);
        
        String ofertaDisponible = this.row.getCellValue("Oferta Disponible").toString();
        ofertaDisponible = replaceFloatingSeparator(ofertaDisponible);
        captacion.setOfertaDisponible((ofertaDisponible!=null)
                                      ?Double.parseDouble(ofertaDisponible) 
                                      :null);
        
        String macroMedicion = this.row.getCellValue("Macro Medicion").toString(); 
        if(macroMedicion.equalsIgnoreCase("SI")){
            captacion.setTieneMacroMedicion(Constantes.TRUE_TO_INTEGER);
        }else{
            captacion.setTieneMacroMedicion(Constantes.FALSE_TO_INTEGER);
        }
        
        String nombreEstadoCaptacion = this.row.getCellValue("Estado Captacion").toString();
        Lista estadoCaptacion = parametrosService.getListaByDescripcion(
                                      nombreEstadoCaptacion, ConstantesParametros.
                                                            CATEGORIA_ESTADO_CAPTACION);
        captacion.setEstadoCaptacion((estadoCaptacion!=null)
                                     ?estadoCaptacion.getCodigo()
                                     :null);
        
        String caudalDisegno = this.row.getCellValue("Caudal Diseno").toString();
        caudalDisegno = replaceFloatingSeparator(caudalDisegno);
        captacion.setCaudalDisegno((caudalDisegno!=null)
                                   ?Double.parseDouble(caudalDisegno)
                                   :null);
        
        String continuidad = this.row.getCellValue("Tiene Continuidad").toString(); 
        if(continuidad.equalsIgnoreCase("SI") || continuidad.equalsIgnoreCase("NO")){
            captacion.setContinuidad(continuidad);
        }
        
        String servidumbre = this.row.getCellValue("Tiene Servidumbre").toString(); 
        if(servidumbre.equalsIgnoreCase("SI")){
            captacion.setTieneServidumbre(Constantes.TRUE_TO_INTEGER);
        }else {
            captacion.setTieneServidumbre(Constantes.FALSE_TO_INTEGER);
        }
        
        String sistemaReferenciaTxt = this.row.getCellValue("Sistema Referencia Captacion").toString();
        Lista sistemaReferencia = parametrosService.getListaByDescripcion(
                                      sistemaReferenciaTxt, ConstantesParametros.
                                                            CATEGORIA_SISTEMA_REFERENCIA);
        captacion.setSistemaReferencia(sistemaReferencia.getCodigo());
        
        Double gradosLatitud = Double.parseDouble( 
                                 this.row.getCellValue("Grados Latitud Captacion").toString());
        captacion.setGradosLat(gradosLatitud.intValue());
        
        Double minutosLatitud = Double.parseDouble(
                                  this.row.getCellValue("Minutos Latitud Captacion").toString());
        captacion.setMinutosLat(minutosLatitud.intValue());
        
        Double segundosLatitud = Double.parseDouble(
                                     this.row.getCellValue("Segundos Latitud Captacion").toString());
        captacion.setSegundosLat(segundosLatitud);
        
        
        Double gradosLongitud = Double.parseDouble( 
                                 this.row.getCellValue("Grados Longitud Captacion").toString());
        captacion.setGradosLong(gradosLongitud.intValue());
        
        Double minutosLongitud = Double.parseDouble(
                                  this.row.getCellValue("Minutos Longitud Captacion").toString());
        captacion.setMinutosLong(minutosLongitud.intValue());
        
        Double segundosLongitud = Double.parseDouble(
                                     this.row.getCellValue("Segundos Longitud Captacion").toString());
        captacion.setSegundosLong(segundosLongitud);
        
        Double altitud = Double.parseDouble(
                                    this.row.getCellValue("Altitud Captacion").toString());
        captacion.setAltitud( altitud );
        
        captacion.setDescripcionAcceso(this.row.getCellValue("Descripcion Acceso a Captacion").toString());
        
        return captacion;
    }

    
    /**
     * Retorna los componentes de una captacion con los datos registrados en RowTO
     * @param captacion
     * @return
     * @throws IdeamException
     */
    private List<RurtCaptacionComponentes> getCaptacionComponentes(Captacion captacion)throws IdeamException{
        List<RurtCaptacionComponentes> componentes = new ArrayList<RurtCaptacionComponentes>();
        ParametrosEJB parametrosService = this.getParametrosService();
        
        if(captacion.getTipoFuenteCaptacion()==ConstantesParametros.LISTA_AGUAS_SUPERFICIALES.intValue()){
            
            String aduccion = this.row.getCellValue("Aduccion").toString();
            if(aduccion!=null && aduccion.equalsIgnoreCase("X")){
                //Lista componente = parametrosService.getLista(ConstantesParametros.LISTA_ADUCCION_SUPERFICIAL.intValue());
                RurtCaptacionComponentes captacionComponente = new RurtCaptacionComponentes();
                captacionComponente.setIdComponente(ConstantesParametros.
                                                    LISTA_ADUCCION_SUPERFICIAL.
                                                    intValue());
                captacionComponente.setIdCaptacion(captacion.getCodigo());
                captacionComponente.setCodigoAutoridad(captacion.getCodigoAutoridad());
                componentes.add(captacionComponente);
            }
            
            String desarenador = this.row.getCellValue("Desarenador").toString();
            if(desarenador!=null && desarenador.equalsIgnoreCase("X")){
                //Lista componente= parametrosService.getLista(ConstantesParametros.LISTA_DESARENADOR_SUPERFICIAL.intValue());
                RurtCaptacionComponentes captacionComponente = new RurtCaptacionComponentes();
                captacionComponente.setIdComponente(ConstantesParametros.
                                                    LISTA_DESARENADOR_SUPERFICIAL.
                                                    intValue());
                captacionComponente.setIdCaptacion(captacion.getCodigo());
                captacionComponente.setCodigoAutoridad(captacion.getCodigoAutoridad());
                componentes.add(captacionComponente);
            }
            
            String ptap = this.row.getCellValue("PTAP").toString();
            if(ptap!=null && ptap.equalsIgnoreCase("X")){
                //Lista componente = parametrosService.getLista(ConstantesParametros.LISTA_PLANTA_POTABILIZACION_SUPERFICIAL.intValue());
                RurtCaptacionComponentes captacionComponente = new RurtCaptacionComponentes();
                captacionComponente.setIdComponente(ConstantesParametros.
                                                    LISTA_PLANTA_POTABILIZACION_SUPERFICIAL.
                                                    intValue());
                captacionComponente.setIdCaptacion(captacion.getCodigo());
                captacionComponente.setCodigoAutoridad(captacion.getCodigoAutoridad());
                componentes.add(captacionComponente);
            }
            
            String red = this.row.getCellValue("Red Distribucion").toString();
            if(red!=null && red.equalsIgnoreCase("X")){
                //Lista componente = parametrosService.getLista(ConstantesParametros.LISTA_RED_DISTRIBUCION_SUPERFICIAL.intValue());
                RurtCaptacionComponentes captacionComponente = new RurtCaptacionComponentes();
                captacionComponente.setIdComponente(ConstantesParametros.
                                                    LISTA_RED_DISTRIBUCION_SUPERFICIAL.
                                                    intValue());
                captacionComponente.setIdCaptacion(captacion.getCodigo());
                captacionComponente.setCodigoAutoridad(captacion.getCodigoAutoridad());
                componentes.add(captacionComponente);
            }
            
            String tanque = this.row.getCellValue("Tanque").toString();
            if(tanque!=null && tanque.equalsIgnoreCase("X")){
                //Lista componente = parametrosService.getLista(ConstantesParametros.LISTA_TANQUE_ALMACENAMIENTO_SUPERFICIAL.intValue());
                RurtCaptacionComponentes captacionComponente = new RurtCaptacionComponentes();
                captacionComponente.setIdComponente(ConstantesParametros.
                                                    LISTA_TANQUE_ALMACENAMIENTO_SUPERFICIAL.
                                                    intValue());
                captacionComponente.setIdCaptacion(captacion.getCodigo());
                captacionComponente.setCodigoAutoridad(captacion.getCodigoAutoridad());
                componentes.add(captacionComponente);
            }
            
        }else if(captacion.getTipoFuenteCaptacion()==ConstantesParametros.LISTA_AGUAS_SUBTERRANEAS.intValue()){

            String ptap = this.row.getCellValue("PTAP").toString();
            if(ptap!=null && ptap.equalsIgnoreCase("X")){
                //Lista componente = parametrosService.getLista(ConstantesParametros.LISTA_PLANTA_POTABILIZACION_SUBTERRANEA.intValue());
                RurtCaptacionComponentes captacionComponente = new RurtCaptacionComponentes();
                captacionComponente.setIdComponente(ConstantesParametros.
                                                    LISTA_PLANTA_POTABILIZACION_SUBTERRANEA.
                                                    intValue());
                captacionComponente.setIdCaptacion(captacion.getCodigo());
                captacionComponente.setCodigoAutoridad(captacion.getCodigoAutoridad());
                componentes.add(captacionComponente);
            }
            
            String red = this.row.getCellValue("Red Distribucion").toString();
            if(red!=null && red.equalsIgnoreCase("X")){
                //Lista componente = parametrosService.getLista(ConstantesParametros.LISTA_RED_DISTRIBUCION_SUBTERRANEA.intValue());
                RurtCaptacionComponentes captacionComponente = new RurtCaptacionComponentes();
                captacionComponente.setIdComponente(ConstantesParametros.
                                                    LISTA_RED_DISTRIBUCION_SUBTERRANEA.
                                                    intValue());
                captacionComponente.setIdCaptacion(captacion.getCodigo());
                captacionComponente.setCodigoAutoridad(captacion.getCodigoAutoridad());
                componentes.add(captacionComponente);
            }
            
            String tanque = this.row.getCellValue("Tanque").toString();
            if(tanque!=null && tanque.equalsIgnoreCase("X")){
                //Lista componente = parametrosService.getLista(ConstantesParametros.LISTA_TANQUE_ALMACENAMIENTO_SUBTERRANEA.intValue());
                RurtCaptacionComponentes captacionComponente = new RurtCaptacionComponentes();
                captacionComponente.setIdComponente(ConstantesParametros.
                                                    LISTA_TANQUE_ALMACENAMIENTO_SUBTERRANEA.
                                                    intValue());
                captacionComponente.setIdCaptacion(captacion.getCodigo());
                captacionComponente.setCodigoAutoridad(captacion.getCodigoAutoridad());
                componentes.add(captacionComponente);
            } 
        } 
        return componentes;
    }
    
    /**
     * Retorna el uso domestico de una captacion con los datos registrados en RowTO
     * @param captacion
     * @return
     * @throws IdeamException
     */
    private RurtCaptacionUso getCaptacionUsoDomestico(Captacion captacion)throws IdeamException{
        ParametrosEJB parametrosService = this.getParametrosService();
        RurtCaptacionUso uso = new RurtCaptacionUso();
        String caudal = this.row.getCellValue("Caudal Domestico").toString();
        String permanentes = this.row.getCellValue("Personas Permanentes").toString();
        String transitorias = this.row.getCellValue("Personas Transitorias").toString();
        String aprovechamiento = this.row.getCellValue("Aprovechamiento").toString();
        
        if((caudal==null || caudal.trim().equalsIgnoreCase(""))
                && (permanentes==null || permanentes.trim().equalsIgnoreCase(""))
                && (transitorias==null || transitorias.trim().equalsIgnoreCase(""))
                && (aprovechamiento==null || aprovechamiento.trim().equalsIgnoreCase(""))){
            uso = null;
        }else{
            uso.setTipoUso(ConstantesParametros.LISTA_USO_DOMESTICO.intValue());

            caudal = replaceFloatingSeparator(caudal);
            uso.setCaudalAsignado((caudal!=null) 
                                  ?Double.parseDouble(caudal) 
                                  :null);
            
            
            permanentes = replaceFloatingSeparator(permanentes);
            uso.setCantidadPersonasPermanentes((permanentes!=null) 
                                               ?new Double(permanentes).intValue()
                                               :null);
            
            transitorias = replaceFloatingSeparator(transitorias);
            uso.setCantidadPersonasTransitorias((transitorias!=null) 
                                               ?new Double(transitorias).intValue() 
                                               :null);
            
            aprovechamiento = replaceFloatingSeparator(aprovechamiento);
            uso.setAprovechamiento((aprovechamiento!=null) 
                                               ?Double.parseDouble(aprovechamiento) 
                                               :null);
        }
        return uso;
    }
    
    /**
     * Retorna el uso agricola de una captacion con los datos registrados en RowTO
     * @param captacion
     * @return
     * @throws IdeamException
     */
    private RurtCaptacionUso getCaptacionUsoAgricola(Captacion captacion)throws IdeamException{
        ParametrosEJB parametrosService = this.getParametrosService();
        RurtCaptacionUso uso = new RurtCaptacionUso();
        
        
        String tipoCultivo = this.row.getCellValue("Tipo Cultivo").toString();
        String caudal = this.row.getCellValue("Caudal Agricola").toString();
        String produccion = this.row.getCellValue("Produccion").toString();
        String eficiencia = this.row.getCellValue("Eficiencia").toString();
        String area = this.row.getCellValue("Area Cultivada").toString();
        
        if((tipoCultivo==null || tipoCultivo.trim().equalsIgnoreCase(""))
               &&(caudal==null || caudal.trim().equalsIgnoreCase(""))
               &&(produccion==null || produccion.trim().equalsIgnoreCase(""))
               &&(eficiencia==null || eficiencia.trim().equalsIgnoreCase(""))
               &&(area==null || area.trim().equalsIgnoreCase(""))){
            
            uso = null;
        }else{
            uso.setTipoUso(ConstantesParametros.LISTA_USO_AGRICOLA.intValue());
            
            uso.setTipo(tipoCultivo);
            
            caudal = replaceFloatingSeparator(caudal);
            uso.setCaudalAsignado((caudal!=null) 
                                  ?Double.parseDouble(caudal) 
                                  :null);
            
            produccion = replaceFloatingSeparator(produccion);
            uso.setProduccion((produccion!=null) 
                                  ?Double.parseDouble(produccion) 
                                  :null);
            
            eficiencia = replaceFloatingSeparator(eficiencia);
            uso.setEficiencia((eficiencia!=null) 
                                  ?Double.parseDouble(eficiencia) 
                                  :null);
            
            area = replaceFloatingSeparator(area);
            uso.setArea((area!=null) ?Double.parseDouble(area) :null);
        }
        
        return uso;
    }
    
    /**
     * Retorna el uso pecuario de una captacion con los datos registrados en RowTO
     * @param captacion
     * @return
     * @throws IdeamException
     */
    private RurtCaptacionUso getCaptacionUsoPecuario(Captacion captacion)throws IdeamException{
        ParametrosEJB parametrosService = this.getParametrosService();
        RurtCaptacionUso uso = new RurtCaptacionUso();
        
        
        String tipoAnimal = this.row.getCellValue("Tipo Animal Pecuario").toString();
        String caudal = this.row.getCellValue("Caudal Pecuario").toString();
        String animales = this.row.getCellValue("Numero Animales Pecuario").toString();
        
        if((tipoAnimal==null || tipoAnimal.trim().equalsIgnoreCase(""))
               &&(caudal==null || caudal.trim().equalsIgnoreCase(""))
               &&(animales==null || animales.trim().equalsIgnoreCase(""))){
            
            uso = null;
        }else{
            uso.setTipoUso(ConstantesParametros.LISTA_USO_PECUARIO.intValue());
            
            uso.setTipo(tipoAnimal);
            
            caudal = replaceFloatingSeparator(caudal);
            uso.setCaudalAsignado((caudal!=null) 
                                  ?Double.parseDouble(caudal) 
                                  :null);
            
            animales = replaceFloatingSeparator(animales);
            uso.setCantidadAnimales((animales!=null) 
                                  ?new Double(animales).longValue()
                                  :null);
        }
        
        return uso;
    }
    
    /**
     * Retorna el uso acuicola de una captacion con los datos registrados en RowTO
     * @param captacion
     * @return
     * @throws IdeamException
     */
    private RurtCaptacionUso getCaptacionUsoAcuicola(Captacion captacion)throws IdeamException{
        ParametrosEJB parametrosService = this.getParametrosService();
        RurtCaptacionUso uso = new RurtCaptacionUso();
        
        String tipoAnimal = this.row.getCellValue("Tipo Animal Acuicola").toString();
        String caudal = this.row.getCellValue("Caudal Acuicola").toString();
        String animales = this.row.getCellValue("Numero Animales Acuicola").toString();
        
        if((tipoAnimal==null || tipoAnimal.trim().equalsIgnoreCase(""))
               &&(caudal==null || caudal.trim().equalsIgnoreCase(""))
               &&(animales==null || animales.trim().equalsIgnoreCase(""))){
            
            uso = null;
        }else{
            uso.setTipoUso(ConstantesParametros.LISTA_USO_ACUICOLA.intValue());
            
            uso.setTipo(tipoAnimal);
            
            caudal = replaceFloatingSeparator(caudal);
            uso.setCaudalAsignado((caudal!=null) 
                                  ?Double.parseDouble(caudal) 
                                  :null);
            
            animales = replaceFloatingSeparator(animales);
            uso.setCantidadAnimales((animales!=null) 
                                  ?new Double(animales).longValue()
                                  :null);
        }
        
        return uso;
    }
    
    /**
     * Retorna el uso otro de una captacion con los datos registrados en RowTO
     * @param captacion
     * @return
     * @throws IdeamException
     */
    private RurtCaptacionUso getCaptacionUsoOtro(Captacion captacion)throws IdeamException{
        ParametrosEJB parametrosService = this.getParametrosService();
        RurtCaptacionUso uso = new RurtCaptacionUso();

        String nombreTipoUso = this.row.getCellValue("Otro Tipo Uso").toString();
        String descripcion = this.row.getCellValue("Descripcion Otro Uso").toString();
        String caudal = this.row.getCellValue("Caudal Otro").toString();
        
        if((nombreTipoUso==null || nombreTipoUso.trim().equalsIgnoreCase(""))
               &&(caudal==null || caudal.trim().equalsIgnoreCase(""))
               &&(descripcion==null || descripcion.trim().equalsIgnoreCase(""))){
            
            uso = null;
        }else{
            uso.setTipoUso(ConstantesParametros.LISTA_USO_OTRO.intValue());
            Lista tipoUso = 
                parametrosService.getListaByDescripcion(nombreTipoUso, 
                                                    ConstantesParametros.
                                                     CATEGORIA_OTROS_USOS_AGUA);

            uso.setTipo((tipoUso!=null) ?tipoUso.getCodigo().toString() :null);
            
            caudal = replaceFloatingSeparator(caudal);
            uso.setCaudalAsignado((caudal!=null) 
                                  ?Double.parseDouble(caudal) 
                                  :null);
            
            uso.setDescripcion(descripcion);
        }
        return uso;
    }
    
    private String replaceFloatingSeparator(String tmp){
        if(tmp!=null && !tmp.trim().equalsIgnoreCase("")){
            if(tmp.indexOf(",")!=-1){
                tmp = tmp.replaceAll(",",".");
            }
            try{
                Double valor = Double.parseDouble(tmp);
            }catch(Exception e){
                return null;
            }
        }else{
            tmp=null;
        }
        return tmp;
    }
}
