package myfood.Exception;

public class NaoEhUmEntregadorValidoException extends RuntimeException {
    public NaoEhUmEntregadorValidoException() {
        super("Nao e um entregador valido");
    }
}
