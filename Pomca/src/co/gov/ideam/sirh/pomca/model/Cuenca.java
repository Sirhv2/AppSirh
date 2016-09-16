package co.gov.ideam.sirh.pomca.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
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
  @NamedQuery(name = "Cuenca.findAll", query = "select o from Cuenca o"),
  @NamedQuery(name = "Cuenca.findByAutoridad", query = "select o from Cuenca o where o.idAutoridad = :idAutoridad"),
  @NamedQuery(name = "Cuenca.findById", query = "select o from Cuenca o where o.id = :idCuenca")

})
@Table(name = "pomt_cuencas")

public class Cuenca implements Serializable {
    
    // Table's Colummns
        
        @GenericGenerator(name = "cuenca_generator", 
                          strategy = "co.gov.ideam.sirh.usuarios.agua.model.IdeamSirhGenerator",
                          parameters = {@Parameter(name = "AutoridadAmbientalId", value = "codigoAutoridad"),
                                        @Parameter(name = "Codigo", value = "id"),
                                        @Parameter(name = SequenceGenerator.SEQUENCE,  value= "seq_cuenca")})
          
        @Id
        @Column(name = "id", nullable = false)
        //@GeneratedValue(generator = "cuenca_generator")        
        private Long id;

        @Column(name="nombre")
        private String nombre;

        @Column(name="id_autoridad")
        private Long idAutoridad;

        @Column(name="area_jurisd_autoridad")
        private Long areaJurisdAutoridad;

        @Column(name="codigo_znf")
        private Long codigoZnf;
        
        @Transient
        private PomtDetalleCuenca detalleCuenca;

    public Cuenca() {
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }


    public void setAreaJurisdAutoridad(Long areaJurisdAutoridad) {
        this.areaJurisdAutoridad = areaJurisdAutoridad;
    }

    public Long getAreaJurisdAutoridad() {
        return areaJurisdAutoridad;
    }

    public void setCodigoZnf(Long codigoZnf) {
        this.codigoZnf = codigoZnf;
    }

    public Long getCodigoZnf() {
        return codigoZnf;
    }

    public void setIdAutoridad(Long idAutoridad) {
        this.idAutoridad = idAutoridad;
    }

    public Long getIdAutoridad() {
        return idAutoridad;
    }

    public void setDetalleCuenca(PomtDetalleCuenca detalleCuenca) {
        this.detalleCuenca = detalleCuenca;
    }

    public PomtDetalleCuenca getDetalleCuenca() {
        return detalleCuenca;
    }
}
