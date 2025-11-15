package myfood.Exception;

public class DonoDeEmpresaNaoPodeFazerUmPedidoExpetion extends RuntimeException {
    public DonoDeEmpresaNaoPodeFazerUmPedidoExpetion() {
        super("Dono de empresa nao pode fazer um pedido");
    }
}
