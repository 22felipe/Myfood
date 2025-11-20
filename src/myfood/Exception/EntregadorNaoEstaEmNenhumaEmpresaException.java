package myfood.Exception;

public class EntregadorNaoEstaEmNenhumaEmpresaException extends RuntimeException {
    public EntregadorNaoEstaEmNenhumaEmpresaException() {
        super("Entregador nao estar em nenhuma empresa.");
    }
}
