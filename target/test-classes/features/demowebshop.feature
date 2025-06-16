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

  @all @finalize
  Scenario: Finalizar compra com dados do banco
    Given que o usuario esta logado
    And que o usuario tem um produto no carrinho
    When ele realiza o checkout
    Then o sistema deve confirmar a compra corretamente

