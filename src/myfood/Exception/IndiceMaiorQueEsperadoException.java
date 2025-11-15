package myfood.Exception;

public class IndiceMaiorQueEsperadoException extends RuntimeException {
    public IndiceMaiorQueEsperadoException() {
        super("Indice maior que o esperado");
    }
}
