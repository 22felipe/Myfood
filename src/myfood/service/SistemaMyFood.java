package myfood.service;

import myfood.Exception.*;
import myfood.models.DonoDeEmpresa;
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

    //cadastra um novo usuario
    public void criarUsuario(Usuario usuario) {

        // Validação de nome
        if (usuario.getNome() == null || usuario.getNome().trim().isEmpty()) {
            throw new NomeInvalidoException();
        }

        // Validação de email
        if (usuario.getEmail() == null || usuario.getEmail().trim().isEmpty() || !usuario.getEmail().contains("@ufal.com.br")) {
            throw new EmailInvalidoException();
        }

        // Validação de senha
        if (usuario.getSenha() == null || usuario.getSenha().trim().isEmpty()) {
            throw new SenhaIvalidoException();
        }

        // Se for DonoDeEmpresa, verifica o CPF e endereco
        if (usuario instanceof DonoDeEmpresa) {
            DonoDeEmpresa dono = (DonoDeEmpresa) usuario;

            // Validação de endereço
            if (dono.getEndereco() == null || dono.getEndereco().trim().isEmpty()) {
                throw new EnderecoInvalidoException();
            }

            //CPF deve ter 14 caracteres
            if (dono.getCpf() == null || dono.getCpf().length() != 14) {
                throw new CPFInvalidoException();
            }
        } else {

            if(usuario.getEndereco() == null || usuario.getEndereco().trim().isEmpty()){
                throw new EnderecoInvalidoException();
            }
        }

        // Validação de email
        if (usuario.getEmail() == null || usuario.getEmail().trim().isEmpty()) {
            throw new EmailInvalidoException();
        }

        // Checa se o email é único
        for (Usuario u : usuarios) {
            if (u.getEmail().equalsIgnoreCase(usuario.getEmail())) {
                throw new ContaComEmailJaExisteException();
            }
        }

        // Adiciona o usuário à lista
        usuarios.add(usuario);
    }

    //Retorna o atributo descrito em "nome" do usuario com base em seu "id"
    public String getAtributoUsuario (int id, String nome){

        for (Usuario u : usuarios){
            if(u.getId() == id){
                return u.getAtributo(nome);
            }
        }

        throw new UsuarioNaoEncontradoException();

    }

    //Válida se um usuário está devidamente cadastrado e se existe com os dados fornecidos
    public int login(String email, String senha) {

        for (Usuario u : usuarios) {
            if (u.getEmail().equalsIgnoreCase(email)) {
                if (u.getAtributo("senha").equals(senha)) {
                    return u.getId(); // Login válido
                }
            }
        }

        throw new LoginInvalidoException();

    }


}
