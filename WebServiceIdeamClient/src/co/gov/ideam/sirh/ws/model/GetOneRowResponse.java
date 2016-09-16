
package co.gov.ideam.sirh.ws.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getOneRowResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getOneRowResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="singleRow" type="{http://businessl.webservices.sirh.ideam.gov.co/}singleRowWebService" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getOneRowResponse", propOrder = {
    "singleRow"
})
public class GetOneRowResponse {

    protected SingleRowWebService singleRow;

    /**
     * Gets the value of the singleRow property.
     * 
     * @return
     *     possible object is
     *     {@link SingleRowWebService }
     *     
     */
    public SingleRowWebService getSingleRow() {
        return singleRow;
    }

    /**
     * Sets the value of the singleRow property.
     * 
     * @param value
     *     allowed object is
     *     {@link SingleRowWebService }
     *     
     */
    public void setSingleRow(SingleRowWebService value) {
        this.singleRow = value;
    }

}
