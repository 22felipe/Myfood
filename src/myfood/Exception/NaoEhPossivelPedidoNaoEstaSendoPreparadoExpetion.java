package myfood.Exception;

public class NaoEhPossivelPedidoNaoEstaSendoPreparadoExpetion extends RuntimeException {
    public NaoEhPossivelPedidoNaoEstaSendoPreparadoExpetion() {
        super("Nao e possivel liberar um produto que nao esta sendo preparado");
    }
}
