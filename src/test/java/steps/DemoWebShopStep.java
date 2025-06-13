package steps;

import org.openqa.selenium.WebDriver;

import config.DriverManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.DemoWebShopPage;

public class DemoWebShopStep {
	
	private WebDriver driver;
    private DemoWebShopPage demo;

    public DemoWebShopStep() {
        this.driver = DriverManager.getDriver();
        this.demo = new DemoWebShopPage(driver);
    }

	@Given("que o usuario esta na pagina de registro")
	public void que_o_usuario_esta_na_pagina_de_registro() {
	    demo.accessRegisterPage();
	}

	@When("ele realiza o cadastro com dados vindos do banco")
	public void ele_realiza_o_cadastro_com_dados_vindos_do_banco() {
		demo.fillRegisterUser();
	}

	@Then("o sistema deve responder de acordo com o resultado esperado")
	public void o_sistema_deve_responder_de_acordo_com_o_resultado_esperado() {
	    demo.validateUserRegistration();
	}
	
	@Given("que o usuario esta na pagina de login")
	public void que_o_usuario_esta_na_pagina_de_login() {
		demo.accessRegisterPage();
		demo.fillRegisterUser();
	    demo.accessLoginPage();
	}

	@When("ele realiza login com os dados do banco")
	public void ele_realiza_login_com_os_dados_do_banco() {
	    demo.realizeLogin();
	}

	@Then("o sistema deve exibir o resultado esperado")
	public void o_sistema_deve_exibir_o_resultado_esperado() {
	    demo.validateLogin();
	}


	@Given("que o usuario esta na pagina inicial")
	public void que_o_usuario_esta_na_pagina_inicial() {
		demo.accessRegisterPage();
		demo.fillRegisterUser();
	    demo.accessLoginPage();
	    demo.realizeLogin();
	}

	@When("ele realiza uma busca")
	public void ele_realiza_uma_busca_com_o_termo_vindo_do_banco() {
	    demo.searchItems();
	}

	@Then("o sistema deve exibir os resultados correspondentes")
	public void o_sistema_deve_exibir_os_resultados_correspondentes() {
	    demo.validateResultsBySearch();
	}

	@Given("que o usuario esta logado")
	public void que_o_usuario_esta_logado() {
		demo.accessRegisterPage();
		demo.fillRegisterUser();
	    demo.accessLoginPage();
	    demo.realizeLogin();
	}

	@Given("esta na pagina de um produto vindo do banco")
	public void esta_na_pagina_de_um_produto_vindo_do_banco() {
		demo.searchItems();
	}

	@When("ele adiciona o produto ao carrinho")
	public void ele_adiciona_o_produto_ao_carrinho() {
	    demo.addToCart();
	}

	@Then("o produto deve estar visivel no carrinho")
	public void o_produto_deve_estar_visivel_no_carrinho() {
		demo.validateCart();
	}

	@Given("que o usuario tem um produto no carrinho")
	public void que_o_usuario_tem_um_produto_no_carrinho_vindo_do_banco() {
		demo.searchItems();
		demo.addToCart();
	    demo.accessCart();
	}

	@When("ele acessa o carrinho e remove o produto")
	public void ele_acessa_o_carrinho_e_remove_o_produto() {
	    demo.removeProduct();
	}

	@Then("o carrinho nao deve conter mais o item")
	public void o_carrinho_nao_deve_conter_mais_o_item() {
	    demo.validateEmptyCart();
	}

	@Given("possui produtos no carrinho cadastrados no banco")
	public void possui_produtos_no_carrinho_cadastrados_no_banco() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("ele realiza o checkout com os dados do banco")
	public void ele_realiza_o_checkout_com_os_dados_do_banco() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("o sistema deve confirmar a compra corretamente")
	public void o_sistema_deve_confirmar_a_compra_corretamente() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Given("que o usuario esta na pagina de contato")
	public void que_o_usuario_esta_na_pagina_de_contato() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("ele preenche o formulario com os dados do banco")
	public void ele_preenche_o_formulario_com_os_dados_do_banco() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("clica em {string}")
	public void clica_em(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("o sistema deve apresentar a resposta esperada")
	public void o_sistema_deve_apresentar_a_resposta_esperada() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("ele solicita recuperacao de senha com e-mail vindo do banco")
	public void ele_solicita_recuperacao_de_senha_com_e_mail_vindo_do_banco() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("o sistema deve responder de forma adequada")
	public void o_sistema_deve_responder_de_forma_adequada() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Given("que o usuario acessa a URL do site")
	public void que_o_usuario_acessa_a_url_do_site() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("a pagina inicial termina de carregar")
	public void a_pagina_inicial_termina_de_carregar() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("o tempo total deve ser menor que o limite definido")
	public void o_tempo_total_deve_ser_menor_que_o_limite_definido() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

}
