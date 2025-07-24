Feature: Calcular total com desconto e imposto

    
  @calculate
	Scenario: Calcular total correto
	  Given que os preços dos produtos sao 100 e 50
	  And o desconto é 10%
	  And o imposto é 5%
	  When eu calcular o total
	  Then o resultado deve ser 141.75
