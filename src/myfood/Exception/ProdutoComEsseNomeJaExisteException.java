package myfood.Exception;

public class ProdutoComEsseNomeJaExisteException extends RuntimeException {
    public ProdutoComEsseNomeJaExisteException() {
        super("Ja existe um produto com esse nome para essa empresa");
    }
}
