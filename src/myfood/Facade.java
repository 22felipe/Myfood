package myfood;


import myfood.models.usuarios.Cliente;
import myfood.models.usuarios.DonoDeEmpresa;
import myfood.models.usuarios.Entregador;
import myfood.service.SistemaMyFood;

import java.util.List;

public class Facade {

    private SistemaMyFood sistema = new SistemaMyFood();


    // ---------------------------- testes 1_1.txt e 1_2 txt -----------------------//

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
        sistema.encerrarSistema();
    }

    // ---------------------------- testes 2_1.txt e 2_2 txt -----------------------//

    public int criarEmpresa(String tipoEmpresa, int dono, String nome, String endereco, String tipoCozinha){
        return sistema.criarEmpresa(tipoEmpresa, dono, nome, endereco, tipoCozinha);
    }

    public String  getEmpresasDoUsuario (int idDono){
        return sistema.getEmpresasDoUsuario(idDono);
    }

    public int getIdEmpresa (int idDono, String nome, int indice){
        return sistema.getIdEmpresa(idDono, nome, indice);
    }

    public String getAtributoEmpresa (int empresa, String atributo){
        return sistema.getAtributoEmpresa(empresa, atributo);
    }


    // ---------------------------- testes 3_1.txt e 3_2.txt -----------------------//

    public int criarProduto(int empresa, String nome, float valor, String categoria){
        return sistema.criarProduto(empresa, nome, valor, categoria);
    }

    public String getProduto(String  nome, int empresa, String atributo){
        return sistema.getProduto(nome, empresa, atributo);
    }

    public void editarProduto(int produto, String nome, float valor, String categoria){
        sistema.editarProduto(produto, nome, valor, categoria);
    }

    public String listarProdutos(int empresa){
        return sistema.listarProdutos(empresa);
    }

    // ---------------------------- testes 4_1.txt e 4_2.txt -----------------------//

    public int criarPedido (int cliente, int empresa){
        return sistema.criarPedido( cliente, empresa);
    }

    public void adicionarProduto(int numero, int produto){
        sistema.adicionarProduto(numero, produto);
    }

    public String getPedidos(int numero, String atributo) {
        return sistema.getPedidos (numero, atributo);
    }

    public void fecharPedido(int numero){
        sistema.fecharPedido(numero);
    }

    public void removerProduto(int  pedido, String produto){
        sistema.removerProduto(pedido, produto);
    }

    public int getNumeroPedido(int cliente, int empresa, int indice){
        return sistema.getNumeroPedido(cliente, empresa, indice);
    }


    // ---------------------------- testes 5_1.txt e 5_2.txt -----------------------//

    //criar uma empresa para o tipo "mercado"
    public int criarEmpresa(String tipoEmpresa, int dono, String nome, String endereco,
                            String abre, String fecha, String tipoMercado) {
        return sistema.criarEmpresa(tipoEmpresa, dono, nome, endereco, abre, fecha, tipoMercado);
    }

    //altera o horario de funcionamento de mercado
    public void alterarFuncionamento(int mercado, String abre, String fecha){
        sistema.alterarFuncionamento(mercado, abre, fecha);
    }

    // ---------------------------- testes 6_1.txt e 6_2.txt -----------------------//

    //criar empresa do tipo farmacia
    public int criarEmpresa(String tipoEmpresa, int dono, String nome, String endereco, Boolean aberto24Horas, int numeroFuncionarios){
        return sistema.criarEmpresa(tipoEmpresa,dono, nome, endereco, aberto24Horas, numeroFuncionarios);
    }


    // ---------------------------- testes 7_1.txt e 7_2.txt -----------------------//

    // Cria entregador
    public void criarUsuario(String nome, String email, String senha, String endereco, String veiculo, String placa) {
        Entregador entregador = new Entregador(nome, email, senha, endereco, veiculo, placa);
        sistema.criarUsuario(entregador);
    }

    //cadastra o entregador em uma empresa
    public void cadastrarEntregador(int empresa, int entregador){
        sistema.cadastrarEntregador(empresa, entregador);
    }

    //Retorna todos os emails dos entregadores que estão alocados em uma empresa.
    public String getEntregadores(int idEmpresa){
        return sistema.getEntregadores(idEmpresa);
    }

    //Retorna os nomes e endereços de todas as empresas a qual o entregador trabalha .
    public String getEmpresas(int idEntregador){
        return sistema.getEmpresas(idEntregador);
    }

    // ---------------------------- testes 8_1.txt e 8_2.txt -----------------------//

    //muda o estado do pedido para "pronto".
    public void liberarPedido(int numero){
        sistema.liberarPedido(numero);
    }

    //Retorna o pedido mais antigo que esteja pronto, e que pertenca a uma empresa a qual o entregador trabalha. Pedidos de Farmacia sempre tem prioridade.
    public int obterPedido(int entregador){
        return sistema.obterPedido( entregador);
    }

    //Cria um objeto de entrega, e muda o estado do pedido para "entregando".
    public int criarEntrega(int pedido, int entregador, String destino){
        return sistema.criarEntrega( pedido, entregador, destino);
    }

    //obtém os dados de uma entrega pelo id
    public String getEntrega(int id, String atributo){
        return sistema.getEntrega(id, atributo);
    }

    //retorna o id da entrega a qual pertece o pedido informado pelo id
    public int getIdEntrega(int pedido){
        return sistema.getIdEntrega( pedido);
    }

    //Muda o estado do pedido para entregue.
    public void entregar(int entrega){
        sistema.entregar( entrega);
    }



}
