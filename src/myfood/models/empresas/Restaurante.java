package myfood.models.empresas;

public class Restaurante extends Empresa {

    private String tipoCozinha;

    public Restaurante() { }

    public Restaurante(String nome, String endereco, int donoId, String tipoCozinha) {
        super(nome, endereco, "restaurante", donoId);
        this.tipoCozinha = tipoCozinha;
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

    //Set
    public void setTipoCozinha(String tipoCozinha) { this.tipoCozinha = tipoCozinha; }

}
