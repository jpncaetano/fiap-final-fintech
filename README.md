# 💰 Fintech - Projeto Final FIAP

📌 **Descrição**  
Este projeto foi desenvolvido como parte da **avaliação final do primeiro ano do curso de Análise e Desenvolvimento de Sistemas da FIAP**. O objetivo era reunir todos os conceitos e tecnologias aprendidas ao longo do ano para a criação de um sistema completo para uma **Fintech**.

O sistema integra **backend, banco de dados e frontend**, permitindo operações financeiras como gerenciamento de contas, transações, receitas e despesas, além da autenticação de usuários e integração com páginas JSP.

---

## 🚀 **Funcionalidades Implementadas**  

✅ **Gerenciamento de contas bancárias** (criação e consulta de contas).  
✅ **Controle de receitas e despesas** com categorização.  
✅ **Registro e acompanhamento de transações financeiras**.  
✅ **Autenticação e autorização de usuários** para acesso restrito a funcionalidades.  
✅ **Interface Web com JSP e Servlets** para interação do usuário.  
✅ **Banco de dados (configuração pelo usuário, veja abaixo)**.  

---

## 🛠️ **Tecnologias Utilizadas**  

- **Java (Servlets e JSP)**  
- **Banco de Dados Oracle (ou outro compatível, configurável pelo usuário)**  
- **JDBC para conexão com o banco**  
- **DAO Pattern para acesso a dados**  
- **Tomcat para execução do servidor web**  
- **HTML, CSS e Bootstrap para estilização da interface**  

---

## 📁 **Estrutura do Projeto**  

```
📂 java/controller
 │── AccController.java
 │── DespesasController.java
 │── LoginController.java
 │── ReceitasController.java
 │── SignupController.java
 │── ErrorHandler.java
 │── HomeController.java

📂 java/dao
 │── AccDao.java
 │── BankDao.java
 │── TransactionDao.java
 │── UserDao.java

📂 java/exceptions
 │── IdAlreadyExistsException.java

📂 java/factories
 (A classe ConnectionFactory não está presente no repositório, veja abaixo como configurar a conexão.)

📂 java/model
 │── Acc.java
 │── Bank.java
 │── Investment.java
 │── Transaction.java
 │── User.java

📂 webapp
 │── index.jsp
 │── acc/acc.jsp
 │── despesas/despesas.jsp
 │── receitas/receitas.jsp
 │── login.jsp
 │── error.jsp
```

---

## ⚠️ **Sobre a Conexão com o Banco de Dados**  

A classe `ConnectionFactory.java` **não está incluída no repositório** por questões de segurança, pois contém credenciais sensíveis. Para rodar o projeto, siga um dos métodos abaixo para configurar a conexão com um banco de sua escolha.

### 📌 **Opção 1: Criar manualmente a classe `ConnectionFactory.java`**

Crie o arquivo `src/main/java/factories/ConnectionFactory.java` com o seguinte código:
```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public static Connection getConnection() throws SQLException {
        String url = System.getenv("DB_URL"); // O usuário deve configurar sua própria URL do banco
        String usuario = System.getenv("DB_USER");
        String senha = System.getenv("DB_PASSWORD");

        if (url == null || usuario == null || senha == null) {
            throw new IllegalStateException("As variáveis de ambiente DB_URL, DB_USER e DB_PASSWORD não estão configuradas.");
        }

        return DriverManager.getConnection(url, usuario, senha);
    }
}
```
⚠️ **Atenção:** Não suba essa classe para o GitHub! Para evitar isso, ela já está no `.gitignore`.

### 📌 **Opção 2: Definir Variáveis de Ambiente**

Caso queira rodar o projeto sem alterar o código, defina as variáveis de ambiente com a URL do seu banco de dados:

#### **Para Oracle XE localmente**
```sh
export DB_URL="jdbc:oracle:thin:@localhost:1521:XE"
export DB_USER="seu_usuario"
export DB_PASSWORD="sua_senha"
```

#### **Caso esteja usando outro banco remoto**
```sh
export DB_URL="jdbc:oracle:thin:@SEU_HOST:1521:SEU_BANCO"
export DB_USER="seu_usuario"
export DB_PASSWORD="sua_senha"
```

No **Windows (PowerShell)**:
```powershell
$env:DB_URL="jdbc:oracle:thin:@localhost:1521:XE"
$env:DB_USER="seu_usuario"
$env:DB_PASSWORD="sua_senha"
```

---

## 🔧 **Como Executar o Projeto**  

1️⃣ **Clone o repositório**  
   ```sh
   git clone https://github.com/jpncaetano/fiap-fintech-final.git
   cd fiap-fintech-final
   ```

2️⃣ **Configure a conexão com o banco de dados** (veja acima).  

3️⃣ **Compile e execute o projeto no Tomcat**  
   - Abra o projeto no **IntelliJ IDEA** ou **Eclipse**.  
   - Configure o servidor Tomcat.  
   - Rode a aplicação e acesse pelo navegador:  
     ```
     http://localhost:8080/fintech
     ```

---

## 📌 **Possíveis Melhorias Futuras**  

🔹 Implementação de testes unitários para validação do sistema.  
🔹 Melhorias na segurança da autenticação.  
🔹 Criação de relatórios financeiros detalhados para usuários.  
🔹 Implementação de APIs REST para integração com outros serviços.  

---

## 📜 **Licença e Uso**  

Este projeto foi desenvolvido exclusivamente para **fins acadêmicos na FIAP**. O código pode ser utilizado como referência, mas não deve ser utilizado comercialmente sem as devidas adaptações.

📩 **Contato:** Caso tenha dúvidas ou sugestões, entre em contato pelo GitHub.  

---

### 🔥 **Resumo Final**  

Este projeto representa a **consolidação de todos os conhecimentos adquiridos ao longo do curso na FIAP**, implementando um sistema realista de **gestão financeira digital**. Ele integra frontend e backend, garantindo um fluxo completo de **cadastro, transações e controle financeiro** para usuários autenticados.
