
package co.gov.ideam.sirh.ws.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getSingleDataResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getSingleDataResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="singleData" type="{http://businessl.webservices.sirh.ideam.gov.co/}dataWebService" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getSingleDataResponse", propOrder = {
    "singleData"
})
public class GetSingleDataResponse {

    protected DataWebService singleData;

    /**
     * Gets the value of the singleData property.
     * 
     * @return
     *     possible object is
     *     {@link DataWebService }
     *     
     */
    public DataWebService getSingleData() {
        return singleData;
    }

    /**
     * Sets the value of the singleData property.
     * 
     * @param value
     *     allowed object is
     *     {@link DataWebService }
     *     
     */
    public void setSingleData(DataWebService value) {
        this.singleData = value;
    }

}
