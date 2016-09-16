package co.gov.ideam.sirh.directorio.model;

import co.gov.ideam.sirh.parametros.model.Divipola;

import co.gov.ideam.sirh.parametros.model.Lista;

import java.io.Serializable;

import java.util.ArrayList;
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
@NamedQueries({
  @NamedQuery(name = "ActOrganizaciones.findAllByAutoridad", query = "select o from ActOrganizaciones o where o.codigoAutoridad = :codigoAutoridad"),
  @NamedQuery(name = "ActOrganizaciones.find", query = "select o from ActOrganizaciones o where o.id = :id"),
  @NamedQuery(name = "ActOrganizaciones.findAll", query = "select o from ActOrganizaciones o ")
})
@Table(name = "ACT_ORGANIZACIONES")
public class ActOrganizaciones implements Serializable {
    @Column(name="ID_DEPARTAMENTO", nullable = false)
    private Long depto;
    @Column(nullable = false)
    private String direccion;
    @GenericGenerator(name = "act_generator", 
                      strategy = "co.gov.ideam.sirh.usuarios.agua.model.IdeamSirhGenerator",
                      parameters = {@Parameter(name = "AutoridadAmbientalId", value = "codigoAutoridad"),
                                    @Parameter(name = "Codigo", value = "id"),
                                    @Parameter(name = SequenceGenerator.SEQUENCE,  value= "seq_actores")})
    @Id
    //@GeneratedValue(generator = "act_generator")            
    @Column(nullable = false)
    private Long id;
    @Column(name="ID_MUNICIPIO", nullable = false)
    private Long municipio;
    @Column(nullable = false)
    private Long naturaleza;
    @Column(nullable = false)
    private String nombre;
    @Column(name="PAGINA_WEB")
    private String paginaWeb;
    @Column(nullable = false)
    private String telefono;
    @Column(name="TIPO_ORGANIZACION")
    private Long tipoOrganizacion;
    @Column(name="ID_AUTORIDAD", nullable = false)
    private Long codigoAutoridad;
    @Column(name="DETALLE_GESTION_OTRO")
    private String detalleGestionOtro;
    @Transient
    private Divipola divipolaDepto;
    @Transient
    private Divipola divipolaMunicipio;
    @Transient
    private Lista listaNAturaleza;
    @Transient
    private Lista listaTipoOrganizacion;
    @Transient
    private List<ActOrganizacionesPropiedades> propiedades;

    public ActOrganizaciones() {
    }

    public ActOrganizaciones(Long depto, String direccion, Long id,
                             Long municipio, Long naturaleza, String nombre,
                             String paginaWeb, String telefono,
                             Long tipoOrganizacion) {
        this.depto = depto;
        this.direccion = direccion;
        this.id = id;
        this.municipio = municipio;
        this.naturaleza = naturaleza;
        this.nombre = nombre;
        this.paginaWeb = paginaWeb;
        this.telefono = telefono;
        this.tipoOrganizacion = tipoOrganizacion;
    }

    public Long getDepto() {
        return depto;
    }

    public void setDepto(Long depto) {
        this.depto = depto;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Long municipio) {
        this.municipio = municipio;
    }

    public Long getNaturaleza() {        
        if(this.listaNAturaleza!=null){            
            return this.listaNAturaleza.getCodigo().longValue();
        }        
        return naturaleza;
    }

    public void setNaturaleza(Long naturaleza) {
        this.naturaleza = naturaleza;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPaginaWeb() {
        return paginaWeb;
    }

    public void setPaginaWeb(String paginaWeb) {
        this.paginaWeb = paginaWeb;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Long getTipoOrganizacion() {
        return tipoOrganizacion;
    }

    public void setTipoOrganizacion(Long tipoOrganizacion) {
        this.tipoOrganizacion = tipoOrganizacion;
    }

    public Long getCodigoAutoridad() {
        return codigoAutoridad;
    }

    public void setCodigoAutoridad(Long codigoAutoridad) {
        this.codigoAutoridad = codigoAutoridad;
    }

    public Divipola getDivipolaDepto() {
        return divipolaDepto;
    }

    public void setDivipolaDepto(Divipola divipolaDepto) {
        this.divipolaDepto = divipolaDepto;
    }

    public Divipola getDivipolaMunicipio() {
        return divipolaMunicipio;
    }

    public void setDivipolaMunicipio(Divipola divipolaMunicipio) {
        this.divipolaMunicipio = divipolaMunicipio;
    }

    public Lista getListaNAturaleza() {
        return listaNAturaleza;
    }

    public void setListaNAturaleza(Lista listaNAturaleza) {
        this.listaNAturaleza = listaNAturaleza;
    }

    public Lista getListaTipoOrganizacion() {
        return listaTipoOrganizacion;
    }

    public void setListaTipoOrganizacion(Lista listaTipoOrganizacion) {
        this.listaTipoOrganizacion = listaTipoOrganizacion;
    }

    public void addPropiedad(ActOrganizacionesPropiedades propiedad){
        if(this.propiedades == null){
            this.propiedades = new ArrayList<ActOrganizacionesPropiedades>();
        }
        this.propiedades.add(propiedad);
    }

    public List<ActOrganizacionesPropiedades> getPropiedades() {
        return propiedades;
    }

    public void setPropiedades(List<ActOrganizacionesPropiedades> propiedades) {
        this.propiedades = propiedades;
    }

    public String getDetalleGestionOtro() {
        return detalleGestionOtro;
    }

    public void setDetalleGestionOtro(String detalleGestionOtro) {
        this.detalleGestionOtro = detalleGestionOtro;
    }
    
    public boolean equals(Object obj){
        return obj != null &&
            obj instanceof ActOrganizaciones &&
            ((ActOrganizaciones)obj).getId() !=null &&
            this.getId() != null &&
            ((ActOrganizaciones)obj).getId().longValue() == this.getId().longValue();
    }
}
