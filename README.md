# Automação de Testes - Demo Web Shop

Projeto de automação de testes end-to-end de um e-commerce, o site: [Demo Web Shop](https://demowebshop.tricentis.com/) utilizando **Java 17**,
**Selenium WebDriver**, **Cucumber** e **JUnit**.  

O projeto também faz uso de **Apache POI** para leitura/escrita de planilhas Excel e **Java Faker** para geração de dados dinâmicos.

🔗 Repositório: [github.com/clessio35/demo-webshop-automation-java](https://github.com/clessio35/demo-webshop-automation-java)

---

## 🚀 Objetivo

Automatizar testes de interface web com foco em:

- ✅ Testes funcionais automatizados com Selenium
- ✅ Escrita de cenários em linguagem Gherkin com Cucumber
- ✅ Execução com JUnit 4 e Cucumber Runner
- ✅ Geração e leitura de dados com Apache POI (Excel)
- ✅ Geração de dados dinâmicos com Java Faker
- ✅ Execução automatizada via GitHub Actions e Jenkins

---

## 🛠️ Tecnologias Utilizadas

| Tecnologia               | Finalidade                                              |
|--------------------------|--------------------------------------------------------|
| Java 17                  | Linguagem base                                         |
| Maven                    | Gerenciador de dependências                            |
| Selenium WebDriver 4.18  | Automação de testes web                                |
| Cucumber 7.12.1          | Testes BDD com escrita de cenários em Gherkin          |
| JUnit 4.13.2             | Framework de execução dos testes                       |
| Apache POI 5.2.5         | Leitura e escrita de arquivos Excel                    |
| Java Faker 1.0.2         | Geração de dados dinâmicos                             |
| WebDriverManager 5.4.1   | Gerenciamento automático dos drivers dos navegadores   |

---

## ▶️ Como Executar Localmente

### Pré-requisitos

- Java 17 instalado  
- Maven 3.8+ instalado  
- Navegador (ex: Chrome)  
- Internet para download automático do WebDriver

### Comando de execução

```bash
mvn clean test


```

> Os testes são executados via `JUnit` com os arquivos de runner que seguem o padrão `Run*.java`.

---

## ⚙️ Integração Contínua

- **GitHub Actions**: Workflows definidos para execução automática dos testes em cada push/pull request
- **Jenkins**: Pipeline definido em `Jenkinsfile`, permitindo execuções agendadas ou sob demanda

### 📸 Exemplo de Job no Jenkins

### 📸 Exemplo de Job no Jenkins

![Jenkins Job](https://github.com/clessio35/demo-webshop-automation-java/blob/main/images/jenkins-job-demo-webshop.png?raw=true)


```

---

## 📌 Status do Projeto

✅ **CONCLUÍDO**
