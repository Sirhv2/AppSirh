package co.gov.ideam.sirh.usuarios.agua.model;

import co.gov.ideam.sirh.parametros.model.Autoridades;

import co.gov.ideam.sirh.parametros.model.Lista;

import co.gov.ideam.sirh.util.Constantes;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.id.SequenceGenerator;

@Entity
@Table(name = "subtt_funias")
@NamedQueries({
    @NamedQuery(name = "SubttFunias.findAll", query = "SELECT s FROM SubttFunias s"),
    @NamedQuery(name = "SubttFunias.findById", query = "SELECT s FROM SubttFunias s WHERE s.id = :id"),
    @NamedQuery(name = "SubttFunias.findByTipoFunias", query = "SELECT s FROM SubttFunias s WHERE s.tipoFunias = :tipoFunias"),
    @NamedQuery(name = "SubttFunias.findByLocalizacionTopografica", query = "SELECT s FROM SubttFunias s WHERE s.localizacionTopografica = :localizacionTopografica"),
    @NamedQuery(name = "SubttFunias.findByUnidadGeologica", query = "SELECT s FROM SubttFunias s WHERE s.unidadGeologica = :unidadGeologica"),
    @NamedQuery(name = "SubttFunias.findByLitologiaSuperficial", query = "SELECT s FROM SubttFunias s WHERE s.litologiaSuperficial = :litologiaSuperficial"),
    @NamedQuery(name = "SubttFunias.findByGeoforma", query = "SELECT s FROM SubttFunias s WHERE s.geoforma = :geoforma"),
    @NamedQuery(name = "SubttFunias.findByEstructura", query = "SELECT s FROM SubttFunias s WHERE s.estructura = :estructura"),
    @NamedQuery(name = "SubttFunias.findByNombreEstructura", query = "SELECT s FROM SubttFunias s WHERE s.nombreEstructura = :nombreEstructura"),
    @NamedQuery(name = "SubttFunias.findByFechaConstruccion", query = "SELECT s FROM SubttFunias s WHERE s.fechaConstruccion = :fechaConstruccion"),
    @NamedQuery(name = "SubttFunias.findByDiametroInterior", query = "SELECT s FROM SubttFunias s WHERE s.diametroInterior = :diametroInterior"),
    @NamedQuery(name = "SubttFunias.findByDiametroExterior", query = "SELECT s FROM SubttFunias s WHERE s.diametroExterior = :diametroExterior"),
    @NamedQuery(name = "SubttFunias.findByDiametroPerforacion", query = "SELECT s FROM SubttFunias s WHERE s.diametroPerforacion = :diametroPerforacion"),
    @NamedQuery(name = "SubttFunias.findByMaterialUsado", query = "SELECT s FROM SubttFunias s WHERE s.materialUsado = :materialUsado"),
    @NamedQuery(name = "SubttFunias.findByMetodoConstruccion", query = "SELECT s FROM SubttFunias s WHERE s.metodoConstruccion = :metodoConstruccion"),
    @NamedQuery(name = "SubttFunias.findByTratamientoEspecial", query = "SELECT s FROM SubttFunias s WHERE s.tratamientoEspecial = :tratamientoEspecial"),
    @NamedQuery(name = "SubttFunias.findByFluidoUsado", query = "SELECT s FROM SubttFunias s WHERE s.fluidoUsado = :fluidoUsado"),
    @NamedQuery(name = "SubttFunias.findByCompaniaPerforadora", query = "SELECT s FROM SubttFunias s WHERE s.companiaPerforadora = :companiaPerforadora"),
    @NamedQuery(name = "SubttFunias.findByEstaColmatado", query = "SELECT s FROM SubttFunias s WHERE s.estaColmatado = :estaColmatado"),
    @NamedQuery(name = "SubttFunias.findByEstaColapsado", query = "SELECT s FROM SubttFunias s WHERE s.estaColapsado = :estaColapsado"),
    @NamedQuery(name = "SubttFunias.findByProfundidadEntubado", query = "SELECT s FROM SubttFunias s WHERE s.profundidadEntubado = :profundidadEntubado"),
    @NamedQuery(name = "SubttFunias.findByProfundidadPerforacion", query = "SELECT s FROM SubttFunias s WHERE s.profundidadPerforacion = :profundidadPerforacion"),
    @NamedQuery(name = "SubttFunias.findByCantidadGravilla", query = "SELECT s FROM SubttFunias s WHERE s.cantidadGravilla = :cantidadGravilla"),
    @NamedQuery(name = "SubttFunias.findByNivelEngravillado", query = "SELECT s FROM SubttFunias s WHERE s.nivelEngravillado = :nivelEngravillado"),
    @NamedQuery(name = "SubttFunias.findByDiametroPromedioGravilla", query = "SELECT s FROM SubttFunias s WHERE s.diametroPromedioGravilla = :diametroPromedioGravilla"),
    @NamedQuery(name = "SubttFunias.findByEmbalse", query = "SELECT s FROM SubttFunias s WHERE s.embalse = :embalse"),
    @NamedQuery(name = "SubttFunias.findByCapacidadEmbalse", query = "SELECT s FROM SubttFunias s WHERE s.capacidadEmbalse = :capacidadEmbalse"),
    @NamedQuery(name = "SubttFunias.findByTanque", query = "SELECT s FROM SubttFunias s WHERE s.tanque = :tanque"),
    @NamedQuery(name = "SubttFunias.findByCapacidadTanque", query = "SELECT s FROM SubttFunias s WHERE s.capacidadTanque = :capacidadTanque"),
    @NamedQuery(name = "SubttFunias.findByAlberca", query = "SELECT s FROM SubttFunias s WHERE s.alberca = :alberca"),
    @NamedQuery(name = "SubttFunias.findByCapacidadAlberca", query = "SELECT s FROM SubttFunias s WHERE s.capacidadAlberca = :capacidadAlberca"),
    @NamedQuery(name = "SubttFunias.findByOtro", query = "SELECT s FROM SubttFunias s WHERE s.otro = :otro"),
    @NamedQuery(name = "SubttFunias.findByCapacidadOtro", query = "SELECT s FROM SubttFunias s WHERE s.capacidadOtro = :capacidadOtro"),
    @NamedQuery(name = "SubttFunias.findByFechaMedicion", query = "SELECT s FROM SubttFunias s WHERE s.fechaMedicion = :fechaMedicion"),
    @NamedQuery(name = "SubttFunias.findByEquipoUsado", query = "SELECT s FROM SubttFunias s WHERE s.equipoUsado = :equipoUsado"),
    @NamedQuery(name = "SubttFunias.findByTipoRegistro", query = "SELECT s FROM SubttFunias s WHERE s.tipoRegistro = :tipoRegistro"),
    @NamedQuery(name = "SubttFunias.findByCompaniaEjecutora", query = "SELECT s FROM SubttFunias s WHERE s.companiaEjecutora = :companiaEjecutora"),
    @NamedQuery(name = "SubttFunias.findByResistividadLodo", query = "SELECT s FROM SubttFunias s WHERE s.resistividadLodo = :resistividadLodo"),
    @NamedQuery(name = "SubttFunias.findByTemperaturaLodo", query = "SELECT s FROM SubttFunias s WHERE s.temperaturaLodo = :temperaturaLodo"),
    @NamedQuery(name = "SubttFunias.findByProfundidadRegistro", query = "SELECT s FROM SubttFunias s WHERE s.profundidadRegistro = :profundidadRegistro"),
    @NamedQuery(name = "SubttFunias.findByCalidadRegistro", query = "SELECT s FROM SubttFunias s WHERE s.calidadRegistro = :calidadRegistro"),
    @NamedQuery(name = "SubttFunias.findByMetodoExplotacion", query = "SELECT s FROM SubttFunias s WHERE s.metodoExplotacion = :metodoExplotacion"),
    @NamedQuery(name = "SubttFunias.findByTipoEnergia", query = "SELECT s FROM SubttFunias s WHERE s.tipoEnergia = :tipoEnergia"),
    @NamedQuery(name = "SubttFunias.findByTipoAcabado", query = "SELECT s FROM SubttFunias s WHERE s.tipoAcabado = :tipoAcabado"),
    @NamedQuery(name = "SubttFunias.findByDiametro", query = "SELECT s FROM SubttFunias s WHERE s.diametro = :diametro"),
    @NamedQuery(name = "SubttFunias.findByLongitud", query = "SELECT s FROM SubttFunias s WHERE s.longitud = :longitud"),
    @NamedQuery(name = "SubttFunias.findByMaterial", query = "SELECT s FROM SubttFunias s WHERE s.material = :material"),
    @NamedQuery(name = "SubttFunias.findByLetrinaCercana", query = "SELECT s FROM SubttFunias s WHERE s.letrinaCercana = :letrinaCercana"),
    @NamedQuery(name = "SubttFunias.findByDistanciaLetrina", query = "SELECT s FROM SubttFunias s WHERE s.distanciaLetrina = :distanciaLetrina"),
    @NamedQuery(name = "SubttFunias.findByAguaEstancada", query = "SELECT s FROM SubttFunias s WHERE s.aguaEstancada = :aguaEstancada"),
    @NamedQuery(name = "SubttFunias.findByDistancia", query = "SELECT s FROM SubttFunias s WHERE s.distancia = :distancia"),
    @NamedQuery(name = "SubttFunias.findByCriaderoGanado", query = "SELECT s FROM SubttFunias s WHERE s.criaderoGanado = :criaderoGanado"),
    @NamedQuery(name = "SubttFunias.findByDistanciaCriadero", query = "SELECT s FROM SubttFunias s WHERE s.distanciaCriadero = :distanciaCriadero"),
    @NamedQuery(name = "SubttFunias.findByFiltracionAgua", query = "SELECT s FROM SubttFunias s WHERE s.filtracionAgua = :filtracionAgua"),
    @NamedQuery(name = "SubttFunias.findByDistanciaFiltracion", query = "SELECT s FROM SubttFunias s WHERE s.distanciaFiltracion = :distanciaFiltracion"),
    @NamedQuery(name = "SubttFunias.findByCubiertaAdecuada", query = "SELECT s FROM SubttFunias s WHERE s.cubiertaAdecuada = :cubiertaAdecuada"),
    @NamedQuery(name = "SubttFunias.findByPisoCemento", query = "SELECT s FROM SubttFunias s WHERE s.pisoCemento = :pisoCemento"),
    @NamedQuery(name = "SubttFunias.findBySelloSanitario", query = "SELECT s FROM SubttFunias s WHERE s.selloSanitario = :selloSanitario"),
    @NamedQuery(name = "SubttFunias.findByCercoAdecuado", query = "SELECT s FROM SubttFunias s WHERE s.cercoAdecuado = :cercoAdecuado"),
    @NamedQuery(name = "SubttFunias.findByCampoInfiltracion", query = "SELECT s FROM SubttFunias s WHERE s.campoInfiltracion = :campoInfiltracion"),
    @NamedQuery(name = "SubttFunias.findByDistanciaCampo", query = "SELECT s FROM SubttFunias s WHERE s.distanciaCampo = :distanciaCampo"),
    @NamedQuery(name = "SubttFunias.findByCementerio", query = "SELECT s FROM SubttFunias s WHERE s.cementerio = :cementerio"),
    @NamedQuery(name = "SubttFunias.findByDistanciaCementerio", query = "SELECT s FROM SubttFunias s WHERE s.distanciaCementerio = :distanciaCementerio"),
    @NamedQuery(name = "SubttFunias.findByEstacionServicio", query = "SELECT s FROM SubttFunias s WHERE s.estacionServicio = :estacionServicio"),
    @NamedQuery(name = "SubttFunias.findByDistanciaEstacionServicio", query = "SELECT s FROM SubttFunias s WHERE s.distanciaEstacionServicio = :distanciaEstacionServicio"),
    @NamedQuery(name = "SubttFunias.findByLagunasOxidacion", query = "SELECT s FROM SubttFunias s WHERE s.lagunasOxidacion = :lagunasOxidacion"),
    @NamedQuery(name = "SubttFunias.findByDistanciaLagunas", query = "SELECT s FROM SubttFunias s WHERE s.distanciaLagunas = :distanciaLagunas"),
    @NamedQuery(name = "SubttFunias.findByLavaderoVehiculos", query = "SELECT s FROM SubttFunias s WHERE s.lavaderoVehiculos = :lavaderoVehiculos"),
    @NamedQuery(name = "SubttFunias.findByDistanciaLavadero", query = "SELECT s FROM SubttFunias s WHERE s.distanciaLavadero = :distanciaLavadero"),
    @NamedQuery(name = "SubttFunias.findByPlantasSacrificio", query = "SELECT s FROM SubttFunias s WHERE s.plantasSacrificio = :plantasSacrificio"),
    @NamedQuery(name = "SubttFunias.findByDistanciaPlantas", query = "SELECT s FROM SubttFunias s WHERE s.distanciaPlantas = :distanciaPlantas"),
    @NamedQuery(name = "SubttFunias.findByPozosAbandonados", query = "SELECT s FROM SubttFunias s WHERE s.pozosAbandonados = :pozosAbandonados"),
    @NamedQuery(name = "SubttFunias.findByDistanciaPozos", query = "SELECT s FROM SubttFunias s WHERE s.distanciaPozos = :distanciaPozos"),
    @NamedQuery(name = "SubttFunias.findByResiduosAgricolas", query = "SELECT s FROM SubttFunias s WHERE s.residuosAgricolas = :residuosAgricolas"),
    @NamedQuery(name = "SubttFunias.findByResiduosDomestico", query = "SELECT s FROM SubttFunias s WHERE s.residuosDomestico = :residuosDomestico"),
    @NamedQuery(name = "SubttFunias.findByResiduosGanaderia", query = "SELECT s FROM SubttFunias s WHERE s.residuosGanaderia = :residuosGanaderia"),
    @NamedQuery(name = "SubttFunias.findByResiduosHospitalarios", query = "SELECT s FROM SubttFunias s WHERE s.residuosHospitalarios = :residuosHospitalarios"),
    @NamedQuery(name = "SubttFunias.findByResiduosIndustriales", query = "SELECT s FROM SubttFunias s WHERE s.residuosIndustriales = :residuosIndustriales"),
    @NamedQuery(name = "SubttFunias.findByResiduosMineros", query = "SELECT s FROM SubttFunias s WHERE s.residuosMineros = :residuosMineros"),
    @NamedQuery(name = "SubttFunias.findByBotaceroAbierto", query = "SELECT s FROM SubttFunias s WHERE s.botaceroAbierto = :botaceroAbierto"),
    @NamedQuery(name = "SubttFunias.findByCompostaje", query = "SELECT s FROM SubttFunias s WHERE s.compostaje = :compostaje"),
    @NamedQuery(name = "SubttFunias.findByIncineracion", query = "SELECT s FROM SubttFunias s WHERE s.incineracion = :incineracion"),
    @NamedQuery(name = "SubttFunias.findByReciclaje", query = "SELECT s FROM SubttFunias s WHERE s.reciclaje = :reciclaje"),
    @NamedQuery(name = "SubttFunias.findByCaptacion", query = "SELECT s FROM SubttFunias s WHERE s.idCaptacion = :id"),
    @NamedQuery(name = "SubttFunias.findByCaptacionTipoFunias",
                query = "SELECT s FROM SubttFunias s WHERE s.idCaptacion = :idCaptacion AND s.tipoFunias = :tipoFunias")
})
public class SubttFunias implements Serializable {
    @GenericGenerator(name = "funias_generator", 
                      strategy = "co.gov.ideam.sirh.usuarios.agua.model.IdeamSirhGenerator",
                      parameters = {@Parameter(name = "AutoridadAmbientalId", value = "codigoAutoridad"),
                                    @Parameter(name = "Codigo", value = "id"),
                                    @Parameter(name = SequenceGenerator.SEQUENCE,  value= "SEQ_FUNIAS")
                                    })
    
    @Id
    @Column(name = "id", nullable=false)
   // @GeneratedValue(generator = "funias_generator") 
    private Long id;
    @Column(name = "tipo_funias")
    private Integer tipoFunias;
    @Column(name = "localizacion_topografica")
    private Integer localizacionTopografica;
    @Column(name = "unidad_geologica")
    private String unidadGeologica;
    @Column(name = "litologia_superficial")
    private String litologiaSuperficial;
    @Column(name = "geoforma")
    private Integer geoforma;
    @Column(name = "estructura")
    private Integer estructura;
    @Column(name = "nombre_estructura")
    private String nombreEstructura;
    @Column(name = "fecha_construccion")
    private Date fechaConstruccion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "diametro_interior")
    private Double diametroInterior;
    @Column(name = "diametro_exterior")
    private Double diametroExterior;
    @Column(name = "diametro_perforacion")
    private Double diametroPerforacion;
    @Column(name = "material_usado")
    private Integer materialUsado;
    @Column(name = "metodo_construccion")
    private Integer metodoConstruccion;
    @Column(name = "tratamiento_especial")
    private Integer tratamientoEspecial;
    @Column(name = "fluido_usado")
    private Integer fluidoUsado;
    @Column(name = "compania_perforadora")
    private String companiaPerforadora;
    @Column(name = "esta_colmatado")
    private Integer estaColmatado;
    @Column(name = "esta_colapsado")
    private Integer estaColapsado;
    @Column(name = "profundidad_entubado")
    private Double profundidadEntubado;
    @Column(name = "profundidad_perforacion")
    private Double profundidadPerforacion;
    @Column(name = "cantidad_gravilla")
    private Double cantidadGravilla;
    @Column(name = "nivel_engravillado")
    private Double nivelEngravillado;
    @Column(name = "diametro_promedio_gravilla")
    private Double diametroPromedioGravilla;
    @Column(name = "embalse")
    private Integer embalse;
    @Column(name = "capacidad_embalse")
    private Double capacidadEmbalse;
    @Column(name = "tanque")
    private Integer tanque;
    @Column(name = "capacidad_tanque")
    private Double capacidadTanque;
    @Column(name = "alberca")
    private Integer alberca;
    @Column(name = "capacidad_alberca")
    private Double capacidadAlberca;
    @Column(name = "otro")
    private Integer otro;
    @Column(name = "capacidad_otro")
    private Double capacidadOtro;
    @Column(name = "fecha_medicion")
    private Date fechaMedicion;
    @Column(name = "equipo_usado")
    private String equipoUsado;
    @Column(name = "tipo_registro")
    private Integer tipoRegistro;
    @Column(name = "compania_ejecutora")
    private String companiaEjecutora;
    @Column(name = "resistividad_lodo")
    private Double resistividadLodo;
    @Column(name = "temperatura_lodo")
    private Double temperaturaLodo;
    @Column(name = "profundidad_registro")
    private Double profundidadRegistro;
    @Column(name = "calidad_registro")
    private Integer calidadRegistro;
    @Column(name = "metodo_explotacion")
    private Integer metodoExplotacion;
    @Column(name = "tipo_energia")
    private Integer tipoEnergia;
    @Column(name = "tipo_acabado")
    private Integer tipoAcabado;
    @Column(name = "diametro")
    private Double diametro;
    @Column(name = "longitud")
    private Double longitud;
    @Column(name = "material")
    private String material;
    @Column(name = "letrina_cercana")
    private Integer letrinaCercana;
    @Column(name = "distancia_letrina")
    private Double distanciaLetrina;
    @Column(name = "agua_estancada")
    private Integer aguaEstancada;
    @Column(name = "distancia")
    private Double distancia;
    @Column(name = "criadero_ganado")
    private Integer criaderoGanado;
    @Column(name = "distancia_criadero")
    private Double distanciaCriadero;
    @Column(name = "filtracion_agua")
    private Integer filtracionAgua;
    @Column(name = "distancia_filtracion")
    private Double distanciaFiltracion;
    @Column(name = "cubierta_adecuada")
    private Integer cubiertaAdecuada;
    @Column(name = "piso_cemento")
    private Integer pisoCemento;
    @Column(name = "sello_sanitario")
    private Integer selloSanitario;
    @Column(name = "cerco_adecuado")
    private Integer cercoAdecuado;
    @Column(name = "campo_infiltracion")
    private Integer campoInfiltracion;
    @Column(name = "distancia_campo")
    private Double distanciaCampo;
    @Column(name = "cementerio")
    private Integer cementerio;
    @Column(name = "distancia_cementerio")
    private Double distanciaCementerio;
    @Column(name = "estacion_servicio")
    private Integer estacionServicio;
    @Column(name = "distancia_estacion_servicio")
    private Double distanciaEstacionServicio;
    @Column(name = "lagunas_oxidacion")
    private Integer lagunasOxidacion;
    @Column(name = "distancia_lagunas")
    private Double distanciaLagunas;
    @Column(name = "lavadero_vehiculos")
    private Integer lavaderoVehiculos;
    @Column(name = "distancia_lavadero")
    private Double distanciaLavadero;
    @Column(name = "plantas_sacrificio")
    private Integer plantasSacrificio;
    @Column(name = "distancia_plantas")
    private Double distanciaPlantas;
    @Column(name = "pozos_abandonados")
    private Integer pozosAbandonados;
    @Column(name = "distancia_pozos")
    private Double distanciaPozos;
    @Column(name = "residuos_agricolas")
    private Integer residuosAgricolas;
    @Column(name = "residuos_domestico")
    private Integer residuosDomestico;
    @Column(name = "residuos_ganaderia")
    private Integer residuosGanaderia;
    @Column(name = "residuos_hospitalarios")
    private Integer residuosHospitalarios;
    @Column(name = "residuos_industriales")
    private Integer residuosIndustriales;
    @Column(name = "residuos_mineros")
    private Integer residuosMineros;
    @Column(name = "botacero_abierto")
    private Integer botaceroAbierto;
    @Column(name = "compostaje")
    private Integer compostaje;
    @Column(name = "incineracion")
    private Integer incineracion;
    @Column(name = "reciclaje")
    private Integer reciclaje;
    @Column(name = "id_captacion")
    private Long idCaptacion;
    @Column(name = "bomba_clase")
    private String bombaClase;
    @Column(name = "bomba_tipo")
    private String bombaTipo;
    @Column(name = "bomba_potencia")
    private Double bombaPotencia;
    @Column(name = "bomba_profundidad_succion")
    private Double bombaProfundidadSuccion;
    @Column(name = "id_tipo")
    private Integer idTipo;//tipo manantial
    @Column(name = "id_permanencia")
    private Integer idPermanencia;
    @Column(name = "id_surgencia")
    private Integer idSurgencia;
    @Column(name = "observacion")
    private String observacion;
    @Column(name = "id_metodo_medida")
    private Integer idMetodoMedida;
    @Column(name = "volumen_sistema")
    private Double volumenSistema;
    @Column(name = "tiempo_llenado")
    private Double tiempoLlenado;
    @Column(name = "caudal_estimado")
    private Double caudalEstimado;
    @Column(name = "acceso")
    private String acceso;
    
    @Transient
    private List<SubttFuniasDocumentos> subttFuniasDocumentosList;
    @Transient
    private Long codigoAutoridad;
    @Transient
    private Autoridades autoridad;    
    @Transient
    private Lista objLocalizacionTopografica;
    @Transient
    private Lista objGeoforma;
    @Transient
    private Lista objEstructura;
    @Transient
    private Lista objMaterialUsado;
    @Transient
    private Lista objMetodoConstruccion;
    @Transient
    private Lista objTratamientoEspecial;
    @Transient
    private Lista objFluidoUsado;
    @Transient
    private Lista objTipoRegistro;
    @Transient
    private Lista objMetodoExplotacion;
    @Transient
    private Lista objTipoEnergia;
    @Transient
    private Lista objTipoAcabado;
    @Transient
    private Lista objTipoManantial;
    @Transient
    private Lista objPermanencia;
    @Transient
    private Lista objSurgencia;
    @Transient
    private Lista objMetodoMedida;
    @Transient
    private boolean colmatadaAux;
    @Transient
    private boolean colapsadaAux;
    @Transient
    private boolean embalseaAux;
    @Transient
    private boolean tanqueAux;
    @Transient
    private boolean albercaAux;
    @Transient
    private boolean otroAux;
    @Transient
    private boolean letrinaAux;
    @Transient
    private boolean aguaEstancadaAux;
    @Transient
    private boolean criaderoGanadoAux;
    @Transient
    private boolean filtracionAguaAux;
    @Transient
    private boolean cubiertaAdecuadaAux;
    @Transient
    private boolean pisoCementoAux;
    @Transient
    private boolean selloSanitarioAux;
    @Transient
    private boolean campoInfiltracionAux;
    @Transient
    private boolean cercoAdecuadoAux;
    @Transient
    private boolean cementerioAux;
    @Transient
    private boolean estacionServicioAux;
    @Transient
    private boolean lagunasOxidacionAux;
    @Transient
    private boolean lavaderoVehiculosAux;
    @Transient
    private boolean plantasSacrificioAux;
    @Transient
    private boolean pozosAbandonadosAux;
    @Transient
    private boolean residuosAgricolasAux;
    @Transient
    private boolean residuosDomesticoAux;
    @Transient
    private boolean residuosGanaderiaAux;
    @Transient
    private boolean residuosHospitalariosAux;
    @Transient
    private boolean residuosIndustrialesAux;
    @Transient
    private boolean residuosMinerosAux;
    @Transient
    private boolean botaceroAbiertoAux;
    @Transient
    private boolean compostajeAux;
    @Transient
    private boolean incineracionAux;
    @Transient
    private boolean reciclajeAux;
    
    
    public SubttFunias() {
    }

    public SubttFunias(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTipoFunias() {
        return tipoFunias;
    }

    public void setTipoFunias(Integer tipoFunias) {
        this.tipoFunias = tipoFunias;
    }

    public Integer getLocalizacionTopografica() {
        return localizacionTopografica;
    }

    public void setLocalizacionTopografica(Integer localizacionTopografica) {
        this.localizacionTopografica = localizacionTopografica;
    }

    public String getUnidadGeologica() {
        return unidadGeologica;
    }

    public void setUnidadGeologica(String unidadGeologica) {
        this.unidadGeologica = unidadGeologica;
    }

    public String getLitologiaSuperficial() {
        return litologiaSuperficial;
    }

    public void setLitologiaSuperficial(String litologiaSuperficial) {
        this.litologiaSuperficial = litologiaSuperficial;
    }

    public Integer getGeoforma() {
        return geoforma;
    }

    public void setGeoforma(Integer geoforma) {
        this.geoforma = geoforma;
    }

    public Integer getEstructura() {
        return estructura;
    }

    public void setEstructura(Integer estructura) {
        this.estructura = estructura;
    }

    public String getNombreEstructura() {
        return nombreEstructura;
    }

    public void setNombreEstructura(String nombreEstructura) {
        this.nombreEstructura = nombreEstructura;
    }

    public Date getFechaConstruccion() {
        return fechaConstruccion;
    }

    public void setFechaConstruccion(Date fechaConstruccion) {
        this.fechaConstruccion = fechaConstruccion;
    }

    public Double getDiametroInterior() {
        return diametroInterior;
    }

    public void setDiametroInterior(Double diametroInterior) {
        this.diametroInterior = diametroInterior;
    }

    public Double getDiametroExterior() {
        return diametroExterior;
    }

    public void setDiametroExterior(Double diametroExterior) {
        this.diametroExterior = diametroExterior;
    }

    public Double getDiametroPerforacion() {
        return diametroPerforacion;
    }

    public void setDiametroPerforacion(Double diametroPerforacion) {
        this.diametroPerforacion = diametroPerforacion;
    }

    public Integer getMaterialUsado() {
        return materialUsado;
    }

    public void setMaterialUsado(Integer materialUsado) {
        this.materialUsado = materialUsado;
    }

    public Integer getMetodoConstruccion() {
        return metodoConstruccion;
    }

    public void setMetodoConstruccion(Integer metodoConstruccion) {
        this.metodoConstruccion = metodoConstruccion;
    }

    public Integer getTratamientoEspecial() {
        return tratamientoEspecial;
    }

    public void setTratamientoEspecial(Integer tratamientoEspecial) {
        this.tratamientoEspecial = tratamientoEspecial;
    }

    public Integer getFluidoUsado() {
        return fluidoUsado;
    }

    public void setFluidoUsado(Integer fluidoUsado) {
        this.fluidoUsado = fluidoUsado;
    }

    public String getCompaniaPerforadora() {
        return companiaPerforadora;
    }

    public void setCompaniaPerforadora(String companiaPerforadora) {
        this.companiaPerforadora = companiaPerforadora;
    }

    public Integer getEstaColmatado() {
        return estaColmatado;
    }

    public void setEstaColmatado(Integer estaColmatado) {
        this.estaColmatado = estaColmatado;
    }

    public Integer getEstaColapsado() {
        return estaColapsado;
    }

    public void setEstaColapsado(Integer estaColapsado) {
        this.estaColapsado = estaColapsado;
    }

    public Double getProfundidadEntubado() {
        return profundidadEntubado;
    }

    public void setProfundidadEntubado(Double profundidadEntubado) {
        this.profundidadEntubado = profundidadEntubado;
    }

    public Double getProfundidadPerforacion() {
        return profundidadPerforacion;
    }

    public void setProfundidadPerforacion(Double profundidadPerforacion) {
        this.profundidadPerforacion = profundidadPerforacion;
    }

    public Double getCantidadGravilla() {
        return cantidadGravilla;
    }

    public void setCantidadGravilla(Double cantidadGravilla) {
        this.cantidadGravilla = cantidadGravilla;
    }

    public Double getNivelEngravillado() {
        return nivelEngravillado;
    }

    public void setNivelEngravillado(Double nivelEngravillado) {
        this.nivelEngravillado = nivelEngravillado;
    }

    public Double getDiametroPromedioGravilla() {
        return diametroPromedioGravilla;
    }

    public void setDiametroPromedioGravilla(Double diametroPromedioGravilla) {
        this.diametroPromedioGravilla = diametroPromedioGravilla;
    }

    public Integer getEmbalse() {
        return embalse;
    }

    public void setEmbalse(Integer embalse) {
        this.embalse = embalse;
    }

    public Double getCapacidadEmbalse() {
        return capacidadEmbalse;
    }

    public void setCapacidadEmbalse(Double capacidadEmbalse) {
        this.capacidadEmbalse = capacidadEmbalse;
    }

    public Integer getTanque() {
        return tanque;
    }

    public void setTanque(Integer tanque) {
        this.tanque = tanque;
    }

    public Double getCapacidadTanque() {
        return capacidadTanque;
    }

    public void setCapacidadTanque(Double capacidadTanque) {
        this.capacidadTanque = capacidadTanque;
    }

    public Integer getAlberca() {
        return alberca;
    }

    public void setAlberca(Integer alberca) {
        this.alberca = alberca;
    }

    public Double getCapacidadAlberca() {
        return capacidadAlberca;
    }

    public void setCapacidadAlberca(Double capacidadAlberca) {
        this.capacidadAlberca = capacidadAlberca;
    }

    public Integer getOtro() {
        return otro;
    }

    public void setOtro(Integer otro) {
        this.otro = otro;
    }

    public Double getCapacidadOtro() {
        return capacidadOtro;
    }

    public void setCapacidadOtro(Double capacidadOtro) {
        this.capacidadOtro = capacidadOtro;
    }

    public Date getFechaMedicion() {
        return fechaMedicion;
    }

    public void setFechaMedicion(Date fechaMedicion) {
        this.fechaMedicion = fechaMedicion;
    }

    public String getEquipoUsado() {
        return equipoUsado;
    }

    public void setEquipoUsado(String equipoUsado) {
        this.equipoUsado = equipoUsado;
    }

    public Integer getTipoRegistro() {
        return tipoRegistro;
    }

    public void setTipoRegistro(Integer tipoRegistro) {
        this.tipoRegistro = tipoRegistro;
    }

    public String getCompaniaEjecutora() {
        return companiaEjecutora;
    }

    public void setCompaniaEjecutora(String companiaEjecutora) {
        this.companiaEjecutora = companiaEjecutora;
    }

    public Double getResistividadLodo() {
        return resistividadLodo;
    }

    public void setResistividadLodo(Double resistividadLodo) {
        this.resistividadLodo = resistividadLodo;
    }

    public Double getTemperaturaLodo() {
        return temperaturaLodo;
    }

    public void setTemperaturaLodo(Double temperaturaLodo) {
        this.temperaturaLodo = temperaturaLodo;
    }

    public Double getProfundidadRegistro() {
        return profundidadRegistro;
    }

    public void setProfundidadRegistro(Double profundidadRegistro) {
        this.profundidadRegistro = profundidadRegistro;
    }

    public Integer getCalidadRegistro() {
        return calidadRegistro;
    }

    public void setCalidadRegistro(Integer calidadRegistro) {
        this.calidadRegistro = calidadRegistro;
    }

    public Integer getMetodoExplotacion() {
        return metodoExplotacion;
    }

    public void setMetodoExplotacion(Integer metodoExplotacion) {
        this.metodoExplotacion = metodoExplotacion;
    }

    public Integer getTipoEnergia() {
        return tipoEnergia;
    }

    public void setTipoEnergia(Integer tipoEnergia) {
        this.tipoEnergia = tipoEnergia;
    }

    public Integer getTipoAcabado() {
        return tipoAcabado;
    }

    public void setTipoAcabado(Integer tipoAcabado) {
        this.tipoAcabado = tipoAcabado;
    }

    public Double getDiametro() {
        return diametro;
    }

    public void setDiametro(Double diametro) {
        this.diametro = diametro;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public void setCodigoAutoridad(Long codigoAutoridad) {
        this.codigoAutoridad = codigoAutoridad;
    }

    public Long getCodigoAutoridad() {
        return codigoAutoridad;
    }

    public void setAutoridad(Autoridades autoridad) {
        this.autoridad = autoridad;
    }

    public Autoridades getAutoridad() {
        return autoridad;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    public void setLetrinaCercana(Integer letrinaCercana) {
        this.letrinaCercana = letrinaCercana;
    }

    public Integer getLetrinaCercana() {
        return letrinaCercana;
    }

    public void setDistanciaLetrina(Double distanciaLetrina) {
        this.distanciaLetrina = distanciaLetrina;
    }

    public Double getDistanciaLetrina() {
        return distanciaLetrina;
    }

    public void setAguaEstancada(Integer aguaEstancada) {
        this.aguaEstancada = aguaEstancada;
    }

    public Integer getAguaEstancada() {
        return aguaEstancada;
    }

    public void setDistancia(Double distancia) {
        this.distancia = distancia;
    }

    public Double getDistancia() {
        return distancia;
    }

    public void setCriaderoGanado(Integer criaderoGanado) {
        this.criaderoGanado = criaderoGanado;
    }

    public Integer getCriaderoGanado() {
        return criaderoGanado;
    }

    public void setDistanciaCriadero(Double distanciaCriadero) {
        this.distanciaCriadero = distanciaCriadero;
    }

    public Double getDistanciaCriadero() {
        return distanciaCriadero;
    }

    public void setFiltracionAgua(Integer filtracionAgua) {
        this.filtracionAgua = filtracionAgua;
    }

    public Integer getFiltracionAgua() {
        return filtracionAgua;
    }

    public void setDistanciaFiltracion(Double distanciaFiltracion) {
        this.distanciaFiltracion = distanciaFiltracion;
    }

    public Double getDistanciaFiltracion() {
        return distanciaFiltracion;
    }

    public void setCubiertaAdecuada(Integer cubiertaAdecuada) {
        this.cubiertaAdecuada = cubiertaAdecuada;
    }

    public Integer getCubiertaAdecuada() {
        return cubiertaAdecuada;
    }

    public void setPisoCemento(Integer pisoCemento) {
        this.pisoCemento = pisoCemento;
    }

    public Integer getPisoCemento() {
        return pisoCemento;
    }

    public void setSelloSanitario(Integer selloSanitario) {
        this.selloSanitario = selloSanitario;
    }

    public Integer getSelloSanitario() {
        return selloSanitario;
    }

    public void setCercoAdecuado(Integer cercoAdecuado) {
        this.cercoAdecuado = cercoAdecuado;
    }

    public Integer getCercoAdecuado() {
        return cercoAdecuado;
    }

    public void setCampoInfiltracion(Integer campoInfiltracion) {
        this.campoInfiltracion = campoInfiltracion;
    }

    public Integer getCampoInfiltracion() {
        return campoInfiltracion;
    }

    public void setDistanciaCampo(Double distanciaCampo) {
        this.distanciaCampo = distanciaCampo;
    }

    public Double getDistanciaCampo() {
        return distanciaCampo;
    }

    public void setCementerio(Integer cementerio) {
        this.cementerio = cementerio;
    }

    public Integer getCementerio() {
        return cementerio;
    }

    public void setDistanciaCementerio(Double distanciaCementerio) {
        this.distanciaCementerio = distanciaCementerio;
    }

    public Double getDistanciaCementerio() {
        return distanciaCementerio;
    }

    public void setEstacionServicio(Integer estacionServicio) {
        this.estacionServicio = estacionServicio;
    }

    public Integer getEstacionServicio() {
        return estacionServicio;
    }

    public void setDistanciaEstacionServicio(Double distanciaEstacionServicio) {
        this.distanciaEstacionServicio = distanciaEstacionServicio;
    }

    public Double getDistanciaEstacionServicio() {
        return distanciaEstacionServicio;
    }

    public void setLagunasOxidacion(Integer lagunasOxidacion) {
        this.lagunasOxidacion = lagunasOxidacion;
    }

    public Integer getLagunasOxidacion() {
        return lagunasOxidacion;
    }

    public void setDistanciaLagunas(Double distanciaLagunas) {
        this.distanciaLagunas = distanciaLagunas;
    }

    public Double getDistanciaLagunas() {
        return distanciaLagunas;
    }

    public void setLavaderoVehiculos(Integer lavaderoVehiculos) {
        this.lavaderoVehiculos = lavaderoVehiculos;
    }

    public Integer getLavaderoVehiculos() {
        return lavaderoVehiculos;
    }

    public void setDistanciaLavadero(Double distanciaLavadero) {
        this.distanciaLavadero = distanciaLavadero;
    }

    public Double getDistanciaLavadero() {
        return distanciaLavadero;
    }

    public void setPlantasSacrificio(Integer plantasSacrificio) {
        this.plantasSacrificio = plantasSacrificio;
    }

    public Integer getPlantasSacrificio() {
        return plantasSacrificio;
    }

    public void setDistanciaPlantas(Double distanciaPlantas) {
        this.distanciaPlantas = distanciaPlantas;
    }

    public Double getDistanciaPlantas() {
        return distanciaPlantas;
    }

    public void setPozosAbandonados(Integer pozosAbandonados) {
        this.pozosAbandonados = pozosAbandonados;
    }

    public Integer getPozosAbandonados() {
        return pozosAbandonados;
    }

    public void setDistanciaPozos(Double distanciaPozos) {
        this.distanciaPozos = distanciaPozos;
    }

    public Double getDistanciaPozos() {
        return distanciaPozos;
    }

    public void setResiduosAgricolas(Integer residuosAgricolas) {
        this.residuosAgricolas = residuosAgricolas;
    }

    public Integer getResiduosAgricolas() {
        return residuosAgricolas;
    }

    public void setResiduosDomestico(Integer residuosDomestico) {
        this.residuosDomestico = residuosDomestico;
    }

    public Integer getResiduosDomestico() {
        return residuosDomestico;
    }

    public void setResiduosGanaderia(Integer residuosGanaderia) {
        this.residuosGanaderia = residuosGanaderia;
    }

    public Integer getResiduosGanaderia() {
        return residuosGanaderia;
    }

    public void setResiduosHospitalarios(Integer residuosHospitalarios) {
        this.residuosHospitalarios = residuosHospitalarios;
    }

    public Integer getResiduosHospitalarios() {
        return residuosHospitalarios;
    }

    public void setResiduosIndustriales(Integer residuosIndustriales) {
        this.residuosIndustriales = residuosIndustriales;
    }

    public Integer getResiduosIndustriales() {
        return residuosIndustriales;
    }

    public void setResiduosMineros(Integer residuosMineros) {
        this.residuosMineros = residuosMineros;
    }

    public Integer getResiduosMineros() {
        return residuosMineros;
    }

    public void setBotaceroAbierto(Integer botaceroAbierto) {
        this.botaceroAbierto = botaceroAbierto;
    }

    public Integer getBotaceroAbierto() {
        return botaceroAbierto;
    }

    public void setCompostaje(Integer compostaje) {
        this.compostaje = compostaje;
    }

    public Integer getCompostaje() {
        return compostaje;
    }

    public void setIncineracion(Integer incineracion) {
        this.incineracion = incineracion;
    }

    public Integer getIncineracion() {
        return incineracion;
    }

    public void setReciclaje(Integer reciclaje) {
        this.reciclaje = reciclaje;
    }

    public Integer getReciclaje() {
        return reciclaje;
    }
    
    public void setIdCaptacion(Long idCaptacion) {
        this.idCaptacion = idCaptacion;
    }

    public Long getIdCaptacion() {
        return idCaptacion;
    }
    
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SubttFunias)) {
            return false;
        }
        SubttFunias other = (SubttFunias) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.gov.ideam.sirh.usuarios.agua.model.SubttFunias[ id=" + id + " ]";
    }


    public void setObjGeoforma(Lista objGeoforma) {
        this.objGeoforma = objGeoforma;
    }

    public Lista getObjGeoforma() {
        return objGeoforma;
    }

    public void setObjEstructura(Lista objEstructura) {
        this.objEstructura = objEstructura;
    }

    public Lista getObjEstructura() {
        return objEstructura;
    }

    public void setObjMaterialUsado(Lista objMaterialUsado) {
        this.objMaterialUsado = objMaterialUsado;
    }

    public Lista getObjMaterialUsado() {
        return objMaterialUsado;
    }

    public void setObjMetodoConstruccion(Lista objMetodoConstruccion) {
        this.objMetodoConstruccion = objMetodoConstruccion;
    }

    public Lista getObjMetodoConstruccion() {
        return objMetodoConstruccion;
    }

    public void setObjTratamientoEspecial(Lista objTratamientoEspecial) {
        this.objTratamientoEspecial = objTratamientoEspecial;
    }

    public Lista getObjTratamientoEspecial() {
        return objTratamientoEspecial;
    }

    public void setObjFluidoUsado(Lista objFluidoUsado) {
        this.objFluidoUsado = objFluidoUsado;
    }

    public Lista getObjFluidoUsado() {
        return objFluidoUsado;
    }

    public void setObjTipoRegistro(Lista objTipoRegistro) {
        this.objTipoRegistro = objTipoRegistro;
    }

    public Lista getObjTipoRegistro() {
        return objTipoRegistro;
    }

    public void setObjMetodoExplotacion(Lista objMetodoExplotacion) {
        this.objMetodoExplotacion = objMetodoExplotacion;
    }

    public Lista getObjMetodoExplotacion() {
        return objMetodoExplotacion;
    }

    public void setObjTipoEnergia(Lista objTipoEnergia) {
        this.objTipoEnergia = objTipoEnergia;
    }

    public Lista getObjTipoEnergia() {
        return objTipoEnergia;
    }

    public void setObjTipoAcabado(Lista objTipoAcabado) {
        this.objTipoAcabado = objTipoAcabado;
    }

    public Lista getObjTipoAcabado() {
        return objTipoAcabado;
    }

    public void setObjLocalizacionTopografica(Lista objLocalizacionTopografica) {
        this.objLocalizacionTopografica = objLocalizacionTopografica;
    }

    public Lista getObjLocalizacionTopografica() {
        return objLocalizacionTopografica;
    }
    
    public boolean isColmatadaAux() {
        if(this.estaColmatado != null &&
                this.estaColmatado.intValue() == Constantes.TRUE_TO_INTEGER.intValue()){
            this.colmatadaAux = true;
        }else{
            this.colmatadaAux = false;
        }
        
        //System.out.println("------------------Colmatada: "+this.colmatadaAux);
        
        return this.colmatadaAux;
    }

    public boolean isColapsadaAux() {
        if(this.estaColapsado != null &&
                this.estaColapsado.intValue() == Constantes.TRUE_TO_INTEGER.intValue()){
            this.colapsadaAux = true;
        }else{
            this.colapsadaAux = false;
        }
        
        //System.out.println("------------------Colapsada: "+this.colapsadaAux);
        return this.colapsadaAux;
    }

    public boolean isEmbalseaAux() {
        if(this.embalse != null &&
                this.embalse.intValue() == Constantes.TRUE_TO_INTEGER.intValue()){
            this.embalseaAux = true;
        }else{
            this.embalseaAux = false;
        }
        //System.out.println("------------------Embalse: "+this.embalseaAux);
        return this.embalseaAux;
    }

    public boolean isTanqueAux() {
        if(this.tanque != null &&
                this.tanque.intValue() == Constantes.TRUE_TO_INTEGER.intValue()){
            this.tanqueAux = true;
        }else{
            this.tanqueAux = false;
        }
        //System.out.println("------------------Tanque: "+this.tanqueAux);
        return tanqueAux;
    }

    public boolean isAlbercaAux() {
        if(this.alberca != null &&
                this.alberca.intValue() == Constantes.TRUE_TO_INTEGER.intValue()){
            this.albercaAux = true;
        }else{
            this.albercaAux = false;
        }
        //System.out.println("------------------Alberca: "+this.albercaAux);
        return albercaAux;
    }

    public boolean isOtroAux() {
        if(this.otro != null &&
                this.otro.intValue() == Constantes.TRUE_TO_INTEGER.intValue()){
            this.otroAux = true;
        }else{
            this.otroAux = false;
        }
        //System.out.println("------------------Otro: "+this.otroAux);
        return otroAux;
    }


    public boolean isLetrinaAux() {
        if(this.letrinaCercana != null &&
                this.letrinaCercana.intValue() == Constantes.TRUE_TO_INTEGER.intValue()){
            this.letrinaAux = true;
        }else{
            this.letrinaAux = false;
        }
        //System.out.println("------------------Letrina: "+this.letrinaAux);
        return letrinaAux;
    }

    public boolean isAguaEstancadaAux() {
        if(this.aguaEstancada != null &&
                this.aguaEstancada.intValue() == Constantes.TRUE_TO_INTEGER.intValue()){
            this.aguaEstancadaAux = true;
        }else{
            this.aguaEstancadaAux = false;
        }
        //System.out.println("------------------Agua Estancada: "+this.aguaEstancadaAux);
        return aguaEstancadaAux;
    }

    public boolean isCriaderoGanadoAux() {
        if(this.criaderoGanado != null &&
                this.criaderoGanado.intValue() == Constantes.TRUE_TO_INTEGER.intValue()){
            this.criaderoGanadoAux = true;
        }else{
            this.criaderoGanadoAux = false;
        }
        //System.out.println("------------------Criadero: "+this.criaderoGanadoAux);
        return criaderoGanadoAux;
    }

    public boolean isFiltracionAguaAux() {
        if(this.filtracionAgua != null &&
                this.filtracionAgua.intValue() == Constantes.TRUE_TO_INTEGER.intValue()){
            this.filtracionAguaAux = true;
        }else{
            this.filtracionAguaAux = false;
        }
        //System.out.println("------------------Filtración: "+this.filtracionAguaAux);
        return filtracionAguaAux;
    }

    public boolean isCubiertaAdecuadaAux() {
        if(this.cubiertaAdecuada != null &&
                this.cubiertaAdecuada.intValue() == Constantes.TRUE_TO_INTEGER.intValue()){
            this.cubiertaAdecuadaAux = true;
        }else{
            this.cubiertaAdecuadaAux = false;
        }
        //System.out.println("------------------Cubierta: "+this.cubiertaAdecuadaAux);
        return cubiertaAdecuadaAux;
    }

    public boolean isSelloSanitarioAux() {
        if(this.selloSanitario != null &&
                this.selloSanitario.intValue() == Constantes.TRUE_TO_INTEGER.intValue()){
            this.selloSanitarioAux = true;
        }else{
            this.selloSanitarioAux = false;
        }
        //System.out.println("------------------Sello: "+this.selloSanitario);
        return selloSanitarioAux;
    }

    public boolean isCampoInfiltracionAux() {
        if(this.campoInfiltracion != null &&
                this.campoInfiltracion.intValue() == Constantes.TRUE_TO_INTEGER.intValue()){
            this.campoInfiltracionAux = true;
        }else{
            this.campoInfiltracionAux = false;
        }
        //System.out.println("------------------Campo: "+this.campoInfiltracionAux);
        return campoInfiltracionAux;
    }

    public boolean isCementerioAux() {
        if(this.cementerio != null &&
                this.cementerio.intValue() == Constantes.TRUE_TO_INTEGER.intValue()){
            this.cementerioAux = true;
        }else{
            this.cementerioAux = false;
        }
        //System.out.println("------------------Cementerio: "+this.cementerioAux);
        return cementerioAux;
    }

    public boolean isEstacionServicioAux() {
        if(this.estacionServicio != null &&
                this.estacionServicio.intValue() == Constantes.TRUE_TO_INTEGER.intValue()){
            this.estacionServicioAux = true;
        }else{
            this.estacionServicioAux = false;
        }
        //System.out.println("------------------Estacion: "+this.estacionServicioAux);
        return estacionServicioAux;
    }

    public boolean isLagunasOxidacionAux() {
        if(this.lagunasOxidacion != null &&
                this.lagunasOxidacion.intValue() == Constantes.TRUE_TO_INTEGER.intValue()){
            this.lagunasOxidacionAux = true;
        }else{
            this.lagunasOxidacionAux = false;
        }
        //System.out.println("------------------Lagunas: "+this.lagunasOxidacionAux);
        return lagunasOxidacionAux;
    }

    public boolean isLavaderoVehiculosAux() {
        if(this.lavaderoVehiculos != null &&
                this.lavaderoVehiculos.intValue() == Constantes.TRUE_TO_INTEGER.intValue()){
            this.lavaderoVehiculosAux = true;
        }else{
            this.lavaderoVehiculosAux = false;
        }
        //System.out.println("------------------Lavadero: "+this.lavaderoVehiculosAux);
        return lavaderoVehiculosAux;
    }

    public boolean isPlantasSacrificioAux() {
        if(this.plantasSacrificio != null &&
                this.plantasSacrificio.intValue() == Constantes.TRUE_TO_INTEGER.intValue()){
            this.plantasSacrificioAux = true;
        }else{
            this.plantasSacrificioAux = false;
        }
        //System.out.println("------------------Plantas Sacrificio: "+this.plantasSacrificioAux);
        return plantasSacrificioAux;
    }

    public boolean isPozosAbandonadosAux() {
        if(this.pozosAbandonados != null &&
                this.pozosAbandonados.intValue() == Constantes.TRUE_TO_INTEGER.intValue()){
            this.pozosAbandonadosAux = true;
        }else{
            this.pozosAbandonadosAux = false;
        }
        //System.out.println("------------------Pozos: "+this.pozosAbandonadosAux);
        return pozosAbandonadosAux;
    }

    public boolean isResiduosAgricolasAux() {
        if(this.residuosAgricolas != null &&
                this.residuosAgricolas.intValue() == Constantes.TRUE_TO_INTEGER.intValue()){
            this.residuosAgricolasAux = true;
        }else{
            this.residuosAgricolasAux = false;
        }
        
        return residuosAgricolasAux;
    }

    public boolean isResiduosDomesticoAux() {
        if(this.residuosDomestico != null &&
                this.residuosDomestico.intValue() == Constantes.TRUE_TO_INTEGER.intValue()){
            this.residuosDomesticoAux = true;
        }else{
            this.residuosDomesticoAux = false;
        }
        
        return residuosDomesticoAux;
    }

    public boolean isResiduosGanaderiaAux() {
        if(this.residuosGanaderia != null &&
                this.residuosGanaderia.intValue() == Constantes.TRUE_TO_INTEGER.intValue()){
            this.residuosGanaderiaAux = true;
        }else{
            this.residuosGanaderiaAux = false;
        }
        
        return residuosGanaderiaAux;
    }

    public boolean isResiduosHospitalariosAux() {
        if(this.residuosHospitalarios != null &&
                this.residuosHospitalarios.intValue() == Constantes.TRUE_TO_INTEGER.intValue()){
            this.residuosHospitalariosAux = true;
        }else{
            this.residuosHospitalariosAux = false;
        }
        
        return residuosHospitalariosAux;
    }

    public boolean isResiduosIndustrialesAux() {
        if(this.residuosIndustriales != null &&
                this.residuosIndustriales.intValue() == Constantes.TRUE_TO_INTEGER.intValue()){
            this.residuosIndustrialesAux = true;
        }else{
            this.residuosIndustrialesAux = false;
        }
        
        return residuosIndustrialesAux;
    }

    public boolean isResiduosMinerosAux() {
        if(this.residuosMineros != null &&
                this.residuosMineros.intValue() == Constantes.TRUE_TO_INTEGER.intValue()){
            this.residuosMinerosAux = true;
        }else{
            this.residuosMinerosAux = false;
        }
        
        return residuosMinerosAux;
    }

    public boolean isBotaceroAbiertoAux() {
        if(this.botaceroAbierto != null &&
                this.botaceroAbierto.intValue() == Constantes.TRUE_TO_INTEGER.intValue()){
            this.botaceroAbiertoAux = true;
        }else{
            this.botaceroAbiertoAux = false;
        }
        
        return botaceroAbiertoAux;
    }

    public boolean isCompostajeAux() {
        if(this.compostaje != null &&
                this.compostaje.intValue() == Constantes.TRUE_TO_INTEGER.intValue()){
            this.compostajeAux = true;
        }else{
            this.compostajeAux = false;
        }
        
        return compostajeAux;
    }

    public boolean isIncineracionAux() {
        if(this.incineracion != null &&
                this.incineracion.intValue() == Constantes.TRUE_TO_INTEGER.intValue()){
            this.incineracionAux = true;
        }else{
            this.incineracionAux = false;
        }
        
        return incineracionAux;
    }

    public boolean isReciclajeAux() {
        if(this.reciclaje != null &&
                this.reciclaje.intValue() == Constantes.TRUE_TO_INTEGER.intValue()){
            this.reciclajeAux = true;
        }else{
            this.reciclajeAux = false;
        }
        
        return reciclajeAux;
    }

    public boolean isPisoCementoAux() {
        if(this.pisoCemento != null &&
                this.pisoCemento.intValue() == Constantes.TRUE_TO_INTEGER.intValue()){
            this.pisoCementoAux = true;
        }else{
            this.pisoCementoAux = false;
        }
        
        return pisoCementoAux;
    }

    public boolean isCercoAdecuadoAux() {
        if(this.cercoAdecuado != null &&
                this.cercoAdecuado.intValue() == Constantes.TRUE_TO_INTEGER.intValue()){
            this.cercoAdecuadoAux = true;
        }else{
            this.cercoAdecuadoAux = false;
        }
        return cercoAdecuadoAux;
    }


    public void setColmatadaAux(boolean colmatadaAux) {
        this.colmatadaAux = colmatadaAux;
    }

    public void setColapsadaAux(boolean colapsadaAux) {
        this.colapsadaAux = colapsadaAux;
    }

    public void setEmbalseaAux(boolean embalseaAux) {
        this.embalseaAux = embalseaAux;
    }

    public void setTanqueAux(boolean tanqueAux) {
        this.tanqueAux = tanqueAux;
    }

    public void setAlbercaAux(boolean albercaAux) {
        this.albercaAux = albercaAux;
    }

    public void setOtroAux(boolean otroAux) {
        this.otroAux = otroAux;
    }

    public void setLetrinaAux(boolean letrinaAux) {
        this.letrinaAux = letrinaAux;
    }

    public void setAguaEstancadaAux(boolean aguaEstancadaAux) {
        this.aguaEstancadaAux = aguaEstancadaAux;
    }

    public void setCriaderoGanadoAux(boolean criaderoGanadoAux) {
        this.criaderoGanadoAux = criaderoGanadoAux;
    }

    public void setFiltracionAguaAux(boolean filtracionAguaAux) {
        this.filtracionAguaAux = filtracionAguaAux;
    }

    public void setCubiertaAdecuadaAux(boolean cubiertaAdecuadaAux) {
        this.cubiertaAdecuadaAux = cubiertaAdecuadaAux;
    }

    public void setPisoCementoAux(boolean pisoCementoAux) {
        this.pisoCementoAux = pisoCementoAux;
    }

    public void setSelloSanitarioAux(boolean selloSanitarioAux) {
        this.selloSanitarioAux = selloSanitarioAux;
    }

    public void setCampoInfiltracionAux(boolean campoInfiltracionAux) {
        this.campoInfiltracionAux = campoInfiltracionAux;
    }

    public void setCercoAdecuadoAux(boolean cercoAdecuadoAux) {
        this.cercoAdecuadoAux = cercoAdecuadoAux;
    }

    public void setCementerioAux(boolean cementerioAux) {
        this.cementerioAux = cementerioAux;
    }

    public void setEstacionServicioAux(boolean estacionServicioAux) {
        this.estacionServicioAux = estacionServicioAux;
    }

    public void setLagunasOxidacionAux(boolean lagunasOxidacionAux) {
        this.lagunasOxidacionAux = lagunasOxidacionAux;
    }

    public void setLavaderoVehiculosAux(boolean lavaderoVehiculosAux) {
        this.lavaderoVehiculosAux = lavaderoVehiculosAux;
    }

    public void setPlantasSacrificioAux(boolean plantasSacrificioAux) {
        this.plantasSacrificioAux = plantasSacrificioAux;
    }

    public void setPozosAbandonadosAux(boolean pozosAbandonadosAux) {
        this.pozosAbandonadosAux = pozosAbandonadosAux;
    }

    public void setResiduosAgricolasAux(boolean residuosAgricolasAux) {
        this.residuosAgricolasAux = residuosAgricolasAux;
    }

    public void setResiduosDomesticoAux(boolean residuosDomesticoAux) {
        this.residuosDomesticoAux = residuosDomesticoAux;
    }

    public void setResiduosGanaderiaAux(boolean residuosGanaderiaAux) {
        this.residuosGanaderiaAux = residuosGanaderiaAux;
    }

    public void setResiduosHospitalariosAux(boolean residuosHospitalariosAux) {
        this.residuosHospitalariosAux = residuosHospitalariosAux;
    }

    public void setResiduosIndustrialesAux(boolean residuosIndustrialesAux) {
        this.residuosIndustrialesAux = residuosIndustrialesAux;
    }

    public void setResiduosMinerosAux(boolean residuosMinerosAux) {
        this.residuosMinerosAux = residuosMinerosAux;
    }

    public void setBotaceroAbiertoAux(boolean botaceroAbiertoAux) {
        this.botaceroAbiertoAux = botaceroAbiertoAux;
    }

    public void setCompostajeAux(boolean compostajeAux) {
        this.compostajeAux = compostajeAux;
    }

    public void setIncineracionAux(boolean incineracionAux) {
        this.incineracionAux = incineracionAux;
    }

    public void setReciclajeAux(boolean reciclajeAux) {
        this.reciclajeAux = reciclajeAux;
    }


    public void setSubttFuniasDocumentosList(List<SubttFuniasDocumentos> subttFuniasDocumentosList) {
        this.subttFuniasDocumentosList = subttFuniasDocumentosList;
    }

    public List<SubttFuniasDocumentos> getSubttFuniasDocumentosList() {
        return subttFuniasDocumentosList;
    }

    public void setBombaClase(String bombaClase) {
        this.bombaClase = bombaClase;
    }

    public String getBombaClase() {
        return bombaClase;
    }

    public void setBombaTipo(String bombaTipo) {
        this.bombaTipo = bombaTipo;
    }

    public String getBombaTipo() {
        return bombaTipo;
    }

    public void setBombaPotencia(Double bombaPotencia) {
        this.bombaPotencia = bombaPotencia;
    }

    public Double getBombaPotencia() {
        return bombaPotencia;
    }

    public void setBombaProfundidadSuccion(Double bombaProfundidadSuccion) {
        this.bombaProfundidadSuccion = bombaProfundidadSuccion;
    }

    public Double getBombaProfundidadSuccion() {
        return bombaProfundidadSuccion;
    }

    public void setIdTipo(Integer idTipo) {
        this.idTipo = idTipo;
    }

    public Integer getIdTipo() {
        return idTipo;
    }

    public void setIdPermanencia(Integer idPermanencia) {
        this.idPermanencia = idPermanencia;
    }

    public Integer getIdPermanencia() {
        return idPermanencia;
    }

    public void setIdSurgencia(Integer idSurgencia) {
        this.idSurgencia = idSurgencia;
    }

    public Integer getIdSurgencia() {
        return idSurgencia;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setIdMetodoMedida(Integer idMetodoMedida) {
        this.idMetodoMedida = idMetodoMedida;
    }

    public Integer getIdMetodoMedida() {
        return idMetodoMedida;
    }

    public void setVolumenSistema(Double volumenSistema) {
        this.volumenSistema = volumenSistema;
    }

    public Double getVolumenSistema() {
        return volumenSistema;
    }

    public void setTiempoLlenado(Double tiempoLlenado) {
        this.tiempoLlenado = tiempoLlenado;
    }

    public Double getTiempoLlenado() {
        return tiempoLlenado;
    }

    public void setCaudalEstimado(Double caudalEstimado) {
        this.caudalEstimado = caudalEstimado;
    }

    public Double getCaudalEstimado() {
        return caudalEstimado;
    }

    public void setAcceso(String acceso) {
        this.acceso = acceso;
    }

    public String getAcceso() {
        return acceso;
    }

    public boolean isColmatadaAux1() {
        return colmatadaAux;
    }

    public boolean isColapsadaAux1() {
        return colapsadaAux;
    }

    public boolean isEmbalseaAux1() {
        return embalseaAux;
    }

    public void setObjTipoManantial(Lista objTipoManantial) {
        this.objTipoManantial = objTipoManantial;
    }

    public Lista getObjTipoManantial() {
        return objTipoManantial;
    }

    public void setObjPermanencia(Lista objPermanencia) {
        this.objPermanencia = objPermanencia;
    }

    public Lista getObjPermanencia() {
        return objPermanencia;
    }

    public void setObjSurgencia(Lista objSurgencia) {
        this.objSurgencia = objSurgencia;
    }

    public Lista getObjSurgencia() {
        return objSurgencia;
    }

    public void setObjMetodoMedida(Lista objMetodoMedida) {
        this.objMetodoMedida = objMetodoMedida;
    }

    public Lista getObjMetodoMedida() {
        return objMetodoMedida;
    }
}
