package myfood.Exception;

public class SenhaIncorretaException extends RuntimeException {
    public SenhaIncorretaException(String email) {
        super("Senha incorreta para o email: " + email);
    }
}
