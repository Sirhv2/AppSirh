package co.gov.ideam.sirh.webservices.model.autenticar;

public class OE_Autenticar {
    public OE_Autenticar() {
    }

    private String aliasUsuario;
    private String clave;


    public void setAliasUsuario(String aliasUsuario) {
        this.aliasUsuario = aliasUsuario;
    }

    public String getAliasUsuario() {
        return aliasUsuario;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getClave() {
        return clave;
    }
}
