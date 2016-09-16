package co.gov.ideam.sirh.mail;
/**
 * Almacena los parametros de configuracion del correo que deben ser
 * cargados externamente al momento de iniciar el sistema
 */
public class MailConfigurator {
    
    private static String usuario;
    private static String password;
    private static String host;
    private static String puerto;

    public static String getUsuario() {
        return usuario;
    }

    public static void setUsuario(String usuario) {
        MailConfigurator.usuario = usuario;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        MailConfigurator.password = password;
    }

    public static String getHost() {
        return host;
    }

    public static void setHost(String host) {
        MailConfigurator.host = host;
    }

    public static String getPuerto() {
        return puerto;
    }

    public static void setPuerto(String puerto) {
        MailConfigurator.puerto = puerto;
    }
}
