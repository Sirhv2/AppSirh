package co.gov.ideam.sirh.usuarios.agua.model;

import co.gov.ideam.sirh.parametros.model.Autoridades;

import co.gov.ideam.sirh.parametros.model.Lista;

import java.io.Serializable;

import java.util.Calendar;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.id.SequenceGenerator;

/**
 *
 * @author Eduin
 */
@Entity
@Table(name = "SUBTT_FUNIAS_NIVEL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SubttFuniasNivel.findAll", query = "SELECT s FROM SubttFuniasNivel s"),
    @NamedQuery(name = "SubttFuniasNivel.findById", query = "SELECT s FROM SubttFuniasNivel s WHERE s.id = :id"),
    @NamedQuery(name = "SubttFuniasNivel.findByFechaMedicion", query = "SELECT s FROM SubttFuniasNivel s WHERE s.fechaMedicion = :fechaMedicion"),
    @NamedQuery(name = "SubttFuniasNivel.findByHoraMedicion", query = "SELECT s FROM SubttFuniasNivel s WHERE s.horaMedicion = :horaMedicion"),
    @NamedQuery(name = "SubttFuniasNivel.findByTipoNivel", query = "SELECT s FROM SubttFuniasNivel s WHERE s.tipoNivel = :tipoNivel"),
    @NamedQuery(name = "SubttFuniasNivel.findByProfundidad", query = "SELECT s FROM SubttFuniasNivel s WHERE s.profundidad = :profundidad"),
    @NamedQuery(name = "SubttFuniasNivel.findByNivelPiezometrico", query = "SELECT s FROM SubttFuniasNivel s WHERE s.nivelPiezometrico = :nivelPiezometrico"),
    @NamedQuery(name = "SubttFuniasNivel.findByNivelMedio", query = "SELECT s FROM SubttFuniasNivel s WHERE s.nivelMedio = :nivelMedio"),
    @NamedQuery(name = "SubttFuniasNivel.findByMetodoMedida", query = "SELECT s FROM SubttFuniasNivel s WHERE s.metodoMedida = :metodoMedida"),
    @NamedQuery(name = "SubttFuniasNivel.findByInstrumento", query = "SELECT s FROM SubttFuniasNivel s WHERE s.instrumento = :instrumento"),
    @NamedQuery(name = "SubttFuniasNivel.findByObservacion", query = "SELECT s FROM SubttFuniasNivel s WHERE s.observacion = :observacion"),
    @NamedQuery(name = "SubttFuniasNivel.findByFunias", query = "SELECT s FROM SubttFuniasNivel s WHERE s.idFunias = :id")
    })
public class SubttFuniasNivel implements Serializable {
    
    @GenericGenerator(name = "nivel_generator", 
                      strategy = "co.gov.ideam.sirh.usuarios.agua.model.IdeamSirhGenerator",
                      parameters = {@Parameter(name = "AutoridadAmbientalId", value = "codigoAutoridad"),
                                    @Parameter(name = "Codigo", value = "id"),
                                    @Parameter(name = SequenceGenerator.SEQUENCE,  value= "SEQ_NIVELES")})
    @Id
    @Column(name="id", nullable = false)
    private Long id;
    @Column(name = "id_funias")
    private Long idFunias;
    @Column(name = "fecha_medicion")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar fechaMedicion;
    @Column(name = "hora_medicion")
    private Integer horaMedicion;
    @Column(name = "minuto_medicion")
    private Integer minutoMedicion;
    @Column(name = "periodo_medicion")
    private String periodoMedicion;
    @Column(name = "tipo_nivel")
    private Integer tipoNivel;
    @Column(name = "profundidad")
    private Double profundidad;
    @Column(name = "nivel_piezometrico")
    private Double nivelPiezometrico;
    @Column(name = "nivel_medio")
    private Double nivelMedio;
    @Column(name = "metodo_medida")
    private Integer metodoMedida;
    @Column(name = "instrumento")
    private Integer instrumento;
    @Column(name = "observacion")
    private String observacion;
    
    @Transient
    private Long codigoAutoridad;
    @Transient
    private Autoridades autoridad; 
    @Transient
    private Lista objTipoNivel;
    @Transient
    private Lista objMetodoMedida;
    @Transient
    private Lista objInstrumentoMedida;
    @Transient
    private String hora;
    @Transient
    private String minuto;

    public SubttFuniasNivel() {
    }

    public SubttFuniasNivel(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public void setIdFunias(Long idFunias) {
        this.idFunias = idFunias;
    }

    public Long getIdFunias() {
        return idFunias;
    }

    public Calendar getFechaMedicion() {
        return fechaMedicion;
    }

    public void setFechaMedicion(Calendar fechaMedicion) {
        this.fechaMedicion = fechaMedicion;
    }
    
    

    public Integer getHoraMedicion() {
        return horaMedicion;
    }

    public void setHoraMedicion(Integer horaMedicion) {
        this.horaMedicion = horaMedicion;
    }
    
    public void setMinutoMedicion(Integer minutoMedicion) {
        this.minutoMedicion = minutoMedicion;
    }

    public Integer getMinutoMedicion() {
        return minutoMedicion;
    }

    public void setPeriodoMedicion(String periodoMedicion) {
        this.periodoMedicion = periodoMedicion;
    }

    public String getPeriodoMedicion() {
        return periodoMedicion;
    }

    public Integer getTipoNivel() {
        return tipoNivel;
    }

    public void setTipoNivel(Integer tipoNivel) {
        this.tipoNivel = tipoNivel;
    }

    public Double getProfundidad() {
        return profundidad;
    }

    public void setProfundidad(Double profundidad) {
        this.profundidad = profundidad;
    }

    public Double getNivelPiezometrico() {
        return nivelPiezometrico;
    }

    public void setNivelPiezometrico(Double nivelPiezometrico) {
        this.nivelPiezometrico = nivelPiezometrico;
    }

    public Double getNivelMedio() {
        return nivelMedio;
    }

    public void setNivelMedio(Double nivelMedio) {
        this.nivelMedio = nivelMedio;
    }

    public Integer getMetodoMedida() {
        return metodoMedida;
    }

    public void setMetodoMedida(Integer metodoMedida) {
        this.metodoMedida = metodoMedida;
    }

    public Integer getInstrumento() {
        return instrumento;
    }

    public void setInstrumento(Integer instrumento) {
        this.instrumento = instrumento;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SubttFuniasNivel)) {
            return false;
        }
        SubttFuniasNivel other = (SubttFuniasNivel) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.gov.ideam.sirh.usuarios.agua.model.SubttFuniasNivel[ id=" + id + " ]";
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

    public void setObjTipoNivel(Lista objTipoNivel) {
        this.objTipoNivel = objTipoNivel;
    }

    public Lista getObjTipoNivel() {
        return objTipoNivel;
    }

    public void setObjMetodoMedida(Lista objMetodoMedida) {
        this.objMetodoMedida = objMetodoMedida;
    }

    public Lista getObjMetodoMedida() {
        return objMetodoMedida;
    }

    public void setObjInstrumentoMedida(Lista objInstrumentoMedida) {
        this.objInstrumentoMedida = objInstrumentoMedida;
    }

    public Lista getObjInstrumentoMedida() {
        return objInstrumentoMedida;
    }

    public String getHora() {
        hora = ""+horaMedicion;
        if(hora!=null && hora.length()==1){
            hora = "0" + hora;  
        }
        return hora;
    }

    public String getMinuto() {
        minuto = ""+minutoMedicion;
        if(minuto!=null && minuto.length()==1){
            minuto = "0" + minuto;  
        }
        return minuto;
    }
    
    
}

