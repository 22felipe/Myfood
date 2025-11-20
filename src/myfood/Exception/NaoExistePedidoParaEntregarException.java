package myfood.Exception;

public class NaoExistePedidoParaEntregarException extends RuntimeException {
    public NaoExistePedidoParaEntregarException() {
        super("Nao existe pedido para entrega");
    }
}
