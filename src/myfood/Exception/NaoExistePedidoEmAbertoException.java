package myfood.Exception;

public class NaoExistePedidoEmAbertoException extends RuntimeException {
    public NaoExistePedidoEmAbertoException() {
        super("Nao existe pedido em aberto");
    }
}
