package myfood.models;

public class DonoDeEmpresa extends Usuario{

    private String endereco;
    private String cpf;


    public DonoDeEmpresa(String nome, String email, String senha, String endereco, String cpf) {
        super(nome, email, senha);
        this.endereco = endereco;
        this.cpf=  cpf;
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
        if (nomeAtributo.equalsIgnoreCase("cpf")) {
            return cpf;
        }
        return super.getAtributo(nomeAtributo);
    }

}
