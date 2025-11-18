package myfood.Exception;

public class NaoEhUmMercadoException extends RuntimeException {
    public NaoEhUmMercadoException() {
        super("Nao e um mercado valido");
    }
}
