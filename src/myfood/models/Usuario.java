package myfood.models;

import myfood.Exception.AtributoInvalidoException;

public abstract class Usuario {

    private static int contadorId = 1;

    protected int id;
    protected String nome;
    protected String email; //email deve ser unico
    protected String senha;
    protected String endereco;

    public Usuario(String nome, String email, String senha, String endereco) {
        this.id = contadorId++;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.endereco = endereco;
    }

    // Metodo genérico para retornar atributos comuns
    public String getAtributo(String nomeAtributo) {
        switch (nomeAtributo.toLowerCase()) {
            case "id": return String.valueOf(id);
            case "nome": return nome;
            case "email": return email;
            case "senha": return senha;
            case "endereco": return endereco;
            default: throw new AtributoInvalidoException(nomeAtributo);
        }
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getSenha() { return  senha; }
    public String getEndereco() { return  endereco; }
}
