package myfood.Exception;

public class PedidoJaLiberadoException extends RuntimeException {
    public PedidoJaLiberadoException() {
        super("Pedido ja liberado");
    }
}
