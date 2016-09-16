package co.gov.ideam.sirh;

public class NovRecibida {

    private String serviceName;
    private String methodName;
    private String data;

    public NovRecibida() {
    }

    public NovRecibida(String serviceName, String methodName, String data) {
        super();
        this.serviceName = serviceName;
        this.methodName = methodName;
        this.data = data;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }
}
