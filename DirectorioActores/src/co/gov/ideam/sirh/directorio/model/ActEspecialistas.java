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
  @NamedQuery(name = "ActEspecialistas.findAllByAutoridad", query = "select o from ActEspecialistas o where o.idAutoridad = :codigoAutoridad"),
  @NamedQuery(name = "ActEspecialistas.find", query = "select o from ActEspecialistas o where o.id = :codigo"),
  @NamedQuery(name = "ActEspecialistas.updateOrganizacion", query = "update ActEspecialistas o set o.idOrganizacion = null where o.idOrganizacion = :codigoOrganizacion"),
  @NamedQuery(name = "ActEspecialistas.findIdentificacion", query = "select o from ActEspecialistas o where o.tipoId = :tipoId and o.numeroId = :numeroId")
})
@Table(name = "ACT_ESPECIALISTAS")
public class ActEspecialistas implements Serializable {
    @Column(nullable = false)
    private Long depto;
    @Column(name="DETALLE_OTRA_GESTION")
    private String detalleOtraGestion;
    @Column(nullable = false, length = 100)
    private String direccion;
    @Column(length = 100)
    private String email;
    @GenericGenerator(name = "act_generator", 
                      strategy = "co.gov.ideam.sirh.usuarios.agua.model.IdeamSirhGenerator",
                      parameters = {@Parameter(name = "AutoridadAmbientalId", value = "idAutoridad"),
                                    @Parameter(name = "Codigo", value = "id"),
                                    @Parameter(name = SequenceGenerator.SEQUENCE,  value= "seq_actores")})
    @Id
   // @GeneratedValue(generator = "act_generator")            
    @Column(nullable = false)
    private Long id;
    @Column(name="ID_AUTORIDAD", nullable = false)
    private Long idAutoridad;
    @Column(name="ID_ORGANIZACION")
    private Long idOrganizacion;
    @Column(nullable = false)
    private Long municipio;
    @Column(nullable = false)
    private String nombre;
    @Column(name="NUMERO_ID", nullable = false, length = 50)
    private String numeroId;
    @Column(name="PAGINA_WEB", length = 100)
    private String paginaWeb;
    @Column(nullable = false, length = 100)
    private String telefono;
    @Column(name="TIPO_ID", nullable = false)
    private Long tipoId;
    @Transient
    private Divipola divipolaDepartamento;
    @Transient
    private Divipola divipolaMunicipio;
    @Transient
    private Lista listaTipoId;
    @Transient
    private List<ActEspecilistasPropiedades> propiedades;
    @Transient
    private ActOrganizaciones organizacion;

    public ActEspecialistas() {
    }

    public ActEspecialistas(Long depto, String detalleOtraGestion,
                            String direccion, String email, Long id,
                            Long idAutoridad, Long idOrganizacion,
                            Long municipio, String nombre, String numeroId,
                            String paginaWeb, String telefono, Long tipoId) {
        this.depto = depto;
        this.detalleOtraGestion = detalleOtraGestion;
        this.direccion = direccion;
        this.email = email;
        this.id = id;
        this.idAutoridad = idAutoridad;
        this.idOrganizacion = idOrganizacion;
        this.municipio = municipio;
        this.nombre = nombre;
        this.numeroId = numeroId;
        this.paginaWeb = paginaWeb;
        this.telefono = telefono;
        this.tipoId = tipoId;
    }

    public Long getDepto() {
        return depto;
    }

    public void setDepto(Long depto) {
        this.depto = depto;
    }

    public String getDetalleOtraGestion() {
        return detalleOtraGestion;
    }

    public void setDetalleOtraGestion(String detalleOtraGestion) {
        this.detalleOtraGestion = detalleOtraGestion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdAutoridad() {
        return idAutoridad;
    }

    public void setIdAutoridad(Long idAutoridad) {
        this.idAutoridad = idAutoridad;
    }

    public Long getIdOrganizacion() {
        return idOrganizacion;
    }

    public void setIdOrganizacion(Long idOrganizacion) {
        this.idOrganizacion = idOrganizacion;
    }

    public Long getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Long municipio) {
        this.municipio = municipio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNumeroId() {
        return numeroId;
    }

    public void setNumeroId(String numeroId) {
        this.numeroId = numeroId;
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

    public Long getTipoId() {
        return tipoId;
    }

    public void setTipoId(Long tipoId) {
        this.tipoId = tipoId;
    }

    public Divipola getDivipolaDepartamento() {
        return divipolaDepartamento;
    }

    public void setDivipolaDepartamento(Divipola divipolaDepartamento) {
        this.divipolaDepartamento = divipolaDepartamento;
    }

    public Divipola getDivipolaMunicipio() {
        return divipolaMunicipio;
    }

    public void setDivipolaMunicipio(Divipola divipolaMunicipio) {
        this.divipolaMunicipio = divipolaMunicipio;
    }

    public Lista getListaTipoId() {
        return listaTipoId;
    }

    public void setListaTipoId(Lista listaTipoId) {
        this.listaTipoId = listaTipoId;
    }

    public List<ActEspecilistasPropiedades> getPropiedades() {
        return propiedades;
    }

    public void setPropiedades(List<ActEspecilistasPropiedades> propiedades) {
        this.propiedades = propiedades;
    }
    
    public void addPropiedad(ActEspecilistasPropiedades propiedad){
        if(this.propiedades == null){
            this.propiedades = new ArrayList<ActEspecilistasPropiedades>();
        }
        this.propiedades.add(propiedad);
    }

    public ActOrganizaciones getOrganizacion() {
        return organizacion;
    }

    public void setOrganizacion(ActOrganizaciones organizacion) {
        this.organizacion = organizacion;
    }
}
