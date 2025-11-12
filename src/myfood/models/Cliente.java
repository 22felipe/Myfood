package myfood.models;

public class Cliente extends Usuario{

    private String endereco;

    public Cliente(String nome, String email, String senha, String endereco) {
        super(nome, email, senha);
        this.endereco=  endereco;
    }

    public String getEndereco() {
        return endereco;
    }

    // Sobrescreve getAtributo para tratar atributos extras
    @Override
    public String getAtributo(String nomeAtributo) {
        if (nomeAtributo.equalsIgnoreCase("endereco")) {
            return endereco;
        }
        return super.getAtributo(nomeAtributo);
    }

}
