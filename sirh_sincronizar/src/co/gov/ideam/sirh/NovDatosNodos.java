package co.gov.ideam.sirh;

public class NovDatosNodos {

 
    private String modulo;
    private String data;
    private String dataIDEAM;
    public NovDatosNodos() {
    }

    public NovDatosNodos(String modulo, String data) {
        super();
        this.modulo = modulo;
     
        this.data = data;
    }

   


    public void setData(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

  

    public void setModulo(String modulo) {
        this.modulo = modulo;
    }

    public String getModulo() {
        return modulo;
    }

    public void setDataIDEAM(String dataIDEAM) {
        this.dataIDEAM = dataIDEAM;
    }

    public String getDataIDEAM() {
        return dataIDEAM;
    }
}
