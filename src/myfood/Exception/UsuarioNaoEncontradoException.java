package myfood.Exception;

public class UsuarioNaoEncontradoException extends RuntimeException {
  public UsuarioNaoEncontradoException() {
    super("Usuario nao cadastrado.");
  }
}
