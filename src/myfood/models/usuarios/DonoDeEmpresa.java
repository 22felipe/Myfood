package myfood.models.usuarios;

import myfood.models.empresas.Empresa;

import java.util.ArrayList;
import java.util.List;

public class DonoDeEmpresa extends Usuario {

    private String cpf;
    private List<Empresa> empresas = new ArrayList<>();

    public DonoDeEmpresa(){}

    public DonoDeEmpresa(String nome, String email, String senha, String endereco, String cpf) {
        super(nome, email, senha, endereco);
        this.cpf=  cpf;
    }

    // Adiciona a empresa a lista de empresas desse Dono
    public void adicionarEmpresa(Empresa e) {
        empresas.add(e);
    }

    //Retornar as empresas desse Dono
    public List<Empresa> getEmpresas() {
        return empresas;
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
