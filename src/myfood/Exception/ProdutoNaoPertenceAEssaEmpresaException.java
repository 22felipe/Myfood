package myfood.Exception;

public class ProdutoNaoPertenceAEssaEmpresaException extends RuntimeException {
    public ProdutoNaoPertenceAEssaEmpresaException() {
        super("O produto nao pertence a essa empresa");
    }
}
