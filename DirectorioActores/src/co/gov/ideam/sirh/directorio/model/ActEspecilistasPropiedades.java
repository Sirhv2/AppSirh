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
  @NamedQuery(name = "ActEspecilistasPropiedades.findAllByEspecialista", query = "select o from ActEspecilistasPropiedades o where o.idEspecialista = :idEspecialista"),
  @NamedQuery(name = "ActEspecilistasPropiedades.deleteByEspecialista", query = "delete from ActEspecilistasPropiedades o where o.idEspecialista = :idEspecialista")
})
@Table(name = "ACT_ESPECILISTAS_PROP")
public class ActEspecilistasPropiedades implements Serializable {
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
    @Column(name="ID_ESPECIALISTA", nullable = false)
    private Long idEspecialista;
    @Column(name="ID_LISTA", nullable = false)
    private Long idLista;
    @Transient
    private Long codigoAutoridad;
    

    public ActEspecilistasPropiedades() {
    }

    public ActEspecilistasPropiedades(Long id, Long idCategoria,
                                      Long idEspecialista, Long idLista) {
        this.id = id;
        this.idCategoria = idCategoria;
        this.idEspecialista = idEspecialista;
        this.idLista = idLista;
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

    public Long getIdEspecialista() {
        return idEspecialista;
    }

    public void setIdEspecialista(Long idEspecialista) {
        this.idEspecialista = idEspecialista;
    }

    public Long getIdLista() {
        return idLista;
    }

    public void setIdLista(Long idLista) {
        this.idLista = idLista;
    }

    public Long getCodigoAutoridad() {
        return codigoAutoridad;
    }

    public void setCodigoAutoridad(Long codigoAutoridad) {
        this.codigoAutoridad = codigoAutoridad;
    }
}
