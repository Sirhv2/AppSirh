package co.gov.ideam.sirh.directorio.model;

import co.gov.ideam.sirh.parametros.model.Lista;

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
  @NamedQuery(name = "ActEspecialistaFormacion.findAllByEspecialista", query = "select o from ActEspecialistaFormacion o where o.idEspecialista = :codigoEspecialista"),
  @NamedQuery(name = "ActEspecialistaFormacion.deleteByEspecialista", query = "delete from ActEspecialistaFormacion o where o.idEspecialista = :codigoEspecialista")
})
@Table(name = "ACT_ESPECIALISTA_FORMACION")
public class ActEspecialistaFormacion implements Serializable {
    @GenericGenerator(name = "act_generator", 
                      strategy = "co.gov.ideam.sirh.usuarios.agua.model.IdeamSirhGenerator",
                      parameters = {@Parameter(name = "AutoridadAmbientalId", value = "codigoAutoridad"),
                                    @Parameter(name = "Codigo", value = "id"),
                                    @Parameter(name = SequenceGenerator.SEQUENCE,  value= "seq_actores")})
    @Id
    @GeneratedValue(generator = "act_generator")            
    @Column(nullable = false)
    private Long id;
    @Column(name="ID_ESPECIALISTA", nullable = false)
    private Long idEspecialista;
    @Column(name="ID_FORMACION", nullable = false)
    private Long idFormacion;
    @Column(name="TITULO_OBTENIDO", nullable = false)
    private String tituloObtenido;
    @Transient
    private Long codigoAutoridad;
    @Transient
    private Lista listaFormacion;

    public ActEspecialistaFormacion() {
    }

    public ActEspecialistaFormacion(Long id, Long idEspecialista,
                                    Long idFormacion, String tituloObtenido) {
        this.id = id;
        this.idEspecialista = idEspecialista;
        this.idFormacion = idFormacion;
        this.tituloObtenido = tituloObtenido;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdEspecialista() {
        return idEspecialista;
    }

    public void setIdEspecialista(Long idEspecialista) {
        this.idEspecialista = idEspecialista;
    }

    public Long getIdFormacion() {
        return idFormacion;
    }

    public void setIdFormacion(Long idFormacion) {
        this.idFormacion = idFormacion;
    }

    public String getTituloObtenido() {
        return tituloObtenido;
    }

    public void setTituloObtenido(String tituloObtenido) {
        this.tituloObtenido = tituloObtenido;
    }

    public Long getCodigoAutoridad() {
        return codigoAutoridad;
    }

    public void setCodigoAutoridad(Long codigoAutoridad) {
        this.codigoAutoridad = codigoAutoridad;
    }

    public Lista getListaFormacion() {
        return listaFormacion;
    }

    public void setListaFormacion(Lista listaFormacion) {
        this.listaFormacion = listaFormacion;
    }
}
