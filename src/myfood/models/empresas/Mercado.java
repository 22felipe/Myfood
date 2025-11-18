package myfood.models.empresas;

import myfood.Exception.AtributoInvalidoException;

public class Mercado extends Empresa {

    private String abre;
    private String fecha;
    private String tipoMercado;

    public Mercado() { }

    public Mercado(String nome, String endereco, int donoId,
                   String abre, String fecha, String tipoMercado) {

        super(nome, endereco, "mercado", donoId);
        this.abre = abre;
        this.fecha = fecha;
        this.tipoMercado = tipoMercado;
    }

    @Override
    public String getAtributo(String nomeAtributo) {
        switch (nomeAtributo.toLowerCase()) {
            case "abre": return abre;
            case "fecha": return fecha;
            case "tipomercado": return tipoMercado;
        }
        return super.getAtributo(nomeAtributo);
    }

    // Getters
    public String getAbre() { return abre; }
    public String getFecha() { return fecha; }
    public String getTipoMercado() { return tipoMercado; }

    // Setters
    public void setAbre(String abre) { this.abre = abre; }
    public void setFecha(String fecha) { this.fecha = fecha; }
    public void setTipoMercado(String tipoMercado) { this.tipoMercado = tipoMercado; }

}
