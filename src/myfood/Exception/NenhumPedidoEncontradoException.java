package myfood.Exception;

public class NenhumPedidoEncontradoException extends RuntimeException {
    public NenhumPedidoEncontradoException() {
        super("Nenhum pedido encontrado");
    }
}
