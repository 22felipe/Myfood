Relatório de Arquitetura do Sistema MyFood

1. Descrição Geral do Design Arquitetural do Sistema

O sistema MyFood segue uma arquitetura modular orientada a objetos,
estruturada em camadas lógicas que separam responsabilidades e facilitam
a manutenção, evolução e testabilidade. A arquitetura é fortemente
guiada pelo uso de padrões de projeto, especialmente pela aplicação de
um Facade, que centraliza a comunicação entre a aplicação e o núcleo
lógico do sistema.

A camada principal é constituída pelas classes de domínio (usuários,
empresas/restaurantes, produtos, pedidos), controladas e orquestradas
pelo SistemaMyFood, que funciona como o “núcleo” da lógica da aplicação.
A classe Facade atua como ponto de entrada único, recebendo comandos
externos (como os do EasyAccept) e delegando para o sistema interno.

A interação entre esses módulos é definida de modo que cada entidade
seja responsável apenas pelo que faz sentido dentro do seu contexto,
mantendo o princípio da responsabilidade única.

------------------------------------------------------------------------

2. Principais Componentes e Suas Interações

2.1. Facade

-   Atua como interface externa do sistema.
-   Concentra e expõe os métodos necessários ao uso do sistema por
    clientes externos.
-   Encaminha as requisições para o SistemaMyFood.

2.2. SistemaMyFood

-   Contém a lógica central da aplicação.
-   Gerencia usuários, empresas, produtos e pedidos.
-   Armazena os dados em coleções internas.
-   Implementa regras de negócio: criação de empresas, validações,
    cadastros, fluxo de pedidos.

2.3. Usuários (Usuario, Cliente, DonoDeEmpresa)

-   Representam as pessoas que interagem com o sistema.
-   Cada subtipo possui responsabilidades específicas:
    -   Cliente faz pedidos.
    -   DonoDeEmpresa gerencia restaurantes e produtos.

2.4. Empresa (Restaurante)

-   Controla produtos e pedidos associados.
-   Armazena lista dos produtos e pedidos abertos/concluídos.

2.5. Produto

-   Entidade simples que compõe a lista dos produto.
-   Gerenciado por um DonoDeEmpresa.

2.6. Pedidos

-   Representam compras realizadas por clientes.
-   Mantidos dentro das empresas responsáveis pelos produtos.

2.7. Interações Resumidas

Facade -> SistemaMyFood -> Usuários/Empresas -> Produtos/Pedidos

-   A Facade não conhece detalhes das entidades.
-   O SistemaMyFood coordena toda lógica.
-   Entidades trabalham de forma coesa com foco no domínio.

------------------------------------------------------------------------

3. Padrões de Projeto Aplicados

A seguir, cada padrão identificado no projeto e sua justificativa.

------------------------------------------------------------------------

3.1. Padrão: Facade

Descrição Geral

O padrão Facade fornece uma interface simples e unificada para um
conjunto complexo de classes ou subsistemas. Ele atua como um “portão de
entrada” para funcionalidades internas, escondendo a complexidade e
oferecendo métodos diretos, fáceis de entender e de usar.

Problema Resolvido

Sem uma fachada, o código cliente precisaria conhecer diretamente todas
as classes internas (SistemaMyFood, Empresa, Usuario, Produto, Pedido
etc.). Isso criaria forte acoplamento e tornaria o sistema difícil de
testar, modificar ou evoluir. A Facade resolve:

-   excesso de dependências externas
-   exposição desnecessária da lógica interna
-   necessidade de controlar acesso ao núcleo do sistema

Identificação da Oportunidade

O projeto usa EasyAccept, que depende de uma classe única para executar
comandos de teste. Além disso, os requisitos pediam simplicidade na
interface pública do sistema. Isso tornou natural concentrar as chamadas
em uma única classe, isolando o restante do sistema dos comandos
externos.

Aplicação no Projeto

-   A classe Facade foi criada como a única interface usada pelos
    testes.
-   Ela instancia internamente o SistemaMyFood.
-   Cada método da Facade apenas delega para o SistemaMyFood,
    preservando a lógica no lugar correto.

Exemplo:

public String criarEmpresa(String id, String nome) { return
sistema.criarEmpresa(id, nome); }

------------------------------------------------------------------------

3.2. Padrão: Strategy (implícito no tratamento de usuários)

Descrição Geral

O padrão Strategy permite definir comportamentos intercambiáveis para
classes diferentes que compartilham uma interface comum. Cada subtipo
implementa o seu próprio comportamento.

Problema Resolvido

O sistema possui diferentes tipos de usuários (Cliente e DonoDeEmpresa).
Cada um possui responsabilidades específicas. Sem Strategy, seria
necessário usar condicionais espalhadas pelo código verificando tipos de
usuário — o que aumenta acoplamento e dificulta manutenção.

Identificação da Oportunidade

O design pediu que clientes pudessem realizar pedidos e donos
gerenciassem empresas. Esses comportamentos são distintos e variam
conforme o tipo de usuário.

Aplicação no Projeto

-   A classe Usuario é a base.
-   Cliente e DonoDeEmpresa estendem Usuario.
-   Cada subtipo implementa suas ações específicas.
-   Exemplo: apenas DonoDeEmpresa pode criar ou gerenciar empresas.

------------------------------------------------------------------------

3.3. Padrão: Repository / Gerenciador de Entidades (não formal, mas presente)

Descrição Geral

O padrão Repository centraliza o gerenciamento de entidades em coleções
internas, fornecendo operações de CRUD e busca de forma organizada.

Problema Resolvido

Evita espalhar manipulação de listas e buscas pelo sistema. Um único
módulo gerencia entidades:

-   criação
-   validação
-   remoção
-   consulta

Identificação da Oportunidade

O SistemaMyFood precisava gerenciar múltiplos elementos: usuários,
empresas, produtos e pedidos. Centralizar isso reduz duplicação e
melhora a coesão.

Aplicação no Projeto

-   O próprio SistemaMyFood atua como repositório geral.
-   Ele mantém mapas/listas de entidades.

------------------------------------------------------------------------

3.4. Padrão: Encapsulamento + Responsabilidade Única (SRP)

Descrição Geral

O princípio da responsabilidade única define que cada classe deve ter
apenas um motivo para mudar. É um pilar essencial de arquiteturas bem
projetadas.

Problema Resolvido

Evita classes gigantes cheias de responsabilidades misturadas.

Identificação da Oportunidade

As entidades do sistema possuem papéis claros: Empresa/restaurante cuida da
lista dos produtos, Pedido cuida do histórico do cliente, Cliente cuida dos
pedidos realizados.

Aplicação no Projeto

-   Cada classe tem responsabilidades definidas de forma clara.
-   Exemplo: Empresa trata de produtos e pedidos; Cliente não manipula a
    lista dos produtos.

------------------------------------------------------------------------

Conclusão

O sistema MyFood utiliza uma arquitetura modular e clara, baseada em
camadas e fortemente apoiada no padrão Facade, garantindo organização,
baixo acoplamento e facilidade na execução dos testes automáticos. Os
padrões e princípios aplicados tornam o sistema extensível, coeso e de
fácil manutenção.
