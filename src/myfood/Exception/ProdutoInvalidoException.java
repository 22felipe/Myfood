package myfood.Exception;

public class ProdutoInvalidoException extends RuntimeException {
    public ProdutoInvalidoException() {
        super("Produto invalido");
    }
}
