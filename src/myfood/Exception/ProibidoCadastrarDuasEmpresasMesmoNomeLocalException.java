package myfood.Exception;

public class ProibidoCadastrarDuasEmpresasMesmoNomeLocalException extends RuntimeException {
    public ProibidoCadastrarDuasEmpresasMesmoNomeLocalException() {
        super("Proibido cadastrar duas empresas com o mesmo nome e local");
    }
}
