package myfood.Exception;

public class UsuarioNaoEhUmEntregadorException extends RuntimeException {
    public UsuarioNaoEhUmEntregadorException() {
        super("Usuario nao e um entregador");
    }
}
