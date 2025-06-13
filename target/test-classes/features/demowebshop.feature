Feature: Funcionalidades do Demo Web Shop com dados externos

  @all @registration
  Scenario: Registro de usuario com dados do banco
    Given que o usuario esta na pagina de registro
    When ele realiza o cadastro com dados vindos do banco
    Then o sistema deve responder de acordo com o resultado esperado
    
  @all @login
  Scenario: Login com dados do banco
    Given que o usuario esta na pagina de login
    When ele realiza login com os dados do banco
    Then o sistema deve exibir o resultado esperado

  @all @search
  Scenario: Busca de produtos
    Given que o usuario esta na pagina inicial
    When ele realiza uma busca
    Then o sistema deve exibir os resultados correspondentes

  @all @cart
  Scenario: Adicionar produto ao carrinho com dados do banco
    Given que o usuario esta logado
    And esta na pagina de um produto vindo do banco
    When ele adiciona o produto ao carrinho
    Then o produto deve estar visivel no carrinho

  @all @remove
  Scenario: Remover produto do carrinho com dados do banco
 		Given que o usuario esta logado
    And que o usuario tem um produto no carrinho
    When ele acessa o carrinho e remove o produto
    Then o carrinho nao deve conter mais o item

  @all @compra @integracao @db
  Scenario: Finalizar compra com dados do banco
    Given que o usuario esta logado
    And possui produtos no carrinho cadastrados no banco
    When ele realiza o checkout com os dados do banco
    Then o sistema deve confirmar a compra corretamente

  @all @formulario @validacao @db
  Scenario: Enviar formulario de contato com dados do banco
    Given que o usuario esta na pagina de contato
    When ele preenche o formulario com os dados do banco
    And clica em "Enviar"
    Then o sistema deve apresentar a resposta esperada

  @all @recuperacao @validacao @db
  Scenario: Recuperar senha com e-mail vindo do banco
    Given que o usuario esta na pagina de login
    When ele solicita recuperacao de senha com e-mail vindo do banco
    Then o sistema deve responder de forma adequada

  @all @performance
  Scenario: Validar tempo de carregamento da pagina inicial
    Given que o usuario acessa a URL do site
    When a pagina inicial termina de carregar
    Then o tempo total deve ser menor que o limite definido
