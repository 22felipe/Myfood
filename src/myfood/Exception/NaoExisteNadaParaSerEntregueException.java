package myfood.Exception;

public class NaoExisteNadaParaSerEntregueException extends RuntimeException {
    public NaoExisteNadaParaSerEntregueException() {
        super("Nao existe nada para ser entregue com esse id");
    }
}
