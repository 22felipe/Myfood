package myfood.models.empresas;

public class Farmacia extends Empresa{

    private boolean aberto24Horas;
    private int numeroFuncionarios;

    public Farmacia() { }

    public Farmacia(String nome, String endereco, String tipoEmpresa, int donoId, boolean aberto24Horas, int numeroFuncionarios) {
        super(nome, endereco, tipoEmpresa, donoId);
        this.aberto24Horas = aberto24Horas;
        this.numeroFuncionarios = numeroFuncionarios;
    }


    public String getAtributo(String nomeAtributo) {
        switch (nomeAtributo.toLowerCase()) {
            case "aberto24horas": return String.valueOf(aberto24Horas);
            case "numerofuncionarios": return String.valueOf(numeroFuncionarios);
        }
        return super.getAtributo(nomeAtributo);
    }

    //Getters
    public boolean isAberto24Horas() { return aberto24Horas; }
    public int getNumeroFuncionarios() { return numeroFuncionarios; }

    //Setters
    public void setAberto24Horas(boolean aberto24Horas) { this.aberto24Horas = aberto24Horas; }
    public void setNumeroFuncionarios(int numeroFuncionarios) { this.numeroFuncionarios = numeroFuncionarios;}



}
