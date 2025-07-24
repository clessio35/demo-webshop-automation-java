# 🧪 Automação de Testes - Automation Exercise

Projeto de automação de testes do site [Automation Exercise](https://automationexercise.com/) utilizando **Java 17**,
**Selenium WebDriver**, **Cucumber**, **JUnit** e para a parte de API, o **RestAssured**.

O projeto também utiliza **Apache POI** para leitura/escrita de planilhas Excel e **Java Faker** para geração de dados dinâmicos.

🔗 Repositório: [github.com/clessio35/demo-webshop-automation-java](https://github.com/clessio35/demo-webshop-automation-java)

---

## 🚀 Objetivo

Automatizar testes de interface e API com foco em:

- ✅ Escrita de cenários em linguagem Gherkin com Cucumber
- ✅ Testes funcionais automatizados com Selenium
- ✅ Reports com Extent Reports e Cucumber Reports
- ✅ Geração e leitura de dados com Apache POI (Excel)
- ✅ Geração de dados dinâmicos com Java Faker
- ✅ Execução automatizada via GitHub Actions e Jenkins

---

## 🛠️ Tecnologias Utilizadas

| Tecnologia               | Finalidade                                             |
|--------------------------|--------------------------------------------------------|
| Java 17                  | Linguagem base                                         |
| Maven                    | Gerenciador de dependências                            |
| Selenium WebDriver 4.18  | Automação de testes web                                |
| Cucumber 7.12.1          | Testes BDD com cenários em Gherkin                     |
| JUnit 4.13.2             | Execução dos testes                                    |
| Apache POI 5.2.5         | Leitura e escrita de arquivos Excel                    |
| Java Faker 1.0.2         | Geração de dados aleatórios                            |
| WebDriverManager 5.4.1   | Gerenciamento automático dos drivers                   |
| RestAssured 5.2.0        | Testes de API com chamadas HTTP                        |

---

## ▶️ Como Executar Localmente

### ✅ Pré-requisitos

- Java 17 instalado  
- Maven 3.8+ instalado  
- Navegador (ex: Google Chrome)  
- Acesso à internet para baixar as dependências e drivers

### ⬇️ Após clonar o projeto:

> **⚠️ Atenção:** O comando `mvn clean test` executa os testes abrindo múltiplos browsers simultaneamente, o que pode causar lentidão ou travamentos.  
>  
> Para evitar esse comportamento, recomenda-se executar os testes diretamente pela sua IDE via JUnit.

#### Executando os testes via IDE (JUnit)

1. Importe o projeto na sua IDE favorita (IntelliJ, Eclipse, VS Code, etc).  
2. Navegue até as classes de teste em `src/test/java`.  
3. Clique com o botão direito sobre a classe ou método de teste que deseja executar.  
4. Selecione a opção **Run as JUnit Test** (ou equivalente da sua IDE).

Assim, os testes serão executados de forma controlada e sem abrir múltiplos browsers ao mesmo tempo.


## ⚙️ Integração Contínua

- **GitHub Actions**: Workflows definidos para execução automática dos testes em cada push/pull request
- **Jenkins**: Pipeline definido em `Jenkinsfile`, permitindo execuções agendadas ou sob demanda

### 📸 Exemplo de Job no Jenkins

![Jenkins Job](https://github.com/clessio35/demo-webshop-automation-java/blob/main/images/jenkins-job-demo-webshop.png?raw=true)

## 📌 Status do Projeto

✅ **CONCLUÍDO**
