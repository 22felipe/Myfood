package myfood.models;

public class DonoDeEmpresa extends Usuario{

    private String cpf;

    public DonoDeEmpresa(){}

    public DonoDeEmpresa(String nome, String email, String senha, String endereco, String cpf) {
        super(nome, email, senha, endereco);
        this.cpf=  cpf;
    }


    // Sobrescreve getAtributo para tratar atributos extras
    @Override
    public String getAtributo(String nomeAtributo) {
        if (nomeAtributo.equalsIgnoreCase("cpf")) {
            return cpf;
        }
        return super.getAtributo(nomeAtributo);
    }


    //Get e set
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

}
