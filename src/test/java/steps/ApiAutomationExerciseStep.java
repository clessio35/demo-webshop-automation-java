package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.ApiAutomationExerciseService;

public class ApiAutomationExerciseStep {
	
	ApiAutomationExerciseService api = new ApiAutomationExerciseService();
	
	@Given("que acesso a API {string}")
	public void que_acesso_a_api(String string) {
	    api.accessApi(string);
	}

	@When("realizo uma request GET para {string}")
	public void realizo_uma_request_get_para(String endpoint) {
	    api.sendRequestGETMethod(endpoint);
	}

	@Then("eu valido a resposta com a lista completa de produtos")
	public void eu_valido_a_resposta_com_a_lista_completa_de_produtos() {
	    api.validateCompleteProductsList();
	}


	@When("realizo uma request POST para {string} com o payload")
	public void realizo_uma_request_post_para_com_o_payload(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("eu valido que todos os resultados contêm {string}")
	public void eu_valido_que_todos_os_resultados_contêm(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("eu valido que o tempo de resposta é menor que {string} segundos")
	public void eu_valido_que_o_tempo_de_resposta_é_menor_que_segundos(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

}
