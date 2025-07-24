Feature: Funcionalidades do site Automation Exercise

  @all @home @web
  Scenario: Acesso ao site e validação da home
    Given que o usuario acessa o site
    Then a pagina inicial deve ser carregada corretamente

  @all @registration @web
  Scenario: Registro de novo usuario com dados do banco
    Given que o usuario acessa o site
    And que o usuario esta na pagina de Signup
    When ele preenche os dados obrigatorios com valores do banco
    And confirma o cadastro
    Then o sistema deve exibir a mensagem "<msg>"
     Examples:
      | msg  					 |
      |Account Created!| 
    

  @all @login @web
  Scenario: Login com usuario previamente cadastrado
    Given que o usuario acessa o site
    And que o usuario esta na pagina de login
    When ele realiza login com os dados do banco
    Then o sistema deve exibir a mensagem de login com sucesso

  @all @cart @web
  Scenario: Adicao de produto ao carrinho
    Given que o usuario acessa o site
    And que o usuario esta logado
    When ele adiciona um produto ao carrinho pela pagina de produtos
    Then o carrinho deve conter o produto adicionado com o nome correto

  @all @remove @web
  Scenario: Remocao de produto do carrinho
    Given que o usuario acessa o site
    And que o usuario possui um produto no carrinho
    When ele remove o produto do carrinho
    Then o sistema deve exibir a mensagem de exclusao "<msg>"
     Examples:
      | msg  					 |
      |Cart is empty!| 

  @all @logout @web
  Scenario: Logout do usuario
    Given que o usuario acessa o site
    And que o usuario esta logado na aplicacao
    When ele realiza o logout
    Then o sistema deve redirecionar para a pagina de login ou home publica
