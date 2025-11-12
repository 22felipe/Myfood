package myfood.Exception;

public class ContaComEmailJaExisteException extends RuntimeException {

    public ContaComEmailJaExisteException() {
        super("Conta com esse email ja existe");
    }

    public ContaComEmailJaExisteException(String mensagem) {
        super(mensagem);
    }
}
