package myfood;


import myfood.models.Cliente;
import myfood.models.DonoDeEmpresa;
import myfood.service.SistemaMyFood;

public class Facade {

    private SistemaMyFood sistema = new SistemaMyFood();


    // ---------------------------- testes 1_1.txt -----------------------//

    //Limpa os dados do sistema
    public void zerarSistema() {
        sistema.zerarSistema();
    }

    // Cria cliente
    public void criarUsuario(String nome, String email, String senha, String endereco) {
        Cliente cliente = new Cliente(nome, email, senha, endereco);
        sistema.criarUsuario(cliente);
    }

    // Cria dono de restaurante
    public void criarUsuario(String nome, String email, String senha, String endereco, String cpf) {
        DonoDeEmpresa dono = new DonoDeEmpresa(nome, email, senha, endereco, cpf);
        sistema.criarUsuario(dono);
    }

    //Retorna o atributo descrito em "nome" do usuario com base em seu "id"
    public String getAtributoUsuario (int id, String nome){
        return sistema.getAtributoUsuario(id, nome);
    }

    //Válida se um usuário está devidamente cadastrado e se existe com os dados fornecidos
    public int login (String email, String senha){
        return sistema.login(email, senha);
    }

    //Finaliza a execução do programa
    public void encerrarSistema() {
        //limpar dados antes de sair
        sistema.zerarSistema();
    }
}
