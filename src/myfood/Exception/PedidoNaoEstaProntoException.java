package myfood.Exception;

public class PedidoNaoEstaProntoException extends RuntimeException {
    public PedidoNaoEstaProntoException() {
        super("Pedido nao esta pronto para entrega");
    }
}
