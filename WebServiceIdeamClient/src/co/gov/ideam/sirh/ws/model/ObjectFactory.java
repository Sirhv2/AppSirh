
package co.gov.ideam.sirh.ws.model;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the co.gov.ideam.sirh.ws.model package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetOneRow_QNAME = new QName("http://businessl.webservices.sirh.ideam.gov.co/", "getOneRow");
    private final static QName _GetSingleData_QNAME = new QName("http://businessl.webservices.sirh.ideam.gov.co/", "getSingleData");
    private final static QName _GetMultipleRows_QNAME = new QName("http://businessl.webservices.sirh.ideam.gov.co/", "getMultipleRows");
    private final static QName _GetSingleDataResponse_QNAME = new QName("http://businessl.webservices.sirh.ideam.gov.co/", "getSingleDataResponse");
    private final static QName _IdeamException_QNAME = new QName("http://businessl.webservices.sirh.ideam.gov.co/", "IdeamException");
    private final static QName _GetMultipleRowsResponse_QNAME = new QName("http://businessl.webservices.sirh.ideam.gov.co/", "getMultipleRowsResponse");
    private final static QName _GetOneRowResponse_QNAME = new QName("http://businessl.webservices.sirh.ideam.gov.co/", "getOneRowResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: co.gov.ideam.sirh.ws.model
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DataWebService }
     * 
     */
    public DataWebService createDataWebService() {
        return new DataWebService();
    }

    /**
     * Create an instance of {@link GetOneRow }
     * 
     */
    public GetOneRow createGetOneRow() {
        return new GetOneRow();
    }

    /**
     * Create an instance of {@link GetMultipleRowsResponse }
     * 
     */
    public GetMultipleRowsResponse createGetMultipleRowsResponse() {
        return new GetMultipleRowsResponse();
    }

    /**
     * Create an instance of {@link GetOneRowResponse }
     * 
     */
    public GetOneRowResponse createGetOneRowResponse() {
        return new GetOneRowResponse();
    }

    /**
     * Create an instance of {@link IdeamException }
     * 
     */
    public IdeamException createIdeamException() {
        return new IdeamException();
    }

    /**
     * Create an instance of {@link SingleRowWebService }
     * 
     */
    public SingleRowWebService createSingleRowWebService() {
        return new SingleRowWebService();
    }

    /**
     * Create an instance of {@link GetSingleDataResponse }
     * 
     */
    public GetSingleDataResponse createGetSingleDataResponse() {
        return new GetSingleDataResponse();
    }

    /**
     * Create an instance of {@link GetSingleData }
     * 
     */
    public GetSingleData createGetSingleData() {
        return new GetSingleData();
    }

    /**
     * Create an instance of {@link GetMultipleRows }
     * 
     */
    public GetMultipleRows createGetMultipleRows() {
        return new GetMultipleRows();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetOneRow }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://businessl.webservices.sirh.ideam.gov.co/", name = "getOneRow")
    public JAXBElement<GetOneRow> createGetOneRow(GetOneRow value) {
        return new JAXBElement<GetOneRow>(_GetOneRow_QNAME, GetOneRow.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetSingleData }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://businessl.webservices.sirh.ideam.gov.co/", name = "getSingleData")
    public JAXBElement<GetSingleData> createGetSingleData(GetSingleData value) {
        return new JAXBElement<GetSingleData>(_GetSingleData_QNAME, GetSingleData.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetMultipleRows }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://businessl.webservices.sirh.ideam.gov.co/", name = "getMultipleRows")
    public JAXBElement<GetMultipleRows> createGetMultipleRows(GetMultipleRows value) {
        return new JAXBElement<GetMultipleRows>(_GetMultipleRows_QNAME, GetMultipleRows.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetSingleDataResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://businessl.webservices.sirh.ideam.gov.co/", name = "getSingleDataResponse")
    public JAXBElement<GetSingleDataResponse> createGetSingleDataResponse(GetSingleDataResponse value) {
        return new JAXBElement<GetSingleDataResponse>(_GetSingleDataResponse_QNAME, GetSingleDataResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IdeamException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://businessl.webservices.sirh.ideam.gov.co/", name = "IdeamException")
    public JAXBElement<IdeamException> createIdeamException(IdeamException value) {
        return new JAXBElement<IdeamException>(_IdeamException_QNAME, IdeamException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetMultipleRowsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://businessl.webservices.sirh.ideam.gov.co/", name = "getMultipleRowsResponse")
    public JAXBElement<GetMultipleRowsResponse> createGetMultipleRowsResponse(GetMultipleRowsResponse value) {
        return new JAXBElement<GetMultipleRowsResponse>(_GetMultipleRowsResponse_QNAME, GetMultipleRowsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetOneRowResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://businessl.webservices.sirh.ideam.gov.co/", name = "getOneRowResponse")
    public JAXBElement<GetOneRowResponse> createGetOneRowResponse(GetOneRowResponse value) {
        return new JAXBElement<GetOneRowResponse>(_GetOneRowResponse_QNAME, GetOneRowResponse.class, null, value);
    }

}
