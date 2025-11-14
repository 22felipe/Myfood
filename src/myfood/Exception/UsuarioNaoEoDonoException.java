package myfood.Exception;

public class UsuarioNaoEoDonoException extends RuntimeException {
    public UsuarioNaoEoDonoException() {
        super("Usuario nao eh dono");
    }
}
