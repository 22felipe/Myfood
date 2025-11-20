package myfood.Exception;

public class EntregaNaoEncontradaException extends RuntimeException {
    public EntregaNaoEncontradaException() {
        super("Entrega não encontrada");
    }
}
