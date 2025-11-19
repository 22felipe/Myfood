package myfood.models.empresas;

import myfood.Exception.AtributoInvalidoException;
import myfood.models.Produtos;

import java.util.ArrayList;
import java.util.List;

public abstract class Empresa {

    //ids sequenciais, unico para cada empresa
    private static int contadorId = 0;

    private List<Integer> entregadores = new ArrayList<>(); //entregadores que trabalham para essa empresa
    protected List<Produtos> produtos = new ArrayList<>(); //produtos cadastrados na empresa
    protected int id;
    protected String nome;
    protected String endereco;
    protected String tipoEmpresa;
    protected int donoId;


    //funcoes para trabalhar com os produtos da empresa
    public List<Produtos> getProdutos() {
        return produtos;
    }

    public void adicionarProduto(Produtos p) {
        produtos.add(p);
    }

    //funcoes para trabalhar com os entregadores da empresa
    public List<Integer> getEntregadores() {
        return entregadores;
    }

    public void setEntregadores(List<Integer> entregadores) {
        this.entregadores = entregadores;
    }

    public void adicionarEntregador(int entregadorId) {
        entregadores.add(entregadorId);
    }



    public Empresa() { }

    public Empresa(String nome, String endereco, String tipoEmpresa, int donoId) {
        this.id = contadorId++;
        this.nome = nome;
        this.endereco = endereco;
        this.tipoEmpresa = tipoEmpresa;
        this.donoId = donoId;
        this.produtos = new ArrayList<>();
    }


    // Metodo genérico para retornar atributos comuns
    public String getAtributo(String nomeAtributo) {
        switch (nomeAtributo.toLowerCase()) {
            case "id": return String.valueOf(id);
            case "nome": return nome;
            case "endereco": return endereco;
            case "tipoempresa": return tipoEmpresa;
            default: throw new AtributoInvalidoException();
        }
    }


    //Gets
    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getEndereco() { return endereco; }
    public String getTipoEmpresa() { return tipoEmpresa; }
    public int getDonoId() { return donoId; }


    //Sets
    public static void setContadorId(int contadorId) { Empresa.contadorId = contadorId;}
    public void setDonoId(int donoId) { this.donoId = donoId; }
    public void setEndereco(String endereco) { this.endereco = endereco;}
    public void setId(int id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome;}
    public void setTipoEmpresa(String tipoEmpresa) { this.tipoEmpresa = tipoEmpresa; }
    public void setProdutos(List<Produtos> produtos) { this.produtos = produtos; }

}

