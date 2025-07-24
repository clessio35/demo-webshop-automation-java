Feature: Testes de API - Automation Exercise

  @list-products @all @api
  Scenario: Listar todos os produtos
    Given que acesso a API "<url>"
    When realizo uma request GET para "<endpoint>"
    Then eu valido a resposta com a lista completa de produtos
    Examples:
      | url                            | endpoint          | statusCode |
      | https://automationexercise.com | /api/productsList |200					|

  @search-product @all @api
  Scenario: Pesquisar um produto e validar resultados contendo texto de busca
    Given que acesso a API "<url>"
    When realizo uma request POST para "<endpoint>" com o payload
    Then eu valido que todos os resultados contêm "<result>"
    Examples:
      | url                            | endpoint           |result |
      | https://automationexercise.com | /api/searchProduct |tshirt |

 @search-product-time @all @api
  Scenario: Pesquisar um produto e validar resultados contendo texto de busca
    Given que acesso a API "<url>"
    When realizo uma request POST para "<endpoint>" com o payload
    Then eu valido que o tempo de resposta é menor que '<timeResp>' segundos
    Examples:
      | url                            | endpoint           |timeResp  |
      | https://automationexercise.com | /api/searchProduct |2	  		 |