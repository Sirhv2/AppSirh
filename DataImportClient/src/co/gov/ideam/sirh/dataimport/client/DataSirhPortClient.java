package co.gov.ideam.sirh.dataimport.client;


import javax.xml.ws.WebServiceRef;
// !THE CHANGES MADE TO THIS FILE WILL BE DESTROYED IF REGENERATED!
// This source file is generated by Oracle tools
// Contents may be subject to change
// For reporting problems, use the following
// Version = Oracle WebServices (11.1.1.0.0, build 081105.0700.56405)

public class DataSirhPortClient
{
  @WebServiceRef
  private static ImportDatosSirh importDatosSirh;

  public static void main(String [] args)
  {
    importDatosSirh = new ImportDatosSirh();
    DataSirh dataSirh = importDatosSirh.getDataSirhPort("http://servicio.sirh.ideam.gov.co/");
    // Add your code to call the desired methods.
  }
}
