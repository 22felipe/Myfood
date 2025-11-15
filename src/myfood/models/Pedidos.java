package myfood.models;

import java.util.ArrayList;
import java.util.List;

public class Pedidos {
    private static int contadorId = 0;

    private int id;
    private String cliente; // Nome do cliente
    private String empresa; // Nome da empresa
    private int clienteId; // ID do Cliente
    private int empresaId; // ID da Empresa
    private String estado;
    private List<Produtos> produtos;
    private float valorTotal;

    public Pedidos() {}

    public Pedidos(String cliente, String empresa, int clienteId, int empresaId) {
        this.cliente = cliente;
        this.empresa = empresa;
        this.clienteId = clienteId;
        this.empresaId = empresaId;
        this.estado = "aberto";
        this.id = contadorId++;
        this.produtos = new ArrayList<>();
    }

    public void adicionarProduto(Produtos produto) {
        // Certifique-se de que a lista de produtos foi inicializada no construtor
        if (this.produtos == null) {
            this.produtos = new ArrayList<>();
        }
        this.produtos.add(produto);
        // Atualiza o valor total
        this.valorTotal += produto.getValor();
    }

    //Getters
    public String getCliente() { return cliente; }
    public String getEmpresa() { return empresa; }
    public String getEstado() { return estado; }
    public int getId() { return id; }
    public List<Produtos> getProdutos() { return produtos; }
    public float getValorTotal() { return valorTotal; }
    public int getClienteId() { return clienteId; }
    public int getEmpresaId() { return empresaId; }

    //Setters
    public void setCliente(String cliente) { this.cliente = cliente; }
    public void setEmpresa(String empresa) { this.empresa = empresa; }
    public void setEstado(String estado) { this.estado = estado; }
    public void setId(int id) { this.id = id; }
    public void setProdutos(List<Produtos> produtos) { this.produtos = produtos; }
    public void setValorTotal(float valorTotal) { this.valorTotal = valorTotal; }
    public void setClienteId(int clienteId) { this.clienteId = clienteId; }
    public void setEmpresaId(int empresaId) { this.empresaId = empresaId; }



}
