package co.gov.ideam.sirh.pueaa.model;

import java.io.Serializable;

import java.sql.Timestamp;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@NamedQueries({
  @NamedQuery(name = "PueatAdmon.findAll", query = "select o from PueatSeguimiento o"),
  @NamedQuery(name = "PueatAdmon.findByUser", query = "select o from PueatAdmon o where o.idUsuario = :idUsuario"),
  @NamedQuery(name = "PueatAdmon.findByUserPreg", query = "select o from PueatAdmon o where o.idUsuario = :idUsuario and o.pregunta = :pregunta ")
})
@Table(name = "PUEAT_ADMON")
public class PueatAdmon implements Serializable {
    @Id
    @SequenceGenerator(name = "PueatAdmonSeq",
                       sequenceName = "SEQ_PUEAT_ADMON", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
                    generator = "PueatAdmonSeq")
    @Column(nullable = false)
    private Long id;
    @Column(name="ID_USUARIO")
    private Long idUsuario;
    @Column(name="PREGUNTA", nullable = false, length = 200)
    private String pregunta;
    @Column(name="RESPUESTA",length = 5)
    private String respuesta;
    
    public PueatAdmon(){}
    public PueatAdmon(Long id, Long idUsuario, String pregunta,
                      String respuesta) {
        super();
        this.id = id;
        this.idUsuario = idUsuario;
        this.pregunta = pregunta;
        this.respuesta = respuesta;
    }


    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}

