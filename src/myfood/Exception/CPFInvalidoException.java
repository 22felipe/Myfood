package myfood.Exception;

public class CPFInvalidoException extends RuntimeException {
    public CPFInvalidoException() {
        super("CPF invalido");
    }
}
