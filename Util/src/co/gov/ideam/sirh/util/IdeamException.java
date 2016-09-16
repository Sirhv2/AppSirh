package co.gov.ideam.sirh.util;
/**
 * Permite manejar excepciones personzalizadas para el aplicativo
 */
public class IdeamException extends Exception {
    public IdeamException(Throwable throwable) {        
        super(throwable);
        System.out.println(throwable.getMessage());
        throwable.printStackTrace(System.out);        
    }

    public IdeamException(String string, Throwable throwable) {
        super(string, throwable);
    }

    public IdeamException(String string) {
        super(string);
    }

    public IdeamException() {
        super();
    }
}
