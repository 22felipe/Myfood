package myfood.Exception;

public class EmpresaNaoCadastradaException extends RuntimeException {
    public EmpresaNaoCadastradaException() {
        super("Empresa nao cadastrada");
    }
}
