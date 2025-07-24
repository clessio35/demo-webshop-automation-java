Feature: Testes de API - Automation Exercise

  ### ---------------- Products List ---------------- ###

  @list-products @all @api
  Scenario: Listar todos os produtos
    Given que acesso a API "<url>"
    When realizo uma request GET para "<endpoint>"
    Then eu valido a resposta com a lista completa de produtos
    Examples:
      | url                            | endpoint          | statusCode |
      | https://automationexercise.com | /api/productsList |200					|

  ### ---------------- Search Product ---------------- ###

  @search-product @all @api
  Scenario: Pesquisar um produto e validar resultados contendo texto de busca
    Given que acesso a API "<url>"
    When realizo uma request POST para "<endpoint>" com o payload
    Then eu valido que todos os resultados contêm "<result>"
    And eu valido que o tempo de resposta é menor que '<time>' segundos
    Examples:
      | url                            | endpoint           |time |result |
      | https://automationexercise.com | /api/searchProduct |2		|Tshirt |

