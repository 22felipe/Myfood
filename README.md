**1\. Descrição Geral do Design Arquitetural do Sistema**
---------------------------------------------------------

O sistema MyFood segue uma arquitetura modular orientada a objetos, estruturada em camadas lógicas que separam responsabilidades e facilitam a manutenção, evolução e testabilidade. A arquitetura é fortemente guiada pelo uso de padrões de projeto, especialmente pela aplicação de um **Facade**, que centraliza a comunicação entre a aplicação e o núcleo lógico do sistema.

A camada principal é constituída pelas classes de domínio (usuários, empresas/restaurantes, produtos, pedidos), controladas e orquestradas pelo **SistemaMyFood**, que funciona como o “núcleo” da lógica da aplicação. A classe **Facade** atua como ponto de entrada único , recebendo comandos externos (como os do EasyAccept) e delegando para o sistema interno.

A interação entre esses módulos é definida de modo que cada entidade seja responsável apenas pelo que faz sentido dentro do seu contexto, mantendo o **princípio da responsabilidade única**.

**2\. Principais Componentes e Suas Interações**
------------------------------------------------

### **2.1. Facade**

*   Atua como **interface externa** do sistema.

*   Concentra e expõe os métodos necessários ao uso do sistema por clientes externos.

*   Encaminha as requisições para o **SistemaMyFood**.


### **2.2. SistemaMyFood**

*   Contém a **lógica central** da aplicação.

*   Gerencia usuários, empresas, produtos e pedidos.

*   Armazena os dados em coleções internas.

*   Implementa regras de negócio: criação de empresas, validações, cadastros, fluxo de pedidos.


### **2.3. Usuários (Usuario, Cliente, DonoDeEmpresa, Entregador)**

Representam todas as pessoas cadastradas dentro do sistema.O MyFood possui três tipos principais de usuários:

*   **Cliente**

*   **Dono de Empresa**

*   **Entregador**


Todos herdam da classe abstrata Usuario.

### **Atributos comuns:**

Cada usuário possui:

*   **id** – gerado automaticamente por um contador sequencial

*   **nome**

*   **email** – obrigatório e único no sistema

*   **senha**

*   **endereco**


Os atributos podem ser obtidos dinamicamente via getAtributo(). O ID pode ser restaurado pela camada de persistência.

**2.3.1. Cliente**
==================

Usuário padrão do sistema, representando compradores que realizam pedidos.

### **Características:**

*   Herda todos os atributos da classe Usuario.

*   Não possui atributos adicionais.

*   É cadastrado pelo comando de criação de usuários.

*   Pode realizar pedidos para qualquer empresa registrada no sistema.

*   É identificado pelo ID e tem seu nome armazenado dentro dos pedidos criados.


**2.3.2. DonoDeEmpresa**
========================

Representa o responsável pela criação e administração de empresas no sistema.

### **Atributos adicionais**

Além dos campos herdados de Usuario, possui:

*   **cpf** – documento do proprietário

*   **lista de empresas (List)** – empresas criadas por ele


### **Comportamento**

*   Pode criar empresas de qualquer tipo: Restaurante, Mercado ou Farmácia.

*   Cada empresa criada é automaticamente adicionada à sua lista através do método adicionarEmpresa().

*   Os pedidos **não** ficam na lista do dono; apenas as empresas.


### **Uso no sistema**

*   É validado ao criar novas empresas.

*   Pode consultar atributos dinâmicos incluindo “cpf”.


**2.3.3. Entregador**
=====================

Usuário responsável pelas entregas associadas a pedidos.

### **Atributos adicionais**

Além dos campos de Usuario, possui:

*   **veiculo** – tipo do veículo usado (moto, bicicleta, carro etc.)

*   **placa** – identificação do veículo

*   **lista de empresas (List)** – histórico de empresas onde já trabalhou


### **Comportamento**

*   Pode ser associado a pedidos em fase _entregando_.

*   Armazena apenas **IDs de empresas**, não objetos Empresa.

*   Registra histórico através do método adicionarEmpresa().


### **Uso no sistema**

*   Seus atributos extras podem ser consultados via getAtributo:

    *   "veiculo"

    *   "placa"


**2.3.4. Relacionamento geral entre usuários**
==============================================

*   Todos os usuários estão armazenados em uma **lista global** dentro de SistemaMyFood.

*   IDs são únicos e sequenciais.

*   A persistência pode reescrever IDs e restaurar o contador global.

*   Cada tipo de usuário possui papel distinto dentro do fluxo do sistema:

    *   **Cliente** → cria pedidos

    *   **DonoDeEmpresa** → administra empresas

    *   **Entregador** → realiza entregas associadas a pedidos


### **2.4. Empresa (Restaurante, Mercado, Farmacia)**

Representam estabelecimentos cadastrados pelos donos dentro do SistemaMyFood. Cada empresa pertence a um único dono e é persistida na lista global mantida pelo sistema.

Todas as empresas compartilham os atributos básicos definidos na classe **Empresa**, enquanto os tipos específicos (Farmácia, Mercado e Restaurante) adicionam seus próprios campos particulares.

### **2.4.1. Atributos comuns (classe Empresa)**

Cada empresa guarda:

*   **id** – identificador único, gerado automaticamente

*   **nome** – nome comercial da empresa

*   **endereco** – endereço físico cadastrado

*   **tipoEmpresa** – tipo geral ("farmacia", "mercado", "restaurante")

*   **donoId** – ID do usuário dono da empresa

*   **produtos** – lista de produtos que a empresa oferece

*   **entregadores** – IDs dos entregadores associados a essa empresa


As empresas não armazenam objetos completos de produtos ou entregadores em outras estruturas do sistema — apenas listas próprias contendo:

*   objetos Produtos

*   IDs de entregadores


Essas listas pertencem exclusivamente à empresa e permitem gerenciar seu catálogo e sua equipe.

**2.4.2. Farmácia**
-------------------

Tipo específico de empresa que adiciona informações relacionadas à operação farmacêutica.

A Farmácia guarda além dos atributos comuns:

*   **aberto24Horas** – indica se a farmácia funciona 24h

*   **numeroFuncionarios** – quantidade de funcionários cadastrados


A farmácia utiliza o mecanismo genérico da classe Empresa, mas adiciona esses campos obrigatórios no momento da criação.

**2.4.3. Mercado**
------------------

Representa um estabelecimento do tipo mercado/supermercado. Além dos atributos herdados, armazena:

*   **abre** – horário de abertura

*   **fecha** – horário de encerramento

*   **tipoMercado** – o tipo de mercado (por exemplo, “atacado”, “varejo”, etc.)


O mercado define automaticamente seu tipoEmpresa como "mercado".

**2.4.4. Restaurante**
----------------------

Empresas destinadas a preparo e venda de refeições. Possui o atributo adicional:

*   **tipoCozinha** – categoria da culinária do restaurante (ex.: italiana, japonesa, caseira)


Assim como acontece com os mercados, o restaurante define seu tipoEmpresa automaticamente como "restaurante".

### **2.5. Produtos**

Representam os itens comercializáveis oferecidos pelas empresas dentro do sistema. Cada produto pertence exclusivamente a uma empresa (armazenado na lista produtos da própria empresa), mas o SistemaMyFood mantém apenas referências indiretas ao produto quando necessário — nunca uma lista global.

Cada produto guarda:

*   **id** – identificador único, gerado automaticamente

*   **nome** – nome comercial do produto

*   **valor** – preço unitário, armazenado como float e formatado com duas casas decimais

*   **categoria** – classificação do produto, usada para filtragem e organização dentro da empresa (ex.: alimentos, bebidas, higiene, remédios etc.)


O ID é atribuído automaticamente por meio de um contador interno (contadorId) e pode ser restaurado pelo módulo de persistência.

### **Uso no sistema**

*   Cada empresa possui sua própria lista de produtos cadastrados.

*   Pedidos armazenam **referências** para produtos selecionados, permitindo calcular o valor total.

*   Atributos podem ser consultados dinamicamente pelo método getAtributo, que retorna valores em formato de texto padronizado.


### **Estados e regras importantes**

*   Um produto não tem estados como “ativo” ou “inativo”: se a empresa não quiser mais oferecê-lo, basta removê-lo da sua lista.

*   O preço é sempre formatado no padrão: 0.00 (Locale.US)


### **Persistência**

A classe oferece setters específicos utilizados pelo módulo de persistência, incluindo:

*   redefinição do ID

*   restauração do contador de IDs globais

*   alteração dos atributos básicos


### **2.6. Pedidos**

Representam as compras realizadas pelos clientes dentro do sistema. São controlados diretamente pelo **SistemaMyFood**, que mantém uma lista global contendo todos os pedidos registrados.

Cada pedido guarda:

*   **cliente** (nome e ID)

*   **empresa** (nome e ID)

*   **lista de produtos** adquiridos

*   **valor total** acumulado conforme os produtos são adicionados

*   **estado atual**, que pode ser:

    *   aberto – criado, mas ainda não enviado para entrega

    *   entregando – já associado a um entregador

    *   entregue – finalizado


Apesar de cada pedido registrar a empresa relacionada, **os pedidos não são armazenados dentro das empresas**, mas sim apenas referenciam a empresa responsável através do seu ID e nome.

### **2.7. Entregas**

Representam o processo de envio físico dos pedidos feitos no sistema. São controladas diretamente pelo **SistemaMyFood**, que mantém uma lista global contendo todas as entregas registradas.

Cada entrega guarda:

*   **cliente** (nome)

*   **empresa** responsável (nome)

*   **pedido** associado (ID do pedido)

*   **entregador** responsável (nome e ID)

*   **destino** informado pelo cliente

*   **lista de produtos enviados**


Além disso, cada entrega possui um **ID único**, gerado automaticamente através de um contador interno.

A entrega não armazena objetos completos de cliente, empresa ou entregador; ela guarda apenas **nomes e IDs**, funcionando como um vínculo entre diferentes partes do sistema.

A lista de produtos contém os itens efetivamente enviados na entrega, permitindo a conferência do que está sendo transportado.

As entregas também não pertencem às empresas ou aos entregadores diretamente. Em vez disso, o sistema armazena todas as entregas em uma lista centralizada, e cada entrega **referencia**:

*   o pedido relacionado

*   o entregador responsável

*   a empresa envolvida


Isso garante desacoplamento entre entidades e facilita consultas globais no sistema.

### **2.8. Interações Resumidas**

**Facade** -> **SistemaMyFood** -> **Usuários/Empresas** -> **Produtos/Pedidos** -> **Entregas**

*   A Facade não conhece detalhes das entidades.

*   O SistemaMyFood coordena toda lógica.

*   Entidades trabalham de forma coesa com foco no domínio.


**3\. Padrões de Projeto Aplicados**
------------------------------------

Padrões utilizados no projeto e sua justificativa.

### **3.1. Padrão: Facade**

#### **Descrição Geral**

O padrão Facade fornece uma interface simples e unificada para um conjunto complexo de classes ou subsistemas. Ele atua como um “portão de entrada” para funcionalidades internas, escondendo a complexidade e oferecendo métodos diretos, fáceis de entender e de usar.

#### **Problema Resolvido**

Sem uma fachada, o código cliente precisaria conhecer diretamente todas as classes internas (SistemaMyFood, Empresa, Usuario, Produto, Pedido etc.). Isso criaria forte acoplamento e tornaria o sistema difícil de testar, modificar ou evoluir. A Facade resolve:

*   excesso de dependências externas

*   exposição desnecessária da lógica interna

*   necessidade de controlar acesso ao núcleo do sistema


#### **Identificação da Oportunidade**

O projeto usa EasyAccept, que depende de uma classe única para executar comandos de teste. Além disso, os requisitos pediam simplicidade na interface pública do sistema. Isso tornou natural concentrar as chamadas em uma única classe, isolando o restante do sistema dos comandos externos.

#### **Aplicação no Projeto**

*   A classe **Facade** foi criada como a única interface usada pelos testes.

*   Ela instancia internamente o SistemaMyFood.

*   Cada método da Facade apenas delega para o SistemaMyFood, preservando a lógica no lugar correto.


_Exemplo:_ public String criarEmpresa(String id, String nome) { return sistema.criarEmpresa(id, nome); }

### **3.2. Padrão: Strategy (implícito no tratamento de usuários)**

#### **Descrição Geral**

O padrão Strategy permite definir comportamentos intercambiáveis para classes diferentes que compartilham uma interface comum. Cada subtipo implementa o seu próprio comportamento.

#### **Problema Resolvido**

O sistema possui diferentes tipos de usuários (Cliente e DonoDeEmpresa). Cada um possui responsabilidades específicas. Sem Strategy, seria necessário usar condicionais espalhadas pelo código verificando tipos de usuário — o que aumenta acoplamento e dificulta manutenção.

#### **Identificação da Oportunidade**

O projeto pedia que clientes pudessem realizar pedidos e donos gerenciassem empresas. Esses comportamentos são distintos e variam conforme o tipo de usuário.

#### **Aplicação no Projeto**

*   A classe **Usuario** é a base.

*   **Cliente**, **DonoDeEmpresa** **e Entregador** estendem Usuario.

*   Cada subtipo implementa suas ações específicas.

*   Exemplo: apenas DonoDeEmpresa pode criar ou gerenciar empresas.


### **3.3. Padrão: Repository / Gerenciador de Entidades (não formal, mas presente)**

#### **Descrição Geral**

O padrão Repository centraliza o gerenciamento de entidades em coleções internas, fornecendo operações de CRUD e busca de forma organizada.

#### **Problema Resolvido**

Evita espalhar manipulação de listas e buscas pelo sistema. Um único módulo gerencia entidades:

*   criação

*   validação

*   remoção

*   consulta


#### **Identificação da Oportunidade**

O SistemaMyFood precisava gerenciar múltiplos elementos: usuários, empresas, produtos, pedidos e entregas. Centralizar isso reduz duplicação e melhora a coesão.

#### **Aplicação no Projeto**

*   O próprio **SistemaMyFood** atua como repositório geral.

*   Ele mantém mapas/listas de entidades.


### **3.4. Padrão: Encapsulamento + Responsabilidade Única (SRP)**

#### **Descrição Geral:** O princípio da responsabilidade única define que cada classe deve ter apenas um motivo para mudar. É um pilar essencial de arquiteturas bem projetadas.

#### **Problema Resolvido:** Evita classes gigantes cheias de responsabilidades misturadas.

#### **Identificação da Oportunidade:** As entidades do sistema possuem papéis claros: Empresa/restaurante cuida da lista dos produtos, Pedido cuida do histórico do cliente, Cliente cuida dos pedidos realizados.

#### **Aplicação no Projeto**

*   Cada classe tem responsabilidades definidas de forma clara.

*   Exemplo: Empresa trata de produtos e pedidos; Cliente não manipula a lista dos produtos.


**Conclusão**
-------------

O sistema MyFood utiliza uma **arquitetura modular e clara**, baseada em camadas e fortemente apoiada no padrão **Facade**, garantindo organização, baixo acoplamento e facilidade na execução dos testes automáticos. Os padrões e princípios aplicados tornam o sistema extensível, coeso e de fácil manutenção.