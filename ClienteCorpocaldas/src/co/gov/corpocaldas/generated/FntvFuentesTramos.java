
package co.gov.corpocaldas.generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FntvFuentesTramos complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FntvFuentesTramos">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idfuente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipofuente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nombrefuente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="existebd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numcarfuente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descripcionfuente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numcartramo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nombretramo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descripciontramo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="longitud" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="tipoflujo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="area" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="zona" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="subzona" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cuenca" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codigocuenca" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sistemareferenciapi" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="gradoslatpi" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="minutoslatpi" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="segundolatpi" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="gradoslonpi" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="minutoslonpi" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="segundolonpi" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="altitudpi" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="sistemareferenciapf" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="gradoslatpf" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="minutoslatpf" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="segundolatpf" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="gradoslonpf" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="minutoslonpf" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="segundolonpf" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="altitudpf" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FntvFuentesTramos", propOrder = {
    "idfuente",
    "tipofuente",
    "nombrefuente",
    "existebd",
    "numcarfuente",
    "descripcionfuente",
    "numcartramo",
    "nombretramo",
    "descripciontramo",
    "longitud",
    "tipoflujo",
    "area",
    "zona",
    "subzona",
    "cuenca",
    "codigocuenca",
    "sistemareferenciapi",
    "gradoslatpi",
    "minutoslatpi",
    "segundolatpi",
    "gradoslonpi",
    "minutoslonpi",
    "segundolonpi",
    "altitudpi",
    "sistemareferenciapf",
    "gradoslatpf",
    "minutoslatpf",
    "segundolatpf",
    "gradoslonpf",
    "minutoslonpf",
    "segundolonpf",
    "altitudpf"
})
public class FntvFuentesTramos {

    protected String idfuente;
    protected String tipofuente;
    protected String nombrefuente;
    protected String existebd;
    protected String numcarfuente;
    protected String descripcionfuente;
    protected String numcartramo;
    protected String nombretramo;
    protected String descripciontramo;
    protected double longitud;
    protected String tipoflujo;
    protected String area;
    protected String zona;
    protected String subzona;
    protected String cuenca;
    protected String codigocuenca;
    protected String sistemareferenciapi;
    protected int gradoslatpi;
    protected int minutoslatpi;
    protected double segundolatpi;
    protected int gradoslonpi;
    protected int minutoslonpi;
    protected double segundolonpi;
    protected double altitudpi;
    protected String sistemareferenciapf;
    protected int gradoslatpf;
    protected int minutoslatpf;
    protected double segundolatpf;
    protected int gradoslonpf;
    protected int minutoslonpf;
    protected double segundolonpf;
    protected double altitudpf;

    /**
     * Gets the value of the idfuente property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdfuente() {
        return idfuente;
    }

    /**
     * Sets the value of the idfuente property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdfuente(String value) {
        this.idfuente = value;
    }

    /**
     * Gets the value of the tipofuente property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipofuente() {
        return tipofuente;
    }

    /**
     * Sets the value of the tipofuente property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipofuente(String value) {
        this.tipofuente = value;
    }

    /**
     * Gets the value of the nombrefuente property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombrefuente() {
        return nombrefuente;
    }

    /**
     * Sets the value of the nombrefuente property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombrefuente(String value) {
        this.nombrefuente = value;
    }

    /**
     * Gets the value of the existebd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExistebd() {
        return existebd;
    }

    /**
     * Sets the value of the existebd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExistebd(String value) {
        this.existebd = value;
    }

    /**
     * Gets the value of the numcarfuente property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumcarfuente() {
        return numcarfuente;
    }

    /**
     * Sets the value of the numcarfuente property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumcarfuente(String value) {
        this.numcarfuente = value;
    }

    /**
     * Gets the value of the descripcionfuente property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcionfuente() {
        return descripcionfuente;
    }

    /**
     * Sets the value of the descripcionfuente property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcionfuente(String value) {
        this.descripcionfuente = value;
    }

    /**
     * Gets the value of the numcartramo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumcartramo() {
        return numcartramo;
    }

    /**
     * Sets the value of the numcartramo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumcartramo(String value) {
        this.numcartramo = value;
    }

    /**
     * Gets the value of the nombretramo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombretramo() {
        return nombretramo;
    }

    /**
     * Sets the value of the nombretramo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombretramo(String value) {
        this.nombretramo = value;
    }

    /**
     * Gets the value of the descripciontramo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripciontramo() {
        return descripciontramo;
    }

    /**
     * Sets the value of the descripciontramo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripciontramo(String value) {
        this.descripciontramo = value;
    }

    /**
     * Gets the value of the longitud property.
     * 
     */
    public double getLongitud() {
        return longitud;
    }

    /**
     * Sets the value of the longitud property.
     * 
     */
    public void setLongitud(double value) {
        this.longitud = value;
    }

    /**
     * Gets the value of the tipoflujo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoflujo() {
        return tipoflujo;
    }

    /**
     * Sets the value of the tipoflujo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoflujo(String value) {
        this.tipoflujo = value;
    }

    /**
     * Gets the value of the area property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArea() {
        return area;
    }

    /**
     * Sets the value of the area property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArea(String value) {
        this.area = value;
    }

    /**
     * Gets the value of the zona property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZona() {
        return zona;
    }

    /**
     * Sets the value of the zona property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZona(String value) {
        this.zona = value;
    }

    /**
     * Gets the value of the subzona property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubzona() {
        return subzona;
    }

    /**
     * Sets the value of the subzona property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubzona(String value) {
        this.subzona = value;
    }

    /**
     * Gets the value of the cuenca property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCuenca() {
        return cuenca;
    }

    /**
     * Sets the value of the cuenca property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCuenca(String value) {
        this.cuenca = value;
    }

    /**
     * Gets the value of the codigocuenca property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigocuenca() {
        return codigocuenca;
    }

    /**
     * Sets the value of the codigocuenca property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigocuenca(String value) {
        this.codigocuenca = value;
    }

    /**
     * Gets the value of the sistemareferenciapi property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSistemareferenciapi() {
        return sistemareferenciapi;
    }

    /**
     * Sets the value of the sistemareferenciapi property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSistemareferenciapi(String value) {
        this.sistemareferenciapi = value;
    }

    /**
     * Gets the value of the gradoslatpi property.
     * 
     */
    public int getGradoslatpi() {
        return gradoslatpi;
    }

    /**
     * Sets the value of the gradoslatpi property.
     * 
     */
    public void setGradoslatpi(int value) {
        this.gradoslatpi = value;
    }

    /**
     * Gets the value of the minutoslatpi property.
     * 
     */
    public int getMinutoslatpi() {
        return minutoslatpi;
    }

    /**
     * Sets the value of the minutoslatpi property.
     * 
     */
    public void setMinutoslatpi(int value) {
        this.minutoslatpi = value;
    }

    /**
     * Gets the value of the segundolatpi property.
     * 
     */
    public double getSegundolatpi() {
        return segundolatpi;
    }

    /**
     * Sets the value of the segundolatpi property.
     * 
     */
    public void setSegundolatpi(double value) {
        this.segundolatpi = value;
    }

    /**
     * Gets the value of the gradoslonpi property.
     * 
     */
    public int getGradoslonpi() {
        return gradoslonpi;
    }

    /**
     * Sets the value of the gradoslonpi property.
     * 
     */
    public void setGradoslonpi(int value) {
        this.gradoslonpi = value;
    }

    /**
     * Gets the value of the minutoslonpi property.
     * 
     */
    public int getMinutoslonpi() {
        return minutoslonpi;
    }

    /**
     * Sets the value of the minutoslonpi property.
     * 
     */
    public void setMinutoslonpi(int value) {
        this.minutoslonpi = value;
    }

    /**
     * Gets the value of the segundolonpi property.
     * 
     */
    public double getSegundolonpi() {
        return segundolonpi;
    }

    /**
     * Sets the value of the segundolonpi property.
     * 
     */
    public void setSegundolonpi(double value) {
        this.segundolonpi = value;
    }

    /**
     * Gets the value of the altitudpi property.
     * 
     */
    public double getAltitudpi() {
        return altitudpi;
    }

    /**
     * Sets the value of the altitudpi property.
     * 
     */
    public void setAltitudpi(double value) {
        this.altitudpi = value;
    }

    /**
     * Gets the value of the sistemareferenciapf property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSistemareferenciapf() {
        return sistemareferenciapf;
    }

    /**
     * Sets the value of the sistemareferenciapf property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSistemareferenciapf(String value) {
        this.sistemareferenciapf = value;
    }

    /**
     * Gets the value of the gradoslatpf property.
     * 
     */
    public int getGradoslatpf() {
        return gradoslatpf;
    }

    /**
     * Sets the value of the gradoslatpf property.
     * 
     */
    public void setGradoslatpf(int value) {
        this.gradoslatpf = value;
    }

    /**
     * Gets the value of the minutoslatpf property.
     * 
     */
    public int getMinutoslatpf() {
        return minutoslatpf;
    }

    /**
     * Sets the value of the minutoslatpf property.
     * 
     */
    public void setMinutoslatpf(int value) {
        this.minutoslatpf = value;
    }

    /**
     * Gets the value of the segundolatpf property.
     * 
     */
    public double getSegundolatpf() {
        return segundolatpf;
    }

    /**
     * Sets the value of the segundolatpf property.
     * 
     */
    public void setSegundolatpf(double value) {
        this.segundolatpf = value;
    }

    /**
     * Gets the value of the gradoslonpf property.
     * 
     */
    public int getGradoslonpf() {
        return gradoslonpf;
    }

    /**
     * Sets the value of the gradoslonpf property.
     * 
     */
    public void setGradoslonpf(int value) {
        this.gradoslonpf = value;
    }

    /**
     * Gets the value of the minutoslonpf property.
     * 
     */
    public int getMinutoslonpf() {
        return minutoslonpf;
    }

    /**
     * Sets the value of the minutoslonpf property.
     * 
     */
    public void setMinutoslonpf(int value) {
        this.minutoslonpf = value;
    }

    /**
     * Gets the value of the segundolonpf property.
     * 
     */
    public double getSegundolonpf() {
        return segundolonpf;
    }

    /**
     * Sets the value of the segundolonpf property.
     * 
     */
    public void setSegundolonpf(double value) {
        this.segundolonpf = value;
    }

    /**
     * Gets the value of the altitudpf property.
     * 
     */
    public double getAltitudpf() {
        return altitudpf;
    }

    /**
     * Sets the value of the altitudpf property.
     * 
     */
    public void setAltitudpf(double value) {
        this.altitudpf = value;
    }

}
