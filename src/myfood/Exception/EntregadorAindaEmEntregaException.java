package myfood.Exception;

public class EntregadorAindaEmEntregaException extends RuntimeException {
    public EntregadorAindaEmEntregaException() {
        super("Entregador ainda em entrega");
    }
}
