package co.gov.corpocaldas.cliente;

import java.io.File;

import java.net.MalformedURLException;
import java.net.URL;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
// !DO NOT EDIT THIS FILE!
// This source file is generated by Oracle tools
// Contents may be subject to change
// For reporting problems, use the following
// Version = Oracle WebServices (11.1.1.0.0, build 081105.0700.56405)

@WebServiceClient(wsdlLocation="DataSirh.wsdl", targetNamespace="WebServiceCAR",
  name="DataSirh")
public class DataSirh
  extends Service
{
  private static URL wsdlLocationURL;

  private static Logger logger;
  static {
    try
    {
      logger = Logger.getLogger("co.gov.corpocaldas.cliente.DataSirh");
      URL baseUrl = DataSirh.class.getResource(".");
      if (baseUrl == null)
      {
        wsdlLocationURL = DataSirh.class.getResource("DataSirh.wsdl");
        if (wsdlLocationURL == null)
        {
          baseUrl = new File(".").toURL();
          wsdlLocationURL = new URL(baseUrl, "DataSirh.wsdl");
        }
      }
      else
      {
        wsdlLocationURL = new URL(baseUrl, "DataSirh.wsdl");
      }
    }
    catch (MalformedURLException e)
    {
      logger.log(Level.ALL,
          "Failed to create wsdlLocationURL using DataSirh.wsdl", e);
    }
  }

  public DataSirh()
  {
    super(wsdlLocationURL, new QName("WebServiceCAR", "DataSirh"));
  }

  public DataSirh(URL wsdlLocation, QName serviceName)
  {
    super(wsdlLocation, serviceName);
  }

  @WebEndpoint(name="DataSirhSoap")
  public co.gov.corpocaldas.cliente.DataSirhSoap getDataSirhSoap()
  {
    return (co.gov.corpocaldas.cliente.DataSirhSoap) super.getPort(new QName("WebServiceCAR",
                                                                             "DataSirhSoap"),
                                                                   co.gov.corpocaldas.cliente.DataSirhSoap.class);
  }

  @WebEndpoint(name="DataSirhSoap")
  public co.gov.corpocaldas.cliente.DataSirhSoap getDataSirhSoap(WebServiceFeature[] features)
  {
    return (co.gov.corpocaldas.cliente.DataSirhSoap) super.getPort(new QName("WebServiceCAR",
                                                                             "DataSirhSoap"),
                                                                   co.gov.corpocaldas.cliente.DataSirhSoap.class,
                                                                   features);
  }

  @WebEndpoint(name="DataSirhSoap12")
  public co.gov.corpocaldas.cliente.DataSirhSoap getDataSirhSoap12()
  {
    return (co.gov.corpocaldas.cliente.DataSirhSoap) super.getPort(new QName("WebServiceCAR",
                                                                             "DataSirhSoap12"),
                                                                   co.gov.corpocaldas.cliente.DataSirhSoap.class);
  }

  @WebEndpoint(name="DataSirhSoap12")
  public co.gov.corpocaldas.cliente.DataSirhSoap getDataSirhSoap12(WebServiceFeature[] features)
  {
    return (co.gov.corpocaldas.cliente.DataSirhSoap) super.getPort(new QName("WebServiceCAR",
                                                                             "DataSirhSoap12"),
                                                                   co.gov.corpocaldas.cliente.DataSirhSoap.class,
                                                                   features);
  }
}
