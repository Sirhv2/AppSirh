/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.ideam.sirh.archivos.model.Masivo.Entidad;

import java.io.Serializable;
import java.math.*;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Johan
 */
@Entity
@Table(name = "CMAT_FUNIAS_TOPOGRAFIA_GEO_V2")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CmatFuniasTopografiaGeoV2.findAllTopo", query = "SELECT c FROM CmatFuniasTopografiaGeoV2 c"),
    @NamedQuery(name = "CmatFuniasTopografiaGeoV2.findByCodigoRechazoTopo", query = "SELECT c FROM CmatFuniasTopografiaGeoV2 c WHERE c.codigoRechazo = :codigoRechazo"),
    @NamedQuery(name = "CmatFuniasTopografiaGeoV2.findByIdRegistroTopo", query = "SELECT c FROM CmatFuniasTopografiaGeoV2 c WHERE c.idRegistro = :idRegistro"),
    @NamedQuery(name = "CmatFuniasConstruccionV2.findByIdControlCargueTopo", query = "SELECT c FROM CmatFuniasConstruccionV2 c WHERE c.idControlCargue = :idControlCargue")})
public class CmatFuniasTopografiaGeoV2 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "CODIGO_RECHAZO")
    private Long codigoRechazo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID_REGISTRO")
    private double idRegistro;
    @Column(name = "ID_PTO_AGUAS_SUB")
    private String idPtoAguasSub;
    @Column(name = "LOCALIZACION_TOPOGRAFICA")
    private String localizacionTopografica;
    @Column(name = "UNIDAD_GEOLOGICA")
    private String unidadGeologica;
    @Column(name = "GEOFORMA")
    private String geoforma;
    @Column(name = "DESCRIPCION_LITOLOGIA")
    private String descripcionLitologia;
    @JoinColumn(name = "ID_CONTROL_CARGUE", referencedColumnName = "ID")
    @ManyToOne
    private CmatControlCargueV2 idControlCargue;

    public CmatFuniasTopografiaGeoV2() {
    }

    public CmatFuniasTopografiaGeoV2(double idRegistro) {
        this.idRegistro = idRegistro;
    }

    public Long getCodigoRechazo() {
        return codigoRechazo;
    }

    public void setCodigoRechazo(Long codigoRechazo) {
        this.codigoRechazo = codigoRechazo;
    }

    public double getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(double idRegistro) {
        this.idRegistro = idRegistro;
    }

    public String getIdPtoAguasSub() {
        return idPtoAguasSub;
    }

    public void setIdPtoAguasSub(String idPtoAguasSub) {
        this.idPtoAguasSub = idPtoAguasSub;
    }

    public String getLocalizacionTopografica() {
        return localizacionTopografica;
    }

    public void setLocalizacionTopografica(String localizacionTopografica) {
        this.localizacionTopografica = localizacionTopografica;
    }

    public String getUnidadGeologica() {
        return unidadGeologica;
    }

    public void setUnidadGeologica(String unidadGeologica) {
        this.unidadGeologica = unidadGeologica;
    }

    public String getGeoforma() {
        return geoforma;
    }

    public void setGeoforma(String geoforma) {
        this.geoforma = geoforma;
    }

    public String getDescripcionLitologia() {
        return descripcionLitologia;
    }

    public void setDescripcionLitologia(String descripcionLitologia) {
        this.descripcionLitologia = descripcionLitologia;
    }

    public CmatControlCargueV2 getIdControlCargue() {
        return idControlCargue;
    }

    public void setIdControlCargue(CmatControlCargueV2 idControlCargue) {
        this.idControlCargue = idControlCargue;
    }

  

    @Override
    public String toString() {
        return "Entidades.CmatFuniasTopografiaGeoV2[ idRegistro=" + idRegistro + " ]";
    }
    
}
