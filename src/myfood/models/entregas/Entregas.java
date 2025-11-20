package myfood.models.entregas;

import myfood.Exception.AtributoInvalidoException;
import myfood.Exception.AtributoNaoExisteException;
import myfood.models.Produtos;

import java.util.ArrayList;
import java.util.List;

public class Entregas {

    private static int contadorId = 0;

    private int id;
    private String nomeCliente;
    private String nomeEmpresa;
    private String nomeEntregador;
    private int pedido;
    private int entregador;
    private String destino;
    private List<Produtos> produtos;


    public Entregas() { }

    public Entregas(int pedido, int entregador, String destino) {
        this.id = contadorId++;
        this.pedido = pedido;
        this.entregador = entregador;
        this.destino = destino;
        this.produtos = new ArrayList<>();
    }

    public String getAtributo(String nomeAtributo) {
        switch (nomeAtributo.toLowerCase()) {
            case "id": return String.valueOf(id);
            case "cliente": return nomeCliente;
            case "empresa": return nomeEmpresa;
            case "pedido": return String.valueOf(pedido);
            case "entregador": return nomeEntregador;
            case "destino": return destino;
            case "produtos":
                if (produtos == null) return "{[]}";
                return "{[" + produtos.stream()
                        .map(Produtos::getNome)
                        .toList()
                        .toString()
                        .replaceAll("^\\[|]$", "") + "]}";
            default:
                throw new AtributoNaoExisteException();
        }
    }

    //Getters
    public String getNomeCliente() { return nomeCliente; }
    public String getNomeEntregador() { return nomeEntregador; }
    public String getDestino() { return destino; }
    public String getNomeEmpresa() { return nomeEmpresa; }
    public int getEntregador() { return entregador; }
    public int getId() { return id; }
    public int getPedido() { return pedido; }
    public List<Produtos> getProdutos() { return produtos; }

    //Setters
    public void setNomeCliente(String cliente) { this.nomeCliente = cliente; }
    public void setNomeEntregador(String entregador) { this.nomeEntregador = entregador; }
    public void setDestino(String destino) { this.destino = destino; }
    public void setNomeEmpresa(String empresa) { this.nomeEmpresa = empresa; }
    public void setEntregador(int entregador) { this.entregador = entregador; }
    public void setId(int id) { this.id = id; }
    public void setPedido(int pedido) { this.pedido = pedido; }
    public void setProdutos(List<Produtos> produtos) { this.produtos = produtos; }


}
