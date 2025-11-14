package myfood.service;

import myfood.Exception.*;
import myfood.models.Produtos;
import myfood.models.empresas.Empresa;
import myfood.models.empresas.Restaurante;
import myfood.models.usuarios.DonoDeEmpresa;
import myfood.models.usuarios.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SistemaMyFood {
    private List<Usuario> usuarios;
    private List<Empresa> empresas = new ArrayList<>();

    //Metodo para iniciar o sistema e carregar os dados de usuarios e empresas.
    public SistemaMyFood() {

        this.usuarios = Persistencia.carregarUsuarios();
        this.empresas = Persistencia.carregarEmpresas();

        // Reconectar empresas aos donos
        for (Usuario u : usuarios) {
            if (u instanceof DonoDeEmpresa dono) {

                for (Empresa e : empresas) {
                    if (e.getDonoId() == dono.getId()) {
                        dono.adicionarEmpresa(e);
                    }
                }
            }
        }
    }

    //Metodo para o encerrarSistema e salvar os dados
    public void encerrarSistema() {
        Persistencia.salvar(usuarios, empresas);
    }

    //apaga os dados dos usuario
    public void zerarSistema() {
        usuarios.clear();
        empresas.clear();
    }


    // ---------------------------- testes 1_1.txt e 1_2.txt -----------------------//


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

        //Salvar novo usuario
        Persistencia.salvar(usuarios, empresas);

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
            if (u.getEmail() != null && u.getEmail().equalsIgnoreCase(email)) {
                if (u.getAtributo("senha").equals(senha)) {
                    return u.getId(); // Login válido
                }
            }
        }

        throw new LoginInvalidoException();

    }


    // ---------------------------- testes 2_1.txt e 2_2.txt -----------------------//

    public int criarEmpresa(String tipoEmpresa, int donoId, String nome, String endereco, String tipoCozinha) {

        // 1 — Verificar se dono existe
        Usuario dono = null;
        for (Usuario u : usuarios) {
            if (u.getId() == donoId) {
                dono = u;
                break;
            }
        }
        if (dono == null) throw new UsuarioNaoEncontradoException();

        // 2 — Verificar se é DonoDeEmpresa
        if (!(dono instanceof DonoDeEmpresa)) {
            throw new UsuarioNaoPodeCriarEmpresaException();
        }

        // 3 — Regra: não pode existir MESMO NOME para donos diferentes
        for (Empresa e : empresas) {
            if (e.getNome().equalsIgnoreCase(nome)) {
                if (e.getDonoId() != donoId) {
                    throw new EmpresaComEsseNomeJaExisteException();
                }
            }
        }

        // 4 — Regra: mesmo dono não pode cadastrar mesmo nome + mesmo endereço
        for (Empresa e : empresas) {
            if (e.getDonoId() == donoId &&
                    e.getNome().equalsIgnoreCase(nome) &&
                    e.getEndereco().equalsIgnoreCase(endereco)) {

                throw new ProibidoCadastrarDuasEmpresasMesmoNomeLocalException();
            }
        }

        // 5 — Criar a empresa de acordo com o tipo
        Empresa nova = null;

        //Trabalhei somente com o tipo restaurante, como é pedido nos testes
        if (tipoEmpresa.equalsIgnoreCase("restaurante")) {
            nova = new Restaurante(nome, endereco, donoId, tipoCozinha);
        } else {
            throw new TipoEmpresaInvalidoException();
        }

        empresas.add(nova);

        ((DonoDeEmpresa) dono).adicionarEmpresa(nova);

        Persistencia.salvar(usuarios, empresas);

        return nova.getId();
    }

    //retorna todas as empresas de um usuario cujo o Id eh "idDono"
    public String getEmpresasDoUsuario(int idDono) {

        // Verifica se o usuário existe
        Usuario user = null;
        for (Usuario u : usuarios) {
            if (u.getId() == idDono) {
                user = u;
                break;
            }
        }

        if (user == null) {
            throw new UsuarioNaoEncontradoException();
        }

        // Verifica se é dono
        if (!(user instanceof DonoDeEmpresa)) {
            throw new UsuarioNaoPodeCriarEmpresaException();
        }

        DonoDeEmpresa dono = (DonoDeEmpresa) user;

        // Monta a saída usando SOMENTE as empresas do dono
        StringBuilder sb = new StringBuilder();
        sb.append("{[");

        boolean first = true;
        for (Empresa e : dono.getEmpresas()) {

            if (!first) {
                sb.append(", ");
            }
            first = false;

            sb.append("[")
                    .append(e.getNome())
                    .append(", ")
                    .append(e.getEndereco())
                    .append("]");
        }

        sb.append("]}");

        return sb.toString();
    }

    //retorna o id da empresa cujo o dono tem o id "idDono" e se chama "nome"
    public int getIdEmpresa(int idDono, String nome, int indice) {

        // Validações do nome
        if (nome == null || nome.trim().isEmpty()) {
            throw new NomeInvalidoException();
        }

        // Validações do índice
        if (indice < 0) {
            throw new IndiceInvalidoException();
        }

        //  busca o dono
        Usuario user = null;
        for (Usuario u : usuarios) {
            if (u.getId() == idDono) { // Compara o ID do usuário com o ID do dono fornecido
                user = u;
                break;
            }
        }

        if (user == null) {
            throw new UsuarioNaoEncontradoException();
        }

        if (!(user instanceof DonoDeEmpresa)) {
            throw new UsuarioNaoEoDonoException();
        }

        DonoDeEmpresa dono = (DonoDeEmpresa) user;

        // obtém todas as empresas do dono com o mesmo nome
        List<Empresa> empresasComNome = dono.getEmpresas().stream()
                .filter(e -> e.getNome().equals(nome))
                .collect(Collectors.toList());

        // se nenhuma empresa com o nome existir
        if (empresasComNome.isEmpty()) {
            throw new NaoExisteEmpresaComEsseNomeException();
        }

        // se o índice solicitado excede o total encontrado
        if (indice >= empresasComNome.size()) {
            throw new IndiceMaiorQueOEsperadoException();
        }

        // --- retorna o ID correto ---
        return empresasComNome.get(indice).getId();
    }

    //retorna o atributo "atributo" da empresa cujo o Id eh "empresaId
    public String getAtributoEmpresa (int empresaId, String atributo){


        //Checo se a empresa existe
        Empresa empresa = null;
        for (Empresa e : empresas) {
            if (e.getId() == empresaId) {
                empresa = e;
                break;
            }
        }

        if (empresa == null) {
            throw new EmpresaNaoCadastradaException();
        }

        //Checo se o atributo eh valido
        if (atributo == null || atributo.trim().isEmpty()) {
            throw new AtributoInvalidoException();
        }

        // TRATAMENTO ESPECIAL PARA O ATRIBUTO "DONO" (retornar o nome)
        if (atributo.equalsIgnoreCase("dono")) {

            // Buscar o nome do dono usando o id
            for (Usuario u : usuarios) {
                if (u.getId() == empresa.getDonoId()) {
                    return u.getAtributo("nome"); // Retorna o nome do Dono
                }
            }
            // Se o Dono não for encontrado
            throw new UsuarioNaoEncontradoException();
        }


        // Retorna o atributo usando a função da Empresa para os demais casos
        return empresa.getAtributo(atributo);
    }


    // ---------------------------- testes 3_1.txt e 3_2.txt -----------------------//

    //cria um produto para uma empresa
    public int criarProduto(int empresaId, String nome, float valor, String categoria){

        //checa se o nome é valido
        if (nome == null || nome.trim().isEmpty()) {
            throw new NomeInvalidoException();
        }

        //Checa se o valor do produto eh valido
        if (valor <= 0) {
            throw new ValorInvalidoException();
        }

        //checa se a categoria eh valido
        if (categoria == null || categoria.trim().isEmpty()) {
            throw new CategoriaInvalidoExpetion();
        }

        //checa se a empresa aonde o produto vai ser salva existe
        Empresa empresa = null;
        for (Empresa e : empresas) {
            if (e.getId() == empresaId) {
                empresa = e;
                break;
            }
        }

        if (empresa == null) {
            throw new EmpresaNaoCadastradaException();
        }

        // Verifica se a empresa é um Restaurante
        if (!(empresa instanceof Restaurante)) {
            throw new TipoEmpresaInvalidoException();
        }

        Restaurante restaurante = (Restaurante) empresa;

        //Verifica se ja existe um produto com esse nome nessa empresa
        for (Produtos p : restaurante.getProdutos()) {
            if (p.getNome().equalsIgnoreCase(nome)) {
                throw new ProdutoComEsseNomeJaExisteException();
            }
        }

        Produtos novoProduto = new Produtos(nome, valor, categoria);
        restaurante.adicionarProduto(novoProduto);

        //Salvar persistência (após adicionar o produto)
        Persistencia.salvar(usuarios, empresas);

        return novoProduto.getId();
    }

    //retorna o produto, apartir do seu nome, da empresa cujo o Id eh "empresaId"
    public String getProduto(String nome, int empresaId, String atributo) {

        //Checa se a empresa existe
        Empresa empresa = null;
        for (Empresa e : empresas) {
            if (e.getId() == empresaId) {
                empresa = e;
                break;
            }
        }

        if (empresa == null) {
            throw new EmpresaNaoCadastradaException();
        }

        // Verifica se a empresa é um Restaurante
        if (!(empresa instanceof Restaurante)) {
            throw new TipoEmpresaInvalidoException();
        }

        Restaurante restaurante = (Restaurante) empresa;

        // Busca o produto pelo nome
        Produtos produtoEncontrado = null;
        for (Produtos p : restaurante.getProdutos()) {
            if (p.getNome().equals(nome)) {
                produtoEncontrado = p;
                break;
            }
        }

        // Checa se o produto foi encontrado
        if (produtoEncontrado == null) {
            throw new ProdutoNaoEncontradoException();
        }

        // Retorna o atributo
        try {
            // TRATAMENTO ESPECIAL PARA O ATRIBUTO "EMPRESA"
            if (atributo.equalsIgnoreCase("empresa")) {
                return empresa.getNome();
            }

            // Retorna o atributo usando a função da classe Produtos para os demais casos
            return produtoEncontrado.getAtributo(atributo);

        } catch (AtributoInvalidoException e) {
            // Captura a exceção lançada pelo Produtos.getAtributo()
            throw new AtributoNaoExisteException();
        }
    }

    //edita os atributos de um produto cujo o o id eh "produtoId"
    public void editarProduto(int produtoId, String nome, float valor, String categoria){

        //checa se o nome é valido
        if (nome == null || nome.trim().isEmpty()) {
            throw new NomeInvalidoException();
        }

        //Checa se o valor do produto eh valido
        if (valor <= 0) {
            throw new ValorInvalidoException();
        }

        //checa se a categoria eh valido
        if (categoria == null || categoria.trim().isEmpty()) {
            throw new CategoriaInvalidoExpetion();
        }

        Produtos produtoParaEditar = null;
        Restaurante restauranteDoProduto = null;

        // Percorrer todas as empresas para encontrar o produto pelo ID
        for (Empresa empresa : empresas) {
            // O produto só pode estar em um Restaurante
            if (empresa instanceof Restaurante restaurante) {

                for (Produtos p : restaurante.getProdutos()) {
                    if (p.getId() == produtoId) {
                        produtoParaEditar = p;
                        restauranteDoProduto = restaurante; // Guardar o restaurante para futuras verificações
                        break;
                    }
                }
            }
            if (produtoParaEditar != null) {
                break; // Produto encontrado, sai do loop de empresas
            }
        }

        // Checagem de Existência
        if (produtoParaEditar == null) {
            throw new ProdutoNaoCadastradoException();
        }

        // Um produto não pode ter o mesmo nome de outro produto DENTRO DO MESMO RESTAURANTE.
        if (restauranteDoProduto != null) {
            for (Produtos p : restauranteDoProduto.getProdutos()) {
                if (p.getNome().equalsIgnoreCase(nome) && p.getId() != produtoId) {
                    throw new ProdutoComEsseNomeJaExisteException();
                }
            }
        }


        //Atualização dos Atributos
        produtoParaEditar.setNome(nome);
        produtoParaEditar.setValor(valor);
        produtoParaEditar.setCategoria(categoria);

        //Persistência
        Persistencia.salvar(usuarios, empresas);

    }

    //lista todos os produtos da empresa cujo o Id eh "empresaId
    public String listarProdutos(int empresaId){

        //Checa se a empresa existe
        Empresa empresa = null;
        for (Empresa e : empresas) {
            if (e.getId() == empresaId) {
                empresa = e;
                break;
            }
        }

        if (empresa == null) {
            throw new EmpresaNaoEncontradaException();
        }

        // Verifica se a empresa é um Restaurante
        if (!(empresa instanceof Restaurante)) {
            throw new TipoEmpresaInvalidoException();
        }

        Restaurante restaurante = (Restaurante) empresa;

        //Constrói a string com o nome dos produtos
        StringBuilder sb = new StringBuilder();
        sb.append("{[");

        boolean first = true;
        for (Produtos p : restaurante.getProdutos()) {
            if (!first) {
                sb.append(", ");
            }
            sb.append(p.getNome());
            first = false;
        }

        sb.append("]}");

        return sb.toString();
    }











}
