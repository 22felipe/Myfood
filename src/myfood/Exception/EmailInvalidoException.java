package myfood.Exception;

public class EmailInvalidoException extends RuntimeException {
    public EmailInvalidoException() {
        super("Email invalido");
    }
}
