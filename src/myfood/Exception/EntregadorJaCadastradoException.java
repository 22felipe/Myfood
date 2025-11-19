package myfood.Exception;

public class EntregadorJaCadastradoException extends RuntimeException {
    public EntregadorJaCadastradoException() {
        super("Entregador ja cadastrado");
    }
}
