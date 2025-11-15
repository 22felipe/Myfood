package myfood.Exception;

public class NaoEhPossivelRemoverProdutosEmPedidoFechadoException extends RuntimeException {
    public NaoEhPossivelRemoverProdutosEmPedidoFechadoException() {
        super("Nao e possivel remover produtos de um pedido fechado");
    }
}
