package myfood.Exception;

public class SenhaIvalidoException extends RuntimeException {
  public SenhaIvalidoException() {
    super("Senha invalido");
  }
}
