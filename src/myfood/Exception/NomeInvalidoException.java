package myfood.Exception;

public class NomeInvalidoException extends RuntimeException {
    public NomeInvalidoException() {
        super("Nome invalido");
    }
}
