package myfood.Exception;

public class NaoEhPossivelAdcionarProdutosPedidoFechadoException extends RuntimeException {
    public NaoEhPossivelAdcionarProdutosPedidoFechadoException() {
        super("Nao e possivel adcionar produtos a um pedido fechado");
    }
}
