package co.gov.ideam.sirh.directorio.model;

import java.io.Serializable;

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
  @NamedQuery(name = "ActOrganizacionesPropiedades.findAllByOrganizacion", query = "select o from ActOrganizacionesPropiedades o where o.idOrganizacion = :codigoOrganizacion"),
  @NamedQuery(name = "ActOrganizacionesPropiedades.deleteByOrganizacion", query = "delete from ActOrganizacionesPropiedades o where o.idOrganizacion = :codigoOrganizacion")
})
@Table(name = "ACT_ORGANIZACIONES_PROP")
public class ActOrganizacionesPropiedades implements Serializable {
    @GenericGenerator(name = "act_generator", 
                      strategy = "co.gov.ideam.sirh.usuarios.agua.model.IdeamSirhGenerator",
                      parameters = {@Parameter(name = "AutoridadAmbientalId", value = "codigoAutoridad"),
                                    @Parameter(name = "Codigo", value = "id"),
                                    @Parameter(name = SequenceGenerator.SEQUENCE,  value= "seq_actores")})
    @Id
    @GeneratedValue(generator = "act_generator")            
    @Column(nullable = false)
    private Long id;
    @Column(name="ID_CATEGORIA", nullable = false)
    private Long idCategoria;
    @Column(name="ID_LISTA", nullable = false, unique = true)
    private Long idLista;
    @Column(name="ID_ORGANIZACION", nullable = false, unique = true)
    private Long idOrganizacion;
    @Transient
    private Long codigoAutoridad;

    public ActOrganizacionesPropiedades() {
    }

    public ActOrganizacionesPropiedades(Long id, Long idCategoria,
                                        Long idLista, Long idOrganizacion) {
        this.id = id;
        this.idCategoria = idCategoria;
        this.idLista = idLista;
        this.idOrganizacion = idOrganizacion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public Long getIdLista() {
        return idLista;
    }

    public void setIdLista(Long idLista) {
        this.idLista = idLista;
    }

    public Long getIdOrganizacion() {
        return idOrganizacion;
    }

    public void setIdOrganizacion(Long idOrganizacion) {
        this.idOrganizacion = idOrganizacion;
    }

    public Long getCodigoAutoridad() {
        return codigoAutoridad;
    }

    public void setCodigoAutoridad(Long codigoAutoridad) {
        this.codigoAutoridad = codigoAutoridad;
    }
}
