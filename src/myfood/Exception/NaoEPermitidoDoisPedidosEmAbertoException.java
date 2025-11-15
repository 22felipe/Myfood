package myfood.Exception;

public class NaoEPermitidoDoisPedidosEmAbertoException extends RuntimeException {
    public NaoEPermitidoDoisPedidosEmAbertoException() {
        super("Nao e permitido ter dois pedidos em aberto para a mesma empresa");
    }
}
