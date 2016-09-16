
package co.gov.corpocaldas.generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="getAllFuentesResult" type="{WebServiceCAR}ArrayOfFntvFuentesTramos" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "getAllFuentesResult"
})
@XmlRootElement(name = "getAllFuentesResponse")
public class GetAllFuentesResponse {

    protected ArrayOfFntvFuentesTramos getAllFuentesResult;

    /**
     * Gets the value of the getAllFuentesResult property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfFntvFuentesTramos }
     *     
     */
    public ArrayOfFntvFuentesTramos getGetAllFuentesResult() {
        return getAllFuentesResult;
    }

    /**
     * Sets the value of the getAllFuentesResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfFntvFuentesTramos }
     *     
     */
    public void setGetAllFuentesResult(ArrayOfFntvFuentesTramos value) {
        this.getAllFuentesResult = value;
    }

}
