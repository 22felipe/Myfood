package myfood.models.empresas;

import myfood.Exception.AtributoInvalidoException;
import myfood.models.Produtos;

import java.util.ArrayList;
import java.util.List;

public class Restaurante extends Empresa {

    private String tipoCozinha;
    private List<Produtos> produtos; // Lista de Produtos

    public Restaurante() { }

    public Restaurante(String nome, String endereco, int donoId, String tipoCozinha) {
        super(nome, endereco, "restaurante", donoId);
        this.tipoCozinha = tipoCozinha;
        this.produtos = new ArrayList<>();
    }

    // Metodo para adicionar produto (usado em criarProduto)
    public void adicionarProduto(Produtos produto) {
        this.produtos.add(produto);
    }

    // Sobrescreve getAtributo para tratar atributos extras
    @Override
    public String getAtributo(String nomeAtributo) {
        if (nomeAtributo.equalsIgnoreCase("tipoCozinha")) {
            return tipoCozinha;
        }

        return super.getAtributo(nomeAtributo);
    }

    //Get
    public String getTipoCozinha() { return tipoCozinha; }
    public List<Produtos> getProdutos() { return produtos; }

    //Set
    public void setTipoCozinha(String tipoCozinha) { this.tipoCozinha = tipoCozinha; }
    public void setProdutos(List<Produtos> produtos) { this.produtos = produtos; }

}
