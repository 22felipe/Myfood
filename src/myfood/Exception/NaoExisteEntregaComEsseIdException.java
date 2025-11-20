package myfood.Exception;

public class NaoExisteEntregaComEsseIdException extends RuntimeException {
    public NaoExisteEntregaComEsseIdException() {
        super("Nao existe entrega com esse id");
    }
}
