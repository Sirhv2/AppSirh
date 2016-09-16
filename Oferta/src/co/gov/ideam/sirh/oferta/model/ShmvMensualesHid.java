/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.gov.ideam.sirh.oferta.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Eduin
 */
@Entity
@Table(name = "shmv_mensuales_hid")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ShmvMensualesHid.findAll", query = "SELECT s FROM ShmvMensualesHid s"),
    @NamedQuery(name = "ShmvMensualesHid.findByMnsId", query = "SELECT s FROM ShmvMensualesHid s WHERE s.mnsId = :mnsId"),
    @NamedQuery(name = "ShmvMensualesHid.findByMnsEstacionId", query = "SELECT s FROM ShmvMensualesHid s WHERE s.mnsEstacionId = :mnsEstacionId"),
    @NamedQuery(name = "ShmvMensualesHid.findByMnsVarId", query = "SELECT s FROM ShmvMensualesHid s WHERE s.mnsVarId = :mnsVarId"),
    @NamedQuery(name = "ShmvMensualesHid.findByMnsAno", query = "SELECT s FROM ShmvMensualesHid s WHERE s.mnsAno = :mnsAno"),
    @NamedQuery(name = "ShmvMensualesHid.findByMnsValor1", query = "SELECT s FROM ShmvMensualesHid s WHERE s.mnsValor1 = :mnsValor1"),
    @NamedQuery(name = "ShmvMensualesHid.findByMnsOd1", query = "SELECT s FROM ShmvMensualesHid s WHERE s.mnsOd1 = :mnsOd1"),
    @NamedQuery(name = "ShmvMensualesHid.findByMnsValor2", query = "SELECT s FROM ShmvMensualesHid s WHERE s.mnsValor2 = :mnsValor2"),
    @NamedQuery(name = "ShmvMensualesHid.findByMnsOd2", query = "SELECT s FROM ShmvMensualesHid s WHERE s.mnsOd2 = :mnsOd2"),
    @NamedQuery(name = "ShmvMensualesHid.findByMnsValor3", query = "SELECT s FROM ShmvMensualesHid s WHERE s.mnsValor3 = :mnsValor3"),
    @NamedQuery(name = "ShmvMensualesHid.findByMnsOd3", query = "SELECT s FROM ShmvMensualesHid s WHERE s.mnsOd3 = :mnsOd3"),
    @NamedQuery(name = "ShmvMensualesHid.findByMnsValor4", query = "SELECT s FROM ShmvMensualesHid s WHERE s.mnsValor4 = :mnsValor4"),
    @NamedQuery(name = "ShmvMensualesHid.findByMnsOd4", query = "SELECT s FROM ShmvMensualesHid s WHERE s.mnsOd4 = :mnsOd4"),
    @NamedQuery(name = "ShmvMensualesHid.findByMnsValor5", query = "SELECT s FROM ShmvMensualesHid s WHERE s.mnsValor5 = :mnsValor5"),
    @NamedQuery(name = "ShmvMensualesHid.findByMnsOd5", query = "SELECT s FROM ShmvMensualesHid s WHERE s.mnsOd5 = :mnsOd5"),
    @NamedQuery(name = "ShmvMensualesHid.findByMnsValor6", query = "SELECT s FROM ShmvMensualesHid s WHERE s.mnsValor6 = :mnsValor6"),
    @NamedQuery(name = "ShmvMensualesHid.findByMnsOd6", query = "SELECT s FROM ShmvMensualesHid s WHERE s.mnsOd6 = :mnsOd6"),
    @NamedQuery(name = "ShmvMensualesHid.findByMnsValor7", query = "SELECT s FROM ShmvMensualesHid s WHERE s.mnsValor7 = :mnsValor7"),
    @NamedQuery(name = "ShmvMensualesHid.findByMnsOd7", query = "SELECT s FROM ShmvMensualesHid s WHERE s.mnsOd7 = :mnsOd7"),
    @NamedQuery(name = "ShmvMensualesHid.findByMnsValor8", query = "SELECT s FROM ShmvMensualesHid s WHERE s.mnsValor8 = :mnsValor8"),
    @NamedQuery(name = "ShmvMensualesHid.findByMnsOd8", query = "SELECT s FROM ShmvMensualesHid s WHERE s.mnsOd8 = :mnsOd8"),
    @NamedQuery(name = "ShmvMensualesHid.findByMnsValor9", query = "SELECT s FROM ShmvMensualesHid s WHERE s.mnsValor9 = :mnsValor9"),
    @NamedQuery(name = "ShmvMensualesHid.findByMnsOd9", query = "SELECT s FROM ShmvMensualesHid s WHERE s.mnsOd9 = :mnsOd9"),
    @NamedQuery(name = "ShmvMensualesHid.findByMnsValor10", query = "SELECT s FROM ShmvMensualesHid s WHERE s.mnsValor10 = :mnsValor10"),
    @NamedQuery(name = "ShmvMensualesHid.findByMnsOd10", query = "SELECT s FROM ShmvMensualesHid s WHERE s.mnsOd10 = :mnsOd10"),
    @NamedQuery(name = "ShmvMensualesHid.findByMnsValor11", query = "SELECT s FROM ShmvMensualesHid s WHERE s.mnsValor11 = :mnsValor11"),
    @NamedQuery(name = "ShmvMensualesHid.findByMnsOd11", query = "SELECT s FROM ShmvMensualesHid s WHERE s.mnsOd11 = :mnsOd11"),
    @NamedQuery(name = "ShmvMensualesHid.findByMnsValor12", query = "SELECT s FROM ShmvMensualesHid s WHERE s.mnsValor12 = :mnsValor12"),
    @NamedQuery(name = "ShmvMensualesHid.findByMnsOd12", query = "SELECT s FROM ShmvMensualesHid s WHERE s.mnsOd12 = :mnsOd12"),
    @NamedQuery(name = "ShmvMensualesHid.findByParametros", 
                query = "SELECT s FROM ShmvMensualesHid s WHERE " +
                        "s.mnsEstacionId = :mnsEstacionId AND s.mnsVarId = :mnsVarId AND s.mnsAno = :mnsAno ORDER BY s.mnsAno"),
    @NamedQuery(name = "ShmvMensualesHid.findByParametrosHist", 
                query = "SELECT s FROM ShmvMensualesHid s WHERE " +
                        "s.mnsEstacionId = :mnsEstacionId AND s.mnsVarId = :mnsVarId ORDER BY s.mnsAno")
    })
public class ShmvMensualesHid implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "mns_id", nullable = false)
    private Integer mnsId;
    @Column(name = "mns_estacion_id", nullable = false)
    private Long mnsEstacionId;
    @Basic(optional = false)
    @Column(name = "mns_var_id", nullable = false)
    private String mnsVarId;
    @Column(name = "mns_ano", nullable = false)
    private int mnsAno;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "mns_valor1")
    private Double mnsValor1;
    @Column(name = "mns_od1")
    private Integer mnsOd1;
    @Column(name = "mns_valor2")
    private Double mnsValor2;
    @Column(name = "mns_od2")
    private Integer mnsOd2;
    @Column(name = "mns_valor3")
    private Double mnsValor3;
    @Column(name = "mns_od3")
    private Integer mnsOd3;
    @Column(name = "mns_valor4")
    private Double mnsValor4;
    @Column(name = "mns_od4")
    private Integer mnsOd4;
    @Column(name = "mns_valor5")
    private Double mnsValor5;
    @Column(name = "mns_od5")
    private Integer mnsOd5;
    @Column(name = "mns_valor6")
    private Double mnsValor6;
    @Column(name = "mns_od6")
    private Integer mnsOd6;
    @Column(name = "mns_valor7")
    private Double mnsValor7;
    @Column(name = "mns_od7")
    private Integer mnsOd7;
    @Column(name = "mns_valor8")
    private Double mnsValor8;
    @Column(name = "mns_od8")
    private Integer mnsOd8;
    @Column(name = "mns_valor9")
    private Double mnsValor9;
    @Column(name = "mns_od9")
    private Integer mnsOd9;
    @Column(name = "mns_valor10")
    private Double mnsValor10;
    @Column(name = "mns_od10")
    private Integer mnsOd10;
    @Column(name = "mns_valor11")
    private Double mnsValor11;
    @Column(name = "mns_od11")
    private Integer mnsOd11;
    @Column(name = "mns_valor12")
    private Double mnsValor12;
    @Column(name = "mns_od12")
    private Integer mnsOd12;
    
    @Transient
    private Double sumaTotalValid;
    @Transient
    private Long cantidadDatosValid;
    @Transient
    private Long cantidadDatosNull;
    @Transient
    private Double promedio;
    
    public ShmvMensualesHid() {
    }

    public ShmvMensualesHid(Integer mnsId) {
        this.mnsId = mnsId;
    }

    public ShmvMensualesHid(Integer mnsId, Long mnsEstacionId, String mnsVarId, int mnsAno) {
        this.mnsId = mnsId;
        this.mnsEstacionId = mnsEstacionId;
        this.mnsVarId = mnsVarId;
        this.mnsAno = mnsAno;
    }

    public Integer getMnsId() {
        return mnsId;
    }

    public void setMnsId(Integer mnsId) {
        this.mnsId = mnsId;
    }

    public Long getMnsEstacionId() {
        return mnsEstacionId;
    }

    public void setMnsEstacionId(Long mnsEstacionId) {
        this.mnsEstacionId = mnsEstacionId;
    }

    public String getMnsVarId() {
        return mnsVarId;
    }

    public void setMnsVarId(String mnsVarId) {
        this.mnsVarId = mnsVarId;
    }

    public int getMnsAno() {
        return mnsAno;
    }

    public void setMnsAno(int mnsAno) {
        this.mnsAno = mnsAno;
    }

    public Double getMnsValor1() {
        return mnsValor1;
    }

    public void setMnsValor1(Double mnsValor1) {
        this.mnsValor1 = mnsValor1;
    }

    public Integer getMnsOd1() {
        return mnsOd1;
    }

    public void setMnsOd1(Integer mnsOd1) {
        this.mnsOd1 = mnsOd1;
    }

    public Double getMnsValor2() {
        return mnsValor2;
    }

    public void setMnsValor2(Double mnsValor2) {
        this.mnsValor2 = mnsValor2;
    }

    public Integer getMnsOd2() {
        return mnsOd2;
    }

    public void setMnsOd2(Integer mnsOd2) {
        this.mnsOd2 = mnsOd2;
    }

    public Double getMnsValor3() {
        return mnsValor3;
    }

    public void setMnsValor3(Double mnsValor3) {
        this.mnsValor3 = mnsValor3;
    }

    public Integer getMnsOd3() {
        return mnsOd3;
    }

    public void setMnsOd3(Integer mnsOd3) {
        this.mnsOd3 = mnsOd3;
    }

    public Double getMnsValor4() {
        return mnsValor4;
    }

    public void setMnsValor4(Double mnsValor4) {
        this.mnsValor4 = mnsValor4;
    }

    public Integer getMnsOd4() {
        return mnsOd4;
    }

    public void setMnsOd4(Integer mnsOd4) {
        this.mnsOd4 = mnsOd4;
    }

    public Double getMnsValor5() {
        return mnsValor5;
    }

    public void setMnsValor5(Double mnsValor5) {
        this.mnsValor5 = mnsValor5;
    }

    public Integer getMnsOd5() {
        return mnsOd5;
    }

    public void setMnsOd5(Integer mnsOd5) {
        this.mnsOd5 = mnsOd5;
    }

    public Double getMnsValor6() {
        return mnsValor6;
    }

    public void setMnsValor6(Double mnsValor6) {
        this.mnsValor6 = mnsValor6;
    }

    public Integer getMnsOd6() {
        return mnsOd6;
    }

    public void setMnsOd6(Integer mnsOd6) {
        this.mnsOd6 = mnsOd6;
    }

    public Double getMnsValor7() {
        return mnsValor7;
    }

    public void setMnsValor7(Double mnsValor7) {
        this.mnsValor7 = mnsValor7;
    }

    public Integer getMnsOd7() {
        return mnsOd7;
    }

    public void setMnsOd7(Integer mnsOd7) {
        this.mnsOd7 = mnsOd7;
    }

    public Double getMnsValor8() {
        return mnsValor8;
    }

    public void setMnsValor8(Double mnsValor8) {
        this.mnsValor8 = mnsValor8;
    }

    public Integer getMnsOd8() {
        return mnsOd8;
    }

    public void setMnsOd8(Integer mnsOd8) {
        this.mnsOd8 = mnsOd8;
    }

    public Double getMnsValor9() {
        return mnsValor9;
    }

    public void setMnsValor9(Double mnsValor9) {
        this.mnsValor9 = mnsValor9;
    }

    public Integer getMnsOd9() {
        return mnsOd9;
    }

    public void setMnsOd9(Integer mnsOd9) {
        this.mnsOd9 = mnsOd9;
    }

    public Double getMnsValor10() {
        return mnsValor10;
    }

    public void setMnsValor10(Double mnsValor10) {
        this.mnsValor10 = mnsValor10;
    }

    public Integer getMnsOd10() {
        return mnsOd10;
    }

    public void setMnsOd10(Integer mnsOd10) {
        this.mnsOd10 = mnsOd10;
    }

    public Double getMnsValor11() {
        return mnsValor11;
    }

    public void setMnsValor11(Double mnsValor11) {
        this.mnsValor11 = mnsValor11;
    }

    public Integer getMnsOd11() {
        return mnsOd11;
    }

    public void setMnsOd11(Integer mnsOd11) {
        this.mnsOd11 = mnsOd11;
    }

    public Double getMnsValor12() {
        return mnsValor12;
    }

    public void setMnsValor12(Double mnsValor12) {
        this.mnsValor12 = mnsValor12;
    }

    public Integer getMnsOd12() {
        return mnsOd12;
    }

    public void setMnsOd12(Integer mnsOd12) {
        this.mnsOd12 = mnsOd12;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mnsId != null ? mnsId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ShmvMensualesHid)) {
            return false;
        }
        ShmvMensualesHid other = (ShmvMensualesHid) object;
        if ((this.mnsId == null && other.mnsId != null) || (this.mnsId != null && !this.mnsId.equals(other.mnsId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.gov.ideam.sirh.oferta.model.ShmvMensualesHid[ mnsId=" + mnsId + " ]";
    }

    public void setSumaTotalValid(Double sumaTotalValid) {
        this.sumaTotalValid = sumaTotalValid;
    }

    public Double getSumaTotalValid() {
        this.sumaTotalValid = new Double(0);
        this.sumaTotalValid = (this.getMnsValor1()!=null ?this.getMnsValor1() :0 ) + 
                         (this.getMnsValor2()!=null ?this.getMnsValor2() :0 ) +
                         (this.getMnsValor3()!=null ?this.getMnsValor3() :0 ) + 
                         (this.getMnsValor4()!=null ?this.getMnsValor4() :0 ) +
                         (this.getMnsValor5()!=null ?this.getMnsValor5() :0 ) + 
                         (this.getMnsValor6()!=null ?this.getMnsValor6() :0 ) +
                         (this.getMnsValor7()!=null ?this.getMnsValor7() :0 ) + 
                         (this.getMnsValor8()!=null ?this.getMnsValor8() :0 ) +
                         (this.getMnsValor9()!=null ?this.getMnsValor9() :0 ) + 
                         (this.getMnsValor10()!=null ?this.getMnsValor10() :0 ) +
                         (this.getMnsValor11()!=null ?this.getMnsValor11() :0 ) + 
                         (this.getMnsValor12()!=null ?this.getMnsValor12() :0 );
        return sumaTotalValid;
    }

    public void setCantidadDatosValid(Long cantidadDatosValid) {
        this.cantidadDatosValid = cantidadDatosValid;
    }

    public Long getCantidadDatosValid() {
        this.cantidadDatosValid = new Long(0);
        this.cantidadDatosValid = (this.getMnsValor1()!=null ? 1 :new Long(0) ) + 
                         (this.getMnsValor2()!=null ? 1 :new Long(0) ) +
                         (this.getMnsValor3()!=null ? 1 :new Long(0) ) + 
                         (this.getMnsValor4()!=null ? 1 :new Long(0) ) +
                         (this.getMnsValor5()!=null ? 1 :new Long(0) ) + 
                         (this.getMnsValor6()!=null ? 1 :new Long(0) ) +
                         (this.getMnsValor7()!=null ? 1 :new Long(0) ) + 
                         (this.getMnsValor8()!=null ? 1 :new Long(0) ) +
                         (this.getMnsValor9()!=null ? 1 :new Long(0) ) + 
                         (this.getMnsValor10()!=null ? 1 :new Long(0) ) +
                         (this.getMnsValor11()!=null ? 1 :new Long(0) ) + 
                         (this.getMnsValor12()!=null ? 1 :new Long(0) );
        
        return cantidadDatosValid;
    }

    public void setCantidadDatosNull(Long cantidadDatosNull) {
        this.cantidadDatosNull = cantidadDatosNull;
    }

    public Long getCantidadDatosNull() {
        this.cantidadDatosNull = new Long(0);
        this.cantidadDatosNull =
                (this.getMnsValor1() == null ? 1 : new Long(0)) +
                (this.getMnsValor2() == null ? 1 : new Long(0)) +
                (this.getMnsValor3() == null ? 1 : new Long(0)) +
                (this.getMnsValor4() == null ? 1 : new Long(0)) +
                (this.getMnsValor5() == null ? 1 : new Long(0)) +
                (this.getMnsValor6() == null ? 1 : new Long(0)) +
                (this.getMnsValor7() == null ? 1 : new Long(0)) +
                (this.getMnsValor8() == null ? 1 : new Long(0)) +
                (this.getMnsValor9() == null ? 1 : new Long(0)) +
                (this.getMnsValor10() == null ? 1 : new Long(0)) +
                (this.getMnsValor11() == null ? 1 : new Long(0)) +
                (this.getMnsValor12() == null ? 1 : new Long(0));

        return cantidadDatosNull;
    }


    public void setPromedio(Double promedio) {            
        this.promedio = promedio;
    }

    public Double getPromedio() {
        
        this.promedio = new Double(0);
        if(this.cantidadDatosValid!=null 
           && this.sumaTotalValid!=null 
           && this.cantidadDatosValid>0){
            
              this.promedio =  this.sumaTotalValid / this.cantidadDatosValid;
        }
        
        return promedio;
    }
}
