package myfood.models.usuarios;

import java.util.ArrayList;
import java.util.List;

public class Entregador extends Usuario{

    private List<Integer> empresas = new ArrayList<>(); //lista de empresas que o entregador trabalhou
    private String veiculo;
    private String placa;

    public Entregador() { }

    public Entregador(String nome, String email, String senha, String endereco, String veiculo, String placa) {
        super(nome, email, senha, endereco);
        this.placa = placa;
        this.veiculo = veiculo;
    }

    public String getAtributo(String nomeAtributo) {
        switch (nomeAtributo.toLowerCase()) {
            case "placa": return placa;
            case "veiculo": return veiculo;
        }
        return super.getAtributo(nomeAtributo);
    }


    //funcoes para trabalhar com a lista de empresas
    public List<Integer> getEmpresas() {
        return empresas;
    }

    public void adicionarEmpresa(int empresaId) {
        empresas.add(empresaId);
    }

    //Getters
    public String getPlaca() { return placa; }
    public String getVeiculo() { return veiculo; }

    //Setters
    public void setPlaca(String placa) { this.placa = placa; }
    public void setVeiculo(String veiculo) { this.veiculo = veiculo; }




}
