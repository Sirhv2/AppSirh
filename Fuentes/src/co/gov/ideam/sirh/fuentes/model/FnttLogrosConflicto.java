package co.gov.ideam.sirh.fuentes.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries({
  @NamedQuery(name = "FnttLogrosConflicto.findAll", query = "select o from FnttLogrosConflicto o")
})
@Table(name = "FNTT_LOGROS_CONFLICTO")
@IdClass(FnttLogrosConflictoPK.class)
public class FnttLogrosConflicto implements Serializable {
    @Id
    @Column(nullable = false, insertable = false, updatable = false)
    private Long idconflicto;
    @Id
    @Column(nullable = false, insertable = false, updatable = false)
    private Long idlogro;
    @ManyToOne
    @JoinColumn(name = "IDLOGRO")
    private FnttLogros fnttLogros;
    @ManyToOne
    @JoinColumn(name = "IDCONFLICTO")
    private FnttConflicto fnttConflicto;

    public FnttLogrosConflicto() {
    }

    public FnttLogrosConflicto(FnttConflicto fnttConflicto, FnttLogros fnttLogros) {
        this.fnttConflicto = fnttConflicto;
        this.fnttLogros = fnttLogros;
    }

    public Long getIdconflicto() {
        return idconflicto;
    }

    public void setIdconflicto(Long idconflicto) {
        this.idconflicto = idconflicto;
    }

    public Long getIdlogro() {
        return idlogro;
    }

    public void setIdlogro(Long idlogro) {
        this.idlogro = idlogro;
    }

    public FnttLogros getFnttLogros() {
        return fnttLogros;
    }

    public void setFnttLogros(FnttLogros fnttLogros) {
        this.fnttLogros = fnttLogros;
        if (fnttLogros != null) {
            this.idlogro = fnttLogros.getIdlogro();
        }
    }

    public FnttConflicto getFnttConflicto() {
        return fnttConflicto;
    }

    public void setFnttConflicto(FnttConflicto fnttConflicto) {
        this.fnttConflicto = fnttConflicto;
        if (fnttConflicto != null) {
            this.idconflicto = fnttConflicto.getId();
        }
    }
}
