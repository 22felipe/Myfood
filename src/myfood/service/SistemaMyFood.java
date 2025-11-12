package myfood.service;

import myfood.Exception.ContaComEmailJaExisteException;
import myfood.Exception.SenhaIncorretaException;
import myfood.Exception.UsuarioNaoEncontradoException;
import myfood.models.Usuario;
import java.util.ArrayList;
import java.util.List;

public class SistemaMyFood {
    private List<Usuario> usuarios = new ArrayList<>();

    // ---------------------------- testes 1_1.txt -----------------------//

    //apaga os dados dos usuario
    public void zerarSistema() {
        usuarios.clear();
    }

    //cadastra um novo usuario, checa se o email passado eh unico
    public void criarUsuario(Usuario usuario) {
        for (Usuario u : usuarios) {
            if (u.getEmail().equalsIgnoreCase(usuario.getEmail())) {
                throw new ContaComEmailJaExisteException();
            }
        }
        usuarios.add(usuario);
    }

    //Retorna o atributo descrito em "nome" do usuario com base em seu "id"
    public String getAtributoUsuario (int id, String nome){
        Usuario e = usuarios.get(id);

        return e.getAtributo(nome);

    }

    //Válida se um usuário está devidamente cadastrado e se existe com os dados fornecidos
    public int login(String email, String senha)
            throws UsuarioNaoEncontradoException, SenhaIncorretaException {

        for (Usuario u : usuarios) {
            if (u.getEmail().equalsIgnoreCase(email)) {
                if (u.getAtributo("senha").equals(senha)) {
                    return u.getId(); // Login válido
                } else {
                    throw new SenhaIncorretaException(email);
                }
            }
        }
        throw new UsuarioNaoEncontradoException(email);
    }


}
