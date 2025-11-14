package myfood.Exception;

public class LoginInvalidoException extends RuntimeException {
    public LoginInvalidoException() {
        super("Login ou senha invalidos");
    }
}
