package myfood.Exception;

public class UsuarioNaoEncontradoException extends RuntimeException {
    public UsuarioNaoEncontradoException(String email) {
        super("Usuário com email " + email + " não encontrado.");
    }
}
