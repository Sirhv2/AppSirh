package co.gov.ideam.sirh.calidad.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@NamedQueries({
  @NamedQuery(name = "MuestrasIca.findByMuestra", query = "select o from MuestrasIca o where o.codigoMuestra = :codigo")
})
@Table(name = "CALT_MUESTRAS_ICA_FQ")
public class MuestrasIca implements Serializable{
    
    @Id
    @Column(name = "CODIGO_MUESTRA", nullable = false)
    private Long codigoMuestra;
    @Column(name = "PRESIONATM", nullable = false)
    private Double presionAtm;
    @Column(name = "PW", nullable = false)
    private Double pw;
    @Column(name = "TETA", nullable = false)
    private Double teta;
    @Column(name = "SOD", nullable = false)
    private Double sod;
    @Column(name = "PSOD", nullable = false)
    private Double psod;
    @Column(name = "IPSOD", nullable = false)
    private Double ipsod;
    @Column(name = "ISST", nullable = false)
    private Double isst;
    @Column(name = "IDQO", nullable = false)
    private Double idqo;
    @Column(name = "ICOND", nullable = false)
    private Double icond;
    @Column(name = "IPH", nullable = false)
    private Double iph;
    @Column(name = "ICA5", nullable = false)
    private Double ica5;
    @Column(name = "INP", nullable = false)
    private Double inp;
    @Column(name = "ICA6", nullable = false)
    private Double ica6;
    @Column(name = "IECOLI", nullable = false)
    private Double iecoli;
    @Column(name = "ICA7", nullable = false)    
    private Double ica7;
    
    @Transient
    private String mensaje;
    
    public MuestrasIca() {
    }

    public Long getCodigoMuestra() {
        return codigoMuestra;
    }

    public void setCodigoMuestra(Long codigoMuestra) {
        this.codigoMuestra = codigoMuestra;
    }

    public Double getPresionAtm() {
        return presionAtm;
    }

    public void setPresionAtm(Double presionaTmgh) {
        this.presionAtm = presionaTmgh;
    }

    public Double getPw() {
        return pw;
    }

    public void setPw(Double pw) {
        this.pw = pw;
    }

    public Double getTeta() {
        return teta;
    }

    public void setTeta(Double teta) {
        this.teta = teta;
    }

    public Double getSod() {
        return sod;
    }

    public void setSod(Double sod) {
        this.sod = sod;
    }

    public Double getPsod() {
        return psod;
    }

    public void setPsod(Double psod) {
        this.psod = psod;
    }

    public Double getIpsod() {
        return ipsod;
    }

    public void setIpsod(Double ipsod) {
        this.ipsod = ipsod;
    }

    public Double getIsst() {
        return isst;
    }

    public void setIsst(Double isst) {
        this.isst = isst;
    }

    public Double getIdqo() {
        return idqo;
    }

    public void setIdqo(Double idqo) {
        this.idqo = idqo;
    }

    public Double getIcond() {
        return icond;
    }

    public void setIcond(Double icond) {
        this.icond = icond;
    }

    public Double getIph() {
        return iph;
    }

    public void setIph(Double iph) {
        this.iph = iph;
    }

    public Double getIca5() {
        return ica5;
    }

    public void setIca5(Double ica5) {
        this.ica5 = ica5;
    }

    public Double getInp() {
        return inp;
    }

    public void setInp(Double inp) {
        this.inp = inp;
    }

    public Double getIca6() {
        return ica6;
    }

    public void setIca6(Double ica6) {
        this.ica6 = ica6;
    }

    public Double getIecoli() {
        return iecoli;
    }

    public void setIecoli(Double iecoli) {
        this.iecoli = iecoli;
    }

    public Double getIca7() {
        return ica7;
    }

    public void setIca7(Double ica7) {
        this.ica7 = ica7;
    }
    
    public boolean isValid(){
        long tope = 99999999;
        if (presionAtm !=null && presionAtm > tope){
            return false;
        }
        if (pw !=null && pw >  tope){
            return false;
        }
        if (teta !=null && teta > tope){
            return false;
        }
        if (sod !=null && sod > tope){
            return false;
        }
        if (psod !=null && psod > tope){
            return false;
        }
        if (ipsod !=null && ipsod> tope){
            return false;
        }
        if (isst !=null && isst > tope){
            return false;
        }
        if (idqo !=null && idqo > tope){
            return false;
        }
        if (icond !=null && icond > tope){
            return false;
        }
        if (iph !=null && iph > tope){
            return false;
        }
        if (ica5 !=null && ica5 > tope){
            return false;
        }
        if (inp !=null && inp> tope){
            return false;
        }
        if (ica6 !=null && ica6> tope){
            return false;
        }
        if (iecoli !=null && iecoli> tope){
            return false;
        }
        if (ica7 !=null && ica7> tope){
            return false;
        }
        return true;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
