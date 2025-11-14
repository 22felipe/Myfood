package myfood.Exception;

public class AtributoNaoExisteException extends RuntimeException {
    public AtributoNaoExisteException() {
        super("Atributo nao existe");
    }
}
