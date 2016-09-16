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
@Table(name = "shmv_diarios_hid")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ShmvDiariosHid.findAll", query = "SELECT s FROM ShmvDiariosHid s"),
    @NamedQuery(name = "ShmvDiariosHid.findByDrsId", query = "SELECT s FROM ShmvDiariosHid s WHERE s.drsId = :drsId"),
    @NamedQuery(name = "ShmvDiariosHid.findByDrsEstacionId", query = "SELECT s FROM ShmvDiariosHid s WHERE s.drsEstacionId = :drsEstacionId"),
    @NamedQuery(name = "ShmvDiariosHid.findByDrsVarId", query = "SELECT s FROM ShmvDiariosHid s WHERE s.drsVarId = :drsVarId"),
    @NamedQuery(name = "ShmvDiariosHid.findByDrsAno", query = "SELECT s FROM ShmvDiariosHid s WHERE s.drsAno = :drsAno"),
    @NamedQuery(name = "ShmvDiariosHid.findByDrsMes", query = "SELECT s FROM ShmvDiariosHid s WHERE s.drsMes = :drsMes"),
    @NamedQuery(name = "ShmvDiariosHid.findByDrsValor1", query = "SELECT s FROM ShmvDiariosHid s WHERE s.drsValor1 = :drsValor1"),
    @NamedQuery(name = "ShmvDiariosHid.findByDrsOd1", query = "SELECT s FROM ShmvDiariosHid s WHERE s.drsOd1 = :drsOd1"),
    @NamedQuery(name = "ShmvDiariosHid.findByDrsValor2", query = "SELECT s FROM ShmvDiariosHid s WHERE s.drsValor2 = :drsValor2"),
    @NamedQuery(name = "ShmvDiariosHid.findByDrsOd2", query = "SELECT s FROM ShmvDiariosHid s WHERE s.drsOd2 = :drsOd2"),
    @NamedQuery(name = "ShmvDiariosHid.findByDrsValor3", query = "SELECT s FROM ShmvDiariosHid s WHERE s.drsValor3 = :drsValor3"),
    @NamedQuery(name = "ShmvDiariosHid.findByDrsOd3", query = "SELECT s FROM ShmvDiariosHid s WHERE s.drsOd3 = :drsOd3"),
    @NamedQuery(name = "ShmvDiariosHid.findByDrsValor4", query = "SELECT s FROM ShmvDiariosHid s WHERE s.drsValor4 = :drsValor4"),
    @NamedQuery(name = "ShmvDiariosHid.findByDrsOd4", query = "SELECT s FROM ShmvDiariosHid s WHERE s.drsOd4 = :drsOd4"),
    @NamedQuery(name = "ShmvDiariosHid.findByDrsValor5", query = "SELECT s FROM ShmvDiariosHid s WHERE s.drsValor5 = :drsValor5"),
    @NamedQuery(name = "ShmvDiariosHid.findByDrsOd5", query = "SELECT s FROM ShmvDiariosHid s WHERE s.drsOd5 = :drsOd5"),
    @NamedQuery(name = "ShmvDiariosHid.findByDrsValor6", query = "SELECT s FROM ShmvDiariosHid s WHERE s.drsValor6 = :drsValor6"),
    @NamedQuery(name = "ShmvDiariosHid.findByDrsOd6", query = "SELECT s FROM ShmvDiariosHid s WHERE s.drsOd6 = :drsOd6"),
    @NamedQuery(name = "ShmvDiariosHid.findByDrsValor7", query = "SELECT s FROM ShmvDiariosHid s WHERE s.drsValor7 = :drsValor7"),
    @NamedQuery(name = "ShmvDiariosHid.findByDrsOd7", query = "SELECT s FROM ShmvDiariosHid s WHERE s.drsOd7 = :drsOd7"),
    @NamedQuery(name = "ShmvDiariosHid.findByDrsValor8", query = "SELECT s FROM ShmvDiariosHid s WHERE s.drsValor8 = :drsValor8"),
    @NamedQuery(name = "ShmvDiariosHid.findByDrsOd8", query = "SELECT s FROM ShmvDiariosHid s WHERE s.drsOd8 = :drsOd8"),
    @NamedQuery(name = "ShmvDiariosHid.findByDrsValor9", query = "SELECT s FROM ShmvDiariosHid s WHERE s.drsValor9 = :drsValor9"),
    @NamedQuery(name = "ShmvDiariosHid.findByDrsOd9", query = "SELECT s FROM ShmvDiariosHid s WHERE s.drsOd9 = :drsOd9"),
    @NamedQuery(name = "ShmvDiariosHid.findByDrsValor10", query = "SELECT s FROM ShmvDiariosHid s WHERE s.drsValor10 = :drsValor10"),
    @NamedQuery(name = "ShmvDiariosHid.findByDrsOd10", query = "SELECT s FROM ShmvDiariosHid s WHERE s.drsOd10 = :drsOd10"),
    @NamedQuery(name = "ShmvDiariosHid.findByDrsValor11", query = "SELECT s FROM ShmvDiariosHid s WHERE s.drsValor11 = :drsValor11"),
    @NamedQuery(name = "ShmvDiariosHid.findByDrsOd11", query = "SELECT s FROM ShmvDiariosHid s WHERE s.drsOd11 = :drsOd11"),
    @NamedQuery(name = "ShmvDiariosHid.findByDrsValor12", query = "SELECT s FROM ShmvDiariosHid s WHERE s.drsValor12 = :drsValor12"),
    @NamedQuery(name = "ShmvDiariosHid.findByDrsOd12", query = "SELECT s FROM ShmvDiariosHid s WHERE s.drsOd12 = :drsOd12"),
    @NamedQuery(name = "ShmvDiariosHid.findByDrsValor13", query = "SELECT s FROM ShmvDiariosHid s WHERE s.drsValor13 = :drsValor13"),
    @NamedQuery(name = "ShmvDiariosHid.findByDrsOd13", query = "SELECT s FROM ShmvDiariosHid s WHERE s.drsOd13 = :drsOd13"),
    @NamedQuery(name = "ShmvDiariosHid.findByDrsValor14", query = "SELECT s FROM ShmvDiariosHid s WHERE s.drsValor14 = :drsValor14"),
    @NamedQuery(name = "ShmvDiariosHid.findByDrsOd14", query = "SELECT s FROM ShmvDiariosHid s WHERE s.drsOd14 = :drsOd14"),
    @NamedQuery(name = "ShmvDiariosHid.findByDrsValor15", query = "SELECT s FROM ShmvDiariosHid s WHERE s.drsValor15 = :drsValor15"),
    @NamedQuery(name = "ShmvDiariosHid.findByDrsOd15", query = "SELECT s FROM ShmvDiariosHid s WHERE s.drsOd15 = :drsOd15"),
    @NamedQuery(name = "ShmvDiariosHid.findByDrsValor16", query = "SELECT s FROM ShmvDiariosHid s WHERE s.drsValor16 = :drsValor16"),
    @NamedQuery(name = "ShmvDiariosHid.findByDrsOd16", query = "SELECT s FROM ShmvDiariosHid s WHERE s.drsOd16 = :drsOd16"),
    @NamedQuery(name = "ShmvDiariosHid.findByDrsValor17", query = "SELECT s FROM ShmvDiariosHid s WHERE s.drsValor17 = :drsValor17"),
    @NamedQuery(name = "ShmvDiariosHid.findByDrsOd17", query = "SELECT s FROM ShmvDiariosHid s WHERE s.drsOd17 = :drsOd17"),
    @NamedQuery(name = "ShmvDiariosHid.findByDrsValor18", query = "SELECT s FROM ShmvDiariosHid s WHERE s.drsValor18 = :drsValor18"),
    @NamedQuery(name = "ShmvDiariosHid.findByDrsOd18", query = "SELECT s FROM ShmvDiariosHid s WHERE s.drsOd18 = :drsOd18"),
    @NamedQuery(name = "ShmvDiariosHid.findByDrsValor19", query = "SELECT s FROM ShmvDiariosHid s WHERE s.drsValor19 = :drsValor19"),
    @NamedQuery(name = "ShmvDiariosHid.findByDrsOd19", query = "SELECT s FROM ShmvDiariosHid s WHERE s.drsOd19 = :drsOd19"),
    @NamedQuery(name = "ShmvDiariosHid.findByDrsValor20", query = "SELECT s FROM ShmvDiariosHid s WHERE s.drsValor20 = :drsValor20"),
    @NamedQuery(name = "ShmvDiariosHid.findByDrsOd20", query = "SELECT s FROM ShmvDiariosHid s WHERE s.drsOd20 = :drsOd20"),
    @NamedQuery(name = "ShmvDiariosHid.findByDrsValor21", query = "SELECT s FROM ShmvDiariosHid s WHERE s.drsValor21 = :drsValor21"),
    @NamedQuery(name = "ShmvDiariosHid.findByDrsOd21", query = "SELECT s FROM ShmvDiariosHid s WHERE s.drsOd21 = :drsOd21"),
    @NamedQuery(name = "ShmvDiariosHid.findByDrsValor22", query = "SELECT s FROM ShmvDiariosHid s WHERE s.drsValor22 = :drsValor22"),
    @NamedQuery(name = "ShmvDiariosHid.findByDrsOd22", query = "SELECT s FROM ShmvDiariosHid s WHERE s.drsOd22 = :drsOd22"),
    @NamedQuery(name = "ShmvDiariosHid.findByDrsValor23", query = "SELECT s FROM ShmvDiariosHid s WHERE s.drsValor23 = :drsValor23"),
    @NamedQuery(name = "ShmvDiariosHid.findByDrsOd23", query = "SELECT s FROM ShmvDiariosHid s WHERE s.drsOd23 = :drsOd23"),
    @NamedQuery(name = "ShmvDiariosHid.findByDrsValor24", query = "SELECT s FROM ShmvDiariosHid s WHERE s.drsValor24 = :drsValor24"),
    @NamedQuery(name = "ShmvDiariosHid.findByDrsOd24", query = "SELECT s FROM ShmvDiariosHid s WHERE s.drsOd24 = :drsOd24"),
    @NamedQuery(name = "ShmvDiariosHid.findByDrsValor25", query = "SELECT s FROM ShmvDiariosHid s WHERE s.drsValor25 = :drsValor25"),
    @NamedQuery(name = "ShmvDiariosHid.findByDrsOd25", query = "SELECT s FROM ShmvDiariosHid s WHERE s.drsOd25 = :drsOd25"),
    @NamedQuery(name = "ShmvDiariosHid.findByDrsValor26", query = "SELECT s FROM ShmvDiariosHid s WHERE s.drsValor26 = :drsValor26"),
    @NamedQuery(name = "ShmvDiariosHid.findByDrsOd26", query = "SELECT s FROM ShmvDiariosHid s WHERE s.drsOd26 = :drsOd26"),
    @NamedQuery(name = "ShmvDiariosHid.findByDrsValor27", query = "SELECT s FROM ShmvDiariosHid s WHERE s.drsValor27 = :drsValor27"),
    @NamedQuery(name = "ShmvDiariosHid.findByDrsOd27", query = "SELECT s FROM ShmvDiariosHid s WHERE s.drsOd27 = :drsOd27"),
    @NamedQuery(name = "ShmvDiariosHid.findByDrsValor28", query = "SELECT s FROM ShmvDiariosHid s WHERE s.drsValor28 = :drsValor28"),
    @NamedQuery(name = "ShmvDiariosHid.findByDrsOd28", query = "SELECT s FROM ShmvDiariosHid s WHERE s.drsOd28 = :drsOd28"),
    @NamedQuery(name = "ShmvDiariosHid.findByDrsValor29", query = "SELECT s FROM ShmvDiariosHid s WHERE s.drsValor29 = :drsValor29"),
    @NamedQuery(name = "ShmvDiariosHid.findByDrsOd29", query = "SELECT s FROM ShmvDiariosHid s WHERE s.drsOd29 = :drsOd29"),
    @NamedQuery(name = "ShmvDiariosHid.findByDrsValor30", query = "SELECT s FROM ShmvDiariosHid s WHERE s.drsValor30 = :drsValor30"),
    @NamedQuery(name = "ShmvDiariosHid.findByDrsOd30", query = "SELECT s FROM ShmvDiariosHid s WHERE s.drsOd30 = :drsOd30"),
    @NamedQuery(name = "ShmvDiariosHid.findByDrsValor31", query = "SELECT s FROM ShmvDiariosHid s WHERE s.drsValor31 = :drsValor31"),
    @NamedQuery(name = "ShmvDiariosHid.findByDrsOd31", query = "SELECT s FROM ShmvDiariosHid s WHERE s.drsOd31 = :drsOd31"),
    @NamedQuery(name = "ShmvDiariosHid.findByParametros", 
                query = "SELECT s FROM ShmvDiariosHid s " +
                            "WHERE s.drsEstacionId = :drsEstacionId AND s.drsVarId = :drsVarId AND s.drsAno = :drsAno AND s.drsMes = :drsMes ORDER BY s.drsAno, s.drsMes"),
    @NamedQuery(name = "ShmvDiariosHid.findByParametrosMin", 
                query = "SELECT s FROM ShmvDiariosHid s " +
                            "WHERE s.drsEstacionId = :drsEstacionId AND s.drsVarId = :drsVarId AND s.drsAno = :drsAno ORDER BY s.drsAno, s.drsMes"),
        @NamedQuery(name = "ShmvDiariosHid.findByParametrosHist", 
                query = "SELECT s FROM ShmvDiariosHid s " +
                            "WHERE s.drsEstacionId = :drsEstacionId AND s.drsVarId = :drsVarId ORDER BY s.drsAno, s.drsMes")
    })
public class ShmvDiariosHid implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "drs_id", nullable = false)
    private Integer drsId;
    @Column(name = "drs_estacion_id", nullable = false)
    private Long drsEstacionId;
    @Column(name = "drs_var_id", nullable = false)
    private String drsVarId;
    @Column(name = "drs_ano", nullable = false)
    private int drsAno;
    @Column(name = "drs_mes", nullable = false)
    private int drsMes;
    @Column(name = "drs_valor1")
    private Double drsValor1;
    @Column(name = "drs_od1")
    private Integer drsOd1;
    @Column(name = "drs_valor2")
    private Double drsValor2;
    @Column(name = "drs_od2")
    private Integer drsOd2;
    @Column(name = "drs_valor3")
    private Double drsValor3;
    @Column(name = "drs_od3")
    private Integer drsOd3;
    @Column(name = "drs_valor4")
    private Double drsValor4;
    @Column(name = "drs_od4")
    private Integer drsOd4;
    @Column(name = "drs_valor5")
    private Double drsValor5;
    @Column(name = "drs_od5")
    private Integer drsOd5;
    @Column(name = "drs_valor6")
    private Double drsValor6;
    @Column(name = "drs_od6")
    private Integer drsOd6;
    @Column(name = "drs_valor7")
    private Double drsValor7;
    @Column(name = "drs_od7")
    private Integer drsOd7;
    @Column(name = "drs_valor8")
    private Double drsValor8;
    @Column(name = "drs_od8")
    private Integer drsOd8;
    @Column(name = "drs_valor9")
    private Double drsValor9;
    @Column(name = "drs_od9")
    private Integer drsOd9;
    @Column(name = "drs_valor10")
    private Double drsValor10;
    @Column(name = "drs_od10")
    private Integer drsOd10;
    @Column(name = "drs_valor11")
    private Double drsValor11;
    @Column(name = "drs_od11")
    private Integer drsOd11;
    @Column(name = "drs_valor12")
    private Double drsValor12;
    @Column(name = "drs_od12")
    private Integer drsOd12;
    @Column(name = "drs_valor13")
    private Double drsValor13;
    @Column(name = "drs_od13")
    private Integer drsOd13;
    @Column(name = "drs_valor14")
    private Double drsValor14;
    @Column(name = "drs_od14")
    private Integer drsOd14;
    @Column(name = "drs_valor15")
    private Double drsValor15;
    @Column(name = "drs_od15")
    private Integer drsOd15;
    @Column(name = "drs_valor16")
    private Double drsValor16;
    @Column(name = "drs_od16")
    private Integer drsOd16;
    @Column(name = "drs_valor17")
    private Double drsValor17;
    @Column(name = "drs_od17")
    private Integer drsOd17;
    @Column(name = "drs_valor18")
    private Double drsValor18;
    @Column(name = "drs_od18")
    private Integer drsOd18;
    @Column(name = "drs_valor19")
    private Double drsValor19;
    @Column(name = "drs_od19")
    private Integer drsOd19;
    @Column(name = "drs_valor20")
    private Double drsValor20;
    @Column(name = "drs_od20")
    private Integer drsOd20;
    @Column(name = "drs_valor21")
    private Double drsValor21;
    @Column(name = "drs_od21")
    private Integer drsOd21;
    @Column(name = "drs_valor22")
    private Double drsValor22;
    @Column(name = "drs_od22")
    private Integer drsOd22;
    @Column(name = "drs_valor23")
    private Double drsValor23;
    @Column(name = "drs_od23")
    private Integer drsOd23;
    @Column(name = "drs_valor24")
    private Double drsValor24;
    @Column(name = "drs_od24")
    private Integer drsOd24;
    @Column(name = "drs_valor25")
    private Double drsValor25;
    @Column(name = "drs_od25")
    private Integer drsOd25;
    @Column(name = "drs_valor26")
    private Double drsValor26;
    @Column(name = "drs_od26")
    private Integer drsOd26;
    @Column(name = "drs_valor27")
    private Double drsValor27;
    @Column(name = "drs_od27")
    private Integer drsOd27;
    @Column(name = "drs_valor28")
    private Double drsValor28;
    @Column(name = "drs_od28")
    private Integer drsOd28;
    @Column(name = "drs_valor29")
    private Double drsValor29;
    @Column(name = "drs_od29")
    private Integer drsOd29;
    @Column(name = "drs_valor30")
    private Double drsValor30;
    @Column(name = "drs_od30")
    private Integer drsOd30;
    @Column(name = "drs_valor31")
    private Double drsValor31;
    @Column(name = "drs_od31")
    private Integer drsOd31;
    
    @Transient
    private Double sumaTotalValid;
    @Transient
    private Long cantidadDatosValid;
    @Transient
    private Long cantidadDatosNull;

    public ShmvDiariosHid() {
    }

    public ShmvDiariosHid(Integer drsId) {
        this.drsId = drsId;
    }

    public ShmvDiariosHid(Integer drsId, Long drsEstacionId, String drsVarId, int drsAno, int drsMes) {
        this.drsId = drsId;
        this.drsEstacionId = drsEstacionId;
        this.drsVarId = drsVarId;
        this.drsAno = drsAno;
        this.drsMes = drsMes;
    }

    public Integer getDrsId() {
        return drsId;
    }

    public void setDrsId(Integer drsId) {
        this.drsId = drsId;
    }

    public Long getDrsEstacionId() {
        return drsEstacionId;
    }

    public void setDrsEstacionId(Long drsEstacionId) {
        this.drsEstacionId = drsEstacionId;
    }

    public String getDrsVarId() {
        return drsVarId;
    }

    public void setDrsVarId(String drsVarId) {
        this.drsVarId = drsVarId;
    }

    public int getDrsAno() {
        return drsAno;
    }

    public void setDrsAno(int drsAno) {
        this.drsAno = drsAno;
    }

    public int getDrsMes() {
        return drsMes;
    }

    public void setDrsMes(int drsMes) {
        this.drsMes = drsMes;
    }

    public Double getDrsValor1() {
        return drsValor1;
    }

    public void setDrsValor1(Double drsValor1) {
        this.drsValor1 = drsValor1;
    }

    public Integer getDrsOd1() {
        return drsOd1;
    }

    public void setDrsOd1(Integer drsOd1) {
        this.drsOd1 = drsOd1;
    }

    public Double getDrsValor2() {
        return drsValor2;
    }

    public void setDrsValor2(Double drsValor2) {
        this.drsValor2 = drsValor2;
    }

    public Integer getDrsOd2() {
        return drsOd2;
    }

    public void setDrsOd2(Integer drsOd2) {
        this.drsOd2 = drsOd2;
    }

    public Double getDrsValor3() {
        return drsValor3;
    }

    public void setDrsValor3(Double drsValor3) {
        this.drsValor3 = drsValor3;
    }

    public Integer getDrsOd3() {
        return drsOd3;
    }

    public void setDrsOd3(Integer drsOd3) {
        this.drsOd3 = drsOd3;
    }

    public Double getDrsValor4() {
        return drsValor4;
    }

    public void setDrsValor4(Double drsValor4) {
        this.drsValor4 = drsValor4;
    }

    public Integer getDrsOd4() {
        return drsOd4;
    }

    public void setDrsOd4(Integer drsOd4) {
        this.drsOd4 = drsOd4;
    }

    public Double getDrsValor5() {
        return drsValor5;
    }

    public void setDrsValor5(Double drsValor5) {
        this.drsValor5 = drsValor5;
    }

    public Integer getDrsOd5() {
        return drsOd5;
    }

    public void setDrsOd5(Integer drsOd5) {
        this.drsOd5 = drsOd5;
    }

    public Double getDrsValor6() {
        return drsValor6;
    }

    public void setDrsValor6(Double drsValor6) {
        this.drsValor6 = drsValor6;
    }

    public Integer getDrsOd6() {
        return drsOd6;
    }

    public void setDrsOd6(Integer drsOd6) {
        this.drsOd6 = drsOd6;
    }

    public Double getDrsValor7() {
        return drsValor7;
    }

    public void setDrsValor7(Double drsValor7) {
        this.drsValor7 = drsValor7;
    }

    public Integer getDrsOd7() {
        return drsOd7;
    }

    public void setDrsOd7(Integer drsOd7) {
        this.drsOd7 = drsOd7;
    }

    public Double getDrsValor8() {
        return drsValor8;
    }

    public void setDrsValor8(Double drsValor8) {
        this.drsValor8 = drsValor8;
    }

    public Integer getDrsOd8() {
        return drsOd8;
    }

    public void setDrsOd8(Integer drsOd8) {
        this.drsOd8 = drsOd8;
    }

    public Double getDrsValor9() {
        return drsValor9;
    }

    public void setDrsValor9(Double drsValor9) {
        this.drsValor9 = drsValor9;
    }

    public Integer getDrsOd9() {
        return drsOd9;
    }

    public void setDrsOd9(Integer drsOd9) {
        this.drsOd9 = drsOd9;
    }

    public Double getDrsValor10() {
        return drsValor10;
    }

    public void setDrsValor10(Double drsValor10) {
        this.drsValor10 = drsValor10;
    }

    public Integer getDrsOd10() {
        return drsOd10;
    }

    public void setDrsOd10(Integer drsOd10) {
        this.drsOd10 = drsOd10;
    }

    public Double getDrsValor11() {
        return drsValor11;
    }

    public void setDrsValor11(Double drsValor11) {
        this.drsValor11 = drsValor11;
    }

    public Integer getDrsOd11() {
        return drsOd11;
    }

    public void setDrsOd11(Integer drsOd11) {
        this.drsOd11 = drsOd11;
    }

    public Double getDrsValor12() {
        return drsValor12;
    }

    public void setDrsValor12(Double drsValor12) {
        this.drsValor12 = drsValor12;
    }

    public Integer getDrsOd12() {
        return drsOd12;
    }

    public void setDrsOd12(Integer drsOd12) {
        this.drsOd12 = drsOd12;
    }

    public Double getDrsValor13() {
        return drsValor13;
    }

    public void setDrsValor13(Double drsValor13) {
        this.drsValor13 = drsValor13;
    }

    public Integer getDrsOd13() {
        return drsOd13;
    }

    public void setDrsOd13(Integer drsOd13) {
        this.drsOd13 = drsOd13;
    }

    public Double getDrsValor14() {
        return drsValor14;
    }

    public void setDrsValor14(Double drsValor14) {
        this.drsValor14 = drsValor14;
    }

    public Integer getDrsOd14() {
        return drsOd14;
    }

    public void setDrsOd14(Integer drsOd14) {
        this.drsOd14 = drsOd14;
    }

    public Double getDrsValor15() {
        return drsValor15;
    }

    public void setDrsValor15(Double drsValor15) {
        this.drsValor15 = drsValor15;
    }

    public Integer getDrsOd15() {
        return drsOd15;
    }

    public void setDrsOd15(Integer drsOd15) {
        this.drsOd15 = drsOd15;
    }

    public Double getDrsValor16() {
        return drsValor16;
    }

    public void setDrsValor16(Double drsValor16) {
        this.drsValor16 = drsValor16;
    }

    public Integer getDrsOd16() {
        return drsOd16;
    }

    public void setDrsOd16(Integer drsOd16) {
        this.drsOd16 = drsOd16;
    }

    public Double getDrsValor17() {
        return drsValor17;
    }

    public void setDrsValor17(Double drsValor17) {
        this.drsValor17 = drsValor17;
    }

    public Integer getDrsOd17() {
        return drsOd17;
    }

    public void setDrsOd17(Integer drsOd17) {
        this.drsOd17 = drsOd17;
    }

    public Double getDrsValor18() {
        return drsValor18;
    }

    public void setDrsValor18(Double drsValor18) {
        this.drsValor18 = drsValor18;
    }

    public Integer getDrsOd18() {
        return drsOd18;
    }

    public void setDrsOd18(Integer drsOd18) {
        this.drsOd18 = drsOd18;
    }

    public Double getDrsValor19() {
        return drsValor19;
    }

    public void setDrsValor19(Double drsValor19) {
        this.drsValor19 = drsValor19;
    }

    public Integer getDrsOd19() {
        return drsOd19;
    }

    public void setDrsOd19(Integer drsOd19) {
        this.drsOd19 = drsOd19;
    }

    public Double getDrsValor20() {
        return drsValor20;
    }

    public void setDrsValor20(Double drsValor20) {
        this.drsValor20 = drsValor20;
    }

    public Integer getDrsOd20() {
        return drsOd20;
    }

    public void setDrsOd20(Integer drsOd20) {
        this.drsOd20 = drsOd20;
    }

    public Double getDrsValor21() {
        return drsValor21;
    }

    public void setDrsValor21(Double drsValor21) {
        this.drsValor21 = drsValor21;
    }

    public Integer getDrsOd21() {
        return drsOd21;
    }

    public void setDrsOd21(Integer drsOd21) {
        this.drsOd21 = drsOd21;
    }

    public Double getDrsValor22() {
        return drsValor22;
    }

    public void setDrsValor22(Double drsValor22) {
        this.drsValor22 = drsValor22;
    }

    public Integer getDrsOd22() {
        return drsOd22;
    }

    public void setDrsOd22(Integer drsOd22) {
        this.drsOd22 = drsOd22;
    }

    public Double getDrsValor23() {
        return drsValor23;
    }

    public void setDrsValor23(Double drsValor23) {
        this.drsValor23 = drsValor23;
    }

    public Integer getDrsOd23() {
        return drsOd23;
    }

    public void setDrsOd23(Integer drsOd23) {
        this.drsOd23 = drsOd23;
    }

    public Double getDrsValor24() {
        return drsValor24;
    }

    public void setDrsValor24(Double drsValor24) {
        this.drsValor24 = drsValor24;
    }

    public Integer getDrsOd24() {
        return drsOd24;
    }

    public void setDrsOd24(Integer drsOd24) {
        this.drsOd24 = drsOd24;
    }

    public Double getDrsValor25() {
        return drsValor25;
    }

    public void setDrsValor25(Double drsValor25) {
        this.drsValor25 = drsValor25;
    }

    public Integer getDrsOd25() {
        return drsOd25;
    }

    public void setDrsOd25(Integer drsOd25) {
        this.drsOd25 = drsOd25;
    }

    public Double getDrsValor26() {
        return drsValor26;
    }

    public void setDrsValor26(Double drsValor26) {
        this.drsValor26 = drsValor26;
    }

    public Integer getDrsOd26() {
        return drsOd26;
    }

    public void setDrsOd26(Integer drsOd26) {
        this.drsOd26 = drsOd26;
    }

    public Double getDrsValor27() {
        return drsValor27;
    }

    public void setDrsValor27(Double drsValor27) {
        this.drsValor27 = drsValor27;
    }

    public Integer getDrsOd27() {
        return drsOd27;
    }

    public void setDrsOd27(Integer drsOd27) {
        this.drsOd27 = drsOd27;
    }

    public Double getDrsValor28() {
        return drsValor28;
    }

    public void setDrsValor28(Double drsValor28) {
        this.drsValor28 = drsValor28;
    }

    public Integer getDrsOd28() {
        return drsOd28;
    }

    public void setDrsOd28(Integer drsOd28) {
        this.drsOd28 = drsOd28;
    }

    public Double getDrsValor29() {
        return drsValor29;
    }

    public void setDrsValor29(Double drsValor29) {
        this.drsValor29 = drsValor29;
    }

    public Integer getDrsOd29() {
        return drsOd29;
    }

    public void setDrsOd29(Integer drsOd29) {
        this.drsOd29 = drsOd29;
    }

    public Double getDrsValor30() {
        return drsValor30;
    }

    public void setDrsValor30(Double drsValor30) {
        this.drsValor30 = drsValor30;
    }

    public Integer getDrsOd30() {
        return drsOd30;
    }

    public void setDrsOd30(Integer drsOd30) {
        this.drsOd30 = drsOd30;
    }

    public Double getDrsValor31() {
        return drsValor31;
    }

    public void setDrsValor31(Double drsValor31) {
        this.drsValor31 = drsValor31;
    }

    public Integer getDrsOd31() {
        return drsOd31;
    }

    public void setDrsOd31(Integer drsOd31) {
        this.drsOd31 = drsOd31;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (drsId != null ? drsId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ShmvDiariosHid)) {
            return false;
        }
        ShmvDiariosHid other = (ShmvDiariosHid) object;
        if ((this.drsId == null && other.drsId != null) || (this.drsId != null && !this.drsId.equals(other.drsId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.gov.ideam.sirh.oferta.model.ShmvDiariosHid[ drsId=" + drsId + " ]";
    }


    public void setSumaTotalValid(Double sumaTotalValid) {
        this.sumaTotalValid = sumaTotalValid;
    }

    public Double getSumaTotalValid() {
        this.sumaTotalValid = new Double(0);
        this.sumaTotalValid =
                (this.getDrsValor1() != null ? this.getDrsValor1() : 0) +
                (this.getDrsValor2() != null ? this.getDrsValor2() : 0) +
                (this.getDrsValor3() != null ? this.getDrsValor3() : 0) +
                (this.getDrsValor4() != null ? this.getDrsValor4() : 0) +
                (this.getDrsValor5() != null ? this.getDrsValor5() : 0) +
                (this.getDrsValor6() != null ? this.getDrsValor6() : 0) +
                (this.getDrsValor7() != null ? this.getDrsValor7() : 0) +
                (this.getDrsValor8() != null ? this.getDrsValor8() : 0) +
                (this.getDrsValor9() != null ? this.getDrsValor9() : 0) +
                (this.getDrsValor10() != null ? this.getDrsValor10() : 0) +
                (this.getDrsValor11() != null ? this.getDrsValor11() : 0) +
                (this.getDrsValor12() != null ? this.getDrsValor12() : 0) +
                (this.getDrsValor13() != null ? this.getDrsValor13() : 0) +
                (this.getDrsValor14() != null ? this.getDrsValor14() : 0) +
                (this.getDrsValor15() != null ? this.getDrsValor15() : 0) +
                (this.getDrsValor16() != null ? this.getDrsValor16() : 0) +
                (this.getDrsValor17() != null ? this.getDrsValor17() : 0) +
                (this.getDrsValor18() != null ? this.getDrsValor18() : 0) +
                (this.getDrsValor19() != null ? this.getDrsValor19() : 0) +
                (this.getDrsValor20() != null ? this.getDrsValor20() : 0) +
                (this.getDrsValor21() != null ? this.getDrsValor21() : 0) +
                (this.getDrsValor22() != null ? this.getDrsValor22() : 0) +
                (this.getDrsValor23() != null ? this.getDrsValor23() : 0) +
                (this.getDrsValor24() != null ? this.getDrsValor24() : 0) +
                (this.getDrsValor25() != null ? this.getDrsValor25() : 0) +
                (this.getDrsValor26() != null ? this.getDrsValor26() : 0) +
                (this.getDrsValor27() != null ? this.getDrsValor27() : 0) +
                (this.getDrsValor28() != null ? this.getDrsValor28() : 0) +
                (this.getDrsValor29() != null ? this.getDrsValor29() : 0) +
                (this.getDrsValor30() != null ? this.getDrsValor30() : 0) +
                (this.getDrsValor31() != null ? this.getDrsValor31() : 0);
        return sumaTotalValid;
    }

    public void setCantidadDatosValid(Long cantidadDatosValid) {
        this.cantidadDatosValid = cantidadDatosValid;
    }

    public Long getCantidadDatosValid() {
        this.cantidadDatosValid = new Long(0);
        this.cantidadDatosValid =
                (this.getDrsValor1() != null ? 1 : new Long(0)) +
                (this.getDrsValor2() != null ? 1 : new Long(0)) +
                (this.getDrsValor3() != null ? 1 : new Long(0)) +
                (this.getDrsValor4() != null ? 1 : new Long(0)) +
                (this.getDrsValor5() != null ? 1 : new Long(0)) +
                (this.getDrsValor6() != null ? 1 : new Long(0)) +
                (this.getDrsValor7() != null ? 1 : new Long(0)) +
                (this.getDrsValor8() != null ? 1 : new Long(0)) +
                (this.getDrsValor9() != null ? 1 : new Long(0)) +
                (this.getDrsValor10() != null ? 1 : new Long(0)) +
                (this.getDrsValor11() != null ? 1 : new Long(0)) +
                (this.getDrsValor12() != null ? 1 : new Long(0)) +
                (this.getDrsValor13() != null ? 1 : new Long(0)) +
                (this.getDrsValor14() != null ? 1 : new Long(0)) +
                (this.getDrsValor15() != null ? 1 : new Long(0)) +
                (this.getDrsValor16() != null ? 1 : new Long(0)) +
                (this.getDrsValor17() != null ? 1 : new Long(0)) +
                (this.getDrsValor18() != null ? 1 : new Long(0)) +
                (this.getDrsValor19() != null ? 1 : new Long(0)) +
                (this.getDrsValor20() != null ? 1 : new Long(0)) +
                (this.getDrsValor21() != null ? 1 : new Long(0)) +
                (this.getDrsValor22() != null ? 1 : new Long(0)) +
                (this.getDrsValor23() != null ? 1 : new Long(0)) +
                (this.getDrsValor24() != null ? 1 : new Long(0)) +
                (this.getDrsValor25() != null ? 1 : new Long(0)) +
                (this.getDrsValor26() != null ? 1 : new Long(0)) +
                (this.getDrsValor27() != null ? 1 : new Long(0)) +
                (this.getDrsValor28() != null ? 1 : new Long(0)) +
                (this.getDrsValor29() != null ? 1 : new Long(0)) +
                (this.getDrsValor30() != null ? 1 : new Long(0)) +
                (this.getDrsValor31() != null ? 1 : new Long(0));

        return cantidadDatosValid;
    }


    public void setCantidadDatosNull(Long cantidadDatosNull) {
        this.cantidadDatosNull = cantidadDatosNull;
    }

    public Long getCantidadDatosNull() {
        this.cantidadDatosNull = new Long(0);
        this.cantidadDatosNull =
                (this.getDrsValor1() == null ? 1 : new Long(0)) +
                (this.getDrsValor2() == null ? 1 : new Long(0)) +
                (this.getDrsValor3() == null ? 1 : new Long(0)) +
                (this.getDrsValor4() == null ? 1 : new Long(0)) +
                (this.getDrsValor5() == null ? 1 : new Long(0)) +
                (this.getDrsValor6() == null ? 1 : new Long(0)) +
                (this.getDrsValor7() == null ? 1 : new Long(0)) +
                (this.getDrsValor8() == null ? 1 : new Long(0)) +
                (this.getDrsValor9() == null ? 1 : new Long(0)) +
                (this.getDrsValor10() == null ? 1 : new Long(0)) +
                (this.getDrsValor11() == null ? 1 : new Long(0)) +
                (this.getDrsValor12() == null ? 1 : new Long(0)) +
                (this.getDrsValor13() == null ? 1 : new Long(0)) +
                (this.getDrsValor14() == null ? 1 : new Long(0)) +
                (this.getDrsValor15() == null ? 1 : new Long(0)) +
                (this.getDrsValor16() == null ? 1 : new Long(0)) +
                (this.getDrsValor17() == null ? 1 : new Long(0)) +
                (this.getDrsValor18() == null ? 1 : new Long(0)) +
                (this.getDrsValor19() == null ? 1 : new Long(0)) +
                (this.getDrsValor20() == null ? 1 : new Long(0)) +
                (this.getDrsValor21() == null ? 1 : new Long(0)) +
                (this.getDrsValor22() == null ? 1 : new Long(0)) +
                (this.getDrsValor23() == null ? 1 : new Long(0)) +
                (this.getDrsValor24() == null ? 1 : new Long(0)) +
                (this.getDrsValor25() == null ? 1 : new Long(0)) +
                (this.getDrsValor26() == null ? 1 : new Long(0)) +
                (this.getDrsValor27() == null ? 1 : new Long(0)) +
                (this.getDrsValor28() == null ? 1 : new Long(0)) +
                (this.getDrsValor29() == null ? 1 : new Long(0)) +
                (this.getDrsValor30() == null ? 1 : new Long(0)) +
                (this.getDrsValor31() == null ? 1 : new Long(0));
        
        return cantidadDatosNull;
    }
}
