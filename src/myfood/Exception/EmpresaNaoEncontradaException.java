package myfood.Exception;

public class EmpresaNaoEncontradaException extends RuntimeException {
    public EmpresaNaoEncontradaException() {
        super("Empresa nao encontrada");
    }
}
