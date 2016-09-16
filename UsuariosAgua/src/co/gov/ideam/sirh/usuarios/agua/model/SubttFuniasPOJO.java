package co.gov.ideam.sirh.usuarios.agua.model;

import java.io.Serializable;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "subtt_funias")
public class SubttFuniasPOJO implements Serializable {
    @Id
    @Column(name = "id", nullable=false)
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
    private Integer idTipo;
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

    public SubttFuniasPOJO() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setTipoFunias(Integer tipoFunias) {
        this.tipoFunias = tipoFunias;
    }

    public Integer getTipoFunias() {
        return tipoFunias;
    }

    public void setLocalizacionTopografica(Integer localizacionTopografica) {
        this.localizacionTopografica = localizacionTopografica;
    }

    public Integer getLocalizacionTopografica() {
        return localizacionTopografica;
    }

    public void setUnidadGeologica(String unidadGeologica) {
        this.unidadGeologica = unidadGeologica;
    }

    public String getUnidadGeologica() {
        return unidadGeologica;
    }

    public void setLitologiaSuperficial(String litologiaSuperficial) {
        this.litologiaSuperficial = litologiaSuperficial;
    }

    public String getLitologiaSuperficial() {
        return litologiaSuperficial;
    }

    public void setGeoforma(Integer geoforma) {
        this.geoforma = geoforma;
    }

    public Integer getGeoforma() {
        return geoforma;
    }

    public void setEstructura(Integer estructura) {
        this.estructura = estructura;
    }

    public Integer getEstructura() {
        return estructura;
    }

    public void setNombreEstructura(String nombreEstructura) {
        this.nombreEstructura = nombreEstructura;
    }

    public String getNombreEstructura() {
        return nombreEstructura;
    }

    public void setFechaConstruccion(Date fechaConstruccion) {
        this.fechaConstruccion = fechaConstruccion;
    }

    public Date getFechaConstruccion() {
        return fechaConstruccion;
    }

    public void setDiametroInterior(Double diametroInterior) {
        this.diametroInterior = diametroInterior;
    }

    public Double getDiametroInterior() {
        return diametroInterior;
    }

    public void setDiametroExterior(Double diametroExterior) {
        this.diametroExterior = diametroExterior;
    }

    public Double getDiametroExterior() {
        return diametroExterior;
    }

    public void setDiametroPerforacion(Double diametroPerforacion) {
        this.diametroPerforacion = diametroPerforacion;
    }

    public Double getDiametroPerforacion() {
        return diametroPerforacion;
    }

    public void setMaterialUsado(Integer materialUsado) {
        this.materialUsado = materialUsado;
    }

    public Integer getMaterialUsado() {
        return materialUsado;
    }

    public void setMetodoConstruccion(Integer metodoConstruccion) {
        this.metodoConstruccion = metodoConstruccion;
    }

    public Integer getMetodoConstruccion() {
        return metodoConstruccion;
    }

    public void setTratamientoEspecial(Integer tratamientoEspecial) {
        this.tratamientoEspecial = tratamientoEspecial;
    }

    public Integer getTratamientoEspecial() {
        return tratamientoEspecial;
    }

    public void setFluidoUsado(Integer fluidoUsado) {
        this.fluidoUsado = fluidoUsado;
    }

    public Integer getFluidoUsado() {
        return fluidoUsado;
    }

    public void setCompaniaPerforadora(String companiaPerforadora) {
        this.companiaPerforadora = companiaPerforadora;
    }

    public String getCompaniaPerforadora() {
        return companiaPerforadora;
    }

    public void setEstaColmatado(Integer estaColmatado) {
        this.estaColmatado = estaColmatado;
    }

    public Integer getEstaColmatado() {
        return estaColmatado;
    }

    public void setEstaColapsado(Integer estaColapsado) {
        this.estaColapsado = estaColapsado;
    }

    public Integer getEstaColapsado() {
        return estaColapsado;
    }

    public void setProfundidadEntubado(Double profundidadEntubado) {
        this.profundidadEntubado = profundidadEntubado;
    }

    public Double getProfundidadEntubado() {
        return profundidadEntubado;
    }

    public void setProfundidadPerforacion(Double profundidadPerforacion) {
        this.profundidadPerforacion = profundidadPerforacion;
    }

    public Double getProfundidadPerforacion() {
        return profundidadPerforacion;
    }

    public void setCantidadGravilla(Double cantidadGravilla) {
        this.cantidadGravilla = cantidadGravilla;
    }

    public Double getCantidadGravilla() {
        return cantidadGravilla;
    }

    public void setNivelEngravillado(Double nivelEngravillado) {
        this.nivelEngravillado = nivelEngravillado;
    }

    public Double getNivelEngravillado() {
        return nivelEngravillado;
    }

    public void setDiametroPromedioGravilla(Double diametroPromedioGravilla) {
        this.diametroPromedioGravilla = diametroPromedioGravilla;
    }

    public Double getDiametroPromedioGravilla() {
        return diametroPromedioGravilla;
    }

    public void setEmbalse(Integer embalse) {
        this.embalse = embalse;
    }

    public Integer getEmbalse() {
        return embalse;
    }

    public void setCapacidadEmbalse(Double capacidadEmbalse) {
        this.capacidadEmbalse = capacidadEmbalse;
    }

    public Double getCapacidadEmbalse() {
        return capacidadEmbalse;
    }

    public void setTanque(Integer tanque) {
        this.tanque = tanque;
    }

    public Integer getTanque() {
        return tanque;
    }

    public void setCapacidadTanque(Double capacidadTanque) {
        this.capacidadTanque = capacidadTanque;
    }

    public Double getCapacidadTanque() {
        return capacidadTanque;
    }

    public void setAlberca(Integer alberca) {
        this.alberca = alberca;
    }

    public Integer getAlberca() {
        return alberca;
    }

    public void setCapacidadAlberca(Double capacidadAlberca) {
        this.capacidadAlberca = capacidadAlberca;
    }

    public Double getCapacidadAlberca() {
        return capacidadAlberca;
    }

    public void setOtro(Integer otro) {
        this.otro = otro;
    }

    public Integer getOtro() {
        return otro;
    }

    public void setCapacidadOtro(Double capacidadOtro) {
        this.capacidadOtro = capacidadOtro;
    }

    public Double getCapacidadOtro() {
        return capacidadOtro;
    }

    public void setFechaMedicion(Date fechaMedicion) {
        this.fechaMedicion = fechaMedicion;
    }

    public Date getFechaMedicion() {
        return fechaMedicion;
    }

    public void setEquipoUsado(String equipoUsado) {
        this.equipoUsado = equipoUsado;
    }

    public String getEquipoUsado() {
        return equipoUsado;
    }

    public void setTipoRegistro(Integer tipoRegistro) {
        this.tipoRegistro = tipoRegistro;
    }

    public Integer getTipoRegistro() {
        return tipoRegistro;
    }

    public void setCompaniaEjecutora(String companiaEjecutora) {
        this.companiaEjecutora = companiaEjecutora;
    }

    public String getCompaniaEjecutora() {
        return companiaEjecutora;
    }

    public void setResistividadLodo(Double resistividadLodo) {
        this.resistividadLodo = resistividadLodo;
    }

    public Double getResistividadLodo() {
        return resistividadLodo;
    }

    public void setTemperaturaLodo(Double temperaturaLodo) {
        this.temperaturaLodo = temperaturaLodo;
    }

    public Double getTemperaturaLodo() {
        return temperaturaLodo;
    }

    public void setProfundidadRegistro(Double profundidadRegistro) {
        this.profundidadRegistro = profundidadRegistro;
    }

    public Double getProfundidadRegistro() {
        return profundidadRegistro;
    }

    public void setCalidadRegistro(Integer calidadRegistro) {
        this.calidadRegistro = calidadRegistro;
    }

    public Integer getCalidadRegistro() {
        return calidadRegistro;
    }

    public void setMetodoExplotacion(Integer metodoExplotacion) {
        this.metodoExplotacion = metodoExplotacion;
    }

    public Integer getMetodoExplotacion() {
        return metodoExplotacion;
    }

    public void setTipoEnergia(Integer tipoEnergia) {
        this.tipoEnergia = tipoEnergia;
    }

    public Integer getTipoEnergia() {
        return tipoEnergia;
    }

    public void setTipoAcabado(Integer tipoAcabado) {
        this.tipoAcabado = tipoAcabado;
    }

    public Integer getTipoAcabado() {
        return tipoAcabado;
    }

    public void setDiametro(Double diametro) {
        this.diametro = diametro;
    }

    public Double getDiametro() {
        return diametro;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getMaterial() {
        return material;
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
}
