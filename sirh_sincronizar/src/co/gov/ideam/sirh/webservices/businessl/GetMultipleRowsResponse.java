
package co.gov.ideam.sirh.webservices.businessl;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import co.gov.ideam.sirh.webservices.businessl.types.SingleRowWebService;


/**
 * <p>Java class for getMultipleRowsResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getMultipleRowsResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="multipleRows" type="{http://businessl.webservices.sirh.ideam.gov.co/types}singleRowWebService" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getMultipleRowsResponse", propOrder = {
    "multipleRows"
})
public class GetMultipleRowsResponse {

    protected List<SingleRowWebService> multipleRows;

    /**
     * Gets the value of the multipleRows property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the multipleRows property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMultipleRows().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SingleRowWebService }
     * 
     * 
     */
    public List<SingleRowWebService> getMultipleRows() {
        if (multipleRows == null) {
            multipleRows = new ArrayList<SingleRowWebService>();
        }
        return this.multipleRows;
    }

}
