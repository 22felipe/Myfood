package myfood.Exception;

public class AtributoInvalidoException extends RuntimeException {
    public AtributoInvalidoException(String atributo) {
        super("Atributo invalido");
    }
}
