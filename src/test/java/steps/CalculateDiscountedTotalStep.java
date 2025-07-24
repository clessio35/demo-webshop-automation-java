package steps;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.MetodosUtils;

public class CalculateDiscountedTotalStep {
	
	List<Double> products;
	double desc;
	double impo;
	double result;

	@Given("que os preços dos produtos sao {double} e {double}")
	public void que_os_preços_dos_produtos_sao_e(Double preco1, Double preco2) {
		products = Arrays.asList(preco1, preco2);
	}

	@Given("o desconto é {double}%")
	public void o_desconto_é(Double valor) {
	    desc = valor;
	}

	@Given("o imposto é {double}%")
	public void o_imposto_é(Double valor) {
	    impo = valor;
	}

	@When("eu calcular o total")
	public void eu_calcular_o_total() {
	    result = MetodosUtils.calculateDiscountedTotal(products, desc, impo);
	}

	@Then("o resultado deve ser {double}")
	public void o_resultado_deve_ser(Double resultEsperado) {
		Assert.assertEquals(resultEsperado, result, 0.001);
	}
}
