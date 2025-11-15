package myfood.models;

import myfood.Exception.AtributoInvalidoException;

import java.util.Locale;

public class Produtos {
    private static int contadorId = 0;

    private int id;
    private String nome;
    private float valor;
    private String categoria;

    public Produtos() { }

    public Produtos (String nome, float valor, String categoria) {
        this.id = contadorId++;
        this.nome = nome;
        this.valor = valor;
        this.categoria = categoria;
    }

    public String getAtributo(String nomeAtributo) {
        switch (nomeAtributo.toLowerCase()) {
            case "id":
                return String.valueOf(this.id);
            case "nome":
                return this.nome;
            case "valor":
                return String.format(Locale.US, "%.2f", this.valor);
            case "categoria":
                return this.categoria;
            default:
                throw new AtributoInvalidoException();
        }
    }

    // Método para ser usado pela Persistencia
    public static void setContadorId(int contadorId) { Produtos.contadorId = contadorId; }


    // Getters
    public int getId() { return id; }
    public String getNome() { return nome; }
    public float getValor() { return valor; }
    public String getCategoria() { return categoria; }

    // Métodos para o Persistencia (Setters)
    public void setId(int id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome; }
    public void setValor(float valor) { this.valor = valor; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
}